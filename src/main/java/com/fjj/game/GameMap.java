package main.java.com.fjj.game;

import main.java.com.fjj.game.MyPanel;

import javax.swing.*;
import java.awt.*;

public class GameMap implements Common {

    //�趨��������Ĭ������
    private static final int ROW = 15;
    //�趨��������Ĭ������
    private static final int COL = 15;

    //��Ʒ����
    private int itemx;
    private int itemy;

    //�趨��ͼ��ͨ����rpg������Ϸ�����У���[��ά����]����Ϊ

    //�������е�ͼ������������X�����Y���ꡣʵ����,����

    //�ٻ�����RPG����Ϸ�����Ǵ���Щ�򵥵�X,Y���꿪ʼ�ġ�

    //PS:��ν[����]����ҿ��Լ򵥵����Ϊ�����ݵļ��ϣ�һά����

    //������X�ᣬ����ά����X,Y��������ɵģ�X��Y�Ľ�֯�㣬��Ϊ

    //һ�����ݡ�
    private int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 3, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    //�趨��ʾͼ�����
    private Image floorImage;
    private Image wallImage;
    private Image item;
    private Image door;

    //��Ϸʹ�����
    private MyPanel panel;

    public GameMap(MyPanel panel){
        //��ʼ��ͼ��
        loadImage();
        itemx = 7;
        itemy = 7;
    }

    public void drawMap(Graphics g) {
        //��Java���κ���Ϸ�����У��㷨��������Ҫ��һ������������ʹ�ü򵥵�˫��forѭ�����е�ͼ���
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                // switch��Ϊjava�е�ת����������ִ�к�()����ֵ��ȵ�case��������ע�⣬��case�������������break�˳�
                // ִ�У�switch�������������㵽���һ��caseΪֹ��
                switch (map[x][y]) {
                    case 0://map���Ϊ0ʱ�����ذ�
                        g.drawImage(floorImage, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                0, 2 * CS, CS, 2 * CS + CS, panel);
                        break;
                    case 1:
                        g.drawImage(wallImage, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                0, 0, CS, CS, panel);
                        break;
                    case 2:
                        g.drawImage(item, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                CS, CS, CS + CS, CS + CS, panel);
                        break;
                    case 3:
                        g.drawImage(door, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                0, 0, CS, CS, panel);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void drawItem(Graphics g) {
        g.drawImage(item, itemx * CS, itemy * CS, itemx * CS + CS, itemy * CS + CS,
                CS, CS, CS + CS, CS + CS, panel);
    }

    /**
     * �����ж��Ƿ������ƶ��ķ�������move()��������
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isAllow(int x, int y) {
        // ��(x,y)������������ж������Ƕ�֪�����ڱ������ҽ���0��Ϊ�ذ�Ĳ�����1��Ϊǽ�Ĳ���
        if (map[y][x] == 1) {
            //�������ƶ�
            return false;
        }
        return true;
    }

    //����ͼ��
    private void loadImage() {
        //��õ�ǰ���Ӧ�����λ��image�ļ����µĵذ�ͼ��[����ͼ����������زĴ���]
        ImageIcon icon = new ImageIcon(getClass().getResource("../win/image/floor.png"));
        //���ذ�ͼ��ʵ������floorImage
        floorImage = icon.getImage();
        //��õ�ǰ���Ӧ�����λ��image�ļ����µ�ǽ��ͼ��
        icon = new ImageIcon(getClass().getResource("../win/image/wall.png"));
        //��ǽ��ͼ��ʵ������wallImage
        wallImage = icon.getImage();
        icon = new ImageIcon(getClass().getResource("../win/image/floor2.png"));
        item = icon.getImage();
        icon = new ImageIcon(getClass().getResource("../win/image/door.png"));
        door = icon.getImage();
    }
}
