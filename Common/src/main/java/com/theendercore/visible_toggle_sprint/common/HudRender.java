package com.theendercore.visible_toggle_sprint.common;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

import static com.theendercore.visible_toggle_sprint.CommonClass.getConfig;
import static com.theendercore.visible_toggle_sprint.Constants.MODID;

public class HudRender {
    static final ResourceLocation MOD_ICONS = new ResourceLocation(MODID, "textures/gui/icons.png");


    public static void renderHud(GuiGraphics guiGraphics) {
        Minecraft client = Minecraft.getInstance();
        Options options = client.options;
        VisibleToggleSprintConfig config = getConfig();

        int sWidth = guiGraphics.guiWidth();
        int sHeight = guiGraphics.guiHeight();

        boolean debug = !client.gui.getDebugOverlay().showDebugScreen();
//        client.player.displayClientMessage(Component.literal("Debug Value : " + debug), true);

        if (client.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            if (options.keySprint.isDown()) {
                if ((debug || client.player.isReducedDebugInfo()) && options.getCameraType().isFirstPerson() && config.sprintCross) {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    guiGraphics.blit(MOD_ICONS, (sWidth) / 2 + config.sprintCrossLocationX, (sHeight) / 2 + config.sprintCrossLocationY, config.sprintCrossIcon.x, 0, 4, 4);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.disableBlend();
                }
                if (config.sprintBar)
                    guiGraphics.blit(MOD_ICONS, (sWidth / 2) + config.sprintBarLocationX, (sHeight) - config.sprintBarLocationY, 0, 16, 16, 16);
                if (debug && config.sprintText)
                    guiGraphics.drawString(client.font, Component.translatable("hud.visible_toggle_sprint.sprint"), config.sprintTextLocationX, config.sprintTextLocationY, config.sprintTextColor.getRGB(), true);
            }

            if (options.keyShift.isDown()) {
                if ((debug || client.player.isReducedDebugInfo()) && options.getCameraType().isFirstPerson() && config.sneakCross) {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    guiGraphics.blit(MOD_ICONS, (sWidth) / 2 + config.sneakCrossLocationX, (sHeight) / 2 + config.sneakCrossLocationY, config.sneakCrossIcon.x, 4, 4, 4);
                    RenderSystem.defaultBlendFunc();
                    RenderSystem.disableBlend();
                }
                if (config.sneakBar)
                    guiGraphics.blit(MOD_ICONS, (sWidth / 2) + config.sneakBarLocationX, (sHeight) - config.sneakBarLocationY, 16, 16, 16, 16);
                if (debug && config.sneakText)
                    guiGraphics.drawString(client.font, Component.translatable("hud.visible_toggle_sprint.sneak"), config.sneakTextLocationX, config.sneakTextLocationY, config.sneakTextColor.getRGB(), true);
            }

        }
    }
}