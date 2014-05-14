/**
 * 
 */
package com.demoswing.components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.demoswing.action.ValiderAction;


public class MainWindow extends JFrame {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -840221350852746153L;

	
	JTextField textField = null;
	
	// info panel bloc 
	JPanel infoPanel = null;
	JLabel infoLabel = null ;
	
	// main panel bloc
	JPanel mainPanel = null;
	JTable tableau = null;
	/**
	 * constructor 
	 */
	public MainWindow(){
		setTitle("Expolrateur de fichiers");
		setSize(800, 600);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setLayout(new BorderLayout());
		
		// construction du panel 
		JPanel uriPanel = new JPanel(new FlowLayout());
	    add(uriPanel, BorderLayout.PAGE_START);
		
	    // label
	    JLabel label = new JLabel("Chemin du répertoire :");
	    
	    // construction du texte field (chemin du repertoire)
	    textField = new JTextField();
	    textField.setColumns(40);
 
		// construction du bouton "valider" 
		JButton button = new JButton(new ValiderAction(this, "Valider"));
		
		uriPanel.add(label);
		uriPanel.add(textField);
		uriPanel.add(button);
		

		// Construction du panel principal 
	    mainPanel = new JPanel(new BorderLayout()); 
	    this.add(mainPanel, BorderLayout.SOUTH);
	    
	    pack();
	}
	
	/**
	 * construit un panel contenant des informations sur uri entrée 
	 */
	public void buildInfoPanel(String text){
		if(tableau != null){
			tableau.setVisible(false);
			tableau.getTableHeader().setVisible(false);
		}
		if(infoLabel == null){
			infoLabel = new JLabel();
		    mainPanel.add(infoLabel);
		}
		
		infoLabel.setText(text);
		infoLabel.setVisible(true);
		this.pack();
	}
	
	/**
	 * Construit le panel affichant la liste des fichiers/dossiers contenus dans le repertoire en question
	 */
	public void buildMainPanel(List<MyFile> filesList) {
		
		if (filesList.isEmpty()) {
			buildInfoPanel("Le répertoire est vide");
			
		} else {
			if (infoLabel != null) {
				infoLabel.setVisible(false);
			}

			tableau = new JTable(new MyJTableModel(filesList));
			mainPanel.add(tableau.getTableHeader(), BorderLayout.NORTH);
			mainPanel.add(tableau, BorderLayout.CENTER);

			tableau.setVisible(true);

			this.pack();

		}
	}
	
	/**
	 * @return textField
	 */
	public JTextField getTextField() {
		return textField;
	}

	/**
	 * @param textField
	 */
	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	
}
