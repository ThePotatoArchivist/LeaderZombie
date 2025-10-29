package archives.tater.leaderzombie.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

import me.pajic.zombieimprovements.util.ZombieExtension;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyReturnValue(
            method = "getDefaultName",
            at = @At("RETURN")
    )
    private Text addLeaderToName(Text original) {
        return this instanceof ZombieExtension zombie && zombie.zi$isLeader() ? Text.translatable("entity.minecraft.zombie.leaderzombie.leader", original) : original;
    }
}
