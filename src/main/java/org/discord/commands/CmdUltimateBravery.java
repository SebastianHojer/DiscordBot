package org.discord.commands;

import net.dv8tion.jda.api.entities.Message;
import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;
import org.discord.interfaces.JDACommands;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class CmdUltimateBravery implements ICommand {
    private WebDriver driver = JDACommands.webDriver;

    @Override
    public void execute(ExecuteArgs var1) {
        var1.getEvent().reply(" - ").queue();
        System.out.println("Done waiting ..");
        // Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        driver.navigate().to("https://www.ultimate-bravery.net/Tft");
        System.out.println("saving file.. ");
        // Close the driver

        var1.getEvent().getTextChannel().sendMessage("Du fik følgende: ").addFile(screenshot).queue();
    }

    @Override
    public String getName() {
        return "UltimateBravery";
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
