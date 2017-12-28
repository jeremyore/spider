package com.it.tmall;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        save_1_Item();

        saveAllPagesItems();
    }

    public static void save_1_Item() {
        String url = "https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.1.686783dd3ogj1K&id=520034057009&skuId=3168677931941&areaId=420100&user_id=2414942299&cat_id=2&is_b=1&rn=5badaa6dfe6ffa774c6bfdc6a4a014e1";
        String path = "/home/wangjing/Downloads/秋名山/音响导航/e车e拍/";
        SpiderUtils.saveDetailItems(url, path);
    }

    public static void saveAllPagesItems() {
        List<String[]> arrayList = new ArrayList<>();
//        arrayList.add(new String[]{"https://renexingryx.tmall.com", "/home/wangjing/Downloads/秋名山/音响导航/任e行/", "1","/category.htm"});
//        arrayList.add(new String[]{"https://eluhang.tmall.com/", "/home/wangjing/Downloads/秋名山/音响导航/e路航/", "1","/category.htm"});
//        arrayList.add(new String[]{"https://tourmate.tmall.com", "/home/wangjing/Downloads/秋名山/音响导航/tourmate途美/", "1","/category.htm"});

//        arrayList.add(new String[]{"https://jfhycp.tmall.com", "/home/wangjing/Downloads/秋名山/音响导航/易图/", "1", "/category-1313270140.htm"});
        arrayList.add(new String[]{"https://3mcarcare.tmall.com", "/home/wangjing/Downloads/秋名山/3mcarcare/", "2", "/category.htm"});


        for (String[] strArr : arrayList) {
            SpiderUtils.saveAllPagesItems(strArr[0], strArr[1], Integer.parseInt(strArr[2]), strArr[3]);

        }
    }
}
