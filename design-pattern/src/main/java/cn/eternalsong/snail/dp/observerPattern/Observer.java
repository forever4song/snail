package cn.eternalsong.snail.dp.observerPattern;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 18-3-31
 * Time: 下午4:52
 * Description: 观察者接口
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
