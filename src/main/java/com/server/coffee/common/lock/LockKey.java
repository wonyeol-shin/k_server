package com.server.coffee.common.lock;

public final class LockKey {

    private LockKey(){}

    public static String pointLock(String nickname) {return "lock:point:" + nickname;}
}