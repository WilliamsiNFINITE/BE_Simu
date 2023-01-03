package aeroport;

import engine.InitData;

/**donn�es d'initialisation minimale d'une entit�
elle a un nom...*/

public class InitDataGate extends InitData {

	Boolean occupe;

	public InitDataGate(String name, Boolean occupe) {
		super(name);
		this.occupe = occupe;

	}
}
