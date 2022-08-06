package com.mshmelev.veinminer.commands

import com.mshmelev.veinminer.recipies.VeinMinerPickaxe
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.StringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GivePickaxeCommand {
    fun givePick() = CommandAPICommand("givepick").apply {
        withArguments(
            StringArgument("pick").replaceSuggestions(
                ArgumentSuggestions.strings(
                    "BASICPICK",
                    "REINFORCEDPICK",
                    "DIAMONDPICK"
                )
            )
        )
        withAliases("gp")
        withPermission(CommandPermission.OP)
        executes(CommandExecutor { sender: CommandSender?, args: Array<Any?> ->
            val pick = args[0] as String?
            val player = sender as Player

            if (pick.equals("BASICPICK", true)) {
                println("gave basic pick to player ${player.name}")

                player.inventory.addItem(VeinMinerPickaxe.basicpick)
            }
        }).register()
    }
}