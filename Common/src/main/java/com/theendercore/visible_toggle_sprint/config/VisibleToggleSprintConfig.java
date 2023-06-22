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
import dev.isxander.yacl3.config.ConfigInstance;
import dev.isxander.yacl3.config.GsonConfigInstance;
import dev.isxander.yacl3.gui.controllers.ColorController;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.List;

import static com.theendercore.visible_toggle_sprint.Constants.*;

public class VisibleToggleSprintConfig {

    public static final ConfigInstance<VisibleToggleSprintConfig> INSTANCE = GsonConfigInstance.createBuilder(VisibleToggleSprintConfig.class)
            .setPath(Services.PLATFORM.getConfigPath("visible_toggle_sprint.json"))
            .build();
    public static PlayerState sprint = new PlayerState(true, new Vec2i(-6), CrosshairIcons.DEFAULT, false, new Vec2i(125, 18), false, new Vec2i(10), -1);
    public static PlayerState sneak = new PlayerState(true, new Vec2i(1), CrosshairIcons.DEFAULT, false, new Vec2i(147, 18), false, new Vec2i(10, 30), -1);

    public static Screen makeScreen(Screen parent) {
        return YetAnotherConfigLib.create(INSTANCE, (defaults, config, builder) -> builder
                        .title(genText("config.", ".title"))
                        .category(genOptions(sprint, "sprint", new Vec2i(-6, 10), 125))
                        .category(genOptions(sneak, "sneak", new Vec2i(1, 30), 150))
                )
                .generateScreen(parent);
    }

    private static ConfigCategory genOptions(PlayerState ps, String name, Vec2i defVal, int defVal2) {
        return ConfigCategory.createBuilder()
                .name(genText("config.", "." + name + ".tab"))
                .group(OptionGroup.createBuilder()
                        .name(genText("config.", ".group.crosshair"))
                        .options(List.of(
                                getBooleanOption(ps.crosshair, true),
                                Option.<Integer>createBuilder()
                                        .name(genText("config.", ".location.x"))
                                        .binding(
                                                defVal.x,
                                                () -> ps.crosshair.location.x,
                                                value -> ps.crosshair.location.x = value
                                        )
                                        .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                .range(-32, 32)
                                                .step(1))
                                        .build(),
                                Option.<Integer>createBuilder()
                                        .name(genText("config.", ".location.y"))
                                        .binding(
                                                defVal.x,
                                                () -> ps.crosshair.location.y,
                                                value -> ps.crosshair.location.y = value
                                        )
                                        .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                .range(-32, 32)
                                                .step(1))
                                        .build(),
                                Option.<CrosshairIcons>createBuilder()
                                        .name(genText("config.", ".icon"))
                                        .binding(
                                                CrosshairIcons.DEFAULT,
                                                () -> ps.crosshair.icon,
                                                newValue -> ps.crosshair.icon = newValue
                                        )
                                        .controller(opt -> EnumControllerBuilder.create(opt).enumClass(CrosshairIcons.class))
                                        .build()
                        ))
                        .build())
                .group(OptionGroup.createBuilder()
                        .name(genText("config.", ".group.hotbar"))
                        .options(
                                List.of(
                                        getBooleanOption(ps.hotbar, false),
                                        Option.<Integer>createBuilder()
                                                .name(genText("config.", ".location.x"))
                                                .binding(
                                                        defVal2,
                                                        () -> ps.hotbar.location.x,
                                                        value -> ps.hotbar.location.x = value
                                                )
                                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                        .range(-250, 250)
                                                        .step(5))
                                                .build(),
                                        Option.<Integer>createBuilder()
                                                .name(genText("config.", ".location.y"))
                                                .binding(
                                                        18,
                                                        () -> ps.hotbar.location.y,
                                                        value -> ps.hotbar.location.y = value
                                                )

                                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                        .range(0, 400)
                                                        .step(2))
                                                .build()
                                )

                        )
                        .build())
                .group(OptionGroup.createBuilder()
                        .name(genText("config.", ".group.text"))
                        .options(
                                List.of(
                                        getBooleanOption(ps.text, false),
                                        Option.<Integer>createBuilder()
                                                .name(genText("config.", ".location.x"))
                                                .binding(
                                                        10,
                                                        () -> ps.text.location.x,
                                                        value -> ps.text.location.x = value
                                                )
                                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                        .range(0, 1920)
                                                        .step(10))
                                                .build(),


                                        Option.<Integer>createBuilder()
                                                .name(genText("config.", ".location.y"))
                                                .binding(
                                                        defVal.y,
                                                        () -> ps.text.location.y,
                                                        value -> ps.text.location.y = value
                                                )

                                                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                                                        .range(0, 1080)
                                                        .step(10))
                                                .build(),
                                        Option.<Color>createBuilder()
                                                .name(genText("config.", ".color"))
                                                .binding(
                                                        Color.WHITE,
                                                        () -> new Color(ps.text.color),
                                                        value -> ps.text.color = value.getRGB()
                                                )
                                                .customController(ColorController::new)
                                                .build()
                                )
                        )
                        .build())
                .build();
    }


    private static <T extends IState> Option<Boolean> getBooleanOption(T inVal, boolean defVal) {
        return Option.<Boolean>createBuilder()
                .name(genText("config.", ".enable"))
                .binding(
                        defVal,
                        () -> inVal.enable,
                        value -> inVal.enable = value
                )
                .controller(TickBoxControllerBuilder::create)
                .build();
    }

    static Component genText(String first, String second) {
        return Component.translatable(first + MODID + second);
    }
}