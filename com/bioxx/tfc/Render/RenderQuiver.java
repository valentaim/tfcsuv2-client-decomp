/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Items.ItemQuiver;
/*    */ import com.bioxx.tfc.Render.Models.ModelQuiver;
/*    */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderQuiver
/*    */ {
/* 21 */   private ModelQuiver quiver = new ModelQuiver();
/* 22 */   private static final ResourceLocation QUIVER_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/models/armor/leatherquiver_1.png");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 29 */     doRender(entity, item, e);
/*    */   }
/*    */ 
/*    */   
/*    */   public void doRender(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 34 */     if (e != null) {
/* 35 */       GL11.glPushMatrix();
/* 36 */       e.renderer.field_77109_a.field_78115_e.func_78794_c(0.0625F);
/* 37 */       GL11.glScalef(0.7F, 0.7F, 0.7F);
/* 38 */       GL11.glTranslatef(0.0F, 0.5F, 0.05F);
/* 39 */       TFC_Core.bindTexture(QUIVER_TEXTURE);
/* 40 */       this.quiver.render(entity, ((ItemQuiver)item.func_77973_b()).getQuiverArrows(item) / 8);
/* 41 */       GL11.glPopMatrix();
/*    */     } else {
/* 43 */       float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
/* 44 */       GL11.glPushMatrix();
/* 45 */       (Minecraft.func_71410_x()).field_71446_o.func_110577_a(QUIVER_TEXTURE);
/* 46 */       if (!entity.func_70093_af()) {
/* 47 */         GL11.glTranslatef(0.0F, entityTranslateY + 0.0F, 0.1F);
/*    */       } else {
/* 49 */         GL11.glTranslatef(0.0F, entityTranslateY + 0.1F, 0.1F);
/* 50 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
/*    */       } 
/* 52 */       if (item != null) {
/* 53 */         if (item.func_77973_b() instanceof IEquipable) {
/* 54 */           ((IEquipable)item.func_77973_b()).onEquippedRender();
/*    */         }
/* 56 */         if (entity instanceof com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC) { this.quiver.render(entity, 16); }
/* 57 */         else { this.quiver.render(entity, ((ItemQuiver)item.func_77973_b()).getQuiverArrows(item) / 8); }
/*    */       
/* 59 */       }  GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */