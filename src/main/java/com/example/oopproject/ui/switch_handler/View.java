package com.example.oopproject.ui.switch_handler;

public enum View {
    MAIN("main-view.fxml"),
    SORTING("sortings-view.fxml"),
    BINARY_TREES("binary-trees-view.fxml"),
    CONCURRENT_VS_PARALLEL("concurrent-parallel.fxml");

    private final String filename;

    /**
     * Sets the view mode.
     *
     * @param filename file of a scene
     */

    View(String filename) {
        this.filename = filename;
    }

    /**
     * Getter for the filename.
     *
     * @return filename as String.
     */

    public String getFilename() {
        return filename;
    }
}
