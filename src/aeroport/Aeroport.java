package aeroport;

import engine.*;
import engine.SimuEngine;
import enstabretagne.base.time.LogicalDateTime;

public class Aeroport {

    SimuEngine engine = new SimuEngine();

    LogicalDateTime start = new LogicalDateTime("14/12/2022 10:00");
    LogicalDateTime end = new LogicalDateTime("14/01/2022 19:00");

    PlanAeroport planAeroport = new PlanAeroport(engine, 1, start, end);

}
