package com.theendercore.visible_toggle_sprint.config;

import com.theendercore.visible_toggle_sprint.lib.*;
import com.theendercore.visible_toggle_sprint.platform.Services;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.ConfigEntry;
import dev.isxander.yacl3.config.ConfigInstance;
import dev.isxander.yacl3.config.GsonConfigInstance;
import dev.isxander.yacl3.gui.controllers.ColorController;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.theendercore.visible_toggle_sprint.Constants.*;

public class VisibleToggleSprintConfig {

    public static final ConfigInstance<VisibleToggleSprintConfig> INSTANCE = GsonConfigInstance.createBuilder(VisibleToggleSprintConfig.class)
            .setPath(Services.PLATFORM.getConfigPath().resolve("visible_toggle_sprint.json"))
            .build();

    @ConfigEntry
    public int version = CONFIG_VERSION;

    // --- Sprint ---
    @ConfigEntry
    public boolean sprintCross = true;
    @ConfigEntry
    public int sprintCrossLocationX = -6;
    @ConfigEntry
    public int sprintCrossLocationY = -6;

    @ConfigEntry
    public CrosshairIcons sprintCrossIcon = CrosshairIcons.DEFAULT;

    @ConfigEntry
    public boolean sprintBar = false;

    @ConfigEntry
    public int sprintBarLocationX = 125;
    @ConfigEntry
    public int sprintBarLocationY = 18;

    @ConfigEntry
    public boolean sprintText = false;
    @ConfigEntry
    public int sprintTextLocationX = 10;
    @ConfigEntry
    public int sprintTextLocationY = 10;
    @ConfigEntry
    public Color sprintTextColor = Color.WHITE;

    // --- Sneak ---
    @ConfigEntry
    public boolean sneakCross = true;
    @ConfigEntry
    public int sneakCrossLocationX = 1;
    @ConfigEntry
    public int sneakCrossLocationY = 1;

    @ConfigEntry
    public CrosshairIcons sneakCrossIcon = CrosshairIcons.DEFAULT;

    @ConfigEntry
    public boolean sneakBar = false;

    @ConfigEntry
    public int sneakBarLocationX = 147;
    @ConfigEntry
    public int sneakBarLocationY = 18;

    @ConfigEntry
    public boolean sneakText = false;
    @ConfigEntry
    public int sneakTextLocationX = 10;
    @ConfigEntry
    public int sneakTextLocationY = 30;
    @ConfigEntry
    public Color sneakTextColor = Color.WHITE;

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> builder
                .title(genText(".title"))
                .category(ConfigCategory.createBuilder().name(genText(".sprint.tab"))
                        .group(genGroup("crosshair").options(List.of(
                                genBool(defaults.sprintCross, () -> config.sprintCross, value -> config.sprintCross = value),
                                genInt("location.x", defaults.sprintCrossLocationX, () -> config.sprintCrossLocationX, val -> config.sprintCrossLocationX = val, -32, 32, 1),
                                genInt("location.y", defaults.sprintCrossLocationY, () -> config.sprintCrossLocationY, val -> config.sprintCrossLocationY = val, -32, 32, 1),
                                genIcon(defaults.sprintCrossIcon, () -> config.sprintCrossIcon, val -> config.sprintCrossIcon = val)
                        )).build())

                        .group(genGroup("hotbar").options(List.of(
                                genBool(defaults.sprintBar, () -> config.sprintBar, value -> config.sprintBar = value),
                                genInt("location.x", defaults.sprintBarLocationX, () -> config.sprintBarLocationX, val -> config.sprintBarLocationX = val, -250, 250, 5),
                                genInt("location.y", defaults.sprintBarLocationY, () -> config.sprintBarLocationY, val -> config.sprintBarLocationY = val, 0, 400, 2)
                        )).build())

                        .group(genGroup("text").options(List.of(
                                genBool(defaults.sprintText, () -> config.sprintText, value -> config.sprintText = value),
                                genInt("location.x", defaults.sprintTextLocationX, () -> config.sprintTextLocationX, val -> config.sprintTextLocationX = val, 0, 1920, 10),
                                genInt("location.y", defaults.sprintTextLocationY, () -> config.sprintTextLocationY, val -> config.sprintTextLocationY = val, 0, 1080, 10),
                                Option.<Color>createBuilder()
                                        .name(genText(".color"))
                                        .binding(defaults.sprintTextColor, () -> config.sprintTextColor, value -> config.sprintTextColor = value)
                                        .customController(ColorController::new)
                                        .build()
                        )).build())
                        .build())
                .category(ConfigCategory.createBuilder().name(genText(".sneak.tab"))
                        .group(genGroup("crosshair").options(List.of(
                                genBool(defaults.sneakCross, () -> config.sneakCross, value -> config.sneakCross = value),
                                genInt("location.x", defaults.sneakCrossLocationX, () -> config.sneakCrossLocationX, val -> config.sneakCrossLocationX = val, -32, 32, 1),
                                genInt("location.y", defaults.sneakCrossLocationY, () -> config.sneakCrossLocationY, val -> config.sneakCrossLocationY = val, -32, 32, 1),
                                genIcon(defaults.sneakCrossIcon, () -> config.sneakCrossIcon, val -> config.sneakCrossIcon = val)
                        )).build())

                        .group(genGroup("hotbar").options(List.of(
                                genBool(defaults.sneakBar, () -> config.sneakBar, value -> config.sneakBar = value),
                                genInt("location.x", defaults.sneakBarLocationX, () -> config.sneakBarLocationX, val -> config.sneakBarLocationX = val, -250, 250, 5),
                                genInt("location.y", defaults.sneakBarLocationY, () -> config.sneakBarLocationY, val -> config.sneakBarLocationY = val, 0, 400, 2)
                        )).build())

                        .group(genGroup("text").options(List.of(
                                genBool(defaults.sneakText, () -> config.sneakText, value -> config.sneakText = value),
                                genInt("location.x", defaults.sneakTextLocationX, () -> config.sneakTextLocationX, val -> config.sneakTextLocationX = val, 0, 1920, 10),
                                genInt("location.y", defaults.sneakTextLocationY, () -> config.sneakTextLocationY, val -> config.sneakTextLocationY = val, 0, 1080, 10),
                                Option.<Color>createBuilder()
                                        .name(genText(".color"))
                                        .binding(defaults.sneakTextColor, () -> config.sneakTextColor, value -> config.sneakTextColor = value)
                                        .customController(ColorController::new)
                                        .build()
                        )).build())
                        .build())
        ).generateScreen(parent);
    }

    private static OptionGroup.Builder genGroup(String name) {
        return OptionGroup.createBuilder().name(genText(".group." + name));
    }

    private static Option<Boolean> genBool(Boolean def, Supplier<Boolean> getter, Consumer<Boolean> setter) {
        return Option.<Boolean>createBuilder()
                .name(genText(".enable"))
                .binding(def, getter, setter)
                .controller(TickBoxControllerBuilder::create)
                .build();
    }

    private static Option<Integer> genInt(String name, Integer def, Supplier<Integer> getter, Consumer<Integer> setter, int min, int max, int step) {
        return Option.<Integer>createBuilder()
                .name(genText("." + name))
                .binding(def, getter, setter)
                .controller(opt -> IntegerSliderControllerBuilder.create(opt).range(min, max).step(step))
                .build();
    }

    private static Option<CrosshairIcons> genIcon(CrosshairIcons def, Supplier<CrosshairIcons> getter, Consumer<CrosshairIcons> setter) {
        return Option.<CrosshairIcons>createBuilder()
                .name(genText(".icon"))
                .binding(def, getter, setter)
                .controller(opt -> EnumControllerBuilder.create(opt).enumClass(CrosshairIcons.class))
                .build();
    }

    static Component genText(String second) {
        return Component.translatable("config." + MODID + second);
    }
}