# One Enough Enchantment

This mod allows you to dynamically control the weight of enchantments or even completely remove enchantments from the game.

### Weight Control

For the vanilla enchantment weight mechanics, refer to:  
https://zh.minecraft.wiki/w/%E9%99%84%E9%AD%94%EF%BC%88%E7%89%A9%E5%93%81%E4%BF%AE%E9%A5%B0%EF%BC%89  
https://minecraft.wiki/w/Enchanting_mechanics

In short, weight determines the frequency of enchantment appearance. In vanilla Minecraft, weight is controlled by four fixed rarities: COMMON (weight 10), UNCOMMON (5), RARE (2), and VERY_RARE (1).

After version 1.21, enchantments became data-driven, and weights can be modified, but this is still not convenient enough.

This mod provides an in-game method to dynamically control enchantment weights and allows setting the weight to 0 to completely remove an enchantment.

In the configuration file, you can input formatted rules in the "rules" field to control the refresh weight. For example, entering `["minecraft:knockback=100", "minecraft:sharpness=0"]` will:
- Greatly increase the probability of the Knockback enchantment appearing.
- Completely remove the Sharpness enchantment from the game.
- Other enchantments remain unaffected and use their default weights.

The mod also integrates with KubeJS events for dynamic weight modification, which can be combined with game stages or other settings.
```javascript
OEEEvent.modifyWeight(event => {
    event.getWeight("minecraft:sharpness");
    event.setWeight("minecraft:knockback", 0);
});
```

### Removing Enchantments

By default, enchantments with weight set to 0 by this mod will not appear in the game in any form.

The following configuration options are enabled by default. You can disable them selectively if you want to retain some functionality.

**Configuration option dropRecipe:**
- For enchantments with weight 0, associated recipes (such as anvil recipes) are removed. This means the corresponding enchanted books cannot be used, but enchantments can still be applied via commands.
- In JEI, the corresponding recipes (e.g., anvil recipes) will not be queryable.

**Configuration option deepDelete:**
- For enchantments with weight 0, even if you force-enchant an item via commands, the enchantment data cannot be parsed (though the NBT is preserved). It can be restored by adjusting the weight back.
- In JEI or the creative inventory, the corresponding enchanted books are removed and cannot be queried at all.

### Other

- Theoretically, this mod can be installed only on the server side.
- Use the `/oee all` command to view information about all enchantments, including enchantment ID, localized name, and weight.
- If you remove many COMMON-rarity enchantments, the enchantment table at low levels might not show any enchantments. This is due to the vanilla mechanism and is not a bug in this mod.