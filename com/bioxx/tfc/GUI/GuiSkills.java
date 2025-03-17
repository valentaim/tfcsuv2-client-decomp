/*     */ package com.bioxx.tfc.GUI;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.ContainerSkills;
/*     */ import com.bioxx.tfc.Core.Player.Anvil_Skills;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.api.SkillsManager;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class GuiSkills
/*     */   extends GuiContainerTFC
/*     */ {
/*  22 */   public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_skills.png");
/*     */   
/*     */   protected EntityPlayer player;
/*     */   private int skillsPage;
/*     */   private static final int SKILLS_PER_PAGE = 9;
/*     */   
/*     */   public GuiSkills(EntityPlayer player) {
/*  29 */     super((Container)new ContainerSkills(player), 176, 166);
/*  30 */     setDrawInventory(false);
/*  31 */     this.player = player;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int par1, int par2) {
/*  37 */     this.field_146289_q.func_85187_a(TFC_Core.translate("gui.skillpage"), this.field_146999_f / 2 - this.field_146289_q.func_78256_a(TFC_Core.translate("gui.skillpage")) / 2, 4, 4210752, false);
/*  38 */     SkillStats ss = TFC_Core.getSkillStats(this.player);
/*  39 */     int y = 30;
/*  40 */     Anvil_Skills.Anvil_Skill w = ss.adv_skills.WEAPON;
/*  41 */     String weapon = String.format("W:  §6%04.1f §e%04.1f §f%04.1f §8%04.1f §0%04.1f §4%04.1f ", new Object[] { Double.valueOf(w.math_bonus((byte)0) * 100.0D), Double.valueOf(w.math_bonus((byte)1) * 100.0D), 
/*  42 */           Double.valueOf(w.math_bonus((byte)2) * 100.0D), Double.valueOf(w.math_bonus((byte)3) * 100.0D), Double.valueOf(w.math_bonus((byte)4) * 100.0D), Double.valueOf(w.math_bonus((byte)5) * 100.0D) });
/*  43 */     this.field_146289_q.func_85187_a(weapon, 6, y - 9, 0, false);
/*  44 */     Anvil_Skills.Anvil_Skill a = ss.adv_skills.ARMOR;
/*  45 */     y += 12;
/*  46 */     String armor = String.format("A:  §6%04.1f §e%04.1f §f%04.1f §8%04.1f §0%04.1f §4%04.1f ", new Object[] { Double.valueOf(a.math_bonus((byte)0) * 100.0D), Double.valueOf(a.math_bonus((byte)1) * 100.0D), 
/*  47 */           Double.valueOf(a.math_bonus((byte)2) * 100.0D), Double.valueOf(a.math_bonus((byte)3) * 100.0D), Double.valueOf(a.math_bonus((byte)4) * 100.0D), Double.valueOf(a.math_bonus((byte)5) * 100.0D) });
/*  48 */     this.field_146289_q.func_85187_a(armor, 6, y - 9, 0, false);
/*  49 */     Anvil_Skills.Anvil_Skill t = ss.adv_skills.TOOLS;
/*  50 */     y += 12;
/*  51 */     String tools = String.format("T:  §6%04.1f §e%04.1f §f%04.1f §8%04.1f §0%04.1f §4%04.1f ", new Object[] { Double.valueOf(t.math_bonus((byte)0) * 100.0D), Double.valueOf(t.math_bonus((byte)1) * 100.0D), 
/*  52 */           Double.valueOf(t.math_bonus((byte)2) * 100.0D), Double.valueOf(t.math_bonus((byte)3) * 100.0D), Double.valueOf(t.math_bonus((byte)4) * 100.0D), Double.valueOf(t.math_bonus((byte)5) * 100.0D) });
/*  53 */     this.field_146289_q.func_85187_a(tools, 6, y - 9, 0, false);
/*     */     
/*  55 */     y = 58;
/*  56 */     int count = -1;
/*  57 */     for (int i = 4; i < SkillsManager.instance.getSkillsArray().size(); i++) {
/*     */ 
/*     */       
/*  60 */       SkillsManager.Skill o = SkillsManager.instance.getSkillsArray().get(i);
/*  61 */       count++;
/*  62 */       if (count < 9 * this.skillsPage + 9 && count >= 9 * this.skillsPage) {
/*     */         
/*  64 */         bindTexture(texture);
/*  65 */         func_73729_b(4, y, 4, 208, 168, 16);
/*  66 */         y += 12;
/*  67 */         float perc = ss.getPercToNextRank(o.skillName);
/*  68 */         func_73729_b(4, y, 4, 168, 168, 4);
/*  69 */         func_73729_b(4, y, 4, 172, (int)Math.floor((168.0F * perc)), 4);
/*     */         
/*  71 */         this.field_146289_q.func_85187_a(TFC_Core.translate(o.skillName) + ": " + EnumChatFormatting.DARK_BLUE + ss.getSkillRank(o.skillName).getLocalizedName(), 6, y - 9, 0, false);
/*  72 */         y += 3;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float f, int i, int j) {
/*  80 */     drawGui(texture);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawGui(ResourceLocation rl) {
/*  86 */     bindTexture(rl);
/*  87 */     this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
/*  88 */     this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2 - 3;
/*  89 */     func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/*  95 */     super.func_73866_w_();
/*  96 */     createButtons();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton guibutton) {
/* 102 */     if (guibutton.field_146127_k == 0) {
/* 103 */       Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiInventoryTFC((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
/* 104 */     } else if (guibutton.field_146127_k == 2) {
/* 105 */       Minecraft.func_71410_x().func_147108_a(new GuiCalendar((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
/* 106 */     } else if (guibutton.field_146127_k == 3) {
/* 107 */       Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiHealth((EntityPlayer)(Minecraft.func_71410_x()).field_71439_g));
/* 108 */     } else if (guibutton.field_146127_k == 4) {
/*     */       
/* 110 */       if (this.skillsPage > 0) {
/* 111 */         this.skillsPage--;
/*     */       }
/* 113 */     } else if (guibutton.field_146127_k == 5) {
/*     */       
/* 115 */       if (9 + this.skillsPage * 9 < SkillsManager.instance.getSkillsArray().size()) {
/* 116 */         this.skillsPage++;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/* 123 */     super.func_73876_c();
/* 124 */     if (this.skillsPage == 0) {
/* 125 */       ((GuiButton)this.field_146292_n.get(4)).field_146124_l = false;
/*     */     } else {
/* 127 */       ((GuiButton)this.field_146292_n.get(4)).field_146124_l = true;
/*     */     } 
/* 129 */     if (9 + this.skillsPage * 9 < SkillsManager.instance.getSkillsArray().size()) {
/* 130 */       ((GuiButton)this.field_146292_n.get(5)).field_146124_l = true;
/*     */     } else {
/* 132 */       ((GuiButton)this.field_146292_n.get(5)).field_146124_l = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void createButtons() {
/* 138 */     this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
/* 139 */     this.field_147009_r = (this.field_146295_m - this.field_147000_g) / 2;
/* 140 */     this.field_146292_n.clear();
/* 141 */     this.field_146292_n.add(new GuiInventoryButton(0, this.field_147003_i + 176, this.field_147009_r, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Inventory.Inventory"), TFC_Textures.guiInventory));
/* 142 */     this.field_146292_n.add(new GuiInventoryButton(1, this.field_147003_i + 176, this.field_147009_r + 19, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Inventory.Skills"), TFC_Textures.guiSkills));
/* 143 */     this.field_146292_n.add(new GuiInventoryButton(2, this.field_147003_i + 176, this.field_147009_r + 38, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Calendar.Calendar"), TFC_Textures.guiCalendar));
/* 144 */     this.field_146292_n.add(new GuiInventoryButton(3, this.field_147003_i + 176, this.field_147009_r + 57, 25, 20, 0, 86, 25, 20, TFC_Core.translate("gui.Inventory.Health"), TFC_Textures.guiHealth));
/* 145 */     this.field_146292_n.add(new GuiButtonPage(4, this.field_147003_i + 4, this.field_147009_r + 144, 30, 15, 0, 177));
/* 146 */     this.field_146292_n.add(new GuiButtonPage(5, this.field_147003_i + 142, this.field_147009_r + 144, 30, 15, 0, 192));
/*     */   }
/*     */   
/*     */   public class GuiButtonPage
/*     */     extends GuiButton {
/*     */     private int u;
/*     */     private int v;
/*     */     
/*     */     public GuiButtonPage(int id, int xPos, int yPos, int xSize, int ySize, int u, int v) {
/* 155 */       super(id, xPos, yPos, xSize, ySize, "");
/* 156 */       this.u = u;
/* 157 */       this.v = v;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void func_146112_a(Minecraft par1Minecraft, int xPos, int yPos) {
/* 163 */       if (this.field_146125_m) {
/*     */         
/* 165 */         GuiSkills.this.bindTexture(GuiSkills.texture);
/* 166 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 167 */         this.field_146123_n = (xPos >= this.field_146128_h && yPos >= this.field_146129_i && xPos < this.field_146128_h + this.field_146120_f && yPos < this.field_146129_i + this.field_146121_g);
/* 168 */         int k = func_146114_a(this.field_146123_n) - 1;
/* 169 */         func_73729_b(this.field_146128_h, this.field_146129_i, this.u + 30 * k, this.v, this.field_146120_f, this.field_146121_g);
/*     */         
/* 171 */         func_146119_b(par1Minecraft, xPos, yPos);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiSkills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */