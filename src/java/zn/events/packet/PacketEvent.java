package zn.events.packet;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class PacketEvent extends Event {

	  private Packet<?> packet;
	
	  public PacketEvent(Packet<?> packet) {
		    this.packet = packet;
	  }
	  
	  public Packet<?> getPacket() {
		    return packet;
	  }
	  
	  public void setPacket(Packet<?> packet) {
		  this.packet = packet;
	  }
	  
	  public static class Out extends PacketEvent {
		    
		    public Out(Packet<?> packet) {
		    	super(packet);
		    }
		    
	  }
	
	  public static class In extends PacketEvent {
		  
		  public In(Packet<?> packet) {
			  super(packet);
		  }
		    
	  }
}
