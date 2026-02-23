package zollsoft.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import zollsoft.pages.AllPages;

public class OffeneStellenSD {

    AllPages allPages = new AllPages();

    @When("Wählt die Stellenanzeige aus, die {string} im Titel enthält, falls vorhanden")
    public void wählt_die_stellenanzeige_aus_die_im_titel_enthält_falls_vorhanden(String jobTitel) {
        allPages.getJobsKarrierePage().klickJobTitel(jobTitel);

    }
    @And("Startet den Bewerbungsprozess über die Schaltfläche Jetzt bewerben")
    public void startetDenBewerbungsprozessÜberDieSchaltflächeJetztBewerben() {
       allPages.getJobsKarrierePage().klickJetztBewerben();
    }

}
