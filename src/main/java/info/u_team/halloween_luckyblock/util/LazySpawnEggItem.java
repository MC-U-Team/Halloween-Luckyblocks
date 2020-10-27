package info.u_team.halloween_luckyblock.util;

import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.Maps;

import net.minecraft.entity.EntityType;
import net.minecraft.item.*;

public class LazySpawnEggItem extends SpawnEggItem {
	
	private static final Map<Supplier<? extends EntityType<?>>, LazySpawnEggItem> LAZY_EGGS = Maps.newIdentityHashMap();
	
	public LazySpawnEggItem(Supplier<? extends EntityType<?>> entityType, int primaryColor, int secondaryColor, Properties builder) {
		super(null, primaryColor, secondaryColor, builder);
		LAZY_EGGS.put(entityType, this);
	}
	
	public static Map<Supplier<? extends EntityType<?>>, LazySpawnEggItem> getLazyEggs() {
		return LAZY_EGGS;
	}
	
}
