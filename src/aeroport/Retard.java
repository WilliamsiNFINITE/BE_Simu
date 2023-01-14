package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Retard extends SimEvent {
    Avion avion;
    LogicalDateTime date;


    public Retard(LogicalDateTime logicalDateTime, Avion avion) {
        super(logicalDateTime);
        this.date = logicalDateTime;
        this.avion = avion;

    }

    @Override
    public void process() {
        Logger.DataSimple("Retard", avion.getName(),getDateOccurence(),getDateOccurence().add(LogicalDuration.ofMinutes(5)));
    }
}
