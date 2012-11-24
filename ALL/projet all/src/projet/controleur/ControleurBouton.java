package projet.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import projet.exceptions.ExceptionCycleEpsilone;
import projet.exceptions.ExceptionLectureFichier;
import projet.modele.AutomateFini;
import projet.modele.Constantes;
import projet.outils.Parser;
import projet.vue.MonApplication;

public class ControleurBouton implements ActionListener{

	private MonApplication monApplication;
	private File fichier;
	private String type;

	public ControleurBouton( MonApplication monApplication){		
		
		this.monApplication = monApplication;
		this.fichier = null;
		this.type = null;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		if(e.getSource() instanceof JButton){
			
			JButton bouton_selectionne = (JButton)e.getSource();
			
			if(bouton_selectionne.getText().equals(Constantes.BTN_PARCOURIR)){
				
				JFileChooser selectionneur_fichier = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

				selectionneur_fichier.setAcceptAllFileFilterUsed(false);
				selectionneur_fichier.setFileFilter(new FileFilter(){

					@Override
					public boolean accept(File f) {
						
						if(f.isFile()){
							
							for (int i=0; i < Constantes.EXTENSION_FICHIER_ACCEPTEE.length; i++){
								
								if (f.getName().endsWith(Constantes.EXTENSION_FICHIER_ACCEPTEE[i])){
									
									return true;
									
								}								
							}
						}
						
						return false;
						
					}

					@Override
					public String getDescription() {
						
						return ".txt";
					}
					
				});
				
				int returnVal = selectionneur_fichier.showOpenDialog(null);
		        //Si l'utilsiateur a bien choisi un fichier
		        if(returnVal == JFileChooser.APPROVE_OPTION){
		        	
		        	this.fichier = selectionneur_fichier.getSelectedFile();
		        	this.monApplication.setCheminFichier(this.fichier.getAbsolutePath());
		        	this.monApplication.validation();
		        	
		        	
		        }
			}
			else if(bouton_selectionne.getText().equals(Constantes.BTN_TESTER)){
				
				if(Constantes.TYPE_AUTOMATE[0].equals(this.type)){
					
					
					try {
						
						AutomateFini automate = new AutomateFini();
						
						Parser.parserAutomateFini(this.fichier, automate);
						System.out.println(automate);
						automate.detectionCycleEsilone();
						
						if(automate.verificationMot(this.monApplication.getMot())){
							
							this.monApplication.setCommentaire("Le mot "+this.monApplication.getMot()+" a été reconnu par l'automate.", false);
							
							
						}else{
							
							this.monApplication.setCommentaire("Le mot "+this.monApplication.getMot()+" n'a pas été reconnu par l'automate.", true);
							
						}
						
						
						
					} catch (ExceptionLectureFichier | ExceptionCycleEpsilone ex) {
						
						this.monApplication.setCommentaire(ex.getMessage(), true);
						
					}
					
					
				}
			}
			
		}
		else if(e.getSource() instanceof JRadioButton){
			
			this.type = e.getActionCommand();
			System.out.println(this.type);
			this.monApplication.validation();
		}
		
	}

}
