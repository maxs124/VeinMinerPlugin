package com.mshmelev.veinminer.util

import org.bukkit.Material
import org.bukkit.entity.Player

data class BaseBlock(
    val x: Int,
    val y: Int,
    val z: Int,
    var type: Material,
    var player : Player
)