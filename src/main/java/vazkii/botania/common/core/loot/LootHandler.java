package vazkii.botania.common.core.loot;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.ILootGenerator;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.common.lib.LibMisc;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public final class LootHandler {

	@SubscribeEvent
	public static void lootLoad(LootTableLoadEvent evt) {
		String prefix = "minecraft:chests/";
		String name = evt.getName().toString();

		if (name.startsWith(prefix)) {
			String file = name.substring(name.indexOf(prefix) + prefix.length());
			switch (file) {
			case "abandoned_mineshaft":
			case "desert_pyramid":
			case "jungle_temple":
			case "simple_dungeon":
			case "spawn_bonus_chest":
			case "stronghold_corridor":
			case "village_blacksmith": evt.getTable().addPool(getInjectPool(file)); break;
			default: break;
			}
		}
	}

	private static LootPool getInjectPool(String entryName) {
		return LootPool.builder()
				.addEntry(getInjectEntry(entryName, 1))
				// todo 1.14 can't set bonusRolls to [0, 1]
				.build();
	}

	private static ILootGenerator.Builder getInjectEntry(String name, int weight) {
		ResourceLocation table = new ResourceLocation(LibMisc.MOD_ID, "inject/" + name);
		return TableLootEntry.func_216171_a(table)
				.weight(weight);
	}

}
