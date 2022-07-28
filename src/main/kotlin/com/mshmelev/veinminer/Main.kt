package com.mshmelev.veinminer

//import com.mshmelev.veinminer.commands.GivePickaxeCommand
import com.mshmelev.veinminer.listeners.BlockBreakEvent
import com.mshmelev.veinminer.recipies.VeinMinerPickaxe
//import dev.jorel.commandapi.CommandAPI
//import dev.jorel.commandapi.CommandAPIConfig
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin


class Main: JavaPlugin() {
    override fun onLoad() {
        //CommandAPI.onLoad(CommandAPIConfig().verboseOutput(true)) //Load with verbose output

        //GivePickaxeCommand.givePick()

        Bukkit.addRecipe(VeinMinerPickaxe(this).basicPickRecipe())

    }

    override fun onEnable() {
        server.pluginManager.registerEvents(BlockBreakEvent(), this)

        //CommandAPI.onEnable(this);
    }

    override fun onDisable() {
        super.onDisable()
    }
}