package com.theendercore.visible_toggle_sprint.platform.services;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.nio.file.Path;
import java.util.function.Consumer;

public interface IPlatformHelper {
    String getPlatformName();
    boolean isModLoaded(String modId);
    boolean isDevelopmentEnvironment();
    void renderHud(Consumer<GuiGraphics> renderer);
    void registerKeyBinding(KeyMapping keyBinding, Consumer<Minecraft> action);
}