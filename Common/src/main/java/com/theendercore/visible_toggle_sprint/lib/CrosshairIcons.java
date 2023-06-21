package com.theendercore.visible_toggle_sprint.lib;

import dev.isxander.yacl3.api.NameableEnum;
import net.minecraft.network.chat.Component;

import static com.theendercore.visible_toggle_sprint.Constants.MODID;

public enum CrosshairIcons implements NameableEnum {
    DEFAULT(0),
    MINIMAL_ONE(4),
    MINIMAL_TWO(8),
    MINIMAL_THREE(12);
    public final int x;

    CrosshairIcons(int x) {
        this.x = x;
    }

    @Override
    public Component getDisplayName() {return Component.translatable(MODID + ".icons." + name().toLowerCase());}
}