package info.u_team.halloween_luckyblock.init;

import java.lang.reflect.Field;
import java.util.ArrayList;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockMod;
import info.u_team.u_team_core.soundevent.USoundEvent;
import info.u_team.u_team_core.util.registry.BaseRegistryUtil;
import net.minecraft.util.*;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = HalloweenLuckyBlockMod.MODID, bus = Bus.MOD)
public class HalloweenLuckyBlockSounds {
	
	public static final SoundCategory CATEGORY = SoundCategory.HOSTILE;// EnumHelperSoundCategory.addSoundCategory("halloween_sounds");
	
	public static SoundEvent BASTA, GAME_DEATH, HAHA, HAPPY_HALLOWEEN, HI_IM_CHUCKY, ONE_TWO, ORGAN, RINGLE, SCUBI_DABI, SNIVELLING, STRANGE_LAUGHING, STRANGE, TENSION, THUNDER, WIND, WUHU, YOU_HAVE;
	
	public static final ArrayList<SoundEvent> COMMON_SOUNDS = new ArrayList<>();
	
	@SubscribeEvent
	public static void register(Register<SoundEvent> event) {
		load();
		BaseRegistryUtil.getAllRegistryEntriesAndApplyNames(HalloweenLuckyBlockMod.MODID, SoundEvent.class).forEach(event.getRegistry()::register);
	}
	
	private static void load() {
		for (Field field : HalloweenLuckyBlockSounds.class.getDeclaredFields()) {
			try {
				if (SoundEvent.class.isAssignableFrom(field.getType())) {
					String name = field.getName().toLowerCase();
					
					USoundEvent event = new USoundEvent(new ResourceLocation(HalloweenLuckyBlockMod.MODID, name));
					
					field.set(null, event);
					
					if (name != "organ" && name != "game_death") {
						COMMON_SOUNDS.add(event);
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
