package FancyCam;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    public static SpotHandler spotHandler = new SpotHandler();

    public static Player victim = null;

    private LoopHandler loopHandler = new LoopHandler();

    public void onEnable() {
        registerCommands();
        registerEvents();
        Bukkit.getScheduler().runTaskTimer(this, loopHandler, 0, 1);
    }

    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("cam").setExecutor(new SetSpotCommand());
        getCommand("bouncecam").setExecutor(new BouncyCommand());
        getCommand("loop").setExecutor(new LoopCommand());
    }

    private void registerEvents() {

    }

}

