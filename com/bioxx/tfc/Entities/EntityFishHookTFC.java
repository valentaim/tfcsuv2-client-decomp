package com.bioxx.tfc.Entities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.Mobs.EntityFishTFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;











public class EntityFishHookTFC
  extends EntityFishHook
{
  private int xTile;
  private int yTile;
  private int zTile;
  private Block inTile;
  private boolean inGround;
  private int ticksInGround;
  private int ticksInAir;
  private int ticksCatchable;
  private int fishPosRotationIncrements;
  private double fishX;
  private double fishY;
  private double fishZ;
  private double fishYaw;
  private double fishPitch;
  @SideOnly(Side.CLIENT)
  private double velocityX;
  @SideOnly(Side.CLIENT)
  private double velocityY;
  @SideOnly(Side.CLIENT)
  private double velocityZ;
  private double maxDistance = -1.0D;

  private boolean canCatchFish;

  public double pullX;

  public double pullY;

  public double pullZ;
  private int lineTension;
  private static final int MAX_LINE_TENSION = 800;
  private int reelCounter;
  private int lastCheckTick;
  private boolean lineTensionSnap;

  public EntityFishHookTFC(World par1World) {
    super(par1World);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    func_70105_a(0.25F, 0.25F);
    this.field_70158_ak = true;
  }


  @SideOnly(Side.CLIENT)
  public EntityFishHookTFC(World par1World, double par2, double par4, double par6, EntityPlayer par8EntityPlayer) {
    this(par1World);
    func_70107_b(par2, par4, par6);
    this.field_70158_ak = true;
    this.field_146042_b = par8EntityPlayer;
    par8EntityPlayer.field_71104_cf = this;
  }

  public double getMaxDistance() {
    return this.maxDistance;
  }


  public EntityFishHookTFC(World par1World, EntityPlayer par2EntityPlayer) {
    super(par1World);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    this.field_70158_ak = true;
    this.field_146042_b = par2EntityPlayer;
    this.field_146042_b.field_71104_cf = this;
    func_70105_a(0.25F, 0.25F);
    func_70012_b(par2EntityPlayer.field_70165_t, par2EntityPlayer.field_70163_u + 1.62D - par2EntityPlayer.field_70129_M, par2EntityPlayer.field_70161_v, par2EntityPlayer.field_70177_z, par2EntityPlayer.field_70125_A);
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.field_70163_u -= 0.10000000149011612D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    this.field_70129_M = 0.0F;
    float f = 0.4F;
    this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F) * f);
    func_146035_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 1.5F, 1.0F);
  }


  public EntityFishHookTFC(World par1World, EntityPlayer par2EntityPlayer, int ticks) {
    super(par1World);
    this.xTile = -1;
    this.yTile = -1;
    this.zTile = -1;
    this.field_70158_ak = true;
    this.field_146042_b = par2EntityPlayer;
    this.field_146042_b.field_71104_cf = this;
    func_70105_a(0.25F, 0.25F);
    func_70012_b(par2EntityPlayer.field_70165_t, par2EntityPlayer.field_70163_u + 1.62D - par2EntityPlayer.field_70129_M, par2EntityPlayer.field_70161_v, par2EntityPlayer.field_70177_z, par2EntityPlayer.field_70125_A);
    this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    this.field_70163_u -= 0.10000000149011612D;
    this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
    func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
    this.field_70129_M = 0.0F;
    float f = 0.4F;
    float tickRatio = Math.min(ticks, 60) / 20.0F;
    this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
    this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F) * f);
    func_146035_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, tickRatio, 1.0F);
  }





  protected void func_70088_a() {}





  @SideOnly(Side.CLIENT)
  public boolean func_70112_a(double par1) {
    double d1 = this.field_70121_D.func_72320_b() * 4.0D;
    d1 *= 64.0D;
    return (par1 < d1 * d1);
  }



  public void func_146035_c(double par1, double par3, double par5, float par7, float par8) {
    float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
    par1 /= f2;
    par3 /= f2;
    par5 /= f2;
    par1 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
    par3 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
    par5 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
    par1 *= par7;
    par3 *= par7;
    par5 *= par7;
    this.field_70159_w = par1;
    this.field_70181_x = par3;
    this.field_70179_y = par5;
    float f3 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
    this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
    this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, f3) * 180.0D / Math.PI);
    this.ticksInGround = 0;
  }








  @SideOnly(Side.CLIENT)
  public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
    this.fishX = par1;
    this.fishY = par3;
    this.fishZ = par5;
    this.fishYaw = par7;
    this.fishPitch = par8;
    this.fishPosRotationIncrements = par9;
    this.field_70159_w = this.velocityX;
    this.field_70181_x = this.velocityY;
    this.field_70179_y = this.velocityZ;
  }







  @SideOnly(Side.CLIENT)
  public void func_70016_h(double par1, double par3, double par5) {
    this.velocityX = this.field_70159_w = par1;
    this.velocityY = this.field_70181_x = par3;
    this.velocityZ = this.field_70179_y = par5;
  }







  public void func_70071_h_() {
    func_70030_z();

    if (func_70032_d((Entity)this.field_146042_b) < 1.0F) {
      func_70106_y();
      if (this.field_146042_b.func_70694_bm() != null) {
        ItemStack itemstack = this.field_146042_b.func_70694_bm();
        if (itemstack.field_77990_d == null) {
          itemstack.field_77990_d = new NBTTagCompound();
        }
        itemstack.field_77990_d.func_74772_a("tickReeledIn", TFC_Time.getTotalTicks());
      }
    }

    if (this.fishPosRotationIncrements > 0) {

      double d0 = this.field_70165_t + (this.fishX - this.field_70165_t) / this.fishPosRotationIncrements;
      double d1 = this.field_70163_u + (this.fishY - this.field_70163_u) / this.fishPosRotationIncrements;
      double d2 = this.field_70161_v + (this.fishZ - this.field_70161_v) / this.fishPosRotationIncrements;
      double d3 = MathHelper.func_76138_g(this.fishYaw - this.field_70177_z);
      this.field_70177_z = (float)(this.field_70177_z + d3 / this.fishPosRotationIncrements);
      this.field_70125_A = (float)(this.field_70125_A + (this.fishPitch - this.field_70125_A) / this.fishPosRotationIncrements);
      this.fishPosRotationIncrements--;
      func_70107_b(d0, d1, d2);
      func_70101_b(this.field_70177_z, this.field_70125_A);
    }
    else {

      if (!this.field_70170_p.field_72995_K) {

        ItemStack itemstack = this.field_146042_b.func_71045_bC();

        if (this.field_146042_b.field_70128_L || !this.field_146042_b.func_70089_S() || itemstack == null || !(itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomFishingRod) || func_70068_e((Entity)this.field_146042_b) > 2500.0D) {

          func_70106_y();
          this.field_146042_b.field_71104_cf = null;

          return;
        }
        if (this.field_146043_c != null) {

          if (!this.field_146043_c.field_70128_L) {

            this.field_70165_t = this.field_146043_c.field_70165_t;
            this.field_70163_u = this.field_146043_c.field_70121_D.field_72338_b + this.field_146043_c.field_70131_O * 0.8D;
            this.field_70161_v = this.field_146043_c.field_70161_v;
            return;
          }
          this.field_146043_c = null;
        }
      }

      if (this.field_146044_a > 0)
      {
        this.field_146044_a--;
      }

      if (this.inGround) {

        if (this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile) == this.inTile) {

          this.ticksInGround++;
          if (this.ticksInGround == 1200) {
            func_70106_y();
          }
          return;
        }
        this.inGround = false;
        this.field_70159_w *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.field_70181_x *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.field_70179_y *= (this.field_70146_Z.nextFloat() * 0.2F);
        this.ticksInGround = 0;
        this.ticksInAir = 0;
      }
      else {

        this.ticksInAir++;
      }

      Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
      MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
      vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);

      if (movingobjectposition != null) {
        vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
      }
      Entity entity = null;
      List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
      double d4 = 0.0D;


      for (int j = 0; j < list.size(); j++) {

        Entity entity1 = list.get(j);
        if (entity1.func_70067_L() && (entity1 != this.field_146042_b || this.ticksInAir >= 5)) {

          float f = 0.3F;
          AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
          MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
          if (movingobjectposition1 != null) {

            double d5 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
            if (d5 < d4 || d4 == 0.0D) {

              entity = entity1;
              d4 = d5;
            }
          }
        }
      }

      if (entity != null) {
        movingobjectposition = new MovingObjectPosition(entity);
      }
      if (movingobjectposition != null)
      {





        if (movingobjectposition.field_72308_g == null)
        {
          this.inGround = true;
        }
      }

      if (!this.inGround) {

        func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);

        for (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f1) * 180.0D / Math.PI); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);


        while (this.field_70125_A - this.field_70127_C >= 180.0F) {
          this.field_70127_C += 360.0F;
        }
        while (this.field_70177_z - this.field_70126_B < -180.0F) {
          this.field_70126_B -= 360.0F;
        }
        while (this.field_70177_z - this.field_70126_B >= 180.0F) {
          this.field_70126_B += 360.0F;
        }
        this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
        this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
        float f2 = 0.92F;

        if (this.field_70122_E || this.field_70123_F) {
          f2 = 0.5F;
        }
        byte b0 = 5;
        double d6 = 0.0D;

        for (int k = 0; k < b0; k++) {

          double d7 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (k + 0) / b0 - 0.125D + 0.125D;
          double d8 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (k + 1) / b0 - 0.125D + 0.125D;
          AxisAlignedBB axisalignedbb1 = AxisAlignedBB.func_72330_a(this.field_70121_D.field_72340_a, d7, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d8, this.field_70121_D.field_72334_f);

          if (this.field_70170_p.func_72830_b(axisalignedbb1, Material.field_151586_h)) {
            d6 += 1.0D / b0;
          }
        }
        if (this.ticksCatchable > 0) {
          this.field_70181_x -= (this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) * 0.2D;
        }
        double d5 = d6 * 2.0D - 1.0D;
        this.field_70181_x += 0.03999999910593033D * d5;

        if (d6 > 0.0D) {

          if (this.maxDistance == -1.0D) {
            this.maxDistance = func_70032_d((Entity)this.field_146042_b);
            this.canCatchFish = true;
          }
          if (this.canCatchFish && !this.field_70170_p.field_72995_K) {
            attemptToCatch();
          }
          f2 = (float)(f2 * 0.9D);
          this.field_70181_x *= 0.8D;
        }

        this.field_70159_w *= f2;
        this.field_70181_x *= f2;
        this.field_70179_y *= f2;

        double distance = func_70032_d((Entity)this.field_146042_b);
        if (distance > this.maxDistance && this.maxDistance != -1.0D) {
          Vec3 distVec = Vec3.func_72443_a(this.field_70165_t - this.field_146042_b.field_70165_t, this.field_70163_u - this.field_146042_b.field_70163_u, this.field_70161_v - this.field_146042_b.field_70161_v);
          double distRatio = this.maxDistance / distance;
          this.field_70165_t = this.field_146042_b.field_70165_t + distVec.field_72450_a * distRatio;
          this.field_70163_u = this.field_146042_b.field_70163_u + distVec.field_72448_b * distRatio;
          this.field_70161_v = this.field_146042_b.field_70161_v + distVec.field_72449_c * distRatio;
        }
        func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
      }
    }
  }














  public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
    par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
    par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
    par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
    par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
    par1NBTTagCompound.func_74774_a("shake", (byte)this.field_146044_a);
    par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
  }






  public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
    this.xTile = par1NBTTagCompound.func_74765_d("xTile");
    this.yTile = par1NBTTagCompound.func_74765_d("yTile");
    this.zTile = par1NBTTagCompound.func_74765_d("zTile");
    this.inTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("inTile") & 0xFF);
    this.field_146044_a = par1NBTTagCompound.func_74771_c("shake") & 0xFF;
    this.inGround = (par1NBTTagCompound.func_74771_c("inGround") == 1);
  }

  public Vec3 applyEntityForce(Vec3 entityForce, double x, double y, double z) {
    Vec3 pullVec = Vec3.func_72443_a(this.pullX, this.pullY, this.pullZ);

    double force = pullVec.func_72438_d(entityForce);
    Vec3 netForceVec = entityForce.func_72441_c(this.pullX, this.pullY, this.pullZ);
    double forceRatio = force * 30.0D / netForceVec.func_72433_c();

    if (TFC_Time.getTotalTicks() % 40L == 0L) {
      force += 0.0D;
    }
    this.lineTension = (int)(this.lineTension + ((forceRatio - 31.0D > 1.0D) ? Math.sqrt(forceRatio - 31.0D) : (forceRatio - 31.0D)));

    this.lineTension = Math.max(this.lineTension, 0);

    ItemStack is = this.field_146042_b.func_70694_bm();
    if (is != null && is.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomFishingRod) {
      if (!is.func_77942_o()) {
        is.func_77982_d(new NBTTagCompound());
      }
      if (this.reelCounter > 2) {
        is.field_77990_d.func_74768_a("tension", (int)(forceRatio - 29.0D + Math.pow(entityForce.func_72433_c() / 0.2D, 3.0D) * 2.0D) * 100);
      } else {

        this.reelCounter++;
      }
    }
    if (forceRatio != 30.0D) {
      this.reelCounter = 0;
    }
    if (this.lineTension >= 400)
    {
      this.maxDistance += pullVec.func_72433_c() * 0.3D;
    }
    if (this.lineTension > 640.0D && !this.lineTensionSnap) {
      this.lineTensionSnap = true;
      TFC_Core.sendInfoMessage(this.field_146042_b, (IChatComponent)new ChatComponentTranslation("fishingRod.lineTension", new Object[0]));
    }
    else if (this.lineTension < 640.0D) {
      this.lineTensionSnap = false;
    }
    if (this.lineTension >= 800) {
      this.field_146042_b.func_71045_bC().func_77972_a(20, (EntityLivingBase)this.field_146042_b);
      this.field_70154_o.field_70153_n = null;
      this.field_70154_o = null;

      TFC_Core.sendInfoMessage(this.field_146042_b, (IChatComponent)new ChatComponentTranslation("fishingRod.lineSnap", new Object[0]));
      func_70106_y();
    }
    this.pullX = 0.0D;
    this.pullY = 0.0D;
    this.pullZ = 0.0D;











    return Vec3.func_72443_a(netForceVec.field_72450_a, (this.field_70170_p.func_147437_c((int)x, (int)y + 1, (int)z) && netForceVec.field_72448_b > 0.0D) ? 0.0D : netForceVec.field_72448_b, netForceVec.field_72449_c);
  }

  public Vec3 getNormalDirectionOfPlayer(double x, double y, double z) {
    Vec3 dirVec = Vec3.func_72443_a(this.field_146042_b.field_70165_t - x, this.field_146042_b.field_70163_u - y, this.field_146042_b.field_70161_v - z);
    return dirVec.func_72432_b();
  }

  public Vec3 getTooFarAdjustedVec(Vec3 motionVec, double x, double y, double z) {
    Vec3 playerMotion = Vec3.func_72443_a(this.field_146042_b.field_70159_w, this.field_146042_b.field_70181_x, this.field_146042_b.field_70179_y);
    double subractedRatio = Math.max(1.0D - this.maxDistance / this.field_146042_b.func_70011_f(x + playerMotion.field_72450_a, y + playerMotion.field_72448_b, z + playerMotion.field_72449_c), 0.0D);
    return Vec3.func_72443_a((this.field_146042_b.field_70165_t + playerMotion.field_72450_a - motionVec.field_72450_a + x) * subractedRatio, (this.field_146042_b.field_70163_u + playerMotion.field_72448_b - motionVec.field_72448_b + y) * subractedRatio, (this.field_146042_b.field_70161_v + playerMotion.field_72449_c - motionVec.field_72449_c + z) * subractedRatio);
  }




  public void attemptToCatch() {
    int fishPopulation = getAverageFishPopFromChunks();
    if (this.lastCheckTick == 0) {
      int maxValue = 72;
      int minValue = 0;
      int hour = TFC_Time.getHour();
      if ((hour >= 3 && hour <= 9) || (hour >= 17 && hour < 22))
      {
        minValue = 1;
      }
      if (this.field_70146_Z.nextInt(maxValue - fishPopulation) <= minValue) {
        func_146034_e();
      }
      this.lastCheckTick = 20;
    } else {

      this.lastCheckTick--;
    }
  }

  public boolean isTooFarFromPlayer(double x, double y, double z) {
    return (this.field_146042_b.func_70011_f(x, y, z) > this.maxDistance);
  }

  public void reelInBobber(Entity entity, ItemStack itemstack) {
    double distance = func_70032_d(entity);
    if (distance < this.maxDistance) {
      this.maxDistance -= 0.2D;
    }



    if (distance > 1.5D) {

      this.pullX = (entity.field_70165_t - this.field_70165_t) / distance;
      this.pullY = (entity.field_70163_u - this.field_70163_u) / distance;
      this.pullZ = (entity.field_70161_v - this.field_70161_v) / distance;

      if (this.field_70154_o == null) {
        this.field_70159_w += this.pullX * 0.2D;
        this.field_70181_x += this.pullY * 0.2D;
        this.field_70179_y += this.pullZ * 0.2D;
      }
    } else {

      setDeadKill();
      if (itemstack.field_77990_d == null) {
        itemstack.field_77990_d = new NBTTagCompound();
      }
      itemstack.field_77990_d.func_74772_a("tickReeledIn", TFC_Time.getTotalTicks());
    }
  }

  public int getAverageFishPopFromChunks() {
    if (this.field_70170_p.field_72995_K)
    {
      return 0;
    }


    EntityPlayer player = this.field_146042_b;
    int lastChunkX = (int)Math.floor(player.field_70165_t) >> 4;
    int lastChunkZ = (int)Math.floor(player.field_70161_v) >> 4;

    int chunksVisited = 0;
    int totalFish = TFC_Core.getCDM(this.field_70170_p).getFishPop(lastChunkX, lastChunkZ);
    if (totalFish > 0) {
      chunksVisited++;
    } else {

      return 0;
    }

    int maxChunksVisitable = 20;
    for (int radius = 1; radius < 5 && chunksVisited < maxChunksVisitable; radius++) {
      for (int i = -radius; i <= radius; i++) {
        int k;
        for (k = -radius; k <= radius; k += (Math.abs(i) == radius) ? 1 : (radius * 2)) {

          int tempFish = TFC_Core.getCDM(this.field_70170_p).getFishPop(lastChunkX + i, lastChunkZ + k);
          if (tempFish > 0) {
            chunksVisited++;
            totalFish += tempFish;
          }
        }
      }
    }
    return totalFish / chunksVisited;
  }




  public int func_146034_e() {
    if (this.field_70170_p.field_72995_K)
    {
      return 0;
    }


    EntityPlayer player = this.field_146042_b;
    EntityFishTFC fish = new EntityFishTFC(this.field_70170_p);
    fish.func_70107_b(this.field_70165_t, this.field_70163_u - 0.3D, this.field_70161_v);
    this.field_70170_p.func_72838_d((Entity)fish);
    TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("fishingRod.bite", new Object[0]));
    func_70078_a((Entity)fish);

    this.canCatchFish = false;

    int lastChunkX = (int)Math.floor(player.field_70165_t) >> 4;
    int lastChunkZ = (int)Math.floor(player.field_70161_v) >> 4;
    int maxChunksVisitable = 20;
    TFC_Core.getCDM(this.field_70170_p).catchFish(lastChunkX, lastChunkZ);
    int chunksVisited = 1;
    for (int radius = 1; radius < 5 && chunksVisited < maxChunksVisitable; radius++) {
      for (int i = -radius; i <= radius; i++) {
        int k;
        for (k = -radius; k <= radius; k += (Math.abs(i) == radius) ? 1 : (radius * 2)) {

          if (TFC_Core.getCDM(this.field_70170_p).catchFish(lastChunkX + i, lastChunkZ + k)) {
            chunksVisited++;
          }
        }
      }
    }
    return 0;
  }


  public void setDeadKill() {
    if (this.field_70154_o != null && this.field_70154_o instanceof EntityLiving) {
      ((EntityLiving)this.field_70154_o).func_70606_j(1.0F);
      ((EntityLiving)this.field_70154_o).func_70097_a((DamageSource)new EntityDamageSource("fishing", (Entity)this.field_146042_b), 1.0F);
      this.field_146042_b.func_71064_a(StatList.field_75933_B, 1);
    }
    this.field_70154_o = null;
    func_70106_y();
  }






  public void func_70106_y() {
    if (this.field_70154_o != null) {
      EntityLiving e = (EntityLiving)this.field_70154_o;
      e.func_70106_y();
    }
    super.func_70106_y();
    this.lineTension = 0;
    this.maxDistance = -1.0D;
    if (this.field_146042_b != null)
      this.field_146042_b.field_71104_cf = null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityFishHookTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */