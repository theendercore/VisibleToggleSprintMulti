package com.theendercore.visible_toggle_sprint.platform.services;

import java.util.function.BiFunction;

public interface IHudRenderer {

//    /**
//     * Checks if a mod with the given id is loaded.
//     *
//     * @param modId The mod to check if it is loaded.
//     * @return True if the mod is loaded, false otherwise.
//     */
    void hudRenderer(BiFunction<Integer, String, String> fn);
}
