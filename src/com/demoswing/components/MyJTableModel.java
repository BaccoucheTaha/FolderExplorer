package com.demoswing.components;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MyJTableModel extends AbstractTableModel {

	
	private List<MyFile> filesList = null;
	
	 private final String[] header = {"Nom", "Modifié le", "Type" ,"Taille"};
	
	public MyJTableModel(List<MyFile> filesList) {
		super();
		this.filesList = filesList;
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}
	
	
	 public String getColumnName(int columnIndex) {
	        return header[columnIndex];
	 }
	 
	@Override
	public int getRowCount() {
		return filesList.size();
	}
	
	 public boolean isCellEditable(int rowIndex, int columnIndex) {
         return columnIndex <= 0;
     }
	 
	 @Override
	 public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	     if(aValue != null){
	         MyFile file = filesList.get(rowIndex);
	  
	         if(columnIndex == 0){
	             file.setFileName((String)aValue);
	         }
	     }
	 }
	 
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return filesList.get(rowIndex).getFileName();
            case 1:
                return filesList.get(rowIndex).getLastModificationDate();
            case 2:
                return (filesList.get(rowIndex).isDirectory() ? "Dossier de fichiers" : "Fichier");
            case 3:
                return filesList.get(rowIndex).getFileSize();
            default:
                throw new IllegalStateException("Ne devrait jamais arriver");
        }

    }

	/**
	 * @return the filesList
	 */
	public List<MyFile> getFilesList() {
		return filesList;
	}

	/**
	 * @param filesList the filesList to set
	 */
	public void setFilesList(List<MyFile> filesList) {
		this.filesList = filesList;
	}

}
