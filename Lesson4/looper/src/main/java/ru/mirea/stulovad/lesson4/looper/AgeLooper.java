package ru.mirea.stulovad.lesson4.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class AgeLooper extends Thread {
    public Handler mHandler;
    private Handler mainHandler;

    public AgeLooper(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }

    public void run() {
        Log.d("AgeLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String data = msg.getData().getString("KEY");
                String ageStr = msg.getData().getString("AGE");
                Log.d("AgeLooper", "Processing message: " + data + " with delay: " + ageStr + "s");

                new Thread(new Runnable() {
                    public void run() {
                        long endTime = System.currentTimeMillis() + Integer.parseInt(ageStr) * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    Message message = new Message();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("result---", String.format("---The profession is %s and age is %s ---", data, ageStr));
                                    message.setData(bundle);
                                    mainHandler.sendMessage(message);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }).start();

            }
        };
        Looper.loop();
    }
}
