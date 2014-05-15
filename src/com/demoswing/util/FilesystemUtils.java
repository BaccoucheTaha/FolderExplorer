package com.demoswing.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.demoswing.components.MyFile;

/**
 * Created by Eugene.
 * <p/>
 * Date: 14/05/2014
 * Time: 21:43
 * <p/>
 * As part of project FolderExplorer
 *
 * Récupère la liste des fichiers/dossiers contenus dans le repertoire en entrée
 *
 * @return
 */
public class FilesystemUtils {
    public static List<MyFile> getFolderContent(File file) {
        List<MyFile> filesList = new ArrayList<>();

        try (DirectoryStream<Path> listPathFiles = Files.newDirectoryStream(Paths.get(StringUtils.trimToEmpty(file.getPath())))) {

			for (Path currentPath : listPathFiles) {
				FileTime lastModifiedDate = Files
						.getLastModifiedTime(currentPath);

				MyFile myCurrentFile = new MyFile(String.valueOf(currentPath
						.getFileName()),
						formateFileTimeToStringDate(lastModifiedDate),
						Files.size(currentPath), Files.isDirectory(currentPath));

				filesList.add(myCurrentFile);
			}
        }catch(IOException e) {
            e.printStackTrace();
        }
        return filesList;
    }
        
    
    public static String formateFileTimeToStringDate(FileTime fileTime){

		Date today = new Date(fileTime.toMillis());

		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		return sdf2.format(today); 
    }
    
}
