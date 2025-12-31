package com.sighs.oneenoughenchantment.compat;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;

public class OEEJSPlugin implements KubeJSPlugin {

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(EnchantmentEvents.GROUP);
    }
}
