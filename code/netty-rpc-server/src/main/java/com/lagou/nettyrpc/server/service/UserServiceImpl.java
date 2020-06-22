package com.lagou.nettyrpc.server.service;

import org.springframework.stereotype.Service;

/**
 * \* User: ZhuFangTao
 * \* Date: 2020/6/17 3:00 下午
 * \
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Override
    public String sayHello(String msg) {
        return "invoke success!" + msg;
    }
}