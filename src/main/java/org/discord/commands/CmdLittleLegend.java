package org.discord.commands;

import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;

public class CmdLittleLegend implements ICommand {
    @Override
    public void execute(ExecuteArgs var1) {
        String[] allChamps = {"Abyssia",
              "  Ao Shin",
                "Baron",
               " Bellswayer",
               " Bungo",
                "Burno",
          "     Choncc",
           "    Craggle",
         "      Dango",
          "     Dowsie",
            "  Duckbill",
           "    Fenroar",
              " Flutterbug",
        "       Fuwa",
         "      Gloop",
           "    Grizzle",
             "Hauntling",
            "Hushtail",
                "Lightcharger",
           "    Melisma",
             "  Molediver",
              " Nimblefoot",
         "      Nixie",
         "      Ossia",
             "  Paddlemar",
              " Piximander",
           "    Poggles",
           "    Prancie",
             "  Protector",
        "       QiQi",
                "River Sprite",
              " Runespirit",
         "      Shisa",
              " Silverwing",
          "     Squink",
           "    Starmaw",
          "     Tocker",
         "      Umbra",
           "    Whisker",
            "   Furyhorn",};
        int random = (int) (Math.random()*allChamps.length);
        var1.getEvent().reply("Du fik: " + allChamps[random].trim()).queue();
    }

    @Override
    public String getName() {
        return "LittleLegend";
    }

    @Override
    public String helpMessage() {
        return null;
    }

    @Override
    public boolean needOwner() {
        return false;
    }
}
