package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Items.ItemBlocks.ItemBarrels;
import com.bioxx.tfc.TileEntities.TELoom;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockLoom
  extends BlockTerraContainer
{
  private String[] woodNames;

  public BlockLoom() {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_DEVICES);
    func_149676_a(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
    this.woodNames = Global.WOOD_ALL;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:wood/BarrelHoop");
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
    return TFCBlocks.loomRenderId;
  }






  public void func_149726_b(World par1World, int par2, int par3, int par4) {
    super.func_149726_b(par1World, par2, par3, par4);
  }





  public void func_149723_a(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {}





  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase player, ItemStack is) {
    super.func_149689_a(world, i, j, k, player, is);
  }







  public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
    return true;
  }



  protected ItemStack func_149644_j(int par1) {
    int j = 0;
    String s = func_149739_a();
    for (int i = 0; i < this.woodNames.length; i++)
      j = (s.substring(s.indexOf('l', s.length())) == ((ItemBarrels)TFCItems.loom).metaNames[i]) ? i : 0;
    return new ItemStack(TFCItems.loom, 1, j);
  }






  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    if (world.func_147438_o(x, y, z) instanceof TELoom) {

      TELoom te = (TELoom)world.func_147438_o(x, y, z);

      ItemStack is = new ItemStack(Item.func_150898_a(block), 1, te.loomType);
      NBTTagCompound nbt = writeLoomToNBT(te);
      is.func_77982_d(nbt);
      EntityItem ei = new EntityItem(world, x, y, z, is);
      world.func_72838_d((Entity)ei);
      te.dropItem();
      for (int s = 0; s < te.func_70302_i_(); s++)
        te.func_70299_a(s, null);
    }
    super.func_149749_a(world, x, y, z, block, meta);
  }



  protected void func_149642_a(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack) {}



  public NBTTagCompound writeLoomToNBT(TELoom te) {
    NBTTagCompound nbt = new NBTTagCompound();

    nbt.func_74768_a("loomType", te.loomType);

    return nbt;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K) {

      world.func_147471_g(x, y, z);
      return true;
    }


    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TELoom) {

      TELoom loomTE = (TELoom)te;

      if (!loomTE.isFinished()) {
        if (!loomTE.canWeave()) {
          loomTE.addString(player.func_71045_bC());
        }
        else if (player.func_70093_af()) {

          loomTE.setIsWeaving(true);
        }

      } else if (!player.func_70093_af()) {

        ItemStack item = loomTE.takeFinishedCloth();
        if (item != null) {
          item.field_77994_a = 1;

          EntityItem entityitem = new EntityItem(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, item);
          world.func_72838_d((Entity)entityitem);
        }
      }
    }
    if (player.func_70093_af())
    {
      return true;
    }

    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TELoom();
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
    if (te instanceof TELoom)
      return ((TELoom)te).loomType;
    return 0;
  }






  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();

    int damageValue = func_149643_k(world, x, y, z);
    ret.add(new ItemStack((Block)this, 1, damageValue));

    return ret;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockLoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */