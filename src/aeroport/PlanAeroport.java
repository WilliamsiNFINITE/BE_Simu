package aeroport;

import engine.*;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.util.LinkedList;

public class PlanAeroport extends Plan {

    LogicalDateTime debut;
    LogicalDateTime fin;
    LinkedList<ScenarioAeroport> scenarioAeroports;

    int nbJour;
    double frequenceArriveeAvion;
    public PlanAeroport(SimuEngine engine, int nbReplique, int nbJour, LogicalDateTime debut, LogicalDateTime fin) {
        super(engine, nbReplique);
        scenarioAeroports = new LinkedList<>();
        this.debut = debut;
        this.fin = fin;
        this.nbJour = nbJour;
    }


    int getNbJour() {
        return nbJour;
    }

    @Override
    public void initScenario() {
        // On met le jour initial dans le moteur de simulation
        this.engine.setCurrentTime(debut);

        // On définit les heures de pointes de l'aéroport en commançant par le jour du debut de la simulation
        LogicalDateTime debutHeurePointeMatin = debut.truncateToDays().add(LogicalDuration.ofHours(7)); // 7h
        LogicalDateTime finHeurePointeMatin = debut.truncateToDays().add(LogicalDuration.ofHours(10)); // 10h
        LogicalDateTime debutHeurePointeSoir = debut.truncateToDays().add(LogicalDuration.ofHours(17)); // 17h
        LogicalDateTime finHeurePointeSoir = debut.truncateToDays().add(LogicalDuration.ofHours(19)); // 19h

        for (int i = 0; i < getNbJour(); i++) {
            //getting current time of day
            LogicalDateTime currentTime = this.engine.getCurrentTime();
            //test to see if the day is a weekend
            if (currentTime.getDayOfWeek().getValue() == 6 || currentTime.getDayOfWeek().getValue() == 7) {
                //if it is a weekend, we use a frequency of 40 minutes
                frequenceArriveeAvion = 1.0 / 40.0;
            }
            else{
                if (((currentTime.compareTo(debutHeurePointeMatin) > 0) && (finHeurePointeMatin.compareTo(currentTime) > 0))  || ((currentTime.compareTo(debutHeurePointeSoir) > 0) && (finHeurePointeSoir.compareTo(currentTime) > 0))) {
                    //if it is a weekday and between 7 and 10 or between 17 and 19, we use a frequency of 10 minutes
                    frequenceArriveeAvion = 1.0/10.0;
                }
                else{
                    //if it is a weekday and not between 7 and 10 or between 17 and 19, we use a frequency of 20 minutes
                    frequenceArriveeAvion = 1.0/20.0;
                }
            }

            scenarioAeroports.add(new ScenarioAeroport(this.getEngine(), new ScenarioAeroportInit(String.format("Scenario %s", i), i, debut, fin, frequenceArriveeAvion, new InitDataAvion("Avion Init"), new AeroportInit("Aeroport Init")), 10, 1, 1, 1));
        }
    }

    @Override
    public boolean hasNextScenario () {
        return scenarioAeroports.size() > 0;
    }

    @Override
    public ScenarioAeroport nextScenario () {
        ScenarioAeroport sc = scenarioAeroports.pop();
        engine.setCurrentScenario(sc);
        return sc;
    }
}

