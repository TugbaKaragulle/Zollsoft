@Regression
Feature: Bewerbung über die Homepage von Zollsoft

  Background: Benutzer öffnet die Startseite
    Given Der Benutzer geht zur Startseite
    And Entfernt das Cookies-Banner

  @Bewerbung
  Scenario: Job suchen und Bewerbung starten
    When Der Benutzer klickt nacheinander auf folgende Produkte, um die Details zu prüfen:
      | intellimago    |
      | arzt-direkt    |
      | tomedo.Dental  |
      | ImpfDocNE      |
      | ImpfPassDE     |
      | TeleScan       |
      | zollsoft.      |
      | KanzLaw        |
      | tomedo.air     |
    And Der Benutzer klickt auf den Button Meine Karriere
    And Wählt den Bereich 'Qualitätssicherung' aus
    And Wählt die Stellenanzeige aus, die "Softwaretester" im Titel enthält, falls vorhanden
    And Startet den Bewerbungsprozess über die Schaltfläche Jetzt bewerben
    And Füllt das Bewerbungsformular aus
    Then Prüft, ob die Schaltfläche zum Absenden klickbar ist






