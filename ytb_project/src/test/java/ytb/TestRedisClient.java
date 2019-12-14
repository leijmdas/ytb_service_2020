//package ytb;
//
//import com.jtest.testframe.ITestImpl;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import redis.clients.jedis.*;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
////1、启动Redis
////首先进入Redis的安装目录。
////d:
////cd D:\redis\redis-latest
////然后启动Redis
////redis-server.exe redis.window.conf
////启动后如下图所示：
////这里写图片描述
////
////2、测试Redis
////再打开一个DOS窗口，记住，Redis服务端窗口不要关闭。
////首先连接Redis服务端，然后往Redis中添加键值对，接着按照键取值。
////连接Redis服务端：redis-cli.exe -h 192.168.1.103 -p 6379
////-h表示host，是主机的意思，这里的ip地址可以在DOS窗口通过ipconfig查看。
////-p是端口的意思，Redis的端口默认是6379
//public class TestRedisClient extends ITestImpl {
//
//    private RedisProperties.Jedis jedis;//非切片额客户端连接
//    private JedisPool jedisPool;//非切片连接池
//    private ShardedJedis shardedJedis;//切片额客户端连接
//    private ShardedJedisPool shardedJedisPool;//切片连接池
//    String ip="mysql.kunlong.com";
//
//    public TestRedisClient()
//    {
//        initialPool();
//        initialShardedPool();
//        shardedJedis = shardedJedisPool.getResource();
//        jedis = jedisPool.getResource();
//
//
//    }
//    public void setUp() {
//    }
//
//    public void tearDown() throws IOException {
//        jedis.close();//.disconnect();
//        shardedJedis.close();//.disconnect();
//    }
//    /**
//     * 初始化非切片池
//     */
//    private void initialPool()
//    {
//        // 池基本配置
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(20);
//        config.setMaxIdle(5);
//        config.setMaxWaitMillis(1000l);
//        config.setTestOnBorrow(false);
//
//        jedisPool = new JedisPool(config,ip,6379);
//    }
//
//    /**
//     * 初始化切片池
//     */
//    private void initialShardedPool()
//    {
//        // 池基本配置
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(20);
//        config.setMaxIdle(5);
//        config.setMaxWaitMillis(1000l);
//        config.setTestOnBorrow(false);
//        // slave链接
//        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//        shards.add(new JedisShardInfo(ip, 6379, "master"));
//
//        // 构造池
//        shardedJedisPool = new ShardedJedisPool(config, shards);
//    }
//
//    public void show() {
//        test0001_KeyOperate();
//        StringOperate();
//        test0002_ListOperate();
//        SetOperate();
//        SortedSetOperate();
//        test0003_HashOperate();
//        jedisPool.returnResource(jedis);
//        shardedJedisPool.returnResource(shardedJedis);
//    }
//
//    public void test0001_KeyOperate() {
//        jedis.set("runoobkey", "www.runoob.com");
//        // 获取存储的数据并输出
//        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
//
//        shardedJedis.set("a","test.web.kunshan");
//        System.out.println("shardedJedis 存储的字符串为: "+ jedis.get("a"));
//
//    }
//
//    private void StringOperate() {
//
//    }
//
//    public void test0002_ListOperate() {
//        System.out.println("连接成功");
//        //存储数据到列表中
//        jedis.lpush("site-list", "Runoob");
//        jedis.lpush("site-list", "Google");
//        jedis.lpush("site-list", "Taobao");
//        // 获取存储的数据并输出
//        List<String> list = jedis.lrange("site-list", 0 ,2);
//        for(int i=0; i<list.size(); i++) {
//            System.out.println("列表项为: "+list.get(i));
//        }
//    }
//
//    private void SetOperate() {
//    }
//
//    private void SortedSetOperate() {
//    }
//
//    public void test0003_HashOperate() {
//    }
//
//    /**
//     //     * redis操作map集合
//     //     */
//    public void test0004_testMap() {
//        // 添加数据
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("name", "xiangjun");
//        map.put("age", "22");
//        map.put("qq", "5443343");
//        jedis.hmset("user", map);
//        Map<String, String> map1 = new HashMap<String, String>();
//        map1.put("name", "songjun");
//        map1.put("age", "122");
//        map1.put("qq", "15443343");
//        jedis.hmset("user1", map1);
//
//        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
//        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变
//        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
//        System.out.println(rsmap);
//
//        // 删除map中的某个键值
//        jedis.hdel("user", "age");
//        System.out.println(jedis.hmget("user", "age")); // 因为删除了，所以返回的是null
//        System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
//        System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
//        System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key
//        System.out.println(jedis.hvals("user"));// 返回map对象中的所有value
//
//        System.out.println("***");
//        for (String key : jedis.hkeys("user")) {
//            System.out.println(key + ":" + jedis.hmget("user", key));
//        }
//        for (String key : jedis.hkeys("user1")) {
//            System.out.println(key + ":" + jedis.hmget("user1", key));
//        }
//
//    }
//
//    public static void main(String[] args) {
//        run(TestRedisClient.class,1);
//    }
//}
//
////ublic class TestRedis {
////    private Jedis jedis;
////
////    /**
////     * 连接redis服务器
////     */
////    public void connectRedis() {
////        // 连接redis服务器
////        jedis = RedisUtil.getJedis();
////    }
////
////    /**
////     * redis操作字符串
////     */
////    public void testString() {
////        // 添加数据
////        jedis.set("name", "xiangjun");
////        System.out.println(jedis.get("name"));
////
////        // 拼接字符串
////        jedis.append("name", ".com");
////        System.out.println(jedis.get("name"));
////
////        // 删除数据
////        jedis.del("name");
////        System.out.println(jedis.get("name"));
////
////        // 设置多个键值对
////        jedis.mset("name", "xiangjun", "age", "23", "qq", "47670002");
////        jedis.incr("age"); // 加1操作
////        System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
////    }
////
////    /**
////     * redis操作map集合
////     */
////    public void testMap() {
////        // 添加数据
////        Map<String, String> map = new HashMap<String, String>();
////        map.put("name", "xiangjun");
////        map.put("age", "22");
////        map.put("qq", "5443343");
////        jedis.hmset("user", map);
////
////        // 取出user中的name，执行结果:[minxr]-->注意结果是一个泛型的List
////        // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变
////        List<String> rsmap = jedis.hmget("user", "name", "age", "qq");
////        System.out.println(rsmap);
////
////        // 删除map中的某个键值
////        jedis.hdel("user", "age");
////        System.out.println(jedis.hmget("user", "age")); // 因为删除了，所以返回的是null
////        System.out.println(jedis.hlen("user")); // 返回key为user的键中存放的值的个数2
////        System.out.println(jedis.exists("user"));// 是否存在key为user的记录 返回true
////        System.out.println(jedis.hkeys("user"));// 返回map对象中的所有key
////        System.out.println(jedis.hvals("user"));// 返回map对象中的所有value
////
////        Iterator<String> iter = jedis.hkeys("user").iterator();
////        while (iter.hasNext()) {
////            String key = iter.next();
////            System.out.println(key + ":" + jedis.hmget("user", key));
////        }
////    }
////
////    /**
////     * redis操作list集合
////     */
////    public void testList() {
////        // 开始前，先移除所有的内容
////        jedis.del("java framework");
////        System.out.println(jedis.lrange("java framework", 0, -1));
////        // 先向key java framework中存放三条数据
////        jedis.lpush("java framework", "spring");
////        jedis.lpush("java framework", "struts");
////        jedis.lpush("java framework", "hibernate");
////        // 再取出所有数据jedis.lrange是按范围取出，
////        // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
////        System.out.println(jedis.lrange("java framework", 0, -1));
////
////        jedis.del("java framework");
////        jedis.rpush("java framework", "spring");
////        jedis.rpush("java framework", "struts");
////        jedis.rpush("java framework", "hibernate");
////        System.out.println(jedis.lrange("java framework", 0, -1));
////    }
////
////    /**
////     * redis操作set集合
////     */
////    public void testSet() {
////        // 添加
////        jedis.sadd("user", "liuling");
////        jedis.sadd("user", "xinxin");
////        jedis.sadd("user", "ling");
////        jedis.sadd("user", "zhangxinxin");
////        jedis.sadd("user", "who");
////        // 移除noname
////        jedis.srem("user", "who");
////        System.out.println(jedis.smembers("user"));// 获取所有加入的value
////        System.out.println(jedis.sismember("user", "who"));// 判断 who
////        // 是否是user集合的元素
////        System.out.println(jedis.srandmember("user"));
////        System.out.println(jedis.scard("user"));// 返回集合的元素个数
////    }
////
////    /**
////     * redis排序
////     */
////    public void testSort() {
////        // jedis 排序
////        // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
////        jedis.del("a");// 先清除数据，再加入数据进行测试
////        jedis.rpush("a", "1");
////        jedis.lpush("a", "6");
////        jedis.lpush("a", "3");
////        jedis.lpush("a", "9");
////        System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]
////        System.out.println(jedis.sort("a")); // [1, 3, 6, 9] //输入排序后结果
////        System.out.println(jedis.lrange("a", 0, -1));
////    }
////
////    /**
////     * redis连接池
////     */
////    public void testRedisPool() {
////        RedisUtil.getJedis().set("newname", "test");
////        System.out.println(RedisUtil.getJedis().get("newname"));
////    }