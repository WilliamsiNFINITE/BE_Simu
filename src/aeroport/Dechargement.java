package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class Dechargement extends SimEvent {

    Avion avion;

    Gate gate;

    public Dechargement(LogicalDateTime d, Avion avion, Gate gate) {
        super(d);
        this.avion = avion;
        this.gate = gate;
    }

    @Override
    public void process() {
        Logger.DataSimple("Dechargement", "Dechargement de l'avion " + avion.getName() + " au " + gate.getName() + " à " + getDateOccurence());
    }
}
