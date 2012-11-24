package projet.modele;

import java.util.ArrayList;
import java.util.HashMap;


public class Etat {

	private String nom;
	private HashMap<String, ArrayList<Etat>> suivants;
	private boolean initial;
	private boolean terminal;
	
	public Etat(String nom, boolean estEtatInitial, boolean estEtatTerminal){
		
		this.nom = nom;
		this.setInitial(estEtatInitial);
		this.setTerminal(estEtatTerminal);
		this.suivants = new HashMap<String, ArrayList<Etat>>();
		
	}
	
	public void ajouterTransition(Etat etat, String valeur){
		
		if(!this.suivants.containsKey(valeur)){
			
			this.suivants.put(valeur, new ArrayList<Etat>());
			
		}
		
		this.suivants.get(valeur).add(etat);
		
	}
	
	
	public boolean equals(Object o){
		
		if(o == null){
			
			return false;
		
		}
		if(o == this){
			
			return true;
		
		}
		else{
			
			Etat e = (Etat)o;
			return this.nom.equals(e.nom);
			
		}
	}

	public String getNom() {
		
		return nom;
		
	}

	public boolean isTerminal() {
		
		return terminal;
		
	}

	public void setTerminal(boolean terminal) {
		
		this.terminal = terminal;
		
	}

	public boolean isInitial() {
		
		return initial;
		
	}

	public void setInitial(boolean initial) {
		
		this.initial = initial;
		
	}
	
	
	public ArrayList<Etat> getEtatTransition(String etiquette){
		
		if(this.suivants.containsKey(etiquette)){
			
			return this.suivants.get(etiquette);
			
		}
		return null;
	}
	
	public String toString(){
		
		String chaine;
		chaine = "Nom de l'état :"+this.nom+"\n";
		
		if(!this.suivants.isEmpty()){
			
			chaine+="\n Transitions :";
			for(String etiquette : this.suivants.keySet()){
				
				chaine += "Etiquette "+etiquette+" =>";
				for(Etat e: this.suivants.get(etiquette)){
					
					chaine+= e.nom+" ";
					
				}
			}
			
		}
			
		else
			chaine+="\n Pas de transition.";
		
		
		
		return chaine+"\n";
	}
	
	
}
