package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Blocks.BlockDetailed;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.BitSet;
import net.minecraft.nbt.NBTTagCompound;

public class TEDetailed
  extends NetworkTileEntity
{
  public short typeID = -1;
  public byte metaID;
  public BitSet data;
  public static final byte PACKET_UPDATE = 0;
  public static final byte PACKET_ACTIVATE = 1;
  protected byte packetType = -1;
  private BitSet quads;

  public TEDetailed() {
    this.data = new BitSet(512);
    this.quads = new BitSet(8);
  }


  public boolean canUpdate() {
    return false;
  }

  public int getType() {
    return this.typeID;
  }

  public int getMeta() {
    return this.metaID;
  }

  public boolean getBlockExists(int x, int y, int z) {
    return this.data.get((x * 8 + z) * 8 + y);
  }

  public void setBlock(int x, int y, int z) {
    this.data.set((x * 8 + z) * 8 + y);
  }

  public void setQuad(int x, int y, int z) {
    int x1 = (x >= 4) ? 1 : 0;
    int y1 = (y >= 4) ? 1 : 0;
    int z1 = (z >= 4) ? 1 : 0;
    int index = (x1 * 2 + z1) * 2 + y1;
    this.quads.set(index);
  }

  public void clearQuad(int x, int y, int z) {
    int x1 = (x >= 4) ? 1 : 0;
    int y1 = (y >= 4) ? 1 : 0;
    int z1 = (z >= 4) ? 1 : 0;
    int index = (x1 * 2 + z1) * 2 + y1;
    this.quads.clear(index);
  }

  public boolean isQuadSolid(int x, int y, int z) {
    return !this.quads.get((x * 2 + z) * 2 + y);
  }

  public boolean isBlockEmpty() {
    byte[] ba = toByteArray(this.data);
    for (byte b : ba) {
      if (b != -1)
        return false;
    }
    return true;
  }





  public void func_145839_a(NBTTagCompound nbttc) {
    super.func_145839_a(nbttc);
    this.metaID = nbttc.func_74771_c("metaID");
    this.typeID = nbttc.func_74765_d("typeID");
    this.data = new BitSet(512);
    this.data.or(fromByteArray(nbttc.func_74770_j("data"), 512));
    this.quads.or(fromByteArray(nbttc.func_74770_j("quads"), 8));
  }





  public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
    super.func_145841_b(par1NBTTagCompound);
    par1NBTTagCompound.func_74777_a("typeID", this.typeID);
    par1NBTTagCompound.func_74774_a("metaID", this.metaID);
    par1NBTTagCompound.func_74773_a("data", toByteArray(this.data));
    par1NBTTagCompound.func_74773_a("quads", toByteArray(this.quads));
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.typeID = nbt.func_74765_d("typeID");
    this.metaID = nbt.func_74771_c("metaID");
    this.data = new BitSet(512);
    this.data.or(fromByteArray(nbt.func_74770_j("data"), 512));

    for (int subX = 0; subX < 8; subX++) {
      for (int subZ = 0; subZ < 8; subZ++) {
        for (int subY = 0; subY < 8; subY++) {
          if (!getBlockExists(subX, subY, subZ)) {
            setQuad(subX, subY, subZ);
          }
        }
      }
    }
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public void handleDataPacket(NBTTagCompound nbt) {
    this.packetType = nbt.func_74771_c("packetType");
    if (this.packetType == 0) {
      int index = nbt.func_74762_e("index");
      this.data.set(index, false);

      for (int subX = 0; subX < 8; subX++) {
        for (int subZ = 0; subZ < 8; subZ++) {
          for (int subY = 0; subY < 8; subY++) {
            if (!getBlockExists(subX, subY, subZ)) {
              setQuad(subX, subY, subZ);
            }
          }
        }
      }
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    } else if (this.packetType == 1 && !this.field_145850_b.field_72995_K) {
      (PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(this.entityplayer)).chiselMode = nbt.func_74771_c("chiselMode");
      ((BlockDetailed)TFCBlocks.detailed).xSelected = nbt.func_74771_c("xSelected");
      ((BlockDetailed)TFCBlocks.detailed).ySelected = nbt.func_74771_c("ySelected");
      ((BlockDetailed)TFCBlocks.detailed).zSelected = nbt.func_74771_c("zSelected");

      ((BlockDetailed)TFCBlocks.detailed).onBlockActivatedServer(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.entityplayer, 0, 0.0F, 0.0F, 0.0F);

      ((BlockDetailed)TFCBlocks.detailed).xSelected = -10;
      ((BlockDetailed)TFCBlocks.detailed).ySelected = -10;
      ((BlockDetailed)TFCBlocks.detailed).zSelected = -10;
    }
  }



  public void createDataNBT(NBTTagCompound nbt) {
    this.packetType = nbt.func_74771_c("packetType");


    if (this.packetType == 1)
    {
      nbt.func_74774_a("chiselMode", (PlayerManagerTFC.getInstance().getClientPlayer()).chiselMode);
    }
  }




  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74777_a("typeID", this.typeID);
    nbt.func_74774_a("metaID", this.metaID);
    nbt.func_74773_a("data", toByteArray(this.data));
  }

  public static BitSet fromByteArray(byte[] bytes, int size) {
    BitSet bits = new BitSet(size);
    for (int i = 0; i < bytes.length * 8; i++) {
      if ((bytes[bytes.length - i / 8 - 1] & 1 << i % 8) > 0)
        bits.set(i);
    }
    return bits;
  }

  public static byte[] toByteArray(BitSet bits) {
    byte[] bytes = new byte[bits.length() / 8 + 1];
    for (int i = 0; i < bits.length(); i++) {
      if (bits.get(i))
        bytes[bytes.length - i / 8 - 1] = (byte)(bytes[bytes.length - i / 8 - 1] | 1 << i % 8);
    }
    return bytes;
  }

  public static BitSet turnCube(byte[] bytes, int xAngle, int yAngle, int zAngle) {
    if (xAngle == 0 && yAngle == 0 && zAngle == 0) {
      return fromByteArray(bytes, 512);
    }
    BitSet data = fromByteArray(bytes, 512);
    BitSet turnedData = new BitSet(512);


    for (int x = 0; x < 8; x++) {
      for (int z = 0; z < 8; z++) {
        for (int y = 0; y < 8; y++) {
          int xCoord = x, yCoord = y, zCoord = z;

          int i;
          for (i = 0; i < xAngle; i += 90) {

            int buf = yCoord;
            yCoord = 7 - zCoord;
            zCoord = buf;
          }

          for (i = 0; i < zAngle; i += 90) {

            int buf = xCoord;
            xCoord = 7 - yCoord;
            yCoord = buf;
          }

          for (i = 0; i < yAngle; i += 90) {

            int buf = zCoord;
            zCoord = 7 - xCoord;
            xCoord = buf;
          }

          int srcI = (x * 8 + z) * 8 + y;
          int resI = (xCoord * 8 + zCoord) * 8 + yCoord;
          turnedData.set(resI, data.get(srcI));
        }
      }
    }  return turnedData;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEDetailed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */