package ytb.common.testcase.mongodb;

import com.jtest.testframe.ITestImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import ytb.common.utils.YtbUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//net start Mongodb

public class TestMongodb extends ITestImpl {
    MongoClient mongoClient = new MongoClient("mysql.kunlong.com", 27017);
    MongoDatabase db = mongoClient.getDatabase("project");
    //MongoDatabase db = mongoClient.getDatabase("manager");

    public void suiteSetUp() {
        System.out.println("leijm");

        //  db.createCollection("leijm");
        //  db.createCollection("leijm1");
        //  db.createCollection("leijm2");
    }

    public void suiteTearDown() throws IOException {
    }

    public void setUp() {
    }

    public void tearDown() throws IOException {
    }

    public void test0001_getCount() {
        MongoCollection<Document> leijm = db.getCollection("leijm");
        System.out.println(leijm.count());
    }


    public void test0002_listCollections() {
        for (Document doc : db.listCollections()) {
            System.out.println(doc);
        }
    }

    public void test0003_insert() {
        MongoCollection<Document> collection = db.getCollection("project");
        Document document = new Document("_id", 1999).append("title", "MongoDB Insert Demo")
                .append("description", "database")
                .append("likes", 30)
                .append("by", "yiibai point")
                .append("url", "http://www.yiibai.com/mongodb/");
        document = new Document().append("title", "MongoDB Insert Demo")
                .append("description", "database")
                .append("likes", 30)
                .append("by", "yiibai point")
                .append("url", "http://www.yiibai.com/mongodb/");

        collection.insertOne(document);
        System.out.print(document.toJson());

        Document doc1 = new Document("name", "Amarcord Pizzeria")
                .append("contact", new Document("phone", "264-555-0193")
                        .append("email", "amarcord.pizzeria@example.net")
                        .append("location", Arrays.asList(-73.88502, 40.749556)))
                .append("stars", 2)
                .append("categories", Arrays.asList("Pizzeria", "Italian", "Pasta"));


        Document doc2 = new Document("name", "Blue Coffee Bar")
                .append("contact", new Document("phone", "604-555-0102")
                        .append("email", "bluecoffeebar@example.com")
                        .append("location", Arrays.asList(-73.97902, 40.8479556)))
                .append("stars", 5)
                .append("categories", Arrays.asList("Coffee", "Pastries"));

        List<Document> documents = new ArrayList<Document>();
        documents.add(doc1);
        documents.add(doc2);
        collection.insertMany(documents);

        for (Document doc : collection.find()) {
            System.out.println(doc);
        }
    }

    public void test0004_del() {
        MongoCollection<Document> collection = db.getCollection("leijm1");
        collection.deleteOne(Filters.eq("name", "Blue"));
        // 删除所有符合条件的文档
        collection.deleteMany(Filters.eq("name", "Blue Coffee Bar"));
        int i = 1;
        for (Document doc : collection.find()) {
            System.out.println(doc);
            System.out.println(i++);
        }
    }

    public void test0005_jdbc() throws Exception {
//        Class.forName("mongodb.jdbc.MongoDriver");
//        String url = "jdbc:mongo://mysql.kunlong.com:27017/tpch";
//        Connection con = DriverManager.getConnection(url, "dbuser", "dbuser");
//        Statement stmt = con.createStatement();
//        String sql = "SELECT * FROM nation WHERE n_name >= 'C';";
//        ResultSet rst = stmt.executeQuery(sql);
//        ResultSetMetaData meta = rst.getMetaData();
//        int numColumns = meta.getColumnCount();
//        //System.out.print(meta.getcolumnName(1));
//        for (int j = 2; j <= meta.getColumnCount(); j++)
//            System.out.print(", " + meta.getColumnName(j));
//        System.out.println();
//        while (rst.next()) {
//            System.out.print(rst.getObject(1));
//            for (int j = 2; j <= numColumns; j++)
//                System.out.println();
//        }
//        rst.close();
//        stmt.close();
//        con.close();
    }

    /*
    * 左边是mongodb查询语句，右边是sql语句。对照着用，挺方便。
db.users.find() select * from users
db.users.find({"age" : 27}) select * from users where age = 27
db.users.find({"username" : "joe", "age" : 27}) select * from users where "username" = "joe" and age = 27
db.users.find({}, {"username" : 1, "email" : 1}) select username, email from users
db.users.find({}, {"username" : 1, "_id" : 0}) // no case  // 即时加上了列筛选，_id也会返回；必须显式的阻止_id返回
db.users.find({"age" : {"$gte" : 18, "$lte" : 30}}) select * from users where age >=18 and age <= 30 // $lt(<) $lte(<=) $gt(>) $gte(>=)
db.users.find({"username" : {"$ne" : "joe"}}) select * from users where username <> "joe"
db.users.find({"ticket_no" : {"$in" : [725, 542, 390]}}) select * from users where ticket_no in (725, 542, 390)
db.users.find({"ticket_no" : {"$nin" : [725, 542, 390]}}) select * from users where ticket_no not in (725, 542, 390)
db.users.find({"$or" : [{"ticket_no" : 725}, {"winner" : true}]}) select * form users where ticket_no = 725 or winner = true
db.users.find({"id_num" : {"$mod" : [5, 1]}}) select * from users where (id_num mod 5) = 1
db.users.find({"$not": {"age" : 27}}) select * from users where not (age = 27)
db.users.find({"username" : {"$in" : [null], "$exists" : true}}) select * from users where username is null // 如果直接通过find({"username" : null})进行查询，那么连带"没有username"的纪录一并筛选出来
db.users.find({"name" : /joey?/i}) // 正则查询，value是符合PCRE的表达式
db.food.find({fruit : {$all : ["apple", "banana"]}}) // 对数组的查询, 字段fruit中，既包含"apple",又包含"banana"的纪录
db.food.find({"fruit.2" : "peach"}) // 对数组的查询, 字段fruit中，第3个(从0开始)元素是peach的纪录
db.food.find({"fruit" : {"$size" : 3}}) // 对数组的查询, 查询数组元素个数是3的记录，$size前面无法和其他的操作符复合使用
db.users.findOne(criteria, {"comments" : {"$slice" : 10}}) // 对数组的查询，只返回数组comments中的前十条，还可以{"$slice" : -10}， {"$slice" : [23, 10]}; 分别返回最后10条，和中间10条
db.people.find({"name.first" : "Joe", "name.last" : "Schmoe"})  // 嵌套查询
db.blog.find({"comments" : {"$elemMatch" : {"author" : "joe", "score" : {"$gte" : 5}}}}) // 嵌套查询，仅当嵌套的元素是数组时使用,
db.foo.find({"$where" : "this.x + this.y == 10"}) // 复杂的查询，$where当然是非常方便的，但效率低下。对于复杂查询，考虑的顺序应当是 正则 -> MapReduce -> $where
db.foo.find({"$where" : "function() { return this.x + this.y == 10; }"}) // $where可以支持javascript函数作为查询条件
db.foo.find().sort({"x" : 1}).limit(1).skip(10); // 返回第(10, 11]条，按"x"进行排序; 三个limit的顺序是任意的，应该尽量避免skip中使用large-number
    *
    * */
//    public DBObject findDocumentById(MongoCollection<Document> collection,String id) {
//        BasicDBObject query = new BasicDBObject();
//        query.put("_id", new ObjectId(id));
//        //DBObject dbObj = collection.findOne(query);
//        return dbObj;
//    }

    public void test0006_qry() {
        MongoCollection<Document> collection = db.getCollection("project");
        collection.find(Filters.eq("name", "MongoDB Insert Demo"));
        // 删除所有符合条件的文档
        //db.users.find({"name":"MongoDB Insert Demo"})
        int i = 1;
        for (Document doc : collection.find(Filters.eq("title", "MongoDB Insert Demo")
        )) {
            System.out.println( doc );
        }
        ObjectId id = new ObjectId("5cf14a9c8d7ff9176886a848");
        for (Document doc : collection.find(Filters.eq("_id", id)
        )) {
            System.err.println(doc.get("_id"));
        }

        for (Document doc : collection.find()) {
            System.out.println(doc.getObjectId("_id"));
        }
        System.out.println("");
        System.out.println(new ObjectId());
        System.out.println( ObjectId.getCurrentCounter());
        System.out.println(new ObjectId());
        System.out.println(new ObjectId());
        System.out.println( ObjectId.getCurrentCounter());
        System.out.println( new ObjectId().toByteArray().length);
        //System.out.println( YtbUtils.getUUID(true).toByteArray().length);
        System.out.println( ObjectId.getGeneratedMachineIdentifier());
        System.out.println( ObjectId.getGeneratedProcessIdentifier());
        System.out.println(YtbUtils.getUUID(true));

    }

//    use world;
//    INSERT INTO test (k) VALUE( x'0123456789ABCDEF' );
//
//    select * from test where k=x'0123456789ABCDEF';
    public static void main(String[] args) {

        run(TestMongodb.class, 3);
    }
}
