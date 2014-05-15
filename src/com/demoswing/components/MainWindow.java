/**
 * 
 */
package com.demoswing.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.lang3.StringUtils;

import com.demoswing.util.FilesystemUtils;


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
        getContentPane().add(mainPanel, BorderLayout.CENTER);
	    
	    pack();
	}

    private void initUi() {
        // Construction du panel principal
        mainPanel = new JPanel(new BorderLayout());

        // construction du panel
        uriPanel = new JPanel(new FlowLayout());

        // label
        JLabel label = new JLabel("Chemin du répertoire :");

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
        
        buildTreePanel();
        
        
    }

    private void installListeners() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            	String folderPath = MainWindow.this.getTextField().getText();
                File file = new File(folderPath);
                
                int modifiers = e.getModifiers();
                if ( StringUtils.equals("Valider" , e.getActionCommand()) && checkMod(modifiers, ActionEvent.CTRL_MASK)){
                	
                	buildValidationUriMsgPane(file);
                	
                }

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
   
    private static void buildValidationUriMsgPane(File file){
    	if(!file.exists()){
    		JOptionPane.showMessageDialog( null,"l'uri renseignée n'est pas valide" ,"Validation de l'uri", JOptionPane.INFORMATION_MESSAGE);	
    	}else if (!file.isDirectory()){
    		JOptionPane.showMessageDialog( null,"l'uri renseignée ne désigne pas un repertoire","Validation de l'uri", JOptionPane.INFORMATION_MESSAGE);
    	}else{
    		JOptionPane.showMessageDialog( null,"l'uri renseignée désigne bien un repertoire","Validation de l'uri", JOptionPane.INFORMATION_MESSAGE);
    	}
    	
    }

    private boolean checkMod(int modifiers, int mask) {
        return ((modifiers & mask) == mask);
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
	
	
	
	  private void buildTreePanel(){      
		  DefaultMutableTreeNode racine = new DefaultMutableTreeNode();       
		    for(File file : File.listRoots()){
		      DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getAbsolutePath());
		      try {
		        for(File nom : file.listFiles()){
		          DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+"\\");               
		          lecteur.add(this.listFile(nom, node));               
		        }
		      } catch (NullPointerException e) {
		    	  System.out.println(e.getCause());
		    	}

		      racine.add(lecteur);                 
		    }
		    
		    JTree arbre = new JTree(racine);      
		    //arbre.setPreferredSize(new Dimension(100, 600));
		    JScrollPane jTreeScrollPanel = new JScrollPane(arbre);
		    jTreeScrollPanel.setPreferredSize(new Dimension(150, 500));
		    getContentPane().add(jTreeScrollPanel, BorderLayout.WEST);
		    this.pack();
		  }

	  private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node){
		    int count = 0;
		      
		    if(file.isFile())
		      return new DefaultMutableTreeNode(file.getName());
		    else{
		      File[] list = file.listFiles();
		      if(list == null)
		        return new DefaultMutableTreeNode(file.getName());


		      for(File nom : list){
		        count++;
		        //Pas plus de 5 enfants par noeud
		        if(count < 5){
		          DefaultMutableTreeNode subNode;
		          if(nom.isDirectory()){
		            subNode = new DefaultMutableTreeNode(nom.getName()+"\\");
		            node.add(this.listFile(nom, subNode));
		          }else{
		            subNode = new DefaultMutableTreeNode(nom.getName());
		          }
		          node.add(subNode);
		        }
		      }
		      return node;
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
