package second_part;

import java.util.ArrayList;

class BPlusTree<K extends Comparable<K>, V> {

    private final int t;
    private Node<K,V> root;
    private final TreeDebugger debugger;

    public BPlusTree(int t, TreeDebugger debugger) {
        this.t = t;
        this.debugger = debugger;
        root = new Node<>(true);
    }

    public Node<K,V> findLeaf(K key) {
        debugger.add(TracePoint.FIND_START);

        Node<K,V> cur = root;

        while (!cur.leaf) {
            debugger.add(TracePoint.FIND_INTERNAL);

            int i = 0;
            while (i < cur.keys.size() &&
                    key.compareTo(cur.keys.get(i)) >= 0) {
                i++;
            }
            cur = cur.children.get(i);
        }

        debugger.add(TracePoint.FIND_LEAF);
        return cur;
    }

    public boolean insert(K key, V value) {

        debugger.add(TracePoint.INSERT_START);

        Node<K,V> leaf = findLeaf(key);

        if (leaf.keys.contains(key)) {
            debugger.add(TracePoint.INSERT_DUPLICATE);
            return false;
        }

        int pos = 0;
        while (pos < leaf.keys.size() &&
                leaf.keys.get(pos).compareTo(key) < 0)
            pos++;

        leaf.keys.add(pos, key);
        leaf.values.add(pos, value);

        debugger.add(TracePoint.INSERT_INTO_LEAF);

        if (leaf.isOverflow(t))
            split(leaf);

        return true;
    }

    private void split(Node<K,V> node) {

        debugger.add(TracePoint.SPLIT_NODE);

        int mid = t;

        Node<K,V> newNode = new Node<>(node.leaf);

        if (node.leaf) {
            newNode.keys.addAll(node.keys.subList(mid, node.keys.size()));
            newNode.values.addAll(node.values.subList(mid, node.values.size()));

            node.keys = new ArrayList<>(node.keys.subList(0, mid));
            node.values = new ArrayList<>(node.values.subList(0, mid));

            newNode.right = node.right;
            if (node.right != null)
                node.right.left = newNode;

            node.right = newNode;
            newNode.left = node;
        }
        else {
            newNode.keys.addAll(node.keys.subList(mid + 1, node.keys.size()));
            newNode.children.addAll(node.children.subList(mid + 1, node.children.size()));

            node.keys = new ArrayList<>(node.keys.subList(0, mid));
            node.children = new ArrayList<>(node.children.subList(0, mid + 1));

            for (Node<K,V> child : newNode.children)
                child.parent = newNode;
        }

        K upKey = node.leaf ? newNode.keys.get(0) : node.keys.get(mid);

        if (!node.leaf)
            node.keys.remove(mid);

        if (node == root) {
            Node<K,V> newRoot = new Node<>(false);

            newRoot.keys.add(upKey);
            newRoot.children.add(node);
            newRoot.children.add(newNode);

            node.parent = newRoot;
            newNode.parent = newRoot;

            root = newRoot;

            debugger.add(TracePoint.NEW_ROOT);
        }
        else {
            insertIntoParent(node, newNode, upKey);
        }
    }

    private void insertIntoParent(Node<K,V> left,
                                  Node<K,V> right,
                                  K key) {

        debugger.add(TracePoint.INSERT_PARENT);

        Node<K,V> parent = left.parent;
        right.parent = parent;

        int pos = parent.children.indexOf(left);

        parent.keys.add(pos, key);
        parent.children.add(pos + 1, right);

        if (parent.isOverflow(t))
            split(parent);
    }

    public boolean delete(K key) {

        debugger.add(TracePoint.DELETE_START);

        Node<K,V> leaf = findLeaf(key);

        int pos = leaf.keys.indexOf(key);
        if (pos == -1) {
            debugger.add(TracePoint.DELETE_NOT_FOUND);
            return false;
        }

        leaf.keys.remove(pos);
        leaf.values.remove(pos);

        debugger.add(TracePoint.DELETE_FROM_LEAF);

        if (leaf != root && leaf.isUnderflow(t))
            rebalance(leaf);

        if (!root.leaf && root.keys.isEmpty()) {
            root = root.children.get(0);
            root.parent = null;
            debugger.add(TracePoint.ROOT_REPLACED);
        }

        return true;
    }

    private void rebalance(Node<K,V> node) {

        debugger.add(TracePoint.UNDERFLOW);

        Node<K,V> parent = node.parent;
        int index = parent.children.indexOf(node);

        Node<K,V> leftSibling =
                index > 0 ? parent.children.get(index - 1) : null;

        Node<K,V> rightSibling =
                index < parent.children.size() - 1
                        ? parent.children.get(index + 1)
                        : null;

        if (leftSibling != null &&
                leftSibling.keys.size() > t - 1) {

            debugger.add(TracePoint.BORROW_LEFT);

            node.keys.add(0,
                    leftSibling.keys.remove(leftSibling.keys.size() - 1));

            parent.keys.set(index - 1, node.keys.get(0));
            return;
        }

        if (rightSibling != null &&
                rightSibling.keys.size() > t - 1) {

            debugger.add(TracePoint.BORROW_RIGHT);

            node.keys.add(
                    rightSibling.keys.remove(0));

            parent.keys.set(index,
                    rightSibling.keys.get(0));
            return;
        }

        if (leftSibling != null) {

            debugger.add(TracePoint.MERGE_LEFT);

            leftSibling.keys.addAll(node.keys);
            parent.children.remove(node);
            parent.keys.remove(index - 1);
        }
        else if (rightSibling != null) {

            debugger.add(TracePoint.MERGE_RIGHT);

            node.keys.addAll(rightSibling.keys);
            parent.children.remove(rightSibling);
            parent.keys.remove(index);
        }

        if (parent != root && parent.isUnderflow(t))
            rebalance(parent);
    }
}
