package com.pj.system.controller;

import com.pj.system.esmapper.UserEasyEs;
import com.pj.system.esmapper.UserEsMapper;
import lombok.AllArgsConstructor;
import org.dromara.easyes.core.core.EsWrappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("es")
@AllArgsConstructor
public class UserEsController {

    private final UserEsMapper userEsMapper;

    /**
     * 创建索引
     * @return
     */
    @GetMapping("create")
    public Boolean createIndex(){
        return userEsMapper.createIndex();
    }

    @GetMapping("save")
    public Integer save(Long id){
        UserEasyEs user = new UserEasyEs();
        user.setId(id);
        user.setName("用户"+id);
        user.setAddress("江苏省无锡市滨湖区");
        user.setAge(30);
        user.setSex(1);
        user.setCreateUser("admin");
        user.setCreateTime(new Date());
        Long count = userEsMapper.selectCount(EsWrappers.lambdaQuery(UserEasyEs.class).eq(UserEasyEs::getId, id));
        if(count > 0){
            return userEsMapper.updateById(user);
        }else{
            return userEsMapper.insert(user);
        }
    }

    @GetMapping("search")
    public List<UserEasyEs> search(String name, String address){
        List<UserEasyEs> userEasyEs = userEsMapper.selectList(
                EsWrappers.lambdaQuery(UserEasyEs.class)
                        .eq(UserEasyEs::getName, name)
                        .match(UserEasyEs::getAddress, address)
        );
        return userEasyEs;
    }

}
