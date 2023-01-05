package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class Roulement extends SimEvent {

    Avion avion;
    public Roulement(LogicalDateTime d, Avion avion) {
        super(d);
        this.avion = avion;
    }

    @Override
    public void process() {
        Logger.DataSimple("Roulement", "Roulement de l'avion " + avion.getName() + " à " + getDateOccurence());
    }
}
