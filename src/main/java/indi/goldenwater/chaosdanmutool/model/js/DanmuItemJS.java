package indi.goldenwater.chaosdanmutool.model.js;

public class DanmuItemJS {
    public static String getJsDanmuList(String html) {
        return getJs("danmuList", html);
    }

    public static String getJs(String listId, String html) {
        return String.format("document.getElementById(\"%s\").insertAdjacentHTML(\"beforeend\",\"%s\");", listId, html.replace("\"", "\\\""));

    }
}