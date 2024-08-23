package com.magicwands.spell;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


public class ExplosionSpell implements Spell{

	@Override
	public void cast(BlockPos target, World world, PlayerEntity user) {
		FireballEntity fireball = new FireballEntity(EntityType.FIREBALL, world);
		Vec3d direction = target.toCenterPos().subtract(user.getPos()).normalize();
		fireball.setPosition(user.getPos().add(direction));  //start the fireball 1 unit ion front of the player
		fireball.setVelocity(direction);
		world.spawnEntity(fireball);
	}

}

