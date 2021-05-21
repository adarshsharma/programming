package design.undoredo.lib.operation;

import design.undoredo.lib.TextAction;

/**
 * Created by adarsh.sharma on 15/03/15.
 */
public class DeleteOperation implements Operation {
    private DeleteOperationData operationData;

    public DeleteOperation(int position, String removedText) {
        if (position < 0 || removedText == null || removedText.length() < 1) {
            throw new RuntimeException("incorrect position or length");
        }
        this.operationData = new DeleteOperationData(position, removedText);
    }

    @Override
    public void perform(TextAction textAction) {
        textAction.removeText(operationData.getPosition(), operationData.getText().length());
    }

    @Override
    public void undo(TextAction textAction) {
        textAction.addText(operationData.getPosition(), operationData.getText());
    }
}
