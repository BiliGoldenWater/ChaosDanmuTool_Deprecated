package indi.goldenwater.chaosdanmutool.model.html.roomstatuschange;

import indi.goldenwater.chaosdanmutool.model.html.DanmuItemHTML;

public class RoomStatusChangeHTML extends DanmuItemHTML {
    public static final String roomStatusChangeTemplate =
            "<span style=\"color: {{prefixColor}}\">{{prefix}} </span>" +
                    "<span style=\"color: {{roomidColor}}\">{{roomid}}</span>" +
                    "<span style=\"color: {{actionColor}}\"> {{action}}</span>";


    public static String parse(String prefix, String roomid, String action, String prefixColor, String roomidColor, String actionColor) {
        String roomStatusChangeHTML = roomStatusChangeTemplate
                .replace("{{prefix}}", prefix)
                .replace("{{roomid}}", roomid)
                .replace("{{action}}", action)
                .replace("{{prefixColor}}", prefixColor)
                .replace("{{roomidColor}}", roomidColor)
                .replace("{{actionColor}}", actionColor);
        return start + roomStatusChangeHTML + end;
    }
}
