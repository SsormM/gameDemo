package main.java.com.fjj.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener , Common {
    private static final long serialVersionUID = 1L;
    //定义全局常量,为默认的[面板]宽与高
    private static final int WIDTH = 720;
    private static final int HEIGHT = 720;
    //游戏地图
    private GameMap map;
    //角色控制
    private GameHandle role;
    public MyPanel(){
        //设置Panel默认大小
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        //设定焦点在本窗体并赋予监听对象
        setFocusable(true);
        addKeyListener(this);
        map = new GameMap(this);
        role = new GameHandle(8,8,"../win/image/hero3.png",map,this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //描绘地图
        map.drawMap(g);
        map.drawItem(g);
        //描绘角色
        role.draw(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //获得按键编号
        int keyCode = e.getKeyCode();
        //通过转换器匹配事件
        switch (keyCode) {
            //当触发left时
            case KeyEvent.VK_LEFT:
                //X--,既向左移动一格
                role.move(LEFT);
                break;
            //当触发rigth时
            case KeyEvent.VK_RIGHT:
                //X++,既向右移动一格
                role.move(RIGHT);
                break;
            //当触发up时
            case KeyEvent.VK_UP:
                //Y--,既向上移动一格
                role.move(UP);
                break;
            //当触发down时
            case KeyEvent.VK_DOWN:
                //y++,既向下移动一格
                role.move(DOWN);
                break;
            //当触发A时
            case KeyEvent.VK_A:
                //X--,既向左移动一格
                role.move(LEFT);
                break;
            //当触发D时
            case KeyEvent.VK_D:
                //X++,既向右移动一格
                role.move(RIGHT);
                break;
            //当触发W时
            case KeyEvent.VK_W:
                //Y--,既向上移动一格
                role.move(UP);
                break;
            //当触发down时
            case KeyEvent.VK_S:
                //y--,既向下移动一格
                role.move(DOWN);
                break;
            default:
                break;
        }
        // 重新绘制窗体图像
        // PS:在此例程中，仅进行了角色的简单移动处理关于避免闪烁及限制活动区域问题，请见后续案例。
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
