package ru.diasoft.micro.basis.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

public class LoggerUtils {

    private static final Logger logger = Logger.getLogger(LoggerUtils.class);

    public static Boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public static void debug(Object clazz, Exception ex) {
        debug(clazz.getClass().getName(), stackTraceToString(ex));
    }

    public static void debug(Object clazz, String text) {
        debug(clazz.getClass().getName(), text);
    }

    private static void debug(String className, String text) {
        if(isDebugEnabled()){
            String message = className + ": " + text;
            logger.debug(message);
        }
    }

    public static void error(Object clazz, String text) {
        error(clazz.getClass().getName(), text);
    }

    public static void error(Object clazz, Exception exception) {
        error(clazz.getClass().getName(), exception.getMessage(), exception);
    }

    public static void error(Object clazz, Throwable throwable) {
        error(clazz.getClass().getName(), throwable.getMessage(), throwable);
    }

    public static void error(Object clazz, String text, Exception exception) {
        error(clazz.getClass().getName(), text, exception);
    }

    public static void error(String className, String text) {
        String message = className + ": " + text;
        logger.error(message);
    }

    public static void error(String className, String text, Exception exception) {
        String message = className + ": " + text;
        logger.error(message, exception);
    }

    public static void error(String className, String text, Throwable throwable) {
        String message = className + ": " + text;
        logger.error(message, throwable);
    }

    public static String stackTraceToString(Exception ex) {
        String text;
        StringWriter sw = null;
        try {
            sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            text = sw.toString();
        }
        finally {
            if (sw != null) {
                try {
                    sw.close();
                }
                catch (IOException e) {
                    debug(LoggerUtils.class.getSimpleName(), e.getMessage());
                }
            }
        }

        return text;
    }
}
