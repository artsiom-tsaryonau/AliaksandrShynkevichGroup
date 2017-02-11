package com.epam.lab.mentoring.homework.support;

import com.epam.lab.mentoring.homework.support.properties.LoadStatus;
import com.epam.lab.mentoring.homework.support.properties.SystemPropertyLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public enum AppConfig {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    private Properties properties;

    {
        LoadStatus consoleLoad = new SystemPropertyLoader().load();

        if (!consoleLoad.isSuccess()) {
            throw new IllegalArgumentException("Failed to initialize application! No system property [taskFile] found!");
        }

        properties = consoleLoad.getResult();
    }

    public String getProperty(String key) {
        String toReturn = properties.getProperty(key);
        if (StringUtils.isBlank(toReturn)) {
            LOGGER.error("Property [{}] not found.", key);
            throw new IllegalArgumentException("No property [".concat(key).concat("] specified!"));
        }

        return toReturn;
    }
}