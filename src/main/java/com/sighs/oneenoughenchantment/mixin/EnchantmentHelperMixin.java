package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    @Inject(
            method = "lambda$getAvailableEnchantmentResults$41",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Holder;value()Ljava/lang/Object;"),
            cancellable = true)
    private static void removeDisabledFromTable(int level, List list, Holder<Enchantment> enchantmentHolder, CallbackInfo ci) {
        if (Config.DELETE.get() && Utils.getWeight(enchantmentHolder.value()) == 0) {
            ci.cancel();
        }
    }
}