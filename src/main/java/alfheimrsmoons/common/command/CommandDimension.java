package alfheimrsmoons.common.command;

import java.util.ArrayList;
import java.util.List;

import alfheimrsmoons.AlfheimrsMoons;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;

public class CommandDimension implements ICommand {

    private List aliases;

    public CommandDimension() {
        this.aliases = new ArrayList();
        // this.aliases.add("tpx");
    }

    @Override
    public String getCommandName() {
        return "amdim";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "command.amdim.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new CommandException("command.amdim.usage");
        }

        if (sender instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) sender;

            int newDim = Integer.parseInt(args[0]);
            // player.mcServer.getConfigurationManager().transferPlayerToDimension(player,
            // AlfheimrsMoons.DIM_ID,
            // new
            // CustomDimensionTeleporter(player.mcServer.worldServerForDimension(AlfheimrsMoons.DIM_ID)));
        }
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    @Override
    public List<String> getCommandAliases() {
        return this.aliases;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

}
