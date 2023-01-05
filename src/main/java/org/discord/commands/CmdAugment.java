package org.discord.commands;

import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;

public class CmdAugment implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        int random = (int) ((Math.random()*3)+1);
        event.getEvent().reply("Du skal vælge augment nummer: " + random).queue();
    }

    @Override
    public String getName() {
        return "augment";
    }

    @Override
    public String helpMessage() {
        return "Denne giver et random augment nummer";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
