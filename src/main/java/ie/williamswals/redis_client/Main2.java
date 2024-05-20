package ie.williamswals.redis_client;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

public class Main2 {

    public static void main(String[] args) {
        try (Jedis jedis = new JedisPool("localhost", 6379).getResource()) {

            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo")); // prints bar

            Map<String, String> hash = new HashMap<>();
            hash.put("name", "John");
            hash.put("surname", "Smith");
            hash.put("company", "Redis");
            hash.put("age", "29");
            jedis.hset("user-session:123", hash);
            Map<String,String> retrieved = jedis.hgetAll("user-session:123");

            System.out.println("First Name: " + retrieved.get("name"));
            System.out.println("Surname: " + retrieved.get("surname"));
            System.out.println("Company: " + retrieved.get("company"));
            System.out.println("Age: " + retrieved.get("age"));

            // Prints: {name=John, surname=Smith, company=Redis, age=29}
        }
    }
}
