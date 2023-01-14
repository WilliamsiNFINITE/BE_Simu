package aeroport;

import engine.SimuEngine;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;

public class Aeroport {
//
    public static void main(String[] args) {
        SimuEngine engine = new SimuEngine();

        LogicalDateTime start = new LogicalDateTime("12/12/2022 00:00");
        LogicalDateTime end = new LogicalDateTime("12/12/2022 22:00");

        engine.initSimulation(start, end);

        PlanAeroport planAeroport = new PlanAeroport(engine, 1, 90,start, end);

        planAeroport.initScenario();

        while(planAeroport.hasNextScenario())
        {
            planAeroport.nextScenario().creerEntitesSimulees();
            engine.initSimulation();
            engine.simulate();
            engine.terminate(engine.hasANextEvent());
        }
        MoreRandom moreRandom = new MoreRandom();
//        Logger.Detail(null, "main", String.valueOf(moreRandom.nextGaussian()));
        Logger.Terminate();
    }
}
