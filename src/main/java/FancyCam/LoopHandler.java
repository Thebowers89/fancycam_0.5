package FancyCam;

public class LoopHandler implements Runnable {

    private int i = 0;

    public void run() {
        if (MainClass.victim != null && MainClass.spotHandler.loop != null) {
            if (i == MainClass.spotHandler.loop.size()) i = 0;
            MainClass.victim.teleport(MainClass.spotHandler.loop.get(i));
            i++;
        }
    }
}
