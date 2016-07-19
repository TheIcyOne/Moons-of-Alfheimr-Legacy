package alfheimrsmoons.client;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;

import java.util.HashMap;
import java.util.Map;

public class CustomStateMapper extends StateMapperBase
{
    private final String name;
    private final IProperty[] ignored;

    public CustomStateMapper(String name, IProperty... ignored)
    {
        this.name = name;
        this.ignored = ignored;
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state)
    {
        Map<IProperty<?>, Comparable<?>> properties = new HashMap<>(state.getProperties());
        for (IProperty<?> property : ignored)
        {
            properties.remove(property);
        }
        return new ModelResourceLocation(AlfheimrsMoons.MOD_ID + ":" + name, getPropertyString(properties));
    }
}
