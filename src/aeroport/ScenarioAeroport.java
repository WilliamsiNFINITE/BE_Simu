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
    Tour tour;
    ArrayList<CreerAvion> listAvion = new ArrayList<>();


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
        // System.out.println("Création des entités simulées (ScenarioAeroport)");
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

        tour = new Tour(getEngine(), new InitDataTour("Tour", gates));
        tour.requestInit();

        listAvion = new ArrayList<CreerAvion>();

        for(int i = 1; i <= 6; i++){
            Gate gate = new Gate(getEngine(), new InitDataGate("Gate " + i , false));
            gate.requestInit();
            gates.add(gate);
        }

        scenarioTest();
    }


    public void atterisagge(int i){
        Avion currentAvion = listAvion.get(i).getAvion();
        if(currentAvion.DemandeAccesPiste(tour)){
            LogicalDuration date = getNextDate4AvionCreation();

            LogicalDateTime testDate = getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(i * 11)));

            System.out.println("In Scenario: " + testDate);
            Post(new Atterissage(testDate, currentAvion));
            currentAvion.FinAtterrissage(tour);

            if(currentAvion.DemandeRoulage(tour, "Atterrissage")){
                Post(new Roulement(getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(2))), currentAvion));
                currentAvion.FinRoulage(getEngine().getEnteringTaxiway());

                Gate aGate = getAvailableGate();
                Post(new Dechargement(getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(2))), currentAvion, aGate));
                aGate.setOccupe(true);
            }
        }
    }

    public void decollage(int i){
        Avion currentAvion = listAvion.get(i).getAvion();
        if(currentAvion.DemandeAccesPiste(tour)){
            LogicalDuration date = getNextDate4AvionCreation();
            Post(new Decollage(getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(i * 6))), currentAvion));
            currentAvion.FinDecollage(tour);
            Gate occupiedGate = getOccupiedGate();
            if(occupiedGate == null) return;
            occupiedGate.setOccupe(false);
        }
    }

    public void scenarioTest() {

        for(int i = 0; i < 200; i++) {
            listAvion.add(new CreerAvion(getEngine(),this.getEngine().SimulationDate().add(LogicalDuration.ofMinutes(i)), "Avion " + i));
        }

        for(int i = 0; i < 200; i++){
            if(i % 3 == 0){
                atterisagge(i);
                continue;
            }
            decollage(i);
        }

        /*for(int i = 0; i < 200; i++){
            Avion currentAvion = listAvion.get(i).getAvion();
            if(currentAvion.DemandeAccesPiste(tour)){
                LogicalDuration date = getNextDate4AvionCreation();

                LogicalDateTime testDate = getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(i * 11)));

                System.out.println("In Scenario: " + testDate);
                Post(new Atterissage(testDate, currentAvion));
                currentAvion.FinAtterrissage(tour);

                if(currentAvion.DemandeRoulage(tour, "Atterrissage")){
                    Post(new Roulement(getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(2))), currentAvion));
                    currentAvion.FinRoulage(getEngine().getEnteringTaxiway());

                    Gate aGate = getAvailableGate();
                    Post(new Dechargement(getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(2))), currentAvion, aGate));
                    aGate.setOccupe(true);
                }
            }
        }

        for(int i = 0; i < 50; i++){
            Avion currentAvion = listAvion.get(i).getAvion();
            if(currentAvion.DemandeAccesPiste(tour)){
                LogicalDuration date = getNextDate4AvionCreation();
                Post(new Decollage(getEngine().SimulationDate().add(date.add(LogicalDuration.ofMinutes(i * 6))), currentAvion));
                currentAvion.FinDecollage(tour);
                Gate occupiedGate = getOccupiedGate();
                if(occupiedGate == null) continue;
                occupiedGate.setOccupe(false);
            }
        }*/

    }

    private Gate getAvailableGate(){
        for(int i = 0; i < gates.size(); i++) {
            if(!gates.get(i).occupe) return gates.get(i);
        }

        return null;
    }

    private Gate getOccupiedGate(){
        for(int i = 0; i < gates.size(); i++) {
            // System.out.println(gates.get(i).occupe);
            if(gates.get(i).occupe) return gates.get(i);
        }
        return null;
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

