package dictionary;

/**
 * @author Tobi, Chris
 * @version 1.00
 * @param <K>
 * @param <V>
 */
public class TreeDictionary<K extends Comparable<? super K>, V>
    implements Dictionary<K, V> {

    private class Entry<K, V> {

        private int height;
        private K key;
        private V value;
        private Entry left;
        private Entry right;

        public Entry(final K key, final V value) {
            this.key = key;
            this.value = value;
            this.height = 0;
            this.left = null;
            this.right = null;
        }
    }
    private static class Node<K, V> {
        private K key;
        private V value;
    }

    private Entry<K, V> root;

    @Override
    public final V insert(final K key, final V value) {
        V old = search(key);
        root = insertR(key, value, root);
        return old;
    }

    private Entry<K, V> insertR(K key, V value, Entry<K, V> p) {
        if (p == null) {
            p = new Entry(key, value);
        } else if (key.compareTo(p.key) < 0) {
            p.left = insertR(key, value, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = insertR(key, value, p.right);
        } else {
            p.value = value;           
        }
        p = balance(p);
        return p;
    }

    @Override
    public final V search(final K key) {
        return searchR(key, root);
    }

    private V searchR(final K key, final Entry<K, V> p) {
        if (p == null) {
            return null;
        } else if (key.compareTo(p.key) < 0) {
            return (V) searchR(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            return (V) searchR(key, p.right);
        } else {
            return p.value;
        }
    }

    @Override
    public final V remove(final K key) {
        V old = search(key);
        if (old == null) {
            return null;
        }
        root = removeR(key, root);
        return old;
    }

    private Entry<K, V> removeR(K key, Entry<K, V> p) {
        if (p == null) {
            // Nothing to do here!
        } else if (key.compareTo(p.key) < 0) {
            p.left = removeR(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = removeR(key, p.right);
        } else {
            if (p.left == null) {
                p = p.right;
            } else if (p.right == null) {
                p = p.left;
            } else {
                Node<K, V> min = new Node<K, V>();
                p.right = getRemMinR(p.right, min);
                p.key = min.key;
                p.value = min.value;
            }
        }
        return p;
    }

  
    private Entry<K, V> getRemMinR(Entry<K, V> p, Node<K, V> min) {
        assert p != null;
        if (p.left == null) {
            min.key = p.key;
            min.value = p.value;
            p = p.right;
        } else {
            p.left = getRemMinR(p.left, min);
        }
        return p;
    }


    private Entry<K, V> balance(Entry<K, V> p) {
        if (p == null) {
            return null;
        }
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        if (getBalance(p) == -2) {
            if (getBalance(p.left) <= 0) {
                p = rotateRight(p);
            } else {
                p = rotateLeftRight(p);
            }
        } else if (getBalance(p) == 2) {
            if (getBalance(p.right) >= 0) {
                p = rotateLeft(p);
            } else {
                p = rotateRightLeft(p);
            }
        }
        return p;
    }


    private int getHeight(final Entry<K, V> p) {
        if (p == null) {
            return -1;
        } else {
            return p.height;
        }
    }


    private int getBalance(final Entry<K, V> p) {
        if (p == null) {
            return 0;
        } else {
            return getHeight(p.right) - getHeight(p.left);
        }
    }

    private Entry<K, V> rotateRight(final Entry<K, V> p) {
        assert p.left != null;
        Entry<K, V> q = p.left;
        p.left = q.right;
        q.right = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        q.height = Math.max(getHeight(q.left), getHeight(q.right)) + 1;
        return q;
    }

    private Entry<K, V> rotateLeft(final Entry<K, V> p) {
        assert p.right != null;
        Entry<K, V> q = p.right;
        p.right = q.left;
        q.left = p;
        p.height = Math.max(getHeight(p.right), getHeight(p.left)) + 1;
        q.height = Math.max(getHeight(q.left), getHeight(q.right)) + 1;
        return q;
    }

    private Entry<K, V> rotateLeftRight(final Entry<K, V> p) {
        assert p.left != null;
        p.left = rotateLeft(p.left);
        return rotateRight(p);
    }

    private Entry<K, V> rotateRightLeft(final Entry<K, V> p) {
        assert p.right != null;
        p.right = rotateRight(p.right);
        return rotateLeft(p);
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        return toStringR(sb, root).toString();
    }

    private StringBuilder toStringR(StringBuilder sb, final Entry<K, V> p) {
        sb.append(p.key).append("  ").append(p.value).append("\n");
        if (p.left != null) {
            sb = toStringR(sb, p.left);
        }
        if (p.right != null) {
            sb = toStringR(sb, p.right);
        }
        return sb;
    }
}
