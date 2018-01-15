package com.it.aurora;

import com.it.utils.DownImgUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderUtils {

    //爬取详情页
    public static void saveDetailItems(String url, String path) {
        try {

            Document curDoc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                    .timeout(3000)
                    .get();

            String itemName = getItemName(curDoc);
            String filePath = path + itemName + "/参数.txt";

            List<String> itemNameList = new ArrayList<>();
            itemNameList.add("商品链接:");
            itemNameList.add(url);
            itemNameList.add("======================================");
            itemNameList.add("商品名称:");
            itemNameList.add(itemName);
            itemNameList.add("======================================");
            FileUtils.writeLines(new File(filePath), "UTF-8", itemNameList, true);
            System.out.println(itemNameList);


            List<String> paraList = getProductPara(curDoc);
            FileUtils.writeLines(new File(filePath), "UTF-8", paraList, true);
            System.out.println(paraList);

            List<String> titleImgsUrl = getTitleImgsURL(curDoc);
            DownImgUtils.saveImgList(titleImgsUrl, path + itemName, "title");

            List<String> descImgsUrl = getDescImgsURL(curDoc);
            DownImgUtils.saveAuroraImgList(descImgsUrl, path + itemName, "desc");
        } catch (Exception e) {
            System.out.println("saveDetailItems错误:" + e.getMessage());
        }
    }

    public static String getItemName(Document curDoc) {
        String itemName = curDoc.select("#products_name").text();
        return itemName;
    }


    public static List<String> getTitleImgsURL(Document curDoc) {
        List<String> imgsURL = new ArrayList<>();
        Elements titleImgEles = curDoc.select("#products_image > img");
        for (Element element : titleImgEles) {
            //替换成大图
            String imgUrl = "http://www.zg-aurora.com/" + element.attr("src");
            imgsURL.add(imgUrl);
        }
        return imgsURL;
    }

    public static List<String> getProductPara(Document curDoc) {
        List<String> productParaList = new ArrayList<>();
        productParaList.add("详细参数:");
        Elements productParaEles = curDoc.select("#products_desc > div > p");
        for (Element element : productParaEles) {
            productParaList.add(element.text());
        }
        productParaList.add("======================================");
        return productParaList;
    }

    public static List<String> getDescImgsURL(Document curDoc) {
        List<String> descImgsUrlList = new ArrayList<>();

        try {
            String descHtml = curDoc.select("#showtext").outerHtml();
            Pattern pImg = Pattern.compile("src=\"(.*?)\"");
            Matcher mImg = pImg.matcher(descHtml);
            int imgIndex = 0;
            while (mImg.find()) {
                if (mImg.group(1).endsWith("jpg")) {
                    imgIndex += 1;
                    descImgsUrlList.add("http://www.zg-aurora.com/" + mImg.group(1));
                }
            }
        } catch (Exception e) {
            System.out.println("getDescImgsURL错误:" + e.getMessage());
        }
        return descImgsUrlList;
    }


    //==============================================================
    //分页爬取全店
    //获取全店所有商品
    public static Set<String> getAllPages(String url) {
        Set<String> itemUrlSet = new HashSet<>();
        try {
            Document curDoc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                    .timeout(3000)
                    .get();

            Elements productEles = curDoc.select("#products > dl > dt > a");
            for (Element element : productEles) {
                itemUrlSet.add("http://www.zg-aurora.com/" + element.attr("href"));
            }
        } catch (Exception e) {
            System.out.println("getAllPages错误:" + e.getMessage());
        }
        return itemUrlSet;
    }

    public static void saveAllPagesItems(String url, String path) {
        Set<String> itemUrlSet = getAllPages(url);
        for (String itemUrl : itemUrlSet) {
            saveDetailItems(itemUrl, path);
            System.out.println(itemUrl);
        }
    }
}
