package pers.ocean.redissondemo.redisson;

import lombok.SneakyThrows;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author ocean_wll
 * @date 2021/7/20
 */
public class RedissonDemo1 {

    @SneakyThrows
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        final RedissonClient client = Redisson.create(config);
        RMap<String, String> rMap = client.getMap("oceanMap");
        RLock lock = client.getLock("oceanLock");

        try {
            lock.lock();
            for (int i = 0; i < 15; i++) {
                rMap.put("rKey:" + i, "rValue:22222-" + i);
            }

            System.out.println("rKey:10");

        } finally {
            lock.unlock();
        }
    }
}
