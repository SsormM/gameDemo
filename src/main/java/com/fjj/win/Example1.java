package main.java.com.fjj.win;

import javax.swing.*;
import java.awt.*;

public class Example1 extends JFrame {
    public Example1(){
        //默认窗体名称
        setTitle("Example1[Java游戏中地图的描绘]");

        //获得我们自定义的面板[地图面板]的实例
        MyPanel myPanel = new MyPanel();
        Container contentPane = getContentPane();
        contentPane.add(myPanel);

        pack();
    }

    public static void main(String[] args) {
        Example1 example1 = new Example1();
        //设定允许窗体关闭操作
        example1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        example1.setVisible(true);
    }
}
