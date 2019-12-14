package ytb.common.testcase.redis;

import com.jtest.testframe.ITestImpl;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//1、启动Redis
//首先进入Redis的安装目录。
//d:
//cd D:\redis\redis-latest
//然后启动Redis
//redis-server.exe redis.window.conf
//启动后如下图所示：
//这里写图片描述
//
//2、测试Redis
//再打开一个DOS窗口，记住，Redis服务端窗口不要关闭。
//首先连接Redis服务端，然后往Redis中添加键值对，接着按照键取值。
//连接Redis服务端：redis-cli.exe -h 192.168.1.103 -p 6379
//-h表示host，是主机的意思，这里的ip地址可以在DOS窗口通过ipconfig查看。
//-p是端口的意思，Redis的端口默认是6379
public class TestRedisClient extends ITestImpl {

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池
    String ip = "mysql.kunlong.com";

    public TestRedisClient() {
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();


    }
    public void setUp() {
    }

    public void tearDown() throws IOException {
        jedis.close();//.disconnect();
        shardedJedis.close();//.disconnect();
    }
    /**
     * 初始化非切片池
     */
    private void initialPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(2000l);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config,ip,6379);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool()
    {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000l);
        config.setTestOnBorrow(false);
        // slave链接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo(ip, 6379, "master"));

        // 构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);
    }

    public void show() {
        test0001_KeyOperate();
        StringOperate();
        test0002_ListOperate();
        SetOperate();
        SortedSetOperate();
        test0003_HashOperate();
        jedisPool.returnResource(jedis);
        shardedJedisPool.returnResource(shardedJedis);
    }

    public void test0001_KeyOperate() {
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));

        shardedJedis.set("a","test.web.kunshan");
        System.out.println("shardedJedis 存储的字符串为: "+ jedis.get("a"));

    }

    private void StringOperate() {

    }

    public void test0002_ListOperate() {
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
    }

    private void SetOperate() {
    }

    private void SortedSetOperate() {
    }

    public void test0003_HashOperate() {
    }

    /**
     //     * redis操作map集合
     //     */
    public void test0004_testMap() {
        // 添加数据
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "xiangjun");
        map.put("age", "22");
        map.put("qq", "5443343");
        jedis.hmset("user", map);
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "songjun");
        map1.put("age", "122");
        map1.put("qq", "15443343");
        jedis.hmset("user1", map1);

        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变
        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
        System.out.println(rsmap);

        // 删除map中的某个键值
        jedis.hdel("user", "age");
        System.out.println(jedis.hmget("user", "age")); // 因为删除了，所以返回的是null
        System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
        System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
        System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key
        System.out.println(jedis.hvals("user"));// 返回map对象中的所有value

        System.out.println("***");
        for (String key : jedis.hkeys("user")) {
            System.out.println(key + ":" + jedis.hmget("user", key));
        }
        for (String key : jedis.hkeys("user1")) {
            System.out.println(key + ":" + jedis.hmget("user1", key));
        }

    }
    public void test0005_setByte() {

        shardedJedis.set("a".getBytes(),"test.web.kunshan".getBytes());
        System.out.println("redis get a:"+ new String(jedis.get("a".getBytes
                ())));

    }
    public static void main(String[] args) {
        run(TestRedisClient.class,5);
    }
}
