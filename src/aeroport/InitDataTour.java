package aeroport;

import engine.InitData;

import java.util.List;

public class InitDataTour extends InitData {
    public List<Gate> gates;

    public List<Gate> getGates(){return gates;}
    public InitDataTour(String name, List<Gate> gates) {
        super(name);
        this.gates = gates;
    }
}
