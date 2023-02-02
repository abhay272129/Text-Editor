import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class texteditor extends JFrame implements ActionListener {

    JScrollPane scrollpane;
    JTextArea textArea;
    JSpinner fontsizespinner;
    JComboBox fontbox;
    JMenuBar mb;
    JMenu menu;
    JMenuItem save;
    JMenuItem exit;
    JLabel fontsizelevel, fontStyle;

    texteditor() {
        this.setSize(1000, 1000);
        this.setTitle("Text-Editor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        //text area
        textArea = new JTextArea();
        textArea.setSelectedTextColor(Color.blue);
        textArea.setFont(new Font("Arial", Font.PLAIN, 15));

        //pane
        scrollpane = new JScrollPane(textArea);
        scrollpane.setPreferredSize(new Dimension(950, 950));

        //font size spinner
        fontsizespinner = new JSpinner();
        fontsizespinner.setPreferredSize(new Dimension(50, 25));
        fontsizespinner.setSize(new Dimension(50, 25));
        fontsizespinner.setValue(18);

        fontsizespinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontsizespinner.getValue()));

            }
        });
        //add color option on your own
        // add font style option on your own (BOLD,Plain, Italic)

        // font scroll bar
        //  String[] fonts={"Arial", "SansSerif"};
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontbox = new JComboBox(fonts);
        fontbox.addActionListener(this);

        // Creating menu bar
        mb = new JMenuBar();
        menu = new JMenu("File");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        save.addActionListener(this);
        menu.add(save);
        menu.add(exit);
        mb.add(menu);

        // add all the element in JFrame
        fontsizelevel=new JLabel("Font Size:");
        fontStyle=new JLabel(" Font Style:");
        this.setJMenuBar(mb);
        this.add(fontsizelevel);
        this.add(fontsizespinner);
        this.add(fontStyle);
        this.add(fontbox);
        this.add(scrollpane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fontbox) {
            textArea.setFont(new Font((String) fontbox.getSelectedItem(), Font.PLAIN, (int) fontsizespinner.getValue()));
        }

        if (e.getSource() == exit) {
            System.exit(29);
        }

        if (e.getSource() == save) {
            JFileChooser file_chooser = new JFileChooser();
            file_chooser.setCurrentDirectory(new File("."));

            int response = file_chooser.showSaveDialog(null);
            /*
             if the user click on save button then the response will be 0
             if the user click on cancel butten then the response will be 1
            */
            if (response == 0) {
                // i need to save  this file
                File file = new File(file_chooser.getSelectedFile().getAbsolutePath());
                PrintWriter text;
                try {
                    text = new PrintWriter(file);
                    text.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                text.close();
            }
        }
    }
    public static  void main(String[] args){
        new texteditor();
    }
}


