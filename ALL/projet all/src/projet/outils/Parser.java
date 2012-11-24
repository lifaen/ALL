package projet.outils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import projet.exceptions.ExceptionLectureFichier;
import projet.modele.AutomateFini;
import projet.modele.Constantes;
import projet.modele.Etat;
import projet.modele.MachineDeTuring;


public class Parser {


	
	public static void parserMachineDeTuring(File fichier, MachineDeTuring machine){
		
		ArrayList<String> buffer = null;

		//lecture du fichier *.txt ligne par ligne	
		try{

			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fichier)));
			String ligne;
			while ((ligne=br.readLine())!=null){

				//Si ligne contient initial

				if((buffer = analyseRegen(ligne, Constantes.DESIGNATION_ETAT_INITIAL)) != null){

					for(String nom: buffer){

						machine.ajouterEtatInitial(nom);

					}

				}
				else if((buffer = analyseRegen(ligne, Constantes.DESIGNATION_ETAT_TERMINAL)) != null){

					//Si ligne contient terminal
					for(String nom: buffer){

						machine.ajouterEtatTerminal(nom);

					}


				}else if((buffer = analyseRegen(ligne, Constantes.DESIGNATION_ETAT_ALPHABET_TERMINAL)) != null){

					//Si ligne contient alphabet_terminal
					machine.initialisationAlphabet(buffer);


				}else{
					//Si ligne contient 3 éléments et transition présente dans alphabet terminal si ce dernier renseigner
					buffer = analyseRegen(ligne, "");
					if(buffer.size() != 3){

						throw new ExceptionLectureFichier("Erreur sur le contenu de la ligne \""+ligne+"\", seul trois paramètres sont attendus: l'état de départ, la nature de la transition et l'état d'arrivée.");

					}

					if(machine.estUneEtiquetteValide(buffer.get(1))){

						Etat etatDepart = null;
						Etat etatArrivee = null;
						if((etatDepart = automate.existeEtat(buffer.get(0))) == null){

							etatDepart = new Etat(buffer.get(0), false, false);
							automate.ajouterEtat(etatDepart);

						}

						if((etatArrivee = automate.existeEtat(buffer.get(2))) == null){

							etatArrivee = new Etat(buffer.get(2), false, false);
							automate.ajouterEtat(etatArrivee);

						}

						etatDepart.ajouterTransition(etatArrivee, buffer.get(1));
					}

				}

			}
			
			br.close(); 
			

		}		
		catch (Exception e){

			throw new ExceptionLectureFichier("Erreur lors de la lecture du fichier "+fichier.getName());

		}
		
	}
	/**
	 * @param Chemin Chemin d'un fichier à lire
	 * @return Chaine de caractères où est enregistrée le contenu du fichier
	 * @throws ExceptionLectureFichier 
	 */
	public static void parserAutomateFini(File fichier, AutomateFini automate) throws ExceptionLectureFichier{

		ArrayList<String> buffer = null;

		//lecture du fichier *.txt ligne par ligne	
		try{

			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fichier)));
			String ligne;
			while ((ligne=br.readLine())!=null){

				//Si ligne contient initial

				if((buffer = analyseRegen(ligne, Constantes.DESIGNATION_ETAT_INITIAL)) != null){

					for(String nom: buffer){

						automate.ajouterEtatInitial(nom);

					}

				}
				else if((buffer = analyseRegen(ligne, Constantes.DESIGNATION_ETAT_TERMINAL)) != null){

					//Si ligne contient terminal
					for(String nom: buffer){

						automate.ajouterEtatTerminal(nom);

					}


				}else if((buffer = analyseRegen(ligne, Constantes.DESIGNATION_ETAT_ALPHABET_TERMINAL)) != null){

					//Si ligne contient alphabet_terminal
					automate.initialisationAlphabet(buffer);


				}else{
					//Si ligne contient 3 éléments et transition présente dans alphabet terminal si ce dernier renseigner
					buffer = analyseRegen(ligne, "");
					if(buffer.size() != 3){

						throw new ExceptionLectureFichier("Erreur sur le contenu de la ligne \""+ligne+"\", seul trois paramètres sont attendus: l'état de départ, la nature de la transition et l'état d'arrivée.");

					}

					if(automate.estUneEtiquetteValide(buffer.get(1))){

						Etat etatDepart = null;
						Etat etatArrivee = null;
						if((etatDepart = automate.existeEtat(buffer.get(0))) == null){

							etatDepart = new Etat(buffer.get(0), false, false);
							automate.ajouterEtat(etatDepart);

						}

						if((etatArrivee = automate.existeEtat(buffer.get(2))) == null){

							etatArrivee = new Etat(buffer.get(2), false, false);
							automate.ajouterEtat(etatArrivee);

						}

						etatDepart.ajouterTransition(etatArrivee, buffer.get(1));
					}

				}

			}
			
			br.close(); 
			

		}		
		catch (Exception e){

			throw new ExceptionLectureFichier("Erreur lors de la lecture du fichier "+fichier.getName());

		}

	}


	public static ArrayList<String> analyseRegen(String ligne, String regen){

		if(ligne.startsWith(regen)){

			ArrayList<String> buffer = new ArrayList<String>();
			String[] etats = ligne.split(" ");

			for(int i = (!regen.isEmpty())? 1 : 0 ; i <etats.length; i++){

				buffer.add(etats[i]);

			}

			return buffer;

		}

		return null;		

	}




}
