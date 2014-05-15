package com.demoswing.components;

public class MyFile {
	private String fileName;
	private String lastModificationDate;
	private long fileSize;
	private boolean isDirectory; 

	public MyFile(String fileName, String lastModificationDate, long fileSize,
			boolean isDirectory) {

        this.fileName = fileName;
		this.lastModificationDate = lastModificationDate;
		this.fileSize = fileSize;
		this.isDirectory = isDirectory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return the lastModificationDate
	 */
	public String getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate the lastModificationDate to set
	 */
	public void setLastModificationDate(String lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return the isDirectory
	 */
	public boolean isDirectory() {
		return isDirectory;
	}

	/**
	 * @param isDirectory the isDirectory to set
	 */
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	
	
	
}
