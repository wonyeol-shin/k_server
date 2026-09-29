package com.server.coffee.common.lock;

public class LockKey {

    public static String point(String nickname) {return "lock:point:" + nickname;}
}
