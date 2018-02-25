package support;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesSupport {
    String filename = "system.properties";

    public String getPropertyValue(String propertyKey) {
        String value = null;

        try {
            File file = new File(filename);
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            value = properties.getProperty(propertyKey);
            fileInput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
