package cc.sighs.oneenoughenchantment.compat;

import cc.sighs.oneenoughenchantment.Utils;
import net.minecraftforge.fml.ModList;

public class KubeJSCompat {
    private static final String MOD_ID = "kubejs";
    private static boolean INSTALLED = false;

    public static void init() {
        INSTALLED = ModList.get().isLoaded(MOD_ID);
    }

    public static int getWeight(String id) {
        if (INSTALLED) {
            return KubeJSCompatInner.getWeight(id);
        }
        return Utils.getCache().getOrDefault(id, 1);
    }
}
