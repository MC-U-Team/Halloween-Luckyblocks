package info.u_team.to_u_team_core;

import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.*;

public class LazySpawnEggListener {
	
	private static void setup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			LazySpawnEggItem.getLazyEggs().forEach((type, item) -> {
				item.typeIn = type.getValue();
				DispenserBlock.registerDispenseBehavior(item, (source, stack) -> {
					final Direction direction = source.getBlockState().get(DispenserBlock.FACING);
					final EntityType<?> entityType = ((LazySpawnEggItem) stack.getItem()).getType(stack.getTag());
					entityType.spawn(source.getWorld(), stack, (PlayerEntity) null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
					stack.shrink(1);
					return stack;
				});
			});
		});
	}
	
	private static void colorItem(ColorHandlerEvent.Item event) {
		LazySpawnEggItem.getLazyEggs().forEach((type, item) -> {
			event.getItemColors().register((stack, color) -> {
				return item.getColor(color);
			}, item);
		});
	}
	
	private static void loadComplete(FMLLoadCompleteEvent event) {
		event.enqueueWork(() -> {
			LazySpawnEggItem.getLazyEggs().forEach((type, item) -> {
				LazySpawnEggItem.EGGS.put(type.getValue(), item);
			});
		});
	}
	
	public static void registerMod(IEventBus bus) {
		bus.addListener(LazySpawnEggListener::setup);
		bus.addListener(LazySpawnEggListener::colorItem);
		bus.addListener(LazySpawnEggListener::loadComplete);
	}
}
