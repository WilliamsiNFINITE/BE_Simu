package aeroport;

public class Piste {

    //Attributs
    String nomPiste;
    Aeroport aeroport;
    Boolean occupe;

    //Constructeur
    public Piste(String nomPiste, Aeroport aeroport) {
        this.nomPiste = nomPiste;
        this.aeroport = aeroport;
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


    //Méthodes

}

