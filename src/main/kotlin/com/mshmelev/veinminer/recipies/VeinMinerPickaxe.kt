package com.mshmelev.veinminer.recipies

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin


class VeinMinerPickaxe(var main: JavaPlugin) {
    companion object {
        lateinit var basicpick: ItemStack
    }

    fun createBasicPick(): ShapedRecipe {
        val item = ItemStack(Material.IRON_PICKAXE)
        val meta = item.itemMeta
        meta.displayName(Component.text("§fBasic Vein Miner Pickaxe"))
        val key = NamespacedKey(main, "basic_vein_miner_pickaxe")

        val recipe = ShapedRecipe(key, item)

        recipe.shape(
            "IRI",
            " S ",
            " S "
        )

        recipe.setIngredient('R', Material.REDSTONE_BLOCK)
        recipe.setIngredient('I', Material.IRON_INGOT)
        recipe.setIngredient('S', Material.STICK)

        item.itemMeta = meta
        basicpick = item
        return recipe

    }
}