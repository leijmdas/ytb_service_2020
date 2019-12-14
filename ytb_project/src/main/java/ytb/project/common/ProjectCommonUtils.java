package ytb.project.common;

/**
 * Package: ytb.project.common
 * Author: ZCS
 * Date: Created in 2019/3/28 10:09
 */
public class ProjectCommonUtils {

    //根据信用等级获取金额
    public static int getAssistMoney(String creditGrade){
        int money = 0;
        switch(creditGrade){
            case "A+":
                money = 180;
                break;
            case "A":
                money = 160;
                break;
            case "A-":
                money = 140;
                break;
            case "B+":
                money = 120;
                break;
            case "B":
                money = 100;
                break;
            case "B-":
                money = 80;
                break;
            case "C+":
                money = 60;
                break;
            case "C":
                money = 40;
                break;
            case "C-":
                money = 20;
                break;
            default:
                break;
        }
        return money;
    }
}
