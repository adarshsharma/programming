package design.undoredo.lib.operation;

import design.undoredo.lib.TextAction;

/**
 * Created by adarsh.sharma on 14/03/15.
 */
public class AddOperation implements Operation {
    private final AddOperationData operationData;

    public AddOperation(int position, String textToAdd) {
        if (textToAdd == null || position < 0) {
            throw new RuntimeException("invalid values to create undoRedo.operation");
        }
        this.operationData = new AddOperationData(position, textToAdd);
    }

    @Override
    public void perform(TextAction textAction) {
        textAction.addText(operationData.getPosition(), operationData.getText());
    }

    @Override
    public void undo(TextAction textAction) {
        textAction.removeText(operationData.getPosition(), operationData.getText().length());
    }
}
