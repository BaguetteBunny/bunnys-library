package bunny.lib.cauldron.custom;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import bunny.lib.config.ModConfig;

public class DragonBreathCauldronBlock extends LeveledCauldronBlock {
    public static final CauldronBehavior.CauldronBehaviorMap DB_CAULDRON_BEHAVIOR = CauldronBehavior.createMap("dragons_breath_cauldron_behavior");

    public DragonBreathCauldronBlock(Settings settings) {
        super(Biome.Precipitation.NONE, DB_CAULDRON_BEHAVIOR, settings);
        setDefaultState(getDefaultState().with(LEVEL, 1));
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world.isClient()) return;
        int level = state.get(LEVEL);

        if (entity instanceof ItemEntity item && item.getStack().getItem() instanceof PotionItem potion && potion != Items.LINGERING_POTION  && ModConfig.INSTANCE.enableLingeringTransform) {
            if (world.getRandom().nextBetweenExclusive(0, 5) == 1) {
                if (level > 1) world.setBlockState(pos, state.with(LEVEL, level - 1));
                else world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.BLOCKS, 1.0F, 1.5F);
            } else {
                world.playSound(null, pos, SoundEvents.ENTITY_PLAYER_SPLASH_HIGH_SPEED, SoundCategory.BLOCKS, 1.0F, 2.0F);
            }
            ItemStack newPotion = new ItemStack(Items.LINGERING_POTION, item.getStack().getCount());
            newPotion.set(DataComponentTypes.POTION_CONTENTS, item.getStack().getComponents().get(DataComponentTypes.POTION_CONTENTS));

            ItemEntity newPotionEntity = new ItemEntity(world, item.getX(), item.getY(), item.getZ(), newPotion);
            item.remove(Entity.RemovalReason.DISCARDED);
            world.spawnEntity(newPotionEntity);
        }

        if (entity instanceof LivingEntity living && ModConfig.INSTANCE.enableCurseClensing) {
            Iterable<ItemStack> armors = living.getAllArmorItems();
            for (ItemStack armor : armors) {
                if (level <= 0) break;
                ItemEnchantmentsComponent enchants = armor.get(DataComponentTypes.ENCHANTMENTS);
                if (enchants == null) enchants = ItemEnchantmentsComponent.DEFAULT;

                ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);

                for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : enchants.getEnchantmentEntries()) {
                    if (level <= 0) break;
                    RegistryEntry<Enchantment> enchantmentEntry = entry.getKey();
                    int enchantLevel = entry.getIntValue();

                    if (!enchantmentEntry.isIn(EnchantmentTags.CURSE)) {
                        builder.add(enchantmentEntry, enchantLevel);
                    } else {
                        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0F, 2.0F);
                        if (level > 1) world.setBlockState(pos, state.with(LEVEL, level - 1));
                        else world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());

                        if (world instanceof ServerWorld serverWorld) {
                            serverWorld.spawnParticles(
                                    ParticleTypes.DRAGON_BREATH,
                                    pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5,
                                    10, 0.3, 0.2, 0.3, 0.1
                            );
                        }
                    }
                }
                armor.set(DataComponentTypes.ENCHANTMENTS, builder.build());
            }
        }
        super.onEntityCollision(state, world, pos, entity);
    }


    @Override
    protected ActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        CauldronBehavior cauldronBehavior = (CauldronBehavior) this.behaviorMap.map().get(stack.getItem());
        ItemEnchantmentsComponent enchants = stack.get(DataComponentTypes.ENCHANTMENTS);
        if (world.isClient || enchants == null) return cauldronBehavior.interact(state, world, pos, player, hand, stack);
        int level = state.get(LEVEL);

        ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
        for (Object2IntMap.Entry<RegistryEntry<Enchantment>> entry : enchants.getEnchantmentEntries()) {
            if (level <= 0) break;
            RegistryEntry<Enchantment> enchantmentEntry = entry.getKey();
            int enchantLevel = entry.getIntValue();

            if (!enchantmentEntry.isIn(EnchantmentTags.CURSE)) {
                builder.add(enchantmentEntry, enchantLevel);
            } else {
                player.swingHand(hand);
                world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_SPLASH, SoundCategory.BLOCKS, 1.0F, 2.0F);
                if (level > 1) world.setBlockState(pos, state.with(LEVEL, level - 1));
                else world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());

                if (world instanceof ServerWorld serverWorld) {
                    serverWorld.spawnParticles(
                            ParticleTypes.DRAGON_BREATH,
                            pos.getX() + 0.5, pos.getY() + 0.9, pos.getZ() + 0.5,
                            10, 0.3, 0.2, 0.3, 0.1
                    );
                }
            }
        }
        stack.set(DataComponentTypes.ENCHANTMENTS, builder.build());
        return cauldronBehavior.interact(state, world, pos, player, hand, stack);
    }
}