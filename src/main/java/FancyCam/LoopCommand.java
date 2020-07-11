package FancyCam;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoopCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            String arg = strings[0];
            if (arg.equalsIgnoreCase("start")) {
                MainClass.victim = player;
            } else if (arg.equalsIgnoreCase("stop")) {
                MainClass.victim = null;
            }
        }
        return false;
    }
}
