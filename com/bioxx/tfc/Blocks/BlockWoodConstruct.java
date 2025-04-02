package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.CollisionRayTraceStandard;
import com.bioxx.tfc.TileEntities.TEWoodConstruct;
import com.bioxx.tfc.api.Interfaces.ICustomCollision;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockWoodConstruct
  extends BlockTerraContainer
  implements ICustomCollision
{
  public BlockWoodConstruct() {
    super(Material.field_151575_d);
    func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEWoodConstruct();
  }



  public int func_149645_b() {
    return TFCBlocks.woodConstructRenderId;
  }



  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int i, int j, int k) {
    return true;
  }




  public void func_149651_a(IIconRegister iconRegisterer) {}




  public IIcon func_149691_a(int i, int j) {
    return TFCBlocks.planks.func_149691_a(i, j);
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    List<ItemStack> ret = new ArrayList<>();

    if (!world.field_72995_K && (TEWoodConstruct)world.func_147438_o(x, y, z) != null) {

      TEWoodConstruct te = (TEWoodConstruct)world.func_147438_o(x, y, z);
      ret = te.getDrops();
    }
    return (ArrayList<ItemStack>)ret;
  }



  public void func_149681_a(World world, int x, int y, int z, int meta, EntityPlayer player) {
    ArrayList<ItemStack> out = getDrops(world, x, y, z, meta, 0);
    for (ItemStack is : out)
    {
      world.func_72838_d((Entity)new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, is));
    }
  }



  public boolean func_149659_a(Explosion ex) {
    return false;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
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




  public void addCollisionBoxesToList(World world, int i, int j, int k, List<Object[]> list) {
    TEWoodConstruct te = (TEWoodConstruct)world.func_147438_o(i, j, k);

    int d = TEWoodConstruct.plankDetailLevel;
    int dd = TEWoodConstruct.plankDetailLevel * TEWoodConstruct.plankDetailLevel;

    float div = 1.0F / d;

    for (int x = 0; x < dd; x++) {

      if (te.data.get(x)) {

        float minX = 0.0F;
        float maxX = 1.0F;
        float minY = div * (x & 0x7);
        float maxY = minY + div;
        float minZ = div * (x >> 3);
        float maxZ = minZ + div;
        list.add(new Object[] { AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ) });
      }
    }

    for (int y = 0; y < dd; y++) {

      if (te.data.get(y + dd)) {

        float minX = div * (y & 0x7);
        float maxX = minX + div;
        float minY = 0.0F;
        float maxY = 1.0F;
        float minZ = div * (y >> 3);
        float maxZ = minZ + div;
        list.add(new Object[] { AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ) });
      }
    }

    for (int z = 0; z < dd; z++) {

      if (te.data.get(z + dd * 2)) {

        float minX = div * (z & 0x7);
        float maxX = minX + div;
        float minY = div * (z >> 3);
        float maxY = minY + div;
        float minZ = 0.0F;
        float maxZ = 1.0F;
        list.add(new Object[] { AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ) });
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockWoodConstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */