/**
 * 
 */
package com.demoswing.components;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

// TODO  - OK, mais pour faire plus joli c'aurait été pas mal d'utiliser MyFile
// apres l'avoir fait implémenter MutableTreeNode
public class FileTreePane extends JPanel {

	private JTree arbre;
	
	public FileTreePane(){
			
	  DefaultMutableTreeNode racine = new DefaultMutableTreeNode();       
	  listRoots(racine);
		    
	  arbre = new JTree(racine);      
	  JScrollPane jTreeScrollPanel = new JScrollPane(arbre);
      jTreeScrollPanel.setPreferredSize(new Dimension(150, 500));
      add(jTreeScrollPanel);
      
	}


	private void listRoots(DefaultMutableTreeNode racine) {
		for(File file : File.listRoots()){
		  DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getAbsolutePath());
		  if(file.isDirectory()){ 
			  for(File nom : file.listFiles()){
		          DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+"\\");               
		          lecteur.add(this.listFile(nom, node));               
		        }    
		  }
		  racine.add(lecteur);                 
		}
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
	 * @return the arbre
	 */
	public JTree getArbre() {
		return arbre;
	}


	/**
	 * @param arbre the arbre to set
	 */
	public void setArbre(JTree arbre) {
		this.arbre = arbre;
	}
	
	
}
