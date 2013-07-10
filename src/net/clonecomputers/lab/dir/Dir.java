package net.clonecomputers.lab.dir;

import org.bukkit.*;
import org.bukkit.plugin.java.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/* Example Template
 * By Adamki11s
 * HUGE Plugin Tutorial
 */


public class Dir extends JavaPlugin implements CommandExecutor{
	
	public String[] messages = {
		"North, -Z",
		"Northeast, +X-Z",
		"East, +X",
		"Southeast, +X+Z",
		"South, +Z",
		"Southwest, -X+Z",
		"West, -X",
		"Northwest, -X-Z",
		"Up, +Y",
		"Down, -Y",
	};
	
	@Deprecated
	public enum Direction{
		U(0.0F,0.0F,"Up, +Y"), D(0.0F,0.0F,"Down, -Y"),
		N(0.0F,0.0F,"North, -Z"), NE(0.0F,0.0F,"Northeast, +X-Z"),
		E(0.0F,0.0F,"East, +X"), SE(0.0F,0.0F,"Southeast, +X+Z"),
		S(-22.5F,22.5F,"South, +Z"), SW(0.0F,0.0F,"Southwest, -X+Z"),
		W(0.0F,0.0F,"West, -X"), NW(0.0F,0.0F,"Northwest, -X-Z"),
		UNKNOWN(0,0,"Unknown");
		public final float min;
		public final float max;
		public final String message;
		Direction(float min, float max, String message){
			this.min = min;
			this.max = max;
			this.message = message;
		}
		
		public String toString(){
			return this.message;
		}
	}

    public void onDisable() {
    	getCommand("dir").setExecutor(null);
		getLogger().info("Dir 1.0 is disabled!");
	}

	public void onEnable() {
		getLogger().info("Dir 1.0 is enabled!");
        getCommand("dir").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player;
		if(args.length > 0){
			player = getServer().getPlayer(args[0]);
		}else{
			if(sender instanceof Player){
				player = (Player) sender;
			}else{
				return false;
			}
		}
		Location l = player.getLocation();
		float ud = l.getPitch();
		float rl = l.getYaw();
		String m = "Unknown direction";
		if(ud < -60F){
			m = messages[8];
		}else if(ud > 60F){
			m = messages[9];
		}else{
			rl += 180;
			rl += 22.5;
			rl %= 360;
			m = messages[(int)rl/45];
		}
		sender.sendMessage(m);
		/*Direction dir = Direction.UNKNOWN;
		if(ud > Direction.U.min){
			dir = Direction.U;
		}else if(ud < Direction.D.max){
			dir = Direction.D;
		}else{
			for(Direction d: Direction.values()){
				if(d.equals(Direction.U) || d.equals(Direction.D)) continue;
				if(d.min < rl && rl < d.max) dir = d;
			}
		}
		sender.sendMessage(dir.toString());*/
		return true;
	}
	
}