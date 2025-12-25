package com.sighs.oneenoughenchantment;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Oneenoughenchantment.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> RULES = BUILDER
            .comment("\"minecraft:knockback=100\", \"minecraft:sharpness=0\"")
            .defineList("rules",
                    List.of(),
                    entry -> entry instanceof String
            );
    public static final ForgeConfigSpec.BooleanValue RECIPE = BUILDER
            .comment("刷新权重为0的附魔是否移除可用配方，如铁砧配方，关闭后对应的附魔书将无法使用，也不会在JEI中看到用途，但此类附魔依旧可以存在，可以通过指令等方式为物品附魔。")
            .define("dropRecipe", true);

    public static final ForgeConfigSpec.BooleanValue DELETE = BUILDER
            .comment("刷新权重为0的附魔是否完全被移除，开启后，创造模式物品栏和JEI等都会查不到，且会影响到已有此附魔的物品。")
            .define("deepDelete", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int getWeight(String id) {
        for (String b : RULES.get()) {
            String[] entry = b.split("=");
            if (entry[0].equals(id)) return Integer.parseInt(entry[1]);
        }
        return Utils.getOriginWeight(id);
    }
}
