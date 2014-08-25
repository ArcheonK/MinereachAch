import me.ccrama.MinereachAch.MinereachAch;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class AchievementHook extends JavaPlugin implements Listener {

	public void onEnable() {

		Bukkit.getPluginManager().registerEvents(this, this);

		// This creates and registeres a new achievement that listens on the
		// stats.Blocks datapoint, with the title of Block Broken and gets
		// thrown at 10 block breaks. The user is given a Golden Spade.
		MinereachAch.registerAchievement("stats.Blocks", "Block Broken", 10,
				new ItemStack(Material.GOLD_SPADE), false);

	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) throws IOException {
		Player play = event.getPlayer();

		// Because this event may fire more than once every 10 seconds, it's
		// best to use .lazySetStat() instead of setStat()
		// This will save the total amount of EACH block a user breaks (by block
		// name) on the blocks collection
		MinereachAch.lazySetStat("BlockBreakEvent"
				+ event.getBlock().getType().name(), play, "blocks", event
				.getBlock().getType().name(), 1);

		// Because this event may fire more than once every 10 seconds, it's
		// best to use .lazySetStat() instead of setStat()
		// This will save the total amount of blocks the user has broken to the
		// main stat collection, and is the basis
		// for the achievement we registered onEnable()
		MinereachAch.lazySetStat("BlockBreakEvent", play, "stats", "Blocks", 1);

	}
}
