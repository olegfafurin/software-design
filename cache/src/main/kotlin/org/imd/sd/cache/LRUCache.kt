package org.imd.sd.cache

/**
 * created by imd on 19.12.2020
 */

class LRUCache<K, V>(val CACHE_CAPACITY: Int) {

    inner class Node(val key: K, val value: V, var prev: Node? = null, var next: Node? = null) {
        fun remove() {
            map.remove(key)
            if (prev != null)
                prev?.next = next
            else
                start = next
            if (next != null)
                next?.prev = prev
            else
                end = prev
            listSize--
        }

        fun addToStart(): Boolean {
            if (listSize >= CACHE_CAPACITY)
                end?.remove()
            if (listSize >= CACHE_CAPACITY)
                return false
            next = start
            start?.prev = this
            start = this
            if (end == null)
                end = start
            listSize++
            return true
        }
    }

    var start: Node? = null
    var end: Node? = null
    var listSize = 0
    val map: MutableMap<K, Node> = mutableMapOf()
    var load: Int
        get() {
            return map.size
        }
        set(value) {}

    public fun put(key: K, value: V) {
        assert(listSize == map.size)
        assert(listSize <= CACHE_CAPACITY)
        if (key in map) {
            map[key]?.remove()
        }
        Node(key, value).apply {
            if (addToStart())
                map[key] = this
        }
        assert(listSize == map.size)
    }

    public fun get(key: K): V? {
        assert(listSize == map.size)
        return map[key]?.value
    }
}