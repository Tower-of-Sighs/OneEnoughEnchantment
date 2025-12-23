package cc.sighs.oneenoughenchantment.mixin;

import cc.sighs.oneenoughenchantment.Config;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Enchantment.class)
public class EnchantmentMixin {
    @Inject(method = "canEnchant", at = @At("HEAD"), cancellable = true)
    private void qq(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        ResourceLocation id = EnchantmentHelper.getEnchantmentId((Enchantment) (Object) this);
        if (id != null && Config.getWeight(id.toString()) == 0) cir.setReturnValue(false);
    }
}
