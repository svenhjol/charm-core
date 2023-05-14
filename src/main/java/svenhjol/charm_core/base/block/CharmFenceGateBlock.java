package svenhjol.charm_core.base.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;
import svenhjol.charm_api.iface.IVariantMaterial;
import svenhjol.charm_api.iface.IVariantWoodMaterial;
import svenhjol.charm_core.base.CharmBlockItem;
import svenhjol.charm_core.base.CharmFeature;
import svenhjol.charm_core.iface.IIgniteProvider;

import java.util.function.Supplier;

public class CharmFenceGateBlock extends FenceGateBlock implements IIgniteProvider {
    protected final CharmFeature feature;
    protected final IVariantMaterial variantMaterial;

    public CharmFenceGateBlock(CharmFeature feature, IVariantWoodMaterial material) {
        super(material.blockProperties()
            .strength(2.0F, 3.0F),
            material.getWoodType());

        this.feature = feature;
        this.variantMaterial = material;
    }

    @Override
    public int igniteChance() {
        return variantMaterial.isFlammable() ? 5 : 0;
    }

    @Override
    public int burnChance() {
        return variantMaterial.isFlammable() ? 20 : 0;
    }

    public static class BlockItem extends CharmBlockItem {
        public <T extends Block> BlockItem(CharmFeature feature, Supplier<T> block) {
            super(feature, block, new Properties());
        }
    }
}
