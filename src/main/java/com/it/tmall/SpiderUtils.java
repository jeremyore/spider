package com.it.tmall;

import com.it.utils.DownImgUtils;
import com.it.utils.ProxyUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderUtils {


    public static void getPageDetailByProxy(String url, String path) {
        try {
            Document curDoc = Jsoup.connect(url).header("Proxy-Authorization", ProxyUtils.getAuthHeader())
                    .proxy(ProxyUtils.proxyIP, ProxyUtils.proxyPort)
                    .followRedirects(false).validateTLSCertificates(false).timeout(10000).get();

        } catch (Exception e) {
            System.out.println("getPageDetail错误:" + e.getMessage());
        }
    }

    //=============================================================
    //爬取详情页
    public static void getPageDetail(String url, String path) {
        try {

            Document curDoc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                    .timeout(3000)
                    .get();

            String itemName = getItemName(curDoc);
            String filePath = path + itemName + "/参数.txt";

            List<String> itemNameList = new ArrayList<>();
            itemNameList.add("商品名称");
            itemNameList.add(itemName);
            itemNameList.add("======================================");
            FileUtils.writeLines(new File(filePath), "UTF-8", itemNameList, true);
            System.out.println(itemNameList);

            List<String> colorList = getColors(curDoc);
            FileUtils.writeLines(new File(filePath), "UTF-8", colorList, true);
            System.out.println(colorList);

            List<String> paraList = getProductPara(curDoc);
            FileUtils.writeLines(new File(filePath), "UTF-8", paraList, true);
            System.out.println(paraList);

            List<String> titleImgsUrl = getTitleImgsURL(curDoc);
            DownImgUtils.saveImgList(titleImgsUrl, path + itemName, "title");

            List<String> descImgsUrl = getDescImgsURL(curDoc);
            DownImgUtils.saveImgList(descImgsUrl, path + itemName, "desc");
        } catch (Exception e) {
            System.out.println("getPageDetail错误:" + e.getMessage());
        }
    }

    public static String getItemName(Document curDoc) {
        String itemName = curDoc.select("#J_DetailMeta > div.tm-clear > div.tb-property > div > div.tb-detail-hd > h1").text();
        return itemName;
    }

    public static List<String> getColors(Document curDoc) {
        List<String> colorList = new ArrayList<>();
        colorList.add("颜色种类");
        Elements colorEles = curDoc.select("#J_DetailMeta > div.tm-clear > div.tb-property > div > div.tb-key > div > div > dl.tb-prop.tm-sale-prop.tm-clear.tm-img-prop > dd > ul > li");
        for (Element element : colorEles) {
            colorList.add(element.attr("title"));
        }
        colorList.add("======================================");
        return colorList;
    }

    public static List<String> getTitleImgsURL(Document curDoc) {
        List<String> imgsURL = new ArrayList<>();
        Elements titleImgEles = curDoc.select("#J_UlThumb > li> a > img");
        for (Element element : titleImgEles) {
            //替换成大图
            String imgUrl = "https:" + element.attr("src").replace("60x60", "430x430");
            imgsURL.add(imgUrl);
        }
        return imgsURL;
    }

    public static List<String> getProductPara(Document curDoc) {
        List<String> productParaList = new ArrayList<>();
        productParaList.add("详细参数");
        Elements productParaEles = curDoc.select("#J_AttrUL > li");
        for (Element element : productParaEles) {
            productParaList.add(element.text());
        }
        productParaList.add("======================================");
        return productParaList;
    }

    public static List<String> getDescImgsURL(Document curDoc) {
        List<String> descImgsUrlList = new ArrayList<>();
        Pattern pDesc = Pattern.compile("\"httpsDescUrl\":\"(.*?)\"");
        Matcher mDesc = pDesc.matcher(curDoc.outerHtml());
        if (mDesc.find()) {
            String descImgURL = "https:" + mDesc.group(1);
            try {
                Document descImgDoc = Jsoup.connect(descImgURL)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                        .timeout(3000)
                        .get();


                //过滤处jpg大图片
                Pattern pImg = Pattern.compile("src=\"(.*?)\"");
                Matcher mImg = pImg.matcher(descImgDoc.outerHtml());
                int imgIndex = 0;
                while (mImg.find()) {
                    if (mImg.group(1).endsWith("jpg")) {
                        imgIndex += 1;
                        descImgsUrlList.add(mImg.group(1));
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return descImgsUrlList;
    }


    //==============================================================
    //分页爬取全店
    public static void getAllPages(String url, String path,int pagesNum) {
        try {
/*
            Document curDoc = Jsoup.connect(url+"/category.htm")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                    .timeout(3000)
                    .get();

            String descUrl=url+curDoc.select("#J_ShopAsynSearchURL").attr("value");

            for (int i = 1; i <=pagesNum ; i++) {
                String fullDescUrl=descUrl+"&pageNo="+i;
                Document descDoc = Jsoup.connect("https://wfjn.tmall.com/i/asynSearch.htm?mid=w-14902631627-0&wid=14902631627&path=/category.htm&pageNo=1")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                        .timeout(3000)
                        .get();


                Elements itemEles=descDoc.select("body > div > div.\\5c \\22 J_TItems\\5c \\22 > div");

                for (Element element:itemEles) {
                    System.out.println(element.text());
                }


            }*/

            Document descDoc = Jsoup.connect("https://wfjn.tmall.com/i/asynSearch.htm?mid=w-14902631627-0&wid=14902631627&path=/category.htm")
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                    .referrer("https://wfjn.tmall.com/category.htm")
                    .timeout(3000)
                    .get();

            System.out.println(descDoc.outerHtml());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
