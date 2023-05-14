package svenhjol.charm_core.mixin.event.item_drag_drop;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import svenhjol.charm_api.event.ItemDragDropEvent;

import javax.annotation.Nullable;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(
        method = "overrideStackedOnOther",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookOverrideStackedOnOther(ItemStack source, Slot slot, ClickAction clickAction, Player player, CallbackInfoReturnable<Boolean> ci) {
        var result = performStack(ItemDragDropEvent.StackType.STACKED_ON_OTHER, source, slot.getItem(), slot, clickAction, player, null);
        if (result != InteractionResult.PASS) {
            ci.setReturnValue(true);
        }
    }

    @Inject(
        method = "overrideOtherStackedOnMe",
        at = @At("HEAD"),
        cancellable = true
    )
    private void hookOverrideOtherStackedOnMe(ItemStack source, ItemStack dest, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess, CallbackInfoReturnable<Boolean> ci) {
        var result = performStack(ItemDragDropEvent.StackType.STACKED_ON_SELF, source, dest, slot, clickAction, player, slotAccess);
        if (result != InteractionResult.PASS) {
            ci.setReturnValue(true);
        }
    }

    private InteractionResult performStack(ItemDragDropEvent.StackType stackType, ItemStack source, ItemStack dest, Slot slot, ClickAction clickAction, Player player, @Nullable SlotAccess slotAccess) {
        return ItemDragDropEvent.INSTANCE.invoke(stackType, source, dest, slot, clickAction, player, slotAccess);
    }
}
