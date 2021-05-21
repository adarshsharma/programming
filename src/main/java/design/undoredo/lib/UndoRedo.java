package design.undoredo.lib;


import design.undoredo.lib.operation.Operation;

import java.util.Stack;

/**
 * Created by adarsh.sharma on 14/03/15.
 */
public class UndoRedo {
    private Stack<Operation> undoStack;
    private Stack<Operation> redoStack;
    private TextAction textAction;

    public UndoRedo(TextAction textAction) {
        this.textAction = textAction;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void addOperation(Operation operation) {
        if (!redoStack.empty()) {
            redoStack.clear();
        }
        undoStack.push(operation);
    }

    public void undo() {
        if (undoStack.empty()) {
            System.out.println("No Operation to undo");
            return;
        }

        Operation operation = undoStack.pop();
        redoStack.push(operation);
        operation.undo(textAction);
    }

    public void redo() {
        if (redoStack.empty()) {
            System.out.println("No Operation to redo");
            return;
        }

        Operation operation = redoStack.pop();
        undoStack.push(operation);
        operation.perform(textAction);
    }
}
