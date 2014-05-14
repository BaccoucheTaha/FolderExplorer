package com.demoswing.action;

import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;

import com.demoswing.components.MainWindow;
import com.demoswing.components.MyFile;

public class ValiderAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6797355167234719164L;

	private MainWindow mainWindow = null;
	
	public ValiderAction(MainWindow mainWindow, String buttonLabel){
		super(buttonLabel);
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String folderPath = mainWindow.getTextField().getText();
		
		File file = new File(folderPath); 

		if(!file.exists()){
			mainWindow.buildInfoPanel("l'uri renseign�e n'est pas valide");
		}else if(!file.isDirectory()){
			mainWindow.buildInfoPanel("l'uri renseign�e ne d�signe pas un repertoire");
		}else{
			mainWindow.buildMainPanel(getFolderContent(file));
			
		}
		
		
		
	}
	
	/**
	 * r�cup�re la liste des fichiers/dossiers contenus dans le repertoire en entr�e
	 * @param file
	 * @return
	 */
	private List<MyFile> getFolderContent(File file){
		
		List<MyFile> filesList = new ArrayList<MyFile>(); 
		
		for(File currentFile : file.listFiles()){
			
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date lastModifiedDate = new Date(currentFile.lastModified());
			
			String lastModification = ft.format(lastModifiedDate);
			
			MyFile myCurrentFile = new MyFile(currentFile.getName(), lastModification, currentFile.length(), currentFile.isDirectory());
			filesList.add(myCurrentFile);
		}
		
		return filesList;
	}

}
