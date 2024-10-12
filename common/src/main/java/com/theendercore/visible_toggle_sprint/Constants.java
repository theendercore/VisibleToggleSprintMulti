package com.theendercore.visible_toggle_sprint;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Constants {
    String MODID = "visible_toggle_sprint";
    String MOD_NAME = "VisibleToggleSprint";
    Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}