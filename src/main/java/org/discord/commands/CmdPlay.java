package org.discord.commands;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;
import org.discord.lavaplayer.PlayerManager;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class CmdPlay implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        if(!event.getMemberVoiceState().inAudioChannel()){
            event.getTextChannel().sendMessage("Du skal være i en stemmekanal før dette virker.").queue();
            return;
        }
        if(!event.getSelfVoiceState().inAudioChannel()){
            final AudioManager audioManager = event.getGuild().getAudioManager();
            final VoiceChannel memberChannel = (VoiceChannel) event.getMemberVoiceState().getChannel();

            audioManager.openAudioConnection(memberChannel);
        }

        String link = String.join(" ", event.getArgs());
        System.out.println(link);
        String[] isLink = link.split(" ");
        System.out.println(isLink[1]);

        if(!isUrl(isLink[1])){
            link = "ytsearch:" + link + " audio";
        }

        PlayerManager.getINSTANCE().loadAndPlay(event.getTextChannel(), link);
    }

    private boolean isUrl(String link) {
        try {
            System.out.println(new URI(link));
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    @Override
    public String getName() {
        return "play";
    }

    @Override
    public String helpMessage() {
        return "Denne kommando er til at afspille musik";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
