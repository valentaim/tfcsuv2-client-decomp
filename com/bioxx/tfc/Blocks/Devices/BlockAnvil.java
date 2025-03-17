/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TEAnvil;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilReq;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockAnvil
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   private IIcon[] textureMapTop;
/*     */   private IIcon[] textureMapSide;
/*     */   private IIcon stoneAnvilIcon;
/*     */   private int anvilId;
/*     */   
/*     */   public BlockAnvil() {
/*  44 */     super(Material.field_151573_f);
/*  45 */     func_149647_a(TFCTabs.TFC_DEVICES);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockAnvil(int offset) {
/*  50 */     this();
/*  51 */     this.anvilId = offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/*  59 */     if (this == TFCBlocks.anvil)
/*     */     {
/*  61 */       for (int i = 1; i < 8; i++) {
/*  62 */         par3List.add(new ItemStack((Block)this, 1, i));
/*     */       }
/*     */     }
/*  65 */     if (this == TFCBlocks.anvil2)
/*     */     {
/*  67 */       for (int i = 0; i < 3; i++) {
/*  68 */         par3List.add(new ItemStack((Block)this, 1, i));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/*  75 */     return dmg & 0x7;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/*  81 */     if (world.field_72995_K)
/*     */     {
/*  83 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  87 */     if ((TEAnvil)world.func_147438_o(i, j, k) != null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  92 */       entityplayer.openGui(TerraFirmaCraft.instance, 21, world, i, j, k);
/*     */     }
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
/* 101 */     int meta = par1World.func_72805_g(par2, par3, par4);
/* 102 */     int direction = getDirectionFromMetadata(meta);
/* 103 */     TileEntity te = par1World.func_147438_o(par2, par3, par4);
/*     */     
/* 105 */     if (te instanceof TEAnvil) {
/*     */       
/* 107 */       TEAnvil teAnvil = (TEAnvil)te;
/* 108 */       if (teAnvil.anvilTier != AnvilReq.STONE.Tier || this == TFCBlocks.anvil2) {
/*     */         
/* 110 */         if (direction == 0) {
/* 111 */           return AxisAlignedBB.func_72330_a(par2 + 0.2D, par3 + 0.0D, par4 + 0.0D, par2 + 0.8D, par3 + 0.6D, par4 + 1.0D);
/*     */         }
/* 113 */         return AxisAlignedBB.func_72330_a(par2 + 0.0D, par3 + 0.0D, par4 + 0.2D, par2 + 1.0D, par3 + 0.6D, par4 + 0.8D);
/*     */       } 
/*     */ 
/*     */       
/* 117 */       return AxisAlignedBB.func_72330_a(par2 + 0.0D, par3 + 0.0D, par4 + 0.0D, par2 + 1.0D, par3 + 1.0D, par4 + 1.0D);
/*     */     } 
/*     */     
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 126 */     int meta = world.func_72805_g(x, y, z);
/* 127 */     int direction = getDirectionFromMetadata(meta);
/* 128 */     TEAnvil te = (TEAnvil)world.func_147438_o(x, y, z);
/*     */     
/* 130 */     if (te.anvilTier != AnvilReq.STONE.Tier) {
/*     */       
/* 132 */       if (direction == 0) {
/*     */         
/* 134 */         func_149676_a(0.2F, 0.0F, 0.0F, 0.8F, 0.6F, 1.0F);
/* 135 */         return AxisAlignedBB.func_72330_a(x + 0.2D, y + 0.0D, z + 0.0D, x + 0.8D, y + 0.6D, z + 1.0D);
/*     */       } 
/*     */ 
/*     */       
/* 139 */       func_149676_a(0.0F, 0.0F, 0.2F, 1.0F, 0.6F, 0.8F);
/* 140 */       return AxisAlignedBB.func_72330_a(x + 0.0D, y + 0.0D, z + 0.2D, x + 1.0D, y + 0.6D, z + 0.8D);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 145 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9F, 1.0F);
/* 146 */     return AxisAlignedBB.func_72330_a(x + 0.0D, y + 0.0D, z + 0.0D, x + 1.0D, y + 0.8999999761581421D, z + 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
/* 156 */     int meta = bAccess.func_72805_g(x, y, z);
/* 157 */     int direction = getDirectionFromMetadata(meta);
/* 158 */     TEAnvil te = (TEAnvil)bAccess.func_147438_o(x, y, z);
/*     */     
/* 160 */     if (te.anvilTier != AnvilReq.STONE.Tier) {
/*     */       
/* 162 */       if (direction == 0) {
/* 163 */         func_149676_a(0.2F, 0.0F, 0.0F, 0.8F, 0.6F, 1.0F);
/*     */       } else {
/* 165 */         func_149676_a(0.0F, 0.0F, 0.2F, 1.0F, 0.6F, 0.8F);
/*     */       } 
/*     */     } else {
/*     */       
/* 169 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.9F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int i, int j) {
/* 176 */     int meta = getAnvilTypeFromMeta(j);
/*     */     
/* 178 */     if (j == 0 && this == TFCBlocks.anvil)
/*     */     {
/* 180 */       return this.stoneAnvilIcon;
/*     */     }
/*     */ 
/*     */     
/* 184 */     if (i == 0 || i == 1) {
/* 185 */       return this.textureMapTop[meta];
/*     */     }
/* 187 */     return this.textureMapSide[meta];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
/* 194 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 200 */     return TFCBlocks.anvilRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
/* 206 */     int type = getAnvilTypeFromMeta(l);
/* 207 */     if (this == TFCBlocks.anvil)
/*     */     {
/* 209 */       if (type == 0)
/*     */         return; 
/*     */     }
/* 212 */     super.func_149636_a(world, entityplayer, i, j, k, type);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149690_a(World par1World, int x, int y, int z, int meta, float par6, int par7) {
/* 218 */     if (!par1World.field_72995_K) {
/* 219 */       func_149642_a(par1World, x, y, z, new ItemStack((Block)this, 1, meta));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_149642_a(World par1World, int par2, int par3, int par4, ItemStack is) {
/* 225 */     if (!par1World.field_72995_K && par1World.func_82736_K().func_82766_b("doTileDrops")) {
/*     */       
/* 227 */       if (is.func_77960_j() == 0 && this == TFCBlocks.anvil)
/*     */         return; 
/* 229 */       float f = 0.7F;
/* 230 */       double d0 = (par1World.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 231 */       double d1 = (par1World.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 232 */       double d2 = (par1World.field_73012_v.nextFloat() * f) + (1.0F - f) * 0.5D;
/* 233 */       EntityItem entityitem = new EntityItem(par1World, par2 + d0, par3 + d1, par4 + d2, is);
/* 234 */       entityitem.field_145804_b = 10;
/* 235 */       par1World.func_72838_d((Entity)entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
/* 248 */     int meta = world.func_72805_g(i, j, k);
/* 249 */     int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 250 */     byte byte0 = 0;
/* 251 */     if (l == 0)
/* 252 */       byte0 = 8; 
/* 253 */     if (l == 1)
/* 254 */       byte0 = 0; 
/* 255 */     if (l == 2)
/* 256 */       byte0 = 8; 
/* 257 */     if (l == 3)
/* 258 */       byte0 = 0; 
/* 259 */     byte0 = (byte)(byte0 + meta);
/*     */     
/* 261 */     world.func_72921_c(i, j, k, byte0, 3);
/*     */     
/* 263 */     TEAnvil te = (TEAnvil)world.func_147438_o(i, j, k);
/* 264 */     if (this == TFCBlocks.anvil) {
/* 265 */       te.anvilTier = (AnvilReq.getReqFromInt(meta)).Tier;
/* 266 */     } else if (this == TFCBlocks.anvil2) {
/* 267 */       te.anvilTier = (AnvilReq.getReqFromInt2(meta)).Tier;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149749_a(World world, int x, int y, int z, Block block, int metadata) {
/* 273 */     TEAnvil var5 = (TEAnvil)world.func_147438_o(x, y, z);
/*     */     
/* 275 */     if (var5 != null)
/*     */     {
/* 277 */       for (int var6 = 0; var6 < var5.func_70302_i_(); var6++) {
/*     */         
/* 279 */         ItemStack var7 = var5.func_70301_a(var6);
/*     */         
/* 281 */         if (var7 != null) {
/*     */           
/* 283 */           float var8 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/* 284 */           float var9 = world.field_73012_v.nextFloat() * 0.8F + 0.1F;
/*     */ 
/*     */           
/* 287 */           for (float var10 = world.field_73012_v.nextFloat() * 0.8F + 0.1F; var7.field_77994_a > 0; world.func_72838_d((Entity)var12)) {
/*     */             
/* 289 */             int var11 = world.field_73012_v.nextInt(21) + 10;
/*     */             
/* 291 */             if (var11 > var7.field_77994_a)
/* 292 */               var11 = var7.field_77994_a; 
/* 293 */             var7.field_77994_a -= var11;
/* 294 */             EntityItem var12 = new EntityItem(world, (x + var8), (y + var9), (z + var10), new ItemStack(var7.func_77973_b(), var11, var7.func_77960_j()));
/* 295 */             float var13 = 0.05F;
/* 296 */             var12.field_70159_w = ((float)world.field_73012_v.nextGaussian() * var13);
/* 297 */             var12.field_70181_x = ((float)world.field_73012_v.nextGaussian() * var13 + 0.2F);
/* 298 */             var12.field_70179_y = ((float)world.field_73012_v.nextGaussian() * var13);
/* 299 */             if (var7.func_77942_o())
/* 300 */               var12.func_92059_d().func_77982_d((NBTTagCompound)var7.func_77978_p().func_74737_b()); 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 305 */     super.func_149749_a(world, x, y, z, block, metadata);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 311 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getAnvilTypeFromMeta(int j) {
/* 316 */     int l = 7;
/* 317 */     return j & l;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDirectionFromMetadata(int i) {
/* 322 */     int d = i >> 3;
/* 323 */     if (d == 1) {
/* 324 */       return 1;
/*     */     }
/* 326 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 332 */     return (TileEntity)new TEAnvil();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 338 */     this.textureMapTop = new IIcon[(this.anvilId == 0) ? 8 : 3];
/* 339 */     this.textureMapSide = new IIcon[(this.anvilId == 0) ? 8 : 3];
/* 340 */     for (int i = (this.anvilId == 0) ? 1 : 0; i < ((this.anvilId == 0) ? 8 : 3); i++) {
/*     */       
/* 342 */       this.textureMapTop[i] = registerer.func_94245_a("terrafirmacraft:devices/Anvil_" + (i + this.anvilId) + "_Top");
/* 343 */       this.textureMapSide[i] = registerer.func_94245_a("terrafirmacraft:devices/Anvil_" + (i + this.anvilId) + "_Side");
/*     */     } 
/*     */     
/* 346 */     this.stoneAnvilIcon = registerer.func_94245_a("terrafirmacraft:rocks/Gabbro Raw");
/*     */     
/* 348 */     TFC_Textures.anvilHit = registerer.func_94245_a("terrafirmacraft:Anvil Hit");
/* 349 */     TFC_Textures.anvilHitHeavy = registerer.func_94245_a("terrafirmacraft:Anvil Hit Heavy");
/* 350 */     TFC_Textures.anvilHitMedium = registerer.func_94245_a("terrafirmacraft:Anvil Hit Medium");
/* 351 */     TFC_Textures.anvilHitLight = registerer.func_94245_a("terrafirmacraft:Anvil Hit Light");
/* 352 */     TFC_Textures.anvilDraw = registerer.func_94245_a("terrafirmacraft:Anvil Draw");
/* 353 */     TFC_Textures.anvilPunch = registerer.func_94245_a("terrafirmacraft:Anvil Punch");
/* 354 */     TFC_Textures.anvilBend = registerer.func_94245_a("terrafirmacraft:Anvil Bend");
/* 355 */     TFC_Textures.anvilUpset = registerer.func_94245_a("terrafirmacraft:Anvil Upset");
/* 356 */     TFC_Textures.anvilShrink = registerer.func_94245_a("terrafirmacraft:Anvil Shrink");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 362 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
/* 369 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
/* 376 */     return (world.func_147439_a(x, y, z) == this);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */