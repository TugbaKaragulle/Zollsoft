package zollsoft.stepDefinitions;

import io.cucumber.java.en.*;
import zollsoft.pages.AllPages;
import java.util.List;

public class HomepageSD {

    AllPages allPages =new AllPages();


    @When("Der Benutzer klickt nacheinander auf folgende Produkte, um die Details zu prüfen:")
    public void derBenutzerKlicktNacheinanderAufFolgendeProdukteUmDieDetailsZuPrüfen(List<String> produktListe) {
        allPages.getHomePage().klickeNacheinanderAufProdukte(produktListe);
    }

    @And("Der Benutzer klickt auf den Button Meine Karriere")
    public void derBenutzerKlicktAufDenButtonMeineKarriere() {
       allPages.getHomePage().klickMeineKarriereButton();
    }

    @When("Wählt den Bereich {string} aus")
    public void wählt_den_bereich_aus(String bereich) {
        allPages.getJobsKarrierePage().klickQualitaetssicherung(bereich);

    }


}
