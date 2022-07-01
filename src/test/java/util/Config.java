package util;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

public class Config {

    private static final String CONFIG_PATH = "./src/test/resources/config.properties";

    static Configurations configs = new Configurations();

    public static String getProperty(String key)  {
        Configuration config = null;
        try {
            config = configs.properties(new File(CONFIG_PATH));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return config.getProperty(key).toString();
    }
}