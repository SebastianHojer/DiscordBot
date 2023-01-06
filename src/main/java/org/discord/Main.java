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
import org.discord.token.Token;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.security.auth.login.LoginException;
import java.time.Duration;
import java.util.Arrays;

public class Main {

    public static GatewayIntent[] INTENTS = {GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES};
    public static JDACommands jdaCommands;

    public static void main(String[] args) throws LoginException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bruger\\Desktop\\GeckoDriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().setSize(new Dimension(800, 700));
        String url = "https://www.ultimate-bravery.net/Tft";
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ub-champion-portrait")));
        System.out.println("Driver is ready");
        jdaCommands = new JDACommands(driver);
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
            jda = JDABuilder.createDefault(Token.token, Arrays.asList(INTENTS)).enableCache(CacheFlag.VOICE_STATE).setActivity(Activity.listening("Top 5 fortnite youtubers who've sworn in their videos")).addEventListeners(new EventJoin()).addEventListeners(jdaCommands).addEventListeners(new CmdSlash()).build().awaitReady();
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
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                driver.close();
            }
        }, "Shutdown-thread"));
    }
}
