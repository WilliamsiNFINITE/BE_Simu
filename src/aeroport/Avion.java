package aeroport;

import engine.EntiteSimulee;
import engine.InitData;
import engine.SimuEngine;

public class Avion extends EntiteSimulee {

    //Attributs
    String compagnie;
    String pays;

    //Constructeur
    public Avion(SimuEngine engine, InitData ini) {
        super(engine, ini);
    }

    //Getter
    public String getCompagnie() {
        return compagnie;
    }
    @Override
    public String getName() {
        return super.getName();
    }
    public String getPays() {
        return pays;
    }

    //Setter
    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }
    public void setName(String name) {
        super.setName(name);
    }
    public void setPays(String pays) {
        this.pays = pays;
    }

    //Méthodes
    public void DemandeAtterrissage(Tour tour) {
        tour.AutorisationAtterrissage(this);

    }

    public void DemandeDecollage(Tour tour) {
        tour.AutorisationDecollage(this);
    }

    public void FinAtterrissage(Tour tour) {

        // TODO Auto-generated method stub

    }

    public void FinDecollage(Tour tour) {
        // TODO Auto-generated method stub

    }


}
