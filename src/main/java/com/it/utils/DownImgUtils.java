package com.it.utils;


import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownImgUtils {
    /**
     * 最大图片大小40M
     */
    private static final long MAX_DOWNLOAD_SIZE = 40L * 1024 * 1024;

    /**
     * UA
     */
    static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";

    public static void byte2File(byte[] buf, String path, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        File pathF = new File(path);
        if (!pathF.exists()) {
            pathF.mkdirs();
        }
        try {
            file = new File(pathF, fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 下载指定文件到内存
     *
     * @param webUrl
     * @return byte
     */
    public static byte[] download(String webUrl, int timeOut) {
        HttpURLConnection connection = null;
        long start = System.currentTimeMillis();
        try {
            URL url = new URL(webUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(timeOut);
            connection.setReadTimeout(timeOut);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            int len = connection.getContentLength();
            if (len >= MAX_DOWNLOAD_SIZE) {
                return null;
            }
            if (len == -1) {
                try (InputStream in = connection.getInputStream()) {
                    return IOUtils.toByteArray(connection.getInputStream());
                }
            } else {
                byte[] data = new byte[len];
                byte[] buffer = new byte[4096 * 2];
                int count = 0, sum = 0;
                try (InputStream in = connection.getInputStream()) {
                    while ((count = in.read(buffer)) > 0) {
                        long elapse = System.currentTimeMillis() - start;
                        if (elapse >= timeOut) {
                            data = null;
                            break;
                        }
                        System.arraycopy(buffer, 0, data, sum, count);
                        sum += count;
                    }
                }
                return data;
            }
        } catch (Exception e) {
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static void saveImg(String imgUrl, String path, String fileName) {
        try{
            byte[] imgBytes = download(imgUrl, 100_000);
            byte2File(imgBytes, path, fileName);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveImgList(List<String> imgsUrl, String path, String fileName) {
        int index=0;
        for (String imgUrl:imgsUrl) {
            index+=1;
            saveImg(imgUrl,path,fileName+"_"+index+".jpg");
            System.out.println(imgUrl);
        }
    }
}
