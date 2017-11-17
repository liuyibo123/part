package com.liuyibo.part.utils;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

/**
 * Created by Liuyibo on 2017/5/31.
 */
public class JPushUtil {
    static JPushClient jpushClient;
    static final  String masterSecret = "009b6ebe5113f2f1b7f4b5d8";
    static final  String appKey = "38acb271b7f4825b1d5e7e73";
    public static PushResult sendPush(String content){
        jpushClient = new JPushClient(masterSecret,appKey);
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.alert(content))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(pushPayload);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        }
        return  result;
    }
    public static PushResult sendMessage(String content){
        jpushClient = new JPushClient(masterSecret,appKey);
        PushPayload pushPayload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setMessage(Message.content(content))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(pushPayload);
        } catch (APIConnectionException e) {
            // Connection error, should retry later
        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        }
        return  result;
    }
}
