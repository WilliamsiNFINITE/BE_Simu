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

    public Boolean DemandeAccesPiste(Tour tour) {
        if (tour.AutorisationAccesPiste(this)){
            // L'avion atterit
            Post(new Atterissage(getEngine().SimulationDate(), this));
            return true;
        } else {
            // L'avion attends est en retard
            // methode pour attendre
            // DemandeAccesPiste(tour);
            Post(new Retard(getEngine().SimulationDate(), this));
            return false;
        }
    }

    public Boolean DemandeRoulage(Tour tour, String action) {
        if (tour.AutorisationRoulage(this, action)){
//            Post(new Roulement(LogicalDateTime.Now(), this));
            Post(new Roulement(getEngine().SimulationDate(), this));
            // L'avion roule jusqu'au gate
            return true;
        } else {
            // L'avion attends est en retard
            // methode pour attendre
            // DemandeRoulage(tour, action);
            Post(new Retard(getEngine().SimulationDate(), this));
            return false;
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

    public void FinRoulage(TaxiWay taxiWay) {
        // Mettre la tw en libre
        taxiWay.setOccupe(false);
    }

    @Override
    public void activate() {

    }
}
