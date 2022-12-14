package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

public class Dechargement extends SimEvent {

    Avion avion;

    public Dechargement(LogicalDateTime d, Avion avion) {
        super(d);
        this.avion = avion;
    }

    @Override
    public void process() {
        Logger.Detail(this.entitePorteuseEvenement, "Dechargement.Process", avion.getName() + " en train de decharger les voyageurs " + " � " +getDateOccurence());
        entitePorteuseEvenement.Post(this);
    }
}
