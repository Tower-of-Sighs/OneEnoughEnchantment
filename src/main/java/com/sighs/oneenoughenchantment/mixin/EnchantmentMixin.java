package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Enchantment.class)
public class EnchantmentMixin {

    @Inject(method = "isSupportedItem", at = @At("HEAD"), cancellable = true)
    public void isSupportedItem(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (Utils.getWeight((Enchantment) (Object) this) == 0) {
            cir.setReturnValue(false);
        }
    }
}
