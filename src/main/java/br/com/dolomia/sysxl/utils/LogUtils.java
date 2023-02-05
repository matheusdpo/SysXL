package br.com.dolomia.sysxl.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    private static final Logger log = LogManager.getLogger("SYSXL");

    public static void registerLog(String mensagem) {
        log.trace(mensagem);
    }

    public static void registerLog(String mensagem, Throwable throwable) {
        log.trace(mensagem, throwable);
    }

    public static void registerError(String mensagem) {
        log.error(mensagem);
    }
}
