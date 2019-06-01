package main.file;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;


public class SelectFile {

    private static SelectFile selectFile = null;
    private JFileChooser jFileChooser;
    private FileNameExtensionFilter filterCsv;
    private FileNameExtensionFilter filterXml;

    private SelectFile() {
    }

    //ładowanie okna do wczytania pliku
    private void initDialog() {
        jFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jFileChooser.setDialogTitle("Wybierz plik XML lub CSV");
        jFileChooser.setAcceptAllFileFilterUsed(false);
        filterCsv = new FileNameExtensionFilter(".csv file", "csv");
        filterXml = new FileNameExtensionFilter(".xml file", "xml");
        jFileChooser.addChoosableFileFilter(filterCsv);
        jFileChooser.addChoosableFileFilter(filterXml);
    }

    //funkcja zwraca załatowane okno
    public JFileChooser showDialog() {
        initDialog();

        return jFileChooser;
    }

    //zwraca tablice z nazwia pliku i rozszerzeniem
    public String[] getSelectedOption(int val) {
        String filePath = "";
        String fileExtension = "";
        if (val == JFileChooser.APPROVE_OPTION) {
            if (jFileChooser.getSelectedFile().isFile()) {
                if (jFileChooser.getFileFilter().equals(filterCsv)) {
                    filePath = jFileChooser.getSelectedFile().toString();
                    fileExtension = "csv";

                }
                if (jFileChooser.getFileFilter().equals(filterXml)) {
                    filePath = jFileChooser.getSelectedFile().toString();
                    fileExtension = "xml";
                }
            }
        }
        return new String[]{filePath, fileExtension};
    }


    public static SelectFile getInstance() {
        if (selectFile == null)
            selectFile = new SelectFile();

        return selectFile;
    }
}
