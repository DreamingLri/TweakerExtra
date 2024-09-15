package com.dreaminglri.tweakerextra.gui;

import com.dreaminglri.tweakerextra.TweakerExtra;
import com.dreaminglri.tweakerextra.config.Configs;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import fi.dy.masa.malilib.gui.button.ButtonBase;
import fi.dy.masa.malilib.gui.button.ButtonGeneric;
import fi.dy.masa.malilib.gui.button.IButtonActionListener;
import fi.dy.masa.malilib.util.StringUtils;

import java.util.Collections;
import java.util.List;


public class GuiConfig extends GuiConfigsBase {
    private static ConfigGuiTab tab = ConfigGuiTab.TWEAKS;

    public GuiConfig() {
        super(10, 50, TweakerExtra.MOD_ID, null, "tweakeroo.gui.title.configs", String.format("%s", TweakerExtra.MOD_VERSION));
    }

    @Override
    public void initGui() {
        super.initGui();
        this.clearOptions();

        int x = 10;
        int y = 26;

        for (ConfigGuiTab tab : ConfigGuiTab.values())
        {
            x += this.createButton(x, y, -1, tab);
        }
    }

    private int createButton(int x, int y, int width, ConfigGuiTab tab) {
        ButtonGeneric button = new ButtonGeneric(x, y, width, 20, tab.getDisplayName());
        button.setEnabled(GuiConfig.tab != tab);
        this.addButton(button, new ButtonListener(tab, this));

        return button.getWidth() + 2;
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        List<? extends IConfigBase> configs;
        ConfigGuiTab tab = GuiConfig.tab;

        if (tab == ConfigGuiTab.GENERIC) {
            configs = Configs.Generic.OPTIONS;
        }
        else {
            return Collections.emptyList();
        }

        return ConfigOptionWrapper.createFor(configs);
    }

    private static class ButtonListener implements IButtonActionListener {
        private final GuiConfig parent;
        private final ConfigGuiTab tab;

        public ButtonListener(ConfigGuiTab tab, GuiConfig parent)
        {
            this.tab = tab;
            this.parent = parent;
        }

        @Override
        public void actionPerformedWithButton(ButtonBase button, int mouseButton)
        {
            GuiConfig.tab = this.tab;
            this.parent.reCreateListWidget(); // apply the new config width
            this.parent.getListWidget().resetScrollbarPosition();
            this.parent.initGui();
        }
    }

    public enum ConfigGuiTab {
        GENERIC         ("tweakeroo.gui.button.config_gui.generic"),
        FIXES           ("tweakeroo.gui.button.config_gui.fixes"),
        LISTS           ("tweakeroo.gui.button.config_gui.lists"),
        TWEAKS          ("tweakeroo.gui.button.config_gui.tweaks"),
        GENERIC_HOTKEYS ("tweakeroo.gui.button.config_gui.generic_hotkeys"),
        DISABLES        ("tweakeroo.gui.button.config_gui.disables");

        private final String translationKey;

        ConfigGuiTab(String translationKey)
        {
            this.translationKey = translationKey;
        }

        public String getDisplayName()
        {
            return StringUtils.translate(this.translationKey);
        }
    }
}
