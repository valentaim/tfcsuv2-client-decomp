package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.CollisionRayTraceStandard;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Interfaces.ICustomCollision;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSulfur
  extends BlockTerra
  implements ICustomCollision
{
  private int itemMeta = Arrays.<String>asList(Global.POWDER).indexOf("Sulfur Powder");
  private IIcon[] icons = new IIcon[4];


  public BlockSulfur(Material material) {
    super(material);
  }



  public IIcon func_149691_a(int i, int j) {
    return this.icons[j];
  }



  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    return null;
  }



  public int func_149692_a(int dmg) {
    return this.itemMeta;
  }



  public void func_149651_a(IIconRegister registerer) {
    for (int i = 0; i < 4; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:ores/Sulfur" + i);
    }
  }


  public int func_149645_b() {
    return TFCBlocks.sulfurRenderId;
  }




  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
    func_149642_a(world, i, j, k, new ItemStack(TFCItems.powder, func_149745_a(new Random()), this.itemMeta));
  }


  public Item func_149650_a(int par1, Random par2Random, int par3) {
    return TFCItems.powder;
  }



  public boolean func_149637_q() {
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public void func_149695_a(World world, int i, int j, int k, Block l) {
    int num = 0;
    if (world.func_147439_a(i, j, k + 1).isSideSolid((IBlockAccess)world, i, j, k + 1, ForgeDirection.NORTH))
      num++;
    if (world.func_147439_a(i, j, k - 1).isSideSolid((IBlockAccess)world, i, j, k - 1, ForgeDirection.SOUTH))
      num++;
    if (world.func_147439_a(i + 1, j, k).isSideSolid((IBlockAccess)world, i + 1, j, k, ForgeDirection.WEST))
      num++;
    if (world.func_147439_a(i - 1, j, k).isSideSolid((IBlockAccess)world, i - 1, j, k, ForgeDirection.EAST))
      num++;
    if (world.func_147439_a(i, j + 1, k).isSideSolid((IBlockAccess)world, i, j + 1, k, ForgeDirection.DOWN))
      num++;
    if (world.func_147439_a(i, j - 1, k).isSideSolid((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP))
      num++;
    if (num == 0) {

      world.func_147468_f(i, j, k);
      func_149642_a(world, i, j, k, new ItemStack(TFCItems.powder, func_149745_a(new Random()), this.itemMeta));
    }
  }





  public int func_149745_a(Random random) {
    return 1 + random.nextInt(2);
  }












































  public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB aabb, List<AxisAlignedBB> list, Entity entity) {
    ArrayList<Object[]> alist = new ArrayList();
    addCollisionBoxesToList(world, i, j, k, alist);
    for (Object[] obj : alist) {

      AxisAlignedBB plankAABB = (AxisAlignedBB)obj[0];
      plankAABB.field_72340_a += i; plankAABB.field_72336_d += i;
      plankAABB.field_72338_b += j; plankAABB.field_72337_e += j;
      plankAABB.field_72339_c += k; plankAABB.field_72334_f += k;
      if (aabb.func_72326_a(plankAABB))
      {
        list.add(plankAABB);
      }
    }
  }



  public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) {
    return CollisionRayTraceStandard.collisionRayTrace(this, world, x, y, z, player, view);
  }




  public void addCollisionBoxesToList(World world, int x, int y, int z, List<Object[]> list) {
    if (world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH))
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.9900000095367432D, 1.0D, 1.0D, 1.0D) });
    }
    if (world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH))
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.009999999776482582D) });
    }
    if (world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.EAST))
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.9900000095367432D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) });
    }
    if (world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.WEST))
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.009999999776482582D, 1.0D, 1.0D) });
    }
    if (world.func_147439_a(x, y + 1, z).isSideSolid((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN))
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.9900000095367432D, 0.0D, 1.0D, 1.0D, 1.0D) });
    }
    if (world.func_147439_a(x, y - 1, z).isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP))
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 0.009999999776482582D, 1.0D) });
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSulfur.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */