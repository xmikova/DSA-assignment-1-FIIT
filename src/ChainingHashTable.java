public class ChainingHashTable<Key, Value> {
     static class ChainingHashTableNode<Key, Value> {
        Key key;
        Value value;
        ChainingHashTableNode<Key, Value> next;


         public ChainingHashTableNode(Key key, Value value) { //constructor
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private static final int def_capacity = 16;
    private static final float def_lf = 0.75f;
    private static final float min_lf = 0.25f;
    private ChainingHashTableNode<Key, Value>[] table;
    private int size;
    private int capacity;
    private float loadFactor;
    private int threshold;

    public ChainingHashTable() { //constructor
        this.loadFactor = def_lf;
        this.capacity = def_capacity;
        this.threshold = (int) (capacity * loadFactor);
        this.table = (ChainingHashTableNode<Key, Value>[]) new ChainingHashTableNode[capacity]; //create a hash table with the nodes and a capacity given
        this.size = 0;
    }


    private int hash(String key, int capacity) {
        return hashFunction(key) % capacity;
    }

    private int hashFunction(String key) {
        int hashValue = 0;
        for (int i = 0; i < key.length(); i++) {
            int charValue = (int) key.charAt(i);
            hashValue += charValue * (i + 1);
        }
        return hashValue;
    }

    private int getIndex(Key key, int capacity){
        int index = hash(key.toString(),capacity);
        return index;
    }

    public void put(Key key, Value value) {
        int index = getIndex(key, capacity);
        ChainingHashTableNode<Key, Value> node = table[index];
        while (node != null && !node.key.equals(key)) { //chain
            node = node.next;
        }
        if (node != null) { // key found in chain, update value
            node.value = value;
        } else { // key not found in chain, add new node to front of chain
            ChainingHashTableNode<Key, Value> newNode = new ChainingHashTableNode<>(key, value);
            newNode.next = table[index];
            table[index] = newNode;
            size++;
            if (size >= threshold) {
                Upsize();
            } else if (size < (capacity * min_lf)) {
                Downsize();
            }
        }
    }

    public void remove(Key key) {
        int index = getIndex(key,capacity);
        ChainingHashTableNode<Key,Value> node = table[index];
        ChainingHashTableNode<Key, Value> prevNode = null;
        while (node != null) {
            if (node.key.equals(key)) {
                if (prevNode == null) {
                    table[index] = node.next;
                } else {
                    prevNode.next = node.next;
                }
                size--;
                if (size < min_lf * capacity) {
                    Downsize();
                }
            }
            prevNode = node;
            node = node.next;
        }
    }

    public Value get(Key key) {
        int index = getIndex(key,capacity);
        ChainingHashTableNode<Key, Value> node = table[index];
        while (node != null) {
            if (node.key.equals(key)) {
                System.out.println(node.value);
                return node.value;
            }
            node = node.next;
        }
        return null;
    }


    private void Upsize() {
        int capacity = this.capacity * 2; //powers of two
        ChainingHashTableNode<Key, Value>[] newTable = new ChainingHashTableNode[capacity];

        for (int i = 0; i < this.capacity; i++) { //rehashing
            ChainingHashTableNode<Key, Value> node = table[i];
            while (node != null) {
                ChainingHashTableNode<Key, Value> next = node.next;
                int newIndex = getIndex(node.key, capacity);
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                node = next;
            }
        }

        // update the fields with the new values
        table = newTable;
        this.capacity = capacity;
        threshold = (int) (capacity * loadFactor);

    }

    private void Downsize() {
        int capacity = this.capacity / 2;
        ChainingHashTableNode<Key, Value>[] newTable = new ChainingHashTableNode[capacity];

        for (int i = 0; i < this.capacity; i++) {
            ChainingHashTableNode<Key, Value> node = table[i];
            while (node != null) {
                ChainingHashTableNode<Key, Value> next = node.next;
                int newIndex = getIndex(node.key, capacity);
                node.next = newTable[newIndex];
                newTable[newIndex] = node;
                node = next;
            }
        }
        table = newTable;
        this.capacity = capacity;
        threshold = (int) (capacity * loadFactor);

    }

    //------------------------------------------------just print f-------------------------------------------------//
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            ChainingHashTableNode<Key, Value> node = table[i];
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


