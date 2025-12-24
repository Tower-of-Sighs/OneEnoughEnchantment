package cc.sighs.oneenoughenchantment;

import cc.sighs.oneenoughenchantment.compat.KubeJSCompat;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

public class Utils {
    private static HashMap<String, Integer> cache = new HashMap<>();

    public static HashMap<String, Integer> getCache() {
        return cache;
    }

    public static void setCache(HashMap<String, Integer> cache) {
        Utils.cache = cache;
    }

    public static int getWeight(String id) {
        return KubeJSCompat.getWeight(id);
    }

    public static int getWeight(ResourceLocation id) {
        if (id != null) return getWeight(id.toString());
        return 1;
    }
}
