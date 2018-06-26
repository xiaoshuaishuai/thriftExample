package com.github.face.impl;

import com.github.face.HelloService;
import org.apache.thrift.TException;

/**
 * created by ShuaishuaiXiao on 2018/6/25 16:55
 **/
public class HelloServiceImpl implements HelloService.Iface {
    public String hello(String msg) throws TException {
        System.out.println("msg = [" + msg + "]");
        return "server response info :" +msg;
    }
}
