package com.mshmelev.veinminer.commands
//
//import com.mshmelev.veinminer.recipies.VeinMinerPickaxe
//import com.mshmelev.veinminer.util.Pickaxes
//import dev.jorel.commandapi.CommandAPICommand
//import dev.jorel.commandapi.CommandPermission
//import dev.jorel.commandapi.arguments.GreedyStringArgument
//import dev.jorel.commandapi.arguments.StringArgument
//import dev.jorel.commandapi.executors.CommandExecutor
//import net.kyori.adventure.text.Component
//import org.bukkit.Bukkit
//import org.bukkit.command.CommandSender
//import org.bukkit.entity.Player
//
//object GivePickaxeCommand {
//    fun myCmd() = CommandAPICommand("broadcastmsg").apply {
//        withArguments(GreedyStringArgument("message"))
//        withAliases("broadcast", "broadcastmessage")
//        withPermission(CommandPermission.OP)
//        executes(CommandExecutor { sender: CommandSender?, args: Array<Any?> ->
//            val message = args[0] as String?
//            Bukkit.getServer().broadcast(Component.text(message!!))
//        }).register()
//    }
//
//    fun givePick() = CommandAPICommand("givepick").apply {
//        withArguments(StringArgument("pick"))
//        withAliases("gp")
//        withPermission(CommandPermission.OP)
//        executes(CommandExecutor { sender: CommandSender?, args: Array<Any?> ->
//            val pick = args[0] as String?
//            val player = sender as Player
//
//            if (pick in Pickaxes.BASICPICKNAMES) {
//                 player.inventory.addItem(VeinMinerPickaxe.basicpick)
//            }
//
//        }).register()
//    }
//}