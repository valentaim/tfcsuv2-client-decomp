package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TELogPile;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;





public class BlockLogPile
  extends BlockTerraContainer
{
  private IIcon[] icons = new IIcon[3];


  public BlockLogPile() {
    super(Material.field_151575_d);
    func_149675_a(true);
  }


  public static int getDirectionFromMetadata(int i) {
    return i & 0x3;
  }



  public boolean isFireSource(World world, int x, int y, int z, ForgeDirection side) {
    if (world.func_147438_o(x, y, z) instanceof TELogPile && side == ForgeDirection.UP)
    {
      if (((TELogPile)world.func_147438_o(x, y, z)).isOnFire)
        return true;
    }
    return false;
  }



  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
    if (world.field_72995_K)
    {
      return true;
    }



    Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
    if ((TELogPile)world.func_147438_o(i, j, k) != null) {



      ItemStack is = entityplayer.func_71045_bC();

      if (is != null && (is.func_77973_b() == TFCItems.logs || (fromcook != null && is.func_77973_b() == fromcook)))
      {
        return false;
      }


      entityplayer.openGui(TerraFirmaCraft.instance, 0, world, i, j, k);

      return true;
    }
    return false;
  }







  public IIcon func_149691_a(int i, int j) {
    if (j == 0 || j == 2) {

      if (i == 0)
        return this.icons[1];
      if (i == 1)
        return this.icons[1];
      if (i == 2)
        return this.icons[2];
      if (i == 3)
        return this.icons[2];
      if (i == 4)
        return this.icons[0];
      if (i == 5) {
        return this.icons[0];
      }
    }
    else if (j == 1 || j == 3) {

      if (i == 0)
        return this.icons[0];
      if (i == 1)
        return this.icons[0];
      if (i == 2)
        return this.icons[0];
      if (i == 3)
        return this.icons[0];
      if (i == 4)
        return this.icons[2];
      if (i == 5) {
        return this.icons[2];
      }
    }

    return this.icons[0];
  }




  public void func_149651_a(IIconRegister iconRegisterer) {
    this.icons[0] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Log Pile Side 0");
    this.icons[1] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Log Pile Side 1");
    this.icons[2] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Log Pile End");
  }


  public void eject(World world, int x, int y, int z) {
    if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TELogPile) {

      TELogPile te = (TELogPile)world.func_147438_o(x, y, z);
      te.ejectContents();
      world.func_147475_p(x, y, z);
    }
  }



  public Item func_149650_a(int par1, Random random, int par3) {
    return Item.func_150899_d(0);
  }



  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
    eject(world, i, j, k);
  }



  public void func_149723_a(World world, int x, int y, int z, Explosion ex) {
    eject(world, x, y, z);
  }



  public void func_149664_b(World world, int x, int y, int z, int i) {
    eject(world, x, y, z);
  }



  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    eject(world, x, y, z);
    return world.func_147468_f(x, y, z);
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TELogPile();
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TELogPile)
    {
      ((TELogPile)world.func_147438_o(x, y, z)).lightNeighbors();
    }
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (world.func_147438_o(x, y, z) instanceof TELogPile) {

      TELogPile te = (TELogPile)world.func_147438_o(x, y, z);

      if (te.isOnFire && te.fireTimer + TFCOptions.charcoalPitBurnTime < (float)TFC_Time.getTotalHours())
      {
        te.createCharcoal(x, y, z, true);
      }
    }
  }



  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {
    if (world.func_147438_o(x, y, z) instanceof TELogPile && ((TELogPile)world.func_147438_o(x, y, z)).isOnFire) {

      double centerX = (x + 0.5F);
      double centerY = (y + 2.0F);
      double centerZ = (z + 0.5F);


      world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.1D, 0.0D);
      world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.15D, 0.0D);
      world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY - 1.0D, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.1D, 0.0D);
      world.func_72869_a("smoke", centerX + rand.nextDouble() - 0.5D, centerY - 1.0D, centerZ + rand.nextDouble() - 0.5D, 0.0D, 0.15D, 0.0D);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockLogPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */