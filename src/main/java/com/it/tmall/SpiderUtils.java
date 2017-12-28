package com.it.tmall;

import com.it.utils.DownImgUtils;
import com.it.utils.ProxyUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
            System.out.println("saveDetailItems错误:" + e.getMessage());
        }
    }

    public static String getItemName(Document curDoc) {
        String itemName = curDoc.select("#J_DetailMeta > div.tm-clear > div.tb-property > div > div.tb-detail-hd > h1").text();
        return itemName;
    }

    public static List<String> getColors(Document curDoc) {
        List<String> colorList = new ArrayList<>();
        colorList.add("颜色种类:");
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
        productParaList.add("详细参数:");
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
                System.out.println("getDescImgsURL错误:" + e.getMessage());
            }
        }
        return descImgsUrlList;
    }


    //==============================================================
    //分页爬取全店
    //获取全店所有商品
    public static Set<String> getAllPages(String url, int pagesNum,String catUrl) {
        Set<String> itemUrlSet = new HashSet<>();
        try {
            Document curDoc = Jsoup.connect(url + catUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                    .timeout(3000)
                    .get();
            String descUrl = url + curDoc.select("#J_ShopAsynSearchURL").attr("value");
            for (int i = 1; i <= pagesNum; i++) {
                String fullDescUrl = descUrl + "&pageNo=" + i;
                Document descDoc = Jsoup.connect(fullDescUrl)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                        .referrer("https://wfjn.tmall.com")
                        .header("cookie", "t=81ed193a07d8f2690024bc4e7e54f5c8; _tb_token_=377353337be58; cookie2=1235cb19733c4d9e22d7237215919b68; cna=bXXKEpgDkE0CAXWYkgXNHo0j; isg=Au7uNQjOOJpzVkz-zvaLqpfBP0RwR7OMfNPATRi3WvGs-45VgH8C-ZRzx1Dt")
                        .timeout(3000)
                        .get();

                String descText = StringUtils.replaceChars(descDoc.toString(), "\\", "");
                descText = StringUtils.removePattern(descText, "&quot;");
                descDoc = Jsoup.parse(descText);
                Elements itemEles = descDoc.select("div.J_TItems>div:lt(15) > dl > dt > a");
                for (Element element : itemEles) {
                    itemUrlSet.add("https:" + element.attr("href"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemUrlSet;
    }

    public static void saveAllPagesItems(String url, String path, int pagesNum,String catUrl) {
        Set<String> itemUrlSet = getAllPages(url, pagesNum,catUrl);
        for (String itemUrl : itemUrlSet) {
            saveDetailItems(itemUrl, path);
            System.out.println(itemUrl);
        }
    }
}
