/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 * File Created @ [Jun 9, 2015, 12:48:18 AM (GMT)]
 */
package vazkii.botania.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.RailShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibMisc;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = LibMisc.MOD_ID)
public class BlockGhostRail extends AbstractRailBlock implements ILexiconable {

	private static final String TAG_FLOAT_TICKS = "Botania_FloatTicks";

	public BlockGhostRail(Properties builder) {
		super(true, builder);
		setDefaultState(stateContainer.getBaseState().with(BlockStateProperties.RAIL_SHAPE_STRAIGHT, RailShape.NORTH_SOUTH));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.RAIL_SHAPE_STRAIGHT);
	}

	/* todo 1.13
	@SubscribeEvent
	public static void onMinecartUpdate(MinecartUpdateEvent event) {
		BlockPos entPos = new BlockPos(event.getEntity());
		IBlockState state = event.getEntity().world.getBlockState(entPos);
		Block block = state.getBlock();
		boolean air = block.isAir(state, event.getEntity().world, entPos);
		int floatTicks = event.getEntity().getEntityData().getInteger(TAG_FLOAT_TICKS);

		if(block == ModBlocks.ghostRail)
			event.getEntity().getEntityData().setInteger(TAG_FLOAT_TICKS, 20);
		else if(block instanceof BlockRailBase || block == ModBlocks.dreamwood) {
			event.getEntity().getEntityData().setInteger(TAG_FLOAT_TICKS, 0);
			if(floatTicks > 0)
				event.getEntity().world.playEvent(2003, entPos, 0);
		}
		floatTicks = event.getEntity().getEntityData().getInteger(TAG_FLOAT_TICKS);

		if(floatTicks > 0) {
			IBlockState stateBelow = event.getEntity().world.getBlockState(entPos.down());
			Block blockBelow = stateBelow.getBlock();
			boolean airBelow = blockBelow.isAir(stateBelow, event.getEntity().world, entPos.down());
			if(air && airBelow || !air && !airBelow)
				event.getEntity().noClip = true;
			event.getEntity().motionY = 0.2;
			event.getEntity().motionX *= 1.4;
			event.getEntity().motionZ *= 1.4;
			event.getEntity().getEntityData().setInteger(TAG_FLOAT_TICKS, floatTicks - 1);
			event.getEntity().world.playEvent(2000, entPos, 0);
		} else event.getEntity().noClip = false;
	}
	*/

	@Override
	public LexiconEntry getEntry(World world, BlockPos pos, PlayerEntity player, ItemStack lexicon) {
		return LexiconData.ghostRail;
	}

	@Nonnull
	@Override
	public IProperty<RailShape> getShapeProperty() {
		return BlockStateProperties.RAIL_SHAPE_STRAIGHT;
	}
}
