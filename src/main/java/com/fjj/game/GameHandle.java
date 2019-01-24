package main.java.com.fjj.game;

import main.java.com.fjj.game.MyPanel;

import javax.swing.*;
import java.awt.*;

public class GameHandle implements Common {
    //设定显示图像对象
    private Image image;
    //角色坐标
    private int x;
    private int y;
    //增加计步器
    private int count;
    //用以确认角色所对方向,对应按键触发
    private int direction;
    private Thread threadAnime;
    private GameMap map;
    private MyPanel panel;

    public GameHandle(int x, int y, String fileName, GameMap map, MyPanel panel) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.panel = panel;

        direction = DOWN;
        count = 0;

        loadImage(fileName);

        threadAnime = new Thread(new AnimationThread());
        threadAnime.start();
    }

    public void draw(Graphics g) {
        g.drawImage(image, x * CS, y * CS, x * CS + CS, y * CS + CS,
                count * 2 * CS, direction * CS, count * 2 * CS + CS, direction * CS + CS,
                panel);
    }

    /**
     * 判断移动事件，关联isAllow()函数
     *
     * @param event
     */

    public void move(int event) {
        //以转换器判断相关事件,仅执行符合规范的操作;
        switch (event) {
            case LEFT:
                //依次判定事件
                if (map.isAllow(x - 1, y)) {
                    x--;
                }
                direction = LEFT;
                break;
            case RIGHT:
                if (map.isAllow(x + 1, y)) {
                    x++;
                }
                direction = RIGHT;
                break;
            case UP:
                if (map.isAllow(x, y - 1)) {
                    y--;
                }
                direction = UP;
                break;
            case DOWN:
                if (map.isAllow(x, y + 1)) {
                    y++;
                }
                direction = DOWN;
                break;
            default:
                break;
        }
    }

    //载入图像
    private void loadImage(String fileName) {
        //获得当前类对应的相对位置image文件夹下的地板图像[以下图像可用任意素材代替]
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        image = icon.getImage();
    }

    //  内部类，用于处理计步动作。

    private class AnimationThread extends Thread {
        public void run() {
            while (true) {
                //count 计步
                if (count == 0) {
                    count = 1;
                } else if (count == 1) {
                    count = 0;
                }
                panel.repaint();
                // 每300毫秒改变一次动作。
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
