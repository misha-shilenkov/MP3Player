

package mp3_interfaces;



// интерфейс непосредственного проигрывания файлов
public interface Player {

    void play(String fileName);

    void stop();

    void pause();

    void setVolume(double value);

    void jump(double controlPosition);
}


