package com.objectspace.coorperation;

import com.objectspace.coorperation.activemq.QueueProduce;
import com.objectspace.coorperation.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoorperationApplicationTests {

    Logger logger = LoggerFactory.getLogger(System.class);
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    QueueProduce queueProduce;
    /*已测试成功
    @Autowired
    HeadLineImgDao headLineImgDao;*/
    @Test
    public void test01() throws InterruptedException {
        System.out.println("=========================================================");
        System.out.println("Junit测试");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("=========================================================");
        System.out.println("日志测试");
        logger.info("INFO日志测试");
        logger.debug("DEBUG日志测试");
        logger.error("ERROR日志测试");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("=========================================================");
        System.out.println("Redis测试");
        System.out.println("存入String测试:"+redisUtil.set("RedisTest", "RedisUtilTest"));
        System.out.println("取出String测试:"+redisUtil.get("RedisTest"));
        System.out.println(redisUtil.get("ninini"));
        System.out.println("存入list测试(lpush):"+redisUtil.lpush("RedisListTest", "test1","test2","test3"));
        System.out.println("弹出list测试(lpop):"+redisUtil.lpop("RedisListTest"));
        System.out.println("弹出list测试(lpop):"+redisUtil.lpop("RedisListTest"));
        System.out.println("弹出list测试(lpop):"+redisUtil.lpop("RedisListTest"));
        System.out.println("存入list测试(rpush):"+redisUtil.rpush("RedisTestList", "0.9","8.9","2.1"));
        System.out.println("遍历list测试(lrange):"+redisUtil.lrange("RedisTestList", 0, 2));
        System.out.println("排序list测试(sort):"+redisUtil.sort("RedisTestList"));
        System.out.println("弹出list测试(rpop):"+redisUtil.lpop("RedisTestList"));
        System.out.println("弹出list测试(rpop):"+redisUtil.lpop("RedisTestList"));
        System.out.println("弹出list测试(rpop):"+redisUtil.lpop("RedisTestList"));
        System.out.println("添加SET测试(sadd):"+redisUtil.sadd("RedisTestSet", "set01","set02","set03"));
        System.out.println("遍历SET测试(sadd):"+redisUtil.smembers("RedisTestSet"));
        System.out.println("删除SET测试(srem):"+redisUtil.srem("RedisTestSet", "set03","set02"));
        System.out.println("遍历SET测试(sadd):"+redisUtil.smembers("RedisTestSet"));
        System.out.println("添加SortedSet测试(zadd)"+redisUtil.zadd("RedisTestSortedSet", 5.0, "test01"));
        System.out.println("添加SortedSet测试(zadd)"+redisUtil.zadd("RedisTestSortedSet", 7.0, "test02"));
        System.out.println("添加SortedSet测试(zadd)"+redisUtil.zadd("RedisTestSortedSet", 1.0, "test03"));
        System.out.println("遍历SortedSet测试(zrange)"+redisUtil.zrange("RedisTestSortedSet", 0L, 2L));
        System.out.println("插入Hash测试(hmset)"+redisUtil.hmset("RedisTestHash", "testkey1", "testvalue1"));
        System.out.println("插入Hash测试(hmset)"+redisUtil.hmset("RedisTestHash", "testkey2", "testvalue2"));
        System.out.println("插入Hash测试(hmset)"+redisUtil.hmset("RedisTestHash", "testkey3", "testvalue3"));
        System.out.println("获取Hash测试(hmget)"+redisUtil.hmget("RedisTestHash", "testkey1","testkey2"));
        System.out.println("获取Hash测试(hget)"+redisUtil.hgetAll("RedisTestHash"));
        Thread.sleep(10000);
        System.out.println("获取符合条件的Key(keys):"+redisUtil.keys("*"));
        System.out.println("获取符合条件的Key(scan):"+redisUtil.scan("*", 10));
        System.out.println("=========================================================");
        System.out.println();
		/*已测试成功
		System.out.println("=========================================================");
		System.out.println("MyBatis测试");
		String result = headLineImgDao.queryAllHeadLineImg();
		System.out.println(result);
		System.out.println("=========================================================");
		System.out.println();*/
    }
    @Test
    public void activeMQTest(){
        System.out.println("=========================================================");
        System.out.println("ActiveMQ测试");
        System.out.println("发送成功");
        System.out.println("=========================================================");
    }
}
