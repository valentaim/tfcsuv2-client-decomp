package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Blocks.Devices.BlockSluice;
import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.block.Block;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TESluice
  extends TileEntity implements IInventory {
  public int soilAmount;
  public long lastUpdateTicks;
  public int processTimeRemaining;
  private ItemStack[] sluiceItemStacks;
  public boolean waterInput;
  public boolean waterOutput;
  public byte soilType;
  private boolean initialized;
  private Random random = new Random();
  private Set<Integer> coreSampleTypes = new TreeSet<>();
  private List<ItemStack> coreSampleStacks = new ArrayList<>();


  public TESluice() {
    this.sluiceItemStacks = new ItemStack[9];
    this.soilAmount = 0;
    this.lastUpdateTicks = 0L;
    this.processTimeRemaining = 0;
    this.waterInput = false;
    this.waterOutput = false;
    this.soilType = 1;
  }


  public void addToInventory(ItemStack is) {
    for (int i = 0; i < func_70302_i_(); i++) {

      ItemStack stackInSlot = func_70301_a(i);
      if (stackInSlot == null) {


        func_70299_a(i, is);


        return;
      }

      if (stackInSlot == is && stackInSlot.func_77960_j() == is.func_77960_j())
      {

        if (stackInSlot.field_77994_a + is.field_77994_a > func_70297_j_()) {


          int size = func_70297_j_() - stackInSlot.field_77994_a;
          stackInSlot.field_77994_a += size;
          is.field_77994_a -= size;

        }
        else {


          stackInSlot.field_77994_a += is.field_77994_a;



          return;
        }
      }
    }



    ejectItem(is);
  }




  public void func_70305_f() {}



  public ItemStack func_70298_a(int i, int j) {
    if (this.sluiceItemStacks[i] != null) {

      if ((this.sluiceItemStacks[i]).field_77994_a <= j) {

        ItemStack itemstack = this.sluiceItemStacks[i];
        this.sluiceItemStacks[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = this.sluiceItemStacks[i].func_77979_a(j);
      if ((this.sluiceItemStacks[i]).field_77994_a == 0)
        this.sluiceItemStacks[i] = null;
      return itemstack1;
    }


    return null;
  }



  private void ejectItem(ItemStack is) {
    float f = this.random.nextFloat() * 0.8F + 0.1F;
    float f1 = this.random.nextFloat() * 2.0F + 0.4F;
    float f2 = this.random.nextFloat() * 0.8F + 0.1F;
    EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), new ItemStack(is.func_77973_b(), is.field_77994_a, is.func_77960_j()));
    float f3 = 0.05F;
    entityitem.field_70159_w = ((float)this.random.nextGaussian() * f3);
    entityitem.field_70181_x = ((float)this.random.nextGaussian() * f3 + 0.2F);
    entityitem.field_70179_y = ((float)this.random.nextGaussian() * f3);
    this.field_145850_b.func_72838_d((Entity)entityitem);
  }


  public int getFirstFreeSlot() {
    for (int i = 0; i < func_70302_i_(); i++) {

      if (func_70301_a(i) == null)
        return i;
    }
    return -1;
  }



  public int func_70297_j_() {
    return 64;
  }



  public String func_145825_b() {
    return "Sluice";
  }


  public int getProcessScaled(int i) {
    return this.processTimeRemaining * i / 100;
  }



  public int func_70302_i_() {
    return this.sluiceItemStacks.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.sluiceItemStacks[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this)
      return false;
    return (entityplayer.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D);
  }




  public void func_70295_k_() {}



  public void func_70299_a(int i, ItemStack itemstack) {
    this.sluiceItemStacks[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }



  public void func_145845_h() {
    int meta = func_145832_p();
    boolean isFoot = BlockSluice.isBlockFootOfBed(meta);
    if (isFoot || this.soilAmount == -1) {
      return;
    }



    if (!this.field_145850_b.field_72995_K) {

      if (!this.initialized && this.field_145850_b.func_72977_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, 10.0D) != null) {

        for (int x = -100; x <= 100; x += 2) {

          for (int z = -100; z <= 100; z += 2) {

            for (int y = this.field_145848_d; y > this.field_145848_d - 50; y--) {

              if (this.field_145850_b.func_147439_a(x + this.field_145851_c, y, z + this.field_145849_e) == TFCBlocks.ore) {

                int m = this.field_145850_b.func_72805_g(x + this.field_145851_c, y, z + this.field_145849_e);
                if (m != 14 && m != 15)
                {
                  if (!this.coreSampleTypes.contains(Integer.valueOf(m))) {

                    this.coreSampleTypes.add(Integer.valueOf(m));
                    this.coreSampleStacks.add(new ItemStack(BlockOre.getDroppedItem(m), 1, m));
                  }
                }
              }
            }
          }
        }
        this.initialized = true;
      }

      List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1.1F), (this.field_145849_e + 1)));



      for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {

        EntityItem entity = iterator.next();
        Item item = entity.func_92059_d().func_77973_b();
        if (item == Item.func_150898_a(TFCBlocks.gravel) || item == Item.func_150898_a(TFCBlocks.gravel2) || item ==
          Item.func_150898_a(TFCBlocks.sand) || item == Item.func_150898_a(TFCBlocks.sand2)) {

          int stackSize = (entity.func_92059_d()).field_77994_a;
          int accept = (69 - this.soilAmount) / 20;
          if (stackSize <= accept) {

            this.soilAmount += 20 * stackSize;
            entity.func_70106_y();
            if (this.soilAmount > 50)
              this.soilAmount = 50;
            if (item == Item.func_150898_a(TFCBlocks.gravel) || item == Item.func_150898_a(TFCBlocks.gravel2)) {
              this.soilType = 2; continue;
            }
            this.soilType = 1;
          }
        }
      }


      long tickDiff = TFC_Time.getTotalTicks() - this.lastUpdateTicks;
      if (this.lastUpdateTicks == 0L)
      {

        tickDiff = 0L;
      }
      this.lastUpdateTicks = TFC_Time.getTotalTicks();


      if (this.soilAmount > 0 && this.waterInput && this.waterOutput) {



        this.processTimeRemaining = (int)(this.processTimeRemaining + tickDiff);
        if (this.processTimeRemaining < 0)
        {

          this.processTimeRemaining = 0;
        }

        ChunkData cd = TFC_Core.getCDM(this.field_145850_b).getData(this.field_145851_c >> 4, this.field_145849_e >> 4);

        if (TFCOptions.enableOverworkingChunks && cd.sluicedAmount > TFCOptions.sluiceLimit) {

          this.processTimeRemaining = 0;
          this.soilAmount = -1;

          return;
        }
        while (this.processTimeRemaining > 100 && this.soilAmount > 0) {

          float gemMod = 1.0F;
          float oreMod = 1.0F;
          if (this.soilType == 1) {
            gemMod = 0.65F;
          } else if (this.soilType == 2) {
            oreMod = 0.6F;
          }
          ArrayList<ItemStack> items = new ArrayList<>();
          if (this.random.nextInt((int)(200.0F * oreMod)) == 0 && !this.coreSampleStacks.isEmpty()) {
            addToInventory(((ItemStack)this.coreSampleStacks.get(this.random.nextInt(this.coreSampleStacks.size()))).func_77946_l());
          } else if (this.random.nextInt((int)(400.0F * gemMod)) == 0) {

            items.add(new ItemStack(TFCItems.gemAgate, 1, 0));
            items.add(new ItemStack(TFCItems.gemAmethyst, 1, 0));
            items.add(new ItemStack(TFCItems.gemBeryl, 1, 0));
            items.add(new ItemStack(TFCItems.gemEmerald, 1, 0));
            items.add(new ItemStack(TFCItems.gemGarnet, 1, 0));
            items.add(new ItemStack(TFCItems.gemJade, 1, 0));
            items.add(new ItemStack(TFCItems.gemJasper, 1, 0));
            items.add(new ItemStack(TFCItems.gemOpal, 1, 0));
            items.add(new ItemStack(TFCItems.gemRuby, 1, 0));
            items.add(new ItemStack(TFCItems.gemSapphire, 1, 0));
            items.add(new ItemStack(TFCItems.gemTourmaline, 1, 0));
            items.add(new ItemStack(TFCItems.gemTopaz, 1, 0));
            addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
          }
          else if (this.random.nextInt((int)(800.0F * gemMod)) == 0) {

            items.add(new ItemStack(TFCItems.gemAgate, 1, 1));
            items.add(new ItemStack(TFCItems.gemAmethyst, 1, 1));
            items.add(new ItemStack(TFCItems.gemBeryl, 1, 1));
            items.add(new ItemStack(TFCItems.gemEmerald, 1, 1));
            items.add(new ItemStack(TFCItems.gemGarnet, 1, 1));
            items.add(new ItemStack(TFCItems.gemJade, 1, 1));
            items.add(new ItemStack(TFCItems.gemJasper, 1, 1));
            items.add(new ItemStack(TFCItems.gemOpal, 1, 1));
            items.add(new ItemStack(TFCItems.gemRuby, 1, 1));
            items.add(new ItemStack(TFCItems.gemSapphire, 1, 1));
            items.add(new ItemStack(TFCItems.gemTourmaline, 1, 1));
            items.add(new ItemStack(TFCItems.gemTopaz, 1, 1));
            addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
          }
          else if (this.random.nextInt((int)(1600.0F * gemMod)) == 0) {

            items.add(new ItemStack(TFCItems.gemAgate, 1, 2));
            items.add(new ItemStack(TFCItems.gemAmethyst, 1, 2));
            items.add(new ItemStack(TFCItems.gemBeryl, 1, 2));
            items.add(new ItemStack(TFCItems.gemEmerald, 1, 2));
            items.add(new ItemStack(TFCItems.gemGarnet, 1, 2));
            items.add(new ItemStack(TFCItems.gemJade, 1, 2));
            items.add(new ItemStack(TFCItems.gemJasper, 1, 2));
            items.add(new ItemStack(TFCItems.gemOpal, 1, 2));
            items.add(new ItemStack(TFCItems.gemRuby, 1, 2));
            items.add(new ItemStack(TFCItems.gemSapphire, 1, 2));
            items.add(new ItemStack(TFCItems.gemTourmaline, 1, 2));
            items.add(new ItemStack(TFCItems.gemTopaz, 1, 2));
            addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
          }
          else if (this.random.nextInt((int)(3200.0F * gemMod)) == 0) {

            items.add(new ItemStack(TFCItems.gemAgate, 1, 3));
            items.add(new ItemStack(TFCItems.gemAmethyst, 1, 3));
            items.add(new ItemStack(TFCItems.gemBeryl, 1, 3));
            items.add(new ItemStack(TFCItems.gemEmerald, 1, 3));
            items.add(new ItemStack(TFCItems.gemGarnet, 1, 3));
            items.add(new ItemStack(TFCItems.gemJade, 1, 3));
            items.add(new ItemStack(TFCItems.gemJasper, 1, 3));
            items.add(new ItemStack(TFCItems.gemOpal, 1, 3));
            items.add(new ItemStack(TFCItems.gemRuby, 1, 3));
            items.add(new ItemStack(TFCItems.gemSapphire, 1, 3));
            items.add(new ItemStack(TFCItems.gemTourmaline, 1, 3));
            items.add(new ItemStack(TFCItems.gemTopaz, 1, 3));
            addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
          }
          else if (this.random.nextInt((int)(6400.0F * gemMod)) == 0) {

            items.add(new ItemStack(TFCItems.gemAgate, 1, 4));
            items.add(new ItemStack(TFCItems.gemAmethyst, 1, 4));
            items.add(new ItemStack(TFCItems.gemBeryl, 1, 4));
            items.add(new ItemStack(TFCItems.gemEmerald, 1, 4));
            items.add(new ItemStack(TFCItems.gemGarnet, 1, 4));
            items.add(new ItemStack(TFCItems.gemJade, 1, 4));
            items.add(new ItemStack(TFCItems.gemJasper, 1, 4));
            items.add(new ItemStack(TFCItems.gemOpal, 1, 4));
            items.add(new ItemStack(TFCItems.gemRuby, 1, 4));
            items.add(new ItemStack(TFCItems.gemSapphire, 1, 4));
            items.add(new ItemStack(TFCItems.gemTourmaline, 1, 4));
            items.add(new ItemStack(TFCItems.gemTopaz, 1, 4));
            addToInventory((ItemStack)items.toArray()[this.random.nextInt((items.toArray()).length)]);
          }
          else if (this.random.nextInt((int)(12800.0F * gemMod)) == 0) {

            int r = this.random.nextInt(50);
            if (r == 0) {
              addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 3));
            } else if (r < 15) {
              addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 2));
            } else if (r < 25) {
              addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 1));
            } else if (r < 50) {
              addToInventory(new ItemStack(TFCItems.gemDiamond, 1, 0));
            }
          }  cd.sluicedAmount++;
          this.processTimeRemaining -= 100;
          this.soilAmount--;
        }
      }
      if (this.soilAmount == 0) {
        this.processTimeRemaining = 0;
      }
    }



    if ((meta & 0x3) == 0) {

      this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1));
      this
        .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e + 2)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e + 2)));
    }
    if ((meta & 0x3) == 1) {

      this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e));
      this
        .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c - 2, this.field_145848_d - 1, this.field_145849_e)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c - 2, this.field_145848_d - 1, this.field_145849_e)));
    }
    if ((meta & 0x3) == 2) {

      this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1));
      this
        .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e - 2)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e - 2)));
    }
    if ((meta & 0x3) == 3) {

      this.waterInput = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e));
      this
        .waterOutput = (TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + 2, this.field_145848_d - 1, this.field_145849_e)) || TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + 2, this.field_145848_d - 1, this.field_145849_e)));
    }




    boolean isFlowing = ((meta & 0x4) == 4);
    ForgeDirection dir = getDir(meta & 0x3);
    Block water = this.field_145850_b.func_147439_a(this.field_145851_c + dir.offsetX, this.field_145848_d + 1, this.field_145849_e + dir.offsetZ);
    boolean isInputWater = TFC_Core.isWater(water);
    boolean isOutputAir = this.field_145850_b.func_147437_c(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2);
    boolean isOutputWater = TFC_Core.isWater(this.field_145850_b.func_147439_a(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2));
    boolean isWaterDepth7 = (this.field_145850_b.func_72805_g(this.field_145851_c + dir.offsetX, this.field_145848_d + 1, this.field_145849_e + dir.offsetZ) == 7);
    int meta2 = this.field_145850_b.func_72805_g(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ);
    if (isInputWater && isWaterDepth7 && !isFlowing && (isOutputAir || isOutputWater)) {


      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta + 4, 3);

      if ((this.field_145850_b.func_72805_g(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ) & 0x4) == 0)
      {

        this.field_145850_b.func_72921_c(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ, meta2 + 4, 3);
      }


      this.field_145850_b.func_147449_b(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2, water);
    }
    if ((!isInputWater || !isWaterDepth7 || (!isOutputAir && !isOutputWater)) && isFlowing) {


      this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta - 4, 3);
      if ((meta2 & 0x4) != 0)
      {

        this.field_145850_b.func_72921_c(this.field_145851_c + (dir.getOpposite()).offsetX, this.field_145848_d, this.field_145849_e + (dir.getOpposite()).offsetZ, meta2 - 4, 3);
      }

      if (!isOutputAir && isOutputWater) {
        this.field_145850_b.func_147468_f(this.field_145851_c + (dir.getOpposite()).offsetX * 2, this.field_145848_d - 1, this.field_145849_e + (dir.getOpposite()).offsetZ * 2);
      }
    }
  }

  private ForgeDirection getDir(int r) {
    if (r == 0)
    {
      return ForgeDirection.NORTH;
    }
    if (r == 1)
    {
      return ForgeDirection.EAST;
    }
    if (r == 2)
    {
      return ForgeDirection.SOUTH;
    }
    if (r == 3)
    {
      return ForgeDirection.WEST;
    }

    return ForgeDirection.UNKNOWN;
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
    this.sluiceItemStacks = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.sluiceItemStacks.length)
        this.sluiceItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
    }
    this.soilType = nbttagcompound.func_74771_c("soilType");
    this.soilAmount = nbttagcompound.func_74762_e("soilAmount");
    this.processTimeRemaining = nbttagcompound.func_74762_e("processTimeRemaining");
    this.lastUpdateTicks = nbttagcompound.func_74763_f("lastUpdateTicks");
    this.waterInput = nbttagcompound.func_74767_n("waterInput");
    this.waterOutput = nbttagcompound.func_74767_n("waterOutput");
  }



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    nbttagcompound.func_74774_a("soilType", this.soilType);
    nbttagcompound.func_74768_a("soilAmount", this.soilAmount);
    nbttagcompound.func_74768_a("processTimeRemaining", this.processTimeRemaining);
    nbttagcompound.func_74772_a("lastUpdateTicks", this.lastUpdateTicks);
    nbttagcompound.func_74757_a("waterInput", this.waterInput);
    nbttagcompound.func_74757_a("waterOutput", this.waterOutput);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.sluiceItemStacks.length; i++) {

      if (this.sluiceItemStacks[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.sluiceItemStacks[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
  }



  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TESluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */