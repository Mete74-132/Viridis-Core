package de.viridis.mete.game;

import dev.doctor4t.wathe.block_entity.DoorBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import de.viridis.mete.game.GameUtil;

import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public final class RoomKeys {
    private static final Pattern ROOM = Pattern.compile("Room (\\d+)");
    private static int roomCount = 0;

    private RoomKeys() {
    }


    public static void scan(ServerWorld world) {
        Box area = GameUtil.playArea(world);
        int max = 0;
        Map<Integer, String> doors = new TreeMap<>();
        ChunkPos min = new ChunkPos((int) Math.floor(area.minX) >> 4, (int) Math.floor(area.minZ) >> 4);
        ChunkPos maxPos = new ChunkPos((int) Math.floor(area.maxX) >> 4, (int) Math.floor(area.maxZ) >> 4);
        for (int cx = min.x; cx <= maxPos.x; cx++) {
            for (int cz = min.z; cz <= maxPos.z; cz++) {
                WorldChunk chunk = world.getChunk(cx, cz);
                for (BlockEntity be : chunk.getBlockEntities().values()) {
                    if (be instanceof DoorBlockEntity door && area.contains(be.getPos().toCenterPos())) {
                        Matcher m = ROOM.matcher(door.getKeyName());
                        if (m.matches()) {
                            int room = Integer.parseInt(m.group(1));
                            max = Math.max(max, room);
                            doors.putIfAbsent(room, be.getPos().toShortString());
                        }
                    }
                }
            }
        }
        roomCount = max;
    }

    public static int roomCount(int fallback) {
        return roomCount > 0 ? roomCount : fallback;
    }
}
