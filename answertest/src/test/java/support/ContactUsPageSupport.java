package support;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ContactUsPageSupport {
    private static final String LI = "li";
    private static final String SEND_QUERY_XPATH = "//*[@id=\"tdtop\"]/div[2]/div[1]/div[1]/form/div[2]/input";
    private static final String VALIDATION_MESSAGES_XPATH = "//*[@id=\"tdtop\"]/div[2]/div[1]/div[1]/form/div[2]/div[3]/ul";
    private static final String NAME_FIELD_XPATH = "//*[@id=\"tdtop\"]/div[2]/div[1]/div[1]/form/div[1]/div[2]/div[2]/input";
    private static final String EMAIL_FIELD_XPATH = "//*[@id=\"tdtop\"]/div[2]/div[1]/div[1]/form/div[1]/div[2]/div[3]/input";
    private static final String MESSAGE_FIELD_XPATH = "//*[@id=\"tdtop\"]/div[2]/div[1]/div[1]/form/div[1]/div[2]/textarea";
    private static final String URL = "http://www.answerdigital.com/contact.html";

    private WebDriver driver;

    public ContactUsPageSupport() {
        driver = DriverSupport.getDriver();
    }

    public void navigateToPage() {
        driver.get(URL);
    }

    public void clickSendQueryButton() {
        driver.findElement(By.xpath(SEND_QUERY_XPATH)).sendKeys(Keys.RETURN);
    }

    public List<String> getValidationMessages() {
        WebElement messagesWebElement = driver.findElement(By.xpath(VALIDATION_MESSAGES_XPATH));
        List<WebElement> messagesList = messagesWebElement.findElements(By.tagName(LI));
        List<String> messages = new ArrayList<>();
        for (WebElement ele : messagesList) {
            messages.add(ele.getText());
        }

        return messages;
    }

    public void populateNameField(String text) {
        driver.findElement(By.xpath(NAME_FIELD_XPATH)).sendKeys(text);
    }

    public void populateEmailField(String text) {
        driver.findElement(By.xpath(EMAIL_FIELD_XPATH)).sendKeys(text);
    }

    public void populateMessage(String text) {
        driver.findElement(By.xpath(MESSAGE_FIELD_XPATH)).sendKeys(text);
    }

}
