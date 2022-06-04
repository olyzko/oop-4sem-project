package com.example.oopproject.controller;

import com.example.oopproject.ui.switch_handler.View;
import com.example.oopproject.ui.switch_handler.ViewSwitcher;

public class SortingsController {
    public void onMainButton() {
        ViewSwitcher.switchTo(View.MAIN);
    }
}
