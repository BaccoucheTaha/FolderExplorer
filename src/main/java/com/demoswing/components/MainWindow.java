/**
 * 
 */
package com.demoswing.components;

import com.demoswing.util.FilesystemUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;


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
    JPanel uriPanel;

    JButton button;
	/**
	 * constructor 
	 */
	public MainWindow() {
		this.setTitle("Expolrateur de fichiers");
		this.setSize(800, 600);
		this.setResizable(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//setLayout(new BorderLayout());

        // Pour de raisons de lisibilité et maintenabilité du code
        // on essaye en général d'isoler dans de methodes séparées
        // les instriction correspondant aux differentes étappes
        initUi();

        installListeners();

        getContentPane().add(uriPanel, BorderLayout.PAGE_START);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);
	    
	    pack();
	}

    private void initUi() {
        // Construction du panel principal
        mainPanel = new JPanel(new BorderLayout());

        // construction du panel
        uriPanel = new JPanel(new FlowLayout());

        // label
        JLabel label = new JLabel("Chemin du r�pertoire :");

        // construction du texte field (chemin du repertoire)
        textField = new JTextField();
        textField.setColumns(40);

        // construction du bouton "valider"
//        JButton button = new JButton(new AbstractAction() {
        // En général on préferre de fournir explicitement les proporiétes pour les composants
        button = new JButton("Valider");
        uriPanel.add(label);
        uriPanel.add(textField);
        uriPanel.add(button);
    }

    private void installListeners() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Est-ce que tu pourrais - si on tient appuyé la touche Ctrl au moment ou on appuient le bouton Validate verrifier que le chemin donné pointe bien un répertoire ?
                String folderPath = MainWindow.this.getTextField().getText();

                File file = new File(folderPath);

                if (!file.exists()) {
                    MainWindow.this.buildInfoPanel("l'uri renseignée n'est pas valide");
                } else if (!file.isDirectory()) {
                    MainWindow.this.buildInfoPanel("l'uri renseignée ne désigne pas un repertoire");
                } else {
                    MainWindow.this.buildMainPanel(
                            FilesystemUtils.getFolderContent(file));
                }
            }
        });
    }

    /**
	 * construit un panel contenant des informations sur uri entr�e 
	 */
	public void buildInfoPanel(String text){
		if(tableau != null){
			tableau.setVisible(false);
			tableau.getTableHeader().setVisible(false);
		}

		if(infoLabel == null) {
			infoLabel = new JLabel();
		    mainPanel.add(infoLabel , BorderLayout.WEST);
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
			buildInfoPanel("Le r�pertoire est vide");
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
