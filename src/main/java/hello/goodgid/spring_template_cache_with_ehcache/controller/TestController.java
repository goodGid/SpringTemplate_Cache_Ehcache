package hello.goodgid.spring_template_cache_with_ehcache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hello.goodgid.spring_template_cache_with_ehcache.service.TestService;

@RestController("temp")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/cache/{name}")
    public String checkEhcache(@PathVariable String name) {
        return testService.ehcache(name);
    }


}
