package cc.sighs.oneenoughenchantment;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> RULES = BUILDER
            .comment("\"minecraft:knockback=100\", \"minecraft:sharpness=0\"")
            .defineList("rules",
                    List.of(),
                    entry -> entry instanceof String
            );

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int getWeight(String id) {
        for (String b : RULES.get()) {
            String[] entry = b.split("=");
            if (entry[0].equals(id)) return Integer.parseInt(entry[1]);
        }
        return 1;
    }
}
