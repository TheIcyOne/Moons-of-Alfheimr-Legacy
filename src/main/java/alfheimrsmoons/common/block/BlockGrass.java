package alfheimrsmoons.common.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.BlockSoil.EnumType;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockGrass extends Block {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);
    private String name;

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    public BlockGrass() {
        super(Material.ground);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.GRASSY));

        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        this.name = "grass";
        this.setUnlocalizedName(this.name);

        GameRegistry.registerBlock(this, ItemModMultiTexture.class, this.name);
        ((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() {
            @Nullable
            public String apply(ItemStack input) {
                return BlockSoil.EnumType.byMetadata(input.getItemDamage()).getName();
            }
        });

        this.setTickRandomly(true);
        setHardness(0.6F);
        setHarvestLevel("shovel", 0);
        setStepSound(soundTypeGrass);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            if (worldIn.getLightFromNeighbors(pos.up()) < 4 && worldIn.getBlockState(pos.up()).getBlock().getLightOpacity(worldIn, pos.up()) > 2) {
                worldIn.setBlockState(pos, ModBlocks.amSoil.getDefaultState().withProperty(VARIANT, state.getValue(VARIANT)));
            } else {
                if (worldIn.getLightFromNeighbors(pos.up()) >= 9) {
                    for (int i = 0; i < 4; ++i) {
                        BlockPos blockpos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);
                        Block block = worldIn.getBlockState(blockpos.up()).getBlock();
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);

                        if (iblockstate.getBlock() == ModBlocks.amSoil && iblockstate.getValue(VARIANT).equals(state.getValue(VARIANT))
                                && worldIn.getLightFromNeighbors(blockpos.up()) >= 4 && block.getLightOpacity(worldIn, blockpos.up()) <= 2) {
                            worldIn.setBlockState(blockpos, ModBlocks.amGrass.getDefaultState().withProperty(VARIANT, state.getValue(VARIANT)));
                        }
                    }
                }
            }
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockSoil.EnumType blockamsoil$enumtype : BlockSoil.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blockamsoil$enumtype.getMetadata()));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumType) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return ModBlocks.amSoil.getItemDropped(state, rand, fortune);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, BlockPos pos, EnumFacing direction, net.minecraftforge.common.IPlantable plantable) {
        net.minecraftforge.common.EnumPlantType plantType = plantable.getPlantType(world, pos.offset(direction));

        switch (plantType) {
        case Desert:
        case Plains:
            return true;
        case Cave:
            return isSideSolid(world, pos, EnumFacing.UP);
        case Beach:
            boolean hasWater = (world.getBlockState(pos.east()).getBlock().getMaterial() == Material.water
                    || world.getBlockState(pos.west()).getBlock().getMaterial() == Material.water || world.getBlockState(pos.north()).getBlock().getMaterial() == Material.water
                    || world.getBlockState(pos.south()).getBlock().getMaterial() == Material.water);
            return hasWater;
        default:
            return false;
        }
    }
}
