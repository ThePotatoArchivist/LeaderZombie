package archives.tater.leaderzombie.mixin;

import archives.tater.leaderzombie.LeaderZombie;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import me.pajic.zombieimprovements.util.AttachmentUtil;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends HostileEntity {
    @Shadow
    @Final
    private static Identifier LEADER_ZOMBIE_BONUS_MODIFIER_ID;

    protected ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieEntity;setCanBreakDoors(Z)V"), method = "applyAttributeModifiers")
	private void init(CallbackInfo info) {
        getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).overwritePersistentModifier(new EntityAttributeModifier(LEADER_ZOMBIE_BONUS_MODIFIER_ID, this.random.nextDouble() * (double)1.5F + (double)1.0F, Operation.ADD_VALUE));
	}

    @ModifyReturnValue(
            method = "getAmbientSound",
            at = @At("RETURN")
    )
    private SoundEvent modifyAmbientSound(SoundEvent original) {
        return AttachmentUtil.isLeader(this) ? LeaderZombie.AMBIENT : original;
    }

    @ModifyReturnValue(
            method = "getHurtSound",
            at = @At("RETURN")
    )
    private SoundEvent modifyHurtSound(SoundEvent original) {
        return AttachmentUtil.isLeader(this) ? LeaderZombie.HURT : original;
    }

    @ModifyReturnValue(
            method = "getDeathSound",
            at = @At("RETURN")
    )
    private SoundEvent modifyDeathSound(SoundEvent original) {
        return AttachmentUtil.isLeader(this) ? LeaderZombie.DEATH : original;
    }
}