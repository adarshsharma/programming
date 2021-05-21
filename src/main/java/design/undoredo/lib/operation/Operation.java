package design.undoredo.lib.operation;

import design.undoredo.lib.TextAction;

/**
 * Created by adarsh.sharma on 14/03/15.
 */
public interface Operation {
    void perform(TextAction textAction);

    void undo(TextAction textAction);
}
