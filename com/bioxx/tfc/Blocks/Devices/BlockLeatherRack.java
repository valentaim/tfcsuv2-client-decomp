package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.TileEntities.TELeatherRack;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockLeatherRack
  extends BlockTerraContainer
{
  public IIcon scrapedTex;

  public BlockLeatherRack() {
    super(Material.field_151575_d);
    func_149647_a(null);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.001F, 1.0F);
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && world.func_147438_o(x, y, z) instanceof TELeatherRack) {

      TELeatherRack te = (TELeatherRack)world.func_147438_o(x, y, z);
      ItemStack equipped = entityplayer.func_71045_bC();

      if (te.workedArea != -1 && equipped != null && equipped.func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife) {

        int coord = (int)Math.floor((hitX / 0.25F)) + (int)Math.floor((hitZ / 0.25F)) * 4;
        if ((te.workedArea >> coord & 0x1) == 0)
        {
          te.workArea(coord);
          NBTTagCompound nbt = new NBTTagCompound();
          nbt.func_74777_a("workedArea", te.workedArea);
          te.broadcastPacketInRange((AbstractPacket)te.createDataPacket(nbt));
          equipped.func_77972_a(1, (EntityLivingBase)entityplayer);
        }

      } else {

        world.func_147468_f(x, y, z);
      }
    }

    if (!func_149718_j(world, x, y, z))
    {
      world.func_147468_f(x, y, z);
    }

    return false;
  }



  public boolean func_149718_j(World world, int x, int y, int z) {
    if (world.func_147438_o(x, y, z) instanceof TELeatherRack) {

      TELeatherRack te = (TELeatherRack)world.func_147438_o(x, y, z);
      if (te.leatherItem == null)
      {
        return false;
      }
    }

    return (world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151575_d);
  }



  public void func_149695_a(World world, int i, int j, int k, Block par5) {
    super.func_149695_a(world, i, j, k, par5);
    if (!func_149718_j(world, i, j, k)) {
      world.func_147465_d(i, j, k, Blocks.field_150350_a, 0, 2);
    }
  }


  public void func_149725_f(World world, int i, int j, int k, int meta) {
    if (!world.field_72995_K && world.func_147438_o(i, j, k) instanceof TELeatherRack) {

      TELeatherRack te = (TELeatherRack)world.func_147438_o(i, j, k);
      if (te.leatherItem != null) {

        EntityItem ei = new EntityItem(world, i, j, k, te.leatherItem);
        ei.field_70159_w = 0.0D;
        ei.field_70179_y = 0.0D;
        world.func_72838_d((Entity)ei);
      }
    }
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }



  public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public int func_149645_b() {
    return TFCBlocks.leatherRackRenderId;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:Soaked Hide");
    this.scrapedTex = iconRegisterer.func_94245_a("terrafirmacraft:Scraped Hide");
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
    return true;
  }



  public TileEntity func_149915_a(World world, int var2) {
    return (TileEntity)new TELeatherRack();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockLeatherRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */