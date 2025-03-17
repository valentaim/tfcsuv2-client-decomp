/*    */ package com.bioxx.tfc.Render;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.texture.TextureMap;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderLargeItem
/*    */ {
/*    */   public void render(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 27 */     doRender(entity, item, e);
/*    */   }
/*    */   
/*    */   public void render(Entity entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 31 */     doRender(entity, item, e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void doRender(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 37 */     if (e != null) {
/* 38 */       GL11.glPushMatrix();
/* 39 */       e.renderer.field_77109_a.field_78115_e.func_78794_c(0.0625F);
/* 40 */       GL11.glScalef(0.7F, 0.7F, 0.7F);
/* 41 */       GL11.glTranslatef(0.0F, 0.5F, 0.6F);
/* 42 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 43 */       TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 44 */       RenderBlocks.getInstance().func_147800_a(Block.func_149634_a(item.func_77973_b()), item.func_77960_j(), 1.0F);
/* 45 */       GL11.glPopMatrix();
/*    */     } else {
/*    */       
/* 48 */       float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
/* 49 */       GL11.glPushMatrix();
/*    */       
/* 51 */       if (!entity.func_70093_af()) { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY + 0.0F, 0.5F); }
/*    */       else
/* 53 */       { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY - 0.1F, 0.6F);
/* 54 */         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F); }
/* 55 */        GL11.glScalef(0.8F, 0.8F, 0.8F);
/* 56 */       GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */       
/* 58 */       if (item != null) {
/* 59 */         if (item.func_77973_b() instanceof IEquipable) {
/* 60 */           ((IEquipable)item.func_77973_b()).onEquippedRender();
/*    */         }
/* 62 */         else if (Block.func_149634_a(item.func_77973_b()) instanceof IEquipable) {
/* 63 */           ((IEquipable)Block.func_149634_a(item.func_77973_b())).onEquippedRender();
/*    */         } 
/* 65 */         Block block = Block.func_149634_a(item.func_77973_b());
/* 66 */         TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 67 */         RenderBlocks.getInstance().func_147800_a(block, item.func_77960_j(), 1.0F);
/*    */       } 
/* 69 */       GL11.glPopMatrix();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void doRender(Entity entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
/* 74 */     float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
/* 75 */     GL11.glPushMatrix();
/*    */     
/* 77 */     if (!entity.func_70093_af()) { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY + 0.0F, 0.5F); }
/*    */     else
/* 79 */     { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY - 0.1F, 0.6F);
/* 80 */       GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F); }
/* 81 */      GL11.glScalef(0.8F, 0.8F, 0.8F);
/* 82 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/*    */     
/* 84 */     if (item != null) {
/* 85 */       if (item.func_77973_b() instanceof IEquipable) {
/* 86 */         ((IEquipable)item.func_77973_b()).onEquippedRender();
/*    */       }
/* 88 */       else if (Block.func_149634_a(item.func_77973_b()) instanceof IEquipable) {
/* 89 */         ((IEquipable)Block.func_149634_a(item.func_77973_b())).onEquippedRender();
/*    */       } 
/* 91 */       Block block = Block.func_149634_a(item.func_77973_b());
/* 92 */       TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 93 */       RenderBlocks.getInstance().func_147800_a(block, item.func_77960_j(), 1.0F);
/*    */     } 
/* 95 */     GL11.glPopMatrix();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderLargeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */