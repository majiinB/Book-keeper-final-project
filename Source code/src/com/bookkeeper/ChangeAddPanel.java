package com.bookkeeper;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeAddPanel extends JPanel{
	//panel
	private JPanel mainPanel;
	private JPanel headingPanel;
	private JPanel titlePanel;
	private JPanel inputPanel;
	private JPanel buttonPanel;


	//label
	private JLabel lblHeading;
	private JTextArea txtTitle;//title of panel "Book Information"
	private JTextArea txtDescription;
	
	private JLabel lblHouseNum;
	private JLabel lblStreet;
	private JLabel lblBarangay;
	private JLabel lblCity;	
	
	//textfield
	private PlaceholderTextField txtHouseNum; 
	private PlaceholderTextField txtStreet;
	private PlaceholderTextField txtBarangay;
	private PlaceholderTextField txtCity;
	
	//button
	private JButton btnUpdate;
	private JButton btnCancel;

	//layout
	private GridBagLayout gbl_mainPanel; 
	private GridBagConstraints gbc_headingPanel;
	private GridBagConstraints gbc_titlePanel;
	private GridBagConstraints gbc_inputPanel;
	private GridBagConstraints gbc_buttonPanel;
	private GridBagLayout gbl_titlePanel; 
    private GridBagConstraints gbc_txtTitle;
    private GridBagConstraints gbc_txtDescription;
    
    private GridBagLayout gbl_inputPanel;
    private GridBagConstraints gbc_lblHouseNum;
    private GridBagConstraints gbc_txtHouseNum;  
    private GridBagConstraints gbc_lblStreet;
    private GridBagConstraints gbc_txtStreet;
    
    private GridBagConstraints gbc_lblBarangay;
    private GridBagConstraints gbc_txtBarangay;
    private GridBagConstraints gbc_lblCity;
    private GridBagConstraints gbc_txtCity;

	//text
	private  Font titleFont;
	private  Font subtitleFont;
	private  Font buttonFont;
	private  Font headerFont;
	private  Font plainFont;
		
	private  int buttonTextSize;
	private  int titleTextSize;
	private  int subtitleTextSize;
	private  int headerTextSize;
	private  int plainTextsize;
	private  Color headerColor = new Color(23, 21, 147);//blue
	private  Color darkplainColor = new Color(14, 14, 15);//black
	private  Color lightplainColor = new Color(250, 251, 255);//white
	private  Color middleplainColor = new Color(243, 243, 247);//dirty white

	private GraphicsEnvironment environment;	
	private GraphicsDevice device;
	private int width;
	private int height;
	
	public ChangeAddPanel(User patron) {
		setBackground(new Color(250, 251, 255));
		setBorder(new CompoundBorder(new CompoundBorder(new LineBorder(middleplainColor, 1, true), new LineBorder(headerColor, 3, true)), new EmptyBorder(10, 10, 10, 10)));
	    setLayout(new BorderLayout(0, 0));
		
	    //create panels
		mainPanel = new JPanel();//panel to hold all panels
	    headingPanel = new JPanel();
	    titlePanel = new JPanel();
	    inputPanel = new JPanel();
	    buttonPanel = new JPanel();

		// Set panel properties
	    mainPanel.setBorder(null);
	    mainPanel.setOpaque(false);
	    headingPanel.setOpaque(false);
	    titlePanel.setOpaque(false);
	    inputPanel.setOpaque(false);
	    buttonPanel.setOpaque(false);

	    
	    lblHeading = new JLabel();
	    lblHeading.setIconTextGap(20);
	    lblHeading.setFocusable(false);
	    lblHeading.setForeground(headerColor);
	    lblHeading.setText("User Details");
	    lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
	  
	    btnCancel = new JButton("Cancel");
	    btnCancel.setFocusPainted(false);
	    btnCancel.setForeground(new Color(23, 21, 147));
	    btnCancel.setBorderPainted(false);
	    btnCancel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    btnCancel.setOpaque(false);
	    btnCancel.setContentAreaFilled(false);

	    txtTitle = new JTextArea();
	    txtTitle.setForeground(headerColor);
	    txtTitle.setLineWrap(true);
	    txtTitle.setOpaque(false);
	    txtTitle.setWrapStyleWord(true);
	    txtTitle.setFocusable(false);
	    txtTitle.setEditable(false);
	    txtTitle.setDragEnabled(false);
	    txtTitle.setAutoscrolls(false);
	    txtTitle.setText("Home Address");
	    
	    txtDescription = new JTextArea();
	    txtDescription.setForeground(darkplainColor);
		txtDescription.setLineWrap(true);
		txtDescription.setOpaque(false);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setFocusable(false);
		txtDescription.setEditable(false);
		txtDescription.setDragEnabled(false);
		txtDescription.setAutoscrolls(false);
	    txtDescription.setText("Manage your address to make sure your info is accurate and up to date.");
	    
//	    HOUSE NUMBER
	    lblHouseNum = new JLabel("House No.");
	    lblHouseNum.setHorizontalAlignment(SwingConstants.LEFT);
	    lblHouseNum.setBorder(null);
	    lblHouseNum.setForeground(darkplainColor);
	
	    txtHouseNum = new PlaceholderTextField("House No.");
	    txtHouseNum.setBackground(middleplainColor);
	    txtHouseNum.setBorder(new EmptyBorder(10, 10, 10, 10));
	    txtHouseNum.setOpaque(true);
	    txtHouseNum.setFocusable(true);
	    txtHouseNum.setEditable(true);
	    txtHouseNum.setDragEnabled(false);
	    
	    
//	    STREET
	    lblStreet = new JLabel("Street");
	    lblStreet.setHorizontalAlignment(SwingConstants.LEFT);
	    lblStreet.setBorder(null);
	    lblStreet.setForeground(darkplainColor);
	
	    txtStreet = new PlaceholderTextField("Street");
	    txtStreet.setBackground(middleplainColor);
	    txtStreet.setBorder(new EmptyBorder(10, 10, 10, 10));
	    txtStreet.setOpaque(true);
	    txtStreet.setFocusable(true);
	    txtStreet.setEditable(true);
	    txtStreet.setDragEnabled(false);
	    
//	    BARANGAY
	    lblBarangay = new JLabel("Barangay");
	    lblBarangay.setHorizontalAlignment(SwingConstants.LEFT);
	    lblBarangay.setBorder(null);
	    lblBarangay.setForeground(darkplainColor);
	
	    txtBarangay = new PlaceholderTextField("Barangay");
	    txtBarangay.setBackground(middleplainColor);
	    txtBarangay.setBorder(new EmptyBorder(10, 10, 10,10));
	    txtBarangay.setOpaque(true);
	    txtBarangay.setFocusable(true);
	    txtBarangay.setEditable(true);
	    txtBarangay.setDragEnabled(false);
	    
//	    CITY
	    lblCity = new JLabel("City");
	    lblCity.setHorizontalAlignment(SwingConstants.LEFT);
	    lblCity.setBorder(null);
	    lblCity.setForeground(darkplainColor);
	
	    txtCity = new PlaceholderTextField("City");
	    txtCity.setBackground(middleplainColor);
	    txtCity.setBorder(new EmptyBorder(10, 10, 10, 10));
	    txtCity.setOpaque(true);
	    txtCity.setFocusable(true);
	    txtCity.setEditable(true);
	    txtCity.setDragEnabled(false);
	    
	    btnUpdate = new JButton();
	    btnUpdate.setText("Update");
	    btnUpdate.setMnemonic(KeyEvent.VK_ENTER);
	    btnUpdate.setForeground(lightplainColor);
	    btnUpdate.setBorder(new EmptyBorder(10, 10, 10, 10));
	    btnUpdate.setOpaque(true);
	    btnUpdate.setFocusPainted(false);
	    btnUpdate.setBorderPainted(false);
	    btnUpdate.setBackground(headerColor);
	    /*
	     * gamit ka ng gridbag layout for more control sa placement ng components  sa panel
	     * ung gbc or grid bag constraints is para madetermine mo ung positioning ng mga components sa gridbag layout
	     * x = column
	     * y = row
	     * gridheight/gridwidth = no. of cells occupied
	     * anchor = alignment
	     * insets = padding
	     */
	    
	    //gridbag layouts
	    gbl_mainPanel = new GridBagLayout();
	    gbl_mainPanel.columnWidths = new int[]{865};
	    gbl_mainPanel.rowHeights = new int[]{35, 0, 0, 0};
	    gbl_mainPanel.columnWeights = new double[]{1.0};
	    gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
	    
	    gbc_headingPanel = new GridBagConstraints();
	    gbc_headingPanel.fill = GridBagConstraints.BOTH;	    
	    gbc_headingPanel.insets = new Insets(20, 20, 10, 20);
	    gbc_headingPanel.gridx = 0;
	    gbc_headingPanel.gridy = 0;
	    
	    gbc_titlePanel = new GridBagConstraints();
	    gbc_titlePanel.anchor = GridBagConstraints.SOUTH;
	    gbc_titlePanel.fill = GridBagConstraints.BOTH;	    
	    gbc_titlePanel.insets = new Insets(10, 20, 20, 20);
	    gbc_titlePanel.gridx = 0;
	    gbc_titlePanel.gridy = 1;
	    
	    gbc_inputPanel = new GridBagConstraints();
	    gbc_inputPanel.fill = GridBagConstraints.HORIZONTAL;
	    gbc_inputPanel.insets = new Insets(10, 20, 20, 20);
	    gbc_inputPanel.gridx = 0;
	    gbc_inputPanel.gridy = 2;	 
	    
	    gbc_buttonPanel = new GridBagConstraints();
	    gbc_buttonPanel.fill = GridBagConstraints.HORIZONTAL;	    
	    gbc_buttonPanel.anchor = GridBagConstraints.NORTH;
	    gbc_buttonPanel.insets = new Insets(20, 20, 20, 20);
	    gbc_buttonPanel.gridx = 0;
	    gbc_buttonPanel.gridy = 3;
	    
	    gbl_titlePanel = new GridBagLayout();
        gbl_titlePanel.columnWidths = new int[]{865};
        gbl_titlePanel.rowHeights = new int[]{35, 0};
        gbl_titlePanel.columnWeights = new double[]{1.0};
        gbl_titlePanel.rowWeights = new double[]{0.0, 1.0,}; 

        gbc_txtTitle = new GridBagConstraints();
        gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtTitle.insets = new Insets(0, 0, 0, 0);
        gbc_txtTitle.gridx = 0;
        gbc_txtTitle.gridy = 0;

        gbc_txtDescription = new GridBagConstraints();
        gbc_txtDescription.fill = GridBagConstraints.BOTH;
        gbc_txtDescription.insets = new Insets(0, 0, 0, 0);
        gbc_txtDescription.gridx = 0;
        gbc_txtDescription.gridy = 1;
	    
	    gbl_inputPanel = new GridBagLayout();
        gbl_inputPanel.columnWidths = new int[]{0, 0};
        gbl_inputPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_inputPanel.columnWeights = new double[]{1.0, 1.0};
        gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        
//      HOUSE NUMBER
        gbc_lblHouseNum = new GridBagConstraints();
        gbc_lblHouseNum.fill = GridBagConstraints.BOTH;
        gbc_lblHouseNum.insets = new Insets(0, 0, 0, 0);
        gbc_lblHouseNum.gridx = 0;
        gbc_lblHouseNum.gridy = 0;
     
        gbc_txtHouseNum = new GridBagConstraints();
        gbc_txtHouseNum.fill = GridBagConstraints.BOTH;
        gbc_txtHouseNum.insets = new Insets(5, 0, 0, 10);
        gbc_txtHouseNum.gridx = 0;
        gbc_txtHouseNum.gridy = 1;
      
//      STREET
        gbc_lblStreet = new GridBagConstraints();
        gbc_lblStreet.fill = GridBagConstraints.BOTH;
        gbc_lblStreet.insets = new Insets(0, 0, 0, 0);
        gbc_lblStreet.gridx = 1;
        gbc_lblStreet.gridy = 0;
      
        gbc_txtStreet = new GridBagConstraints();
        gbc_txtStreet.fill = GridBagConstraints.BOTH;
        gbc_txtStreet.insets = new Insets(5, 0, 0, 0);
        gbc_txtStreet.gridx = 1;
        gbc_txtStreet.gridy = 1;
      	
        
//      BARANGAY
        gbc_lblBarangay = new GridBagConstraints();
        gbc_lblBarangay.fill = GridBagConstraints.BOTH;
        gbc_lblBarangay.insets = new Insets(10, 0, 0, 0);
        gbc_lblBarangay.gridx = 0;
        gbc_lblBarangay.gridy = 2;
     
        gbc_txtBarangay = new GridBagConstraints();
        gbc_txtBarangay.fill = GridBagConstraints.BOTH;
        gbc_txtBarangay.insets = new Insets(5, 0, 0, 10);
        gbc_txtBarangay.gridx = 0;
        gbc_txtBarangay.gridy = 3;
      
//      CITY
        gbc_lblCity = new GridBagConstraints();
        gbc_lblCity.fill = GridBagConstraints.BOTH;
        gbc_lblCity.insets = new Insets(10, 0, 0, 0);
        gbc_lblCity.gridx = 1;
        gbc_lblCity.gridy = 2;
      
      	gbc_txtCity = new GridBagConstraints();
      	gbc_txtCity.fill = GridBagConstraints.BOTH;
      	gbc_txtCity.insets = new Insets(5, 0, 0, 0);
      	gbc_txtCity.gridx = 1;
      	gbc_txtCity.gridy = 3;
	    
	    // Set panel layout
	    mainPanel.setLayout(gbl_mainPanel);
	    headingPanel.setLayout(new BorderLayout(0, 0));
	    titlePanel.setLayout(gbl_titlePanel);
	    inputPanel.setLayout(gbl_inputPanel);
	    buttonPanel.setLayout(new BorderLayout(0, 0));

	    //Add all to main panel
	    headingPanel.add(lblHeading, BorderLayout.WEST);
	    headingPanel.add(btnCancel, BorderLayout.EAST);

	    titlePanel.add(txtTitle, gbc_txtTitle);
	    titlePanel.add(txtDescription, gbc_txtDescription);
//	    inputPanel.add(=);
	    inputPanel.add(lblHouseNum, gbc_lblHouseNum);
	    inputPanel.add(txtHouseNum, gbc_txtHouseNum);
	    inputPanel.add(lblStreet, gbc_lblStreet);
	    inputPanel.add(txtStreet, gbc_txtStreet);
	    inputPanel.add(lblBarangay, gbc_lblBarangay);
	    inputPanel.add(txtBarangay, gbc_txtBarangay);
	    inputPanel.add(lblCity, gbc_lblCity);
	    inputPanel.add(txtCity, gbc_txtCity);
	    
	    buttonPanel.add(btnUpdate);
	    mainPanel.add(headingPanel, gbc_headingPanel);
	    mainPanel.add(titlePanel, gbc_titlePanel);
	    mainPanel.add(inputPanel, gbc_inputPanel);
	    mainPanel.add(buttonPanel,gbc_buttonPanel);
	    
	    add(mainPanel);
	    
	    addComponentListener(new ComponentAdapter() {
	    	  @Override
	          public void componentResized(ComponentEvent e) {
	  	        	titleTextSize = Math.min(getHeight() / 7, getWidth()/ 10) ;
	  	            subtitleTextSize =  Math.min(getHeight() / 20, getWidth()/ 25);
	  	            buttonTextSize =  Math.min(getHeight() / 30, getWidth()/ 30);
	  	           	headerTextSize =   Math.min(getHeight() / 20, getWidth()/ 30);
	  	           	plainTextsize=   Math.min(getHeight() / 25, getWidth()/ 25);
	  	          
	  	            titleFont = new Font("Montserrat", Font.BOLD, titleTextSize);
	  	            subtitleFont = new Font("Montserrat", Font.ITALIC, subtitleTextSize);
	  	            buttonFont = new Font("Montserrat", Font.BOLD, buttonTextSize);
	  	            headerFont = new Font("Montserrat", Font.PLAIN, headerTextSize);
	  	            plainFont = new Font("Montserrat", Font.ITALIC | Font.BOLD, plainTextsize);

	  	            txtTitle.setFont(titleFont);
	  	           	txtDescription.setFont(subtitleFont);
	  	            btnUpdate.setFont(buttonFont);	  	            
	  	            btnCancel.setFont(headerFont);
	  	            lblHeading.setFont(headerFont);	  	            
	  	            lblHouseNum.setFont(plainFont);  	          
	  	              	          
	  	            lblStreet.setFont(plainFont);  	          
	  	      		lblBarangay.setFont(plainFont);    	        
	  	    		lblCity.setFont(plainFont);  	          
	          }
	      });
	 // Action Listener
	    btnUpdate.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	
	    	String DB_URL = "jdbc:mysql://localhost:3306/book_keeper";
	        String DB_USERNAME = "root";
	        String DB_PASSWORD = "";
	    	String houseNum = txtHouseNum.getText().trim();
	    	String street = txtStreet.getText().trim();
	    	String barangay = txtBarangay.getText().trim();
	    	String city = txtCity.getText().trim();
	    	String Address = houseNum +", "+ street +", "+ barangay+", "+city;
	    	
	    	String formattedID = patron.getUser_id();
	    	
	    	// Shield
	    	if (houseNum.isBlank() || street.isBlank() || houseNum.equals("House No./Block No./Unit No.") || street.equals("Street")
	    		|| barangay.isBlank() || barangay.equals("Barangay") || city.isBlank() || city.equals("City")) {
	    		MalfunctionPanel mal = new MalfunctionPanel("An Error Occured", 
	    				"Oops! It seems like an error occurred. Please check the information you provided and try again. "
	    				+ "Ensure that all fields are correctly filled and any required details are provided.");
	    		showDialog(mal);
	    		return;
	    	}

	    	
	    	Connection conn = null;
	        PreparedStatement stmt = null;

	        try {
	            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            
	            //Prepare query
	            String sql = "UPDATE patron SET patron_address = ? WHERE formatted_id = ?";
	            
	            //Execute update
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, Address);
	            stmt.setString(2, formattedID);
	            stmt.executeUpdate();
	            //Update Object
	            patron.setUser_address(Address);
	            
	            //Prompt successful update
	            SuccessPanel success = new SuccessPanel("Update Success", 
	            		"User Information is successfully updated! Please Refresh application to see new updates.");
	            showDialog(success);
	            
	            //Close Frame after update
	            closeDialog(e);
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	    }
	 });
	}
	
	public ChangeAddPanel(Employee employee) {
		setBackground(new Color(250, 251, 255));
		setBorder(new CompoundBorder(new CompoundBorder(new LineBorder(middleplainColor, 1, true), new LineBorder(headerColor, 3, true)), new EmptyBorder(10, 10, 10, 10)));
	    setLayout(new BorderLayout(0, 0));
		
	    //create panels
		mainPanel = new JPanel();//panel to hold all panels
	    headingPanel = new JPanel();
	    titlePanel = new JPanel();
	    inputPanel = new JPanel();
	    buttonPanel = new JPanel();

		// Set panel properties
	    mainPanel.setBorder(null);
	    mainPanel.setOpaque(false);
	    headingPanel.setOpaque(false);
	    titlePanel.setOpaque(false);
	    inputPanel.setOpaque(false);
	    buttonPanel.setOpaque(false);

	    
	    lblHeading = new JLabel();
	    lblHeading.setIconTextGap(20);
	    lblHeading.setFocusable(false);
	    lblHeading.setForeground(headerColor);
	    lblHeading.setText("User Details");
	    lblHeading.setHorizontalAlignment(SwingConstants.CENTER);
	  
	    btnCancel = new JButton("Cancel");
	    btnCancel.setFocusPainted(false);
	    btnCancel.setForeground(new Color(23, 21, 147));
	    btnCancel.setBorderPainted(false);
	    btnCancel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    btnCancel.setOpaque(false);
	    btnCancel.setContentAreaFilled(false);

	    txtTitle = new JTextArea();
	    txtTitle.setForeground(headerColor);
	    txtTitle.setLineWrap(true);
	    txtTitle.setOpaque(false);
	    txtTitle.setWrapStyleWord(true);
	    txtTitle.setFocusable(false);
	    txtTitle.setEditable(false);
	    txtTitle.setDragEnabled(false);
	    txtTitle.setAutoscrolls(false);
	    txtTitle.setText("Home Address");
	    
	    txtDescription = new JTextArea();
	    txtDescription.setForeground(darkplainColor);
		txtDescription.setLineWrap(true);
		txtDescription.setOpaque(false);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setFocusable(false);
		txtDescription.setEditable(false);
		txtDescription.setDragEnabled(false);
		txtDescription.setAutoscrolls(false);
	    txtDescription.setText("Manage your address to make sure your info is accurate and up to date.");
	    
//	    HOUSE NUMBER
	    lblHouseNum = new JLabel("House No.");
	    lblHouseNum.setHorizontalAlignment(SwingConstants.LEFT);
	    lblHouseNum.setBorder(null);
	    lblHouseNum.setForeground(darkplainColor);
	
	    txtHouseNum = new PlaceholderTextField("House No.");
	    txtHouseNum.setBackground(middleplainColor);
	    txtHouseNum.setBorder(new EmptyBorder(10, 10, 10, 10));
	    txtHouseNum.setOpaque(true);
	    txtHouseNum.setFocusable(true);
	    txtHouseNum.setEditable(true);
	    txtHouseNum.setDragEnabled(false);
	    
	    
//	    STREET
	    lblStreet = new JLabel("Street");
	    lblStreet.setHorizontalAlignment(SwingConstants.LEFT);
	    lblStreet.setBorder(null);
	    lblStreet.setForeground(darkplainColor);
	
	    txtStreet = new PlaceholderTextField("Street");
	    txtStreet.setBackground(middleplainColor);
	    txtStreet.setBorder(new EmptyBorder(10, 10, 10, 10));
	    txtStreet.setOpaque(true);
	    txtStreet.setFocusable(true);
	    txtStreet.setEditable(true);
	    txtStreet.setDragEnabled(false);
	    
//	    BARANGAY
	    lblBarangay = new JLabel("Barangay");
	    lblBarangay.setHorizontalAlignment(SwingConstants.LEFT);
	    lblBarangay.setBorder(null);
	    lblBarangay.setForeground(darkplainColor);
	
	    txtBarangay = new PlaceholderTextField("Barangay");
	    txtBarangay.setBackground(middleplainColor);
	    txtBarangay.setBorder(new EmptyBorder(10, 10, 10,10));
	    txtBarangay.setOpaque(true);
	    txtBarangay.setFocusable(true);
	    txtBarangay.setEditable(true);
	    txtBarangay.setDragEnabled(false);
	    
//	    CITY
	    lblCity = new JLabel("City");
	    lblCity.setHorizontalAlignment(SwingConstants.LEFT);
	    lblCity.setBorder(null);
	    lblCity.setForeground(darkplainColor);
	
	    txtCity = new PlaceholderTextField("City");
	    txtCity.setBackground(middleplainColor);
	    txtCity.setBorder(new EmptyBorder(10, 10, 10, 10));
	    txtCity.setOpaque(true);
	    txtCity.setFocusable(true);
	    txtCity.setEditable(true);
	    txtCity.setDragEnabled(false);
	    
	    btnUpdate = new JButton();
	    btnUpdate.setText("Update");
	    btnUpdate.setMnemonic(KeyEvent.VK_ENTER);
	    btnUpdate.setForeground(lightplainColor);
	    btnUpdate.setBorder(new EmptyBorder(10, 10, 10, 10));
	    btnUpdate.setOpaque(true);
	    btnUpdate.setFocusPainted(false);
	    btnUpdate.setBorderPainted(false);
	    btnUpdate.setBackground(headerColor);
	    /*
	     * gamit ka ng gridbag layout for more control sa placement ng components  sa panel
	     * ung gbc or grid bag constraints is para madetermine mo ung positioning ng mga components sa gridbag layout
	     * x = column
	     * y = row
	     * gridheight/gridwidth = no. of cells occupied
	     * anchor = alignment
	     * insets = padding
	     */
	    
	    //gridbag layouts
	    gbl_mainPanel = new GridBagLayout();
	    gbl_mainPanel.columnWidths = new int[]{865};
	    gbl_mainPanel.rowHeights = new int[]{35, 0, 0, 0};
	    gbl_mainPanel.columnWeights = new double[]{1.0};
	    gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
	    
	    gbc_headingPanel = new GridBagConstraints();
	    gbc_headingPanel.fill = GridBagConstraints.BOTH;	    
	    gbc_headingPanel.insets = new Insets(20, 20, 10, 20);
	    gbc_headingPanel.gridx = 0;
	    gbc_headingPanel.gridy = 0;
	    
	    gbc_titlePanel = new GridBagConstraints();
	    gbc_titlePanel.anchor = GridBagConstraints.SOUTH;
	    gbc_titlePanel.fill = GridBagConstraints.BOTH;	    
	    gbc_titlePanel.insets = new Insets(10, 20, 20, 20);
	    gbc_titlePanel.gridx = 0;
	    gbc_titlePanel.gridy = 1;
	    
	    gbc_inputPanel = new GridBagConstraints();
	    gbc_inputPanel.fill = GridBagConstraints.HORIZONTAL;
	    gbc_inputPanel.insets = new Insets(10, 20, 20, 20);
	    gbc_inputPanel.gridx = 0;
	    gbc_inputPanel.gridy = 2;	 
	    
	    gbc_buttonPanel = new GridBagConstraints();
	    gbc_buttonPanel.fill = GridBagConstraints.HORIZONTAL;	    
	    gbc_buttonPanel.anchor = GridBagConstraints.NORTH;
	    gbc_buttonPanel.insets = new Insets(20, 20, 20, 20);
	    gbc_buttonPanel.gridx = 0;
	    gbc_buttonPanel.gridy = 3;
	    
	    gbl_titlePanel = new GridBagLayout();
        gbl_titlePanel.columnWidths = new int[]{865};
        gbl_titlePanel.rowHeights = new int[]{35, 0};
        gbl_titlePanel.columnWeights = new double[]{1.0};
        gbl_titlePanel.rowWeights = new double[]{0.0, 1.0,}; 

        gbc_txtTitle = new GridBagConstraints();
        gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtTitle.insets = new Insets(0, 0, 0, 0);
        gbc_txtTitle.gridx = 0;
        gbc_txtTitle.gridy = 0;

        gbc_txtDescription = new GridBagConstraints();
        gbc_txtDescription.fill = GridBagConstraints.BOTH;
        gbc_txtDescription.insets = new Insets(0, 0, 0, 0);
        gbc_txtDescription.gridx = 0;
        gbc_txtDescription.gridy = 1;
	    
	    gbl_inputPanel = new GridBagLayout();
        gbl_inputPanel.columnWidths = new int[]{0, 0};
        gbl_inputPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
        gbl_inputPanel.columnWeights = new double[]{1.0, 1.0};
        gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
        
//      HOUSE NUMBER
        gbc_lblHouseNum = new GridBagConstraints();
        gbc_lblHouseNum.fill = GridBagConstraints.BOTH;
        gbc_lblHouseNum.insets = new Insets(0, 0, 0, 0);
        gbc_lblHouseNum.gridx = 0;
        gbc_lblHouseNum.gridy = 0;
     
        gbc_txtHouseNum = new GridBagConstraints();
        gbc_txtHouseNum.fill = GridBagConstraints.BOTH;
        gbc_txtHouseNum.insets = new Insets(5, 0, 0, 10);
        gbc_txtHouseNum.gridx = 0;
        gbc_txtHouseNum.gridy = 1;
      
//      STREET
        gbc_lblStreet = new GridBagConstraints();
        gbc_lblStreet.fill = GridBagConstraints.BOTH;
        gbc_lblStreet.insets = new Insets(0, 0, 0, 0);
        gbc_lblStreet.gridx = 1;
        gbc_lblStreet.gridy = 0;
      
        gbc_txtStreet = new GridBagConstraints();
        gbc_txtStreet.fill = GridBagConstraints.BOTH;
        gbc_txtStreet.insets = new Insets(5, 0, 0, 0);
        gbc_txtStreet.gridx = 1;
        gbc_txtStreet.gridy = 1;
      	
        
//      BARANGAY
        gbc_lblBarangay = new GridBagConstraints();
        gbc_lblBarangay.fill = GridBagConstraints.BOTH;
        gbc_lblBarangay.insets = new Insets(10, 0, 0, 0);
        gbc_lblBarangay.gridx = 0;
        gbc_lblBarangay.gridy = 2;
     
        gbc_txtBarangay = new GridBagConstraints();
        gbc_txtBarangay.fill = GridBagConstraints.BOTH;
        gbc_txtBarangay.insets = new Insets(5, 0, 0, 10);
        gbc_txtBarangay.gridx = 0;
        gbc_txtBarangay.gridy = 3;
      
//      CITY
        gbc_lblCity = new GridBagConstraints();
        gbc_lblCity.fill = GridBagConstraints.BOTH;
        gbc_lblCity.insets = new Insets(10, 0, 0, 0);
        gbc_lblCity.gridx = 1;
        gbc_lblCity.gridy = 2;
      
      	gbc_txtCity = new GridBagConstraints();
      	gbc_txtCity.fill = GridBagConstraints.BOTH;
      	gbc_txtCity.insets = new Insets(5, 0, 0, 0);
      	gbc_txtCity.gridx = 1;
      	gbc_txtCity.gridy = 3;
	    
	    // Set panel layout
	    mainPanel.setLayout(gbl_mainPanel);
	    headingPanel.setLayout(new BorderLayout(0, 0));
	    titlePanel.setLayout(gbl_titlePanel);
	    inputPanel.setLayout(gbl_inputPanel);
	    buttonPanel.setLayout(new BorderLayout(0, 0));

	    //Add all to main panel
	    headingPanel.add(lblHeading, BorderLayout.WEST);
	    headingPanel.add(btnCancel, BorderLayout.EAST);

	    titlePanel.add(txtTitle, gbc_txtTitle);
	    titlePanel.add(txtDescription, gbc_txtDescription);
//	    inputPanel.add(=);
	    inputPanel.add(lblHouseNum, gbc_lblHouseNum);
	    inputPanel.add(txtHouseNum, gbc_txtHouseNum);
	    inputPanel.add(lblStreet, gbc_lblStreet);
	    inputPanel.add(txtStreet, gbc_txtStreet);
	    inputPanel.add(lblBarangay, gbc_lblBarangay);
	    inputPanel.add(txtBarangay, gbc_txtBarangay);
	    inputPanel.add(lblCity, gbc_lblCity);
	    inputPanel.add(txtCity, gbc_txtCity);
	    
	    buttonPanel.add(btnUpdate);
	    mainPanel.add(headingPanel, gbc_headingPanel);
	    mainPanel.add(titlePanel, gbc_titlePanel);
	    mainPanel.add(inputPanel, gbc_inputPanel);
	    mainPanel.add(buttonPanel,gbc_buttonPanel);
	    
	    add(mainPanel);
	    
	    addComponentListener(new ComponentAdapter() {
	    	  @Override
	          public void componentResized(ComponentEvent e) {
	  	        	titleTextSize = Math.min(getHeight() / 7, getWidth()/ 10) ;
	  	            subtitleTextSize =  Math.min(getHeight() / 20, getWidth()/ 25);
	  	            buttonTextSize =  Math.min(getHeight() / 30, getWidth()/ 30);
	  	           	headerTextSize =   Math.min(getHeight() / 20, getWidth()/ 30);
	  	           	plainTextsize=   Math.min(getHeight() / 25, getWidth()/ 25);
	  	          
	  	            titleFont = new Font("Montserrat", Font.BOLD, titleTextSize);
	  	            subtitleFont = new Font("Montserrat", Font.ITALIC, subtitleTextSize);
	  	            buttonFont = new Font("Montserrat", Font.BOLD, buttonTextSize);
	  	            headerFont = new Font("Montserrat", Font.PLAIN, headerTextSize);
	  	            plainFont = new Font("Montserrat", Font.ITALIC | Font.BOLD, plainTextsize);

	  	            txtTitle.setFont(titleFont);
	  	           	txtDescription.setFont(subtitleFont);
	  	            btnUpdate.setFont(buttonFont);	  	            
	  	            btnCancel.setFont(headerFont);
	  	            lblHeading.setFont(headerFont);	  	            
	  	            lblHouseNum.setFont(plainFont);  	          
	  	              	          
	  	            lblStreet.setFont(plainFont);  	          
	  	      		lblBarangay.setFont(plainFont);    	        
	  	    		lblCity.setFont(plainFont);  	          
	          }
	      });
	 // Action Listener
	    btnUpdate.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	
	    	String DB_URL = "jdbc:mysql://localhost:3306/book_keeper";
	        String DB_USERNAME = "root";
	        String DB_PASSWORD = "";
	    	String houseNum = txtHouseNum.getText().trim();
	    	String street = txtStreet.getText().trim();
	    	String barangay = txtBarangay.getText().trim();
	    	String city = txtCity.getText().trim();
	    	String Address = houseNum +", "+ street +", "+ barangay+", "+city;
	    	
	    	String formattedID = employee.getEmployee_id();
	    	
	    	// Shield
	    	if (houseNum.isBlank() || street.isBlank() || houseNum.equals("House No./Block No./Unit No.") || street.equals("Street")
	    		|| barangay.isBlank() || barangay.equals("Barangay") || city.isBlank() || city.equals("City")) {
	    		MalfunctionPanel mal = new MalfunctionPanel("An Error Occurred", 
	    				"Oops! It seems like an error occurred. Please check the information you provided and try again. "
	    				+ "Ensure that all fields are correctly filled and any required details are provided.");
	            showDialog(mal);
	    		return;
	    	}
	    	
	    	Connection conn = null;
	        PreparedStatement stmt = null;

	        try {
	            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            
	            //Prepare query
	            String sql = "UPDATE admin SET admin_address = ? WHERE admin_formatted_id = ?";
	            
	            //Execute update
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, Address);
	            stmt.setString(2, formattedID);
	            stmt.executeUpdate();
	            //Update Object
	            employee.setAddress(Address);
	            
	            //Prompt successful update
	            SuccessPanel success = new SuccessPanel("Update Success", 
	            		"User Information is successfully updated! Please Refresh application to see new updates.");
	            showDialog(success);
	            
	            //Close Frame after update
	            closeDialog(e);
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        } finally {
	            try {
	                if (stmt != null) {
	                    stmt.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e2) {
	                e2.printStackTrace();
	            }
	        }
	    }
	 });
	}
	@Override
	 protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    /*
		    * use super.paintComponent(g) for proper rendering 
		    * of components 
		*/

	 }
	public JButton getBtnBack() {
		return btnCancel;
	}
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
		    	
				JDialog dialog = new JDialog((JDialog) SwingUtilities.getWindowAncestor(this), "Book Keeper", true);
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
		    	
				JDialog dialog = new JDialog((JDialog) SwingUtilities.getWindowAncestor(this), "Book Keeper", true);
				dialog.setUndecorated(true);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.getContentPane().add(panel);
				dialog.setSize(width, height);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			}
		    
		    //Method used by showDialog to close the JDialog containing the alert panels
			private void closeDialog(ActionEvent e) {
		        Component component = (Component) e.getSource();
		        Window window = SwingUtilities.getWindowAncestor(component);
		        if (window != null) {
		            window.dispose();
		        }
		    }
}