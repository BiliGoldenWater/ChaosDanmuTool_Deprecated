package indi.goldenwater.chaosdanmutool.controller;

import com.sun.javafx.webkit.Accessor;
import com.sun.webkit.WebPage;
import indi.goldenwater.chaosdanmutool.ChaosDanmuTool;
import indi.goldenwater.chaosdanmutool.config.Config;
import indi.goldenwater.chaosdanmutool.utils.HTMLReplaceVar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class DanmuViewController {
    final Logger logger = LogManager.getLogger(DanmuViewController.class);

    private double lastPressedX;
    private double lastPressedY;
    private Stage thisStage;

    @FXML
    protected WebView danmuView;
    @FXML
    protected AnchorPane anchorPane;
    @FXML
    protected Button btnClose;
    @FXML
    protected Button btnReload;

    @FXML
    protected void initialize() throws Exception {
        logger.debug("[DanmuView] Initializing.");
        thisStage = ChaosDanmuTool.getInstance().getStageManager().getStage("danmuView");
        final Config config = ChaosDanmuTool.getConfig();

        loadPosition(config);
        thisStage.setOnCloseRequest(event -> onClose(config));
        anchorPane.setStyle("-fx-background-color: transparent;");
        btnClose.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: transparent;" +
                "-fx-text-fill: transparent;");
        btnReload.setStyle(btnClose.getStyle());
        btnClose.setLayoutX(0);
        btnClose.setLayoutY(config.internalBrowser.height - 26);
        btnReload.setLayoutX(25);
        btnReload.setLayoutY(config.internalBrowser.height - 26);
        btnClose.setOnMouseExited((mouseEvent) -> btnClose.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: transparent;" +
                "-fx-text-fill: transparent;"));
        btnClose.setOnMouseEntered((mouseEvent) -> btnClose.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: rgba(0,0,0,0.1); " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px;" +
                "-fx-text-fill: white"));
        btnReload.setOnMouseExited((mouseEvent) -> btnReload.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: transparent;" +
                "-fx-text-fill: transparent;"));
        btnReload.setOnMouseEntered((mouseEvent) -> btnReload.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: rgba(0,0,0,0.1); " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px;" +
                "-fx-text-fill: white"));
        initDanmuView(config);

        logger.debug("[DanmuView] Initialized.");
    }

    private void initDanmuView(Config config) throws Exception {
        danmuView.setStyle("-fx-background-color: transparent;");
        danmuView.setMouseTransparent(true);

        WebEngine webEngine = danmuView.getEngine();
        String html = HTMLReplaceVar.get(config);

        if (html == null) {
            webEngine.loadContent("Failed to load html.");
            logger.error("[DanmuView] Failed to load html.");
            return;
        }

        webEngine.loadContent(html);
        WebPage page = Accessor.getPageFor(webEngine);
        page.setBackgroundColor(0x00000001);
    }

    @FXML
    protected void onAnchorPaneMousePressed(MouseEvent event) {
        lastPressedX = event.getSceneX();
        lastPressedY = event.getSceneY();
    }

    @FXML
    protected void onAnchorPaneMouseDragged(MouseEvent event) {
        thisStage.setX(event.getScreenX() - lastPressedX);
        thisStage.setY(event.getScreenY() - lastPressedY);
    }

    @FXML
    protected void onBtnCloseClicked(MouseEvent event) {
        if (event.getButton().compareTo(MouseButton.PRIMARY) == 0) {
            ChaosDanmuTool.getInstance().getStageManager().closeStage("danmuView");
        }
    }

    @FXML
    protected void onBtnReloadClicked(MouseEvent event) throws IOException {
        if (event.getButton().compareTo(MouseButton.PRIMARY) == 0) {
            final Config config = ChaosDanmuTool.getConfig();

            WebEngine webEngine = danmuView.getEngine();
            String html = HTMLReplaceVar.get(config);

            if (html == null) {
                webEngine.loadContent("Failed to load html.");
                logger.error("[DanmuView] Failed to load html.");
                return;
            }

            webEngine.loadContent(html);
        }
    }

    private void loadPosition(Config config) {
        logger.debug("[DanmuView] Loading position.");
        if (config.internalBrowser.posX != 0) thisStage.setX(config.internalBrowser.posX);
        if (config.internalBrowser.posY != 0) thisStage.setY(config.internalBrowser.posY);
        logger.debug("[DanmuView] Position loaded.");
    }

    private void savePosition(Config config) {
        logger.debug("[DanmuView] Saving position.");
        config.internalBrowser.posX = thisStage.getX();
        config.internalBrowser.posY = thisStage.getY();
        logger.debug("[DanmuView] Position saved.");
    }

    private void onClose(Config config) {
        savePosition(config);
        thisStage.close();
    }
}
