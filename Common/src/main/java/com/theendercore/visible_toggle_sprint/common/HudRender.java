package com.theendercore.visible_toggle_sprint.common;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

import static com.theendercore.visible_toggle_sprint.CommonClass.getConfig;
import static com.theendercore.visible_toggle_sprint.Constants.MODID;
import static net.minecraft.client.gui.GuiComponent.blit;

public class HudRender {
    static final ResourceLocation MOD_ICONS = new ResourceLocation(MODID, "textures/gui/icons.png");

    public static void renderHud(PoseStack poseStack) {
        Minecraft client = Minecraft.getInstance();
        Options options = client.options;
        int scaledWidth = client.getWindow().getGuiScaledWidth();
        int scaledHeight = client.getWindow().getGuiScaledHeight();

        VisibleToggleSprintConfig config = getConfig();

        RenderSystem.setShaderTexture(0, MOD_ICONS);

        assert client.gameMode != null;
        if (!options.renderDebug && !options.hideGui && options.getCameraType().isFirstPerson() && client.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            if (options.keySprint.isDown()) {
                if (config.sprintCross) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    blit(poseStack, (scaledWidth) / 2 + config.sprintCrossLocationX, (scaledHeight) / 2 + config.sprintCrossLocationY, config.sprintCrossIcon.x, 0, 4, 4);
                    RenderSystem.defaultBlendFunc();
                }
                if (config.sprintBar)
                    blit(poseStack, (scaledWidth / 2) + config.sprintBarLocationX, (scaledHeight) - config.sprintBarLocationY, 0, 16, 16, 16);
            }

            if (options.keyShift.isDown()) {
                if (config.sneakCross) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    blit(poseStack, (scaledWidth) / 2 + config.sneakCrossLocationX, (scaledHeight) / 2 + config.sneakCrossLocationY, config.sneakCrossIcon.x, 4, 4, 4);
                    RenderSystem.defaultBlendFunc();
                }
                if (config.sneakBar)
                    blit(poseStack, (scaledWidth / 2) + config.sneakBarLocationX, (scaledHeight) - config.sneakBarLocationY, 16, 16, 16, 16);
                if (config.sneakText)
                    client.font.drawShadow(poseStack, Component.translatable("hud.visible_toggle_sprint.sneak"), config.sneakTextLocationX, config.sneakTextLocationY, config.sneakTextColor.getRGB());
            }

            if (options.keySprint.isDown() && config.sprintText)
                client.font.drawShadow(poseStack, Component.translatable("hud.visible_toggle_sprint.sprint"), config.sprintTextLocationX, config.sprintTextLocationY, config.sprintTextColor.getRGB());
        }

    }
}