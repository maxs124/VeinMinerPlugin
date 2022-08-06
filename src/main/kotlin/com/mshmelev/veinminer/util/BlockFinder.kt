package com.mshmelev.veinminer.util

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class BlockFinder {
    fun iterate(currBlock: Block, ctx: MiningContext) {
        val originalBlockType = currBlock.type
        if (originalBlockType == Material.AIR) return
        val blockQueue = mutableListOf(currBlock)
        val minedBlocks = mutableListOf<Block>()
        val relBlock: (Int, Int, Int, Block) -> Block = { x: Int, y: Int, z: Int, cur_block: Block ->
            ctx.player.world.getBlockAt(cur_block.x + x, cur_block.y + y, cur_block.z + z)
        }

        while (blockQueue.isNotEmpty()) {
            if (ctx.maxBlox < 0) break
            val tempBlock: Block = blockQueue.removeAt(0)
            ctx.maxBlox--
            for (x1 in -1..1) {
                for (y1 in -1..1) {
                    for (z1 in -1..1) {
                        if ((x1 == y1) and (y1 == z1) and (z1 == 0)) continue
                        val testBlock = relBlock(x1, y1, z1, tempBlock)
                        if (testBlock in minedBlocks) continue
                        if (testBlock.type == Material.AIR) continue
                        if (testBlock.type == originalBlockType && testBlock.type != Material.AIR) {
                            blockQueue.add(testBlock)
                            minedBlocks.add(testBlock)
                            ctx.player.inventory.addItem(ItemStack(testBlock.type))
                            testBlock.type = Material.AIR
                        }
                    }
                }
            }
        }
    }
}

data class MiningContext(
    val player: Player,
    var maxBlox: Int
)