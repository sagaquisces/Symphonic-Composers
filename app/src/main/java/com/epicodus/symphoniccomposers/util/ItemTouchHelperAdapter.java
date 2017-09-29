package com.epicodus.symphoniccomposers.util;

/**
 * Created by Guest on 9/29/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int positiion);
}
