package aeroport;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

public class ScenarioAeroportInit extends ScenarioInitData {
    public ScenarioAeroportInit(String name, int graine, LogicalDateTime debut, LogicalDateTime fin, double frequenceArriveeAvion, InitDataAvion avionType, AeroportInit airportIni) {
        super(name, graine, debut, fin);
        this.frequenceArriveeAvion = frequenceArriveeAvion;
        this.avioniniType = avionType;
        this.aeroportInit = airportIni;
    }

    public void setName(String newname) {
        this.name = newname;
    }

    public final double frequenceArriveeAvion;
    public final InitDataAvion avioniniType;
    public final AeroportInit aeroportInit;
}

