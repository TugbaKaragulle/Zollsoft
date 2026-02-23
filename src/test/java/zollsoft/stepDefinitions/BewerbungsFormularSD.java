package zollsoft.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import zollsoft.pages.AllPages;

public class BewerbungsFormularSD {

AllPages allPages = new AllPages();


    @When("Füllt das Bewerbungsformular aus")
    public void füllt_das_bewerbungsformular_aus() {
        allPages.getBewerbungsFormularPage().fillForm();
    }
    @Then("Prüft, ob die Schaltfläche zum Absenden klickbar ist")
    public void prüft_ob_die_schaltfläche_zum_absenden_klickbar_ist() {
        Assert.assertTrue(allPages.getBewerbungsFormularPage().isSendButtonClickable());
    }

}
