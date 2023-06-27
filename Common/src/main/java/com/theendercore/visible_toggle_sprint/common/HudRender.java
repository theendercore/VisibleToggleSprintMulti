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

        int scaledWidth = guiGraphics.guiWidth();
        int scaledHeight = guiGraphics.guiHeight();

        VisibleToggleSprintConfig config = getConfig();

        assert client.gameMode != null;
        if (!options.renderDebug && !options.hideGui && options.getCameraType().isFirstPerson() && client.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            if (options.keySprint.isDown()) {
                if (config.sprintCross) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    guiGraphics.blit(MOD_ICONS, (scaledWidth) / 2 + config.sprintCrossLocationX, (scaledHeight) / 2 + config.sprintCrossLocationY, config.sprintCrossIcon.x, 0, 4, 4);
                    RenderSystem.defaultBlendFunc();
                }
                if (config.sprintBar)
                    guiGraphics.blit(MOD_ICONS, (scaledWidth / 2) + config.sprintBarLocationX, (scaledHeight) - config.sprintBarLocationY, 0, 16, 16, 16);
            }

            if (options.keyShift.isDown()) {
                if (config.sneakCross) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    guiGraphics.blit(MOD_ICONS, (scaledWidth) / 2 + config.sneakCrossLocationX, (scaledHeight) / 2 + config.sneakCrossLocationY, config.sneakCrossIcon.x, 4, 4, 4);
                    RenderSystem.defaultBlendFunc();
                }
                if (config.sneakBar)
                    guiGraphics.blit(MOD_ICONS, (scaledWidth / 2) + config.sneakBarLocationX, (scaledHeight) - config.sneakBarLocationY, 16, 16, 16, 16);
                if (config.sneakText)
                    guiGraphics.drawString(client.font, Component.translatable("hud.visible_toggle_sprint.sneak"), config.sneakTextLocationX, config.sneakTextLocationY, config.sneakTextColor.getRGB(), true);
            }

            if (options.keySprint.isDown() && config.sprintText)
                guiGraphics.drawString(client.font, Component.translatable("hud.visible_toggle_sprint.sprint"), config.sprintTextLocationX, config.sprintTextLocationY, config.sprintTextColor.getRGB(), true);
        }

    }
}