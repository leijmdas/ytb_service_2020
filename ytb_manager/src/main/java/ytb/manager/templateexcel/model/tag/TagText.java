package ytb.manager.templateexcel.model.tag;

/**
 * @Author lxz
 * @Date 2018/11/1 14:05
 * @Description xxx
 */
public class TagText extends Tag {

    private boolean topLevelDir = false;

    /**
     * 是否可添加目录
     */
    private boolean canAddDir = false;

    /**
     * 是否能添加子级目录
     */
    private boolean canAddDirChildLevel = false;

    /**
     * 是否可移除
     */
    private boolean canRemoveDir = false;

    /**
     * 是否是字母目录
     */
    private boolean letterDir = false;

    public boolean isTopLevelDir() {
        return topLevelDir;
    }

    public void setTopLevelDir(boolean topLevelDir) {
        this.topLevelDir = topLevelDir;
    }

    public boolean isCanAddDir() {
        return canAddDir;
    }

    public void setCanAddDir(boolean canAddDir) {
        this.canAddDir = canAddDir;
    }

    public boolean isCanAddDirChildLevel() {
        return canAddDirChildLevel;
    }

    public void setCanAddDirChildLevel(boolean canAddDirChildLevel) {
        this.canAddDirChildLevel = canAddDirChildLevel;
    }

    public boolean isLetterDir() {
        return letterDir;
    }

    public void setLetterDir(boolean letterDir) {
        this.letterDir = letterDir;
    }

    public boolean isCanRemoveDir() {
        return canRemoveDir;
    }

    public void setCanRemoveDir(boolean canRemoveDir) {
        this.canRemoveDir = canRemoveDir;
    }
}
