package aeroport;

import engine.EntiteSimulee;
import engine.InitData;
import engine.SimuEngine;

public class Tour extends EntiteSimulee {

    //Attributs

    //Constructeur
    public Tour(SimuEngine engine, InitData ini) {
        super(engine, ini);
    }

    //Getter
    public String getName() {
        return super.getName();
    }
    //Setter
    public void setName(String name) {
        super.setName(name);
    }

    //Méthodes

    public Boolean AutorisationAtterrissage(Avion avion) {
        // Attribution d'un numero de vol à l'avion de la forme F + entier aléatoire de 00 à 99
        avion.setName("F" + (int) (Math.random() * 100));
        // Verifie si la piste est libre pour l'atterrissage
        if (!this.getEngine().getPiste().getOccupe()) {
            // Si la piste est libre, l'avion atterit
            this.getEngine().getPiste().setOccupe(true);
            return true;
        } else {
            // Si la piste est occupée, l'avion ne peut pas atterir
            return false;
        }
    }

    public Boolean AutorisationDecollage(Avion avion) {
        avion.setName("F" + (int) (Math.random() * 100));
        // Verifie si la piste est libre pour le décollage
        if (!this.getEngine().getPiste().getOccupe()) {
            // Si la piste est libre, l'avion decolle
            this.getEngine().getPiste().setOccupe(true);
            return true;
        } else {
            // Si la piste est occupée, l'avion ne peut pas atterir
            return false;
        }
    }

    public Boolean AutorisationRoulageEntrant(Avion avion) {
        // Verifie si le taxiway est libre pour le roulement de l'avion
        if (!this.getEngine().getEnteringTaxiway().getOccupeEntrant()) {
            // Si le taxiway est libre, l'avion roule
            this.getEngine().getEnteringTaxiway().setOccupeEntrant(true);
            return true;
        } else {
            // Si le taxiway est occupé, l'avion ne peut pas rouler
            return false;
        }
    }

    public Boolean AutorisationRoulageSortant(Avion avion) {
        // Verifie si le taxiway est libre pour le roulement de l'avion
        if (!this.getEngine().getLeavingTaxiway().getOccupeSortant()) {
            // Si le taxiway est libre, l'avion roule
            this.getEngine().getLeavingTaxiway().setOccupeSortant(true);
            return true;
        } else {
            // Si le taxiway est occupé, l'avion ne peut pas rouler
            return false;
        }
    }

    @Override
    public void activate() {

    }
}
