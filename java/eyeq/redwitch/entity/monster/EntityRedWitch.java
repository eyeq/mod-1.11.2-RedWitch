package eyeq.redwitch.entity.monster;

import eyeq.util.world.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.google.common.collect.ImmutableMap;

public class EntityRedWitch extends EntityWitch {
    public EntityRedWitch(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(world.isRemote) {
            return;
        }
        if(!world.getGameRules().getBoolean("mobGriefing")) {
            return;
        }
        if(rand.nextInt(500) != 0) {
            return;
        }
        BlockPos pos = this.getPosition().add(rand.nextInt(17) - 8, rand.nextInt(3), rand.nextInt(17) - 8);
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if(block == Blocks.AIR) {
            if(Blocks.REDSTONE_TORCH.canPlaceBlockAt(world, pos)) {
                world.setBlockState(pos, Blocks.REDSTONE_TORCH.getDefaultState(), 4);
                world.markBlockRangeForRenderUpdate(pos, pos);
                SoundType soundType = block.getSoundType(state, world, pos, this);
                WorldUtils.playSoundBlocks(world, null, pos, soundType);
            }
        } else {
            ImmutableMap immutable = state.getProperties();
            if(immutable.containsKey(BlockDoor.OPEN)) {
                world.setBlockState(pos, state.cycleProperty(BlockDoor.OPEN));
                world.markBlockRangeForRenderUpdate(pos, pos);
                float f = state.getValue(BlockDoor.OPEN) ? 0.6F : 0.5F;
                world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            } else if(immutable.containsKey(BlockDoor.POWERED)) {
                world.setBlockState(pos, state.cycleProperty(BlockDoor.POWERED));
                world.markBlockRangeForRenderUpdate(pos, pos);
                float f = state.getValue(BlockDoor.POWERED) ? 0.6F : 0.5F;
                world.playSound(null, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 0.3F, f);
            }
        }
    }
}
