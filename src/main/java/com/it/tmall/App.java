package com.it.tmall;

public class App {
    public static void main(String[] args) {
        //String url = "https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.1.686783dd3ogj1K&id=520034057009&skuId=3168677931941&areaId=420100&user_id=2414942299&cat_id=2&is_b=1&rn=5badaa6dfe6ffa774c6bfdc6a4a014e1";
        //String path = "/home/wangjing/Downloads/秋名山/音响导航/e车e拍/";

//        String url = "https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.1.3ded9593wRq3V8&id=41854601980&skuId=3516251817491&user_id=684825750&cat_id=56838011&is_b=1&rn=ff6a4b397e9d0b149a8f899b673c0427";
//        String path = "/home/wangjing/Downloads/秋名山/汽车坐垫/五福金牛/";
//        SpiderUtils.getPageDetail(url, path);


        String url = "https://wfjn.tmall.com";
        String path = "/home/wangjing/Downloads/秋名山/汽车坐垫/五福金牛/";
        SpiderUtils.getAllPages(url, path,2);
    }
}
