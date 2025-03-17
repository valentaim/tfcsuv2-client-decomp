/*     */ package com.bioxx.tfc.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Core.CollisionRayTraceStandard;
/*     */ import com.bioxx.tfc.Core.TFC_Textures;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TEMetalSheet;
/*     */ import com.bioxx.tfc.api.Interfaces.ICustomCollision;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.particle.EffectRenderer;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.Explosion;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ public class BlockMetalSheet
/*     */   extends BlockTerraContainer
/*     */   implements ICustomCollision
/*     */ {
/*     */   public IIcon[] icons;
/*  36 */   public String[] metalNames = new String[] { "Bismuth", "Bismuth Bronze", "Black Bronze", "Black Steel", "Blue Steel", "Brass", "Bronze", "Copper", "Gold", "Wrought Iron", "Lead", "Nickel", "Pig Iron", "Platinum", "Red Steel", "Rose Gold", "Silver", "Steel", "Sterling Silver", "Tin", "Zinc" };
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockMetalSheet() {
/*  41 */     super(Material.field_151573_f);
/*  42 */     this.icons = new IIcon[this.metalNames.length];
/*  43 */     func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int par1, Random par2Random, int par3) {
/*  49 */     return Item.func_150899_d(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_149712_f(World world, int x, int y, int z) {
/*  56 */     return this.field_149782_v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess blockAccess, int x, int y, int z, int side) {
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149725_f(World world, int i, int j, int k, int meta) {
/*  69 */     if (world.func_147438_o(i, j, k) instanceof TEMetalSheet) {
/*     */       
/*  71 */       TEMetalSheet te = (TEMetalSheet)world.func_147438_o(i, j, k);
/*  72 */       if (te.sheetStack != null) {
/*     */         
/*  74 */         int stack = 0;
/*  75 */         if (te.topExists()) stack++; 
/*  76 */         if (te.bottomExists()) stack++; 
/*  77 */         if (te.northExists()) stack++; 
/*  78 */         if (te.southExists()) stack++; 
/*  79 */         if (te.eastExists()) stack++; 
/*  80 */         if (te.westExists()) stack++; 
/*  81 */         te.sheetStack.field_77994_a = stack;
/*  82 */         EntityItem ei = new EntityItem(world, i, j, k, te.sheetStack);
/*  83 */         world.func_72838_d((Entity)ei);
/*     */       } else {
/*     */         
/*  86 */         TerraFirmaCraft.LOG.error("Metal sheet block (" + i + ", " + j + ", " + k + ") being broken contains null sheetstack. Please report this on the forums.");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onBlockExploded(World world, int i, int j, int k, Explosion explosion) {
/*  93 */     TEMetalSheet te = (TEMetalSheet)world.func_147438_o(i, j, k);
/*  94 */     if (te != null) {
/*  95 */       te.clearSides();
/*     */     }
/*  97 */     super.onBlockExploded(world, i, j, k, explosion);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 103 */     return TFCBlocks.metalsheetRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 109 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {
/* 121 */     for (int i = 0; i < this.icons.length; i++) {
/* 122 */       this.icons[i] = registerer.func_94245_a("terrafirmacraft:metal/" + this.metalNames[i]);
/*     */     }
/* 124 */     TFC_Textures.sheetBismuth = this.icons[0];
/* 125 */     TFC_Textures.sheetBismuthBronze = this.icons[1];
/* 126 */     TFC_Textures.sheetBlackBronze = this.icons[2];
/* 127 */     TFC_Textures.sheetBlackSteel = this.icons[3];
/* 128 */     TFC_Textures.sheetBlueSteel = this.icons[4];
/* 129 */     TFC_Textures.sheetBrass = this.icons[5];
/* 130 */     TFC_Textures.sheetBronze = this.icons[6];
/* 131 */     TFC_Textures.sheetCopper = this.icons[7];
/* 132 */     TFC_Textures.sheetGold = this.icons[8];
/* 133 */     TFC_Textures.sheetWroughtIron = this.icons[9];
/* 134 */     TFC_Textures.sheetLead = this.icons[10];
/* 135 */     TFC_Textures.sheetNickel = this.icons[11];
/* 136 */     TFC_Textures.sheetPigIron = this.icons[12];
/* 137 */     TFC_Textures.sheetPlatinum = this.icons[13];
/* 138 */     TFC_Textures.sheetRedSteel = this.icons[14];
/* 139 */     TFC_Textures.sheetRoseGold = this.icons[15];
/* 140 */     TFC_Textures.sheetSilver = this.icons[16];
/* 141 */     TFC_Textures.sheetSteel = this.icons[17];
/* 142 */     TFC_Textures.sheetSterlingSilver = this.icons[18];
/* 143 */     TFC_Textures.sheetTin = this.icons[19];
/* 144 */     TFC_Textures.sheetZinc = this.icons[20];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World var1, int var2) {
/* 150 */     return (TileEntity)new TEMetalSheet();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 156 */     if (meta >= 0 && meta < this.icons.length)
/* 157 */       return this.icons[meta]; 
/* 158 */     return this.icons[19];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int meta) {
/* 165 */     TEMetalSheet te = (TEMetalSheet)access.func_147438_o(i, j, k);
/* 166 */     if (te != null) {
/* 167 */       return this.icons[te.metalID];
/*     */     }
/* 169 */     return this.icons[19];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCollisionBoxesToList(World world, int i, int j, int k, List<Object[]> list) {
/* 176 */     TEMetalSheet te = (TEMetalSheet)world.func_147438_o(i, j, k);
/* 177 */     double f0 = 0.0625D;
/* 178 */     double f1 = 0.9375D;
/* 179 */     double yMax = 1.0D;
/* 180 */     double yMin = 0.0D;
/*     */     
/* 182 */     if (te.topExists())
/* 183 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, f1, 0.0D, 1.0D, 1.0D, 1.0D), Integer.valueOf(0) }); 
/* 184 */     if (te.bottomExists())
/* 185 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, f0, 1.0D), Integer.valueOf(1) }); 
/* 186 */     if (te.northExists())
/* 187 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, yMin, 0.0D, 1.0D, yMax, f0), Integer.valueOf(2) }); 
/* 188 */     if (te.southExists())
/* 189 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, yMin, f1, 1.0D, yMax, 1.0D), Integer.valueOf(3) }); 
/* 190 */     if (te.eastExists())
/* 191 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, yMin, 0.0D, f0, yMax, 1.0D), Integer.valueOf(4) }); 
/* 192 */     if (te.westExists()) {
/* 193 */       list.add(new Object[] { AxisAlignedBB.func_72330_a(f1, yMin, 0.0D, 1.0D, yMax, 1.0D), Integer.valueOf(5) });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB aabb, List<AxisAlignedBB> list, Entity entity) {
/* 200 */     ArrayList<Object[]> l = new ArrayList();
/* 201 */     addCollisionBoxesToList(world, i, j, k, l);
/* 202 */     for (Object[] o : l) {
/*     */       
/* 204 */       AxisAlignedBB a = (AxisAlignedBB)o[0];
/* 205 */       if (a != null && aabb.func_72326_a(a)) {
/* 206 */         list.add(a);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) {
/* 213 */     return CollisionRayTraceStandard.collisionRayTrace(this, world, x, y, z, player, view);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149747_d(IBlockAccess world, int x, int y, int z, int side) {
/* 219 */     TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/* 220 */     switch (side) {
/*     */       case 0:
/* 222 */         return te.bottomExists();
/* 223 */       case 1: return te.topExists();
/* 224 */       case 2: return te.northExists();
/* 225 */       case 3: return te.southExists();
/* 226 */       case 4: return te.eastExists();
/* 227 */       case 5: return te.westExists();
/* 228 */     }  return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
/* 235 */     TEMetalSheet te = (TEMetalSheet)world.func_147438_o(x, y, z);
/* 236 */     switch (side) {
/*     */       case DOWN:
/* 238 */         return te.bottomExists();
/* 239 */       case UP: return te.topExists();
/* 240 */       case NORTH: return te.northExists();
/* 241 */       case SOUTH: return te.southExists();
/* 242 */       case EAST: return te.eastExists();
/* 243 */       case WEST: return te.westExists();
/* 244 */     }  return false;
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
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
/* 277 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
/* 284 */     return (world.func_147439_a(x, y, z) == this);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockMetalSheet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */