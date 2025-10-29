package archives.tater.leaderzombie.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;

import me.pajic.zombieimprovements.util.ZombieExtension;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(
            method = "scale",
            at = @At("TAIL")
    )
    private <T extends LivingEntity> void scaleLeader(T entity, MatrixStack matrices, float amount, CallbackInfo ci) {
        if (entity instanceof ZombieExtension zombie && zombie.zi$isLeader())
            matrices.scale(1.125F, 1.125F, 1.125F);
    }
}
