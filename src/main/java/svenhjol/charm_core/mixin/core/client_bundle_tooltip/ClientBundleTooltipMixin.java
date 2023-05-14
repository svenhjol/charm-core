package svenhjol.charm_core.mixin.core.client_bundle_tooltip;

import net.minecraft.client.gui.screens.inventory.tooltip.ClientBundleTooltip;
import net.minecraft.world.inventory.tooltip.BundleTooltip;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charm_core.iface.ITooltipGrid;

@Mixin({ClientBundleTooltip.class})
public class ClientBundleTooltipMixin {
    private static BundleTooltip storedBundleTooltip;

    public ClientBundleTooltipMixin() {
    }

    @Inject(
        method = {"<init>"},
        at = {@At("TAIL")}
    )
    private void hookInit(BundleTooltip bundleTooltip, CallbackInfo ci) {
        storedBundleTooltip = bundleTooltip;
    }

    @Inject(
        method = {"gridSizeX"},
        at = {@At("RETURN")},
        cancellable = true
    )
    private void hookGridSizeX(CallbackInfoReturnable<Integer> cir) {
        if (storedBundleTooltip instanceof ITooltipGrid) {
            cir.setReturnValue(((ITooltipGrid)storedBundleTooltip).gridSizeX());
        }

    }

    @Inject(
        method = {"gridSizeY"},
        at = {@At("RETURN")},
        cancellable = true
    )
    private void hookGridSizeY(CallbackInfoReturnable<Integer> cir) {
        if (storedBundleTooltip instanceof ITooltipGrid) {
            cir.setReturnValue(((ITooltipGrid)storedBundleTooltip).gridSizeY());
        }

    }
}
