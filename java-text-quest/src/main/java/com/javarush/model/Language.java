package com.javarush.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Language {

        UK, EN;

    private static final Logger LOGGER = LogManager.getLogger(Language.class);

        public static boolean isSupported(String langParam) {
            if (langParam == null) return false;
            try {
                Language.valueOf(langParam.toUpperCase());
                return true;
            } catch (IllegalArgumentException e) {
                LOGGER.error("Attempted to set unsupported language: '{}'", langParam);
                return false;
            }
        }
}
