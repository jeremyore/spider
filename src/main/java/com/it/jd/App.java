package com.it.jd;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        save_1_Item();
        //saveAllPagesItems();
    }

    public static void save_1_Item() {
        String path = "/home/wangjing/Downloads/商品列表/易图/";

        List<String> urlList = new ArrayList<>();

//        urlList.add("https://item.jd.com/18848075603.html");
//        urlList.add("https://item.jd.com/18847961807.html");
//        urlList.add("https://item.jd.com/18815253605.html");
//
//        urlList.add("https://item.jd.com/19489037465.html");
//        urlList.add("https://item.jd.com/19489037466.html");
//        urlList.add("https://item.jd.com/19489037467.html");
//        urlList.add("https://item.jd.com/19489037468.html");
//        urlList.add("https://item.jd.com/19489037469.html");
//        urlList.add("https://item.jd.com/19489037470.html");
//        urlList.add("https://item.jd.com/19489037471.html");
//        urlList.add("https://item.jd.com/19489037472.html");

        urlList.add("https://item.jd.com/26805699918.html");

/*        urlList.add("https://item.jd.com/18814027441.html");
        urlList.add("https://item.jd.com/18913180555.html");
        urlList.add("https://item.jd.com/18913947332.html");
        urlList.add("https://item.jd.com/18897887801.html");
        urlList.add("https://item.jd.com/18912228390.html");
        urlList.add("https://item.jd.com/18914363353.html");
        urlList.add("https://item.jd.com/18845073203.html");
        urlList.add("https://item.jd.com/24151153343.html");*/

        for (String url : urlList) {
            SpiderUtils.saveDetailItems(url, path);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

/*    public static void saveAllPagesItems() {
        List<String[]> arrayList = new ArrayList<>();
        arrayList.add(new String[]{"https://mall.jd.com/view_search-813393-0-99-1-24-", "/home/wangjing/Downloads/商品数据/歌浪香品/", "1", ".html"});


        for (String[] strArr : arrayList) {
            SpiderUtils.saveAllPagesItems(strArr[0], strArr[1], Integer.parseInt(strArr[2]), strArr[3]);

        }
    }*/
}
