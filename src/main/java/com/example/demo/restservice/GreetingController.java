package com.example.demo.restservice;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.demo.dao.bdp.UserFuncDao;
import com.example.demo.dao.index.IndexDao;
import com.example.demo.model.Greeting;
import com.example.demo.model.ResponseModel;
import com.example.demo.model.User;
import com.example.demo.model.UserFunc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private UserFuncDao userFuncDao;

    @Autowired
    private IndexDao indexDao;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Object getUsers(@PathVariable("id") Integer id) {
        User user = new User();
        user.setUserId(id);
        user.setUserName("ZS");
        user.setUserPass("123");
        log.info(user.toString());
        return new ResponseModel("success", 0, user);
    }

    @RequestMapping(value = "/userFunc/{id}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Object getUserFunc(@PathVariable("id") Integer id) {
        UserFunc userFunc = userFuncDao.findById(id);
        return new ResponseModel("success", 0, userFunc);
    }

    @RequestMapping(value = "/index/{id}", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Object getIndex(@PathVariable("id") Integer id) {
        int c = indexDao.findById(id);
        return new ResponseModel("success", 0, c);
    }

    @RequestMapping(value = "/index/list", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Object getIndexConfig() {
        List<UserFunc> data = userFuncDao.getUserFuncs();
        return new ResponseModel("success", 0, data);
    }
}