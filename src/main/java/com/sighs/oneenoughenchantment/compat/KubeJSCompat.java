package com.sighs.oneenoughenchantment.compat;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.OneEnoughEnchantment;
import com.sighs.oneenoughenchantment.Utils;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = OneEnoughEnchantment.MOD_ID)
public class KubeJSCompat {
    private static final String MOD_ID = "kubejs";
    private static boolean INSTALLED = false;

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(KubeJSCompat::init);
        event.enqueueWork(Config::updateCache);
    }

    public static void init() {
        INSTALLED = ModList.get().isLoaded(MOD_ID);
    }

    public static int getWeight(String id) {
        if (INSTALLED) {
            return KubeJSCompatInner.getWeight(id);
        }
        return Utils.getCache().getOrDefault(id, Utils.getOriginWeight(id));
    }
}
