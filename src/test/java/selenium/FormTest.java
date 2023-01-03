package selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String USER_OS = System.getProperty("os.name");
    private static final String SYSTEM_ARCHITECTURE = System.getProperty("os.arch");
    private static final String MAC_INTEL_DRIVER = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "macos" + File.separator + "chromedriver_mac_intel";
    private static final String MAC_ARM_DRIVER = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "macos" + File.separator + "chromedriver_mac_arm";
    private static final String LINUX_DRIVER = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "linux" + File.separator + "chromedriver_linux";
    private static final String WINDOWS_DRIVER = System.getProperty("user.dir") + File.separator + "drivers" + File.separator + "windows" + File.separator + "chromedriver_windows.exe";

    @BeforeAll
    static void init() {
        if (USER_OS.startsWith("Mac") && "x86_64".equals(SYSTEM_ARCHITECTURE)) System.setProperty("webdriver.chrome.driver", MAC_INTEL_DRIVER);
        else if (USER_OS.startsWith("Windows")) System.setProperty("webdriver.chrome.driver", WINDOWS_DRIVER);
        else if (USER_OS.startsWith("Linux")) System.setProperty("webdriver.chrome.driver", LINUX_DRIVER);
        else System.setProperty("webdriver.chrome.driver", MAC_ARM_DRIVER);
    }

    @BeforeEach
    void getFormPage() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLScVG7idLWR8sxNQygSnLuhehUNVFti0FnVviWCSjDh-JNhsMA/viewform");
    }

    @AfterEach
    void terminate() {
        driver.close();
    }

    @Test
    @DisplayName("Test valid form submission")
    void testFormSubmissionWithValidData() {
        // find and click on choice where text is "Option 3"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text() = 'Option 3']")))).click();

        // find element where data-param contains "Name" and find input element with type text within it
        var nameSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Name\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(nameSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("John");

        // find element where data-param contains "Email" and find input element with type text within it
        var emailSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Email\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(emailSection.findElement(By.xpath(".//input[@type='email']")))).sendKeys("test@example.com");

        // find element where data-param contains "Address" and find input element with type text within it
        var addressSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Address\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addressSection.findElement(By.xpath(".//textarea")))).sendKeys("Turu 2, 51004 Tartu");

        // find element where data-param contains "Phone number" and find input element with type text within it
        var phoneSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Phone number\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(phoneSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("+37211111111");

        // find element where data-param contains "Comments" and find input element with type text within it
        var commentsSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Comments\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(commentsSection.findElement(By.xpath(".//textarea")))).sendKeys("Lorem ipsum dolor sit amet");

        // find div element where role attribute is "button" and locate element within it where
        // text value is "Submit" or "Saada ära" and click on it
        var submitButton = driver.findElement((By.xpath("//div[@role='button' and .//*[contains(text(), 'Submit') or contains(text(), 'Saada ära')]]")));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        assertTrue(driver.getPageSource().contains("Thanks for submitting your contact info!"));
    }

    @Test
    @DisplayName("Test form submission missing name")
    void testFormSubmissionWithMissingName() {
        // find and click on choice where text is "Option 3"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text() = 'Option 3']")))).click();

        // find element where data-param contains "Name" and find input element with type text within it
        var nameSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Name\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(nameSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("");

        // find element where data-param contains "Email" and find input element with type text within it
        var emailSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Email\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(emailSection.findElement(By.xpath(".//input[@type='email']")))).sendKeys("test@example.com");

        // find element where data-param contains "Address" and find input element with type text within it
        var addressSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Address\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addressSection.findElement(By.xpath(".//textarea")))).sendKeys("Turu 2, 51004 Tartu");

        // find element where data-param contains "Phone number" and find input element with type text within it
        var phoneSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Phone number\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(phoneSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("+37211111111");

        // find element where data-param contains "Comments" and find input element with type text within it
        var commentsSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Comments\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(commentsSection.findElement(By.xpath(".//textarea")))).sendKeys("Lorem ipsum dolor sit amet");

        // find div element where role attribute is "button" and locate element within it where
        // text value is "Submit" or "Saada ära" and click on it
        var submitButton = driver.findElement((By.xpath("//div[@role='button' and .//*[contains(text(), 'Submit') or contains(text(), 'Saada ära')]]")));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        var alertElement = nameSection.findElement(By.xpath(".//div[@role='alert']"));
        assertTrue(alertElement.isDisplayed()
                && (alertElement.getText().contains("See on kohustuslik küsimus") || alertElement.getText().contains("This is a required question"))
        );
    }

    @Test
    @DisplayName("Test form submission invalid email")
    void testFormSubmissionWithInvalidEmail() {
        // find and click on choice where text is "Option 3"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text() = 'Option 3']")))).click();

        // find element where data-param contains "Name" and find input element with type text within it
        var nameSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Name\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(nameSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("John");

        // find element where data-param contains "Email" and find input element with type text within it
        var emailSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Email\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(emailSection.findElement(By.xpath(".//input[@type='email']")))).sendKeys("test");

        // find element where data-param contains "Address" and find input element with type text within it
        var addressSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Address\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addressSection.findElement(By.xpath(".//textarea")))).sendKeys("Turu 2, 51004 Tartu");

        // find element where data-param contains "Phone number" and find input element with type text within it
        var phoneSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Phone number\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(phoneSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("+37211111111");

        // find element where data-param contains "Comments" and find input element with type text within it
        var commentsSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Comments\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(commentsSection.findElement(By.xpath(".//textarea")))).sendKeys("Lorem ipsum dolor sit amet");

        // find div element where role attribute is "button" and locate element within it where
        // text value is "Submit" or "Saada ära" and click on it
        var submitButton = driver.findElement((By.xpath("//div[@role='button' and .//*[contains(text(), 'Submit') or contains(text(), 'Saada ära')]]")));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        var alertElement = emailSection.findElement(By.xpath(".//div[@role='alert']"));
        assertTrue(alertElement.isDisplayed()
                && (alertElement.getText().contains("Please enter a valid email address"))
        );
    }

    @Test
    @DisplayName("Test form submission missing email")
    void testFormSubmissionWithMissingEmail() {
        // find and click on choice where text is "Option 3"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text() = 'Option 3']")))).click();

        // find element where data-param contains "Name" and find input element with type text within it
        var nameSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Name\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(nameSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("John");

        // find element where data-param contains "Email" and find input element with type text within it
        var emailSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Email\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(emailSection.findElement(By.xpath(".//input[@type='email']")))).sendKeys("");

        // find element where data-param contains "Address" and find input element with type text within it
        var addressSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Address\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addressSection.findElement(By.xpath(".//textarea")))).sendKeys("Turu 2, 51004 Tartu");

        // find element where data-param contains "Phone number" and find input element with type text within it
        var phoneSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Phone number\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(phoneSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("+37211111111");

        // find element where data-param contains "Comments" and find input element with type text within it
        var commentsSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Comments\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(commentsSection.findElement(By.xpath(".//textarea")))).sendKeys("Lorem ipsum dolor sit amet");

        // find div element where role attribute is "button" and locate element within it where
        // text value is "Submit" or "Saada ära" and click on it
        var submitButton = driver.findElement((By.xpath("//div[@role='button' and .//*[contains(text(), 'Submit') or contains(text(), 'Saada ära')]]")));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        var alertElement = emailSection.findElement(By.xpath(".//div[@role='alert']"));
        assertTrue(alertElement.isDisplayed()
                && (alertElement.getText().contains("See on kohustuslik küsimus") || alertElement.getText().contains("Please enter a valid email address"))
        );
    }

    @Test
    @DisplayName("Test form submission missing address")
    void testFormSubmissionWithMissingAddress() {
        // find and click on choice where text is "Option 3"
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[text() = 'Option 3']")))).click();

        // find element where data-param contains "Name" and find input element with type text within it
        var nameSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Name\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(nameSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("John");

        // find element where data-param contains "Email" and find input element with type text within it
        var emailSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Email\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(emailSection.findElement(By.xpath(".//input[@type='email']")))).sendKeys("test@example.com");

        // find element where data-param contains "Address" and find input element with type text within it
        var addressSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Address\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(addressSection.findElement(By.xpath(".//textarea")))).sendKeys("");

        // find element where data-param contains "Phone number" and find input element with type text within it
        var phoneSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Phone number\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(phoneSection.findElement(By.xpath(".//input[@type='text']")))).sendKeys("+37211111111");

        // find element where data-param contains "Comments" and find input element with type text within it
        var commentsSection = driver.findElement(By.xpath("//*[contains(@data-params, '\"Comments\"')]"));
        wait.until(ExpectedConditions.elementToBeClickable(commentsSection.findElement(By.xpath(".//textarea")))).sendKeys("Lorem ipsum dolor sit amet");

        // find div element where role attribute is "button" and locate element within it where
        // text value is "Submit" or "Saada ära" and click on it
        var submitButton = driver.findElement((By.xpath("//div[@role='button' and .//*[contains(text(), 'Submit') or contains(text(), 'Saada ära')]]")));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();

        var alertElement = addressSection.findElement(By.xpath(".//div[@role='alert']"));
        assertTrue(alertElement.isDisplayed()
                && (alertElement.getText().contains("See on kohustuslik küsimus") || alertElement.getText().contains("This is a required question"))
        );
    }

}
