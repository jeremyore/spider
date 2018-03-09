package com.it.wx;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        save_1_Item();

        saveAllPagesItems();
    }

    public static void save_1_Item() {
        String url = "https://mp.weixin.qq.com/s/THT5tH6o6-vqat7LPevyRg";
        String path = "D:/商品数据//明宇/";
        SpiderUtils.saveDetailItems(url, path);
    }

    public static void saveAllPagesItems() {
        List<String> urlList = new ArrayList<>();
        String path = "D:/商品数据//明宇/";

        urlList.add("http://mp.weixin.qq.com/s/qXnTkaoq8i9J4zybDjhgNA  ");
        urlList.add("http://mp.weixin.qq.com/s/CSxwfvWew9Uuhs5c7bv3pA  ");
        urlList.add("http://mp.weixin.qq.com/s/VKrRlpu_vgd2j9-cmy_9tg  ");
        urlList.add("http://mp.weixin.qq.com/s/O0EngIA4H1JjgoKo19UTGQ  ");
        urlList.add("http://mp.weixin.qq.com/s/HLw-3vG3ivQEOX_oIXVQGA  ");
        urlList.add("http://mp.weixin.qq.com/s/baoERRjZHYDV6Fb_KrGJqg  ");
        urlList.add("http://mp.weixin.qq.com/s/WVlEKdx3wCahCZHLIPCj0w  ");
        urlList.add("https://mp.weixin.qq.com/s/V3GegaBQNdYQNzdbj3df5Q ");
        urlList.add("http://mp.weixin.qq.com/s/292YAwpyaY4PMuzZJKjXOg  ");
        urlList.add("http://mp.weixin.qq.com/s/YRDN_ssaVh6UTGCyli08AA  ");
        urlList.add("http://mp.weixin.qq.com/s/rdzl5yp4mOQBJr-X2RS_Mg  ");
        urlList.add("http://mp.weixin.qq.com/s/HZYqsnaHEaDrCZu7v-T_yQ  ");
        urlList.add("http://mp.weixin.qq.com/s/HFSOwSS6xaT6HEi-63AGAA  ");
        urlList.add("http://mp.weixin.qq.com/s/KzrfIDGlIov3BopxXYH_-w  ");
        urlList.add("http://mp.weixin.qq.com/s/VtXp_IpSQhsW-y6pByLxlw  ");
        urlList.add("http://mp.weixin.qq.com/s/nVBk-IkMGtuJYgU8kKH01Q  ");
        urlList.add("https://mp.weixin.qq.com/s/YOySS2XY2CQVifK0Mz-P5g ");
        urlList.add("http://mp.weixin.qq.com/s/Cia3He3laZtex358sFTlFA  ");
        urlList.add("http://mp.weixin.qq.com/s/2SdeSEncpqPVT-8Iaj52ig  ");
        urlList.add("http://mp.weixin.qq.com/s/ihqfDW4IvcjcnpTi0PapUg  ");
        urlList.add("http://mp.weixin.qq.com/s/H8YKU65bAjYlz-9UGbgeIg  ");
        urlList.add("http://mp.weixin.qq.com/s/EU48P3rVcc1MU0VzdgKJDw  ");
        urlList.add("http://mp.weixin.qq.com/s/B1fWtLrwsP6I0qSXkhVQBg  ");
        urlList.add("http://mp.weixin.qq.com/s/eRNXU-DBSxVyVppFMLjKYg  ");
        urlList.add("https://mp.weixin.qq.com/s/vTqYRIXWElUTnjLrQ4kHuA ");
        urlList.add("http://mp.weixin.qq.com/s/KtJZeysgtGIqo7ZtiMy7bg  ");
        urlList.add("http://mp.weixin.qq.com/s/7PjPojKwx9Ic2qxqc2YWew  ");
        urlList.add("http://mp.weixin.qq.com/s/ATp1XR3gEzb4IjOIvOozMg  ");
        urlList.add("http://mp.weixin.qq.com/s/Y1TU5nX53SRtAhYF-w_u_A  ");
        urlList.add("http://mp.weixin.qq.com/s/fFBLc2IUhnx1RbEhAYkLAg  ");
        urlList.add("http://mp.weixin.qq.com/s/egGu2GWOZZE3nO5x4EZzUQ  ");
        urlList.add("http://mp.weixin.qq.com/s/m7ewB-jjBN8wMjFvsP2W0A  ");
        urlList.add("http://mp.weixin.qq.com/s/HqRCxmlwsRiMO8-BuCJ6vQ  ");
        urlList.add("http://mp.weixin.qq.com/s/8ENP-fE5RPR7K2GJaJEc9w  ");
        urlList.add("http://mp.weixin.qq.com/s/JraV_osJflqy_0eV-TtgRA  ");
        urlList.add("https://mp.weixin.qq.com/s/THT5tH6o6-vqat7LPevyRg ");
        urlList.add("http://mp.weixin.qq.com/s/XRmYmX3m205nx23OhjHgEA  ");
        urlList.add("http://mp.weixin.qq.com/s/KJ0XCD7Vaiv3zyERVL10Sw  ");
        urlList.add("http://mp.weixin.qq.com/s/lKWvkqlTwQuS8hjRTcnBbQ  ");
        urlList.add("http://mp.weixin.qq.com/s/uIJdK_s-3-KyRvn9VG187w  ");
        urlList.add("http://mp.weixin.qq.com/s/McenfJyHN95VbRzcXyjRxg  ");
        urlList.add("http://mp.weixin.qq.com/s/K7ty7GlPyDVQLx2meM7o6w  ");
        urlList.add("http://mp.weixin.qq.com/s/jpbhrWBwTPPLPSFIyN_8HA  ");
        urlList.add("http://mp.weixin.qq.com/s/OEtxjY3JC8-Ue3qJYYY-Sg  ");
        urlList.add("http://mp.weixin.qq.com/s/crypYGlVpdqa-XWxOJKmtQ  ");
        urlList.add("http://mp.weixin.qq.com/s/EgeMa6Bu0KprAL1sLrUtQA  ");
        urlList.add("http://mp.weixin.qq.com/s/EgeMa6Bu0KprAL1sLrUtQA  ");
        urlList.add("http://mp.weixin.qq.com/s/3Sz6w6nHXa0AJfB-fGG-Fg  ");
        urlList.add("http://mp.weixin.qq.com/s/6rUkquBXY5hvHSdrdXHS6A  ");
        urlList.add("http://mp.weixin.qq.com/s/5NsZP4nN5DKQ1PT9li4yIw  ");
        urlList.add("http://mp.weixin.qq.com/s/5_uS72Zrvk70dwkLCJVQRQ  ");
        urlList.add("http://mp.weixin.qq.com/s/W2khzfBRt7fQsubGQfee6g  ");
        urlList.add("https://mp.weixin.qq.com/s/0RW5hZ4FdwF416BF3vZT3Q ");
        urlList.add("http://mp.weixin.qq.com/s/TH5szWlT_CeWMYKpvYYNPQ  ");
        urlList.add("http://mp.weixin.qq.com/s/n1HyEz_xWUist9QNY70Z3A  ");
        urlList.add("https://mp.weixin.qq.com/s/NDgjJ6_bhAqdDRN4-852Qw ");
        urlList.add("http://mp.weixin.qq.com/s/fmIhAcAv6AFsTbefTudwlg  ");
        urlList.add("http://mp.weixin.qq.com/s/FPatZmTRkSKp91k7u55dig  ");
        urlList.add("http://mp.weixin.qq.com/s/7_vPnjqqMH6NDCVqKvgnNg  ");
        urlList.add("http://mp.weixin.qq.com/s/dOE2_73mHxJ-FypGbxWNdA  ");
        urlList.add("https://mp.weixin.qq.com/s/i5Uz7v-c4USIQOh18k_r_Q ");
        urlList.add("http://mp.weixin.qq.com/s/IXC3_rt6Pg4M8ray3GzDbw  ");
        urlList.add("http://mp.weixin.qq.com/s/zScz-nKp39kl1t6Ub3BHoA  ");
        urlList.add("http://mp.weixin.qq.com/s/kXG-XG6KrJIRI7H_-orRQg  ");
        urlList.add("http://mp.weixin.qq.com/s/8jU4Ru8nn6CgCoDvUEZIMw  ");
        urlList.add("http://mp.weixin.qq.com/s/op8fFkajKXtJJHKfTcAIow  ");
        urlList.add("http://mp.weixin.qq.com/s/a_jKLQu001Q5tjUL_5-OWw  ");
        urlList.add("http://mp.weixin.qq.com/s/EU48P3rVcc1MU0VzdgKJDw  ");
        urlList.add("http://mp.weixin.qq.com/s/B1fWtLrwsP6I0qSXkhVQBg  ");
        urlList.add("http://mp.weixin.qq.com/s/eRNXU-DBSxVyVppFMLjKYg  ");
        urlList.add("http://mp.weixin.qq.com/s/vTqYRIXWElUTnjLrQ4kHuA  ");
        urlList.add("http://mp.weixin.qq.com/s/KtJZeysgtGIqo7ZtiMy7bg  ");
        urlList.add("http://mp.weixin.qq.com/s/7PjPojKwx9Ic2qxqc2YWew  ");

        for (String url:urlList) {
            SpiderUtils.saveDetailItems(url, path);
        }
    }
}
