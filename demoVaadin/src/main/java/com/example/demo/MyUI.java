package com.example.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		final VerticalLayout main = new VerticalLayout();
		final Label selection = new Label("-");
		MenuBar barmenu = initMenu(selection);
		main.addComponent(barmenu);
		main.addComponent(selection);

		main.setMargin(true);
		main.setSpacing(true);

		setContent(main);
	}

	private MenuBar initMenu(Label selection) {
		MenuBar barmenu = new MenuBar();

		// Define a common menu command for all the menu items.
		MenuBar.Command mycommand = new MenuBar.Command() {
			public void menuSelected(MenuItem selectedItem) {
				selection.setValue("Ordered a " + selectedItem.getText() + " from menu.");
			}
		};

		MenuItem drinks = barmenu.addItem("Beverages", null, null);
		// Submenu item with a sub-submenu
		MenuItem hots = drinks.addItem("Hot", null, null);
		hots.addItem("Tea", mycommand);
		hots.addItem("Coffee", mycommand);

		// Another submenu item with a sub-submenu
		MenuItem colds = drinks.addItem("Cold", null, null);
		colds.addItem("Milk", null, mycommand);
		colds.addItem("Weissbier", null, mycommand);

		// Another top-level item
		MenuItem snacks = barmenu.addItem("Snacks", null, null);
		snacks.addItem("Weisswurst", null, mycommand);
		snacks.addItem("Bratwurst", null, mycommand);
		snacks.addItem("Currywurst", null, mycommand);

		// Yet another top-level item
		MenuItem servs = barmenu.addItem("Services", null, null);
		servs.addItem("Car Service", null, mycommand);

		return barmenu;
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
