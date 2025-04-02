package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TileEntities.TEStand;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import com.bioxx.tfc.api.Interfaces.IMultipleBlock;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;



public class BlockStand
  extends BlockTerraContainer
  implements IMultipleBlock, IEquipable
{
  private String[] woodNames;

  public BlockStand() {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_DECORATION);
    func_149676_a(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:wood/BarrelHoop");
  }



  public IIcon func_149691_a(int side, int meta) {
    if (side == 0 || side == 1) {
      return TFC_Textures.invisibleTexture;
    }
    return this.field_149761_L;
  }




  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < this.woodNames.length; i++) {
      list.add(new ItemStack((Block)this, 1, i));
    }
  }


  public int func_149692_a(int dmg) {
    return dmg;
  }



  public boolean func_149662_c() {
    return false;
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
    super.func_149689_a(world, i, j, k, entityliving, is);
    TileEntity te = world.func_147438_o(i, j, k);
    if (te instanceof TEStand) {

      TEStand tes = (TEStand)te;
      tes.yaw = ((int)(entityliving.field_70177_z % 360.0F + 360.0F + 45.0F) / 90 * 90 % 360);
      if (tes.yaw % 180.0F == 0.0F)
        tes.yaw += 180.0F;
      world.func_147449_b(i, j + 1, k, (Block)this);
      world.func_72921_c(i, j + 1, k, is.func_77960_j(), 0);
      world.func_147455_a(i, j + 1, k, (TileEntity)new TEStand());
      ((TEStand)world.func_147438_o(i, j + 1, k)).isTop = true;
    }
  }



  public boolean func_149686_d() {
    return false;
  }



  public int func_149645_b() {
    return TFCBlocks.standRenderId;
  }






  public void func_149726_b(World par1World, int par2, int par3, int par4) {
    super.func_149726_b(par1World, par2, par3, par4);
  }










  public void func_149723_a(World par1World, int par2, int par3, int par4, Explosion par5Explosion) {}










  public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEStand();
  }



  public Block getBlockTypeForRender() {
    return TFCBlocks.planks;
  }


  public IEquipable.EquipType getEquipType(ItemStack is) {
    return null;
  }


  public void onEquippedRender() {
    GL11.glTranslatef(-0.0F, -0.8F, -0.63F);
    GL11.glScalef(1.8F, 1.8F, 1.8F);
    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
  }




  public boolean getTooHeavyToCarry(ItemStack is) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */