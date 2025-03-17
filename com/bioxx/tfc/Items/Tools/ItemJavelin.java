/*     */ package com.bioxx.tfc.Items.Tools;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Items.ItemQuiver;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumAmmo;
/*     */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*     */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.Interfaces.IProjectile;
/*     */ import com.bioxx.tfc.api.Interfaces.IQuiverAmmo;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.google.common.collect.HashMultimap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ public class ItemJavelin
/*     */   extends ItemTerraTool
/*     */   implements ICausesDamage, IProjectile, IQuiverAmmo
/*     */ {
/*     */   public float weaponDamage;
/*     */   private float weaponRangeDamage;
/*     */   private int startingSlot;
/*     */   
/*     */   public ItemJavelin(Item.ToolMaterial par2EnumToolMaterial, float damage) {
/*  45 */     super(10.0F, par2EnumToolMaterial, Sets.newHashSet((Object[])new Block[] { Blocks.field_150350_a }));
/*  46 */     this.field_77777_bU = 1;
/*  47 */     this.weaponDamage = damage;
/*  48 */     this.weaponRangeDamage = damage;
/*  49 */     func_77656_e(par2EnumToolMaterial.func_77997_a() / 2);
/*  50 */     func_77637_a(TFCTabs.TFC_WEAPONS);
/*  51 */     this.startingSlot = -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
/*  58 */     list.add(new ItemStack((Item)this));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  70 */     String name = func_77658_a().replace("item.", "");
/*  71 */     name = name.replace("IgIn ", "");
/*  72 */     name = name.replace("IgEx ", "");
/*  73 */     name = name.replace("Sed ", "");
/*  74 */     name = name.replace("MM ", "");
/*  75 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:tools/" + name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77619_b() {
/*  84 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack) {
/*  93 */     return EnumAction.bow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack par1ItemStack) {
/* 102 */     return 72000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 108 */     Block b = world.func_147439_a(x, y, z);
/* 109 */     return (b == TFCBlocks.toolRack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
/* 118 */     par3EntityPlayer.func_71008_a(par1ItemStack, func_77626_a(par1ItemStack));
/* 119 */     this.startingSlot = par3EntityPlayer.field_71071_by.field_70461_c;
/* 120 */     return par1ItemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int itemInUseCount) {
/* 129 */     if (!world.field_72995_K) {
/*     */       
/* 131 */       int var6 = func_77626_a(itemstack) - itemInUseCount;
/* 132 */       float force = Math.min(var6 / 20.0F, 1.0F);
/*     */       
/* 134 */       EntityJavelin javelin = new EntityJavelin(world, (EntityLivingBase)player, 1.5F * force);
/* 135 */       javelin.func_70239_b(getRangedDamage(itemstack));
/* 136 */       javelin.duraBuff = AnvilManager.getDurabilityBuff(itemstack);
/* 137 */       javelin.damageBuff = AnvilManager.getDamageBuff(itemstack);
/* 138 */       NBTTagCompound nbt = AnvilManager.getCraftTag(itemstack);
/* 139 */       if (nbt.func_74764_b("blacksmith")) javelin.blacksmith = nbt.func_74779_i("blacksmith");
/*     */       
/* 141 */       int var9 = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, itemstack);
/*     */       
/* 143 */       if (var9 > 0)
/*     */       {
/* 145 */         javelin.func_70239_b(javelin.func_70242_d() + var9 * 0.5D + 0.5D);
/*     */       }
/*     */       
/* 148 */       int var10 = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, itemstack);
/*     */       
/* 150 */       if (var10 > 0)
/*     */       {
/* 152 */         javelin.func_70239_b(javelin.func_70242_d() + var10);
/*     */       }
/*     */       
/* 155 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, itemstack) > 0)
/*     */       {
/* 157 */         javelin.func_70015_d(100);
/*     */       }
/*     */       
/* 160 */       world.func_72956_a((Entity)player, "random.bow", 1.0F, 0.3F);
/* 161 */       javelin.setDamageTaken((short)itemstack.func_77960_j());
/* 162 */       javelin.setPickupItem(itemstack.func_77973_b());
/*     */ 
/*     */       
/* 165 */       player.field_71071_by.field_70461_c = this.startingSlot;
/*     */       
/* 167 */       player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = null;
/*     */ 
/*     */ 
/*     */       
/* 171 */       if (!consumeJavelin(player))
/*     */       {
/* 173 */         player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c] = consumeJavelinInQuiver(player, true);
/*     */       }
/*     */       
/* 176 */       if (!world.field_72995_K)
/*     */       {
/* 178 */         world.func_72838_d((Entity)javelin);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int getInventorySlotContainJavelin(EntityPlayer player) {
/* 185 */     for (int j = 0; j < player.field_71071_by.field_70462_a.length; j++) {
/*     */       
/* 187 */       if (player.field_71071_by.field_70462_a[j] != null && player.field_71071_by.field_70462_a[j].func_77973_b() instanceof ItemJavelin)
/*     */       {
/* 189 */         return j;
/*     */       }
/*     */     } 
/*     */     
/* 193 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack consumeJavelinInQuiver(EntityPlayer player, boolean shouldConsume) {
/* 198 */     ItemStack quiver = player.field_71071_by.func_70301_a(36);
/* 199 */     if (quiver != null && quiver.func_77973_b() instanceof ItemQuiver)
/* 200 */       return ((ItemQuiver)quiver.func_77973_b()).consumeAmmo(quiver, EnumAmmo.JAVELIN, shouldConsume); 
/* 201 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean consumeJavelin(EntityPlayer player) {
/* 206 */     int active = player.field_71071_by.field_70461_c;
/*     */     
/* 208 */     int nextJav = getInventorySlotContainJavelin(player);
/*     */ 
/*     */     
/* 211 */     if (nextJav < 0) {
/*     */       
/* 213 */       player.field_71071_by.field_70462_a[active] = null;
/* 214 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 218 */     player.field_71071_by.field_70462_a[active] = player.field_71071_by.field_70462_a[nextJav].func_77946_l();
/* 219 */     if (--(player.field_71071_by.field_70462_a[nextJav]).field_77994_a <= 0)
/*     */     {
/* 221 */       player.field_71071_by.field_70462_a[nextJav] = null;
/*     */     }
/*     */     
/* 224 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77662_d() {
/* 230 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDamageType getDamageType() {
/* 236 */     return EnumDamageType.PIERCING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getRangedDamage(ItemStack is) {
/* 242 */     if (is != null) {
/* 243 */       return this.weaponRangeDamage + this.weaponRangeDamage * AnvilManager.getDamageBuff(is);
/*     */     }
/* 245 */     return this.weaponRangeDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Multimap getAttributeModifiers(ItemStack stack) {
/* 252 */     HashMultimap hashMultimap = HashMultimap.create();
/* 253 */     hashMultimap.put(SharedMonsterAttributes.field_111264_e.func_111108_a(), new AttributeModifier(field_111210_e, "Weapon modifier", getWeaponDamage(stack), 0));
/* 254 */     return (Multimap)hashMultimap;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getWeaponDamage(ItemStack is) {
/* 259 */     return Math.floor((this.weaponDamage + this.weaponDamage * AnvilManager.getDamageBuff(is)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack is) {
/* 265 */     return (int)Math.floor((func_77612_l() + func_77612_l() * AnvilManager.getDurabilityBuff(is)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAmmo getAmmoType() {
/* 271 */     return EnumAmmo.JAVELIN;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumItemReach getReach(ItemStack is) {
/* 276 */     return EnumItemReach.FAR;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemJavelin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */