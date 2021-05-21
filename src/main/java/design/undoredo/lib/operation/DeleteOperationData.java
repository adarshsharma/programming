package design.undoredo.lib.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by adarsh.sharma on 30/05/18.
 */

public class DeleteOperationData extends OperationData {
    private int position;
    private String text;

    public DeleteOperationData(int position, String text) {
        this.position = position;
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
