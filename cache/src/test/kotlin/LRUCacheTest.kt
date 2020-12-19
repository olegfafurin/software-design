//import junit.framework.Assert.assertTrue
import org.imd.sd.cache.LRUCache
//import org.junit.Assert.*
import org.junit.Assert
import org.junit.Test

//import sun.jvm.hotspot.utilities.Assert

/**
 * created by imd on 19.12.2020
 */

class LRUCacheTest {

    @Test
    fun consistentSize() {
        val cache = LRUCache<String, Int>(4)
        Assert.assertTrue("cache load equals to map size", cache.load == cache.map.size)
        Assert.assertTrue("cache map size is not greater that cache capacity", cache.map.size <= cache.CACHE_CAPACITY)
    }

    @Test
    fun emptyCacheLoad() {
        val cache = LRUCache<String, Int>(0)
        cache.put("anything", 1)
        Assert.assertTrue("no elements added to cache of size 0", cache.load == 0)
    }

    @Test
    fun emptyCache() {
        val cache = LRUCache<String, Int>(0)
        cache.put("1", 1)
        Assert.assertTrue("no mapping in empty cache", cache.get("1") == null)
    }

    @Test
    fun replacementPolicy() {
        val cache = LRUCache<String, Int>(5)
        for (i in 1..10) {
            cache.put("$i", i)
        }
        Assert.assertTrue(
            "LRU replacement policy check #1",
            cache.map.values.map { it.value }.toSet() == (6..10).toSet()
        )
        cache.put("1", 1)
        Assert.assertTrue(
            "LRU replacement policy check #2",
            cache.map.values.map { it.value }.toSet() == setOf(1, 7, 8, 9, 10)
        )
    }

    @Test
    fun valuesMapping() {
        val cache = LRUCache<String, Int>(5)
        cache.put("1", 1)
        Assert.assertTrue(
            "correct value mapped",
            cache.get("1") == 1
        )
        cache.put("1", 0)
        Assert.assertTrue(
            "correct changed value mapped",
            cache.get("1") == 0
        )
    }

}