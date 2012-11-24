package projet.modele;


import java.util.ArrayList;

import projet.exceptions.ExceptionCycleEpsilone;

public class AutomateFini extends Automate{

	private ArrayList<Etat> listeEtatsInitiaux;
	private ArrayList<String> alphabet_terminal;
	private ArrayList<Etat> listeEtats;

	public AutomateFini(){

		this.listeEtatsInitiaux = new ArrayList<Etat>();
		this.listeEtats = new ArrayList<Etat>();
		this.alphabet_terminal = new ArrayList<String>();

	}

	public void ajouterEtatInitial(String nom){

		Etat e = null;
		if((e = this.existeEtat(nom)) != null){

			e.setInitial(true);

		}
		else{

			e = new Etat(nom, true, false);
			this.listeEtatsInitiaux.add(e);
			this.ajouterEtat(e);

		}


	}

	public void ajouterEtatTerminal(String nom){

		Etat e = null;
		if((e = this.existeEtat(nom)) != null){

			e.setTerminal(true);

		}
		else{

			e = new Etat(nom, false, true);
			this.ajouterEtat(e);

		}
	}
	
	public void initialisationAlphabet(ArrayList<String> alpha){
		
		if(!alpha.isEmpty()){
			
			this.alphabet_terminal.addAll(alpha);
			
		}		
	}
	
	public void ajouterEtat(Etat e){
		
		this.listeEtats.add(e);
		
	}
	
	public Etat existeEtat(String nom){

		for(Etat e: listeEtats){

			if(e.getNom().equals(nom)){

				return e;

			}
		}
		return null;
	}
	
	public Etat existeEtat(Etat e){

		for(Etat etat: listeEtats){

			if(etat.equals(e)){

				return etat;

			}
		}
		return null;
	}
	
	public boolean estUneEtiquetteValide(String nom){
		
		if(this.alphabet_terminal.isEmpty()){
			
			return true;
			
		}
		else{
			
			for(String alpha: this.alphabet_terminal){
				
				if(alpha.equals(nom)){
					
					return true;
					
				}
			}
			return false;
		}
	}
	
	@Override
	public String toString(){
		
		String chaine;
		chaine = "Liste des états :\n";
		for(Etat e: this.listeEtats){
			chaine+= e.toString();
		}
		return chaine;
	}

	public boolean verificationMot(String mot){

		Boolean verif= false;

		for(int i=0; i <this.listeEtatsInitiaux.size(); i++){

			verif = verif || verification(mot, 0, this.listeEtatsInitiaux.get(i));

		}

		return verif;


	}

	private boolean verification(String mot, int indice, Etat e) {


		ArrayList<Etat> buffer, buffer2;

		Boolean verif = false;
		if (indice == mot.length()){

			if(!e.isTerminal()){

				buffer2 = e.getEtatTransition(Constantes.DESIGNATION_TRANSITION_EPSILONE);

				if(buffer2 == null){

					return false;

				}
				else{

					for(int i=0; i <buffer2.size(); i++){

						verif = verif || verification(mot, indice, buffer2.get(i));

					}
					return verif;

				}
			}
			else{

				return true;

			}

		}

		System.out.println("Sommet traité : "+e.getNom());
		System.out.print("Mot étudié : ");
		for(int cpt=0; cpt <= indice; cpt++){
			System.out.print(mot.charAt(cpt));
		}
		System.out.println(" ");


		buffer = e.getEtatTransition((mot.charAt(indice))+"");		
		buffer2 = e.getEtatTransition(Constantes.DESIGNATION_TRANSITION_EPSILONE);

		if(buffer == null && buffer2 == null){

			return false;

		}

		if(buffer != null ){

			for(int i=0; i <buffer.size(); i++){

				verif = verif || verification(mot, indice + 1, buffer.get(i));

			}

		}

		if(buffer2 != null ){

			for(int i=0; i <buffer2.size(); i++){

				verif = verif || verification(mot, indice, buffer2.get(i));

			}

		}


		return verif;



	}

	public void detectionCycleEsilone() throws ExceptionCycleEpsilone{

		Boolean verif= false;

		for(int i=0; i <this.listeEtats.size(); i++){

			verif = verif || detectionCycleEsilone(this.listeEtats.get(i), null);
			if (verif){

				break;
			}

		}
		
		if(verif){
			
			throw new ExceptionCycleEpsilone("Un cycle epsilone a été détecté. Votre fichier est invalide !");
			
		}

	}

	private boolean detectionCycleEsilone(Etat e, ArrayList<Etat> listeEtatsEpsilone){

		Boolean verif = false;
		System.out.println("Sommet traité : "+e.getNom());
		ArrayList<Etat> buffer2 = e.getEtatTransition(Constantes.DESIGNATION_TRANSITION_EPSILONE);
		if (buffer2 == null){

			return false;

		}

		for(int i=0; i < buffer2.size(); i++){

			if(listeEtatsEpsilone != null){

				if(listeEtatsEpsilone.contains(buffer2.get(i))){

					return true;

				}

			}
			else{

				listeEtatsEpsilone = new ArrayList<Etat>();

			}
			listeEtatsEpsilone.add(e);
			verif = verif || detectionCycleEsilone(buffer2.get(i), listeEtatsEpsilone); 
			if (verif){

				break;
			}

		}

		return verif;


	}


}
