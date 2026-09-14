package org.example.config;

public final class MobileConfig {
    private MobileConfig() {
    }

    public static String appiumUrl() {
        return property("appium.url", "http://127.0.0.1:4723");
    }

    public static String appPath() {
        return property("app.path", "");
    }

    public static String deviceName() {
        return property("device.name", "Android Emulator");
    }

    public static String platformVersion() {
        return property("platform.version", "");
    }

    private static String property(String name, String defaultValue) {
        String systemValue = System.getProperty(name);
        if (systemValue != null && !systemValue.trim().isEmpty()) {
            return systemValue;
        }
        String environmentValue = System.getenv(name.toUpperCase().replace('.', '_'));
        return environmentValue == null || environmentValue.trim().isEmpty()
                ? defaultValue : environmentValue;
    }
}
