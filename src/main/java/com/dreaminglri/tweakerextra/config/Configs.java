package com.dreaminglri.tweakerextra.config;

import com.dreaminglri.tweakerextra.TweakerExtra;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;


public class Configs implements IConfigHandler {
    public static final String CONFIG_FILE_NAME = TweakerExtra.MOD_ID + ".json";

    public static class Generic{

        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(

        );

    }

    @Override
    public void load() {
        loadFromFile();
    }

    @Override
    public void save() {
        saveToFile();
    }

    public void loadFromFile(){
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);

        if (configFile.isFile() && configFile.exists() && configFile.canRead()) {
            JsonElement jsonElement = JsonUtils.parseJsonFile(configFile);

            if (jsonElement != null && jsonElement.isJsonObject()){
                JsonObject root = jsonElement.getAsJsonObject();

            }
        }
    }

    public void saveToFile(){
        File configfile = FileUtils.getConfigDirectory();
        if ((configfile.exists() && configfile.isDirectory()) || configfile.mkdirs()){
            JsonObject root = new JsonObject();

            JsonUtils.writeJsonToFile(root, new File(configfile, CONFIG_FILE_NAME));
        }
    }
}
