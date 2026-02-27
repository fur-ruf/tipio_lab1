package second_part;

import java.util.ArrayList;
import java.util.List;

class Node<K extends Comparable<K>, V> {

    boolean leaf;
    List<K> keys = new ArrayList<>();
    List<Node<K,V>> children = new ArrayList<>();
    List<V> values = new ArrayList<>();

    Node<K,V> parent;
    Node<K,V> left;
    Node<K,V> right;

    Node(boolean leaf) {
        this.leaf = leaf;
    }

    boolean isOverflow(int t) {
        return keys.size() > 2 * t - 1;
    }

    boolean isUnderflow(int t) {
        return keys.size() < t - 1;
    }
}
