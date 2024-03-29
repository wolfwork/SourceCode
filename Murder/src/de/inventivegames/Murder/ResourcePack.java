package de.inventivegames.Murder;

import org.bukkit.entity.Player;

public class ResourcePack {

	private static Class<?>	nmsChatSerializer		= Reflection.getNMSClass("ChatSerializer");
	private static Class<?>	nmsPacketPlayOutChat	= Reflection.getNMSClass("PacketPlayOutChat");

	public static void setResourcePack(final Player p, String version) {
		Murder.instance.getServer().getScheduler().scheduleSyncDelayedTask(Murder.instance, new Runnable() {

			@Override
			public void run() {
				try {
					p.sendMessage(Murder.prefix + "�6Downloading Custom Resources...");
					p.setResourcePack("https://dl.dropboxusercontent.com/s/sbqllqe98huhrv1/MurderResourcePack%20256x%20%28V2.1%29.zip?dl=1");
					p.sendMessage("�6If the ResourcePack isn't downloading,");

					try {
						Object handle = Reflection.getHandle(p);
						Object connection = Reflection.getField(handle.getClass(), "playerConnection").get(handle);
						Object serialized = Reflection.getMethod(nmsChatSerializer, "a", String.class).invoke(null, "{\"text\":\"\",\"extra\":[{\"text\":\"make sure you have \",\"color\":\"gold\"},{\"translate\":\"options.serverTextures\",\"color\":\"green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"�7Options/Video Settings/Server Textures\"}},{\"text\":\" enabled.\",\"color\":\"gold\"}]}");
						Object packet = nmsPacketPlayOutChat.getConstructor(Reflection.getNMSClass("IChatBaseComponent")).newInstance(serialized);
						Reflection.getMethod(connection.getClass(), "sendPacket").invoke(connection, packet);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					p.sendMessage("�6make sure you have �2Server-Textures �6enabled");
					e.printStackTrace();
				}

			}

		}, 10L);
	}

	public static void resetResourcePack(Player p) {

		p.setResourcePack("https://dl.dropboxusercontent.com/s/99udm9shqq98hlc/MurderDefault.zip?dl=1");

	}

}
