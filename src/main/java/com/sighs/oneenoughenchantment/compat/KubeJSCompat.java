package com.sighs.oneenoughenchantment.compat;

import com.sighs.oneenoughenchantment.Config;
import com.sighs.oneenoughenchantment.Oneenoughenchantment;
import com.sighs.oneenoughenchantment.Utils;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Oneenoughenchantment.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
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
