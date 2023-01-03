package aeroport;

import engine.InitData;

/**donn�es d'initialisation minimale d'une entit�
elle a un nom...*/

public class InitDataGate extends InitData {

	Aeroport aeroport;
	Boolean occupe;

	public InitDataGate(String name, Aeroport aeroport, Boolean occupe) {
		super(name);
		this.aeroport = aeroport;
		this.occupe = occupe;

	}
}
