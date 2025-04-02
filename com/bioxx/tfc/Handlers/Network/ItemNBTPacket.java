package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.TerraFirmaCraft;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;






public class ItemNBTPacket
  extends AbstractPacket
{
  private NBTTagCompound tags = new NBTTagCompound();
  private List<String> tagNames = new LinkedList<>();
  private List<String> removeNames = new LinkedList<>();


  public ItemNBTPacket(NBTTagCompound nbt) {
    this();
    this.tags = nbt;
  }
  public ItemNBTPacket() {}

  public ItemNBTPacket(NBTTagCompound nbt, List<String> acceptedTagNames) {
    this();
    this.tagNames = acceptedTagNames;
    for (String tagName : this.tagNames) {
      this.tags.func_74782_a(tagName, nbt.func_74781_a(tagName));
    }
  }

  public ItemNBTPacket(NBTTagCompound nbt, List<String> acceptedTagNames, List<String> removeTagNames) {
    this();
    this.tagNames = acceptedTagNames;
    for (String tagName : this.tagNames)
      this.tags.func_74782_a(tagName, nbt.func_74781_a(tagName));
    this.removeNames = removeTagNames;
  }

  public ItemNBTPacket addAcceptedTag(String name) {
    if (!this.removeNames.contains(name) && !this.tagNames.contains(name))
      this.tagNames.add(name);
    return this;
  }

  public ItemNBTPacket removeAcceptedTag(String name) {
    this.tagNames.remove(name);
    return this;
  }

  public ItemNBTPacket addRemoveTag(String name) {
    if (!this.removeNames.contains(name))
      this.removeNames.add(name);
    return this;
  }

  public ItemNBTPacket removeRemoveTag(String name) {
    this.removeNames.remove(name);
    return this;
  }


  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    PacketBuffer pb = new PacketBuffer(buffer);
    NBTTagCompound tags = this.tags;

    for (String tagName : this.removeNames) {
      tags.func_82580_o(tagName);
    }
    try {
      pb.func_150786_a(tags);

      pb.writeInt(this.tagNames.size());
      for (String tagName : this.tagNames) {
        pb.func_150785_a(tagName);
      }
      pb.writeInt(this.removeNames.size());
      for (String tagName : this.removeNames)
        pb.func_150785_a(tagName);
    } catch (Exception e) {
      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    PacketBuffer pb = new PacketBuffer(buffer);

    try {
      this.tags = pb.func_150793_b();

      int size = pb.readInt(); int i;
      for (i = 0; i < size; i++) {
        this.tagNames.add(pb.func_150789_c(256));
      }
      size = pb.readInt();
      for (i = 0; i < size; i++)
        this.removeNames.add(pb.func_150789_c(256));
    } catch (Exception e) {
      TerraFirmaCraft.LOG.catching(e);
    }
  }




  public void handleClientSide(EntityPlayer player) {
    ItemStack stack = player.field_71071_by.func_70448_g();

    if (stack != null) {
      NBTTagCompound stackNBT;
      if (stack.func_77942_o()) {

        stackNBT = stack.field_77990_d;


      }
      else {


        stackNBT = new NBTTagCompound();
      }

      for (String tagName : this.tagNames)
        stackNBT.func_74782_a(tagName, this.tags.func_74781_a(tagName));
      for (String tagName : this.removeNames)
        stackNBT.func_82580_o(tagName);
      player.field_71071_by.func_70448_g().func_77982_d(stackNBT);
    }
  }




  public void handleServerSide(EntityPlayer player) {
    ItemStack stack = player.field_71071_by.func_70448_g();

    if (stack != null) {
      NBTTagCompound stackNBT;
      if (stack.func_77942_o()) {

        stackNBT = stack.field_77990_d;


      }
      else {


        stackNBT = new NBTTagCompound();
      }
      for (String tagName : this.tagNames)
        stackNBT.func_74782_a(tagName, this.tags.func_74781_a(tagName));
      for (String tagName : this.removeNames)
        stackNBT.func_82580_o(tagName);
      player.field_71071_by.func_70448_g().func_77982_d(stackNBT);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\ItemNBTPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */