/*     */ package com.bioxx.tfc.GUI;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.inventory.GuiContainer;
/*     */ import net.minecraft.client.renderer.InventoryEffectRenderer;
/*     */ import net.minecraft.client.renderer.OpenGlHelper;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.entity.RenderManager;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiInventoryTFC
/*     */   extends InventoryEffectRenderer
/*     */ {
/*     */   private float xSizeLow;
/*     */   private float ySizeLow;
/*     */   private boolean hasEffect;
/*  44 */   protected static final ResourceLocation UPPER_TEXTURE = new ResourceLocation("terrafirmacraft:textures/gui/inventory.png");
/*  45 */   protected static final ResourceLocation UPPER_TEXTURE_2X2 = new ResourceLocation("terrafirmacraft:textures/gui/gui_inventory2x2.png");
/*  46 */   protected static final ResourceLocation EFFECTS_TEXTURE = new ResourceLocation("terrafirmacraft:textures/gui/inv_effects.png");
/*     */   protected EntityPlayer player;
/*     */   protected Slot activeSlot;
/*     */   private long spamTimer;
/*     */   
/*     */   public GuiInventoryTFC(EntityPlayer player) {
/*  52 */     super(player.field_71069_bz);
/*  53 */     this.field_146291_p = true;
/*  54 */     player.func_71064_a((StatBase)AchievementList.field_76004_f, 1);
/*  55 */     this.field_146999_f = 176;
/*  56 */     this.field_147000_g = 85 + PlayerInventory.invYSize;
/*  57 */     this.player = player;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/*  63 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  64 */     if (this.player.getEntityData().func_74764_b("craftingTable")) {
/*  65 */       TFC_Core.bindTexture(UPPER_TEXTURE);
/*     */     } else {
/*  67 */       TFC_Core.bindTexture(UPPER_TEXTURE_2X2);
/*  68 */     }  int k = this.field_147003_i;
/*  69 */     int l = this.field_147009_r;
/*  70 */     func_73729_b(k, l, 0, 0, this.field_146999_f, 86);
/*     */     
/*  72 */     drawPlayerModel(k + 51, l + 75, 30, (k + 51) - this.xSizeLow, (l + 75 - 50) - this.ySizeLow, (EntityLivingBase)this.field_146297_k.field_71439_g);
/*     */     
/*  74 */     PlayerInventory.drawInventory((GuiContainer)this, this.field_146294_l, this.field_146295_m, this.field_147000_g - PlayerInventory.invYSize);
/*  75 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94065_a(int i, int j, IIcon icon, int w, int h) {
/*  81 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  82 */     GL11.glEnable(3042);
/*  83 */     GL11.glEnable(2929);
/*  84 */     tessellator.func_78382_b();
/*  85 */     tessellator.func_78374_a((i + 0), (j + h), this.field_73735_i, icon.func_94209_e(), icon.func_94210_h());
/*  86 */     tessellator.func_78374_a((i + w), (j + h), this.field_73735_i, icon.func_94212_f(), icon.func_94210_h());
/*  87 */     tessellator.func_78374_a((i + w), (j + 0), this.field_73735_i, icon.func_94212_f(), icon.func_94206_g());
/*  88 */     tessellator.func_78374_a((i + 0), (j + 0), this.field_73735_i, icon.func_94209_e(), icon.func_94206_g());
/*  89 */     tessellator.func_78381_a();
/*  90 */     GL11.glDisable(3042);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawPlayerModel(int par0, int par1, int par2, float par3, float par4, EntityLivingBase par5EntityLivingBase) {
/*  95 */     GL11.glEnable(2903);
/*  96 */     GL11.glPushMatrix();
/*  97 */     GL11.glTranslatef(par0, par1, 50.0F);
/*  98 */     GL11.glScalef(-par2, par2, par2);
/*  99 */     GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
/* 100 */     float f2 = par5EntityLivingBase.field_70761_aq;
/* 101 */     float f3 = par5EntityLivingBase.field_70177_z;
/* 102 */     float f4 = par5EntityLivingBase.field_70125_A;
/* 103 */     float f5 = par5EntityLivingBase.field_70758_at;
/* 104 */     float f6 = par5EntityLivingBase.field_70759_as;
/* 105 */     GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
/* 106 */     RenderHelper.func_74519_b();
/* 107 */     GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
/* 108 */     GL11.glRotatef(-((float)Math.atan((par4 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
/* 109 */     par5EntityLivingBase.field_70761_aq = (float)Math.atan((par3 / 40.0F)) * 20.0F;
/* 110 */     par5EntityLivingBase.field_70177_z = (float)Math.atan((par3 / 40.0F)) * 40.0F;
/* 111 */     par5EntityLivingBase.field_70125_A = -((float)Math.atan((par4 / 40.0F))) * 20.0F;
/* 112 */     par5EntityLivingBase.field_70759_as = par5EntityLivingBase.field_70177_z;
/* 113 */     par5EntityLivingBase.field_70758_at = par5EntityLivingBase.field_70177_z;
/* 114 */     GL11.glTranslatef(0.0F, par5EntityLivingBase.field_70129_M, 0.0F);
/* 115 */     RenderManager.field_78727_a.field_78735_i = 180.0F;
/* 116 */     RenderManager.field_78727_a.func_147940_a((Entity)par5EntityLivingBase, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
/* 117 */     par5EntityLivingBase.field_70761_aq = f2;
/* 118 */     par5EntityLivingBase.field_70177_z = f3;
/* 119 */     par5EntityLivingBase.field_70125_A = f4;
/* 120 */     par5EntityLivingBase.field_70758_at = f5;
/* 121 */     par5EntityLivingBase.field_70759_as = f6;
/* 122 */     GL11.glPopMatrix();
/* 123 */     RenderHelper.func_74518_a();
/* 124 */     GL11.glDisable(32826);
/* 125 */     OpenGlHelper.func_77473_a(OpenGlHelper.field_77476_b);
/* 126 */     GL11.glDisable(3553);
/* 127 */     OpenGlHelper.func_77473_a(OpenGlHelper.field_77478_a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int par1, int par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/* 142 */     if (this.field_146297_k.field_71442_b.func_78758_h()) {
/* 143 */       this.field_146297_k.func_147108_a((GuiScreen)new GuiContainerCreativeTFC(this.player));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/* 149 */     this.field_146292_n.clear();
/*     */     
/* 151 */     if (this.field_146297_k.field_71442_b.func_78758_h()) {
/* 152 */       this.field_146297_k.func_147108_a((GuiScreen)new GuiContainerCreativeTFC((EntityPlayer)this.field_146297_k.field_71439_g));
/*     */     } else {
/* 154 */       super.func_73866_w_();
/*     */     } 
/* 156 */     if (!this.field_146297_k.field_71439_g.func_70651_bq().isEmpty()) {
/*     */ 
/*     */       
/* 159 */       this.field_147003_i = (this.field_146294_l - this.field_146999_f) / 2;
/* 160 */       this.hasEffect = true;
/*     */     } 
/*     */     
/* 163 */     this.field_146292_n.clear();
/* 164 */     this.field_146292_n.add(new GuiInventoryButton(0, this.field_147003_i + 176, this.field_147009_r + 3, 25, 20, 0, 86, 25, 20, 
/* 165 */           TFC_Core.translate("gui.Inventory.Inventory"), TFC_Textures.guiInventory));
/* 166 */     this.field_146292_n.add(new GuiInventoryButton(1, this.field_147003_i + 176, this.field_147009_r + 22, 25, 20, 0, 86, 25, 20, 
/* 167 */           TFC_Core.translate("gui.Inventory.Skills"), TFC_Textures.guiSkills));
/* 168 */     this.field_146292_n.add(new GuiInventoryButton(2, this.field_147003_i + 176, this.field_147009_r + 41, 25, 20, 0, 86, 25, 20, 
/* 169 */           TFC_Core.translate("gui.Calendar.Calendar"), TFC_Textures.guiCalendar));
/* 170 */     this.field_146292_n.add(new GuiInventoryButton(3, this.field_147003_i + 176, this.field_147009_r + 60, 25, 20, 0, 86, 25, 20, 
/* 171 */           TFC_Core.translate("gui.Inventory.Health"), TFC_Textures.guiHealth));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton guibutton) {
/* 177 */     if (guibutton.field_146127_k == 1) {
/* 178 */       Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiSkills(this.player));
/* 179 */     } else if (guibutton.field_146127_k == 2) {
/* 180 */       Minecraft.func_71410_x().func_147108_a(new GuiCalendar(this.player));
/* 181 */     } else if (guibutton.field_146127_k == 3) {
/* 182 */       Minecraft.func_71410_x().func_147108_a((GuiScreen)new GuiHealth(this.player));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_73863_a(int par1, int par2, float par3) {
/* 188 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 189 */     super.func_73863_a(par1, par2, par3);
/* 190 */     this.xSizeLow = par1;
/* 191 */     this.ySizeLow = par2;
/* 192 */     if (this.hasEffect) {
/* 193 */       displayDebuffEffects();
/*     */     }
/* 195 */     for (int j1 = 0; j1 < this.field_147002_h.field_75151_b.size(); j1++) {
/*     */       
/* 197 */       Slot slot = this.field_147002_h.field_75151_b.get(j1);
/* 198 */       if (func_146981_a(slot, par1, par2) && slot.func_111238_b()) {
/* 199 */         this.activeSlot = slot;
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean func_146981_a(Slot par1Slot, int par2, int par3) {
/* 205 */     return func_146978_c(par1Slot.field_75223_e, par1Slot.field_75221_f, 16, 16, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void displayDebuffEffects() {
/* 213 */     int var1 = this.field_147003_i - 124;
/* 214 */     int var2 = this.field_147009_r;
/* 215 */     Collection var4 = this.field_146297_k.field_71439_g.func_70651_bq();
/*     */     
/* 217 */     if (!var4.isEmpty()) {
/*     */       
/* 219 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 220 */       GL11.glDisable(2896);
/* 221 */       int var6 = 33;
/*     */       
/* 223 */       if (var4.size() > 5) {
/* 224 */         var6 = 132 / (var4.size() - 1);
/*     */       }
/* 226 */       for (Iterator<PotionEffect> var7 = this.field_146297_k.field_71439_g.func_70651_bq().iterator(); var7.hasNext(); var2 += var6) {
/*     */         
/* 228 */         PotionEffect var8 = var7.next();
/*     */ 
/*     */         
/* 231 */         Potion var9 = (Potion.field_76425_a[var8.func_76456_a()] instanceof com.bioxx.tfc.Food.TFCPotion) ? Potion.field_76425_a[var8.func_76456_a()] : Potion.field_76425_a[var8.func_76456_a()];
/* 232 */         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 233 */         TFC_Core.bindTexture(EFFECTS_TEXTURE);
/* 234 */         func_73729_b(var1, var2, 0, 166, 140, 32);
/*     */         
/* 236 */         if (var9.func_76400_d()) {
/*     */           
/* 238 */           int var10 = var9.func_76392_e();
/* 239 */           func_73729_b(var1 + 6, var2 + 7, 0 + var10 % 8 * 18, 198 + var10 / 8 * 18, 18, 18);
/*     */         } 
/*     */         
/* 242 */         String var12 = TFC_Core.translate(var9.func_76393_a());
/*     */         
/* 244 */         if (var8.func_76458_c() == 1) {
/* 245 */           var12 = var12 + " II";
/* 246 */         } else if (var8.func_76458_c() == 2) {
/* 247 */           var12 = var12 + " III";
/* 248 */         } else if (var8.func_76458_c() == 3) {
/* 249 */           var12 = var12 + " IV";
/*     */         } 
/* 251 */         this.field_146289_q.func_78261_a(var12, var1 + 10 + 18, var2 + 6, 16777215);
/* 252 */         String var11 = Potion.func_76389_a(var8);
/* 253 */         this.field_146289_q.func_78261_a(var11, var1 + 10 + 18, var2 + 6 + 10, 8355711);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isCooking(ItemStack is) {
/* 261 */     String itype = is.func_77973_b().func_77658_a().toLowerCase();
/* 262 */     return (itype.contains("salad") || itype.contains("soup") || itype.contains("sandwich"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_146983_a(int keycode) {
/* 269 */     boolean cooking = false;
/* 270 */     if (this.activeSlot != null && this.activeSlot.field_75222_d == 0 && this.activeSlot.func_75216_d() && this.activeSlot
/* 271 */       .func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/* 272 */       return false;
/*     */     }
/* 274 */     if (this.activeSlot != null && this.activeSlot.func_75216_d() && 
/* 275 */       isCooking(this.activeSlot.func_75211_c())) cooking = true;
/*     */     
/* 277 */     if (!cooking && keycode == 31 && this.activeSlot != null && this.activeSlot.func_82869_a(this.player) && this.activeSlot.func_75216_d() && this.activeSlot
/* 278 */       .func_75211_c() != null && this.activeSlot.func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && TFC_Time.getTotalTicks() > this.spamTimer + 5L) {
/*     */       
/* 280 */       this.spamTimer = TFC_Time.getTotalTicks();
/* 281 */       Item iType = this.activeSlot.func_75211_c().func_77973_b();
/* 282 */       ItemStack activeIS = this.activeSlot.func_75211_c();
/* 283 */       for (int i = 9; i < 45 && getEmptyCraftSlot() != -1; i++) {
/*     */         
/* 285 */         ItemStack is = this.field_147002_h.func_75139_a(i).func_75211_c();
/* 286 */         if (is != null && is.func_77973_b() == iType && Food.areEqual(activeIS, is) && Food.getWeight(is) < 160.0F) {
/* 287 */           func_146984_a(this.field_147002_h.func_75139_a(i), i, getEmptyCraftSlot(), 7);
/*     */         }
/*     */       } 
/* 290 */       if (this.field_147002_h.func_75139_a(0).func_75211_c() != null)
/*     */       {
/* 292 */         func_146984_a(this.field_147002_h.func_75139_a(0), 0, 0, 1);
/*     */       }
/* 294 */       return true;
/*     */     } 
/* 296 */     if (!cooking && keycode == 32 && TFC_Time.getTotalTicks() > this.spamTimer + 5L) {
/*     */       
/* 298 */       this.spamTimer = TFC_Time.getTotalTicks();
/* 299 */       int knifeSlot = -1;
/* 300 */       if (!getCraftingHasKnife())
/*     */       {
/* 302 */         for (int j = 9; j < 45 && getEmptyCraftSlot() != -1; j++) {
/*     */           
/* 304 */           ItemStack is = this.field_147002_h.func_75139_a(j).func_75211_c();
/* 305 */           if (is != null && is.func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife) {
/*     */             
/* 307 */             knifeSlot = j;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/* 312 */       for (int i = 9; i < 45 && getEmptyCraftSlot() != -1 && knifeSlot != -1 && this.field_147002_h.func_75139_a(knifeSlot).func_75211_c() != null; i++) {
/*     */         
/* 314 */         ItemStack is = this.field_147002_h.func_75139_a(i).func_75211_c();
/* 315 */         int knifeDamage = this.field_147002_h.func_75139_a(knifeSlot).func_75211_c().func_77960_j();
/* 316 */         if (knifeDamage >= this.field_147002_h.func_75139_a(knifeSlot).func_75211_c().func_77958_k()) {
/*     */           break;
/*     */         }
/* 319 */         if (is != null && !isCooking(is) && 
/* 320 */           is != null && !(is.func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) && is.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && Food.getDecay(is) > 0.0F && 
/* 321 */           Food.getDecayTimer(is) >= TFC_Time.getTotalHours()) {
/*     */           
/* 323 */           func_146984_a(this.field_147002_h.func_75139_a(i), i, getEmptyCraftSlot(), 7);
/* 324 */           func_146984_a(this.field_147002_h.func_75139_a(0), 0, 0, 1);
/*     */         } 
/*     */       } 
/* 327 */       return true;
/*     */     } 
/* 329 */     return super.func_146983_a(keycode);
/*     */   }
/*     */ 
/*     */   
/*     */   private int getEmptyCraftSlot() {
/* 334 */     if (this.field_147002_h.func_75139_a(4).func_75211_c() == null)
/* 335 */       return 4; 
/* 336 */     if (this.field_147002_h.func_75139_a(1).func_75211_c() == null)
/* 337 */       return 1; 
/* 338 */     if (this.field_147002_h.func_75139_a(2).func_75211_c() == null)
/* 339 */       return 2; 
/* 340 */     if (this.field_147002_h.func_75139_a(3).func_75211_c() == null)
/* 341 */       return 3; 
/* 342 */     if (this.player.getEntityData().func_74764_b("craftingTable")) {
/*     */       
/* 344 */       if (this.field_147002_h.func_75139_a(45).func_75211_c() == null)
/* 345 */         return 45; 
/* 346 */       if (this.field_147002_h.func_75139_a(46).func_75211_c() == null)
/* 347 */         return 46; 
/* 348 */       if (this.field_147002_h.func_75139_a(47).func_75211_c() == null)
/* 349 */         return 47; 
/* 350 */       if (this.field_147002_h.func_75139_a(48).func_75211_c() == null)
/* 351 */         return 48; 
/* 352 */       if (this.field_147002_h.func_75139_a(49).func_75211_c() == null) {
/* 353 */         return 49;
/*     */       }
/*     */     } 
/* 356 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean getCraftingHasKnife() {
/* 361 */     if (this.field_147002_h.func_75139_a(4).func_75211_c() != null && this.field_147002_h.func_75139_a(4).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 362 */       return true; 
/* 363 */     if (this.field_147002_h.func_75139_a(1).func_75211_c() != null && this.field_147002_h.func_75139_a(1).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 364 */       return true; 
/* 365 */     if (this.field_147002_h.func_75139_a(2).func_75211_c() != null && this.field_147002_h.func_75139_a(2).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 366 */       return true; 
/* 367 */     if (this.field_147002_h.func_75139_a(3).func_75211_c() != null && this.field_147002_h.func_75139_a(3).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 368 */       return true; 
/* 369 */     if (this.player.getEntityData().func_74764_b("craftingTable")) {
/*     */       
/* 371 */       if (this.field_147002_h.func_75139_a(45).func_75211_c() != null && this.field_147002_h.func_75139_a(45).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 372 */         return true; 
/* 373 */       if (this.field_147002_h.func_75139_a(46).func_75211_c() != null && this.field_147002_h.func_75139_a(46).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 374 */         return true; 
/* 375 */       if (this.field_147002_h.func_75139_a(47).func_75211_c() != null && this.field_147002_h.func_75139_a(47).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 376 */         return true; 
/* 377 */       if (this.field_147002_h.func_75139_a(48).func_75211_c() != null && this.field_147002_h.func_75139_a(48).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife)
/* 378 */         return true; 
/* 379 */       if (this.field_147002_h.func_75139_a(49).func_75211_c() != null && this.field_147002_h.func_75139_a(49).func_75211_c().func_77973_b() instanceof com.bioxx.tfc.api.Tools.IKnife) {
/* 380 */         return true;
/*     */       }
/*     */     } 
/* 383 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiInventoryTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */