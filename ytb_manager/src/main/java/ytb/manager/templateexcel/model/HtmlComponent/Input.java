package ytb.manager.templateexcel.model.HtmlComponent;

/**
 * LXZ
 */
public class Input extends Metadata {

    private String value = "";

    private String placeholder = "";

    public Input() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }
}
