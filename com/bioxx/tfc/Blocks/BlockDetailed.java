package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.CollisionRayTraceDetailed;
import com.bioxx.tfc.Core.Player.PlayerInfo;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.TileEntities.TEDetailed;
import com.bioxx.tfc.TileEntities.TEWoodConstruct;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockDetailed extends BlockPartial {
  public static int lockX;
  public static int lockY;
  public static int lockZ;
  public int xSelected;
  public int ySelected;
  public int zSelected;
  public int side;

  public BlockDetailed() {
    super(Material.field_151576_e);





























































































































































































































































    this.xSelected = -10; this.ySelected = -10; this.zSelected = -10; this.side = -1;
  } public int func_149645_b() { return TFCBlocks.detailedRenderId; } @SideOnly(Side.CLIENT) public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) { return true; } @SideOnly(Side.CLIENT) public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) { return true; } public boolean func_149655_b(IBlockAccess bAccess, int i, int j, int k) { return true; }
  public void func_149651_a(IIconRegister iconRegisterer) {}
  public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) { TEDetailed te = (TEDetailed)bAccess.func_147438_o(x, y, z); return te.func_145838_q().func_149691_a(side, te.metaID); }
  public TileEntity func_149915_a(World var1, int var2) { return (TileEntity)new TEDetailed(); }
  public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) { TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);

    player = player.func_72441_c(-x, -y, -z);
    view = view.func_72441_c(-x, -y, -z);
    if (te == null) {
      return null;
    }
    List<Object[]> returns = new ArrayList();


    returns = CollisionRayTraceDetailed.rayTraceSubBlocks(this, player, view, x, y, z, returns, te.data, te);











    if (!returns.isEmpty()) {
      Object[] min = null;
      double distMin = 0.0D;
      for (Object[] ret : returns) {

        double dist = ((Double)ret[2]).doubleValue();
        if (min == null || dist < distMin) {

          distMin = dist;
          min = ret;
        }
      }
      if (min != null) {

        this.side = ((Byte)min[1]).byteValue();
        this.xSelected = ((Integer)min[3]).intValue();
        this.ySelected = ((Integer)min[4]).intValue();
        this.zSelected = ((Integer)min[5]).intValue();

        int index = (this.xSelected * 8 + this.zSelected) * 8 + this.ySelected;

        if (index >= 0 && te.data.get(index)) {

          int d = TEWoodConstruct.plankDetailLevel;

          float div = 1.0F / d;

          float minX = x + this.xSelected * div;
          float maxX = minX + div;
          float minY = y + this.ySelected * div;
          float maxY = minY + div;
          float minZ = z + this.zSelected * div;
          float maxZ = minZ + div;

          func_149676_a(minX, minY, minZ, maxX, maxY, maxZ);
          rayTraceBound(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ), x, y, z, player, view);
        }
        setBlockBoundsBasedOnSelection((IBlockAccess)world, x, y, z);

        lockX = x; lockY = y; lockZ = z;

        return new MovingObjectPosition(x, y, z, ((Byte)min[1])
            .byteValue(), ((Vec3)min[0])
            .func_72441_c(x, y, z));
      }
    }
    this.xSelected = -10;
    this.ySelected = -10;
    this.zSelected = -10;
    this.side = -1;
    setBlockBoundsBasedOnSelection((IBlockAccess)world, x, y, z);

    return null; } public boolean func_149662_c() { return false; }
  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) { return true; }
  public boolean func_149686_d() { return false; }
  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) { return true; }
  public void setBlockBoundsBasedOnSelection(IBlockAccess access, int x, int y, int z) { if (this.xSelected == -10)

    { func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F); }

    else

    { TEDetailed te = (TEDetailed)access.func_147438_o(x, y, z);
      int index = (this.xSelected * 8 + this.zSelected) * 8 + this.ySelected;

      if (index >= 0 && te.data.get(index))

      { int d = 8;



        float div = 1.0F / d;

        float minX = this.xSelected * div;
        float maxX = minX + div;
        float minY = this.ySelected * div;
        float maxY = minY + div;
        float minZ = this.zSelected * div;
        float maxZ = minZ + div;

        AxisAlignedBB bound = AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ);
        func_149676_a((float)bound.field_72340_a, (float)bound.field_72338_b, (float)bound.field_72339_c, (float)bound.field_72336_d, (float)bound.field_72337_e, (float)bound.field_72334_f); }  }  }
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) { boolean hasHammer = false; PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(entityplayer); for (int i = 0; i < 9; i++) { if (entityplayer.field_71071_by.field_70462_a[i] != null && entityplayer.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) hasHammer = true;  }  if (entityplayer.func_71045_bC() != null && entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel && hasHammer && world.field_72995_K && pi.lockMatches(x, y, z)) { TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z); lockX = x; lockY = y; lockZ = z; NBTTagCompound nbt = new NBTTagCompound(); nbt.func_74774_a("packetType", (byte)1); nbt.func_74768_a("xSelected", this.xSelected); nbt.func_74768_a("ySelected", this.ySelected); nbt.func_74768_a("zSelected", this.zSelected); te.createDataNBT(nbt); te.broadcastPacketInRange((AbstractPacket)te.createDataPacket(nbt)); }  return false; }
  public boolean onBlockActivatedServer(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) { int mode = 0; PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player); if (pi != null) mode = pi.chiselMode;  TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z); int hasChisel = -1; int hasHammer = -1; for (int i = 0; i < 9; i++) { if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) hasHammer = i;  if (player.field_71071_by.field_70462_a[i] != null && player.field_71071_by.field_70462_a[i].func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel)
        hasChisel = i;  }  if (mode == 1) { int index = -10; if (this.xSelected < 4 && this.ySelected < 4 && this.zSelected < 4)
        for (int subX = 0; subX < 4; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected < 4 && this.zSelected < 4)
        for (int subX = 4; subX < 8; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected < 4 && this.zSelected > 3)
        for (int subX = 4; subX < 8; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected < 4 && this.ySelected < 4 && this.zSelected > 3)
        for (int subX = 0; subX < 4; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 0; subY < 4; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected < 4 && this.ySelected > 3 && this.zSelected < 4)
        for (int subX = 0; subX < 4; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected > 3 && this.zSelected < 4)
        for (int subX = 4; subX < 8; ) { for (int subZ = 0; subZ < 4; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected > 3 && this.ySelected > 3 && this.zSelected > 3)
        for (int subX = 4; subX < 8; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   if (this.xSelected < 4 && this.ySelected > 3 && this.zSelected > 3)
        for (int subX = 0; subX < 4; ) { for (int subZ = 4; subZ < 8; ) { for (int subY = 4; subY < 8; subY++) { index = (subX * 8 + subZ) * 8 + subY; deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer); }  subZ++; }  subX++; }   return true; }  if (mode == 3 && this.xSelected != -10) { int index = (this.xSelected * 8 + this.zSelected) * 8 + this.ySelected; if (index >= 0)
        deleteBox(world, x, y, z, player, te, index, hasChisel, hasHammer);  return true; }  return false; } public Object[] rayTraceBound(AxisAlignedBB bound, int i, int j, int k, Vec3 player, Vec3 view) { Vec3 minX = player.func_72429_b(view, bound.field_72340_a);
    Vec3 maxX = player.func_72429_b(view, bound.field_72336_d);
    Vec3 minY = player.func_72435_c(view, bound.field_72338_b);
    Vec3 maxY = player.func_72435_c(view, bound.field_72337_e);
    Vec3 minZ = player.func_72434_d(view, bound.field_72339_c);
    Vec3 maxZ = player.func_72434_d(view, bound.field_72334_f);
    if (!isVecInsideYZBounds(bound, minX))
      minX = null;
    if (!isVecInsideYZBounds(bound, maxX))
      maxX = null;
    if (!isVecInsideXZBounds(bound, minY))
      minY = null;
    if (!isVecInsideXZBounds(bound, maxY))
      maxY = null;
    if (!isVecInsideXYBounds(bound, minZ))
      minZ = null;
    if (!isVecInsideXYBounds(bound, maxZ)) {
      maxZ = null;
    }
    Vec3 tracedBound = null;
    if (minX != null && (tracedBound == null || player.func_72438_d(minX) < player.func_72438_d(tracedBound)))
      tracedBound = minX;
    if (maxX != null && (tracedBound == null || player.func_72438_d(maxX) < player.func_72438_d(tracedBound)))
      tracedBound = maxX;
    if (minY != null && (tracedBound == null || player.func_72438_d(minY) < player.func_72438_d(tracedBound)))
      tracedBound = minY;
    if (maxY != null && (tracedBound == null || player.func_72438_d(maxY) < player.func_72438_d(tracedBound)))
      tracedBound = maxY;
    if (minZ != null && (tracedBound == null || player.func_72438_d(minZ) < player.func_72438_d(tracedBound)))
      tracedBound = minZ;
    if (maxZ != null && (tracedBound == null || player.func_72438_d(maxZ) < player.func_72438_d(tracedBound)))
      tracedBound = maxZ;
    if (tracedBound == null) {
      return null;
    }
    byte side = -1;
    if (tracedBound == minX)
      side = 4;
    if (tracedBound == maxX)
      side = 5;
    if (tracedBound == minY)
      side = 0;
    if (tracedBound == maxY)
      side = 1;
    if (tracedBound == minZ)
      side = 2;
    if (tracedBound == maxZ) {
      side = 3;
    }
    return new Object[] { tracedBound, Byte.valueOf(side), Double.valueOf(player.func_72438_d(tracedBound)) }; }
  public void deleteBox(World world, int x, int y, int z, EntityPlayer player, TEDetailed te, int index, int hasChisel, int hasHammer) { te.data.clear(index); te.clearQuad(this.xSelected, this.ySelected, this.zSelected); if (te.isBlockEmpty())
      world.func_147468_f(x, y, z);  if (player.field_71071_by.field_70462_a[hasChisel] != null)
      player.field_71071_by.field_70462_a[hasChisel].func_77972_a(1, (EntityLivingBase)player);  NBTTagCompound nbt = new NBTTagCompound(); nbt.func_74774_a("packetType", (byte)0); nbt.func_74768_a("index", index); te.createDataNBT(nbt); te.broadcastPacketInRange((AbstractPacket)te.createDataPacket(nbt)); }
  public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB aabb, List list, Entity entity) { TEDetailed te = (TEDetailed)world.func_147438_o(i, j, k); float div = 0.125F; for (int subX = 0; subX < 8; subX++) { for (int subZ = 0; subZ < 8; subZ++) { for (int subY = 0; subY < 8; subY++) { if (te.data.get((subX * 8 + subZ) * 8 + subY)) { float minX = subX * div; float maxX = minX + div; float minY = subY * div; float maxY = minY + div; float minZ = subZ * div; float maxZ = minZ + div; func_149676_a(minX, minY, minZ, maxX, maxY, maxZ); super.func_149743_a(world, i, j, k, aabb, list, entity); }  }  }  }  setBlockBoundsBasedOnSelection((IBlockAccess)world, i, j, k); } private boolean isVecInsideYZBounds(AxisAlignedBB bound, Vec3 vec3) { if (vec3 == null) {
      return false;
    }
    return (vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f); }


  private boolean isVecInsideXZBounds(AxisAlignedBB bound, Vec3 vec3) {
    if (vec3 == null) {
      return false;
    }
    return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f);
  }

  private boolean isVecInsideXYBounds(AxisAlignedBB bound, Vec3 vec3) {
    if (vec3 == null) {
      return false;
    }
    return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e);
  }



  public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
    TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);
    if (te.typeID >= 0)
      return Blocks.field_150480_ab.getFlammability(Block.func_149729_e(te.typeID));
    return 0;
  }



  public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
    TEDetailed te = (TEDetailed)world.func_147438_o(x, y, z);
    if (te.typeID >= 0)
      return Blocks.field_150480_ab.getEncouragement(Block.func_149729_e(te.typeID));
    return 0;
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockDetailed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */