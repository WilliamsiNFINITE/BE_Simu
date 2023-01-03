package aeroport;

import engine.ScenarioInitData;
import enstabretagne.base.time.LogicalDateTime;

public class ScenarioAirportInit extends ScenarioInitData {
    public ScenarioAirportInit(String name, int graine, LogicalDateTime debut, LogicalDateTime fin, double frequenceArriveeAvion, AvionInit avionType, AirportInit airportIni) {
        super(name, graine, debut, fin);
        this.frequenceArriveeAvion = frequenceArriveeAvion;
        this.avioniniType = avionType;
        this.airportIni = airportIni;
    }

    public void setName(String newname) {
        this.name = newname;
    }

    public final double frequenceArriveeAvion;
    public final AvionInit avioniniType;
    public final AirportInit airportIni;
}
}
