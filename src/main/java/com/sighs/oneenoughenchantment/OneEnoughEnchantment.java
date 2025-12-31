package com.sighs.oneenoughenchantment;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.server.ServerLifecycleHooks;
import org.slf4j.Logger;

@Mod(OneEnoughEnchantment.MOD_ID)
public class OneEnoughEnchantment {
    public static final String MOD_ID = "oneenoughenchantment";
    public static final Logger LOGGER = LogUtils.getLogger();

    public OneEnoughEnchantment
            (IEventBus modEventBus, ModContainer modContainer, Dist dist) {
        if (dist == Dist.CLIENT) {
            modContainer.registerConfig(ModConfig.Type.COMMON, Config.CONFIG_SPEC, "%s_config.toml".formatted(MOD_ID));
            modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        }
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static String formattedMod(String path) {
        return path.formatted(MOD_ID);
    }

    public static boolean isPresentResource(ResourceLocation resourceLocation) {
        return Minecraft.getInstance().getResourceManager().getResource(resourceLocation).isPresent();
    }

    public static Registry<Enchantment> getEnchantmentRegistry() {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server != null) {
            ServerLevel level = server.getLevel(Level.OVERWORLD);
            if (level != null) {
                return level.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            }
        } else {
            ClientPacketListener connection = Minecraft.getInstance().getConnection();
            if (connection != null) return connection.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        }
        return null;
    }

    private static boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }
}