package com.mshmelev.veinminer.util

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.inventory.ItemStack


class BlockFinder2 {
    // maximum number of blocks searched
    private var maxBlocks = 500

    // maximum number of blocks mine-able
    private var maxMinedBlocks = 50

    //
    // Creates a list of blocks, that contains all the blocks that are of the same type that were mined (the original block).
    // Input: the current block that was mined, the player context
    // Output: nothing, blocks are broken and mined in the function
    //
    fun iterate(currBlock: Block, ctx: MiningContext) {
        // function pseudocode
        // blocks_queue = queue<Block>
        // while(blocks_queue is not empty)
        //   look at nearby blocks, (if maxBlocks > 0)
        //      if (block is of same type) add to blocks_queue else() continue to next block to check
        //      decrement maxBlocks
        //   remove current block, decrement mined block

        //getting the original block to check against
        val originalBlock: Block = currBlock
        if (originalBlock.type == Material.AIR) return // I really do not know how this would have happened, there are so many safeguards, I would literally be shocked if this were to happen.

        // creating a queue for the blocks to go into
        val blockQueue = mutableListOf(currBlock)

        //creating a lambda to find the close neighbors of a given block
        val rel_block: (Int, Int, Int, Block) -> Block = { x: Int, y: Int, z: Int, cur_block: Block ->
            ctx.player.world.getBlockAt(cur_block.x + x, cur_block.y + y, cur_block.z + z)
        }

        // beginning the queue loop
        while (blockQueue.isNotEmpty()) {
            println("Queue: $blockQueue")

            // if more blocks than the max have been mined, end the loop
            if (maxMinedBlocks < 0) break
            // dequeue from the queue
            val tempBlock: Block = blockQueue.removeAt(0)
            // if the block is air, just act like it never happened
            if (tempBlock.type != originalBlock.type) continue
            val minedItem = ItemStack(tempBlock.type)

            maxMinedBlocks--
            // giving the item to the player
            ctx.player.inventory.addItem(minedItem)

            // iterating through the nearby blocks (one away including diagonals, making it 26 blocks
            for (x1 in -1..1) {
                for (y1 in -1..1) {
                    for (z1 in -1..1) {
                        // skipping self in the check
                        if ((x1 == y1) and (y1 == z1) and (z1 == 0)) continue
                        // if too many blocks have been checked, stop
                        if (maxBlocks <= 0) return
                        // getting the relative block
                        val testBlock = rel_block(x1, y1, z1, tempBlock)
                        maxBlocks--

                        // if the test block is air, just discontinue this
                        println("TestBlock: ${testBlock.type} | ${testBlock.x}, ${testBlock.y}, ${testBlock.z}")
                        if (testBlock.type == Material.AIR) continue
                        // if the relative block is the same as the original block add it to the queue
                        if (testBlock.type == originalBlock.type) {
                            println("type matched!")
                            blockQueue.add(testBlock)
                        }


                    }
                }
            }
            println(blockQueue)
            blockQueue.forEach {
                println("${it.type} was deleted")
                it.type = Material.AIR
            }
            // deleting the current block
            //tempBlock.type = Material.AIR
            // end of loop, restart
        }


    }

}