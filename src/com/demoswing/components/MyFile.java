package com.demoswing.components;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class MyFile implements TreeNode {
	private String fileName;
	private String lastModificationDate;
	private long fileSize;
	private boolean isDirectory;
    private List<MyFile> files;

    private MyFile parent;

	public MyFile(String fileName, String lastModificationDate, long fileSize,
			boolean isDirectory) {

        this.fileName = fileName;
		this.lastModificationDate = lastModificationDate;
		this.fileSize = fileSize;
		this.isDirectory = isDirectory;
        this.files = new ArrayList<>();
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


    @Override
    public TreeNode getChildAt(int childIndex) {
        return files.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return files.size();
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        if (!(node instanceof MyFile)) {
            throw new IllegalArgumentException("Unsupported type for the tree node " + node);
        }
        return this.files.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return this.files.isEmpty();
    }

    @Override
    public Enumeration children() {
        final Iterator<MyFile> it = this.files.iterator();
        return new Enumeration() {
            @Override
            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @Override
            public Object nextElement() {
                return it.next();
            }
        };
    }

    public List<MyFile> getFiles() {
        return files;
    }

    public void setFiles(List<MyFile> files) {
        this.files = files;
    }

    public void setParent(MyFile parent) {
        this.parent = parent;
    }
}
