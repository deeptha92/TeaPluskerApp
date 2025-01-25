package com.example.TeaPlucker;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static String[] readTextFile(Context context, int resourceId) {
        List<String> lines = new ArrayList<>();

        try {
            Resources resources = context.getResources();
            InputStream inputStream = resources.openRawResource(resourceId);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Add each line to the array
                lines.add(line);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list to an array
        return lines.toArray(new String[0]);
    }

}
