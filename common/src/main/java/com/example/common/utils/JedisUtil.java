package com.example.common.utils;


import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisUtil {


    /**
     * 获取指定key的值,如果key不存在返回null，如果该Key存储的不是字符串，会抛出一个错误
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.get(key);
    }

    /**
     * 设置key的值为value
     *
     * @param key
     * @param value
     * @return
     */
    public static String set(String key, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.set(key, value);
    }

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys
     * @return
     */
    public static Long del(String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.del(keys);
    }

    /**
     * 通过key向指定的value值追加值
     *
     * @param key
     * @param str
     * @return
     */
    public static Long append(String key, String str) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.append(key, str);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public static Boolean exists(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.exists(key);
    }

    /**
     * 设置key value,如果key已经存在则返回0
     *
     * @param key
     * @param value
     * @return
     */
    public static Long setnx(String key, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.setnx(key, value);
    }

    /**
     * 设置key value并指定这个键值的有效期
     *
     * @param key
     * @param seconds
     * @param value
     * @return
     */
    public static String setex(String key, int seconds, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.setex(key, seconds, value);
    }

    /**
     * 通过key 和offset 从指定的位置开始将原先value替换
     *
     * @param key
     * @param offset
     * @param str
     * @return
     */
    public static Long setrange(String key, int offset, String str) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.setrange(key, offset, str);
    }

    /**
     * 通过批量的key获取批量的value
     *
     * @param keys
     * @return
     */
    public static List<String> mget(String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.mget(keys);
    }

    /**
     * 批量的设置key:value,也可以一个
     *
     * @param keysValues
     * @return
     */
    public static String mset(String... keysValues) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.mset(keysValues);
    }

    /**
     * 批量的设置key:value,可以一个,如果key已经存在则会失败,操作会回滚
     *
     * @param keysValues
     * @return
     */
    public static Long msetnx(String... keysValues) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.msetnx(keysValues);
    }

    /**
     * 设置key的值,并返回一个旧值
     *
     * @param key
     * @param value
     * @return
     */
    public static String getSet(String key, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.getSet(key, value);
    }

    /**
     * 通过下标 和key 获取指定下标位置的 value
     *
     * @param key
     * @param startOffset
     * @param endOffset
     * @return
     */
    public static String getrange(String key, int startOffset, int endOffset) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.getrange(key, startOffset, endOffset);
    }

    /**
     * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
     *
     * @param key
     * @return
     */
    public static Long incr(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.incr(key);
    }

    /**
     * 通过key给指定的value加值,如果key不存在,则这是value为该值
     *
     * @param key
     * @param integer
     * @return
     */
    public static Long incrBy(String key, long integer) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.incrBy(key, integer);
    }

    /**
     * 对key的值做减减操作,如果key不存在,则设置key为-1
     *
     * @param key
     * @return
     */
    public static Long decr(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.decr(key);
    }

    /**
     * 减去指定的值
     *
     * @param key
     * @param integer
     * @return
     */
    public static Long decrBy(String key, long integer) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.decrBy(key, integer);
    }

    /**
     * 通过key获取value值的长度
     *
     * @param key
     * @return
     */
    public static Long strLen(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.strlen(key);
    }

    /**
     * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hsetnx(String key, String field, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hsetnx(key, field, value);
    }

    /**
     * 通过key给field设置指定的值,如果key不存在,则先创建
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hset(String key, String field, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hset(key, field, value);
    }

    /**
     * 通过key同时设置 hash的多个field
     *
     * @param key
     * @param hash
     * @return
     */
    public static String hmset(String key, Map<String, String> hash) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hmset(key, hash);
    }

    /**
     * 通过key 和 field 获取指定的 value
     *
     * @param key
     * @param failed
     * @return
     */
    public static String hget(String key, String failed) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hget(key, failed);
    }

    /**
     * 设置key的超时时间为seconds
     *
     * @param key
     * @param seconds
     * @return
     */
    public static Long expire(String key, int seconds) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.expire(key, seconds);
    }

    /**
     * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
     *
     * @param key
     * @param fields 可以是 一个String 也可以是 String数组
     * @return
     */
    public static List<String> hmget(String key, String... fields) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hmget(key, fields);
    }

    /**
     * 通过key给指定的field的value加上给定的值
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public static Long hincrby(String key, String field, Long value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hincrBy(key, field, value);
    }

    /**
     * 通过key和field判断是否有指定的value存在
     *
     * @param key
     * @param field
     * @return
     */
    public static Boolean hexists(String key, String field) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hexists(key, field);
    }

    /**
     * 通过key返回field的数量
     *
     * @param key
     * @return
     */
    public static Long hlen(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hlen(key);
    }

    /**
     * 通过key 删除指定的 field
     *
     * @param key
     * @param fields 可以是 一个 field 也可以是 一个数组
     * @return
     */
    public static Long hdel(String key, String... fields) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hdel(key, fields);
    }

    /**
     * 通过key返回所有的field
     *
     * @param key
     * @return
     */
    public static Set<String> hkeys(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hkeys(key);
    }

    /**
     * 通过key返回所有和key有关的value
     *
     * @param key
     * @return
     */
    public static List<String> hvals(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hvals(key);
    }

    /**
     * 通过key获取所有的field和value
     *
     * @param key
     * @return
     */
    public static Map<String, String> hgetall(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.hgetAll(key);
    }

    /**
     * 通过key向list头部添加字符串
     *
     * @param key
     * @param strs 可以是一个string 也可以是string数组
     * @return 返回list的value个数
     */
    public static Long lpush(String key, String... strs) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.lpush(key, strs);
    }

    /**
     * 通过key向list尾部添加字符串
     *
     * @param key
     * @param strs 可以是一个string 也可以是string数组
     * @return 返回list的value个数
     */
    public static Long rpush(String key, String... strs) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.rpush(key, strs);
    }

    /**
     * 通过key设置list指定下标位置的value
     * 如果下标超过list里面value的个数则报错
     *
     * @param key
     * @param index 从0开始
     * @param value
     * @return 成功返回OK
     */
    public static String lset(String key, Long index, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.lset(key, index, value);
    }

    /**
     * 通过key从对应的list中删除指定的count个 和 value相同的元素
     *
     * @param key
     * @param count 当count为0时删除全部
     * @param value
     * @return 返回被删除的个数
     */
    public static Long lrem(String key, long count, String value) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.lrem(key, count, value);
    }

    /**
     * 通过key保留list中从strat下标开始到end下标结束的value值
     *
     * @param key
     * @param start
     * @param end
     * @return 成功返回OK
     */
    public static String ltrim(String key, long start, long end) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.ltrim(key, start, end);
    }

    /**
     * 通过key从list的头部删除一个value,并返回该value
     *
     * @param key
     * @return
     */
    public static synchronized String lpop(String key) {

        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.lpop(key);
    }

    /**
     * 通过key从list尾部删除一个value,并返回该元素
     *
     * @param key
     * @return
     */
    synchronized public static String rpop(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.rpop(key);
    }

    /**
     * 通过key从一个list的尾部删除一个value并添加到另一个list的头部,并返回该value
     * 如果第一个list为空或者不存在则返回null
     *
     * @param srckey
     * @param dstkey
     * @return
     */
    public static String rpoplpush(String srckey, String dstkey) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.rpoplpush(srckey, dstkey);
    }

    /**
     * 通过key获取list中指定下标位置的value
     *
     * @param key
     * @param index
     * @return 如果没有返回null
     */
    public static String lindex(String key, long index) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.lindex(key, index);
    }

    /**
     * 通过key返回list的长度
     *
     * @param key
     * @return
     */
    public static Long llen(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.llen(key);
    }

    /**
     * 通过key获取list指定下标位置的value
     * 如果start 为 0 end 为 -1 则返回全部的list中的value
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<String> lrange(String key, long start, long end) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.lrange(key, start, end);
    }

    /**
     * 通过key向指定的set中添加value
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public static Long sadd(String key, String... members) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sadd(key, members);
    }

    /**
     * 通过key删除set中对应的value值
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 删除的个数
     */
    public static Long srem(String key, String... members) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.srem(key, members);
    }

    /**
     * 通过key随机删除一个set中的value并返回该值
     *
     * @param key
     * @return
     */
    public static String spop(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.spop(key);
    }

    /**
     * 通过key获取set中的差集
     * 以第一个set为标准
     *
     * @param keys 可以 是一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public static Set<String> sdiff(String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sdiff(keys);
    }

    /**
     * 通过key获取set中的差集并存入到另一个key中
     * 以第一个set为标准
     *
     * @param dstkey 差集存入的key
     * @param keys   可以 是一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public static Long sdiffstore(String dstkey, String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sdiffstore(dstkey, keys);
    }

    /**
     * 通过key获取指定set中的交集
     *
     * @param keys 可以 是一个string 也可以是一个string数组
     * @return
     */
    public static Set<String> sinter(String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sinter(keys);
    }

    /**
     * 通过key获取指定set中的交集 并将结果存入新的set中
     *
     * @param dstkey
     * @param keys   可以 是一个string 也可以是一个string数组
     * @return
     */
    public static Long sinterstore(String dstkey, String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sinterstore(dstkey, keys);
    }

    /**
     * 通过key返回所有set的并集
     *
     * @param keys 可以 是一个string 也可以是一个string数组
     * @return
     */
    public static Set<String> sunion(String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sunion(keys);
    }

    /**
     * 通过key返回所有set的并集,并存入到新的set中
     *
     * @param dstkey
     * @param keys   可以 是一个string 也可以是一个string数组
     * @return
     */
    public static Long sunionstore(String dstkey, String... keys) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sunionstore(dstkey, keys);
    }

    /**
     * 通过key将set中的value移除并添加到第二个set中
     *
     * @param srckey 需要移除的
     * @param dstkey 添加的
     * @param member set中的value
     * @return
     */
    public static Long smove(String srckey, String dstkey, String member) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.smove(srckey, dstkey, member);
    }

    /**
     * 通过key获取set中value的个数
     *
     * @param key
     * @return
     */
    public static Long scard(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.scard(key);
    }

    /**
     * 通过key判断value是否是set中的元素
     *
     * @param key
     * @param member
     * @return
     */
    public static Boolean sismember(String key, String member) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.sismember(key, member);
    }

    /**
     * 通过key获取set中随机的value,不删除元素
     *
     * @param key
     * @return
     */
    public static String srandmember(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.srandmember(key);
    }

    /**
     * 通过key获取set中所有的value
     *
     * @param key
     * @return
     */
    public static Set<String> smembers(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.smembers(key);
    }


    /**
     * 通过key向zset中添加value,score,其中score就是用来排序的
     * 如果该value已经存在则根据score更新元素
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public static Long zadd(String key, double score, String member) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zadd(key, score, member);
    }

    /**
     * 通过key删除在zset中指定的value
     *
     * @param key
     * @param members 可以 是一个string 也可以是一个string数组
     * @return
     */
    public static Long zrem(String key, String... members) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zrem(key, members);
    }

    /**
     * 通过key增加该zset中value的score的值
     *
     * @param key
     * @param score
     * @param member
     * @return
     */
    public static Double zincrby(String key, double score, String member) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zincrby(key, score, member);
    }

    /**
     * 通过key返回zset中value的排名
     * 下标从小到大排序
     *
     * @param key
     * @param member
     * @return
     */
    public static Long zrank(String key, String member) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zrank(key, member);
    }

    /**
     * 通过key返回zset中value的排名
     * 下标从大到小排序
     *
     * @param key
     * @param member
     * @return
     */
    public static Long zrevrank(String key, String member) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zrevrank(key, member);
    }

    /**
     * 通过key将获取score从start到end中zset的value
     * socre从大到小排序
     * 当start为0 end为-1时返回全部
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<String> zrevrange(String key, long start, long end) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zrevrange(key, start, end);
    }

    /**
     * 通过key返回指定score内zset中的value
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public static Set<String> zrangebyscore(String key, String max, String min) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zrevrangeByScore(key, max, min);
    }

    /**
     * 通过key返回指定score内zset中的value
     *
     * @param key
     * @param max
     * @param min
     * @return
     */
    public static Set<String> zrangeByScore(String key, double max, double min) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zrevrangeByScore(key, max, min);
    }

    /**
     * 返回指定区间内zset中value的数量
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public static Long zcount(String key, String min, String max) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zcount(key, min, max);
    }

    /**
     * 通过key返回zset中的value个数
     *
     * @param key
     * @return
     */
    public static Long zcard(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zcard(key);
    }

    /**
     * 通过key获取zset中value的score值
     *
     * @param key
     * @param member
     * @return
     */
    public static Double zscore(String key, String member) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zscore(key, member);
    }

    /**
     * 通过key删除给定区间内的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Long zremrangeByRank(String key, long start, long end) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zremrangeByRank(key, start, end);
    }

    /**
     * 通过key删除指定score内的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Long zremrangeByScore(String key, double start, double end) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.zremrangeByScore(key, start, end);
    }

    /**
     * 返回满足pattern表达式的所有key
     * keys(*)
     * 返回所有的key
     *
     * @param pattern
     * @return
     */
    public static Set<String> keys(String pattern) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.keys(pattern);
    }

    /**
     * 通过key判断值得类型
     *
     * @param key
     * @return
     */
    public static String type(String key) {
        Jedis jedis = JedisPoolUtil.getJedisPoolInstance().getResource();
        return jedis.type(key);
    }


    private void close(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }


    public static void main(String[] args) {
        JedisUtil.set("test","test");
        System.out.println(JedisUtil.get("test"));
    }

}



