package com.it.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;

public class PicUtils {
    public static void main(String[] args) {
        try {
            Thumbnails.of("C:\\Users\\n00ne\\Desktop\\雷朋1\\letbon雷朋汽车隔热膜冰钻系列前档清晰防爆防晒玻璃贴膜高隔热\\desc_09_00.jpg")
                    .sourceRegion(0, 0,790,1400)
                    .scale(1.0)
                    .toFile("C:\\Users\\n00ne\\Desktop\\1.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
