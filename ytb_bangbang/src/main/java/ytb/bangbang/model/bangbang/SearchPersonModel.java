package ytb.bangbang.model.bangbang;


/**
 * @author Lxz 2019/2/26 18:14
 */
public class SearchPersonModel  {

    private String bangbangNo;

    private int sex;

    private Integer ageStart;

    private Integer ageEnd;

    public SearchPersonModel() {
    }

    public String getBangbangNo() {
        return bangbangNo;
    }

    public void setBangbangNo(String bangbangNo) {
        this.bangbangNo = bangbangNo;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(Integer ageStart) {
        this.ageStart = ageStart;
    }

    public Integer getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(Integer ageEnd) {
        this.ageEnd = ageEnd;
    }
}
