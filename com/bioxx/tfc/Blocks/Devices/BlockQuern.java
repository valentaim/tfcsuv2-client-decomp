package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEQuern;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockQuern
  extends BlockTerraContainer
{
  private IIcon quernBase;
  private IIcon quernTop1;
  private IIcon quernTop2;

  public BlockQuern() {
    super(Material.field_151576_e);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    super.func_149727_a(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
    TEQuern te = (TEQuern)world.func_147438_o(x, y, z);


    Boolean hit = Boolean.valueOf(((side == 1 && hitX >= 0.7D && hitX <= 90.0F && hitZ >= 0.7D && hitZ <= 0.9D) || (side == 5 && hitZ >= 0.8D && hitZ <= 0.99D && hitY >= 0.85D)));


    if (!world.field_72995_K) {

      if (!te.shouldRotate && hit.booleanValue() && te.storage[2] != null)
      {
        te.shouldRotate = true;
        world.func_147471_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
        world.func_72908_a(x, y, z, "terrafirmacraft:quern.stonedrag", 1.0F, 1.0F);
        entityplayer.func_71029_a((StatBase)TFC_Achievements.achQuern);
      }
      else if ((!te.shouldRotate && !hit.booleanValue()) || te.storage[2] == null)
      {
        entityplayer.openGui(TerraFirmaCraft.instance, 33, world, x, y, z);
      }

    } else if (!te.shouldRotate && hit.booleanValue() && te.hasQuern) {

      te.shouldRotate = true;
    }
    return true;
  }



  public IIcon func_149691_a(int i, int j) {
    if (i == 0 || (i == 1 && j == 1))
      return this.quernTop1;
    if (i == 1 && j == 0)
      return this.quernTop2;
    return this.quernBase;
  }



  public void func_149651_a(IIconRegister registerer) {
    this.quernBase = registerer.func_94245_a("terrafirmacraft:devices/Quern Base");
    this.quernTop1 = registerer.func_94245_a("terrafirmacraft:devices/Quern Top 1");
    this.quernTop2 = registerer.func_94245_a("terrafirmacraft:devices/Quern Top 2");
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
    return TFCBlocks.quernRenderId;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
    return true;
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), y + 0.825D, (z + 1));
  }



  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), y + 0.825D, (z + 1));
  }



  public TileEntity func_149915_a(World world, int var2) {
    return (TileEntity)new TEQuern();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockQuern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */