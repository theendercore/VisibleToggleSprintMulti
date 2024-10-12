package com.theendercore.visible_toggle_sprint.events;


import com.theendercore.visible_toggle_sprint.CommonClass;
import com.theendercore.visible_toggle_sprint.Constants;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.event.TickEvent.ClientTickEvent;
import net.neoforged.event.TickEvent.Phase;
import net.neoforged.eventbus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Constants.MODID, value = Dist.CLIENT)
public class ModClientEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeymappingActions(ClientTickEvent event) {
        if (event.phase == Phase.END) CommonClass.handleKeyBinds(Minecraft.getInstance());
    }

}
