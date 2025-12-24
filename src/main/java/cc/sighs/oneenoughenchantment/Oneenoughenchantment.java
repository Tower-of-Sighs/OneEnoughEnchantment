package cc.sighs.oneenoughenchantment;

import cc.sighs.oneenoughenchantment.compat.KubeJSCompat;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Oneenoughenchantment.MODID)
public class Oneenoughenchantment {

    public static final String MODID = "oneenoughenchantment";

    public Oneenoughenchantment() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC);
    }

    @SubscribeEvent
    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(KubeJSCompat::init);
        event.enqueueWork(Config::updateCache);
    }
}
