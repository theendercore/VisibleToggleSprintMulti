package com.theendercore.visible_toggle_sprint.events;


import com.theendercore.visible_toggle_sprint.Constants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.theendercore.visible_toggle_sprint.CommonClass.configButton;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Constants.MODID, value = Dist.CLIENT)
public class ClientEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeymapping(RegisterKeyMappingsEvent event) {
        event.register(configButton);
    }

}
