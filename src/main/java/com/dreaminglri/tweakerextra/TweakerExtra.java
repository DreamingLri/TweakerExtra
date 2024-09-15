package com.dreaminglri.tweakerextra;

import com.dreaminglri.tweakerextra.config.Configs;
import fi.dy.masa.malilib.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TweakerExtra implements ClientModInitializer {
    public static final Logger logger = LogManager.getLogger();

    public static final String MOD_NAME = "TweakerExtra";
    public static final String MOD_ID = "tweakerextra";
    public static String MOD_VERSION = "1.0.0";

    @Override
    public void onInitializeClient() {
        ConfigManager.getInstance().registerConfigHandler(MOD_ID, new Configs());

    }
}
