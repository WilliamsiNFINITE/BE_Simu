package aeroport;

import engine.*;
import enstabretagne.base.time.LogicalDateTime;

import java.util.LinkedList;

public class PlanAeroport extends Plan {

    LogicalDateTime debut;
    LogicalDateTime fin;
    LinkedList<ScenarioAeroport> scenarioAeroports;

    public PlanAeroport(SimuEngine engine, int nbReplique, LogicalDateTime debut, LogicalDateTime fin) {
        super(engine, nbReplique);
        scenarioAeroports = new LinkedList<>();
        this.debut = debut;
        this.fin = fin;
    }

    @Override
    public void initScenario() {
        for(int i = 0;i < getNbReplique(); i++)
            scenarioAeroports.add(new ScenarioAeroport(this.getEngine(), new ScenarioAeroportInit(String.format("Scenario %s", i), i, debut, fin, 1.0, new InitDataAvion("Avion Init"), new AeroportInit("Aeroport Init") ), 2, 1, 1, 1));
    }

    @Override
    public boolean hasNextScenario() {
        return scenarioAeroports.size() > 0;
    }

    @Override
    public ScenarioAeroport nextScenario() {
        ScenarioAeroport sc = scenarioAeroports.pop();
        engine.setCurrentScenario(sc);
        return sc;
    }
}
