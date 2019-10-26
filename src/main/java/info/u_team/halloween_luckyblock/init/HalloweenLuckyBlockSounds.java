package info.u_team.halloween_luckyblock.init;

import java.lang.reflect.Field;
import java.util.ArrayList;

import info.u_team.halloween_luckyblock.HalloweenLuckyBlockConstants;
import info.u_team.u_team_core.registry.SoundRegistry;
import info.u_team.u_team_core.util.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.loading.moddiscovery.ModAnnotation.EnumHolder;

public class HalloweenLuckyBlockSounds {
	
	public static final SoundCategory category = EnumHelperSoundCategory.addSoundCategory("halloween_sounds");
	
	public static SoundEvent basta, game_death, haha, happy_halloween, hi_im_chucky, one_two, organ, ringle, scubi_dabi, snivelling, strange_laughing, strange, tension, thunder, wind, wuhu, you_have;
	
	public static final ArrayList<SoundEvent> common_sounds = new ArrayList<>();
	
	public static void preinit() {
		load();
		SoundRegistry.register(HalloweenLuckyBlockConstants.MODID, RegistryUtil.getRegistryEntries(SoundEvent.class, HalloweenLuckyBlockSounds.class));
	}
	
	private static void load() {
		for (Field field : HalloweenLuckyBlockSounds.class.getDeclaredFields()) {
			try {
				if (SoundEvent.class.isAssignableFrom(field.getType())) {
					String name = field.getName();
					
					SoundEvent event = new SoundEvent(new ResourceLocation(HalloweenLuckyBlockConstants.MODID, name));
					event.setRegistryName(event.getSoundName());
					
					field.set(null, event);
					
					if (name != "organ" && name != "game_death") {
						common_sounds.add(event);
					}
				}
			} catch (Exception ex) {
				HalloweenLuckyBlockConstants.LOGGER.error("Failed loading soundevents {}", ex);
			}
		}
	}
}
