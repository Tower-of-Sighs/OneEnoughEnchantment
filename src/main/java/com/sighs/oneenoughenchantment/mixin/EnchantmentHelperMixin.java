package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Redirect(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/Enchantment;isDiscoverable()Z"))
    private static boolean modify(Enchantment enchantment) {
        ResourceLocation id = BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
        int weight = Utils.getWeight(id);
        return enchantment.isDiscoverable() && weight > 0;
    }

    @Redirect(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private static <E> boolean modify(List<E> instance, E e) {
        if (e instanceof EnchantmentInstance enchantment) {
            ResourceLocation id = BuiltInRegistries.ENCHANTMENT.getKey(enchantment.enchantment);
            for (int i = 0; i < Utils.getWeight(id); i++) instance.add(e);
        }
        return false;
    }

    @Inject(method = "getEnchantmentId*", at = @At("RETURN"), cancellable = true)
    private static void aaa(CallbackInfoReturnable<ResourceLocation> cir) {
        if (cir.getReturnValue() != null && Config.DELETE.get() && Utils.getWeight(cir.getReturnValue()) == 0) cir.setReturnValue(null);
    }
}
