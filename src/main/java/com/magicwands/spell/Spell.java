package com.magicwands.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface Spell {

	public void cast(BlockPos target, World world, PlayerEntity user);

}
