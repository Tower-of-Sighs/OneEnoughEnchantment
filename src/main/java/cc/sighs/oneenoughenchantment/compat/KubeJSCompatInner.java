package cc.sighs.oneenoughenchantment.compat;

import net.minecraftforge.server.ServerLifecycleHooks;

public class KubeJSCompatInner {
    public static int getWeight(String id) {
        WeightModifyEventJS e = new WeightModifyEventJS(ServerLifecycleHooks.getCurrentServer());
        EnchantmentEvents.WEIGHT.post(e);
        return e.getWeight(id);
    }
}
