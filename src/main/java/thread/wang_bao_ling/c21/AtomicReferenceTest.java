package thread.wang_bao_ling.c21;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    AtomicReference<User> userAtomicReference = new AtomicReference<>();

    public static void main(String[] args) {


        new Thread(){
            User user   =new User();
            @Override
            public void run() {
                user =new User();
            }
        }.start();

    }
}
