package com.theendercore.visible_toggle_sprint.platform;

import com.theendercore.visible_toggle_sprint.platform.services.IPlatformHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;

import java.util.function.Consumer;

import static com.theendercore.visible_toggle_sprint.VTSConst.LAYER_ID;

public class FabricPlatformHelper implements IPlatformHelper {
    @Override
    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public void renderHud(Consumer<GuiGraphics> renderer) {
        HudElementRegistry.attachElementAfter(VanillaHudElements.CROSSHAIR, LAYER_ID, (c, d) -> renderer.accept(c));
    }

    @Override
    public void registerKeyBinding(KeyMapping keyBinding, Consumer<Minecraft> action) {
        KeyBindingHelper.registerKeyBinding(keyBinding);
        ClientTickEvents.END_CLIENT_TICK.register(action::accept);
    }
}
