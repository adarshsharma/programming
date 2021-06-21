package design.undoredo;


import design.undoredo.lib.TextAction;
import design.undoredo.lib.UndoRedo;
import design.undoredo.lib.operation.AddOperation;
import design.undoredo.lib.operation.DeleteOperation;
import design.undoredo.lib.operation.FindAndReplaceOperation;
import design.undoredo.lib.operation.Operation;

/**
 * Created by adarsh.sharma on 14/03/15.
 */
public class TextEditor implements TextAction {

    private String text;
    private UndoRedo undoRedo;

    public TextEditor() {
        this.text = "";
        this.undoRedo = new UndoRedo(this);
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

        textEditor.addText(0, "abcde", true);
        textEditor.addText(2, "JKhijk", true);
        textEditor.addText(10, "lmno", true);
        textEditor.removeText(4, 2, true);
        textEditor.replaceFirst("jk", "JK", true);

        textEditor.undo();
        textEditor.redo();
        textEditor.undo();
        textEditor.undo();
        textEditor.undo();
    }

    public String getText() {
        return text;
    }

    public void undo() {
        undoRedo.undo();
    }

    public void redo() {
        undoRedo.redo();
    }

    private void addText(int position, String textToAdd, boolean callUndoRedo) {
        if (callUndoRedo) {
            Operation operation = new AddOperation(position, textToAdd);
            undoRedo.addOperation(operation);
        }
        text = new StringBuilder(text).insert(position, textToAdd).toString();
        System.out.println("text: " + text);
    }

    private void removeText(int position, int length, boolean callUndoRedo) {
        if (callUndoRedo) {
            Operation operation = new DeleteOperation(position,
                text.substring(position, position + length));
            undoRedo.addOperation(operation);
        }
        text = new StringBuilder(text).delete(position, position + length).toString();
        System.out.println("text: " + text);
    }

    private void replaceFirst(String originalText, String newText, boolean callUndoRedo) {
        if (callUndoRedo) {
            Operation operation = new FindAndReplaceOperation(text.indexOf(originalText),
                originalText, newText);
            undoRedo.addOperation(operation);
        }
        text = text.replaceFirst(originalText, newText);
        System.out.println("text: " + text);
    }


    @Override
    public void addText(int position, String textToAdd) {
        addText(position, textToAdd, false);
    }

    @Override
    public void removeText(int position, int length) {
        removeText(position, length, false);
    }

}
