package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEHopper;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockHopper
  extends BlockTerraContainer
{
  private final Random random = new Random();

  @SideOnly(Side.CLIENT)
  private static IIcon hopperoutside;
  @SideOnly(Side.CLIENT)
  private static IIcon hopperTop;
  @SideOnly(Side.CLIENT)
  private static IIcon hopperInside;

  public BlockHopper() {
    super(Material.field_151573_f);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }






  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }








  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    float f = 0.125F;
    func_149676_a(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
    super.func_149743_a(world, x, y, z, aabb, list, entity);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }






  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    int j1 = Facing.field_71588_a[side];

    if (j1 == 1)
    {
      j1 = 0;
    }

    return j1;
  }






  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
    super.func_149689_a(world, x, y, z, entity, is);

    if (is.func_82837_s()) {

      TEHopper tileentityhopper = getHopperTE((IBlockAccess)world, x, y, z);
      tileentityhopper.setCustomName(is.func_82833_r());
    }
  }






  public void func_149726_b(World world, int x, int y, int z) {
    super.func_149726_b(world, x, y, z);
    updatePowerState(world, x, y, z);
  }






  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    TEHopper te = getHopperTE((IBlockAccess)world, x, y, z);
    if (world.field_72995_K) {

      if (te != null && te.pressBlock != null && player.func_70093_af()) {

        te.pressBlock = null;
        te.pressCooldown = 0;
      }
      return true;
    }


    if (te != null && te.pressCooldown == 0) {

      player.openGui(TerraFirmaCraft.instance, 49, world, x, y, z);
    }
    else if (te != null && te.pressBlock != null && player.func_70093_af()) {

      TFC_Core.giveItemToPlayer(te.pressBlock, player);
      te.pressBlock = null;
      te.pressCooldown = 0;
    }

    return true;
  }








  public void func_149695_a(World world, int x, int y, int z, Block block) {
    updatePowerState(world, x, y, z);
  }


  private void updatePowerState(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    int dir = getDirectionFromMetadata(meta);
    boolean recievesPower = !world.func_72864_z(x, y, z);
    boolean hopperPower = checkMeta(meta);

    if (recievesPower != hopperPower)
    {
      world.func_72921_c(x, y, z, dir | (recievesPower ? 0 : 8), 4);
    }
  }



  public void func_149749_a(World world, int x, int y, int z, Block block, int meta) {
    if (world.func_147438_o(x, y, z) instanceof TEHopper) {

      TEHopper te = (TEHopper)world.func_147438_o(x, y, z);

      for (int i1 = 0; i1 < te.func_70302_i_(); i1++) {

        ItemStack itemstack = te.func_70301_a(i1);

        if (itemstack != null)
        {
          while (itemstack.field_77994_a > 0) {

            int j1 = this.random.nextInt(21) + 10;

            if (j1 > itemstack.field_77994_a)
            {
              j1 = itemstack.field_77994_a;
            }

            itemstack.field_77994_a -= j1;
            EntityItem entityitem = new EntityItem(world, (x + 0.5F), (y + 0.5F), (z + 0.5F), new ItemStack(itemstack.func_77973_b(), j1, itemstack.func_77960_j()));

            if (itemstack.func_77942_o())
            {
              entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
            }


            world.func_72838_d((Entity)entityitem);
          }
        }
      }
      if (te.pressBlock != null) {

        EntityItem entityitem = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, te.pressBlock);
        world.func_72838_d((Entity)entityitem);
      }
      world.func_147453_f(x, y, z, block);
    }

    super.func_149749_a(world, x, y, z, block, meta);
  }






  public int func_149645_b() {
    return TFCBlocks.hopperRenderId;
  }






  public boolean func_149686_d() {
    return false;
  }







  public boolean func_149662_c() {
    return false;
  }







  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess blockAccess, int x, int y, int z, int side) {
    return true;
  }






  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return (side == 1) ? hopperTop : hopperoutside;
  }


  public static int getDirectionFromMetadata(int meta) {
    return meta & 0x7;
  }


  public static boolean checkMeta(int meta) {
    return ((meta & 0x8) != 8);
  }







  public boolean func_149740_M() {
    return true;
  }







  public int func_149736_g(World world, int x, int y, int z, int meta) {
    return Container.func_94526_b((IInventory)getHopperTE((IBlockAccess)world, x, y, z));
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister registerer) {
    hopperoutside = registerer.func_94245_a("hopper_outside");
    hopperTop = registerer.func_94245_a("hopper_top");
    hopperInside = registerer.func_94245_a("hopper_inside");
  }


  @SideOnly(Side.CLIENT)
  public static IIcon getHopperIcon(String s) {
    return "hopper_outside".equals(s) ? hopperoutside : ("hopper_inside".equals(s) ? hopperInside : null);
  }


  public static TEHopper getHopperTE(IBlockAccess access, int x, int y, int z) {
    return (TEHopper)access.func_147438_o(x, y, z);
  }






  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "hopper";
  }


  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEHopper();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */