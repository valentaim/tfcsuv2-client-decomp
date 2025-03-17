/*    */ package com.bioxx.tfc.GUI;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.ContainerHorseInventoryTFC;
/*    */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.client.gui.inventory.GuiInventory;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class GuiScreenHorseInventoryTFC
/*    */   extends GuiContainerTFC {
/* 18 */   public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_horse.png");
/*    */   
/*    */   private EntityHorseTFC horse;
/*    */   
/*    */   private float xSize;
/*    */   private float ySize;
/*    */   
/*    */   public GuiScreenHorseInventoryTFC(InventoryPlayer playerInv, IInventory horseInv, EntityHorseTFC entityHorse) {
/* 26 */     super((Container)new ContainerHorseInventoryTFC(playerInv, horseInv, entityHorse), 176, 85);
/*    */     
/* 28 */     this.horse = entityHorse;
/* 29 */     this.field_146291_p = false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146979_b(int par1, int par2) {
/* 38 */     if (this.horse.func_94056_bM()) {
/* 39 */       this.field_146289_q.func_78276_b(this.horse.func_94057_bL(), 8, 6, 4210752);
/*    */     }
/* 41 */     this.field_146289_q.func_78276_b(String.format("Health: %.3f§6", new Object[] { Double.valueOf(this.horse.func_110138_aP()) }), 85, 30, 4210752);
/* 42 */     double jump = this.horse.func_110215_cj();
/* 43 */     this.field_146289_q.func_78276_b(String.format("Jump Strength: %.3f§6", new Object[] { Double.valueOf(jump) }), 85, 38, 4210752);
/* 44 */     double speed = this.horse.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b();
/* 45 */     this.field_146289_q.func_78276_b(String.format("Speed: %.3f§6", new Object[] { Double.valueOf(speed) }), 85, 46, 4210752);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float par1, int par2, int par3) {
/* 55 */     drawGui(texture);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void drawForeground(int guiLeft, int guiTop) {
/* 61 */     if (this.horse.func_110261_ca())
/*    */     {
/* 63 */       func_73729_b(guiLeft + 79, guiTop + 17, 0, getShiftedYSize() + 1, 90, 54);
/*    */     }
/*    */     
/* 66 */     if (this.horse.func_110259_cr())
/*    */     {
/* 68 */       func_73729_b(guiLeft + 7, guiTop + 35, 0, getShiftedYSize() + 55, 18, 18);
/*    */     }
/*    */ 
/*    */     
/* 72 */     GuiInventory.func_147046_a(guiLeft + 51, guiTop + 60, 17, (guiLeft + 51) - this.xSize, (guiTop + 75 - 50) - this.ySize, (EntityLivingBase)this.horse);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_73863_a(int par1, int par2, float par3) {
/* 82 */     this.xSize = par1;
/* 83 */     this.ySize = par2;
/* 84 */     super.func_73863_a(par1, par2, par3);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiScreenHorseInventoryTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */