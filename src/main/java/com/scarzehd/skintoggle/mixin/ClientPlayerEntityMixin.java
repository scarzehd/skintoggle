package com.scarzehd.skintoggle.mixin;

import com.mojang.authlib.GameProfile;
import com.scarzehd.skintoggle.ModelPartSwitcher;
import com.scarzehd.skintoggle.ModelPartSwitchers;
import com.scarzehd.skintoggle.SkinToggle;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
        throw new AssertionError();
    }

    @Inject(method = "damage", at = @At("HEAD"))
    private void damageClient(DamageSource source, float amount, CallbackInfoReturnable info) {
        if (amount <= 0) {
            return;
        }

        ClientPlayerEntity self = ((ClientPlayerEntity)(Object)this);

        if (self.getWorld().isClient() && self.isMainPlayer()) {
            for (ModelPartSwitcher partSwitcher : ModelPartSwitchers.ALL) {
                partSwitcher.onPlayerDamage(self);
            }
        }
    }

    @Override
    public void setHealth(float amount) {
        ClientPlayerEntity self = ((ClientPlayerEntity)(Object)this);

        if (self.getWorld().isClient() && self.isMainPlayer()) {
            for (ModelPartSwitcher partSwitcher : ModelPartSwitchers.ALL) {
                partSwitcher.onPlayerHealthChanged(self);
            }
        }

        super.setHealth(amount);
    }
}
