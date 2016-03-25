package alfheimrsmoons.common;

import alfheimrsmoons.AlfheimrsMoons;
import alfheimrsmoons.common.block.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class AMAchievementPage extends AchievementPage {
    public static Achievement amTestAchievement = (new Achievement(AlfheimrsMoons.MODID + 1, "amTestAchievement", -2, 0, new ItemStack(ModBlocks.nitroTorch, 1),
            (Achievement) null)).registerStat();
    public static Achievement amTempAchievement = (new Achievement(AlfheimrsMoons.MODID + 2, "amTempAchievement", 0, 0, new ItemStack(Blocks.tnt, 1), amTestAchievement))
            .setSpecial().registerStat();

    public AMAchievementPage() {
        super(AlfheimrsMoons.NAME, amTestAchievement, amTempAchievement);
    }

    // TODO add AchievementEventHandler
    // @see
    // https://github.com/Glitchfiend/BiomesOPlenty/blob/887d715c6121915cf14b12f731bfa21bc1f6c0f9/src/main/java/biomesoplenty/common/handler/AchievementEventHandler.java

}
