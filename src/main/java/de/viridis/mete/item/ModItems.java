package de.viridis.mete.item;

import de.viridis.mete.ViridisCore;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item METE_PLUSH = registerItem("mete_plush", new Item(new Item.Settings()));
    public static final Item DOK_PLUSH = registerItem("dok_plush", new Item(new Item.Settings()));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ViridisCore.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ViridisCore.LOGGER.info("Registering Mod Items for " + ViridisCore.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
        });
    }
}