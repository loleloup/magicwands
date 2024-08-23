package com.magicwands.item;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public enum WandMaterials implements ToolMaterial {
	WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 0.0F, 1.0F, 15, () -> Ingredient.fromTag(ItemTags.PLANKS)),
	BLAZE(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 131, 0.0F, 2.0F, 15, () -> Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)),
	BREEZE(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 250, 0.0F, 3.0F, 15, () -> Ingredient.ofItems(Items.IRON_INGOT));
	
	private final TagKey<Block> inverseTag;
	private final int itemDurability;
	private final float miningSpeed;
	private final float attackDamage;
	private final int enchantability;
	private final Supplier<Ingredient> repairIngredient;
	
	private WandMaterials(
		final TagKey<Block> inverseTag,
		final int itemDurability,
		final float miningSpeed,
		final float attackDamage,
		final int enchantability,
		final Supplier<Ingredient> repairIngredient
	) {
		this.inverseTag = inverseTag;
		this.itemDurability = itemDurability;
		this.miningSpeed = miningSpeed;
		this.attackDamage = attackDamage;
		this.enchantability = enchantability;
		this.repairIngredient = Suppliers.memoize(repairIngredient::get);
	}

	@Override
	public int getDurability() {
		return this.itemDurability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return this.miningSpeed;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public TagKey<Block> getInverseTag() {
		return this.inverseTag;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return (Ingredient)this.repairIngredient.get();
	}


}
