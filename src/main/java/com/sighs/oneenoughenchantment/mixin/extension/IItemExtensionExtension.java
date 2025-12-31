package com.sighs.oneenoughenchantment.mixin.extension;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Utils;
import net.minecraft.core.Holder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;

public interface IItemExtensionExtension {
    default boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        if (Config.RECIPE.get() && Utils.getWeight(enchantment.value()) == 0) return false;
        return stack.is(Items.ENCHANTED_BOOK) || ((Enchantment) enchantment.value()).isSupportedItem(stack);
    }
}
