package support;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WhoAreWePageSupport {
    private static final String COMPANY_VALUES_XPATH = "//*[@id=\"tdtop\"]/div[2]/div[3]/div";
    private static final String COMPANY_VALUES_CLASS_NAME = "text-two-5";
    private static final String ANSWER_ATMOSPHERE_VIDEO_ID = "answerDigitalVideo";
    private static final String URL = "http://www.answerdigital.com/who-we-are.html";

    private WebDriver driver;

    public WhoAreWePageSupport() {
        driver = DriverSupport.getDriver();
    }

    public void navigateToPage() {
        driver.get(URL);
    }

    public List<String> getCompanyValues() {
        WebElement valuesWebElement = driver.findElement(By.xpath(COMPANY_VALUES_XPATH));
        List<WebElement> valuesList = valuesWebElement.findElements(By.className(COMPANY_VALUES_CLASS_NAME));
        List<String> values = new ArrayList<>();
        for (WebElement ele : valuesList) {
            values.add(ele.getText());
        }

        return values;
    }

    public void playAnswerAtmosphereVideo() {
        driver.findElement(By.id(ANSWER_ATMOSPHERE_VIDEO_ID)).click();
    }

    // TODO: find way to detect video has played > 1 second
    public void isAnswerAtmosphereVideoPlaying() throws InterruptedException {
        WebElement video = driver.findElement(By.id(ANSWER_ATMOSPHERE_VIDEO_ID));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String script =
                        "var tag = document.createElement('script');\n" +
                        "tag.src = \"//www.youtube.com/player_api\";\n" +
                        "var firstScriptTag = document.getElementsByTagName('script')[0];\n" +
                        "firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);\n" +
                        "var player;\n" +
                        "\n" +
                        "function onYouTubePlayerAPIReady() {\n" +
                        "  // create the global player from the specific iframe (#video)\n" +
                        "  player = new YT.Player('video', {\n" +
                        "    events: {\n" +
                        "      // call this function when player is ready to use\n" +
                        "      'onReady': onPlayerReady\n" +
                        "    }\n" +
                        "  });\n" +
                        "}\n" +
                        "player.pauseVideo()";
        jse.executeScript(script);
    }
}
