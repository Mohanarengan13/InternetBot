package com.krct.cssv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Csvreader {

    public static Object[][] readCSV(String filePath) {

        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            boolean skipHeader = true;

            while ((line = br.readLine()) != null) {

                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }

                String[] values = line.split(",");

                data.add(new Object[]{
                        values[0], // username
                        values[1], // password
                        values[2]  // expected message
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][]);
    }
}