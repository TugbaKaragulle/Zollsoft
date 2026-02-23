package zollsoft.stepDefinitions;

import io.cucumber.java.en.Given;
import zollsoft.pages.AllPages;
import zollsoft.utilities.ConfigReader;
import zollsoft.utilities.Driver;
import zollsoft.utilities.ReusableMethods;


public class CommonSD {

    AllPages allPages =new AllPages();

    @Given("Der Benutzer geht zur Startseite")
    public void der_benutzer_geht_zur_startseite() {
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        ReusableMethods.waitForVisibilityOfTitle("zollsoft");
    }

    @Given("Entfernt das Cookies-Banner")
    public void entfernt_das_cookies_banner() {
        allPages.getHomePage().removeCookies();
        ReusableMethods.waitForSeconds(3);
    }

}
