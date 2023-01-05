package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;

public class Atterissage extends SimEvent {

    Avion avion;

    LogicalDateTime date;
    public Atterissage(LogicalDateTime d, Avion avion) {
        super(d);
        this.date = d;
        this.avion = avion;

    }

    @Override
    public void process() {
        System.out.println("In Atterissageg : " + getDateOccurence());
        //Ici on ne traite que de l'événementiel (pas de traitement pour savoir si l'avion a le droit d'atterrir ou non)
        Logger.DataSimple("Atterissage", "Atterissage de l'avion " + avion.getName() + " à " + getDateOccurence());
    }
}
