package agh.ics.oop.presenter;

import agh.ics.oop.model.WorldElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WorldElementBox extends VBox {
    private static final int IMAGE_HEIGHT = 20;
    private static final int  IMAGE_WIDTH = 20;
    private static  final Map<String, Image> images = new HashMap<>();

    static {
        String[] images = {"up.png", "right.png", "down.png", "left.png", "grass.png"};
        for (String url : images) {
            WorldElementBox.images.put(url, new Image(url));
        }
    }

    private ImageView lastImageView;
    private String lastImageUrl;
    private String lastLabel;

    // if simulation length was larger we could keep all 4 images for every animal
    // by doing so we would always create exactly 4 images per Animal (still one per Grass)
    // I believe that currently we will on average have less than 4
    private void updateImage(WorldElement element) {
        String currImageUrl = element.getImageResource();
        if (! Objects.equals(lastImageUrl, currImageUrl)) {
            ImageView currImageView = new ImageView(images.get(currImageUrl));
            currImageView.setFitHeight(IMAGE_HEIGHT);
            currImageView.setFitWidth(IMAGE_WIDTH);

            this.getChildren().remove(lastImageView);
            this.getChildren().add(currImageView);

            lastImageUrl = currImageUrl;
            lastImageView = currImageView;
        }
    }

    private  void updateLabel(WorldElement element) {
        String currLabel = element.getPosition().toString();

        if (! Objects.equals(currLabel, lastLabel)) {
            Label positionLabel = new Label(currLabel);

            this.getChildren().add(positionLabel);

            lastLabel = currLabel;
        }
    }

    public WorldElementBox(WorldElement element) {
        updateImage(element);
        updateLabel(element);
        this.setAlignment(Pos.CENTER);
    }
}
