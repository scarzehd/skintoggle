package com.scarzehd.skintoggle.mixin;

import com.mojang.authlib.GameProfile;
import com.scarzehd.skintoggle.ModelPartSwitcher;
import com.scarzehd.skintoggle.ModelPartSwitchers;
import com.scarzehd.skintoggle.SkinToggle;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
        throw new AssertionError();
    }

    @Override
    public void onDamaged(DamageSource source) {


        ClientPlayerEntity self = ((ClientPlayerEntity)(Object)this);

        if (self.getWorld().isClient() && self.isMainPlayer()) {
            for (ModelPartSwitcher partSwitcher : ModelPartSwitchers.ALL) {
                partSwitcher.onPlayerDamage(self);
            }
        }

        super.onDamaged(source);
    }

    @Inject(method = "updateHealth", at = @At("HEAD"))
    public void setHealth(float amount, CallbackInfo info) {
        ClientPlayerEntity self = ((ClientPlayerEntity)(Object)this);

        if (self.getWorld().isClient() && self.isMainPlayer()) {
            for (ModelPartSwitcher partSwitcher : ModelPartSwitchers.ALL) {
                partSwitcher.onPlayerHealthChanged(self);
            }
        }
    }
}
