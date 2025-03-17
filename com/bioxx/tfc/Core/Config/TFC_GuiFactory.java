/*    */ package com.bioxx.tfc.Core.Config;
/*    */ 
/*    */ import cpw.mods.fml.client.IModGuiFactory;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.gui.GuiScreen;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TFC_GuiFactory
/*    */   implements IModGuiFactory
/*    */ {
/*    */   public void initialize(Minecraft minecraftInstance) {}
/*    */   
/*    */   public Class<? extends GuiScreen> mainConfigGuiClass() {
/* 20 */     return (Class)TFC_ConfigGUI.class;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
/* 26 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IModGuiFactory.RuntimeOptionGuiHandler getHandlerFor(IModGuiFactory.RuntimeOptionCategoryElement element) {
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\TFC_GuiFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */