package com.neusoft.planwar.constant;

import java.io.IOException;
import java.util.Properties;

public class Constant {
	public static final Properties props = new Properties();
	static {
		try {
			props.load(Constant.class.getClassLoader().getResourceAsStream("game.propertics"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static final String IMGPATH_PRE = props.getProperty("IMGPATH_PRE");
	public static final int GAME_WIDTH = Integer.parseInt(props.getProperty("GAME_WIDTH"));
	public static final int GAME_HEIGHT = Integer.parseInt(props.getProperty("GAME_HEIGHT"));
	public static final int MYPLANE01_SPEED = Integer.parseInt(props.getProperty("MYPLANE01_SPEED"));
	public static final int MYPLANE01_BULLET_SPEED = Integer.parseInt(props.getProperty("MYPLANE01_BULLET_SPEED"));
	public static final int ENEMYPLANE01_SPEED = Integer.parseInt(props.getProperty("ENEMYPLANE01_SPEED"));
	public static final int PLANE_COUNT = Integer.parseInt(props.getProperty("PLANE_COUNT"));
}
