package threadlocal;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/24
 * Time: 16:57
 * Description: 序列号生成器A
 */
public class SequenceA implements Sequence {
    private static int number = 0;  // 共享变量，线程不安全
    @Override
    public int getNumber()  {
        number = number + 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceA();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}