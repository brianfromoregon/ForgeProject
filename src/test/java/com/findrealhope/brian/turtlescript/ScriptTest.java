package com.findrealhope.brian.turtlescript;

import com.findrealhope.turtle.TestTurtle;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.junit.Before;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public abstract class ScriptTest {

    public TestTurtle turtle = new TestTurtle();

    @Before
    public void setup() {
        turtle.reset(xyz(4, 0, 0), EnumFacing.NORTH);
    }

    public BlockPos xyz(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }

    public void verifyOnlyMarked(BlockPos... positions) {
        Set<BlockPos> marked = new HashSet<>();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                for (int z = 0; z < 9; z++) {
                    if (turtle.space[x][y][z] == 1) {
                        marked.add(xyz(x, y, z));
                    }
                }
            }
        }

        for (BlockPos expected : positions) {
            assertTrue("not marked! " + expected.toString(), marked.remove(expected));
        }
        assertTrue("marked but not expected! " + marked, marked.isEmpty());
    }
}
