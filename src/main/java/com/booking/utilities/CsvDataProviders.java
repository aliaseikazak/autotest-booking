package com.booking.utilities;

import com.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

public class CsvDataProviders {

    @DataProvider(name = "csvReader")
    public static Iterator<Object[]> csvReader(Method method) {
        List<Object[]> list = new ArrayList<Object[]>();
        String pathname = "src" + File.separator + "test" + File.separator + "resources" + File.separator
                + "dataprovider" + File.separator + method.getDeclaringClass().getSimpleName() + File.separator
                + method.getName() + ".csv";
        File file = new File(pathname);
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] keys = reader.readNext();
            if (keys != null) {
                String[] dataParts;
                while ((dataParts = reader.readNext()) != null) {
                    Map<String, String> testData = new HashMap<String, String>();
                    for (int i = 0; i < keys.length; i++) {
                        testData.put(keys[i], dataParts[i]);
                    }
                    list.add(new Object[]{testData});
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("File %s was not found.\n%s",
                    pathname, Arrays.toString(e.getStackTrace())));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Could not read %s file.\n%s",
                    pathname, Arrays.toString(e.getStackTrace())));
        }
        return list.iterator();
    }
}
