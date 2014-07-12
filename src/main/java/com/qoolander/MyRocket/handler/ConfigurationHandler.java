package com.qoolander.MyRocket.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigurationHandler {

    public static Configuration configuration;

    public static void init(File configFile) {
        configuration = new Configuration(configFile);
        boolean configValue = false;

        try {
            configuration.load();

            //Read in properties from config file
            configValue = configuration.get("forgeCraft", "configTestValue", true, "This is an example config value").getBoolean(true);
        } catch (Exception e) {
            //log exception
        } finally {
            if (configuration.hasChanged()) {
                configuration.save();
            }
        }

        System.out.println("configuration test: " + configValue);
    }


}
