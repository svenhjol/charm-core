package svenhjol.charm_core.fabric.mixin.render_tooltip_callback;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipPositioner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import svenhjol.charm_core.fabric.TooltipHelper;
import svenhjol.charm_core.fabric.event.RenderTooltipCallback;

import java.util.List;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
    /**
     * Fires the {@link RenderTooltipCallback} event.
     * Modules can hook into the tooltip before it is rendered.
     */
    @Inject(
        method = "renderTooltipInternal",
        at = @At("HEAD")
    )
    private void hookRenderOrderedTooltip(Font font, List<ClientTooltipComponent> lines, int x, int y, ClientTooltipPositioner clientTooltipPositioner, CallbackInfo ci) {
        var itemStack = TooltipHelper.getTooltipItemStack();
        RenderTooltipCallback.EVENT.invoker().interact((GuiGraphics)(Object)this, itemStack, lines, x, y);
        TooltipHelper.clearTooltipItemStack();
    }
}
