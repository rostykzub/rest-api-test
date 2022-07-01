package util;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

@Log4j2
public class Config {

    private static final String CONFIG_PATH = "./src/test/resources/config.properties";

    static Configurations configs = new Configurations();

    public static String getProperty(String key)  {
        return accessReadProperties().getProperty(key).toString();
    }

    @SneakyThrows
    public static void addProperty(String key, String value){
        FileBasedConfigurationBuilder<PropertiesConfiguration> builder = new Configurations()
                .propertiesBuilder(CONFIG_PATH);
        PropertiesConfiguration config = builder.getConfiguration();
        // Clear out current value. Add in a new value
        config.clearProperty(key);
        config.addProperty(key, value);
        // Save updates to file
        builder.save();
        log.info("Setting new property: "+key);
    }

    private static Configuration accessReadProperties(){
        Configuration config = null;
        try {
            config = configs.properties(new File(CONFIG_PATH));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        assert config != null;
        return config;
    }
}