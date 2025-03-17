/*     */ package com.bioxx.tfc.Blocks.Devices;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockTerraContainer;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.TileEntities.TEToolRack;
/*     */ import com.bioxx.tfc.api.Constant.Global;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
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
/*     */ public class BlockToolRack
/*     */   extends BlockTerraContainer
/*     */ {
/*     */   protected String[] woodNames;
/*     */   
/*     */   public BlockToolRack() {
/*  45 */     super(Material.field_151575_d);
/*  46 */     func_149647_a(TFCTabs.TFC_DECORATION);
/*  47 */     this.woodNames = Global.WOOD_ALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149662_c() {
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
/*  59 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
/*  65 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149686_d() {
/*  71 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149645_b() {
/*  77 */     return TFCBlocks.toolRackRenderId;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
/*  83 */     if (!world.field_72995_K) {
/*     */       
/*  85 */       TileEntity te = world.func_147438_o(x, y, z);
/*  86 */       if (te instanceof TEToolRack) {
/*     */         
/*  88 */         TEToolRack tet = (TEToolRack)te;
/*  89 */         int dir = world.func_72805_g(x, y, z);
/*  90 */         if (dir == 0) {
/*     */           
/*  92 */           if (hitX < 0.5D && hitY > 0.5D) {
/*  93 */             handleArea(world, x, y, z, entityplayer, tet, 0, 0);
/*  94 */           } else if (hitX > 0.5D && hitY > 0.5D) {
/*  95 */             handleArea(world, x, y, z, entityplayer, tet, 1, 0);
/*  96 */           } else if (hitX < 0.5D) {
/*  97 */             handleArea(world, x, y, z, entityplayer, tet, 2, 0);
/*  98 */           } else if (hitX > 0.5D) {
/*  99 */             handleArea(world, x, y, z, entityplayer, tet, 3, 0);
/*     */           } 
/* 101 */         } else if (dir == 1) {
/*     */           
/* 103 */           if (hitZ < 0.5D && hitY > 0.5D) {
/* 104 */             handleArea(world, x, y, z, entityplayer, tet, 0, 1);
/* 105 */           } else if (hitZ > 0.5D && hitY > 0.5D) {
/* 106 */             handleArea(world, x, y, z, entityplayer, tet, 1, 1);
/* 107 */           } else if (hitZ < 0.5D) {
/* 108 */             handleArea(world, x, y, z, entityplayer, tet, 2, 1);
/* 109 */           } else if (hitZ > 0.5D) {
/* 110 */             handleArea(world, x, y, z, entityplayer, tet, 3, 1);
/*     */           } 
/* 112 */         } else if (dir == 2) {
/*     */           
/* 114 */           if (hitX < 0.5D && hitY > 0.5D) {
/* 115 */             handleArea(world, x, y, z, entityplayer, tet, 0, 2);
/* 116 */           } else if (hitX > 0.5D && hitY > 0.5D) {
/* 117 */             handleArea(world, x, y, z, entityplayer, tet, 1, 2);
/* 118 */           } else if (hitX < 0.5D) {
/* 119 */             handleArea(world, x, y, z, entityplayer, tet, 2, 2);
/* 120 */           } else if (hitX > 0.5D) {
/* 121 */             handleArea(world, x, y, z, entityplayer, tet, 3, 2);
/*     */           } 
/* 123 */         } else if (dir == 3) {
/*     */           
/* 125 */           if (hitZ < 0.5D && hitY > 0.5D) {
/* 126 */             handleArea(world, x, y, z, entityplayer, tet, 0, 3);
/* 127 */           } else if (hitZ > 0.5D && hitY > 0.5D) {
/* 128 */             handleArea(world, x, y, z, entityplayer, tet, 1, 3);
/* 129 */           } else if (hitZ < 0.5D) {
/* 130 */             handleArea(world, x, y, z, entityplayer, tet, 2, 3);
/* 131 */           } else if (hitZ > 0.5D) {
/* 132 */             handleArea(world, x, y, z, entityplayer, tet, 3, 3);
/*     */           } 
/* 134 */         }  world.func_147471_g(x, y, z);
/*     */         
/* 136 */         return true;
/*     */       } 
/*     */     } 
/* 139 */     return false;
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
/*     */   private void handleArea(World world, int x, int y, int z, EntityPlayer entityplayer, TEToolRack te, int slot, int dir) {
/* 155 */     boolean hasToolInHand = (entityplayer.func_71045_bC() != null && (entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemTool || entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemHoe || entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemProPick || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemBow || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemSword || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemAxe || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemSpade || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemShears || entityplayer.func_71045_bC().func_77977_a().toString().contains("crowbar") || entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemSpindle));
/* 156 */     if (te.storage[slot] == null && hasToolInHand) {
/*     */       
/* 158 */       te.storage[slot] = entityplayer.func_71045_bC().func_77946_l();
/* 159 */       entityplayer.field_71071_by.field_70462_a[entityplayer.field_71071_by.field_70461_c] = null;
/*     */     }
/* 161 */     else if (te.storage[slot] != null) {
/*     */       
/* 163 */       te.ejectItem(slot, dir);
/* 164 */       te.storage[slot] = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
/* 171 */     if (!world.field_72995_K) {
/*     */ 
/*     */       
/* 174 */       TileEntity te = world.func_147438_o(x, y, z);
/* 175 */       if (te instanceof TEToolRack) {
/*     */         
/* 177 */         TEToolRack rack = (TEToolRack)te;
/* 178 */         func_149642_a(world, x, y, z, new ItemStack(TFCBlocks.toolRack, 1, rack.woodType));
/*     */       } 
/*     */     } 
/* 181 */     return world.func_147468_f(x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
/* 195 */     ArrayList<ItemStack> ret = new ArrayList<>();
/*     */     
/* 197 */     int damageValue = func_149643_k(world, x, y, z);
/* 198 */     ret.add(new ItemStack((Block)this, 1, damageValue));
/*     */     
/* 200 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TileEntity func_149915_a(World world, int meta) {
/* 206 */     return (TileEntity)new TEToolRack();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149719_a(IBlockAccess access, int x, int y, int z) {
/* 212 */     int dir = access.func_72805_g(x, y, z);
/* 213 */     if (dir == 0) {
/* 214 */       func_149676_a(0.0F, 0.0F, 0.85F, 1.0F, 1.0F, 1.0F);
/* 215 */     } else if (dir == 1) {
/* 216 */       func_149676_a(0.0F, 0.0F, 0.0F, 0.15F, 1.0F, 1.0F);
/* 217 */     } else if (dir == 2) {
/* 218 */       func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.15F);
/* 219 */     } else if (dir == 3) {
/* 220 */       func_149676_a(0.85F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
/* 226 */     int dir = world.func_72805_g(x, y, z);
/* 227 */     if (dir == 0)
/* 228 */       return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.85F), (x + 1.0F), (y + 1.0F), (z + 1.0F)); 
/* 229 */     if (dir == 1)
/* 230 */       return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.0F), (x + 0.15F), (y + 1.0F), (z + 1.0F)); 
/* 231 */     if (dir == 2)
/* 232 */       return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.0F), (x + 1.0F), (y + 1.0F), (z + 0.15F)); 
/* 233 */     if (dir == 3) {
/* 234 */       return AxisAlignedBB.func_72330_a((x + 0.85F), (y + 0.0F), (z + 0.0F), (x + 1.0F), (y + 1.0F), (z + 1.0F));
/*     */     }
/* 236 */     return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 1), (z + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149695_a(World world, int x, int y, int z, Block block) {
/* 242 */     int dir = world.func_72805_g(x, y, z);
/*     */     
/* 244 */     if (dir == 0) {
/*     */       
/* 246 */       if (!world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH)) {
/* 247 */         removedByPlayer(world, (EntityPlayer)null, x, y, z);
/*     */       }
/* 249 */     } else if (dir == 1) {
/*     */       
/* 251 */       if (!world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.EAST)) {
/* 252 */         removedByPlayer(world, (EntityPlayer)null, x, y, z);
/*     */       }
/* 254 */     } else if (dir == 2) {
/*     */       
/* 256 */       if (!world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH)) {
/* 257 */         removedByPlayer(world, (EntityPlayer)null, x, y, z);
/*     */       }
/* 259 */     } else if (dir == 3) {
/*     */       
/* 261 */       if (!world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.WEST)) {
/* 262 */         removedByPlayer(world, (EntityPlayer)null, x, y, z);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
/* 269 */     if (side == 4) return 3; 
/* 270 */     if (side == 5) return 1; 
/* 271 */     if (side == 2) return 0; 
/* 272 */     if (side == 3) return 2;
/*     */     
/* 274 */     return 5;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
/* 280 */     TileEntity te = world.func_147438_o(x, y, z);
/* 281 */     if (te instanceof TEToolRack) {
/*     */       
/* 283 */       TEToolRack rack = (TEToolRack)te;
/* 284 */       rack.woodType = (byte)is.func_77960_j();
/* 285 */       world.func_147471_g(x, y, z);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_149707_d(World world, int x, int y, int z, int side) {
/* 292 */     if (func_149742_c(world, x, y, z)) {
/*     */       
/* 294 */       if (side == 5 && world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.EAST))
/* 295 */         return true; 
/* 296 */       if (side == 4 && world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.WEST))
/* 297 */         return true; 
/* 298 */       if (side == 2 && world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH))
/* 299 */         return true; 
/* 300 */       if (side == 3 && world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH))
/* 301 */         return true; 
/*     */     } 
/* 303 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/* 314 */     for (int i = 0; i < this.woodNames.length; i++) {
/* 315 */       list.add(new ItemStack(item, 1, i));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) {
/* 321 */     TEToolRack te = (TEToolRack)bAccess.func_147438_o(x, y, z);
/*     */     
/* 323 */     if (te.woodType > 15)
/* 324 */       return TFCBlocks.woodSupportV2.func_149691_a(side, te.woodType); 
/* 325 */     return TFCBlocks.woodSupportV.func_149691_a(side, te.woodType);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon func_149691_a(int side, int meta) {
/* 331 */     if (meta > 15)
/* 332 */       return TFCBlocks.woodSupportV2.func_149691_a(side, meta); 
/* 333 */     return TFCBlocks.woodSupportV.func_149691_a(side, meta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149692_a(int dmg) {
/* 339 */     return dmg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_149651_a(IIconRegister registerer) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
/* 352 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_149643_k(World world, int x, int y, int z) {
/* 361 */     TileEntity te = world.func_147438_o(x, y, z);
/* 362 */     if (te instanceof TEToolRack)
/* 363 */       return ((TEToolRack)te).woodType; 
/* 364 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockToolRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */