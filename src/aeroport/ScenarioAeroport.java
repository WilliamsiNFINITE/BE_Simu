package aeroport;

import engine.*;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.util.ArrayList;
import java.util.List;


// comment
public class ScenarioAeroport extends Scenario {

    int nbAvions;
    int nbPiste;
    int nbTaxiwayEntrant;
    int nbTaxiwaySortant;

    List<Gate> gates = new ArrayList<>();



    ScenarioAeroportInit iniAirport;
    public ScenarioAeroport(SimuEngine engine, ScenarioAeroportInit init, int nbAvions, int nbPiste, int nbTaxiwayEntrant, int nbTaxiwaySortant) {
        super(engine, init);
        iniAirport = init;

        /* this.nbAvions = nbAvions;
        this.nbPiste = nbPiste;
        this.nbTaxiwayEntrant = nbTaxiwayEntrant;
        this.nbTaxiwaySortant = nbTaxiwaySortant; */
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
        new Piste(getEngine(), new InitDataPiste("Piste")).requestInit();
        new TaxiWay(getEngine(), new InitDataTaxiWay("Taxiway 1"), "Entrant").requestInit();
        new TaxiWay(getEngine(), new InitDataTaxiWay("Taxiway 2"), "Sortant").requestInit();

        for(int i = 1; i <= 6; i++){
            Gate gate = new Gate(getEngine(), new InitDataGate("Gate " + i , false));
            gate.requestInit();
            gates.add(gate);
        }

        scenarioTest();
    }


    public void scenarioTest() {

        Tour tour = new Tour(getEngine(), new InitDataTour("Tour", gates));
        tour.requestInit();

        ArrayList<CreerAvion> listAvion = new ArrayList<CreerAvion>();

        for(int i = 0; i < 100; i++) {
            listAvion.add(new CreerAvion(getEngine(),this.getEngine().SimulationDate().add(LogicalDuration.ofMinutes(i)), "Avion " + i));
        }


        for(int i = 0; i < 10; i++){
            Avion currentAvion = listAvion.get(i).getAvion();
            if(currentAvion.DemandeAccesPiste(tour)){
                Post(new Atterissage(getEngine().SimulationDate().add(getNextDate4AvionCreation()), currentAvion));
                currentAvion.FinAtterrissage(tour);

                if(currentAvion.DemandeRoulage(tour, "Atterrissage")){
                    Post(new Roulement(getEngine().SimulationDate(), currentAvion));
                    currentAvion.FinRoulage(getEngine().getEnteringTaxiway());
                }
            }
        }
    }

    LogicalDuration getNextDate4AvionCreation() {
        double time = getRandomGenerator().nextExp(iniAirport.frequenceArriveeAvion);
        return LogicalDuration.ofMinutes((long) time);
    }


    private static class CreerAvion extends SimEvent {

        String name;
        SimuEngine engine;

        Avion avion;
        public CreerAvion(SimuEngine engine, LogicalDateTime d, String nom) {
            super(d);
            this.name = nom;
            this.engine = engine;
            this.avion = new Avion(engine, new InitDataAvion(name));
        }
        @Override
        public void process() {
            avion.requestInit();
            MoreRandom moreRandom = new MoreRandom();
            Logger.Detail(null, "main", String.valueOf(moreRandom.nextGaussian()));
        }

        public Avion getAvion(){
            return this.avion;
        }
    }




}

