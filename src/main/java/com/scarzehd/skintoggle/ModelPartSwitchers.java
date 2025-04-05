package com.scarzehd.skintoggle;

import net.minecraft.entity.player.PlayerModelPart;

import java.util.ArrayList;
import java.util.List;

public class ModelPartSwitchers {

    public static final ModelPartSwitcher RIGHT_ARM = new ModelPartSwitcher(PlayerModelPart.RIGHT_SLEEVE, SkinToggle.CONFIG.right_arm);

    public static final ModelPartSwitcher LEFT_ARM = new ModelPartSwitcher(PlayerModelPart.LEFT_SLEEVE, SkinToggle.CONFIG.left_arm);

    public static final ModelPartSwitcher RIGHT_LEG = new ModelPartSwitcher(PlayerModelPart.RIGHT_PANTS_LEG, SkinToggle.CONFIG.right_leg);

    public static final ModelPartSwitcher LEFT_LEG = new ModelPartSwitcher(PlayerModelPart.LEFT_PANTS_LEG, SkinToggle.CONFIG.left_leg);

    public static final ModelPartSwitcher HAT = new ModelPartSwitcher(PlayerModelPart.HAT, SkinToggle.CONFIG.hat);

    public static final ModelPartSwitcher JACKET = new ModelPartSwitcher(PlayerModelPart.JACKET, SkinToggle.CONFIG.jacket);

    public static final ModelPartSwitcher CAPE = new ModelPartSwitcher(PlayerModelPart.CAPE, SkinToggle.CONFIG.cape);

    public static List<ModelPartSwitcher> ALL;

    public static void createModelPartSwitchers() {
        SkinToggle.LOGGER.info("Setting up model part switching.");

        ALL = new ArrayList<>();

        ALL.add(RIGHT_ARM);
        ALL.add(LEFT_ARM);
        ALL.add(RIGHT_LEG);
        ALL.add(LEFT_LEG);
        ALL.add(HAT);
        ALL.add(JACKET);
        ALL.add(CAPE);
    }
}
