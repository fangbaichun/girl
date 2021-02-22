package com.fbc.frame.gateway.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;

//@Configuration
public class RedisConfig {

    //最大连接数
    @Value("${spring.redis.jedis.pool.maxTotal:30}")
    private int maxTotal;
    //最大空闲时间
    @Value("${spring.redis.jedis.pool.maxIdle:10}")
    private int maxIdle;
    //每次最大连接数
    @Value("${spring.redis.jedis.pool.numTestsPerEvictionRun:1024}")
    private int numTestsPerEvictionRun;
    //释放扫描的扫描间隔
    @Value("${spring.redis.jedis.pool.timeBetweenEvictionRunsMillis:30000}")
    private int timeBetweenEvictionRunsMillis;
    //连接的最小空闲时间
    @Value("${spring.redis.jedis.pool.minEvictableIdleTimeMillis:1800000}")
    private int minEvictableIdleTimeMillis;
    //连接控歘按时间多久后释放，当空闲时间>该值且空闲连接>最大空闲连接数时直接释放
    @Value("${spring.redis.jedis.pool.softMinEvictableIdleTimeMillis:10000}")
    private int softMinEvictableIdleTimeMillis;
    //获得链接时的最大等待毫秒数，小于0：阻塞不确定时间，默认-1
    @Value("${spring.redis.jedis.pool.maxWaitMillis:1500}")
    private int maxWaitMillis;
    //在获得链接的时候检查有效性，默认false
    @Value("${spring.redis.jedis.pool.testOnBorrow:false}")
    private boolean testOnBorrow;
    //在空闲时检查有效性，默认false
    @Value("${spring.redis.jedis.pool.testWhileIdle:true}")
    private boolean testWhileIdle;
    //连接耗尽时是否阻塞，false报异常，true阻塞超时,默认true
    @Value("${spring.redis.jedis.pool.blockWhenExhausted:false}")
    private boolean blockWhenExhausted;


    @Value("${spring.redis.host:127.0.0.1}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private int port;
    @Value("${spring.redis.password:}")
    private String password;

    @Value("${spring.redis.cluster.max-redirects:3}")
    private String maxRedirects;
    @Value("${spring.redis.cluster.nodes:127.0.0.1}")
    private List<String> nodes;

    /**
     * redisTemplate 提供redis操作相关api
     * @param jedisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 单机模式redis连接工厂
     * @param redisStandaloneConfiguration
     * @return
     */
    @Bean
    @Primary
    public RedisConnectionFactory jedisConnectionFactory(RedisStandaloneConfiguration redisStandaloneConfiguration) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setDatabase(0);
        return jedisConnectionFactory;
    }

    /**
     * 哨兵模式redis连接工厂
     * @param redisSentinelConfiguration
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(RedisSentinelConfiguration redisSentinelConfiguration, JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration,jedisPoolConfig);
        return jedisConnectionFactory;
    }

    /**
     * 集群模式redis连接工厂
     * @param redisClusterConfiguration
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration, JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration,jedisPoolConfig);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setDatabase(0);
        return jedisConnectionFactory;
    }
    
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        //设置密码
        if (StringUtils.isNotBlank(password)) {
            redisStandaloneConfiguration.setPassword(password);
        }
        redisStandaloneConfiguration.setDatabase(0);
        return redisStandaloneConfiguration;
    }

    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        redisSentinelConfiguration.setMaster("Master");
        redisSentinelConfiguration.addSentinel(new RedisNode(host,18380));
        if (StringUtils.isNotBlank(password)) {
            redisSentinelConfiguration.setPassword(password);
        }
        redisSentinelConfiguration.setDatabase(0);
        return redisSentinelConfiguration;
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.addClusterNode(new RedisNode(host,11001));
        redisClusterConfiguration.addClusterNode(new RedisNode(host,11002));
        redisClusterConfiguration.addClusterNode(new RedisNode(host,11003));
        redisClusterConfiguration.addClusterNode(new RedisNode(host,11004));
        redisClusterConfiguration.addClusterNode(new RedisNode(host,11005));
        redisClusterConfiguration.addClusterNode(new RedisNode(host,11006));
        redisClusterConfiguration.setMaxRedirects(3);
        //设置密码
        if (StringUtils.isNotBlank(password)) {
            redisClusterConfiguration.setPassword(password);
        }
        return redisClusterConfiguration;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
        return jedisPoolConfig;
    }
}
