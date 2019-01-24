package main.java.com.fjj.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener , Common {
    private static final long serialVersionUID = 1L;
    //����ȫ�ֳ���,ΪĬ�ϵ�[���]�����
    private static final int WIDTH = 720;
    private static final int HEIGHT = 720;
    //��Ϸ��ͼ
    private GameMap map;
    //��ɫ����
    private GameHandle role;
    public MyPanel(){
        //����PanelĬ�ϴ�С
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //�趨�����ڱ����岢�����������
        setFocusable(true);
        addKeyListener(this);
        map = new GameMap(this);
        role = new GameHandle(8,8,"../win/image/hero3.png",map,this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //����ͼ
        map.drawMap(g);
        map.drawItem(g);
        //����ɫ
        role.draw(g);
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
                role.move(LEFT);
                break;
            //������rigthʱ
            case KeyEvent.VK_RIGHT:
                //X++,�������ƶ�һ��
                role.move(RIGHT);
                break;
            //������upʱ
            case KeyEvent.VK_UP:
                //Y--,�������ƶ�һ��
                role.move(UP);
                break;
            //������downʱ
            case KeyEvent.VK_DOWN:
                //y++,�������ƶ�һ��
                role.move(DOWN);
                break;
            //������Aʱ
            case KeyEvent.VK_A:
                //X--,�������ƶ�һ��
                role.move(LEFT);
                break;
            //������Dʱ
            case KeyEvent.VK_D:
                //X++,�������ƶ�һ��
                role.move(RIGHT);
                break;
            //������Wʱ
            case KeyEvent.VK_W:
                //Y--,�������ƶ�һ��
                role.move(UP);
                break;
            //������downʱ
            case KeyEvent.VK_S:
                //y--,�������ƶ�һ��
                role.move(DOWN);
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
}
