package com.sighs.oneenoughenchantment;

import com.sighs.oneenoughenchantment.compat.KubeJSCompat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    private static HashMap<String, Integer> cache = new HashMap<>();

    public static HashMap<String, Integer> getCache() {
        return cache;
    }

    public static void setCache(HashMap<String, Integer> cache) {
        Utils.cache = cache;
    }

    public static int getOriginWeight(String id) {
        Enchantment enchantment = BuiltInRegistries.ENCHANTMENT.get(new ResourceLocation(id));
        return enchantment == null ? 0 : enchantment.getRarity().getWeight();
    }

    public static int getWeight(String id) {
        return KubeJSCompat.getWeight(id);
    }

    public static int getWeight(ResourceLocation id) {
        if (id != null) return getWeight(id.toString());
        return 1;
    }

    public static int getWeight(Enchantment enchantment) {
        return getWeight(BuiltInRegistries.ENCHANTMENT.getKey(enchantment));
    }

    public static String getAllEnchantment() {
        List<String> result = new ArrayList<>();
        for (Enchantment enchantment : BuiltInRegistries.ENCHANTMENT) {
            ResourceLocation id = BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
            String name = Component.translatable(enchantment.getDescriptionId()).getString();
            if (id != null) result.add(id + "(" + name + ")=" + getWeight(id));
        }
        return toJsonArray(result);
    }

    public static String toJsonArray(List<String> items) {
        if (items == null || items.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < items.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append("\"").append(items.get(i)).append("\"");
        }
        sb.append("]");
        return sb.toString();
    }
}
