/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemMudBrick
/*     */   extends ItemTerra
/*     */ {
/*     */   private IIcon overlayIcon;
/*     */   
/*     */   public ItemMudBrick() {
/*  24 */     this.field_77787_bX = true;
/*  25 */     func_77656_e(0);
/*  26 */     setFolder("pottery/");
/*  27 */     func_77637_a(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/*     */     int i;
/*  33 */     for (i = 0; i < Global.STONE_ALL.length; i++)
/*  34 */       list.add(new ItemStack(this, 1, i)); 
/*  35 */     for (i = 0; i < Global.STONE_ALL.length; i++) {
/*  36 */       list.add(new ItemStack(this, 1, i + 32));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  42 */     this.overlayIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + "Mud Brick Overlay");
/*  43 */     super.func_94581_a(registerer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_77667_c(ItemStack itemstack) {
/*  49 */     if ((itemstack.func_77960_j() & 0x20) > 0)
/*  50 */       return "item.Wet Mud Brick"; 
/*  51 */     return super.func_77667_c(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/*  58 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_77618_c(int meta, int pass) {
/*  65 */     return (pass == 1) ? this.overlayIcon : super.func_77618_c(meta, pass);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_82790_a(ItemStack is, int pass) {
/*  72 */     int color = 16777215;
/*  73 */     if (pass == 0)
/*     */     {
/*  75 */       switch (is.func_77960_j() & 0x1F) {
/*     */         
/*     */         case 0:
/*  78 */           color = 10985616; break;
/*     */         case 1:
/*  80 */           color = 9210761; break;
/*     */         case 2:
/*  82 */           color = 5985361; break;
/*     */         case 3:
/*  84 */           color = 8683644; break;
/*     */         case 4:
/*  86 */           color = 13415322; break;
/*     */         case 5:
/*  88 */           color = 13947083; break;
/*     */         case 6:
/*  90 */           color = 12958367; break;
/*     */         case 7:
/*  92 */           color = 12299924; break;
/*     */         case 8:
/*  94 */           color = 7565170; break;
/*     */         case 9:
/*  96 */           color = 8674890; break;
/*     */         case 10:
/*  98 */           color = 13683909; break;
/*     */         case 11:
/* 100 */           color = 10130570; break;
/*     */         case 12:
/* 102 */           color = 5527384; break;
/*     */         case 13:
/* 104 */           color = 7962237; break;
/*     */         case 14:
/* 106 */           color = 9210763; break;
/*     */         case 15:
/* 108 */           color = 13548993; break;
/*     */         case 16:
/* 110 */           color = 11050380; break;
/*     */         case 17:
/* 112 */           color = 10260877; break;
/*     */         case 18:
/* 114 */           color = 10067336; break;
/*     */         case 19:
/* 116 */           color = 9605505; break;
/*     */         case 20:
/* 118 */           color = 13816272;
/*     */           break;
/*     */       }  } 
/* 121 */     if (is.func_77960_j() < 32)
/* 122 */       color -= 2236962; 
/* 123 */     return color;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 129 */     super.func_77624_a(is, player, arraylist, flag);
/* 130 */     if ((is.func_77960_j() & 0x1F) < 21) {
/* 131 */       arraylist.add(EnumChatFormatting.DARK_GRAY + Global.STONE_ALL[is.func_77960_j() & 0x1F]);
/*     */     } else {
/* 133 */       arraylist.add(EnumChatFormatting.DARK_RED + "Unknown: " + is.func_77960_j());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemMudBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */