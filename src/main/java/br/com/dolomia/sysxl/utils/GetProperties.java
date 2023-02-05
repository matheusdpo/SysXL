package br.com.dolomia.sysxl.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class GetProperties {
    private static final String path = getPath();

    public static String getProperty(String property) {
        Properties properties = new Properties();

        try {
            properties.load(Files.newInputStream(Path.of(path + "\\application.properties")));
            return properties.getProperty(property);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getPath() {
        return new File(GetProperties.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getParentFile().getParentFile().getAbsolutePath();
    }
}