package com.it.tmall;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        save_1_Item();

//        saveAllPagesItems();
    }

    public static void save_1_Item() {
        String url = "https://detail.tmall.com/item.htm?spm=a1z10.5-b-s.w4011-16202868421.72.c5733e59XfhDIN&id=40712770433&rn=72c24bdec6ff613d2eca8a5aabdc5feb&abbucket=14&skuId=61115830823";
        String path = "D:\\商品数据\\";
        SpiderUtils.saveDetailItems(url, path);
    }

    public static void saveAllPagesItems() {
        List<String[]> arrayList = new ArrayList<>();
//        arrayList.add(new String[]{"https://renexingryx.tmall.com", "/home/wangjing/Downloads/商品数据/音响导航/任e行/", "1", "/category.htm"});
        // arrayList.add(new String[]{"https://eluhang.tmall.com/", "/home/wangjing/Downloads/商品数据/音响导航/e路航/", "1", "/category.htm"});
//        arrayList.add(new String[]{"https://tourmate.tmall.com", "/home/wangjing/Downloads/商品数据/音响导航/tourmate途美/", "1", "/category.htm"});

//        arrayList.add(new String[]{"https://jfhycp.tmall.com", "/home/wangjing/Downloads/商品数据/音响导航/易图/", "1", "/category-1313270140.htm"});
//        arrayList.add(new String[]{"https://3mcarcare.tmall.com", "/home/wangjing/Downloads/商品数据/3mcarcare/", "2", "/category.htm"});

//        arrayList.add(new String[]{"https://llumar.tmall.com", "D:/秋名山/太阳膜/龙膜/", "1", "/category.htm"});

//        arrayList.add(new String[]{"https://steelmate.tmall.com", "D:/秋名山/铁将军/", "1", "/category.htm"});

//        arrayList.add(new String[]{"https://meto.tmall.com", "D:/秋名山/美途/", "2", "/category.htm"});

//        arrayList.add(new String[]{"https://wfjn.tmall.com", "D:/商品数据/汽车坐垫/五福金牛/", "2","/category.htm"});
//        arrayList.add(new String[]{"https://steelmate.tmall.com", "/home/wangjing/Downloads/商品数据/铁将军/", "1", "/category.htm"});

//        arrayList.add(new String[]{"https://hulianyidong.tmall.com", "/home/wangjing/Downloads/商品数据/音响导航/互联移动/", "1", "/category.htm"});

//        arrayList.add(new String[]{"https://wfjn.tmall.com", "D:/商品数据/五福金牛/脚垫/", "1", "/category-804274908.htm"});
        arrayList.add(new String[]{"https://autodoc.tmall.com", "D:/商品数据/车博士", "1", "/search.htm"});
//        arrayList.add(new String[]{"https://wfjn.tmall.com", "D:/商品数据/五福金牛/尾箱垫/", "1", "/category-804275982.htm"});


        for (String[] strArr : arrayList) {
            SpiderUtils.saveAllPagesItems(strArr[0], strArr[1], Integer.parseInt(strArr[2]), strArr[3]);

        }
    }
}
