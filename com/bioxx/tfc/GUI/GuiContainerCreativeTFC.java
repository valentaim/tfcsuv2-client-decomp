package com.bioxx.tfc.GUI;

import com.bioxx.tfc.Containers.ContainerCreativeTFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.gui.inventory.CreativeCrafting;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiContainerCreativeTFC extends InventoryEffectRenderer {
  private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
  private static InventoryBasic inventory = new InventoryBasic("tmp", true, 51);


  private static int selectedTabIndex = CreativeTabs.field_78030_b.func_78021_a();


  private float currentScroll;


  private boolean isScrolling;


  private boolean wasClicking;

  private GuiTextField searchField;

  private List<Slot> backupContainerSlots;

  private Slot zeroSlot;

  private boolean eventTriggered;

  private CreativeCrafting crafting;

  private static int tabPage;

  private int maxPages;


  public GuiContainerCreativeTFC(EntityPlayer par1EntityPlayer) {
    super((Container)new ContainerCreativeTFC(par1EntityPlayer));

    par1EntityPlayer.field_71070_bA = this.field_147002_h;
    this.field_146291_p = true;
    par1EntityPlayer.func_71064_a((StatBase)AchievementList.field_76004_f, 1);
    this.field_147000_g = 136;
    this.field_146999_f = 195;
  }






  public void func_73876_c() {
    if (!this.field_146297_k.field_71442_b.func_78758_h()) {
      this.field_146297_k.func_147108_a((GuiScreen)new GuiInventoryTFC((EntityPlayer)this.field_146297_k.field_71439_g));
    }
  }


  protected void func_146984_a(Slot par1Slot, int par2, int par3, int par4) {
    this.eventTriggered = true;
    boolean flag = (par4 == 1);
    par4 = (par2 == -999 && par4 == 0) ? 4 : par4;



    if (par1Slot == null && selectedTabIndex != CreativeTabs.field_78036_m.func_78021_a() && par4 != 5) {

      InventoryPlayer inventoryplayer = this.field_146297_k.field_71439_g.field_71071_by;

      if (inventoryplayer.func_70445_o() != null)
      {
        if (par3 == 0) {

          this.field_146297_k.field_71439_g.func_70099_a(inventoryplayer.func_70445_o(), 1.5F);
          this.field_146297_k.field_71442_b.func_78752_a(inventoryplayer.func_70445_o());
          inventoryplayer.func_70437_b((ItemStack)null);
        }

        if (par3 == 1)
        {
          ItemStack itemstack = inventoryplayer.func_70445_o().func_77979_a(1);
          this.field_146297_k.field_71439_g.func_70099_a(itemstack, 1.5F);
          this.field_146297_k.field_71442_b.func_78752_a(itemstack);

          if ((inventoryplayer.func_70445_o()).field_77994_a == 0) {
            inventoryplayer.func_70437_b((ItemStack)null);

          }
        }

      }

    }
    else if (par1Slot == this.zeroSlot && flag) {

      for (int l = 0; l < this.field_146297_k.field_71439_g.field_71069_bz.func_75138_a().size(); l++)
      {
        this.field_146297_k.field_71442_b.func_78761_a((ItemStack)null, l);


      }


    }
    else if (selectedTabIndex == CreativeTabs.field_78036_m.func_78021_a()) {

      if (par1Slot == this.zeroSlot)
      {
        this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
      }
      else if (par4 == 4 && par1Slot != null && par1Slot.func_75216_d())
      {
        ItemStack itemstack1 = par1Slot.func_75209_a((par3 == 0) ? 1 : par1Slot.func_75211_c().func_77976_d());
        this.field_146297_k.field_71439_g.func_70099_a(itemstack1, 1.5F);
        this.field_146297_k.field_71442_b.func_78752_a(itemstack1);
      }
      else if (par4 == 4 && this.field_146297_k.field_71439_g.field_71071_by.func_70445_o() != null)
      {
        this.field_146297_k.field_71439_g.func_70099_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o(), 1.5F);
        this.field_146297_k.field_71442_b.func_78752_a(this.field_146297_k.field_71439_g.field_71071_by.func_70445_o());
        this.field_146297_k.field_71439_g.field_71071_by.func_70437_b((ItemStack)null);
      }
      else
      {
        this.field_146297_k.field_71439_g.field_71069_bz.func_75144_a((par1Slot == null) ? par2 : (SlotCreativeInventoryTFC.getSlot((SlotCreativeInventoryTFC)par1Slot)).field_75222_d, par3, par4, (EntityPlayer)this.field_146297_k.field_71439_g);
        this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
      }

    } else if (par4 != 5 && par1Slot != null && par1Slot.field_75224_c == inventory) {

      InventoryPlayer inventoryplayer = this.field_146297_k.field_71439_g.field_71071_by;
      ItemStack itemstack = inventoryplayer.func_70445_o();
      ItemStack itemstack2 = par1Slot.func_75211_c();


      if (par4 == 2) {

        if (itemstack2 != null && par3 >= 0 && par3 < 9) {

          ItemStack itemstack3 = itemstack2.func_77946_l();
          itemstack3.field_77994_a = itemstack3.func_77976_d();
          this.field_146297_k.field_71439_g.field_71071_by.func_70299_a(par3, itemstack3);
          this.field_146297_k.field_71439_g.field_71069_bz.func_75142_b();
        }

        return;
      }

      if (par4 == 3) {

        if (inventoryplayer.func_70445_o() == null && par1Slot.func_75216_d()) {

          ItemStack itemstack3 = par1Slot.func_75211_c().func_77946_l();
          itemstack3.field_77994_a = itemstack3.func_77976_d();
          inventoryplayer.func_70437_b(itemstack3);
        }

        return;
      }

      if (par4 == 4) {

        if (itemstack2 != null) {

          ItemStack itemstack3 = itemstack2.func_77946_l();
          itemstack3.field_77994_a = (par3 == 0) ? 1 : itemstack3.func_77976_d();
          this.field_146297_k.field_71439_g.func_70099_a(itemstack3, 1.5F);
          this.field_146297_k.field_71442_b.func_78752_a(itemstack3);
        }

        return;
      }

      if (itemstack != null && itemstack2 != null && itemstack.func_77969_a(itemstack2) && ItemStack.func_77970_a(itemstack, itemstack2)) {

        if (par3 == 0) {

          if (flag) {
            itemstack.field_77994_a = itemstack.func_77976_d();
          } else if (itemstack.field_77994_a < itemstack.func_77976_d()) {
            itemstack.field_77994_a++;
          }
        } else if (itemstack.field_77994_a <= 1) {

          inventoryplayer.func_70437_b((ItemStack)null);
        }
        else {

          itemstack.field_77994_a--;
        }

      } else if (itemstack2 != null && itemstack == null) {

        inventoryplayer.func_70437_b(ItemStack.func_77944_b(itemstack2));
        itemstack = inventoryplayer.func_70445_o();

        if (flag) {
          itemstack.field_77994_a = itemstack.func_77976_d();
        }
      } else {

        inventoryplayer.func_70437_b((ItemStack)null);
      }

    } else {

      this.field_147002_h.func_75144_a((par1Slot == null) ? par2 : par1Slot.field_75222_d, par3, par4, (EntityPlayer)this.field_146297_k.field_71439_g);

      if (Container.func_94532_c(par3) == 2) {

        for (int l = 0; l < 9; l++) {
          this.field_146297_k.field_71442_b.func_78761_a(this.field_147002_h.func_75139_a(45 + l).func_75211_c(), 36 + l);
        }
      } else if (par1Slot != null) {

        ItemStack itemstack1 = this.field_147002_h.func_75139_a(par1Slot.field_75222_d).func_75211_c();
        this.field_146297_k.field_71442_b.func_78761_a(itemstack1, par1Slot.field_75222_d - this.field_147002_h.field_75151_b.size() + 9 + 36);
      }
    }
  }








  public void func_73866_w_() {
    if (this.field_146297_k.field_71442_b.func_78758_h()) {

      super.func_73866_w_();
      this.field_146292_n.clear();
      Keyboard.enableRepeatEvents(true);
      this.searchField = new GuiTextField(this.field_146289_q, this.field_147003_i + 82, this.field_147009_r + 6, 89, this.field_146289_q.field_78288_b);
      this.searchField.func_146203_f(15);
      this.searchField.func_146185_a(false);
      this.searchField.func_146189_e(false);
      this.searchField.func_146193_g(16777215);
      int i = selectedTabIndex;
      selectedTabIndex = -1;
      setCurrentCreativeTab(CreativeTabs.field_78032_a[i]);
      this.crafting = new CreativeCrafting(this.field_146297_k);
      this.field_146297_k.field_71439_g.field_71069_bz.func_75132_a((ICrafting)this.crafting);
      int tabCount = CreativeTabs.field_78032_a.length;
      if (tabCount > 12)
      {
        this.field_146292_n.add(new GuiButton(101, this.field_147003_i, this.field_147009_r - 50, 20, 20, "<"));
        this.field_146292_n.add(new GuiButton(102, this.field_147003_i + this.field_146999_f - 20, this.field_147009_r - 50, 20, 20, ">"));
        this.maxPages = (tabCount - 12) / 10 + 1;
      }

    } else {

      this.field_146297_k.func_147108_a((GuiScreen)new GuiInventoryTFC((EntityPlayer)this.field_146297_k.field_71439_g));
    }
  }






  public void func_146281_b() {
    super.func_146281_b();

    if (this.field_146297_k.field_71439_g != null && this.field_146297_k.field_71439_g.field_71071_by != null) {
      this.field_146297_k.field_71439_g.field_71069_bz.func_82847_b((ICrafting)this.crafting);
    }
    Keyboard.enableRepeatEvents(false);
  }






  protected void func_73869_a(char par1, int par2) {
    if (!CreativeTabs.field_78032_a[selectedTabIndex].hasSearchBar()) {

      if (GameSettings.func_100015_a(this.field_146297_k.field_71474_y.field_74310_D)) {
        setCurrentCreativeTab(CreativeTabs.field_78027_g);
      } else {
        super.func_73869_a(par1, par2);
      }
    } else {

      if (this.eventTriggered) {

        this.eventTriggered = false;
        this.searchField.func_146180_a("");
      }

      if (!func_146983_a(par2))
      {
        if (this.searchField.func_146201_a(par1, par2)) {
          updateCreativeSearch();
        } else {
          super.func_73869_a(par1, par2);
        }
      }
    }
  }

  private void updateCreativeSearch() {
    ContainerCreativeTFC containercreative = (ContainerCreativeTFC)this.field_147002_h;
    containercreative.itemList.clear();

    CreativeTabs tab = CreativeTabs.field_78032_a[selectedTabIndex];
    if (tab.hasSearchBar() && tab != CreativeTabs.field_78027_g) {

      tab.func_78018_a(containercreative.itemList);
      updateFilteredItems(containercreative);

      return;
    }
    Iterator<Item> iItems = Item.field_150901_e.iterator();
    while (iItems.hasNext()) {

      Item item = iItems.next();
      if (item != null && item.func_77640_w() != null) {
        item.func_150895_a(item, (CreativeTabs)null, containercreative.itemList);
      }
    }
    Enchantment[] aenchantment = Enchantment.field_77331_b;
    int i = aenchantment.length;
    for (int j = 0; j < i; j++) {

      Enchantment enchantment = aenchantment[j];
      if (enchantment != null && enchantment.field_77351_y != null)
        Items.field_151134_bR.func_92113_a(enchantment, containercreative.itemList);
    }
    updateFilteredItems(containercreative);
  }



  private void updateFilteredItems(ContainerCreativeTFC containercreative) {
    Iterator<ItemStack> iterator = containercreative.itemList.iterator();
    String s = this.searchField.func_146179_b().toLowerCase();

    while (iterator.hasNext()) {

      ItemStack itemstack = iterator.next();
      boolean flag = false;
      Iterator<String> iterator1 = itemstack.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x).iterator();
      boolean loop = true;

      while (loop) {

        if (iterator1.hasNext()) {

          String s1 = iterator1.next();
          if (!s1.toLowerCase().contains(s))
            continue;
          flag = true;
        }

        if (!flag)
          iterator.remove();
        loop = false;
      }
    }

    this.currentScroll = 0.0F;
    containercreative.scrollTo(0.0F);
  }






  protected void func_146979_b(int par1, int par2) {
    CreativeTabs creativetabs = CreativeTabs.field_78032_a[selectedTabIndex];
    if (creativetabs != null && creativetabs.func_78019_g()) {
      this.field_146289_q.func_78276_b(I18n.func_135052_a(creativetabs.func_78024_c(), new Object[0]), 8, 6, 4210752);
    }
  }





  protected void func_73864_a(int par1, int par2, int par3) {
    if (par3 == 0) {

      int l = par1 - this.field_147003_i;
      int i1 = par2 - this.field_147009_r;
      CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
      int j1 = acreativetabs.length;

      for (int k1 = 0; k1 < j1; k1++) {

        CreativeTabs creativetabs = acreativetabs[k1];
        if (switchTab(creativetabs, l, i1))
          return;
      }
    }
    super.func_73864_a(par1, par2, par3);
  }







  protected void func_146286_b(int par1, int par2, int par3) {
    if (par3 == 0) {

      int l = par1 - this.field_147003_i;
      int i1 = par2 - this.field_147009_r;
      CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
      int j1 = acreativetabs.length;

      for (int k1 = 0; k1 < j1; k1++) {

        CreativeTabs creativetabs = acreativetabs[k1];
        if (creativetabs != null && switchTab(creativetabs, l, i1)) {

          setCurrentCreativeTab(creativetabs);

          return;
        }
      }
    }
    super.func_146286_b(par1, par2, par3);
  }





  private boolean needsScrollBars() {
    if (CreativeTabs.field_78032_a[selectedTabIndex] == null) return false;
    return (selectedTabIndex != CreativeTabs.field_78036_m.func_78021_a() && CreativeTabs.field_78032_a[selectedTabIndex].func_78017_i() && ((ContainerCreativeTFC)this.field_147002_h).hasMoreThan1PageOfItemsInList());
  }


  private void setCurrentCreativeTab(CreativeTabs par1CreativeTabs) {
    if (par1CreativeTabs == null) {
      return;
    }
    int i = selectedTabIndex;
    selectedTabIndex = par1CreativeTabs.func_78021_a();
    ContainerCreativeTFC containercreative = (ContainerCreativeTFC)this.field_147002_h;
    this.field_147008_s.clear();
    containercreative.itemList.clear();
    par1CreativeTabs.func_78018_a(containercreative.itemList);

    if (par1CreativeTabs == CreativeTabs.field_78036_m) {

      Container container = this.field_146297_k.field_71439_g.field_71069_bz;
      if (this.backupContainerSlots == null) {
        this.backupContainerSlots = containercreative.field_75151_b;
      }
      containercreative.field_75151_b = new ArrayList();
      for (int j = 0; j < container.field_75151_b.size(); j++) {

        SlotCreativeInventoryTFC slotcreativeinventory = new SlotCreativeInventoryTFC(this, container.field_75151_b.get(j), j);
        containercreative.field_75151_b.add(slotcreativeinventory);




        if (j >= 5 && j < 9) {

          int k = j - 5;
          int l = k / 2;
          int i1 = k % 2;
          slotcreativeinventory.field_75223_e = 9 + l * 54;
          slotcreativeinventory.field_75221_f = 6 + i1 * 27;
        }
        else if (j == 50) {

          int k = 2;
          int l = k / 2;
          int i1 = k % 2;
          slotcreativeinventory.field_75223_e = 27 + l * 54;
          slotcreativeinventory.field_75221_f = 6 + i1 * 27;
        }
        else if (j >= 0 && j < 5) {

          slotcreativeinventory.field_75221_f = -2000;
          slotcreativeinventory.field_75223_e = -2000;
        }
        else if (j < container.field_75151_b.size()) {

          int k = j - 9;
          int l = k % 9;
          int i1 = k / 9;
          slotcreativeinventory.field_75223_e = 9 + l * 18;

          if (j >= 36) {
            slotcreativeinventory.field_75221_f = 112;
          } else {
            slotcreativeinventory.field_75221_f = 54 + i1 * 18;
          }
        }
      }
      this.zeroSlot = new Slot((IInventory)inventory, 0, 173, 112);
      containercreative.field_75151_b.add(this.zeroSlot);
    }
    else if (i == CreativeTabs.field_78036_m.func_78021_a()) {

      containercreative.field_75151_b = this.backupContainerSlots;
      this.backupContainerSlots = null;
    }

    if (this.searchField != null)
    {
      if (par1CreativeTabs.hasSearchBar()) {

        this.searchField.func_146189_e(true);
        this.searchField.func_146205_d(false);
        this.searchField.func_146195_b(true);
        this.searchField.func_146180_a("");
        updateCreativeSearch();
      }
      else {

        this.searchField.func_146189_e(false);
        this.searchField.func_146205_d(true);
        this.searchField.func_146195_b(false);
      }
    }

    this.currentScroll = 0.0F;
    containercreative.scrollTo(0.0F);
  }






  public void func_146274_d() {
    super.func_146274_d();
    int i = Mouse.getEventDWheel();

    if (i != 0 && needsScrollBars()) {

      int j = ((ContainerCreativeTFC)this.field_147002_h).itemList.size() / 9 - 5 + 1;

      if (i > 0)
        i = 1;
      if (i < 0) {
        i = -1;
      }
      this.currentScroll = (float)(this.currentScroll - i / j);

      if (this.currentScroll < 0.0F) {
        this.currentScroll = 0.0F;
      }
      if (this.currentScroll > 1.0F) {
        this.currentScroll = 1.0F;
      }
      ((ContainerCreativeTFC)this.field_147002_h).scrollTo(this.currentScroll);
    }
  }






  public void func_73863_a(int par1, int par2, float par3) {
    boolean flag = Mouse.isButtonDown(0);
    int k = this.field_147003_i;
    int l = this.field_147009_r;
    int i1 = k + 175;
    int j1 = l + 18;
    int k1 = i1 + 14;
    int l1 = j1 + 112;

    if (!this.wasClicking && flag && par1 >= i1 && par2 >= j1 && par1 < k1 && par2 < l1) {
      this.isScrolling = needsScrollBars();
    }
    if (!flag) {
      this.isScrolling = false;
    }
    this.wasClicking = flag;

    if (this.isScrolling) {

      this.currentScroll = ((par2 - j1) - 7.5F) / ((l1 - j1) - 15.0F);

      if (this.currentScroll < 0.0F) {
        this.currentScroll = 0.0F;
      }
      if (this.currentScroll > 1.0F) {
        this.currentScroll = 1.0F;
      }
      ((ContainerCreativeTFC)this.field_147002_h).scrollTo(this.currentScroll);
    }

    super.func_73863_a(par1, par2, par3);
    CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
    int start = tabPage * 10;
    int i2 = Math.min(acreativetabs.length, (tabPage + 1) * 10 + 2);
    if (tabPage != 0) start += 2;
    boolean rendered = false;

    for (int j2 = start; j2 < i2; j2++) {

      CreativeTabs creativetabs = acreativetabs[j2];

      if (creativetabs != null && renderCreativeInventoryHoveringText(creativetabs, par1, par2)) {

        rendered = true;

        break;
      }
    }
    if (!rendered && !renderCreativeInventoryHoveringText(CreativeTabs.field_78027_g, par1, par2)) {
      renderCreativeInventoryHoveringText(CreativeTabs.field_78036_m, par1, par2);
    }
    if (this.zeroSlot != null && selectedTabIndex == CreativeTabs.field_78036_m.func_78021_a() &&
      func_146978_c(this.zeroSlot.field_75223_e, this.zeroSlot.field_75221_f, 16, 16, par1, par2))
    {
      func_146279_a(I18n.func_135052_a("inventory.binSlot", new Object[0]), par1, par2);
    }

    if (this.maxPages != 0) {

      String page = String.format("%d / %d", new Object[] { Integer.valueOf(tabPage + 1), Integer.valueOf(this.maxPages + 1) });
      int width = this.field_146289_q.func_78256_a(page);
      GL11.glDisable(2896);
      this.field_73735_i = 300.0F;
      field_146296_j.field_77023_b = 300.0F;
      this.field_146289_q.func_78276_b(page, this.field_147003_i + this.field_146999_f / 2 - width / 2, this.field_147009_r - 44, -1);
      this.field_73735_i = 0.0F;
      field_146296_j.field_77023_b = 0.0F;
    }

    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    GL11.glDisable(2896);
  }



  protected void func_146285_a(ItemStack par1ItemStack, int par2, int par3) {
    if (selectedTabIndex == CreativeTabs.field_78027_g.func_78021_a()) {

      List<String> list = par1ItemStack.func_82840_a((EntityPlayer)this.field_146297_k.field_71439_g, this.field_146297_k.field_71474_y.field_82882_x);
      CreativeTabs creativetabs = par1ItemStack.func_77973_b().func_77640_w();

      if (creativetabs == null && par1ItemStack.func_77973_b() == Items.field_151134_bR) {

        Map map = EnchantmentHelper.func_82781_a(par1ItemStack);

        if (map.size() == 1) {

          Enchantment enchantment = Enchantment.field_77331_b[((Integer)map.keySet().iterator().next()).intValue()];
          CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
          int k = acreativetabs.length;

          for (int l = 0; l < k; l++) {

            CreativeTabs creativetabs1 = acreativetabs[l];
            if (creativetabs1.func_111226_a(enchantment.field_77351_y)) {

              creativetabs = creativetabs1;

              break;
            }
          }
        }
      }
      if (creativetabs != null) {
        list.add(1, EnumChatFormatting.BOLD.toString() + EnumChatFormatting.BLUE + I18n.func_135052_a(creativetabs.func_78024_c(), new Object[0]));
      }
      for (int i1 = 0; i1 < list.size(); i1++) {

        if (i1 == 0) {
          list.set(i1, (par1ItemStack.func_77953_t()).field_77937_e + (String)list.get(i1));
        } else {
          list.set(i1, EnumChatFormatting.GRAY + (String)list.get(i1));
        }
      }
      func_146283_a(list, par2, par3);
    }
    else {

      super.func_146285_a(par1ItemStack, par2, par3);
    }
  }






  protected void func_146976_a(float par1, int par2, int par3) {
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    RenderHelper.func_74520_c();
    CreativeTabs creativetabs = CreativeTabs.field_78032_a[selectedTabIndex];
    CreativeTabs[] acreativetabs = CreativeTabs.field_78032_a;
    int k = acreativetabs.length;


    int start = tabPage * 10;
    k = Math.min(acreativetabs.length, (tabPage + 1) * 10 + 2);
    if (tabPage != 0) start += 2;
    GL11.glEnable(3008); int l;
    for (l = start; l < k; l++) {

      CreativeTabs creativetabs1 = acreativetabs[l];
      this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
      if (creativetabs1 != null && creativetabs1.func_78021_a() != selectedTabIndex) {
        renderCreativeTab(creativetabs1);
      }
    }
    if (tabPage != 0) {

      if (creativetabs != CreativeTabs.field_78027_g) {

        this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
        renderCreativeTab(CreativeTabs.field_78027_g);
      }
      if (creativetabs != CreativeTabs.field_78036_m) {

        this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);
        renderCreativeTab(CreativeTabs.field_78036_m);
      }
    }
    this.field_146297_k.func_110434_K().func_110577_a(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + creativetabs.func_78015_f()));
    func_73729_b(this.field_147003_i, this.field_147009_r, 0, 0, this.field_146999_f, this.field_147000_g);
    this.searchField.func_146194_f();
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int i1 = this.field_147003_i + 175;
    k = this.field_147009_r + 18;
    l = k + 112;
    this.field_146297_k.func_110434_K().func_110577_a(TEXTURE);

    if (creativetabs.func_78017_i()) {
      func_73729_b(i1, k + (int)((l - k - 17) * this.currentScroll), 232 + (needsScrollBars() ? 0 : 12), 0, 12, 15);
    }
    if (creativetabs.getTabPage() != tabPage &&
      creativetabs != CreativeTabs.field_78027_g && creativetabs != CreativeTabs.field_78036_m) {
      return;
    }
    renderCreativeTab(creativetabs);

    if (creativetabs == CreativeTabs.field_78036_m)
      GuiInventoryTFC.drawPlayerModel(this.field_147003_i + 43, this.field_147009_r + 45, 20, (this.field_147003_i + 43 - par2), (this.field_147009_r + 45 - 30 - par3), (EntityLivingBase)this.field_146297_k.field_71439_g);
  }

  protected boolean switchTab(CreativeTabs par1CreativeTabs, int par2, int par3) {
    int i1;
    if (par1CreativeTabs.getTabPage() != tabPage &&
      par1CreativeTabs != CreativeTabs.field_78027_g && par1CreativeTabs != CreativeTabs.field_78036_m) {
      return false;
    }
    int k = par1CreativeTabs.func_78020_k();
    int l = 28 * k;
    byte b0 = 0;

    if (k == 5) {
      l = this.field_146999_f - 28 + 2;
    } else if (k > 0) {
      l += k;
    }


    if (par1CreativeTabs.func_78023_l()) {
      i1 = b0 - 32;
    } else {
      i1 = b0 + this.field_147000_g;
    }
    return (par2 >= l && par2 <= l + 28 && par3 >= i1 && par3 <= i1 + 32);
  }






  protected boolean renderCreativeInventoryHoveringText(CreativeTabs par1CreativeTabs, int par2, int par3) {
    int i1, k = par1CreativeTabs.func_78020_k();
    int l = 28 * k;
    byte b0 = 0;

    if (k == 5) {
      l = this.field_146999_f - 28 + 2;
    } else if (k > 0) {
      l += k;
    }


    if (par1CreativeTabs.func_78023_l()) {
      i1 = b0 - 32;
    } else {
      i1 = b0 + this.field_147000_g;
    }
    if (func_146978_c(l + 3, i1 + 3, 23, 27, par2, par3)) {

      func_146279_a(I18n.func_135052_a(par1CreativeTabs.func_78024_c(), new Object[0]), par2, par3);
      return true;
    }


    return false;
  }






  protected void renderCreativeTab(CreativeTabs par1CreativeTabs) {
    boolean flag = (par1CreativeTabs.func_78021_a() == selectedTabIndex);
    boolean flag1 = par1CreativeTabs.func_78023_l();
    int i = par1CreativeTabs.func_78020_k();
    int j = i * 28;
    int k = 0;
    int l = this.field_147003_i + 28 * i;
    int i1 = this.field_147009_r;
    byte b0 = 32;

    if (flag) {
      k += 32;
    }
    if (i == 5) {
      l = this.field_147003_i + this.field_146999_f - 28;
    } else if (i > 0) {
      l += i;
    }
    if (flag1) {

      i1 -= 28;
    }
    else {

      k += 64;
      i1 += this.field_147000_g - 4;
    }

    GL11.glDisable(2896);
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    GL11.glEnable(3008);
    func_73729_b(l, i1, j, k, 28, b0);
    this.field_73735_i = 100.0F;
    field_146296_j.field_77023_b = 100.0F;
    l += 6;
    i1 += 8 + (flag1 ? 1 : -1);
    GL11.glEnable(2896);
    GL11.glEnable(32826);
    ItemStack itemstack = par1CreativeTabs.func_151244_d();
    if (itemstack != null) {

      field_146296_j.func_82406_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, l, i1);
      field_146296_j.func_77021_b(this.field_146289_q, this.field_146297_k.func_110434_K(), itemstack, l, i1);
    }
    GL11.glDisable(2896);
    field_146296_j.field_77023_b = 0.0F;
    this.field_73735_i = 0.0F;
  }






  protected void func_146284_a(GuiButton par1GuiButton) {
    if (par1GuiButton.field_146127_k == 0) {
      this.field_146297_k.func_147108_a((GuiScreen)new GuiAchievements((GuiScreen)this, this.field_146297_k.field_71439_g.func_146107_m()));
    }
    if (par1GuiButton.field_146127_k == 1) {
      this.field_146297_k.func_147108_a((GuiScreen)new GuiStats((GuiScreen)this, this.field_146297_k.field_71439_g.func_146107_m()));
    }
    if (par1GuiButton.field_146127_k == 101) {
      tabPage = Math.max(tabPage - 1, 0);
    } else if (par1GuiButton.field_146127_k == 102) {
      tabPage = Math.min(tabPage + 1, this.maxPages);
    }
  }




  public int getCurrentTabIndex() {
    return selectedTabIndex;
  }





  public static InventoryBasic getInventory() {
    return inventory;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\GuiContainerCreativeTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */