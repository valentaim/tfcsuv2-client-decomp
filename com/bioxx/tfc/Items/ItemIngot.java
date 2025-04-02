package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEIngotPile;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;



public class ItemIngot
  extends ItemTerra
  implements ISmeltable
{
  private EnumSize size = EnumSize.SMALL;

  private String metal;

  private short metalAmount;

  private boolean smeltable = true;

  public ItemIngot() {
    func_77637_a(TFCTabs.TFC_MATERIALS);
    setFolder("ingots/");
    this.metalAmount = 100;
  }


  public ItemIngot(boolean canSmelt) {
    this();
    this.smeltable = canSmelt;
  }


  public ItemTerra setMetal(String m, int amt) {
    this.metal = m;
    this.metalAmount = (short)amt;
    return this;
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "").replace("Weak ", "").replace("HC ", ""));
  }



  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }



  public EnumSize getSize(ItemStack is) {
    return this.size;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.MEDIUM;
  }



  public ItemIngot setSize(EnumSize s) {
    this.size = s;
    return this;
  }


  public void addCreativeItems(List<ItemStack> list) {
    list.add(new ItemStack(this));
  }



  private boolean createPile(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, int l) {
    boolean fullStack = true;

    TEIngotPile te = null;

    if (world.func_147438_o(x, y, z) instanceof TEIngotPile && world.func_147439_a(x, y, z) == TFCBlocks.ingotPile) {

      te = (TEIngotPile)world.func_147438_o(x, y, z);
      if (te.contentsMatch(0, itemstack) && (te.func_70301_a(0)).field_77994_a < te.func_70297_j_()) {

        fullStack = false;
        te.injectContents(0, 1);
      }
    } else {
      fullStack = true;
    }
    if (fullStack && isPlaceable(itemstack)) {

      if (side == 0 && world.func_147437_c(x, y - 1, z) && isValid(world, x, y - 1, z)) {

        world.func_147465_d(x, y - 1, z, TFCBlocks.ingotPile, l, 2);
        if (world.field_72995_K) {
          world.func_147471_g(x, y - 1, z);
        }
        te = (TEIngotPile)world.func_147438_o(x, y - 1, z);
      }
      else if (side == 1 && world.func_147437_c(x, y + 1, z) && isValid(world, x, y + 1, z)) {

        world.func_147465_d(x, y + 1, z, TFCBlocks.ingotPile, l, 2);
        if (world.field_72995_K) {
          world.func_147471_g(x, y + 1, z);
        }
        te = (TEIngotPile)world.func_147438_o(x, y + 1, z);
      }
      else if (side == 2 && world.func_147437_c(x, y, z - 1) && isValid(world, x, y, z - 1)) {

        world.func_147465_d(x, y, z - 1, TFCBlocks.ingotPile, l, 2);
        if (world.field_72995_K) {
          world.func_147471_g(x, y, z - 1);
        }
        te = (TEIngotPile)world.func_147438_o(x, y, z - 1);
      }
      else if (side == 3 && world.func_147437_c(x, y, z + 1) && isValid(world, x, y, z + 1)) {

        world.func_147465_d(x, y, z + 1, TFCBlocks.ingotPile, l, 2);
        if (world.field_72995_K) {
          world.func_147471_g(x, y, z + 1);
        }
        te = (TEIngotPile)world.func_147438_o(x, y, z + 1);
      }
      else if (side == 4 && world.func_147437_c(x - 1, y, z) && isValid(world, x - 1, y, z)) {

        world.func_147465_d(x - 1, y, z, TFCBlocks.ingotPile, l, 2);
        if (world.field_72995_K) {
          world.func_147471_g(x - 1, y, z);
        }
        te = (TEIngotPile)world.func_147438_o(x - 1, y, z);
      }
      else if (side == 5 && world.func_147437_c(x + 1, y, z) && isValid(world, x + 1, y, z)) {

        world.func_147465_d(x + 1, y, z, TFCBlocks.ingotPile, l, 2);
        if (world.field_72995_K) {
          world.func_147471_g(x + 1, y, z);
        }
        te = (TEIngotPile)world.func_147438_o(x + 1, y, z);
      }
      else {

        return false;
      }

      if (te != null) {

        te.storage[0] = new ItemStack(this, 1, 0);
        te.setType((MetalRegistry.instance.getMetalFromItem(this)).name);

        if (entityplayer.field_71075_bZ.field_75098_d)
        {
          te.storage[0] = new ItemStack(this, 32, 0);
        }
      }
    }
    return true;
  }


  public boolean isPlaceable(ItemStack is) {
    Item id = is.func_77973_b();

    return (id != TFCItems.weakSteelIngot && id != TFCItems.highCarbonSteelIngot && id != TFCItems.highCarbonBlackSteelIngot && id != TFCItems.weakRedSteelIngot && id != TFCItems.weakBlueSteelIngot && id != TFCItems.highCarbonRedSteelIngot && id != TFCItems.highCarbonBlueSteelIngot);
  }





  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    NBTTagCompound stackTagCompound = itemstack.func_77978_p();

    if (entityplayer.func_70093_af() && stackTagCompound == null && itemstack.func_77973_b().func_77658_a().indexOf("Double") == -1 &&
      isPlaceable(itemstack)) {

      int dir = MathHelper.func_76128_c((entityplayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      if (!world.field_72995_K && entityplayer.func_70093_af() && (world.func_147439_a(x, y, z) != TFCBlocks.ingotPile || (side != 1 && side != 0))) {


        if (createPile(itemstack, entityplayer, world, x, y, z, side, dir))
        {

          itemstack.field_77994_a--;
          world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
          return true;
        }

      }
      else if (world.func_147439_a(x, y, z) == TFCBlocks.ingotPile) {

        TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);

        if (te != null)
        {
          te.func_145838_q().func_149727_a(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
          if (te.storage[0] != null && te.contentsMatch(0, itemstack) && (te.storage[0]).field_77994_a < 64) {

            te.injectContents(0, 1);
            te.func_145829_t();
          } else {
            if (te.storage[0] != null && !te.contentsMatch(0, itemstack) && (te.storage[0]).field_77994_a < 64)
            {
              return false;
            }
            if (te.storage[0] == null) {

              te.addContents(0, new ItemStack(this, 1));
            }
            else {

              if (createPile(itemstack, entityplayer, world, x, y, z, side, dir)) {

                itemstack.field_77994_a--;




                world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
                te.func_145838_q().func_149727_a(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
              }
              return true;
            }
          }
          itemstack.field_77994_a--;




          world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
          return true;
        }

      }
      else {

        int m = itemstack.func_77960_j();
        if (side == 1) {

          if (m >= 16) {
            world.func_147465_d(x, y + 1, z, TFCBlocks.ingotPile, m, 2);
            itemstack.field_77994_a--;
          } else {

            world.func_147465_d(x, y + 1, z, TFCBlocks.ingotPile, m, 2);
            itemstack.field_77994_a--;
          }

        } else if (side == 0 && world.func_147437_c(x, y - 1, z)) {

          if (m >= 16) {
            world.func_147465_d(x, y - 1, z, TFCBlocks.ingotPile, m, 2);
            itemstack.field_77994_a--;
          } else {

            world.func_147465_d(x, y - 1, z, TFCBlocks.ingotPile, m, 2);
            itemstack.field_77994_a--;
          }

        } else if (side == 2 && world.func_147437_c(x, y, z - 1)) {

          setSide(world, itemstack, m, dir, x, y, z, 0, 0, -1);
        }
        else if (side == 3 && world.func_147437_c(x, y, z + 1)) {

          setSide(world, itemstack, m, dir, x, y, z, 0, 0, 1);
        }
        else if (side == 4 && world.func_147437_c(x - 1, y, z)) {

          setSide(world, itemstack, m, dir, x, y, z, -1, 0, 0);
        }
        else if (side == 5 && world.func_147437_c(x + 1, y, z)) {

          setSide(world, itemstack, m, dir, x, y, z, 1, 0, 0);
        }




        world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
        return true;
      }
    }

    return false;
  }


  public boolean isValid(World world, int i, int j, int k) {
    if (world.isSideSolid(i, j - 1, k, ForgeDirection.UP) || world.func_147439_a(i, j - 1, k) == TFCBlocks.ingotPile) {

      TileEntity te = world.func_147438_o(i, j - 1, k);

      if (te instanceof TEIngotPile) {

        TEIngotPile ip = (TEIngotPile)te;

        if (ip.storage[0] == null || (ip.storage[0]).field_77994_a < 64)
        {
          return false;
        }
      }
      return true;
    }
    return false;
  }


  public void setSide(World world, ItemStack itemstack, int m, int dir, int x, int y, int z, int i, int j, int k) {
    if (m < 8) {

      if (dir == 0 || dir == 2) {
        world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m, 2);
      } else {
        world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m | 0x8, 2);
      }
      itemstack.field_77994_a--;
    }
    else if (m >= 16) {

      if (dir == 0 || dir == 2) {
        world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m - 8, 2);
      } else {
        world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m - 8 | 0x8, 2);
      }
      itemstack.field_77994_a--;
    }
  }




  public Metal getMetalType(ItemStack is) {
    if (this.metal == null)
    {
      return MetalRegistry.instance.getMetalFromItem(this);
    }


    return MetalRegistry.instance.getMetalFromString(this.metal);
  }




  public short getMetalReturnAmount(ItemStack is) {
    return this.metalAmount;
  }




  public boolean isSmeltable(ItemStack is) {
    return this.smeltable;
  }



  public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
    return ISmeltable.EnumTier.TierI;
  }




  public int getItemStackLimit(ItemStack is) {
    if (is.func_77942_o()) {

      NBTTagCompound tag = is.func_77978_p();
      if (TFC_ItemHeat.hasTemp(is) || tag.func_74764_b("itemCraftingValue") || tag.func_74764_b("itemCraftingRule1"))
      {
        return 1;
      }
    }

    return super.getItemStackLimit(is);
  }


  public boolean onEntityItemUpdate(EntityItem entityItem) {
    return false;
  }



  public boolean hasCustomEntity(ItemStack stack) {
    return true;
  }


  public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
    EntityTossIngotItem customIngotEntity = null;
    List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(entity.field_70165_t - 1.0D, entity.field_70163_u - 1.0D, entity.field_70161_v - 1.0D, entity.field_70165_t + 1.0D, entity.field_70163_u + 1.0D, entity.field_70161_v + 1.0D));

    if (players.size() > 0 && players.get(0) != null) {
      EntityPlayer p = players.get(0);
      customIngotEntity = new EntityTossIngotItem(world, p.field_70165_t, p.field_70163_u - 0.30000001192092896D + p.func_70047_e(), p.field_70161_v, itemstack);
      customIngotEntity.field_145804_b = 40;

      float var5 = 0.1F;

      var5 = 0.3F;
      customIngotEntity.field_70159_w = (-MathHelper.func_76126_a(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
      customIngotEntity.field_70179_y = (MathHelper.func_76134_b(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
      customIngotEntity.field_70181_x = (-MathHelper.func_76126_a(p.field_70125_A / 180.0F * 3.1415927F) * var5 + 0.1F);
      var5 = 0.02F;
      float var6 = world.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
      var5 *= world.field_73012_v.nextFloat();
      customIngotEntity.field_70159_w += Math.cos(var6) * var5;
      customIngotEntity.field_70181_x += ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F);
      customIngotEntity.field_70179_y += Math.sin(var6) * var5;
    } else {
      customIngotEntity = new EntityTossIngotItem(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, itemstack);
      customIngotEntity.field_145804_b = 40;
    }

    return (Entity)customIngotEntity;
  }

  private class EntityTossIngotItem
    extends EntityItem {
    public EntityTossIngotItem(World p_i1711_1_) {
      super(p_i1711_1_);
    }

    public EntityTossIngotItem(World world, double posX, double posY, double posZ, ItemStack itemStack) {
      super(world, posX, posY, posZ, itemStack);
    }

    public void func_70071_h_() {
      if (this.field_70170_p.func_82737_E() % 10L == 0L) {
        ItemStack ingot = func_92059_d();
        if (TFC_ItemHeat.hasTemp(ingot)) {
          Block block = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
          if (TFC_Core.isWater(block)) {
            TFC_ItemHeat.removeTempTag(ingot);
            func_85030_a("random.fizz", 0.4F, 2.0F + this.field_70146_Z.nextFloat() * 0.4F);
            func_92058_a(ingot);
          }
        }
      }
      super.func_70071_h_();
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemIngot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */