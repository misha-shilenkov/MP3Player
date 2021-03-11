/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp3_interfaces;

import java.io.File;

/**
 *
 * @author Михаил
 */
public interface PlayList {
    void next();

    void prev();
    
    void delete();
    
    void clear();

    boolean search(String name);

    boolean savePlaylist(File file);

    boolean openFiles(File[] files);
    
    boolean openPlayList(File file);
    
    void playFile();
}
