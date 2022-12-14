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
        if (this.getEngine().getPiste().getOccupe() == true) {
            // Si la piste est libre, l'avion atterit
            this.getEngine().getPiste().setOccupe(true);
            return true;
        } else {
            // Si la piste est occupée, l'avion ne peut pas atterir
            return false;
        }
    }

    public Boolean AutorisationDecollage(Avion avion) {
        // Attribution d'un numero de vol à l'avion
        // Verifie si le taxiway est libre pour le roulement de l'avion
        // Verifie si la piste est libre pour l'atterrissage
        return null;
    }

    public Boolean AutorisationRoulage(Avion avion) {
        // Verifie si le taxiway est libre pour le roulement de l'avion
        if (this.getEngine().getEnteringTaxiway().getOccupe() == false) {
            // Si le taxiway est libre, l'avion roule
            this.getEngine().getEnteringTaxiway().setOccupe(true);
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
