package aeroport;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

public class ScenarioAeroportInit extends ScenarioInitData {
    public ScenarioAeroportInit(String name, int graine, int jour, LogicalDateTime debut, LogicalDateTime fin, double frequenceArriveeAvion, InitDataAvion avionType, AeroportInit airportIni) {
        super(name, graine, jour, debut, fin);
        this.frequenceArriveeAvion = frequenceArriveeAvion;
        this.avioniniType = avionType;
        this.aeroportInit = airportIni;
        this.debut = debut;
    }

    public void setName(String newname) {
        this.name = newname;
    }

    public final LogicalDateTime debut;
    public final double frequenceArriveeAvion;
    public final InitDataAvion avioniniType;
    public final AeroportInit aeroportInit;
}

