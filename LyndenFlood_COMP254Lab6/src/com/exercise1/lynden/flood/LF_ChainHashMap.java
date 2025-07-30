package com.exercise1.lynden.flood;

import com.Entry;
import com.UnsortedTableMap;

import java.util.ArrayList;

/**
 * Map implementation using hash table with separate chaining and configurable load factor.
 */
public class LF_ChainHashMap<K,V> extends LF_AbstractHashMap<K,V> {
  // a fixed capacity array of UnsortedTableMap that serve as buckets
  private UnsortedTableMap<K,V>[] table;   // initialized within createTable

  // provide constructors with configurable load factor
  /** Creates a hash table with capacity 17, load factor 0.5, and prime factor 109345121. */
  public LF_ChainHashMap() { super(); }

  /** Creates a hash table with given capacity, load factor 0.5, and prime factor 109345121. */
  public LF_ChainHashMap(int cap) { super(cap); }

  /** Creates a hash table with the given capacity and prime factor, load factor 0.5. */
  public LF_ChainHashMap(int cap, int p) { super(cap, p); }

  /** Creates a hash table with given capacity and load factor, prime factor 109345121. */
  public LF_ChainHashMap(int cap, double loadFactor) { super(cap, loadFactor); }

  /** Creates a hash table with the given capacity, prime factor, and load factor. */
  public LF_ChainHashMap(int cap, int p, double loadFactor) { super(cap, p, loadFactor); }

  /** Creates an empty table having length equal to current capacity. */
  @Override
  @SuppressWarnings({"unchecked"})
  protected void createTable() {
    table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
  }

  /**
   * Returns value associated with key k in bucket with hash value h.
   * If no such entry exists, returns null.
   */
  @Override
  protected V bucketGet(int h, K k) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null) return null;
    return bucket.get(k);
  }

  /**
   * Associates key k with value v in bucket with hash value h, returning
   * the previously associated value, if any.
   */
  @Override
  protected V bucketPut(int h, K k, V v) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null)
      bucket = table[h] = new UnsortedTableMap<>();
    int oldSize = bucket.size();
    V answer = bucket.put(k,v);
    n += (bucket.size() - oldSize);   // size may have increased
    return answer;
  }

  /**
   * Removes entry having key k from bucket with hash value h, returning
   * the previously associated value, if found.
   */
  @Override
  protected V bucketRemove(int h, K k) {
    UnsortedTableMap<K,V> bucket = table[h];
    if (bucket == null) return null;
    int oldSize = bucket.size();
    V answer = bucket.remove(k);
    n -= (oldSize - bucket.size());   // size may have decreased
    return answer;
  }

  /**
   * Returns an iterable collection of all key-value entries of the map.
   */
  @Override
  public Iterable<Entry<K,V>> entrySet() {
    ArrayList<Entry<K,V>> buffer = new ArrayList<>();
    for (int h=0; h < capacity; h++)
      if (table[h] != null)
        for (Entry<K,V> entry : table[h].entrySet())
          buffer.add(entry);
    return buffer;
  }

  /** Returns the number of buckets currently in use. */
  public int getBucketsInUse() {
    int count = 0;
    for (int h = 0; h < capacity; h++) {
      if (table[h] != null && table[h].size() > 0) {
        count++;
      }
    }
    return count;
  }

  /** Returns the maximum bucket size (longest chain). */
  public int getMaxBucketSize() {
    int maxSize = 0;
    for (int h = 0; h < capacity; h++) {
      if (table[h] != null) {
        maxSize = Math.max(maxSize, table[h].size());
      }
    }
    return maxSize;
  }

  /** Returns the average bucket size for non-empty buckets. */
  public double getAverageBucketSize() {
    int totalSize = 0;
    int bucketsInUse = 0;
    for (int h = 0; h < capacity; h++) {
      if (table[h] != null && table[h].size() > 0) {
        totalSize += table[h].size();
        bucketsInUse++;
      }
    }
    return bucketsInUse > 0 ? (double) totalSize / bucketsInUse : 0.0;
  }
}