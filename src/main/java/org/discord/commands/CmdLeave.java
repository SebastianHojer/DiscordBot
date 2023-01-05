package org.discord.commands;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;
import org.discord.lavaplayer.PlayerManager;

public class CmdLeave implements ICommand {

    @Override
    public void execute(ExecuteArgs event) {
        if (!event.getMemberVoiceState().inAudioChannel()) {
            event.getTextChannel().sendMessage("Du skal være i en stemmekanal før dette virker.").queue();
            return;
        }
        if (!event.getSelfVoiceState().inAudioChannel()) {
            final AudioManager audioManager = event.getGuild().getAudioManager();

            audioManager.closeAudioConnection();
        }
    }
    @Override
    public String getName() {
        return "leave";
    }

    @Override
    public String helpMessage() {
        return "Denne kommando er til at få botten til at leave";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}

