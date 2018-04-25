package threadlocal;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/25
 * Time: 11:50
 * Description: 序列号生成器B（使用ThreadLocal）
 */
public class SequenceB implements Sequence{

    private static ThreadLocal<Integer> numberContainer = ThreadLocal.withInitial(() -> 0);

    @Override
    public int getNumber()  {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        Sequence sequence = new SequenceB();

        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}