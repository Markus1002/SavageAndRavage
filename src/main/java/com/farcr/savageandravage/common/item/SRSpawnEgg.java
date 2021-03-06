package com.farcr.savageandravage.common.item;

import java.util.function.Supplier;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

// Remove this once forge implements a way to add entity spawn eggs without this thing.
//Imported from Buzzier Bees
public class SRSpawnEgg extends SpawnEggItem {
	 private final Supplier<EntityType<?>> entity;

	   public SRSpawnEgg(Supplier<EntityType<?>> entityTypeIn, int primaryColorIn, int secondaryColorIn, Item.Properties builder) {
	      super(null, primaryColorIn, secondaryColorIn, builder);
	      entity = entityTypeIn;
	   }

	   @Override
	   public EntityType<?> getType(CompoundNBT compound) {
	      if (compound != null && compound.contains("EntityTag", 10)) {
	         CompoundNBT entityTag = compound.getCompound("EntityTag");

	         if (entityTag.contains("id", 8)) {
	            return EntityType.byKey(entityTag.getString("id")).orElse(entity.get());
	         }
	      }
	      return this.entity.get();
	   }
}
