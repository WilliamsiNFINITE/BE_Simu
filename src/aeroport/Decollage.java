package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class Decollage extends SimEvent {

    Avion avion;

    public Decollage(LogicalDateTime d, Avion avion) {
        super(d);
        this.avion = avion;
    }

    @Override
    public void process() {
        Logger.DataSimple("DecollageDetail", "Decollage de l'avion " + avion.getName() + " à " + getDateOccurence());
        Logger.DataSimple("Decollage",  avion.getName(), getDateOccurence().truncateToHours());
    }
}
