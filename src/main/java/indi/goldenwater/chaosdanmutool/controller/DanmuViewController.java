package indi.goldenwater.chaosdanmutool.controller;

import indi.goldenwater.chaosdanmutool.utils.DanmuReceiver;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.net.URISyntaxException;

public class DanmuViewController {
    @FXML
    protected VBox danmuContainer;

    @FXML
    protected void initialize() {
        danmuContainer.setAlignment(Pos.BOTTOM_LEFT);
    }
}
