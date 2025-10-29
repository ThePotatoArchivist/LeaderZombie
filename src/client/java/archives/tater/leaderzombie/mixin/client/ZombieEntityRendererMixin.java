package archives.tater.leaderzombie.mixin.client;

import archives.tater.leaderzombie.LeaderZombie;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.client.render.entity.ZombieBaseEntityRenderer;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

import me.pajic.zombieimprovements.util.ZombieExtension;

@Mixin(ZombieBaseEntityRenderer.class)
public class ZombieEntityRendererMixin {
    @ModifyReturnValue(
            method = "getTexture(Lnet/minecraft/entity/mob/ZombieEntity;)Lnet/minecraft/util/Identifier;",
            at = @At("RETURN")
    )
	private Identifier init(Identifier original, @Local(argsOnly = true) ZombieEntity zombie) {
        return ((ZombieExtension) zombie).zi$isLeader() ? LEADER_TEXTURE : original;
    }

    @Unique
    private static final Identifier LEADER_TEXTURE = LeaderZombie.id("textures/entity/leader_zombie.png");
}