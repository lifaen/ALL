package projet.outils;




import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

import projet.exceptions.ExceptionCheminRepertoireInvalide;
import projet.exceptions.ExceptionSaisieStringFormat;


public class OutilsSaisie {
	
		
	/**
	 * @param messageSaisie Message affiché à l'utilisateur lui demandant de saisir une chaine de caractère spécifique.
	 * @param limiteTailleString Valeur limite de la chaine de caractères saisie
	 * @return La chaine de caractère saisie. Des exceptions sont levées pour s'assurer de la cohérence
	 * de la chaine fournie. Tant qu'une information correcte n'est pas renseignée, on demande à l'utilisateur d'en saisir une.
	 */
	
	public static String saisieString(String messageSaisie){
		
		String buffer = new String();
		Scanner in = new Scanner(System.in);
		
		System.out.println(messageSaisie);
		try {
			buffer = in.nextLine();

			if (buffer.length() < 1 ){
				
				throw new ExceptionSaisieStringFormat("La chaine de caractères saisie est invalide. Merci de réitérer votre saisie:");
				
			}
			
		} catch (ExceptionSaisieStringFormat e) {
			
			System.err.println(e.getMessage());	
			buffer = saisieString(messageSaisie);
		}
		catch (NoSuchElementException | IllegalStateException e2){
			
			System.err.println("Une erreur dans le processus de saisie a eu lieu. Merci de réitérer votre saisie ");
			buffer = saisieString(messageSaisie);
			
		}
			
		return buffer;
		
	}
	
	/**
	 * @return Retourne un chemin valide vers un répertoire
	 */
	public static String saisieCheminValide(){
		
		String buffer = saisieString("Sélectionnez un répertoire :");
		try{
			
			if(buffer == null || buffer.isEmpty() || !new File(buffer).exists() || !new File(buffer).isFile() ||  !buffer.endsWith(".txt")){

				throw new ExceptionCheminRepertoireInvalide("Le chemin du fichier fourni est invalide. Merci de founir un chemin correct vers un fichier .txt :");
				
			}
			
		}
		catch(ExceptionCheminRepertoireInvalide e){
			
			System.err.println(e.getMessage());
			buffer= saisieCheminValide();
			
		}
		
		return buffer;
	}

}
