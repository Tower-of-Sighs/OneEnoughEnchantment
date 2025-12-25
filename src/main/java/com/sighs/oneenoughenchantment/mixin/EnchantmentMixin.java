package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    private void qq(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        ResourceLocation id = Registry.f_122825_.getKey((Enchantment) (Object) this);
        if (Config.RECIPE.get() && Utils.getWeight(id) == 0) cir.setReturnValue(false);
    }
}
