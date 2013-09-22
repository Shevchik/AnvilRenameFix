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

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.events.PacketContainer;

public class SecondSlotListener implements Listener {

	private AnvilRenameFixPlugin pluginInstance;
	public SecondSlotListener(AnvilRenameFixPlugin plugin)
	{
		this.pluginInstance = plugin;
	}
	
	@EventHandler(priority=EventPriority.HIGHEST,ignoreCancelled=true)
	public void onAnvilSecondSlotClick(InventoryClickEvent event)
	{
		Inventory inv = event.getInventory();
		if (inv instanceof AnvilInventory && event.getSlot() == 1)
		{
			AnvilInventory ainv = (AnvilInventory) inv;
			final ItemStack itemtorename = ainv.getItem(2);
			if (itemtorename != null && itemtorename.getItemMeta().hasDisplayName()) 
			{
				Player player = (Player) event.getWhoClicked();
				String name = itemtorename.getItemMeta().getDisplayName();
				PacketContainer packet = new PacketContainer(Packets.Client.CUSTOM_PAYLOAD);
				packet.getStrings().write(0, "MC|ItemName");
				packet.getIntegers().write(0, name.length());
				packet.getByteArrays().write(0, name.getBytes());				
				try {
					pluginInstance.getProtocolManager().recieveClientPacket(player, packet);
				} catch (Exception e) {e.printStackTrace();}
			}
		}
	}
	
}
