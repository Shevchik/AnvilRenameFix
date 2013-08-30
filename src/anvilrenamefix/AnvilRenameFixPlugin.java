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

import java.util.concurrent.ConcurrentHashMap;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class AnvilRenameFixPlugin extends JavaPlugin
{
  private AnvilRenameEvent are;
  private AnvilCloseEventListener acel;
  private ProtocolManager protocolManager;
  protected ProtocolManager getProtocolManager()
  {
    return this.protocolManager;
  }
  private AnvilNameDecoder nameDecoder;
  
  
  private ConcurrentHashMap<String,String> playernameitemname = new ConcurrentHashMap<String,String>();
  protected String getDecodedItemString(String playername)
  {
	  if (playernameitemname.containsKey(playername))
	  {
		  return playernameitemname.get(playername);
	  }
	  return null;
  }
  protected void setDecodedItemStirng(String playername, String itemname)
  {
	  playernameitemname.put(playername, itemname);
  }
  protected void removeDecodedItemString(String playername)
  {
	  playernameitemname.remove(playername);
  }
   
  
  
  public void onEnable()
  {
    nameDecoder = new AnvilNameDecoder();
    protocolManager = ProtocolLibrary.getProtocolManager();
    new PacketListener(this, nameDecoder);
    are = new AnvilRenameEvent(this);
    getServer().getPluginManager().registerEvents(are, this);
    acel = new AnvilCloseEventListener(this);
    getServer().getPluginManager().registerEvents(acel, this);
  }

  public void onDisable()
  {
    protocolManager.removePacketListeners(this);
    protocolManager = null;
    HandlerList.unregisterAll(this);
    are = null;
    acel = null;
    nameDecoder = null;
  }
}
