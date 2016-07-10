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
	
	public void setNormal(String path) {
		img = Images.getInstance().getImage(path);
		changeExit(ButtonState.NORMAL);
	}
	
	public void setHover(String path) {
		hover = Images.getInstance().getImage(path);
	}
	
	public void setPressed(String path) {
		pressed = Images.getInstance().getImage(path);
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
		setOnMouseReleased((event) -> changeExit(ButtonState.NORMAL));
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
