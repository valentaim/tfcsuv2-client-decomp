/*    */ package com.bioxx.tfc.Entities.Mobs;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*    */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*    */ import com.bioxx.tfc.api.Interfaces.IInnateArmor;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.monster.EntityEnderman;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class EntityEndermanTFC
/*    */   extends EntityEnderman
/*    */   implements ICausesDamage, IInnateArmor
/*    */ {
/* 14 */   public static boolean[] carriableBlocks = new boolean[256];
/*    */ 
/*    */   
/*    */   public EntityEndermanTFC(World par1World) {
/* 18 */     super(par1World);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_110147_ax() {
/* 24 */     super.func_110147_ax();
/* 25 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(100.0D);
/* 26 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4000.0D);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumDamageType getDamageType() {
/* 32 */     return EnumDamageType.GENERIC;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCrushArmor() {
/* 37 */     return -335;
/*    */   }
/*    */   
/*    */   public int getSlashArmor() {
/* 41 */     return -335;
/*    */   }
/*    */   
/*    */   public int getPierceArmor() {
/* 45 */     return -335;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityEndermanTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */