package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    static {
        try (InputStream input =
                     ConfigReader.class.getClassLoader()
                             .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in src/test/resources");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }
    public static String getBrowser() {
        return properties.getProperty("browser");
    }
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("timeout"));
    }
}