/*     */ package com.bioxx.tfc.Blocks.Terrain;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TEOre;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockOre
/*     */   extends BlockCollapsible
/*     */ {
/*  33 */   public String[] blockNames = Global.ORE_METAL;
/*     */   protected IIcon[] icons; public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) { if (TFCOptions.enableDebugMode && world.field_72995_K) {
/*     */       int metadata = world.func_72805_g(x, y, z); TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata); TEOre te = (TEOre)world.func_147438_o(x, y, z); if (te != null)
/*     */         TerraFirmaCraft.LOG.info("Ore  BaseID = " + te.baseBlockID + "| BaseMeta =" + te.baseBlockMeta); 
/*  37 */     }  return false; } public BlockOre(Material mat) { super(mat);
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
/* 101 */     this.icons = new IIcon[this.blockNames.length]; func_149675_a(true); func_149647_a(null); }
/*     */   public int[] getDropBlock(World world, int x, int y, int z) { int[] data = { -1, -1 }; DataLayer dl = TFC_Climate.getCacheManager(world).getRockLayerAt(x, z, TFC_Core.getRockLayerFromHeight(world, x, y, z)); if (dl != null) { BlockStone stone = null; if (dl.block instanceof BlockStone)
/*     */         stone = (BlockStone)dl.block;  if (stone != null) {
/*     */         data[0] = Block.func_149682_b(stone.dropBlock); data[1] = dl.data2;
/*     */       }  }
/* 106 */      return data; } public void func_149651_a(IIconRegister iconRegisterer) { for (int i = 0; i < this.blockNames.length; i++)
/* 107 */       this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:ores/" + this.blockNames[i] + " Ore");  } public int func_149692_a(int dmg) { if (dmg == 14 || dmg == 15)
/*     */       return 0;  return dmg; }
/*     */   public int quantityDropped(int meta, int fortune, Random random) { if (meta == 14 || meta == 15)
/*     */       return 1 + random.nextInt(2);  return 1; }
/*     */   public IIcon func_149691_a(int side, int meta) { if (meta >= this.icons.length)
/*     */       return this.icons[0];  return this.icons[meta]; }
/* 113 */   public int func_149645_b() { return TFCBlocks.oreRenderId; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
/* 119 */     if (!world.field_72995_K) {
/*     */       
/* 121 */       boolean dropOres = false;
/* 122 */       boolean hasHammer = false;
/* 123 */       int meta = world.func_72805_g(x, y, z);
/* 124 */       boolean isCoal = (meta == 14 || meta == 15);
/* 125 */       ItemStack itemstack = null;
/* 126 */       if (player != null) {
/*     */         
/* 128 */         TFC_Core.addPlayerExhaustion(player, 0.001F);
/* 129 */         player.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
/* 130 */         dropOres = player.func_146099_a((Block)this);
/* 131 */         ItemStack heldItem = player.func_71045_bC();
/* 132 */         if (heldItem != null) {
/*     */           
/* 134 */           int[] itemIDs = OreDictionary.getOreIDs(heldItem);
/* 135 */           for (int id : itemIDs) {
/*     */             
/* 137 */             String name = OreDictionary.getOreName(id);
/* 138 */             if (name.startsWith("itemHammer")) {
/*     */               
/* 140 */               hasHammer = true;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 147 */       if (player == null || dropOres) {
/*     */         
/* 149 */         if (isCoal) {
/* 150 */           itemstack = new ItemStack(TFCItems.coal, 1 + world.field_73012_v.nextInt(2));
/*     */         } else {
/*     */           
/* 153 */           TEOre te = (TEOre)world.func_147438_o(x, y, z);
/* 154 */           int ore = getOreGrade(te, meta);
/* 155 */           itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(ore));
/*     */         }
/*     */       
/* 158 */       } else if (hasHammer && !isCoal) {
/* 159 */         itemstack = new ItemStack(TFCItems.smallOreChunk, 1, meta);
/*     */       } 
/* 161 */       if (itemstack != null)
/* 162 */         func_149642_a(world, x, y, z, itemstack); 
/*     */     } 
/* 164 */     return world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 176 */     ArrayList<ItemStack> ret = new ArrayList<>();
/* 177 */     TEOre te = (TEOre)world.func_147438_o(x, y, z);
/* 178 */     int ore = getOreGrade(te, metadata);
/*     */     
/* 180 */     int count = quantityDropped(metadata, fortune, world.field_73012_v);
/* 181 */     for (int i = 0; i < count; i++) {
/*     */       ItemStack itemstack;
/*     */       
/* 184 */       if (metadata == 14 || metadata == 15) {
/* 185 */         itemstack = new ItemStack(TFCItems.coal);
/*     */       } else {
/* 187 */         itemstack = new ItemStack(TFCItems.oreChunk, 1, func_149692_a(ore));
/*     */       } 
/* 189 */       ret.add(itemstack);
/*     */     } 
/* 191 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item getDroppedItem(int meta) {
/* 196 */     if (meta == 14 || meta == 15) {
/* 197 */       return TFCItems.coal;
/*     */     }
/* 199 */     return TFCItems.smallOreChunk;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149659_a(Explosion exp) {
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149723_a(World world, int x, int y, int z, Explosion exp) {
/* 211 */     world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBlockExploded(World world, int x, int y, int z, Explosion exp) {
/* 217 */     if (!world.field_72995_K) {
/*     */       ItemStack itemstack;
/* 219 */       TEOre te = (TEOre)world.func_147438_o(x, y, z);
/* 220 */       Random random = new Random();
/*     */       
/* 222 */       int meta = world.func_72805_g(x, y, z);
/* 223 */       int ore = getOreGrade(te, meta);
/*     */       
/* 225 */       if (meta == 14 || meta == 15) {
/* 226 */         itemstack = new ItemStack(TFCItems.coal, 1 + random.nextInt(2));
/*     */       } else {
/* 228 */         itemstack = new ItemStack(TFCItems.oreChunk, 1, ore);
/*     */       } 
/* 230 */       func_149642_a(world, x, y, z, itemstack);
/* 231 */       func_149723_a(world, x, y, z, exp);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOreGrade(TEOre te, int ore) {
/* 237 */     if (te != null) {
/*     */       
/* 239 */       int grade = te.extraData & 0x7;
/* 240 */       if (grade == 1) {
/* 241 */         ore += 35;
/* 242 */       } else if (grade == 2) {
/* 243 */         ore += 49;
/*     */       } 
/* 245 */     }  return ore;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
/* 251 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity createTileEntity(World w, int meta) {
/* 257 */     return (TileEntity)new TEOre();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149674_a(World world, int x, int y, int z, Random rand) {
/* 263 */     if (!world.field_72995_K) {
/* 264 */       scanVisible(world, x, y, z);
/*     */     }
/*     */   }
/*     */   
/*     */   public void scanVisible(World world, int x, int y, int z) {
/* 269 */     if (!world.field_72995_K) {
/* 270 */       TileEntity te = world.func_147438_o(x, y, z);
/* 271 */       if (!(te instanceof TEOre)) {
/* 272 */         TerraFirmaCraft.LOG.warn("TE was not ore on call of BlockOre.scanVisible({}, {}, {}, {})", new Object[] { world, Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(z) });
/*     */         
/*     */         return;
/*     */       } 
/* 276 */       if ((((TEOre)te).extraData & 0x8) == 0 && y < 255 && y > 0 && world.func_72899_e(x, y - 1, z) && world.func_72899_e(x, y + 1, z) && world.func_72899_e(x - 1, y, z) && world.func_72899_e(x + 1, y, z) && world.func_72899_e(x, y, z - 1) && world.func_72899_e(x, y, z + 1) && (!world.func_147439_a(x, y - 1, z).func_149662_c() || !world.func_147439_a(x, y + 1, z).func_149662_c() || !world.func_147439_a(x - 1, y, z).func_149662_c() || !world.func_147439_a(x + 1, y, z).func_149662_c() || !world.func_147439_a(x, y, z - 1).func_149662_c() || !world.func_147439_a(x, y, z + 1).func_149662_c())) {
/* 277 */         ((TEOre)te).setVisible();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block b) {
/* 287 */     if (!world.field_72995_K)
/*     */     {
/* 289 */       scanVisible(world, x, y, z);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */