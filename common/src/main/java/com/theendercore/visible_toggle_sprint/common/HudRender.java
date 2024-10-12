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

import static com.theendercore.visible_toggle_sprint.CommonClass.CONFIG;
import static com.theendercore.visible_toggle_sprint.Constants.MODID;

public class HudRender {
    static final ResourceLocation MOD_ICONS = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/icons.png");


    public static void renderHud(GuiGraphics gui) {
        Minecraft client = Minecraft.getInstance();
        Options options = client.options;

        int sWidth = gui.guiWidth() / 2;
        int sHeight = gui.guiHeight();

        boolean debug = !client.gui.getDebugOverlay().showDebugScreen();
        if (client.player == null) return;

        if (Services.PLATFORM.isDevelopmentEnvironment())
            client.player.displayClientMessage(Component.literal("Sprint : " + client.player.isSprinting() + ", Sneak: " + client.player.isCrouching()), true);

        if (client.gameMode.getPlayerMode() != GameType.SPECTATOR) {

            if (shouldRender(CONFIG.sprint.indicator, options.keySprint.isDown(), client.player.isSprinting())) {
                if ((debug || client.player.isReducedDebugInfo()) && options.getCameraType().isFirstPerson() && CONFIG.sprint.crosshair.enable) {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    gui.blit(MOD_ICONS, sWidth + CONFIG.sprint.crosshair.x, (sHeight / 2) + CONFIG.sprint.crosshair.y, CONFIG.sprint.crosshair.icon.x, 0, 4, 4);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.disableBlend();
                }
                if (CONFIG.sprint.hotbar.enable)
                    gui.blit(MOD_ICONS, sWidth + CONFIG.sprint.hotbar.x, (sHeight - CONFIG.sprint.hotbar.y), 0, 16, 16, 16);
                if (debug && CONFIG.sprint.text.enable)
                    gui.drawString(client.font, Component.translatable("hud.visible_toggle_sprint.sprint"), CONFIG.sprint.text.x, CONFIG.sprint.text.y, CONFIG.sprint.text.color.toInt(), true);
            }

            if (shouldRender(CONFIG.sneak.indicator, options.keyShift.isDown(), client.player.isCrouching())) {
                if ((debug || client.player.isReducedDebugInfo()) && options.getCameraType().isFirstPerson() && CONFIG.sneak.crosshair.enable) {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    gui.blit(MOD_ICONS, sWidth + CONFIG.sneak.crosshair.x, (sHeight / 2) + CONFIG.sneak.crosshair.y, CONFIG.sneak.crosshair.icon.x, 4, 4, 4);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.disableBlend();
                }
                if (CONFIG.sneak.hotbar.enable)
                    gui.blit(MOD_ICONS, sWidth + CONFIG.sneak.hotbar.x, (sHeight - CONFIG.sneak.hotbar.y), 16, 16, 16, 16);
                if (debug && CONFIG.sneak.text.enable)
                    gui.drawString(client.font, Component.translatable("hud.visible_toggle_sprint.sneak"), CONFIG.sneak.text.x, CONFIG.sneak.text.y, CONFIG.sneak.text.color.toInt(), true);
            }

        }
    }

    public static boolean shouldRender(VisibleToggleSprintConfig.PlayerState.IndicatorType type, boolean keyDown, boolean isStateActive) {
        return switch (type) {
            case KEY_PRESSED_ONLY -> keyDown;
            case STATE_ONLY -> isStateActive;
            case BOTH -> keyDown || isStateActive;
        };
    }
}