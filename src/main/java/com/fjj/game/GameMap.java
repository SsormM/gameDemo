package main.java.com.fjj.game;

import main.java.com.fjj.game.MyPanel;

import javax.swing.*;
import java.awt.*;

public class GameMap implements Common {

    //设定背景方格默认行数
    private static final int ROW = 15;
    //设定背景方格默认列数
    private static final int COL = 15;

    //物品坐标
    private int itemx;
    private int itemy;

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
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
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
    private Image item;
    private Image door;

    //游戏使用面板
    private MyPanel panel;

    public GameMap(MyPanel panel){
        //初始化图像
        loadImage();
        itemx = 7;
        itemy = 7;
    }

    public void drawMap(Graphics g) {
        //在Java或任何游戏开发中，算法都是最重要的一步，本例尽量使用简单的双层for循环进行地图描绘
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                // switch作为java中的转换器，用于执行和()中数值相等的case操作。请注意，在case操作中如果不以break退出
                // 执行；switch函数将持续运算到最后一个case为止。
                switch (map[x][y]) {
                    case 0://map标记为0时画出地板
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
     * 用于判定是否允许移动的发生，被move()函数调用
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isAllow(int x, int y) {
        // 以(x,y)交点进行数据判定，我们都知道，在本例中我仅以0作为地板的参数，1作为墙的参数
        if (map[y][x] == 1) {
            //不允许移动
            return false;
        }
        return true;
    }

    //载入图像
    private void loadImage() {
        //获得当前类对应的相对位置image文件夹下的地板图像[以下图像可用任意素材代替]
        ImageIcon icon = new ImageIcon(getClass().getResource("../win/image/floor.png"));
        //将地板图像实例付与floorImage
        floorImage = icon.getImage();
        //获得当前类对应的相对位置image文件夹下的墙体图像
        icon = new ImageIcon(getClass().getResource("../win/image/wall.png"));
        //将墙体图像实例付与wallImage
        wallImage = icon.getImage();
        icon = new ImageIcon(getClass().getResource("../win/image/floor2.png"));
        item = icon.getImage();
        icon = new ImageIcon(getClass().getResource("../win/image/door.png"));
        door = icon.getImage();
    }
}
