package com.bookkeeper;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Frame for displaying the dashboard with menu bar and content panels
public class DashboardFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//panel
	private JPanel mainPanel;
	private JPanel menuBarPanel;
	private JPanel ContentPanel;
	private AdminMenuPanel AdminMenuPanel;
	private AdminLibraryPanel AdminLibraryPanel;
	private AdminReportPanel AdminReportPanel;
	private AdminManagePatronPanel AdminManagePatronPanel;
	private AdminManageEmployeePanel AdminManageEmployeePanel;
	private AdminSettingsPanel AdminSettingsPanel;
	private PatronMenuPanel PatronMenuPanel;
	private PatronLibraryPanel PatronLibraryPanel;
	private PatronSettingsPanel PatronSettingsPanel;
	
	//layout
	private CardLayout cardLayout1;
	private CardLayout cardLayout2;
	
	//icon
	private ImageIcon icon;
	private Image image;
	
	//Graphics environment and screen dimensions
	private GraphicsEnvironment environment;	
	private GraphicsDevice device;
	private int width;
	private int height;
	private int selectedValue;
	
	//admin
	public DashboardFrame(Employee employee, Setting setting) {
		setTitle("Book Keeper");
    	
//    	// Set the Icon
    	icon = new ImageIcon("img/Logo_Original.png"); 
    	image = icon.getImage();
    	setIconImage(image);
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Remove window decorations (title bar, borders)
    	setUndecorated(true); 
    	
    	// Prevent resizing
    	setResizable(false); 

    	// Set the frame size based on the screen dimensions
    	GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = environment.getDefaultScreenDevice();
        Rectangle screenBounds = device.getDefaultConfiguration().getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(device.getDefaultConfiguration());
        int width = screenBounds.width - screenInsets.left - screenInsets.right;
        int height = screenBounds.height - screenInsets.top - screenInsets.bottom;

        //device.setFullScreenWindow(this);
    	setSize(width,height);

	    //create panels
		mainPanel = new JPanel();//panel to hold all panels
		menuBarPanel = new JPanel();// panel to hold menu bar
		ContentPanel = new JPanel();// panel to hold dashboard contents
		//menu bar panels
		AdminMenuPanel = new AdminMenuPanel();//menu bar for admin and employees

		//dashboard panels
		AdminLibraryPanel = new AdminLibraryPanel(setting);//panel for  book-related operations 
		AdminReportPanel  = new AdminReportPanel();//panel for showcasing reports regarding the library
		AdminManagePatronPanel  = new AdminManagePatronPanel();//panel for managing patrons
		AdminManageEmployeePanel  = new AdminManageEmployeePanel();//panel for managing employees
		AdminSettingsPanel  = new AdminSettingsPanel(employee, setting);//panel for the settings of admin and employees

		
		// Set the layout of panels
		cardLayout1 = new CardLayout(0, 0);
		cardLayout2 = new CardLayout(0, 0);
		ContentPanel.setLayout(cardLayout1);
		menuBarPanel.setLayout(cardLayout2);
		mainPanel.setLayout(new BorderLayout(0, 0)); 

		// Add Panels to Menu Bar panel
		menuBarPanel.add(AdminMenuPanel, "panel1");
		
		// Add Panels to Dashboard panel
		ContentPanel.add(AdminLibraryPanel, "panel1");
		ContentPanel.add(AdminReportPanel, "panel2");
		ContentPanel.add(AdminManagePatronPanel, "panel3");		    	
		ContentPanel.add(AdminManageEmployeePanel, "panel4");
		ContentPanel.add(AdminSettingsPanel, "panel5");
		
		cardLayout1.show(ContentPanel, "panel1");
    	cardLayout2.show(menuBarPanel, "panel1");

		// Add Panels to Main panel
		mainPanel.add(menuBarPanel,BorderLayout.WEST);
		mainPanel.add(ContentPanel,BorderLayout.CENTER);
		
    	// Set mainPanel as the content pane of the JFrame
    	setContentPane(mainPanel);
		
    	// Center the frame on the screen
    	setLocationRelativeTo(null);

    	// Make the frame visible
    	setAlwaysOnTop(false);
    	setVisible(true);
    	
    	//Action Listeners for admin
    	AdminMenuPanel.getBtnLibrary().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout1.show(ContentPanel,"panel1");      
        		AdminMenuPanel.setBtnLibrary();
        	}
        });
    	AdminMenuPanel.getBtnReport().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Shield
//        		if(employee.getPosition().equals("Employee")) {
//        			MalfunctionPanel mal = new MalfunctionPanel("Access Denied", 
//        				"Oops! It appears you’re trying to access a page that is \n"
//       					+ "restricted to administrators only.");
//        			showDialog(mal);
//					return;
//        		}
        		cardLayout1.show(ContentPanel,"panel2");
        		AdminMenuPanel.setBtnReport();
        	}
        });
    	AdminMenuPanel.getBtnPatron().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout1.show(ContentPanel,"panel3");
        		AdminMenuPanel.setBtnPatron();

        	}
        });
    	AdminMenuPanel.getBtnEmployee().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//Shield
        		if(employee.getPosition().equals("Employee")) {
        			MalfunctionPanel mal = new MalfunctionPanel("Access Denied", 
        				"Oops! It appears you’re trying to access a page that is \n"
        				+ "restricted to administrators only.");
					showDialog(mal);
					return;
        		}
        		cardLayout1.show(ContentPanel,"panel4");
        		AdminMenuPanel.setBtnEmployee(); 

        	}
        });
    	AdminMenuPanel.getBtnAcc().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout1.show(ContentPanel,"panel5");
        		AdminMenuPanel.setBtnAcc();

        	}
        });
    	AdminMenuPanel.getBtnLogOut().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConfirmationPanel confirm = new ConfirmationPanel("Confirm Log Out", "Are you sure you want to log out?");
        		int option = showDialog(confirm);
			    
        		if(option == 1) {
        			DashboardFrame frame = (DashboardFrame) SwingUtilities.getWindowAncestor(AdminMenuPanel);
    				frame.dispose();
    				
    				AuthenticationFrame frame2 = new AuthenticationFrame();
    				frame2.setVisible(true);
        		}
        	}
        });
    }
	
	//patron
	public DashboardFrame(User user, Setting setting) {
		setTitle("Book Keeper");
    	
    	// Set the Icon
    	icon = new ImageIcon("img/Logo_Original.png"); 
    	image = icon.getImage();
    	setIconImage(image);
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	// Remove window decorations (title bar, borders)
    	setUndecorated(true); 
    	
    	// Prevent resizing
    	setResizable(false); 

    	// Set the frame size based on the screen dimensions
//    	GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
//    	GraphicsDevice device = devices[0]; // Choose the appropriate device index if there are multiple
//    	GraphicsConfiguration config = device.getDefaultConfiguration();
//    	setUndecorated(true);
//    	//device.setFullScreenWindow(this);
//    	setSize(config.getBounds().getSize());

    	GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = environment.getDefaultScreenDevice();
        Rectangle screenBounds = device.getDefaultConfiguration().getBounds();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(device.getDefaultConfiguration());
        int width = screenBounds.width - screenInsets.left - screenInsets.right;
        int height = screenBounds.height - screenInsets.top - screenInsets.bottom;

        //device.setFullScreenWindow(this);
    	setSize(width,height);

	 
	    //create panels
		mainPanel = new JPanel();//panel to hold all panels
		menuBarPanel = new JPanel();// panel to hold menu bar
		ContentPanel = new JPanel();// panel to hold dashboard contents
		//menu bar panels
		PatronMenuPanel = new PatronMenuPanel();//menu bar for patron
		//dashboard panels
		PatronLibraryPanel = new PatronLibraryPanel(user, setting);//panel for  book-related operations 
		PatronSettingsPanel  = new PatronSettingsPanel(user);//panel for the settings of patron
		
		// Set the layout of panels
		cardLayout1 = new CardLayout(0, 0);
		cardLayout2 = new CardLayout(0, 0);
		ContentPanel.setLayout(cardLayout1);
		menuBarPanel.setLayout(cardLayout2);
		mainPanel.setLayout(new BorderLayout(0, 0)); 

		// Add Panels to Menu Bar panel
		menuBarPanel.add(PatronMenuPanel, "panel2");
		
		// Add Panels to Dashboard panel
		ContentPanel.add(PatronLibraryPanel, "panel6");
		ContentPanel.add(PatronSettingsPanel, "panel7");
		
		    	
    	// Set mainPanel as the content pane of the JFrame
    	setContentPane(mainPanel);

    	cardLayout1.show(ContentPanel, "panel6");
    	cardLayout2.show(menuBarPanel, "panel2");


		// Add Panels to Main panel
		mainPanel.add(menuBarPanel,BorderLayout.WEST);
		mainPanel.add(ContentPanel,BorderLayout.CENTER);
		
    	// Center the frame on the screen
    	setLocationRelativeTo(null);

    	// Make the frame visible
    	setVisible(true);
    	
    	//Action Listeners for PatronMenuPanel
    	PatronMenuPanel.getBtnLibrary().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout1.show(ContentPanel,"panel6");
        		PatronMenuPanel.setBtnLibrary();
        	}
        });
    	PatronMenuPanel.getBtnAcc().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cardLayout1.show(ContentPanel,"panel7");
        		PatronMenuPanel.setBtnAcc();

        	}
        });
    	PatronMenuPanel.getBtnLogOut().addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ConfirmationPanel confirm = new ConfirmationPanel("Confirm Log Out", "Are you sure you want to log out?");
        		int option = showDialog(confirm);
			    
        		if(option == 1) {
        			DashboardFrame frame = (DashboardFrame) SwingUtilities.getWindowAncestor(PatronMenuPanel);
    				frame.dispose();
    				
    				AuthenticationFrame frame2 = new AuthenticationFrame();
    				frame2.setVisible(true);
        		}
        	}
        });
    }	
	// Methods
	// OVERLOADED METHOD -> showDialog()
	 	//Method to show alert panel (Success Panel)
	 	public void showDialog(SuccessPanel panel) {
	 		
	 		panel.getBtnConfirm().addActionListener(new ActionListener() {
	 	    	public void actionPerformed(ActionEvent e) {
	 	            closeDialog(e);
	 	    	}
	 	    });
			environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    	device = environment.getDefaultScreenDevice();
	       	width = (int) (device.getDisplayMode().getWidth() * 0.4);    	
	    	height = (int) (device.getDisplayMode().getHeight() * 0.23); 
	    	
			JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Book Keeper", true);
			dialog.setUndecorated(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.getContentPane().add(panel);
			dialog.setSize(width, height);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
	 	}
		 	
	 	//Method to show alert panel (Malfunction Panel)
	     public void showDialog(MalfunctionPanel panel) {
	 		
	 		panel.getBtnConfirm().addActionListener(new ActionListener() {
	 	    	public void actionPerformed(ActionEvent e) {
	 	            closeDialog(e);
	 	    	}
	 	    });
			environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    	device = environment.getDefaultScreenDevice();
	       	width = (int) (device.getDisplayMode().getWidth() * 0.4);    	
	    	height = (int) (device.getDisplayMode().getHeight() * 0.23); 
	    	
			JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Book Keeper", true);
			dialog.setUndecorated(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.getContentPane().add(panel);
			dialog.setSize(width, height);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
	 	}
	//Method to show alert panel (Confirmation Panel)
	public int showDialog(ConfirmationPanel panel) {
		selectedValue = 0;
		
		panel.getBtnConfirm().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		selectedValue = 1; // Set selectedValue to 1 when "OK" is clicked
	            closeDialog(e);
	    	}
	    });
	    panel.getBtnCancel().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            selectedValue = 2; // Set selectedValue to2 when "Cancel" is clicked
	            closeDialog(e);
	    	}
	    });
	    
		environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	device = environment.getDefaultScreenDevice();
       	width = (int) (device.getDisplayMode().getWidth() * 0.4);    	
    	height = (int) (device.getDisplayMode().getHeight() * 0.23); 
    	
		JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Book Keeper", true);
		dialog.setUndecorated(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.getContentPane().add(panel);
		dialog.setSize(width, height);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		
		return selectedValue;
	}
	private static void closeDialog(ActionEvent e) {
        Component component = (Component) e.getSource();
        Window window = SwingUtilities.getWindowAncestor(component);
        if (window != null) {
            window.dispose();
        }
    }
}