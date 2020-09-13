package hello.goodgid.spring_template_cache_with_ehcache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService{

    @Override
    @Cacheable(value = "tempConfig", key = "#name")
    public String ehcache(String name) {
        System.out.println("Invoke `ehcache` method");
        return name;
    }
}
