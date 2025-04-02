package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Entities.EntityBarrel;
import com.bioxx.tfc.Items.ItemBlocks.ItemBarrels;
import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;








public class BlockBarrel
  extends BlockTerraContainer
{
  private String[] woodNames;

  public BlockBarrel() {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_DEVICES);
    func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
    this.woodNames = Global.WOOD_ALL;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:wood/BarrelHoop");
    TFC_Textures.guiSolidStorage = iconRegisterer.func_94245_a("terrafirmacraft:button_barrel_solid");
    TFC_Textures.guiLiquidStorage = iconRegisterer.func_94245_a("terrafirmacraft:button_barrel_liquid");
  }




  public IIcon func_149691_a(int side, int meta) {
    if (side >= 10) {

      side -= 10;
      if (side == 0 || side == 1) {
        return TFC_Textures.invisibleTexture;
      }
      return this.field_149761_L;
    }
    if (meta < 16)
      return TFCBlocks.planks.func_149691_a(side, meta);
    return TFCBlocks.planks2.func_149691_a(side, meta - 16);
  }





  public IIcon func_149673_e(IBlockAccess access, int x, int y, int z, int side) {
    if (side == 0 || side == 1) {
      return TFC_Textures.invisibleTexture;
    }
    return this.field_149761_L;
  }




  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    for (int i = 0; i < this.woodNames.length; i++) {
      par3List.add(new ItemStack((Block)this, 1, i));
    }
  }


  public int func_149692_a(int dmg) {
    return dmg;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public int func_149645_b() {
    return TFCBlocks.barrelRenderId;
  }



  public void func_149725_f(World world, int x, int y, int z, int meta) {
    if (!world.field_72995_K) {

      TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
      if (te != null && te.shouldDropItem && world.func_82736_K().func_82766_b("doTileDrops"))
      {
        if (te.getSealed()) {

          ItemStack is = new ItemStack(Item.func_150898_a((Block)this), 1, te.barrelType);
          NBTTagCompound nbt = writeBarrelToNBT(te);
          is.func_77982_d(nbt);
          EntityItem ei = new EntityItem(world, x, y, z, is);
          world.func_72838_d((Entity)ei);

          te.fluid = null;

          for (int s = 0; s < te.func_70302_i_(); s++) {
            te.func_70299_a(s, null);
          }
        } else {

          ItemStack is = new ItemStack(Item.func_150898_a((Block)this), 1, te.barrelType);
          EntityItem ei = new EntityItem(world, x, y, z, is);
          world.func_72838_d((Entity)ei);
        }
      }
    }
  }




  public void onBlockExploded(World world, int x, int y, int z, Explosion explosion) {
    func_149723_a(world, x, y, z, explosion);
    world.func_147468_f(x, y, z);
  }



  public void func_149723_a(World world, int x, int y, int z, Explosion exp) {
    if (world.func_147438_o(x, y, z) instanceof TEBarrel) {

      TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);

      if (this == TFCBlocks.barrel && te != null && te.getGunPowderCount() >= 12 && te.getSealed()) {

        spawnPowderKeg(world, x, y, z, te, true);

        return;
      }
    }
    super.func_149723_a(world, x, y, z, exp);
  }






  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack is) {
    super.func_149689_a(world, i, j, k, player, is);
    TEBarrel teb = null;
    TileEntity te = world.func_147438_o(i, j, k);
    if (te != null && is.func_77942_o() && te instanceof TEBarrel) {

      teb = (TEBarrel)te;
      teb.readFromItemNBT(is.func_77978_p());
      world.func_147471_g(i, j, k);
    }
  }






  public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
    return true;
  }




  public boolean func_149659_a(Explosion exp) {
    return true;
  }



  protected ItemStack func_149644_j(int par1) {
    int j = 0;
    String s = func_149739_a();
    for (int i = 0; i < this.woodNames.length; i++)
      j = (s.substring(s.indexOf('l', s.length())) == ((ItemBarrels)TFCItems.barrel).metaNames[i]) ? i : 0;
    return new ItemStack(TFCItems.barrel, 1, j);
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (world.func_147438_o(x, y, z) instanceof TEBarrel) {

      TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);

      if (this == TFCBlocks.barrel && te != null && te.getGunPowderCount() >= 12 && te.getSealed()) {

        boolean fireNearby = false;
        if (world.func_147439_a(x - 1, y, z) instanceof net.minecraft.block.BlockFire)
          fireNearby = true;
        if (world.func_147439_a(x + 1, y, z) instanceof net.minecraft.block.BlockFire)
          fireNearby = true;
        if (world.func_147439_a(x, y, z - 1) instanceof net.minecraft.block.BlockFire)
          fireNearby = true;
        if (world.func_147439_a(x, y, z + 1) instanceof net.minecraft.block.BlockFire) {
          fireNearby = true;
        }
        if (fireNearby || world.func_72864_z(x, y, z)) {

          spawnPowderKeg(world, x, y, z, te, false);
          world.func_147468_f(x, y, z);
        }
      }
    }
  }



  protected void func_149642_a(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack) {}



  public NBTTagCompound writeBarrelToNBT(TEBarrel te) {
    NBTTagCompound nbt = new NBTTagCompound();

    NBTTagCompound fluidNBT = new NBTTagCompound();
    if (te.fluid != null)
      te.fluid.writeToNBT(fluidNBT);
    nbt.func_74782_a("fluidNBT", (NBTBase)fluidNBT);
    nbt.func_74768_a("barrelType", te.barrelType);
    nbt.func_74768_a("SealTime", te.sealtime);
    nbt.func_74757_a("Sealed", te.getSealed());

    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < te.storage.length; i++) {

      if (te.storage[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        te.storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    if (nbttaglist.func_74745_c() > 0) {
      nbt.func_74782_a("Items", (NBTBase)nbttaglist);
    }
    return nbt;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K) {

      world.func_147471_g(x, y, z);
      return true;
    }



    if (player.func_70093_af())
    {
      return false;
    }

    if (world.func_147438_o(x, y, z) instanceof TEBarrel) {

      TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);

      if (this == TFCBlocks.barrel && te.getSealed() && te.getGunPowderCount() >= 12 && player
        .func_71045_bC() != null && player.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemFlintAndSteel) {

        spawnPowderKeg(world, x, y, z, te, false);
        world.func_147468_f(x, y, z);
        return true;
      }

      if (!handleInteraction(player, te)) {

        if (te.getFluidLevel() > 0 || te.getInvCount() == 0) {
          player.openGui(TerraFirmaCraft.instance, 35, world, x, y, z);
        } else {
          player.openGui(TerraFirmaCraft.instance, 36, world, x, y, z);
        }  return true;
      }
      return true;
    }

    return false;
  }




  public void func_149749_a(World world, int x, int y, int z, Block p_149749_5_, int p_149749_6_) {
    TileEntity tileEntity = world.func_147438_o(x, y, z);
    if (tileEntity != null && tileEntity instanceof TEBarrel) {
      TEBarrel barrel = (TEBarrel)tileEntity;
      barrel.ejectContents();
      barrel.clearInventory();
      barrel.updateGui();
    }
    super.func_149749_a(world, x, y, z, p_149749_5_, p_149749_6_);
  }




  protected boolean handleInteraction(EntityPlayer player, TEBarrel te) {
    if (!te.getSealed() && te.getInvCount() <= 1 && !(te.func_145831_w()).field_72995_K) {

      ItemStack equippedItem = player.func_71045_bC();
      if (equippedItem == null) {
        return false;
      }
      if ((FluidContainerRegistry.isFilledContainer(equippedItem) || (equippedItem
        .func_77973_b() instanceof IFluidContainerItem && ((IFluidContainerItem)equippedItem.func_77973_b()).getFluid(equippedItem) != null)) &&
        !te.getSealed()) {

        ItemStack tmp = equippedItem.func_77946_l();
        tmp.field_77994_a = 1;
        ItemStack is = te.addLiquid(tmp);


        if (ItemStack.func_77989_b(equippedItem, is))
        {
          return false;
        }

        equippedItem.field_77994_a--;

        if (equippedItem.field_77994_a == 0) {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        }
        if (equippedItem.field_77994_a == 0 && (is.func_77976_d() == 1 || !player.field_71071_by.func_70431_c(is))) {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, is);

        }
        else if (!player.field_71071_by.func_70441_a(is)) {
          player.func_71019_a(is, false);
        }

        if (player.field_71069_bz != null)
        {

          player.field_71069_bz.func_75142_b();
        }

        return true;
      }
      if (FluidContainerRegistry.isEmptyContainer(equippedItem) || equippedItem.func_77973_b() instanceof IFluidContainerItem) {

        ItemStack tmp = equippedItem.func_77946_l();
        tmp.field_77994_a = 1;
        ItemStack is = te.removeLiquid(tmp);


        if (ItemStack.func_77989_b(equippedItem, is))
        {
          return false;
        }

        if (is.func_77973_b() == TFCItems.woodenBucketMilk)
        {
          ItemCustomBucketMilk.createTag(is, 20.0F);
        }

        equippedItem.field_77994_a--;

        if (equippedItem.field_77994_a == 0) {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        }
        if (equippedItem.field_77994_a == 0 && (is.func_77976_d() == 1 || !player.field_71071_by.func_70431_c(is))) {
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, is);

        }
        else if (!player.field_71071_by.func_70441_a(is)) {
          player.func_71019_a(is, false);
        }

        if (player.field_71069_bz != null)
        {

          player.field_71069_bz.func_75142_b();
        }

        return true;
      }
      if (equippedItem.func_77973_b() instanceof ItemBarrels || equippedItem.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel) {

        ItemStack is = equippedItem.func_77946_l();
        is.field_77994_a = 1;
        if (equippedItem.func_77942_o()) {

          if (equippedItem.func_77978_p().func_74764_b("fluidNBT") && !equippedItem.func_77978_p().func_74764_b("Items") && te.getFluidLevel() < te.getMaxLiquid())
          {
            FluidStack fs = FluidStack.loadFluidStackFromNBT(equippedItem.func_77978_p().func_74775_l("fluidNBT"));
            if (te.addLiquid(fs))
            {
              if (fs.amount == 0) {

                equippedItem.func_77978_p().func_82580_o("Sealed");
                equippedItem.func_77978_p().func_82580_o("fluidNBT");
                if (equippedItem.func_77978_p().func_82582_d()) {
                  equippedItem.func_77982_d(null);
                }
              } else {

                fs.writeToNBT(equippedItem.func_77978_p().func_74775_l("fluidNBT"));
              }
              return true;
            }

          }

        }
        else if (te.getFluidStack() != null) {

          NBTTagCompound nbt = new NBTTagCompound();
          if (is.func_77973_b() instanceof ItemBarrels) {

            nbt.func_74782_a("fluidNBT", (NBTBase)te.getFluidStack().writeToNBT(new NBTTagCompound()));
            nbt.func_74757_a("Sealed", true);
            is.func_77982_d(nbt);
            te.actionEmpty();
            equippedItem.field_77994_a--;
            TFC_Core.giveItemToPlayer(is, player);
          }
          else if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemLargeVessel) {

            if (is.func_77960_j() == 0) {
              return false;
            }
            FluidStack fs = te.getFluidStack().copy();
            if (fs.amount > 5000) {

              fs.amount = 5000;
              te.drainLiquid(5000);
            }
            else {

              te.actionEmpty();
            }
            nbt.func_74782_a("fluidNBT", (NBTBase)fs.writeToNBT(new NBTTagCompound()));
            nbt.func_74757_a("Sealed", true);
            is.func_77982_d(nbt);
            equippedItem.field_77994_a--;
            TFC_Core.giveItemToPlayer(is, player);
          }
          return true;
        }
      }
    }

    return false;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEBarrel();
  }



  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }






  public int func_149643_k(World world, int x, int y, int z) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TEBarrel)
      return ((TEBarrel)te).barrelType;
    return 0;
  }






  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();

    int damageValue = func_149643_k(world, x, y, z);
    ret.add(new ItemStack((Block)this, 1, damageValue));

    return ret;
  }


  public void spawnPowderKeg(World world, int x, int y, int z, TEBarrel te, boolean shortFuse) {
    if (TFCOptions.enablePowderKegs) {

      ItemStack is = new ItemStack((Block)this, 1, te.barrelType);
      NBTTagCompound nbt = writeBarrelToNBT(te);
      is.func_77982_d(nbt);
      EntityBarrel entity = new EntityBarrel(world, x, y, z, is, te.getGunPowderCount());
      te.clearInventory();
      te.shouldDropItem = false;
      if (shortFuse) {

        entity.setFuse(1);
        world.func_72838_d((Entity)entity);
      }
      else {

        world.func_72838_d((Entity)entity);
        world.func_72956_a((Entity)entity, "game.tnt.primed", 1.0F, 1.0F);
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */