package com.issuerpro.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton configuration manager that loads properties from config.properties
 * and supports system property / environment variable overrides.
 */
public class ConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);
    private static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                properties.load(is);
                logger.info("Loaded configuration properties successfully.");
            } else {
                logger.warn("config.properties not found in classpath.");
            }
        } catch (IOException e) {
            logger.error("Failed to load config.properties", e);
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    public static String get(String key) {
        // System property takes precedence over config file
        String systemVal = System.getProperty(key);
        if (systemVal != null && !systemVal.isBlank()) {
            return systemVal.trim();
        }
        String val = properties.getProperty(key);
        return val != null ? val.trim() : null;
    }

    public static String get(String key, String defaultValue) {
        String val = get(key);
        return val != null ? val : defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        String val = get(key);
        if (val == null) return defaultValue;
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String val = get(key);
        if (val == null) return defaultValue;
        return Boolean.parseBoolean(val);
    }

    public static String getAppUrl() {
        return get("app.url", "https://issuer-platform.emergent.host");
    }

    public static String getLoginUrl() {
        return getAppUrl() + get("app.login.path", "/login");
    }

    public static String getApiBaseUrl() {
        return get("api.base.url", "https://issuer-platform.emergent.host/api");
    }

    public static String getBrowser() {
        return get("browser", "chrome");
    }

    public static boolean isHeadless() {
        return getBoolean("browser.headless", true);
    }

    public static int getPageLoadTimeout() {
        return getInt("page.load.timeout", 30);
    }

    public static int getExplicitWaitTimeout() {
        return getInt("explicit.wait.timeout", 15);
    }
}
