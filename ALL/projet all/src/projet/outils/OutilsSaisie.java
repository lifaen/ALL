package projet.outils;




import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;

import projet.exceptions.ExceptionCheminRepertoireInvalide;
import projet.exceptions.ExceptionSaisieStringFormat;


public class OutilsSaisie {
	
		
	/**
	 * @param messageSaisie Message affich� � l'utilisateur lui demandant de saisir une chaine de caract�re sp�cifique.
	 * @param limiteTailleString Valeur limite de la chaine de caract�res saisie
	 * @return La chaine de caract�re saisie. Des exceptions sont lev�es pour s'assurer de la coh�rence
	 * de la chaine fournie. Tant qu'une information correcte n'est pas renseign�e, on demande � l'utilisateur d'en saisir une.
	 */
	
	public static String saisieString(String messageSaisie){
		
		String buffer = new String();
		Scanner in = new Scanner(System.in);
		
		System.out.println(messageSaisie);
		try {
			buffer = in.nextLine();

			if (buffer.length() < 1 ){
				
				throw new ExceptionSaisieStringFormat("La chaine de caract�res saisie est invalide. Merci de r�it�rer votre saisie:");
				
			}
			
		} catch (ExceptionSaisieStringFormat e) {
			
			System.err.println(e.getMessage());	
			buffer = saisieString(messageSaisie);
		}
		catch (NoSuchElementException | IllegalStateException e2){
			
			System.err.println("Une erreur dans le processus de saisie a eu lieu. Merci de r�it�rer votre saisie ");
			buffer = saisieString(messageSaisie);
			
		}
			
		return buffer;
		
	}
	
	/**
	 * @return Retourne un chemin valide vers un r�pertoire
	 */
	public static String saisieCheminValide(){
		
		String buffer = saisieString("S�lectionnez un r�pertoire :");
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
