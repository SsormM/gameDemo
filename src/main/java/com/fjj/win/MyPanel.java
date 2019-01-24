package main.java.com.fjj.win;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {
    //����ȫ�ֳ���,ΪĬ�ϵ�[���]�����
    private static final int WIDTH = 720;
    private static final int HEIGTH = 720;

    //�趨��������Ĭ������
    private static final int ROW = 15;
    //�趨��������Ĭ������
    private static final int COL = 15;

    //����ͼ���С����Ĭ�ϲ���32x32ͼ��,�ɸ�����Ҫ����������

    //��ʱ��ʼ��Ӧ�ʹ����С����Э��������32x32��ͼƬ�����

    //һ������15������ô����480,Ҳ���Ǳ�����Ĭ�ϵĴ����С��

    //��Ȼ������Ҳ���Ը���ROW*CS,COl*CS�ڳ�ʼ��ʱ�Զ�����

    //�����С���Ժ�������л��õ����������
    private static final int CS = 48;
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
    private Image roleImage;
    private Image item;
    private Image door;

    //��ɫ����
    private int x;
    private int y;
    //��Ʒ����
    private int itemx;
    private int itemy;
    //���ӼƲ���
    private int count;
    //�˴��������һ�鳣�������������������°����Ĵ���
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 0;
    //����ȷ�Ͻ�ɫ���Է���,��Ӧ��������
    private int direction;
    private Thread threadAnime;

    public MyPanel() {
        //����PanelĬ�ϴ�С
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        //�ڳ�ʼ��ʱ����ͼ��
        loadImage();
        //��ʼ����ɫλ��
        x = 8;
        y = 8;
        //��ʼ��itemλ��
        itemx=7;
        itemy=7;
        //Ĭ�Ͻ�ɫ����
        direction = DOWN;
        //����幹��ʱ����Ʋ�����ֵ
        count = 0;
        //�趨�����ڱ����岢�����������
        setFocusable(true);
        addKeyListener(this);
        //ʵ�����ڲ��߳�AnimationThread
        threadAnime = new Thread(new AnimationThread());
        //�����߳�
        threadAnime.start();
    }

    /**
     * GraphicsΪjava.awt�������࣬����[���]ͼ�ν���
     * <p>
     * �����Java�µ�ͼ�ν��濪��ʱ�������Դ���Ϊ�����ġ�
     */

    public void paintComponent(Graphics g) {
        //��������û���Զ���Graphics,����ֱ�ӵ���[����]��ͬ������ʵ�֡�
        super.paintComponent(g);
        drawMap(g);
        //��MyPanel(20,50)��λ����"��"��"Easy Java"
        //�������drawImage����������ָ��λ�ü���һ��ͼƬ��
        //g.drawString("fxxk �ھ�", 40, 40);
        drawRole(g);
        drawItem(g);
    }

    //����ͼ��
    private void loadImage() {
        //��õ�ǰ���Ӧ�����λ��image�ļ����µĵذ�ͼ��[����ͼ����������زĴ���]
        ImageIcon icon = new ImageIcon(getClass().getResource("image/floor.png"));
        //���ذ�ͼ��ʵ������floorImage
        floorImage = icon.getImage();
        //��õ�ǰ���Ӧ�����λ��image�ļ����µ�ǽ��ͼ��
        icon = new ImageIcon(getClass().getResource("image/wall.png"));
        //��ǽ��ͼ��ʵ������wallImage
        wallImage = icon.getImage();
        icon = new ImageIcon(getClass().getResource("image/floor2.png"));
        item = icon.getImage();
        icon = new ImageIcon(getClass().getResource("image/hero3.png"));
        roleImage = icon.getImage();
        icon = new ImageIcon(getClass().getResource("image/door.png"));
        door = icon.getImage();

    }

    private void drawRole(Graphics g) {
        g.drawImage(roleImage, x * CS, y * CS, x * CS + CS, y * CS + CS,
                count * 2 * CS, direction * CS, count * 2 * CS + CS, direction * CS + CS,
                this);
    }

    private void drawItem(Graphics g) {
        g.drawImage(item, itemx * CS, itemy * CS, itemx * CS + CS, itemy * CS + CS,
                CS, CS, CS + CS, CS + CS, this);
    }

    private void drawMap(Graphics g) {
        //��Java���κ���Ϸ�����У��㷨��������Ҫ��һ������������ʹ�ü򵥵�˫��forѭ�����е�ͼ���
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                // switch��Ϊjava�е�ת����������ִ�к�()����ֵ��ȵ�case��������ע�⣬��case�������������break�˳�
                // ִ�У�switch�������������㵽���һ��caseΪֹ��
                switch (map[x][y]) {
                    case 0://map���Ϊ0ʱ�����ذ�
                        g.drawImage(floorImage, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                0, 2 * CS, CS, 2 * CS + CS, this);
                        break;
                    case 1:
                        g.drawImage(wallImage, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                0, 0, CS, CS, this);
                        break;
                    case 2:
                        g.drawImage(item, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                CS, CS, CS + CS, CS + CS, this);
                        break;
                    case 3:
                        g.drawImage(door, y * CS, x * CS, y * CS + CS, x * CS + CS,
                                0, 0, CS, CS, this);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //��ð������
        int keyCode = e.getKeyCode();

        //ͨ��ת����ƥ���¼�
        switch (keyCode) {
            //������leftʱ
            case KeyEvent.VK_LEFT:
                //X--,�������ƶ�һ��
                move(LEFT);
                break;
            //������rigthʱ
            case KeyEvent.VK_RIGHT:
                //X++,�������ƶ�һ��
                move(RIGHT);
                break;
            //������upʱ
            case KeyEvent.VK_UP:
                //Y--,�������ƶ�һ��
                move(UP);
                break;
            //������downʱ
            case KeyEvent.VK_DOWN:
                //y++,�������ƶ�һ��
                move(DOWN);
                break;
            //������Aʱ
            case KeyEvent.VK_A:
                //X--,�������ƶ�һ��
                move(LEFT);
                break;
            //������Dʱ
            case KeyEvent.VK_D:
                //X++,�������ƶ�һ��
                move(RIGHT);
                break;
            //������Wʱ
            case KeyEvent.VK_W:
                //Y--,�������ƶ�һ��
                move(UP);
                break;
            //������downʱ
            case KeyEvent.VK_S:
                //y--,�������ƶ�һ��
                move(DOWN);
                break;
            default:
                break;
        }
        // ���»��ƴ���ͼ��
        // PS:�ڴ������У��������˽�ɫ�ļ��ƶ�������ڱ�����˸�����ƻ�������⣬�������������
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * �����ж��Ƿ������ƶ��ķ�������move()��������
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isAllow(int x, int y) {
        // ��(x,y)������������ж������Ƕ�֪�����ڱ������ҽ���0��Ϊ�ذ�Ĳ�����1��Ϊǽ�Ĳ���
        if (map[y][x] == 1) {
            //�������ƶ�
            return false;
        }
        return true;
    }

    /**
     * �ж��ƶ��¼�������isAllow()����
     *
     * @param event
     */

    private void move(int event) {
        //��ת�����ж�����¼�,��ִ�з��Ϲ淶�Ĳ���;
        switch (event) {
            case LEFT:
                //�����ж��¼�
                if (isAllow(x - 1, y)) {
                    x--;
                }
                direction = LEFT;
                break;
            case RIGHT:
                if (isAllow(x + 1, y)) {
                    x++;
                }
                direction = RIGHT;
                break;
            case UP:
                if (isAllow(x, y - 1)) {
                    y--;
                }
                direction = UP;
                break;
            case DOWN:
                if (isAllow(x, y + 1)) {
                    y++;
                }
                direction = DOWN;
                break;
            default:
                break;
        }
    }

    //�ڲ��࣬���ڴ���Ʋ�������
    public class AnimationThread extends Thread {
        @Override
        public void run() {
            while (true) {
                //count �Ʋ�
                if (count == 0) {
                    count = 1;
                } else if (count == 1) {
                    count = 0;
                }
                repaint();
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
