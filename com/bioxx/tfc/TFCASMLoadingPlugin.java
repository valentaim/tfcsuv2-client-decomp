/*    */ package com.bioxx.tfc;
/*    */ 
/*    */ import com.bioxx.tfc.ASM.Transform.TF_EntityLeashKnot;
/*    */ import com.bioxx.tfc.ASM.Transform.TF_EntityPlayer;
/*    */ import com.bioxx.tfc.ASM.Transform.TF_EntityPlayerMP;
/*    */ import com.bioxx.tfc.ASM.Transform.TF_EntityRenderer;
/*    */ import com.bioxx.tfc.ASM.Transform.TF_RenderGlobal;
/*    */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
/*    */ import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;
/*    */ import java.io.File;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ @TransformerExclusions({"com.bioxx.tfc.ASM"})
/*    */ public class TFCASMLoadingPlugin
/*    */   implements IFMLLoadingPlugin
/*    */ {
/*    */   public static boolean runtimeDeobf;
/*    */   public static File location;
/*    */   
/*    */   public String getAccessTransformerClass() {
/* 23 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] getASMTransformerClass() {
/* 28 */     return new String[] { TF_EntityRenderer.class
/* 29 */         .getName(), TF_RenderGlobal.class
/* 30 */         .getName(), TF_EntityLeashKnot.class
/* 31 */         .getName(), TF_EntityPlayerMP.class
/* 32 */         .getName(), TF_EntityPlayer.class
/* 33 */         .getName() };
/*    */   }
/*    */ 
/*    */   
/*    */   public String getModContainerClass() {
/* 38 */     return TerraFirmaCraftCore.class.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getSetupClass() {
/* 43 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void injectData(Map<String, Object> data) {
/* 48 */     runtimeDeobf = ((Boolean)data.get("runtimeDeobfuscationEnabled")).booleanValue();
/* 49 */     location = (File)data.get("coremodLocation");
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TFCASMLoadingPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */