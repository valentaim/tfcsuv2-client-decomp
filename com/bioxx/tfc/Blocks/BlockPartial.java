package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.api.Tools.IToolChisel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;



public class BlockPartial
  extends BlockTerraContainer
{
  public BlockPartial(Material m) {
    super(m);
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }




  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return true;
  }




  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }



  public void func_149726_b(World world, int par2, int par3, int par4) {
    super.func_149726_b(world, par2, par3, par4);
    world.func_147471_g(par2, par3, par4);
  }





  public void func_149723_a(World world, int i, int j, int k, Explosion ex) {}





  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    boolean hasHammer = false;
    for (int i = 0; i < 9; i++) {
      if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer)
        hasHammer = true;
    }
    if (entityplayer.func_71045_bC() != null && entityplayer.func_71045_bC().func_77973_b() instanceof IToolChisel && hasHammer && !world.field_72995_K && ((IToolChisel)entityplayer
      .func_71045_bC().func_77973_b()).canChisel(entityplayer, x, y, z)) {
      Block id = world.func_147439_a(x, y, z);
      byte meta = (byte)world.func_72805_g(x, y, z);
      return ((IToolChisel)entityplayer.func_71045_bC().func_77973_b()).onUsed(world, entityplayer, x, y, z, id, meta, side, hitX, hitY, hitZ);
    }
    return false;
  }




  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {}



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEPartial();
  }



  public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
    TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
    if (te.typeID >= 0)
      return Blocks.field_150480_ab.getFlammability(Block.func_149729_e(te.typeID));
    return 0;
  }



  public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
    TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
    if (te.typeID >= 0)
      return Blocks.field_150480_ab.getEncouragement(Block.func_149729_e(te.typeID));
    return 0;
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockPartial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */