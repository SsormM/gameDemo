package main.java.com.fjj.game;

import main.java.com.fjj.game.MyPanel;

import javax.swing.*;
import java.awt.*;

public class GameHandle implements Common {
    //�趨��ʾͼ�����
    private Image image;
    //��ɫ����
    private int x;
    private int y;
    //���ӼƲ���
    private int count;
    //����ȷ�Ͻ�ɫ���Է���,��Ӧ��������
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
     * �ж��ƶ��¼�������isAllow()����
     *
     * @param event
     */

    public void move(int event) {
        //��ת�����ж�����¼�,��ִ�з��Ϲ淶�Ĳ���;
        switch (event) {
            case LEFT:
                //�����ж��¼�
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

    //����ͼ��
    private void loadImage(String fileName) {
        //��õ�ǰ���Ӧ�����λ��image�ļ����µĵذ�ͼ��[����ͼ����������زĴ���]
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        image = icon.getImage();
    }

    //  �ڲ��࣬���ڴ���Ʋ�������

    private class AnimationThread extends Thread {
        public void run() {
            while (true) {
                //count �Ʋ�
                if (count == 0) {
                    count = 1;
                } else if (count == 1) {
                    count = 0;
                }
                panel.repaint();
                // ÿ300����ı�һ�ζ�����
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
