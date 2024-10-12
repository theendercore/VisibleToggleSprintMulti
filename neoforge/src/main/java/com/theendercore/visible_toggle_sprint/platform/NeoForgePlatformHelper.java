package com.theendercore.visible_toggle_sprint.platform;

import com.theendercore.visible_toggle_sprint.platform.services.IPlatformHelper;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.RenderGuiEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.nio.file.Path;
import java.util.function.Consumer;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public void renderHud(Consumer<GuiGraphics> renderer) {
        NeoForge.EVENT_BUS.addListener((RenderGuiEvent.Post e) -> renderer.accept(e.getGuiGraphics()));
    }
}