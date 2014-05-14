package com.demoswing.util;

import com.demoswing.components.MyFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // TODO pourais-tu reecrire la boucle ci-dessous en utilisant NIO ?
        for (File currentFile : file.listFiles()) {

            Date lastModifiedDate = new Date(currentFile.lastModified());

            String lastModification = ft.format(lastModifiedDate);

            MyFile myCurrentFile = new MyFile(currentFile.getName(),
                    lastModification, currentFile.length(),
                    currentFile.isDirectory());

            filesList.add(myCurrentFile);
        }

        return filesList;
    }
}
