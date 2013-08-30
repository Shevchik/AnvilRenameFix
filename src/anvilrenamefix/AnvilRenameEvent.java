/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 */

package anvilrenamefix;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class AnvilRenameEvent implements Listener {

	private AnvilRenameFixPlugin pluginInstance;
	public AnvilRenameEvent(AnvilRenameFixPlugin plugin)
	{
		this.pluginInstance = plugin;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST,ignoreCancelled=true)
	public void onInventoryClick(final InventoryClickEvent e) 
	{ 
		String playername = ((Player) e.getWhoClicked()).getName();
		if(e.getInventory() instanceof AnvilInventory)
		{
			int rawSlot = e.getRawSlot();
			if(rawSlot == e.getView().convertSlot(rawSlot) && rawSlot == 2)
			{
				ItemStack item = e.getCurrentItem();
				//only do something if we have item in slot and it has displayername
				if(item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
				{
					//rename item
					String itemname = pluginInstance.getDecodedItemString(playername);
					if (itemname != null)
					{
						ItemMeta im = item.getItemMeta();
						im.setDisplayName(itemname);
						item.setItemMeta(im);
						pluginInstance.removeDecodedItemString(playername);
						//fix ghost items in other slots
						Bukkit.getScheduler().scheduleSyncDelayedTask(pluginInstance, new Runnable()
						{
							public void run()
							{
								((Player) e.getWhoClicked()).updateInventory();
							}
						});
					}
				}
			}
		}
	}

	
}
