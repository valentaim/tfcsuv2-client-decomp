package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;


public class TEBellows
  extends NetworkTileEntity
{
  private static final int[][] BLOCK_MAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };

  public boolean shouldBlow;

  public int blowTimer;

  public int blowDirection;

  public void func_145845_h() {
    if (this.shouldBlow)
    {
      if (this.blowDirection == 0) {

        this.blowTimer++;
        if (this.field_145850_b.field_72995_K)
          generateSmoke();
        if (this.blowTimer == 5) {

          this.blowDirection = 1;
          if (!this.field_145850_b.field_72995_K) {
            giveAir();
          }
        }
      } else {

        this.blowTimer--;
        if (this.blowTimer == -3) {

          this.blowDirection = 0;
          this.shouldBlow = false;
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
      }
    }
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public void generateSmoke() {
    int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    int x = BLOCK_MAP[meta][0];
    int z = BLOCK_MAP[meta][1];
    Random random = new Random();

    float f = this.field_145851_c + x + 0.5F;
    float f1 = this.field_145848_d + 0.1F + random.nextFloat() * 6.0F / 16.0F;
    float f2 = this.field_145849_e + z + 0.5F;

    float f4 = random.nextFloat() * 0.6F;
    float f5 = random.nextFloat() * -0.6F;

    this.field_145850_b.func_72869_a("smoke", (f + f4 - 0.3F), f1, (f2 + f5 + 0.3F), 0.0D, 0.0D, 0.0D);
  }


  public void giveAir() {
    int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    int x = BLOCK_MAP[meta][0];
    int z = BLOCK_MAP[meta][1];
    if (this.field_145850_b.func_72899_e(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z)) {

      TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z);
      TileEntity te2 = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d - 1, this.field_145849_e + z);
      TEFireEntity tileentityfirepit = null;

      if (te instanceof TEFireEntity) {
        tileentityfirepit = (TEFireEntity)te;
      } else if (te2 instanceof TEForge) {
        tileentityfirepit = (TEFireEntity)te2;
      }
      if (tileentityfirepit != null) {
        tileentityfirepit.receiveAirFromBellows();
      }
    }
  }


  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.shouldBlow = nbt.func_74767_n("shouldBlow");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74757_a("shouldBlow", this.shouldBlow);
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.shouldBlow = nbt.func_74767_n("shouldBlow");
  }




  public void handleDataPacket(NBTTagCompound nbt) {}




  public void createDataNBT(NBTTagCompound nbt) {}




  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74757_a("shouldBlow", this.shouldBlow);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */