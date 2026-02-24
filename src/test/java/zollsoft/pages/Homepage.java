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
import java.util.List;


public class Homepage {

    private WebDriver driver = Driver.getDriver();
    private static final Logger log = LogManager.getLogger(Homepage.class);

    public Homepage() {
        PageFactory.initElements(driver, this);
    }

    // Elemente
    private By cookies = By.xpath("//button[contains(@class, 'accept-only-essential')]");
    private By meineKarriereButton = By.xpath("//a[@class='et_pb_button et_pb_button_3 karriere-button et_pb_bg_layout_light']");

    // Methods
    public void removeCookies() {
        try {
            ReusableMethods.clickElement(cookies);
            log.info("Cookies wurden erfolgreich entfernt.");
        } catch (Exception e) {
            log.warn("Cookie-Banner nicht gefunden oder bereits geschlossen.");
        }
    }

    public void klickeNacheinanderAufProdukte(List<String> produktListe) {
        JavascriptUtils.seiteLangsamNachUntenScrollen(driver,14);
        for (String produktName : produktListe) {
            By produktBy = By.xpath("//li[@class='app-item']/a[contains(text(),'" + produktName + "')]");
            try {
                JavascriptUtils.scrollToVisibleElement(driver, produktBy);
                JavascriptUtils.changeBackgroundColorByJS(produktBy,"yellow");
                JavascriptUtils.clickElementByJS(produktBy);
                ReusableMethods.waitForSeconds(1);
                log.info("Produkt '{}' wurde erfolgreich angeklickt.", produktName);
            } catch (Exception e) {
                log.error("Fehler beim Klicken auf das Produkt '{}': {}", produktName, e.getMessage());
                Assert.fail("Das Produkt '" + produktName + "' konnte nicht gefunden werden.");
            }
        }
    }

    public void klickMeineKarriereButton() {
        try {
            JavascriptUtils.seiteLangsamNachUntenScrollen(driver,14);
            ReusableMethods.waitForElementToBeClickable(driver, meineKarriereButton, 10);
            ReusableMethods.clickElementByJS(meineKarriereButton);
            log.info("Button 'Meine Karriere' wurde angeklickt.");
        } catch (Exception e) {
            log.error("Button 'Meine Karriere' konnte nicht geklickt werden.", e);
            Assert.fail("Karriere-Button nicht klickbar.");
        }
    }


}

