package com.it.taobao;

import com.it.utils.DownImgUtils;
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

            List<String> colorList = getColors(curDoc, path + itemName);
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
            System.out.println("saveDetailItems错误:" + e.getMessage());
        }
    }

    public static String getItemName(Document curDoc) {
        String itemName = curDoc.select("#J_Title > h3").text();
        return itemName;
    }

    public static List<String> getColors(Document curDoc, String path) {
        List<String> colorList = new ArrayList<>();
        colorList.add("颜色种类:");
        Elements colorEles = curDoc.select("#J_isku > div > dl.J_Prop.tb-prop.tb-clear.J_Prop_Color > dd > ul > li");
        for (Element element : colorEles) {
            //String colorName = element.attr("title");
            String colorName = element.child(0).child(0).text();
            colorList.add(colorName);
            //颜色对应图片下载
            String backgroundUrl = element.child(0).attr("style");
            Pattern p = Pattern.compile("(//.*.jpg)");
            Matcher m = p.matcher(backgroundUrl);
            if (m.find()) {
                String colorImgURL = "https:" + m.group(1);
                colorImgURL = colorImgURL.replace("30x30", "400x400");
                DownImgUtils.saveImg(colorImgURL, path, colorName + ".jpg");
            }
        }
        colorList.add("======================================");
        return colorList;
    }

    public static List<String> getTitleImgsURL(Document curDoc) {
        List<String> imgsURL = new ArrayList<>();
        Elements titleImgEles = curDoc.select("#J_UlThumb > li > div > a > img");
        for (Element element : titleImgEles) {
            //替换成大图
            String imgUrl = "https:" + element.attr("data-src").replace("_50x50.jpg", "");
            imgsURL.add(imgUrl);
        }
        return imgsURL;
    }

    public static List<String> getProductPara(Document curDoc) {
        List<String> productParaList = new ArrayList<>();
        productParaList.add("详细参数:");
        Elements productParaEles = curDoc.select("#attributes > ul > li");
        for (Element element : productParaEles) {
            productParaList.add(element.text());
        }
        productParaList.add("======================================");
        return productParaList;
    }

    public static List<String> getDescImgsURL(Document curDoc) {
        List<String> descImgsUrlList = new ArrayList<>();
        Pattern pDesc = Pattern.compile("'(//desc.alicdn.com.*?)'");
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
                System.out.println("getDescImgsURL错误:" + e.getMessage());
            }
        }
        return descImgsUrlList;
    }
}
