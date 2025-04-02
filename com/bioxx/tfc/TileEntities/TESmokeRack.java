package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.Food;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public class TESmokeRack
  extends NetworkTileEntity
  implements IInventory {
  public ItemStack[] storage = new ItemStack[2];
  public int[] driedCounter = new int[] { 0, 0 };



  private int dryTimer;



  public int lastSmokedTime;



  public void func_145845_h() {
    float env = 1.0F;
    float base = 1.0F;

    if (TFC_Climate.getRainfall(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) < 500.0F) {

      env = 0.75F; base = 0.75F;
    }

    this.dryTimer++;
    if (this.dryTimer > 1000) {

      this.dryTimer = 0;
      dryFoods();
    }

    if (!TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) && TFC_Time.getTotalHours() > (this.lastSmokedTime + 1)) {
      TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env, base);
    } else if (TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) > 0.0F) {
      TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env * 2.0F, base * 2.0F);
    }
  }


  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    TFC_Core.readInventoryFromNBT(nbt, this.storage);
    this.driedCounter = nbt.func_74759_k("driedCounter");
    if (this.driedCounter.length == 0) {
      this.driedCounter = new int[] { 0, 0 };
    }
  }


  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    TFC_Core.writeInventoryToNBT(nbt, this.storage);
    nbt.func_74783_a("driedCounter", this.driedCounter);
  }



  public int func_70302_i_() {
    return this.storage.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public ItemStack func_70298_a(int i, int j) {
    (this.storage[i]).field_77994_a -= j;
    return this.storage[i];
  }



  public ItemStack func_70304_b(int i) {
    return null;
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    boolean flag = false;
    if (!TFC_Core.areItemsEqual(this.storage[i], itemstack))
    {
      flag = true;
    }

    if (itemstack != null && !ItemStack.func_77989_b(itemstack, this.storage[i])) {

      if (Food.getDried(itemstack) > 0) {
        this.driedCounter[i] = (int)(TFC_Time.getTotalHours() - Food.getDried(itemstack));
      } else {
        this.driedCounter[i] = (int)TFC_Time.getTotalHours();
      }  flag = true;
    }
    if (flag) {

      this.storage[i] = itemstack;
      broadcastPacketInRange();
    }
  }


  public ItemStack removeStackInSlot(int i) {
    ItemStack is = func_70301_a(i).func_77946_l();
    Food.setDried(is, (int)TFC_Time.getTotalHours() - this.driedCounter[i]);
    func_70299_a(i, (ItemStack)null);
    return is;
  }


  public void dryFoods() {
    for (int i = 0; i < this.storage.length; i++) {

      if (func_70301_a(i) != null) {

        ItemStack is = func_70301_a(i);
        Food.setDried(is, (int)TFC_Time.getTotalHours() - this.driedCounter[i]);
        this.driedCounter[i] = (int)(TFC_Time.getTotalHours() - Food.getDried(is));
      }
    }
  }




  public String func_145825_b() {
    return "";
  }



  public boolean func_145818_k_() {
    return false;
  }



  public int func_70297_j_() {
    return 1;
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}




  public void func_70305_f() {}



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public void handleInitPacket(NBTTagCompound nbt) {
    TFC_Core.readInventoryFromNBT(nbt, this.storage);
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    this.storage = new ItemStack[this.storage.length];
    TFC_Core.readInventoryFromNBT(nbt, this.storage);
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void createDataNBT(NBTTagCompound nbt) {
    TFC_Core.writeInventoryToNBT(nbt, this.storage);
  }



  public void createInitNBT(NBTTagCompound nbt) {
    TFC_Core.writeInventoryToNBT(nbt, this.storage);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TESmokeRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */