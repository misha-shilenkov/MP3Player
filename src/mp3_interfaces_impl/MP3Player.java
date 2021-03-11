package mp3_interfaces_impl;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import mp3_interfaces.Player;
import utils.FileUtils;


import mp3_interfaces.PlayControlListener;
import objects.BasicPlayerListenerAdapter;


//Класс который служит непосредственно для проигрывания МП3 файлов
public class MP3Player implements Player{
    
    //константы
    public static final String MP3_FILE_EXTESION="mp3"; // добавляет mp3 в качестве расширения при сохранении файла mp3
    public static final String MP3_FILE_DESCRIPTION="файлы mp3"; // константа для подсказки
    public static int MAX_VOLUME = 100;
    
   
    //создаем объект типа BasicPlayer. BasicPlayer - это стандартный класс для проигрывания мп3 из библиотеки java
    // библиотеки подключаем к проету вручную jar файлами!
    private BasicPlayer basicPlayer = new BasicPlayer();// используем библиотеку для реализации проигрывания mp3 
    private String currentFileName; //Текущая песня
    private double currentVolumeValue; // текущий уровень звука
    
    private long duration; // длительность песни в секундах
    private int bytesLen; // размер песни в байтах
    private long secondsAmount; // сколько секунд прошло с начала проигрывания

    private final PlayControlListener playControlListener; // контроль за состоянием проигрывания
    
        public MP3Player(PlayControlListener playControlListener) {
        this.playControlListener=playControlListener;
        
        basicPlayer.addBasicPlayerListener(new BasicPlayerListenerAdapter() {
           
            @Override
            public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {

                float progress = -1.0f;// f приведение к float

                if ((bytesread > 0) && ((duration > 0))) {
                    progress = bytesread * 1.0f / bytesLen * 1.0f;
                }

                // сколько секунд прошло
                secondsAmount = (long) (duration * progress);

                if (duration != 0) {
                    int length = ((int) Math.round(secondsAmount * 1000 / duration));
                    MP3Player.this.playControlListener.processScroll(length);
                }
            }

            @Override
            public void opened(Object o, Map map) {
                duration = (long) Math.round((((Long) map.get("duration"))) / 1000000);
                bytesLen = (int) Math.round(((Integer) map.get("mp3.length.bytes")));

                // если есть mp3 тег для имени - берем его, если нет - вытаскиваем название из имени файла
                String songName = map.get("title") != null ? map.get("title").toString() : FileUtils.getFileNameWithoutExtension(new File(o.toString()).getName());

                // если длинное название - укоротить его
                if (songName.length() > 30) {
                    songName = songName.substring(0, 30) + "...";
                }

                MP3Player.this.playControlListener.playStarted(songName);

            }

            @Override
            public void stateUpdated(BasicPlayerEvent bpe) {
                int state = bpe.getCode();

                if (state == BasicPlayerEvent.EOM) {
                    MP3Player.this.playControlListener.playFinished();
                }

            }

        });
        
    }
        
        
        // подсчитать значение звука исходя из значений компонента регулировки звука (JSlider)
    
    private double calcVolume(double currentValue) {
        currentVolumeValue = (double) currentValue / MAX_VOLUME;
        return currentVolumeValue;
    }    
        
     
   // <editor-fold desc="Переопределяем методы интерфейса Player">
       
        
    @Override
    public void play(String fileName) {

        try {
            // если включают ту же самую песню, которая была на паузе
            if (currentFileName != null && currentFileName.equals(fileName) && basicPlayer.getStatus() == BasicPlayer.PAUSED) {
                basicPlayer.resume();
                return;
            }

            File mp3File = new File(fileName);

            currentFileName = fileName;
            basicPlayer.open(mp3File);
            basicPlayer.play();
            basicPlayer.setGain(currentVolumeValue);

        } catch (BasicPlayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   @Override
    public void stop() {
        try {
            basicPlayer.stop();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
@Override
    public void pause() {
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // регулирует звук при проигрывании песни
    @Override
    public void setVolume(double controlValue) {
        try {

            currentVolumeValue = calcVolume(controlValue);
            basicPlayer.setGain(currentVolumeValue);

        } catch (BasicPlayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @Override
    public void jump(double controlPosition) {
        try {
            long skipBytes = (long) Math.round(((Integer) bytesLen) * controlPosition);
            basicPlayer.seek(skipBytes);
            basicPlayer.setGain(currentVolumeValue);
        } catch (BasicPlayerException ex) {
            Logger.getLogger(MP3Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold> 
    
    

}

