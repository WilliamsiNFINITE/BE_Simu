package aeroport;

import engine.SimuEngine;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ScenarioAeroportTest {

    private SimuEngine engine;
    private ScenarioAeroport scenario;
    private List<Avion> avions;
    private LogicalDateTime start;
    private LogicalDateTime end;
    private ScenarioAeroportInit scenarioAeroportInit;
    private ScenarioAeroport scenarioAeroport;
    private ArrayList<ScenarioAeroport.CreerAvion> listAvion = new ArrayList<>();
    private Tour tour;


    @BeforeEach
    void setUp() {
        int nbAvions = 1;
        engine = new SimuEngine();
        start = new LogicalDateTime("02/01/2023 07:00");
        end = new LogicalDateTime("02/04/2023 22:00");
        scenarioAeroportInit =  new ScenarioAeroportInit("scenarioTest", 1, 1, start, end, 1.0, new InitDataAvion("AvionTest"), new AeroportInit("AeroportTest"));
        scenarioAeroport = new ScenarioAeroport(engine, scenarioAeroportInit , nbAvions, 1, 1, 1);

        double parametreMeteo = 0.0;

        List<Gate> gates = new ArrayList<>();

        Gate gate = new Gate(engine, new InitDataGate("Gate 1", false));
        gate.requestInit();
        gates.add(gate);


        tour = new Tour(engine, new InitDataTour("Tour", gates));
        tour.requestInit();
        new Piste(engine, new InitDataPiste("Piste")).requestInit();
        new TaxiWay(engine, new InitDataTaxiWay("Taxiway 1"), "Entrant").requestInit();
        new TaxiWay(engine, new InitDataTaxiWay("Taxiway 2"), "Sortant").requestInit();


        for(int i = 0; i < 5; i++) {
            listAvion.add(new ScenarioAeroport.CreerAvion(engine,engine.SimulationDate().add(LogicalDuration.ofMinutes(i)), "Avion " + i));
        }


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAvailableGate() {
        assert scenarioAeroport.getAvailableGate() != null;
    }

    @Test
    void getOccupiedGate() {
        assert scenarioAeroport.getOccupiedGate() != null;
    }

    @Test
    void coefficientMeteo() {

    }

//    @Test
//    void creerEntitesSimulees() {
//    }
//
//    @Test
//    void getNbAvions() {
//    }
//
//    @Test
//    void activate() {
//    }
//
//    @Test
//    void init() {
//    }
//
//    @Test
//    void atterisagge() {
//    }
//
//    @Test
//    void decollage() {
//    }
//
//    @Test
//    void scenarioTest() {
//    }

//
//    @Test
//    void getNextDate4AvionCreation() {
//    }
}