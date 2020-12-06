package com.miaofen.xiaoying.utils;

import android.app.Activity;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 项目名称：com.miaofen.xiaoying.utils
 * 类描述：
 * 创建人：duzepeng
 * 创建时间：2020/12/1
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class BufferUtil {
    /**
     * 保存json到本地
     *
     * @param mActivity
     * @param filename
     * @param content
     */
    public static File dir = new File(Environment.getExternalStorageDirectory() + "/.Imageloader/json/");

    public static void saveToSDCard(Activity mActivity, String filename, String content) {
        String en = Environment.getExternalStorageState();
        //获取SDCard状态,如果SDCard插入了手机且为非写保护状态
        if (en.equals(Environment.MEDIA_MOUNTED)) {
            try {
                dir.mkdirs(); //create folders where write files
                File file = new File(dir, filename);

                OutputStream out = new FileOutputStream(file);
                out.write(content.getBytes());
                out.close();
                ToastUtils.showToast("保存成功");
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast("保存失败");
            }
        } else {
            //提示用户SDCard不存在或者为写保护状态
            ToastUtils.showToast("SDCard不存在或者为写保护状态");
        }
    }

    /**
     * 从本地读取json
     */
    public static String readTextFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(dir + "/" + filePath);
            InputStream in = null;
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                sb.append((char) tempbyte);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
