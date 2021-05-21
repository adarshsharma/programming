package design.undoredo.lib;

/**
 * Created by adarsh.sharma on 29/05/18.
 */
public interface TextAction {
    void addText(int position, String textToAdd);

    void removeText(int position, int length);
}
