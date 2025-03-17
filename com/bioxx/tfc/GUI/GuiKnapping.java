/*     */ package com.bioxx.tfc.GUI;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.ContainerSpecialCrafting;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.KnappingUpdatePacket;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.relauncher.ReflectionHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ 
/*     */ public class GuiKnapping
/*     */   extends GuiContainerTFC
/*     */ {
/*     */   private boolean previouslyLoaded;
/*  26 */   public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_knapping.png");
/*  27 */   private final Field _selectedButton = ReflectionHelper.findField(GuiScreen.class, new String[] { "selectedButton", "field_146290_a" });
/*     */ 
/*     */   
/*     */   public GuiKnapping(InventoryPlayer inventoryplayer, ItemStack is, World world, int x, int y, int z) {
/*  31 */     super((Container)new ContainerSpecialCrafting(inventoryplayer, is, world, x, y, z), 176, 103);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146281_b() {
/*  37 */     (PlayerManagerTFC.getInstance().getClientPlayer()).knappingInterface = new boolean[25];
/*  38 */     super.func_146281_b();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  44 */     super.func_73866_w_();
/*     */     
/*  46 */     this.field_146292_n.clear();
/*  47 */     ((ContainerSpecialCrafting)this.field_147002_h).setDecreasedStack(Boolean.valueOf(false));
/*     */     
/*  49 */     for (int y = 0; y < 5; y++) {
/*     */       
/*  51 */       for (int x = 0; x < 5; x++) {
/*     */         
/*  53 */         this.field_146292_n.add(new GuiKnappingButton(x + y * 5, this.field_147003_i + x * 16 + 10, this.field_147009_r + y * 16 + 12, 16, 16));
/*     */         
/*  55 */         if (!this.previouslyLoaded) {
/*     */           
/*  57 */           if ((PlayerManagerTFC.getInstance().getClientPlayer()).knappingInterface[y * 5 + x])
/*     */           {
/*  59 */             resetButton(y * 5 + x);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*  69 */         else if ((PlayerManagerTFC.getInstance().getClientPlayer()).specialCraftingType.func_77973_b() != TFCItems.flatClay && ((ContainerSpecialCrafting)this.field_147002_h).craftMatrix
/*  70 */           .func_70301_a(y * 5 + x) == null) {
/*     */           
/*  72 */           resetButton(y * 5 + x);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  78 */     this.previouslyLoaded = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton guibutton) {
/*  84 */     resetButton(guibutton.field_146127_k);
/*  85 */     KnappingUpdatePacket knappingUpdatePacket = new KnappingUpdatePacket(guibutton.field_146127_k);
/*  86 */     TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)knappingUpdatePacket);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetButton(int id) {
/*  91 */     if ((PlayerManagerTFC.getInstance().getClientPlayer()).specialCraftingTypeAlternate == null)
/*     */     {
/*  93 */       ((GuiKnappingButton)this.field_146292_n.get(id)).field_146125_m = false;
/*     */     }
/*  95 */     (PlayerManagerTFC.getInstance().getClientPlayer()).knappingInterface[id] = true;
/*  96 */     ((GuiKnappingButton)this.field_146292_n.get(id)).field_146124_l = false;
/*  97 */     ((ContainerSpecialCrafting)this.field_147002_h).craftMatrix.func_70299_a(id, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float f, int p, int j) {
/* 103 */     drawGui(texture);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_146983_a(int par1) {
/* 113 */     if (this.field_146297_k.field_71439_g.field_71071_by.field_70461_c != par1 - 2) {
/*     */       
/* 115 */       super.func_146983_a(par1);
/* 116 */       return true;
/*     */     } 
/*     */     
/* 119 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146273_a(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
/* 125 */     if (clickedMouseButton == 0)
/*     */     {
/* 127 */       for (int i = 0; i < this.field_146292_n.size(); i++) {
/*     */         
/* 129 */         if (this.field_146292_n.get(i) instanceof GuiButton) {
/*     */           
/* 131 */           GuiButton guiButton = this.field_146292_n.get(i);
/*     */           
/* 133 */           if (guiButton.func_146116_c(this.field_146297_k, mouseX, mouseY))
/*     */             
/*     */             try {
/*     */               
/* 137 */               GuiScreenEvent.ActionPerformedEvent.Pre event = new GuiScreenEvent.ActionPerformedEvent.Pre((GuiScreen)this, guiButton, this.field_146292_n);
/* 138 */               if (MinecraftForge.EVENT_BUS.post((Event)event))
/*     */                 break; 
/* 140 */               if (this._selectedButton.get(this) != event.button)
/*     */               
/*     */               { 
/* 143 */                 func_146286_b(mouseX, mouseY, 0);
/*     */                 
/* 145 */                 this._selectedButton.set(this, event.button);
/* 146 */                 event.button.func_146113_a(this.field_146297_k.func_147118_V());
/* 147 */                 func_146284_a(event.button);
/* 148 */                 if (equals(this.field_146297_k.field_71462_r))
/* 149 */                   MinecraftForge.EVENT_BUS.post((Event)new GuiScreenEvent.ActionPerformedEvent.Post((GuiScreen)this, event.button, this.field_146292_n));  } 
/* 150 */             } catch (Exception e) {
/*     */               
/* 152 */               throw new RuntimeException(e);
/*     */             }  
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiKnapping.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */