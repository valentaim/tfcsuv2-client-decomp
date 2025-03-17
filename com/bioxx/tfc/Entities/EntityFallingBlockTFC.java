/*     */ package com.bioxx.tfc.Entities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockFalling;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.crash.CrashReportCategory;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFallingBlockTFC
/*     */   extends Entity
/*     */   implements IEntityAdditionalSpawnData
/*     */ {
/*     */   private Block block;
/*     */   public int blockMeta;
/*     */   public int aliveTimer;
/*     */   public boolean shouldDropItem;
/*     */   private boolean hurtEntities;
/*     */   public int maxDamage;
/*     */   public float damage;
/*     */   public NBTTagCompound tileEntityData;
/*     */   
/*     */   public EntityFallingBlockTFC(World world) {
/*  46 */     super(world);
/*  47 */     this.shouldDropItem = true;
/*  48 */     this.maxDamage = 2000;
/*  49 */     this.damage = 100.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFallingBlockTFC(World world, double x, double y, double z, Block b) {
/*  54 */     this(world, x, y, z, b, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFallingBlockTFC(World world, double x, double y, double z, Block b, int meta) {
/*  59 */     this(world);
/*  60 */     this.shouldDropItem = false;
/*  61 */     this.block = b;
/*  62 */     this.blockMeta = meta;
/*  63 */     this.field_70156_m = true;
/*  64 */     func_70105_a(0.98F, 0.98F);
/*  65 */     this.field_70129_M = this.field_70131_O / 2.0F;
/*  66 */     func_70107_b(x, y, z);
/*  67 */     this.field_70159_w = 0.0D;
/*  68 */     this.field_70181_x = 0.0D;
/*  69 */     this.field_70179_y = 0.0D;
/*  70 */     this.field_70169_q = x;
/*  71 */     this.field_70167_r = y;
/*  72 */     this.field_70166_s = z;
/*  73 */     this.hurtEntities = b instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70041_e_() {
/*  83 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70067_L() {
/*  95 */     return !this.field_70128_L;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 105 */     if (this.block == null)
/*     */       return; 
/* 107 */     if (this.block.func_149688_o() == Material.field_151579_a) {
/*     */       
/* 109 */       func_70106_y();
/*     */     }
/*     */     else {
/*     */       
/* 113 */       this.field_70169_q = this.field_70165_t;
/* 114 */       this.field_70167_r = this.field_70163_u;
/* 115 */       this.field_70166_s = this.field_70161_v;
/* 116 */       this.aliveTimer++;
/* 117 */       this.field_70181_x -= 0.03999999910593033D;
/* 118 */       func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 119 */       this.field_70159_w *= 0.9800000190734863D;
/* 120 */       this.field_70181_x *= 0.9800000190734863D;
/* 121 */       this.field_70179_y *= 0.9800000190734863D;
/*     */       
/* 123 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 125 */         int i = MathHelper.func_76128_c(this.field_70165_t);
/* 126 */         int j = MathHelper.func_76128_c(this.field_70163_u);
/* 127 */         int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */         
/* 129 */         if (this.aliveTimer == 1)
/*     */         {
/* 131 */           this.field_70170_p.func_147468_f(i, j, k);
/*     */         }
/*     */         
/* 134 */         if (this.field_70122_E) {
/*     */           
/* 136 */           if (canReplace(this.field_70170_p, i, j, k)) {
/*     */             
/* 138 */             this.field_70170_p.func_147468_f(i, j, k);
/*     */           }
/* 140 */           else if (this.field_70170_p.func_147439_a(i, j, k).func_149669_A() < 1.0D) {
/*     */             
/* 142 */             j++;
/*     */           } 
/* 144 */           if (canReplace(this.field_70170_p, i, j - 1, k)) {
/*     */ 
/*     */             
/* 147 */             this.field_70170_p.func_147468_f(i, j - 1, k);
/* 148 */             this.field_70122_E = false;
/*     */           } 
/*     */         } 
/*     */         
/* 152 */         if (this.field_70122_E) {
/*     */           
/* 154 */           this.field_70159_w *= 0.699999988079071D;
/* 155 */           this.field_70179_y *= 0.699999988079071D;
/* 156 */           this.field_70181_x *= -0.5D;
/*     */           
/* 158 */           if (this.field_70170_p.func_147439_a(i, j, k) != Blocks.field_150326_M) {
/*     */             
/* 160 */             func_70106_y();
/*     */             
/* 162 */             if (canPlaceEntityOnSide(this.field_70170_p, this.block, i, j, k, true, 1, (Entity)null, (ItemStack)null) && !BlockFalling.func_149831_e(this.field_70170_p, i, j - 1, k)) {
/*     */               
/* 164 */               if (this.tileEntityData != null && this.block instanceof net.minecraft.block.ITileEntityProvider) {
/*     */                 
/* 166 */                 TileEntity tileentity = this.field_70170_p.func_147438_o(i, j, k);
/*     */                 
/* 168 */                 if (tileentity != null)
/*     */                 {
/* 170 */                   NBTTagCompound nbttagcompound = new NBTTagCompound();
/* 171 */                   tileentity.func_145841_b(nbttagcompound);
/* 172 */                   Iterator<String> iterator = this.tileEntityData.func_150296_c().iterator();
/*     */                   
/* 174 */                   while (iterator.hasNext()) {
/*     */                     
/* 176 */                     String s = iterator.next();
/* 177 */                     NBTBase nbtbase = this.tileEntityData.func_74781_a(s);
/*     */                     
/* 179 */                     if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s))
/*     */                     {
/* 181 */                       nbttagcompound.func_74782_a(s, nbtbase.func_74737_b());
/*     */                     }
/*     */                   } 
/*     */                   
/* 185 */                   tileentity.func_145839_a(nbttagcompound);
/* 186 */                   tileentity.func_70296_d();
/*     */                 }
/*     */               
/*     */               } 
/* 190 */             } else if (this.shouldDropItem) {
/*     */               
/* 192 */               func_70099_a(new ItemStack(this.block, 1, this.block.func_149692_a(this.blockMeta)), 0.0F);
/*     */             }
/*     */           
/*     */           } 
/* 196 */         } else if ((this.aliveTimer > 100 && !this.field_70170_p.field_72995_K && (j < 1 || j > 256)) || this.aliveTimer > 600) {
/*     */           
/* 198 */           if (this.shouldDropItem)
/*     */           {
/* 200 */             func_70099_a(new ItemStack(this.block, 1, this.block.func_149692_a(this.blockMeta)), 0.0F);
/*     */           }
/*     */           
/* 203 */           func_70106_y();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceEntityOnSide(World world, Block fallingBlock, int x, int y, int z, boolean skipEntityCheck, int side, Entity thisEntity, ItemStack is) {
/* 211 */     AxisAlignedBB axisalignedbb = null;
/* 212 */     if (!skipEntityCheck) {
/*     */       
/* 214 */       axisalignedbb = fallingBlock.func_149668_a(world, x, y, z);
/* 215 */       if (!world.func_72917_a(axisalignedbb, thisEntity)) {
/* 216 */         return false;
/*     */       }
/*     */     } 
/* 219 */     Block block1 = world.func_147439_a(x, y, z);
/* 220 */     return (block1.func_149688_o() == Material.field_151594_q) ? true : canReplace(world, x, y, z);
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
/*     */   public boolean canFallBelow(Block block, World world, int x, int y, int z) {
/* 255 */     if (block == Blocks.field_150480_ab)
/* 256 */       return true; 
/* 257 */     if (block == TFCBlocks.tallGrass)
/* 258 */       return true; 
/* 259 */     if (block == TFCBlocks.torch)
/* 260 */       return true; 
/* 261 */     if (block == TFCBlocks.smokeRack)
/* 262 */       return true; 
/* 263 */     if (block == TFCBlocks.toolRack) {
/* 264 */       return true;
/*     */     }
/* 266 */     if (block == Blocks.field_150357_h)
/* 267 */       return false; 
/* 268 */     if (block == TFCBlocks.charcoal)
/* 269 */       return false; 
/* 270 */     if (block == TFCBlocks.molten) {
/* 271 */       return false;
/*     */     }
/* 273 */     Material material = block.func_149688_o();
/* 274 */     if (material == Material.field_151586_h || material == Material.field_151587_i) return true;
/*     */     
/* 276 */     if (!block.func_149662_c() && !block.func_149686_d() && !world.isSideSolid(x, y, z, ForgeDirection.UP)) return false;
/*     */     
/* 278 */     return (material == Material.field_151586_h || material == Material.field_151587_i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canReplace(World world, int x, int y, int z) {
/* 284 */     Block b = world.func_147439_a(x, y, z);
/* 285 */     if (canDestroy(b) && (b.isAir((IBlockAccess)world, x, y, z) || canFallBelow(b, world, x, y, z)))
/* 286 */       return TFC_Core.setBlockWithDrops(this.field_70170_p, x, y, z, getBlock(), this.blockMeta); 
/* 287 */     if (b instanceof com.bioxx.tfc.Blocks.Terrain.BlockOre && TFCOptions.enableCaveInsDestroyOre)
/* 288 */       return world.func_147468_f(x, y, z); 
/* 289 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean canDestroy(Block b) {
/* 295 */     return (b != TFCBlocks.charcoal && b != TFCBlocks.molten);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70069_a(float fallDistance) {
/* 305 */     if (this.hurtEntities) {
/*     */       
/* 307 */       int height = MathHelper.func_76123_f(fallDistance - 1.0F);
/*     */       
/* 309 */       if (height > 0) {
/*     */         
/* 311 */         ArrayList<Entity> arraylist = new ArrayList(this.field_70170_p.func_72839_b(this, this.field_70121_D));
/* 312 */         DamageSource damagesource = (new DamageSource("caveIn")).func_76348_h().func_151518_m();
/* 313 */         Iterator<Entity> iterator = arraylist.iterator();
/*     */         
/* 315 */         while (iterator.hasNext()) {
/*     */           
/* 317 */           Entity entity = iterator.next();
/* 318 */           entity.func_70097_a(damagesource, Math.min(MathHelper.func_76141_d(height * this.damage), this.maxDamage));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70014_b(NBTTagCompound nbt) {
/* 330 */     nbt.func_74774_a("Tile", (byte)Block.func_149682_b(this.block));
/* 331 */     nbt.func_74768_a("TileID", Block.func_149682_b(this.block));
/* 332 */     nbt.func_74774_a("Data", (byte)this.blockMeta);
/* 333 */     nbt.func_74774_a("Time", (byte)this.aliveTimer);
/* 334 */     nbt.func_74757_a("DropItem", this.shouldDropItem);
/* 335 */     nbt.func_74757_a("HurtEntities", this.hurtEntities);
/* 336 */     nbt.func_74776_a("FallHurtAmount", this.damage);
/* 337 */     nbt.func_74768_a("FallHurtMax", this.maxDamage);
/*     */     
/* 339 */     if (this.tileEntityData != null)
/*     */     {
/* 341 */       nbt.func_74782_a("TileEntityData", (NBTBase)this.tileEntityData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70037_a(NBTTagCompound nbt) {
/* 351 */     if (nbt.func_150297_b("TileID", 99)) {
/*     */       
/* 353 */       this.block = Block.func_149729_e(nbt.func_74762_e("TileID"));
/*     */     }
/*     */     else {
/*     */       
/* 357 */       this.block = Block.func_149729_e(nbt.func_74771_c("Tile") & 0xFF);
/*     */     } 
/*     */     
/* 360 */     this.blockMeta = nbt.func_74771_c("Data") & 0xFF;
/* 361 */     this.aliveTimer = nbt.func_74771_c("Time") & 0xFF;
/*     */     
/* 363 */     if (nbt.func_150297_b("HurtEntities", 99)) {
/*     */       
/* 365 */       this.hurtEntities = nbt.func_74767_n("HurtEntities");
/* 366 */       this.damage = nbt.func_74760_g("FallHurtAmount");
/* 367 */       this.maxDamage = nbt.func_74762_e("FallHurtMax");
/*     */     }
/* 369 */     else if (this.block instanceof com.bioxx.tfc.Blocks.Terrain.BlockCobble) {
/*     */       
/* 371 */       this.hurtEntities = true;
/*     */     } 
/*     */     
/* 374 */     if (nbt.func_150297_b("DropItem", 99))
/*     */     {
/* 376 */       this.shouldDropItem = nbt.func_74767_n("DropItem");
/*     */     }
/*     */     
/* 379 */     if (nbt.func_150297_b("TileEntityData", 10))
/*     */     {
/* 381 */       this.tileEntityData = nbt.func_74775_l("TileEntityData");
/*     */     }
/*     */     
/* 384 */     if (this.block.func_149688_o() == Material.field_151579_a)
/*     */     {
/* 386 */       this.block = (Block)Blocks.field_150354_m;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHurt(boolean hurt) {
/* 392 */     this.hurtEntities = hurt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_85029_a(CrashReportCategory category) {
/* 398 */     super.func_85029_a(category);
/* 399 */     category.func_71507_a("Immitating block ID", Integer.valueOf(Block.func_149682_b(this.block)));
/* 400 */     category.func_71507_a("Immitating block data", Integer.valueOf(this.blockMeta));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_70053_R() {
/* 407 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public World getWorld() {
/* 413 */     return this.field_70170_p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_90999_ad() {
/* 423 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getBlock() {
/* 428 */     return this.block;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(ByteBuf buffer) {
/* 433 */     buffer.writeInt(Block.func_149682_b(this.block));
/* 434 */     buffer.writeByte(this.blockMeta & 0xF);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(ByteBuf additionalData) {
/* 439 */     this.block = Block.func_149729_e(additionalData.readInt());
/* 440 */     this.blockMeta = additionalData.readByte();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityFallingBlockTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */