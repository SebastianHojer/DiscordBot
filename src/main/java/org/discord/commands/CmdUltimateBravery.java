package org.discord.commands;

import net.dv8tion.jda.api.entities.Message;
import org.discord.interfaces.ExecuteArgs;
import org.discord.interfaces.ICommand;
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
    @Override
    public void execute(ExecuteArgs var1) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bruger\\Desktop\\GeckoDriver\\chromedriver.exe");

        // Create a new instance of the Chrome driver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);

        driver.manage().window().setSize(new Dimension(900, 700));
        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the webpage
        String url = "https://www.ultimate-bravery.net/Tft";
        driver.get(url);
        System.out.println("About to wait ...");
        // Wait for the page to fully load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        //WebElement element = driver.findElement(By.className("col-md-4"));
        //((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", element);
        System.out.println("Done waiting ..");
        // Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        System.out.println("saving file.. ");
        // Close the driver
        driver.quit();

        var1.getEvent().reply(" - ").queue();
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
