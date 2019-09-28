package io.github.lukas2005.supernaturalcreatures.world;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public interface IPackHandler {

    World getWorld();

    ArrayList<Pack> getAllPacks();
    HashMap<ChunkPos, Pack> getPackChunks();

    Pack newPack(Chunk mainChunk);

    Pack getPackAt(ChunkPos pos);

    void growPack(Pack pack, Chunk chunk);

    boolean isOccupied(Chunk chunk);

    boolean isOccupied(ChunkPos pos);

    boolean isAllFree(ChunkPos[] poss);

    void sync();

    class Storage implements Capability.IStorage<IPackHandler> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<IPackHandler> capability, IPackHandler instance, Direction side) {
            CompoundNBT nbt = new CompoundNBT();

            ListNBT allPacks = new ListNBT();
            for (Pack pack : instance.getAllPacks()) {
                allPacks.add(pack.toNBT());
            }
            nbt.put("allPacks", allPacks);

            ListNBT packChunks = new ListNBT();
            for (Map.Entry<ChunkPos, Pack> entry : instance.getPackChunks().entrySet()) {
                CompoundNBT entryNBT = new CompoundNBT();
                entryNBT.putInt("chunkX", entry.getKey().x);
                entryNBT.putInt("chunkZ", entry.getKey().z);
                entryNBT.putUniqueId("packId", entry.getValue().id);
                packChunks.add(entryNBT);
            }
            nbt.put("packChunks", packChunks);

            return nbt;
        }

        @Override
        public void readNBT(Capability<IPackHandler> capability, IPackHandler instance, Direction side, INBT nbtBase) {
            CompoundNBT nbt = (CompoundNBT) nbtBase;

            for (INBT packNBT : nbt.getList("allPacks", Constants.NBT.TAG_COMPOUND)) {
                instance.getAllPacks().add(Pack.fromNBT(packNBT, instance.getWorld()));
            }

            for (INBT inbt : nbt.getList("packChunks", Constants.NBT.TAG_COMPOUND)) {
                CompoundNBT chunkNBT = (CompoundNBT) inbt;

                ChunkPos pos = new ChunkPos(chunkNBT.getInt("chunkX"), chunkNBT.getInt("chunkZ"));

                UUID id = chunkNBT.getUniqueId("packId");

                AtomicReference<Pack> pack = new AtomicReference<>();

                instance.getAllPacks().forEach((p) -> {
                    if (p.id.equals(id)) {
                        pack.set(p);
                    }
                });

                if (pack.get() != null) {
                    instance.getPackChunks().put(pos, pack.get());
                }
            }
        }

    }


    class Impl implements IPackHandler {

        @Override
        public World getWorld() {
            return null;
        }

        @Override
        public ArrayList<Pack> getAllPacks() {
            return null;
        }

        @Override
        public HashMap<ChunkPos, Pack> getPackChunks() {
            return null;
        }

        @Override
        public Pack newPack(Chunk mainChunk) {
            return null;
        }

        @Override
        public Pack getPackAt(ChunkPos pos) {
            return null;
        }

        @Override
        public void growPack(Pack pack, Chunk chunk) {

        }

        @Override
        public boolean isOccupied(Chunk chunk) {
            return false;
        }

        @Override
        public boolean isOccupied(ChunkPos pos) {
            return false;
        }

        @Override
        public boolean isAllFree(ChunkPos[] poss) {
            return false;
        }

        @Override
        public void sync() {

        }
    }
}