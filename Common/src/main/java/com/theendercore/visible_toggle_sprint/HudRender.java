package com.theendercore.visible_toggle_sprint;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

import static com.theendercore.visible_toggle_sprint.Constants.MODID;
import static com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig.sneak;
import static com.theendercore.visible_toggle_sprint.config.VisibleToggleSprintConfig.sprint;
import static net.minecraft.client.gui.GuiComponent.blit;

public class HudRender {
    static final ResourceLocation MOD_ICONS = new ResourceLocation(MODID, "textures/gui/icons.png");

    static void renderHud(PoseStack poseStack) {
        Minecraft client = Minecraft.getInstance();
        Options options = client.options;
        int scaledWidth = client.getWindow().getGuiScaledWidth();
        int scaledHeight = client.getWindow().getGuiScaledHeight();

        RenderSystem.setShaderTexture(0, MOD_ICONS);


        assert client.gameMode != null;
        if (!options.renderDebug && !options.hideGui && options.getCameraType().isFirstPerson() && client.gameMode.getPlayerMode() != GameType.SPECTATOR) {
            if (options.keySprint.isDown()) {
                if (sprint.crosshair.enable) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    blit(poseStack, (scaledWidth) / 2 + sprint.crosshair.location.x, (scaledHeight) / 2 + sprint.crosshair.location.y, sprint.crosshair.icon.x, 0, 4, 4);
                    RenderSystem.defaultBlendFunc();
                }
                if (sprint.hotbar.enable) blit(poseStack, (scaledWidth / 2) + sprint.hotbar.location.x, (scaledHeight) - sprint.hotbar.location.y, 0, 16, 16, 16);
                if (sprint.text.enable) client.font.drawShadow(poseStack, Component.translatable("hud.visible_toggle_sprint.sprint"), sprint.text.location.x, sprint.text.location.y, sprint.text.color);
            }

            if (options.keyShift.isDown()) {
                if (sneak.crosshair.enable) {
                    RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                    blit(poseStack, (scaledWidth) / 2 + sneak.crosshair.location.x, (scaledHeight) / 2 + sneak.crosshair.location.y, sneak.crosshair.icon.x, 4, 4, 4);
                    RenderSystem.defaultBlendFunc();
                }
                if (sneak.hotbar.enable) blit(poseStack,(scaledWidth / 2) + sneak.hotbar.location.x, (scaledHeight) - sneak.hotbar.location.y, 16, 16, 16, 16);
                if (sneak.text.enable) client.font.drawShadow(poseStack, Component.translatable("hud.visible_toggle_sprint.sneak"), sneak.text.location.x, sneak.text.location.y, sneak.text.color);
            }
        }

    }
}