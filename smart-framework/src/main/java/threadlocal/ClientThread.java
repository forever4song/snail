package threadlocal;

/**
 * Created with IntelliJ IDEA.
 * User: 长歌
 * Date: 2018/4/24
 * Time: 16:50
 * Description: 线程类
 */
public class ClientThread extends Thread {
    private Sequence sequence;
    public ClientThread(Sequence sequence){
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i=0;i<3;i++){
            try {
                System.out.println(Thread.currentThread().getName() + " => "+sequence.getNumber());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}