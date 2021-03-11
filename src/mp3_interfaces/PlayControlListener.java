package mp3_interfaces;

/**
 *
 * @author Михаил
 */
public interface PlayControlListener {
    
    void playStarted(String name);
    
    void processScroll(int position);
    
    void playFinished();
    
}
