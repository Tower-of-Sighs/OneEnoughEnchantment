package com.sighs.oneenoughenchantment.compat;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;

public interface EnchantmentEvents {

    EventGroup GROUP = EventGroup.of("OEEEvents");

    EventHandler WEIGHT = GROUP.server("modifyWeight", () -> WeightModifyEventJS.class);

}
