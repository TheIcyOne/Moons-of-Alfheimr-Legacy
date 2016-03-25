package alfheimrsmoons.common.block;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Function;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.BlockCrystalOre.EnumType;
import alfheimrsmoons.common.item.ItemModMultiTexture;
import alfheimrsmoons.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStorage extends Block {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumType.class);
    private String name;

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { VARIANT });
    }

    public BlockStorage() {
        super(Material.iron);

        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.KASOLITE));

        this.setCreativeTab(AlfheimrsMoons.instance.creativeTab);
        this.name = "storage";
        this.setUnlocalizedName(this.name);

        GameRegistry.registerBlock(this, ItemModMultiTexture.class, this.name);
        ((ItemModMultiTexture) Item.getItemFromBlock(this)).setNameFunction(new Function<ItemStack, String>() {
            @Nullable
            public String apply(ItemStack input) {
                return BlockCrystalOre.EnumType.byMetadata(input.getItemDamage()).getName();
            }
        });

        this.setHardness(5.0F);
        this.setResistance(10.0F);
        this.setStepSound(Block.soundTypeMetal);
        // TODO custom harvest level for different ore types
        // this.setHarvestLevel("pickaxe", 2,
        // getStateFromMeta(EnumType.KASOLITE.ordinal()));
        this.setHarvestLevel("pickaxe", 2);
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood
     * returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (BlockCrystalOre.EnumType blockamcrystalore$enumtype : BlockCrystalOre.EnumType.values()) {
            list.add(new ItemStack(itemIn, 1, blockamcrystalore$enumtype.getMetadata()));
        }
    }

    // map from state to meta and vice verca
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((EnumType) state.getValue(VARIANT)).ordinal();
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }
}
