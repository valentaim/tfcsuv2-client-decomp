package com.bioxx.tfc.Entities.Mobs;

import com.bioxx.tfc.Entities.EntityJavelin;
import com.bioxx.tfc.Entities.EntityProjectileTFC;
import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import com.bioxx.tfc.api.Interfaces.IInnateArmor;
import com.bioxx.tfc.api.Interfaces.IProjectile;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySkeletonTFC
  extends EntityMob
  implements IRangedAttackMob, ICausesDamage, IInnateArmor {
  private final EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 120, 15.0F);
  private final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.2D, false);
  private static final float[] ARMOR_PROBABILITY = new float[] { 0.0F, 0.5F, 0.1F, 0.15F };
  
  private static ItemStack heldItem;
  
  public EntitySkeletonTFC(World par1World) {
    super(par1World);
    heldItem = new ItemStack(TFCItems.bow);
    this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
    this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
    this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFleeSun((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
    this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
    this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
    this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    
    if (par1World != null && !par1World.field_72995_K) {
      setCombatTask();
    }
  }
  
  public void setCombatTask() {
    this.field_70714_bg.func_85156_a((EntityAIBase)this.aiAttackOnCollide);
    this.field_70714_bg.func_85156_a((EntityAIBase)this.aiArrowAttack);
    ItemStack itemstack = func_70694_bm();
    
    if (itemstack != null && (itemstack.func_77973_b() == TFCItems.bow || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin)) {
      
      this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiArrowAttack);

    
    }
    else {


      
      this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiAttackOnCollide);
    } 
  }





  
  public boolean func_70650_aV() {
    return true;
  }





  
  protected String func_70639_aQ() {
    return "mob.skeleton.say";
  }





  
  protected String func_70621_aR() {
    return "mob.skeleton.hurt";
  }





  
  protected String func_70673_aS() {
    return "mob.skeleton.death";
  }





  
  protected void func_145780_a(int par1, int par2, int par3, Block par4) {
    func_85030_a("mob.skeleton.step", 0.15F, 1.0F);
    super.func_145780_a(par1, par2, par3, par4);
  }


  
  public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
    super.func_70037_a(par1NBTTagCompound);
    if (par1NBTTagCompound.func_74764_b("SkeletonType")) {
      
      byte b0 = par1NBTTagCompound.func_74771_c("SkeletonType");
      setSkeletonType(b0);
    } 
    setCombatTask();
  }





  
  public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
    super.func_70014_b(par1NBTTagCompound);
    par1NBTTagCompound.func_74774_a("SkeletonType", (byte)getSkeletonType());
  }

  
  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0D);
    func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
  }


  
  protected void func_70088_a() {
    super.func_70088_a();
    this.field_70180_af.func_75682_a(13, Byte.valueOf((byte)this.field_70146_Z.nextInt(2)));
  }


  
  public void func_70030_z() {
    super.func_70030_z();
    if (func_70027_ad()) {
      func_70097_a(DamageSource.field_76370_b, 50.0F);
    }
  }










  
  public EnumCreatureAttribute func_70668_bt() {
    return EnumCreatureAttribute.UNDEAD;
  }


  
  public void func_70636_d() {
    if (this.field_70170_p.func_72935_r() && !this.field_70170_p.field_72995_K) {
      
      float f = func_70013_c(1.0F);
      
      if (f > 0.5F && this.field_70146_Z.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.field_70170_p.func_72937_j(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v))) {
        
        boolean flag = true;
        ItemStack itemstack = func_71124_b(4);
        if (itemstack != null) {
          
          if (itemstack.func_77984_f()) {
            
            itemstack.func_77964_b(itemstack.func_77952_i() + this.field_70146_Z.nextInt(2));
            if (itemstack.func_77952_i() >= itemstack.func_77958_k()) {
              
              func_70669_a(itemstack);
              func_70062_b(4, (ItemStack)null);
            } 
          } 
          flag = false;
        } 
        
        if (flag) {
          func_70015_d(8);
        }
      } 
    } 
    if (this.field_70170_p.field_72995_K && getSkeletonType() == 1) {
      func_70105_a(0.72F, 2.34F);
    }
    super.func_70636_d();
  }





  
  public void func_70098_U() {
    super.func_70098_U();
    if (this.field_70154_o instanceof EntityCreature) {
      
      EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
      this.field_70761_aq = entitycreature.field_70761_aq;
    } 
  }





  
  public void func_70645_a(DamageSource par1DamageSource) {
    super.func_70645_a(par1DamageSource);
    if (par1DamageSource.func_76364_f() instanceof net.minecraft.entity.projectile.EntityArrow && par1DamageSource.func_76346_g() instanceof EntityPlayer) {
      
      EntityPlayer entityplayer = (EntityPlayer)par1DamageSource.func_76346_g();
      double d0 = entityplayer.field_70165_t - this.field_70165_t;
      double d1 = entityplayer.field_70161_v - this.field_70161_v;
      
      if (d0 * d0 + d1 * d1 >= 2500.0D) {
        entityplayer.func_71029_a((StatBase)AchievementList.field_76020_v);
      }
    } 
  }







  
  protected void func_70628_a(boolean par1, int par2) {
    if (getSkeletonType() == 1) {
      
      if (func_70694_bm() != null && func_70694_bm().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin && this.field_70146_Z.nextFloat() < 0.03F) {
        func_145779_a(func_70694_bm().func_77973_b(), 1);
      
      }
    }
    else if (func_70694_bm() != null && func_70694_bm().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomBow) {
      
      int i = this.field_70146_Z.nextInt(3 + par2);
      func_145779_a(TFCItems.arrow, i);
    } 

    
    int amnt = this.field_70146_Z.nextInt(3 + par2);
    func_145779_a(Items.field_151103_aS, amnt);
  }



  
  protected void func_70600_l(int par1) {}


  
  protected void func_82160_b(boolean forceDrop, int dropChance) {
    for (int j = 0; j < (func_70035_c()).length; j++) {
      
      ItemStack itemstack = func_71124_b(j);
      boolean flag1 = (this.field_82174_bp[j] > 1.0F);
      
      if (itemstack != null && (forceDrop || flag1) && this.field_70146_Z.nextFloat() - dropChance * 0.01F < this.field_82174_bp[j]) {
        
        if (!flag1 && itemstack.func_77984_f()) {
          
          int k = Math.max(itemstack.func_77958_k() - 25, 1);
          int l = itemstack.func_77958_k() - this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(k) + 1);
          
          if (l > k)
          {
            l = k;
          }
          
          if (l < 1)
          {
            l = 1;
          }
          
          itemstack.func_77964_b(l);
        } 
        
        func_70099_a(itemstack, 0.0F);
      } 
    } 
  }


  
  protected void func_82164_bB() {
    superAddRandomArmor();
    if (getSkeletonType() == 0) {
      
      func_70062_b(0, new ItemStack(TFCItems.bow));
    }
    else if (getSkeletonType() == 1) {
      
      int i = this.field_70146_Z.nextInt(2);
      if (this.field_70146_Z.nextFloat() < 0.095F)
        i++; 
      if (this.field_70146_Z.nextFloat() < 0.095F)
        i++; 
      if (this.field_70146_Z.nextFloat() < 0.095F) {
        i++;
      }
      if (i == 0) {
        func_70062_b(0, new ItemStack(TFCItems.sedStoneJavelin));
      } else if (i == 1) {
        func_70062_b(0, new ItemStack(TFCItems.igExStoneJavelin));
      } else if (i == 2) {
        func_70062_b(0, new ItemStack(TFCItems.copperJavelin));
      } else if (i == 3) {
        func_70062_b(0, new ItemStack(TFCItems.bronzeJavelin));
      } else if (i == 4) {
        func_70062_b(0, new ItemStack(TFCItems.wroughtIronJavelin));
      } 
    } 
  }


  
  protected void func_82162_bC() {}

  
  public static Item getArmorItemForSlot(int par0, int par1) {
    switch (par0) {
      
      case 4:
        if (par1 == 0)
          return TFCItems.leatherHelmet; 
        if (par1 == 1)
          return TFCItems.copperHelmet; 
        if (par1 == 2)
          return TFCItems.bronzeHelmet; 
        if (par1 == 3)
          return TFCItems.wroughtIronHelmet; 
        if (par1 == 4) {
          return TFCItems.steelHelmet;
        }







































        
        return null;case 3: if (par1 == 0) return TFCItems.leatherChestplate;  if (par1 == 1) return TFCItems.copperChestplate;  if (par1 == 2) return TFCItems.bronzeChestplate;  if (par1 == 3) return TFCItems.wroughtIronChestplate;  if (par1 == 4) return TFCItems.steelChestplate;  return null;case 2: if (par1 == 0) return TFCItems.leatherLeggings;  if (par1 == 1) return TFCItems.copperGreaves;  if (par1 == 2) return TFCItems.bronzeGreaves;  if (par1 == 3) return TFCItems.wroughtIronGreaves;  if (par1 == 4) return TFCItems.steelGreaves;  return null;case 1: if (par1 == 0) return TFCItems.leatherBoots;  if (par1 == 1) return TFCItems.copperBoots;  if (par1 == 2) return TFCItems.bronzeBoots;  if (par1 == 3) return TFCItems.wroughtIronBoots;  if (par1 == 4) return TFCItems.steelBoots;  return null;
    } 
    return null;
  }
  private void superAddRandomArmor() {
    if (this.field_70146_Z.nextFloat() < ARMOR_PROBABILITY[this.field_70170_p.field_73013_u.func_151525_a()]) {
      
      int i = this.field_70146_Z.nextInt(2);
      float f = (this.field_70170_p.field_73013_u == EnumDifficulty.HARD) ? 0.1F : 0.25F;
      
      if (this.field_70146_Z.nextFloat() < 0.095F)
        i++; 
      if (this.field_70146_Z.nextFloat() < 0.095F)
        i++; 
      if (this.field_70146_Z.nextFloat() < 0.095F) {
        i++;
      }
      for (int j = 3; j >= 0; j--) {
        
        ItemStack itemstack = func_71124_b(j);
        if (j < 3 && this.field_70146_Z.nextFloat() < f) {
          break;
        }
        if (itemstack == null) {
          
          Item item = getArmorItemForSlot(j + 1, i);
          if (item != null)
            func_70062_b(j + 1, new ItemStack(item)); 
        } 
      } 
    } 
  }
  
  public ItemStack func_70694_bm() {
    return heldItem;
  }



  
  public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
    par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
    
    int skelType = this.field_70146_Z.nextInt(2);
    setSkeletonType(skelType);
    func_82164_bB();
    func_82162_bC();
    setCombatTask();
    func_98053_h((this.field_70146_Z.nextFloat() < 0.55F * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v)));
    
    return par1EntityLivingData;
  }

  
  public void func_82196_d(EntityLivingBase par1EntityLiving, float par2) {
    EntityJavelin entityJavelin;
    EntityProjectileTFC projectile = null;
    if (getSkeletonType() == 0) {
      
      projectile = new EntityProjectileTFC(this.field_70170_p, (EntityLivingBase)this, par1EntityLiving, 1.6F, 12.0F);
      projectile.func_70239_b(65.0D);
    }
    else if (getSkeletonType() == 1) {
      
      ItemStack is = func_70694_bm();
      if (is != null && is.func_77973_b() instanceof IProjectile) {
        
        entityJavelin = new EntityJavelin(this.field_70170_p, (EntityLivingBase)this, par1EntityLiving, 1.6F, 12.0F);
        double dam = ((IProjectile)is.func_77973_b()).getRangedDamage(is);
        entityJavelin.func_70239_b(dam);
      } 
    } 
    
    if (entityJavelin != null) {
      
      int var3 = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
      int var4 = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
      entityJavelin.func_70239_b(entityJavelin.func_70242_d() * 1.0D + this.field_70146_Z.nextGaussian() * 0.25D + (this.field_70170_p.field_73013_u.func_151525_a() * 0.11F));
      
      if (var3 > 0)
        entityJavelin.func_70239_b(entityJavelin.func_70242_d() + var3 * 0.5D); 
      if (var4 > 0)
        entityJavelin.func_70240_a(var4); 
      if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, func_70694_bm()) > 0) {
        entityJavelin.func_70015_d(100);
      }
      this.field_70170_p.func_72956_a((Entity)this, "random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
      this.field_70170_p.func_72838_d((Entity)entityJavelin);
    } 
  }




  
  public int getSkeletonType() {
    return this.field_70180_af.func_75683_a(13);
  }

  
  public void setSkeletonType(int par1) {
    this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)par1));
    func_70105_a(0.6F, 1.8F);
  }


  
  public EnumDamageType getDamageType() {
    return EnumDamageType.PIERCING;
  }

  
  public int getCrushArmor() {
    return -335;
  }
  
  public int getSlashArmor() {
    return 1000;
  }
  
  public int getPierceArmor() {
    return 500000;
  }


  
  public boolean func_70601_bi() {
    int x = MathHelper.func_76128_c(this.field_70165_t);
    int y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
    int z = MathHelper.func_76128_c(this.field_70161_v);
    Block b = this.field_70170_p.func_147439_a(x, y, z);
    
    if (b == TFCBlocks.leaves || b == TFCBlocks.leaves2 || b == TFCBlocks.thatch) {
      return false;
    }
    return super.func_70601_bi();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntitySkeletonTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */