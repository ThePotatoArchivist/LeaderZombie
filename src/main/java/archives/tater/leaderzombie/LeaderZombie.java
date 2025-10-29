package archives.tater.leaderzombie;

import net.fabricmc.api.ModInitializer;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeaderZombie implements ModInitializer {
	public static final String MOD_ID = "leaderzombie";

    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static SoundEvent sound(String path) {
        var id = id(path);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static final SoundEvent AMBIENT = sound("entity.zombie.leader.ambient");
    public static final SoundEvent HURT = sound("entity.zombie.leader.hurt");
    public static final SoundEvent DEATH = sound("entity.zombie.leader.death");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}