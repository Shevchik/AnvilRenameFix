package anvilrenamefix;

import com.comphenix.protocol.Packets;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;

public class PacketListener {

	private AnvilRenameFixPlugin pluginInstance;
	private AnvilNameDecoder nameDecoder;
	public PacketListener(AnvilRenameFixPlugin plugin, AnvilNameDecoder nameDecoder)
	{
		this.pluginInstance = plugin;
		this.nameDecoder = nameDecoder;
		initAnvilCustomPacketListener();
	}
	
	
	private void initAnvilCustomPacketListener()
	{
		pluginInstance.getProtocolManager().addPacketListener(
				  new PacketAdapter(pluginInstance, ConnectionSide.CLIENT_SIDE, 
				  ListenerPriority.HIGHEST, Packets.Client.CUSTOM_PAYLOAD) {
					@Override
				    public void onPacketReceiving(PacketEvent e) {
						PacketContainer packet = e.getPacket();
						//check if it is anvil rename packet
						if (packet.getStrings().getValues().get(0).equals("MC|ItemName"))
						{
							byte[] barray = packet.getByteArrays().getValues().get(0);
							if (barray != null)
							{
								pluginInstance.setDecodedItemStirng(e.getPlayer().getName(), nameDecoder.decodeName(barray));
							}
						}
					}
				});
	}
	
}
