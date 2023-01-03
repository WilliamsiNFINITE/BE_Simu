package aeroport;

import engine.EntiteSimulee;
import engine.SimuEngine;

public class Gate extends EntiteSimulee {

    //Attributs
    String nomGate;
    Aeroport aeroport;
    Boolean occupe;

    //Constructeur
    public Gate(SimuEngine engine, InitDataGate ini) {
        super(engine, ini);
        this.nomGate = ini.getName();
        this.aeroport = ini.aeroport;
        this.occupe = ini.occupe;
    }

    @Override
    public void activate() {

    }


    //Getter
    public Aeroport getAeroport() {
        return aeroport;
    }
    public String getNomGate() {
        return nomGate;
    }
    public Boolean getOccupe() {
        return occupe;
    }


    //Setter
    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }
    public void setNomGate(String nomGate) {
        this.nomGate = nomGate;
    }
    public void setOccupe(Boolean occupe) {
        this.occupe = occupe;
    }


    //Méthodes

}
