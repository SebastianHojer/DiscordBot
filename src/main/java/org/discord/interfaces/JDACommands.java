package org.discord.interfaces;


import java.util.ArrayList;
import java.util.Iterator;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;

public class JDACommands extends ListenerAdapter {
    private ArrayList<ICommand> commands = new ArrayList();
    public static WebDriver webDriver;

    public JDACommands(WebDriver driver) {
        webDriver=driver;
    }

    public ArrayList<ICommand> getCommands() {
        return this.commands;
    }

    public void setCommands(ArrayList<ICommand> commands) {
        this.commands = commands;
    }

    public void registerCommand(ICommand command) {
        this.commands.add(command);
    }

    private void init(SlashCommandInteractionEvent event) {
        Iterator commandIterator = this.commands.iterator();

        ICommand command;
        do {
            if (!commandIterator.hasNext()) {
                event.getChannel().sendMessage("This command wasn't recognized.").queue();
                return;
            }

            command = (ICommand)commandIterator.next();
        } while(!event.getName().split(" ")[0].equalsIgnoreCase(command.getName()));

        if (command.needOwner()) {
            if (event.getMember().isOwner()) {
                command.execute(new ExecuteArgs(event));
            } else {
                event.getChannel().sendMessage("You don't have the required permissions to use this command.");
            }
        } else {
            command.execute(new ExecuteArgs(event));
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
            this.init(event);
    }
}
