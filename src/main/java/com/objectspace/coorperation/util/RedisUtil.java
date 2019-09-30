package com.objectspace.coorperation.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

/**
 * Redis操作工具类
 *
 * @author Object
 *
 */
@Component
@Lazy(false)
public class RedisUtil{

    @Autowired
    private JedisPool jedisPool;


    private Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    /**
     * 通常是MyBatis二级缓存调用，功能和String类型的set相同
     * @param key
     * @param value
     * @return
     */
    public String set(byte[] key,byte[] value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        }catch(Exception e){
            logger.error("往Redis中存入序列化key出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return "failure";
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 通常是MyBatis二级缓存调用，功能和String类型的get相同
     * @param key
     * @return
     */
    public byte[] get(byte[] key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch(Exception e) {
            logger.error("从Redis中取出序列化对象出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * 设置String类型的值，无过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            logger.error("往Redis中存入String过程出现异常!");
            logger.error("异常信息:" + e.getMessage());
            return "failure";
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    /**
     * 获取KEY = key的Value,类型为String
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("从缓存中取K,V时出现异常!");
            logger.error("异常信息:" + e.getMessage());
            return "failure";
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key);
        }catch(Exception e) {
            logger.error("删除"+key+"出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return -1L;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 往Redis List左侧Push元素
     *
     * @param listName
     * @param values
     * @return
     */
    public Long lpush(String listName, String... values) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpush(listName, values);
        } catch (Exception e) {
            logger.error("往Redis中存入list过程出现异常(lpush)!");
            logger.error("异常信息:" + e.getMessage());
            return -1L;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    /**
     * 弹出Redis List左侧元素
     *
     * @param listName
     * @return
     */
    public String lpop(String listName) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpop(listName);
        } catch (Exception e) {
            logger.error("从Redis中获取list过程出现异常(lpop)!");
            logger.error("异常信息:" + e.getMessage());
            return "failure";
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    /**
     * 往redis右侧push元素
     * @param listName
     * @param values
     * @return
     */
    public Long rpush(String listName, String... values) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpush(listName, values);
        } catch (Exception e) {
            logger.error("往Redis中存入list过程出现异常(rpush)!");
            logger.error("异常信息:" + e.getMessage());
            return -1L;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
    /**
     * 从Redis右侧弹出元素
     * @param listName
     * @return
     */
    public String rpop(String listName) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpop(listName);
        }catch(Exception e) {
            logger.error("从Redis中获取list过程出现异常(rpop)!");
            logger.error("异常信息:"+e.getMessage());
            return "failure";
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 遍历list中的start —— end 的元素
     * @param listName
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String listName, long start, long end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrange(listName, start, end);
        }catch(Exception e) {
            logger.error("获取指定list多个元素过程出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 排序，默认将values指定为double进行排序，如果无法转换则抛出异常
     * @param listName
     * @return
     */
    public List<String> sort(String listName){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sort(listName);
        }catch(Exception e) {
            logger.error("排序出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 往Redis Set中存入数据
     * Set中的数据不保证有序性，且不可重复 重复返回0
     * @param setName
     * @param members
     * @return
     */
    public Long sadd(String setName,String... members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(setName, members);
        }catch(Exception e) {
            logger.error("sadd出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return 0L;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 通过setName和members对SET中的元素进行删除操作
     * @param setName
     * @param members
     * @return
     */
    public Long srem(String setName,String...members) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srem(setName, members);
        }catch(Exception e) {
            logger.error("srem出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return 0L;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 遍历SET
     * @param setName
     * @return
     */
    public Set<String> smembers(String setName){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(setName);
        }catch(Exception e) {
            logger.error("smembers出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     *
     * 将元素加入排序队列，按score进行排序
     * @param setName 集合名
     * @param score 权重
     * @param member 成员
     * @return
     */
    public Long zadd(String setName,Double score,String member) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zadd(setName, score, member);
        }catch(Exception e) {
            logger.error("zadd出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return 0L;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }

    /**
     * 遍历有序集合Sorted Set
     * @param setName
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(String setName,Long start,Long end){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.zrange(setName, start, end);
        }catch(Exception e) {
            logger.error("zrange出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 往Redis中添加类似HashMap的数据结构，适合存对象，如果存入相同的键值对，后写入的将会把前写入的覆盖
     * @param mapName
     * @param key
     * @param value
     * @return
     */
    public String hmset(String mapName,String key,String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Map<String,String> model = new HashMap<String,String>();
            model.put(key, value);
            return jedis.hmset(mapName,model);
        }catch(Exception e) {
            logger.error("hmset出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return "failure";
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 从Hash中取某个属性值
     * @param mapName
     * @param keys
     * @return
     */
    public List<String> hmget(String mapName,String... keys) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hmget(mapName, keys);
        }catch(Exception e) {
            logger.error("hmget出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 获取Hash中的所有属性
     * @param mapName
     * @return
     */
    public Map<String,String> hgetAll(String mapName){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.hgetAll(mapName);
        }catch(Exception e) {
            logger.error("hgetAll出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 返回所有符合pattern条件的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.keys(pattern);
        }catch(Exception e) {
            logger.error("keys出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 遍历符合pattern条件的key 使用 scan cursor MATCH pattern COUNT count
     * @param pattern
     * @param count
     * @return
     */
    public Set<String> scan(String pattern,Integer count){
        Jedis jedis = null;
        try {
            Set<String> set = new HashSet<String>();
            jedis = jedisPool.getResource();
            //设置游标初始值为0
            String cursor = ScanParams.SCAN_POINTER_START;
            ScanParams scanParams = new ScanParams();
            scanParams.match(pattern);
            scanParams.count(count);
            while(true) {
                ScanResult<String> scanResult = jedis.scan(cursor, scanParams);
                cursor = scanResult.getStringCursor();//返回0说明遍历完成
                List<String> result = scanResult.getResult();
                for(String str:result) {
                    set.add(str);
                }
                if("0".equals(cursor))
                    break;
            }
            return set;
        }catch(Exception e) {
            logger.error("scan发生异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
    /**
     * 删库跑路，没把握千万别调！
     * @return
     */
    public String flushAll() {
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            return jedis.flushAll();
        }catch(Exception e) {
            logger.error("flushAll出现异常!");
            logger.error("异常信息:"+e.getMessage());
            return null;
        }finally {
            if(jedis!=null)
                jedis.close();
        }
    }
}