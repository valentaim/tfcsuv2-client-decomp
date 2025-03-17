/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.EntityRenderer;
/*    */ import net.minecraft.client.resources.IResourceManager;
/*    */ import net.minecraft.client.shader.ShaderGroup;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ 
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class EntityRendererTFC
/*    */   extends EntityRenderer
/*    */ {
/*    */   private boolean allowShaderSwitching = true;
/*    */   private ResourceLocation currentShader;
/*    */   
/*    */   public EntityRendererTFC(Minecraft minecraft, IResourceManager irm) {
/* 22 */     super(minecraft, irm);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_147703_b() {
/* 28 */     if (this.allowShaderSwitching) {
/* 29 */       super.func_147705_c();
/*    */     }
/*    */   }
/*    */   
/*    */   public void setManualShader(ResourceLocation shaderLocation) {
/* 34 */     deactivateManualShader();
/*    */     try {
/* 36 */       Minecraft mc = Minecraft.func_71410_x();
/* 37 */       this.field_147707_d = new ShaderGroup(mc.func_110434_K(), mc.func_110442_L(), mc.func_147110_a(), shaderLocation);
/* 38 */       this.field_147707_d.func_148026_a(mc.field_71443_c, mc.field_71440_d);
/* 39 */       this.currentShader = shaderLocation;
/*    */     }
/* 41 */     catch (IOException ioexception) {
/*    */       
/* 43 */       LogManager.getLogger().warn("Failed to load shader: " + shaderLocation, ioexception);
/*    */     } 
/* 45 */     this.allowShaderSwitching = false;
/*    */   }
/*    */   
/*    */   public void deactivateManualShader() {
/* 49 */     this.allowShaderSwitching = true;
/* 50 */     super.func_147703_b();
/*    */   }
/*    */   
/*    */   public ResourceLocation getCurrentShaderLocation() {
/* 54 */     return this.currentShader;
/*    */   }
/*    */   
/*    */   public boolean getManualShaderBeingUsed() {
/* 58 */     return !this.allowShaderSwitching;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_147705_c() {
/* 64 */     if (this.allowShaderSwitching)
/* 65 */       super.func_147705_c(); 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\EntityRendererTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */