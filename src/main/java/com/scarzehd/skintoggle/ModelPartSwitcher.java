package com.scarzehd.skintoggle;

import com.scarzehd.skintoggle.config.SkinToggleConfig;
import com.scarzehd.skintoggle.config.SkinToggleConfigModel;
import com.scarzehd.skintoggle.timer.Timer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.entity.player.PlayerModelPart;

public class ModelPartSwitcher {
    private final PlayerModelPart part;

    private final SkinToggleConfig.SkinPartConfigModel config;

    private final Timer flashTimer;

    public ModelPartSwitcher(PlayerModelPart part, SkinToggleConfig.SkinPartConfigModel config) {
        this.part = part;
        this.config = config;

        flashTimer = new Timer(config.flash_duration(), this::flashTimerCallback);
        flashTimer.ticking = false;
    }

    public boolean flashTimerCallback(Timer timer) {
        boolean value = config.mode() != SkinToggleConfigModel.SkinPartMode.FLASH_ON; // If mode is set to FLASH_ON, turn it back off when the timer ends

        MinecraftClient.getInstance().options.setPlayerModelPart(part, value);

        MinecraftClient.getInstance().options.write();

        return true;
    }

    private void startTimer() {
        flashTimer.ticksLeft = config.flash_duration();
        flashTimer.ticking = true;
    }

    public void onEntityDamage() {
        if (config.activation_type() == SkinToggleConfigModel.ActivationType.HIT_ENTITY) {
            SkinToggleConfigModel.SkinPartMode mode = config.mode();

            if (mode == SkinToggleConfigModel.SkinPartMode.FLASH_ON || mode == SkinToggleConfigModel.SkinPartMode.FLASH_OFF) {
                boolean value = mode == SkinToggleConfigModel.SkinPartMode.FLASH_ON;
                MinecraftClient.getInstance().options.setPlayerModelPart(part, value);
                startTimer();
            }

            if (mode == SkinToggleConfigModel.SkinPartMode.TOGGLE) {
                boolean value = !MinecraftClient.getInstance().options.isPlayerModelPartEnabled(part);
                MinecraftClient.getInstance().options.setPlayerModelPart(part, value);
            }

            MinecraftClient.getInstance().options.write();
        }
    }

    public void onPlayerDamage(ClientPlayerEntity player) {
        GameOptions options = MinecraftClient.getInstance().options;
        if (config.activation_type() == SkinToggleConfigModel.ActivationType.TAKE_DAMAGE) {
            SkinToggleConfigModel.SkinPartMode mode = config.mode();

            if (mode == SkinToggleConfigModel.SkinPartMode.FLASH_ON || mode == SkinToggleConfigModel.SkinPartMode.FLASH_OFF) {
                boolean value = mode == SkinToggleConfigModel.SkinPartMode.FLASH_ON;
                options.setPlayerModelPart(part, value);
                startTimer();
            }

            if (mode == SkinToggleConfigModel.SkinPartMode.TOGGLE) {
                boolean value = !options.isPlayerModelPartEnabled(part);
                options.setPlayerModelPart(part, value);
            }

            options.write();
        }
    }

    public void onPlayerHealthChanged(ClientPlayerEntity player) {
        SkinToggleConfigModel.ActivationType activationType = config.activation_type();

        if (activationType == SkinToggleConfigModel.ActivationType.HEALTH_HIGH || activationType == SkinToggleConfigModel.ActivationType.HEALTH_LOW) {
            int healthTarget = config.health_threshold();
            boolean value = activationType == SkinToggleConfigModel.ActivationType.HEALTH_HIGH ? player.getHealth() >= healthTarget : player.getHealth() <= healthTarget;

            if (config.mode() == SkinToggleConfigModel.SkinPartMode.FLASH_OFF) {
                value = !value;
            }

            MinecraftClient.getInstance().options.setPlayerModelPart(part, value);

            MinecraftClient.getInstance().options.write();
        }
    }
}
