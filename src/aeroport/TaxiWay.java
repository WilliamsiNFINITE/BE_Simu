package aeroport;

import engine.EntiteSimulee;
import engine.SimuEngine;

public class TaxiWay extends EntiteSimulee {
    SimuEngine engine;
    String nomTaxiWay;
    Boolean occupe = false;
    String direction;

    //Constructeur
    public TaxiWay(SimuEngine engine, InitDataTaxiWay ini, String direction) {
        super(engine, ini);
        this.engine = engine;
        this.direction = direction;
    }

    public boolean getOccupe() {
        return occupe;
    }

    public void setOccupe(boolean b) {
        occupe = b;
    }

    public boolean getOccupeEntrant() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setOccupeEntrant(boolean b) {
        // TODO Auto-generated method stub

    }

    public boolean getOccupeSortant() {
        // TODO Auto-generated method stub
        return false;
    }

    public void setOccupeSortant(boolean b) {
        // TODO Auto-generated method stub

    }

    @Override
    public void activate() {
        // TODO Auto-generated method stub

    }

    public String getDirection() {
        return direction;
    }
}
