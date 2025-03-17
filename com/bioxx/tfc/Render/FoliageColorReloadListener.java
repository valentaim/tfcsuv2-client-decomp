/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.ColorizerFoliageTFC;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.renderer.texture.TextureUtil;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.client.resources.IResourceManagerReloadListener;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class FoliageColorReloadListener
/*    */   implements IResourceManagerReloadListener
/*    */ {
/* 17 */   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/colormap/foliage.png");
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_110549_a(IResourceManager par1ResourceManager) {
/*    */     try {
/* 24 */       ColorizerFoliageTFC.setFoliageBiomeColorizer(TextureUtil.func_110986_a(par1ResourceManager, TEXTURE));
/*    */     }
/* 26 */     catch (IOException iOException) {}
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\FoliageColorReloadListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */