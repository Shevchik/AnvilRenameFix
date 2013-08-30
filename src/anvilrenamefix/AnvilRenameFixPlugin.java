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
