/*     */ package com.bioxx.tfc.GUI;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.ContainerAnvil;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Crafting.PlanRecipe;
/*     */ import com.bioxx.tfc.api.Enums.RuleEnum;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.client.renderer.texture.TextureMap;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.world.World;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GuiAnvil
/*     */   extends GuiContainerTFC
/*     */ {
/*     */   public TEAnvil anvilTE;
/*  33 */   public static ResourceLocation texture = new ResourceLocation("terrafirmacraft", "textures/gui/gui_anvil.png");
/*     */   private EntityPlayer player;
/*     */   private int x;
/*  36 */   private String plan = "";
/*     */   
/*     */   private int y;
/*     */   private int z;
/*     */   
/*     */   public GuiAnvil(InventoryPlayer inventoryplayer, TEAnvil te, World world, int x, int y, int z) {
/*  42 */     super((Container)new ContainerAnvil(inventoryplayer, te, world, x, y, z), 208, 117);
/*  43 */     this.anvilTE = te;
/*  44 */     this.player = inventoryplayer.field_70458_d;
/*  45 */     this.x = x;
/*  46 */     this.y = y;
/*  47 */     this.z = z;
/*     */   }
/*     */   private ItemStack input;
/*     */   private ItemStack input2;
/*     */   
/*     */   public void func_73866_w_() {
/*  53 */     super.func_73866_w_();
/*     */     
/*  55 */     this.field_146292_n.clear();
/*  56 */     this.field_146292_n.add(new GuiAnvilButton(7, this.field_147003_i + 123, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilShrink, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Shrink")));
/*  57 */     this.field_146292_n.add(new GuiAnvilButton(6, this.field_147003_i + 105, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilUpset, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Upset")));
/*  58 */     this.field_146292_n.add(new GuiAnvilButton(5, this.field_147003_i + 123, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilBend, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Bend")));
/*  59 */     this.field_146292_n.add(new GuiAnvilButton(4, this.field_147003_i + 105, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilPunch, 208, 17, 16, 16, this, TFC_Core.translate("gui.Anvil.Punch")));
/*  60 */     this.field_146292_n.add(new GuiAnvilButton(3, this.field_147003_i + 87, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilDraw, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.Draw")));
/*  61 */     this.field_146292_n.add(new GuiAnvilButton(2, this.field_147003_i + 69, this.field_147009_r + 82, 16, 16, TFC_Textures.anvilHitHeavy, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.HeavyHit")));
/*  62 */     this.field_146292_n.add(new GuiAnvilButton(1, this.field_147003_i + 87, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilHitMedium, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.MediumHit")));
/*  63 */     this.field_146292_n.add(new GuiAnvilButton(0, this.field_147003_i + 69, this.field_147009_r + 64, 16, 16, TFC_Textures.anvilHitLight, 208, 33, 16, 16, this, TFC_Core.translate("gui.Anvil.LightHit")));
/*  64 */     this.field_146292_n.add(new GuiButton(8, this.field_147003_i + 13, this.field_147009_r + 53, 36, 20, TFC_Core.translate("gui.Anvil.Weld")));
/*  65 */     this.field_146292_n.add(new GuiAnvilButton(9, this.field_147003_i + 113, this.field_147009_r + 7, 19, 21, 208, 49, 19, 21, this, 2, TFCOptions.anvilRuleColor2[0], TFCOptions.anvilRuleColor2[1], TFCOptions.anvilRuleColor2[2]));
/*  66 */     this.field_146292_n.add(new GuiAnvilButton(10, this.field_147003_i + 94, this.field_147009_r + 7, 19, 21, 208, 49, 19, 21, this, 1, TFCOptions.anvilRuleColor1[0], TFCOptions.anvilRuleColor1[1], TFCOptions.anvilRuleColor1[2]));
/*  67 */     this.field_146292_n.add(new GuiAnvilButton(11, this.field_147003_i + 75, this.field_147009_r + 7, 19, 21, 208, 49, 19, 21, this, 0, TFCOptions.anvilRuleColor0[0], TFCOptions.anvilRuleColor0[1], TFCOptions.anvilRuleColor0[2]));
/*  68 */     this.field_146292_n.add(new GuiAnvilPlanButton(12, this.field_147003_i + 122, this.field_147009_r + 45, 18, 18, this, TFC_Core.translate("gui.Anvil.Plans")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/*  74 */     super.func_73876_c();
/*  75 */     if (this.anvilTE != null) {
/*     */       
/*  77 */       String craftingPlan = this.anvilTE.craftingPlan;
/*  78 */       ItemStack stack1 = this.anvilTE.anvilItemStacks[1];
/*  79 */       ItemStack stack2 = this.anvilTE.anvilItemStacks[5];
/*     */       
/*  81 */       if ((craftingPlan != null && craftingPlan != this.plan) || (stack1 != null && stack1 != this.input) || (stack2 != null && stack2 != this.input2)) {
/*     */ 
/*     */ 
/*     */         
/*  85 */         this.plan = this.anvilTE.craftingPlan;
/*  86 */         this.anvilTE.updateRecipe();
/*  87 */         this.input = this.anvilTE.anvilItemStacks[1];
/*  88 */         this.input2 = this.anvilTE.anvilItemStacks[5];
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton guibutton) {
/*  96 */     if (guibutton.field_146127_k == 0) {
/*  97 */       this.anvilTE.actionLightHammer();
/*  98 */     } else if (guibutton.field_146127_k == 2) {
/*  99 */       this.anvilTE.actionHeavyHammer();
/* 100 */     } else if (guibutton.field_146127_k == 1) {
/* 101 */       this.anvilTE.actionHammer();
/* 102 */     } else if (guibutton.field_146127_k == 3) {
/* 103 */       this.anvilTE.actionDraw();
/* 104 */     } else if (guibutton.field_146127_k == 4) {
/* 105 */       this.anvilTE.actionPunch();
/* 106 */     } else if (guibutton.field_146127_k == 5) {
/* 107 */       this.anvilTE.actionBend();
/* 108 */     } else if (guibutton.field_146127_k == 6) {
/* 109 */       this.anvilTE.actionUpset();
/* 110 */     } else if (guibutton.field_146127_k == 7) {
/* 111 */       this.anvilTE.actionShrink();
/* 112 */     } else if (guibutton.field_146127_k == 8) {
/* 113 */       this.anvilTE.actionWeld();
/* 114 */     } else if (guibutton.field_146127_k == 12 && this.anvilTE.anvilItemStacks[1] != null) {
/* 115 */       this.player.openGui(TerraFirmaCraft.instance, 24, this.player.field_70170_p, this.x, this.y, this.z);
/* 116 */     }  this.field_147002_h.func_75142_b();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float f, int i, int j) {
/* 122 */     drawGui(texture);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void drawForeground(int guiLeft, int guiTop) {
/* 128 */     if (this.anvilTE != null) {
/*     */       
/* 130 */       int i1 = this.anvilTE.getCraftingValue();
/* 131 */       func_73729_b(guiLeft + 27 + i1, guiTop + 103, 213, 10, 5, 5);
/*     */       
/* 133 */       i1 = this.anvilTE.getItemCraftingValue();
/* 134 */       func_73729_b(guiLeft + 27 + i1, guiTop + 108, 208, 10, 5, 6);
/* 135 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*     */       
/* 137 */       if (this.anvilTE.workRecipe != null) {
/*     */ 
/*     */         
/* 140 */         int s0 = (int)(this.anvilTE.workRecipe.getSkillMult(this.player) * 1000.0F);
/* 141 */         float s1 = s0 / 10.0F;
/* 142 */         this.field_146289_q.func_78276_b("Bonus: ", guiLeft + 150, guiTop + 8, 16736256);
/* 143 */         this.field_146289_q.func_78276_b("   " + s1 + "%", guiLeft + 150, guiTop + 20, 16736256);
/*     */       } 
/*     */       
/* 146 */       drawItemRulesImages(guiLeft, guiTop);
/* 147 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/* 148 */       drawRulesImages(guiLeft, guiTop);
/* 149 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTooltip(int mx, int my, String text) {
/* 156 */     List<String> list = new ArrayList<>();
/* 157 */     list.add(text);
/* 158 */     drawHoveringText(list, mx, my + 15, this.field_146289_q);
/* 159 */     RenderHelper.func_74518_a();
/* 160 */     GL11.glDisable(2896);
/* 161 */     GL11.glDisable(2929);
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawItemRulesImages(int w, int h) {
/* 166 */     if (this.anvilTE != null && this.anvilTE.itemCraftingRules != null) {
/*     */       
/* 168 */       PlanRecipe p = AnvilManager.getInstance().getPlan(this.anvilTE.craftingPlan);
/* 169 */       if (p == null)
/*     */         return; 
/* 171 */       RuleEnum[] rules = (this.anvilTE.workRecipe != null) ? p.rules : null;
/* 172 */       int[] itemRules = this.anvilTE.getItemRules();
/*     */       
/* 174 */       this.field_146297_k.func_110434_K().func_110577_a(TextureMap.field_110575_b);
/* 175 */       func_94065_a(w + 80, h + 31, getIconFromRule(itemRules[0]), 10, 10);
/* 176 */       func_94065_a(w + 99, h + 31, getIconFromRule(itemRules[1]), 10, 10);
/* 177 */       func_94065_a(w + 118, h + 31, getIconFromRule(itemRules[2]), 10, 10);
/*     */       
/* 179 */       this.field_146297_k.func_110434_K().func_110577_a(texture);
/*     */       
/* 181 */       if (rules != null && rules[0].matches(itemRules, 0))
/* 182 */         GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F); 
/* 183 */       func_73729_b(w + 77, h + 28, 210, 115, 15, 15);
/* 184 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 186 */       if (rules != null && rules[1].matches(itemRules, 1))
/* 187 */         GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F); 
/* 188 */       func_73729_b(w + 96, h + 28, 210, 115, 15, 15);
/* 189 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 191 */       if (rules != null && rules[2].matches(itemRules, 2))
/* 192 */         GL11.glColor4f(0.0F, 1.0F, 0.0F, 1.0F); 
/* 193 */       func_73729_b(w + 115, h + 28, 210, 115, 15, 15);
/* 194 */       GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawRulesImages(int w, int h) {
/* 203 */     if (this.anvilTE.workRecipe != null) {
/*     */       
/* 205 */       PlanRecipe p = AnvilManager.getInstance().getPlan(this.anvilTE.craftingPlan);
/* 206 */       if (p == null)
/*     */         return; 
/* 208 */       RuleEnum[] rules = p.rules;
/*     */ 
/*     */       
/* 211 */       TFC_Core.bindTexture(TextureMap.field_110575_b);
/* 212 */       func_94065_a(w + 80, h + 10, getIconFromRule((rules[0]).Action), 10, 10);
/* 213 */       func_94065_a(w + 99, h + 10, getIconFromRule((rules[1]).Action), 10, 10);
/* 214 */       func_94065_a(w + 118, h + 10, getIconFromRule((rules[2]).Action), 10, 10);
/*     */       
/* 216 */       TFC_Core.bindTexture(texture);
/*     */       
/* 218 */       GL11.glColor4ub(TFCOptions.anvilRuleColor0[0], TFCOptions.anvilRuleColor0[1], TFCOptions.anvilRuleColor0[2], (byte)-1);
/* 219 */       if ((rules[0]).Min == 0)
/* 220 */         func_73729_b(w + 75, h + 26, 228, 68, 19, 3); 
/* 221 */       if ((rules[0]).Max > 0 && ((rules[0]).Min <= 1 || (rules[0]).Max == 1))
/* 222 */         func_73729_b(w + 94, h + 26, 228, 68, 19, 3); 
/* 223 */       if ((rules[0]).Max > 1 && ((rules[0]).Min <= 2 || (rules[0]).Max == 2)) {
/* 224 */         func_73729_b(w + 113, h + 26, 228, 68, 19, 3);
/*     */       }
/* 226 */       GL11.glColor4ub(TFCOptions.anvilRuleColor1[0], TFCOptions.anvilRuleColor1[1], TFCOptions.anvilRuleColor1[2], (byte)-1);
/* 227 */       if ((rules[1]).Min == 0)
/* 228 */         func_73729_b(w + 75, h + 24, 228, 68, 19, 3); 
/* 229 */       if ((rules[1]).Max > 0 && ((rules[1]).Min <= 1 || (rules[1]).Max == 1))
/* 230 */         func_73729_b(w + 94, h + 24, 228, 68, 19, 3); 
/* 231 */       if ((rules[1]).Max > 1 && ((rules[1]).Min <= 2 || (rules[1]).Max == 2)) {
/* 232 */         func_73729_b(w + 113, h + 24, 228, 68, 19, 3);
/*     */       }
/* 234 */       GL11.glColor4ub(TFCOptions.anvilRuleColor2[0], TFCOptions.anvilRuleColor2[1], TFCOptions.anvilRuleColor2[2], (byte)-1);
/* 235 */       if ((rules[2]).Min == 0)
/* 236 */         func_73729_b(w + 75, h + 22, 228, 68, 19, 3); 
/* 237 */       if ((rules[2]).Max > 0 && ((rules[2]).Min <= 1 || (rules[2]).Max == 1))
/* 238 */         func_73729_b(w + 94, h + 22, 228, 68, 19, 3); 
/* 239 */       if ((rules[2]).Max > 1 && ((rules[2]).Min <= 2 || (rules[2]).Max == 2)) {
/* 240 */         func_73729_b(w + 113, h + 22, 228, 68, 19, 3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIconFromRule(int action) {
/* 249 */     switch (action) {
/*     */       
/*     */       case 0:
/* 252 */         return TFC_Textures.anvilHit;
/*     */       case 1:
/* 254 */         return TFC_Textures.anvilDraw;
/*     */       case 3:
/* 256 */         return TFC_Textures.anvilPunch;
/*     */       case 4:
/* 258 */         return TFC_Textures.anvilBend;
/*     */       case 5:
/* 260 */         return TFC_Textures.anvilUpset;
/*     */       case 6:
/* 262 */         return TFC_Textures.anvilShrink;
/*     */     } 
/* 264 */     return TFC_Textures.invisibleTexture;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_146978_c(int slotX, int slotY, int sizeX, int sizeY, int clickX, int clickY) {
/* 271 */     int k1 = this.field_147003_i;
/* 272 */     int l1 = this.field_147009_r;
/* 273 */     clickX -= k1;
/* 274 */     clickY -= l1;
/* 275 */     return (clickX >= slotX - 1 && clickX < slotX + sizeX + 1 && clickY >= slotY - 1 && clickY < slotY + sizeY + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawTexturedModalRect(int drawX, int drawY, int drawWidth, int drawHeight, int u, int v, int width, int height) {
/* 283 */     float f = 0.00390625F;
/* 284 */     float f1 = 0.00390625F;
/* 285 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 286 */     tessellator.func_78382_b();
/* 287 */     tessellator.func_78374_a((drawX + 0), (drawY + drawHeight), this.field_73735_i, ((u + 0) * f), ((v + height) * f1));
/* 288 */     tessellator.func_78374_a((drawX + drawWidth), (drawY + drawHeight), this.field_73735_i, ((u + width) * f), ((v + height) * f1));
/* 289 */     tessellator.func_78374_a((drawX + drawWidth), (drawY + 0), this.field_73735_i, ((u + width) * f), ((v + 0) * f1));
/* 290 */     tessellator.func_78374_a((drawX + 0), (drawY + 0), this.field_73735_i, ((u + 0) * f), ((v + 0) * f1));
/* 291 */     tessellator.func_78381_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94065_a(int x, int y, IIcon par3Icon, int width, int height) {
/* 297 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 298 */     tessellator.func_78382_b();
/* 299 */     tessellator.func_78374_a((x + 0), (y + height), this.field_73735_i, par3Icon.func_94209_e(), par3Icon.func_94210_h());
/* 300 */     tessellator.func_78374_a((x + width), (y + height), this.field_73735_i, par3Icon.func_94212_f(), par3Icon.func_94210_h());
/* 301 */     tessellator.func_78374_a((x + width), (y + 0), this.field_73735_i, par3Icon.func_94212_f(), par3Icon.func_94206_g());
/* 302 */     tessellator.func_78374_a((x + 0), (y + 0), this.field_73735_i, par3Icon.func_94209_e(), par3Icon.func_94206_g());
/* 303 */     tessellator.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */