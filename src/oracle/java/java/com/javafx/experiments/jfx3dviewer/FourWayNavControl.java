/*
 * Copyright (c) 2017. Tous les fichiers dans ce programme sont soumis à la License Publique Générale GNU créée par la Free Softxware Association, Boston.
 * La plupart des licenses de parties tièrces sont compatibles avec la license principale.
 * Les parties tierces peuvent être soumises à d'autres licenses.
 * Montemedia : Creative Commons
 * ECT : Tests à valeur artistique ou technique.
 * La partie RayTacer a été honteusement copiée sur le Net. Puis traduite en Java et améliorée.
 * Java est une marque de la société Oracle.
 *
 * Pour le moment le programme est entièrement accessible sans frais supplémentaire. Get the sources, build it, use it, like it, share it.
 */
package com.javafx.experiments.jfx3dviewer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Duration;

/**
 * A four way control with 4 direction arrow buttons.
 */
public class FourWayNavControl extends GridPane {

    private FourWayListener listener;
    private Side currentDirection = null;
    private Timeline eventFiringTimeline;
    private boolean hasFired = false;

    public FourWayNavControl() {
        getStyleClass().addAll("button", "four-way");
        Region upIcon = new Region();
        upIcon.getStyleClass().add("up");
        Region downIcon = new Region();
        downIcon.getStyleClass().add("down");
        Region leftIcon = new Region();
        leftIcon.getStyleClass().add("left");
        Region rightIcon = new Region();
        rightIcon.getStyleClass().add("right");
        Region centerIcon = new Region();
        centerIcon.getStyleClass().add("center");

        GridPane.setConstraints(upIcon,1,0);
        GridPane.setConstraints(leftIcon,0,1);
        GridPane.setConstraints(centerIcon,1,1);
        GridPane.setConstraints(rightIcon,2,1);
        GridPane.setConstraints(downIcon, 1, 2);

        getChildren().addAll(upIcon,downIcon,leftIcon,rightIcon,centerIcon);

        eventFiringTimeline = new Timeline(
            new KeyFrame(Duration.millis(80), event -> {
                if (listener != null && currentDirection != null) listener.navigateStep(currentDirection,0.5);
                hasFired = true;
            })
        );
        eventFiringTimeline.setDelay(Duration.millis(300));
        eventFiringTimeline.setCycleCount(Timeline.INDEFINITE);

        upIcon.setOnMousePressed(
                event -> {
                    currentDirection = Side.TOP;
                    hasFired = false;
                    eventFiringTimeline.playFromStart();
                });
        downIcon.setOnMousePressed(
                event -> {
                    currentDirection = Side.BOTTOM;
                    hasFired = false;
                    eventFiringTimeline.playFromStart();
                });
        leftIcon.setOnMousePressed(
                event -> {
                    currentDirection = Side.LEFT;
                    hasFired = false;
                    eventFiringTimeline.playFromStart();
                });
        rightIcon.setOnMousePressed(
                event -> {
                    currentDirection = Side.RIGHT;
                    hasFired = false;
                    eventFiringTimeline.playFromStart();
                });

        EventHandler<MouseEvent> stopHandler = event -> {
            if (listener != null && currentDirection != null && !hasFired) {
                listener.navigateStep(currentDirection,10);
            }
            currentDirection = null;
            eventFiringTimeline.stop();
        };
        upIcon.setOnMouseReleased(stopHandler);
        downIcon.setOnMouseReleased(stopHandler);
        rightIcon.setOnMouseReleased(stopHandler);
        leftIcon.setOnMouseReleased(stopHandler);
    }

    public FourWayListener getListener() {
        return listener;
    }

    public void setListener(FourWayListener listener) {
        this.listener = listener;
    }

    public static interface FourWayListener {
        public void navigateStep(Side direction, double amount);
    }
}
