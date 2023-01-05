package org.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.discord.commands.*;
import org.discord.events.EventJoin;
import org.discord.interfaces.JDACommands;

import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class Main {

    public static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES};
    public static JDACommands jdaCommands;

    public static void main(String[] args) throws LoginException {
        jdaCommands = new JDACommands();
        jdaCommands.registerCommand(new CmdPlay());
        jdaCommands.registerCommand(new CmdSkip());
        jdaCommands.registerCommand(new CmdLeave());
        jdaCommands.registerCommand(new CmdTFT());
        jdaCommands.registerCommand(new CmdAugment());
        jdaCommands.registerCommand(new CmdEloRip());
        jdaCommands.registerCommand(new CmdHelp());
        jdaCommands.registerCommand(new CmdLittleLegend());
        jdaCommands.registerCommand(new CmdUltimateBravery());

        JDA jda = null;
        try {
            jda = JDABuilder.createDefault("MTAzMDEzNDgxMzQxNzU0MTYzMg.GzWR_j.w_XeEvqt8nWR7-iBJ0hZsSKIShfxuiTobRZiqQ", Arrays.asList(INTENTS)).enableCache(CacheFlag.VOICE_STATE).setActivity(Activity.listening("Top 5 fortnite youtubers who've sworn in their videos")).addEventListeners(new EventJoin()).addEventListeners(jdaCommands).addEventListeners(new CmdSlash()).build().awaitReady();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Guild guild = jda.getGuildById("948565303393149038");
        if(guild != null){
        guild.upsertCommand("slash", "slash").queue();
        guild.upsertCommand("play", "play").addOption(OptionType.STRING, "link", "Play the chosen link").queue();
        guild.upsertCommand("littlelegend", "Random Little legend").queue();
        guild.upsertCommand("ultimatebravery", "Få random TFT comp").queue();
        guild.upsertCommand("help", "help").queue();
        guild.upsertCommand("augment", "Random augment fra 1-3").queue();
        guild.upsertCommand("elorip", "Elo rip").queue();

        }
    }
}
