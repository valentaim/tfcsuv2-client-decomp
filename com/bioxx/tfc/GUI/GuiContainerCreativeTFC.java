/*     */ package com.bioxx.tfc.GUI;
/*     */ 
/*     */ import com.bioxx.tfc.Containers.ContainerCreativeTFC;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.client.gui.GuiButton;
/*     */ import net.minecraft.client.gui.GuiScreen;
/*     */ import net.minecraft.client.gui.GuiTextField;
/*     */ import net.minecraft.client.gui.achievement.GuiAchievements;
/*     */ import net.minecraft.client.gui.achievement.GuiStats;
/*     */ import net.minecraft.client.gui.inventory.CreativeCrafting;
/*     */ import net.minecraft.client.renderer.InventoryEffectRenderer;
/*     */ import net.minecraft.client.renderer.RenderHelper;
/*     */ import net.minecraft.client.resources.I18n;
/*     */ import net.minecraft.client.settings.GameSettings;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.InventoryPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.ICrafting;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryBasic;
/*     */ import net.minecraft.inventory.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.input.Mouse;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public class GuiContainerCreativeTFC extends InventoryEffectRenderer {
/*  44 */   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
/*  45 */   private static InventoryBasic inventory = new InventoryBasic("tmp", true, 51);
/*     */ 
/*     */   
/*  48 */   private static int selectedTabIndex = CreativeTabs.field_78030_b.func_78021_a();
/*     */ 
/*     */   
/*     */   private float currentScroll;
/*     */ 
/*     */   
/*     */   private boolean isScrolling;
/*     */ 
/*     */   
/*     */   private boolean wasClicking;
/*     */   
/*     */   private GuiTextField searchField;
/*     */   
/*     */   private List<Slot> backupContainerSlots;
/*     */   
/*     */   private Slot zeroSlot;
/*     */   
/*     */   private boolean eventTriggered;
/*     */   
/*     */   private CreativeCrafting crafting;
/*     */   
/*     */   private static int tabPage;
/*     */   
/*     */   private int maxPages;
/*     */ 
/*     */   
/*     */   public GuiContainerCreativeTFC(EntityPlayer par1EntityPlayer) {
/*  75 */     super((Container)new ContainerCreativeTFC(par1EntityPlayer));
/*     */     
/*  77 */     par1EntityPlayer.field_71070_bA = this.field_147002_h;
/*  78 */     this.field_146291_p = true;
/*  79 */     par1EntityPlayer.func_71064_a((StatBase)AchievementList.field_76004_f, 1);
/*  80 */     this.field_147000_g = 136;
/*  81 */     this.field_146999_f = 195;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73876_c() {
/*  90 */     if (!this.field_146297_k.field_71442_b.func_78758_h()) {
/*  91 */       this.field_146297_k.func_147108_a((GuiScreen)new GuiInventoryTFC((EntityPlayer)this.field_146297_k.field_71439_g));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_146984_a(Slot par1Slot, int par2, int par3, int par4) {
/*  97 */     this.eventTriggered = true;
/*  98 */     boolean flag = (par4 == 1);
/*  99 */     par4 = (par2 == -999 && par4 == 0) ? 4 : par4;
/*     */ 
/*     */ 
/*     */     
/* 103 */     if (par1Slot == null && selectedTabIndex != CreativeTabs.field_78036_m.func_78021_a() && par4 != 5) {
/*     */       
/* 105 */       InventoryPlayer inventoryplayer = this.field_146297_k.field_71439_g.field_71071_by;
/*     */       
/* 107 */       if (inventoryplayer.func_70445_o() != null)
/*     */       {
/* 109 */         if (par3 == 0) {
/*     */           
/* 111 */           this.field_146297_k.field_71439_g.func_70099_a(inventoryplayer.func_70445_o(), 1.5F);
/* 112 */           this.field_146297_k.field_71442_b.func_78752_a(inventoryplayer.func_70445_o());
/* 113 */           inventoryplayer.func_70437_b((ItemStack)null);
/*     */         } 
/*     */         
/* 116 */         if (par3 == 1)
/*     */         {
/* 118 */           ItemStack itemstack = inventoryplayer.func_70445_o().func_77979_a(1);
/* 119 */           this.field_146297_k.field_71439_g.func_70099_a(itemstack, 1.5F);
/* 120 */           this.field_146297_k.field_71442_b.func_78752_a(itemstack);
/*     */           
/* 122 */           if ((inventoryplayer.func_70445_o()).field_77994_a == 0) {
/* 123 */             inventoryplayer.func_70437_b((ItemStack)null);
/*     */           
/*     */           }
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 131 */     else if (par1Slot == this.zeroSlot && flag) {
/*     */       
/* 133 */       for (int l = 0; l < this.field_146297_k.field_71439_g.field_71069_bz.func_75138_a().size(); l++)
/*     */       {
/* 135 */         this.field_146297_k.field_71442_b.func_78761_a((ItemStack)null, l);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 142 */     else if (selectedTabIndex == CreativeTabs.field_78036_m.func_78021_a()) {
/*     */       
/* 144 */       if (par1Slot == this.zeroSlot)
/*     */       {
/* 146 */         this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
/*     */       }
/* 148 */       else if (par4 == 4 && par1Slot != null && par1Slot.func_75216_d())
/*     */       {
/* 150 */         ItemStack itemstack1 = par1Slot.func_75209_a((par3 == 0) ? 1 : par1Slot.func_75211_c().func_77976_d());
/* 151 */         this.field_146297_k.field_71439_g.func_70099_a(itemstack1, 1.5F);
/* 152 */         this.field_146297_k.field_71442_b.func_78752_a(itemstack1);
/*     */       }
/* 154 */       else if (par4 == 4 && this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null)
/*     */       {
/* 156 */         this.field_146297_k.field_71439_g.func_70099_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o(), 1.5F);
/* 157 */         this.field_146297_k.field_71442_b.func_78752_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o());
/* 158 */         this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
/*     */       }
/*     */       else
/*     */       {
/* 162 */         this.field_146297_k.field_71439_g.field_71069_bz.func_75144_a((par1Slot == null) ? par2 : (SlotCreativeInventoryTFC.getSlot((SlotCreativeInventoryTFC)par1Slot)).field_75222_d, par3, par4, (EntityPlayer)this.field_146297_k.field_71439_g);
/* 163 */         this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
/*     */       }
/*     */     
/* 166 */     } else if (par4 != 5 && par1Slot != null && par1Slot.field_75224_c == inventory) {
/*     */       
/* 168 */       InventoryPlayer inventoryplayer = this.field_146297_k.field_71439_g.field_71071_by;
/* 169 */       ItemStack itemstack = inventoryplayer.func_70445_o();
/* 170 */       ItemStack itemstack2 = par1Slot.func_75211_c();
/*     */ 
/*     */       
/* 173 */       if (par4 == 2) {
/*     */         
/* 175 */         if (itemstack2 != null && par3 >= 0 && par3 < 9) {
/*     */           
/* 177 */           ItemStack itemstack3 = itemstack2.func_77946_l();
/* 178 */           itemstack3.field_77994_a = itemstack3.func_77976_d();
/* 179 */           this.field_146297_k.field_71439_g.field_71071_by.func_70299_a(par3, itemstack3);
/* 180 */           this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 186 */       if (par4 == 3) {
/*     */         
/* 188 */         if (inventoryplayer.func_70445_o() == null && par1Slot.func_75216_d()) {
/*     */           
/* 190 */           ItemStack itemstack3 = par1Slot.func_75211_c().func_77946_l();
/* 191 */           itemstack3.field_77994_a = itemstack3.func_77976_d();
/* 192 */           inventoryplayer.func_70437_b(itemstack3);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 198 */       if (par4 == 4) {
/*     */         
/* 200 */         if (itemstack2 != null) {
/*     */           
/* 202 */           ItemStack itemstack3 = itemstack2.func_77946_l();
/* 203 */           itemstack3.field_77994_a = (par3 == 0) ? 1 : itemstack3.func_77976_d();
/* 204 */           this.field_146297_k.field_71439_g.func_70099_a(itemstack3, 1.5F);
/* 205 */           this.field_146297_k.field_71442_b.func_78752_a(itemstack3);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 211 */       if (itemstack != null && itemstack2 != null && itemstack.func_77969_a(itemstack2) && ItemStack.func_77970_a(itemstack, itemstack2)) {
/*     */         
/* 213 */         if (par3 == 0) {
/*     */           
/* 215 */           if (flag) {
/* 216 */             itemstack.field_77994_a = itemstack.func_77976_d();
/* 217 */           } else if (itemstack.field_77994_a < itemstack.func_77976_d()) {
/* 218 */             itemstack.field_77994_a++;
/*     */           } 
/* 220 */         } else if (itemstack.field_77994_a <= 1) {
/*     */           
/* 222 */           inventoryplayer.func_70437_b((ItemStack)null);
/*     */         }
/*     */         else {
/*     */           
/* 226 */           itemstack.field_77994_a--;
/*     */         }
/*     */       
/* 229 */       } else if (itemstack2 != null && itemstack == null) {
/*     */         
/* 231 */         inventoryplayer.func_70437_b(ItemStack.func_77944_b(itemstack2));
/* 232 */         itemstack = inventoryplayer.func_70445_o();
/*     */         
/* 234 */         if (flag) {
/* 235 */           itemstack.field_77994_a = itemstack.func_77976_d();
/*     */         }
/*     */       } else {
/*     */         
/* 239 */         inventoryplayer.func_70437_b((ItemStack)null);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 244 */       this.field_147002_h.func_75144_a((par1Slot == null) ? par2 : par1Slot.field_75222_d, par3, par4, (EntityPlayer)this.field_146297_k.field_71439_g);
/*     */       
/* 246 */       if (Container.func_94532_c(par3) == 2) {
/*     */         
/* 248 */         for (int l = 0; l < 9; l++) {
/* 249 */           this.field_146297_k.field_71442_b.func_78761_a(this.field_147002_h.func_75139_a(45 + l).func_75211_c(), 36 + l);
/*     */         }
/* 251 */       } else if (par1Slot != null) {
/*     */         
/* 253 */         ItemStack itemstack1 = this.field_147002_h.func_75139_a(par1Slot.field_75222_d).func_75211_c();
/* 254 */         this.field_146297_k.field_71442_b.func_78761_a(itemstack1, par1Slot.field_75222_d - this.field_147002_h.field_75151_b.size() + 9 + 36);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73866_w_() {
/* 267 */     if (this.field_146297_k.field_71442_b.func_78758_h()) {
/*     */       
/* 269 */       super.func_73866_w_();
/* 270 */       this.field_146292_n.clear();
/* 271 */       Keyboard.enableRepeatEvents(true);
/* 272 */       this.searchField = new GuiTextField(this.field_146289_q, this.field_147003_i + 82, this.field_147009_r + 6, 89, this.field_146289_q.field_78288_b);
/* 273 */       this.searchField.func_146203_f(15);
/* 274 */       this.searchField.func_146185_a(false);
/* 275 */       this.searchField.func_146189_e(false);
/* 276 */       this.searchField.func_146193_g(16777215);
/* 277 */       int i = selectedTabIndex;
/* 278 */       selectedTabIndex = -1;
/* 279 */       setCurrentCreativeTab(CreativeTabs.field_78032_a[i]);
/* 280 */       this.crafting = new CreativeCrafting(this.field_146297_k);
/* 281 */       this.field_146297_k.field_71439_g.field_71069_bz.func_75132_a((ICrafting)this.crafting);
/* 282 */       int tabCount = CreativeTabs.field_78032_a.length;
/* 283 */       if (tabCount > 12)
/*     */       {
/* 285 */         this.field_146292_n.add(new GuiButton(101, this.field_147003_i, this.field_147009_r - 50, 20, 20, "<"));
/* 286 */         this.field_146292_n.add(new GuiButton(102, this.field_147003_i + this.field_146999_f - 20, this.field_147009_r - 50, 20, 20, ">"));
/* 287 */         this.maxPages = (tabCount - 12) / 10 + 1;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 292 */       this.field_146297_k.func_147108_a((GuiScreen)new GuiInventoryTFC((EntityPlayer)this.field_146297_k.field_71439_g));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146281_b() {
/* 302 */     super.func_146281_b();
/*     */     
/* 304 */     if (this.field_146297_k.field_71439_g != null && this.field_146297_k.field_71439_g.field_71071_by != null) {
/* 305 */       this.field_146297_k.field_71439_g.field_71069_bz.func_82847_b((ICrafting)this.crafting);
/*     */     }
/* 307 */     Keyboard.enableRepeatEvents(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_73869_a(char par1, int par2) {
/* 316 */     if (!CreativeTabs.field_78032_a[selectedTabIndex].hasSearchBar()) {
/*     */       
/* 318 */       if (GameSettings.func_100015_a(this.field_146297_k.field_71474_y.field_74310_D)) {
/* 319 */         setCurrentCreativeTab(CreativeTabs.field_78027_g);
/*     */       } else {
/* 321 */         super.func_73869_a(par1, par2);
/*     */       } 
/*     */     } else {
/*     */       
/* 325 */       if (this.eventTriggered) {
/*     */         
/* 327 */         this.eventTriggered = false;
/* 328 */         this.searchField.func_146180_a("");
/*     */       } 
/*     */       
/* 331 */       if (!func_146983_a(par2))
/*     */       {
/* 333 */         if (this.searchField.func_146201_a(par1, par2)) {
/* 334 */           updateCreativeSearch();
/*     */         } else {
/* 336 */           super.func_73869_a(par1, par2);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void updateCreativeSearch() {
/* 343 */     ContainerCreativeTFC containercreative = (ContainerCreativeTFC)this.field_147002_h;
/* 344 */     containercreative.itemList.clear();
/*     */     
/* 346 */     CreativeTabs tab = CreativeTabs.field_78032_a[selectedTabIndex];
/* 347 */     if (tab.hasSearchBar() && tab != CreativeTabs.field_78027_g) {
/*     */       
/* 349 */       tab.func_78018_a(containercreative.itemList);
/* 350 */       updateFilteredItems(containercreative);
/*     */       
/*     */       return;
/*     */     } 
/* 354 */     Iterator<Item> iItems = Item.field_150901_e.iterator();
/* 355 */     while (iItems.hasNext()) {
/*     */       
/* 357 */       Item item = iItems.next();
/* 358 */       if (item != null && item.func_77640_w() != null) {
/* 359 */         item.func_150895_a(item, (CreativeTabs)null, containercreative.itemList);
/*     */       }
/*     */     } 
/* 362 */     Enchantment[] aenchantment = Enchantment.field_77331_b;
/* 363 */     int i = aenchantment.length;
/* 364 */     for (int j = 0; j < i; j++) {
/*     */       
/* 366 */       Enchantment enchantment = aenchantment[j];
/* 367 */       if (enchantment != null && enchantment.field_77351_y != null)
/* 368 */         Items.field_151134_bR.func_92113_a(enchantment, containercreative.itemList); 
/*     */     } 
/* 370 */     updateFilteredItems(containercreative);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateFilteredItems(ContainerCreativeTFC containercreative) {
/* 376 */     Iterator<ItemStack> iterator = containercreative.itemList.iterator();
/* 377 */     String s = this.searchField.func_146179_b().toLowerCase();
/*     */     
/* 379 */     while (iterator.hasNext()) {
/*     */       
/* 381 */       ItemStack itemstack = iterator.next();
/* 382 */       boolean flag = false;
/* 383 */       Iterator<String> iterator1 = itemstack.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x).iterator();
/* 384 */       boolean loop = true;
/*     */       
/* 386 */       while (loop) {
/*     */         
/* 388 */         if (iterator1.hasNext()) {
/*     */           
/* 390 */           String s1 = iterator1.next();
/* 391 */           if (!s1.toLowerCase().contains(s))
/*     */             continue; 
/* 393 */           flag = true;
/*     */         } 
/*     */         
/* 396 */         if (!flag)
/* 397 */           iterator.remove(); 
/* 398 */         loop = false;
/*     */       } 
/*     */     } 
/*     */     
/* 402 */     this.currentScroll = 0.0F;
/* 403 */     containercreative.scrollTo(0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146979_b(int par1, int par2) {
/* 412 */     CreativeTabs creativetabs = CreativeTabs.field_78032_a[selectedTabIndex];
/* 413 */     if (creativetabs != null && creativetabs.func_78019_g()) {
/* 414 */       this.field_146289_q.func_78276_b(I18n.func_135052_a(creativetabs.func_78024_c(), new Object[0]), 8, 6, 4210752);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_73864_a(int par1, int par2, int par3) {
/* 423 */     if (par3 == 0) {
/*     */       
/* 425 */       int l = par1 - this.field_147003_i;
/* 426 */       int i1 = par2 - this.field_147009_r;
/* 427 */       CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
/* 428 */       int j1 = acreativetabs.length;
/*     */       
/* 430 */       for (int k1 = 0; k1 < j1; k1++) {
/*     */         
/* 432 */         CreativeTabs creativetabs = acreativetabs[k1];
/* 433 */         if (switchTab(creativetabs, l, i1))
/*     */           return; 
/*     */       } 
/*     */     } 
/* 437 */     super.func_73864_a(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146286_b(int par1, int par2, int par3) {
/* 447 */     if (par3 == 0) {
/*     */       
/* 449 */       int l = par1 - this.field_147003_i;
/* 450 */       int i1 = par2 - this.field_147009_r;
/* 451 */       CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
/* 452 */       int j1 = acreativetabs.length;
/*     */       
/* 454 */       for (int k1 = 0; k1 < j1; k1++) {
/*     */         
/* 456 */         CreativeTabs creativetabs = acreativetabs[k1];
/* 457 */         if (creativetabs != null && switchTab(creativetabs, l, i1)) {
/*     */           
/* 459 */           setCurrentCreativeTab(creativetabs);
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 465 */     super.func_146286_b(par1, par2, par3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean needsScrollBars() {
/* 473 */     if (CreativeTabs.field_78032_a[selectedTabIndex] == null) return false; 
/* 474 */     return (selectedTabIndex != CreativeTabs.field_78036_m.func_78021_a() && CreativeTabs.field_78032_a[selectedTabIndex].func_78017_i() && ((ContainerCreativeTFC)this.field_147002_h).hasMoreThan1PageOfItemsInList());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setCurrentCreativeTab(CreativeTabs par1CreativeTabs) {
/* 479 */     if (par1CreativeTabs == null) {
/*     */       return;
/*     */     }
/* 482 */     int i = selectedTabIndex;
/* 483 */     selectedTabIndex = par1CreativeTabs.func_78021_a();
/* 484 */     ContainerCreativeTFC containercreative = (ContainerCreativeTFC)this.field_147002_h;
/* 485 */     this.field_147008_s.clear();
/* 486 */     containercreative.itemList.clear();
/* 487 */     par1CreativeTabs.func_78018_a(containercreative.itemList);
/*     */     
/* 489 */     if (par1CreativeTabs == CreativeTabs.field_78036_m) {
/*     */       
/* 491 */       Container container = this.field_146297_k.field_71439_g.field_71069_bz;
/* 492 */       if (this.backupContainerSlots == null) {
/* 493 */         this.backupContainerSlots = containercreative.field_75151_b;
/*     */       }
/* 495 */       containercreative.field_75151_b = new ArrayList();
/* 496 */       for (int j = 0; j < container.field_75151_b.size(); j++) {
/*     */         
/* 498 */         SlotCreativeInventoryTFC slotcreativeinventory = new SlotCreativeInventoryTFC(this, container.field_75151_b.get(j), j);
/* 499 */         containercreative.field_75151_b.add(slotcreativeinventory);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 504 */         if (j >= 5 && j < 9) {
/*     */           
/* 506 */           int k = j - 5;
/* 507 */           int l = k / 2;
/* 508 */           int i1 = k % 2;
/* 509 */           slotcreativeinventory.field_75223_e = 9 + l * 54;
/* 510 */           slotcreativeinventory.field_75221_f = 6 + i1 * 27;
/*     */         }
/* 512 */         else if (j == 50) {
/*     */           
/* 514 */           int k = 2;
/* 515 */           int l = k / 2;
/* 516 */           int i1 = k % 2;
/* 517 */           slotcreativeinventory.field_75223_e = 27 + l * 54;
/* 518 */           slotcreativeinventory.field_75221_f = 6 + i1 * 27;
/*     */         }
/* 520 */         else if (j >= 0 && j < 5) {
/*     */           
/* 522 */           slotcreativeinventory.field_75221_f = -2000;
/* 523 */           slotcreativeinventory.field_75223_e = -2000;
/*     */         }
/* 525 */         else if (j < container.field_75151_b.size()) {
/*     */           
/* 527 */           int k = j - 9;
/* 528 */           int l = k % 9;
/* 529 */           int i1 = k / 9;
/* 530 */           slotcreativeinventory.field_75223_e = 9 + l * 18;
/*     */           
/* 532 */           if (j >= 36) {
/* 533 */             slotcreativeinventory.field_75221_f = 112;
/*     */           } else {
/* 535 */             slotcreativeinventory.field_75221_f = 54 + i1 * 18;
/*     */           } 
/*     */         } 
/*     */       } 
/* 539 */       this.zeroSlot = new Slot((IInventory)inventory, 0, 173, 112);
/* 540 */       containercreative.field_75151_b.add(this.zeroSlot);
/*     */     }
/* 542 */     else if (i == CreativeTabs.field_78036_m.func_78021_a()) {
/*     */       
/* 544 */       containercreative.field_75151_b = this.backupContainerSlots;
/* 545 */       this.backupContainerSlots = null;
/*     */     } 
/*     */     
/* 548 */     if (this.searchField != null)
/*     */     {
/* 550 */       if (par1CreativeTabs.hasSearchBar()) {
/*     */         
/* 552 */         this.searchField.func_146189_e(true);
/* 553 */         this.searchField.func_146205_d(false);
/* 554 */         this.searchField.func_146195_b(true);
/* 555 */         this.searchField.func_146180_a("");
/* 556 */         updateCreativeSearch();
/*     */       }
/*     */       else {
/*     */         
/* 560 */         this.searchField.func_146189_e(false);
/* 561 */         this.searchField.func_146205_d(true);
/* 562 */         this.searchField.func_146195_b(false);
/*     */       } 
/*     */     }
/*     */     
/* 566 */     this.currentScroll = 0.0F;
/* 567 */     containercreative.scrollTo(0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146274_d() {
/* 576 */     super.func_146274_d();
/* 577 */     int i = Mouse.getEventDWheel();
/*     */     
/* 579 */     if (i != 0 && needsScrollBars()) {
/*     */       
/* 581 */       int j = ((ContainerCreativeTFC)this.field_147002_h).itemList.size() / 9 - 5 + 1;
/*     */       
/* 583 */       if (i > 0)
/* 584 */         i = 1; 
/* 585 */       if (i < 0) {
/* 586 */         i = -1;
/*     */       }
/* 588 */       this.currentScroll = (float)(this.currentScroll - i / j);
/*     */       
/* 590 */       if (this.currentScroll < 0.0F) {
/* 591 */         this.currentScroll = 0.0F;
/*     */       }
/* 593 */       if (this.currentScroll > 1.0F) {
/* 594 */         this.currentScroll = 1.0F;
/*     */       }
/* 596 */       ((ContainerCreativeTFC)this.field_147002_h).scrollTo(this.currentScroll);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_73863_a(int par1, int par2, float par3) {
/* 606 */     boolean flag = Mouse.isButtonDown(0);
/* 607 */     int k = this.field_147003_i;
/* 608 */     int l = this.field_147009_r;
/* 609 */     int i1 = k + 175;
/* 610 */     int j1 = l + 18;
/* 611 */     int k1 = i1 + 14;
/* 612 */     int l1 = j1 + 112;
/*     */     
/* 614 */     if (!this.wasClicking && flag && par1 >= i1 && par2 >= j1 && par1 < k1 && par2 < l1) {
/* 615 */       this.isScrolling = needsScrollBars();
/*     */     }
/* 617 */     if (!flag) {
/* 618 */       this.isScrolling = false;
/*     */     }
/* 620 */     this.wasClicking = flag;
/*     */     
/* 622 */     if (this.isScrolling) {
/*     */       
/* 624 */       this.currentScroll = ((par2 - j1) - 7.5F) / ((l1 - j1) - 15.0F);
/*     */       
/* 626 */       if (this.currentScroll < 0.0F) {
/* 627 */         this.currentScroll = 0.0F;
/*     */       }
/* 629 */       if (this.currentScroll > 1.0F) {
/* 630 */         this.currentScroll = 1.0F;
/*     */       }
/* 632 */       ((ContainerCreativeTFC)this.field_147002_h).scrollTo(this.currentScroll);
/*     */     } 
/*     */     
/* 635 */     super.func_73863_a(par1, par2, par3);
/* 636 */     CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
/* 637 */     int start = tabPage * 10;
/* 638 */     int i2 = Math.min(acreativetabs.length, (tabPage + 1) * 10 + 2);
/* 639 */     if (tabPage != 0) start += 2; 
/* 640 */     boolean rendered = false;
/*     */     
/* 642 */     for (int j2 = start; j2 < i2; j2++) {
/*     */       
/* 644 */       CreativeTabs creativetabs = acreativetabs[j2];
/*     */       
/* 646 */       if (creativetabs != null && renderCreativeInventoryHoveringText(creativetabs, par1, par2)) {
/*     */         
/* 648 */         rendered = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 653 */     if (!rendered && !renderCreativeInventoryHoveringText(CreativeTabs.field_78027_g, par1, par2)) {
/* 654 */       renderCreativeInventoryHoveringText(CreativeTabs.field_78036_m, par1, par2);
/*     */     }
/* 656 */     if (this.zeroSlot != null && selectedTabIndex == CreativeTabs.field_78036_m.func_78021_a() && 
/* 657 */       func_146978_c(this.zeroSlot.field_75223_e, this.zeroSlot.field_75221_f, 16, 16, par1, par2))
/*     */     {
/* 659 */       func_146279_a(I18n.func_135052_a("inventory.binSlot", new Object[0]), par1, par2);
/*     */     }
/*     */     
/* 662 */     if (this.maxPages != 0) {
/*     */       
/* 664 */       String page = String.format("%d / %d", new Object[] { Integer.valueOf(tabPage + 1), Integer.valueOf(this.maxPages + 1) });
/* 665 */       int width = this.field_146289_q.func_78256_a(page);
/* 666 */       GL11.glDisable(2896);
/* 667 */       this.field_73735_i = 300.0F;
/* 668 */       field_146296_j.field_77023_b = 300.0F;
/* 669 */       this.field_146289_q.func_78276_b(page, this.field_147003_i + this.field_146999_f / 2 - width / 2, this.field_147009_r - 44, -1);
/* 670 */       this.field_73735_i = 0.0F;
/* 671 */       field_146296_j.field_77023_b = 0.0F;
/*     */     } 
/*     */     
/* 674 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 675 */     GL11.glDisable(2896);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146285_a(ItemStack par1ItemStack, int par2, int par3) {
/* 681 */     if (selectedTabIndex == CreativeTabs.field_78027_g.func_78021_a()) {
/*     */       
/* 683 */       List<String> list = par1ItemStack.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
/* 684 */       CreativeTabs creativetabs = par1ItemStack.func_77973_b().func_77640_w();
/*     */       
/* 686 */       if (creativetabs == null && par1ItemStack.func_77973_b() == Items.field_151134_bR) {
/*     */         
/* 688 */         Map map = EnchantmentHelper.func_82781_a(par1ItemStack);
/*     */         
/* 690 */         if (map.size() == 1) {
/*     */           
/* 692 */           Enchantment enchantment = Enchantment.field_77331_b[((Integer)map.keySet().iterator().next()).intValue()];
/* 693 */           CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
/* 694 */           int k = acreativetabs.length;
/*     */           
/* 696 */           for (int l = 0; l < k; l++) {
/*     */             
/* 698 */             CreativeTabs creativetabs1 = acreativetabs[l];
/* 699 */             if (creativetabs1.func_111226_a(enchantment.field_77351_y)) {
/*     */               
/* 701 */               creativetabs = creativetabs1;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 708 */       if (creativetabs != null) {
/* 709 */         list.add(1, EnumChatFormatting.BOLD.toString() + EnumChatFormatting.BLUE + I18n.func_135052_a(creativetabs.func_78024_c(), new Object[0]));
/*     */       }
/* 711 */       for (int i1 = 0; i1 < list.size(); i1++) {
/*     */         
/* 713 */         if (i1 == 0) {
/* 714 */           list.set(i1, (par1ItemStack.func_77953_t()).field_77937_e + (String)list.get(i1));
/*     */         } else {
/* 716 */           list.set(i1, EnumChatFormatting.GRAY + (String)list.get(i1));
/*     */         } 
/*     */       } 
/* 719 */       func_146283_a(list, par2, par3);
/*     */     }
/*     */     else {
/*     */       
/* 723 */       super.func_146285_a(par1ItemStack, par2, par3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146976_a(float par1, int par2, int par3) {
/* 733 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 734 */     RenderHelper.func_74520_c();
/* 735 */     CreativeTabs creativetabs = CreativeTabs.field_78032_a[selectedTabIndex];
/* 736 */     CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
/* 737 */     int k = acreativetabs.length;
/*     */ 
/*     */     
/* 740 */     int start = tabPage * 10;
/* 741 */     k = Math.min(acreativetabs.length, (tabPage + 1) * 10 + 2);
/* 742 */     if (tabPage != 0) start += 2; 
/* 743 */     GL11.glEnable(3008); int l;
/* 744 */     for (l = start; l < k; l++) {
/*     */       
/* 746 */       CreativeTabs creativetabs1 = acreativetabs[l];
/* 747 */       this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
/* 748 */       if (creativetabs1 != null && creativetabs1.func_78021_a() != selectedTabIndex) {
/* 749 */         renderCreativeTab(creativetabs1);
/*     */       }
/*     */     } 
/* 752 */     if (tabPage != 0) {
/*     */       
/* 754 */       if (creativetabs != CreativeTabs.field_78027_g) {
/*     */         
/* 756 */         this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
/* 757 */         renderCreativeTab(CreativeTabs.field_78027_g);
/*     */       } 
/* 759 */       if (creativetabs != CreativeTabs.field_78036_m) {
/*     */         
/* 761 */         this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
/* 762 */         renderCreativeTab(CreativeTabs.field_78036_m);
/*     */       } 
/*     */     } 
/* 765 */     this.field_146297_k.func_110434_K().func_110577_a(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + creativetabs.func_78015_f()));
/* 766 */     func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
/* 767 */     this.searchField.func_146194_f();
/* 768 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 769 */     int i1 = this.field_147003_i + 175;
/* 770 */     k = this.field_147009_r + 18;
/* 771 */     l = k + 112;
/* 772 */     this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
/*     */     
/* 774 */     if (creativetabs.func_78017_i()) {
/* 775 */       func_73729_b(i1, k + (int)((l - k - 17) * this.currentScroll), 232 + (needsScrollBars() ? 0 : 12), 0, 12, 15);
/*     */     }
/* 777 */     if (creativetabs.getTabPage() != tabPage && 
/* 778 */       creativetabs != CreativeTabs.field_78027_g && creativetabs != CreativeTabs.field_78036_m) {
/*     */       return;
/*     */     }
/* 781 */     renderCreativeTab(creativetabs);
/*     */     
/* 783 */     if (creativetabs == CreativeTabs.field_78036_m)
/* 784 */       GuiInventoryTFC.drawPlayerModel(this.field_147003_i + 43, this.field_147009_r + 45, 20, (this.field_147003_i + 43 - par2), (this.field_147009_r + 45 - 30 - par3), (EntityLivingBase)this.field_146297_k.field_71439_g); 
/*     */   }
/*     */   
/*     */   protected boolean switchTab(CreativeTabs par1CreativeTabs, int par2, int par3) {
/*     */     int i1;
/* 789 */     if (par1CreativeTabs.getTabPage() != tabPage && 
/* 790 */       par1CreativeTabs != CreativeTabs.field_78027_g && par1CreativeTabs != CreativeTabs.field_78036_m) {
/* 791 */       return false;
/*     */     }
/* 793 */     int k = par1CreativeTabs.func_78020_k();
/* 794 */     int l = 28 * k;
/* 795 */     byte b0 = 0;
/*     */     
/* 797 */     if (k == 5) {
/* 798 */       l = this.field_146999_f - 28 + 2;
/* 799 */     } else if (k > 0) {
/* 800 */       l += k;
/*     */     } 
/*     */ 
/*     */     
/* 804 */     if (par1CreativeTabs.func_78023_l()) {
/* 805 */       i1 = b0 - 32;
/*     */     } else {
/* 807 */       i1 = b0 + this.field_147000_g;
/*     */     } 
/* 809 */     return (par2 >= l && par2 <= l + 28 && par3 >= i1 && par3 <= i1 + 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean renderCreativeInventoryHoveringText(CreativeTabs par1CreativeTabs, int par2, int par3) {
/* 818 */     int i1, k = par1CreativeTabs.func_78020_k();
/* 819 */     int l = 28 * k;
/* 820 */     byte b0 = 0;
/*     */     
/* 822 */     if (k == 5) {
/* 823 */       l = this.field_146999_f - 28 + 2;
/* 824 */     } else if (k > 0) {
/* 825 */       l += k;
/*     */     } 
/*     */ 
/*     */     
/* 829 */     if (par1CreativeTabs.func_78023_l()) {
/* 830 */       i1 = b0 - 32;
/*     */     } else {
/* 832 */       i1 = b0 + this.field_147000_g;
/*     */     } 
/* 834 */     if (func_146978_c(l + 3, i1 + 3, 23, 27, par2, par3)) {
/*     */       
/* 836 */       func_146279_a(I18n.func_135052_a(par1CreativeTabs.func_78024_c(), new Object[0]), par2, par3);
/* 837 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 841 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void renderCreativeTab(CreativeTabs par1CreativeTabs) {
/* 850 */     boolean flag = (par1CreativeTabs.func_78021_a() == selectedTabIndex);
/* 851 */     boolean flag1 = par1CreativeTabs.func_78023_l();
/* 852 */     int i = par1CreativeTabs.func_78020_k();
/* 853 */     int j = i * 28;
/* 854 */     int k = 0;
/* 855 */     int l = this.field_147003_i + 28 * i;
/* 856 */     int i1 = this.field_147009_r;
/* 857 */     byte b0 = 32;
/*     */     
/* 859 */     if (flag) {
/* 860 */       k += 32;
/*     */     }
/* 862 */     if (i == 5) {
/* 863 */       l = this.field_147003_i + this.field_146999_f - 28;
/* 864 */     } else if (i > 0) {
/* 865 */       l += i;
/*     */     } 
/* 867 */     if (flag1) {
/*     */       
/* 869 */       i1 -= 28;
/*     */     }
/*     */     else {
/*     */       
/* 873 */       k += 64;
/* 874 */       i1 += this.field_147000_g - 4;
/*     */     } 
/*     */     
/* 877 */     GL11.glDisable(2896);
/* 878 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/* 879 */     GL11.glEnable(3008);
/* 880 */     func_73729_b(l, i1, j, k, 28, b0);
/* 881 */     this.field_73735_i = 100.0F;
/* 882 */     field_146296_j.field_77023_b = 100.0F;
/* 883 */     l += 6;
/* 884 */     i1 += 8 + (flag1 ? 1 : -1);
/* 885 */     GL11.glEnable(2896);
/* 886 */     GL11.glEnable(32826);
/* 887 */     ItemStack itemstack = par1CreativeTabs.func_151244_d();
/* 888 */     if (itemstack != null) {
/*     */       
/* 890 */       field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, l, i1);
/* 891 */       field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, l, i1);
/*     */     } 
/* 893 */     GL11.glDisable(2896);
/* 894 */     field_146296_j.field_77023_b = 0.0F;
/* 895 */     this.field_73735_i = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_146284_a(GuiButton par1GuiButton) {
/* 904 */     if (par1GuiButton.field_146127_k == 0) {
/* 905 */       this.field_146297_k.func_147108_a((GuiScreen)new GuiAchievements((GuiScreen)this, this.field_146297_k.field_71439_g.func_146107_m()));
/*     */     }
/* 907 */     if (par1GuiButton.field_146127_k == 1) {
/* 908 */       this.field_146297_k.func_147108_a((GuiScreen)new GuiStats((GuiScreen)this, this.field_146297_k.field_71439_g.func_146107_m()));
/*     */     }
/* 910 */     if (par1GuiButton.field_146127_k == 101) {
/* 911 */       tabPage = Math.max(tabPage - 1, 0);
/* 912 */     } else if (par1GuiButton.field_146127_k == 102) {
/* 913 */       tabPage = Math.min(tabPage + 1, this.maxPages);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentTabIndex() {
/* 921 */     return selectedTabIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InventoryBasic getInventory() {
/* 929 */     return inventory;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiContainerCreativeTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */