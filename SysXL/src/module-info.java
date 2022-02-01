module sys {
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.fxml;
	requires transitive javafx.controls;
	requires java.desktop;
	requires org.apache.poi.poi;
	requires java.sql;

	exports sys to javafx.graphics, javafx.fxml;

	opens sys to javafx.fxml;
}