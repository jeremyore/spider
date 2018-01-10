package com.it.taobao;


import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        save_1_Item();

    }

    public static void save_1_Item() {
        String path = "/home/wangjing/Downloads/商品数据/taobao/";

        List<String> urlList = new ArrayList<>();
//        urlList.add("https://item.taobao.com/item.htm?ft=t&spm=a21m2.8958473.0.0.a053ed6CBC8T3&id=543678690807");

//        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.1.19b789b7MjjTcm&id=563025456534&ns=1&abbucket=1#detail");

  /*      urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.16.2f26df2aycLNX2&id=563073757143&ns=1&abbucket=1#detail");

        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.27.4209df5e7dhhN&id=563762934872&ns=1&abbucket=1#detail");

        urlList.add("https://item.taobao.com/item.htm?spm=a1z10.3-c-s.w4002-17762867540.40.50b1ddbaT6ksDu&id=563923686426");

        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.34.26236f17PFeBuK&id=546417302908&ns=1&abbucket=1#detail");*/

        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.17.5c5e552cvN78OH&id=553714370284&ns=1&abbucket=1#detail");
        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.6.5c5e552cvN78OH&id=538671443850&ns=1&abbucket=1#detail");

        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.28.7522a78cQAwVaG&id=545703636010&ns=1&abbucket=1#detail");
        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.11.7522a78cQAwVaG&id=538236401205&ns=1&abbucket=1#detail");

        urlList.add("https://item.taobao.com/item.htm?spm=a230r.1.14.11.12ca4a58OvxeS2&id=560845024863&ns=1&abbucket=1#detail");

        for (String url : urlList) {
            SpiderUtils.saveDetailItems(url, path);
        }
    }
}
