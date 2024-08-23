package com.magicwands;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class EntityAttributes {
	public static final RegistryEntry<EntityAttribute> WAND_MAX_RANGE = register(
		"wand.max_range", new ClampedEntityAttribute("attribute.name.wand.max_range", 20.0, 1.0, 64.0).setTracked(true)
	);
	public static final RegistryEntry<EntityAttribute> WAND_MANA_COST = register(
		"wand.mana_cost", new ClampedEntityAttribute("attribute.name.wand.mana_cost", 20.0, 1.0, 64.0).setTracked(true)
	);
	
	private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
		return Registry.registerReference(Registries.ATTRIBUTE, Identifier.ofVanilla(id), attribute);
	};
	
};