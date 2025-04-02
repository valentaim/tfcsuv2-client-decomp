package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.CreateMealPacket;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.Interfaces.IItemFoodBlock;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

public class TEFoodPrep
  extends NetworkTileEntity
  implements IInventory {
  public ItemStack[] storage = new ItemStack[11];
  public int lastTab;
  private final float[] sandwichWeights = new float[] { 2.0F, 3.0F, 2.0F, 2.0F, 1.0F };
  private final float[] saladWeights = new float[] { 10.0F, 4.0F, 4.0F, 2.0F };



  public void func_145845_h() {
    TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public int getFoodIdFromItemStack(ItemStack is) {
    if (is != null) {

      if (is.func_77973_b() instanceof IFood)
        return ((IFood)is.func_77973_b()).getFoodID();
      if (is.func_77973_b() instanceof IItemFoodBlock)
        return ((IItemFoodBlock)is.func_77973_b()).getFoodId(is);
    }
    return 1;
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public int getHealAmountFromItemStack(ItemStack is) {
    if (is != null) {

      if (is.func_77973_b() instanceof IFood)
        return ((IFood)is.func_77973_b()).getFoodID();
      if (is.func_77973_b() instanceof IItemFoodBlock)
        return ((IItemFoodBlock)is.func_77973_b()).getHealAmount(is);
    }
    return 1;
  }


  public void actionCreate(EntityPlayer player) {
    if (!this.field_145850_b.field_72995_K) {

      if (this.lastTab == 0) {
        createSandwich(player);
      } else if (this.lastTab == 1) {
        createSalad(player);
      }

    }
    else {

      CreateMealPacket createMealPacket = new CreateMealPacket(0, this);
      broadcastPacketInRange((AbstractPacket)createMealPacket);
    }
  }


  private void createSandwich(EntityPlayer player) {
    if (validateSandwich()) {

      ItemStack is = new ItemStack(TFCItems.sandwich, 1);

      float w = 0.0F;
      for (int i = 0; i < 5; i++) {

        ItemStack f = func_70301_a(i);
        if (f != null && Food.getWeight(f) >= this.sandwichWeights[i]) {
          w += this.sandwichWeights[i];
        }
      }
      ItemFoodTFC.createTag(is, w);
      Food.setDecayRate(is, 2.0F);

      int[] foodGroups = { -1, -1, -1, -1, -1 };
      if (func_70301_a(0) != null) foodGroups[0] = ((IFood)func_70301_a(0).func_77973_b()).getFoodID();
      if (func_70301_a(1) != null) foodGroups[1] = ((IFood)func_70301_a(1).func_77973_b()).getFoodID();
      if (func_70301_a(2) != null) foodGroups[2] = ((IFood)func_70301_a(2).func_77973_b()).getFoodID();
      if (func_70301_a(3) != null) foodGroups[3] = ((IFood)func_70301_a(3).func_77973_b()).getFoodID();
      if (func_70301_a(4) != null) foodGroups[4] = ((IFood)func_70301_a(4).func_77973_b()).getFoodID();

      Food.setFoodGroups(is, foodGroups);
      setSandwichIcon(is);

      combineTastes(is.func_77978_p(), this.sandwichWeights, new ItemStack[] { func_70301_a(0), func_70301_a(1), func_70301_a(2), func_70301_a(3), func_70301_a(4) });

      Food.setMealSkill(is, TFC_Core.getSkillStats(player).getSkillRank("skill.cooking").ordinal());
      func_70299_a(6, is);

      consumeFoodWeight(this.sandwichWeights, new ItemStack[] { func_70301_a(0), func_70301_a(1), func_70301_a(2),
            func_70301_a(3), func_70301_a(4) });
    }
  }


  private void setSandwichIcon(ItemStack is) {
    if (func_70301_a(0).func_77973_b() == TFCItems.wheatBread) {
      is.func_77964_b(0);
    } else if (func_70301_a(0).func_77973_b() == TFCItems.oatBread) {
      is.func_77964_b(1);
    } else if (func_70301_a(0).func_77973_b() == TFCItems.barleyBread) {
      is.func_77964_b(2);
    } else if (func_70301_a(0).func_77973_b() == TFCItems.ryeBread) {
      is.func_77964_b(3);
    } else if (func_70301_a(0).func_77973_b() == TFCItems.cornBread) {
      is.func_77964_b(4);
    } else if (func_70301_a(0).func_77973_b() == TFCItems.riceBread) {
      is.func_77964_b(5);
    }
  }

  private void createSalad(EntityPlayer player) {
    if (validateSalad()) {

      ItemStack is = new ItemStack(TFCItems.salad, 1);

      float w = 0.0F;
      for (int i = 0; i < 4; i++) {

        ItemStack f = func_70301_a(i + 1);
        if (f != null && Food.getWeight(f) >= this.saladWeights[i]) {
          w += this.saladWeights[i];
        }
      }
      ItemFoodTFC.createTag(is, w);
      Food.setDecayRate(is, 2.0F);

      int[] foodGroups = { -1, -1, -1, -1 };
      if (func_70301_a(1) != null) foodGroups[0] = ((IFood)func_70301_a(1).func_77973_b()).getFoodID();
      if (func_70301_a(2) != null) foodGroups[1] = ((IFood)func_70301_a(2).func_77973_b()).getFoodID();
      if (func_70301_a(3) != null) foodGroups[2] = ((IFood)func_70301_a(3).func_77973_b()).getFoodID();
      if (func_70301_a(4) != null) foodGroups[3] = ((IFood)func_70301_a(4).func_77973_b()).getFoodID();

      Food.setFoodGroups(is, foodGroups);

      is.func_77964_b((new Random(getIconSeed())).nextInt(((ItemTerra)TFCItems.salad).metaIcons.length));

      combineTastes(is.func_77978_p(), this.saladWeights, new ItemStack[] { func_70301_a(1), func_70301_a(2), func_70301_a(3), func_70301_a(4) });

      Food.setMealSkill(is, TFC_Core.getSkillStats(player).getSkillRank("skill.cooking").ordinal());
      func_70299_a(6, is);

      consumeFoodWeight(this.saladWeights, new ItemStack[] { func_70301_a(1), func_70301_a(2), func_70301_a(3), func_70301_a(4) });

      (TFC_Core.getItemInInventory(TFCItems.potteryBowl, this)).field_77994_a--;
    }
  }


  public boolean validateSandwich() {
    if (this.lastTab == 0) {

      if (this.storage[0] == null || this.storage[6] != null) {
        return false;
      }





      if (!validateIngreds(new ItemStack[] { this.storage[1], this.storage[2], this.storage[3], this.storage[4] })) {
        return false;
      }
      float weight = 0.0F;
      for (int i = 0; i < 5; i++) {

        ItemStack f = func_70301_a(i);
        if (f != null && f.func_77973_b() instanceof IFood && Food.getWeight(f) - Food.getDecay(f) >= this.sandwichWeights[i])
        {
          weight += this.sandwichWeights[i];
        }
      }

      if (weight < 7.0F)
        return false;
    }
    return true;
  }


  public boolean validateSalad() {
    if (this.lastTab == 1) {

      if (this.storage[6] != null) {
        return false;
      }







      if (!validateIngreds(new ItemStack[] { this.storage[1], this.storage[2], this.storage[3], this.storage[4] })) {
        return false;
      }
      float weight = 0.0F;
      for (int i = 0; i < 4; i++) {

        ItemStack f = func_70301_a(i + 1);
        if (f != null && Food.getWeight(f) - Food.getDecay(f) >= this.saladWeights[i])
        {
          weight += this.saladWeights[i];
        }
      }

      if (weight < 14.0F) {
        return false;
      }
      ItemStack bowlStack = TFC_Core.getItemInInventory(TFCItems.potteryBowl, this);
      if (bowlStack == null || bowlStack.func_77960_j() != 1)
      {
        return false;
      }
    }
    return true;
  }


  public boolean validateIngreds(ItemStack... is) {
    for (int i = 0; i < is.length; i++) {

      if (is[i] != null && !((IFood)is[i].func_77973_b()).isUsable(is[i]))
        return false;
      for (int j = 0; j < is.length; j++) {


        if (j != i && !compareIngred(is[i], is[j]))
          return false;
      }
    }
    return true;
  }


  private boolean compareIngred(ItemStack is1, ItemStack is2) {
    return (is1 == null || is2 == null || is1.func_77973_b() != is2.func_77973_b());
  }


  private void combineTastes(NBTTagCompound nbt, float[] weights, ItemStack... isArray) {
    int tasteSweet = 0;
    int tasteSour = 0;
    int tasteSalty = 0;
    int tasteBitter = 0;
    int tasteUmami = 0;

    for (int i = 0; i < isArray.length; i++) {

      float weightMult = 1.0F;
      if (isArray[i] != null) {

        tasteSweet = (int)(tasteSweet + ((IFood)isArray[i].func_77973_b()).getTasteSweet(isArray[i]) * weightMult);
        tasteSour = (int)(tasteSour + ((IFood)isArray[i].func_77973_b()).getTasteSour(isArray[i]) * weightMult);
        tasteSalty = (int)(tasteSalty + ((IFood)isArray[i].func_77973_b()).getTasteSalty(isArray[i]) * weightMult);
        tasteBitter = (int)(tasteBitter + ((IFood)isArray[i].func_77973_b()).getTasteBitter(isArray[i]) * weightMult);
        tasteUmami = (int)(tasteUmami + ((IFood)isArray[i].func_77973_b()).getTasteSavory(isArray[i]) * weightMult);
      }
    }
    nbt.func_74768_a("tasteSweet", tasteSweet);
    nbt.func_74768_a("tasteSour", tasteSour);
    nbt.func_74768_a("tasteSalty", tasteSalty);
    nbt.func_74768_a("tasteBitter", tasteBitter);
    nbt.func_74768_a("tasteUmami", tasteUmami);
  }


  public void openGui(EntityPlayer player) {
    if (this.lastTab == 0) {
      player.openGui(TerraFirmaCraft.instance, 44, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    } else if (this.lastTab == 1) {
      player.openGui(TerraFirmaCraft.instance, 45, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
  }














  private long getIconSeed() {
    int seed = 0;
    for (int i = 1; i < 5; i++) {

      ItemStack is = func_70301_a(i);
      if (is != null)
        seed += ((ItemFoodTFC)is.func_77973_b()).getFoodID();
    }
    return seed + this.field_145850_b.func_72905_C();
  }


  public void consumeFoodWeight(float[] weights, ItemStack... isArray) {
    for (int i = 0; i < isArray.length; i++) {

      ItemStack is = isArray[i];
      if (is != null) {

        float oldW = Food.getWeight(is);
        Food.setWeight(is, oldW - weights[i]);
        float newW = Food.getWeight(is);
        if (newW <= 0.0F || newW <= Food.getDecay(is)) {
          is.field_77994_a = 0;
        }
      }
    }
  }


  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
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
        this.storage[i] = null;
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

      if (this.storage[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
        this.storage[i] = null;
      }
    }
  }


  public void ejectItem(int index) {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    if (this.storage[index] != null) {

      EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[index]);
      entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
      entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.05F);
      entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
      this.field_145850_b.func_72838_d((Entity)entityitem);
    }
  }



  public int func_70302_i_() {
    return this.storage.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    if (!TFC_Core.areItemsEqual(this.storage[i], itemstack))
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    this.storage[i] = itemstack;
  }



  public String func_145825_b() {
    return "FoodPrep";
  }



  public int func_70297_j_() {
    return 64;
  }



  public boolean func_70300_a(EntityPlayer var1) {
    return false;
  }




  public void func_70295_k_() {}



  public void func_70305_f() {
    if (this.field_145850_b.field_72995_K)
    {
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
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


  public void actionSwitchTab(int tab, EntityPlayer player) {
    NBTTagCompound nbt = new NBTTagCompound();
    nbt.func_74774_a("tab", (byte)tab);
    nbt.func_74778_a("player", player.func_70005_c_());
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }


  public void handleInitPacket(NBTTagCompound nbt) {
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

  public void createInitNBT(NBTTagCompound nbt) {
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



  public void handleDataPacket(NBTTagCompound nbt) {
    if (!this.field_145850_b.field_72995_K)
    {
      if (nbt.func_74764_b("tab")) {

        int tab = nbt.func_74771_c("tab");
        EntityPlayer player = this.field_145850_b.func_72924_a(nbt.func_74779_i("player"));
        if (player != null && tab == 0) {
          player.openGui(TerraFirmaCraft.instance, 44, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else if (player != null && tab == 1) {
          player.openGui(TerraFirmaCraft.instance, 45, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */