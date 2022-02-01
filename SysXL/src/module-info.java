module sys {
	requires javafx.graphics;
	requires javafx.fxml;
	requires transitive javafx.controls;
	requires java.desktop;
	requires java.sql;
	requires org.apache.poi.poi;

	exports sys to javafx.graphics, javafx.fxml;

	opens sys to javafx.fxml;
}