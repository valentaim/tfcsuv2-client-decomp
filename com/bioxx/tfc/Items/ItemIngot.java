/*     */ package com.bioxx.tfc.Items;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Metal.MetalRegistry;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TileEntities.TEIngotPile;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Interfaces.ISmeltable;
/*     */ import com.bioxx.tfc.api.Metal;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemIngot
/*     */   extends ItemTerra
/*     */   implements ISmeltable
/*     */ {
/*  37 */   private EnumSize size = EnumSize.SMALL;
/*     */   
/*     */   private String metal;
/*     */   
/*     */   private short metalAmount;
/*     */   
/*     */   private boolean smeltable = true;
/*     */   
/*     */   public ItemIngot() {
/*  46 */     func_77637_a(TFCTabs.TFC_MATERIALS);
/*  47 */     setFolder("ingots/");
/*  48 */     this.metalAmount = 100;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemIngot(boolean canSmelt) {
/*  53 */     this();
/*  54 */     this.smeltable = canSmelt;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemTerra setMetal(String m, int amt) {
/*  59 */     this.metal = m;
/*  60 */     this.metalAmount = (short)amt;
/*  61 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  67 */     this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", "").replace("Weak ", "").replace("HC ", ""));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/*  80 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/*  86 */     return EnumWeight.MEDIUM;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemIngot setSize(EnumSize s) {
/*  92 */     this.size = s;
/*  93 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addCreativeItems(List<ItemStack> list) {
/*  98 */     list.add(new ItemStack(this));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean createPile(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, int l) {
/* 104 */     boolean fullStack = true;
/*     */     
/* 106 */     TEIngotPile te = null;
/*     */     
/* 108 */     if (world.func_147438_o(x, y, z) instanceof TEIngotPile && world.func_147439_a(x, y, z) == TFCBlocks.ingotPile) {
/*     */       
/* 110 */       te = (TEIngotPile)world.func_147438_o(x, y, z);
/* 111 */       if (te.contentsMatch(0, itemstack) && (te.func_70301_a(0)).field_77994_a < te.func_70297_j_()) {
/*     */         
/* 113 */         fullStack = false;
/* 114 */         te.injectContents(0, 1);
/*     */       } 
/*     */     } else {
/* 117 */       fullStack = true;
/*     */     } 
/* 119 */     if (fullStack && isPlaceable(itemstack)) {
/*     */       
/* 121 */       if (side == 0 && world.func_147437_c(x, y - 1, z) && isValid(world, x, y - 1, z)) {
/*     */         
/* 123 */         world.func_147465_d(x, y - 1, z, TFCBlocks.ingotPile, l, 2);
/* 124 */         if (world.field_72995_K) {
/* 125 */           world.func_147471_g(x, y - 1, z);
/*     */         }
/* 127 */         te = (TEIngotPile)world.func_147438_o(x, y - 1, z);
/*     */       }
/* 129 */       else if (side == 1 && world.func_147437_c(x, y + 1, z) && isValid(world, x, y + 1, z)) {
/*     */         
/* 131 */         world.func_147465_d(x, y + 1, z, TFCBlocks.ingotPile, l, 2);
/* 132 */         if (world.field_72995_K) {
/* 133 */           world.func_147471_g(x, y + 1, z);
/*     */         }
/* 135 */         te = (TEIngotPile)world.func_147438_o(x, y + 1, z);
/*     */       }
/* 137 */       else if (side == 2 && world.func_147437_c(x, y, z - 1) && isValid(world, x, y, z - 1)) {
/*     */         
/* 139 */         world.func_147465_d(x, y, z - 1, TFCBlocks.ingotPile, l, 2);
/* 140 */         if (world.field_72995_K) {
/* 141 */           world.func_147471_g(x, y, z - 1);
/*     */         }
/* 143 */         te = (TEIngotPile)world.func_147438_o(x, y, z - 1);
/*     */       }
/* 145 */       else if (side == 3 && world.func_147437_c(x, y, z + 1) && isValid(world, x, y, z + 1)) {
/*     */         
/* 147 */         world.func_147465_d(x, y, z + 1, TFCBlocks.ingotPile, l, 2);
/* 148 */         if (world.field_72995_K) {
/* 149 */           world.func_147471_g(x, y, z + 1);
/*     */         }
/* 151 */         te = (TEIngotPile)world.func_147438_o(x, y, z + 1);
/*     */       }
/* 153 */       else if (side == 4 && world.func_147437_c(x - 1, y, z) && isValid(world, x - 1, y, z)) {
/*     */         
/* 155 */         world.func_147465_d(x - 1, y, z, TFCBlocks.ingotPile, l, 2);
/* 156 */         if (world.field_72995_K) {
/* 157 */           world.func_147471_g(x - 1, y, z);
/*     */         }
/* 159 */         te = (TEIngotPile)world.func_147438_o(x - 1, y, z);
/*     */       }
/* 161 */       else if (side == 5 && world.func_147437_c(x + 1, y, z) && isValid(world, x + 1, y, z)) {
/*     */         
/* 163 */         world.func_147465_d(x + 1, y, z, TFCBlocks.ingotPile, l, 2);
/* 164 */         if (world.field_72995_K) {
/* 165 */           world.func_147471_g(x + 1, y, z);
/*     */         }
/* 167 */         te = (TEIngotPile)world.func_147438_o(x + 1, y, z);
/*     */       }
/*     */       else {
/*     */         
/* 171 */         return false;
/*     */       } 
/*     */       
/* 174 */       if (te != null) {
/*     */         
/* 176 */         te.storage[0] = new ItemStack(this, 1, 0);
/* 177 */         te.setType((MetalRegistry.instance.getMetalFromItem(this)).name);
/*     */         
/* 179 */         if (entityplayer.field_71075_bZ.field_75098_d)
/*     */         {
/* 181 */           te.storage[0] = new ItemStack(this, 32, 0);
/*     */         }
/*     */       } 
/*     */     } 
/* 185 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlaceable(ItemStack is) {
/* 190 */     Item id = is.func_77973_b();
/*     */     
/* 192 */     return (id != TFCItems.weakSteelIngot && id != TFCItems.highCarbonSteelIngot && id != TFCItems.highCarbonBlackSteelIngot && id != TFCItems.weakRedSteelIngot && id != TFCItems.weakBlueSteelIngot && id != TFCItems.highCarbonRedSteelIngot && id != TFCItems.highCarbonBlueSteelIngot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
/* 200 */     NBTTagCompound stackTagCompound = itemstack.func_77978_p();
/*     */     
/* 202 */     if (entityplayer.func_70093_af() && stackTagCompound == null && itemstack.func_77973_b().func_77658_a().indexOf("Double") == -1 && 
/* 203 */       isPlaceable(itemstack)) {
/*     */       
/* 205 */       int dir = MathHelper.func_76128_c((entityplayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 206 */       if (!world.field_72995_K && entityplayer.func_70093_af() && (world.func_147439_a(x, y, z) != TFCBlocks.ingotPile || (side != 1 && side != 0))) {
/*     */ 
/*     */         
/* 209 */         if (createPile(itemstack, entityplayer, world, x, y, z, side, dir))
/*     */         {
/*     */           
/* 212 */           itemstack.field_77994_a--;
/* 213 */           world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
/* 214 */           return true;
/*     */         }
/*     */       
/*     */       }
/* 218 */       else if (world.func_147439_a(x, y, z) == TFCBlocks.ingotPile) {
/*     */         
/* 220 */         TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);
/*     */         
/* 222 */         if (te != null)
/*     */         {
/* 224 */           te.func_145838_q().func_149727_a(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
/* 225 */           if (te.storage[0] != null && te.contentsMatch(0, itemstack) && (te.storage[0]).field_77994_a < 64) {
/*     */             
/* 227 */             te.injectContents(0, 1);
/* 228 */             te.func_145829_t();
/*     */           } else {
/* 230 */             if (te.storage[0] != null && !te.contentsMatch(0, itemstack) && (te.storage[0]).field_77994_a < 64)
/*     */             {
/* 232 */               return false;
/*     */             }
/* 234 */             if (te.storage[0] == null) {
/*     */               
/* 236 */               te.addContents(0, new ItemStack(this, 1));
/*     */             }
/*     */             else {
/*     */               
/* 240 */               if (createPile(itemstack, entityplayer, world, x, y, z, side, dir)) {
/*     */                 
/* 242 */                 itemstack.field_77994_a--;
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 247 */                 world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
/* 248 */                 te.func_145838_q().func_149727_a(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
/*     */               } 
/* 250 */               return true;
/*     */             } 
/*     */           } 
/* 253 */           itemstack.field_77994_a--;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 258 */           world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
/* 259 */           return true;
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 265 */         int m = itemstack.func_77960_j();
/* 266 */         if (side == 1) {
/*     */           
/* 268 */           if (m >= 16) {
/* 269 */             world.func_147465_d(x, y + 1, z, TFCBlocks.ingotPile, m, 2);
/* 270 */             itemstack.field_77994_a--;
/*     */           } else {
/*     */             
/* 273 */             world.func_147465_d(x, y + 1, z, TFCBlocks.ingotPile, m, 2);
/* 274 */             itemstack.field_77994_a--;
/*     */           }
/*     */         
/* 277 */         } else if (side == 0 && world.func_147437_c(x, y - 1, z)) {
/*     */           
/* 279 */           if (m >= 16) {
/* 280 */             world.func_147465_d(x, y - 1, z, TFCBlocks.ingotPile, m, 2);
/* 281 */             itemstack.field_77994_a--;
/*     */           } else {
/*     */             
/* 284 */             world.func_147465_d(x, y - 1, z, TFCBlocks.ingotPile, m, 2);
/* 285 */             itemstack.field_77994_a--;
/*     */           }
/*     */         
/* 288 */         } else if (side == 2 && world.func_147437_c(x, y, z - 1)) {
/*     */           
/* 290 */           setSide(world, itemstack, m, dir, x, y, z, 0, 0, -1);
/*     */         }
/* 292 */         else if (side == 3 && world.func_147437_c(x, y, z + 1)) {
/*     */           
/* 294 */           setSide(world, itemstack, m, dir, x, y, z, 0, 0, 1);
/*     */         }
/* 296 */         else if (side == 4 && world.func_147437_c(x - 1, y, z)) {
/*     */           
/* 298 */           setSide(world, itemstack, m, dir, x, y, z, -1, 0, 0);
/*     */         }
/* 300 */         else if (side == 5 && world.func_147437_c(x + 1, y, z)) {
/*     */           
/* 302 */           setSide(world, itemstack, m, dir, x, y, z, 1, 0, 0);
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 308 */         world.func_147452_c(x, y, z, TFCBlocks.ingotPile, 0, 0);
/* 309 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 313 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isValid(World world, int i, int j, int k) {
/* 318 */     if (world.isSideSolid(i, j - 1, k, ForgeDirection.UP) || world.func_147439_a(i, j - 1, k) == TFCBlocks.ingotPile) {
/*     */       
/* 320 */       TileEntity te = world.func_147438_o(i, j - 1, k);
/*     */       
/* 322 */       if (te instanceof TEIngotPile) {
/*     */         
/* 324 */         TEIngotPile ip = (TEIngotPile)te;
/*     */         
/* 326 */         if (ip.storage[0] == null || (ip.storage[0]).field_77994_a < 64)
/*     */         {
/* 328 */           return false;
/*     */         }
/*     */       } 
/* 331 */       return true;
/*     */     } 
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSide(World world, ItemStack itemstack, int m, int dir, int x, int y, int z, int i, int j, int k) {
/* 338 */     if (m < 8) {
/*     */       
/* 340 */       if (dir == 0 || dir == 2) {
/* 341 */         world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m, 2);
/*     */       } else {
/* 343 */         world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m | 0x8, 2);
/*     */       } 
/* 345 */       itemstack.field_77994_a--;
/*     */     }
/* 347 */     else if (m >= 16) {
/*     */       
/* 349 */       if (dir == 0 || dir == 2) {
/* 350 */         world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m - 8, 2);
/*     */       } else {
/* 352 */         world.func_147465_d(x + i, y + j, z + k, TFCBlocks.ingotPile, m - 8 | 0x8, 2);
/*     */       } 
/* 354 */       itemstack.field_77994_a--;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Metal getMetalType(ItemStack is) {
/* 362 */     if (this.metal == null)
/*     */     {
/* 364 */       return MetalRegistry.instance.getMetalFromItem(this);
/*     */     }
/*     */ 
/*     */     
/* 368 */     return MetalRegistry.instance.getMetalFromString(this.metal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getMetalReturnAmount(ItemStack is) {
/* 375 */     return this.metalAmount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSmeltable(ItemStack is) {
/* 382 */     return this.smeltable;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
/* 388 */     return ISmeltable.EnumTier.TierI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemStackLimit(ItemStack is) {
/* 395 */     if (is.func_77942_o()) {
/*     */       
/* 397 */       NBTTagCompound tag = is.func_77978_p();
/* 398 */       if (TFC_ItemHeat.hasTemp(is) || tag.func_74764_b("itemCraftingValue") || tag.func_74764_b("itemCraftingRule1"))
/*     */       {
/* 400 */         return 1;
/*     */       }
/*     */     } 
/*     */     
/* 404 */     return super.getItemStackLimit(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onEntityItemUpdate(EntityItem entityItem) {
/* 409 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCustomEntity(ItemStack stack) {
/* 415 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity createEntity(World world, Entity entity, ItemStack itemstack) {
/* 420 */     EntityTossIngotItem customIngotEntity = null;
/* 421 */     List<EntityPlayer> players = world.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a(entity.field_70165_t - 1.0D, entity.field_70163_u - 1.0D, entity.field_70161_v - 1.0D, entity.field_70165_t + 1.0D, entity.field_70163_u + 1.0D, entity.field_70161_v + 1.0D));
/*     */     
/* 423 */     if (players.size() > 0 && players.get(0) != null) {
/* 424 */       EntityPlayer p = players.get(0);
/* 425 */       customIngotEntity = new EntityTossIngotItem(world, p.field_70165_t, p.field_70163_u - 0.30000001192092896D + p.func_70047_e(), p.field_70161_v, itemstack);
/* 426 */       customIngotEntity.field_145804_b = 40;
/*     */       
/* 428 */       float var5 = 0.1F;
/*     */       
/* 430 */       var5 = 0.3F;
/* 431 */       customIngotEntity.field_70159_w = (-MathHelper.func_76126_a(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
/* 432 */       customIngotEntity.field_70179_y = (MathHelper.func_76134_b(p.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(p.field_70125_A / 180.0F * 3.1415927F) * var5);
/* 433 */       customIngotEntity.field_70181_x = (-MathHelper.func_76126_a(p.field_70125_A / 180.0F * 3.1415927F) * var5 + 0.1F);
/* 434 */       var5 = 0.02F;
/* 435 */       float var6 = world.field_73012_v.nextFloat() * 3.1415927F * 2.0F;
/* 436 */       var5 *= world.field_73012_v.nextFloat();
/* 437 */       customIngotEntity.field_70159_w += Math.cos(var6) * var5;
/* 438 */       customIngotEntity.field_70181_x += ((world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.1F);
/* 439 */       customIngotEntity.field_70179_y += Math.sin(var6) * var5;
/*     */     } else {
/* 441 */       customIngotEntity = new EntityTossIngotItem(world, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, itemstack);
/* 442 */       customIngotEntity.field_145804_b = 40;
/*     */     } 
/*     */     
/* 445 */     return (Entity)customIngotEntity;
/*     */   }
/*     */   
/*     */   private class EntityTossIngotItem
/*     */     extends EntityItem {
/*     */     public EntityTossIngotItem(World p_i1711_1_) {
/* 451 */       super(p_i1711_1_);
/*     */     }
/*     */     
/*     */     public EntityTossIngotItem(World world, double posX, double posY, double posZ, ItemStack itemStack) {
/* 455 */       super(world, posX, posY, posZ, itemStack);
/*     */     }
/*     */     
/*     */     public void func_70071_h_() {
/* 459 */       if (this.field_70170_p.func_82737_E() % 10L == 0L) {
/* 460 */         ItemStack ingot = func_92059_d();
/* 461 */         if (TFC_ItemHeat.hasTemp(ingot)) {
/* 462 */           Block block = this.field_70170_p.func_147439_a(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v));
/* 463 */           if (TFC_Core.isWater(block)) {
/* 464 */             TFC_ItemHeat.removeTempTag(ingot);
/* 465 */             func_85030_a("random.fizz", 0.4F, 2.0F + this.field_70146_Z.nextFloat() * 0.4F);
/* 466 */             func_92058_a(ingot);
/*     */           } 
/*     */         } 
/*     */       } 
/* 470 */       super.func_70071_h_();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemIngot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */