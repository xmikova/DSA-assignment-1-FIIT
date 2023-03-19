public class QuadraticProbingHashTable<Key, Value> {
    static class QuadraticProbingHashTableNode<Key, Value> {
        Key key;
        Value value;
        QuadraticProbingHashTableNode<Key, Value> next;


        public QuadraticProbingHashTableNode(Key key, Value value) { //constructor
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static final int def_capacity = 16;
    private static final float def_lf = 0.75f;
    private static final float min_lf = 0.25f;
    private QuadraticProbingHashTableNode<Key, Value>[] table;
    private int size;
    private int capacity;
    private float loadFactor;
    private int threshold;

    public QuadraticProbingHashTable() { //constructor
        this.loadFactor = def_lf;
        this.capacity = def_capacity;
        this.threshold = (int) (capacity * loadFactor);
        this.table = (QuadraticProbingHashTableNode<Key, Value>[]) new QuadraticProbingHashTableNode[capacity]; //create a hash table with the nodes and a capacity given
        this.size = 0;
    }

    private int hash(String key, int capacity) {
        int hashCode = hashFunction(key);
        int index = Math.abs(hashCode) % capacity;
        return index;
    }

    private int hashFunction(String key) {
        int hash = 0x811c9dc5;
        for (int i = 0; i < key.length(); i++) {
            hash ^= key.charAt(i);
            hash *= 0x01000193;
        }
        return hash;
    }

    private int getIndex(Key key, int capacity){
        int index = hash(key.toString(),capacity);
        return index;
    }

    public void put(Key key, Value value) {
        int index = getIndex(key, capacity);
        int i = 0;

        while (table[index] != null && !table[index].key.equals(key)) {
            i++;
            index = (index + i * i) % capacity;
        }

        if (table[index] != null && table[index].key.equals(key)) {
            table[index].value = value;
        } else {
            table[index] = new QuadraticProbingHashTableNode<>(key, value);
            size++;

            if (size >= threshold) {
                Upsize();
            } else if (size < (capacity * min_lf)) {
                Downsize();
            }
        }
    }

    public void remove(Key key) {
        int index = getIndex(key, capacity);
        int i = 0;

        while (table[index] != null && !table[index].key.equals(key)) {
            i++;
            index = (index + i * i) % capacity;
        }

        if (table[index] != null && table[index].key.equals(key)) {
            table[index] = null;
            size--;

            // Re-insert all keys in the cluster starting from the next position
            // to ensure quadratic probing still works correctly.
            int nextIndex = (index + 1) % capacity;
            while (table[nextIndex] != null) {
                QuadraticProbingHashTableNode<Key, Value> node = table[nextIndex];
                table[nextIndex] = null;
                size--;
                put(node.key, node.value);
                nextIndex = (nextIndex + 1) % capacity;
            }
        }
    }

    public Value get(Key key) {
        int index = getIndex(key, capacity);
        int i = 0;

        while (table[index] != null && !table[index].key.equals(key)) {
            i++;
            index = (index + i * i) % capacity;
        }

        if (table[index] != null && table[index].key.equals(key)) {
            return table[index].value;
        } else {
            return null;
        }
    }


    private void Upsize() {
        int newCapacity = capacity * 2;
        QuadraticProbingHashTableNode<Key, Value>[] newTable = new QuadraticProbingHashTableNode[newCapacity];
        size = 0;

        for (int i = 0; i < capacity; i++) {
            QuadraticProbingHashTableNode<Key, Value> node = table[i];
            while (node != null) {
                QuadraticProbingHashTableNode<Key, Value> next = node.next;
                int newIndex = getIndex(node.key, newCapacity);
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                size++;
                node = next;
            }
        }

        table = newTable;
        capacity = newCapacity;
        threshold = (int) (capacity * loadFactor);
    }

    private void Downsize() {
        int newCapacity = capacity / 2;
        QuadraticProbingHashTableNode<Key, Value>[] newTable = (QuadraticProbingHashTableNode<Key, Value>[]) new QuadraticProbingHashTableNode[newCapacity];

        for (int i = 0; i < capacity; i++) {
            QuadraticProbingHashTableNode<Key, Value> node = table[i];
            if (node != null) {
                int index = getIndex(node.key, newCapacity);
                int j = 0;
                while (newTable[index] != null) {
                    j++;
                    index = (index + j * j) % newCapacity;
                }
                newTable[index] = node;
            }
        }

        table = newTable;
        capacity = newCapacity;
        threshold = (int) (capacity * loadFactor);
    }

    //------------------------------------------------just print f-------------------------------------------------//
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
           QuadraticProbingHashTableNode<Key, Value> node = table[i];
            sb.append("Bucket ").append(i).append(": ");
            while (node != null) {
                sb.append("(").append(node.key).append(", ").append(node.value).append(")");
                if (node.next != null) {
                    sb.append(" -> ");
                }
                node = node.next;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
