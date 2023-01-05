package org.discord.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {

    private static PlayerManager INSTANCE;
    private final Map<Long, GuildMusicManager> musicManagers;
    private final AudioPlayerManager audioPlayerManager;

    public PlayerManager(){
        this.musicManagers = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }

    public GuildMusicManager getMusicManager(Guild guild){
        return this.musicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            final GuildMusicManager guildMusicManager = new GuildMusicManager(this.audioPlayerManager);
            guild.getAudioManager().setSendingHandler(guildMusicManager.getSendHandler());
            return guildMusicManager;
        });
    }

    public void loadAndPlay(TextChannel textChannel, String trackURL){
        final GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());
        this.audioPlayerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack audioTrack) {
                musicManager.scheduler.queue(audioTrack);

                textChannel.sendMessage("Tilføjer til køen: ").append(audioTrack.getInfo().title).append(" af ").append(audioTrack.getInfo().author).queue();
            }

            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {
                final List<AudioTrack> tracks = audioPlaylist.getTracks();
                if(!tracks.isEmpty()){
                    musicManager.scheduler.queue(tracks.get(0));
                    textChannel.sendMessage("Tilføjer: ").append(tracks.get(0).getInfo().title).append(" af ").append(tracks.get(0).getInfo().author).queue();
                }
            }

            @Override
            public void noMatches() {
                textChannel.sendMessage("Intet fundet ved: " + trackURL).queue();
            }

            @Override
            public void loadFailed(FriendlyException e) {
                textChannel.sendMessage("Could not play: " + e.getMessage()).queue();
            }
        });
    }

    public static PlayerManager getINSTANCE(){
        if(INSTANCE==null){
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }

    public void skipTrack(TextChannel textChannel) {
        GuildMusicManager musicManager = this.getMusicManager(textChannel.getGuild());
        musicManager.scheduler.nextTrack();

        textChannel.sendMessage("Skippede til næste sang").queue();
    }
}
