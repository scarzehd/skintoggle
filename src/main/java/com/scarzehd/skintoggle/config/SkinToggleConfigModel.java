package com.scarzehd.skintoggle.config;

import com.scarzehd.skintoggle.SkinToggle;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Hook;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Nest;

@Modmenu(modId = SkinToggle.MOD_ID)
@Config(name = "skin-toggle", wrapperName = "SkinToggleConfig")
public class SkinToggleConfigModel {

    @Nest
    public SkinPartConfigModel left_arm = new SkinPartConfigModel();

    @Nest
    public SkinPartConfigModel right_arm = new SkinPartConfigModel();

    @Nest
    public SkinPartConfigModel left_leg = new SkinPartConfigModel();

    @Nest
    public SkinPartConfigModel right_leg = new SkinPartConfigModel();

    @Nest
    public SkinPartConfigModel cape = new SkinPartConfigModel();

    @Nest
    public SkinPartConfigModel hat = new SkinPartConfigModel();

    @Nest
    public SkinPartConfigModel jacket = new SkinPartConfigModel();

    public static class SkinPartConfigModel {
        @Hook
        public SkinPartMode mode = SkinPartMode.DISABLED;
        public int flash_duration = 100;
        public ActivationType activation_type = ActivationType.HIT_ENTITY;
        public int health_threshold = 5;
    }

    public enum SkinPartMode {
        DISABLED,
        TOGGLE,
        FLASH_ON,
        FLASH_OFF
    }

    public enum ActivationType {
        HIT_ENTITY,
        HEALTH_LOW,
        HEALTH_HIGH,
        TAKE_DAMAGE
    }
}
