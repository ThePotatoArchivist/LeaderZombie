package archives.tater.leaderzombie;

import net.fabricmc.api.ClientModInitializer;

import me.pajic.zombieimprovements.Main;

public class LeaderZombieClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
        Main.CONFIG.leaderRedAura.accept(false);
	}
}