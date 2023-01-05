package aeroport;

import engine.EntiteSimulee;
import engine.SimuEngine;

public class Gate extends EntiteSimulee {

    //Attributs
    String nomGate;
    Boolean occupe = false;

    //Constructeur
    public Gate(SimuEngine engine, InitDataGate ini) {
        super(engine, ini);
        this.nomGate = ini.getName();
        this.occupe = ini.occupe;
    }

    @Override
    public void activate() {

    }


    //Getter

    public String getNomGate() {
        return nomGate;
    }
    public Boolean getOccupe() {
        return occupe;
    }


    //Setter
    public void setNomGate(String nomGate) {
        this.nomGate = nomGate;
    }
    public void setOccupe(Boolean occupe) {
        this.occupe = occupe;
    }


    //Méthodes

}
