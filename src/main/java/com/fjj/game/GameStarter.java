package main.java.com.fjj.game;

import javax.swing.*;
import java.awt.*;

public class GameStarter extends JFrame {
    public  GameStarter(){
        setTitle("Java ��Ϸ����");
        MyPanel myPanel = new MyPanel();
        Container container = getContentPane();
        //�����Զ�������[MyPanel]�������У��ͺñ��ڵװ�������һ�㻭�档
        //Ҳ����˵��ʾʲô���ݣ��������Ǽ����Ǹ��������������ģ���������
        //��ʵ����Ϸ�в�ͬ�������л���
        container.add(myPanel);
        //�Զ����ϴ���
        pack();
    }

    public static void main(String[] args) {
        GameStarter gameStarter = new GameStarter();
        //�����ܹر�[�¼�]
        gameStarter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʾ���壨PS:��Java�б���show()���������ѳ�Ϊ׼��̭������
        //˵����ʲôʱ��㲻֧���ˣ�ǿ�Ҳ�����ʹ�ã���ȻVisible�ڲ�
        //Ŀǰ���ǵ���������
        gameStarter.setVisible(true);
    }
}
