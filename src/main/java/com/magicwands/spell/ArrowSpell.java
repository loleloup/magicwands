package com.magicwands.spell;

import java.util.Random;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class ArrowSpell implements Spell{

	@Override
	public void cast(BlockPos target, World world, PlayerEntity user) {
		Vec3d direction = new Vec3d(0, -1, 0);	//straight down
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			ArrowEntity arrow = new ArrowEntity(EntityType.ARROW, world);
			arrow.setVelocity(direction);
			//TODO : make the random position a polar coordinate so we can control the range better
			double x = (random.nextDouble() - 0.5) * 10;	//-0.5, we want them around the target
			double z = (random.nextDouble() - 0.5) * 10;
			arrow.setPosition(target.getX() + x, target.getY() - direction.getY() * 10, target.getZ() + z);	//randomized position in an area around the target
			world.spawnEntity(arrow);
		}
		
		
		
	}

}

