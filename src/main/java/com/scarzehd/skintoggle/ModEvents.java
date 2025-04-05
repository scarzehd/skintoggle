package com.scarzehd.skintoggle;

import com.scarzehd.skintoggle.timer.TimerManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class ModEvents {
    public static void registerEvents() {
        SkinToggle.LOGGER.info("Registering events for SkinToggle");

        ClientTickEvents.END_CLIENT_TICK.register((client -> {
            TimerManager.tick();
        }));
    }
}
