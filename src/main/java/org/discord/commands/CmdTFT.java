package org.discord.commands;

import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;

import java.util.ArrayList;

public class CmdTFT implements ICommand {


    @Override
    public void execute(ExecuteArgs event) {
        String[] originArray = {"Astral", "Dragon", "Darkflight", "Guild", "Jade", "Lagoon", "Mirage", "Ragewing", "Scalescorn", "Shimmerscale", "Tempest", "Whispers"};
        String[] classArray = {"Assassin", "Bruiser", "Cannoneer",  "Cavalier", "Dragonmancer", "Evoker", "Guardian", "Mage", "Mystic", "Shapeshifter", "Swiftshot", "Warrior"};
        int randomOrigin = (int) (Math.random()*12);
        int randomClass = (int) (Math.random()*12);
        String finalRoll = "" + originArray[randomOrigin] + ", " + classArray[randomClass];
        event.getTextChannel().sendMessage("Du fik: " + finalRoll).queue();
    }

    @Override
    public String getName() {
        return "TFT";
    }

    @Override
    public String helpMessage() {
        return "Rul et random trait";
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
