package com.theendercore.visible_toggle_sprint;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;

import static com.theendercore.visible_toggle_sprint.Constants.MODID;
import static com.theendercore.visible_toggle_sprint.VisibleToggleSprintConfig.sneak;
import static com.theendercore.visible_toggle_sprint.VisibleToggleSprintConfig.sprint;
import static net.minecraft.client.gui.GuiComponent.blit;

public class HudRender {
    private final ResourceLocation MOD_ICONS = new ResourceLocation(MODID, "textures/gui/icons.png");

    void renderHud(PoseStack poseStack) {
        Minecraft client = Minecraft.getInstance();
        Options options = client.options;
        int scaledWidth = client.getWindow().getGuiScaledWidth();
        int scaledHeight = client.getWindow().getGuiScaledHeight();

        RenderSystem.setShaderTexture(0, MOD_ICONS);



        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        if (!options.renderDebug && !options.hideGui && options.getCameraType().isFirstPerson()) {

            assert client.gameMode != null;
            if (client.gameMode.getPlayerMode() != GameType.SPECTATOR) {

                if (options.keySprint.isDown() && sprint.crosshair.enable) {
                    blit(poseStack, (scaledWidth) / 2 + sprint.crosshair.location.x, (scaledHeight) / 2 + sprint.crosshair.location.y, sprint.crosshair.icon.x, 0, 4, 4);
                }

                RenderSystem.setShaderTexture(0, MOD_ICONS);
                if (options.keyShift.isDown() && sneak.crosshair.enable) {
                    blit(poseStack, (scaledWidth) / 2 + sneak.crosshair.location.x, (scaledHeight) / 2 + sneak.crosshair.location.y, sneak.crosshair.icon.x, 4, 4, 4);
                }

            }

        }
        RenderSystem.defaultBlendFunc();
    }

}
