package com.it.wx;

import com.it.utils.DownImgUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderUtils {
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

            List<String> descImgsUrl = getDescImgsURL(curDoc);
            DownImgUtils.saveTmallDescImgList(descImgsUrl, path + itemName, "desc");
        } catch (Exception e) {
            System.out.println("saveDetailItems错误:" + e.getMessage());
        }
    }

    public static String getItemName(Document curDoc) {
        String itemName = curDoc.select("#activity-name").text();
        return itemName;
    }


    public static List<String> getDescImgsURL(Document curDoc) {
        List<String> descImgsUrlList = new ArrayList<>();
        try {
            //过滤处jpg大图片
            Pattern pImg = Pattern.compile("data-src=\"(.*?)\"");
            Matcher mImg = pImg.matcher(curDoc.outerHtml());
            int imgIndex = 0;
            while (mImg.find()) {
                imgIndex += 1;
                descImgsUrlList.add(mImg.group(1));
            }
        } catch (Exception e) {
            System.out.println("getDescImgsURL错误:" + e.getMessage());
        }
        return descImgsUrlList;
    }
}
