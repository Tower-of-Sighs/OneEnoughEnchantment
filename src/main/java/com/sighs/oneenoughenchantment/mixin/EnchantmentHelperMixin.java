package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Redirect(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;isDiscoverable()Z"))
    private static boolean modify(Enchantment enchantment) {
        return enchantment.isDiscoverable() && Utils.getWeight(enchantment) > 0;
    }

    @Inject(method = "getEnchantmentId*", at = @At("RETURN"), cancellable = true)
    private static void aaa(CallbackInfoReturnable<ResourceLocation> cir) {
        if (cir.getReturnValue() != null && Config.DELETE.get() && Utils.getWeight(cir.getReturnValue()) == 0) cir.setReturnValue(null);
    }
}
