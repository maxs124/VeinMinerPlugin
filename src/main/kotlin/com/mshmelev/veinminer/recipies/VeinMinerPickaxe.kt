package com.mshmelev.veinminer.recipies

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.plugin.java.JavaPlugin

class VeinMinerPickaxe(val main: JavaPlugin) {
    companion object {
        val basicpick = ItemStack(Material.IRON_PICKAXE)
    }

    fun basicPickRecipe() : ShapedRecipe {
        val meta = basicpick.itemMeta
        meta.displayName(Component.text("§fBasic Vein Miner Pickaxe"))
        basicpick.itemMeta = meta
        val key = NamespacedKey(main, "basic_vein_miner_pickaxe")
        val recipe = ShapedRecipe(key, basicpick)

        recipe.shape(
            "IRI",
            " S ",
            " S "
        )

        recipe.setIngredient('R', Material.REDSTONE_BLOCK)
        recipe.setIngredient('I', Material.IRON_INGOT)
        recipe.setIngredient('S', Material.STICK)

        Bukkit.addRecipe(recipe)

        return recipe
    }
}