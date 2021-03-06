package indi.goldenwater.chaosdanmutool.model.html;

import indi.goldenwater.chaosdanmutool.ChaosDanmuTool;
import indi.goldenwater.chaosdanmutool.config.Config;
import indi.goldenwater.chaosdanmutool.model.danmu.GuardBuy;

public class GuardBuyHTML extends DanmuItemHTML {
    private static final String guardBuyTemplate = "" +
            "<span style=\"color: {{textColor}}\"> 购买了<span style=\"color: {{giftNameColor}};\"> {{gift_name}} </span>" +
            " x {{gift_num}} {{price}}" +
            "</span>";

    public static String parse(GuardBuy guardBuy) {
        final Config config = ChaosDanmuTool.getConfig();

        String userInfoHTML = UserInfoHTML.parse(null,
                false,
                false,
                false,
                guardBuy.username);

        String guardBuyHTML = guardBuyTemplate
                .replace("{{textColor}}", config.internalViewConfig.style.danmuContent.textColor)
                .replace("{{giftNameColor}}", "#ffff00")
                .replace("{{gift_name}}", guardBuy.gift_name != null ? guardBuy.gift_name : "未知")
                .replace("{{gift_num}}", String.valueOf(guardBuy.num))
                .replace("{{price}}", "￥" + (guardBuy.price / 1000));
        return start.replace("class=\"danmu-item\"", "class=\"danmu-item danmu-gift\"")
                + userInfoHTML
                + guardBuyHTML
                + end;
    }
}
