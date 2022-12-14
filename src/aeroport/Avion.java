package aeroport;

import engine.EntiteSimulee;
import engine.InitData;
import engine.SimuEngine;

public class Avion extends EntiteSimulee {

    String name;
    String compagnie;
    String pays;


    public Avion(SimuEngine engine, InitData ini) {
        super(engine, ini);
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }
    public String getCompagnie() {
        return compagnie;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }
    public String getPays() {
        return pays;
    }

}
