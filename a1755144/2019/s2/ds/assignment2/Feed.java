import java.io.Serializable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Feed implements Serializable, Comparable<Feed> {

    private int lamportTime;
    private String content;

    public Feed() {
        this.lamportTime = -1;
        this.content = null;
    }

    public int getLamportTime() {
        return lamportTime;
    }

    public void setLamportTime(int lamportTime) {
        this.lamportTime = lamportTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Feed feed) {
        return getLamportTime() - feed.getLamportTime();
    }

}
