package net.louis.overhaulmod.item.custom;

import net.louis.overhaulmod.component.ModComponents;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class FilledParticleOrb extends Item {

    public FilledParticleOrb(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemCooldownManager cooldownManager = user.getItemCooldownManager();
        ItemStack orb = user.getStackInHand(hand);

        if (orb.getOrDefault(ModComponents.ORB_PARTICLE_EFFECT, null) == null || cooldownManager.isCoolingDown(orb)) return ActionResult.FAIL;

        cooldownManager.set(orb, 20);
        Vec3d eyePos = user.getEyePos();
        Vec3d lookVec = user.getRotationVec(1.0F);
        Vec3d spawnPos = eyePos.add(lookVec.multiply(1.5));

        for (int i = 0; i < 20; i++) {
            double offsetX = (world.random.nextDouble() - 0.5) * 0.5;
            double offsetY = (world.random.nextDouble() - 0.5) * 0.5;
            double offsetZ = (world.random.nextDouble() - 0.5) * 0.5;

            world.addParticle(
                    (ParticleEffect) orb.get(ModComponents.ORB_PARTICLE_EFFECT),
                    spawnPos.x + offsetX,
                    spawnPos.y + offsetY,
                    spawnPos.z + offsetZ,
                    0, 0, 0
            );
        }

        return ActionResult.SUCCESS_SERVER;
    }

    @Override
    public boolean hasGlint(ItemStack stack) { return true; }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.getOrDefault(ModComponents.ORB_SHERD_NAME, null) == null) return;

        String newTT = "Sherd Consumed: " + stack.get(ModComponents.ORB_SHERD_NAME);

        tooltip.add(Text.literal(newTT).formatted(Formatting.DARK_AQUA));
        super.appendTooltip(stack, context, tooltip, type);
    }

}
