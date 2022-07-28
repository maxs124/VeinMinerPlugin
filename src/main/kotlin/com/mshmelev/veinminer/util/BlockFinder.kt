package com.mshmelev.veinminer.util

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class BlockFinder {
    private var maxBlocks = 500

    tailrec fun iterate(currBlocks: List<Block>, ctx: MiningContext) {
        val matchedBlocks = mutableListOf<Block>()
        currBlocks.forEach { currBlock ->
            for (x1 in currBlock.x - 1..currBlock.x + 1) {
                for (y1 in currBlock.y - 1..currBlock.y + 1) {
                    for (z1 in currBlock.z - 1..currBlock.z + 1) {
                        ctx.player.world.getBlockAt(x1, y1, z1).let { testBlock ->
                            println("CurrBlock: ${currBlock.type} | ${currBlock.x}, ${currBlock.y}, ${currBlock.z}")
                            println("TestBlock: ${testBlock.type} | ${testBlock.x}, ${testBlock.y}, ${testBlock.z}")
                            if (testBlock.type == currBlock.type) {
                                if (testBlock.type != Material.AIR) {
                                    println("Types matched!!!")
                                    val minedItem = ItemStack(currBlock.type)
                                    matchedBlocks.add(testBlock)
                                    matchedBlocks.remove(currBlock)
                                    testBlock.type = Material.AIR
                                    ctx.player.inventory.addItem(minedItem)
                                    maxBlocks--
                                } else {
                                    println("air")
                                }
                            } else {
                                println("Types did NOT match...")
                            }
                        }
                    }
                }
            }
        }
        if (maxBlocks <= 0) {
            iterate(matchedBlocks, ctx)
        } else {
            return
        }
        //iterate(currBlocks, ctx)
    }

}

data class MiningContext(
    val player: Player
)