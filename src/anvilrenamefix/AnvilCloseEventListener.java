package anvilrenamefix;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;

public class AnvilCloseEventListener implements Listener {

	private AnvilRenameFixPlugin pluginInstance;
	public AnvilCloseEventListener(AnvilRenameFixPlugin plugin)
	{
		this.pluginInstance = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST,ignoreCancelled=true)
	public void onInventoryClose(InventoryCloseEvent e)
	{
		Inventory inv = e.getView().getTopInventory();
		if (inv != null && inv instanceof AnvilInventory)
		{
			pluginInstance.removeDecodedItemString(e.getPlayer().getName());
		}
	}
	
}
