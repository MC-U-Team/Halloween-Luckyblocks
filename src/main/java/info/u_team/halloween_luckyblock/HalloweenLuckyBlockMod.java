package info.u_team.halloween_luckyblock;

import info.u_team.u_team_core.util.annotation.AnnotationManager;
import info.u_team.u_team_core.util.verify.JarSignVerifier;
import net.minecraftforge.fml.common.Mod;

@Mod(HalloweenLuckyBlockMod.MODID)
public class HalloweenLuckyBlockMod {
	
	public static final String MODID = "halloween_luckyblock";
	
	public HalloweenLuckyBlockMod() {
		JarSignVerifier.checkSigned(MODID);
		
		AnnotationManager.callAnnotations(MODID);
	}
	
}
