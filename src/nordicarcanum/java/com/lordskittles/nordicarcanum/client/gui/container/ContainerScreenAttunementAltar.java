package com.lordskittles.nordicarcanum.client.gui.container;

import com.lordskittles.arcanumapi.client.gui.container.ContainerScreenBase;
import com.lordskittles.arcanumapi.client.gui.utilities.Button;
import com.lordskittles.arcanumapi.client.gui.utilities.GUISide;
import com.lordskittles.arcanumapi.client.gui.utilities.Tab;
import com.lordskittles.arcanumapi.client.gui.utilities.TabGroup;
import com.lordskittles.arcanumapi.common.math.UVInt;
import com.lordskittles.arcanumapi.common.math.Vector2Int;
import com.lordskittles.arcanumapi.magic.schools.MagicSchool;
import com.lordskittles.arcanumapi.magic.spells.Spell;
import com.lordskittles.nordicarcanum.common.inventory.containers.ContainerAttunementAltar;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntityAttunementAltar;
import com.lordskittles.nordicarcanum.common.tileentity.TileEntitySigilPodium;
import com.lordskittles.nordicarcanum.core.NordicArcanum;
import com.lordskittles.nordicarcanum.core.NordicNames;
import com.lordskittles.nordicarcanum.core.NordicResourceLocations;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.INestedGuiEventHandler;
import net.minecraft.client.renderer.Rectangle2d;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;
import java.util.List;

public class ContainerScreenAttunementAltar extends ContainerScreenBase<ContainerAttunementAltar> implements INestedGuiEventHandler
{
    public TileEntityAttunementAltar Tile;

    private boolean multiblockValid = false;
    private List<Button> buttons = new ArrayList<>();
    private List<MagicSchool> schools = new ArrayList<>();

    private final ResourceLocation backgroundTexture = NordicArcanum.RL("textures/gui/attunement_altar_background.png");
    private final Rectangle2d bgRect = new Rectangle2d(4, 24, 204, 174);

    private float scale = 1f;
    private Vector2Int position = new Vector2Int(0, 0);

    private TabGroup group;

    public ContainerScreenAttunementAltar(ContainerAttunementAltar container, PlayerInventory inventory, ITextComponent title)
    {
        super(NordicArcanum.MODID, NordicNames.ATTUNEMENT_ALTAR, container, inventory, title);

        setTextureRect(0, 0, 212, 225);

        this.Tile = container.getTile();
        this.multiblockValid = this.Tile.isMultiblockFormed;
    }

    @Override
    public boolean mouseScrolled(double p_mouseScrolled_1_, double p_mouseScrolled_3_, double p_mouseScrolled_5_)
    {
        this.scale += p_mouseScrolled_5_ * 0.25f;
        this.scale = MathHelper.clamp(this.scale, 0.25f, 1.5f);

        return super.mouseScrolled(p_mouseScrolled_1_, p_mouseScrolled_3_, p_mouseScrolled_5_);
    }

    @Override
    protected void init()
    {
        super.init();

        this.buttons.clear();

        this.schools = getSchools();

        this.group = new TabGroup(
                new Vector2Int(0, 10),
                new UVInt(24, 44),
                new UVInt(0, 44),
                new Vector2Int(24, 24),
                new Vector2Int(20, 24),
                NordicResourceLocations.BUTTON_TEXTURES,
                this.minecraft,
                this::blit
        );
        this.group.setup(this.schools.size(), 0, GUISide.Left, Tab.class);

        rebuildButtonList();
    }

    private void rebuildButtonList()
    {
        int index = this.group.getActiveIndex();
        if (index < this.schools.size())
        {
            this.buttons.clear();
            this.buttons.addAll(buildSpellButtons(this.schools.get(index).getSpells()));
        }
    }

    private List<Button> buildSpellButtons(List<Spell> spells)
    {
        List<Button> buttons = new ArrayList<>();
        int offset = 0;

        for(Spell spell : spells)
        {
            Button button = new Button(
                    new Vector2Int(offset, 0),
                    new UVInt(0, 20),
                    new Vector2Int(24, 24),
                    NordicResourceLocations.BUTTON_TEXTURES,
                    this.minecraft,
                    this::blit,
                    (location, minecraft) -> minecraft.getTextureManager().bindTexture(location)
            );

            buttons.add(button);

            offset += button.size.x;
        }

        return buttons;
    }

    private List<MagicSchool> getSchools()
    {
        List<MagicSchool> schools = new ArrayList<>();
        List<TileEntitySigilPodium> podiums = this.Tile.getPodiums();

        for (TileEntitySigilPodium podium : podiums)
        {
            if (podium != null)
            {
                if (podium.getSchool() != null)
                {
                    schools.add((MagicSchool) podium.getSchool());
                }
            }
        }

        return schools;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY)
    {
        RenderSystem.color4f(this.red, this.green, this.blue, this.alpha);

        int left = this.guiLeft;
        int top = this.guiTop;

        drawInnerGui(matrixStack);

        if(!this.Tile.isMultiblockFormed)
        {
            this.minecraft.getTextureManager().bindTexture(this.texture);
            this.blit(matrixStack, left + (this.xSize / 2) - 48, top + this.ySize / 2 - 10, 1, 235, 102, 20);
            TranslationTextComponent text = new TranslationTextComponent("attunement_altar.invalid");

            // TODO: Replace the getstring with the correct formatted string
            this.font.drawString(matrixStack, text.getString(), left + (this.xSize / 2) - 44, top + (this.ySize / 2) - 4.5f, TextFormatting.WHITE.getColor());
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int mouseX, int mouseY)
    {
        RenderSystem.color4f(this.red, this.green, this.blue, this.alpha);
        this.minecraft.getTextureManager().bindTexture(this.texture);

        this.blit(matrixStack, 0, 0, 0, 0, this.xSize, this.ySize);
        this.group.draw(matrixStack);

        super.drawGuiContainerForegroundLayer(matrixStack, mouseX, mouseY);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double deltaX, double deltaY)
    {
        if (this.multiblockValid)
        {
            this.position.update(new Vector2Int(deltaX, deltaY));
//            for (Button button : buttons)
//            {
//                button.updatePosition(new Vector2Int(deltaX, deltaY));
//            }
        }

        return super.mouseDragged(mouseX, mouseY, mouseButton, deltaX, deltaY);
    }

    @Override
    public boolean mouseClicked(double posX, double posY, int mouseButton)
    {
        if (this.group.handleClick(posX, posY, this.guiLeft, this.guiTop))
        {
            rebuildButtonList();
        }

        return super.mouseClicked(posX, posY, mouseButton);
    }

    protected void drawInnerGui(MatrixStack matrixStack)
    {
        int boxStartX = (this.width  - this.xSize) / 2;
        int boxStartY = (this.height - this.ySize) / 2;
        preRenderInner(matrixStack, boxStartX, boxStartY);

        if(this.Tile.isMultiblockFormed)
        {
            for (Button button : buttons)
            {
                button.draw(matrixStack);
            }
        }

        postRenderInner(matrixStack);
    }

    private void preRenderInner(MatrixStack matrixStack, int boxStartX, int boxStartY)
    {
        RenderSystem.pushMatrix();
        RenderSystem.translatef((float)(boxStartX + bgRect.getX()), (float)(boxStartY + bgRect.getY()), 0.0F);

        RenderSystem.pushMatrix();
        RenderSystem.enableDepthTest();
        RenderSystem.translatef(0.0F, 0.0F, 950.0F);
        RenderSystem.colorMask(false, false, false, false);
        fill(matrixStack, 4680, 2260, -4680, -2260, -16777216);
        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.translatef(0.0F, 0.0F, -950.0F);
        RenderSystem.depthFunc(518);
        fill(matrixStack, bgRect.getWidth(), bgRect.getHeight(), 0, 0, -16777216);
        RenderSystem.depthFunc(515);

        RenderSystem.scalef(1.2f, 1.2f, 0);

        this.minecraft.getTextureManager().bindTexture(this.backgroundTexture);
        this.blit(matrixStack, 0, 0, bgRect.getX(), bgRect.getY(), bgRect.getWidth(), bgRect.getHeight());

        RenderSystem.scalef(1f, 1f, 0);

        this.minecraft.getTextureManager().bindTexture(this.texture);

        RenderSystem.translatef(position.x, position.y, 0);
        RenderSystem.scalef(scale, scale, 0F);
    }

    private void postRenderInner(MatrixStack matrixStack)
    {
        RenderSystem.scalef(-scale, -scale, 0F);
        RenderSystem.translatef(-108, -108, 0);
        RenderSystem.depthFunc(518);
        RenderSystem.translatef(0.0F, 0.0F, -950.0F);
        RenderSystem.colorMask(false, false, false, false);
        fill(matrixStack, 4680, 2260, -4680, -2260, -16777216);
        RenderSystem.colorMask(true, true, true, true);
        RenderSystem.translatef(0.0F, 0.0F, 950.0F);
        RenderSystem.depthFunc(515);
        RenderSystem.popMatrix();

        RenderSystem.popMatrix();
        RenderSystem.depthFunc(515);
        RenderSystem.disableDepthTest();
    }
}
