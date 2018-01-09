package com.it.taobao;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        save_1_Item();

    }

    public static void save_1_Item() {
        String url = "https://detail.tmall.com/item.htm?id=15548208885&rn=59ece18a6e39b7b30b55d14697d33a99&abbucket=15&skuId=3659141406714";
        String path = "D:/商品数据/";
        SpiderUtils.saveDetailItems(url, path);
    }


}
