package aeroport;

import engine.EntiteSimulee;
import engine.InitData;
import engine.SimuEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tour extends EntiteSimulee {

    //Attributs

    List<Gate> gates = new ArrayList<>();

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

    public Boolean AutorisationAccesPiste(Avion avion) {
        // Attribution d'un numero de vol à l'avion de la forme F + entier aléatoire de 00  99
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

    public Boolean AutorisationRoulage(Avion avion, String action) {
        // Verifie si le taxiway est libre pour le roulement de l'avion

        if (Objects.equals(action, "Atterrissage")) {
            if ((!this.getEngine().getEnteringTaxiway().getOccupe()) && (this.getEngine().getGate())  ) { //on verifie que le taxiway est libre et qu'un gate est libre
                // Si le taxiway est libre, l'avion roule
                this.getEngine().getEnteringTaxiway().setOccupe(true);
                return true;
            } else {
                // Si le taxiway est occupé, l'avion ne peut pas rouler
                return false;
            }
        } else {
            if (Objects.equals(action, "Decollage")) {
                if (!this.getEngine().getLeavingTaxiway().getOccupe()) {
                    // Si le taxiway est libre, l'avion roule
                    this.getEngine().getLeavingTaxiway().setOccupe(true);
                    return true;
                } else {
                    // Si le taxiway est occupé, l'avion ne peut pas rouler
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public void activate () {

    }
}

