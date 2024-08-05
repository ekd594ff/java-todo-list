package org.homework.step4.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfiguration {

    private static String appMode;
    private static Properties properties;
    private static DBConfiguration dbConfiguration;

    DBConfiguration() throws FileNotFoundException, IOException {
        properties = new Properties();
        InputStream input;
        try {
            input = new FileInputStream("src/main/java/org/homework/step4/application.properties");
            properties.load(input);
            appMode = (String) properties.get("app.mode");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConfiguration getInstance() throws IOException {
        if (dbConfiguration == null) {
            dbConfiguration = new DBConfiguration();
        }
        return dbConfiguration;
    }

    public String getAppMode() {
        return appMode;
    }

    public Properties getProperties() {
        return properties;
    }
}
