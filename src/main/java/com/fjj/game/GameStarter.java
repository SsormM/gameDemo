package main.java.com.fjj.game;

import javax.swing.*;
import java.awt.*;

public class GameStarter extends JFrame {
    public  GameStarter(){
        setTitle("Java 游戏测试");
        MyPanel myPanel = new MyPanel();
        Container container = getContentPane();
        //加载自定义的面板[MyPanel]到窗体中，就好比在底板上再帖一层画面。
        //也就是说显示什么内容，是由我们加载那个［面板］来决定的，可以轻松
        //的实现游戏中不同画面间的切换．
        container.add(myPanel);
        //自动整合窗体
        pack();
    }

    public static void main(String[] args) {
        GameStarter gameStarter = new GameStarter();
        //令窗体接受关闭[事件]
        gameStarter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示窗体（PS:在Java中本有show()方法，但已成为准淘汰方法，
        //说不定什么时候便不支持了，强烈不建议使用，虽然Visible内部
        //目前还是调用它……
        gameStarter.setVisible(true);
    }
}
