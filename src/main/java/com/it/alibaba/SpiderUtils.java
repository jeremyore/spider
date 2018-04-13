package com.it.alibaba;

import com.it.utils.DownImgUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
                    .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36")
                    .header("cookie", "cna=aSLOEpMnFnwCAatRTwa37Zev; _m_h5_tk=8fa5c775ac5dc879c399df36902ef7ce_1514618129404; _m_h5_tk_enc=395aa496fffbfeea113cdb7006ec738e; _uab_collina=151461615393926348635204; hng=CN%7Czh-CN%7CCNY%7C156; uc1=cookie14=UoTdf1eg5d%2FbFA%3D%3D&lng=zh_CN&cookie16=UIHiLt3xCS3yM2h4eKHS9lpEOw%3D%3D&existShop=true&cookie21=UtASsssmfaCONGki4KTH3w%3D%3D&tag=8&cookie15=UIHiLt3xD8xYTw%3D%3D&pas=0; uc3=sg2=BdKPbwp%2BZ2Bu77djrIdNx9G89sCsABJFeIu77idJ5ME%3D&nk2=s3HdEsfI&id2=UNJXw5DtIMP%2F&vt3=F8dBzLeP1WfS2QCMCsM%3D&lg2=W5iHLLyFOGW7aA%3D%3D; tracknick=%5Cu8F69%5Cu96E8%5Cu5915; _l_g_=Ug%3D%3D; unb=326520558; lgc=%5Cu8F69%5Cu96E8%5Cu5915; cookie1=VqpePiHblzYCDdQVKH4rzY5102lKcahg47Kp9e6BVHQ%3D; login=true; cookie17=UNJXw5DtIMP%2F; cookie2=2a37ac9ed2843eb2b0484b864e55bb53; _nk_=%5Cu8F69%5Cu96E8%5Cu5915; uss=URsUssD1NJgyzNM0iZJrzPQj4mV7D%2BWplOfutYcPphG1zzQYByof2Xj3; sg=%E5%A4%958c; t=1a6dda88319afc0d9eac6e843b46e2ac; _tb_token_=e6e3156ae5350; x=__ll%3D-1%26_ato%3D0; cq=ccp%3D0; swfstore=313512; _umdata=486B7B12C6AA95F247DC96710A6775A5E63A6D169A60D233D7A7432A45D083B2D272A7D0677A412ECD43AD3E795C914C39BC92ED903147A865F887EFD57281B3; pnm_cku822=; otherx=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; whl=-1%260%260%260; isg=AsnJJAN39z_JR4tL74KQ9rM82PXjvrw9n2pHXGs-cLD3sunEs2bNGLfggCH-")
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

            List<String> titleImgsUrl = getTitleImgsURL(curDoc);
            DownImgUtils.saveAlibabaTitleImgList(titleImgsUrl, path + itemName, "title");

            List<String> descImgsUrl = getDescImgsURL(curDoc);
            DownImgUtils.saveAlibabaDescImgList(descImgsUrl, path + itemName, "desc");
        } catch (Exception e) {
            System.out.println("saveDetailItems错误:" + e.getMessage());
        }
    }

    public static String getItemName(Document curDoc) {
        String itemName = curDoc.select("#mod-detail-title > h1").text();
        return itemName;
    }

    public static List<String> getColors(Document curDoc, String path) {
        List<String> colorList = new ArrayList<>();
        colorList.add("颜色种类:");
        Elements colorEles = curDoc.select("#mod-detail-bd > div.region-custom.region-detail-property.region-takla.ui-sortable.region-vertical > div.widget-custom.offerdetail_ditto_purchasing > div > div > div > div.obj-sku > div.obj-content > table > tbody > tr > td.name > span > div > div > img");
        for (Element element : colorEles) {
            String colorName = element.attr("alt");
            colorList.add(colorName);

            String colorImgURL = element.attr("data-lazy-src").replace(".32x32", "");
            DownImgUtils.saveImg(colorImgURL, path, colorName + ".jpg");
        }
        colorList.add("======================================");
        return colorList;
    }

    public static List<String> getTitleImgsURL(Document curDoc) {
        List<String> imgsURL = new ArrayList<>();
        Elements titleImgEles = curDoc.select("#dt-tab > div > ul > li > div > a > img");
        for (Element element : titleImgEles) {
            //替换成大图
            String imgUrl = element.attr("src").replace(".60x60", "");
            if (element.attr("src").endsWith("png")) {
                imgUrl = element.attr("data-lazy-src").replace(".60x60", "");
            }
            imgsURL.add(imgUrl);
        }
        return imgsURL;
    }

    public static List<String> getDescImgsURL(Document curDoc) {
        List<String> descImgsUrlList = new ArrayList<>();
        try {

            String descImgURL = curDoc.select("#desc-lazyload-container").attr("data-tfs-url");

            Document descImgDoc = Jsoup.connect(descImgURL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0")
                    .header("cookie", "cna=aSLOEpMnFnwCAatRTwa37Zev; _m_h5_tk=8fa5c775ac5dc879c399df36902ef7ce_1514618129404; _m_h5_tk_enc=395aa496fffbfeea113cdb7006ec738e; _uab_collina=151461615393926348635204; hng=CN%7Czh-CN%7CCNY%7C156; uc1=cookie14=UoTdf1eg5d%2FbFA%3D%3D&lng=zh_CN&cookie16=UIHiLt3xCS3yM2h4eKHS9lpEOw%3D%3D&existShop=true&cookie21=UtASsssmfaCONGki4KTH3w%3D%3D&tag=8&cookie15=UIHiLt3xD8xYTw%3D%3D&pas=0; uc3=sg2=BdKPbwp%2BZ2Bu77djrIdNx9G89sCsABJFeIu77idJ5ME%3D&nk2=s3HdEsfI&id2=UNJXw5DtIMP%2F&vt3=F8dBzLeP1WfS2QCMCsM%3D&lg2=W5iHLLyFOGW7aA%3D%3D; tracknick=%5Cu8F69%5Cu96E8%5Cu5915; _l_g_=Ug%3D%3D; unb=326520558; lgc=%5Cu8F69%5Cu96E8%5Cu5915; cookie1=VqpePiHblzYCDdQVKH4rzY5102lKcahg47Kp9e6BVHQ%3D; login=true; cookie17=UNJXw5DtIMP%2F; cookie2=2a37ac9ed2843eb2b0484b864e55bb53; _nk_=%5Cu8F69%5Cu96E8%5Cu5915; uss=URsUssD1NJgyzNM0iZJrzPQj4mV7D%2BWplOfutYcPphG1zzQYByof2Xj3; sg=%E5%A4%958c; t=1a6dda88319afc0d9eac6e843b46e2ac; _tb_token_=e6e3156ae5350; x=__ll%3D-1%26_ato%3D0; cq=ccp%3D0; swfstore=313512; _umdata=486B7B12C6AA95F247DC96710A6775A5E63A6D169A60D233D7A7432A45D083B2D272A7D0677A412ECD43AD3E795C914C39BC92ED903147A865F887EFD57281B3; pnm_cku822=; otherx=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; whl=-1%260%260%260; isg=AsnJJAN39z_JR4tL74KQ9rM82PXjvrw9n2pHXGs-cLD3sunEs2bNGLfggCH-")
                    .ignoreContentType(true)
                    .timeout(3000)
                    .get();

            String descText = StringUtils.replaceChars(descImgDoc.outerHtml(), "\\", "");
            descText = StringUtils.removePattern(descText, "&quot;");

            //过滤处jpg大图片
            Pattern pImg = Pattern.compile("src=\"(.*?)\"");
            Matcher mImg = pImg.matcher(descText);
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
        return descImgsUrlList;
    }
}
