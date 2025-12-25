package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EnchantmentInstance.class)
public class EnchantmentInstanceMixin {
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment$Rarity;getWeight()I"))
    private static int redirect(Enchantment.Rarity instance, Enchantment enchantment, int level) {
        return Utils.getWeight(enchantment);
    }
}
