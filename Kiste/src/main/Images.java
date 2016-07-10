package main;

import java.util.HashMap;

import javafx.scene.image.Image;

public class Images {

	private static Images instance;
	private HashMap<String, Image> img;
	
	private Images() {
		img = new HashMap<String, Image>();
	}
	
	public static Images getInstance() {
		if(instance == null) instance = new Images();
		return instance;
	}
	
	public void load(String key) {
		img.put(key, new Image(key + ".png"));
	}
	
	public Image getImage(String key) {
		if(!img.containsKey(key))
			load(key);
		return img.get(key);
	}
}
