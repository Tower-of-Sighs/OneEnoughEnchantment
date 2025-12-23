package cc.sighs.oneenoughenchantment.mixin;

import cc.sighs.oneenoughenchantment.Config;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.List;

@Mixin(value = EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {
    @Shadow
    @Nullable
    public static ResourceLocation getEnchantmentId(Enchantment p_182433_) {
        return null;
    }

    @Redirect(method = "getAvailableEnchantmentResults", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
    private static <E> boolean modify(List<E> instance, E e) {
        if (e instanceof EnchantmentInstance enchantment) {
            ResourceLocation id = getEnchantmentId(enchantment.enchantment);
            if (id != null) for (int i = 0; i < Config.getWeight(id.toString()); i++) instance.add(e);
        }
        return false;
    }

    @Inject(method = "getEnchantmentId(Lnet/minecraft/world/item/enchantment/Enchantment;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private static void aaa(Enchantment enchantment, CallbackInfoReturnable<ResourceLocation> cir) {
        ResourceLocation id = BuiltInRegistries.ENCHANTMENT.getKey(enchantment);
        if (id != null && Config.getWeight(id.toString()) == 0) cir.setReturnValue(null);
    }
}
