package aeroport;

import engine.EntiteSimulee;
import engine.InitData;
import engine.SimuEngine;

public class Piste extends EntiteSimulee
{

    //Attributs
    String nomPiste;
    Aeroport aeroport;
    Boolean occupe;

    SimuEngine engine;

    //Constructeur
    public Piste(SimuEngine engine, InitDataPiste ini){
        super(engine, ini);
        this.occupe = false;
    }


    //Getter
    public Aeroport getAeroport() {
        return aeroport;
    }
    public String getNomPiste() {
        return nomPiste;
    }
    public Boolean getOccupe() {
        return occupe;
    }


    //Setter
    public void setAeroport(Aeroport aeroport) {
        this.aeroport = aeroport;
    }
    public void setNomPiste(String nomPiste) {
        this.nomPiste = nomPiste;
    }
    public void setOccupe(Boolean occupe) {
        this.occupe = occupe;
    }

    @Override
    public void activate() {

    }


    //Méthodes

}

