package org.discord.commands;

import org.discord.Main;
import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;

import java.util.ArrayList;

public class CmdHelp implements ICommand {
    @Override
    public void execute(ExecuteArgs event) {
        ArrayList<ICommand> commands = Main.jdaCommands.getCommands();
        ArrayList<String> commandsAsString = new ArrayList<>();
        for (ICommand command: commands) {
            commandsAsString.add(command.getName());}
        String listString = String.join(", ", commandsAsString);
        event.getEvent().reply("Følgende commands findes: " + listString).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String helpMessage() {
        return "Denne kommando viser en liste af kommandoer";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
