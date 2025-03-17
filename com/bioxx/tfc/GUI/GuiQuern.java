/*    */ package com.bioxx.tfc.GUI;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.ContainerQuern;
/*    */ import com.bioxx.tfc.TileEntities.TEQuern;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class GuiQuern
/*    */   extends GuiContainerTFC
/*    */ {
/* 13 */   public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_quern.png");
/*    */ 
/*    */   
/*    */   public GuiQuern(InventoryPlayer inventoryplayer, TEQuern te, World world, int x, int y, int z) {
/* 17 */     super((Container)new ContainerQuern(inventoryplayer, te, world, x, y, z), 176, 85);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float f, int i, int j) {
/* 23 */     drawGui(texture);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiQuern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */