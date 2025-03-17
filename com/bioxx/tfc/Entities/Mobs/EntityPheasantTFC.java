/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityPheasantTFC
/*     */   extends EntityChickenTFC
/*     */ {
/*     */   private boolean wasRoped;
/*     */   
/*     */   public EntityPheasantTFC(World par1World) {
/*  27 */     super(par1World);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPheasantTFC(World world, double posX, double posY, double posZ, NBTTagCompound genes) {
/*  32 */     super(world, posX, posY, posZ, genes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAI() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70692_ba() {
/*  44 */     return !this.wasRoped;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/*  51 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/*  52 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  53 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/*  55 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/*  56 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/*  57 */     if ((temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F) || TFC_Climate.isSwamp(this.field_70170_p, i, 150, k))
/*  58 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/*  59 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/*  65 */     super.func_110147_ax();
/*  66 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/*  76 */     this.field_70887_j = 10000;
/*  77 */     if (func_110167_bD() && !this.wasRoped)
/*  78 */       this.wasRoped = true; 
/*  79 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70639_aQ() {
/*  88 */     return func_70631_g_() ? "terrafirmacraft:mob.pheasant.chick.say" : "terrafirmacraft:mob.pheasant.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70621_aR() {
/*  97 */     return func_70631_g_() ? null : "terrafirmacraft:mob.pheasant.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70673_aS() {
/* 106 */     return func_70631_g_() ? null : "terrafirmacraft:mob.pheasant.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void roosterCrow() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 121 */     float ageMod = TFC_Core.getPercentGrown(this);
/* 122 */     func_145779_a(Items.field_151008_G, (int)(ageMod * getSizeMod() * (5 + this.field_70146_Z.nextInt(10))));
/*     */     
/* 124 */     if (isAdult()) {
/*     */       
/* 126 */       float foodWeight = ageMod * getSizeMod() * 40.0F;
/* 127 */       TFC_Core.animalDropMeat((Entity)this, TFCItems.chickenRaw, foodWeight);
/* 128 */       func_145779_a(Items.field_151103_aS, this.field_70146_Z.nextInt(2) + 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(IAnimal animal) {
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable createChildTFC(EntityAgeable entityageable) {
/* 141 */     if (entityageable instanceof IAnimal) {
/*     */       
/* 143 */       IAnimal animal = (IAnimal)entityageable;
/* 144 */       NBTTagCompound nbt = new NBTTagCompound();
/* 145 */       nbt.func_74776_a("m_size", animal.getSizeMod());
/* 146 */       nbt.func_74776_a("f_size", animal.getSizeMod());
/* 147 */       return (EntityAgeable)new EntityPheasantTFC(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, nbt);
/*     */     } 
/*     */     
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 156 */     super.func_70037_a(nbt);
/* 157 */     this.wasRoped = nbt.func_74767_n("wasRoped");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 163 */     super.func_70014_b(nbt);
/* 164 */     nbt.func_74757_a("wasRoped", this.wasRoped);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnimalTypeID() {
/* 170 */     return Helper.stringToInt("pheasant");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trySetName(String name, EntityPlayer player) {
/* 175 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*     */       
/* 177 */       func_94058_c(name);
/* 178 */       return true;
/*     */     } 
/* 180 */     func_85030_a("terrafirmacraft:mob.pheasant.say", 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/* 186 */     boolean flag = false;
/* 187 */     switch (interaction) {
/*     */       case NAME:
/* 189 */         flag = (getFamiliarity() > 40);
/*     */         break;
/*     */     } 
/*     */     
/* 193 */     if (!flag && !player.field_70170_p.field_72995_K) {
/* 194 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*     */     }
/* 196 */     return flag;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityPheasantTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */