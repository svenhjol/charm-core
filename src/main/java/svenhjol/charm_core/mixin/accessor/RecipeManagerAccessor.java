package svenhjol.charm_core.mixin.accessor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeManager.class)
public interface RecipeManagerAccessor {
    @Mutable
    @Accessor()
    Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> getRecipes();

    @Mutable
    @Accessor()
    void setRecipes(Map<RecipeType<?>, Map<ResourceLocation, Recipe<?>>> recipes);
}
