/*    */ package com.bioxx.tfc.Containers;
/*    */ 
/*    */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerPlanSelection
/*    */   extends ContainerTFC
/*    */ {
/*    */   private TEAnvil anvil;
/* 13 */   private String plan = "";
/*    */   
/*    */   public ContainerPlanSelection(EntityPlayer p, TEAnvil a, World w, int x, int y, int z) {
/* 16 */     this.anvil = a;
/*    */     
/* 18 */     this.player = p;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75142_b() {
/* 24 */     super.func_75142_b();
/* 25 */     if (this.anvil.craftingPlan != this.plan)
/* 26 */       this.plan = this.anvil.craftingPlan; 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerPlanSelection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */