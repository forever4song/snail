package cn.eternalsong.snail.dp.observerPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-3-31
 * Time: 下午4:53
 * Description: 观察者实例1
 */
public class BinaryObserver extends Observer{
    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}
