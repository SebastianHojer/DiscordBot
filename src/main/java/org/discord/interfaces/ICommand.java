package org.discord.interfaces;

public interface ICommand {
    void execute(ExecuteArgs var1);

    String getName();

    String helpMessage();

    boolean needOwner();
}
