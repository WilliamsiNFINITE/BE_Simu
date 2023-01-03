package aeroport;

import engine.*;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

import java.util.ArrayList;


// comment
public class ScenarioAeroport extends Scenario {

    int nbAvions;
    int nbPiste;
    int nbTaxiwayEntrant;
    int nbTaxiwaySortant;



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
//        for(int i = 0; i<this.nbAvions; i++) {
//            new Avion(getEngine(), new InitDataAvion("Avion " + i)).requestInit();
//        }
//        Post(new CreerAvion(getEngine(),this.getEngine().SimulationDate().add(LogicalDuration.ofMinutes(5)), "Avion"));

        scenarioTest();
    }


    public void scenarioTest() {

        ArrayList<CreerAvion> listAvion = new ArrayList<CreerAvion>();

        for(int i = 0; i < 1000; i++) {
            listAvion.add(new CreerAvion(getEngine(),this.getEngine().SimulationDate().add(LogicalDuration.ofMinutes(i)), "Avion " + i));
        }


       for(int i = 0; i < 100; i++){
           Post(new Atterissage(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(i+ 30)),new Avion(getEngine(), new InitDataAvion("Avion 1"))));
           Logger.DataSimple("Logger_loi_exp",getNextDate4AvionCreation().toString());
//           Logger.DataSimple("Logger_test_date",new LogicalDateTime("14/12/2022 07:03").getDayOfWeek().getValue());
//           Logger.DataSimple("Logger_test_date",new LogicalDateTime("14/12/2022 07:03").truncateToDays().add(LogicalDuration.ofHours(10)));
//           Logger.DataSimple("Logger_test_date",new LogicalDateTime("14/12/2022 07:03").compareTo(new LogicalDateTime("14/12/2022 07:05")));
       }

        //TODO cycle de l'aeroport : horaires, jours, semaines, mois.
        // L'aeroport est ouvert entre 7h et 22h
        // Le weekend, la fréquence est divisée par deux (40 minutes)
        // Les heures de pointes sont de 7h à 10h et de 17h à 19h (10h à 17h, 20 minutes)
        // En heure de pointe, la fréquence est multipliée par 2 (10 minutes)

        //New day



       /* //creation d'un avion
        for (int i = 0; i < 1000; i++) {
//            Post(new CreerAvion(getEngine(), this.getEngine().SimulationDate().add(LogicalDuration.ofMinutes(i)), "Avion " + i));
            Post(new Atterissage(getEngine().SimulationDate(), new Avion(getEngine(), new InitDataAvion("Avion " + i))));
        }
//        Post(new CreerAvion(getEngine(),this.getEngine().SimulationDate(), "Avion"));
////        //decollage de l'avion
////        Post(new Decollage(getEngine().SimulationDate(),new Avion(getEngine(), new InitDataAvion("Avion 1"))));
//        Post(new Atterissage(getEngine().SimulationDate(),new Avion(getEngine(), new InitDataAvion("Avion 1")))); */
    }

    LogicalDuration getNextDate4AvionCreation() {
        double time = getRandomGenerator().nextExp(iniAirport.frequenceArriveeAvion);
        return LogicalDuration.ofMinutes((long) time);
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
            MoreRandom moreRandom = new MoreRandom();
            Logger.Detail(null, "main", String.valueOf(moreRandom.nextGaussian()));
        }
    }




}

