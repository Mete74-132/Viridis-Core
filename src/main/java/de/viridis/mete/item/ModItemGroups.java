package de.viridis.mete.item;

import de.viridis.mete.ViridisCore;
import de.viridis.mete.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup WATHE_VIRIDIS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(ViridisCore.MOD_ID, "core"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.METE_PLUSH))
                    .displayName(Text.translatable("Wathe Viridis"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.METE_PLUSH);
                        entries.add(ModBlocks.DOK_PLUSH);
                        entries.add(ModBlocks.BIEN_PLUSH);
                        entries.add(ModBlocks.LACHSI_PLUSH);
                        entries.add(ModBlocks.MATTI_PLUSH);
                        entries.add(ModBlocks.SODOG_PLUSH);
                    }).build());



    public static void registerItemGroups() {
        ViridisCore.LOGGER.info("Registering Item Groups for " + ViridisCore.MOD_ID);
    }
}
