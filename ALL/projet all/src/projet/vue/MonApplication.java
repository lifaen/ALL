package projet.vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import projet.controleur.ControleurBouton;
import projet.modele.Constantes;




public class MonApplication extends JFrame{
	
	private static final long serialVersionUID = 1795068992367097533L;
	private JPanel type_automate;
	private JLabel label_fichier;
	private JTextField chemin_fichier;
	private JButton btn_parcourir;
	private JLabel label_mot;
	private JTextField champ_mot;
	private JButton btn_valider;
	private ButtonGroup groupe_radio;
	private JLabel label_commentaire;


	
	
	public MonApplication(){
		
		
		this.type_automate = new JPanel(new GridLayout(0, 1));
		Border border = BorderFactory.createTitledBorder("Type de l'automate étudié");
		this.type_automate.setBorder(border);
		this.groupe_radio = new ButtonGroup();
		
		JRadioButton radio1 = new JRadioButton(Constantes.TYPE_AUTOMATE[0]);	
		radio1.setActionCommand(Constantes.TYPE_AUTOMATE[0]);
		groupe_radio.add(radio1);
		
		JRadioButton radio2 = new JRadioButton(Constantes.TYPE_AUTOMATE[1]);	
		radio2.setActionCommand(Constantes.TYPE_AUTOMATE[1]);
		groupe_radio.add(radio2);
				
		this.type_automate.add(radio1);
		this.type_automate.add(radio2);		
		this.type_automate.setPreferredSize(new Dimension(530, 80));
		
		this.label_fichier = new JLabel("Chemin du fichier :");
		this.chemin_fichier = new JTextField();		
		this.chemin_fichier.setEnabled(false);
		
		this.chemin_fichier.setPreferredSize(new Dimension (300, 30));
		this.btn_parcourir = new JButton(Constantes.BTN_PARCOURIR);
		
		this.label_mot = new JLabel("Mot à reconnaitre :");
		this.champ_mot = new JTextField();
		this.champ_mot.setPreferredSize(new Dimension (300, 30));
		this.btn_valider = new JButton(Constantes.BTN_TESTER);
		this.btn_valider.setEnabled(false);
		this.label_commentaire = new JLabel();
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c =new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		
		c.insets = new Insets(0,5,10,5);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		this.add(this.type_automate, c);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		this.add(this.label_fichier, c);
		
		c.gridx = 1;
		c.gridy = 1;		
		this.add(this.chemin_fichier, c);
		
		c.gridx = 2;
		c.gridy = 1;
		this.add(this.btn_parcourir, c);		
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(this.label_mot, c);
		
		c.gridx = 1;
		c.gridy = 2;
		this.add(this.champ_mot, c);
		
		c.insets = new Insets(15,0,5,0);
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		this.add(this.btn_valider, c);
		
		c.insets = new Insets(5,0,5,0);
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 1;
		this.add(this.label_commentaire, c);
		
		ControleurBouton controleur = new ControleurBouton(this);
		
		this.btn_parcourir.addActionListener(controleur);
		this.btn_valider.addActionListener(controleur);
		radio1.addActionListener(controleur);
		radio2.addActionListener(controleur);
		
		this.setPreferredSize(new Dimension (600, 400));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Projet All - v 1.0");	
		
	}
	
	public String getChemin(){
		
		return this.chemin_fichier.getText();
		
	}
	
	public void setCheminFichier(String chemin){
		
		this.chemin_fichier.setText(chemin);
		
	}
	
	public void validation(){
		
		this.btn_valider.setEnabled((!this.chemin_fichier.getText().isEmpty() && this.groupe_radio.getSelection() != null));
		this.label_commentaire.setText(null);

	}
	
	public void setCommentaire(String commentaire, Boolean erreur){
		
		this.label_commentaire.setText("<html><font color="+ ((erreur)? "red": "green")+">"+commentaire+"</font></html>");
		
	}
	
	public String getMot(){
		
		return this.champ_mot.getText();
		
	}
	/**
	 * Fonction permettant de lancer l'éditeur
	 */
	public void lancer(){
		
		
		this.pack();
		//On place la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		
		
		MonApplication monApplication = new MonApplication();
		monApplication.lancer();
		//File fichier = new File(OutilsSaisie.saisieCheminValide());
		
		

	}

}
