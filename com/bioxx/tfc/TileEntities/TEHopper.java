package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fof.tfcsu.utils.ExpBonus;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.IHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.fluids.FluidStack;


public class TEHopper
  extends NetworkTileEntity
  implements IHopper
{
  private ItemStack[] storage = new ItemStack[5];
  private String customName;
  private int cooldown = -1;


  public int pressCooldown;


  public ItemStack pressBlock;


  public float startedweight;


  private boolean giveexp = false;


  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.storage = new ItemStack[func_70302_i_()];

    if (nbt.func_150297_b("CustomName", 8))
    {
      this.customName = nbt.func_74779_i("CustomName");
    }

    this.cooldown = nbt.func_74762_e("TransferCooldown");

    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte b0 = nbttagcompound1.func_74771_c("Slot");

      if (b0 >= 0 && b0 < this.storage.length)
      {
        this.storage[b0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }

    this.pressCooldown = nbt.func_74762_e("pressCooldown");
    this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
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
    nbt.func_74768_a("TransferCooldown", this.cooldown);

    if (func_145818_k_())
    {
      nbt.func_74778_a("CustomName", this.customName);
    }

    nbt.func_74768_a("pressCooldown", this.pressCooldown);

    if (this.pressBlock != null) {

      NBTTagCompound nbttagcompound1 = new NBTTagCompound();
      this.pressBlock.func_77955_b(nbttagcompound1);
      nbt.func_74782_a("pressBlock", (NBTBase)nbttagcompound1);
    }
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 2), (this.field_145849_e + 1));
  }






  public int func_70302_i_() {
    return this.storage.length;
  }






  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }







  public ItemStack func_70298_a(int i, int amount) {
    if (this.storage[i] != null) {



      if ((this.storage[i]).field_77994_a <= amount) {

        ItemStack itemStack = this.storage[i];
        this.storage[i] = null;
        return itemStack;
      }


      ItemStack itemstack = this.storage[i].func_77979_a(amount);

      if ((this.storage[i]).field_77994_a == 0)
      {
        this.storage[i] = null;
      }

      return itemstack;
    }



    return null;
  }








  public ItemStack func_70304_b(int i) {
    if (this.storage[i] != null) {

      ItemStack itemstack = this.storage[i];
      this.storage[i] = null;
      return itemstack;
    }


    return null;
  }







  public void func_70299_a(int i, ItemStack is) {
    this.storage[i] = is;

    if (is != null && is.field_77994_a > func_70297_j_())
    {
      is.field_77994_a = func_70297_j_();
    }
  }






  public String func_145825_b() {
    return func_145818_k_() ? this.customName : "container.hopper";
  }






  public boolean func_145818_k_() {
    return (this.customName != null && this.customName.length() > 0);
  }


  public void setCustomName(String s) {
    this.customName = s;
  }






  public int func_70297_j_() {
    return 64;
  }






  public boolean func_70300_a(EntityPlayer p) {
    return (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this) ? false : ((p.func_70092_e(this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D) <= 64.0D));
  }




  public void func_70295_k_() {}



  public void func_70305_f() {}



  public boolean func_94041_b(int i, ItemStack is) {
    return true;
  }



  public void func_145845_h() {
    if (this.field_145850_b.field_72995_K) {

      if (this.pressCooldown > 0) {
        this.pressCooldown--;
      } else {
        this.pressBlock = null;
      }
    } else if (this.field_145850_b != null && !this.field_145850_b.field_72995_K) {

      this.cooldown--;

      TFC_Core.handleItemTicking((IInventory)this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);




      if (getPressableItem() == -1) this.pressCooldown = 0;

      if (this.pressCooldown > 0) {

        this.pressCooldown--;
        if (this.pressCooldown % 20 == 0) press();

      } else if (this.pressCooldown == 0 && this.pressBlock != null) {

        this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, Block.func_149634_a(this.pressBlock.func_77973_b()), this.pressBlock.func_77960_j(), 2);
        this.pressBlock = null;
        if (this.giveexp) { ExpBonus.PRESS.spawnExp(this.field_145850_b, func_96107_aA(), func_96109_aB(), func_96108_aC()); this.giveexp = false; }

      }

























      if (!isCoolingDown())
      {
        setCooldown(0);
      }

      Block blockAbove = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
      if (blockAbove != null && hasPressableItem() > 0)
      {
        if (this.pressBlock != null && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockGravel) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockSand) && !(blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockDirt)) {

          TFC_Core.setBlockToAirWithDrops(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        }
        else if (blockAbove instanceof com.bioxx.tfc.Blocks.Terrain.BlockSmooth) {

          this.pressBlock = new ItemStack(blockAbove, 1, this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e));
          this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
          sendPressPacket();
          beginPressing();
        }
      }
    }
  }


  private void press() {
    TEBarrel barrel = null;
    if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TEBarrel) {
      barrel = (TEBarrel)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
    }

    int num = getPressableItem();
    ItemStack item = null;
    if (num != -1) item = this.storage[num];

    if (item != null) {

      if (item.func_77973_b() == TFCItems.honeycomb && this.startedweight == 0.0F) this.startedweight = Food.getWeight(item);







      if (item.func_77973_b() == TFCItems.honeycomb) {

        if (item.field_77994_a > 0) Food.setWeight(item, Food.getWeight(item) - 0.98F);

        if (item.field_77994_a == 0 || Food.getWeight(item) < 0.98F) {

          if (this.startedweight > 150.0F) this.storage[num] = new ItemStack(TFCItems.paraffin);
          this.startedweight = 0.0F;
        }
        if (barrel != null && barrel.canAcceptLiquids() && !barrel.getSealed()) barrel.addLiquid(new FluidStack(TFCFluids.HONEY, 1));

      }
    }
  }


  private void beginPressing() {
    int pressWeight = hasPressableItem();
    if (pressWeight > 0) {


      this.pressCooldown = (int)(this.pressCooldown + pressWeight / 0.98F * 20.0F);
      sendCooldownPacket();
      if (pressWeight > 60) { this.giveexp = true; } else { this.giveexp = false; }

    }
  }

  public int hasPressableItem() {
    int amount = 0;
    for (int i = 0; i < this.storage.length; i++) {

      if (this.storage[i] != null && this.storage[i].func_77973_b() == TFCItems.honeycomb)
      {
        amount = (int)(amount + Math.floor(Food.getWeight(this.storage[i])));
      }
    }
    return amount;
  }


  public int getPressableItem() {
    for (int i = 0; i < this.storage.length; i++) {

      if (this.storage[i] != null && this.storage[i].func_77973_b() == TFCItems.honeycomb)
      {
        return i;
      }
    }
    return -1;
  }




































  public void setCooldown(int time) {
    this.cooldown = time;
  }


  public boolean isCoolingDown() {
    return (this.cooldown > 0);
  }











































































































































































































































































































































































































  public void handleInitPacket(NBTTagCompound nbt) {
    if (nbt.func_74764_b("pressBlock"))
    {
      this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
    }
    this.pressCooldown = nbt.func_74762_e("pressCooldown");
  }



  public void createInitNBT(NBTTagCompound nbt) {
    if (this.pressBlock != null) {

      NBTTagCompound pb = new NBTTagCompound();
      this.pressBlock.func_77955_b(pb);
      nbt.func_74782_a("pressBlock", (NBTBase)pb);
    }
    nbt.func_74768_a("pressCooldown", this.pressCooldown);
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    if (nbt.func_74764_b("pressBlock"))
    {
      this.pressBlock = ItemStack.func_77949_a(nbt.func_74775_l("pressBlock"));
    }
    if (nbt.func_74764_b("pressCooldown"))
    {
      this.pressCooldown = nbt.func_74762_e("pressCooldown");
    }
  }


  private void sendPressPacket() {
    NBTTagCompound nbt = new NBTTagCompound();
    if (this.pressBlock != null) {

      NBTTagCompound pb = new NBTTagCompound();
      this.pressBlock.func_77955_b(pb);
      nbt.func_74782_a("pressBlock", (NBTBase)pb);
    }
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }


  private void sendCooldownPacket() {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74768_a("pressCooldown", this.pressCooldown);
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }


  public double func_96107_aA() {
    return this.field_145851_c;
  }


  public double func_96109_aB() {
    return this.field_145848_d;
  }


  public double func_96108_aC() {
    return this.field_145849_e;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */