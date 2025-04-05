package com.scarzehd.skintoggle.mixin;

import com.scarzehd.skintoggle.ModelPartSwitcher;
import com.scarzehd.skintoggle.ModelPartSwitchers;
import com.scarzehd.skintoggle.SkinToggle;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"))
    private void damageClient(DamageSource source, float amount, CallbackInfoReturnable info) {
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
