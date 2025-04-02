package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;



public class CreateMealPacket
  extends AbstractPacket
{
  private byte flag;
  private int x;
  private int y;
  private int z;

  public CreateMealPacket() {}

  public CreateMealPacket(int f, TEFoodPrep te) {
    this.flag = (byte)f;
    this.x = te.field_145851_c;
    this.y = te.field_145848_d;
    this.z = te.field_145849_e;
  }



  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    buffer.writeByte(this.flag);
    if (this.flag == 0) {

      buffer.writeInt(this.x);
      buffer.writeInt(this.y);
      buffer.writeInt(this.z);
    }
  }



  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    this.flag = buffer.readByte();
    if (this.flag == 0) {

      this.x = buffer.readInt();
      this.y = buffer.readInt();
      this.z = buffer.readInt();
    }
  }




  public void handleClientSide(EntityPlayer player) {}




  public void handleServerSide(EntityPlayer player) {
    if (this.flag == 0) {

      TEFoodPrep te = (TEFoodPrep)player.field_70170_p.func_147438_o(this.x, this.y, this.z);
      TFC_Core.getSkillStats(player).increaseSkill("skill.cooking", 1);
      te.actionCreate(player);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\CreateMealPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */