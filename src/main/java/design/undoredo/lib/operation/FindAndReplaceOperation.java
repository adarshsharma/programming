package design.undoredo.lib.operation;

import design.undoredo.lib.TextAction;

/**
 * Created by adarsh.sharma on 30/05/18.
 */
public class FindAndReplaceOperation implements Operation {
    private final FindAndReplaceOperationData operationData;

    public FindAndReplaceOperation(Integer position, String originalText, String newText) {
        this.operationData = new FindAndReplaceOperationData(position, originalText, newText);
    }

    @Override
    public void perform(TextAction textAction) {
        textAction.removeText(operationData.getPosition(), operationData.getOriginalText().length());
        textAction.addText(operationData.getPosition(), operationData.getNewText());
    }

    @Override
    public void undo(TextAction textAction) {
        textAction.removeText(operationData.getPosition(), operationData.getNewText().length());
        textAction.addText(operationData.getPosition(), operationData.getOriginalText());
    }
}
