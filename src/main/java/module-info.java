module br.com.dolomia.sysxl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;
    requires java.sql;
    requires poi.ooxml;
    requires org.apache.logging.log4j;
    requires org.junit.jupiter.api;


    opens br.com.dolomia.sysxl.main to javafx.fxml;
    exports br.com.dolomia.sysxl.main;
    exports br.com.dolomia.sysxl.controller;
    opens br.com.dolomia.sysxl.controller to javafx.fxml;
}