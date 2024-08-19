package com.magicwands.items;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magicwands.mixin.MixinPlayerEntity;
import com.magicwands.playermana.SpellcastCallback;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext;

public class WandItem extends ToolItem{

	public WandItem(ToolMaterial toolmaterial, Item.Settings settings) {
		super(toolmaterial, settings);
		// TODO Auto-generated constructor stub
	}
	
	public static final Logger LOGGER = LoggerFactory.getLogger("magicwands");
	
	
	@Override
	public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
		return !miner.isCreative();
	}

	@Override
	public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		return true;
	}

	@Override
	public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.damage(1, attacker, EquipmentSlot.MAINHAND);
	}
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		// Ensure we don't spawn the lightning only on the client.
		// This is to prevent desync.
		if (world.isClient) {
			return TypedActionResult.pass(user.getStackInHand(hand));
		}
		
	    

		if (!this.decreasePlayerMana(user)) {
			return TypedActionResult.fail(user.getStackInHand(hand));
		}
		
		
		BlockPos frontOfPlayer = this.getTarget(world, user).getBlockPos();
		
		
		// Spawn the lightning bolt.
		LightningEntity lightningBolt = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
		lightningBolt.setPosition(frontOfPlayer.toCenterPos());
		world.spawnEntity(lightningBolt);
		
		this.decreasePlayerMana(user);

		// Nothing has changed to the item stack,
		// so we just return it how it was.
		return TypedActionResult.success(user.getStackInHand(hand));
	}
	
	public BlockHitResult getTarget(World world, PlayerEntity user) {
		//some kind of generic targetting method using pixel raycast and returning the entity? pos?
		Vec3d origin = user.getEyePos();
		Vec3d looking_direction = user.getRotationVec(1.0f).normalize();
		LOGGER.info(looking_direction.toString());
		Vec3d dest = origin.add(looking_direction.multiply(10));
		
		return world.raycast(new RaycastContext(origin, dest, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user));
	}
	
	public boolean decreasePlayerMana(PlayerEntity user) {
		//decrease the player mana by the wand's mana attribute
		LOGGER.info("decrease mana for user:");
		LOGGER.info(user.toString());
		ActionResult result = SpellcastCallback.EVENT.invoker().interact(user, 9);
		return result.isAccepted();
	}

}
