package com.it.aurora;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //save_1_Item();

        saveAllPagesItems();
    }

    public static void save_1_Item() {
        String path = "/home/wangjing/Downloads/商品数据/欧若拉/";

        List<String> urlList = new ArrayList<>();
        urlList.add("http://www.zg-aurora.com/products_detail.php?id=8&cid=1&search_key=&page=1");

        for (String url : urlList) {
            SpiderUtils.saveDetailItems(url, path);
        }
    }

    public static void saveAllPagesItems() {
        String path = "/home/wangjing/Downloads/欧若拉/";

        List<String> urlList = new ArrayList<>();
        urlList.add("http://www.zg-aurora.com/products.php?cid=1");
        urlList.add("http://www.zg-aurora.com/products.php?cid=2");

        for (String url : urlList) {
            SpiderUtils.saveAllPagesItems(url, path);
        }
    }
}
