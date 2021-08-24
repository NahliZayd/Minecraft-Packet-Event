package zn.events.packet;

import io.netty.channel.ChannelPipeline;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ChannelHandlerInput {
	
  //Minecraft instance
	public Minecraft mc = Minecraft.getMinecraft();
  
  //To check if the event engine has been set up or not
	public boolean inited = false;

  //this is fired when the player log to a world/server , if it's the first time we add our listener in the netty shit
	@SubscribeEvent
	public void init(PlayerEvent.PlayerLoggedInEvent event)  {
		if(event.player == Minecraft.getMinecraft().thePlayer) {
			if(!inited) {
				inited = true;
				ChannelPipeline pipeline = mc.getNetHandler().getNetworkManager().channel().pipeline();
				pipeline.addBefore("zn_events", "listener", new PacketListener());
			}
		}
	}

  //just in case the init didn't work
	@SubscribeEvent
	public void onClientTick(final TickEvent.ClientTickEvent e) {
        if (mc.thePlayer != null) {
            if (mc.getNetHandler() != null && mc.getNetHandler().getNetworkManager().channel().pipeline().get("zn_events") != null && mc.getNetHandler().getNetworkManager().channel().pipeline().get("listener") == null) {
                mc.getNetHandler().getNetworkManager().channel().pipeline().addBefore("zn_events", "listener", new PacketListener());
            }
           }
         }
	


	
}
