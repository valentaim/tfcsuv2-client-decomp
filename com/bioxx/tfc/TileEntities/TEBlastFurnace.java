package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Blocks.Devices.BlockBlastFurnace;
import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.GUI.GuiBlastFurnace;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import cpw.mods.fml.client.FMLClientHandler;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import mods.railcraft.common.items.RailcraftToolItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;

































public class TEBlastFurnace
  extends TEFireEntity
  implements IInventory
{
  public boolean isValid = false;
  public ItemStack[] fireItemStacks = new ItemStack[20];
  public ItemStack[] outputItemStacks = new ItemStack[20];
  public ItemStack[] storage = new ItemStack[2];


  public int charcoalCount = 0;
  public int oreCount = 0;
  public static final int ORE_SLOT1 = 0;
  public String oreType;
  public int slowCounter;

  public boolean canLight() {
    if (!this.field_145850_b.field_72995_K) {

      if (this.charcoalCount < this.oreCount) {
        return false;
      }


      int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      if (this.charcoalCount >= 4 && this.fireTemp == 0.0F) {

        this.fireTemp = 1.0F;
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta + 4, 2);
        return true;
      }
    }
    return false;
  }
  private int outMetal1Count; private int cookDelay; private int maxValidStackSize; private int moltenCount;

  private Boolean checkValidity() {
    int y = this.field_145848_d + 1;
    if (isStackValid(this.field_145851_c, y, this.field_145849_e))
      return Boolean.valueOf(true);
    return Boolean.valueOf(false);
  }



  public void func_70305_f() {}



  public void cookItem(int i) {
    ItemStack cookingItemStack = this.fireItemStacks[i];

    TECrucible crucibleTE = (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TECrucible) ? (TECrucible)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) : null;





    if (cookingItemStack != null && crucibleTE != null && crucibleTE.getTotalMetal() < 3000.0F && this.storage[1] != null && this.cookDelay == 0) {


      Random r = new Random();
      HeatRegistry manager = HeatRegistry.getInstance();
      HeatIndex index = manager.findMatchingIndex(cookingItemStack);

      if (index != null && TFC_ItemHeat.getTemp(cookingItemStack) >= index.meltTemp) {

        int output = 0;
        Item cookingItem = cookingItemStack.func_77973_b();

        if (cookingItem instanceof ISmeltable) {

          output = ((ISmeltable)cookingItem).getMetalReturnAmount(cookingItemStack);

          if (!crucibleTE.addMetal(((ISmeltable)cookingItem).getMetalType(cookingItemStack), output)) {
            return;
          }
        } else {

          Metal m = MetalRegistry.instance.getMetalFromItem(cookingItem);
          output = index.getOutput(cookingItemStack, r).func_77960_j();
          if (m != null)
          {

            if (!crucibleTE.addMetal(m, (short)(100 - output))) {
              return;
            }
          }
        }
        this.oreCount--;
        this.charcoalCount--;
        this.cookDelay = 100;
        this.fireItemStacks[i] = null;





        Queue<ItemStack> buffer = new ArrayBlockingQueue<>(this.fireItemStacks.length);
        for (ItemStack is : this.fireItemStacks) {

          if (is != null)
          {
            buffer.offer(is);
          }
        }

        this.fireItemStacks = (ItemStack[])buffer.toArray((Object[])new ItemStack[this.fireItemStacks.length]);


        this.storage[1].func_77964_b(this.storage[1].func_77960_j() + 1);
        if (this.storage[1] != null && this.storage[1].func_77960_j() == this.storage[1].func_77958_k())
        {
          func_70299_a(1, (ItemStack)null);
        }


        crucibleTE.temperature = (int)this.fireTemp;
      }
    }
  }



  public ItemStack func_70298_a(int i, int j) {
    if (this.storage[i] != null) {

      if ((this.storage[i]).field_77994_a <= j) {

        ItemStack itemstack = this.storage[i];
        this.storage[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = this.storage[i].func_77979_a(j);
      if ((this.storage[i]).field_77994_a == 0)
      {
        this.storage[i] = null;
      }
      return itemstack1;
    }


    return null;
  }



  public void ejectContents() {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.fireItemStacks[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.fireItemStacks[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
      }
    }


    if (this.charcoalCount > 0) {

      EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), RailcraftToolItems.getCoalCoke());
      entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
      entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
      entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
      this.field_145850_b.func_72838_d((Entity)entityitem);
    }
  }




  public int func_70297_j_() {
    return 1;
  }



  public String func_145825_b() {
    return "BlastFurnace";
  }



  public int func_70302_i_() {
    return this.storage.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public ItemStack func_70304_b(int i) {
    return this.storage[i];
  }


  public void handleTemperature() {
    int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);



    if (this.fuelTimeLeft > 0) {

      float desiredTemp = handleTemp();
      handleTempFlux(desiredTemp);
    }
    else if (this.fuelTimeLeft <= 0 && this.charcoalCount > 0 && (meta & 0x4) > 0) {

      this.charcoalCount--;

      this.fuelTimeLeft = 1875;
      this.fuelBurnTemp = 1400;
    }
    else {

      if ((meta & 0x4) > 0) {
        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta & 0x3, 3);
      }
      this.fuelBurnTemp = 0;
      float desiredTemp = handleTemp();
      handleTempFlux(desiredTemp);
    }


    handleAirReduction();
  }



  public void receiveAirFromBellows() {
    if (this.storage[1] != null) {
      super.receiveAirFromBellows();
    }
  }

  public boolean isStackValid(int i, int j, int k) {
    Block yNegBlock = this.field_145850_b.func_147439_a(i, j - 1, k);
    if (yNegBlock != TFCBlocks.molten && this.field_145850_b
      .func_147439_a(i, j - 1, k).func_149688_o() != Material.field_151576_e &&
      !this.field_145850_b.func_147439_a(i, j - 1, k).func_149721_r() && yNegBlock != TFCBlocks.blastFurnace &&
      TFC_Core.isTopFaceSolid(this.field_145850_b, i, j - 1, k))
    {
      return false;
    }

    this.maxValidStackSize = 0;
    for (int num = 0; num < 5; num++) {

      if (!((BlockBlastFurnace)TFCBlocks.blastFurnace).checkStackAt(this.field_145850_b, i, j + num, k))
        break;
      this.maxValidStackSize++;
    }
    return (this.maxValidStackSize != 0);
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }



  public void func_70295_k_() {}



  public boolean addOreToFire(ItemStack is) {
    for (int i = 0; i < this.fireItemStacks.length; i++) {

      if (this.fireItemStacks[i] == null) {

        this.fireItemStacks[i] = is;
        return true;
      }
    }
    return false;
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    this.storage[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }


























  public void createTuyereBlock() {}

























  public int getTotalCount() {
    return this.charcoalCount + this.oreCount;
  }






  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {

      createTuyereBlock();

      if (this.oreCount < 0)
        this.oreCount = 0;
      if (this.charcoalCount < 0) {
        this.charcoalCount = 0;
      }

      List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class,
          AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + this.moltenCount) + 1.1D, (this.field_145849_e + 1)));


      List<EntityItem> playerList = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + this.moltenCount) + 1.1D, (this.field_145849_e + 1)));

      if (this.moltenCount == 0) {
        this.moltenCount = 1;
      }



      if (list != null && !list.isEmpty() && ((BlockBlastFurnace)TFCBlocks.blastFurnace).checkStackAt(this.field_145850_b, this.field_145851_c, this.field_145848_d + this.moltenCount, this.field_145849_e) && (playerList == null || playerList.isEmpty()))
      {




        for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {

          EntityItem entity = iterator.next();
          ItemStack itemstack = entity.func_92059_d();
          Item item = itemstack.func_77973_b();
          boolean isOre = TFC_Core.isOreIron(itemstack);
          HeatRegistry manager = HeatRegistry.getInstance();
          HeatIndex index = manager.findMatchingIndex(itemstack);

          if (item == RailcraftToolItems.getCoalCoke().func_77973_b()) {



            for (int c = 0; c < itemstack.field_77994_a; c++) {

              if (getTotalCount() < 40 && this.charcoalCount < this.maxValidStackSize * 4) {

                this.charcoalCount++;
                itemstack.field_77994_a--;
              }
            }

            if (itemstack.field_77994_a == 0) {
              entity.func_70106_y();
            }


            continue;
          }

          if ((TFC_ItemHeat.isCookable(itemstack) != -1.0F && isOre) || (!isOre && item instanceof ISmeltable && ((ISmeltable)item)
            .getMetalType(itemstack) == Global.PIGIRON && index != null)) {


            int c = itemstack.field_77994_a;
            int nonConsumedOre = 0;
            for (; c > 0; c--) {

              if (getTotalCount() < 40 && this.oreCount < this.maxValidStackSize * 4) {

                if (foundFlux(this.moltenCount) && addOreToFire(new ItemStack(item, 1, itemstack.func_77960_j()))) {
                  this.oreCount++;
                } else {
                  nonConsumedOre++;
                }
              } else {

                nonConsumedOre++;
              }
            }

            if (c + nonConsumedOre == 0) {
              entity.func_70106_y();
              continue;
            }
            itemstack.field_77994_a = c + nonConsumedOre;
            entity.func_92058_a(itemstack);
          }
        }
      }



      handleTemperature();

      if (this.cookDelay > 0) {
        this.cookDelay--;
      }
      for (int i = 0; i < this.fireItemStacks.length && this.isValid; i++) {


        careForInventorySlot(this.fireItemStacks[i]);

        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == TFCBlocks.crucible)
        {
          cookItem(i);
        }
      }



      if (this.slowCounter > 100) {


        this.isValid = checkValidity().booleanValue();
        this.moltenCount = updateMoltenBlocks();
      }
      this.slowCounter++;
    }
  }





  private int updateMoltenBlocks() {
    int count = this.charcoalCount + this.oreCount;

    int moltenCount = 0;
    if (count > 0 && count <= 8) { moltenCount = 1; }
    else if (count > 8 && count <= 16) { moltenCount = 2; }
    else if (count > 16 && count <= 24) { moltenCount = 3; }
    else if (count > 24 && count <= 32) { moltenCount = 4; }
    else if (count > 32 && count <= 40) { moltenCount = 5; }


    for (int i = 1; i <= 5; i++) {


      if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + i, this.field_145849_e) || this.field_145850_b
        .func_147439_a(this.field_145851_c, this.field_145848_d + i, this.field_145849_e) == TFCBlocks.molten)
      {





        if (i <= moltenCount && i <= this.maxValidStackSize) {

          if (this.fireTemp > 100.0F)
          {
            int m = (count > 7) ? 7 : count;
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + i, this.field_145849_e, TFCBlocks.molten, m + 8, 2);
            count -= 8;
          }
          else
          {
            int m = (count > 7) ? 7 : count;
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + i, this.field_145849_e, TFCBlocks.molten, m, 2);
            count -= 8;
          }

        } else {

          this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + i, this.field_145849_e);
        }
      }
    }
    return moltenCount;
  }



  private boolean foundFlux(int moltenCount) {
    List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class,
        AxisAlignedBB.func_72330_a(this.field_145851_c, (this.field_145848_d + moltenCount), this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + moltenCount) + 1.1D, (this.field_145849_e + 1)));
    boolean found = false;
    for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext() && !found; ) {

      EntityItem entity = iterator.next();
      ItemStack is = entity.func_92059_d();
      if (!entity.field_70128_L && is.func_77960_j() == 0 && is.func_77973_b() == TFCItems.powder) {

        is.field_77994_a--;
        if (is.field_77994_a == 0) {
          entity.func_70106_y();
        } else {
          entity.func_92058_a(is);
        }  found = true;
      }
    }
    return found;
  }


  public int getOreCountScaled(int l) {
    return this.oreCount * l / 20;
  }


  public int getCharcoalCountScaled(int l) {
    return this.charcoalCount * l / 20;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public boolean func_145818_k_() {
    return false;
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("charcoalCount", this.charcoalCount);
    nbt.func_74768_a("outMetal1Count", this.outMetal1Count);
    nbt.func_74774_a("oreCount", (byte)this.oreCount);
    nbt.func_74768_a("maxValidStackSize", this.maxValidStackSize);


    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.fireItemStacks.length; i++) {

      if (this.fireItemStacks[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.fireItemStacks[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);

    NBTTagList nbttaglist2 = new NBTTagList();
    for (int j = 0; j < this.storage.length; j++) {

      if (this.storage[j] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)j);
        this.storage[j].func_77955_b(nbttagcompound1);
        nbttaglist2.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Input", (NBTBase)nbttaglist2);

    NBTTagList nbttaglist3 = new NBTTagList();
    for (int k = 0; k < this.outputItemStacks.length; k++) {

      if (this.outputItemStacks[k] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)k);
        this.outputItemStacks[k].func_77955_b(nbttagcompound1);
        nbttaglist3.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Output", (NBTBase)nbttaglist3);
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.charcoalCount = nbt.func_74762_e("charcoalCount");
    this.outMetal1Count = nbt.func_74762_e("outMetal1Count");
    this.oreCount = nbt.func_74771_c("oreCount");
    this.maxValidStackSize = nbt.func_74762_e("maxValidStackSize");

    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.fireItemStacks = new ItemStack[20];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.fireItemStacks.length) {
        this.fireItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
    NBTTagList nbttaglist2 = nbt.func_150295_c("Input", 10);
    this.storage = new ItemStack[2];
    for (int j = 0; j < nbttaglist2.func_74745_c(); j++) {

      NBTTagCompound nbttagcompound1 = nbttaglist2.func_150305_b(j);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length)
      {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }

    NBTTagList nbttaglist3 = nbt.func_150295_c("Output", 10);
    this.outputItemStacks = new ItemStack[20];
    for (int k = 0; k < nbttaglist3.func_74745_c(); k++) {

      NBTTagCompound nbttagcompound1 = nbttaglist3.func_150305_b(k);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.outputItemStacks.length) {
        this.outputItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }


  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());

    GuiScreen gui = (FMLClientHandler.instance().getClient()).field_71462_r;
    if (gui instanceof GuiBlastFurnace) {
      ((GuiBlastFurnace)gui).func_73876_c();
    }
  }

  public void updateGui() {
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */