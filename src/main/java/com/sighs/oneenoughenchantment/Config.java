package com.sighs.oneenoughenchantment;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.List;

@EventBusSubscriber(modid = OneEnoughEnchantment.MOD_ID)
public class Config {
    public static final ModConfigSpec CONFIG_SPEC;

    public static final ModConfigSpec.ConfigValue<List<? extends String>> RULES;

    public static final ModConfigSpec.BooleanValue RECIPE;

    public static final ModConfigSpec.BooleanValue DELETE;

    static {
        ModConfigSpec.Builder CONFIG_BUILDER = new ModConfigSpec.Builder();
        CONFIG_BUILDER.push("config");
        RULES = CONFIG_BUILDER.comment("\"minecraft:knockback=100\", \"minecraft:sharpness=0\"")
                .defineList("rules",
                        List.of(),
                        entry -> entry instanceof String
                );
        RECIPE = CONFIG_BUILDER
                .comment("刷新权重为0的附魔是否移除可用配方，如铁砧配方，关闭后对应的附魔书将无法使用，也不会在JEI中看到用途，但此类附魔依旧可以存在，可以通过指令等方式为物品附魔。")
                .define("dropRecipe", true);
        DELETE = CONFIG_BUILDER
                .comment("刷新权重为0的附魔是否完全被移除，开启后，创造模式物品栏和JEI等都会查不到，且会影响到已有此附魔的物品。")
                .define("deepDelete", true);
        CONFIG_BUILDER.pop();
        CONFIG_SPEC = CONFIG_BUILDER.build();
    }

    @SubscribeEvent
    public static void listen(ModConfigEvent event) {
        if (event.getConfig().getModId().equals(OneEnoughEnchantment.MOD_ID)) {
            updateCache();
        }
    }

    public static int getWeight(String id) {
        for (String b : RULES.get()) {
            String[] entry = b.split("=");
            if (entry[0].equals(id)) return Integer.parseInt(entry[1]);
        }
        return 1;
    }

    public static void updateCache() {
        HashMap<String, Integer> map = new HashMap<>();
        for (String b : RULES.get()) {
            String[] entry = b.split("=");
            if (entry.length == 2) {
                map.put(entry[0], Integer.parseInt(entry[1]));
            }
        }
        Utils.setCache(map);
    }
}
