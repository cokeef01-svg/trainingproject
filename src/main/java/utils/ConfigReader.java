package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    // Constructor (runs when object is created)
    public ConfigReader() {
        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties"
            );
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getLoginUrl() {
        return prop.getProperty("loginUrl");
    }

    public String getDragDropUrl() {
        return prop.getProperty("dragDropUrl");
    }

    public String getNavigationUrl() {
        return prop.getProperty("ebayNavigationUrl");
    }

    public int getTimeout() {
        return Integer.parseInt(prop.getProperty("timeout"));
    }
    
    public String getRunMode() {
    	return System.getProperty("runMode", prop.getProperty("runMode"));
    }

    public String getGridUrl() {
    	return System.getProperty("gridUrl", prop.getProperty("gridUrl"));
    }
}