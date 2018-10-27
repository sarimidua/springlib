package training.com.first.security;

import java.util.List;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
	
	@Bean
	JedisConnectionFactory getJdisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName("localhost");
		jedisConnectionFactory.setPort(6379);
		return jedisConnectionFactory;
	}
	
	@Bean
	RedisTemplate<String, Long> getRedisTemplate() {
		RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(getJdisConnectionFactory());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}
	
	@Bean
	RedisServerCommands getRedisCommands() {
		RedisServerCommands redisCommands = new RedisServerCommands() {
			
			@Override
			public Long time() {
				return null;
			}
			
			@Override
			public void slaveOfNoOne() {
				
			}
			
			@Override
			public void slaveOf(String arg0, int arg1) {
				
			}
			
			@Override
			public void shutdown(ShutdownOption arg0) {
				
			}
			
			@Override
			public void shutdown() {
				
			}
			
			@Override
			public void setConfig(String arg0, String arg1) {
				
			}
			
			@Override
			public void setClientName(byte[] arg0) {
				
			}
			
			@Override
			public void save() {
				
			}
			
			@Override
			public void resetConfigStats() {
				
			}
			
			@Override
			public void migrate(byte[] arg0, RedisNode arg1, int arg2, MigrateOption arg3, long arg4) {
				
			}
			
			@Override
			public void migrate(byte[] arg0, RedisNode arg1, int arg2, MigrateOption arg3) {
				
			}
			
			@Override
			public Long lastSave() {
				return null;
			}
			
			@Override
			public void killClient(String arg0, int arg1) {
				
			}
			
			@Override
			public Properties info(String arg0) {
				return null;
			}
			
			@Override
			public Properties info() {
				return null;
			}
			
			@Override
			public List<String> getConfig(String arg0) {
				return null;
			}
			
			@Override
			public String getClientName() {
				return null;
			}
			
			@Override
			public List<RedisClientInfo> getClientList() {
				return null;
			}
			
			@Override
			public void flushDb() {
				
			}
			
			@Override
			public void flushAll() {
				
			}
			
			@Override
			public Long dbSize() {
				return null;
			}
			
			@Override
			public void bgWriteAof() {
				
			}
			
			@Override
			public void bgSave() {
				
			}
			
			@Override
			public void bgReWriteAof() {
				
			}
		};
		return redisCommands;
	}
}
