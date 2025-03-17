/*    */ package com.bioxx.tfc.GUI;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.ContainerHopper;
/*    */ import com.bioxx.tfc.TileEntities.TEHopper;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.Container;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class GuiHopper
/*    */   extends GuiContainerTFC {
/* 13 */   public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_hopper.png");
/*    */   
/*    */   public GuiHopper(InventoryPlayer inventoryplayer, TEHopper te, World world, int i, int j, int k) {
/* 16 */     super((Container)new ContainerHopper(inventoryplayer, (IInventory)te), 176, 49);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_146976_a(float f, int i, int j) {
/* 22 */     drawGui(texture);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */