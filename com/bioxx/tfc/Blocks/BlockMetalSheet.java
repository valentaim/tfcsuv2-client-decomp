package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.CollisionRayTraceStandard;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEMetalSheet;
import com.bioxx.tfc.api.Interfaces.ICustomCollision;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockMetalSheet
  extends BlockTerraContainer
  implements ICustomCollision
{
  public IIcon[] icons;
  public String[] metalNames = new String[] { "Bismuth", "Bismuth Bronze", "Black Bronze", "Black Steel", "Blue Steel", "Brass", "Bronze", "Copper", "Gold", "Wrought Iron", "Lead", "Nickel", "Pig Iron", "Platinum", "Red Steel", "Rose Gold", "Silver", "Steel", "Sterling Silver", "Tin", "Zinc" };



  public BlockMetalSheet() {
    super(Material.field_151573_f);
    this.icons = new IIcon[this.metalNames.length];
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }



  public Item func_149650_a(int par1, Random par2Random, int par3) {
    return Item.func_150899_d(0);
  }




  public float func_149712_f(World world, int x, int y, int z) {
    return this.field_149782_v;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess blockAccess, int x, int y, int z, int side) {
    return true;
  }



  public void func_149725_f(World world, int i, int j, int k, int meta) {
    if (world.func_147438_o(i, j, k) instanceof TEMetalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(i, j, k);
      if (te.sheetStack != null) {

        int stack = 0;
        if (te.topExists()) stack++;
        if (te.bottomExists()) stack++;
        if (te.northExists()) stack++;
        if (te.southExists()) stack++;
        if (te.eastExists()) stack++;
        if (te.westExists()) stack++;
        te.sheetStack.field_77994_a = stack;
        EntityItem ei = new EntityItem(world, i, j, k, te.sheetStack);
        world.func_72838_d((Entity)ei);
      } else {

        TerraFirmaCraft.LOG.error("Metal sheet block (" + i + ", " + j + ", " + k + ") being broken contains null sheetstack. Please report this on the forums.");
      }
    }
  }


  public void onBlockExploded(World world, int i, int j, int k, Explosion explosion) {
    TEMetalSheet te = (TEMetalSheet)world.func_147438_o(i, j, k);
    if (te != null) {
      te.clearSides();
    }
    super.onBlockExploded(world, i, j, k, explosion);
  }



  public int func_149645_b() {
    return TFCBlocks.metalsheetRenderId;
  }



  public boolean func_149686_d() {
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public void func_149651_a(IIconRegister registerer) {
    for (int i = 0; i < this.icons.length; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:metal/" + this.metalNames[i]);
    }
    TFC_Textures.sheetBismuth = this.icons[0];
    TFC_Textures.sheetBismuthBronze = this.icons[1];
    TFC_Textures.sheetBlackBronze = this.icons[2];
    TFC_Textures.sheetBlackSteel = this.icons[3];
    TFC_Textures.sheetBlueSteel = this.icons[4];
    TFC_Textures.sheetBrass = this.icons[5];
    TFC_Textures.sheetBronze = this.icons[6];
    TFC_Textures.sheetCopper = this.icons[7];
    TFC_Textures.sheetGold = this.icons[8];
    TFC_Textures.sheetWroughtIron = this.icons[9];
    TFC_Textures.sheetLead = this.icons[10];
    TFC_Textures.sheetNickel = this.icons[11];
    TFC_Textures.sheetPigIron = this.icons[12];
    TFC_Textures.sheetPlatinum = this.icons[13];
    TFC_Textures.sheetRedSteel = this.icons[14];
    TFC_Textures.sheetRoseGold = this.icons[15];
    TFC_Textures.sheetSilver = this.icons[16];
    TFC_Textures.sheetSteel = this.icons[17];
    TFC_Textures.sheetSterlingSilver = this.icons[18];
    TFC_Textures.sheetTin = this.icons[19];
    TFC_Textures.sheetZinc = this.icons[20];
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEMetalSheet();
  }



  public IIcon func_149691_a(int side, int meta) {
    if (meta >= 0 && meta < this.icons.length)
      return this.icons[meta];
    return this.icons[19];
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int meta) {
    TEMetalSheet te = (TEMetalSheet)access.func_147438_o(i, j, k);
    if (te != null) {
      return this.icons[te.metalID];
    }
    return this.icons[19];
  }




  public void addCollisionBoxesToList(World world, int i, int j, int k, List<Object[]> list) {
    TEMetalSheet te = (TEMetalSheet)world.func_147438_o(i, j, k);
    double f0 = 0.0625D;
    double f1 = 0.9375D;
    double yMax = 1.0D;
    double yMin = 0.0D;

    if (te.topExists())
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, f1, 0.0D, 1.0D, 1.0D, 1.0D), Integer.valueOf(0) });
    if (te.bottomExists())
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, f0, 1.0D), Integer.valueOf(1) });
    if (te.northExists())
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, yMin, 0.0D, 1.0D, yMax, f0), Integer.valueOf(2) });
    if (te.southExists())
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, yMin, f1, 1.0D, yMax, 1.0D), Integer.valueOf(3) });
    if (te.eastExists())
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, yMin, 0.0D, f0, yMax, 1.0D), Integer.valueOf(4) });
    if (te.westExists()) {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(f1, yMin, 0.0D, 1.0D, yMax, 1.0D), Integer.valueOf(5) });
    }
  }



  public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB aabb, List<AxisAlignedBB> list, Entity entity) {
    ArrayList<Object[]> l = new ArrayList();
    addCollisionBoxesToList(world, i, j, k, l);
    for (Object[] o : l) {

      AxisAlignedBB a = (AxisAlignedBB)o[0];
      if (a != null && aabb.func_72326_a(a)) {
        list.add(a);
      }
    }
  }


  public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) {
    return CollisionRayTraceStandard.collisionRayTrace(this, world, x, y, z, player, view);
  }



  public boolean func_149747_d(IBlockAccess world, int x, int y, int z, int side) {
    TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
    switch (side) {
      case 0:
        return te.bottomExists();
      case 1: return te.topExists();
      case 2: return te.northExists();
      case 3: return te.southExists();
      case 4: return te.eastExists();
      case 5: return te.westExists();
    }  return false;
  }




  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
    switch (side) {
      case DOWN:
        return te.bottomExists();
      case UP: return te.topExists();
      case NORTH: return te.northExists();
      case SOUTH: return te.southExists();
      case EAST: return te.eastExists();
      case WEST: return te.westExists();
    }  return false;
  }





























  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return (world.func_147439_a(x, y, z) == this);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockMetalSheet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */