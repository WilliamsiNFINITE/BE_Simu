package aeroport;

public class Gate {

    String nomGate;
    Aeroport aeroport;
    Boolean occupe;

    public Gate(String nomGate, Aeroport aeroport) {
        this.nomGate = nomGate;
        this.aeroport = aeroport;
        this.occupe = false;
    }
}
