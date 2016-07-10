package main;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Kiste {

	private Scene scene;

	private StackPane sp;
	private ImageView[] kiste;
	private ImageButton btnExit, btnNew, btnRemove;
	private DragDelta dragDelta;
	private boolean draged;

	public Kiste() {
		this(new Stage());
	}

	public Kiste(Stage stage) {

		dragDelta = new DragDelta();

		sp = new StackPane();

		loadKiste(stage);
		loadButtons(stage);

		changePic(false);

		scene = new Scene(sp);
		scene.setFill(Color.TRANSPARENT);

		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
	}

	private void changePic(boolean open) {
		if (open) {
			sp.getChildren().clear();
			sp.getChildren().addAll(kiste[0], kiste[2], btnExit, btnNew, btnRemove);
		} else {
			sp.getChildren().clear();
			sp.getChildren().addAll(kiste[0], kiste[1]);
		}
	}

	private void loadKiste(Stage stage) {

		kiste = new ImageView[3];

		kiste[0] = new ImageView(Images.getInstance().getImage("KisteUnten"));
		kiste[1] = new ImageView(Images.getInstance().getImage("KisteGeschlossen"));
		kiste[2] = new ImageView(Images.getInstance().getImage("KisteOffen"));

		for(ImageView img: kiste) {
			img.setOnMousePressed((event) -> {
				draged = false;
				dragDelta.x = stage.getX() - event.getScreenX();
				dragDelta.y = stage.getY() - event.getScreenY();
			});
			img.setOnMouseDragged((event) -> {
				draged = true;
				stage.setX(event.getScreenX() + dragDelta.x);
				stage.setY(event.getScreenY() + dragDelta.y);
			});
		}

		kiste[1].setOnMouseClicked((event) -> {
			if(!draged)
				changePic(true);
		});

		kiste[2].setOnMouseClicked((event) -> {
			if(!draged)
				changePic(false);
		});
	}

	private void loadButtons(Stage stage) {

		btnExit = new ImageButton();
		btnExit.setNormal("KnopfExit");
		btnExit.setHover("KnopfExitHover");
		btnExit.setPressed("KnopfExitPressed");
		btnExit.createChangeListener();
		btnExit.setOnAction((event) -> System.exit(0));

		btnNew = new ImageButton();
		btnNew.setNormal("KnopfNew");
		btnNew.setHover("KnopfNewHover");
		btnNew.setPressed("KnopfNewPressed");
		btnNew.createChangeListener();
		btnNew.setOnAction((event) -> new Kiste());
		
		btnRemove = new ImageButton();
		btnRemove.setNormal("KnopfRemove");
		btnRemove.setHover("KnopfRemoveHover");
		btnRemove.setPressed("KnopfRemovePressed");
		btnRemove.createChangeListener();
		btnRemove.setOnAction((event) -> stage.close());
	}
}

class DragDelta {
	public double x, y;
}
