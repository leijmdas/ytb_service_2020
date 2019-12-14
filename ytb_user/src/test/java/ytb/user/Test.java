package ytb.user;

/**
 * @Author hj
 * @Description //TODO
 * @Date 2019/3/14
 **/
public class Test {
    public static void main(String[] args) {
        MyPaper myPaper = new MyPaper(new White());
        myPaper.choicePen();
    }
}
interface PaperColor{
    public void getPenColor();
}
class White implements PaperColor{
    public void getPenColor(){
        System.out.println("You need a white pen!");
    }
}
class Red implements PaperColor{
    public void getPenColor(){
        System.out.println("You need a red pen!");
    }
}
class Blue implements PaperColor{
    public void getPenColor(){
        System.out.println("You need a blue pen!");
    }
}
class Black implements PaperColor{
    public void getPenColor(){
        System.out.println("You need a black pen!");
    }
}
class MyPaper{
    private PaperColor paperColor;
    public MyPaper(PaperColor paperColor){
        this.paperColor = paperColor;
    }
    public PaperColor getPaperColor(){
        return this.paperColor;
    }
    public void choicePen(){
        this.paperColor.getPenColor();
    }
}
