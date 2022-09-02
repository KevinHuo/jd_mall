package com.aries.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.qiniu.cdnr.Client;
import com.qiniu.cdnr.Config;
import com.qiniu.cdnr.File;
import com.qiniu.cdnr.Task;

public class ImageUtils {

    private static Client sClient;

    public static void init(Context context) {
        sClient = Client.create(context, "", "", new Config());
    }

    public static void load(String url, ImageView imageView) {
        new Thread(() -> {
            Task task = sClient.createTask(url, 1000);
            File file = task.start();
            String filePath = file.waitDone();
            if (imageView != null) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                    imageView.setImageBitmap(bitmap);
                });
            }
        }).start();
    }
}
