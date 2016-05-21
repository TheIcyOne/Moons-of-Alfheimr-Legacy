package alfheimrsmoons.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class WorldGenAMBigTree extends WorldGenBigTree {
    private final Block wood;
    private final Block leaves;

    public WorldGenAMBigTree(boolean notify, Block wood, Block leaves) {
        super(notify);
        this.wood = wood;
        this.leaves = leaves;
    }

    @Override
    public void generateLeafNode(BlockPos pos) {
        for (int i = 0; i < leafDistanceLimit; ++i) {
            func_181631_a(pos.up(i), leafSize(i), leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
        }
    }

    @Override
    public void generateTrunk() {
        BlockPos pos = basePos;
        BlockPos upPos = basePos.up(height);
        Block block = wood;
        func_175937_a(pos, upPos, block);

        if (trunkSize == 2) {
            func_175937_a(pos.east(), upPos.east(), block);
            func_175937_a(pos.east().south(), upPos.east().south(), block);
            func_175937_a(pos.south(), upPos.south(), block);
        }
    }

    @Override
    public void generateLeafNodeBases() {
        for (WorldGenBigTree.FoliageCoordinates coords : field_175948_j) {
            int i = coords.func_177999_q();
            BlockPos pos = new BlockPos(basePos.getX(), i, basePos.getZ());

            if (!pos.equals(coords) && leafNodeNeedsBase(i - basePos.getY())) {
                func_175937_a(pos, coords, wood);
            }
        }
    }
}
