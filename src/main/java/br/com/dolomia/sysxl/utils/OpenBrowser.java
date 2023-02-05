package br.com.dolomia.sysxl.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;


public class OpenBrowser {
    public static void init() {
        try {
            Desktop.getDesktop().browse(new URI("https://linktr.ee/matheusdpo_"));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}