package com.theendercore.visible_toggle_sprint;

import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface VTSConst {
    String MODID = "visible_toggle_sprint";
    String NAME = "VisibleToggleSprint";
    Logger log = LoggerFactory.getLogger(NAME);

    static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}