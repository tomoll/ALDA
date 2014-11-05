package dictionary;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Tobi, Chris
 * @param <K>
 * @param <V>
 */
public class HashDictionary<K, V> implements Dictionary<K, V> {

    private int arraySize = 16001;
    private int tempHash;

    LinkedList<Entry> tab[] = new LinkedList[arraySize];

    static class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    @Override
    public V insert(K key, V value) {
        tempHash = hash(key);
        if (tab[tempHash] != null) {
            {
                for (int i = 0; i < tab[tempHash].size(); i++) {
                    if (tab[tempHash].get(i).key.equals(key)) {
                        tab[tempHash].remove(i);
                        tab[tempHash].add(new Entry<K, V>(key, value));
                        return value;
                    }
                }
                tab[tempHash].addLast(new Entry<K, V>(key, value));
                return value;
            }
        } else {
            tab[tempHash] = new LinkedList<Entry>();
            Entry e = new Entry<K, V>(key, value);
            tab[tempHash].add(e);
            return value;
        }
    }

    @Override
    public V search(K key) {
        tempHash = hash(key);
        if (tab[tempHash] != null) {
            for (int i = 0; i < tab[tempHash].size(); i++) {
                if (tab[tempHash].get(i).key.equals(key)) {
                    Entry<K, V> temp = tab[tempHash].get(i);
                    return temp.value;
                } 
                
            }
        } else {
            return null;

        }
        return null;               
    }

    @Override
    public V remove(K key) {
        tempHash = hash(key);
        if(tab[tempHash]!=null){
            for(int i = 0; i < tab[tempHash].size(); i++){
                if (tab[tempHash].get(i).key.equals(key)) {
                    Entry<K, V> temp = tab[tempHash].get(i);
                    tab[tempHash].remove(i);
                    return temp.value;
                } 
            }
        } else {
            return null;
          
        }
        return null;
    }
    

    private int hash(K key) {
        String ke = key.toString();
        int adr = 0;
        for (int i = 0; i < ke.length(); i++) {
            adr = 31 * adr + ke.charAt(i);
        }
        if (adr < 0) {
            adr = -adr;
        }
        return adr % arraySize;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String sb1;

        for (int i = 0; i < arraySize; i++) {
            if(tab[i]!=null){
                for(int j = 0; j < tab[i].size(); j++){
                    sb.append(tab[i].get(j).key).append(" ").append(tab[i].get(j).value).append("\n");
                }
            }
        }
        sb1 = sb.toString();
        return sb1;
    }
}


