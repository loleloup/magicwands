package com.magicwands.spell;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThunderSpell implements Spell{

	@Override
	public void cast(BlockPos target, World world, PlayerEntity user) {
		LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
		lightningBolt.setPosition(target.toCenterPos());
		world.spawnEntity(lightningBolt);
	}

}
