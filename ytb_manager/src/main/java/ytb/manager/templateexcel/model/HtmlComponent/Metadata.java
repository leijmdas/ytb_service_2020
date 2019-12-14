package ytb.manager.templateexcel.model.HtmlComponent;

/**
 * LXZ
 */
public abstract class Metadata {

    private final String type = this.getClass().getSimpleName().toLowerCase();

    public String getType() {
        return type;
    }
}
