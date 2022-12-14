package aeroport;

import engine.*;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

// comment
public class ScenarioAeroport extends Scenario {

    int nbAvions;

    public ScenarioAeroport(SimuEngine engine, ScenarioInitData init, int nbAvions) {
        super(engine, init);
        this.nbAvions = nbAvions;
    }

    @Override
    public void creerEntitesSimulees() {
        for(int i = 0; i<this.nbAvions; i++) {
            new Avion(getEngine(), new InitData("Avion " + i) {
            });
        }

        Post(new CreerAvion(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(8)), "Avion"));
    }

    public int getNbAvions() {
        return nbAvions;
    }



    private static class CreerAvion extends SimEvent {

        String name;
        public CreerAvion(LogicalDateTime d, String nom) {
            super(d);
            this.name = nom;
        }
        @Override
        public void process() {
            Avion av = new Avion(entitePorteuseEvenement.getEngine(), new InitData(name) {
            });
        }

    }
}

