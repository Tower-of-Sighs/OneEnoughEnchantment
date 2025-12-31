package com.sighs.oneenoughenchantment;

import com.sighs.oneenoughenchantment.compat.KubeJSCompat;
import lombok.Getter;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Utils {
    @Getter
    private static HashMap<String, Integer> cache = new HashMap<>();

    public static void setCache(HashMap<String, Integer> cache) {
        Utils.cache = cache;
    }

    public static int getOriginWeight(String id) {
        Registry<Enchantment> enchantmentRegistry = OneEnoughEnchantment.getEnchantmentRegistry();
        Enchantment enchantment = null;
        if (enchantmentRegistry != null) {
            enchantment = enchantmentRegistry.get(ResourceLocation.parse(id));
        }
        return enchantment == null ? 0 : enchantment.getWeight();
    }

    public static int getWeight(String id) {
        return KubeJSCompat.getWeight(id);
    }

    public static int getWeight(ResourceLocation id) {
        if (id != null) return getWeight(id.toString());
        return 1;
    }

    public static int getWeight(Enchantment enchantment) {
        Registry<Enchantment> enchantmentRegistry = OneEnoughEnchantment.getEnchantmentRegistry();
        if (enchantmentRegistry != null) {
            return getWeight(enchantmentRegistry.getKey(enchantment));
        }
        return 0;
    }

    public static String getAllEnchantment() {
        List<String> result = new ArrayList<>();
        Registry<Enchantment> enchantmentRegistry = OneEnoughEnchantment.getEnchantmentRegistry();
        if (enchantmentRegistry == null) return "";
        for (Enchantment enchantment : enchantmentRegistry) {
            ResourceLocation id = enchantmentRegistry.getKey(enchantment);
            String name = enchantment.description().getString();
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
