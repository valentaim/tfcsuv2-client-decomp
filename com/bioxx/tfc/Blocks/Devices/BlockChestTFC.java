/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.TileEntities.TEChest;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.InventoryLargeChest;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockChestTFC
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   private String[] woodNames;
/*     */   
/*     */   public BlockChestTFC() {
/*  40 */     super(Material.field_151575_d);
/*  41 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*  42 */     func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*  43 */     this.woodNames = Global.WOOD_ALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int var2) {
/*  49 */     return (TileEntity)new TEChest();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149643_k(World world, int x, int y, int z) {
/*  55 */     TileEntity te = world.func_147438_o(x, y, z);
/*  56 */     if (te instanceof TEChest)
/*  57 */       return ((TEChest)te).type; 
/*  58 */     return 0;
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
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/*  72 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Item func_149650_a(int metadata, Random rand, int fortune) {
/*  78 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
/*  86 */     for (int i = 0; i < this.woodNames.length; i++) {
/*  87 */       par3List.add(new ItemStack((Block)this, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_149725_f(World world, int i, int j, int k, int meta) {
/*  93 */     if (!world.field_72995_K && world.func_82736_K().func_82766_b("doTileDrops")) {
/*     */       
/*  95 */       int damageValue = func_149643_k(world, i, j, k);
/*  96 */       EntityItem ei = new EntityItem(world, i, j, k, new ItemStack(TFCBlocks.chest, 1, damageValue));
/*  97 */       world.func_72838_d((Entity)ei);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
/* 104 */     if (world.field_72995_K)
/*     */     {
/* 106 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 110 */     IInventory iinventory = getInventory(world, x, y, z);
/*     */     
/* 112 */     if (iinventory != null) {
/* 113 */       player.openGui(TerraFirmaCraft.instance, 29, world, x, y, z);
/*     */     }
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase player, ItemStack itemStack) {
/* 125 */     byte chestSide = 0;
/* 126 */     int facingDir = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 127 */     int secFacingDir = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F)) & 0x3;
/*     */     
/* 129 */     int facingN = 0;
/* 130 */     int facingE = 1;
/* 131 */     int facingS = 2;
/* 132 */     int facingW = 3;
/*     */     
/* 134 */     byte sideN = 2;
/* 135 */     byte sideS = 3;
/* 136 */     byte sideE = 5;
/* 137 */     byte sideW = 4;
/*     */     
/* 139 */     if (facingDir == 0) chestSide = 2; 
/* 140 */     if (facingDir == 1) chestSide = 5; 
/* 141 */     if (facingDir == 2) chestSide = 3; 
/* 142 */     if (facingDir == 3) chestSide = 4;
/*     */     
/* 144 */     ForgeDirection adjDirection = getAdjacentChestDirection((IBlockAccess)world, x, y, z, itemStack.func_77960_j());
/* 145 */     if (adjDirection == ForgeDirection.UNKNOWN) {
/*     */       
/* 147 */       world.func_72921_c(x, y, z, chestSide, 3);
/*     */     }
/*     */     else {
/*     */       
/* 151 */       switch (adjDirection) {
/*     */         
/*     */         case NORTH:
/*     */         case SOUTH:
/* 155 */           if (chestSide == 2 || chestSide == 3) {
/*     */             
/* 157 */             if (secFacingDir == 1 || secFacingDir == 0) chestSide = 5; 
/* 158 */             if (secFacingDir == 3 || secFacingDir == 2) chestSide = 4;
/*     */           
/*     */           } 
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 165 */           if (chestSide == 5 || chestSide == 4) {
/*     */             
/* 167 */             chestSide = 2;
/* 168 */             if (secFacingDir == 0 || secFacingDir == 3) chestSide = 2; 
/* 169 */             if (secFacingDir == 2 || secFacingDir == 1) chestSide = 3;
/*     */           
/*     */           } 
/*     */           break;
/*     */       } 
/*     */       
/* 175 */       world.func_72921_c(x, y, z, chestSide, 3);
/* 176 */       world.func_72921_c(x + adjDirection.offsetX, y, z + adjDirection.offsetZ, chestSide, 3);
/*     */     } 
/*     */     
/* 179 */     if (itemStack.func_82837_s()) {
/* 180 */       ((TEChest)world.func_147438_o(x, y, z)).func_145976_a(itemStack.func_82833_r());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/* 192 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/* 198 */     return TFCBlocks.chestRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess access, int x, int y, int z) {
/* 205 */     TEChest chest = (TEChest)access.func_147438_o(x, y, z);
/* 206 */     if (chest != null) {
/*     */       
/* 208 */       ForgeDirection adjDirection = getAdjacentChestDirection(access, x, y, z, chest.type);
/* 209 */       switch (adjDirection) {
/*     */         
/*     */         case NORTH:
/* 212 */           func_149676_a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
/*     */           return;
/*     */         case SOUTH:
/* 215 */           func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
/*     */           return;
/*     */         case EAST:
/* 218 */           func_149676_a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
/*     */           return;
/*     */         case WEST:
/* 221 */           func_149676_a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */           return;
/*     */       } 
/*     */       
/* 225 */       func_149676_a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149726_b(World world, int x, int y, int z) {
/* 234 */     super.func_149726_b(world, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void unifyAdjacentChests(World world, int x, int y, int z) {}
/*     */ 
/*     */   
/*     */   private boolean isChestOfType(IBlockAccess world, int x, int y, int z, int type) {
/* 243 */     if (world.func_147439_a(x, y, z) == this) {
/*     */       
/* 245 */       TEChest chest = (TEChest)world.func_147438_o(x, y, z);
/* 246 */       if (chest != null) return (chest.type == type); 
/*     */     } 
/* 248 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private ForgeDirection getAdjacentChestDirection(IBlockAccess world, int x, int y, int z, int type) {
/* 253 */     ForgeDirection[] dirs = { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
/* 254 */     for (ForgeDirection dir : dirs) {
/*     */       
/* 256 */       if (isChestOfType(world, x + dir.offsetX, y, z + dir.offsetZ, type))
/* 257 */         return dir; 
/*     */     } 
/* 259 */     return ForgeDirection.UNKNOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149705_a(World world, int x, int y, int z, int side, ItemStack itemStack) {
/* 269 */     int type = itemStack.func_77960_j();
/*     */     
/* 271 */     ForgeDirection adjDirection = ForgeDirection.UNKNOWN;
/* 272 */     ForgeDirection[] dirs = { ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST };
/* 273 */     for (ForgeDirection dir : dirs) {
/*     */       
/* 275 */       if (isChestOfType((IBlockAccess)world, x + dir.offsetX, y, z + dir.offsetZ, type)) {
/*     */         
/* 277 */         if (adjDirection != ForgeDirection.UNKNOWN) {
/* 278 */           return false;
/*     */         }
/* 280 */         adjDirection = dir;
/*     */       } 
/*     */     } 
/* 283 */     if (adjDirection == ForgeDirection.UNKNOWN)
/*     */     {
/*     */       
/* 286 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 291 */     ForgeDirection doubleDirection = getAdjacentChestDirection((IBlockAccess)world, x + adjDirection.offsetX, y, z + adjDirection.offsetZ, type);
/*     */     
/* 293 */     return (doubleDirection == ForgeDirection.UNKNOWN);
/*     */   }
/*     */ 
/*     */   
/*     */   public IInventory getInventory(World world, int x, int y, int z) {
/* 298 */     TEChest tEChest4, tEChest3, tEChest2, tEChest8, tEChest7, tEChest6, chest = (TEChest)world.func_147438_o(x, y, z);
/*     */     
/* 300 */     if (chest == null)
/*     */     {
/*     */       
/* 303 */       return null;
/*     */     }
/* 305 */     if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN))
/*     */     {
/*     */       
/* 308 */       return null;
/*     */     }
/*     */     
/* 311 */     ForgeDirection adjDirection = getAdjacentChestDirection((IBlockAccess)world, x, y, z, chest.type);
/* 312 */     if (adjDirection == ForgeDirection.UNKNOWN)
/*     */     {
/*     */       
/* 315 */       return (IInventory)chest;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 320 */     TEChest adjChest = (TEChest)world.func_147438_o(x + adjDirection.offsetX, y, z + adjDirection.offsetZ);
/* 321 */     switch (adjDirection)
/*     */     
/*     */     { case NORTH:
/* 324 */         tEChest4 = chest;
/* 325 */         tEChest8 = adjChest;
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
/* 341 */         return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest4, (IInventory)tEChest8);case SOUTH: tEChest7 = chest; tEChest3 = adjChest; return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest3, (IInventory)tEChest7);case EAST: tEChest6 = chest; tEChest2 = adjChest; return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest2, (IInventory)tEChest6); }  TEChest tEChest1 = chest; TEChest tEChest5 = adjChest; return (IInventory)new InventoryLargeChest("container.chestDouble", (IInventory)tEChest1, (IInventory)tEChest5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149651_a(IIconRegister register) {
/* 350 */     this.field_149761_L = register.func_94245_a("planks_oak");
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockChestTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */