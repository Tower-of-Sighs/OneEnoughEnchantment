package com.sighs.oneenoughenchantment.mixin;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Utils;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEnchantments.class)
public class ItemEnchantmentsMixin {

    @Inject(method = "<init>", at = @At("HEAD"))
    private static void init(Object2IntOpenHashMap<Holder<Enchantment>> enchantments, boolean showInTooltip, CallbackInfo ci) {
        boolean delete;
        try {
            delete = Config.DELETE.get();
        } catch (Exception e) {
            delete = true;
        }
        if (delete && !enchantments.isEmpty()) {
            enchantments.keySet().removeIf(holder -> Utils.getWeight(holder.value()) == 0);
        }
    }
}
