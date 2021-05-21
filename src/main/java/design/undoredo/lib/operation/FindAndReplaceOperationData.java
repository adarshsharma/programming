package design.undoredo.lib.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by adarsh.sharma on 30/05/18.
 */

public class FindAndReplaceOperationData extends OperationData {
    private Integer position;
    private String originalText;
    private String newText;

    public FindAndReplaceOperationData(Integer position, String originalText, String newText) {
        this.position = position;
        this.originalText = originalText;
        this.newText = newText;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getNewText() {
        return newText;
    }

    public void setNewText(String newText) {
        this.newText = newText;
    }
}
