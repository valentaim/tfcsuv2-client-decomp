package com.bioxx.tfc.Entities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;




public class EntityFallingBlockTFC
  extends Entity
  implements IEntityAdditionalSpawnData
{
  private Block block;
  public int blockMeta;
  public int aliveTimer;
  public boolean shouldDropItem;
  private boolean hurtEntities;
  public int maxDamage;
  public float damage;
  public NBTTagCompound tileEntityData;

  public EntityFallingBlockTFC(World world) {
    super(world);
    this.shouldDropItem = true;
    this.maxDamage = 2000;
    this.damage = 100.0F;
  }


  public EntityFallingBlockTFC(World world, double x, double y, double z, Block b) {
    this(world, x, y, z, b, 0);
  }


  public EntityFallingBlockTFC(World world, double x, double y, double z, Block b, int meta) {
    this(world);
    this.shouldDropItem = false;
    this.block = b;
    this.blockMeta = meta;
    this.field_70156_m = true;
    func_70105_a(0.98F, 0.98F);
    this.field_70129_M = this.field_70131_O / 2.0F;
    func_70107_b(x, y, z);
    this.field_70159_w = 0.0D;
    this.field_70181_x = 0.0D;
    this.field_70179_y = 0.0D;
    this.field_70169_q = x;
    this.field_70167_r = y;
    this.field_70166_s = z;
    this.hurtEntities = b instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble;
  }







  protected boolean func_70041_e_() {
    return false;
  }




  protected void func_70088_a() {}




  public boolean func_70067_L() {
    return !this.field_70128_L;
  }







  public void func_70071_h_() {
    if (this.block == null)
      return;
    if (this.block.func_149688_o() == Material.field_151579_a) {

      func_70106_y();
    }
    else {

      this.field_70169_q = this.field_70165_t;
      this.field_70167_r = this.field_70163_u;
      this.field_70166_s = this.field_70161_v;
      this.aliveTimer++;
      this.field_70181_x -= 0.03999999910593033D;
      func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
      this.field_70159_w *= 0.9800000190734863D;
      this.field_70181_x *= 0.9800000190734863D;
      this.field_70179_y *= 0.9800000190734863D;

      if (!this.field_70170_p.field_72995_K) {

        int i = MathHelper.func_76128_c(this.field_70165_t);
        int j = MathHelper.func_76128_c(this.field_70163_u);
        int k = MathHelper.func_76128_c(this.field_70161_v);

        if (this.aliveTimer == 1)
        {
          this.field_70170_p.func_147468_f(i, j, k);
        }

        if (this.field_70122_E) {

          if (canReplace(this.field_70170_p, i, j, k)) {

            this.field_70170_p.func_147468_f(i, j, k);
          }
          else if (this.field_70170_p.func_147439_a(i, j, k).func_149669_A() < 1.0D) {

            j++;
          }
          if (canReplace(this.field_70170_p, i, j - 1, k)) {


            this.field_70170_p.func_147468_f(i, j - 1, k);
            this.field_70122_E = false;
          }
        }

        if (this.field_70122_E) {

          this.field_70159_w *= 0.699999988079071D;
          this.field_70179_y *= 0.699999988079071D;
          this.field_70181_x *= -0.5D;

          if (this.field_70170_p.func_147439_a(i, j, k) != Blocks.field_150326_M) {

            func_70106_y();

            if (canPlaceEntityOnSide(this.field_70170_p, this.block, i, j, k, true, 1, (Entity)null, (ItemStack)null) && !BlockFalling.func_149831_e(this.field_70170_p, i, j - 1, k)) {

              if (this.tileEntityData != null && this.block instanceof net.minecraft.block.ITileEntityProvider) {

                TileEntity tileentity = this.field_70170_p.func_147438_o(i, j, k);

                if (tileentity != null)
                {
                  NBTTagCompound nbttagcompound = new NBTTagCompound();
                  tileentity.func_145841_b(nbttagcompound);
                  Iterator<String> iterator = this.tileEntityData.func_150296_c().iterator();

                  while (iterator.hasNext()) {

                    String s = iterator.next();
                    NBTBase nbtbase = this.tileEntityData.func_74781_a(s);

                    if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s))
                    {
                      nbttagcompound.func_74782_a(s, nbtbase.func_74737_b());
                    }
                  }

                  tileentity.func_145839_a(nbttagcompound);
                  tileentity.func_70296_d();
                }

              }
            } else if (this.shouldDropItem) {

              func_70099_a(new ItemStack(this.block, 1, this.block.func_149692_a(this.blockMeta)), 0.0F);
            }

          }
        } else if ((this.aliveTimer > 100 && !this.field_70170_p.field_72995_K && (j < 1 || j > 256)) || this.aliveTimer > 600) {

          if (this.shouldDropItem)
          {
            func_70099_a(new ItemStack(this.block, 1, this.block.func_149692_a(this.blockMeta)), 0.0F);
          }

          func_70106_y();
        }
      }
    }
  }


  public boolean canPlaceEntityOnSide(World world, Block fallingBlock, int x, int y, int z, boolean skipEntityCheck, int side, Entity thisEntity, ItemStack is) {
    AxisAlignedBB axisalignedbb = null;
    if (!skipEntityCheck) {

      axisalignedbb = fallingBlock.func_149668_a(world, x, y, z);
      if (!world.func_72917_a(axisalignedbb, thisEntity)) {
        return false;
      }
    }
    Block block1 = world.func_147439_a(x, y, z);
    return (block1.func_149688_o() == Material.field_151594_q) ? true : canReplace(world, x, y, z);
  }
































  public boolean canFallBelow(Block block, World world, int x, int y, int z) {
    if (block == Blocks.field_150480_ab)
      return true;
    if (block == TFCBlocks.tallGrass)
      return true;
    if (block == TFCBlocks.torch)
      return true;
    if (block == TFCBlocks.smokeRack)
      return true;
    if (block == TFCBlocks.toolRack) {
      return true;
    }
    if (block == Blocks.field_150357_h)
      return false;
    if (block == TFCBlocks.charcoal)
      return false;
    if (block == TFCBlocks.molten) {
      return false;
    }
    Material material = block.func_149688_o();
    if (material == Material.field_151586_h || material == Material.field_151587_i) return true;

    if (!block.func_149662_c() && !block.func_149686_d() && !world.isSideSolid(x, y, z, ForgeDirection.UP)) return false;

    return (material == Material.field_151586_h || material == Material.field_151587_i);
  }



  public boolean canReplace(World world, int x, int y, int z) {
    Block b = world.func_147439_a(x, y, z);
    if (canDestroy(b) && (b.isAir((IBlockAccess)world, x, y, z) || canFallBelow(b, world, x, y, z)))
      return TFC_Core.setBlockWithDrops(this.field_70170_p, x, y, z, getBlock(), this.blockMeta);
    if (b instanceof com.bioxx.tfc.Blocks.Terrain.BlockOre && TFCOptions.enableCaveInsDestroyOre)
      return world.func_147468_f(x, y, z);
    return false;
  }



  private boolean canDestroy(Block b) {
    return (b != TFCBlocks.charcoal && b != TFCBlocks.molten);
  }







  protected void func_70069_a(float fallDistance) {
    if (this.hurtEntities) {

      int height = MathHelper.func_76123_f(fallDistance - 1.0F);

      if (height > 0) {

        ArrayList<Entity> arraylist = new ArrayList(this.field_70170_p.func_72839_b(this, this.field_70121_D));
        DamageSource damagesource = (new DamageSource("caveIn")).func_76348_h().func_151518_m();
        Iterator<Entity> iterator = arraylist.iterator();

        while (iterator.hasNext()) {

          Entity entity = iterator.next();
          entity.func_70097_a(damagesource, Math.min(MathHelper.func_76141_d(height * this.damage), this.maxDamage));
        }
      }
    }
  }






  protected void func_70014_b(NBTTagCompound nbt) {
    nbt.func_74774_a("Tile", (byte)Block.func_149682_b(this.block));
    nbt.func_74768_a("TileID", Block.func_149682_b(this.block));
    nbt.func_74774_a("Data", (byte)this.blockMeta);
    nbt.func_74774_a("Time", (byte)this.aliveTimer);
    nbt.func_74757_a("DropItem", this.shouldDropItem);
    nbt.func_74757_a("HurtEntities", this.hurtEntities);
    nbt.func_74776_a("FallHurtAmount", this.damage);
    nbt.func_74768_a("FallHurtMax", this.maxDamage);

    if (this.tileEntityData != null)
    {
      nbt.func_74782_a("TileEntityData", (NBTBase)this.tileEntityData);
    }
  }






  protected void func_70037_a(NBTTagCompound nbt) {
    if (nbt.func_150297_b("TileID", 99)) {

      this.block = Block.func_149729_e(nbt.func_74762_e("TileID"));
    }
    else {

      this.block = Block.func_149729_e(nbt.func_74771_c("Tile") & 0xFF);
    }

    this.blockMeta = nbt.func_74771_c("Data") & 0xFF;
    this.aliveTimer = nbt.func_74771_c("Time") & 0xFF;

    if (nbt.func_150297_b("HurtEntities", 99)) {

      this.hurtEntities = nbt.func_74767_n("HurtEntities");
      this.damage = nbt.func_74760_g("FallHurtAmount");
      this.maxDamage = nbt.func_74762_e("FallHurtMax");
    }
    else if (this.block instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble) {

      this.hurtEntities = true;
    }

    if (nbt.func_150297_b("DropItem", 99))
    {
      this.shouldDropItem = nbt.func_74767_n("DropItem");
    }

    if (nbt.func_150297_b("TileEntityData", 10))
    {
      this.tileEntityData = nbt.func_74775_l("TileEntityData");
    }

    if (this.block.func_149688_o() == Material.field_151579_a)
    {
      this.block = (Block)Blocks.field_150354_m;
    }
  }


  public void setHurt(boolean hurt) {
    this.hurtEntities = hurt;
  }



  public void func_85029_a(CrashReportCategory category) {
    super.func_85029_a(category);
    category.func_71507_a("Immitating block ID", Integer.valueOf(Block.func_149682_b(this.block)));
    category.func_71507_a("Immitating block data", Integer.valueOf(this.blockMeta));
  }



  @SideOnly(Side.CLIENT)
  public float func_70053_R() {
    return 0.0F;
  }


  @SideOnly(Side.CLIENT)
  public World getWorld() {
    return this.field_70170_p;
  }






  @SideOnly(Side.CLIENT)
  public boolean func_90999_ad() {
    return false;
  }


  public Block getBlock() {
    return this.block;
  }


  public void writeSpawnData(ByteBuf buffer) {
    buffer.writeInt(Block.func_149682_b(this.block));
    buffer.writeByte(this.blockMeta & 0xF);
  }


  public void readSpawnData(ByteBuf additionalData) {
    this.block = Block.func_149729_e(additionalData.readInt());
    this.blockMeta = additionalData.readByte();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityFallingBlockTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */