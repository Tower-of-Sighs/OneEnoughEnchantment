package com.sighs.oneenoughenchantment;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

public class Utils {

    public static int getOriginWeight(String id) {
        Enchantment enchantment = Registry.f_122825_.get(new ResourceLocation(id));
        return enchantment == null ? 0 : enchantment.getRarity().getWeight();
    }

    public static int getWeight(ResourceLocation id) {
        if (id != null) return Config.getWeight(id.toString());
        return 1;
    }

    public static int getWeight(Enchantment enchantment) {
        return getWeight(Registry.f_122825_.getKey(enchantment));
    }
}
