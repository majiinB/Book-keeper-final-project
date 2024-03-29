package com.bookkeeper;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.bookkeeper.AdminLibraryPanel.headerRenderer;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatronLibraryPanel extends JPanel {
	//panel
//	private JPanel mainPanel;
	private BackgroundPanel mainPanel;
//	private BackgroundPanel triangleBackground;
	private JPanel contentPanel;
	private JPanel headingPanel;
	private JPanel titlePanel;
	private RoundedPanel searchBarPanel;
	private RoundedPanel searchResultsPanel;

	//label
	private JLabel lblHeading1;
	private JLabel lblHeading2;
	private JTextArea txtTitle;
	
	//textfield
	private PlaceholderTextField txtSearchBar;
	
	//icon
	private ImageIcon searchIcon;
	private Image searchImage;
	private int iconHeight; 
	private int iconWidth;
	//button
	private JButton btnSearch;
	
	//scroll
	private JScrollPane  searchScrollPane;
	
	//table
    private JTable table;
	private DefaultTableModel tableModel;

	//layout
	private GridBagLayout gbl_contentPanel;
	private GridBagConstraints gbc_headingPanel;
	private GridBagConstraints gbc_titlePanel;
	private GridBagConstraints gbc_searchBarPanel;
	private GridBagConstraints gbc_searchResultsPanel;
	//text
	private  Font titleFont;
	private  Font newTitleTextFont;
	private  Font buttonFont;
	private  Font headerFont;
	private  Font plainFont;
	
	private  int buttonTextSize;
	private  int titleTextSize;
	private  int newTitleTextSize;
	private  int headerTextSize;
	private  int plainTextsize;
	
	private  Color headerColor = new Color(23, 21, 147);
	private  Color darkplainColor = new Color(14, 14, 15);
	private  Color lightplainColor = new Color(250, 251, 255);
	
	// Object
	private Book selectedBook;

	public PatronLibraryPanel(User patron, Setting setting) {
	setBackground(lightplainColor);
	setBorder(new EmptyBorder(10, 20, 10, 20));
	setLayout(new BorderLayout(0, 0));
	 
	//create panels
	mainPanel = new BackgroundPanel();
//	mainPanel = new JPanel();
	contentPanel = new JPanel();
	headingPanel = new JPanel();
    titlePanel = new JPanel();
    searchBarPanel =  new RoundedPanel(20);
    searchResultsPanel =  new RoundedPanel(20);


    // Set panel properties
    mainPanel.setOpaque(false);
    headingPanel.setOpaque(false);
    contentPanel.setOpaque(false);
    titlePanel.setOpaque(false);
    searchBarPanel.setOpaque(true);
    searchResultsPanel.setOpaque(true);
	mainPanel.setBorder(new EmptyBorder(15, 25, 10, 25));
    searchBarPanel.setBorder(new EmptyBorder(10, 10, 10, 15));
	headingPanel.setBorder(new EmptyBorder(10, 25, 10, 25));
    searchResultsPanel.setBorder(new EmptyBorder(10, 0, 10, 5));

    searchBarPanel.setBorderWidth(2);
    searchResultsPanel.setBorderWidth(2);
    
    searchBarPanel.setForeground(darkplainColor);
    searchResultsPanel.setForeground(darkplainColor);
    searchBarPanel.setBackground(lightplainColor);
    searchResultsPanel.setBackground(lightplainColor);

    lblHeading1 = new JLabel("Dashboard ");
    lblHeading1.setOpaque(false);
    
    lblHeading2 = new JLabel("/ Library");
    lblHeading2.setOpaque(false);
    
    txtTitle = new JTextArea("Explore the World of Books with Book Keeper.");
    txtTitle.setForeground(headerColor);
    txtTitle.setLineWrap(true);
    txtTitle.setOpaque(false);
    txtTitle.setWrapStyleWord(true);
    txtTitle.setFocusable(false);
    txtTitle.setEditable(false);
    txtTitle.setDragEnabled(false);
    txtTitle.setAutoscrolls(false);

    btnSearch = new JButton();
    btnSearch.setMnemonic(KeyEvent.VK_ENTER);
    btnSearch.setBorderPainted(false);
    btnSearch.setFocusPainted(false);
    btnSearch.setContentAreaFilled(false);
    btnSearch.setPreferredSize(new Dimension(25, 25));

    searchIcon = new ImageIcon("img/DashboardFrame/Search.png");    
    iconHeight = (int) (btnSearch.getPreferredSize().getHeight()-5);
    iconWidth = (int) (btnSearch.getPreferredSize().getHeight()-5);
    searchIcon = new ImageIcon(searchIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH));
    btnSearch.setIcon(searchIcon);
    
    txtSearchBar = new PlaceholderTextField("Search Book");
    txtSearchBar.setBorder(null);
    txtSearchBar.setHorizontalAlignment(SwingConstants.RIGHT);
    txtSearchBar.setOpaque(false);

    // Create the scroll pane and add the table to it
    searchScrollPane = new JScrollPane(table);
    searchScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    searchScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    searchScrollPane.setBackground(new Color(0,0,0,0));
    searchScrollPane.setBorder(new EmptyBorder(5, 10, 0, 0));
    searchScrollPane.setOpaque(false);
    searchScrollPane.getViewport().setOpaque(false);
    
    // Create the table with the tableModel
    tableModel = new DefaultTableModel();//add table
    
    table = new JTable(tableModel) {
    	
    // Override isCellEditable method to make cells not editable
		@Override
		public boolean isCellEditable(int row, int column) {
		      return false;
		}
     };
     table.setBackground(new Color(0, 0, 0, 0));;
     table.setFillsViewportHeight(true);
     table.setOpaque(false);
     table.setShowVerticalLines(false);
     table.setRowHeight(45);
     table.getTableHeader().setOpaque(false);
     table.setGridColor(darkplainColor);
     table.getTableHeader().setDefaultRenderer(new headerRenderer());
     table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     table.setSelectionBackground(new Color(125, 147, 194));
     table.setSelectionForeground(lightplainColor);
     //listener for clicking cells in table  
     
     table.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) { 
			int selectedRow = table.getSelectedRow();
			if (selectedRow != -1) {
			    // Get the values from the selected row
			    int bookId = (int) table.getValueAt(selectedRow, 0);
			    String ISBN = (String) table.getValueAt(selectedRow, 1);
			    String bookTitle = (String) table.getValueAt(selectedRow, 2);
			    String authorName = (String) table.getValueAt(selectedRow, 3);
			    String genreName = (String) table.getValueAt(selectedRow, 4);
			    String bookPublisher = (String) table.getValueAt(selectedRow, 5);
			    java.sql.Date date = (java.sql.Date) table.getValueAt(selectedRow, 6);
			    String bookPublishDate = date.toString();
			    String bookStatus = (String) table.getValueAt(selectedRow, 7);
			    int aisleNumber = (int) table.getValueAt(selectedRow, 8);
			    int shelfNumber = (int) table.getValueAt(selectedRow, 9);
			    
			    //Create a Book object with the retrieved values
			    selectedBook = new Book(bookId, bookTitle, genreName, authorName, bookPublishDate , bookPublisher, bookStatus, aisleNumber, shelfNumber, ISBN);
			    PatronBookInfoPanel panel = new PatronBookInfoPanel(selectedBook, patron, setting);
			    showDialog(panel);
			} 
		}
	});
	
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
    gbl_contentPanel = new GridBagLayout();
    gbl_contentPanel.columnWidths = new int[]{380, 0};
    gbl_contentPanel.rowHeights = new int[]{30, 46, 0};
    gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};  
	
	gbc_titlePanel = new GridBagConstraints();
	gbc_titlePanel.anchor = GridBagConstraints.SOUTH;
    gbc_titlePanel.fill = GridBagConstraints.HORIZONTAL;
    gbc_titlePanel.insets = new Insets(40, 0, 15, 0);
    gbc_titlePanel.gridx = 0;
	gbc_titlePanel.gridy = 0;
	
    gbc_searchBarPanel = new GridBagConstraints();
    gbc_searchBarPanel.fill = GridBagConstraints.BOTH;
    gbc_searchBarPanel.insets = new Insets(0, 0, 10, 0);
    gbc_searchBarPanel.gridx = 0;
    gbc_searchBarPanel.gridy = 1;
    
    gbc_searchResultsPanel = new GridBagConstraints();
    gbc_searchResultsPanel.fill = GridBagConstraints.BOTH;
    gbc_searchResultsPanel.insets = new Insets(10, 0, 20, 0);
    gbc_searchResultsPanel.gridx = 0;
    gbc_searchResultsPanel.gridy = 2;
    
	// Set panel layouts
    mainPanel.setLayout(new BorderLayout(0,0));
    contentPanel.setLayout(gbl_contentPanel);
    titlePanel.setLayout(new BorderLayout(0, 0));
    searchBarPanel.setLayout(new BorderLayout(0, 0));
    searchResultsPanel.setLayout(new BorderLayout(0, 0));
    headingPanel.setLayout(new BoxLayout(headingPanel, BoxLayout.X_AXIS));
    searchResultsPanel.setVisible(false);
    //Add all to main panel
    headingPanel.add(lblHeading1);
    headingPanel.add(lblHeading2);
    titlePanel.add(txtTitle);
    searchBarPanel.add(btnSearch,BorderLayout.WEST);
    searchBarPanel.add(txtSearchBar,BorderLayout.CENTER);

//    contentPanel.add(headingPanel,gbc_headingPanel);
    contentPanel.add(titlePanel,gbc_titlePanel);
    contentPanel.add(searchBarPanel,gbc_searchBarPanel);
    contentPanel.add(searchResultsPanel,gbc_searchResultsPanel);

 
    // Add the scroll pane to the searchResultsPanel
    searchResultsPanel.add(searchScrollPane);
    
    
	//displayAllBooks();

    mainPanel.add(contentPanel, BorderLayout.CENTER);
	add(headingPanel, BorderLayout.NORTH);
	add(mainPanel, BorderLayout.CENTER);
	
	addComponentListener(new ComponentAdapter() {
  	  @Override
        public void componentResized(ComponentEvent e) {
	  		titleTextSize = Math.min(getHeight() / 8, getWidth()/ 8) ;
	        buttonTextSize =  Math.min(getHeight() / 50, getWidth()/ 50);
	        headerTextSize =   Math.min(getHeight() / 20, getWidth()/ 20);
	        plainTextsize=   Math.min(getHeight() / 60, getWidth()/ 60);
              
	        titleFont = new Font("Montserrat", Font.BOLD, titleTextSize);
	            	            
	        buttonFont = new Font("Montserrat", Font.ITALIC, buttonTextSize);
	     	headerFont = new Font("Montserrat", Font.PLAIN, headerTextSize);
			plainFont = new Font("Montserrat", Font.ITALIC, plainTextsize);
			
	     	lblHeading1.setFont(new Font("Montserrat", Font.BOLD, headerTextSize));
	     	lblHeading2.setFont(new Font("Montserrat", Font.PLAIN, headerTextSize));
	        txtTitle.setFont(titleFont);
	     	table.setFont(plainFont);
	     	table.getTableHeader().setFont(new Font("Montserrat", Font.ITALIC  | Font.BOLD, plainTextsize));	            
	     	table.getTableHeader().setForeground(headerColor);
               }
    });
	
	// Action Listener
	btnSearch.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		//make results appear and triangles gone
            mainPanel.drawBackgroundImage = false;
            mainPanel.repaint();
    		searchResultsPanel.setVisible(true);
    		//update insets
    	    gbc_titlePanel.insets = new Insets(0, 0, 15, 0);
            contentPanel.setLayout(gbl_contentPanel); 
            contentPanel.add(titlePanel, gbc_titlePanel);
            //update text size
    		newTitleTextSize = Math.min(getHeight() / 12, getWidth()/ 14) ;//Size after search button is pressed
    		newTitleTextFont = new Font("Montserrat", Font.BOLD, newTitleTextSize);
	        txtTitle.setFont(newTitleTextFont);

    		tableModel.setRowCount(0);
    		searchScrollPane.setViewportView(table);
 		    try {
 		        // Establish database connection
 		        String getSearch = txtSearchBar.getText().trim();
 		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_keeper", "root", "");
 		        String getQuery = "";
 		              
 		        // Check for empty search
 		        if (getSearch.isEmpty()||getSearch.equals("Search Book")) {
 		        	getQuery = "SELECT book_id, ISBN, book_title, author_name, genre_name, book_publisher, book_publication_date, book_status, aisle_number, shelf_number FROM book " +
                             "ORDER BY book_title ASC;";
 		        } else {
 		             getQuery = searchQuery(getSearch);
 		        }

 		       // Execute the SQL query
 		      Statement statement = connection.createStatement();
 		      ResultSet resultSet = statement.executeQuery(getQuery);

 		      // Get the metadata for column information
 		      ResultSetMetaData metaData = resultSet.getMetaData();
 		      int columnCount = metaData.getColumnCount();
               // Create an array to store column names
 		      String[] columnNames = new String[10];
 	            columnNames[0] = "Book ID";
 	            columnNames[1] = "ISBN";
 	            columnNames[2] = "Title";
 	            columnNames[3] = "Author name";
 	            columnNames[4] = "Genre";
 	            columnNames[5] = "Publisher";
 	            columnNames[6] = "Publication Date";
 	            columnNames[7] = "Status";
 	            columnNames[8] = "Aisle No.";
 	            columnNames[9] = "Shelf No.";

 		      // Set the column names in the table model
 		      tableModel.setColumnIdentifiers(columnNames);
 		      table.setModel(tableModel);
 		      
 		      while (resultSet.next()) {
 		              Object[] rowData = new Object[columnCount];
 		              for (int i = 1; i <= columnCount; i++) {
 		                     rowData[i - 1] = resultSet.getObject(i);
 		              }
 		              tableModel.addRow(rowData);
 		       }
 		     table.getColumnModel().getColumn(0).setWidth(0);
 		     table.getColumnModel().getColumn(0).setMinWidth(0);
 		     table.getColumnModel().getColumn(0).setMaxWidth(0);
 		     
 		     table.getColumnModel().getColumn(1).setMinWidth(130);
 		     table.getColumnModel().getColumn(1).setMaxWidth(145);
 		     
 		     table.getColumnModel().getColumn(4).setMinWidth(120);
 		     table.getColumnModel().getColumn(4).setMaxWidth(120); 
 		     
 		     table.getColumnModel().getColumn(6).setMinWidth(140);
 		     table.getColumnModel().getColumn(6).setMaxWidth(140); 
 		     
 		     table.getColumnModel().getColumn(7).setMinWidth(100);
 		     table.getColumnModel().getColumn(7).setMaxWidth(100); 
 		     
 		     table.getColumnModel().getColumn(8).setMinWidth(80);
 		     table.getColumnModel().getColumn(8).setMaxWidth(80); 
 		     
 		     table.getColumnModel().getColumn(9).setMinWidth(80);
 		     table.getColumnModel().getColumn(9).setMaxWidth(80);
 		    table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
 		      // Close the database connection
 		     resultSet.close();
 		     statement.close();
 		     connection.close();
 		     } catch (SQLException e1) {
 		              e1.printStackTrace();
 		     }
    	}
    });
 }
	public class headerRenderer implements TableCellRenderer {
		@Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean hasFocus, int row, int column) {
		        JTableHeader header = table.getTableHeader();
		        JLabel label = new JLabel(value.toString());
		        label.setOpaque(false);
		        label.setFont(header.getFont());
		        label.setBackground(new Color(0, 0, 0, 0)); 
		        label.setForeground(header.getForeground());
		        label.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		        return label;
		    }
		}


public class BackgroundPanel extends JPanel {
	//background
	private Image backgroundImage;
    private boolean drawBackgroundImage = true;

	
	public BackgroundPanel() {
		backgroundImage = new ImageIcon("img/DashboardFrame/Triangles_Library.png").getImage();
	}
	
	//put image over panel
	 @Override
	 protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    /*
	    * use super.paintComponent(g) for proper rendering 
	    * ng mga components sa taas ng bg image 
	    * ng d nasisira ni custom drawing 
	    */
	    //if drawBackgroundImage is true, triangle will be displayed
        if (drawBackgroundImage) {
        	g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 
        }
	    /* dis ^^ da custom drawing, s-start nya ilagay si bg from coordinate 0,0 
	    * hanggang sa ma-cover nya  buong panel
	    */
	 }
	    
	}  
	//Methods
	public String searchQuery(String search) {
		String query = "SELECT book_id, ISBN, book_title, author_name, genre_name, book_publisher, book_publication_date, book_status, aisle_number, shelf_number FROM book " +
	            "WHERE book_title LIKE '" + search + "%' OR " +
	            "author_name LIKE '" + search + "%' OR " +
	            "genre_name LIKE '%" + search + "%' OR " +
	            "book_publisher LIKE '" + search + "%' OR " +
	            "ISBN LIKE '" + search + "%'"; 
	  return query;
	}
	public void showDialog(PatronBookInfoPanel panel) {
			
		panel.getBtnBack().addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            closeDialog(e);
	    	}
	    });
	    
		JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Success", true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.getContentPane().add(panel);
		dialog.setUndecorated(true);
	    dialog.setResizable(false);
		dialog.setSize(855, 770);
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