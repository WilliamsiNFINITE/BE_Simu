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
        Logger.Detail(this.entitePorteuseEvenement, "Decollage.Process", avion.getName() + " en train de decoller " + " � " +getDateOccurence());
        entitePorteuseEvenement.Post(this);
    }
}
