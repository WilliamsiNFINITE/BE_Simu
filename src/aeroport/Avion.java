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
//        Post(new Atterissage(LogicalDateTime.Now(), this));
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

    public void DemandeAccesPiste(Tour tour) {
        if (tour.AutorisationAccesPiste(this)){
            Post(new Atterissage(LogicalDateTime.Now(), this));
//            Atterissage atterissage = new Atterissage(LogicalDateTime.Now(), this);
            // L'avion atterit
        } else {
            // L'avion attends et redemande l'autorisation d'atterir
            // methode pour attendre
            DemandeAccesPiste(tour);
        }
    }

    public void DemandeRoulage(Tour tour, String action) {
        if (tour.AutorisationRoulage(this, action)){
            Post(new Roulement(LogicalDateTime.Now(), this));
//            Atterissage atterissage = new Atterissage(LogicalDateTime.Now(), this);
            // L'avion roule jusqu'au gate
        } else {
            // L'avion attends et redemande l'autorisation d'atterir
            // methode pour attendre
            DemandeRoulage(tour, action);
        }
    }

    public void FinAtterrissage(Tour tour) {
        // Mettre la piste en libre
        getEngine().getPiste().setOccupe(false);
    }

    public void FinDecollage(Tour tour) {
        // Mettre la piste en libre
        getEngine().getPiste().setOccupe(false);
    }

    @Override
    public void activate() {

    }
}
