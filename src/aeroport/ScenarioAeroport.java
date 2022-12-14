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
        System.out.println("Création des entités simulées (ScenarioAeroport)");
    }

    public int getNbAvions() {
        return nbAvions;
    }

    @Override
    public void activate() {
        for(EntiteSimulee e: getEntites()) {
            if(!(e instanceof Scenario))
                e.activate();
        }

    }


    @Override
    public void init() {
        super.init();
        for(int i = 0; i<this.nbAvions; i++) {
            new Avion(getEngine(), new InitDataAvion("Avion " + i)).requestInit();

        }
        Post(new CreerAvion(getEngine(),this.getEngine().SimulationDate().add(LogicalDuration.ofMinutes(5)), "Avion"));


    }

    private static class CreerAvion extends SimEvent {

        String name;
        SimuEngine engine;
        public CreerAvion(SimuEngine engine, LogicalDateTime d, String nom) {
            super(d);
            this.name = nom;
            this.engine = engine;
        }
        @Override
        public void process() {
            new Avion(engine, new InitDataAvion(name)).requestInit();
        }
    }




}

