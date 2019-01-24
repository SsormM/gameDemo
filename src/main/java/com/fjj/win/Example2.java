package main.java.com.fjj.win;

import javax.swing.*;
import java.awt.*;

public class Example2 extends JFrame {
    public Example2(){
        setTitle("Example2[java游戏中角色的移动与限制]");

        MyPanel myPanel = new MyPanel();
        Container contentPane = getContentPane();
        contentPane.add(myPanel);

        pack();
    }

    public static void main(String[] args) {
        Example2 example2 = new Example2();
        example2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example2.setVisible(true);

    }


}
