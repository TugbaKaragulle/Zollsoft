package zollsoft.pages;

public class AllPages {

    private Homepage homePage;
    private BewerbungsFormularPage bewerbungsFormularPage;
    private JobsKarrierePage jobsKarrierePage;

    public Homepage getHomePage() {
        if (homePage == null) {
            homePage = new Homepage();
        }
        return homePage;
    }

    public BewerbungsFormularPage getBewerbungsFormularPage() {
        if (bewerbungsFormularPage == null) {
            bewerbungsFormularPage = new BewerbungsFormularPage();
        }
        return bewerbungsFormularPage;
    }

    public JobsKarrierePage getJobsKarrierePage() {
        if (jobsKarrierePage == null) {
            jobsKarrierePage = new JobsKarrierePage();
        }
        return jobsKarrierePage;
    }
}
