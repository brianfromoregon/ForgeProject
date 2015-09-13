package com.findrealhope.shapes;

import java.util.List;

/**
 * Helper methods for shapes
 */
public interface ShapeUtil {

    default int param(List<String> params, int idx, int def) {
        if (params.size() >= idx) {
            try {
                return Integer.parseInt(params.get(idx));
            } catch (Exception e) {
            }
        }
        return def;
    }
}
