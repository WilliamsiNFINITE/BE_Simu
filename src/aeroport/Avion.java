package aeroport;

import engine.EntiteSimulee;
import engine.InitData;
import engine.SimuEngine;
import enstabretagne.base.time.LogicalDateTime;

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


    @Override
    protected void init() {
        super.init();
    }

    public void DemandeAtterrissage(Tour tour) {
        if (tour.AutorisationAtterrissage(this)){
            Post(new Atterissage(LogicalDateTime.Now(), this));
            Atterissage atterissage = new Atterissage(LogicalDateTime.Now(), this);
            // L'avion atterit
        } else {
            // L'avion ne peut pas atterir
        }

    }

    public void DemandeDecollage(Tour tour) {
        tour.AutorisationDecollage(this);
    }

    public void DemandeRoulageEntrant(Tour tour) {
        tour.AutorisationRoulageEntrant(this);
    }

    public void DemandeRoulageSortant(Tour tour) {
        tour.AutorisationRoulageSortant(this);
    }


    public void FinAtterrissage(Tour tour) {

        // TODO Auto-generated method stub
        // Mettre la piste en libre

    }

    public void FinDecollage(Tour tour) {
        // TODO Auto-generated method stub
        // Mettre la piste en libre

    }

    @Override
    public void activate() {

    }
}
