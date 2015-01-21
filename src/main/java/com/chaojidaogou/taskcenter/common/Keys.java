package com.chaojidaogou.taskcenter.common;

/**
 * Created by Administrator on 2015/1/20.
 */
public class Keys {
    public static String uidInboxQueue(Long uid) {
        return String.format("uid:%d:inbox", uid);
    }

    public static String globalTaskIdOfUid(Long uid) {
        return String.format("uid:%d:taskId", uid);
    }
}
