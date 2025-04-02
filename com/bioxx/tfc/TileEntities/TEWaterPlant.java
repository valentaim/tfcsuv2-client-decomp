package com.bioxx.tfc.TileEntities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;






public class TEWaterPlant
  extends NetworkTileEntity
{
  private Block blockType;

  public void setBlock(Block block) {
    if (block.func_149662_c()) {
      this.blockType = block;
    }
  }



  public boolean canUpdate() {
    return false;
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public Block getBlockFromType() {
    if (!this.field_145850_b.field_72995_K) {
      return this.blockType;
    }
    return this.blockType;
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    this.blockType = Block.func_149729_e(nbttagcompound.func_74762_e("block"));
  }



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    nbttagcompound.func_74768_a("block", Block.func_149682_b(this.blockType));
  }



  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.blockType = Block.func_149729_e(nbt.func_74762_e("blockType"));
  }


  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74768_a("blockType", Block.func_149682_b(this.blockType));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEWaterPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */