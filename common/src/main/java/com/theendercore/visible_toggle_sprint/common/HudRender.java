package com.theendercore.visible_toggle_sprint.common;

import com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

import static com.theendercore.visible_toggle_sprint.VTSCommon.CONFIG;
import static com.theendercore.visible_toggle_sprint.VTSConst.id;
import static com.theendercore.visible_toggle_sprint.platform.Services.PLATFORM;

public class HudRender {
    public static void renderHud(GuiGraphics gui) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;
        Options options = client.options;
        if (options.hideGui) return;

        int width = gui.guiWidth();
        int height = gui.guiHeight();

        boolean debug = !client.gui.getDebugOverlay().showDebugScreen();

        if (PLATFORM.isDevelopmentEnvironment())
            client.player.displayClientMessage(Component.literal("Sprint : " + client.player.isSprinting() + ", Sneak: " + client.player.isCrouching()), true);

        if (client.gameMode != null && client.gameMode.getPlayerMode() == GameType.SPECTATOR) return;

        if (shouldRender(CONFIG.sprint, options.keySprint.isDown(), client.player.isSprinting())) {
            renderIndicator(CONFIG.sprint, debug, client, options, gui, width, height, "sprint");
        }
        if (shouldRender(CONFIG.sneak, options.keyShift.isDown(), client.player.isCrouching())) {
            renderIndicator(CONFIG.sneak, debug, client, options, gui, width, height, "sneak");
        }
    }

    public static boolean shouldRender(VisibleToggleSprintConfig.PlayerState state, boolean keyDown, boolean isStateActive) {
        return switch (state.indicator) {
            case KEY_ONLY -> keyDown;
            case STATE_ONLY -> isStateActive;
            case COMBINED -> keyDown || isStateActive;
        };
    }

    public static void renderIndicator(VisibleToggleSprintConfig.PlayerState state, boolean debug, Minecraft client, Options options, GuiGraphics gui, int width, int height, String type) {
        assert client.player != null;
        if ((debug || client.player.isReducedDebugInfo()) && options.getCameraType().isFirstPerson() && state.crosshairEnable) {
            gui.pose().pushMatrix();
            // set the position
            gui.pose().translate((float) ((width - 4 + state.crosshairX) / 2), (float) ((height - 4 + state.crosshairY) / 2));
            if (state.rotation.get() != 0) { // get the right rotation
                gui.pose().rotateAbout((float) (Math.PI / 180.0) * state.rotation.get(), 2, 2);
            }
            // render icon
            gui.blitSprite(RenderPipelines.CROSSHAIR, hud(state.crosshairIcon.get().toLowerCase()),
                    4, 4, 0, 0,
                    0, 0, 4, 4);
            gui.pose().popMatrix();
        }
        if (state.hotbarEnable) {
            gui.blit(RenderPipelines.GUI, id("hud/" + type),
                    16, 16, 0, 0,
                    (width / 2) + state.hotbarX, (height - state.hotbarY), 16, 16);
        }
        if (debug && state.textEnable)
            gui.drawString(client.font, Component.translatable("hud.visible_toggle_sprint." + type), state.textX, state.textY, state.textColor.toInt(), true);
    }

    public static ResourceLocation hud(String name) {
        return id("hud/crosshair/" + name);
    }
}