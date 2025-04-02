package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Render.Models.ModelLoom;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.LoomManager;
import com.bioxx.tfc.api.Crafting.LoomRecipe;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fof.tfcsu.utils.ExpBonus;
import java.util.Random;
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
import net.minecraft.util.ResourceLocation;




public class TELoom
  extends NetworkTileEntity
  implements IInventory
{
  public byte rotation;
  public int loomType;
  private ItemStack[] storage;
  private boolean weaving;
  private boolean finished;
  private ModelLoom model;
  private int clothCompletionCount;
  public LoomRecipe recipe;
  private final ResourceLocation defaultTexture = new ResourceLocation("terrafirmacraft", "textures/blocks/String.png");

  long lasttick;

  public boolean canUpdate() {
    return false;
  }







  public void func_70305_f() {}






  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }



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


  public boolean isFinished() {
    return this.finished;
  }
  public TELoom() {
    this.lasttick = 0L;
    this.storage = new ItemStack[2];
  } public ItemStack addString(ItemStack i) {
    if (System.currentTimeMillis() < this.lasttick + 300L) {
      this.lasttick = System.currentTimeMillis();
      return i;
    }
    this.lasttick = System.currentTimeMillis();
    if (!isFinished() && i != null && !this.field_145850_b.field_72995_K) {

      this.recipe = LoomManager.getInstance().findPotentialRecipes(this.storage[0]);
      if (this.storage[0] != null) {

        LoomRecipe lr = LoomManager.getInstance().findPotentialRecipes(i);
        if (lr != null && lr.equals(this.recipe))
        {
          if (getStringCount() < this.recipe.getReqSize())
          {
            i.field_77994_a--;
            (this.storage[0]).field_77994_a++;
            updateLoom();
          }

        }
      } else if (LoomManager.getInstance().hasPotentialRecipes(i)) {
        i.field_77994_a--;
        ItemStack is = i.func_77946_l();
        is.field_77994_a = 1;
        func_70299_a(0, is);
      }
    }
    return i;
  }

  public ItemStack takeFinishedCloth() {
    if (this.finished) {

      this.finished = false;
      this.clothCompletionCount = 0;
      ItemStack is = this.storage[1].func_77946_l();
      this.storage[1] = null;
      updateLoom();
      Item i = is.func_77973_b();
      if (i.equals(TFCItems.burlapCloth)) { ExpBonus.LOOM_BURLAP.give(this.entityplayer); }
      else if (i.equals(TFCItems.woolCloth)) { ExpBonus.LOOM_WOOL.give(this.entityplayer); }
      else if (i.equals(TFCItems.silkCloth)) { ExpBonus.LOOM_SILK.give(this.entityplayer); }
       return is;
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
    return getRequiredStringCount();
  }



  public String func_145825_b() {
    return "Loom";
  }

  public ModelLoom getModel() {
    if (this.field_145850_b.field_72995_K) {
      return this.model;
    }
    return null;
  }

  public void setModel(ModelLoom loomModel) {
    if (this.field_145850_b.field_72995_K) {
      this.model = loomModel;
      this.model.cloth = this.clothCompletionCount;
    }
  }



  public int func_70302_i_() {
    return 2;
  }

  public ResourceLocation getWoodResource() {
    return new ResourceLocation("terrafirmacraft", "textures/blocks/wood/WoodSheet/" + Global.WOOD_ALL[this.loomType] + ".png");
  }

  public ResourceLocation getStringResource() {
    LoomRecipe resource = null;

    if (this.storage[1] != null) {
      resource = LoomManager.getInstance().findMatchingResult(this.storage[1]);
    } else {
      resource = LoomManager.getInstance().findPotentialRecipes(this.storage[0]);
    }
    ResourceLocation rl = LoomManager.getInstance().findMatchingTexture(resource);
    return (resource != null && rl != null) ? rl : this.defaultTexture;
  }

  public Item getStringType() {
    return (this.storage[0] != null) ? this.storage[0].func_77973_b() : null;
  }

  public int getStringCount() {
    return (this.storage[0] != null) ? (this.storage[0]).field_77994_a : 0;
  }

  public void setString(ItemStack is) {
    this.storage[0] = is;
    if (!this.field_145850_b.field_72995_K) updateLoom();
  }

  public void setStringCount(int count) {
    if (this.storage[0] != null) (this.storage[0]).field_77994_a = count;
    if (!this.field_145850_b.field_72995_K) updateLoom();

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

  public boolean canWeave() {
    this.recipe = LoomManager.getInstance().findMatchingRecipe(this.storage[0]);
    return (this.recipe != null && !this.finished);
  }

  public void setIsWeaving(boolean isWeaving) {
    if (canWeave()) {
      this.weaving = isWeaving;
      if (!this.field_145850_b.field_72995_K) updateLoom();
    }
  }

  public boolean getIsWeaving() {
    return this.weaving;
  }




  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}



  public void func_70299_a(int i, ItemStack is) {
    this.storage[i] = is;
  }


  public ItemStack getInputStack() {
    return this.storage[0];
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }

  public int getRequiredStringCount() {
    if (this.storage[0] != null) {

      this.recipe = LoomManager.getInstance().findPotentialRecipes(this.storage[0]);
      if (this.recipe != null) {
        return this.recipe.getReqSize();
      }
    }
    else if (this.storage[1] != null) {

      this.recipe = LoomManager.getInstance().findMatchingResult(this.storage[1]);
      if (this.recipe != null)
      {
        return this.recipe.getReqSize();
      }
    }
    return 16;
  }

  public void finishCloth() {
    if (!this.finished) {
      NBTTagCompound nbt = new NBTTagCompound();
      this.weaving = false;
      this.finished = true;
      this.recipe = LoomManager.getInstance().findMatchingRecipe(this.storage[0]);
      this.storage[1] = this.recipe.getResult(this.storage[0]);
      setString((ItemStack)null);
      func_145841_b(nbt);
      broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
    }
  }

  public void dropItem() {
    if (!this.field_145850_b.field_72995_K) {
      ejectContents();
    }
  }

  public void finishWeaveCycle() {
    NBTTagCompound nbt = new NBTTagCompound();
    this.weaving = false;
    this.clothCompletionCount++;
    func_145841_b(nbt);
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }

  public void updateLoom() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
  }


  public int getCloth() {
    return this.clothCompletionCount;
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("loomType", this.loomType);
    nbt.func_74774_a("rotation", this.rotation);
    nbt.func_74757_a("weaving", this.weaving);
    nbt.func_74757_a("finished", this.finished);
    nbt.func_74768_a("cloth", this.clothCompletionCount);
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
    this.loomType = nbt.func_74762_e("loomType");
    this.weaving = nbt.func_74767_n("weaving");
    this.rotation = nbt.func_74771_c("rotation");
    this.finished = nbt.func_74767_n("finished");
    this.clothCompletionCount = nbt.func_74762_e("cloth");
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
    this.loomType = nbt.func_74762_e("loomType");
  }












  public void updateGui() {
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }




  public void handleInitPacket(NBTTagCompound nbt) {
    func_145839_a(nbt);
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }




  public void handleDataPacket(NBTTagCompound nbt) {
    func_145839_a(nbt);
  }



  public void createInitNBT(NBTTagCompound nbt) {
    func_145841_b(nbt);
  }



  public static void registerRecipes() {
    LoomManager.getInstance().addRecipe(new LoomRecipe(new ItemStack(TFCItems.woolYarn, 16), new ItemStack(TFCItems.woolCloth, 1)), new ResourceLocation("terrafirmacraft", "textures/blocks/String.png"));
    LoomManager.getInstance().addRecipe(new LoomRecipe(new ItemStack(Items.field_151007_F, 24), new ItemStack(TFCItems.silkCloth, 1)), new ResourceLocation("terrafirmacraft", "textures/blocks/Silk.png"));
    LoomManager.getInstance().addRecipe(new LoomRecipe(new ItemStack(TFCItems.juteFiber, 12), new ItemStack(TFCItems.burlapCloth, 1)), new ResourceLocation("terrafirmacraft", "textures/blocks/Rope.png"));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TELoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */