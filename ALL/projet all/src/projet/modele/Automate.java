package projet.modele;

import java.util.ArrayList;

public abstract class Automate {
	
	
	public abstract void ajouterEtatInitial(String nom);
	public abstract void ajouterEtatTerminal(String nom);
	public abstract void initialisationAlphabet(ArrayList<String> alpha);
	
}
