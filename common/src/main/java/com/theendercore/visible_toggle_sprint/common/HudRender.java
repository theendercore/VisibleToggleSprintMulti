package com.theendercore.visible_toggle_sprint.common;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig;
import com.theendercore.visible_toggle_sprint.platform.Services;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

import static com.theendercore.visible_toggle_sprint.VTSCommon.CONFIG;
import static com.theendercore.visible_toggle_sprint.VTSConst.id;

public class HudRender {
    static final ResourceLocation MOD_ICONS = id("textures/gui/icons.png");

    public static void renderHud(GuiGraphics gui) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null) return;
        Options options = client.options;

        int sWidth = gui.guiWidth() / 2;
        int sHeight = gui.guiHeight();

        boolean debug = !client.gui.getDebugOverlay().showDebugScreen();

        if (Services.PLATFORM.isDevelopmentEnvironment())
            client.player.displayClientMessage(Component.literal("Sprint : " + client.player.isSprinting() + ", Sneak: " + client.player.isCrouching()), true);

        if (client.gameMode != null && client.gameMode.getPlayerMode() == GameType.SPECTATOR) return;

        if (shouldRender(CONFIG.sprint, options.keySprint.isDown(), client.player.isSprinting())) {
            renderIndicator(CONFIG.sprint, debug, client, options, gui, sWidth, sHeight, "sprint");
        }
        if (shouldRender(CONFIG.sneak, options.keyShift.isDown(), client.player.isCrouching())) {
            renderIndicator(CONFIG.sneak, debug, client, options, gui, sWidth, sHeight, "sneak");
        }
    }

    public static boolean shouldRender(VisibleToggleSprintConfig.PlayerState state, boolean keyDown, boolean isStateActive) {
        return switch (state.indicator) {
            case KEY_ONLY -> keyDown;
            case STATE_ONLY -> isStateActive;
            case COMBINED -> keyDown || isStateActive;
        };
    }

    public static void renderIndicator(VisibleToggleSprintConfig.PlayerState state, boolean debug, Minecraft client, Options options, GuiGraphics gui, int sWidth, int sHeight, String langKey) {
        assert client.player != null;
        if ((debug || client.player.isReducedDebugInfo()) && options.getCameraType().isFirstPerson() && state.crosshairEnable) {
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            gui.blit(MOD_ICONS, sWidth + state.crosshairX, (sHeight / 2) + state.crosshairY, state.crosshairIcon.get().x, 0, 4, 4);
            RenderSystem.defaultBlendFunc();
            RenderSystem.disableBlend();
        }
        if (state.hotbarEnable)
            gui.blit(MOD_ICONS, sWidth + state.hotbarX, (sHeight - state.hotbarY), 0, 16, 16, 16);
        if (debug && state.textEnable)
            gui.drawString(client.font, Component.translatable("hud.visible_toggle_sprint." + langKey), state.textX, state.textY, state.textColor.toInt(), true);
    }
}