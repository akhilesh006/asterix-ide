package asterix;


/*
 * Editor.java
 *
 * Created on Mar 22, 2013, 8:26:30 PM
 * 
 * Use for anyone as long as it is acknowldedged to me
 * Please dont reuse this code without prior notice.
 * If you have any enquiries please do not hesitate to email me
 * akhil.bca08@gmail.com
 * 
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.beans.PropertyVetoException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit.ParserCallback;
import javax.swing.text.html.parser.ParserDelegator;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

/**
 *
 * @author Akhilesh Dhar Dubey
 * @version 1.0.0
 * @since Mar 22, 2013, 8:26:30 PM
 *
 */
public class Editor extends JFrame {

    private static JLabel lblTitle;
    private static JButton btnClose;
    static public boolean setOK;
    boolean isSet = true;
    Calendar calendar;
    public Document doc;
    private Document doc1;
    public boolean isTextPlaying;
    public boolean isNarration;
    private BufferedReader input;
    public static PrintStream out;
    private String textPlainOrHtml;
    //where shall the compiled class be saved to (should exist already)
    private static String classOutputFolder = null;//"./src"
    DefaultMutableTreeNode root;
    DefaultTreeModel model;
    private static long lineNo;
    private static long columnNo;
    private int numberOfErrors;
    private int numberOfWarnings;
    private int numberOfNotes;
    public UndoManager m_undoManager = new UndoManager();
    public static int index = 0;
    public Thread t2;
    private String java_home;
    private UIManager.LookAndFeelInfo[] looks;
    private int itmCount;
    private JFileChooser chooser;
    private boolean dontSave;
    private final JPopupMenu jpm;
    private final JMenuItem jMenuItm[] = new JMenuItem[15];
    private final JSeparator jSeparat1;
    private final JSeparator jSeparat2;
    private final JSeparator jSeparat3;
    private final JSeparator jSeparat4;
    private final JSeparator jSeparat5;
    private final ImageIcon cutIcon;
    private final ImageIcon copyIcon;
    private final ImageIcon redoIcon;
    private final ImageIcon colorIcon;
    private final ImageIcon gotoIcon;
    private final ImageIcon findIcon;
    private final ImageIcon compileIcon;
    private final ImageIcon saveIcon;
    private final ImageIcon deleteIcon;
    private final ImageIcon pasteIcon;
    private final ImageIcon runIcon;
    private final ImageIcon undoIcon;
    private final ImageIcon replaceIcon;
    private final ImageIcon selallIcon;
    private final ImageIcon com_runIcon;
    FileSystemView fsv = FileSystemView.getFileSystemView();
    private final JPopupMenu jpm1;
    private final JMenuItem jPMenuItm[] = new JMenuItem[6];
    private final JSeparator jPSeparat1;
    private final JSeparator jPSeparat2;
    private static Icon CLOSE_TAB_ICON;
    static private int tabCount = 1;
    static private int countButton = 0;
    private static Icon PAGE_ICON;
    private static ImageIcon CLOSE_TAB_ICON_ROLEOVER;
    private static final List<UndoManager> undoMgr = new ArrayList<>();
    private static final List<JTextPane> paneAreas = new ArrayList<>();
    private static final List<JTextArea> textAreas = new ArrayList<>();
    private static final List<JButton> buttons = new ArrayList<>();
    private static final List<JLabel> lblTitles = new ArrayList<>();
    private static final List<File> files = new ArrayList<>();
    private static final List<File> fileHistory = new ArrayList<>();
    private static final List<StringBuffer> classNames = new ArrayList<>();
    private static final List<StringBuffer> packNames = new ArrayList<>();
    private static final List<StringBuffer> packClassNames = new ArrayList<>();
    private static final List<URL> htmlpages = new LinkedList<>();
    private static final Map<DefaultMutableTreeNode, String> filesDitercory = new HashMap<>();
    private String fileDir;
    //private File file;
    private String fileName;
    private String fileExtenssion;
    //public static String className = null;
    //public static String packName = null;
    private File curDir = new File(System.getProperty("user.dir"));
    private File currentFile;
    private StringBuffer currentClassName;
    private StringBuffer currentPackName;
    public static StringBuffer currentPackClassName;
    private boolean dontRun;
    private String pack;
    private int newFileCounter = 1;
    ArrayList<String> suggest = new ArrayList<>();
    private final JPopupMenu jpmTree;
    private final JMenuItem jMenuItmTree[] = new JMenuItem[6];
    private final JSeparator jPSeparatTree1;
    private int currentIndexOfSpace = 0, previousIndexOfSpace = 0;

    static {

        DataOutputStream dou = null;
        try {
            String path;
            dou = new DataOutputStream(new FileOutputStream("LoadingMsg.txt"));
            // System.setOut(new PrintStream(dou));
            //System.setProperty("java.home",System.getProperty("java.class.path").substring(0, System.getProperty("java.class.path").lastIndexOf("Asterix"))+"jre7");
//System.setProperty("java.home",System.getProperty("java.class.path").substring(0, System.getProperty("java.class.path").lastIndexOf("\\"))+"\\lib;"+System.getProperty("java.home")+";");
            //  System.setProperty("java.home","C:\\Program Files\\Java\\jdk1.7.0_21\\bin");

            if (new File(path = System.getProperty("java.class.path").substring(0, System.getProperty("java.class.path").lastIndexOf("Asterix")) + "jre7").isDirectory()) {
                path = System.getProperty("java.class.path");
                System.setProperty("java.home",
                        path = System.getProperty("java.class.path").substring(0, System.getProperty("java.class.path").lastIndexOf("Asterix")) + "jre7");
                //System.out.println(path);
                setOK = true;
                //  System.out.println("\nAll paths \n\njava.home =" + (path = System.getProperty("java.home")));
                // System.out.println("\njava.class.path =" + (path = System.getProperty("java.class.path")));
                //System.out.println("\nuser.home =" + (path = System.getProperty("user.home")));
                //System.out.println("\njava.library.path =" + (path = System.getProperty("java.library.path")));
                //System.out.println("\njava.ext.dirs =" + (path = System.getProperty("java.ext.dirs")));


            } else {
                //System.out.println("\nPaths \n\njava.home " + (path = System.getProperty("java.home")));
                if (path.substring(path.length() - 3, path.length()).equalsIgnoreCase("jre")) {
                    setOK = true;
                } else {
                    setOK = false;
                }
                //System.out.println("setOk =" + setOK);
            }
            String s = System.getProperty("java.version");
            if (s.compareTo("1.7") < 0) {
                System.err.println((new StringBuilder()).append("You are running Java version ").append(s).append('.').toString());
                System.err.println("Asterix IDE requires Java 1.7 or later.");
                System.exit(1);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dou.close();
            } catch (IOException ex) {
                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        new StartSplaceScreen();
    }
    private final JSeparator jSeparat6;
    private int htmlPageCounter;
    private boolean isMorePages;
    private final ImageIcon viewIcon;
    private final JSeparator jPSeparatTree2;
    private FileSystemView fileSystemView;
    private Desktop desktop;
    private final JPopupMenu jpm2;
    private final JMenuItem jPMenu2Itm2;
    private StringBuilder errorMsg = new StringBuilder();
    private ArrayList<Long> lineNoList;
    private final JSeparator jPSeparatTree3;
    private String language = "java";
    private static boolean success;
    private int totalFileCount = 0;

    /**
     * Creates new form Editor
     */
    @SuppressWarnings("CallToThreadStartDuringObjectConstruction")
    public Editor() {

        initComponents();

//        jInternalFrame3.setLayer(1);

//         jInternalFrame3.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("javatoken.txt")));

            String s;
            while ((s = br.readLine()) != null) {
                //String s1[] = s.split(" ");
                //for (int i = 0; i < s1.length; i++) {
                suggest.add(s);//suggest.add(s1[i]);
                //  System.out.println(">" + s);
                //}
            }


        } catch (Exception e) {
            System.out.println("error in reading file javatoken.txt");
        }

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
          //  UIManager.put("background", Color.lightGray);
            //customizeNimbusLaF();
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.updateComponentTreeUI(Editor.this);
        UIManager.put("nimbusSelectionBackground",
                new Color(51, 153, 255));

        redirectSystemStreams();

        String s, s1;
        s = System.getProperty("user.name");
        s1 = System.getProperty("user.dir");
        //jEditorPane1.setText("Dear <font color=red>" + s + "</font>,<br><br>Welcome back in Asterix IDE<br><br>"
        //      + "<font color=green>Your current working directory:"
        //    + "</font><br><hr>" + s1 + "<hr>");

        jLabel15.setText("Dear " + s + ", Welcome back in Asterix IDE.");
        s = System.getProperty("java.version");
        jLabel3.setText("Java Version: " + s + " ");

        CLOSE_TAB_ICON = new ImageIcon(getClass().getResource("/asterix/image/close2.png"));
        CLOSE_TAB_ICON_ROLEOVER = new ImageIcon(getClass().getResource("/asterix/image/close_1.png"));
        PAGE_ICON = null;
        //jDesktopPane2.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);                


        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);

        jPanel4.setVisible(false);//find 
        jPanel7.setVisible(false);//find 
        jPanel8.setVisible(false);
        jPanel2.setVisible(false);

//        jLabel1.setVisible(false);
        //      jTextField1.setVisible(false);

        jTextArea1.setFont(new java.awt.Font("Tahoma", Font.PLAIN, jSlider1.getValue()));
        jTextPane1.setFont(new java.awt.Font("Tahoma", Font.PLAIN, jSlider1.getValue()));

        paneAreas.add(jTextPane1);
        textAreas.add(jTextArea1);

        /*        AutoSuggestor autoSuggestor = new AutoSuggestor(jTextPane1, this, suggest, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
         @Override
         boolean wordTyped(String typedWord) {
         // System.out.println(typedWord);
         return super.wordTyped(typedWord);//checks for a match in dictionary and returns true or false if found or not
         }
         };
         */
        File ff = new File("");
        files.add(ff);
        StringBuffer ss = new StringBuffer("");
        classNames.add(ss);
        StringBuffer ss1 = new StringBuffer("");
        packNames.add(ss1);
        StringBuffer ss2 = new StringBuffer("");
        packClassNames.add(ss2);
        /*
         System.out.println("files :" + ff.hashCode());
         System.out.println("ss :" + ss.hashCode());
         System.out.println("ss1 :" + ss1.hashCode());
         System.out.println();
         */


        

        FlowLayout f = new FlowLayout(FlowLayout.CENTER, 0, 2);
        /*        JPanel pnlTab1 = new JPanel(f);
         pnlTab1.setOpaque(false);
         JLabel lblTitle1 = new JLabel("Home");
         lblTitle1.setIcon(new ImageIcon(getClass().getResource("/asterix/image/home.png")));
         pnlTab1.add(lblTitle1);
         pnlTab1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

         JPanel pnlTab2 = new JPanel(f);
         pnlTab2.setOpaque(false);
         JLabel lblTitle2 = new JLabel("Extra");
         lblTitle2.setIcon(new ImageIcon(getClass().getResource("/asterix/image/extra.png")));
         pnlTab2.add(lblTitle2);
         pnlTab2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
         */
        JPanel pnlTab = new JPanel(f);
        pnlTab.setOpaque(false);
        Icon icon = PAGE_ICON;
        lblTitle = new JLabel("Tab " + tabCount + " ");

        lblTitles.add(lblTitle);
        //   System.out.println("title= " + lblTitle.hashCode());


        lblTitle.setIcon(icon);

        lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(null);
        lblTitles.get(jTabbedPane2.getSelectedIndex()).setText("NoName" + newFileCounter++);

        JButton btnClose1 = new JButton();
        //  System.out.println("btn= " + btnClose.hashCode());

        buttons.add(btnClose1);//  adding just fore fake no use but help in counting tab and removig it.

        /*        
         btnClose.setOpaque(false);
         btnClose.setIcon(CLOSE_TAB_ICON);
         btnClose.setRolloverEnabled(true);
         btnClose.setRolloverIcon(CLOSE_TAB_ICON_ROLEOVER);
         btnClose.setRolloverSelectedIcon(CLOSE_TAB_ICON_ROLEOVER);
         btnClose.setBorder(null);
         btnClose.setFocusable(false);
         */ pnlTab.add(lblTitle);
        //pnlTab.add(btnClose);
        pnlTab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        jTabbedPane2.setTabComponentAt(0, pnlTab);

        jTabbedPane2.addTab(null, new ImageIcon(getClass().getResource("/asterix/image/addnew.png")), null, "Click to create New Tab");

        cutIcon = new ImageIcon(getClass().getResource("/asterix/image/cut.png"));
        copyIcon = new ImageIcon(getClass().getResource("/asterix/image/copy.png"));
        pasteIcon = new ImageIcon(getClass().getResource("/asterix/image/paste.png"));
        deleteIcon = new ImageIcon(getClass().getResource("/asterix/image/del.png"));
        selallIcon = new ImageIcon(getClass().getResource("/asterix/image/selall.png"));
        findIcon = new ImageIcon(getClass().getResource("/asterix/image/find.png"));
        gotoIcon = new ImageIcon(getClass().getResource("/asterix/image/goto.png"));
        replaceIcon = new ImageIcon(getClass().getResource("/asterix/image/replace.png"));
        colorIcon = new ImageIcon(getClass().getResource("/asterix/image/color.png"));
        undoIcon = new ImageIcon(getClass().getResource("/asterix/image/undo.png"));
        redoIcon = new ImageIcon(getClass().getResource("/asterix/image/redo.png"));
        saveIcon = new ImageIcon(getClass().getResource("/asterix/image/save.png"));
        compileIcon = new ImageIcon(getClass().getResource("/asterix/image/compile.png"));
        runIcon = new ImageIcon(getClass().getResource("/asterix/image/run.png"));
        com_runIcon = new ImageIcon(getClass().getResource("/asterix/image/com_run.png"));
        viewIcon = new ImageIcon(getClass().getResource("/asterix/image/view.png"));


        jpm = new JPopupMenu();

        jMenuItm[0] = new JMenuItem("Cut", cutIcon);

        jMenuItm[1] = new JMenuItem("Copy", copyIcon);
        jMenuItm[2] = new JMenuItem("Paste", pasteIcon);
        jMenuItm[3] = new JMenuItem("Delete", deleteIcon);
        jMenuItm[4] = new JMenuItem("Select All", selallIcon);
        jMenuItm[5] = new JMenuItem("Save", saveIcon);
        jMenuItm[6] = new JMenuItem("Find", findIcon);
        jMenuItm[7] = new JMenuItem("Replace", replaceIcon);
        jMenuItm[8] = new JMenuItem("Goto", gotoIcon);
        jMenuItm[9] = new JMenuItem("Color", colorIcon);
        jMenuItm[10] = new JMenuItem("Undo", undoIcon);
        jMenuItm[11] = new JMenuItem("Redo", redoIcon);
        jMenuItm[12] = new JMenuItem("Compile", compileIcon);
        jMenuItm[13] = new JMenuItem("Run", runIcon);
        jMenuItm[14] = new JMenuItem("Compile & Run", com_runIcon);


        jSeparat1 = new JSeparator();
        jSeparat2 = new JSeparator();
        jSeparat3 = new JSeparator();
        jSeparat4 = new JSeparator();
        jSeparat5 = new JSeparator();
        jSeparat6 = new JSeparator();

        int i = 0;
        for (; i < 5; i++) {
            jpm.add(jMenuItm[i]);
        }
        jpm.add(jSeparat1);

        jpm.add(jMenuItm[i]);
        jpm.add(jSeparat2);

        jpm.add(jMenuItm[++i]);
        jpm.add(jMenuItm[++i]);
        jpm.add(jMenuItm[++i]);
        jpm.add(jSeparat3);

        jpm.add(jMenuItm[++i]);
        jpm.add(jSeparat4);

        jpm.add(jMenuItm[++i]);
        jpm.add(jMenuItm[++i]);

        jpm.add(jSeparat6);

        jpm.add(jSeparat5);

        jpm.add(jMenuItm[++i]);
        jpm.add(jMenuItm[++i]);
        jpm.add(jMenuItm[++i]);

        jpm2 = new JPopupMenu();
        jPMenu2Itm2 = new JMenuItem("Close All", CLOSE_TAB_ICON_ROLEOVER);

        jpm2.add(jPMenu2Itm2);

        jPMenu2Itm2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                countButton = buttons.size() - 1;
                //  System.out.println(">"+countButton);
                for (; countButton > 0; countButton--) {
                    tabCount--;
                    //   System.out.println(" close btn "+buttons.get(countButton).hashCode());
                    buttons.remove(countButton);
                    paneAreas.remove(paneAreas.get(countButton));
                    textAreas.remove(textAreas.get(countButton));
                    lblTitles.remove(lblTitles.get(countButton));

                    undoMgr.remove(countButton);

                    files.remove(files.get(countButton));
                    classNames.remove(classNames.get(countButton));
                    packNames.remove(packNames.get(countButton));
                    packClassNames.remove(packClassNames.get(countButton));

                    jTabbedPane2.remove(tabCount);
                    jTabbedPane2.setSelectedComponent(jTabbedPane2.getComponentAt(0));

                    jTabbedPane2.setSelectedIndex(0);
                }
            }
        });






        jpm1 = new JPopupMenu();

        jPMenuItm[0] = new JMenuItem("Cut", cutIcon);
        jPMenuItm[1] = new JMenuItem("Copy", copyIcon);
        jPMenuItm[2] = new JMenuItem("Paste", pasteIcon);
        jPMenuItm[3] = new JMenuItem("Delete", deleteIcon);
        jPMenuItm[4] = new JMenuItem("Select All", selallIcon);
        jPMenuItm[5] = new JMenuItem("Save", saveIcon);

        jPSeparat1 = new JSeparator();
        jPSeparat2 = new JSeparator();

        if (!jEditorPane1.isEditable()) {

            jPMenuItm[0].setEnabled(false);
            jPMenuItm[3].setEnabled(false);
        }

        jpm1.add(jPMenuItm[0]);
        jpm1.add(jPMenuItm[1]);
        jpm1.add(jPMenuItm[2]);
        jpm1.add(jPMenuItm[3]);
        jpm1.add(jPSeparat1);
        jpm1.add(jPMenuItm[4]);
        jpm1.add(jPSeparat2);
        jpm1.add(jPMenuItm[5]);


        jpmTree = new JPopupMenu();
        jMenuItmTree[0] = new JMenuItem("Open in current Tab");

        jMenuItmTree[1] = new JMenuItem("Open in new Tab");
        jPSeparatTree2 = new JSeparator();
        jMenuItmTree[2] = new JMenuItem("Open File");
        jMenuItmTree[3] = new JMenuItem("Open File Location");
        jPSeparatTree1 = new JSeparator();
        jMenuItmTree[5] = new JMenuItem("Copy File Path");
        jPSeparatTree3 = new JSeparator();
        jMenuItmTree[4] = new JMenuItem("Properties");

        jpmTree.add(jMenuItmTree[0]);
        jpmTree.add(jMenuItmTree[1]);
        jpmTree.add(jPSeparatTree1);
        jpmTree.add(jMenuItmTree[2]);
        jpmTree.add(jMenuItmTree[3]);
        jpmTree.add(jPSeparatTree2);
        jpmTree.add(jMenuItmTree[5]);
        jpmTree.add(jPSeparatTree3);
        jpmTree.add(jMenuItmTree[4]);




        try {

            doc = paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument();

            if (language.equals("java")) {
                textPlainOrHtml = "package com;\n\n"
                        + "public class Demo {\n\n"
                        + "\tpublic static void main(String[] args) {\n\n"
                        + "\t	System.out.println(\"Hello World!\");\n\n"
                        + "\t}\n\n"
                        + "}";
            } else if (language.equals("c")) {
                textPlainOrHtml = "#include<iostream>\n\n"
                        + "using namespace std;\n\n"
                        + "int main() {\n\n"
                        + "\tcout<<\"Hello World!\";\n\n" + "return 0;\n\n" + "}";
            } else if (language.equals("html")) {
                textPlainOrHtml = "<html>\n\n"
                        + "<head>\n\n\t<title>\tDemo\t</title>\n\n</head>\n\n"
                        + "<body>\n\n"
                        + "\tHello World!\n\n"
                        + "</body>\n\n"
                        + "<html>";
            }
            //paneAreas.get(jTabbedPane2.getSelectedIndex()).setText(textPlainOrHtml);

            textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());

            fileName = null;


            /*            File[] roots = File.listRoots();
             dirSelBox.addItem(".");
             for (File r : roots) {
             dirSelBox.addItem(r.getPath());
             }
             */



            textAreas.get(jTabbedPane2.getSelectedIndex()).setTabSize(5);


            clipUndoBtn.setEnabled(false);
            clipRedoBtn.setEnabled(false);
            jMenuItem11.setEnabled(false);
            jMenuItem12.setEnabled(false);

            /*
             clipCutBtn.setEnabled(false);
             clipCopyBtn.setEnabled(false);
             clipDelBtn.setEnabled(false);
             printBtn.setEnabled(false);
             jMenuItem4.setEnabled(false);
             jMenuItem7.setEnabled(false);
             jMenuItem10.setEnabled(false);
             jMenuItem13.setEnabled(false);
             jMenuItem15.setEnabled(false);
             jMenuItem19.setEnabled(false);
             */

            jMenuItem18.setEnabled(false);


            jEditorPane1.addHyperlinkListener(new HyperlinkListener() {
                @Override
                public void hyperlinkUpdate(HyperlinkEvent e) {
                    try {
                        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            try {
                                jEditorPane1.setPage(e.getURL());
                            } catch (IOException ex) {
                                //jEditorPane1.setText(""+e.getDescription());
                                String desc = e.getDescription();
                                int currentPosition = 0;
                                if (desc.startsWith("goToPosition(")) {
                                    currentPosition = Integer.parseInt(desc.substring(desc.indexOf("(") + 1, desc.indexOf(")")));
                                }

                                paneAreas.get(jTabbedPane2.getSelectedIndex()).requestFocusInWindow();

                                paneAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(currentPosition);

                            }
                            String fnd = "" + e.getURL().getFile().toString();
                            jLabel10.setText(fnd.substring(fnd.lastIndexOf("/") + 1, fnd.length()));
                            try {
                                htmlpages.add(e.getURL());
                            } catch (Exception ex) {
                                jEditorPane1.setText("<font color=red>Can't Open this page.</font><br><br>");
                            }
                            htmlPageCounter++;
                            isMorePages = true;
                        }
                        jEditorPane1.setContentType("text/html");
                        //jEditorPane1.removeAll();
                    } catch (Exception ee) {
                    }
                }
            });


            jScrollPane4.setRowHeaderView(textAreas.get(jTabbedPane2.getSelectedIndex()));

            jScrollPane2.setColumnHeaderView(jPanel15);

            this.setDefaultCloseOperation(
                    WindowConstants.DO_NOTHING_ON_CLOSE);

            // jScrollPane2.setColumnHeaderView(jPanel15);

            //Editor.setDefaultLookAndFeelDecorated(true);
            //Toolkit.getDefaultToolkit().getImage("favicon.png"));
            Image image = new ImageIcon(getClass().getResource("/asterix/image/favicon.png")).getImage();
            setIconImage(image);//new ImageIcon("/playerApp/Image/play.png").getImage()
            Icon icon1 = new ImageIcon(getClass().getResource("/asterix/image/frameicon1.png"));

            jInternalFrame2.setFrameIcon(icon1);
            jInternalFrame3.setFrameIcon(icon1);
            jInternalFrame4.setFrameIcon(icon1);
//            jInternalFrame5.setFrameIcon(icon1);

        } catch (Throwable texp) {
            System.err.println("Please use Java 1.7.0 or above version for All Component Access. "
                    + "For example, Directory Component may not work properly... etc.");
        }



        try {
            DefaultMutableTreeNode root2 = new DefaultMutableTreeNode(curDir);

            model = new DefaultTreeModel(root2);


            jTree1.setModel(model);
            // jTree1.setRootVisible(true);
            jTree1.setRootVisible(false);
            jTree1.setShowsRootHandles(true);

            DefaultMutableTreeNode root1 = (DefaultMutableTreeNode) model.getRoot();
            root1.removeAllChildren();
            model.reload();

            File rootFile1 = (File) root1.getUserObject();

            jLabel13.setText(rootFile1.getName());

            new treeUpdates().addFiles(rootFile1, model, root1);
            jProgressBar1.setValue(0);
            jTree1.expandPath(new TreePath(root1));
        } catch (Throwable texp) {
            System.err.println("Can't read Basic Directory..." + texp);
            System.err.println("Please use Java 1.7.0 or above version for All Component Access. "
                    + "For example, Directory Component may not work properly... etc.");
        }


        setTitle("Asterix IDE 1.5.5");

        
        jProgressBar1.setUI(new BasicProgressBarUI() {

        Rectangle r = new Rectangle();

        @Override
        protected void paintIndeterminate(Graphics g, JComponent c) {
            
                        Graphics2D g2d = (Graphics2D) g;
           // g2d.setClip(r.x-20, r.y, r.width, r.height);
                        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            
            r = getBox(r);
            g2d.setColor(new Color(0,204,0));
            g2d.fill3DRect(r.x, r.y, r.width, r.height,true);
           try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
               // Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }
    });

        jProgressBar1.setIndeterminate(false);
        
        windowLookAndFeel();
        setExtendedState(Editor.MAXIMIZED_BOTH);
        /*
         PlasticLookAndFeel laf = new PlasticLookAndFeel();         
         PlasticLookAndFeel.setCurrentTheme(new ExperienceRoyale());//DefaultMetalTheme()
         */
        setVisible(true);

        jMenuItmTree[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Object object = jTree1.getLastSelectedPathComponent();
                if (object instanceof DefaultMutableTreeNode) {

                    // Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                    //   File file1 = (File) userObject;
                    File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));

                    if (file1.isFile()) {//userObject instanceof File

                        jEditorPane1.setText("");
                        // System.out.println("\n\nfile : " + file1);
                        String fn = file1.getName();
                        fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());
                        if (fileExtenssion.equalsIgnoreCase("html") || fileExtenssion.equalsIgnoreCase("htm")) {
                         /*   try {
                                jEditorPane1.setPage(file1.toURI().toURL());
                                jLabel10.setText("" + file1.getName());

                                jButton10.setVisible(true);
                                jButton11.setVisible(true);
                                jLabel10.setVisible(true);

                                try {
                                    htmlpages.add(file1.toURI().toURL());
                                } catch (Exception ex) {
                                    System.out.println("Can't Open this page. \n\n");
                                }
                                htmlPageCounter++;
                                //     System.out.println("htmlPageCounter" + htmlPageCounter + "\n\n" + file1.toURI().toURL()+ "\n\n size " + htmlpages.size());
                                //                              jEditorPane1.setText("");        


                                //System.out.println("size "+htmlpages.size()+"htmlPageCounter "+htmlPageCounter);
                                //jPanel7.setVisible(false);
                                // jPanel6.setVisible(true);
                            } catch (IOException ex) {
                                System.out.println("Can't Open this page.");
                            }
                            */ 
                        } //else {
                            if (!files.contains((File) file1)) {

                                //open in curren tab
                                openFile(file1);
                                jButton10.setVisible(false);
                                jButton11.setVisible(false);
                                jLabel10.setVisible(false);
                            } else {
                                jTabbedPane2.setSelectedIndex(files.indexOf(file1));
                            }
                            //jPanel6.setVisible(false);
                            //jPanel7.setVisible(true);
                        //}
                        //file1.getName().substring(0, file1.getName().indexOf("."));
                    }
                }

            }
        });

        jMenuItmTree[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object object = jTree1.getLastSelectedPathComponent();
                if (object instanceof DefaultMutableTreeNode) {

                    //   Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                    //  File file1 = (File) userObject;
                    File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));

                    if (file1.isFile()) {//userObject instanceof File

                        StringBuilder sb = new StringBuilder();

                        sb.append("<font color=red>File Properties:<hr><br>");
                        String fn = file1.getName();

                        sb.append("<br><font color=red>Name:</font><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(fn);

                        sb.append("<br><font color=red>Type:</font><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(fn.substring(fn.lastIndexOf(".") + 1, fn.length()));

                        String path = file1.getAbsolutePath();
                        String exactPath = path.substring(0, path.indexOf(file1.getName()));
                        if (exactPath.indexOf(".\\") != -1) {
                            exactPath = exactPath.substring(0, exactPath.length() - 2);
                        }

                        sb.append("<br><font color=red>Location:</font><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(exactPath);
                        int size;
                        try {
                            DataInputStream din = new DataInputStream(new BufferedInputStream(new FileInputStream(file1)));
                            size = din.available();
                        } catch (Exception ee) {
                            size = 0;
                        }

                        sb.append("<br><font color=red>Size:</font><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(size).append(" bytes");
                        sb.append("<br><font color=red>Attributes:</font><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;").append(file1.canRead() ? "Read/" : "").append(file1.canWrite() ? "Write" : "");
                        sb.append("<br><font color=red>Owner:</font><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(System.getProperty("user.name"));

                        jEditorPane1.setText("" + sb + "<br><br>");


                    }
                }

            }
        });
        jMenuItmTree[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Object object = jTree1.getLastSelectedPathComponent();
                if (object instanceof DefaultMutableTreeNode) {

                    //  Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                    //  File file1 = (File) userObject;
                    File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));
                    if (file1.isFile()) {//userObject instanceof File

                        try {
                            Runtime.getRuntime().exec("cmd /c start " + file1);
                        } catch (IOException ex) {
                            System.out.println("Error: Can't Execute Command");
                        }

                    }
                }

            }
        });

        jMenuItmTree[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                Object object = jTree1.getLastSelectedPathComponent();
                if (object instanceof DefaultMutableTreeNode) {

                    //Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                    // File file1 = (File) userObject;
                    File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));

                    String path = file1.getAbsolutePath();
                    String exactPath = path.substring(0, path.indexOf(file1.getName()));
                    if (exactPath.indexOf(".\\") != -1) {
                        exactPath = exactPath.substring(0, exactPath.length() - 2);
                    }

                    if (file1.isFile()) {//userObject instanceof File

                        try {
                            Runtime.getRuntime().exec("cmd /c start " + "\"\" \"" + exactPath + "\"");
                        } catch (IOException ex) {
                            System.out.println("Error: Can't Execute Command");
                        }

                    }
                }

            }
        });

        jMenuItmTree[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                Object object = jTree1.getLastSelectedPathComponent();
                if (object instanceof DefaultMutableTreeNode) {

                    //Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                    //File file1 = (File) userObject;
                    File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));

                    if (file1.isFile()) {//userObject instanceof File

                        jEditorPane1.setText("");
                        // System.out.println("\n\nfile : " + file1);
                        String fn = file1.getName();
                        fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());
                        if (fileExtenssion.equalsIgnoreCase("html") || fileExtenssion.equalsIgnoreCase("htm")) {
                           /* try {
                                jEditorPane1.setPage(file1.toURI().toURL());
                                jLabel10.setText("" + file1.getName());
                                jButton10.setVisible(true);
                                jButton11.setVisible(true);
                                jLabel10.setVisible(true);

                                try {
                                    htmlpages.add(file1.toURI().toURL());
                                } catch (Exception ex) {
                                    System.out.println("Can't Open this page. \n\n");
                                }
                                htmlPageCounter++;
                                //     System.out.println("htmlPageCounter" + htmlPageCounter + "\n\n" + file1.toURI().toURL()+ "\n\n size " + htmlpages.size());
                                //                              jEditorPane1.setText("");        


                                //System.out.println("size "+htmlpages.size()+"htmlPageCounter "+htmlPageCounter);
                                //jPanel7.setVisible(false);
                                // jPanel6.setVisible(true);
                            } catch (IOException ex) {
                                System.out.println("Can't Open this page.");
                            }
                            */ 
                        }// else {
                            if (!files.contains((File) file1)) {
                                if (files.get(jTabbedPane2.getSelectedIndex()).getName().length() != 0) {

                                    addClosableTab(jTabbedPane2, "Tab " + tabCount + " ");
                                }
                                openFile(file1);

                                jButton10.setVisible(false);
                                jButton11.setVisible(false);
                                jLabel10.setVisible(false);
                                //jPanel6.setVisible(false);
                                //jPanel7.setVisible(true);
                            } else {
                                jTabbedPane2.setSelectedIndex(files.indexOf(file1));
                            }
                        //}
                        //file1.getName().substring(0, file1.getName().indexOf("."));
                    }
                }
            }
        });

        jMenuItmTree[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object object = jTree1.getLastSelectedPathComponent();
                if (object instanceof DefaultMutableTreeNode) {

                    //   Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                    //  File file1 = (File) userObject;
                    File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));

                    if (file1.isFile()) {//userObject instanceof File

                        StringSelection ss = new StringSelection(file1.getAbsolutePath());
                        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                    }
                }
            }
        });




        jMenuItm[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem4ActionPerformed(e);
            }
        });
        jMenuItm[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem7ActionPerformed(e);
            }
        });
        jMenuItm[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem9ActionPerformed(e);
            }
        });
        jMenuItm[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem10ActionPerformed(e);
            }
        });
        jMenuItm[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem13ActionPerformed(e);
            }
        });
        jMenuItm[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem2ActionPerformed(e);
            }
        });
        jMenuItm[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem22ActionPerformed(e);
            }
        });
        jMenuItm[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem24ActionPerformed(e);
            }
        });
        jMenuItm[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem25ActionPerformed(e);
            }
        });
        jMenuItm[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem8ActionPerformed(e);
            }
        });
        jMenuItm[10].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem11ActionPerformed(e);
            }
        });
        jMenuItm[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem12ActionPerformed(e);
            }
        });
        jMenuItm[12].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem17ActionPerformed(e);
            }
        });
        jMenuItm[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem16ActionPerformed(e);
            }
        });
        jMenuItm[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jMenuItem20ActionPerformed(e);
            }
        });

        jPMenuItm[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPMenuItm2ActionPerformed(e);
            }

            private void jPMenuItm2ActionPerformed(ActionEvent e) {
                if (jEditorPane1.getSelectedText() == null) {
                    StringSelection ss = new StringSelection(jEditorPane1.getText());
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                } else {
                    StringSelection ss = new StringSelection(jEditorPane1.getSelectedText());
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                }
            }
        });
        jPMenuItm[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPMenuItm3ActionPerformed(e);
            }

            private void jPMenuItm3ActionPerformed(ActionEvent e) {
                Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                String text = null;
                if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    try {
                        text = (String) t.getTransferData(DataFlavor.stringFlavor);

                        int caretPos = jEditorPane1.getCaretPosition();
                        if (caretPos < 0) {
                            caretPos = 0;
                        }
                        jEditorPane1.getDocument().insertString(caretPos, text, null);
                    } catch (UnsupportedFlavorException | IOException | BadLocationException ex) {
                        Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        jPMenuItm[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPMenuItm5ActionPerformed(e);
            }

            private void jPMenuItm5ActionPerformed(ActionEvent e) {
                jEditorPane1.selectAll();
            }
        });

        jPMenuItm[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPMenuItm1ActionPerformed(e);
            }

            private void jPMenuItm1ActionPerformed(ActionEvent e) {
                if (jEditorPane1.getSelectedText() == null) {
                    StringSelection ss = new StringSelection(jEditorPane1.getText());
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

                    jEditorPane1.setText("");
                } else {
                    StringSelection ss = new StringSelection(jEditorPane1.getSelectedText());
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                    jEditorPane1.replaceSelection("");
                }
            }
        });

        jPMenuItm[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPMenuItm4ActionPerformed(e);
            }

            private void jPMenuItm4ActionPerformed(ActionEvent e) {
                if (jEditorPane1.getSelectedText() == null) {
                    jEditorPane1.setText("");
                } else {
                    jEditorPane1.replaceSelection("");
                }
            }
        });

        jPMenuItm[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jPMenuItm6ActionPerformed(e);
            }

            private void jPMenuItm6ActionPerformed(ActionEvent e) {
                JFileChooser chooser1 = new JFileChooser();

                chooser1.setPreferredSize(new Dimension(600, 500));
                int r;
                File f;
                boolean acceptable = false;
                do {
                    //      chooser1 = new JFileChooser();
                    r = chooser1.showSaveDialog(Editor.this);
                    f = chooser1.getSelectedFile();
                    if (r == JFileChooser.APPROVE_OPTION) {
                        f = chooser1.getSelectedFile();
                        if (f.exists()) {
                            int confirm = JOptionPane.showOptionDialog(Editor.this,
                                    "File Exists! Do you want to overrite it ?", "Exit Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null, null, null);
                            if (confirm == JOptionPane.YES_OPTION) {
                                acceptable = true;
                            }
                        } else {
                            acceptable = true;
                        }
                    } else {
                        acceptable = true;
                    }
                } while (!acceptable);

                if (r == JFileChooser.APPROVE_OPTION) {
                    FileWriter fw;
                    try {
                        fw = new FileWriter(f);
                        fw.write(jEditorPane1.getText());
                        fw.close();
                        jEditorPane1.setText("<font color=green>File Saved to: <br>" + f.getAbsolutePath() + "</font><br><br>");
                    } catch (IOException ex) {
                        System.out.println("Error. Can't write Text");
                    }
                }
            }
        });

        if (!setOK) {
            FileOutputStream fo;
            try {
                File f1 = new File("Java_Home.txt");
                if (!f1.exists()) {
                    fo = new FileOutputStream("Java_Home.txt");
                    fo.close();
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found.");
            } catch (IOException ex) {
                System.out.println("File Not Found. Or Can't create/read Java_Home.txt");
            }
        }


        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                jTree1.setSelectionRow(0);
                int selRow = jTree1.getRowForLocation(e.getX(), e.getY());
//                TreePath selPath = jTree1.getPathForLocation(e.getX(), e.getY());

                if (selRow != -1) {
                    jTree1.setSelectionRow(selRow);

                    Object object = jTree1.getLastSelectedPathComponent();
                    if (object != null) {
                        // Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                        //File file1 =filesDitercory.get(userObject);

                        //  File file1 = (File) userObject;
                        //if (e.getClickCount() == 1) {
                        // System.out.println(">>>"+filesDitercory.get(object));

                        File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));
                        String path = file1.getAbsolutePath();
                        String exactPath = path.substring(0, path.indexOf(file1.getName()));
                        if (exactPath.indexOf(".\\") != -1) {
                            exactPath = exactPath.substring(0, exactPath.length() - 2);
                        }

                        jLabel2.setText(exactPath + "" + file1.getName());

                        int s = e.getButton();
                        if (s == MouseEvent.BUTTON3 && file1.isFile()) {
                            jpmTree.show(e.getComponent(), e.getX(), e.getY());
                            jTree1.setSelectionRow(selRow);
                        }

                        //              System.out.println("Single click\n");
                        //} else
                        if (e.getClickCount() == 2) {

                            if (object instanceof DefaultMutableTreeNode) {

                                if (file1.isFile()) {//userObject instanceof File

                                    jEditorPane1.setText("");
                                    // System.out.println("\n\nfile : " + file1);
                                    String fn = file1.getName();
                                    fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());
                                    if (fileExtenssion.equalsIgnoreCase("html") || fileExtenssion.equalsIgnoreCase("htm")) {
                                      /*  try {
                                            jEditorPane1.setPage(file1.toURI().toURL());
                                            //  SimpleWebBrowserExample.main(file1 + "");
                                            jButton10.setVisible(true);
                                            jButton11.setVisible(true);
                                            jLabel10.setVisible(true);

                                            jLabel10.setText("" + file1.getName());
                                            try {
                                                htmlpages.add(file1.toURI().toURL());
                                            } catch (Exception ex) {
                                                System.out.println("Can't Open this page. \n\n");
                                            }
                                            htmlPageCounter++;
                                            //     System.out.println("htmlPageCounter" + htmlPageCounter + "\n\n" + file1.toURI().toURL()+ "\n\n size " + htmlpages.size());
                                            //                              jEditorPane1.setText("");        


                                            //System.out.println("size "+htmlpages.size()+"htmlPageCounter "+htmlPageCounter);
                                            //jPanel7.setVisible(false);
                                            // jPanel6.setVisible(true);
                                        } catch (IOException ex) {
                                            System.out.println("Can't Open this page.");
                                        }
                                        */ 
                                    } //else {

                                        if (!files.contains((File) file1)) {
                                            if (files.get(jTabbedPane2.getSelectedIndex()).getName().length() != 0) {

                                                addClosableTab(jTabbedPane2, "Tab " + tabCount + " ");
                                            }
                                            openFile(file1);

                                            jButton10.setVisible(false);
                                            jButton11.setVisible(false);
                                            jLabel10.setVisible(false);



                                            //if (fileExtenssion.equalsIgnoreCase("java")) {
                                            try {
                                                classOutputFolder = file1.getCanonicalPath().substring(0, file1.getCanonicalPath().indexOf(file1.getName()) - 1);
                                                fileName = file1.getName().substring(0, file1.getName().indexOf("."));

//                                            System.out.println("output folder:"+classOutputFolder+"\t"+fileName);

                                            } catch (IOException ex) {
                                                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            //}
                                            //jPanel6.setVisible(false);
                                            //jPanel7.setVisible(true);
                                        } else {
                                            jTabbedPane2.setSelectedIndex(files.indexOf(file1));
                                        }
                                    }
                                    //file1.getName().substring(0, file1.getName().indexOf("."));
                                }
                           // }
                            //System.out.println("Double click\n");
                        }
                    }
                }
            }
        };
        jTree1.addMouseListener(ml);

        jTree1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    Object object = jTree1.getLastSelectedPathComponent();
                    //                  Object userObject = ((DefaultMutableTreeNode) object).getUserObject();
                    //File file1 = (File) userObject;
                    File file1 = new File(filesDitercory.get((DefaultMutableTreeNode) object));
                    if (file1.isFile()) {

                        jEditorPane1.setText("");
                        // System.out.println("\n\nfile : " + file1);
                        String fn = file1.getName();
                        fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());
                        if (fileExtenssion.equalsIgnoreCase("html") || fileExtenssion.equalsIgnoreCase("htm")) {
                           /* try {
                                jEditorPane1.setPage(file1.toURI().toURL());

                                jButton10.setVisible(true);
                                jButton11.setVisible(true);
                                jLabel10.setVisible(true);

                                jLabel10.setText("" + file1.getName());
                                try {
                                    htmlpages.add(file1.toURI().toURL());
                                } catch (Exception ex) {
                                    System.out.println("Can't Open this page. \n\n");
                                }
                                htmlPageCounter++;
                            } catch (IOException ex) {
                                System.out.println("Can't Open this page.");
                            }
                            */ 
                        } //else {

                            jButton10.setVisible(false);
                            jButton11.setVisible(false);
                            jLabel10.setVisible(false);
                            //     if (fileExtenssion.equalsIgnoreCase("java")) {
                            try {
                                classOutputFolder = file1.getCanonicalPath().substring(0, file1.getCanonicalPath().indexOf(file1.getName()) - 1);
                                fileName = file1.getName().substring(0, file1.getName().indexOf("."));
                            } catch (IOException ex) {
                                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //   }
                            if (!files.contains((File) file1)) {
                                if (files.get(jTabbedPane2.getSelectedIndex()).getName().length() != 0) {

                                    addClosableTab(jTabbedPane2, "Tab " + tabCount + " ");
                                }
                                openFile(file1);
                            } else {
                                jTabbedPane2.setSelectedIndex(files.indexOf(file1));
                            }
                        //}
                    }

                }
            }
        });



// here we add for first only later we add for next when we create new tab

        /*jTextPane1.addFocusListener(new FocusListener() {

         @Override
         public void focusGained(FocusEvent e) {
           
         try {
                    
         textAreas.get(jTabbedPane2.getSelectedIndex()).setFont(new java.awt.Font(jTextPane1.getFont().getFontName(), 0, jTextPane1.getFont().getSize())); // NOI18N
        
         textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
         updateButtons();
         } catch (Exception ex) {
         System.out.println("Error Occured in updating Caret");
         }
         }

         @Override
         public void focusLost(FocusEvent e) {
         //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         }
         });


         */

        jTextPane1.getDocument()
                .addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                try {
                    textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());

                    //    textAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(0);
                    updateButtons();
                } catch (Exception ex) {
                    System.out.println("Error Occured in updating Caret 1");
                }
            }

            @Override
            public void insertUpdate(DocumentEvent de) {

                try {
                    textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                    updateButtons();
                } catch (Exception ex) {
                    System.out.println("Error Occured in updating Caret 2");
                }

            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                try {
                    textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                    updateButtons();
                } catch (Exception ex) {
                    System.out.println("Error Occured in updating Caret 3");
                }
            }
        });

        jTextPane1.addCaretListener(
                new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    //updateButtons();
                } catch (Exception ee) {
                }
                //Get the location in the text
    /*int dot = e.getDot();
                 int mark = e.getMark();
                 if (dot == mark) {  // no selection
                 try {
                 Rectangle caretCoords = jTextPane1.modelToView(dot);
                 //Convert it to view coordinates
                 System.out.println("caret: text position: " + dot +
                 ", view location = [" +
                 caretCoords.x + ", " + caretCoords.y + "]");
                 } catch (BadLocationException ble) {
                 System.out.println("caret: text position: " + dot );
                 }
                 } else if (dot < mark) {
                 System.out.println("selection from: " + dot + " to " + mark );
                 } else {
                 System.out.println("selection from: " + mark + " to " + dot );
                 }
                 */
                /*try {
                 updateButtons();
                 int caretPosition = e.getMark();
                 String lineText = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();

                 String subStr = lineText.substring(0, caretPosition);

                 int lineNo = 1;
                 for (int i = 0; i < subStr.length(); i++) {
                 if (subStr.charAt(i) ==13) {
                 lineNo++;
                 }
                 }

                 jLabel12.setText("Ln " + lineNo);
                 /*                  String text1 = textAreas.get(jTabbedPane2.getSelectedIndex()).getText();
                 System.out.println(lineNo);
                 textAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(text1.indexOf(lineNo));
                 */


                //              int lineNo = subStr.split("\n").length;
                //System.out.println("" + lineNo);
  /*                  Element root = jTextPane1.getDocument().getDefaultRootElement();
                 int lineNum = root.getElementIndex(e.getDot());
                 int colNum = e.getDot() - root.getElement(lineNum).getStartOffset();
                 colNum++;
                 lineNum++;
                 jLabel12.setText("Ln "+lineNum + " | Col " + colNum);
                 */
                /*  String pattern = "" + lineNo;

                 try {
                 Highlighter hilite = textAreas.get(jTabbedPane2.getSelectedIndex()).getHighlighter();

                 hilite.removeAllHighlights();

                 doc1 = textAreas.get(jTabbedPane2.getSelectedIndex()).getDocument();
                 String text = doc1.getText(0, doc1.getLength());
                 int pos = 0;
                 // Search for pattern
                 while ((pos = text.indexOf(pattern, pos)) >= 0) {
                 // Create highlighter using private painter and apply around pattern
                 hilite.addHighlight(pos, pos + pattern.length(), new DefaultHighlighter.DefaultHighlightPainter(new Color(51, 153, 255)));
                 pos += pattern.length();
                 break;
                 }
                 } catch (Exception ee) {
                 System.out.println("Error: Can't Read Data from file");
                 }
                 } catch (Exception ee) {
                 }
                 */
            }
        });

        //for color of text doc...
/*
         jTextPane1.addKeyListener(new KeyAdapter() {
         public void keyTyped(KeyEvent e) {
         textHiglighter();
         }
         public String getCurrentlyTypedWord() {//get newest word after last white spaceif any or the first word if no white spaces
         String text = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText().substring(0, paneAreas.get(jTabbedPane2.getSelectedIndex()).getCaretPosition());
         String wordBeingTyped = "";
         text = text.replaceAll("(\\r|\\n)", " ");
         if (text.contains(" ")) {
         int tmp = text.lastIndexOf(" ");
         if (tmp >= currentIndexOfSpace || currentIndexOfSpace <= previousIndexOfSpace) {
         previousIndexOfSpace = currentIndexOfSpace;
         currentIndexOfSpace = tmp;
         wordBeingTyped = text.substring(text.lastIndexOf(" "));
         }
         } else {
         wordBeingTyped = text;
         }
         return wordBeingTyped.trim().equals("") ? null : wordBeingTyped.trim();
         }

         private void textHiglighter() {
         //                Highlighter hilite = paneAreas.get(jTabbedPane2.getSelectedIndex()).getHighlighter();

         String text = getCurrentlyTypedWord();
         if (text != null) {
         System.out.println(text);
         int pos = 0;
         // Search for pattern
         for (int i = 0; i < suggest.size(); i++) {
         // String suggestWord = suggest.get(i);

         if (suggest.contains(text)) {
         pos = paneAreas.get(jTabbedPane2.getSelectedIndex()).getCaretPosition();
         try {
         // Create highlighter using private painter and apply around pattern
         //                          hilite.addHighlight(pos- text.length(),pos, new DefaultHighlighter.DefaultHighlightPainter(new Color(51, 153, 255)));

         StyleContext sc = new StyleContext();
         Style style = sc.addStyle("color", null);

         StyleConstants.setForeground(style, Color.red);

         paneAreas.get(jTabbedPane2.getSelectedIndex()).select(pos - text.length(), pos);
         paneAreas.get(jTabbedPane2.getSelectedIndex()).replaceSelection("");
                               
         doc = paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument();
         doc.insertString(pos - text.length(), text, style);
         break;
         } catch (Exception ex) {
         //
         System.out.println("" + ex);
         }
         }

         }

         }

         }
            
         });
         */
        /*
         public String getText(){
         int caretPosition = jta.getDocument().getLength();
         Element root = jta.getDocument().getDefaultRootElement();
         String text = "1" + System.getProperty("line.separator");
         for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
         text += i + System.getProperty("line.separator");
         }
         return text;
         }
	
         */

        undoMgr.add(m_undoManager);

        jTextPane1.getDocument()
                .addUndoableEditListener(
                new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                try {
                    undoMgr.get(jTabbedPane2.getSelectedIndex()).addEdit(e.getEdit());
                    updateButtons();


                } catch (Exception ee) {
                }
            }
        });

        int size = 14;

        jSlider1.setValue(size);

        jLabel4.setText(size
                + "%");

        /*  JPopupMenu jpm= new JPopupMenu();
         final JMenuItem jm1=new JMenuItem("System.out.println()");
         jm1.setSelected(true);
         final JMenuItem jm2=new JMenuItem("System.err.println()");
         jm1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {                     
         try {
         int caretPos = paneAreas.get(jTabbedPane2.getSelectedIndex()).getCaretPosition();
         if (caretPos < 0) {
         caretPos = 0;
         }
         paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().insertString(caretPos, jm1.getText(), null);
         } catch (BadLocationException ex) {
         Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         });
         jm2.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {                     
         try {
         int caretPos = paneAreas.get(jTabbedPane2.getSelectedIndex()).getCaretPosition();
         if (caretPos < 0) {
         caretPos = 0;
         }
         paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().insertString(caretPos, jm2.getText(), null);
         } catch (BadLocationException ex) {
         Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
         });
         jpm.add(jm1);                        
         jpm.add(jm2);                        
         jpm.show(jTextPane1, (int)jTextPane1.getLocation().getX(),(int) jTextPane1.getLocation().getY());
         */

        WindowListener l = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Editor.this.setAlwaysOnTop(false);
                int confirm = JOptionPane.showOptionDialog(Editor.this,
                        "Really Exit?", "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);
                if (confirm == 0) {
                    dispose();
                    System.exit(0);
                }
            }
        };

        addWindowListener(l);

        jToolBar1.setOpaque(
                true);
        //      jPanel9.setSize(jToolBar1.getWidth(), jToolBar1.getHeight());
        //setLocation(0,0);  
        //pack();
        /*Toolkit t = getToolkit();
         Dimension screenSize = t.getScreenSize();
         int width = (int) screenSize.getWidth();
         int height = (int) screenSize.getHeight();
         //setBounds(0, 0, width, height);
         //setLocation(0, 0);
         */


        jDesktopPane1.addComponentListener(
                new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension desktopSize = jDesktopPane1.getSize();
                int dWidth = (int) desktopSize.getWidth();
                int dHeight = (int) desktopSize.getHeight();
                jToolBar1.setSize(dWidth, jToolBar1.getHeight());
//jPanel9.setSize(dWidth, jToolBar1.getHeight());

                //  jInternalFrame5.setSize(dWidth , (dHeight / 6));
                jInternalFrame2.setSize(dWidth / 6, dHeight - jToolBar1.getHeight());
                jInternalFrame3.setSize((dWidth * 4 / 6) - 150, dHeight - jToolBar1.getHeight());
                jInternalFrame4.setSize((dWidth / 6) + 150, dHeight - jToolBar1.getHeight());

                //jInternalFrame5.setLocation(0, 0);
                jInternalFrame2.setLocation(0, jToolBar1.getHeight());
                jInternalFrame3.setLocation((dWidth / 6), jToolBar1.getHeight());
                jInternalFrame4.setLocation((dWidth * 5 / 6) - 150, jToolBar1.getHeight());

            }
        });

    }

    public void addClosableTab(final JTabbedPane tabbedPane, final String title) {


        final JTextPane tPane = new JTextPane();

        tPane.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jTextPane1MouseClicked(evt);

                } catch (Exception ee) {
                }

            }
        });
        int size = jSlider1.getValue();
        tPane.setFont(new java.awt.Font("Tahoma", Font.PLAIN, size)); // NOI18N

        tPane.setAlignmentX(0.0F);
        tPane.setAlignmentY(0.0F);
        tPane.setFocusTraversalPolicyProvider(true);
        tPane.setMargin(new java.awt.Insets(0, 0, 0, 0));

        JTextArea tArea = new JTextArea();



        final UndoManager undoManager = new UndoManager();

        undoMgr.add(undoManager);
        tPane.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {

                try {
                    undoMgr.get(jTabbedPane2.getSelectedIndex()).addEdit(e.getEdit());
                    updateButtons();

                } catch (Exception ee) {
                }

            }
        });

        tPane.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
//                    updateButtons();
                } catch (Exception ee) {
                }
                /*
                 try {
                 updateButtons();
                 int caretPosition = e.getMark();
                 String lineText = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();

                 String subStr = lineText.substring(0, caretPosition);

                 int lineNo = 1;
                 for (int i = 0; i < subStr.length(); i++) {
                 if (subStr.charAt(i) ==13) {
                 lineNo++;
                 }
                 }

                 jLabel12.setText("Ln: " + lineNo);

                 /*     Element root = jTextPane1.getDocument().getDefaultRootElement();
                 int lineNum = root.getElementIndex(e.getDot());
                 int colNum = e.getDot() - root.getElement(lineNum).getStartOffset();
                 colNum++;
                 lineNum++;
                 jLabel12.setText(lineNum + " | " + colNum);
                 lineNo = lineNum;
                 */
//                int lineNo = subStr.split("\n").length;
//                System.out.println("" + lineNo);

                /*  String pattern = "" + lineNo;
                 try {
                 Highlighter hilite = textAreas.get(jTabbedPane2.getSelectedIndex()).getHighlighter();

                 hilite.removeAllHighlights();

                 doc1 = textAreas.get(jTabbedPane2.getSelectedIndex()).getDocument();
                 String text = doc1.getText(0, doc1.getLength());
                 int pos = 0;
                 // Search for pattern
                 while ((pos = text.indexOf(pattern, pos)) >= 0) {
                 // Create highlighter using private painter and apply around pattern
                 hilite.addHighlight(pos, pos + pattern.length(), new DefaultHighlighter.DefaultHighlightPainter(new Color(51, 153, 255)));
                 pos += pattern.length();
                 break;
                 }
                 } catch (Exception ee) {
                 System.out.println("Error: Can't Read Data from file");
                 }

                 } catch (Exception ee) {
                 }
                 */

            }
        });



        tPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent de) {
                try {
                    textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                    updateButtons();

                } catch (Exception ee) {
                }

            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                try {
                    textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                    updateButtons();
                } catch (Exception ee) {
                }

            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                try {
                    textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                    updateButtons();
                } catch (Exception ee) {
                }

            }
        });



        paneAreas.add(tPane);
        textAreas.add(tArea);

        textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());


        /*        AutoSuggestor autoSuggestor = new AutoSuggestor(tPane, this, suggest, Color.WHITE.brighter(), Color.BLUE, Color.RED, 0.75f) {
         @Override
         boolean wordTyped(String typedWord) {
         //System.out.println(typedWord);
         return super.wordTyped(typedWord);//checks for a match in dictionary and returns true or false if found or not
         }
         };
         */
        //System.out.println("tpane="+tPane.hashCode()+"\ttext"+tArea.hashCode());

        tArea.setEditable(false);

        tArea.setFont(new java.awt.Font("Tahoma", Font.PLAIN, size)); // NOI18N

//        tArea.setFont(new java.awt.Font(tPane.getFont().getFontName(), 0, tPane.getFont().getSize())); // NOI18N
        tArea.setRows(5);
        tArea.setDisabledTextColor(new java.awt.Color(0, 0, 102));
        tArea.setEnabled(false);
        tArea.setFocusable(false);
        //tArea.setText("New tab number " + tabCount);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(tPane);
        scrollPane.setRowHeaderView(tArea);
        final JComponent c = scrollPane;
        Icon icon = PAGE_ICON;
        tabCount++;

        File ff = new File("");
        files.add(ff);
        StringBuffer ss = new StringBuffer("");
        classNames.add(ss);
        StringBuffer ss1 = new StringBuffer("");
        packNames.add(ss1);
        StringBuffer ss2 = new StringBuffer("");
        packClassNames.add(ss2);

        /*      System.out.println("files :" + ff.hashCode());
         System.out.println("ss :" + ss.hashCode());
         System.out.println("ss1 :" + ss1.hashCode());
         System.out.println();
         */
        FlowLayout f = new FlowLayout(FlowLayout.CENTER, 0, 2);
        JPanel pnlTab = new JPanel(f);
        pnlTab.setOpaque(false);
        lblTitle = new JLabel(title);

        lblTitles.add(lblTitle);
        //System.out.println("title= " + lblTitle.hashCode());


        lblTitle.setIcon(icon);
        btnClose = new JButton();


        buttons.add(btnClose);
        //  System.out.println(buttons.size()+"btn= " + btnClose.hashCode());


        btnClose.setOpaque(false);
        btnClose.setIcon(CLOSE_TAB_ICON);
        btnClose.setRolloverEnabled(true);
        btnClose.setRolloverIcon(CLOSE_TAB_ICON_ROLEOVER);
        btnClose.setRolloverSelectedIcon(CLOSE_TAB_ICON_ROLEOVER);
        btnClose.setBorder(null);
        btnClose.setFocusable(false);
        pnlTab.add(lblTitle);
        pnlTab.add(btnClose);
        pnlTab.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        //pnlTab.setBackground(new Color(210, 220, 243));
        tabbedPane.insertTab(title, null, c, "Click on close button to Close Tab", tabbedPane.getTabCount() - 1);//c// for actual component to be display
        int pos = tabbedPane.indexOfComponent(c);


        tabbedPane.setTabComponentAt(pos, pnlTab);// for title at tab
        MouseListener listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int s = e.getButton();
                if (s == MouseEvent.BUTTON3) {
                    jpm2.show(e.getComponent(), e.getX(), e.getY());
                } else {
                    --tabCount;
                    //System.out.println("tab cnt="+tabbedPane.getTabCount()+"\t"+tabCount);  
                    countButton = 0;
                    Iterator i = buttons.iterator();
                    while (i.hasNext()) {
                        if ((JButton) i.next() == e.getSource()) {
                            break;
                        }
                        countButton++;
                    }


                    // we have not register zeroth button to its listener so when w click on 
                    // zeroth button it will not call action performed and then will not be close

                    //if (paneAreas.remove(paneAreas.get(count)) && textAreas.remove(textAreas.get(count))) {//tabCount

                    //   System.out.println("remove\npos=" + countButton);
                    //  System.out.println("pane= "+paneAreas.get(countButton).hashCode());                    
                    //  System.out.println("text= "+textAreas.get(countButton).hashCode());                    

                    // countButton-1 because we start adding button from second tab
                    //System.out.println("tpane="+paneAreas.get(countButton).hashCode()+"\ttext"+textAreas.get(countButton).hashCode());
                    buttons.remove(countButton);
                    paneAreas.remove(paneAreas.get(countButton));
                    textAreas.remove(textAreas.get(countButton));
                    lblTitles.remove(lblTitles.get(countButton));

                    undoMgr.remove(countButton);

                    files.remove(files.get(countButton));
                    classNames.remove(classNames.get(countButton));
                    packNames.remove(packNames.get(countButton));
                    packClassNames.remove(packClassNames.get(countButton));

                    tabbedPane.remove(c);

                    //htmlpages.remove(i);

                    /*
                     System.out.println("Removed");
                     System.out.println("files :" + files.get(countButton).hashCode());
                     System.out.println("ss :" + classNames.get(countButton).hashCode());
                     System.out.println("ss1 :" + packNames.get(countButton).hashCode());
                     System.out.println();

                     */
                    //  System.out.println("TextArea is Empty? " + textAreas.isEmpty() + "\tsize=" + paneAreas.size()
                    //   + "\nTextPane is Empty? " + paneAreas.isEmpty() + "\tsize=" + paneAreas.size()+
                    //   "\tsize=" + buttons.size());
                    countButton = 0;

                    if (tabCount == 0) {
                        tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(0));
                    }
                    // System.out.println((jTabbedPane2.getSelectedIndex() + 1 == jTabbedPane2.getTabCount()) + "\t"
                    //       + (jTabbedPane2.getSelectedIndex() + 1) + "\t" + jTabbedPane2.getTabCount());
                    if ((jTabbedPane2.getSelectedIndex() + 1) == jTabbedPane2.getTabCount()) {
                        jTabbedPane2.setSelectedIndex(0);
                    }
                    /*} else {
                     System.out.println("Can't remove TextArea and TextPane");
                     } */
                }
            }
        };
        btnClose.addMouseListener(listener);
        tabbedPane.setSelectedComponent(c);
        /*
         AbstractAction closeTabAction = new AbstractAction() {
         @Override
         public void actionPerformed(ActionEvent e) {
         --tabCount;
         //System.out.println("tab cnt="+tabbedPane.getTabCount()+"\t"+tabCount);                    
         countButton=0;
         Iterator i = buttons.iterator();
         while (i.hasNext()) {
         if ((JButton) i.next() == e.getSource()) {
         break;
         }
         countButton++;
         }


         // we have not register zeroth button to its listener so when w click on 
         // zeroth button it will not call action performed and then will not be close

         //if (paneAreas.remove(paneAreas.get(count)) && textAreas.remove(textAreas.get(count))) {//tabCount

         //   System.out.println("remove\npos=" + countButton);
         //  System.out.println("pane= "+paneAreas.get(countButton).hashCode());                    
         //  System.out.println("text= "+textAreas.get(countButton).hashCode());                    

         buttons.remove(countButton);
         // countButton-1 because we start adding button from second tab
         //System.out.println("tpane="+paneAreas.get(countButton).hashCode()+"\ttext"+textAreas.get(countButton).hashCode());
         paneAreas.remove(paneAreas.get(countButton));
         textAreas.remove(textAreas.get(countButton));
         lblTitles.remove(lblTitles.get(countButton));
         /*
         System.out.println("Removed");
         System.out.println("files :" + files.get(countButton).hashCode());
         System.out.println("ss :" + classNames.get(countButton).hashCode());
         System.out.println("ss1 :" + packNames.get(countButton).hashCode());
         System.out.println();

         */
        /*               files.remove(files.get(countButton));

         classNames.remove(classNames.get(countButton));
         packNames.remove(packNames.get(countButton));
         packClassNames.remove(packClassNames.get(countButton));


         tabbedPane.remove(c);




         //  System.out.println("TextArea is Empty? " + textAreas.isEmpty() + "\tsize=" + paneAreas.size()
         //   + "\nTextPane is Empty? " + paneAreas.isEmpty() + "\tsize=" + paneAreas.size()+
         //   "\tsize=" + buttons.size());
         countButton = 0;

         if (tabCount == 0) {
         tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(0));
         }
         // System.out.println((jTabbedPane2.getSelectedIndex() + 1 == jTabbedPane2.getTabCount()) + "\t"
         //       + (jTabbedPane2.getSelectedIndex() + 1) + "\t" + jTabbedPane2.getTabCount());
         if ((jTabbedPane2.getSelectedIndex() + 1) == jTabbedPane2.getTabCount()) {
         jTabbedPane2.setSelectedIndex(0);
         }
         /*} else {
         System.out.println("Can't remove TextArea and TextPane");
         } */
        /*           }
         };
         KeyStroke controlW = KeyStroke.getKeyStroke("control W");
         InputMap inputMap = c.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
         inputMap.put(controlW, "closeTab");
         c.getActionMap().put("closeTab", closeTabAction);
         */

    }

    private String getText1() {
        String text;
        int caretPosition = paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().getLength();
        Element root1 = paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().getDefaultRootElement();
        text = "1" + System.getProperty("line.separator");
        for (int i = 2; i < root1.getElementIndex(caretPosition) + 2; i++) {
            text += i + System.getProperty("line.separator");
        }
        return text;
    }

    private void windowLookAndFeel() {


        looks = UIManager.getInstalledLookAndFeels();

        JMenuItem[] itmMenu = new JMenuItem[looks.length + 1];
        //System.out.println(looks.length);
        for (itmCount = 0; itmCount < looks.length; itmCount++) {

            itmMenu[itmCount] = new JMenuItem();
            itmMenu[itmCount].setText(looks[itmCount].getName());
            itmMenu[itmCount].setActionCommand("" + itmCount);
            itmMenu[itmCount].addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        int cnt = Integer.parseInt(evt.getActionCommand());
                        UIManager.setLookAndFeel(looks[ cnt].getClassName());
                    } catch (ClassNotFoundException ex) {
                        System.out.println("Look and Feel Not Found.");
                    } catch (InstantiationException ex) {
                        System.out.println("Look and Feel not installed.");
                    } catch (IllegalAccessException ex) {
                        System.out.println("Look and Feel not accessed.");
                    } catch (UnsupportedLookAndFeelException ex) {
                        System.out.println("Look and Feel not Supported.");
                    }
                    SwingUtilities.updateComponentTreeUI(Editor.this);
                }
            });

            jMenu8.add(itmMenu[itmCount]);

        }

        itmMenu[itmCount] = new JMenuItem();
        itmMenu[itmCount].setText("System");
        itmMenu[itmCount].setActionCommand("" + itmCount);
        itmMenu[itmCount].addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    int cnt = Integer.parseInt(evt.getActionCommand());
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    System.out.println("Look and Feel Not Found.");
                } catch (InstantiationException ex) {
                    System.out.println("Look and Feel not installed.");
                } catch (IllegalAccessException ex) {
                    System.out.println("Look and Feel not accessed.");
                } catch (UnsupportedLookAndFeelException ex) {
                    System.out.println("Look and Feel not Supported.");
                }
                SwingUtilities.updateComponentTreeUI(Editor.this);

            }
        });

        jMenu8.add(itmMenu[itmCount]);
    }

    /*
     @Override
     public Dimension getPreferredSize() {
     return new Dimension(200, 200);
     }
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jDesktopPane1 = new javax.swing.JDesktopPane(){

            java.awt.image.BufferedImage bimg =null;

            @Override
            protected void paintComponent(Graphics grphcs) {
                super.paintComponent(grphcs);
                try{
                    bimg=javax.imageio.ImageIO.read(getClass().getResource("/asterix/image/desktoplogo.jpg"));
                }catch(Exception e){
                    System.out.println("Desktop Image not found...");
                }
                grphcs.drawImage(bimg, 0, 0, null);
            }

            @Override
            public Dimension getPreferredSize() {
                return null;//new Dimension(bimg.getWidth(), bimg.getHeight());
            }
        };
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel13 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jInternalFrame3 = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton18 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton16 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtURL = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jInternalFrame4 = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        clipCutBtn = new javax.swing.JButton();
        clipCopyBtn = new javax.swing.JButton();
        clipPasteBtn = new javax.swing.JButton();
        clipDelBtn = new javax.swing.JButton();
        styleColorBtn = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jPanel16 = new javax.swing.JPanel();
        clipUndoBtn = new javax.swing.JButton();
        clipRedoBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JSeparator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JSeparator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Make dragging faster by setting drag mode to Outline
        jDesktopPane1.putClientProperty("JDesktopPane.dragMode", "outline");

        jInternalFrame2.setBackground(new java.awt.Color(210, 220, 243));
        jInternalFrame2.setIconifiable(true);
        jInternalFrame2.setMaximizable(true);
        jInternalFrame2.setResizable(true);
        jInternalFrame2.setTitle("Directory");
        jInternalFrame2.setAlignmentX(0.0F);
        jInternalFrame2.setAlignmentY(0.0F);
        jInternalFrame2.setAutoscrolls(true);
        jInternalFrame2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jInternalFrame2.setDoubleBuffered(true);
        jInternalFrame2.setFocusTraversalPolicyProvider(true);
        jInternalFrame2.setVisible(true);

        jTree1.setBackground(new java.awt.Color(210, 220, 243));
        jTree1.setCellRenderer(new MyTreeCellRenderer());
        jTree1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTree1.setAutoscrolls(true);
        jTree1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTree1.setFocusCycleRoot(true);
        jTree1.setFocusTraversalPolicyProvider(true);
        jTree1.setRootVisible(false);
        UIManager.put("Tree.rendererFillBackground", false);
        jScrollPane3.setViewportView(jTree1);
        // Add a listener

        jLabel13.setBackground(new java.awt.Color(210, 220, 243));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Directory Tree");
        jLabel13.setAlignmentX(1.0F);
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setOpaque(true);

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/refresh.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jProgressBar1.setForeground(new java.awt.Color(0, 204, 0));
        jProgressBar1.setMaximumSize(new java.awt.Dimension(32767, 10));
        jProgressBar1.setMinimumSize(new java.awt.Dimension(10, 10));
        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 10));

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame2Layout.createSequentialGroup()
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)
                .addGap(1, 1, 1)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jInternalFrame2.setBounds(0, 42, 180, 680);
        jDesktopPane1.add(jInternalFrame2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrame3.setIconifiable(true);
        jInternalFrame3.setMaximizable(true);
        jInternalFrame3.setResizable(true);
        jInternalFrame3.setTitle("Source Code");
        jInternalFrame3.setAlignmentX(0.0F);
        jInternalFrame3.setAlignmentY(0.0F);
        jInternalFrame3.setAutoscrolls(true);
        jInternalFrame3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jInternalFrame3.setDoubleBuffered(true);
        jInternalFrame3.setFocusable(false);
        jInternalFrame3.setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));
        try {
            jInternalFrame3.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        jInternalFrame3.setVisible(true);

        jPanel5.setBackground(new java.awt.Color(210, 220, 243));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 51), null));
        jPanel5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jScrollPane1.setAlignmentX(0.0F);
        jScrollPane1.setAlignmentY(0.0F);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(181, 201, 243));
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setAlignmentX(0.0F);
        jTextArea1.setAlignmentY(0.0F);
        jTextArea1.setDisabledTextColor(new java.awt.Color(0, 0, 102));
        jTextArea1.setEnabled(false);
        jTextArea1.setFocusable(false);
        jTextArea1.setMinimumSize(new java.awt.Dimension(2, 26));
        jScrollPane1.setViewportView(jTextArea1);

        jTabbedPane2.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane2.setAlignmentX(0.0F);
        jTabbedPane2.setAlignmentY(0.0F);
        jTabbedPane2.setFocusable(false);
        jTabbedPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane2MouseClicked(evt);
            }
        });

        jTextPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextPane1.setAlignmentX(0.0F);
        jTextPane1.setAlignmentY(0.0F);
        jTextPane1.setFocusTraversalPolicyProvider(true);
        jTextPane1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTextPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextPane1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTextPane1);

        jTabbedPane2.addTab("", jScrollPane4);

        jPanel4.setBackground(new java.awt.Color(210, 220, 243));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setAlignmentX(0.0F);
        jPanel4.setAlignmentY(0.0F);
        jPanel4.setFocusable(false);

        jLabel6.setText("Find:");

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/previous.png"))); // NOI18N
        jButton17.setText("Previous");
        jButton17.setToolTipText("Search Text ( Enter- find next, Shift+Enter- find previous )");
        jButton17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton17.setIconTextGap(2);
        jButton17.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Match Case");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 102, 0), null));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jCheckBox1.setOpaque(false);
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/close_1.png"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jTextField3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jTextField3.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextField3CaretUpdate(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/next.png"))); // NOI18N
        jButton16.setText("Next");
        jButton16.setToolTipText("Search Text ( Enter- find next, Shift+Enter- find previous )");
        jButton16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton16.setIconTextGap(2);
        jButton16.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jLabel8.setText("0 Match");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(30, 30, 30)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel7.setBackground(new java.awt.Color(210, 220, 243));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setAlignmentX(0.0F);
        jPanel7.setAlignmentY(0.0F);

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel7.setText("Replace With:");

        jButton19.setText("Replace");
        jButton19.setToolTipText("Replace Text ( Enter- replace next, Shift+Enter- replace all )");
        jButton19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton19.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setText("Replace All");
        jButton20.setToolTipText("Replace Text ( Enter- replace next, Shift+Enter- replace all )");
        jButton20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton20.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/close_1.png"))); // NOI18N
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel11.setText("0 Replaced");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.setBackground(new java.awt.Color(210, 220, 243));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);

        jLabel5.setText("Run:");

        txtURL.setToolTipText("Type program name to open like: CMD CALC WebAddress etc.");
        txtURL.setAlignmentX(0.0F);
        txtURL.setAlignmentY(0.0F);
        txtURL.setMaximumSize(new java.awt.Dimension(150, 29));
        txtURL.setMinimumSize(new java.awt.Dimension(150, 29));
        txtURL.setName(""); // NOI18N
        txtURL.setPreferredSize(new java.awt.Dimension(100, 27));
        txtURL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtURLActionPerformed(evt);
            }
        });

        jButton15.setText("Ok");
        jButton15.setToolTipText("Run any application directly by typing there name in textbox");
        jButton15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton15.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/close_1.png"))); // NOI18N
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(33, 33, 33)
                .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(210, 220, 243));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setAlignmentX(0.0F);
        jPanel8.setAlignmentY(0.0F);

        jLabel9.setText("Go To:");

        jTextField5.setPreferredSize(new java.awt.Dimension(150, 29));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/close_1.png"))); // NOI18N
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton22.setText("Go");
        jButton22.setToolTipText("Go to line no. ( Enter- go to next )");
        jButton22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton22.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(22, 22, 22)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addComponent(jTabbedPane2)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jInternalFrame3Layout = new javax.swing.GroupLayout(jInternalFrame3.getContentPane());
        jInternalFrame3.getContentPane().setLayout(jInternalFrame3Layout);
        jInternalFrame3Layout.setHorizontalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame3Layout.setVerticalGroup(
            jInternalFrame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jInternalFrame3.setBounds(180, 42, 740, 680);
        jDesktopPane1.add(jInternalFrame3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrame4.setIconifiable(true);
        jInternalFrame4.setMaximizable(true);
        jInternalFrame4.setResizable(true);
        jInternalFrame4.setTitle("Output");
        jInternalFrame4.setAlignmentX(0.0F);
        jInternalFrame4.setAlignmentY(0.0F);
        jInternalFrame4.setAutoscrolls(true);
        jInternalFrame4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jInternalFrame4.setDoubleBuffered(true);
        jInternalFrame4.setFocusable(false);
        jInternalFrame4.setVisible(true);

        jPanel3.setBackground(new java.awt.Color(210, 220, 243));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 102, 51), null));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.setFocusable(false);

        jEditorPane1.setEditable(false);
        jEditorPane1.setBackground(new java.awt.Color(210, 220, 243));
        jEditorPane1.setContentType("text/html"); // NOI18N
        jEditorPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jEditorPane1.setForeground(new java.awt.Color(0, 0, 153));
        jEditorPane1.setToolTipText("");
        jEditorPane1.setDisabledTextColor(new java.awt.Color(0, 0, 153));
        jEditorPane1.setFocusTraversalPolicyProvider(true);
        jEditorPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jEditorPane1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jEditorPane1);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/back.png"))); // NOI18N
        jButton10.setToolTipText("Backword");
        jButton10.setIconTextGap(0);
        jButton10.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/for.png"))); // NOI18N
        jButton11.setToolTipText("Forword");
        jButton11.setIconTextGap(0);
        jButton11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(210, 220, 243));
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jInternalFrame4Layout = new javax.swing.GroupLayout(jInternalFrame4.getContentPane());
        jInternalFrame4.getContentPane().setLayout(jInternalFrame4Layout);
        jInternalFrame4Layout.setHorizontalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame4Layout.setVerticalGroup(
            jInternalFrame4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jInternalFrame4.setBounds(920, 42, 260, 680);
        jDesktopPane1.add(jInternalFrame4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jToolBar1.setBackground(new java.awt.Color(210, 220, 243));
        jToolBar1.setRollover(true);
        jToolBar1.setAlignmentX(0.0F);

        jPanel10.setBackground(new java.awt.Color(210, 220, 243));

        jPanel12.setBackground(new java.awt.Color(210, 220, 243));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 51), null));
        jPanel12.setAlignmentX(0.0F);
        jPanel12.setAlignmentY(0.0F);
        jPanel12.setOpaque(false);

        clipCutBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/cut.png"))); // NOI18N
        clipCutBtn.setToolTipText("Cut Source code to clipboard.");
        clipCutBtn.setAlignmentY(0.0F);
        clipCutBtn.setBorderPainted(false);
        clipCutBtn.setFocusable(false);
        clipCutBtn.setIconTextGap(0);
        clipCutBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        clipCutBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        clipCutBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        clipCutBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        clipCutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipCutBtnActionPerformed(evt);
            }
        });

        clipCopyBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/copy.png"))); // NOI18N
        clipCopyBtn.setToolTipText("Copy Source code to clipboard.");
        clipCopyBtn.setActionCommand("Copy Source code to clipboard.");
        clipCopyBtn.setAlignmentY(0.0F);
        clipCopyBtn.setBorderPainted(false);
        clipCopyBtn.setFocusable(false);
        clipCopyBtn.setIconTextGap(0);
        clipCopyBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        clipCopyBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        clipCopyBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        clipCopyBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        clipCopyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipCopyBtnActionPerformed(evt);
            }
        });

        clipPasteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/paste.png"))); // NOI18N
        clipPasteBtn.setToolTipText("Paste Source code from clipboard.");
        clipPasteBtn.setAlignmentY(0.0F);
        clipPasteBtn.setBorderPainted(false);
        clipPasteBtn.setFocusable(false);
        clipPasteBtn.setIconTextGap(0);
        clipPasteBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        clipPasteBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        clipPasteBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        clipPasteBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        clipPasteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipPasteBtnActionPerformed(evt);
            }
        });

        clipDelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/del.png"))); // NOI18N
        clipDelBtn.setToolTipText("Delete Source code");
        clipDelBtn.setAlignmentY(0.0F);
        clipDelBtn.setBorderPainted(false);
        clipDelBtn.setFocusable(false);
        clipDelBtn.setIconTextGap(0);
        clipDelBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        clipDelBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        clipDelBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        clipDelBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        clipDelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipDelBtnActionPerformed(evt);
            }
        });

        styleColorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/color.png"))); // NOI18N
        styleColorBtn.setToolTipText("Color Chooser");
        styleColorBtn.setAlignmentY(0.0F);
        styleColorBtn.setBorderPainted(false);
        styleColorBtn.setFocusable(false);
        styleColorBtn.setIconTextGap(0);
        styleColorBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        styleColorBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        styleColorBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        styleColorBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        styleColorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleColorBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(clipCutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clipCopyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clipPasteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clipDelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(styleColorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clipCutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(clipCopyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(clipPasteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(clipDelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(styleColorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel15.setBackground(new java.awt.Color(210, 220, 243));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 51), null));
        jPanel15.setAlignmentX(0.0F);
        jPanel15.setAlignmentY(0.0F);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/compile.png"))); // NOI18N
        jButton12.setToolTipText("Compile");
        jButton12.setAlignmentY(0.0F);
        jButton12.setBorderPainted(false);
        jButton12.setFocusable(false);
        jButton12.setIconTextGap(0);
        jButton12.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton12.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton12.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton12.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/run.png"))); // NOI18N
        jButton13.setToolTipText("Run");
        jButton13.setAlignmentY(0.0F);
        jButton13.setBorderPainted(false);
        jButton13.setFocusable(false);
        jButton13.setIconTextGap(0);
        jButton13.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton13.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton13.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton13.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/com_run.png"))); // NOI18N
        jButton6.setToolTipText("Compile & Run");
        jButton6.setAlignmentY(0.0F);
        jButton6.setBorderPainted(false);
        jButton6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton6.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton6.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton6.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Args");

        jTextField1.setText("0");
        jTextField1.setToolTipText("Provide command line input for program");
        jTextField1.setAlignmentX(0.0F);
        jTextField1.setAlignmentY(0.0F);
        jTextField1.setFocusCycleRoot(true);
        jTextField1.setFocusTraversalPolicyProvider(true);
        jTextField1.setMargin(new java.awt.Insets(1, 1, 1, 1));
        jTextField1.setPreferredSize(new java.awt.Dimension(150, 29));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(210, 220, 243));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 51), null));
        jPanel17.setAlignmentX(0.0F);
        jPanel17.setAlignmentY(0.0F);
        jPanel17.setOpaque(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/new.png"))); // NOI18N
        jButton1.setToolTipText("New");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorderPainted(false);
        jButton1.setFocusable(false);
        jButton1.setIconTextGap(0);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton1.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton1.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/open.png"))); // NOI18N
        jButton3.setToolTipText("Open");
        jButton3.setAlignmentY(0.0F);
        jButton3.setBorderPainted(false);
        jButton3.setIconTextGap(0);
        jButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton3.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton3.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton3.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/save.png"))); // NOI18N
        jButton2.setToolTipText("Save");
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setIconTextGap(0);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton2.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton2.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel18.setBackground(new java.awt.Color(210, 220, 243));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 51), null));
        jPanel18.setAlignmentX(0.0F);
        jPanel18.setAlignmentY(0.0F);
        jPanel18.setOpaque(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/asteix_info.png"))); // NOI18N
        jButton4.setToolTipText("Help");
        jButton4.setAlignmentY(0.0F);
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setIconTextGap(0);
        jButton4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton4.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton4.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton4.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setForeground(new java.awt.Color(102, 0, 0));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/about.png"))); // NOI18N
        jButton5.setToolTipText("System Information");
        jButton5.setAlignmentY(0.0F);
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setIconTextGap(0);
        jButton5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton5.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton5.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton5.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel13.setBackground(new java.awt.Color(210, 220, 243));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 51), null));
        jPanel13.setAlignmentX(0.0F);
        jPanel13.setAlignmentY(0.0F);
        jPanel13.setOpaque(false);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/goto.png"))); // NOI18N
        jButton9.setToolTipText("Go to line number");
        jButton9.setAlignmentY(0.0F);
        jButton9.setBorderPainted(false);
        jButton9.setFocusable(false);
        jButton9.setIconTextGap(0);
        jButton9.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton9.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton9.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton9.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/find.png"))); // NOI18N
        jButton8.setToolTipText("Find");
        jButton8.setAlignmentY(0.0F);
        jButton8.setBorderPainted(false);
        jButton8.setFocusable(false);
        jButton8.setIconTextGap(0);
        jButton8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton8.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton8.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton8.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/replace.png"))); // NOI18N
        jButton7.setToolTipText("Find & Replace");
        jButton7.setAlignmentY(0.0F);
        jButton7.setBorderPainted(false);
        jButton7.setFocusable(false);
        jButton7.setIconTextGap(0);
        jButton7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton7.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton7.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton7.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/look.png"))); // NOI18N
        jButton24.setAlignmentY(0.0F);
        jButton24.setBorderPainted(false);
        jButton24.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton24.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton24.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton24.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/print.png"))); // NOI18N
        printBtn.setToolTipText("Print");
        printBtn.setAlignmentY(0.0F);
        printBtn.setBorderPainted(false);
        printBtn.setFocusable(false);
        printBtn.setIconTextGap(0);
        printBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        printBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        printBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        printBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        printBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(printBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(printBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel14.setBackground(new java.awt.Color(210, 220, 243));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 51), null));
        jPanel14.setAlignmentX(0.0F);
        jPanel14.setAlignmentY(0.0F);
        jPanel14.setOpaque(false);

        jSlider1.setMaximum(35);
        jSlider1.setMinimum(10);
        jSlider1.setAlignmentX(0.0F);
        jSlider1.setAlignmentY(0.0F);
        jSlider1.setMaximumSize(new java.awt.Dimension(100, 30));
        jSlider1.setMinimumSize(new java.awt.Dimension(100, 30));
        jSlider1.setPreferredSize(new java.awt.Dimension(100, 30));
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel4.setText("size");
        jLabel4.setAlignmentY(0.0F);
        jLabel4.setMaximumSize(new java.awt.Dimension(30, 30));
        jLabel4.setMinimumSize(new java.awt.Dimension(30, 30));
        jLabel4.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator17.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel16.setBackground(new java.awt.Color(210, 220, 243));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 153, 51), null));
        jPanel16.setAlignmentX(0.0F);
        jPanel16.setAlignmentY(0.0F);
        jPanel16.setOpaque(false);

        clipUndoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/undo.png"))); // NOI18N
        clipUndoBtn.setToolTipText("Undo");
        clipUndoBtn.setAlignmentY(0.0F);
        clipUndoBtn.setBorderPainted(false);
        clipUndoBtn.setIconTextGap(0);
        clipUndoBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        clipUndoBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        clipUndoBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        clipUndoBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        clipUndoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipUndoBtnActionPerformed(evt);
            }
        });

        clipRedoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/redo.png"))); // NOI18N
        clipRedoBtn.setToolTipText("Redo");
        clipRedoBtn.setAlignmentY(0.0F);
        clipRedoBtn.setBorderPainted(false);
        clipRedoBtn.setIconTextGap(0);
        clipRedoBtn.setMargin(new java.awt.Insets(0, 0, 0, 0));
        clipRedoBtn.setMaximumSize(new java.awt.Dimension(30, 30));
        clipRedoBtn.setMinimumSize(new java.awt.Dimension(30, 30));
        clipRedoBtn.setPreferredSize(new java.awt.Dimension(30, 30));
        clipRedoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clipRedoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(clipUndoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clipRedoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clipUndoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clipRedoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(123, 123, 123))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator15)
                    .addComponent(jSeparator16)
                    .addComponent(jSeparator17)
                    .addComponent(jSeparator18)
                    .addComponent(jSeparator19)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.add(jPanel10);

        jToolBar1.setBounds(0, 0, 1180, 40);
        jDesktopPane1.add(jToolBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel1.setBackground(new java.awt.Color(210, 220, 243));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Info");
        jLabel2.setAlignmentY(0.0F);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setAlignmentY(0.0F);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setAlignmentY(0.0F);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("jLabel15");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(183, 183, 183)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(181, 201, 243));

        jMenu1.setText("File");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/new.png"))); // NOI18N
        jMenuItem6.setText("New");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/open.png"))); // NOI18N
        jMenuItem1.setText("Open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/open.png"))); // NOI18N
        jMenu9.setText("Open Recent File");
        jMenu1.add(jMenu9);

        jMenuItem27.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/save.png"))); // NOI18N
        jMenuItem27.setText("Save");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem27);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/save.png"))); // NOI18N
        jMenuItem2.setText("Save As");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem15.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/print.png"))); // NOI18N
        jMenuItem15.setText("Print");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem15);
        jMenu1.add(jSeparator12);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/exit.png"))); // NOI18N
        jMenuItem3.setText("Exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu5.setText("Edit");

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/cut.png"))); // NOI18N
        jMenuItem4.setText("Cut");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/copy.png"))); // NOI18N
        jMenuItem7.setText("Copy");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/paste.png"))); // NOI18N
        jMenuItem9.setText("Paste");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/del.png"))); // NOI18N
        jMenuItem10.setText("Delete");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/selall.png"))); // NOI18N
        jMenuItem13.setText("Select All");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);
        jMenu5.add(jSeparator7);

        jMenuItem22.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/find.png"))); // NOI18N
        jMenuItem22.setText("Find");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/replace.png"))); // NOI18N
        jMenuItem24.setText("Replace");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem24);

        jMenuItem25.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/goto.png"))); // NOI18N
        jMenuItem25.setText("Go To");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem25);
        jMenu5.add(jSeparator8);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/color.png"))); // NOI18N
        jMenuItem8.setText("Color");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);
        jMenu5.add(jSeparator9);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/undo.png"))); // NOI18N
        jMenuItem11.setText("Undo");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem11);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/redo.png"))); // NOI18N
        jMenuItem12.setText("Redo");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);
        jMenu5.add(jSeparator10);

        jMenuItem14.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/clock.png"))); // NOI18N
        jMenuItem14.setText("Time/Date");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Settings");

        jMenuItem21.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/home.png"))); // NOI18N
        jMenuItem21.setText("Set JAVA_HOME");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem21);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/look.png"))); // NOI18N
        jMenu8.setText("Look And Feel");

        jMenuItem23.setText("Default");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem23);
        jMenu8.add(jSeparator3);

        jMenu6.add(jMenu8);

        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/color.png"))); // NOI18N
        jMenuItem30.setText("Change Color Theme");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem30);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/atop.png"))); // NOI18N
        jMenu7.setText("Language");

        jRadioButtonMenuItem1.setText("C/C++");
        jRadioButtonMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem1ActionPerformed(evt);
            }
        });
        jMenu7.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("Java");
        jRadioButtonMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem2ActionPerformed(evt);
            }
        });
        jMenu7.add(jRadioButtonMenuItem2);

        jRadioButtonMenuItem3.setText("Html");
        jRadioButtonMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItem3ActionPerformed(evt);
            }
        });
        jMenu7.add(jRadioButtonMenuItem3);

        jMenu6.add(jMenu7);

        jMenuBar1.add(jMenu6);

        jMenu4.setText("Run");

        jMenuItem17.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        jMenuItem17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/compile.png"))); // NOI18N
        jMenuItem17.setText("Compile");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem17);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/run.png"))); // NOI18N
        jMenuItem16.setText("Run");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/com_run.png"))); // NOI18N
        jMenuItem20.setText("Compile & Run");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem20);

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        jMenuItem18.setText("De Compile");
        jMenu4.add(jMenuItem18);
        jMenu4.add(jSeparator11);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/compile.png"))); // NOI18N
        jMenuItem19.setText("Generate JavaDoc");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem19);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Windows");

        jCheckBoxMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItem1.setText("Always On Top");
        jCheckBoxMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/view.png"))); // NOI18N
        jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem1);
        jMenu2.add(jSeparator2);

        jCheckBoxMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jCheckBoxMenuItem4.setText("Full Screen");
        jCheckBoxMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/fullscreen.png"))); // NOI18N
        jCheckBoxMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jCheckBoxMenuItem4);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/reset.png"))); // NOI18N
        jMenuItem28.setText("Reset Windows");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem28);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/asterix/image/asteix_info.png"))); // NOI18N
        jMenuItem5.setText("About Us");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1179, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        jEditorPane1.setText("");
        String oldDir = classOutputFolder;
        classOutputFolder = openFileDialog();
        if (classOutputFolder == null) {
            classOutputFolder = oldDir;
        } else {
            dontSave = false;
            jLabel15.setVisible(false);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jCheckBoxMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem1ActionPerformed
        if (jCheckBoxMenuItem1.isSelected()) {
            this.setAlwaysOnTop(true);
        } else {
            this.setAlwaysOnTop(false);
        }
}//GEN-LAST:event_jCheckBoxMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed


        jLabel15.setVisible(false);
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        jEditorPane1.setText("");
        jEditorPane1.setText("<font color=red>New File. Please Save It.</font><br><br>");
        currentFile = null;
        fileDir = null;
        fileName = null;
        curDir = null;
        fileExtenssion = null;
        currentClassName = null;
        currentPackName = null;
        currentPackClassName = null;


        dontSave = false;

        if (language.equals("java")) {
            textPlainOrHtml = "package com;\n\n"
                    + "public class Demo {\n\n"
                    + "\tpublic static void main(String[] args) {\n\n"
                    + "\t	System.out.println(\"Hello World!\");\n\n"
                    + "\t}\n\n"
                    + "}";
        } else if (language.equals("c")) {
            textPlainOrHtml = "#include<iostream>\n\n"
                    + "using namespace std;\n\n"
                    + "int main() {\n\n"
                    + "\tcout<<\"Hello World!\";\n\n" + "return 0;\n\n" + "}";
        } else if (language.equals("html")) {
            textPlainOrHtml = "<html>\n\n"
                    + "<head>\n\n\t<title>\tDemo\t</title>\n\n</head>\n\n"
                    + "<body>\n\n"
                    + "\tHello World!\n\n"
                    + "</body>\n\n"
                    + "<html>";
        }

        //jTabbedPane2.setIconAt(0, null);

        //paneAreas.get(jTabbedPane2.getSelectedIndex()).setText(textPlainOrHtml);
        /*int pos = 0;
         if (jTabbedPane2.getSelectedIndex() > 0) {
         pos = jTabbedPane2.getSelectedIndex();
         } else {
         pos = 0;
         }
         */

        paneAreas.get(jTabbedPane2.getSelectedIndex()).setText(textPlainOrHtml);

        /* if (paneAreas.isEmpty()) {
         paneAreas.get(jTabbedPane2.getSelectedIndex()).setText(textPlainOrHtml);
         System.out.println("i m here ");
         } else {
         if (jTabbedPane2.getComponentAt(jTabbedPane2.getSelectedIndex()) != null) {
         paneAreas.get(pos).setText(textPlainOrHtml);
         //System.out.println("i m aaaaaaaaa "+pos+"\t"+jTabbedPane2.getSelectedIndex());
         } else {
         paneAreas.get(pos - 1).setText(textPlainOrHtml);
         System.out.println("Do nothing " + jTabbedPane2.getSelectedIndex());
         }
         }
         */

        files.set(jTabbedPane2.getSelectedIndex(), new File(""));

        lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(null);
        lblTitles.get(jTabbedPane2.getSelectedIndex()).setText("NoName" + newFileCounter++);

        /*      System.out.println("\nReSet Data Values:\n\ncurrentClassName\t" + currentClassName + "\ncurrentPackName\t" + currentPackName
         + "\ncurrentFile\t" + currentFile + "\ncurDir\t" + curDir + "\ncurrentPackClassName\t" + currentPackClassName
         + "\nfileDir\t" + fileDir + "\nfileName\t" + fileName + "\nfileExtenssion\t" + fileExtenssion);
         */
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    public static List<String> extractText(Reader reader) {
        final ArrayList<String> list = new ArrayList<>();

        ParserDelegator parserDelegator = new ParserDelegator();
        ParserCallback parserCallback = new ParserCallback() {
            @Override
            public void handleText(final char[] data, final int pos) {
                list.add(new String(data));
            }

            @Override
            public void handleStartTag(Tag tag, MutableAttributeSet attribute, int pos) {
            }

            @Override
            public void handleEndTag(Tag t, final int pos) {
            }

            @Override
            public void handleSimpleTag(Tag t, MutableAttributeSet a, final int pos) {
            }

            @Override
            public void handleComment(final char[] data, final int pos) {
            }

            @Override
            public void handleError(final java.lang.String errMsg, final int pos) {
            }
        };
        try {
            parserDelegator.parse(reader, parserCallback, true);
        } catch (IOException ex) {
            System.out.println("Internal I/O Error.");
        }
        return list;
    }

    public static String removeHTML(String input) {
        int i;
        String[] str1 = input.split("");

        String s = "";
        boolean inTag = false;

        for (i = input.indexOf("<"); i < input.indexOf(">"); i++) {
            inTag = true;
        }
        if (!inTag) {
            for (i = 0; i < str1.length; i++) {
                s = s + str1[i];
            }
        }
        return s;
    }
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        String oldDir = classOutputFolder;
        classOutputFolder = saveFile();//for java
        if (classOutputFolder == null) {
            classOutputFolder = oldDir;
        } else {
            dontSave = false;
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        About abt = new About(Editor.this, true);
        abt.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        chooseColor();
}//GEN-LAST:event_jMenuItem8ActionPerformed

    private void styleColorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleColorBtnActionPerformed
        chooseColor();
    }//GEN-LAST:event_styleColorBtnActionPerformed

    private void clipUndoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipUndoBtnActionPerformed
        try {

            undoMgr.get(jTabbedPane2.getSelectedIndex()).undo();
        } catch (CannotRedoException ex) {
            System.out.println("Error. Can't Undo Text");
        }
        updateButtons();
    }//GEN-LAST:event_clipUndoBtnActionPerformed

    private void clipRedoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipRedoBtnActionPerformed
        try {

            undoMgr.get(jTabbedPane2.getSelectedIndex()).redo();
        } catch (CannotRedoException ex) {
            System.out.println("Error. Can't Redo Text");
        }
        updateButtons();
    }//GEN-LAST:event_clipRedoBtnActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        clipUndoBtnActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        clipRedoBtnActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed


        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthStr;
        if (month < 10) {
            monthStr = "0" + month;
        } else {
            monthStr = "" + month;
        }

        int year = calendar.get(Calendar.YEAR);
        String text = ("  " + date + "/" + monthStr + "/" + year + "   ||   " + hour / 10 + hour % 10 + ":" + minute / 10 + minute % 10 + ":" + second / 10 + second % 10 + "  ");
        try {
            paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().insertString(paneAreas.get(jTabbedPane2.getSelectedIndex()).getCaretPosition(), text, null);
        } catch (BadLocationException ex) {
            System.out.println("Bad Location Error. Can't Insert Text");
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void printBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printBtnActionPerformed

        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        try {
            String s = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();
            String s1 = "";
            String str[] = jEditorPane1.getText().split(" ");
            for (int i = 0; i < str.length; i++) {
                s1 += removeHTML(str[i]);
            }
            if (currentClassName != null) {
                paneAreas.get(jTabbedPane2.getSelectedIndex()).setText((currentClassName + "." + fileExtenssion + ":\n\n"
                        + paneAreas.get(jTabbedPane2.getSelectedIndex()).getText() + "\n\nOutput:\n\n" + s1));
            }

            paneAreas.get(jTabbedPane2.getSelectedIndex()).print();
            if (currentClassName != null) {
                paneAreas.get(jTabbedPane2.getSelectedIndex()).setText(s);
            }

        } catch (PrinterException ex) {
            System.out.println("Printer Eror. Can't print data.");
        }
    }//GEN-LAST:event_printBtnActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        printBtnActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    public static String compile(File f) {
        String log = "C:/MinGW/bin/mingw32-g++.exe " + f.getName();
        int ret = -1;
        try {
            String s = null;
            //change this string to your compilers location
            Process p = Runtime.getRuntime().exec("cmd.exe /C  \"C:/MinGW/bin/mingw32-g++.exe\" " + f.getName(), null, new File(classOutputFolder));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            boolean error = false;

            p.waitFor();
            if (p.exitValue() == 0) {
                success = true;
            } else {
                success = false;
                System.out.println("\n\nCompiler mingw32-g++\n\nmight not be installed at location C:/MinGW/bin/mingw32-g++.exe\n\nPlease install MinGw compiler in C: drive to compile and run C/C++ file\n\n");
            }

            log += "\n";
            while ((s = stdError.readLine()) != null) {
                log += s;
                error = true;
                log += "\n";
            }
            if (error == false) {
                log += "Compilation successful !!!";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return log;
    }

    public static int runProgram() {
        int ret = -1;
        String log = "";
        try {
            String s = null;
            //change this string to your compilers location

            //    String[] command = new String[4];
            //      command[0] = "cmd.exe";// a.exe
            //        command[1] = "/c";
            //          command[2] = "start";
//command[3] = "cmd.exe /k a.exe";
//command[4] = "a.exe";
//command[5] = "pause";
//command[6] = "exit";
            long startTime = System.currentTimeMillis();
            Process p = Runtime.getRuntime().exec("cmd /c start cmd.exe /s /k \"a.exe & echo. & echo. & echo Finish Time: " + (System.currentTimeMillis() - startTime) + " & pause & exit\"", null, new File(classOutputFolder));// %TIME%

            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((s = stdError.readLine()) != null) {
                log += s + "\n";
            }
            log += "\n";
            while ((s = stdIn.readLine()) != null) {
                log += s + "\n";
            }

            ret = p.exitValue();
            System.out.println(log + "\nExit Value " + ret);
        } catch (Throwable t) {
            t.printStackTrace();
            return ret;
        }
        return ret;
    }
    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed


        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        jEditorPane1.setText("");
        int i;
        int countIndex;
        String fileContent = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();

//                                    System.out.println(fileContent+"<b><font color=blue>File name "+fileName+" classOutputFolder "+classOutputFolder
  //                                          +"file    >"+files.get(jTabbedPane2.getSelectedIndex()).getName());
        if (files.get(jTabbedPane2.getSelectedIndex()) == null || files.get(jTabbedPane2.getSelectedIndex()).length() == 0) {// || classOutputFolder == null || fileName == null

            String oldDir = classOutputFolder;
    //                        System.out.println(fileContent+"<b><font color=red>File name "+fileName+" classOutputFolder "+classOutputFolder
      //                                      +"file    "+files.get(jTabbedPane2.getSelectedIndex()));
            classOutputFolder = saveFile();//for java
        //                                System.out.println(fileContent+"<b><font color=green>File name "+fileName+" classOutputFolder "+classOutputFolder
          //                                  +"file    "+files.get(jTabbedPane2.getSelectedIndex()));
           jEditorPane1.setText("");
            if (classOutputFolder == null) {
                classOutputFolder = oldDir;
            } else {
                dontSave = false;
            }
            if (classOutputFolder == null) {
                jEditorPane1.setText("<b><font color=red>File must be saved. So, please save the file...!!!!!!</font></b><br><br>");
                return;
            }// else {
            // jMenuItem17ActionPerformed(evt);
            //}// return;
            
        }
        String fn = files.get(jTabbedPane2.getSelectedIndex()).getName();

        String fileType = fn.substring(fn.lastIndexOf(".") + 1, fn.length());

        if (fileType.equalsIgnoreCase("java")) {
            fileName = fn.substring(0, fn.indexOf(".")).trim();
            currentClassName = new StringBuffer(fileName);
            //System.out.println("\nClass: " + currentClassName + "\n");
            classNames.set(jTabbedPane2.getSelectedIndex(), currentClassName);
        } else if (fileType.equalsIgnoreCase("c") || fileType.equalsIgnoreCase("cpp")) {
            
            compileCpp();
            return;
        } else if (fileType.equalsIgnoreCase("htm") ||fileType.equalsIgnoreCase("html")) {
          /*  try {
                File file1=files.get(jTabbedPane2.getSelectedIndex());
                jEditorPane1.setPage(file1.toURI().toURL());
                //  SimpleWebBrowserExample.main(file1 + "");
                jButton10.setVisible(true);
                jButton11.setVisible(true);
                jLabel10.setVisible(true);

                jLabel10.setText("" + file1.getName());
                try {
                    htmlpages.add(file1.toURI().toURL());
                } catch (Exception ex) {
                    System.out.println("Can't Open this page. \n\n");
                }
                htmlPageCounter++;
            } catch (Exception ex) {
                System.out.println("Can't Open this page.");
            }
            */ 
                                    try {
                            Runtime.getRuntime().exec("cmd /c start " + files.get(jTabbedPane2.getSelectedIndex()));
                        } catch (IOException ex) {
                            System.out.println("Error: Can't Execute Command");
                        }

            return;
             
        } else if (fileContent.length() == 0) {
            jEditorPane1.setText("<b><font color=red>Nothing to Compile.</font></b><br><br>");
            return;
        } else if (files.get(jTabbedPane2.getSelectedIndex()).getName().length() == 0) {

            String oldDir = classOutputFolder;
            classOutputFolder = saveFile();//for java
            if (classOutputFolder == null) {
                classOutputFolder = oldDir;
            } else {
                dontSave = false;
            }
            if (classOutputFolder == null) {
                jEditorPane1.setText("<b><font color=red>File must be saved. So, please save the file...!!!</font></b><br><br>");
                return;
            }
        } else {
            if (language.equals("java")) {
                jEditorPane1.setText("<b><font color=red>File must be a Java file...!!!</font></b><br><br>");
            } else if (language.equals("c")) {
                jEditorPane1.setText("<b><font color=red>File must be a C/C++ file...!!!</font></b><br><br>");
            } else if (language.equals("html")) {
                jEditorPane1.setText("<b><font color=red>File must be a Html file...!!!</font></b><br><br>");
            }
            return;
        }
        /*if (fileName != null) {
         String fn = files.get(jTabbedPane2.getSelectedIndex()).getName();
         fileName = fn.substring(0, fn.indexOf(".")).trim();
         int indexP = fileContent.indexOf("public class");
         int indexC = fileContent.indexOf("class");

         if (indexP != -1) {
         currentClassName = new StringBuffer(fileContent.substring(indexP + "public class".length(), indexP + "public class".length()
         + fileContent.substring(indexP + "public class".length(),
         fileContent.length()).indexOf("{")).trim());
         } else if (indexC != -1) {
         currentClassName = new StringBuffer(fileContent.substring(indexC + "class".length(), indexC + "class".length()
         + fileContent.substring(indexC + "class".length(),
         fileContent.length()).indexOf("{")).trim());
         } else {
         System.out.println("\n\n Java Class is missing.\n\n");
         return;
         }

         if (!fileName.equalsIgnoreCase(currentClassName + "")) {
         System.out.println("\n\nFile Name Must be same as class Name.\n\n");
         return;
         }


         System.out.println("\n\nClass: " + currentClassName + "\n\n");
         //currentClassName = new StringBuffer(fileName);
         classNames.set(jTabbedPane2.getSelectedIndex(), currentClassName);
         } else {
         System.out.println("\n\nFile must be saved. So, please save the file\n\n");
         return;
         }
         */
        pack = "package";
        if ((countIndex = fileContent.indexOf(pack)) != -1) {
            StringBuilder str = new StringBuilder(fileContent.substring(countIndex + pack.length() + 1));
            i = str.indexOf(";");

            String subStr = fileContent.substring(0, countIndex);
            String packLine[] = subStr.split("\n");

            String packLineText = packLine[packLine.length - 1];

            if (packLineText.indexOf("//") != -1 || packLineText.indexOf("/*") != -1) {
                packLineText = null;
            } else {
                currentPackName = new StringBuffer(str.substring(0, i).trim());
                packNames.set(jTabbedPane2.getSelectedIndex(), currentPackName);
            }
        } else {
            currentPackName = null;
        }
        if (currentPackName != null && currentClassName!=null) {
            currentPackClassName = new StringBuffer(currentPackName + "." + currentClassName);
            packClassNames.set(jTabbedPane2.getSelectedIndex(), currentPackClassName);
            //System.out.println("Package: " + currentPackName);
        } else if(currentClassName!=null){
            currentPackClassName = new StringBuffer(currentClassName);
            packClassNames.set(jTabbedPane2.getSelectedIndex(), currentPackClassName);
            //System.out.println("Package: " + currentPackName + "(Dafault)\n\n");
        }

        if (!setOK) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(new File("Java_Home.txt")));
                java_home = br.readLine();

                if (java_home == null) {
                    jEditorPane1.setText("<font color=red>Java_Home must be set</font><br><br>");
                    new JavaHome(Editor.this, true);
                } else {
                    setOK = true;
                    try {
                        System.setProperty("java.home", java_home);
                    } catch (Exception e) {
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File not Found... Java_Home.txt");
            } catch (IOException ex) {
                System.out.println("Does Not Exist or Can't read the file");
            } catch (Exception ex) {
                System.out.println("File Error");
            }
        }

        if (setOK) {
            try {
                JavaFileObject javaFileObject = getJavaFileObject(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
                //System.out.println(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
                if (paneAreas.get(jTabbedPane2.getSelectedIndex()).getText().length() == 0) {

                    String oldDir = classOutputFolder;
                    classOutputFolder = openFileDialog();
                    if (classOutputFolder == null) {
                        classOutputFolder = oldDir;
                    } else {
                        dontSave = false;
                    }
                } else if (javaFileObject != null) {
                    Iterable<? extends JavaFileObject> files1 = Arrays.asList(javaFileObject);

                    try {
                        //2.Compile your files by JavaCompiler
                        currentFile = files.get(jTabbedPane2.getSelectedIndex());
                        files.set(jTabbedPane2.getSelectedIndex(), currentFile);
                        if ((classOutputFolder == null && !dontSave && currentFile == null)) {//"./src"

                            String oldDir = classOutputFolder;
                            classOutputFolder = saveFile();//for java
                            if (classOutputFolder == null) {
                                classOutputFolder = oldDir;
                            } else {
                                dontSave = false;
                            }
                        }
                        if (classOutputFolder != null && fileName != null) {

                            FileWriter fw;
                            try {
                                fw = new FileWriter(currentFile);
                                fw.write(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
                                fw.close();
                            } catch (Exception e) {
                                System.out.println("File Not Found. " + " Can't save file");
                            }

                            jEditorPane1.setText("<html><body>" + compileIt(files1) + "<br><br></body></html>");
                            errorMsg = new StringBuilder();
                            jEditorPane1.setCaretPosition(0);
                        } else {
                            jEditorPane1.setText("<b><font color=red>Please Save File...!!!</font><b><br><br>");
                            jEditorPane1.setCaretPosition(0);
                        }
                    } catch (FileNotFoundException ex) {
                        System.out.println("File Not Found. or Does Not Exist");
                    }
                } else {
                    jEditorPane1.setText("<b><font color=red>Please save the File first...!!!</font><b><br><br>");
                    jEditorPane1.setCaretPosition(0);
                }
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found. or Does Not Exist");
            } catch (IOException ex) {
                System.out.println("Does Not Exist or Can't read the file");
            } catch (Exception ex) {
                System.out.println("File Error");
            }
        }


    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed

        jEditorPane1.setText("");
        String fn = files.get(jTabbedPane2.getSelectedIndex()).getName();

        String fileType = fn.substring(fn.lastIndexOf("."), fn.length());
        if (fileType.indexOf("java") != -1) {
            if (currentClassName != null && currentClassName.length() != 0) {
                runInCMD();
            }
        } else if (fileType.indexOf("c") != -1) {
            runProgram();
        }else if (fileType.indexOf("htm") != -1) {
            
                        try {
                            Runtime.getRuntime().exec("cmd /c start " + files.get(jTabbedPane2.getSelectedIndex()));
                        } catch (IOException ex) {
                            System.out.println("Error: Can't Execute Command");
                        }

        }
        //  runInWin();


    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        jMenuItem17ActionPerformed(evt);

//        if (currentClassName != null && currentClassName.length() != 0) {

        if (success) {
            jMenuItem16ActionPerformed(evt);
        }
        //      }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jMenuItem1ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jMenuItem6ActionPerformed(evt);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jMenuItem2ActionPerformed(evt);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jMenuItem5ActionPerformed(evt);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBoxMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItem4ActionPerformed
        //get a reference to the device.
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        DisplayMode dispMode = device.getDisplayMode();
        //save the old display mode before changing it.
        DisplayMode dispModeOld = device.getDisplayMode();

        try {
            if (jCheckBoxMenuItem4.isSelected()) {
                //change to fullscreen.
                //hide everything
                setVisible(false);
                //remove the frame from being displayable.
                dispose();
                //remove borders around the frame
                setUndecorated(true);
                //make the window fullscreen.
                device.setFullScreenWindow(this);
                //attempt to change the screen resolution.
                device.setDisplayMode(dispMode);
                setResizable(false);
                setAlwaysOnTop(false);
                //show the frame
                setVisible(true);

            } else {
                device.setDisplayMode(dispModeOld);
                //hide the frame so we can change it.
                setVisible(false);
                //remove the frame from being displayable.
                dispose();
                //put the borders back on the frame.
                setUndecorated(false);
                //needed to unset this window as the fullscreen window.
                device.setFullScreenWindow(null);
                //recenter window
                setLocationRelativeTo(null);
                setResizable(true);
                //reset the display mode to what it was before
                //we changed it.
                setExtendedState(Editor.MAXIMIZED_BOTH);
                setVisible(true);
            }
            jToolBar1.setOpaque(true);
        } catch (Exception e) {
            System.err.println(" Cannot change display mode");
        }
        //         jPanel9.setSize(jToolBar1.getWidth(), jToolBar1.getHeight());

    }//GEN-LAST:event_jCheckBoxMenuItem4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        /*       try {
         jInternalFrame4.setMaximum(true);
         } catch (PropertyVetoException ex) {
         System.out.println("Can't Maximise Output frame.");
         }
         */
        String nameOS = "os.name";
        String versionOS = "os.version";
        String architectureOS = "os.arch";
        jEditorPane1.setText("");

        StringBuilder sb = new StringBuilder();

        sb.append("<hr><font color=red>The information about OS:<br><hr>");
        sb.append("<br><font color=green>Name of the OS: ").append(System.getProperty(nameOS));
        sb.append("<br><font color=green>Version of the OS: ").append(System.getProperty(versionOS));
        sb.append("<br><font color=green>Architecture of The OS: ").append(System.getProperty(architectureOS));
        sb.append("<br><br><hr><font color=red>Hard Disk Information:<br><hr>"
                + "<br><font color=green>Available processors (cores): ").append(Runtime.getRuntime().availableProcessors());

        /* Total amount of free memory available to the JVM */
        sb.append("<br><font color=green>Free Memory: ").append(Runtime.getRuntime().freeMemory() / (1024 * 1024)).append("MB");

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        sb.append("<br><font color=green>Maximum Memory: ").append(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory / (1024 * 1024) + "MB");

        /* Total memory currently in use by the JVM */
        sb.append("<br><font color=green>Total Memory: ").append(Runtime.getRuntime().totalMemory() / (1024 * 1024)).append("MB");

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */

        sb.append("<br><br><hr>");

        sb.append("<font color=blue>Hard Drive | Total Space | Free Space");
        for (File r : roots) {
            if (r.getTotalSpace() != 0) {

                sb.append("<hr>");
                try {
                    sb.append("<br><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(r.getCanonicalPath()).append(""
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "").append(r.getTotalSpace() / (1024 * 1024 * 1024)).append("GB" + ""
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "").append(r.getFreeSpace() / (1024 * 1024 * 1024)).append("GB");
                } catch (IOException ex) {
                    sb.append("<br><font color=green>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(r.getAbsolutePath()).append(""
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "").append(r.getTotalSpace() / (1024 * 1024 * 1024)).append("GB" + ""
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "").append(r.getFreeSpace() / (1024 * 1024 * 1024)).append("GB");
                    Logger
                            .getLogger(Editor.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        jEditorPane1.setText(sb.toString() + "<br><br><hr><br><br>");

        /*
         Properties systemProps = System.getProperties();
         Set<Entry<Object, Object>> sets = systemProps.entrySet();
         System.out.println("\n\n\tJavasystems properties:\n\t-----------------------------------\n");
         for (Entry<Object, Object> entry : sets) {
         System.out.println("\tname: \t" + entry.getKey() + ",\t value: \t" + entry.getValue());
         }
         */
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        new JavaHome(Editor.this, true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            System.out.println("Look and Feel Not Found.");
        } catch (InstantiationException ex) {
            System.out.println("Look and Feel not installed.");
        } catch (IllegalAccessException ex) {
            System.out.println("Look and Feel not accessed.");
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Look and Feel not Supported.");
        }

        // TODO add your handling code here:
        SwingUtilities.updateComponentTreeUI(this);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed

        jButton18.setVisible(true);
        jLabel6.setText("Find:");
        jPanel7.setVisible(false);
        jPanel8.setVisible(false);
        jPanel2.setVisible(false);
        jPanel4.setVisible(true);

        if (jTextField3.getText().length() == 0) {
            jButton16.setEnabled(false);
            jButton17.setEnabled(false);
        } else {
            jButton16.setEnabled(true);
            jButton17.setEnabled(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed

        jButton18.setVisible(false);
        jLabel6.setText("Find What:");

        jPanel7.setVisible(true);
        jPanel4.setVisible(true);
        jPanel8.setVisible(false);
        jPanel2.setVisible(false);
        if (jTextField3.getText().length() == 0) {
            jButton16.setEnabled(false);
            jButton17.setEnabled(false);
            jButton19.setEnabled(false);
            jButton20.setEnabled(false);
        } else {
            jButton16.setEnabled(true);
            jButton17.setEnabled(true);
            jButton19.setEnabled(true);
            jButton20.setEnabled(true);
        }


    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed

        jPanel8.setVisible(true);
        jPanel4.setVisible(false);
        jPanel7.setVisible(false);
        jPanel2.setVisible(false);
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jMenuItem25ActionPerformed(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        jMenuItem22ActionPerformed(evt);       // TODO add your handling code here:

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jMenuItem24ActionPerformed(evt);       // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextPane1MouseClicked
        evt.getComponent().setFocusable(true);
        int s = evt.getButton();
        if (s == MouseEvent.BUTTON3) {
            jpm.show(evt.getComponent(), evt.getX(), evt.getY());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextPane1MouseClicked

    private void jEditorPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jEditorPane1MouseClicked
        int s = evt.getButton();
        if (s == MouseEvent.BUTTON3) {
            jpm1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jEditorPane1MouseClicked

    private void txtURLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtURLActionPerformed
        jEditorPane1.setText("<font color=green>Type the name of a program, folder, document, or Internet Resource,"
                + " and Operation System will open it for you. <br><br></font><font color=blue>For Example:<br>Calc, Write, Notepad, CMD, Paint ...etc.</font><br><br>");
        try {
            Runtime.getRuntime().exec("cmd /c start " + txtURL.getText());
        } catch (IOException ex) {
            System.out.println("Error: Can't Execute Command");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtURLActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        jMenuItem17ActionPerformed(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        jMenuItem16ActionPerformed(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jEditorPane1.setContentType("text/html");
        try {
            if (htmlPageCounter > 0) {
                htmlPageCounter--;
                //jTextField2.setText("size " + htmlpages.size() + "htmlPageCounter " + htmlPageCounter);

                jEditorPane1.setPage(htmlpages.get(htmlPageCounter));

                String fnd = "" + htmlpages.get(htmlPageCounter).getFile().toString();
                jLabel10.setText(fnd.substring(fnd.lastIndexOf("/") + 1, fnd.length()));

                jButton11.setEnabled(true);
            }
        } catch (Exception ex) {
//            jButton10.setEnabled(false);
//            System.out.println("Can't set the Page." + ex);
            //          System.out.println("index zero is not valid" + htmlPageCounter);
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        jEditorPane1.setContentType("text/html");
        try {
            if (htmlPageCounter < htmlpages.size() - 1) {
                htmlPageCounter++;
                //jTextField2.setText("size " + htmlpages.size() + "htmlPageCounter " + htmlPageCounter);

                jEditorPane1.setPage(htmlpages.get(htmlPageCounter));
                String fnd = "" + htmlpages.get(htmlPageCounter).getFile().toString();
                jLabel10.setText(fnd.substring(fnd.lastIndexOf("/") + 1, fnd.length()));

                jButton10.setEnabled(true);
            }
        } catch (Exception ex) {
//            System.out.println("Can't set the Page.");
//            jButton11.setEnabled(false);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed

        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        currentFile = files.get(jTabbedPane2.getSelectedIndex());
        files.set(jTabbedPane2.getSelectedIndex(), currentFile);


        if ((classOutputFolder == null && !dontSave && currentFile == null)) {//"./src"

            String oldDir = classOutputFolder;
            classOutputFolder = saveFile();//for java
            if (classOutputFolder == null) {
                classOutputFolder = oldDir;
            } else {
                dontSave = false;
            }
        }
        if (classOutputFolder != null && fileName != null) {

            FileWriter fw;
            try {
                fw = new FileWriter(currentFile);
                fw.write(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
                fw.close();
            } catch (Exception e) {
                classOutputFolder = saveFile();
                dontSave = false;
                //System.out.println("File Not Found. " + " Can't save file");
            }
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged

        // System.out.println("<<<"+jTabbedPane2.getSelectedIndex()+">>>"+jTabbedPane2.getTabCount());
        if (jTabbedPane2.getSelectedIndex() < (jTabbedPane2.getTabCount() - 1)) {
            //bcoz there r 2 tab already  and last tab is only for create new tab

            Font f = paneAreas.get(jTabbedPane2.getSelectedIndex()).getFont();
            String name = f.getName();
            int style = f.getStyle();
            //int size=f.getSize();
            int newSize = jSlider1.getValue();

            for (int i = 0; i < jTabbedPane2.getTabCount() - 1; i++) {

                paneAreas.get(i).setFont(new Font(name, style, newSize));//(zoom += 5)
                textAreas.get(i).setFont(new Font(name, style, newSize));//(zoom)
            }


            f = jEditorPane1.getFont();
            name = f.getName();
            style = f.getStyle();
            jEditorPane1.setFont(new Font(name, style, newSize));//(zoom += 5)

            f = jTree1.getFont();
            name = f.getName();
            style = f.getStyle();
            jTree1.setFont(new Font(name, style, newSize > 25 ? 25 : newSize));//(zoom += 5)

            for (int i = 0; i < jMenuItm.length; i++) {
                f = jMenuItm[i].getFont();
                name = f.getName();
                style = f.getStyle();
                jMenuItm[i].setFont(new Font(name, style, newSize > 25 ? 25 : newSize));//(zoom += 5)
            }

            f = jPMenu2Itm2.getFont();
            name = f.getName();
            style = f.getStyle();
            jPMenu2Itm2.setFont(new Font(name, style, newSize > 25 ? 25 : newSize));//(zoom += 5)

            for (int i = 0; i < jPMenuItm.length; i++) {
                f = jPMenuItm[i].getFont();
                name = f.getName();
                style = f.getStyle();
                jPMenuItm[i].setFont(new Font(name, style, newSize > 25 ? 25 : newSize));//(zoom += 5)
            }

            for (int i = 0; i < jMenuItmTree.length; i++) {
                f = jMenuItmTree[i].getFont();
                name = f.getName();
                style = f.getStyle();
                jMenuItmTree[i].setFont(new Font(name, style, newSize > 25 ? 25 : newSize));//(zoom += 5)
            }




            jLabel4.setText(newSize + "%");        // TODO add your handling code here:
        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        paneAreas.get(jTabbedPane2.getSelectedIndex()).selectAll();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        clipDelBtnActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        clipPasteBtnActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        clipCopyBtnActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        clipCutBtnActionPerformed(evt);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void clipDelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipDelBtnActionPerformed
        if (paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText() == null) {
            paneAreas.get(jTabbedPane2.getSelectedIndex()).setText("");//textPlainOrHtml

        } else {
            paneAreas.get(jTabbedPane2.getSelectedIndex()).replaceSelection("");
        }
    }//GEN-LAST:event_clipDelBtnActionPerformed

    private void clipPasteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipPasteBtnActionPerformed
        try {
            Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            String text = null;
            if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {

                text = (String) t.getTransferData(DataFlavor.stringFlavor);

            }
            int caretPos = paneAreas.get(jTabbedPane2.getSelectedIndex()).getCaretPosition();
            if (caretPos < 0) {
                caretPos = 0;
            }
            paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().insertString(caretPos, text, null);
        } catch (BadLocationException | UnsupportedFlavorException ex) {
            System.out.println("Bad Location Error. Can't Insert Text");
        } catch (IOException ex) {
            System.out.println("Error, Can't read or writedata.");
        }

    }//GEN-LAST:event_clipPasteBtnActionPerformed

    private void clipCopyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipCopyBtnActionPerformed
        if (paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText() == null) {
            StringSelection ss = new StringSelection(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        } else {
            StringSelection ss = new StringSelection(paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        }
    }//GEN-LAST:event_clipCopyBtnActionPerformed

    private void clipCutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clipCutBtnActionPerformed

        if (paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText() == null) {
            StringSelection ss = new StringSelection(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

            paneAreas.get(jTabbedPane2.getSelectedIndex()).setText("");

        } else {
            StringSelection ss = new StringSelection(paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText());
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
            paneAreas.get(jTabbedPane2.getSelectedIndex()).replaceSelection("");
        }
    }//GEN-LAST:event_clipCutBtnActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jMenuItem20ActionPerformed(evt);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        txtURLActionPerformed(evt);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jTextField3CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextField3CaretUpdate

        if (jTextField3.getText().length() == 0) {
            jButton16.setEnabled(false);
            jButton17.setEnabled(false);
            jButton19.setEnabled(false);
            jButton20.setEnabled(false);
        } else {
            jButton16.setEnabled(true);
            jButton17.setEnabled(true);
            jButton19.setEnabled(true);
            jButton20.setEnabled(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3CaretUpdate

    public int[] WordFind(String s, String w) {
        int count = 0;
        int pos[] = new int[s.length()], k = 0;
        String s1, s2;
        for (int i = 0, j = 0; i < s.length();) {
            s1 = w.charAt(j) + "";
            s2 = s.charAt(i) + "";
            if ((s1.equalsIgnoreCase(s2) && !flag) || (s1.equals(s2) && flag)) {
                i++;
                j++;
                if (j == w.length()) {
                    j = 0;
                    count++;
                    pos[k] = i;
                    k++;
                }
            } else {
                i++;
                j = 0;
            }
        }
        int info[] = new int[count + 1];
        info[0] = count;
        for (int i = 1; i < info.length; i++) {
            info[i] = pos[i - 1];
        }


        return info;
    }
    private boolean flag;
    private int backwordcount;
    private int forwordcount = 1;
    private boolean newFlag = true;

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed

        String word1 = jTextField3.getText();
        if (word1.length() != 0) {
            paneAreas.get(jTabbedPane2.getSelectedIndex()).requestFocusInWindow();

            if (jCheckBox1.isSelected()) {
                flag = true;
            } else {
                flag = false;
            }

            String str = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();
            int data1[] = WordFind(str, word1);

            jLabel8.setText(data1[0] > 1 ? data1[0] + " Matches" : data1[0] + " Match");

            if (newFlag) {
                //  System.out.println(word + " found at position");

                // data1 = WordFind(str, word1);

                /*for (int i = 1; i < data.length; i++) {
                 System.out.println(data[i]);
                 }
                 */ backwordcount = data1.length - 1;
                newFlag = false;
            }
            //edt.jTextPane1.setSelectionStart(data[i]-word.length());
            //edt.jTextPane1.setSelectionEnd(data[i]);

            paneAreas.get(jTabbedPane2.getSelectedIndex()).setFocusable(true);

            paneAreas.get(jTabbedPane2.getSelectedIndex()).setSelectedTextColor(Color.red);

            try {
                paneAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(0);
                paneAreas.get(jTabbedPane2.getSelectedIndex()).select(data1[forwordcount] - word1.length(), data1[forwordcount]);
                forwordcount++;

            } catch (Exception e) {
                forwordcount = 1;
                backwordcount = data1.length - 1;
                newFlag = true;
                JOptionPane.showMessageDialog(this, "Can not Found: " + word1, "Information", JOptionPane.INFORMATION_MESSAGE);
                //e.printStackTrace();
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed


        String word1 = jTextField3.getText();

        if (word1.length() != 0) {

            paneAreas.get(jTabbedPane2.getSelectedIndex()).requestFocusInWindow();

            if (jCheckBox1.isSelected()) {
                flag = true;
            } else {
                flag = false;
            }

            String str = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();
            int data1[] = WordFind(str, word1);

            jLabel8.setText(data1[0] > 1 ? data1[0] + " Matches" : data1[0] + " Match");

            if (newFlag) {
//            System.out.println("No. of maches is=" + data1[0]);
                //          System.out.println(word1 + " found at position");

                data1 = WordFind(str, word1);
                /*
                 for (int i = 1; i < data1.length; i++) {
                 System.out.println(data1[i]);
                 }
                 */
                backwordcount = data1.length - 1;
                newFlag = false;
            }
            //edt.jTextPane1.setSelectionStart(data[i]-word.length());
            //edt.jTextPane1.setSelectionEnd(data[i]);

            paneAreas.get(jTabbedPane2.getSelectedIndex()).setFocusable(true);

            paneAreas.get(jTabbedPane2.getSelectedIndex()).setSelectedTextColor(Color.red);

            try {
                paneAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText().length() - 10);
                if (backwordcount == 0) {
                    throw new Exception();
                }
                paneAreas.get(jTabbedPane2.getSelectedIndex()).select(data1[backwordcount] - word1.length(), data1[backwordcount]);
                backwordcount--;
                /*else{
                 forwordcount=1;
                 backwordcount=data.length-1;
                 newFlag=true;
                 JOptionPane.showMessageDialog(this,word+" Not Found" );
                 }*/
            } catch (Exception e) {
                forwordcount = 1;
                backwordcount = data1.length - 1;
                newFlag = true;
                JOptionPane.showMessageDialog(this, "Can not Found: " + word1, "Information", JOptionPane.INFORMATION_MESSAGE);
                //e.printStackTrace();
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

        jPanel4.setVisible(false);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed

        paneAreas.get(jTabbedPane2.getSelectedIndex()).requestFocusInWindow();

        paneAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(0);

        String selectedText = paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText();

        if (selectedText == null) {
            jButton16ActionPerformed(evt);
        }
        selectedText = paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText();


        if (selectedText != null) {
            // System.out.println(selectedText);


            paneAreas.get(jTabbedPane2.getSelectedIndex()).replaceSelection(jTextField4.getText());
            jLabel11.setText("1 Replaced");
            forwordcount--;
            /*Note: 1 hr error found :
             * we have to do forwordcount--; here because first word replaced here and then only n-1 word is remaining there.
             but it will not need to do backwordcount++; because we are always finding word from starting position of the given string not
             at the end so no problem at all...
             */
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed

        paneAreas.get(jTabbedPane2.getSelectedIndex()).requestFocusInWindow();

        int data[];
        String word;

        try {
            word = jTextField3.getText();
            String str = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();
            data = WordFind(str, word);

            jLabel8.setText(data[0] > 1 ? data[0] + " Matches" : data[0] + " Match");

            if (data[0] == 0) {

                jLabel11.setText(data[0] + " Replaced");
                //       System.out.println(data[0]);
                JOptionPane.showMessageDialog(this, "Can not Found: " + word, "Information", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            jLabel11.setText(data[0] + " Replaced");
            for (int i = 1; i < data.length; i++) {


                /* if(word.length()<jTextField2.getText().length())
                 if(word.equals(jTextField2.getText().substring(0,word.length())))
                 //i++;
                 */


                paneAreas.get(jTabbedPane2.getSelectedIndex()).select(data[i] - word.length(), data[i]);
                String selectedText = paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText();

                if (selectedText != null) {
                    //       System.out.println(selectedText);
                    paneAreas.get(jTabbedPane2.getSelectedIndex()).replaceSelection(jTextField4.getText());
                    //     System.out.println("\t\t\t" + data[i] + "\t" + i);
                    i--;
                } else {
                    JOptionPane.showMessageDialog(this, "Can not Found: " + word, "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                str = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();//.substring(jTextField2.getText().length()+1, edt.jTextPane1.getText().length());
                data = WordFind(str, word);
            }
        } catch (Exception e) {
            //  System.out.println(e);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed

        jPanel4.setVisible(false);
        jPanel7.setVisible(false);         // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed

        String s = jTextField5.getText();
        goToLineNumber(s);
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        jPanel8.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        jPanel2.setVisible(true);

        jPanel8.setVisible(false);
        jPanel4.setVisible(false);
        jPanel7.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        jPanel2.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        /*  if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

         String text = jTextField1.getText();
         jTextField1.setText("");

         //   System.out.println(text); 
         inWriter.println(text);
         }
         */// TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed

        Dimension desktopSize = jDesktopPane1.getSize();
        int dWidth = (int) desktopSize.getWidth();
        int dHeight = (int) desktopSize.getHeight();
        try {
            /*        jInternalFrame2.reshape(0, jToolBar1.getHeight(), dWidth / 7, dHeight - jToolBar1.getHeight());
             jInternalFrame3.reshape((dWidth / 7), jToolBar1.getHeight(),dWidth * 4 / 7, dHeight - jToolBar1.getHeight());
             jInternalFrame4.reshape((dWidth / 7) + (dWidth * 4 / 7), jToolBar1.getHeight(),dWidth * 2 / 7, dHeight - jToolBar1.getHeight());
             */
            jInternalFrame2.setMaximum(true);
            jInternalFrame3.setMaximum(true);
            jInternalFrame4.setMaximum(true);


        } catch (PropertyVetoException ex) {
            Logger.getLogger(Editor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        jInternalFrame2.setSize(dWidth / 6, dHeight - jToolBar1.getHeight());
        jInternalFrame3.setSize((dWidth * 4 / 6) - 150, dHeight - jToolBar1.getHeight());
        jInternalFrame4.setSize((dWidth / 6) + 150, dHeight - jToolBar1.getHeight());

        //jInternalFrame5.setLocation(0, 0);
        jInternalFrame2.setLocation(0, jToolBar1.getHeight());
        jInternalFrame3.setLocation((dWidth / 6), jToolBar1.getHeight());
        jInternalFrame4.setLocation((dWidth * 5 / 6) - 150, jToolBar1.getHeight());

        jToolBar1.setSize(dWidth, jToolBar1.getHeight());

    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        Color initialcolor = Color.RED;
        Color color = JColorChooser.showDialog(this, "Select a color", initialcolor);
        if (color != null) {

            UIManager.put("nimbusBase", color);
            UIManager.put("nimbusBlueGrey", color);
            //customizeNimbusLaF();
        }
        dispose();
        setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jTabbedPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane2MouseClicked


        int s = evt.getButton();
        if (s == MouseEvent.BUTTON3) {

            if (jTabbedPane2.getSelectedIndex() == 1) {
                jTabbedPane2.setSelectedIndex(0);
            }
            jpm2.show(evt.getComponent(), evt.getX(), evt.getY());
        } else {
            if (jTabbedPane2.getSelectedIndex() == ((jTabbedPane2.getTabCount() > 1) ? (jTabbedPane2.getTabCount() - 1) : 0)) {
                addClosableTab(jTabbedPane2, "Tab " + tabCount + " ");//newTab("Tab " + ii++);                
            }
            if (jTabbedPane2.getSelectedIndex() < (jTabbedPane2.getTabCount() - 1)) {
                textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                try {
                    updateButtons();
                } catch (Exception ee) {
                }
                //      ((JScrollPane)jTabbedPane2.getSelectedComponent()).setColumnHeaderView(jPanel15);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2MouseClicked

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String s = jTextField5.getText();
            if (s.length() != 0) {
                goToLineNumber(s);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton16ActionPerformed(null);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jButton19ActionPerformed(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked

        if (evt.getClickCount() == 2) {
            jMenuItem25ActionPerformed(null);        // TODO add your handling code here:
        }
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed

        jEditorPane1.setText("Not Working...");

/*
        JFileChooser chooser2 = new JFileChooser();
        chooser2.setPreferredSize(new Dimension(600, 500));
        chooser2.setMultiSelectionEnabled(true);

        chooser2.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory() || f.getName().endsWith("java")) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public String getDescription() {
                return "Java Files (*.java)";

            }
        });

        String docFile = "";
        int returnVal = chooser2.showOpenDialog(this);
        File curDir2 = chooser2.getCurrentDirectory();
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File[] ff = chooser2.getSelectedFiles();
            List<String> docFileList = new ArrayList<>();

            docFileList.add("javadoc");
            docFileList.add("-private");
            docFileList.add("-d");
            docFileList.add(curDir2.toString() + "/doc");
            //           docFileList.add("-sourcepath");
            //         docFileList.add(curDir2.toString());

            if (ff != null && ff.length == 1) {
                //docFile = ff[0].getAbsolutePath();
                docFileList.add(ff[0].getAbsolutePath());
            }
            if (ff != null && ff.length > 1) {
                for (File file : ff) {
                    //docFile += file.getAbsolutePath() + "\", \"";
                    docFileList.add(file.getAbsolutePath());
                }
            }
            /*
             if(ff.length>1){
                
             docFile=docFile.substring(0,docFile.lastIndexOf("\","));
             }
             */
  /*          try {
//            com.sun.tools.javadoc.Main.execute(new String[]{"-d", curDir2 + "/doc","-sourcepath", curDir2.toString(),docFile});
                com.sun.tools.javadoc.Main.execute(docFileList.toArray(new String[0]));
                if (!curDir.equals(curDir2)) {
                    new treeUpdates(curDir2);
                }
            } catch (Exception e) {
                System.out.println("Can't make javadoc... error occured during operation");
            }

        } else {
            System.out.println("Operation is CANCELLED :(");
        }
*/
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jRadioButtonMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem1ActionPerformed
        jEditorPane1.setText("<font color=red>Language: C/C++ Selected</font><br><br>");
        jRadioButtonMenuItem2.setSelected(false);
        jRadioButtonMenuItem3.setSelected(false);
        language = "c";
        
        if (curDir!=null) {
            new treeUpdates(curDir);
        }else if(classOutputFolder == null){
            new treeUpdates(new File("."));            
        }
        else {
            new treeUpdates(new File(classOutputFolder));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonMenuItem1ActionPerformed

    private void jRadioButtonMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem2ActionPerformed
        jEditorPane1.setText("<font color=red>Language: JAVA Selected</font><br><br>");
        jRadioButtonMenuItem1.setSelected(false);
        jRadioButtonMenuItem3.setSelected(false);
        language = "java";
        if (curDir!=null) {
            new treeUpdates(curDir);
        }else if(classOutputFolder == null){
            new treeUpdates(new File("."));            
        }
        else {
            new treeUpdates(new File(classOutputFolder));
        }

    }//GEN-LAST:event_jRadioButtonMenuItem2ActionPerformed

    private void jRadioButtonMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItem3ActionPerformed
        jEditorPane1.setText("<font color=red>Language: HTML Selected</font><br><br>");
        jRadioButtonMenuItem1.setSelected(false);
        jRadioButtonMenuItem2.setSelected(false);
        language = "html";
        if (curDir!=null) {
            new treeUpdates(curDir);
        }else if(classOutputFolder == null){
            new treeUpdates(new File("."));            
        }
        else {
            new treeUpdates(new File(classOutputFolder));
        }

    }//GEN-LAST:event_jRadioButtonMenuItem3ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if (curDir != null) {
            new treeUpdates(curDir);
        }
    }//GEN-LAST:event_jButton14ActionPerformed
    private void customizeNimbusLaF() {
        //    UIManager.put("control", Color.lightGray);
        UIManager.put("nimbusAlertYellow", Color.YELLOW);
//        UIManager.put("nimbusBase", Color.darkGray);
        //      UIManager.put("nimbusDisabledText", Color.darkGray);
        UIManager.put("nimbusFocus", Color.blue);
        UIManager.put("nimbusGreen", Color.GREEN);
        UIManager.put("nimbusInfoBlue", Color.blue);
        UIManager.put("nimbusRed", Color.RED);
        UIManager.put("nimbusSelectionBackground",
                Color.blue);

        // UIManager.put("background", Color.lightGray);
        //  UIManager.put("controlDkShadow", Color.darkGray);
        //  UIManager.put("controlShadow", Color.gray);
        UIManager.put("desktop", Color.blue);
        //  UIManager.put("menu", Color.lightGray);
        //UIManager.put("nimbusBorder", Color.gray);
        UIManager.put("nimbusSelection", Color.blue);
        UIManager.put("textBackground", Color.blue);
        UIManager.put("textHighlight", Color.blue);
        //    UIManager.put("textInactiveText", Color.gray);

        // panel
        //   UIManager.put("Panel.background", Color.lightGray);
        //    UIManager.put("Panel.disabled", Color.lightGray);
        //UIManager.put( "Panel.font", );
        UIManager.put("Panel.opaque", true);

        // button
        //    UIManager.put("Button.background", Color.lightGray);
        //    UIManager.put("Button.disabled", Color.lightGray);
        UIManager.put("Button.disabledText", Color.blue);
        // UIManager.put( "Button.font", Color.DEFAULT_FONT );

        // menu
        //    UIManager.put("Menu.background", Color.lightGray);
        //    UIManager.put("Menu.disabled", Color.lightGray);
        //    UIManager.put("Menu.disabledText", Color.darkGray);
        // UIManager.put( "Menu.font", Color.MENU_FONT );
        //     UIManager.put("Menu.foreground", Color.BLACK);
        UIManager.put("Menu[Disabled].textForeground",
                Color.gray);
        //     UIManager.put("Menu[Enabled].textForeground", Color.BLACK);
        //     UIManager.put("MenuBar.background", Color.lightGray);
        //    UIManager.put("MenuBar.disabled", Color.lightGray);
        // UIManager.put( "MenuBar.font", Color.MENU_FONT );
        UIManager.put("MenuBar:Menu[Disabled].textForeground",
                Color.gray);
        UIManager.put("MenuBar:Menu[Enabled].textForeground",
                Color.BLACK);
        //      UIManager.put("MenuItem.background", Color.lightGray);
        //      UIManager.put("MenuItem.disabled", Color.lightGray);
        //     UIManager.put("MenuItem.disabledText", Color.gray);
        //  UIManager.put( "MenuItem.font", Color.MENU_FONT );
        //     UIManager.put("MenuItem.foreground", Color.BLACK);
        UIManager.put("MenuItem[Disabled].textForeground",
                Color.gray);
        UIManager.put("MenuItem[Enabled].textForeground",
                Color.BLACK);

        // tree
        //      UIManager.put("Tree.background", Color.BLACK);


    }

    private void goToLineNumber(String s) {
 
        try {
            if (s != null && Integer.parseInt(s) != -1) {
                paneAreas.get(jTabbedPane2.getSelectedIndex()).requestFocusInWindow();


                String lineText = textAreas.get(jTabbedPane2.getSelectedIndex()).getText();
                if (Integer.parseInt(s) < textAreas.get(jTabbedPane2.getSelectedIndex()).getLineCount()) {
                    String subStr = lineText.substring(0, lineText.indexOf(s));
                    int len = subStr.split("\n").length + 1;
                    //System.out.println("len >"+len+"total line        "+(textAreas.get(jTabbedPane2.getSelectedIndex()).getLineCount()-1));

                    String paneText = paneAreas.get(jTabbedPane2.getSelectedIndex()).getText();
                    String newLineData[] = paneText.split("\n");
                    String lineData = "";
                    int i;
                    for (i = 0; i < newLineData.length; i++) {
                        if (i == len) {
                            break;
                        } else {
                            lineData += newLineData[i];
                        }
                        //System.out.println(i+">>"+newLineData[i]);
                    }

//                int tabCount=lineData.split("\r").length;
                    if (Integer.parseInt(s) == 1) {
                        paneAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(newLineData[0].length());
                    } else {
                        paneAreas.get(jTabbedPane2.getSelectedIndex()).setCaretPosition(lineData.length() + len - 1);
                    }

                    // add i because the data also have carriage return with new line \r\n
                } else {
                    System.out.println("Line number not exist!");
                }
                // TODO add your handling code here:

            }
        } catch (Exception e) {
            System.out.println("Not a valid number!");
        }

    }

    private void runInCMD() {
        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        try {
            //2.Load your class by URLClassLoader, then instantiate the instance, and call method by reflection
            //3.Compile your files by JavaCompiler
            if (paneAreas.get(jTabbedPane2.getSelectedIndex()).getText().length() == 0) {
                jEditorPane1.setText("<font color=red>Nothing to Run.</font><br><br>");
                return;
            } else {
                if (currentClassName != null && currentClassName.length() != 0) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            jEditorPane1.setText("");

                            if (classOutputFolder == null) {
                                jEditorPane1.setText("<b><font color=red>File must be saved. So, please save the file...!!!</font></b><br><br>");
                                return;
                            }

                            int ret = -1;
                            try {
                                if (currentPackName == null || currentPackName.length() == 0) {
                                    Runtime rt = Runtime.getRuntime();
                                    long startTime = System.currentTimeMillis();

                                    String args;
                                    if (jTextField1.getText() != null && jTextField1.getText().length() != 0) {
                                        args = currentClassName + " " + jTextField1.getText();
                                    } else {
                                        args = null;
                                    }

                                    Process proc;
                                    if (args != null) {
                                        proc = rt.exec("cmd /C start cmd.exe /s /k \"java "
                                                + args + " & echo. & echo. & echo Finish Time: " + (System.currentTimeMillis() - startTime) + " & pause & exit\"", null, new File(classOutputFolder));

                                    } else {
                                        proc = rt.exec("cmd /C start cmd.exe /s /k \"java "
                                                + currentClassName + " & echo. & echo. & echo Finish Time: " + (System.currentTimeMillis() - startTime) + " & pause & exit\"", null, new File(classOutputFolder));

                                    }

                                    proc.waitFor();
                                    ret = proc.exitValue();

                                } else {
                                    Runtime rt = Runtime.getRuntime();
                                    long startTime = System.currentTimeMillis();

                                    String args;
                                    if (jTextField1.getText() != null && jTextField1.getText().length() != 0) {
                                        args = currentPackClassName + " " + jTextField1.getText();
                                    } else {
                                        args = null;
                                    }

                                    Process proc;
                                    if (args != null) {
                                        proc = rt.exec("cmd /C start cmd.exe /s /k \"java "
                                                + args + " & echo. & echo. & echo Finish Time: " + (System.currentTimeMillis() - startTime) + " & pause & exit\"", null, new File(classOutputFolder));

                                    } else {
                                        proc = rt.exec("cmd /C start cmd.exe /s /k \"java "
                                                + currentPackClassName + " & echo. & echo. & echo Finish Time: " + (System.currentTimeMillis() - startTime) + " & pause & exit\"", null, new File(classOutputFolder));

                                    }

                                    proc.waitFor();
                                    ret = proc.exitValue();
                                }
                            } catch (Throwable t) {
                                //   t.printStackTrace();

                                jEditorPane1.setText("<font color=blue>Error +" + t.getMessage() + "<br><br>Exit Value " + ret + ".</font><br><br>");


                                return;
                            }
                            jEditorPane1.setText("<font color=blue>Exit Value " + ret + ".</font><br><br>");


                        }
                    }).start();

                    jEditorPane1.setCaretPosition(0);
                } else {
                    jEditorPane1.setText("<font color=blue>Compile First.</font><br><br>");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: Can't run the file.");
        }
        System.gc();


    }

    private void runInWin() {

        jButton10.setVisible(false);
        jButton11.setVisible(false);
        jLabel10.setVisible(false);
        try {
            //2.Load your class by URLClassLoader, then instantiate the instance, and call method by reflection
            //3.Compile your files by JavaCompiler
            if (paneAreas.get(jTabbedPane2.getSelectedIndex()).getText().length() == 0) {
                jEditorPane1.setText("<font color=red>Nothing to Run.</font><br><br>");
                return;
            } else {
                if (currentClassName != null && currentClassName.length() != 0) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            runIt();
                        }
                    }).start();

                    jEditorPane1.setCaretPosition(0);
                } else {
                    jEditorPane1.setText("<font color=blue>Compile First.</font><br><br>");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: Can't run the file.");
        }
        System.gc();

    }

    private void compileCpp() {


        if (classOutputFolder != null && fileName != null) {

            FileWriter fw;
            try {
                fw = new FileWriter(files.get(jTabbedPane2.getSelectedIndex()));
                fw.write(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
                fw.close();
            } catch (Exception e) {
                System.out.println("File Not Found. " + " Can't save file");
            }

            String log = compile(files.get(jTabbedPane2.getSelectedIndex()));
            System.out.println(log);
            //if (log.endsWith("!!!")) {
            //  int rt = runProgram();
            // System.out.println("\n\nexit value " + rt);

            //}
        }
        return;
    }

    /**
     * Add nodes from under "dir" into jTree1. Highly recursive.
     */
    public class treeUpdates extends Thread {

        File dir1;
        int i = 0;

        treeUpdates(File dir1) {
            super("Directory Thread");
            this.dir1 = dir1;
            jLabel13.setText(dir1.getName());
            start();
        }

        treeUpdates() {
        }

        @Override
        public void run() {


            try {
                DefaultMutableTreeNode root = new DefaultMutableTreeNode(dir1);
                model = new DefaultTreeModel(root);

                jTree1.setModel(model);
//            jTree1.setRootVisible(true);
                jTree1.setShowsRootHandles(true);

                jTree1.setRootVisible(false);
                DefaultMutableTreeNode root1 = (DefaultMutableTreeNode) model.getRoot();
                root1.removeAllChildren();

                filesDitercory.clear();

                model.reload();
                File rootFile1 = (File) root1.getUserObject();

                addFiles(rootFile1, model, root1);
                i = 0;
                jProgressBar1.setValue(i);
                        jProgressBar1.setIndeterminate(false);
                jTree1.expandPath(new TreePath(root1));
            } catch (Exception e) {
                System.out.println("Can't read Directory...");
            }
        }

        public synchronized void addFiles(File rootFile, DefaultTreeModel model, DefaultMutableTreeNode root) {
        jProgressBar1.setIndeterminate(true);
           // if (i == 100) {
             //   i = 1;
           // }
          //  jProgressBar1.setValue(i++);
            /*     File[] files = rootFile.listFiles(new FilenameFilter() {

             @Override
             public boolean accept(File dir, String name) {
             // Strip path information:
             String f = new File(name).getName();
             return f.indexOf("java") != -1;
             }
             });     
             */
            final String fileExtension[] = {".java", ".html", ".txt", ".htm", ".c", ".cpp"};

            File[] files = rootFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    Path path1;
                    if (pathname.isFile() || pathname.isDirectory() || !pathname.isHidden()) {
                        try {
                            path1 = Paths.get(pathname.getCanonicalPath());
                        } catch (Exception e) {
                            path1 = Paths.get(pathname.getAbsolutePath());
                        }

                        DosFileAttributes dfa;
                        try {
                            dfa = Files.readAttributes(path1, DosFileAttributes.class);
                        } catch (Exception e) {
                            System.out.println("File Not Found. or Does Not Exist");// bad practice
                            return false;
                        }
                        boolean b = false;
                        String s = pathname.getName().toLowerCase();
                        //if file is directory then add them otherwise if file is file then find absolute file.
                        if (language.equals("java")) {
                            if (pathname.isDirectory() || s.endsWith(fileExtension[0]) || s.endsWith(fileExtension[1])
                                    || s.endsWith(fileExtension[2]) || s.endsWith(fileExtension[3])) {
                                b = true;
                            }
                        } else if (language.equals("c")) {
                            if (pathname.isDirectory() || s.endsWith(fileExtension[2]) || s.endsWith(fileExtension[4]) || s.endsWith(fileExtension[5])) {
                                b = true;
                            }
                        } else if (language.equals("html")) {
                            if (pathname.isDirectory() || s.endsWith(fileExtension[1])
                                    || s.endsWith(fileExtension[2]) || s.endsWith(fileExtension[3])) {
                                b = true;
                            }
                        }
                        // Warning: Don't use, every file is dfa.isArchive(),dfa.isRegularFile() in windows OS
                        //return !dfa.isHidden() && !dfa.isSystem() && !dfa.isSymbolicLink() && !dfa.isOther() && !dfa.isReadOnly();
                        return b && !(dfa.isHidden() || dfa.isSystem() || dfa.isReadOnly() || dfa.isSymbolicLink() || dfa.isOther());
                    } else {
                        return false;
                    }
                }
            });


            //  File[] files = rootFile.listFiles();
            List<File> ol = new ArrayList<>();
            ol.addAll(Arrays.asList(files));

            Collections.sort(ol, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {

                    int result = o1.getName().compareTo(o2.getName());

                    if (o1.isDirectory() && o2.isFile()) {
                        result = -1;
                    } else if (o2.isDirectory() && o1.isFile()) {
                        result = 1;
                    }

                    return result;
                }
            });

            // Pass two: for files.
//            String fileExtension[] = {".java", ".html", ".txt", ".properties", ".bat", ".c", ".cpp", ".css", ".mf", ".xml", ".JAVA", ".HTML", ".TXT",
            //              ".PROPERTIES", ".BAT", ".C", ".CPP", ".CSS", ".MF", ".XML",};
            for (File file1 : ol) {
                DefaultMutableTreeNode child = new DefaultMutableTreeNode(file1.getName());
                filesDitercory.put(child, file1.getAbsolutePath());
                model.insertNodeInto(child, root, root.getChildCount());
                if (file1.isDirectory()) {
                    addFiles(file1, model, child);
                }
            }

        }
    }

    private static class StartSplaceScreen extends JWindow {

        private final JProgressBar jp;
        private Timer progressBarTimer;

        public StartSplaceScreen() {
            ImageIcon icon = new ImageIcon(getClass().getResource("/asterix/image/splace.png"));
            setSize(icon.getIconWidth() + 10, icon.getIconHeight() + 30);
            JPanel jpanel = new JPanel();
            //jpanel.setLayout(new FlowLayout());
            jpanel.setBackground(new Color(210, 220, 243));

            JLabel jlbl = new JLabel(icon);
            jpanel.add(jlbl);
            jp = new JProgressBar(0, 100);
            jp.setPreferredSize(new Dimension(icon.getIconWidth(), 15));
            jp.setForeground(Color.BLUE);
            jp.setBackground(Color.WHITE);
            jp.setStringPainted(true);
            setLocation(400, 200);//Bounds(500, 150, 280, 300);
            jpanel.add(jp);
//            jpanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(new Color(51, 153, 0), new Color(255, 255, 0)), "Asterix IDE", TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("Arial", Font.BOLD, 20), new Color(51, 153, 0)));
            add(jpanel);
            setVisible(true);


            for (int jpvalue = 0; jpvalue <= jp.getMaximum(); jpvalue++) {
                jp.setValue(jpvalue);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Entrrupt Occurs.");
                }
            }
            dispose();

            /*startProgressBar();
            
         
             }
             private static int count = 1, TIMER_PAUSE = 25,PROGBAR_MAX=100;
        
             private void startProgressBar() {
             progressBarTimer = new Timer(TIMER_PAUSE, new ActionListener() {

             @Override
             public void actionPerformed(java.awt.event.ActionEvent evt) {
             jp.setValue(count);
             System.out.println(count);
             if (PROGBAR_MAX == count) {
             dispose();//dispose of splashscreen
             progressBarTimer.stop();//stop the timer
                
             new Editor();
             }
             count++;//increase counter

             }
             }
             );
             progressBarTimer.start();
             }*/
        }
    }

    public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

        /*
         FileSystemView fsv;
         private JLabel label;

         MyTreeCellRenderer(){
         label = new JLabel();
         label.setOpaque(false);
            
         fsv = FileSystemView.getFileSystemView();

         }
         @Override
         public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
         //  super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            
         if (filesDitercory.get((DefaultMutableTreeNode) value) != null) {
         File file = new File(filesDitercory.get((DefaultMutableTreeNode) value));
                
         label.setIcon(fsv.getSystemIcon(file));
         label.setText(fsv.getSystemDisplayName(file));
         label.setToolTipText(file.getPath());

         if (selected) {
         label.setBackground(backgroundSelectionColor);
         } else {
         label.setBackground(backgroundNonSelectionColor);
         }
         }
         return label;   
         } 
         */
        FileSystemView fsv;

        MyTreeCellRenderer() {
            fsv = FileSystemView.getFileSystemView();

        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            //  super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

            if (filesDitercory.get((DefaultMutableTreeNode) value) != null) {
                File file = new File(filesDitercory.get((DefaultMutableTreeNode) value));

                //setIcon(fsv.getSystemIcon(file));

                if (file.toString().indexOf(".java") != -1) {
                    setIcon(new ImageIcon(getClass().getResource("/asterix/image/javafile.png")));
                } else if (file.toString().indexOf(".c") != -1) {
                    setIcon(new ImageIcon(getClass().getResource("/asterix/image/cfile.png")));
                } else if (file.toString().indexOf(".cpp") != -1) {
                    setIcon(new ImageIcon(getClass().getResource("/asterix/image/cppfile.png")));
                } else {
                    setIcon(fsv.getSystemIcon(file));
                }



                setText(fsv.getSystemDisplayName(file));
                setToolTipText(file.getPath());

            }
            return this;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         Thread l_splash_thread = new Thread(
         new Runnable() {

         @Override
         public void run() {
         new StartSplaceScreen ();
         }
         });
         l_splash_thread.start();

         try {
         l_splash_thread.join();
         } catch (InterruptedException e1) {
         e1.printStackTrace();
         }
         */

        /*
         new SwingWorker<Void, Void>() {
         @Override
         protected Void doInBackground() throws Exception {
         new StartSplaceScreen();
         return null;
         }
         }.execute();
         */

        String s = System.getProperty("java.version");
        if (s.compareTo("1.7") < 0) {
            System.err.println((new StringBuilder()).append("You are running Java version ").append(s).append('.').toString());
            System.err.println("Asterix IDE requires Java 1.7 or later.");
            System.exit(1);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Editor();

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton clipCopyBtn;
    private javax.swing.JButton clipCutBtn;
    private javax.swing.JButton clipDelBtn;
    private javax.swing.JButton clipPasteBtn;
    private javax.swing.JButton clipRedoBtn;
    private javax.swing.JButton clipUndoBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JDesktopPane jDesktopPane1;
    public javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JInternalFrame jInternalFrame3;
    private javax.swing.JInternalFrame jInternalFrame4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JSlider jSlider1;
    private static javax.swing.JTabbedPane jTabbedPane2;
    public javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    public javax.swing.JTextPane jTextPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree1;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton styleColorBtn;
    private javax.swing.JTextField txtURL;
    // End of variables declaration//GEN-END:variables

    private void chooseColor() {
        Color initialcolor = Color.RED;
        Color color = JColorChooser.showDialog(this, "Select a color", initialcolor);
        if (color != null) {

            StyleContext sc = new StyleContext();
            Style style = sc.addStyle("color", null);

            int startIndex = paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectionStart();
            int endIndex = paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectionEnd();
            String selectedText = paneAreas.get(jTabbedPane2.getSelectedIndex()).getSelectedText();

            StyleConstants.setForeground(style, color);
            if (selectedText == null) {
                paneAreas.get(jTabbedPane2.getSelectedIndex()).setForeground(color);
            } else {
                try {
                    paneAreas.get(jTabbedPane2.getSelectedIndex()).replaceSelection("");
                    doc = paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument();
                    doc.insertString(startIndex, selectedText, style);

                } catch (BadLocationException ex) {
                    System.out.println("Bad Location Error. Can't Insert Text");
                }
            }
        }
    }

    public void openFile(File file1) {
        //currentFile = files.get(jTabbedPane2.getSelectedIndex());
        jEditorPane1.setText("");

        jLabel15.setVisible(false);
        try {

            input = new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream(
                    file1)));

            currentFile = file1;
            files.set(jTabbedPane2.getSelectedIndex(), currentFile);
            fileHistory.add(totalFileCount, currentFile);

            JMenuItem itmMenu = new JMenuItem();
            String spc = "";
            if (totalFileCount + 1 < 10) {
                spc = "0" + (totalFileCount + 1) + ".  |  ";
            } else {
                spc = (totalFileCount + 1) + ".  |  ";
            }

            itmMenu.setText(spc + fileHistory.get(totalFileCount).getName());
            itmMenu.setActionCommand("" + totalFileCount);
            itmMenu.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    int cnt = Integer.parseInt(evt.getActionCommand());
                    jEditorPane1.setText("");
                    // System.out.println("\n\nfile : " + file1);
                    File file1 = fileHistory.get(cnt);
                    String fn = file1.getName();
                    fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());
                    if (fileExtenssion.equalsIgnoreCase("html") || fileExtenssion.equalsIgnoreCase("htm")) {
                     /*   try {
                            jEditorPane1.setPage(file1.toURI().toURL());
                            //  SimpleWebBrowserExample.main(file1 + "");
                            jButton10.setVisible(true);
                            jButton11.setVisible(true);
                            jLabel10.setVisible(true);

                            jLabel10.setText("" + file1.getName());
                            try {
                                htmlpages.add(file1.toURI().toURL());
                            } catch (Exception ex) {
                                System.out.println("Can't Open this page. \n\n");
                            }
                            htmlPageCounter++;
                        } catch (IOException ex) {
                            System.out.println("Can't Open this page.");
                        }
                        */ 
                    } //else {

                        if (!files.contains((File) file1)) {
                            if (files.get(jTabbedPane2.getSelectedIndex()).getName().length() != 0) {

                                addClosableTab(jTabbedPane2, "Tab " + tabCount + " ");
                            }
                            openFile(file1);

                            jButton10.setVisible(false);
                            jButton11.setVisible(false);
                            jLabel10.setVisible(false);
                            if (fileExtenssion.equalsIgnoreCase("java")) {
                                try {
                                    classOutputFolder = file1.getCanonicalPath().substring(0, file1.getCanonicalPath().indexOf(file1.getName()) - 1);
                                    fileName = file1.getName().substring(0, file1.getName().indexOf("."));
                                } catch (IOException ex) {
                                    Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {
                            jTabbedPane2.setSelectedIndex(files.indexOf(file1));
                        }
                    //}

                }
            });

            jMenu9.add(itmMenu);

            totalFileCount++;


            try {
                fileDir = file1.getCanonicalPath();
            } catch (Exception e) {
                fileDir = file1.getPath();
            }
            //System.out.println("path " + fileDir);
            String fn = file1.getName();
            fileName = fn.substring(0, fn.indexOf(".")).trim();
            fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());


            if(language.equals("java")){
            if (!fileExtenssion.equalsIgnoreCase("java")) {
                System.out.println("\n\nNot a java file. Re-Open a java file");
            }
        }else if(language.equals("c")){
            if (fileExtenssion.indexOf("c") == -1) {
                System.out.println("\n\nNot a C/C++ file. Re-Open a C/C++ file");
            }
        }else if(language.equals("html")){
            if (fileExtenssion.indexOf("htm") == -1) {
                System.out.println("\n\nNot a Html file. Re-Open a Html file");
            }
            }
            //PAGE_ICON=;

            //if (!lblTitles.isEmpty() && jTabbedPane2.getSelectedIndex() > 0) {
//            lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(fsv.getSystemIcon(file1));

            if (fileExtenssion.equals("java")) {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(new ImageIcon(getClass().getResource("/asterix/image/javafile.png")));
            } else if (fileExtenssion.equals("c")) {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(new ImageIcon(getClass().getResource("/asterix/image/cfile.png")));
            } else if (fileExtenssion.equals("cpp")) {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(new ImageIcon(getClass().getResource("/asterix/image/cppfile.png")));
            } else {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(fsv.getSystemIcon(file1));
            }

            //      } else {
            //        jTabbedPane2.setIconAt(jTabbedPane2.getSelectedIndex(), fsv.getSystemIcon(file1));
            //  }


            paneAreas.get(jTabbedPane2.getSelectedIndex()).read(input, "READING FILE :-)");

            final UndoManager undoManager = new UndoManager();

            undoMgr.add(undoManager);
            paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().addUndoableEditListener(
                    new UndoableEditListener() {
                @Override
                public void undoableEditHappened(UndoableEditEvent e) {
                    try {
                        undoMgr.get(jTabbedPane2.getSelectedIndex()).addEdit(e.getEdit());
                        updateButtons();

                    } catch (Exception ee) {
                    }
                }
            });

            paneAreas.get(jTabbedPane2.getSelectedIndex()).getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void changedUpdate(DocumentEvent de) {
                    try {
                        textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                        updateButtons();
                    } catch (Exception ee) {
                    }
                }

                @Override
                public void insertUpdate(DocumentEvent de) {
                    try {
                        textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                        updateButtons();
                    } catch (Exception ee) {
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent de) {
                    try {
                        textAreas.get(jTabbedPane2.getSelectedIndex()).setText(getText1());
                        updateButtons();
                    } catch (Exception ee) {
                    }

                }
            });
            jEditorPane1.setText("<b><font color=green>File Opened!</font><b><br><br>");
            //        if (jTabbedPane2.getSelectedIndex() > 0 && fileName != null) {
            lblTitles.get(jTabbedPane2.getSelectedIndex()).setText(fn + " ");
            /*          }
             else {
             jTabbedPane2.setTitleAt(jTabbedPane2.getSelectedIndex(), fileName);
             }
             */

        } catch (FileNotFoundException ex) {
            System.out.println("Error: File not found.");

        } catch (IOException ex) {
            System.out.println("Error: Can't Read Data from file");
        }


        dontSave = true;
        /* System.out.println("currentClassName\t" + currentClassName + "\ncurrentPackName\t" + currentPackName
         + "\ncurrentFile\t" + currentFile + "\ncurDir\t" + curDir + "\ncurrentPackClassName\t" + currentPackClassName
         + "\nfileDir\t" + fileDir + "\nfileName\t" + fileName + "\nfileExtenssion\t" + fileExtenssion);
         */
    }

    private String openFileDialog() {
        chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);

        chooser.setPreferredSize(new Dimension(600, 500));

        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (language.equals("java")) {
                    if (f.isDirectory() || f.getName().endsWith("java") || f.getName().endsWith("txt") || f.getName().endsWith("html") || f.getName().endsWith("htm")) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (language.equals("c")) {
                    if (f.isDirectory() || f.getName().endsWith("c") || f.getName().endsWith("cpp") || f.getName().endsWith("txt")) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (language.equals("html")) {
                    if (f.isDirectory() || f.getName().endsWith("html") || f.getName().endsWith("htm") || f.getName().endsWith("txt")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            @Override
            public String getDescription() {
                if (language.equals("java")) {
                    return "Java Files (*.java)";
                } else if (language.equals("c")) {
                    return "C/Cpp Files (*.c,*.cpp)";
                } else if (language.equals("html")) {
                    return "Html Files (*.html,*.htm)";
                } else {
                    return "Other files";
                }
            }
        });


        int returnVal = chooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            if (curDir != null) {
                String previousDir = curDir.getAbsolutePath();
                curDir = chooser.getCurrentDirectory();
                String newDir = curDir.getAbsolutePath();

                if (!previousDir.equals(newDir)) {
                    new treeUpdates(curDir);
                }
            }
            curDir = chooser.getCurrentDirectory();
            //File file1 = chooser.getSelectedFile();
            //openFile(file1);            
            File[] ff = chooser.getSelectedFiles();
            if (ff != null && ff.length == 1) {
                String fn = chooser.getSelectedFile().getName();
                fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());
                if (fileExtenssion.equalsIgnoreCase("html") || fileExtenssion.equalsIgnoreCase("htm")) {
                   /* try {
                        jEditorPane1.setPage(chooser.getSelectedFile().toURI().toURL());
                        jLabel10.setText("" + chooser.getSelectedFile().getName());
                        htmlpages.add(chooser.getSelectedFile().toURI().toURL());

                        jButton10.setVisible(true);
                        jButton11.setVisible(true);
                        jLabel10.setVisible(true);

                        //jTextPane1.setText("htmlPageCounter" + htmlPageCounter + "\n\n" + chooser.getSelectedFile().toURI().toURL() + "\n\n size " + htmlpages.size());
                        htmlPageCounter++;
                    } catch (Exception ex) {
                        System.out.println("Can't Open this page. \n\n");
                    }

                    //jPanel7.setVisible(false);
                    //  jPanel6.setVisible(true);
                    System.out.println("htmlPageCounter" + htmlPageCounter + " size " + htmlpages.size());//                            

*/ 
                } //else {
                    if (!files.contains((File) ff[0])) {
                        if (files.get(jTabbedPane2.getSelectedIndex()).getName().length() != 0) {
                            addClosableTab(jTabbedPane2, ff[0].getName());
                        }
                        openFile(ff[0]);
                        jButton10.setVisible(false);
                        jButton11.setVisible(false);
                        jLabel10.setVisible(false);

                    } else {
                        jTabbedPane2.setSelectedIndex(files.indexOf(ff[0]));
                    }

                    //jPanel6.setVisible(false);
                    //jPanel7.setVisible(true);
                //}
            }
            if (ff != null && ff.length > 1) {
                for (File file : ff) {
                    if (!files.contains((File) file)) {
                        if (files.get(jTabbedPane2.getSelectedIndex()).getName().length() != 0) {

                            addClosableTab(jTabbedPane2, file.getName());
                        }
                        openFile(file);
                    } else {
                        jTabbedPane2.setSelectedIndex(files.indexOf(file));
                    }

                }
            }

            String s = chooser.getCurrentDirectory().getPath();
            try {
                s = chooser.getCurrentDirectory().getCanonicalPath();


            } catch (IOException ex) {
                Logger.getLogger(Editor.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return s;

        } else {
            System.out.println("Operation is CANCELLED :(");
        }
        return null;
    }

    private String saveFile() {
        jEditorPane1.setText("");
        //currentFile= files.get(jTabbedPane2.getSelectedIndex());
        //this method is only for java file.

        JFileChooser chooser1 = new JFileChooser();

        chooser1.setPreferredSize(new Dimension(600, 500));


//        chooser1.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser1.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (language.equals("java")) {
                    if (f.isDirectory() || f.getName().endsWith("java") || f.getName().endsWith("txt") || f.getName().endsWith("html") || f.getName().endsWith("htm")) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (language.equals("c")) {
                    if (f.isDirectory() || f.getName().endsWith("c") || f.getName().endsWith("cpp") || f.getName().endsWith("txt")) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (language.equals("html")) {
                    if (f.isDirectory() || f.getName().endsWith("html") || f.getName().endsWith("htm") || f.getName().endsWith("txt")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            @Override
            public String getDescription() {
                if (language.equals("java")) {
                    return "Java Files (*.java)";
                } else if (language.equals("c")) {
                    return "C/Cpp Files (*.c,*.cpp)";
                } else if (language.equals("html")) {
                    return "Html Files (*.html,*.htm)";
                } else {
                    return "Other files";
                }
            }
        });

        File f;
        String fn;
        boolean acceptable = false;
        int r;
        do {
            //      chooser1 = new JFileChooser();
            r = chooser1.showSaveDialog(this);
            if (r == JFileChooser.APPROVE_OPTION) {
                f = chooser1.getSelectedFile();
                if (f.exists()) {
                    int confirm = JOptionPane.showOptionDialog(Editor.this,
                            "File Exists! Do you want to overrite it ?", "Exit Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, null, null);
                    if (confirm == JOptionPane.YES_OPTION) {
                        acceptable = true;
                    }
                } else {
                    acceptable = true;
                }
            } else {
                acceptable = true;
            }
        } while (!acceptable);

        if (r == JFileChooser.APPROVE_OPTION) {

            f = chooser1.getSelectedFile();

            fn = f.getName();
            if (fn.indexOf(".") == -1) {

                if (language.equals("java")) {
                    fn += ".java";
                    f = new File(f + ".java");
                } else if (language.equals("c")) {
                    fn += ".cpp";
                    f = new File(f + ".cpp");
                } else if (language.equals("html")) {
                    fn += ".html";
                    f = new File(f + ".html");
                }

            }
            fileName = fn.substring(0, fn.indexOf(".")).trim();
            fileExtenssion = fn.substring(fn.lastIndexOf(".") + 1, fn.length());

//            if (!fileExtenssion.equalsIgnoreCase("java")) {
            //              System.out.println("File Must be saved as Java File.\nRe-Save it to Java File");
//                fileName = null;
            //        }


            if (curDir != null) {
                String previousDir = curDir.getAbsolutePath();
                curDir = chooser1.getCurrentDirectory();
                String newDir = curDir.getAbsolutePath();

                if (!previousDir.equals(newDir)) {
                    new treeUpdates(curDir);
                }
            }
            curDir = chooser1.getCurrentDirectory();
            currentFile = f;
            files.set(jTabbedPane2.getSelectedIndex(), currentFile);

            FileWriter fw;

            try {
                fw = new FileWriter(f);
                fw.write(paneAreas.get(jTabbedPane2.getSelectedIndex()).getText());
                fw.close();
            } catch (IOException ex) {
                System.out.println("Error: File not found or Can't Read Data from file");
            }

            try {
                fileDir = currentFile.getCanonicalPath();
            } catch (Exception e) {
                fileDir = currentFile.getPath();
            }
            //System.out.println("path " + fileDir);

            int i;
            // if (!lblTitles.isEmpty() && jTabbedPane2.getSelectedIndex() > 0) {
            //lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(fsv.getSystemIcon(currentFile));

            if (fileExtenssion.equals("java")) {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(new ImageIcon(getClass().getResource("/asterix/image/javafile.png")));
            } else if (fileExtenssion.equals("c")) {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(new ImageIcon(getClass().getResource("/asterix/image/cfile.png")));
            } else if (fileExtenssion.equals("cpp")) {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(new ImageIcon(getClass().getResource("/asterix/image/cppfile.png")));
            } else {
                lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(fsv.getSystemIcon(currentFile));
            }

            // } else {
            //     jTabbedPane2.setIconAt(jTabbedPane2.getSelectedIndex(), fsv.getSystemIcon(file));
            //  }

            //if (jTabbedPane2.getSelectedIndex() > 0 && fileName != null) {
            lblTitles.get(jTabbedPane2.getSelectedIndex()).setText(fn + " ");
            //  } else {
            //      jTabbedPane2.setTitleAt(jTabbedPane2.getSelectedIndex(), fileName);
            //  }

            String s = chooser1.getCurrentDirectory().getPath();
            try {
                s = chooser1.getCurrentDirectory().getCanonicalPath();


            } catch (IOException ex) {
                Logger.getLogger(Editor.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            /*     System.out.println("\nFile SAVED SUCCESSFULLY\n\n" + "currentClassName\t" + currentClassName + "\ncurrentPackName\t" + currentPackName
             + "\ncurrentFile\t" + currentFile + "\ncurDir\t" + curDir + "\ncurrentPackClassName\t" + currentPackClassName
             + "\nfileDir\t" + fileDir + "\nfileName\t" + fileName + "\nfileExtenssion\t" + fileExtenssion);
             */
            //lblTitles.get(jTabbedPane2.getSelectedIndex()).setIcon(fsv.getSystemIcon(currentFile));

            lblTitles.get(jTabbedPane2.getSelectedIndex()).setText(fn + " ");

            jEditorPane1.setText("<b><font color=green>File Saved!</font><b><br><br><br><br>");
            return s;
        }
//        currentFile = null;
        //      files.set(jTabbedPane2.getSelectedIndex(), currentFile);
        // System.out.println("\nPlease save file...!!!");
        /*      System.out.println("\nCAN'T SAVE\n\n" + "currentClassName\t" + currentClassName + "\ncurrentPackName\t" + currentPackName
         + "\ncurrentFile\t" + currentFile + "\ncurDir\t" + curDir + "\ncurrentPackClassName\t" + currentPackClassName
         + "\nfileDir\t" + fileDir + "\nfileName\t" + fileName + "\nfileExtenssion\t" + fileExtenssion);
         */
        return null;

    }

    public void updateButtons() {

        clipUndoBtn.setEnabled(undoMgr.get(jTabbedPane2.getSelectedIndex()).canUndo());
        clipRedoBtn.setEnabled(undoMgr.get(jTabbedPane2.getSelectedIndex()).canRedo());
        jMenuItem11.setEnabled(undoMgr.get(jTabbedPane2.getSelectedIndex()).canUndo());
        jMenuItem12.setEnabled(undoMgr.get(jTabbedPane2.getSelectedIndex()).canRedo());

        jMenuItm[10].setEnabled(undoMgr.get(jTabbedPane2.getSelectedIndex()).canUndo());
        jMenuItm[11].setEnabled(undoMgr.get(jTabbedPane2.getSelectedIndex()).canRedo());
        /*
         if (paneAreas.get(jTabbedPane2.getSelectedIndex()).getText().length() == 0) {
         clipCutBtn.setEnabled(false);
         clipCopyBtn.setEnabled(false);
         clipDelBtn.setEnabled(false);
         printBtn.setEnabled(false);
         jMenuItem4.setEnabled(false);
         jMenuItem7.setEnabled(false);
         jMenuItem10.setEnabled(false);
         jMenuItem13.setEnabled(false);
         jMenuItem15.setEnabled(false);

         jMenuItm1.setEnabled(false);
         jMenuItm2.setEnabled(false);
         jMenuItm3.setEnabled(true);
         jMenuItm4.setEnabled(false);
         jMenuItm5.setEnabled(false);
         } else {
         clipCutBtn.setEnabled(true);
         clipCopyBtn.setEnabled(true);
         clipDelBtn.setEnabled(true);
         printBtn.setEnabled(true);
         jMenuItem4.setEnabled(true);
         jMenuItem7.setEnabled(true);
         jMenuItem10.setEnabled(true);
         jMenuItem13.setEnabled(true);
         jMenuItem15.setEnabled(true);

         jMenuItm1.setEnabled(true);
         jMenuItm2.setEnabled(true);
         jMenuItm3.setEnabled(true);
         jMenuItm4.setEnabled(true);
         jMenuItm5.setEnabled(true);
         }
         */


    }

    public class MyDiagnosticListener implements DiagnosticListener<JavaFileObject> {

        @Override
        public void report(Diagnostic<? extends JavaFileObject> diagnostic) {

            lineNo = diagnostic.getLineNumber();
            columnNo = diagnostic.getColumnNumber();

            if (diagnostic.getCode().startsWith("compiler.err")) {
                numberOfErrors++;
            } else if (diagnostic.getCode().startsWith("compiler.note")) {
                numberOfNotes++;
            } else if (diagnostic.getCode().startsWith("compiler.warn")) {
                numberOfWarnings++;
            }

            if (lineNo != -1) {
                errorMsg.append("<br><Font color=red>").append(diagnostic.getKind()).append(":</font>&nbsp;<a href=goToPosition(").append(diagnostic.getPosition()).append(")>| Line: ").append(lineNo).append(" | Column: ").append(columnNo).append(" |</a>");
            } else {
                errorMsg.append("<br><Font color=red>").append(diagnostic.getKind() + ":</font>");
            }

            String pattern = "" + lineNo;
            try {
                Highlighter hilite = textAreas.get(jTabbedPane2.getSelectedIndex()).getHighlighter();
                doc1 = textAreas.get(jTabbedPane2.getSelectedIndex()).getDocument();
                String text = doc1.getText(0, doc1.getLength());
                int pos = 0;
                // Search for pattern
                while ((pos = text.indexOf(pattern, pos)) >= 0) {
                    // Create highlighter using private painter and apply around pattern
                    hilite.addHighlight(pos, pos + pattern.length(), new DefaultHighlighter.DefaultHighlightPainter(Color.red));
                    pos += pattern.length();
                    break;
                }
            } catch (BadLocationException e) {
                System.out.println("Error: Can't Read Data from file");
            }


            errorMsg.append("<br>Message:  ").append(diagnostic.getMessage(Locale.ENGLISH));
            errorMsg.append("<br>");
        }
    }

    /**
     * compile your files by JavaCompiler
     */
    public String compileIt(Iterable<? extends JavaFileObject> files1) throws FileNotFoundException, IOException {
        //get system compiler:
        try {

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            if (compiler == null) {
                System.out.println("javax.tools.JavaCompiler is not available. Is tools.jar missing "
                        + "from the classpath?");
            }
            // for compilation diagnostic message processing on compilation WARNING/ERROR
            MyDiagnosticListener c = new MyDiagnosticListener();
            //DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
            //System.out.println(System.getProperty("java.home"));
            StandardJavaFileManager fileManager = null;
            try {
                fileManager = compiler.getStandardFileManager(c, Locale.getDefault(), null);//Locale.ENGLISH
            } catch (Exception e) {
                System.out.println("\ncompiler.getStandardFileManager exception\n");
                System.out.println("\n\nPlease set your JAVA_HOME to  <<JDK instalation Directory>>/jre/bin and JAVA Version must be 1.7 or greater.\n\n"
                        + "Goto >> \n\n Settings>>Set JAVA_HOME\n\n");
            }
            if (classOutputFolder != null) {
                //specify classes output folder
                Iterable options = Arrays.asList("-d", classOutputFolder);//"-ea",
                JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
                        c, options, null,
                        files1);

//                errorMsg.append("Class: <font color=blue>").append(currentClassName).append("</font> Package: "
                //                      + "<font color=blue>").append(currentPackName).append("</font><br><hr><br>");

                errorMsg.append("<font color=green>").append(currentPackClassName).append("</font> compiling...!!!<br><hr><br>");
                success = task.call();
                if (success) {
                    dontRun = true;
                    errorMsg.append("<br><hr><font color=green>Compiled Succesfully with status 0<br>").append(numberOfErrors).append(" error(s), "
                            + "").append(numberOfWarnings).append(" warning(s), ").append(numberOfNotes).append(" note(s)"
                            + "</font><hr><br>");
                } else {
                    dontRun = false;
                    errorMsg.append("<br><br><hr><font color=red>Compilation Error with status 1<br>").append(numberOfErrors).append(" error(s), "
                            + "").append(numberOfWarnings).append(" warning(s), ").append(numberOfNotes).append(" note(s)"
                            + "</font><hr><br>");
                    currentClassName = null;
                }
                //System.out.println(errorMsg);
                numberOfErrors = 0;
                numberOfWarnings = 0;
                numberOfNotes = 0;
            }
            try {
                fileManager.close();
            } catch (IOException ex) {
                System.out.println("Can't save file.");
            }
        } catch (Exception ex) {
            System.out.println("Error Occured during operation.");
        }
        return errorMsg.toString();
    }

    public static String readDoc(File f) {
        String text = "", read;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while ((read = br.readLine()) != null) {
                text += read + "\n";
            }
        } catch (Exception ex) {
            System.out.println("Error: File Not Found or Can't read file.");
        }
        return text;


    }

    public static class InMemoryJavaFileObject extends SimpleJavaFileObject {

        private String contents = null;

        public InMemoryJavaFileObject(String className, String contents) throws Exception {
            super(URI.create("string:///" + className.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
            this.contents = contents;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors)
                throws IOException {
            return contents;
        }
    }

    @SuppressWarnings("empty-statement")
    private JavaFileObject getJavaFileObject(String fileContent) {

        StringBuilder contents = new StringBuilder(fileContent);
        JavaFileObject so = null;

        String fn = null;
        if (files.get(jTabbedPane2.getSelectedIndex()) != null && files.get(jTabbedPane2.getSelectedIndex()).length() != 0) {
            fn = files.get(jTabbedPane2.getSelectedIndex()).getName();
        } else {
            System.out.println("\n\nPlease Save File\n");
        }

        if (fn != null && fn.length() != 0) {
            fileName = fn.substring(0, fn.indexOf(".")).trim();
        }
        try {
            so = new InMemoryJavaFileObject(currentPackClassName + "", contents.toString());
            //java File Object represents an in-memory java source file so there is no need to put the source file on hard disk
        } catch (Exception ex) {
            System.out.println("Internal File Creation Error.");
        }
        return so;
    }

    public String runIt() {
        jEditorPane1.setText("");

        try {
            if (classOutputFolder == null) {
                jEditorPane1.setText("<b><font color=red>File must be saved. So, please save the file...!!!</font></b><br><br>");
                return null;
            }
            File file1 = new File(classOutputFolder);
            // Convert File to a URL
            URL url = file1.toURL(); // file:/classes/demo
            URL[] urls = new URL[]{url};
            // Create a new class loader with the directory
            ClassLoader loader = new URLClassLoader(urls);
            Class thisClass;
            if (currentPackName == null || currentPackName.length() == 0) {
                thisClass = loader.loadClass(currentClassName + "");
            } else {
                thisClass = loader.loadClass(currentPackClassName + "");


            }

            Method thisMethod = thisClass.getDeclaredMethod("main", String[].class); //(Class[])null
            loader.setClassAssertionStatus(currentClassName
                    + "", true);
            loader.setPackageAssertionStatus(currentClassName
                    + "", true);
            thisMethod.setAccessible(
                    true);
            // run the main() method on the instance:
            String[] str;

            if (jTextField1.getText().length() == 0) {
                str = new String[0];
            } else {
                str = jTextField1.getText().split(" ");
            }

            try {
                thisMethod.invoke(null, (Object) str);       //(Object[])null //(Object) str //new Object[]{new String[] {"arg1","arg2"}}//thisClass.newInstance()

            } catch (InvocationTargetException ite) {//Throwable e
                System.out.println(ite.getCause());//"Internal Error: Can,t Call Object"
            }

            System.gc();
        } catch (Exception ex) {
            System.out.println("Exception Occured:\n\n" + ex);
        }

        return "";
    }

    private void updateTextPane(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Document doc = jEditorPane1.getDocument();
                try {
                    doc.insertString(doc.getLength(), text, null);
                } catch (BadLocationException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void redirectSystemStreams() {


        OutputStream out1 = new OutputStream() {
            @Override
            public void write(final int b) throws IOException {
                updateTextPane(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextPane(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out1, true));
        System.setErr(new PrintStream(out1, true));
        /* System.setIn(new InputStream() {
         @Override
         public int read() throws IOException {
         if (jTextField1.getText().length() != 0) {
         return Integer.parseInt(jTextField1.getText());// + System.getProperty("line.separetor"));
         } else {
         return Integer.parseInt("Data in Input Box is Empty");//+ System.getProperty("line.separetor"));
         }
         }
         });
         */



// 2. set the System.in and System.out streams
 /*       System.setIn(inPipe);
         try {
         System.setOut(new PrintStream(new PipedOutputStream(outPipe), true));
         inWriter = new PrintWriter(new PipedOutputStream(inPipe), true);
         } catch (IOException e) {
         System.out.println("Error: " + e);
         }

         new SwingWorker<Void, String>() {
         @Override
         protected Void doInBackground() throws Exception {
         Scanner s = new Scanner(outPipe);
         while (s.hasNextLine()) {
         String line = s.nextLine();
         publish(line);
         }

         return null;

         }

         @Override
         protected void process(java.util.List<String> chunks) {
         for (String line : chunks) {
         if (line.length() < 1) {
         continue;
         }
         jEditorPane1.setText(jEditorPane1.getText() + line.trim() + "\n");
         }
         }
         }.execute();
         */
    }
//    private final PipedInputStream inPipe = new PipedInputStream();
    //  private final PipedInputStream outPipe = new PipedInputStream();
    //PrintWriter inWriter;
}
