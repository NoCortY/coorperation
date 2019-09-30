package com.objectspace.coorperation.cache;

import com.objectspace.coorperation.util.RedisUtil;
import com.objectspace.coorperation.util.SerializeUtil;
import com.objectspace.coorperation.util.SpringUtil;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RedisCache implements Cache {
    private SerializeUtil serializeUtil = new SerializeUtil();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private String id;
    private RedisUtil redisUtil;

    public RedisCache() {}
    public RedisCache(String id) {
        logger.info("MyBatis开启二级缓存!");
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }


    private synchronized RedisUtil getRedisUtil() {
        if(redisUtil==null) {
            redisUtil = SpringUtil.getBean(RedisUtil.class);
        }
        return redisUtil;
    }
    /***************做为MyBatis的二级缓存***************/
    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return id;
    }


    /**
     *
     * 写入缓存
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        RedisUtil redisUtil = getRedisUtil();
        logger.info("将数据回写Redis缓存。");
        redisUtil.set(serializeUtil.serialize(key), serializeUtil.serialize(value));

    }

    @Override
    public Object getObject(Object key) {
        Object object = null;
        RedisUtil redisUtil = getRedisUtil();
        logger.info("尝试从Redis缓存中读取数据...");
        byte[] serializedInfo = redisUtil.get(serializeUtil.serialize(key));
        if(serializedInfo!=null) {
            logger.info("成功命中Redis缓存并get所需数据!");
            object = serializeUtil.unSerialize(serializedInfo);
        }else {
            logger.info("Redis缓存中不存在所需的数据，进行穿透查询!");
        }
        return object;
    }

    @Override
    public Object removeObject(Object key) {
        RedisUtil redisUtil = getRedisUtil();
        Object object = redisUtil.del(serializeUtil.serialize(key).toString());
        return object;
    }

    @Override
    public void clear() {
        RedisUtil redisUtil = getRedisUtil();
        redisUtil.flushAll();
    }

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        // TODO Auto-generated method stub
        return readWriteLock;
    }

}
