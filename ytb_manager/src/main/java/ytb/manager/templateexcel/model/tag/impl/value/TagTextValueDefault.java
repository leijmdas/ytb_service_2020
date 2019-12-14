package ytb.manager.templateexcel.model.tag.impl.value;


import ytb.manager.templateexcel.model.HtmlComponent.Metadata;

/**
 * LXZ
 */
public class TagTextValueDefault extends TagValue {

    //Input or Text
    private Metadata metadata;

    public TagTextValueDefault() {
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
