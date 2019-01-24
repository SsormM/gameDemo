package main.java.com.fjj.win;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {
    //定义全局常量,为默认的[面板]宽与高
    private static final int WIDTH = 720;
    private static final int HEIGTH = 720;

    //设定背景方格默认行数
    private static final int ROW = 15;
    //设定背景方格默认列数
    private static final int COL = 15;

    //单个图像大小，我默认采用32x32图形,可根据需要调整比例。

    //当时，始终应和窗体大小比例协调；比如32x32的图片，如何

    //一行设置15个，那么就是480,也就是本例子默认的窗体大小，

    //当然，我们也可以根据ROW*CS,COl*CS在初始化时自动调整

    //窗体大小，以后的例子中会用到类似情况。
    private static final int CS = 48;
    //设定地图，通常在rpg类型游戏开发中，以[二维数组]对象为

    //基础进行地图处理，用以描绘出X坐标和Y坐标。实际上,即令

    //再华丽的RPG类游戏，都是从这些简单的X,Y坐标开始的。

    //PS:所谓[数组]，大家可以简单的理解为即数据的集合，一维数组

    //仅包含X轴，而二维是由X,Y两个轴组成的，X与Y的交织点，即为

    //一条数据。
    private int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 2, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 3, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    //设定显示图像对象
    private Image floorImage;
    private Image wallImage;
    private Image roleImage;
    private Image item;
    private Image door;

    //角色坐标
    private int x;
    private int y;
    //增加计步器
    private int count;
    //此处我们添加一组常数，用以区别左右上下按键的触发
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    //用以确认角色所对方向,对应按键触发
    private int direction;
    private Thread threadAnime;

    public MyPanel() {
        //设置Panel默认大小
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        //于初始化时载入图形
        loadImage();
        //初始化角色位置
        x = 8;
        y = 8;
        //默认角色向下
        direction = DOWN;
        //在面板构建时赋予计步器初值
        count = 0;
        //设定焦点在本窗体并赋予监听对象
        setFocusable(true);
        addKeyListener(this);
        //实例化内部线程AnimationThread
        threadAnime = new Thread(new AnimationThread());
        //启动线程
        threadAnime.start();
    }

    /**
     * Graphics为java.awt下所有类，用以[描绘]图形界面
     * <p>
     * 大多数Java下的图形界面开发时，都是以此类为基础的。
     */

    public void paintComponent(Graphics g) {
        //这里我们没有自定义Graphics,而是直接调用[父类]的同名方法实现。
        super.paintComponent(g);
        drawMap(g);
        //在MyPanel(20,50)的位置上"画"出"Easy Java"
        //如果换成drawImage函数就能在指定位置加载一张图片。
        //g.drawString("fxxk 挖局", 40, 40);
        drawRole(g);
    }

    //载入图像
    private void loadImage() {
        //获得当前类对应的相对位置image文件夹下的地板图像[以下图像可用任意素材代替]
        ImageIcon icon = new ImageIcon(getClass().getResource("image/floor3.png"));
        //将地板图像实例付与floorImage
        floorImage = icon.getImage();
        //获得当前类对应的相对位置image文件夹下的墙体图像
        icon = new ImageIcon(getClass().getResource("image/wall.png"));
        //将墙体图像实例付与wallImage
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
                count * 2*CS, 0, count * 2*CS + CS, CS, this);
    }

    private void drawMap(Graphics g) {
        //在Java或任何游戏开发中，算法都是最重要的一步，本例尽量使用简单的双层for循环进行地图描绘
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                // switch作为java中的转换器，用于执行和()中数值相等的case操作。请注意，在case操作中如果不以break退出
                // 执行；switch函数将持续运算到最后一个case为止。
                switch (map[x][y]) {
                    case 0://map标记为0时画出地板
                        g.drawImage(floorImage, y * CS, x * CS, this);
                        break;
                    case 1:
                        g.drawImage(wallImage, y * CS, x * CS, this);
                        break;
                    case 2:
                        g.drawImage(item, y * CS, x * CS, this);
                        break;
                    case 3:
                        g.drawImage(door, y * CS, x * CS, this);
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
        //获得按键编号
        int keyCode = e.getKeyCode();

        //通过转换器匹配事件
        switch (keyCode) {
            //当触发left时
            case KeyEvent.VK_LEFT:
                //X--,既向左移动一格
                move(LEFT);
                break;
            //当触发rigth时
            case KeyEvent.VK_RIGHT:
                //X++,既向右移动一格
                move(RIGHT);
                break;
            //当触发up时
            case KeyEvent.VK_UP:
                //Y--,既向上移动一格
                move(UP);
                break;
            //当触发down时
            case KeyEvent.VK_DOWN:
                //y++,既向下移动一格
                move(DOWN);
                break;
            //当触发A时
            case KeyEvent.VK_A:
                //X--,既向左移动一格
                move(LEFT);
                break;
            //当触发D时
            case KeyEvent.VK_D:
                //X++,既向右移动一格
                move(RIGHT);
                break;
            //当触发W时
            case KeyEvent.VK_W:
                //Y--,既向上移动一格
                move(UP);
                break;
            //当触发down时
            case KeyEvent.VK_S:
                //y--,既向下移动一格
                move(DOWN);
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

    /**
     * 用于判定是否允许移动的发生，被move()函数调用
     *
     * @param x
     * @param y
     * @return
     */
    private boolean isAllow(int x, int y) {
        // 以(x,y)交点进行数据判定，我们都知道，在本例中我仅以0作为地板的参数，1作为墙的参数
        if (map[y][x] == 1) {
            //不允许移动
            return false;
        }
        return true;
    }

    /**
     * 判断移动事件，关联isAllow()函数
     *
     * @param event
     */

    private void move(int event) {
        //以转换器判断相关事件,仅执行符合规范的操作;
        switch (event) {
            case LEFT:
                //依次判定事件
                if (isAllow(x - 1, y)) {
                    x--;
                }
                break;
            case RIGHT:
                if (isAllow(x + 1, y)) {
                    x++;
                }
                break;
            case UP:
                if (isAllow(x, y - 1)) {
                    y--;
                }
                break;
            case DOWN:
                if (isAllow(x, y + 1)) {
                    y++;
                }
                break;
            default:
                break;
        }
    }

    //内部类，用于处理计步动作。
    public class AnimationThread extends Thread {
        @Override
        public void run() {
            while (true) {
                //count 计步
                if (count == 0) {
                    count = 1;
                } else if (count == 1) {
                    count = 0;
                }
                repaint();
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
