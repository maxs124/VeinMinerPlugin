package com.mshmelev.veinminer.commands

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.GreedyStringArgument
import dev.jorel.commandapi.executors.CommandExecutor
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

object Broadcast {
    fun myCmd() = CommandAPICommand("broadcastmsg").apply {
        withArguments(GreedyStringArgument("message"))
        withAliases("broadcast", "broadcastmessage")
        withPermission(CommandPermission.OP)
        executes(CommandExecutor { sender: CommandSender?, args: Array<Any?> ->
            val message = args[0] as String?
            Bukkit.getServer().broadcast(Component.text(message!!))
        }).register()
    }
}