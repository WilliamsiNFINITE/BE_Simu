package engine;

/**donn�es d'initialisation minimale d'une entit�
elle a un nom...*/

public abstract class InitData {
	public String name;
	public InitData(String name) {
		this.name=name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
