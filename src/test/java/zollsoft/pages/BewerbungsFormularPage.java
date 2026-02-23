package zollsoft.pages;

import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import zollsoft.utilities.Driver;
import zollsoft.utilities.JavascriptUtils;
import zollsoft.utilities.ReusableMethods;

public class BewerbungsFormularPage {

    private WebDriver driver = Driver.getDriver();
    private static final Logger log = LogManager.getLogger(BewerbungsFormularPage.class);
    private Faker faker = new Faker();

    public BewerbungsFormularPage() {
        PageFactory.initElements(driver, this);
    }

    // Elemente
    private By gender = By.xpath("//select[@name='gender']");
    private By academicDegree = By.xpath("//select[@name='custom_attribute_668153']");
    private By firstName = By.xpath("//input[@name='first_name']");
    private By lastName = By.xpath("//input[@name='last_name']");
    private By email = By.xpath("//input[@name='email']");
    private By phone = By.xpath("//input[@name='phone']");
    private By availableFrom = By.xpath("//input[@name='available_from']");
    private By expectedSalary = By.xpath("//input[@name='salary_expectations']");
    private By germanSkills = By.xpath("//select[@name='custom_attribute_3356837']");
    private By kanaele = By.xpath("//select[@name='custom_attribute_668354']");
    private By hiddenInput = By.xpath("(//input[@type='file'])[1]");
   // private By hiddenInputOther = By.xpath("(//input[@type='file'])[2]");
    private By sendApplication = By.xpath("//button[@type='submit']");

    // Methods
    public void fillForm() {
        try {
            JavascriptUtils.scrollToVisibleElement(driver, gender);

            selectGender();
            selectAcademicDegree();
            enterFirstName();
            enterLastName();
            enterEmail();
            enterPhoneNumber();
            enterAvailableFrom();
            enterExpectedSalary();
            selectGermanSkills();
            selectKanaele();
            uploadCV();
            uploadOther();
            JavascriptUtils.scrollToVisibleElement(driver, sendApplication);
            log.info("Bewerbungsformular wurde erfolgreich und vollständig ausgefüllt.");

        } catch (Exception e) {
            log.error("Fehler beim Ausfüllen des Formulars: ", e);
            Assert.fail("Formular konnte nicht komplett ausgefüllt werden.");
        }
    }

    public void selectGender() {
        JavascriptUtils.changeBackgroundColorByJS(gender,"yellow");
        new Select(driver.findElement(gender)).selectByVisibleText("Female");
        log.info("Geschlecht ausgewählt.");
    }

    public void selectAcademicDegree() {
        JavascriptUtils.changeBackgroundColorByJS(academicDegree,"yellow");
        new Select(driver.findElement(academicDegree)).selectByVisibleText("Master");
        log.info("Akademischer Grad ausgewählt.");
    }

    public void enterFirstName() {
        JavascriptUtils.changeBackgroundColorByJS(firstName,"yellow");
        ReusableMethods.sendKeys(firstName, faker.name().firstName());
        log.info("Vorname eingegeben.");
    }

    public void enterLastName() {
        JavascriptUtils.changeBackgroundColorByJS(lastName,"yellow");
        ReusableMethods.sendKeys(lastName, faker.name().lastName());
        log.info("Nachname eingegeben.");
    }

    public void enterEmail() {
        JavascriptUtils.changeBackgroundColorByJS(email,"yellow");
        ReusableMethods.sendKeys(email, faker.internet().emailAddress());
        log.info("Email eingegeben.");
    }

    public void enterPhoneNumber() {
        JavascriptUtils.changeBackgroundColorByJS(phone,"yellow");
        ReusableMethods.sendKeys(phone, faker.phoneNumber().cellPhone());
        log.info("Telefonnummer eingegeben.");
    }

    public void enterAvailableFrom() {
        JavascriptUtils.changeBackgroundColorByJS(availableFrom,"yellow");
        ReusableMethods.sendKeys(availableFrom, "Ab sofort");
        log.info("Startdatum eingegeben.");
    }

    public void enterExpectedSalary() {
        JavascriptUtils.changeBackgroundColorByJS(expectedSalary,"yellow");
        ReusableMethods.sendKeys(expectedSalary, "45.000");
        log.info("Gehaltsvorstellung eingegeben.");
    }

    public void selectGermanSkills() {
        JavascriptUtils.changeBackgroundColorByJS(germanSkills,"yellow");
        new Select(driver.findElement(germanSkills)).selectByVisibleText("C1");
        log.info("Deutschkenntnisse ausgewählt.");
    }

    public void selectKanaele() {
        JavascriptUtils.changeBackgroundColorByJS(kanaele,"yellow");
        new Select(driver.findElement(kanaele)).selectByVisibleText("Bundesagentur für Arbeit/Jobcenter");
        log.info("Kanal ausgewählt.");
    }

    public void uploadCV() {
        try {
            String filePath = System.getProperty("user.dir") + "/zollsoft.docx";
            WebElement fileInput = ReusableMethods.waitForElementToBePrecense(driver, hiddenInput,10);
            fileInput.sendKeys(filePath);
            ReusableMethods.waitForSeconds(1);
            log.info("Lebenslauf (CV) erfolgreich hochgeladen.");
        } catch (Exception e) {
            log.error("Fehler beim Hochladen des Lebenslaufs: ", e);
            Assert.fail("CV konnte nicht hochgeladen werden.");
        }
    }

    public void uploadOther() {
        try {
            String filePath = System.getProperty("user.dir") + "/Dummy Dokument.docx";
            WebElement fileInput = ReusableMethods.waitForElementToBePrecense(driver,hiddenInput,10);
            fileInput.sendKeys(filePath);
            ReusableMethods.waitForSeconds(1);
            log.info("Anderes Dokument erfolgreich hochgeladen.");
        } catch (Exception e) {
            log.warn("Zweites Dokument (Other) konnte nicht hochgeladen werden.", e);
        }
    }

    public boolean isSendButtonClickable() {
        try {
            WebElement sendButton = ReusableMethods.waitForElementToBeClickable(driver,sendApplication,10);
            log.info("Senden-Button ist aktiv und klickbar.");
            return sendButton.isEnabled();
        } catch (Exception e) {
            log.error("Senden-Button ist NICHT klickbar oder nicht gefunden.", e);
            return false;
        }
    }
}