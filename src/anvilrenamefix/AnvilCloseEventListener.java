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
