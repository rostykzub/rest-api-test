package util;

import lombok.SneakyThrows;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;

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
        config.clearProperty(key);
        config.addProperty(key, value);
        builder.save();
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