package com.gkh.springboot.service;

import com.gkh.springboot.entity.User;
import com.gkh.springboot.mapper.primary.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Cacheable
 * @Cacheable 可以标记在一个方法上，也可以标记在一个类上。当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的。
 * 对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次执行该方法。
 * Spring在缓存方法的返回值时是以键值对进行缓存的，值就是方法的返回结果，至于键的话，Spring又支持两种策略，默认策略和自定义策略，这个稍后会进行说明。
 * 需要注意的是当一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的。@Cacheable可以指定三个属性，value、key和condition
 * vaule: 缓存的名称，在 spring 配置文件中定义，必须指定至少一个。 @Cacheable(value=”mycache”) @Cacheable(value={”cache1”,”cache2”}
 * key： 缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合。 @Cacheable(value=”testcache”,key=”#userName”)
 * condition： 缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存。@Cacheable(value=”testcache”,condition=”#userName.length()>2”)
 *
 * @CachePut
 * 在支持Spring Cache的环境下，对于使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
 * @CachePut 也可以声明一个方法支持缓存功能。与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
 *
 * @CacheEvict
 * @CacheEvict 是用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作。
 * @CacheEvict 可以指定的属性有value、key、condition、allEntries和beforeInvocation。其中value、key和condition的语义与@Cacheable对应的属性类似。
 * 即value表示清除操作是发生在哪些Cache上的（对应Cache的名称）；key表示需要清除的是哪个key，如未指定则会使用默认策略生成的key；condition表示清除操作发生的条件。
 *
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "user_sel", key = "#p0", unless = "#result == null")
    public User selectById(String id) { //#p0代表参数
        return userMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @Override
    @CachePut(value = "user_upd", key = "#p0.id", unless = "#result == 0")
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    @CacheEvict(value = "user_sel", key = "#id", condition = "#id != ''")
    public int deleteById(String id) {
        return userMapper.deleteByPrimaryKey(Long.valueOf(id));
    }
}
