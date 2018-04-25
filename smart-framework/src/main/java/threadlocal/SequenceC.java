package threadlocal;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/25
 * Time: 16:39
 * Description: 序列号生成器C(使用自定义ThreadLocal)
 */
public class SequenceC implements Sequence {
    private static MyThreadLocalLocal<Integer> numberContainer = new MyThreadLocalLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get()+1);
        return numberContainer.get();
    }
    /*
    ThreadLocal
    举个栗子: 数据库连接管理。多个线程共享相同Connection时，其中之一关闭可能会影响其他使用Connection的线程。
     */
    public static void main(String[] args) {
        Sequence sequence = new SequenceC();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}