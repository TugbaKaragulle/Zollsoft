package zollsoft.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import zollsoft.utilities.Driver;
import zollsoft.utilities.JavascriptUtils;
import zollsoft.utilities.ReusableMethods;

import static zollsoft.utilities.JavascriptUtils.changeBackgroundColorByJS;

public class JobsKarrierePage {

    private WebDriver driver = Driver.getDriver();
    private static final Logger log = LogManager.getLogger(JobsKarrierePage.class);

    public JobsKarrierePage() {
        PageFactory.initElements(driver, this);
    }

    // Elemente

    private By jetztBewerben = By.xpath("(//a[.='Jetzt bewerben'])[1]");


    // Methods

    public By getJobTitelLocator(String job) {
        return By.xpath(String.format("//a[contains(text(),'%s')]", job));
    }

    public void klickQualitaetssicherung(String bereich) { // 'Qualitätssicherung' -> 'Qualitaetssicherung'
        try {
            JavascriptUtils.seiteLangsamNachUntenScrollen(driver,33);
            JavascriptUtils.seiteLangsamNachObenScrollen(driver,45);

            By bereichBy = By.xpath("//li[@class='cat-title']/a[contains(text(), '" + bereich + "')]");
            ReusableMethods.waitForElementToBeClickable(driver, bereichBy, 10);
            changeBackgroundColorByJS(bereichBy,"yellow");
            ReusableMethods.clickElement(bereichBy);

            log.info("Bereich '{}' wurde erfolgreich ausgewählt.", bereich);
        } catch (Exception e) {
            log.error("Bereich '{}' konnte nicht ausgewählt werden.", bereich, e);
            Assert.fail("Fehler bei der Bereichsauswahl: " + bereich);
        }
    }

    public void klickJobTitel(String jobTitelName){
        try {
            log.info("Prüfe Sichtbarkeit für Job-Titel: '{}'", jobTitelName);
            JavascriptUtils.seiteLangsamNachObenScrollen(driver,20);
            JavascriptUtils.scrollToVisibleElement(driver,getJobTitelLocator(jobTitelName));
            ReusableMethods.waitForElementToBeClickable(driver,getJobTitelLocator(jobTitelName),10);

            if (!driver.findElements(getJobTitelLocator(jobTitelName)).isEmpty()) {
                changeBackgroundColorByJS(getJobTitelLocator(jobTitelName),"yellow");
                ReusableMethods.clickElement(getJobTitelLocator(jobTitelName));
                log.info("Erfolg: Job-Titel '{}' wurde angeklickt.", jobTitelName);
                //ReusableMethods.waitForSeconds(1);

            } else {
                log.warn("Warnung: Job-Titel '{}' wurde nicht gefunden.", jobTitelName);
                Assert.fail("Job-Titel '" + jobTitelName + "' existiert nicht auf der Seite.");
            }
        } catch (Exception e) {
            log.error("Fehler: Job-Titel '{}' konnte nicht verarbeitet werden. Grund: {}", jobTitelName, e.getMessage());
            Assert.fail("Fehler beim Klicken auf den Job: " + e.getMessage());
        }
    }

    public void klickJetztBewerben() {
        try {
            ReusableMethods.waitForElementToBeClickable(driver, jetztBewerben, 10);
            changeBackgroundColorByJS(jetztBewerben,"yellow");
            ReusableMethods.clickElement(jetztBewerben);
            log.info("'Jetzt bewerben' Button wurde geklickt.");
        } catch (Exception e) {
            log.error("'Jetzt bewerben' Button konnte nicht geklickt werden.", e);
            Assert.fail("Bewerbungs-Button nicht gefunden.");
        }
    }
}