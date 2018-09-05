package ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

@Theme("valo")
@Title("Hello-World")
@SuppressWarnings("serial")
public class HelloWorldUI extends UI {

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = HelloWorldUI.class)
    public static class Servlet extends VaadinServlet {

    }

    private int clickCounter = 0;

    private Label clickCounterLabel;

    protected void init(VaadinRequest request){
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        clickCounterLabel = new Label();
        layout.addComponent(clickCounterLabel);

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button button = new Button("Suche", FontAwesome.SEARCH);
        Button bookButton = new Button("Buchen");
        TextField textField = new TextField();
        Label label = new Label("Geben Sie einen Ort ein: ");

        horizontalLayout.addComponents(label, textField, button,bookButton);
    }
}
