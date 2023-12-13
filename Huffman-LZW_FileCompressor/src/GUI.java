import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class GUI implements ActionListener {
    File file;
    Compressor cpm = new Compressor();

    private void onAlgortihmSelect(CompressAlgorithm algo) {
        cpm.setAlgorithm(algo);
    }

    private void onCompressButton1Click() throws IOException {
        CompressAlgorithm alg = new Huffman();
        onAlgortihmSelect(alg);
        File mycFile = cpm.compress(file);
    }

    private void onCompressButton2Click() throws IOException {
        CompressAlgorithm alg = new LZW();
        onAlgortihmSelect(alg);
        File mycFile = cpm.compress(file);
    }

    private void onDecompressButton1Click() throws IOException {
        CompressAlgorithm alg = new Huffman();
        onAlgortihmSelect(alg);
        File mydcFile = cpm.decompress(file);
    }

    private void onDecompressButton2Click() throws IOException {
        CompressAlgorithm alg = new LZW();
        onAlgortihmSelect(alg);
        File mydcFile = cpm.decompress(file);
    }

    public void renderGUI() {
        // Creating the Frame
        JFrame frame = new JFrame("File Compressor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 250);
        frame.getContentPane().setBackground(new Color(0xF7F7F7));

        // Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("Open");
        m1.add(m11);
        JFileChooser fc = new JFileChooser();
        // Creating the panel at bottom and adding components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        JLabel label = new JLabel("Selected File Size:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel label2 = new JLabel("After ZIP the file size:");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setBackground(new Color(0xF7F7F7));
        JButton ZHF = new JButton("ZIP->Huffman Coding");
        ZHF.setPreferredSize(new Dimension(200, 40));
        ZHF.setBackground(new Color(0x2196F3));
        ZHF.setForeground(Color.WHITE);
        ZHF.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JButton ZLZ = new JButton("ZIP->LZW Algortithm");
        ZLZ.setPreferredSize(new Dimension(200, 40));
        ZLZ.setBackground(new Color(0x2196F3));
        ZLZ.setForeground(Color.WHITE);
        ZLZ.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JButton HFZ = new JButton("Huffman Coding->ZIP");
        HFZ.setPreferredSize(new Dimension(200, 40));
        HFZ.setBackground(new Color(0x2196F3));
        HFZ.setForeground(Color.WHITE);
        HFZ.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JButton LZZ = new JButton("LZW Algortithm->ZIP");
        LZZ.setPreferredSize(new Dimension(200, 40));
        LZZ.setBackground(new Color(0x2196F3));
        LZZ.setForeground(Color.WHITE);
        LZZ.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        buttonPanel.add(ZHF);
        buttonPanel.add(ZLZ);
        buttonPanel.add(HFZ);
        buttonPanel.add(LZZ);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(label2);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(buttonPanel);
        ZHF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onCompressButton1Click();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        HFZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onDecompressButton1Click();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        ZLZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onCompressButton2Click();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        LZZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    onDecompressButton2Click();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        m11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(m11);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    file = fc.getSelectedFile();
                    // This is where a real application would open the file.
                    // log.append("Opening: " + file.getName() + "." + newline);
                    // } else {
                    // log.append("Open command cancelled by user." + newline);
                }
                // log.setCaretPosition(log.getDocument().getLength()); //
                // https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html

            }
        });

        // Text Area at the Center
        JTextArea ta = new JTextArea();
        ta.setBackground(new Color(0xE0E0E0));

        // Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}