package com.mshmelev.veinminer.listeners

import com.mshmelev.veinminer.recipies.VeinMinerPickaxe
import com.mshmelev.veinminer.util.BlockFinder
import com.mshmelev.veinminer.util.MiningContext
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BlockBreakEvent : Listener {
    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        val pickaxe = VeinMinerPickaxe.basicpick

        val block = event.block
        val blockloc = block.location

        if (event.player.inventory.itemInMainHand == pickaxe) {
            Bukkit.getServer()
                .broadcast(Component.text("${blockloc.blockX}, ${blockloc.blockY}, ${blockloc.blockZ} | ${block.type}"))

            BlockFinder().iterate(block, MiningContext(event.player, 100))

        }
    }

}