package com.scarzehd.skintoggle.mixin;

import com.scarzehd.skintoggle.ModelPartSwitcher;
import com.scarzehd.skintoggle.ModelPartSwitchers;
import com.scarzehd.skintoggle.SkinToggle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin {
    @Inject(method = "onDamaged", at = @At("HEAD"))
    public void clientDamage(DamageSource source, CallbackInfo info) {
        LivingEntity self = ((LivingEntity)(Object)this);


        if (self.getWorld().isClient()) {
            if (source.getAttacker() instanceof PlayerEntity player) {
                if (player.isMainPlayer()) {
                    for (ModelPartSwitcher partSwitcher : ModelPartSwitchers.ALL) {
                        partSwitcher.onEntityDamage();
                    }
                }
            }
        }
    }
}
