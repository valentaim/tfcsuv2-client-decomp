/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Enums.EnumAmmo;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.Interfaces.IQuiverAmmo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemQuiver
/*     */   extends ItemTerra
/*     */   implements IEquipable
/*     */ {
/*     */   public ItemQuiver() {
/*  30 */     func_77637_a(TFCTabs.TFC_ARMOR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_77618_c(int par1, int par2) {
/*  38 */     return this.field_77791_bV;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77663_a(ItemStack is, World world, Entity entity, int i, boolean isSelected) {
/*  52 */     super.func_77663_a(is, world, entity, i, isSelected);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
/*  58 */     entityplayer.openGui(TerraFirmaCraft.instance, 40, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
/*  59 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  65 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:quiver");
/*     */   }
/*     */   
/*     */   public int getQuiverArrows(ItemStack item) {
/*  69 */     int n = 0;
/*  70 */     ItemStack[] inventory = loadInventory(item);
/*  71 */     for (ItemStack i : inventory) {
/*     */       
/*  73 */       if (i != null && (i.func_77973_b() instanceof ItemArrow || (i
/*  74 */         .func_77973_b() instanceof IQuiverAmmo && ((IQuiverAmmo)i.func_77973_b()).getAmmoType() == EnumAmmo.ARROW)))
/*  75 */         n += i.field_77994_a; 
/*     */     } 
/*  77 */     return n;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getQuiverJavelins(ItemStack item) {
/*  82 */     int n = 0;
/*  83 */     ItemStack[] inventory = loadInventory(item);
/*  84 */     for (ItemStack i : inventory) {
/*  85 */       if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin)
/*  86 */         n += i.field_77994_a; 
/*     */     } 
/*  88 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List[] getQuiverJavelinTypes(ItemStack item) {
/*  95 */     ArrayList[] pair = new ArrayList[2];
/*  96 */     ArrayList<String> list = new ArrayList<>();
/*  97 */     ArrayList<Integer> listNum = new ArrayList<>();
/*  98 */     ItemStack[] inventory = loadInventory(item);
/*  99 */     for (ItemStack i : inventory) {
/*     */       
/* 101 */       if (i != null && i.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {
/*     */         
/* 103 */         String s = i.func_77973_b().func_77653_i(i);
/* 104 */         if (!list.contains(s))
/* 105 */           list.add(s); 
/* 106 */         int n = list.indexOf(s);
/* 107 */         if (listNum.size() == n)
/* 108 */           listNum.add(Integer.valueOf(0)); 
/* 109 */         listNum.set(n, Integer.valueOf(((Integer)listNum.get(n)).intValue() + 1));
/*     */       } 
/*     */     } 
/* 112 */     pair[0] = list;
/* 113 */     pair[1] = listNum;
/* 114 */     return (List[])pair;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 120 */     ItemTerra.addSizeInformation(is, arraylist);
/*     */     
/* 122 */     if (TFC_Core.showShiftInformation()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       arraylist.add(EnumChatFormatting.WHITE + TFC_Core.translate("gui.Advanced") + ":");
/* 130 */       arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Bow.Arrows") + ": " + EnumChatFormatting.YELLOW + getQuiverArrows(is));
/* 131 */       arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Bow.Javelins") + ": " + EnumChatFormatting.YELLOW + getQuiverJavelins(is));
/* 132 */       List[] javData = getQuiverJavelinTypes(is);
/* 133 */       for (int i = 0; i < javData[0].size(); i++) {
/*     */         
/* 135 */         String s = javData[0].get(i);
/* 136 */         int n = ((Integer)javData[1].get(i)).intValue();
/* 137 */         arraylist.add(EnumChatFormatting.ITALIC + "  -" + s + ": " + EnumChatFormatting.YELLOW + n);
/*     */       } 
/* 139 */       if (is.func_77942_o()) {
/*     */         
/* 141 */         NBTTagCompound stackTagCompound = is.func_77978_p();
/* 142 */         if (stackTagCompound.func_74764_b("creator")) {
/* 143 */           arraylist.add(EnumChatFormatting.ITALIC + TFC_Core.translate("gui.Armor.ForgedBy") + " " + stackTagCompound.func_74779_i("creator"));
/*     */         }
/*     */       } 
/*     */     } else {
/* 147 */       arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.Advanced") + ": (" + TFC_Core.translate("gui.Hold") + " " + EnumChatFormatting.GRAY + TFC_Core.translate("gui.Shift") + EnumChatFormatting.DARK_GRAY + ")");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack addItem(ItemStack quiver, ItemStack ammo) {
/* 154 */     ItemStack[] inventory = loadInventory(quiver);
/* 155 */     for (int i = 0; i < inventory.length && ammo != null; i++) {
/*     */       
/* 157 */       if (inventory[i] != null && inventory[i].func_77969_a(ammo)) {
/*     */         
/* 159 */         if (ammo.field_77994_a + (inventory[i]).field_77994_a <= ammo.func_77976_d())
/*     */         {
/* 161 */           (inventory[i]).field_77994_a += ammo.field_77994_a;
/* 162 */           ammo = null;
/*     */         }
/* 164 */         else if (ammo.field_77994_a + (inventory[i]).field_77994_a > ammo.func_77976_d())
/*     */         {
/* 166 */           int diff = ammo.func_77976_d() - (inventory[i]).field_77994_a;
/* 167 */           (inventory[i]).field_77994_a = ammo.func_77976_d();
/* 168 */           ammo.field_77994_a -= diff;
/*     */         }
/*     */       
/* 171 */       } else if (inventory[i] == null) {
/*     */         
/* 173 */         inventory[i] = ammo.func_77946_l();
/* 174 */         ammo = null;
/*     */       } 
/*     */     } 
/* 177 */     saveInventory(quiver, inventory);
/* 178 */     return ammo;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack consumeAmmo(ItemStack quiver, EnumAmmo ammoType, boolean shouldConsume) {
/* 183 */     ItemStack[] inventory = loadInventory(quiver);
/* 184 */     for (int i = 0; i < inventory.length; i++) {
/*     */       
/* 186 */       if (inventory[i] != null && inventory[i].func_77973_b() instanceof IQuiverAmmo && ((IQuiverAmmo)inventory[i].func_77973_b()).getAmmoType() == ammoType) {
/*     */         
/* 188 */         ItemStack out = inventory[i].func_77946_l();
/* 189 */         out.field_77994_a = 1;
/* 190 */         if (shouldConsume)
/* 191 */           (inventory[i]).field_77994_a--; 
/* 192 */         if ((inventory[i]).field_77994_a <= 0)
/* 193 */           inventory[i] = null; 
/* 194 */         saveInventory(quiver, inventory);
/* 195 */         return out;
/*     */       } 
/*     */     } 
/* 198 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] loadInventory(ItemStack quiver) {
/* 203 */     ItemStack[] inventory = new ItemStack[8];
/* 204 */     NBTTagCompound nbt = quiver.func_77978_p();
/* 205 */     if (nbt != null && nbt.func_74764_b("Items")) {
/*     */       
/* 207 */       NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/*     */       
/* 209 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */         
/* 211 */         NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 212 */         byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 213 */         if (byte0 >= 0 && byte0 < 8)
/* 214 */           inventory[byte0] = ItemStack.func_77949_a(nbttagcompound1); 
/*     */       } 
/*     */     } 
/* 217 */     return inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveInventory(ItemStack quiver, ItemStack[] inventory) {
/* 222 */     NBTTagList nbttaglist = new NBTTagList();
/* 223 */     for (int i = 0; i < inventory.length; i++) {
/*     */       
/* 225 */       if (inventory[i] != null) {
/*     */         
/* 227 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 228 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 229 */         inventory[i].func_77955_b(nbttagcompound1);
/* 230 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 233 */     if (quiver != null) {
/*     */       
/* 235 */       if (!quiver.func_77942_o())
/* 236 */         quiver.func_77982_d(new NBTTagCompound()); 
/* 237 */       quiver.func_77978_p().func_74782_a("Items", (NBTBase)nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IEquipable.EquipType getEquipType(ItemStack is) {
/* 244 */     return IEquipable.EquipType.BACK;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEquippedRender() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getTooHeavyToCarry(ItemStack is) {
/* 254 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77639_j() {
/* 260 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 266 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */