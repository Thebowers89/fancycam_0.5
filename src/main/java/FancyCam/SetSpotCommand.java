package FancyCam;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SetSpotCommand implements CommandExecutor {

    public static float interval = 0.01f;

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Location location = player.getLocation();
            double radius = Double.parseDouble(strings[0]);
            if (strings.length == 3) {
                double h = Double.parseDouble(strings[1]);
                double speed = Double.parseDouble(strings[2]);
                standard(location, radius, h, speed);
            } else if (strings.length == 2) {
                double h = Double.parseDouble(strings[1]);
                standard(location, radius, h, 1);
            } else {
                standard(location, radius, 0, 1);
            }
        }
        return false;
    }

    public void bouncy(Location origin, double radius, double height) {
        ArrayList<Location> list = new ArrayList<Location>();
        for (double i = 0; i < 360; i+=interval) {
            double radians = i;
            double x = origin.getX() + radius * Math.cos(radians);
            double z = origin.getZ() - radius * Math.sin(radians);

            Location l = new Location(origin.getWorld(), x, origin.getY() + height, z);
            l.setY(l.getY() + (getHeight(origin, l) * 0.1) - ((getHeight(origin, l) * 0.1)*2));
            l.setPitch(getPitch(l, origin));
            l.setYaw(getHeading(l, origin));

            list.add(l);
        }
        MainClass.spotHandler.loop = list;
    }

    public void standard(Location origin, double radius, double height, double speed) {
        ArrayList<Location> list = new ArrayList<Location>();
        for (double i = 0; i < 360; i+=interval*speed) {
            double radians = i;
            double x = origin.getX() + radius * Math.cos(radians);
            double z = origin.getZ() - radius * Math.sin(radians);

            Location l = new Location(origin.getWorld(), x, origin.getY() + height, z);
            l.setPitch(getPitch(l, origin));
            l.setYaw(getHeading(l, origin));

            list.add(l);
        }
        MainClass.spotHandler.loop = list;
    }

    private float getAngleY(Location pos1, Location pos2) {
        double a = Math.abs(pos2.getY() - pos1.getY());
        double b = pos1.distance(pos2);
        return (float) Math.toDegrees(Math.asin(a/b));
    }

    private float getHeight(Location pos1, Location pos2) {
        double a = Math.abs(pos2.getZ() - pos1.getZ());
        double b = pos1.distance(pos2);
        return (float) Math.toDegrees(Math.asin(a/b));
    }

    //Call this method to get the pitch for the projectile
    private float getPitch(Location p, Location target) {
        float angle = getAngleY(p, target);
        if (target.getY() > p.getY()) {
            return -angle;
        } else {
            return angle;
        }
    }

    private float getAngleX(Location pos1, Location pos2) {
        double a = Math.abs(pos2.getX() - pos1.getX());
        double a2 = Math.pow(pos2.getX() - pos1.getX(), 2);
        double b2 = Math.pow(pos2.getZ() - pos1.getZ(), 2);
        double c2 = a2 + b2;
        return (float) Math.toDegrees(Math.acos(a/Math.sqrt(c2)));
    }

    //Call this method to get the heading for the projectile
    private float getHeading(Location p, Location t) {
        float angle = getAngleX(p, t);
        float I = 0;
        float II = -90;
        float III = 180;
        float IV = 90;
        if (t.getZ() > p.getZ()) {
            if (t.getX() > p.getX()) {
                return II + angle;
            } else {
                return IV + -angle;
            }
        } else {
            if (t.getX() > p.getX()) {
                return II + -angle;
            } else {
                return IV + angle;
            }
        }
    }

}
