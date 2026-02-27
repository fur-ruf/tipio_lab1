package second_part;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BPlusTreeTest {

    @Test
    void testSplitRoot() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(5, "C");
        tree.insert(6, "D");

        List<TracePoint> actual = debugger.get();

        assertTrue(actual.contains(TracePoint.SPLIT_NODE));
        assertTrue(actual.contains(TracePoint.NEW_ROOT));
    }

    @Test
    void testSplitInternal() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        int[] keys = {10, 20, 5, 6, 12, 30, 7, 17};

        for (int k : keys)
            tree.insert(k, "V");

        List<TracePoint> actual = debugger.get();

        assertTrue(actual.contains(TracePoint.SPLIT_NODE));
        assertTrue(actual.contains(TracePoint.INSERT_PARENT)); // разделили не корневой узел, ключ закинули в родителя
    }

    @Test
    void testDeleteWithoutUnderflow() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        tree.insert(10, "A");
        tree.insert(20, "B");
        tree.insert(5, "C");

        debugger.clear();

        tree.delete(20);

        List<TracePoint> actual = debugger.get();

        assertTrue(actual.contains(TracePoint.DELETE_FROM_LEAF));
        assertFalse(actual.contains(TracePoint.UNDERFLOW)); // удалили без опустошения
    }

    @Test
    void testBorrowLeft() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        int[] keys = {10, 20, 5, 6, 12, 30};
        for (int k : keys)
            tree.insert(k, "V");

        debugger.clear();

        tree.delete(30);
        tree.delete(20);

        List<TracePoint> actual = debugger.get();

        assertTrue(actual.contains(TracePoint.UNDERFLOW)); // опустошили
        assertTrue(actual.contains(TracePoint.BORROW_LEFT)
                || actual.contains(TracePoint.BORROW_RIGHT)); // заняли у соседей
    }

    @Test
    void testBorrowOrMerge() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        int[] keys = {10, 20, 5, 6, 12, 30};
        for (int k : keys)
            tree.insert(k, "V");

        debugger.clear();

        tree.delete(30);
        tree.delete(20);

        List<TracePoint> actual = debugger.get();

        assertTrue(actual.contains(TracePoint.UNDERFLOW));

        boolean resolved =
                actual.contains(TracePoint.BORROW_LEFT)
                        || actual.contains(TracePoint.BORROW_RIGHT)
                        || actual.contains(TracePoint.MERGE_LEFT)
                        || actual.contains(TracePoint.MERGE_RIGHT);

        assertTrue(resolved);
    }

    @Test
    void testMerge() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        int[] keys = {10, 20, 5, 6};
        for (int k : keys)
            tree.insert(k, "V");

        debugger.clear();

        tree.delete(6);
        tree.delete(5);

        List<TracePoint> actual = debugger.get();

        boolean mergeHappened =
                actual.contains(TracePoint.MERGE_LEFT)
                        || actual.contains(TracePoint.MERGE_RIGHT);

        assertTrue(
                actual.contains(TracePoint.UNDERFLOW)
                        || mergeHappened
        );
    }

    @Test
    void testDeleteNotFound() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        tree.insert(10, "A");

        debugger.clear();

        boolean result = tree.delete(99);

        assertFalse(result);
        assertTrue(debugger.get().contains(TracePoint.DELETE_NOT_FOUND));
    }

    @Test
    void testDuplicateInsert() {

        TreeDebugger debugger = new TreeDebugger();
        BPlusTree<Integer, String> tree =
                new BPlusTree<>(2, debugger);

        tree.insert(10, "A");
        debugger.clear();

        boolean result = tree.insert(10, "B");

        assertFalse(result);
        assertTrue(debugger.get().contains(TracePoint.INSERT_DUPLICATE));
    }
}
