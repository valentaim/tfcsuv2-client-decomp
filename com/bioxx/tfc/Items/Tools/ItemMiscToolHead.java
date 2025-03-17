/*    */ package com.bioxx.tfc.Items.Tools;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.Items.ItemTerra;
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*    */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*    */ import com.bioxx.tfc.api.Metal;
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMiscToolHead
/*    */   extends ItemTerra
/*    */   implements ISmeltable
/*    */ {
/*    */   private Item.ToolMaterial material;
/*    */   
/*    */   public ItemMiscToolHead() {
/* 26 */     func_77656_e(100);
/* 27 */     func_77625_d(4);
/* 28 */     func_77637_a(TFCTabs.TFC_MISC);
/* 29 */     setFolder("toolheads/");
/* 30 */     setWeight(EnumWeight.MEDIUM);
/* 31 */     setSize(EnumSize.SMALL);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemMiscToolHead(Item.ToolMaterial m) {
/* 36 */     this();
/* 37 */     this.material = m;
/*    */   }
/*    */ 
/*    */   
/*    */   public Item.ToolMaterial getMaterial() {
/* 42 */     return this.material;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_94581_a(IIconRegister registerer) {
/* 48 */     String name = func_77658_a().replace("item.", "");
/* 49 */     name = name.replace("IgIn ", "");
/* 50 */     name = name.replace("IgEx ", "");
/* 51 */     name = name.replace("Sed ", "");
/* 52 */     name = name.replace("MM ", "");
/* 53 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 59 */     ItemTerraTool.addSmithingBonusInformation(is, arraylist);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Metal getMetalType(ItemStack is) {
/* 65 */     if (this == TFCItems.wroughtIronKnifeHead) {
/* 66 */       return Global.WROUGHTIRON;
/*    */     }
/* 68 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public short getMetalReturnAmount(ItemStack is) {
/* 74 */     if (this == TFCItems.wroughtIronKnifeHead)
/*    */     {
/* 76 */       return 50;
/*    */     }
/* 78 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSmeltable(ItemStack is) {
/* 84 */     return (this == TFCItems.wroughtIronKnifeHead);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
/* 90 */     return ISmeltable.EnumTier.TierIII;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemMiscToolHead.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */