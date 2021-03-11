package utils;

import java.io.File;
import javax.swing.filechooser.FileFilter;

// фильтр для возможносьти выбора файлов только с расширением mp3 для компонента FileChoser
// этот фильтр нужно указать (задать) для компонента FileChoser в его свойствах
public class PlaylistFileFilter extends FileFilter{
// реализуем все абстрактные методы (правой кнопкой)
    private String fileExtension; // для расширения
    private String fileDescription; // для текстового названия
    
    // конструктор
    public PlaylistFileFilter(String fileExtension, String fileDescription) { // передаем в класс любое расширение(fileExtension) и любое описание этого расширения(fileDescription)
        this.fileExtension=fileExtension;// расширение файла
        this.fileDescription=fileDescription; // описание файла
    }
    
    
    @Override
    public boolean accept(File file) { 
        return file.isDirectory() || file.getAbsolutePath().endsWith(fileExtension); // Разрешить только папки, а так же файлы с расширением mp3
        
    }

    @Override
    public String getDescription() {
        return fileDescription+"(*."+fileExtension+")"; // описание для формата mp3 при выборе в диалоговом окне
    }


    
}
