package main;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends ImageView {

	public enum ButtonState {
		NORMAL, HOVER, PRESSED
	}
	
	private Image img, hover, pressed;
	
	public void setNormal(String key) {
		img = Images.getInstance().getImage(key);
		changeExit(ButtonState.NORMAL);
	}
	
	public void setHover(String key) {
		hover = Images.getInstance().getImage(key);
	}
	
	public void setPressed(String key) {
		pressed = Images.getInstance().getImage(key);
	}
	
	public void createChangeListener() {

		setOnMouseEntered((event) -> {
			if(!isPressed())
				changeExit(ButtonState.HOVER);
		});
		setOnMouseExited((event) -> {
			if(!isPressed())
				changeExit(ButtonState.NORMAL);
		});
		
		setOnMousePressed((event) -> changeExit(ButtonState.PRESSED));
		setOnMouseReleased((event) -> {
			if(isHover()) {
				changeExit(ButtonState.HOVER);
			} else {
				changeExit(ButtonState.NORMAL);
			}
		});
	}

	public void setOnAction(EventHandler<? super MouseEvent> event) {
		
		setOnMouseClicked(event);
	}
	
	/**
	 * Changes the image of the exit button to the given state.
	 * 
	 * @param i
	 *            The state of the button.
	 */
	public void changeExit(ButtonState state) {
		
		switch (state) {
		case NORMAL:
			setImage(img);
			break;
		case HOVER:
			setImage(hover);
			break;
		case PRESSED:
			setImage(pressed);
			break;
		default:
			break;
		}
	}
}
