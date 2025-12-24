package cc.sighs.oneenoughenchantment.compat;

import cc.sighs.oneenoughenchantment.Utils;
import dev.latvian.mods.kubejs.server.ServerEventJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.server.MinecraftServer;

import java.util.Map;

public class WeightModifyEventJS extends ServerEventJS {
    @HideFromJS
    public Map<String, Integer> weights;

    public WeightModifyEventJS(MinecraftServer s) {
        super(s);
        weights = Map.copyOf(Utils.getCache());
    }

    public void setWeight(String id, int weight) {
        weights.put(id, weight);
    }

    public Map<String, Integer> getWeights() {
        return weights;
    }

    public int getWeight(String id) {
        return weights.getOrDefault(id, 1);
    }
}
