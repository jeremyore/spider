package com.it.alibaba;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        save_1_Item();
    }

    public static void save_1_Item() {
        String path = "/home/wangjing/Downloads/商品列表/车载/";

        List<String> urlList = new ArrayList<>();


        urlList.add("https://detail.1688.com/offer/551872834458.html");

        for (String url : urlList) {
            SpiderUtils.saveDetailItems(url, path);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
