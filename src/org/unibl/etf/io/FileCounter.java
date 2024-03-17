package org.unibl.etf.io;

import org.unibl.etf.Utilities;

import java.io.File;
import java.util.ArrayList;

public final class FileCounter {

    public static ArrayList<String> countFilesInDirectory()
    {
        ArrayList<String> files = new ArrayList<>();
        File f = new File(Utilities.Constants.gamesPlayedFilePath);
        if (f.isDirectory())
        {
            File[] list = f.listFiles();
            assert list != null;
            for (File file : list) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    if (fileName.contains("GAME") || fileName.endsWith(".txt")) {
                        files.add(fileName);
                    }
                }
            }
        }
        return files;
    }
}
