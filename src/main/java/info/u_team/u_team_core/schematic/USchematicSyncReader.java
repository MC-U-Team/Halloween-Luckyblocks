package info.u_team.u_team_core.schematic;

import java.io.*;
import java.util.function.BiConsumer;

import info.u_team.u_team_core.util.io.NBTStreamUtil;

/**
 * Schematic API<br>
 * -> Reader Sync
 * 
 * @author HyCraftHD
 * @date 21.10.2017
 */
public class USchematicSyncReader {
	
	private final USchematicLoadRegion region;
	private final InputStream stream;
	private BiConsumer<Boolean, Long> consumer;
	
	public USchematicSyncReader(USchematicLoadRegion region, File file) throws IOException {
		this(region, new FileInputStream(file));
	}
	
	public USchematicSyncReader(USchematicLoadRegion region, InputStream stream) throws IOException {
		this.region = region;
		this.stream = stream;
	}
	
	public USchematicSyncReader start() {
		startLoader();
		return this;
	}
	
	public USchematicSyncReader finished(BiConsumer<Boolean, Long> consumer) {
		this.consumer = consumer;
		return this;
	}
	
	private void startLoader() {
		boolean success = true;
		final long time = System.currentTimeMillis(); // Time measurement
		
		try {
			region.readNBT(NBTStreamUtil.readNBTFromStream(stream));
		} catch (final IOException ex) {
			System.err.println("Error while trying to load schematic region.");
			ex.printStackTrace();
			success = false;
		}
		
		if (consumer != null) {
			consumer.accept(success, System.currentTimeMillis() - time);
		}
	}
	
}
