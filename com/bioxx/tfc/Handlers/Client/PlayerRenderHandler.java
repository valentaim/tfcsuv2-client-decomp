/*    */ package com.bioxx.tfc.Handlers.Client;
/*    */ 
/*    */ import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
/*    */ import com.bioxx.tfc.Render.RenderLargeItem;
/*    */ import com.bioxx.tfc.Render.RenderQuiver;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.client.event.RenderPlayerEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerRenderHandler
/*    */ {
/* 20 */   public static final RenderQuiver RENDER_QUIVER = new RenderQuiver();
/* 21 */   public static final RenderLargeItem RENDER_LARGE = new RenderLargeItem();
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onPlayerRenderTick(RenderPlayerEvent.Specials.Pre e) {
/* 27 */     EntityLivingBase el = e.entityLiving;
/* 28 */     if (el instanceof EntityPlayer && 
/* 29 */       ((EntityPlayer)el).field_71071_by instanceof InventoryPlayerTFC) {
/* 30 */       ItemStack[] equipables = ((InventoryPlayerTFC)((EntityPlayer)el).field_71071_by).extraEquipInventory;
/* 31 */       for (ItemStack i : equipables) {
/*    */         
/* 33 */         if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.ItemQuiver) {
/* 34 */           RENDER_QUIVER.render(e.entityLiving, i, e);
/*    */         }
/* 36 */         else if (i != null) {
/* 37 */           RENDER_LARGE.render(el, i, e);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent e) {}
/*    */   
/*    */   @SubscribeEvent
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void onPlayerTick(TickEvent.PlayerTickEvent e) {}
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\PlayerRenderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */