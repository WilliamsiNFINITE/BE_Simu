package aeroport;

public class Gate {

    //Attributs
    String nomGate;
    Aeroport aeroport;
    Boolean occupe;

    //Constructeur
    public Gate(String nomGate, Aeroport aeroport) {
        this.nomGate = nomGate;
        this.aeroport = aeroport;
        this.occupe = false;
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
