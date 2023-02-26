package Qn_9to12;

import javax.swing.*;
import java.awt.*;

class ScrollableLabel extends JLabel implements Scrollable {
    /*scrollable is used to show the list of task to complete when a job is selected*/
    public ScrollableLabel(String i) {
        super(i);
    }

    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    public int getScrollableBlockIncrement(Rectangle r, int orietation,
                                           int direction) {
        return 10;
    }

    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    public int getScrollableUnitIncrement(Rectangle r, int orientation,
                                          int direction) {
        return 10;
    }

}