package org.flyfish.springstudy.kotlinstudy;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class JFrameTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("My Jframe");
        String name = "test";
        JButton jButton = new JButton("My JButton");

        jFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("windowOpened");
                name.charAt(0);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("windowActivated");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
//        jButton.addActionListener(new AbstractAction() {
//            private static final long serialVersionUID = 1395149109797545779L;
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        //lamda 方式
        jButton.addActionListener((event)-> System.out.println("jButton pressed"));
        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
