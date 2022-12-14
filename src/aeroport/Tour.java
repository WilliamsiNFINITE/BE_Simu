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
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean AutorisationDecollage(Avion avion) {
        // TODO Auto-generated method stub
        return null;
    }



}
