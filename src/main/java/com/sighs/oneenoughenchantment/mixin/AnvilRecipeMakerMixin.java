package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "mezz.jei.library.plugins.vanilla.anvil.AnvilRecipeMaker$EnchantmentData")
public class AnvilRecipeMakerMixin {
    @Final
    @Shadow
    private Holder<Enchantment> enchantment;

    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    public void canEnchant(ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        if (Config.DELETE.get() && Config.getWeight(oneEnoughEnchantment$toKey(enchantment.value())) == 0) {
            cir.setReturnValue(false);
        }
    }

    @Unique
    private static String oneEnoughEnchantment$toKey(Enchantment enchantment) {
        String translationKey = ((TranslatableContents) enchantment.description().getContents()).getKey();
        if (!translationKey.startsWith("enchantment.")) {
            return translationKey;
        }

        String remaining = translationKey.substring(12);

        int firstDotIndex = remaining.indexOf('.');

        if (firstDotIndex != -1) {
            String namespace = remaining.substring(0, firstDotIndex);
            String path = remaining.substring(firstDotIndex + 1);
            return namespace + ":" + path;
        }

        return remaining;
    }
}
