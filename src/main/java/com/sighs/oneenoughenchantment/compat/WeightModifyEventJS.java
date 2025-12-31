package com.sighs.oneenoughenchantment.compat;

import com.sighs.oneenoughenchantment.Utils;
import dev.latvian.mods.kubejs.server.ServerKubeEvent;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.server.MinecraftServer;

import java.util.HashMap;
import java.util.Map;

public class WeightModifyEventJS extends ServerKubeEvent {
    @HideFromJS
    public Map<String, Integer> weights = new HashMap<>();

    public WeightModifyEventJS(MinecraftServer s, Map<String, Integer> weights) {
        super(s);
        weights.forEach(this::setWeight);
    }

    public void setWeight(String id, int weight) {
        weights.put(id, weight);
    }

    public int getWeight(String id) {
        return weights.getOrDefault(id, Utils.getOriginWeight(id));
    }
}
