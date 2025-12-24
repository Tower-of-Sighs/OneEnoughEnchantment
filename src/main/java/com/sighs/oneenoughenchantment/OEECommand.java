package com.sighs.oneenoughenchantment;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerPlayer;

public class OEECommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> BiomeCommand =
                Commands.literal("oee")
                        .requires(source -> source.hasPermission(0));

        BiomeCommand.then(Commands.literal("hand").executes(context -> {
            ServerPlayer player = context.getSource().getPlayer();
            if (player != null) {
                String result = Utils.getHandEnchantment(player);
                sendBasicCopyMessage(player, Component.translatable("message.oee.copy").getString() + " [...]", result);
            }
            return 1;
        }));

        dispatcher.register(BiomeCommand);
    }

    public static void sendBasicCopyMessage(ServerPlayer player, String displayText, String copyText) {
        MutableComponent message = Component.literal(displayText)
                .withStyle(Style.EMPTY
                        .withColor(ChatFormatting.AQUA)
                        .withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, copyText))
                        .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                Component.translatable("message.oee.copy")))
                );

        player.sendSystemMessage(message);
    }
}
