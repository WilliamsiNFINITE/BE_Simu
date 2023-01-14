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
    double frequenceArriveeAvion = 1/20.0;
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

        for (int i = 0; i < getNbJour(); i++) {
            //getting current time of day
            scenarioAeroports.add(new ScenarioAeroport(this.getEngine(), new ScenarioAeroportInit(String.format("Jour %s", i), 1, i, debut, fin, frequenceArriveeAvion, new InitDataAvion("Avion Init"), new AeroportInit("Aeroport Init")), 10, 1, 1, 1));
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

