package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;











public class TEForge
  extends TEFireEntity
  implements IInventory
{
  public boolean isSmokeStackValid = false;
  public ItemStack[] fireItemStacks = new ItemStack[14];

  private int smokeTimer;


  private boolean validateSmokeStack() {
    if (!TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e))
      return true;
    if (checkChimney(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e))
      return true;
    if (checkChimney(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e))
      return true;
    if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1))
      return true;
    if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1))
      return true;
    if (notOpaque(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e))
      return true;
    if (notOpaque(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e))
      return true;
    if (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2)) {
      return true;
    }
    return (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2));
  }


  private boolean checkChimney(int x, int y, int z) {
    return (notOpaque(x, y, z) && this.field_145850_b.func_72937_j(x, y, z));
  }


  private boolean notOpaque(int x, int y, int z) {
    return (this.field_145850_b.func_72899_e(x, y, z) && !this.field_145850_b.func_147439_a(x, y, z).func_149662_c());
  }


  private void genSmokeRoot(int x, int y, int z) {
    if (this.fuelTimeLeft >= 0) {

      if (this.field_145850_b.func_147439_a(x, y, z) != TFCBlocks.smoke) {
        this.field_145850_b.func_147449_b(x, y, z, TFCBlocks.smoke);
      }
    } else {

      this.field_145850_b.func_147468_f(x, y, z);
    }
  }








  public void func_70305_f() {}







  public void combineMetals(ItemStack inputItem, ItemStack destItem) {
    int d1 = 100 - inputItem.func_77960_j();
    int d2 = 100 - destItem.func_77960_j();
    destItem.func_77964_b(100 - d1 + d2);
  }


  public void cookItem(int i) {
    HeatRegistry manager = HeatRegistry.getInstance();
    Random r = new Random();
    if (this.fireItemStacks[i] != null) {

      HeatIndex index = manager.findMatchingIndex(this.fireItemStacks[i]);
      ItemStack inputCopy = this.fireItemStacks[i].func_77946_l();

      if (index != null && TFC_ItemHeat.getTemp(this.fireItemStacks[i]) > index.meltTemp) {

        float temperature = TFC_ItemHeat.getTemp(this.fireItemStacks[i]);



        if (!(this.fireItemStacks[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal)) {
          this.fireItemStacks[i] = index.getMorph();
        }

        if (this.fireItemStacks[i] != null) {

          HeatIndex morphIndex = manager.findMatchingIndex(this.fireItemStacks[i]);
          if (morphIndex != null)
          {

            TFC_ItemHeat.setTemp(this.fireItemStacks[i], temperature);
          }
        }
        else if (index.hasOutput()) {

          ItemStack output = index.getOutput(inputCopy, r);
          if (inputCopy.func_77973_b() instanceof ISmeltable) {
            ItemStack meltedItem;
            ISmeltable smelt = (ISmeltable)inputCopy.func_77973_b();




            try { meltedItem = new ItemStack((smelt.getMetalType(inputCopy)).meltedItem); }
            catch (Exception e) { return; }
             TFC_ItemHeat.setTemp(meltedItem, temperature);

            int units = smelt.getMetalReturnAmount(inputCopy);

            if (inputCopy.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBloom) {
              units = Math.min(100, units);
            }
            while (units > 0 && getMold() != null) {

              ItemStack moldIS = getMold();
              ItemStack outputCopy = meltedItem.func_77946_l();

              if (units > 100) {

                units -= 100;
                moldIS.field_77994_a--;
                if (!addToStorage(outputCopy.func_77946_l())) {

                  EntityItem ei = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 1.5D, this.field_145849_e + 0.5D, outputCopy);
                  ei.field_70159_w = 0.0D; ei.field_70181_x = 0.0D; ei.field_70179_y = 0.0D;
                  this.field_145850_b.func_72838_d((Entity)ei);
                }  continue;
              }
              if (units > 0)
              {
                outputCopy.func_77964_b(100 - units);
                units = 0;
                moldIS.field_77994_a--;
                this.fireItemStacks[i] = outputCopy.func_77946_l();
              }

            }
          } else {

            this.fireItemStacks[i] = output;
          }


          if (TFC_ItemHeat.isCookable(this.fireItemStacks[i]) > -1.0F)
          {

            TFC_ItemHeat.setTemp(this.fireItemStacks[i], temperature);
          }
        }
      }
    }
  }


  public boolean addToStorage(ItemStack is) {
    if (func_70301_a(10) == null) {

      func_70299_a(10, is);
      return true;
    }
    if (func_70301_a(11) == null) {

      func_70299_a(11, is);
      return true;
    }
    if (func_70301_a(12) == null) {

      func_70299_a(12, is);
      return true;
    }
    if (func_70301_a(13) == null) {

      func_70299_a(13, is);
      return true;
    }
    return false;
  }


  private ItemStack getMold() {
    if (this.fireItemStacks[10] != null && this.fireItemStacks[10].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[10]).field_77994_a > 0)
    {
      return this.fireItemStacks[10];
    }
    if (this.fireItemStacks[11] != null && this.fireItemStacks[11].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[11]).field_77994_a > 0)
    {
      return this.fireItemStacks[11];
    }
    if (this.fireItemStacks[12] != null && this.fireItemStacks[12].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[12]).field_77994_a > 0)
    {
      return this.fireItemStacks[12];
    }
    if (this.fireItemStacks[13] != null && this.fireItemStacks[13].func_77973_b() == TFCItems.ceramicMold && (this.fireItemStacks[13]).field_77994_a > 0)
    {
      return this.fireItemStacks[13];
    }
    return null;
  }



  public ItemStack func_70298_a(int i, int j) {
    if (this.fireItemStacks[i] != null) {

      if ((this.fireItemStacks[i]).field_77994_a <= j) {

        ItemStack is = this.fireItemStacks[i];
        this.fireItemStacks[i] = null;
        return is;
      }

      ItemStack isSplit = this.fireItemStacks[i].func_77979_a(j);
      if ((this.fireItemStacks[i]).field_77994_a == 0)
        this.fireItemStacks[i] = null;
      return isSplit;
    }

    return null;
  }


  public void ejectContents() {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 0.8F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.fireItemStacks[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.fireItemStacks[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
        this.fireItemStacks[i] = null;
      }
    }
  }



  public int func_70297_j_() {
    return 64;
  }



  public String func_145825_b() {
    return "Forge";
  }


  public int getMoldIndex() {
    if (this.fireItemStacks[10] != null && this.fireItemStacks[10].func_77973_b() == TFCItems.ceramicMold)
      return 10;
    if (this.fireItemStacks[11] != null && this.fireItemStacks[11].func_77973_b() == TFCItems.ceramicMold)
      return 11;
    if (this.fireItemStacks[12] != null && this.fireItemStacks[12].func_77973_b() == TFCItems.ceramicMold)
      return 12;
    if (this.fireItemStacks[13] != null && this.fireItemStacks[13].func_77973_b() == TFCItems.ceramicMold)
      return 13;
    return -1;
  }



  public int func_70302_i_() {
    return this.fireItemStacks.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.fireItemStacks[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }


  public void handleFuelStack() {
    Random random = new Random();
    if (this.fireItemStacks[7] == null)
    {
      if (random.nextBoolean() && this.fireItemStacks[6] != null) {

        this.fireItemStacks[7] = this.fireItemStacks[6];
        this.fireItemStacks[6] = null;
      }
      else {

        this.fireItemStacks[7] = this.fireItemStacks[8];
        this.fireItemStacks[8] = null;
      }
    }

    if (this.fireItemStacks[6] == null)
    {
      if (this.fireItemStacks[5] != null) {

        this.fireItemStacks[6] = this.fireItemStacks[5];
        this.fireItemStacks[5] = null;
      }
    }

    if (this.fireItemStacks[8] == null)
    {
      if (this.fireItemStacks[9] != null) {

        this.fireItemStacks[8] = this.fireItemStacks[9];
        this.fireItemStacks[9] = null;
      }
    }
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.isSmokeStackValid = nbt.func_74767_n("isValid");

    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.fireItemStacks = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbt1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.fireItemStacks.length) {
        this.fireItemStacks[byte0] = ItemStack.func_77949_a(nbt1);
      }
    }
  }


  public void func_70299_a(int i, ItemStack itemstack) {
    this.fireItemStacks[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }



  public void func_145845_h() {
    this.isSmokeStackValid = validateSmokeStack();

    if (!this.field_145850_b.field_72995_K) {


      careForInventorySlot(this.fireItemStacks[0]);
      careForInventorySlot(this.fireItemStacks[1]);
      careForInventorySlot(this.fireItemStacks[2]);
      careForInventorySlot(this.fireItemStacks[3]);
      careForInventorySlot(this.fireItemStacks[4]);

      ItemStack[] fuelStack = new ItemStack[9];
      fuelStack[0] = this.fireItemStacks[5];
      fuelStack[1] = this.fireItemStacks[6];
      fuelStack[2] = this.fireItemStacks[7];
      fuelStack[3] = this.fireItemStacks[8];
      fuelStack[4] = this.fireItemStacks[9];
      fuelStack[5] = this.fireItemStacks[10];
      fuelStack[6] = this.fireItemStacks[11];
      fuelStack[7] = this.fireItemStacks[12];
      fuelStack[8] = this.fireItemStacks[13];


      cookItem(0);
      cookItem(1);
      cookItem(2);
      cookItem(3);
      cookItem(4);


      handleFuelStack();


      Random r = new Random();
      if (r.nextInt(10) == 0 && this.fireTemp > 20.0F) {
        this.field_145850_b.func_72908_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, "fire.fire", 0.4F + r.nextFloat() / 2.0F, 0.7F + r.nextFloat());
      }
      if (this.fireTemp >= 20.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1) {
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
      } else if (this.fireTemp < 20.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 0) {
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
      }

      if (this.fuelTimeLeft > 0 && this.fireTemp >= 1.0F && !TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {

        float desiredTemp = handleTemp();
        handleTempFlux(desiredTemp);
        this.smokeTimer++;
        if (this.smokeTimer > 60) {

          this.smokeTimer = 0;
          createSmoke();
        }
        if (TFCOptions.enableDebugMode) {

          this.fireTemp = 2000.0F;
          this.fuelTimeLeft = 9999;
        }

        TFC_Core.handleItemTicking(fuelStack, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
      else if (this.fuelTimeLeft <= 0 && this.fireTemp >= 1.0F && this.fireItemStacks[7] != null && this.isSmokeStackValid) {


        EnumFuelMaterial m = TFC_Core.getFuelMaterial(this.fireItemStacks[7]);
        this.fuelTimeLeft = m.burnTimeMax;
        this.fuelBurnTemp = m.burnTempMax;
        this.fuelTasteProfile = m.ordinal();
        this.fireItemStacks[7] = null;
      }
      else {

        removeSmoke();

        handleTempFlux(0.0F);
        TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }


      handleAirReduction();


      for (int c = 0; c < 5; c++) {

        if (this.fireItemStacks[c] != null)
        {
          if ((this.fireItemStacks[c]).field_77994_a <= 0) {
            (this.fireItemStacks[c]).field_77994_a = 1;
          }
        }
      }
    }
  }

  private void createSmoke() {
    if (!TFCOptions.generateSmoke) {
      return;
    }
    if (checkChimney(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e)) {
      genSmokeRoot(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e);
    } else if (checkChimney(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e)) {
      genSmokeRoot(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e);
    } else if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1)) {
      genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1);
    } else if (checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1)) {
      genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1);
    } else if (notOpaque(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e)) {
      genSmokeRoot(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e);
    } else if (notOpaque(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e) && checkChimney(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e)) {
      genSmokeRoot(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e);
    } else if (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2)) {
      genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2);
    } else if (notOpaque(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1) && checkChimney(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2)) {
      genSmokeRoot(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2);
    }
  }
  public void removeSmoke() {
    if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    } else if (isSmoke(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e)) {
      this.field_145850_b.func_147468_f(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e);
    } else if (isSmoke(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e)) {
      this.field_145850_b.func_147468_f(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e);
    } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1)) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1);
    } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1)) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1);
    } else if (isSmoke(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e)) {
      this.field_145850_b.func_147468_f(this.field_145851_c + 2, this.field_145848_d + 1, this.field_145849_e);
    } else if (isSmoke(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e)) {
      this.field_145850_b.func_147468_f(this.field_145851_c - 2, this.field_145848_d + 1, this.field_145849_e);
    } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2)) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 2);
    } else if (isSmoke(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2)) {
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 2);
    }
  }

  private boolean isSmoke(int x, int y, int z) {
    return (this.field_145850_b.func_72899_e(x, y, z) && this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.smoke);
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74757_a("isValid", this.isSmokeStackValid);

    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.fireItemStacks.length; i++) {

      if (this.fireItemStacks[i] != null) {

        NBTTagCompound nbt1 = new NBTTagCompound();
        nbt1.func_74774_a("Slot", (byte)i);
        this.fireItemStacks[i].func_77955_b(nbt1);
        nbttaglist.func_74742_a((NBTBase)nbt1);
      }
    }
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */