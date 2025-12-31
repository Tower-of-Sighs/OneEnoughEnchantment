package com.sighs.oneenoughenchantment.compat;

import com.sighs.oneenoughenchantment.Utils;
import net.neoforged.neoforge.server.ServerLifecycleHooks;

import java.util.Map;

public class KubeJSCompatInner {
    public static int getWeight(String id) {
        WeightModifyEventJS e = new WeightModifyEventJS(ServerLifecycleHooks.getCurrentServer(), Map.copyOf(Utils.getCache()));
        EnchantmentEvents.WEIGHT.post(e);
        return e.getWeight(id);
    }
}
