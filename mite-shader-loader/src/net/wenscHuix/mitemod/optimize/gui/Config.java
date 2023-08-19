package net.wenscHuix.mitemod.optimize.gui;

import net.minecraft.Minecraft;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.Callable;

public class Config {
    static String optionsFileName = "optimize.txt";
    public static File configFile = new File(Minecraft.w().x, optionsFileName);
    public static Properties optimizeConfig = new Properties();;


    public static boolean blockDestroyEffects;


    public static void loadConfig() {
        try {
            if (configFile.exists()) {
                FileReader reader = new FileReader(configFile);
                optimizeConfig.load(reader);
                reader.close();
            } else if (!configFile.exists()) {
                storeConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





        blockDestroyEffects = Boolean.parseBoolean(optimizeConfig.getProperty("blockDestroyEffects", "true"));
    }


    public static void storeConfig() {
        optimizeConfig.setProperty("blockDestroyEffects", Boolean.toString(blockDestroyEffects));


        try{
            FileWriter writer = new FileWriter(configFile);
            optimizeConfig.store(writer, null);
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
