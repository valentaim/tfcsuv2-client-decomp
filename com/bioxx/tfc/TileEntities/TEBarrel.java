package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Crafting.BarrelAlcoholRecipe;
import com.bioxx.tfc.api.Crafting.BarrelBriningRecipe;
import com.bioxx.tfc.api.Crafting.BarrelLiquidToLiquidRecipe;
import com.bioxx.tfc.api.Crafting.BarrelManager;
import com.bioxx.tfc.api.Crafting.BarrelMultiItemRecipe;
import com.bioxx.tfc.api.Crafting.BarrelPreservativeRecipe;
import com.bioxx.tfc.api.Crafting.BarrelRecipe;
import com.bioxx.tfc.api.Crafting.BarrelVinegarRecipe;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.zerren.extrafirma.items.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fof.tfcsu.utils.ExpBonus;
import growthcraft.cellar.GrowthCraftCellar;
import java.util.Random;
import java.util.Stack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;





public class TEBarrel
  extends NetworkTileEntity
  implements IInventory
{
  public FluidStack fluid;
  public byte rotation;
  public int barrelType;
  public int mode;
  public ItemStack[] storage;
  private boolean sealed;
  public int sealtime;
  public int unsealtime;
  private int processTimer;
  public static final int MODE_IN = 0;
  public static final int MODE_OUT = 1;
  public static final int INPUT_SLOT = 0;
  public BarrelRecipe recipe;
  public boolean shouldDropItem = true;
  private Boolean needCheck;
  private boolean hasTemp;
  private boolean hasExp;

  public TEBarrel() {
    this.storage = new ItemStack[12];
  }


  public boolean getSealed() {
    return this.sealed;
  }


  public int getTechLevel() {
    return 1;
  }


  public void clearInventory() {
    this.storage = new ItemStack[12];
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public void setSealed() {
    this.sealed = true;
  }


  public void setUnsealed(String reason) {
    if ("killing fuse".equals(reason)) {
      this.sealed = false;
    }
  }



  public void func_70305_f() {}



  public ItemStack func_70298_a(int i, int j) {
    if (this.storage[i] != null) {

      if ((this.storage[i]).field_77994_a <= j) {

        ItemStack is = this.storage[i];
        this.storage[i] = null;
        return is;
      }
      ItemStack isSplit = this.storage[i].func_77979_a(j);
      if ((this.storage[i]).field_77994_a == 0)
        this.storage[i] = null;
      return isSplit;
    }


    return null;
  }



  public void ejectContents() {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.3F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.3F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.storage[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
      }
    }
  }



  public int func_70297_j_() {
    return 64;
  }



  public String func_145825_b() {
    return "Barrel";
  }



  public int func_70302_i_() {
    return 12;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public ItemStack func_70304_b(int i) {
    return this.storage[i];
  }


  public int getInvCount() {
    int count = 0;
    for (ItemStack is : this.storage) {

      if (is != null)
        count++;
    }
    if (this.storage[0] != null && count == 1)
      return 0;
    return count;
  }


  public int getGunPowderCount() {
    int count = 0;
    for (ItemStack is : this.storage) {

      if (is != null && is.func_77973_b() == Items.field_151016_H)
        count += is.field_77994_a;
    }
    return count;
  }


  public boolean canAcceptLiquids() {
    return (getInvCount() == 0);
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }






  public void func_70295_k_() {}






  public void func_70299_a(int i, ItemStack is) {
    boolean check = true;
    if (this.storage[i] != null && is != null &&
      this.storage[i].func_77973_b().equals(is.func_77973_b()) && TFC_ItemHeat.hasTemp(this.storage[i]) == TFC_ItemHeat.hasTemp(is)) check = false;

    if (!ItemStack.func_77989_b(this.storage[i], is)) {


      this.storage[i] = is;
      if (i == 0) {

        processItems();
        if (!getSealed())
          this.unsealtime = (int)TFC_Time.getTotalHours();
      }
    }
    if (check) checkItems();

  }

  public int getFluidLevel() {
    if (this.fluid != null)
      return this.fluid.amount;
    return 0;
  }


  public ItemStack getInputStack() {
    return this.storage[0];
  }


  public FluidStack getFluidStack() {
    return this.fluid;
  }


  public int getMaxLiquid() {
    return 10000;
  }


  public boolean addLiquid(FluidStack inFS) {
    if (inFS != null) {


      if (inFS.getFluid() != null && inFS.getFluid().getTemperature(inFS) > 385) {
        return false;
      }
      if (this.fluid == null) {

        this.fluid = inFS.copy();
        if (this.fluid.amount > getMaxLiquid())
        {
          this.fluid.amount = getMaxLiquid();
          inFS.amount -= getMaxLiquid();
        }
        else
        {
          inFS.amount = 0;
        }

      } else {

        if (this.fluid.amount == getMaxLiquid() || !this.fluid.isFluidEqual(inFS)) {
          return false;
        }
        int a = this.fluid.amount + inFS.amount - getMaxLiquid();
        this.fluid.amount = Math.min(this.fluid.amount + inFS.amount, getMaxLiquid());
        if (a > 0) {
          inFS.amount = a;
        } else {
          inFS.amount = 0;
        }
      }  this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      return true;
    }

    return false;
  }


  public ItemStack addLiquid(ItemStack is) {
    if (is == null || is.field_77994_a > 1)
      return is;
    if (FluidContainerRegistry.isFilledContainer(is)) {

      FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(is);

      if (is.func_77973_b().func_77658_a().toLowerCase().contains("soup") &&
        is.func_77973_b() instanceof straywolfe.cookingwithtfc.common.item.ItemTFCMealTransform) {

        float weight = Food.getWeight(is);


        fs.amount = (int)(weight * fs.amount) / 10;
      }



      if (addLiquid(fs))
      {
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return FluidContainerRegistry.drainFluidContainer(is);
      }

    } else if (is.func_77973_b() instanceof IFluidContainerItem) {

      FluidStack isfs = ((IFluidContainerItem)is.func_77973_b()).getFluid(is);

      if (isfs != null && addLiquid(isfs)) {

        ((IFluidContainerItem)is.func_77973_b()).drain(is, is.func_77958_k(), true);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }
    return is;
  }





  public ItemStack removeLiquid(ItemStack is) {
    if (is == null || is.field_77994_a > 1)
      return is;
    if (FluidContainerRegistry.isEmptyContainer(is)) {

      ItemStack out = FluidContainerRegistry.fillFluidContainer(this.fluid, is);
      if (out != null)
      {
        FluidStack fs = FluidContainerRegistry.getFluidForFilledItem(out);
        this.fluid.amount -= fs.amount;
        is = null;
        if (this.fluid.amount == 0)
        {
          this.fluid = null;
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return out;
      }

    } else if (this.fluid != null && is.func_77973_b() instanceof IFluidContainerItem) {

      FluidStack isfs = ((IFluidContainerItem)is.func_77973_b()).getFluid(is);
      if (isfs == null || this.fluid.isFluidEqual(isfs)) {

        this.fluid.amount -= ((IFluidContainerItem)is.func_77973_b()).fill(is, this.fluid, true);
        if (this.fluid.amount == 0)
          this.fluid = null;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }
    return is;
  }





  public void drainLiquid(int amount) {
    if (!getSealed() && getFluidStack() != null) {

      (getFluidStack()).amount -= amount;
      if ((getFluidStack()).amount <= 0) {
        actionEmpty();
      } else {
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }
  }

  public int getLiquidScaled(int i) {
    if (this.fluid != null)
      return this.fluid.amount * i / getMaxLiquid();
    return 0;
  }


  public boolean actionSeal(int tab, EntityPlayer player) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74757_a("seal", true);
    nbt.func_74774_a("tab", (byte)tab);
    nbt.func_74778_a("player", player.func_70005_c_());
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
    this.sealed = true;
    this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    return true;
  }


  public boolean actionUnSeal(int tab, EntityPlayer player) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74757_a("seal", false);
    nbt.func_74774_a("tab", (byte)tab);
    nbt.func_74778_a("player", player.func_70005_c_());
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
    this.sealed = false;
    this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    return true;
  }


  public void actionEmpty() {
    this.fluid = null;
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74774_a("fluidID", (byte)-1);
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }


  public void actionMode() {
    this.mode = (this.mode == 0) ? 1 : 0;
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74774_a("mode", (byte)this.mode);
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }


  public void actionSwitchTab(int tab, EntityPlayer player) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74774_a("tab", (byte)tab);
    nbt.func_74778_a("player", player.func_70005_c_());
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74757_a("hasExp", this.hasExp);
    nbt.func_74757_a("Sealed", this.sealed);
    nbt.func_74768_a("SealTime", this.sealtime);
    nbt.func_74768_a("barrelType", this.barrelType);

    NBTTagCompound fluidNBT = new NBTTagCompound();
    if (this.fluid != null)
      this.fluid.writeToNBT(fluidNBT);
    nbt.func_74782_a("fluidNBT", (NBTBase)fluidNBT);
    nbt.func_74774_a("rotation", this.rotation);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.storage.length; i++) {

      if (this.storage[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
    this.sealed = nbt.func_74767_n("Sealed");
    this.sealtime = nbt.func_74762_e("SealTime");
    this.barrelType = nbt.func_74762_e("barrelType");

    this.rotation = nbt.func_74771_c("rotation");
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.storage = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }


  public void readFromItemNBT(NBTTagCompound nbt) {
    this.barrelType = nbt.func_74762_e("barrelType");
    this.fluid = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("fluidNBT"));
    this.hasExp = nbt.func_74767_n("hasExp");
    this.sealed = nbt.func_74767_n("Sealed");
    this.sealtime = nbt.func_74762_e("SealTime");
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbt1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        func_70299_a(byte0, ItemStack.func_77949_a(nbt1));
      }
    }
  }

  public void updateGui() {
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }





  public void handleInitPacket(NBTTagCompound nbt) {
    this.rotation = nbt.func_74771_c("rotation");
    this.sealed = nbt.func_74767_n("sealed");
    this.sealtime = nbt.func_74762_e("SealTime");
    this.barrelType = nbt.func_74762_e("barrelType");
    if (nbt.func_74762_e("fluid") != -1) {

      if (this.fluid != null) {
        this.fluid.amount = nbt.func_74762_e("fluidAmount");
      } else {
        this.fluid = new FluidStack(nbt.func_74762_e("fluid"), nbt.func_74762_e("fluidAmount"));
      }
    } else {

      this.fluid = null;
    }
    this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74774_a("rotation", this.rotation);
    nbt.func_74757_a("sealed", this.sealed);
    nbt.func_74768_a("SealTime", this.sealtime);
    nbt.func_74768_a("fluid", (this.fluid != null) ? this.fluid.getFluidID() : -1);
    nbt.func_74768_a("fluidAmount", (this.fluid != null) ? this.fluid.amount : 0);
    nbt.func_74768_a("barrelType", this.barrelType);
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    if (nbt.func_74764_b("fluidID")) {

      if (nbt.func_74771_c("fluidID") == -1)
        this.fluid = null;
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
    if (!this.field_145850_b.field_72995_K) {

      if (nbt.func_74764_b("mode")) {

        this.mode = nbt.func_74771_c("mode");
      }
      else if (nbt.func_74764_b("seal")) {

        this.sealed = nbt.func_74767_n("seal");
        if (!this.sealed) {

          this.unsealtime = (int)TFC_Time.getTotalHours();
          this.sealtime = 0;

          if (this.hasExp) {
            ExpBonus.BARREL.give(this.entityplayer);
            this.hasExp = false;
          }

        } else {

          this.sealtime = (int)TFC_Time.getTotalHours();
          this.unsealtime = 0;
        }


        NBTTagCompound timeTag = new NBTTagCompound();
        timeTag.func_74768_a("SealTime", this.sealtime);
        broadcastPacketInRange((AbstractPacket)createDataPacket(timeTag));

        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }

      if (nbt.func_74764_b("tab"))
      {
        int tab = nbt.func_74771_c("tab");
        switchTab(this.field_145850_b.func_72924_a(nbt.func_74779_i("player")), tab);

      }


    }
    else if (nbt.func_74764_b("SealTime")) {
      this.sealtime = nbt.func_74762_e("SealTime");
    }
  }


  protected void switchTab(EntityPlayer player, int tab) {
    if (player != null)
      if (tab == 0) {
        player.openGui(TerraFirmaCraft.instance, 35, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      } else if (tab == 1) {
        player.openGui(TerraFirmaCraft.instance, 36, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
  }
  private void checkItems() {
    for (int i = 0; i < func_70302_i_(); i++) {
      ItemStack is = func_70301_a(i);
      if (is != null) {
        if (TFC_ItemHeat.hasTemp(is)) {
          this.needCheck = Boolean.valueOf(true);
          this.hasTemp = true;
          return;
        }
        Item item = is.func_77973_b();
        if (item instanceof IFood || item instanceof ItemFoodTFC || item.equals(ModItems.leatherBag)) {
          this.needCheck = Boolean.valueOf(true);
          return;
        }
      }
    }
    this.hasTemp = false;
    this.needCheck = Boolean.valueOf(false);
  }




  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {

      if (this.needCheck == null) checkItems();
      ItemStack itemstack = this.storage[0];
      if (itemstack != null && this.fluid != null && this.fluid.getFluid() == TFCFluids.FRESHWATER)
      {
        if (TFC_ItemHeat.hasTemp(itemstack)) {

          float temp = TFC_ItemHeat.getTemp(itemstack);
          if (this.fluid.amount >= 1 && temp > 1.0F) {

            temp -= 50.0F;
            this.fluid.amount--;
            TFC_ItemHeat.setTemp(itemstack, temp);
            TFC_ItemHeat.handleItemHeat(itemstack);
          }
        }
      }
      if (itemstack != null && this.fluid != null && itemstack.func_77973_b() instanceof IFood) {

        float w = Food.getWeight(itemstack);
        if (this.fluid.getFluid() == TFCFluids.VINEGAR)
        {

          if (Food.isBrined(itemstack) && !Food.isPickled(itemstack) && w / this.fluid.amount <= 160.0F / getMaxLiquid() && getSealed() && this.sealtime != 0 &&
            TFC_Time.getTotalHours() - this.sealtime >= 4L) {

            this.fluid.amount = (int)(this.fluid.amount - 1.0F * w);
            Food.setPickled(itemstack, true);
            this.hasExp = true;
          }
        }
      }

      BarrelPreservativeRecipe preservative = BarrelManager.getInstance().findMatchingPreservativeRepice(this, itemstack, this.fluid, this.sealed);
      if (preservative == null) {



        if (this.needCheck.booleanValue()) TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        if (this.hasTemp && this.field_145850_b.func_82737_E() % 600L == 0L) checkItems();
        if (!this.needCheck.booleanValue() && this.field_145850_b.func_82737_E() % 1200L == 0L) checkItems();

      }
      else {

        float env = preservative.getEnvironmentalDecayFactor();
        float base = preservative.getBaseDecayModifier();
        if (Float.isNaN(env) || env < 0.0D) {

          TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        else if (Float.isNaN(base) || base < 0.0D) {

          TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env);
        }
        else {

          TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, env, base);
        }
      }

      boolean shipsworld = false;
      if (TerraFirmaCraft.instance.ShipsExist) shipsworld = this.field_145850_b instanceof cuchaz.ships.ShipWorld;

      if (!shipsworld && !getSealed()) {
        boolean isfillwater = (this.fluid != null && this.fluid.getFluid() == TFCFluids.FRESHWATER);
        if (this.fluid == null || (isfillwater && this.fluid.amount < getMaxLiquid()))
        {
          if (TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {

            int count = getInvCount();
            if (count == 0 || (count == 1 && getInputStack() != null))
            {
              if (this.fluid == null) { this.fluid = new FluidStack(TFCFluids.FRESHWATER, 1); }
              else if (isfillwater)
              { this.fluid.amount = Math.min(this.fluid.amount + 1, getMaxLiquid()); }

            }
          }
        }
      }


      this.processTimer++;
      if (this.processTimer > 100) {

        processItems();
        this.processTimer = 0;
      }




      if (getFluidLevel() > 0 && getInputStack() != null) {

        int count = 1;
        int max_stack_size = getInputStack().func_77976_d();
        while ((getInputStack()).field_77994_a > max_stack_size)
        {
          ItemStack is = getInputStack().func_77979_a(max_stack_size);
          if (count < this.storage.length && func_70301_a(count) == null) { func_70299_a(count, is); }
          else { this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, is)); }
           count++;

        }

      }
      else if (getFluidLevel() > 0 && getInputStack() == null && getInvCount() > 0) {

        for (int i = 0; i < this.storage.length; i++) {

          if (this.storage[i] != null) {

            this.storage[0] = this.storage[i].func_77946_l();
            this.storage[i] = null;


            break;
          }
        }
      }

      if (this.fluid != null && this.fluid.amount == 0) {
        this.fluid = null;
      }

      if (this.mode == 0) {

        ItemStack container = getInputStack();
        FluidStack inLiquid = FluidContainerRegistry.getFluidForFilledItem(container);

        if (container != null && container.func_77973_b() instanceof IFluidContainerItem)

        { FluidStack isfs = ((IFluidContainerItem)container.func_77973_b()).getFluid(container);
          if (container.func_77973_b().func_77658_a().toLowerCase().contains("waterbag") && isfs != null)
          {
            int dosage = Math.min(isfs.amount, (GrowthCraftCellar.getConfig()).waterBagDosage);
            int amount = 0;
            if (this.fluid == null) { this.fluid = isfs.copy(); this.fluid.amount = dosage; }
            else if (this.fluid.getFluid() == isfs.getFluid())

            { int a = getFluidLevel() + dosage - getMaxLiquid();
              this.fluid.amount = Math.min(getFluidLevel() + dosage, getMaxLiquid());
              if (a > 0) amount = a;  }
            else
            { return; }
             ((IFluidContainerItem)container.func_77973_b()).drain(container, dosage - amount, true);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
          }
          else if (isfs != null && addLiquid(isfs))
          {
            ((IFluidContainerItem)container.func_77973_b()).drain(container, ((IFluidContainerItem)container.func_77973_b()).getCapacity(container), true);
          }
           }

        else if (inLiquid != null && container != null && container.field_77994_a == 1 &&
          addLiquid(inLiquid)) { func_70299_a(0, FluidContainerRegistry.drainFluidContainer(container)); }


      } else if (this.mode == 1) {

        ItemStack container = getInputStack();

        if (this.fluid != null && this.fluid.amount > 0) {
          int ln = checkLantern(container);
          if (ln > 1)
            return;
        }
        if (container != null && this.fluid != null && container.func_77973_b() instanceof IFluidContainerItem) {

          FluidStack isfs = ((IFluidContainerItem)container.func_77973_b()).getFluid(container);
          if (isfs == null || this.fluid.isFluidEqual(isfs)) {

            this.fluid.amount -= ((IFluidContainerItem)container.func_77973_b()).fill(container, this.fluid, true);
            if (this.fluid.amount == 0) this.fluid = null;

          }
        } else if (FluidContainerRegistry.isEmptyContainer(container)) {

          ItemStack fullContainer = removeLiquid(getInputStack());
          if (fullContainer.func_77973_b() == TFCItems.woodenBucketMilk) ItemCustomBucketMilk.createTag(fullContainer, 20.0F);
          func_70299_a(0, fullContainer);
        }
      }
    }
  }

  private int checkLantern(ItemStack is) {
    if (is != null && (
      is.func_77977_a().toLowerCase().contains("lante") || is.func_77973_b().getClass().getSimpleName().toLowerCase().contains("lante"))) {
      return is.field_77994_a;
    }
    return -1;
  }

































  public void processItems() {
    if (getInvCount() == 0) {



      boolean isCheese = false;

      if (getFluidStack() != null && !isCheese)

      { this.recipe = BarrelManager.getInstance().findMatchingRecipe(getInputStack(), getFluidStack(), this.sealed, getTechLevel());







        if (this.recipe != null && !this.field_145850_b.field_72995_K) {

          int time = 0;
          if (this.sealtime > 0) {
            time = (int)TFC_Time.getTotalHours() - this.sealtime;
          } else if (this.unsealtime > 0) {
            time = (int)TFC_Time.getTotalHours() - this.unsealtime;
          }

          if (this.recipe.isSealedRecipe() && time < this.recipe.sealTime) {
            return;
          }
          ItemStack origIS = (getInputStack() != null) ? getInputStack().func_77946_l() : null;
          FluidStack origFS = (getFluidStack() != null) ? getFluidStack().copy() : null;
          if (this.fluid.isFluidEqual(this.recipe.getResultFluid(origIS, origFS, time)) && this.recipe.removesLiquid) {

            this.hasExp = true;
            if (this.fluid.getFluid() == TFCFluids.BRINE && origIS != null && origIS.func_77973_b() instanceof IFood) {
              this.fluid.amount = (int)(this.fluid.amount - (this.recipe.getResultFluid(origIS, origFS, time)).amount * Food.getWeight(origIS));
            } else {
              this.fluid.amount -= (this.recipe.getResultFluid(origIS, origFS, time)).amount;
            }
          } else {

            this.fluid = this.recipe.getResultFluid(origIS, origFS, time).copy();
            this.hasExp = true;
            if (this.fluid != null && !(this.recipe instanceof BarrelLiquidToLiquidRecipe) && origFS != null) {
              this.fluid.amount = origFS.amount;
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
          }

          if (origFS != null && origFS.getFluid() != TFCFluids.MILKCURDLED && this.fluid.getFluid() == TFCFluids.MILKCURDLED) {
            this.sealtime = (int)TFC_Time.getTotalHours();
          }
          Stack<ItemStack> resultStacks = this.recipe.getResult(origIS, origFS, time);
          if (!resultStacks.isEmpty())
          {
            ItemStack result = resultStacks.pop();
            if (this.fluid != null && this.fluid.getFluid() == TFCFluids.BRINE) {

              if (result == null && origIS != null)
                result = origIS.func_77946_l();
              if (result != null && result.func_77973_b() instanceof IFood && (result.func_77973_b() == TFCItems.cheese || ((IFood)result.func_77973_b()).getFoodGroup() != EnumFoodGroup.Grain))
              {
                if (!Food.isBrined(result)) {
                  Food.setBrined(result, true);
                  this.hasExp = true;
                }
              }
            }

            this.storage[0] = result;

            for (int i = 1; i < this.storage.length; i++) {

              if (this.storage[i] == null && !resultStacks.isEmpty()) {
                func_70299_a(i, resultStacks.pop());
              }
            }
            while (!resultStacks.isEmpty()) {
              this.field_145850_b.func_72838_d((Entity)new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, resultStacks.pop()));
            }
            func_70299_a(0, result);
          }

        }  }
      else if (getFluidStack() == null && !isCheese) { this.recipe = null; }

    }
  }































































  public static void registerRecipes() {
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.potato), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.VODKA, 10000)));


    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.wheatGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.WHISKEY, 10000)));
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.ryeGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RYEWHISKEY, 10000)));


    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.sugar), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.RUM, 10000)));
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelAlcoholRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.cornmealGround), 160.0F), new FluidStack(TFCFluids.FRESHWATER, 10000), null, new FluidStack(TFCFluids.CORNWHISKEY, 10000)));


    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 3), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 4), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 5), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 6), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.logs, 1, 9), new FluidStack(TFCFluids.FRESHWATER, 1000), null, new FluidStack(TFCFluids.TANNIN, 1000))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCItems.powder, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 500), null, new FluidStack(TFCFluids.LIMEWATER, 500), 0)).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(false).setAllowAnyStack(false));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 300), new ItemStack(TFCItems.prepHide, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 300))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 1), new FluidStack(TFCFluids.FRESHWATER, 400), new ItemStack(TFCItems.prepHide, 1, 1), new FluidStack(TFCFluids.FRESHWATER, 400))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.scrapedHide, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 500), new ItemStack(TFCItems.prepHide, 1, 2), new FluidStack(TFCFluids.FRESHWATER, 500))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 300), new ItemStack(TFCItems.soakedHide, 1, 0), new FluidStack(TFCFluids.LIMEWATER, 300))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 1), new FluidStack(TFCFluids.LIMEWATER, 400), new ItemStack(TFCItems.soakedHide, 1, 1), new FluidStack(TFCFluids.LIMEWATER, 400))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.hide, 1, 2), new FluidStack(TFCFluids.LIMEWATER, 500), new ItemStack(TFCItems.soakedHide, 1, 2), new FluidStack(TFCFluids.LIMEWATER, 500))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 0), new FluidStack(TFCFluids.TANNIN, 300), new ItemStack(TFCItems.leather, 1), new FluidStack(TFCFluids.TANNIN, 300))).setKeepStackSize(false).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 1), new FluidStack(TFCFluids.TANNIN, 400), new ItemStack(TFCItems.leather, 2), new FluidStack(TFCFluids.TANNIN, 400))).setKeepStackSize(false).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.prepHide, 1, 2), new FluidStack(TFCFluids.TANNIN, 500), new ItemStack(TFCItems.leather, 3), new FluidStack(TFCFluids.TANNIN, 500))).setKeepStackSize(false).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCBlocks.sand, 1, 32767), new FluidStack(TFCFluids.LIMEWATER, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(TFCFluids.LIMEWATER, 100))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelRecipe(new ItemStack(TFCBlocks.sand2, 1, 32767), new FluidStack(TFCFluids.LIMEWATER, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(TFCFluids.LIMEWATER, 100))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.VODKA, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.CIDER, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.WHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.RYEWHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));


    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.RUM, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
    BarrelManager.getInstance().addRecipe((BarrelRecipe)new BarrelVinegarRecipe(new FluidStack(TFCFluids.CORNWHISKEY, 100), new FluidStack(TFCFluids.VINEGAR, 100)));
    BarrelManager.getInstance().addRecipe((new BarrelLiquidToLiquidRecipe(new FluidStack(TFCFluids.SALTWATER, 9000), new FluidStack(TFCFluids.VINEGAR, 1000), new FluidStack(TFCFluids.BRINE, 10000))).setSealedRecipe(false).setMinTechLevel(0).setRemovesLiquid(false));
    BarrelManager.getInstance().addRecipe((new BarrelBriningRecipe(new FluidStack(TFCFluids.BRINE, 60), new FluidStack(TFCFluids.BRINE, 60))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(ItemFoodTFC.createTag(new ItemStack(TFCItems.sugarcane), 1.0F), new FluidStack(TFCFluids.FRESHWATER, 60), ItemFoodTFC.createTag(new ItemStack(TFCItems.sugar), 0.1F), new FluidStack(TFCFluids.FRESHWATER, 60))).setMinTechLevel(0));
    BarrelManager.getInstance().addRecipe((new BarrelMultiItemRecipe(new ItemStack(TFCItems.jute, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 155), new ItemStack(TFCItems.juteFiber, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 155))).setMinTechLevel(0));





    BarrelPreservativeRecipe picklePreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.VINEGAR, 31), "gui.barrel.preserving")).setAllowGrains(false).setRequiresPickled(true).setEnvironmentalDecayFactor(0.25F).setBaseDecayModifier(0.1F).setRequiresSealed(true);
    BarrelPreservativeRecipe brineInBrinePreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.BRINE, 62), "gui.barrel.preserving")).setAllowGrains(false).setRequiresBrined(true).setEnvironmentalDecayFactor(0.75F).setRequiresSealed(true);
    BarrelPreservativeRecipe brineInVinegarPreservative = (new BarrelPreservativeRecipe(new FluidStack(TFCFluids.VINEGAR, 62), "gui.barrel.preserving")).setAllowGrains(false).setRequiresBrined(true).setEnvironmentalDecayFactor(0.75F).setRequiresSealed(true);
    BarrelManager.getInstance().addPreservative(picklePreservative);
    BarrelManager.getInstance().addPreservative(brineInBrinePreservative);
    BarrelManager.getInstance().addPreservative(brineInVinegarPreservative);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */