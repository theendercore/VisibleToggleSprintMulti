package com.theendercore.visible_toggle_sprint.events;


import com.theendercore.visible_toggle_sprint.Constants;
import com.theendercore.visible_toggle_sprint.common.ConfigKeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = Constants.MODID, value = Dist.CLIENT)
public class ModClientEvents {
    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerKeymappingActions(ClientTickEvent event) {
        if (event.phase == Phase.END) ConfigKeyMapping.keyMappingAction(Minecraft.getInstance());
    }

}
