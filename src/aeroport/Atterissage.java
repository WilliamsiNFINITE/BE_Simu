package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Atterissage extends SimEvent {

    Avion avion;

    public Atterissage(LogicalDateTime d, Avion avion) {
        super(d);
        this.avion = avion;

    }

    @Override
    public void process() {
        MoreRandom moreRandom = new MoreRandom();
        Logger.Detail(null, "main", String.valueOf(moreRandom.nextGaussian()));

//        Logger.Detail(this.entitePorteuseEvenement, "Atterissage.Process", avion.getName() + " en train d'atterrire " + " � " +getDateOccurence());
//        entitePorteuseEvenement.Post(this);
    }
}
