package org.unibl.etf.io;

import org.unibl.etf.Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class GameReader {

    private String fileName;

    public GameReader(String fileName)
    {
        this.fileName = fileName;
    }


    public String readGame() throws IOException
    {
            FileReader file = new FileReader(Utilities.Constants.gamesPlayedFilePath + File.separatorChar + fileName);
            String read = "";
            int i;
            while((i = file.read())!=-1)
                read+=(char)i;

            return read;
    }
}
