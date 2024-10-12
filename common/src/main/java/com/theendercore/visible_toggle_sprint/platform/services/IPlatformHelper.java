package com.theendercore.visible_toggle_sprint.platform.services;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.util.function.Consumer;

public interface IPlatformHelper {
    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    void renderHud(Consumer<GuiGraphics> renderer);

    void registerKeyBinding(KeyMapping keyBinding, Consumer<Minecraft> action);
}