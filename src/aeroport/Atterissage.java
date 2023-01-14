package aeroport;

import engine.SimEvent;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;

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
        //Ici on ne traite que de l'événementiel (pas de traitement pour savoir si l'avion a le droit d'atterrir ou non)
        Logger.DataSimple("AtterissageDetail", "Atterissage de l'avion " + avion.getName() + " à " + getDateOccurence());
        Logger.DataSimple("Atterissage", avion.getName(),getDateOccurence().truncateToHours());
    }
}
