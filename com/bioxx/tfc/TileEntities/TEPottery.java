package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Items.Pottery.ItemPotteryBase;
import com.bioxx.tfc.api.Crafting.KilnCraftingManager;
import com.bioxx.tfc.api.Crafting.KilnRecipe;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;







public class TEPottery
  extends NetworkTileEntity
  implements IInventory
{
  public ItemStack[] inventory = new ItemStack[12];

  public boolean hasRack = false;
  public long burnStart;

  public boolean canAddItem(int slot) {
    if (this.inventory[0] != null && this.inventory[0].func_77973_b() instanceof net.minecraft.item.ItemBlock)
    {
      return false;
    }

    return (this.inventory[slot] == null);
  }
  public int straw;
  public int wood;

  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }

  private boolean isDone() {
    if (this.burnStart > 0L) {
      int hours = (int)(TFCOptions.pitKilnBurnTime - (float)(TFC_Time.getTotalHours() - this.burnStart / 1000L));
      float percent = Helper.roundNumber(100.0F - hours / TFCOptions.pitKilnBurnTime * 100.0F, 10.0F);
      return (percent >= 100.0F || hours < 0);
    }
    return false;
  }






  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K && this.straw > 0 && this.wood < 8)
    {


      if (isFireNear().booleanValue()) {

        ejectContents();
        this.field_145850_b.func_147475_p(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, Blocks.field_150350_a);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }

    if (!this.field_145850_b.field_72995_K && this.wood == 8) {

      Block blockAbove = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);


      if (this.burnStart == 0L && isFireNear().booleanValue()) {
        startPitFire();
      }

      if (blockAbove != Blocks.field_150480_ab && (float)(TFC_Time.getTotalTicks() - this.burnStart) <= 1000.0F * TFCOptions.pitKilnBurnTime)
      {
        if ((blockAbove.isAir((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).func_149688_o().func_76217_h()) && isValid()) {
          this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, (Block)Blocks.field_150480_ab);
        } else {

          this.wood = 0;
          this.inventory[4] = null; this.inventory[5] = null; this.inventory[6] = null; this.inventory[7] = null;
          this.inventory[8] = null; this.inventory[9] = null; this.inventory[10] = null; this.inventory[11] = null;
          this.straw = 0;
          this.burnStart = 0L;
        }
      }


      if ((blockAbove == Blocks.field_150480_ab && (float)TFC_Time.getTotalTicks() >= (float)this.burnStart + TFCOptions.pitKilnBurnTime * 1000.0F) || isDone()) {

        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        if (this.inventory[0] != null) {

          this.inventory[0] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[0], 0)).func_77946_l();
          if (this.inventory[0].func_77973_b() instanceof ItemPotteryBase)
            ((ItemPotteryBase)this.inventory[0].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[0], Alloy.EnumTier.TierI);
        }
        if (this.inventory[1] != null) {

          this.inventory[1] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[1], 0)).func_77946_l();
          if (this.inventory[1].func_77973_b() instanceof ItemPotteryBase)
            ((ItemPotteryBase)this.inventory[1].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[1], Alloy.EnumTier.TierI);
        }
        if (this.inventory[2] != null) {

          this.inventory[2] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[2], 0)).func_77946_l();
          if (this.inventory[2].func_77973_b() instanceof ItemPotteryBase)
            ((ItemPotteryBase)this.inventory[2].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[2], Alloy.EnumTier.TierI);
        }
        if (this.inventory[3] != null) {

          this.inventory[3] = KilnCraftingManager.getInstance().findCompleteRecipe(new KilnRecipe(this.inventory[3], 0)).func_77946_l();
          if (this.inventory[3].func_77973_b() instanceof ItemPotteryBase) {
            ((ItemPotteryBase)this.inventory[3].func_77973_b()).onDoneCooking(this.field_145850_b, this.inventory[3], Alloy.EnumTier.TierI);
          }
        }
        this.wood = 0;
        this.inventory[4] = null; this.inventory[5] = null; this.inventory[6] = null; this.inventory[7] = null;
        this.inventory[8] = null; this.inventory[9] = null; this.inventory[10] = null; this.inventory[11] = null;
        this.straw = 0;
        this.burnStart = 0L;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }
  }


  private Boolean isFireNear() {
    Boolean foundFire = Boolean.valueOf(false);

    for (int x = -1; x <= 1; x++) {

      for (int z = -1; z <= 1; z++) {

        if (this.field_145850_b.func_72899_e(this.field_145851_c + x, this.field_145848_d + 1, this.field_145849_e + z) && this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + 1, this.field_145849_e + z) == Blocks.field_150480_ab) {
          foundFire = Boolean.valueOf(true);
        }
      }
    }
    return foundFire;
  }


  public void startPitFire() {
    if (this.straw == 8 && this.wood == 8) {

      this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, (Block)Blocks.field_150480_ab);
      this.burnStart = TFC_Time.getTotalTicks();
    }
  }


  public boolean addLog(ItemStack is, EntityPlayer player) {
    if (this.wood < 8)
    {
      if (!player.field_71075_bZ.field_75098_d) {

        for (int i = 4; i < 12; i++) {

          if (this.inventory[i] == null)
          {
            this.wood++;
            ItemStack itemStack = is.func_77946_l();
            is.field_77994_a--;
            itemStack.field_77994_a = 1;
            func_70299_a(i, itemStack);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
          }

        }
      } else {

        for (int i = 4; i < 12; i++) {

          if (this.inventory[i] == null) {

            this.wood++;
            ItemStack itemStack = is.func_77946_l();
            itemStack.field_77994_a = 1;
            func_70299_a(i, itemStack);
          }
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return true;
      }
    }
    return false;
  }


  public void addStraw(ItemStack is, EntityPlayer player) {
    if (this.straw < 8) {

      if (!player.field_71075_bZ.field_75098_d) {

        this.straw++;
        is.field_77994_a--;
      }
      else {

        this.straw = 8;
      }
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
  }





  public boolean isValid() {
    boolean surroundSolids = (TFC_Core.isNorthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) && TFC_Core.isSouthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) && TFC_Core.isEastFaceSolid(this.field_145850_b, this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) && TFC_Core.isWestFaceSolid(this.field_145850_b, this.field_145851_c + 1, this.field_145848_d, this.field_145849_e));
    return (surroundSolids && this.field_145850_b.isSideSolid(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ForgeDirection.UP));
  }


  public boolean isLit() {
    return (TFC_Time.getTotalTicks() > this.burnStart && (float)(TFC_Time.getTotalTicks() - this.burnStart) < 1000.0F * TFCOptions.pitKilnBurnTime);
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    this.inventory[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }

  public void ejectContents() {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F;
    float f1 = rand.nextFloat() * 0.4F;
    float f2 = rand.nextFloat() * 0.8F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.inventory[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.inventory[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
        this.inventory[i] = null;
      }
    }

    if (this.straw > 0) {

      EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), new ItemStack(TFCItems.straw, this.straw));
      entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
      entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
      entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
      this.field_145850_b.func_72838_d((Entity)entityitem);
    }
  }





  public void ejectItem(int index) {
    if (this.inventory[index] != null) {

      EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, this.inventory[index]);
      entityitem.lifespan = 48000;
      this.field_145850_b.func_72838_d((Entity)entityitem);
      this.inventory[index] = null;
    }

    if (this.inventory[0] == null && this.inventory[1] == null && this.inventory[2] == null && this.inventory[3] == null) {


      int m = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      if (m > 0) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, new ItemStack(TFCItems.straw, m));
        entityitem.lifespan = 48000;
        this.field_145850_b.func_72838_d((Entity)entityitem);
      }
      this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}



  public int func_70302_i_() {
    return this.inventory.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.inventory[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public ItemStack func_70298_a(int i, int j) {
    return null;
  }



  public String func_145825_b() {
    return "Pottery";
  }



  public int func_70297_j_() {
    return 1;
  }




  public void func_70305_f() {}



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.inventory.length; i++) {

      if (this.inventory[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.inventory[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    nbttagcompound.func_74772_a("burnStart", this.burnStart);
    nbttagcompound.func_74757_a("hasRack", this.hasRack);
    nbttagcompound.func_74768_a("wood", this.wood);
    nbttagcompound.func_74768_a("straw", this.straw);
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.inventory = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.inventory.length)
        this.inventory[byte0] = ItemStack.func_77949_a(nbttagcompound1);
    }
    this.burnStart = nbt.func_74763_f("burnStart");
    this.wood = nbt.func_74762_e("wood");
    this.hasRack = nbt.func_74767_n("hasRack");
    this.straw = nbt.func_74762_e("straw");
  }



  public void handleInitPacket(NBTTagCompound nbt) {
    func_145839_a(nbt);
  }





  public void handleDataPacket(NBTTagCompound nbt) {}





  public void createDataNBT(NBTTagCompound nbt) {}





  public void createInitNBT(NBTTagCompound nbt) {
    func_145841_b(nbt);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEPottery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */