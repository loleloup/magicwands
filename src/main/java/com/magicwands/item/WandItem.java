package com.magicwands.item;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.magicwands.mixin.MixinPlayerEntity;
import com.magicwands.playermana.SpellcastCallback;
import com.magicwands.spell.Spell;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
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

	private int max_range = 20;
	private int mana_cost = 20;
	private Spell spell;
	

	public WandItem(ToolMaterial toolmaterial, Item.Settings settings, Spell spell) {
		super(toolmaterial, settings);
		this.spell = spell;
		// TODO Auto-generated constructor stub
	}
	
	public int getTier() {
		switch (this.getMaterial()) {
		case WandMaterials.WOOD:
			return 1;
		case WandMaterials.BLAZE:
			return 2;
		case WandMaterials.BREEZE:
			return 4;	//power is doubled each time
		default:
			return 0;
		}
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
		
		LOGGER.info("yes {}", Registries.ITEM.get(BASE_ATTACK_SPEED_MODIFIER_ID));
		
	    

		if (!this.decreasePlayerMana(user)) {
			return TypedActionResult.fail(user.getStackInHand(hand));
		}
		
		
		BlockPos target = this.getTarget(world, user).getBlockPos();
		
		this.spell.cast(target, world, user);
		
		// Spawn the lightning bolt.
		
		
		// Nothing has changed to the item stack,
		// so we just return it how it was.
		return TypedActionResult.success(user.getStackInHand(hand));
	}
	
	public BlockHitResult getTarget(World world, PlayerEntity user) {
		//some kind of generic targetting method using pixel raycast and returning the entity? pos?
		Vec3d origin = user.getEyePos();
		Vec3d looking_direction = user.getRotationVec(1.0f).normalize();
		Vec3d dest = origin.add(looking_direction.multiply(this.max_range));
		
		return world.raycast(new RaycastContext(origin, dest, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, user));
	}
	
	public boolean decreasePlayerMana(PlayerEntity user) {
		//decrease the player mana by the wand's mana attribute
		ActionResult result = SpellcastCallback.EVENT.invoker().interact(user, this.mana_cost);
		return result.isAccepted();
	}

}
