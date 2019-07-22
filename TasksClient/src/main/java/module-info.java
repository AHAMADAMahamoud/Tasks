module fr.sii.tasks.client {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires org.apache.commons.io;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpcore;
	 requires java.sql;
	requires org.json;
	
    opens com.kmsoft.tasks.client to javafx.fxml;
    exports com.kmsoft.tasks.client;
}