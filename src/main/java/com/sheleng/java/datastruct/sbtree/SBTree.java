package com.sheleng.java.datastruct.sbtree;

import java.util.Map;
import java.util.Objects;

/**
 * SBTree
 *
 * @author Sheleng
 * @version 0.0.1
 */
public class SBTree<K extends Comparable, V> implements ISBTree<K, V> {

    private SBNode<K, V> root;
    private final SBNode<K, V> nil;

    public SBTree() {
        nil = new SBNode<K, V>();
        nil.size(0);
        nil.parent(nil).left(nil).right(nil);
    }

    /**
     * 右旋
     *
     * @param node
     */
    protected void rightRotate(SBNode node) {
        SBNode left = node.left();
        replace(node, left);
        node.left(left.right());
        left.right(node);
        left.size(node.size());
        node.size(node.left().size() + node.right().size() + 1);
    }


    /**
     * 左旋
     *
     * @param node
     */
    protected void leftRotate(SBNode node) {
        SBNode right = node.right();
        replace(node, right);
        node.right(right.left());
        right.left(node);
        right.size(node.size());
        node.size(node.left().size() + node.right().size() + 1);
    }

    protected void replace(SBNode oldNode, SBNode newNode) {
        newNode.parent(oldNode.parent());
        if (oldNode.parent().equals(nil)) {
            root = newNode;
        } else if (oldNode.parent().left().equals(oldNode)) {
            oldNode.parent().left(newNode);
        } else {
            oldNode.parent().right(newNode);
        }
    }

    @Override
    public void insert(K key, V value) {
        insert(root, key, value);
    }

    protected void insert(SBNode<K, V> node, K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key == null.");
        }
        if (value == null) {
            throw new IllegalArgumentException("value == null.");
        }
        if (node == null) {
            root = new SBNode<K, V>(key, value);
            root.parent(nil).left(nil).right(nil);
            return;
        }
        node.size(node.size() + 1);
        boolean flag = key.compareTo(node.key()) <= 0 ? true : false;
        if (flag) {
            if (!node.left().equals(nil)) {
                insert(node.left(), key, value);
            } else {
                SBNode left = new SBNode(key, value);
                left.left(nil).right(nil);
                node.left(left).right(nil);
            }
        } else {
            if (!node.right().equals(nil)) {
                insert(node.right(), key, value);
            } else {
                SBNode right = new SBNode(key, value);
                right.left(nil).right(nil);
                node.right(right).left(nil);
            }
        }
        maintain(node, key.compareTo(node.key()) >= 0);
    }

    @Override
    public V delete(K key) {
        return delete(root, key);
    }

    protected V delete(SBNode<K, V> tree, K key) {
        if (key == null) {
            throw new IllegalArgumentException("key == null.");
        }
        if (tree.equals(nil)) {
            for (SBNode parent = tree.parent(); !parent.equals(nil); parent = parent.parent()) {
                parent.size(parent.size() + 1);
            }
            return null;
        }
        tree.size(tree.size() - 1);
        int flag = key.compareTo(tree.key());
        if (flag == 0) {
            if (tree.left().equals(nil) || tree.right().equals(nil)) {
                SBNode child = tree.left().equals(nil) ? tree.right() : tree.left();
                replace(tree, child);
                tree.parent(nil);
                return tree.value();
            } else {
                SBNode rightMin;
                for (rightMin = tree.right(); !rightMin.left().equals(nil); rightMin = rightMin.left()) ;
                replace(rightMin, rightMin.right());
                replace(tree, rightMin);
                rightMin.left(tree.left()).right(tree.right()).size(tree.size());
                return tree.value();
            }
        } else if (flag < 0) {
            return delete(tree.left(), key);
        } else {
            return delete(tree.right(), key);
        }
    }

    protected void maintain(SBNode<K, V> tree, boolean flag) {
        if (tree.size() == 0) return;
        if (flag == false) {
            if (tree.left().left().size() > tree.right().size()) {
                rightRotate(tree);
            } else {
                if (tree.left().right().size() > tree.right().size()) {
                    leftRotate(tree.left());
                    rightRotate(tree);
                } else {
                    return;
                }
            }
        } else {
            if (tree.right().right().size() > tree.left().size()) {
                leftRotate(tree);
            } else {
                if (tree.right().left().size() > tree.left().size()) {
                    rightRotate(tree.right());
                    leftRotate(tree);
                } else {
                    return;
                }
            }
        }

        maintain(tree.left(), false);
        maintain(tree.right(), true);
        maintain(tree, false);
        maintain(tree, true);
    }


    /**
     * 在SBTree中查找与key对应的value值，若存在则返回对应的value，否则返回null。
     */
    @Override
    public V find(K key) {
        return find(root, key).getValue();
    }

    @Override
    public Map.Entry<K, V> findEntry(K key) {
        return find(root, key);
    }


    protected Map.Entry<K, V> find(SBNode<K, V> tree, K key) {
        if (tree.equals(nil)) {
            return null;
        }
        int flag = key.compareTo(tree.key());
        if (flag < 0) {
            return find(tree.left(), key);
        } else if (flag > 0) {
            return find(tree.right(), key);
        } else {
            return tree.entry();
        }
    }

    @Override
    public V max() {
        return max(root);
    }

    protected V max(SBNode tree) {
        if (tree.size() == 0) return null;
        SBNode<K, V> rightMax;
        for (rightMax = tree.right(); !rightMax.right().equals(nil); rightMax = rightMax.right()) ;
        return rightMax.value();
    }

    @Override
    public V min() {
        return min(root);
    }

    protected V min(SBNode tree) {
        if (tree.size() == 0) return null;
        SBNode<K, V> leftMin;
        for (leftMin = tree.left(); !leftMin.left().equals(nil); leftMin = leftMin.left()) ;
        return leftMin.value();
    }

    @Override
    public int size() {
        return root.size();
    }

    @Override
    public void clear() {
        root = nil;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Map.Entry<K, V> next() {
        return null;
    }

    @Override
    public void remove() {

    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        final K key;
        V value;

        Entry(K k, V v) {
            value = v;
            key = k;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry e = (Map.Entry) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if (k1 == k2 || (k1 != null && k1.equals(k2))) {
                Object v1 = getValue();
                Object v2 = e.getValue();
                if (v1 == v2 || (v1 != null && v1.equals(v2)))
                    return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }
}
