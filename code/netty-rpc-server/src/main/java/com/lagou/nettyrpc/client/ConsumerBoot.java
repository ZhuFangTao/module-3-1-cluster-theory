package com.lagou.nettyrpc.client;


import com.lagou.nettyrpc.server.service.IUserService;


public class ConsumerBoot {


    public static void main(String[] args) throws InterruptedException {
        //1.创建代理对象
        IUserService service = (IUserService) RPCConsumer.createProxy(IUserService.class);

        //2.循环给服务器写数据
        while (true) {
            Object result = service.sayHello("Eric");
            System.out.println(result);
            Thread.sleep(2000);
        }

    }
}
