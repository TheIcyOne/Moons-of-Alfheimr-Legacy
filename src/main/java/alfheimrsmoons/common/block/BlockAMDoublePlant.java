package alfheimrsmoons.common.block;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.util.IStringSerializable;

// TODO register block, add json + texture files
public class BlockAMDoublePlant extends BlockDoublePlant {
    public static enum EnumType implements IStringSerializable {
        LIONTAILS(0, "liontails");

        private static final BlockAMDoublePlant.EnumType[] META_LOOKUP = new BlockAMDoublePlant.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;

        private EnumType(int meta, String name) {
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String unlocalizedName) {
            this.meta = meta;
            this.name = name;
            this.unlocalizedName = unlocalizedName;
        }

        public int getMetadata() {
            return this.meta;
        }

        public String toString() {
            return this.name;
        }

        public static BlockAMDoublePlant.EnumType byMetadata(int meta) {
            if (meta < 0 || meta >= META_LOOKUP.length) {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() {
            return this.name;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        static {
            for (BlockAMDoublePlant.EnumType blockamdoubleplant$enumtype : values()) {
                META_LOOKUP[blockamdoubleplant$enumtype.getMetadata()] = blockamdoubleplant$enumtype;
            }
        }
    }
}
