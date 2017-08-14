package com.forecast.testplugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheltonz on 4/5/2017.
 */
public class Util {

    public static List<String> readFileAsDocument(final File file) throws IOException {
        final Reader reader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(reader);
        final List<String> contents = new ArrayList<String>();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            contents.add(line);
        }
        reader.close();
        bufferedReader.close();
        return contents;
    }
}
