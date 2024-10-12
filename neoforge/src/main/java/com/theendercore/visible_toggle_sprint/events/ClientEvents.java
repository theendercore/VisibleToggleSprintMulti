package com.theendercore.visible_toggle_sprint.events;


import com.theendercore.visible_toggle_sprint.Constants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.client.event.RegisterKeyMappingsEvent;
import net.neoforged.eventbus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

import static com.theendercore.visible_toggle_sprint.CommonClass.configButton;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Constants.MODID, value = Dist.CLIENT)
public class ClientEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeymapping(RegisterKeyMappingsEvent event) {
        event.register(configButton);
    }

}
