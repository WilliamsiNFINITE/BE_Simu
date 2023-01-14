package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import aeroport.Gate;
import aeroport.Piste;
import aeroport.TaxiWay;
import aeroport.Tour;
import enstabretagne.base.logger.Logger;
import enstabretagne.base.math.MoreRandom;
import enstabretagne.base.time.LogicalDateTime;
import engine.EntiteSimulee.EtatEntite;
import enstabretagne.simulation.basics.IScenarioIdProvider;
import enstabretagne.simulation.basics.ISimulationDateProvider;
import enstabretagne.simulation.basics.ScenarioId;
import enstabretagne.simulation.basics.SortedList;
/*
 * SimuEngine impl�mente ISimulationDateProvider et IScenarioIdProvider
 * Il est donc apte � d�livrer :
 * - un temps logique
 * - l'identifiant du sc�nario en cours * 
 */
public class SimuEngine implements ISimulationDateProvider, IScenarioIdProvider{

	//�ch�ancier. Pi�ce principale du moteur
	private SortedList<SimEvent> echeancier;
	//dates de d�but et fin de fonctionnement de la simulation
	private LogicalDateTime start;
	private LogicalDateTime end;
	
	//Liste des entit�s de simulation cr��es et connues du moteur
	protected List<EntiteSimulee> mesEntitesSimulees; 

	//temps logique de la simulation g�r� par le moteur de simulation
	private LogicalDateTime currentDate;
	protected LogicalDateTime getCurrentDate()
	{
		return currentDate;
	}

	public void setCurrentDate(LogicalDateTime d)
	{
		currentDate = d;
	}
	
	//g�n�rateur de nombre al�atoire principal du moteur de simulation
	private MoreRandom randomGenerator;
	MoreRandom getRandomGenerator() {
		return randomGenerator;
	}

	//sc�nario en cours d'ex�cution
	Scenario currentscenario;
	public Scenario getCurrentscenario() {
		return currentscenario;
	}
	public void setCurrentScenario(Scenario sc) {
		currentscenario = sc;
		//on r�initialise le g�n�rateur avec la nouvelle graine � chaque changement de sc�nario
		randomGenerator = new MoreRandom(sc.getGraine());
	}

	public SimuEngine() {
		echeancier = new SortedList<>();
		mesEntitesSimulees = new ArrayList<>();
		//on initialise le journaliseur
		Logger.setDateProvider(this);
	}

	//initialisation de la simulation avec param�tres de d�but et fin
	public void initSimulation(LogicalDateTime start, LogicalDateTime end) {
		//initialisation du temps logique de la simulation 
		//et des bornes temporelles d'ex�cution de la simulation
		this.start=start;
		currentDate = this.start;
		this.end = end;

		try{
			//initialisation des entit�s simul�es
			for(EntiteSimulee e: mesEntitesSimulees){
				e.requestInit();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	//initialisation de la simulation sans param�tres de d�but et fin
	//Le d�but et fin viennent du sc�nario
	public void initSimulation() {
		if(currentscenario!=null) {
			this.start=currentscenario.getDebut();
			currentDate = this.start;
			this.end = currentscenario.getFin();
			
			currentscenario.requestInit();
		}
	}

	//seule m�thode donnant acc�s � l'�ch�ancier
	protected void Post(SimEvent ev) {
		echeancier.add(ev);
	}
	
	//boucle de simulation
	public void simulate()
	{
		//simple parcours de l'�ch�ancier
		while(hasANextEvent())
		{
			//on prend le premier �v�nement suivant de l'�ch�ancier
			SimEvent ev = echeancier.first();
			// System.out.println("event name (SimuEngine) : "+ev.entitePorteuseEvenement.getName());
			//on l'enl�ve de l'�ch�ancier
			echeancier.remove(ev);
			
			//si l'entit� est DEAD on ne tire pas l'�v�nement
			if(ev.entitePorteuseEvenement.getEtat()==EtatEntite.INITIALIZED) {
				currentDate = ev.getDateOccurence();
				ev.process();
			}
		}
	}
	
	public void terminate(boolean last) {
		//on vide l'�ch�ancier.
		//il peut contenir des �v�nements restants
		//indispensable pour le prochain run
		echeancier.clear();
		
		//on termine les entit�s.
		//on vide la liste des entit�s
		for(EntiteSimulee e:mesEntitesSimulees)
		{
			//seule l'entit� sc�nario persiste d'un run � l'autre			
			if(!last) {
				if(!(e instanceof Scenario)) e.terminate();
			}
			else
				e.terminate();
		}
		mesEntitesSimulees.clear();
		
		//on ne fait plus r�f�rence au sc�nario pr�c�dent
		currentscenario = null;
		
		//on met � zero les �l�ments de temps logique
		currentDate = null;
		start = null;
		end = null;

		//on sollicite le garbage collector pour qu'il cleane la m�moire
		//c'est un bon moment pour le faire
		System.gc();

	}

	//permet de savoir s'il reste un �v�nement encore � traiter
	public boolean hasANextEvent() { //TODO: remake private
		if(echeancier.size()>0) {
			if(echeancier.first().getDateOccurence().compareTo(end)<=0){
				return true;
			}
		}

		return false;
	}


	//moteur de recherche tr�s simple sur les entit�s
	public List<EntiteSimulee> recherche(Predicate<EntiteSimulee> query) {
		List<EntiteSimulee> resultats = new ArrayList<>();
		for(EntiteSimulee e:mesEntitesSimulees)
			if(query.test(e)) resultats.add(e);
		return resultats;
	}

	
	@Override
	public ScenarioId getScenarioId() {
		if (currentscenario != null)
			return currentscenario.getID();
		else
			return null;
	}

	@Override
	public LogicalDateTime SimulationDate() {
		return currentDate;
	}

	public Piste getPiste() {
		for (EntiteSimulee e : mesEntitesSimulees) {
			if (e instanceof Piste) {
				return (Piste) e;
			}
		}
		return null;
	}

	public Tour getTour() {
		for (EntiteSimulee e : mesEntitesSimulees) {
			if (e instanceof Tour) {
				return (Tour) e;
			}
		}
		return null;
	}

	public TaxiWay getEnteringTaxiway() {
		//Attention, cette méthode ne fonctionne que pour 1 seul taxiway entrant
		// Pour plusieurs taxiway entrant, il faudra faire une liste ou utiliser une méthode similaire à celle de getGate()
		for (EntiteSimulee es : mesEntitesSimulees) {
			if (es instanceof TaxiWay) {
				TaxiWay tw = (TaxiWay) es;
				if (tw.getDirection().equals("Entrant")) {
					return tw;
				}
			}
		}
		return null;
	}

	public TaxiWay getLeavingTaxiway() {
		//Attention, cette méthode ne fonctionne que pour 1 seul taxiway sortant
		// Pour plusieurs taxiway sortant, il faudra faire une liste ou utiliser une méthode similaire à celle de getGate()
		for (EntiteSimulee es : mesEntitesSimulees) {
			if (es instanceof TaxiWay) {
				TaxiWay tw = (TaxiWay) es;
				if (tw.getDirection().equals("Sortant")) {
					return tw;
				}
			}
		}
		return null;
	}


	public boolean getGate() {
		//returns true if at least one gate is free
		for (EntiteSimulee es : mesEntitesSimulees) {
			if (es instanceof Gate) {
				Gate g = (Gate) es;
				if (!g.getOccupe()){
					return true;
				}
			}
		}
		return false;
	}
}
