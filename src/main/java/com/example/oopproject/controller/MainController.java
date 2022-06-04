package com.example.oopproject.controller;

import com.example.oopproject.ui.switch_handler.View;
import com.example.oopproject.ui.switch_handler.ViewSwitcher;
import javafx.event.ActionEvent;

public class MainController {
    public void onSortingsButton() {
        ViewSwitcher.switchTo(View.SORTING);
    }

    public void onBinaryTreesButton() {
        ViewSwitcher.switchTo(View.BINARY_TREES);
    }
}
