package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.EntityFishHookTFC;
import com.bioxx.tfc.api.TFCItems;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;









public class EntityFishTFC
  extends EntitySquid
{
  private float randomMotionVecX;
  private float randomMotionVecY;
  private float randomMotionVecZ;
  private float randomMotionSpeed;
  private float rotationVelocity;
  private float yawMult;
  private List<EntityPlayer> nearbyPlayers;
  private boolean hooked;
  private int energy = 200;

  private int tiredTicks;

  private int numRecoveries;

  private int inGroundTimer;

  public float currentRenderRoll;
  public float currentRenderYaw;
  public float currentRenderPitch;
  private float currentDestX;
  private float currentDestY;
  private float currentDestZ;
  private double renderMotionY;
  private double renderMotionX;
  private double renderMotionZ;
  private final double fishStrength;
  private double minPlayerDistance = 16.0D;
  private double pullX;
  private double pullY;
  private double pullZ;

  public EntityFishTFC(World par1World) {
    super(par1World);
    func_70105_a(0.4F, 0.4F);
    this.fishStrength = this.field_70146_Z.nextInt(40) / 100.0D + 1.0D;
  }






  public boolean func_70601_bi() {
    return (this.field_70163_u > 128.0D && this.field_70163_u <= 144.0D && this.field_70170_p.func_72855_b(this.field_70121_D));
  }


  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(21, new Float(1.0F));
    this.field_70180_af.func_75682_a(22, new Float(1.0F));
    this.field_70180_af.func_75682_a(23, new Float(1.0F));

    this.field_70180_af.func_75682_a(26, new Float(1.0F));
    this.field_70180_af.func_75682_a(27, new Float(1.0F));
    this.field_70180_af.func_75682_a(28, new Float(1.0F));
  }



  public boolean func_70090_H() {
    return this.field_70170_p.func_72918_a(this.field_70121_D.func_72314_b(0.0D, -0.0200000238418579D, 0.0D), Material.field_151586_h, (Entity)this);
  }







  public void func_70636_d() {
    super.func_70636_d();
    if (!this.field_70170_p.field_72995_K) {
      this.field_70862_e = this.field_70861_d;
      this.field_70860_g = this.field_70859_f;
      this.field_70868_i = this.field_70867_h;
      this.field_70865_by = this.field_70866_j;
      this.field_70867_h += this.rotationVelocity;

      if (this.field_70153_n != null) {
        if (!this.hooked) {
          this.energy = 1000;
          this.hooked = true;
        }
      } else {

        this.hooked = false;
      }

      if (this.hooked && this.field_70153_n instanceof EntityFishHookTFC && !this.field_70170_p.field_72995_K && func_70090_H()) {
        double randX, randY, randZ; EntityFishHookTFC fh = (EntityFishHookTFC)this.field_70153_n;
        if (Vec3.func_72443_a(fh.pullX, fh.pullY, fh.pullZ).func_72433_c() != 0.0D) {
          Vec3 dirVec = Vec3.func_72443_a(fh.pullX, fh.pullY, fh.pullZ).func_72432_b();
          this.pullX = dirVec.field_72450_a * 0.2D;
          this.pullY = dirVec.field_72448_b * 0.2D;
          this.pullZ = dirVec.field_72449_c * 0.2D;
        }

        if (this.pullX == this.pullY && this.pullY == this.pullZ && this.pullZ == 0.0D) {
          Vec3 temp = fh.getNormalDirectionOfPlayer(this.field_70165_t, this.field_70163_u, this.field_70161_v);
          this.pullX = temp.field_72450_a * 0.2D;
          this.pullY = temp.field_72448_b * 0.2D;
          this.pullZ = temp.field_72449_c * 0.2D;
        }






        if (this.pullX != 0.0D && TFC_Core.isWater(this.field_70170_p.func_147439_a((int)(this.field_70165_t - this.pullX / Math.abs(this.pullX)), (int)this.field_70163_u, (int)this.field_70161_v))) {
          randX = this.field_70146_Z.nextDouble() * 0.5D + 0.16D;
        } else {

          randX = 0.0D;
        }

        if (this.pullZ != 0.0D && TFC_Core.isWater(this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)this.field_70163_u, (int)(this.field_70161_v - this.pullZ / Math.abs(this.pullZ))))) {
          randZ = this.field_70146_Z.nextDouble() * (1.0D - randX - 0.09D) + 0.16D;
        } else {

          randZ = 0.0D;
        }
        if (this.pullX != 0.0D && TFC_Core.isWater(this.field_70170_p.func_147439_a((int)this.field_70165_t, (int)(this.field_70163_u - this.pullY / Math.abs(this.pullY)), (int)this.field_70161_v))) {
          randY = 1.0D - randX + randZ;
        } else {

          randY = 0.0D;
        }
        randX *= 0.33D;
        randY *= 0.33D;
        randZ *= 0.33D;
        if (randY == randX && randX == randZ && randZ == 0.0D) {
          int choice = this.field_70146_Z.nextInt(3);
          switch (choice) { case 0:
              randX = -1.0D; break;
            case 1: randY = -1.0D; break;
            case 2: randZ = -1.0D; break;
            default: randY = -1.0D; break; }

        }
        randX += (randX != 0.0D) ? 0.7D : 0.0D;
        randY += (randY != 0.0D) ? 0.7D : 0.0D;
        randZ += (randZ != 0.0D) ? 0.7D : 0.0D;
        double energyStrength = 0.05D;

        if (this.energy > 0) {
          energyStrength = 1.0D;
          this.tiredTicks = 0;
        }
        else if (this.tiredTicks > 80 && this.numRecoveries < 5) {
          this.numRecoveries++;
          this.energy = (int)(1000.0D * Math.pow(0.9D, this.numRecoveries));
        } else {

          this.tiredTicks++;
        }
        Vec3 fishVec = Vec3.func_72443_a(-(this.pullX * randX) * this.fishStrength * energyStrength, -(this.pullY * randY) * this.fishStrength * energyStrength, -(this.pullZ * randZ) * this.fishStrength * energyStrength);
        this.renderMotionX = fishVec.field_72450_a;
        this.renderMotionY = fishVec.field_72448_b;
        this.renderMotionZ = fishVec.field_72449_c;
        if (this.energy > 0) {
          this.energy = (int)(this.energy - fishVec.func_72433_c());
        }
        Vec3 calcVec = ((EntityFishHookTFC)this.field_70153_n).applyEntityForce(fishVec, this.field_70165_t, this.field_70163_u, this.field_70161_v);

        this.randomMotionVecX += (float)calcVec.field_72450_a;
        this.randomMotionVecY += (float)calcVec.field_72448_b - 0.008F;
        this.randomMotionVecY = (float)(this.randomMotionVecY * 0.9800000190734863D);
        this.randomMotionVecZ += (float)calcVec.field_72449_c;
      }
      if (this.field_70867_h > 6.2831855F) {

        this.field_70867_h -= 6.2831855F;

        if (this.field_70146_Z.nextInt(10) == 0)
        {
          this.rotationVelocity = 1.0F / (this.field_70146_Z.nextFloat() + 1.0F) * 0.2F;
        }
      }

      if (func_70090_H()) {



        if (this.field_70867_h < 3.1415927F) {

          float f1 = this.field_70867_h / 3.1415927F;
          this.field_70866_j = MathHelper.func_76126_a(f1 * f1 * 3.1415927F) * 3.1415927F * 0.25F;

          if (f1 > 0.75D)
          {
            this.randomMotionSpeed = 1.0F;
            this.yawMult = 1.0F;
          }
          else
          {
            this.yawMult *= 0.8F;
          }

        } else {

          this.field_70866_j = 0.0F;
          this.randomMotionSpeed *= 0.9F;
          this.yawMult *= 0.99F;
        }

        if (!this.field_70170_p.field_72995_K) {

          this.field_70159_w = (this.randomMotionVecX * (this.hooked ? 1.0F : this.randomMotionSpeed));
          this.field_70181_x = (this.randomMotionVecY * (this.hooked ? 1.0F : this.randomMotionSpeed));
          this.field_70179_y = (this.randomMotionVecZ * (this.hooked ? 1.0F : this.randomMotionSpeed));
          this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
        }

        float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
        this.field_70761_aq += (-((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0F / 3.1415927F - this.field_70761_aq) * 0.1F;
        this.field_70177_z = this.field_70761_aq;
        this.field_70859_f += 3.1415927F * this.yawMult * 1.5F;
        this.field_70861_d += (-((float)Math.atan2(f, this.field_70181_x)) * 180.0F / 3.1415927F - this.field_70861_d) * 0.1F;

      }
      else {

        this.field_70159_w *= 0.8D;
        this.field_70181_x -= 0.08D;
        this.field_70181_x *= 0.9800000190734863D;
        this.field_70179_y *= 0.8D;

        this.field_70866_j = MathHelper.func_76135_e(MathHelper.func_76126_a(this.field_70867_h)) * 3.1415927F * 0.25F;

        if (!this.field_70170_p.field_72995_K)
        {
          if (this.inGroundTimer > 100 + this.field_70146_Z.nextInt(20) && this.field_70122_E) {
            this.inGroundTimer = 0;
            this.field_70181_x = 0.65D;
            this.field_70159_w = this.field_70146_Z.nextDouble() * 0.65D * (this.field_70146_Z.nextBoolean() ? true : -1);
            this.field_70179_y = this.field_70146_Z.nextDouble() * 0.65D * (this.field_70146_Z.nextBoolean() ? true : -1);
          }
          else if (this.field_70122_E) {
            if (this.field_70153_n != null) {
              func_110145_l(this.field_70153_n);
            }
            this.inGroundTimer++;
            this.field_70159_w *= 0.01D;
            this.field_70179_y *= 0.01D;
          }
        }

        this.field_70861_d = (float)(this.field_70861_d + (-90.0F - this.field_70861_d) * 0.02D);
      }
      if (!this.hooked || !func_70090_H()) {
        this.renderMotionX = this.field_70159_w;
        this.renderMotionY = this.field_70181_x;
        this.renderMotionZ = this.field_70179_y;
      }
      if (this.hooked && this.field_70153_n instanceof EntityFishHookTFC) {
        EntityFishHookTFC fh = (EntityFishHookTFC)this.field_70153_n;
        for (int i = 0; i < 1; i++) {
          if (fh.isTooFarFromPlayer(this.field_70165_t, this.field_70163_u, this.field_70161_v)) {
            Vec3 dirVec = fh.getTooFarAdjustedVec(Vec3.func_72443_a(this.field_70159_w, this.field_70181_x, this.field_70179_y), this.field_70165_t, this.field_70163_u, this.field_70161_v);
            func_70091_d(dirVec.field_72450_a, dirVec.field_72448_b, dirVec.field_72449_c);
            this.field_70159_w *= 0.7D;
            this.field_70181_x *= 0.7D;
            this.field_70179_y *= 0.7D;
          }
        }
      }
      double xzMotion = Math.sqrt(this.renderMotionX * this.renderMotionX + this.renderMotionZ * this.renderMotionZ);
      this.currentRenderPitch = (xzMotion != 0.0D) ? (float)(Math.atan2(this.renderMotionY, xzMotion) * 180.0D / Math.PI) : this.currentRenderPitch;
      this.currentRenderYaw = (xzMotion != 0.0D) ? (float)(Math.atan2(-this.renderMotionX, -this.renderMotionZ) * 180.0D / Math.PI) : this.currentRenderYaw;
      if (this.field_70170_p.func_147437_c((int)this.field_70165_t, (int)this.field_70163_u - 1, (int)this.field_70161_v)) {
        this.currentRenderRoll = 90.0F;
      } else {

        this.currentRenderRoll = 0.0F;
      }
      this.field_70180_af.func_75692_b(26, Float.valueOf(this.currentRenderPitch));
      this.field_70180_af.func_75692_b(27, Float.valueOf(this.currentRenderYaw));
      this.field_70180_af.func_75692_b(28, Float.valueOf(this.currentRenderRoll));

      this.field_70180_af.func_75692_b(21, Float.valueOf((float)this.field_70159_w));
      this.field_70180_af.func_75692_b(22, Float.valueOf((float)this.field_70181_x));
      this.field_70180_af.func_75692_b(23, Float.valueOf((float)this.field_70179_y));
    } else {

      if (!this.field_70122_E) {
        this.currentRenderPitch = this.field_70180_af.func_111145_d(26);
      } else {

        this.currentRenderPitch = 0.0F;
      }
      this.currentRenderYaw = this.field_70180_af.func_111145_d(27);
      this.currentRenderRoll = this.field_70180_af.func_111145_d(28);


      this.field_70159_w = this.field_70180_af.func_111145_d(21);
      this.field_70181_x = this.field_70180_af.func_111145_d(22);
      this.field_70179_y = this.field_70180_af.func_111145_d(23);
    }
    this.field_70177_z = this.currentRenderYaw;
  }







  public void func_70612_e(float par1, float par2) {
    func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
  }



  protected void func_70626_be() {
    this.field_70708_bq++;

    int[] destination = getRandomDestination(this.field_70165_t, this.field_70163_u, this.field_70161_v);

    if (this.field_70708_bq > 100) {

      this.randomMotionVecX = this.randomMotionVecY = this.randomMotionVecZ = 0.0F;
    }
    else if (this.field_70146_Z.nextInt(50) == 0 || !this.field_70171_ac || (this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F && !this.hooked)) {






      float speedCoef = 1.0F;
      if (this.minPlayerDistance < 8.0D) {
        this.minPlayerDistance = Math.max(0.1D, this.minPlayerDistance);
        speedCoef = (float)(speedCoef * 1.0D / this.minPlayerDistance);
        speedCoef = MathHelper.func_76131_a(speedCoef, 1.0F, 8.0F);
      }
      double distance = func_70011_f(destination[0], destination[1], destination[2]);
      if (distance != 0.0D) {
        this.randomMotionVecX = (float)((destination[0] - this.field_70165_t) / distance) * 0.2F * speedCoef;
        this.randomMotionVecY = (float)((destination[1] - this.field_70163_u) / distance) * 0.2F * speedCoef;
        this.randomMotionVecZ = (float)((destination[2] - this.field_70161_v) / distance) * 0.2F * speedCoef;
      } else {

        this.randomMotionVecX = 0.0F;
        this.randomMotionVecY = 0.0F;
        this.randomMotionVecZ = 0.0F;
      }
    }

    func_70623_bb();
  }




  private int[] getRandomDestination(double x, double y, double z) {
    int destX = (int)this.currentDestX, destY = (int)this.currentDestY, destZ = (int)this.currentDestZ;
    int numAttempts = 0;
    this.nearbyPlayers = this.field_70170_p.func_72872_a(EntityPlayer.class, this.field_70121_D.func_72314_b(16.0D, 8.0D, 16.0D));
    boolean tooCloseToPlayer = false;
    for (EntityPlayer p : this.nearbyPlayers) {
      if (p.func_70011_f(destX, destY, destZ) < 8.0D)
      {
        tooCloseToPlayer = true;
      }
    }






    boolean[] validDirs = { TFC_Core.isWater(this.field_70170_p.func_147439_a((int)x + 1, (int)y, (int)z)), TFC_Core.isWater(this.field_70170_p.func_147439_a((int)x - 1, (int)y, (int)z)), TFC_Core.isWater(this.field_70170_p.func_147439_a((int)x, (int)y + 1, (int)z)), TFC_Core.isWater(this.field_70170_p.func_147439_a((int)x, (int)y - 1, (int)z)), TFC_Core.isWater(this.field_70170_p.func_147439_a((int)x, (int)y, (int)z + 1)), TFC_Core.isWater(this.field_70170_p.func_147439_a((int)x, (int)y, (int)z - 1)) };

    boolean needsNewLocation = (this.field_70146_Z.nextInt(25) < 3 || tooCloseToPlayer);
    while (needsNewLocation && numAttempts < 255) {
      numAttempts++;
      double currentPlayerDistance = 16.0D;
      int tempX = 0;

      int tempZ = 0;
      for (EntityPlayer p : this.nearbyPlayers) {
        if (p.func_70011_f(destX, destY, destZ) < 8.0D) {

          tempX = (int)(tempX + p.field_70165_t);

          tempZ = (int)(tempZ + p.field_70161_v);
        }
      }
      destX = (int)x + this.field_70146_Z.nextInt(10) * (this.field_70146_Z.nextBoolean() ? (validDirs[1] ? -1 : (validDirs[0] ? 1 : 0)) : (validDirs[0] ? 1 : (validDirs[1] ? -1 : 0)));
      destY = (int)y + this.field_70146_Z.nextInt(3) * (this.field_70146_Z.nextBoolean() ? (validDirs[3] ? -1 : (validDirs[2] ? 1 : 0)) : (validDirs[2] ? 1 : (validDirs[3] ? -1 : 0)));
      destZ = (int)z + this.field_70146_Z.nextInt(10) * (this.field_70146_Z.nextBoolean() ? (validDirs[5] ? -1 : (validDirs[4] ? 1 : 0)) : (validDirs[4] ? 1 : (validDirs[5] ? -1 : 0)));
      if (!this.nearbyPlayers.isEmpty()) {

        tempX /= this.nearbyPlayers.size();

        tempZ /= this.nearbyPlayers.size();

        destX -= tempX * 3;

        destZ -= tempZ * 3;
      }

      while (TFC_Core.isWater(this.field_70170_p.func_147439_a(destX, destY + 1, destZ)))
      {
        destY++;
      }

      for (EntityPlayer p : this.nearbyPlayers) {
        double playerDist = p.func_70011_f(destX, destY, destZ);
        if (playerDist < currentPlayerDistance || (playerDist < 8.0D && currentPlayerDistance == 16.0D)) {

          currentPlayerDistance = playerDist;
          tooCloseToPlayer = true;
        }
      }

      needsNewLocation = (!TFC_Core.isWater(this.field_70170_p.func_147439_a(destX, destY, destZ)) || tooCloseToPlayer);
      this.minPlayerDistance = currentPlayerDistance;
    }
    this.currentDestX = destX;
    this.currentDestY = destY;
    this.currentDestZ = destZ;
    return new int[] { destX, destY, destZ };
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
  }







  protected void func_70628_a(boolean par1, int par2) {
    TFC_Core.animalDropMeat((Entity)this, TFCItems.fishRaw, (32.0F + this.field_70170_p.field_73012_v.nextFloat()) * 16.0F);
  }



  protected boolean func_70692_ba() {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityFishTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */