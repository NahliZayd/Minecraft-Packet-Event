package zn.events.packet;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;

public class PacketListener extends ChannelDuplexHandler {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof Packet) {
      PacketEvent.In packetEvent = new PacketEvent.In((Packet < ? > ) msg);
      MinecraftForge.EVENT_BUS.post(packetEvent);
      if (!inPacket.isCanceled()) {
        msg = inPacket.getPacket();
        super.channelRead(ctx, msg);
      }
    }
  }

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    if (msg instanceof Packet) {
      PacketEvent.Out packetEvent = new PacketEvent.Out((Packet < ? > ) msg);
      MinecraftForge.EVENT_BUS.post(packetEvent);
      if (!outPacket.isCanceled()) {
        msg = outPacket.getPacket();
        super.write(ctx, msg, promise);
      }
    }
  }
}
