import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;


public class ProjectWindow extends JFrame {
	
	/* GUI Fields */
	private static final long serialVersionUID = 1L;
	JPanel loginBottomPanel = new JPanel(), userMainMenu = new JPanel(), quitMainPanel = new JPanel(), loginTopPanel = new JPanel(),
			searchBooksTopPanel = new JPanel(), searchBooksFieldPanel = new JPanel(), searchBooksBottomPanel = new JPanel(), searchBooksTextMainPanel = new JPanel(),
			searchBooksMainPanel = new JPanel(), searchBooksTablePanel = new JPanel(), searchMainPanel = new JPanel(), userLoginMainPanel, introTextPanel = new JPanel(),
			mainMenuMain = new JPanel(), topMain = new JPanel(), middleMain = new JPanel(), createAcartBottom = new JPanel(), createAcartMain = new JPanel(),
	 		createAcartMiddle = new JPanel(), createAcartInfo = new JPanel(), createCartInputMid = new JPanel(), infoRight = new JPanel(), succesPanel = new JPanel(),
	 		quitPanelTop = new JPanel(), quitPanelBottom = new JPanel();	
	
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final double WINDOW_WIDTH = screenSize.getWidth();
	private static final double WINDOW_HEIGHT = screenSize.getHeight();	 
	Dimension fullWindow = new Dimension((int)(WINDOW_WIDTH/1.75), (int)(WINDOW_HEIGHT/1.5)),
			  halfWindow = new Dimension((int)(WINDOW_WIDTH/1.75), (int)(WINDOW_HEIGHT/3)),
			  thirdWindow = new Dimension((int)(WINDOW_WIDTH/1.75), (int)(WINDOW_HEIGHT/4.5)),
			  quarterWindow = new Dimension((int)(WINDOW_WIDTH/1.75), (int)(WINDOW_HEIGHT/6)),
			  quarterQuarterWindow = new Dimension((int)(WINDOW_WIDTH/3.5), (int)(WINDOW_HEIGHT/6)),
			  tableSize = new Dimension((int)(WINDOW_WIDTH/2.4), (int)(WINDOW_HEIGHT/4.5)),
			  small = new Dimension((int)(WINDOW_WIDTH/7), (int)(WINDOW_HEIGHT/12)),
			  frameSize= new Dimension(this.getWidth(), this.getHeight());
	private static Color panelColor = Color.DARK_GRAY;
	JButton loginButton = new JButton("Login"), userButton = new JButton("User"), employeeButton = new JButton("Employee"), button0 = new JButton("Quit"), button1 = new JButton("Search Books"),
			button2 = new JButton("Create Shopping Cart"), button3 = new JButton("Add to Shopping Cart"), button4 = new JButton("Delete From Shopping Cart"), button5 = new JButton("Place Order"),
			button6 = new JButton("Merge Carts"), button7 = new JButton("Track Order"), button8 = new JButton("Change Password"), button9 = new JButton("Print Table"), searchButton = new JButton("Search"),
			userMenuButton = new JButton("Back to Menu"), quitButton, addToCartButton, registerButton;
	ImageIcon goodbye = new ImageIcon("art//goodbye.jpeg"), mainBackground = new ImageIcon("art//mainBackground.jpg");
	JTable table;
	JLabel searchBooks = new JLabel("Enter A Search Term"), titleLabel = new JLabel("Title:"), authorLabel = new JLabel("Author:"),
			isbnLabel = new JLabel("ISBN:"), categoryLabel = new JLabel("Category:"), splashScreen, success, cartNum, addToCartTitle, addToCartISBN, addToCartQuant, addToCartCartID,
			yourCartsTitle, availableCarts = new JLabel(""), addToCartSuccess;	
	
	// Add To Cart
	JPanel  addToCartMain = new JPanel(), addToCartTop = new JPanel(), addToCartMiddleTop = new JPanel(), addToCartBottom = new JPanel(), addToCartMiddleBottom = new JPanel(),
			adToCartSuccessPan = new JPanel(), addToCartMiddleTopRightBottom = new JPanel(), addToCartMiddleAddButpan = new JPanel(), addToCartMiddleTopBurn1 = new JPanel(),
			addToCartMiddleTopMainGrid = new JPanel(), addToCartMiddleTopAddButtPan = new JPanel(),	addToCartMiddleBottomCartspan = new JPanel(), addToInput1 = new JPanel(),
			addToInput2 = new JPanel(), addToInput3 = new JPanel(), addToInput4 = new JPanel(), addToInput5 = new JPanel(), addToInput6 = new JPanel(), 
			addToCartMiddleTopMiddle = new JPanel();
	JButton addToCartBackButton;
	
	
	// Search Books
	JTextField title, author, isbn, category, addISBN, addQuant, addCartID;
	
	// Create A Cart
	JPanel createInput1 = new JPanel(), createInput2 = new JPanel(), createInput3 = new JPanel(), createInput4 = new JPanel(), createSuccessPan = new JPanel(),
		   createCartNum = new JPanel(), createBottomBurn = new JPanel(), createAcartRealBottom = new JPanel(), createAcartTop = new JPanel(), createBottomBurnLeft = new JPanel(), 
		   createBackButtonPanel = new JPanel();
	JLabel createTitle, cartName, createSuccess, createCartNumLabel;
	JTextField cartNameText;
	JButton createCartButton, backButton;
	
	// Login Menu
	JPanel loginMiddle = new JPanel(), loginMiddleInput = new JPanel(), loginInput1 = new JPanel(), loginInput2 = new JPanel(), loginInput3 = new JPanel(),
			loginInput4 = new JPanel(), loginInput5 = new JPanel(), loginBurn = new JPanel(); 
	JLabel headerLabel, userid, password;
	JTextField usertext;
	JPasswordField passwordtext;
	JButton loginNewUser;
	
	//Employee Login Menu
	
	JPanel employeeLoginMiddle = new JPanel(), employeeLoginMiddleInput = new JPanel(), employeeLoginInput1 = new JPanel(), employeeLoginInput2 = new JPanel(), employeeLoginInput3 = new JPanel(),
			employeeLoginInput4 = new JPanel(), employeeLoginInput5 = new JPanel(), employeeLoginBurn = new JPanel(); 
	JLabel employeeHeaderLabel, employeeid, employeePassword;
	JTextField employeeText;
	JPasswordField employeePasswordText;
	String employeeIDtextField;
	char[] employeePasswordTextField;
	//JButton loginNewUser;
	JButton employeeLoginButton = new JButton("Login");
	//for employee login
	private static JPanel employeeLoginBottomPanel = new JPanel(), employeeLoginTopPanel = new JPanel(), employeeMainMenu = new JPanel(), quitEmployeeMainPanel = new JPanel(), employeeLoginMainPanel = new JPanel();
	
	//JButton employeeLoginButton = new JButton();

	//Employee main menu
	JButton ebutton1 = new JButton("Book Info"), ebutton2 = new JButton("Employee Orders"), ebutton3 = new JButton("Update Status"), ebutton4 = new JButton("Add/Update Book");	
	
	// User Menu
	JPanel searchPan = new JPanel(), createPan = new JPanel(), addPan = new JPanel(), deletePan = new JPanel(), placePan = new JPanel(), mergePan = new JPanel(),
		   trackPan = new JPanel(), changePan = new JPanel(), printPan = new JPanel(), quitPan = new JPanel(), userMenuBurn = new JPanel(), userMenuBurn2 = new JPanel();
		
	//Change Password Method Labels
	JLabel changePassword, currentPassword, newPassword, passwordStatus;
	JButton changePasswordSubmitButton, changePasswordBackButton;
	JPanel changePasswordMain = new JPanel(), changePasswordTop = new JPanel(), 
	 		changePasswordMidTop = new JPanel(), changePasswordMidBot = new JPanel(), changePasswordBot = new JPanel(), changePasswordBotLeft = new JPanel(), changePasswordBotRight = new JPanel(), 
	 		changePasswordMidBotSubmit = new JPanel(), changePasswordBotRightBack = new JPanel(), changePasswordMidTopCenter = new JPanel(), changeMiddleBottomText = new JPanel(), changeMiddleBottom = new JPanel(),	
			changeInput1 = new JPanel(), changeInput2 = new JPanel(), changeInput3 = new JPanel(), changeInput4 = new JPanel(), changeInput5 = new JPanel(), changeInput6 = new JPanel();
	
	// Delete From Cart Method Fields
	JPanel  deleteFromCartMain = new JPanel(), deleteFromCartTop = new JPanel(), deleteFromCartMiddleTop = new JPanel(), deleteFromCartBottom = new JPanel(), deleteFromCartMiddleBottom = new JPanel(),
			deleteFromCartSuccessPan = new JPanel(), deleteFromCartMiddleTopRightBottom = new JPanel(), deleteFromCartMiddleAddButpan = new JPanel(), deleteFromCartMiddleTopBurn1 = new JPanel(),
			deleteFromCartMiddleTopMainGrid = new JPanel(), deleteFromCartMiddleTopAddButtPan = new JPanel(),	deleteFromCartMiddleBottomCartspan = new JPanel(), deleteFromInput1 = new JPanel(),
			deleteFromInput2 = new JPanel(), deleteFromInput3 = new JPanel(), deleteFromInput4 = new JPanel(), deleteFromInput5 = new JPanel(), deleteFromInput6 = new JPanel(), 
			deleteFromCartMiddleTopMiddle = new JPanel();
	JButton deleteFromCartButton, deleteFromCartBackButton;
	JLabel deleteFromTitle, deleteFromISBN, deleteFromQuant, deleteFromCartID, deleteFromYourTitle, deleteFromAvailableCarts = new JLabel(""), deleteFromCartSuccess;;
	JTextField deleteFromIsbnInput, deleteFromQuantInput, deleteFromCartIDInput;
	String deleteUserIsbn, deleteUserQuant, deleteUserCartId;
	
	//Merge Carts Method Labels

	 JLabel mergeCartsTitle, mergeCartsResults, mergeCartOne, mergeCartTwo, mergeCartsCartLabel, mergeCartsListLabel;
	 JTextField mergeCartOneText, mergeCartTwoText;
	 JButton mergeCartsSubmitButton, mergeCartsBackButton;
	 
	 JPanel mergeCartsMain = new JPanel(), mergeCartsTop = new JPanel(), mergeCartsMidTop = new JPanel(), mergeCartsMid = new JPanel(), mergeCartsBot = new JPanel(), mergeCartsBotLeft = new JPanel(), mergeCartsBotRight = new JPanel(),
			 mergeCartsBotRightTop = new JPanel(), mergeCartsBotRightBot = new JPanel(), mergeCartsBotRightTopSubmit = new JPanel(), mergeCartsBotRightBotBack = new JPanel(), mergeCartsBotLeftResults = new JPanel(), mergeCartsMidTopList = new JPanel();

	 // Place Order Fields
	 JPanel placeOrderMain = new JPanel(), placeOrderTop = new JPanel(), placeOrderMiddle = new JPanel(), placeOrderBottom = new JPanel(),
			placeOrderMiddleMiddle = new JPanel(), placeOrderBottomMiddle = new JPanel(), placeOrderMiddleCartSlot = new JPanel(), placeOrderMiddleShipAdSlot = new JPanel(),
			placeOrderMiddleBillAdSlot = new JPanel(), placeOrderMiddleCCNumSlot = new JPanel(), placeOrderMiddleBillAdText = new JPanel(), placeOrderMiddleCCNumText = new JPanel(),
			placeOrderMiddleCartText = new JPanel(), placeOrderMiddleShipAdText = new JPanel(), burn = new JPanel(), burnTop = new JPanel(), burnBottom = new JPanel(),
			burn2 = new JPanel(), burn3 = new JPanel(), burn4 = new JPanel(), burn5 = new JPanel(), placeOrderTablePan = new JPanel(),
			searchBottomTop = new JPanel(), searchBottomBottom = new JPanel(), searchTitlePan = new JPanel(), searchAuthorPan = new JPanel(), searchISBNPan = new JPanel(),
			searchCatPan = new JPanel(), searchTitleTextPan = new JPanel(), searchAuthorTextPan = new JPanel(), searchISBNTextPan = new JPanel(), searchCatTextPan = new JPanel(),
			searchMiddleLeft = new JPanel(), searchMiddleRight = new JPanel(), searchTopTop = new JPanel(), searchTopBottom = new JPanel();
	JLabel placeOrderTitle, placeOrderCartID, placeOrderShippingAd, placeOrderBillingAd, placeOrderCCNum, placeOrderUpdateInfo, placeOrderNumCarts, placeOrderYourCarts,
	placeOrderText2, info;
	JTextField placeOrderCartInput, placeOrderShippAdInput, placeOrderBillAdInput, placeOrderCCNumInput, placeOrderText;
	JButton checkout, placeOrderBack1, placeOrderBack2, placeOrderView;
	
	// Register New User Fields
	JPanel registerMain = new JPanel(), registerTop = new JPanel(), registerMiddleTop = new JPanel(), registerBottom = new JPanel(), registerInputPanel = new JPanel(),
		   registerInput1 = new JPanel(), registerInput2 = new JPanel(), registerInput3 = new JPanel(), registerInput4 = new JPanel(), registerInput5 = new JPanel(),
		   registerInput6 = new JPanel(), registerInput7 = new JPanel(), registerInput8 = new JPanel(), registerInput9 = new JPanel(), registerInput10 = new JPanel(),
		   registerMiddleBottom = new JPanel(), registerInfoPanel = new JPanel();
	JButton signUpButton, registerLoginButton;
	JLabel registerTitle, registerPass, registerName, registerPhone, registerEmail, registerId, registerInfoLabel;
	JTextField registerNameInput, registerPhoneInput, registerEmailInput, registerIdInput;
	JPasswordField registerPassInput;
	
	// Print Tables Fields
		JPanel printTablesMain = new JPanel(), printTablesVeryTop = new JPanel(), printTablesTop = new JPanel(), printTablesBottom = new JPanel(), printTablesVeryBottom = new JPanel(), printTablePanel = new JPanel(), printTopGrid = new JPanel(),
			   printGrid1 = new JPanel(), printGrid2 = new JPanel(), printGrid3 = new JPanel(), printGrid4 = new JPanel(), printGrid5 = new JPanel(), printGrid6 = new JPanel(),
			   printGrid7 = new JPanel(), printGrid8 = new JPanel(), printGrid9 = new JPanel(), printGrid10 = new JPanel();
		JButton printBooksButton, printBooksInCartButton, printBooksOrderedButton, printCartButton, printCustomerButton, printOrdersButton, printBackButton;
		JLabel printTitle;
	
	//Track Orders Labels
	 JLabel trackOrderTitle, trackOrderResults, trackOrderNumber, trackOrderNumberLabel, trackOrderListLabel;
	 JTextField trackOrderText;
	 JButton trackOrderSubmitButton, trackOrderBackButton;
	 
	 JPanel trackOrderMain = new JPanel(), trackOrderTop = new JPanel(), trackOrderMidTop = new JPanel(), trackOrderMid = new JPanel(), trackOrderBot = new JPanel(), trackOrderBotLeft = new JPanel(), trackOrderBotRight = new JPanel(),
			 trackOrderBotRightTop = new JPanel(), trackOrderBotRightBot = new JPanel(), trackOrderBotRightTopSubmit = new JPanel(), trackOrderBotRightBotBack = new JPanel(), trackOrderBotLeftResults = new JPanel(), trackOrderMidTopList = new JPanel();

	//book info fields
	 JPanel biMain = new JPanel(), biv1 = new JPanel(), biv2 = new JPanel(), biv3 = new JPanel(), biv4 = new JPanel(), biv5 = new JPanel(), biv3h1 = new JPanel(), biv3h2 = new JPanel(), biv3h3 = new JPanel(), biv4h1 = new JPanel(), biv4h2 = new JPanel(), biv5List = new JPanel();
	 JLabel biISBNLabel, biTitleLabel, biv4h1Results;
	 JButton biSearchButton, biBackButton, biClearButton;
	 JTextField biISBNText;
	 
	 //employee orders fields
	 
	 JPanel eoMain = new JPanel(), eov1 = new JPanel(), eov2 = new JPanel(), eov3 = new JPanel();
	 JButton eoBackButton = new JButton();
	 JLabel  eoTitleLabel = new JLabel();
	
	 //employee Update Status
	 
	 JPanel usMain = new JPanel(), usv1 = new JPanel(), usv2 = new JPanel(), usv3 = new JPanel(), usv4 = new JPanel(), usv2h1 = new JPanel(), usv2h2 = new JPanel(), usv2h1Grid = new JPanel(), usg1 = new JPanel(), usg2 = new JPanel(), usg3 = new JPanel(), usg4 = new JPanel(),
			 usv3h1 = new JPanel(), usv3h2 = new JPanel(), usv2h2Results = new JPanel(), usv4List = new JPanel();
	 
	 JLabel usOrderNumLabel, usStatusLabel, usTitleLabel, usResultsLabel;
	 JButton usSubmitButton, usBackButton;
	 JTextField usOrderNumText, usStatusText;
	
	// Add/Update Book (Employees)
		 JPanel auMain = new JPanel(), auTop, auMiddle, auBottom, auVeryBottom, auInputGrid, auInput1, auInput2, auInput3, auInput4, auInput5, auInput6, auInput7, auInput8, auInput9, auInput10, auInput11,
		 	    auInput12, auInput13, auInput14, auInput15, auInput16, auInput17, auInput18, auMessagePanel, auBurn, auButtonPan;
		 JLabel isbnLab, authLab, titLab, edLab, yearLab, catLab, pubLab, quantLab, priceLab, auTitle, auMessageLab;
		 JTextField isbnT, authT, titT, edT, yearT, catT, pubT, quantT, priceT;
		 JButton auBackButton, auAddButton;
	 
	 
	 /* Database Fields */
	
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://localhost:3306/project";     
	final String USER = "root";
	final String PASS = "root";
	Connection conn = null, conn2 = null, conn3 = null, conn4 = null, conn5 = null;;
	Statement stmt = null, stmt2 = null, stmt3 = null, stmt4 = null, stmt5 = null;	
	ResultSet rs, rs2, rs3, rs4, rs5;
	String sql, sql2, sql3, sql4, sql5, sql6, sql7;
	int update;
	
	/* Other Fields */
	String customerID, employeeID, userIDtextField, pass = "", titleInput, authorInput, isbnInput, categoryInput, createKart, cartID, quantInput, cartIdInput, globalCartIDs = "", globalOrders = "",
			d;
	char[] passwordTextField;
	
	
	
	/**
	 * Constructor
	 * 
	 * Sets up initial page
	 * 
	 */
	public ProjectWindow() {		
		
		buildgui();       
	}
	
	
	/**
	 * main method
	 * 
	 * creates an instance of itself and makes visible
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				ProjectWindow pw = new ProjectWindow();
				pw.setVisible(true);
			}
		});		
	}

	
	/**
	 * Builds initial page
	 */
	public void buildgui() {
		
		this.setTitle("Billy Bob's Book Store");	
		this.setSize(fullWindow);		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);				
		this.setLayout(new BorderLayout());		
		this.setLocationRelativeTo(null);	
		
		JLabel menuDialog = new JLabel("Choose An Option");		
		 
		
		mainMenuMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};
		
		mainMenuMain.setLayout(new BoxLayout(mainMenuMain, BoxLayout.Y_AXIS));
		mainMenuMain.setPreferredSize(fullWindow);	
		//mainMenuMain.setBackground(Color.CYAN);			
		
		topMain.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));		
		//topMain.setBackground(panelColor);		
		topMain.setOpaque(false);
		
		middleMain.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));		
		//middleMain.setBackground(panelColor);				 
		middleMain.setOpaque(false);
		
		JPanel mainMenuBottom = new JPanel();
		mainMenuBottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));		
		//mainMenuBottom.setBackground(panelColor);	
		mainMenuBottom.setOpaque(false);
		
		menuDialog.setFont(new Font("Serif", Font.PLAIN, 60));
        menuDialog.setForeground(Color.WHITE);		
        
        userButton.setPreferredSize(new Dimension((int)WINDOW_WIDTH/10,(int) WINDOW_HEIGHT/12));
		userButton.setHorizontalTextPosition(SwingConstants.CENTER);
		userButton.setVerticalTextPosition(SwingConstants.CENTER);;
		userButton.setFont(new Font(null, Font.PLAIN, 24));
		
		employeeButton.setPreferredSize(new Dimension((int)WINDOW_WIDTH/10, (int)WINDOW_HEIGHT/12));		
		employeeButton.setHorizontalTextPosition(SwingConstants.CENTER);		
		employeeButton.setVerticalTextPosition(SwingConstants.CENTER);
		employeeButton.setFont(new Font(null, Font.PLAIN, 28));
			
		registerButton = new JButton("New User");
		registerButton.setPreferredSize(new Dimension((int)WINDOW_WIDTH/14,(int) WINDOW_HEIGHT/16));
		registerButton.setHorizontalTextPosition(SwingConstants.CENTER);
		registerButton.setVerticalTextPosition(SwingConstants.CENTER);;
		registerButton.setFont(new Font(null, Font.PLAIN, 20));
	
		
		topMain.add(menuDialog);
		
		middleMain.add(userButton);
		middleMain.add(employeeButton); 
		
		mainMenuBottom.add(registerButton);
		  
		mainMenuMain.add(topMain);
		mainMenuMain.add(middleMain);
		mainMenuMain.add(mainMenuBottom);      
		
		userButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				userLogin();
			}
		});
		
		employeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeLogin();
			}
		});
		
		userButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	userButton.setBackground(Color.GRAY);
		    	userButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	userButton.setBackground(UIManager.getColor("control"));
		    	userButton.setForeground(Color.BLACK);
		    }
		});
		
		employeeButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	employeeButton.setBackground(Color.GRAY);
		    	employeeButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	employeeButton.setBackground(UIManager.getColor("control"));
		    	employeeButton.setForeground(Color.BLACK);
		    }
		});
		
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerNewUser();
			}
		});
		
		registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	registerButton.setBackground(Color.GRAY);
		    	registerButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	registerButton.setBackground(UIManager.getColor("control"));
		    	registerButton.setForeground(Color.BLACK);
		    }
		});
		
		
		 this.add(mainMenuMain);
		 this.pack();
		 this.setVisible(true);		 
	}
	
	
	/**
	 * 
	 */
	public void registerNewUser() {
		
		mainMenuMain.setVisible(false);		
		
		
		registerMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};
		registerMain.setLayout(new BoxLayout(registerMain, BoxLayout.Y_AXIS));
		registerMain.setPreferredSize(fullWindow);
		
		registerTop.setLayout(new FlowLayout(FlowLayout.CENTER));		
		registerTop.setOpaque(false);
		
		registerMiddleTop.setLayout(new FlowLayout(FlowLayout.CENTER));		
		registerMiddleTop.setOpaque(false);
		
		registerMiddleBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		registerMiddleBottom.setOpaque(false);
		
		registerBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		registerBottom.setOpaque(false);
		
		registerInputPanel.setLayout(new GridLayout(5, 2));
		registerInputPanel.setOpaque(false);
		
		registerInput1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		registerInput1.setOpaque(false);
		
		registerInput2.setLayout(new FlowLayout(FlowLayout.LEFT));
		registerInput2.setOpaque(false);
		
		registerInput3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		registerInput3.setOpaque(false);
		
		registerInput4.setLayout(new FlowLayout(FlowLayout.LEFT));
		registerInput4.setOpaque(false);
		
		registerInput5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		registerInput5.setOpaque(false);
		
		registerInput6.setLayout(new FlowLayout(FlowLayout.LEFT));
		registerInput6.setOpaque(false);
		
		registerInput7.setLayout(new FlowLayout(FlowLayout.RIGHT));
		registerInput7.setOpaque(false);
		
		registerInput8.setLayout(new FlowLayout(FlowLayout.LEFT));
		registerInput8.setOpaque(false);
		
		registerInput9.setLayout(new FlowLayout(FlowLayout.RIGHT));
		registerInput9.setOpaque(false);
		
		registerInput10.setLayout(new FlowLayout(FlowLayout.LEFT));
		registerInput10.setOpaque(false);
		
		registerInfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		registerInfoPanel.setOpaque(false);
		
		registerInfoLabel = new JLabel("");
		registerInfoLabel.setFont(new Font("Serif", Font.PLAIN, 40));
		registerInfoLabel.setForeground(Color.WHITE);
		
		registerTitle = new JLabel("Register");
		registerTitle.setFont(new Font("Serif", Font.PLAIN, 60));
		registerTitle.setForeground(Color.WHITE);
		
		registerPass = new JLabel("Enter A Password:");
		registerPass.setFont(new Font(null, Font.PLAIN, 20));
		registerPass.setForeground(Color.WHITE);
		
		registerName = new JLabel("Enter Your Name:");
		registerName.setFont(new Font(null, Font.PLAIN, 20));
		registerName.setForeground(Color.WHITE);
		
		registerPhone = new JLabel("Enter Your Phone Number:");
		registerPhone.setFont(new Font(null, Font.PLAIN, 20));
		registerPhone.setForeground(Color.WHITE);
		
		registerEmail = new JLabel("Enter Your Email:");
		registerEmail.setFont(new Font(null, Font.PLAIN, 20));
		registerEmail.setForeground(Color.WHITE);
		
		registerId = new JLabel("Enter an ID:");
		registerId.setFont(new Font(null, Font.PLAIN, 20));
		registerId.setForeground(Color.WHITE);
		
		signUpButton = new JButton("Sign Up");
		signUpButton.setFont(new Font(null, Font.PLAIN, 20));	
				
		registerLoginButton = new JButton("Login");
		registerLoginButton.setFont(new Font(null, Font.PLAIN, 20));	
		
		registerPassInput = new JPasswordField(15);
		registerPassInput.setFont(new Font(null, Font.PLAIN, 20));
		
		registerNameInput = new JTextField(15);
		registerNameInput.setFont(new Font(null, Font.PLAIN, 20));
		
		registerPhoneInput = new JTextField(15);
		registerPhoneInput.setFont(new Font(null, Font.PLAIN, 20));
		
		registerEmailInput = new JTextField(15);
		registerEmailInput.setFont(new Font(null, Font.PLAIN, 20));
		
		registerIdInput = new JTextField(15);
		registerIdInput.setFont(new Font(null, Font.PLAIN, 20));
		
		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				@SuppressWarnings("deprecation")
				String pass = registerPassInput.getText();
				String name = registerNameInput.getText();
				String phone = registerPhoneInput.getText();
				String email = registerEmailInput.getText();
				String id = registerIdInput.getText();		
				
				runRegisterSQL(name, id, pass, phone, email);
			}
		});		
		
		signUpButton.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					@SuppressWarnings("deprecation")
					String pass = registerPassInput.getText();
					String name = registerNameInput.getText();
					String phone = registerPhoneInput.getText();
					String email = registerEmailInput.getText();
					String id = registerIdInput.getText();		
										
					runRegisterSQL(name, id, pass, phone, email);
				}	      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		
		signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	signUpButton.setBackground(Color.GRAY);
		    	signUpButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	signUpButton.setBackground(UIManager.getColor("control"));
		    	signUpButton.setForeground(Color.BLACK);
		    }
		});
		
		registerLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
								
				registerInfoPanel.setBackground(panelColor);
				registerInfoLabel.setText("");
				registerInfoPanel.setBorder(BorderFactory.createLineBorder(panelColor));
				
				registerMain.setVisible(false);
				
				userLogin();
			}
		});
		
		registerLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	registerLoginButton.setBackground(Color.GRAY);
		    	registerLoginButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	registerLoginButton.setBackground(UIManager.getColor("control"));
		    	registerLoginButton.setForeground(Color.BLACK);
		    }
		});
		
		registerEmailInput.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {					
					
					if (signUpButton.isDisplayable()) {
						@SuppressWarnings("deprecation")
						String pass = registerPassInput.getText();
						String name = registerNameInput.getText();
						String phone = registerPhoneInput.getText();
						String email = registerEmailInput.getText();
						String id = registerIdInput.getText();				
						
						runRegisterSQL(name, id, pass, phone, email);
					}
					else {
						registerInfoPanel.setBackground(panelColor);
						registerInfoLabel.setText("");
						registerInfoPanel.setBorder(BorderFactory.createLineBorder(panelColor));
						
						registerMain.setVisible(false);
						
						userLogin();
					}
				}	      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		
		registerIdInput.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if (signUpButton.isDisplayable()) {
						@SuppressWarnings("deprecation")
						String pass = registerPassInput.getText();
						String name = registerNameInput.getText();
						String phone = registerPhoneInput.getText();
						String email = registerEmailInput.getText();
						String id = registerIdInput.getText();				
						
						runRegisterSQL(name, id, pass, phone, email);
					}
					else {
						registerInfoPanel.setBackground(panelColor);
						registerInfoLabel.setText("");
						registerInfoPanel.setBorder(BorderFactory.createLineBorder(panelColor));
						
						registerMain.setVisible(false);
						
						userLogin();
					}
				}	      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});		
		
		registerInput1.add(registerName);
		registerInput2.add(registerNameInput);
		registerInput3.add(registerId);
		registerInput4.add(registerIdInput);
		registerInput5.add(registerPass);
		registerInput6.add(registerPassInput);
		registerInput7.add(registerPhone);
		registerInput8.add(registerPhoneInput);
		registerInput9.add(registerEmail);
		registerInput10.add(registerEmailInput);
		
		registerInputPanel.add(registerInput1);
		registerInputPanel.add(registerInput2);
		registerInputPanel.add(registerInput3);
		registerInputPanel.add(registerInput4);
		registerInputPanel.add(registerInput5);
		registerInputPanel.add(registerInput6);
		registerInputPanel.add(registerInput7);
		registerInputPanel.add(registerInput8);
		registerInputPanel.add(registerInput9);
		registerInputPanel.add(registerInput10);
		
		registerTop.add(registerTitle);
		
		registerMiddleTop.add(registerInputPanel);
		
		registerInfoPanel.add(registerInfoLabel);
		
		registerMiddleBottom.add(registerInfoPanel);
		
		registerBottom.add(signUpButton);
		
		registerMain.add(registerTop);
		registerMain.add(registerMiddleTop);
		registerMain.add(registerMiddleBottom);
		registerMain.add(registerBottom);
		
		this.add(registerMain);
		this.pack();
		this.setVisible(true);
	}
	
	public void runRegisterSQL(String name, String id, String pass, String phone, String email) {
		
		conn = null;
		stmt = null;
		conn2 = null;
		stmt2 = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();		
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt2 = conn2.createStatement();	
				
			sql = "select * from customer where customerID = " + id;
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				registerInfoLabel.setText("ID Taken, Choose Another.");	
				
				registerIdInput.setText("");
				
				registerInfoPanel.setBackground(Color.GRAY);
				registerInfoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
			}
			else {
				sql2 = "CALL AccountCreation('" + id + "','" + name + "','" + phone + "','" + email + "','" + pass + "')";
				update = stmt2.executeUpdate(sql2);	
				
				registerInfoLabel.setText("You Are Signed Up!");
				
				registerPassInput.setText("");
				registerNameInput.setText("");
				registerPhoneInput.setText("");
				registerEmailInput.setText("");
				registerIdInput.setText("");
				
				registerInfoPanel.setBackground(Color.GRAY);
				registerInfoPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				
				registerBottom.remove(signUpButton);				
				registerBottom.revalidate();				
				registerBottom.add(registerLoginButton);
				registerBottom.revalidate();
			}							
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
	}
	
	
	/**
	 * Displays the users main menu
	 */
	public void getCartIDs() {
		
		conn = null;
		stmt = null;
		globalCartIDs = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();		
				
			sql = "SELECT CartID FROM cart WHERE customerid = '" + customerID + "'";
			
			
			rs = stmt.executeQuery(sql);		
			
			while(rs.next()) {				
				globalCartIDs += rs.getString("CartID") + " ";
			}				
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
	}
	
	/**
	 * Displays the users main menu
	 */
	
	
	public void displayUserMenu() {
		
		// From user login
		loginBottomPanel.removeAll();
		loginTopPanel.removeAll();
		userLoginMainPanel.removeAll();
		this.remove(userLoginMainPanel);		
				
		userMainMenu = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};		
		userMainMenu.setLayout(new GridLayout(4, 3));		
		userMainMenu.setPreferredSize(fullWindow);			
				
		searchPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		searchPan.setOpaque(false);		
		
		createPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		createPan.setOpaque(false);
		
		addPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		addPan.setOpaque(false);
		
		deletePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		deletePan.setOpaque(false);
		
		placePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		placePan.setOpaque(false);
		
		mergePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		mergePan.setOpaque(false);
		
		trackPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		trackPan.setOpaque(false);
		
		changePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		changePan.setOpaque(false);
		
		printPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		printPan.setOpaque(false);
		
		quitPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		quitPan.setOpaque(false);
		
		userMenuBurn = new JPanel();
		userMenuBurn.setOpaque(false);
		
		userMenuBurn2 = new JPanel();
		userMenuBurn2.setOpaque(false);
		
		// Quit
		button0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userMainMenu.setVisible(false);
				quitSplashScreen();
			}
		});
		
		// Search Books
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(searchMainPanel.isDisplayable()) {
					
					userMainMenu.setVisible(false);
					searchMainPanel.setVisible(true);					
					searchMainPanel.repaint();
				}
				else  {					
					userMainMenu.setVisible(false);
					bookSearch();					
				}
			}
		});
		
		// Create Shopping Cart
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(createAcartMain.isDisplayable()) {
					
					userMainMenu.setVisible(false);
					createAcartMain.setVisible(true);
					createAcartMain.repaint();
					
				}
				else {					
					userMainMenu.setVisible(false);
					createShoppingCart();
				}
			}
		});		
		
		// Add to Shopping Cart
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//userLogin();
				if(addToCartMain.isDisplayable()) {
					getCartIDs();
					
					availableCarts.setText(globalCartIDs);
					
					userMainMenu.setVisible(false);
					addToCartMain.setVisible(true);
					addToCartMain.repaint();
					
				}
				else {	
					getCartIDs();
					userMainMenu.setVisible(false);
					
					availableCarts.setText(globalCartIDs);
					
					addToCart();
				}
			}
		});
		
		// Delete From Cart
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(deleteFromCartMain.isDisplayable()) {
					getCartIDs();
					
					deleteFromAvailableCarts.setText(globalCartIDs);
					
					userMainMenu.setVisible(false);
					deleteFromCartMain.setVisible(true);
					deleteFromCartMain.repaint();
					
				}
				else {			
					getCartIDs();
					
					deleteFromAvailableCarts.setText(globalCartIDs);
					
					userMainMenu.setVisible(false);
					deleteFromCart();
				}
			}
		});
		// Place Order
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//userLogin();
				
				if(placeOrderMain.isDisplayable()) {
					
					userMainMenu.setVisible(false);
					placeOrderMain.setVisible(true);
					placeOrderMain.repaint();
					getCartIDs();
					placeOrderYourCarts.setText(globalCartIDs);	
			        
				}
				else {					
					userMainMenu.setVisible(false);
					placeOrder();
				}
			}
		});
		
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Merge Carts
				if(mergeCartsMain.isDisplayable()) {
					
					getCartIDs();
					mergeCartsListLabel.setText(globalCartIDs);
					userMainMenu.setVisible(false);
					mergeCartsMain.setVisible(true);
					mergeCartsMain.repaint();
					
				}
				else {					
					userMainMenu.setVisible(false);
					mergeCarts();
			}
		}
		});
		
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(trackOrderMain.isDisplayable()) {
					
					userMainMenu.setVisible(false);
					trackOrderMain.setVisible(true);
					trackOrderMain.repaint();
					
				}
				else {					
					userMainMenu.setVisible(false);
					trackOrder();
				}
			}
		});
		
		button8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//change password
				if(changePasswordMain.isDisplayable()) {
					
					userMainMenu.setVisible(false);
					changePasswordMain.setVisible(true);
					changePasswordMain.repaint();
					
				}
				else {					
					userMainMenu.setVisible(false);
					changePassword();
				}
				
			}
		});
		
		button9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Print Table
				if(printTablesMain.isDisplayable()) {
					
					userMainMenu.setVisible(false);
					printTablesMain.setVisible(true);
					printTablesMain.repaint();
					
				}
				else {					
					userMainMenu.setVisible(false);
					printTables();
				}
			}
		});
		
		button0.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button0.setBackground(Color.GRAY);
		    	button0.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button0.setBackground(UIManager.getColor("control"));
		    	button0.setForeground(Color.BLACK);
		    }
		});
		
		button1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button1.setBackground(Color.GRAY);
		    	button1.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button1.setBackground(UIManager.getColor("control"));
		    	button1.setForeground(Color.BLACK);
		    }
		});
		button2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button2.setBackground(Color.GRAY);
		    	button2.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button2.setBackground(UIManager.getColor("control"));
		    	button2.setForeground(Color.BLACK);
		    }
		});
		
		button3.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button3.setBackground(Color.GRAY);
		    	button3.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button3.setBackground(UIManager.getColor("control"));
		    	button3.setForeground(Color.BLACK);
		    }
		});
		
		button4.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button4.setBackground(Color.GRAY);
		    	button4.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button4.setBackground(UIManager.getColor("control"));
		    	button4.setForeground(Color.BLACK);
		    }
		});
		
		button5.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button5.setBackground(Color.GRAY);
		    	button5.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button5.setBackground(UIManager.getColor("control"));
		    	button5.setForeground(Color.BLACK);
		    }
		});
		
		button6.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button6.setBackground(Color.GRAY);
		    	button6.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button6.setBackground(UIManager.getColor("control"));
		    	button6.setForeground(Color.BLACK);
		    }
		});
		
		button7.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button7.setBackground(Color.GRAY);
		    	button7.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button7.setBackground(UIManager.getColor("control"));
		    	button7.setForeground(Color.BLACK);
		    }
		});
		
		button8.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button8.setBackground(Color.GRAY);
		    	button8.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button8.setBackground(UIManager.getColor("control"));
		    	button8.setForeground(Color.BLACK);
		    }
		});
		
		button9.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button9.setBackground(Color.GRAY);
		    	button9.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button9.setBackground(UIManager.getColor("control"));
		    	button9.setForeground(Color.BLACK);
		    }
		});
		
		button0.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button0.setHorizontalTextPosition(SwingConstants.CENTER);
		button0.setVerticalTextPosition(SwingConstants.CENTER);;
		button0.setFont(new Font(null, Font.PLAIN, 18));
		
		button1.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button1.setHorizontalTextPosition(SwingConstants.CENTER);
		button1.setVerticalTextPosition(SwingConstants.CENTER);;
		button1.setFont(new Font(null, Font.PLAIN, 18));
		
		button2.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button2.setHorizontalTextPosition(SwingConstants.CENTER);
		button2.setVerticalTextPosition(SwingConstants.CENTER);;
		button2.setFont(new Font(null, Font.PLAIN, 18));
		
		button3.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button3.setHorizontalTextPosition(SwingConstants.CENTER);
		button3.setVerticalTextPosition(SwingConstants.CENTER);;
		button3.setFont(new Font(null, Font.PLAIN, 18));
		
		button4.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button4.setHorizontalTextPosition(SwingConstants.CENTER);
		button4.setVerticalTextPosition(SwingConstants.CENTER);;
		button4.setFont(new Font(null, Font.PLAIN, 18));
		
		button5.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button5.setHorizontalTextPosition(SwingConstants.CENTER);
		button5.setVerticalTextPosition(SwingConstants.CENTER);;
		button5.setFont(new Font(null, Font.PLAIN, 18));
		
		button6.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button6.setHorizontalTextPosition(SwingConstants.CENTER);
		button6.setVerticalTextPosition(SwingConstants.CENTER);;
		button6.setFont(new Font(null, Font.PLAIN, 18));
		
		button7.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button7.setHorizontalTextPosition(SwingConstants.CENTER);
		button7.setVerticalTextPosition(SwingConstants.CENTER);;
		button7.setFont(new Font(null, Font.PLAIN, 18));
		
		button8.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button8.setHorizontalTextPosition(SwingConstants.CENTER);
		button8.setVerticalTextPosition(SwingConstants.CENTER);;
		button8.setFont(new Font(null, Font.PLAIN, 18));
		
		button9.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button9.setHorizontalTextPosition(SwingConstants.CENTER);
		button9.setVerticalTextPosition(SwingConstants.CENTER);;
		button9.setFont(new Font(null, Font.PLAIN, 18));	
		
		searchPan.add(button1);
		createPan.add(button2);
		addPan.add(button3);
		deletePan.add(button4);
		placePan.add(button5);
		mergePan.add(button6);
		trackPan.add(button7);
		changePan.add(button8);
		printPan.add(button9);
		quitPan.add(button0);	
		
		userMainMenu.add(searchPan);
		userMainMenu.add(createPan);
		userMainMenu.add(addPan);
		userMainMenu.add(deletePan);
		userMainMenu.add(placePan);
		userMainMenu.add(mergePan);
		userMainMenu.add(trackPan);
		userMainMenu.add(changePan);
		userMainMenu.add(printPan);	
		userMainMenu.add(userMenuBurn);
		userMainMenu.add(quitPan);	
		userMainMenu.add(userMenuBurn2);
		
		this.add(userMainMenu);
		this.pack();
	}
	
	
	/**
	 * Runs sql test for user login validation
	 * 
	 * @param String - Id from text field
	 * @param char[] - password from the password text field
	 */
	
	public void userValidation (String uidText, char[] pwText) {
			
		pass = "";			
		
		for (int i = 0; i < pwText.length; i++) {
			pass += pwText[i];
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();		
				
			sql = "SELECT * FROM customer WHERE customerid = '" + uidText + "' and cuspassword = '" + pass + "'";
			
			
			rs = stmt.executeQuery(sql);		
			
			if(rs.next()) {				
				customerID = uidText;					
				JOptionPane.showMessageDialog(null,"Welcome", "Success", JOptionPane.INFORMATION_MESSAGE);
				displayUserMenu();
			}
			else {					
				JOptionPane.showMessageDialog(null,"Incorrect Username and/or Password", "Failed Login", JOptionPane.ERROR_MESSAGE);						
			}			
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
	}
	
	
	/**
	 * Sets up the GUI for the user login page 
	 */
	
	public void displayEmployeeMenu() {
		
		
		employeeLoginBottomPanel.removeAll();
		employeeLoginTopPanel.removeAll();
		employeeLoginMainPanel.removeAll();
		this.remove(employeeLoginMainPanel);		
		createTempBooks();	
		employeeMainMenu = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};		
		employeeMainMenu.setLayout(new GridLayout(4, 3));		
		employeeMainMenu.setPreferredSize(fullWindow);			
		
		searchPan = new JPanel();
		searchPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		searchPan.setOpaque(false);
		
		createPan = new JPanel();
		createPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		createPan.setOpaque(false);
		
		addPan = new JPanel();
		addPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		addPan.setOpaque(false);
		
		deletePan = new JPanel();
		deletePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		deletePan.setOpaque(false);
		
		placePan = new JPanel();
		placePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		placePan.setOpaque(false);
		
		mergePan = new JPanel();
		mergePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		mergePan.setOpaque(false);
		
		trackPan = new JPanel();
		trackPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		trackPan.setOpaque(false);
		
		changePan = new JPanel();
		changePan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		changePan.setOpaque(false);
		
		printPan = new JPanel();
		printPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		printPan.setOpaque(false);
		
		quitPan = new JPanel();
		quitPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		quitPan.setOpaque(false);
		
		burn = new JPanel();
		burn.setOpaque(false);
		
		// Quit
		button0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeMainMenu.setVisible(false);
				quitSplashScreen();
			}
		});
		
		// Book Info
		ebutton1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(biMain.isDisplayable()) {
					
					employeeMainMenu.setVisible(false);
					biMain.setVisible(true);					
					biMain.repaint();
				}
				else  {					
					employeeMainMenu.setVisible(false);
					bookInfo();					
				}
			}
		});
		
		// Employee Orders
		ebutton2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(eoMain.isDisplayable()) {
					
					employeeMainMenu.setVisible(false);
					eoMain.setVisible(true);
					eoMain.repaint();
					
				}
				else {					
					employeeMainMenu.setVisible(false);
					employeeOrders();
				}
			}
		});		
		
		// Update Status
		ebutton3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//userLogin();
				if(usMain.isDisplayable()) {
					
					employeeMainMenu.setVisible(false);
					usMain.setVisible(true);
					usMain.repaint();
					
				}
				else {					
					employeeMainMenu.setVisible(false);
					updateStatus();
					updateStatusTable();
				}
			}
		});
		
		// Add Books
		ebutton4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(deleteFromCartMain.isDisplayable()) {
					
					employeeMainMenu.setVisible(false);
					deleteFromCartMain.setVisible(true);
					deleteFromCartMain.repaint();
					
				}
				else {					
					employeeMainMenu.setVisible(false);
					deleteFromCart();
				}
			}
		});
				
		button0.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button0.setBackground(Color.GRAY);
		    	button0.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button0.setBackground(UIManager.getColor("control"));
		    	button0.setForeground(Color.BLACK);
		    }
		});
		
		button1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ebutton1.setBackground(Color.GRAY);
		    	ebutton1.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ebutton1.setBackground(UIManager.getColor("control"));
		    	ebutton1.setForeground(Color.BLACK);
		    }
		});
		button2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ebutton2.setBackground(Color.GRAY);
		    	ebutton2.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ebutton2.setBackground(UIManager.getColor("control"));
		    	ebutton2.setForeground(Color.BLACK);
		    }
		});
		
		button3.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ebutton3.setBackground(Color.GRAY);
		    	ebutton3.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ebutton3.setBackground(UIManager.getColor("control"));
		    	ebutton3.setForeground(Color.BLACK);
		    }
		});
		
		button4.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	ebutton4.setBackground(Color.GRAY);
		    	ebutton4.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	ebutton4.setBackground(UIManager.getColor("control"));
		    	ebutton4.setForeground(Color.BLACK);
		    }
		});
		
				
		button0.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		button0.setHorizontalTextPosition(SwingConstants.CENTER);
		button0.setVerticalTextPosition(SwingConstants.CENTER);;
		button0.setFont(new Font(null, Font.PLAIN, 18));
		
		ebutton1.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		ebutton1.setHorizontalTextPosition(SwingConstants.CENTER);
		ebutton1.setVerticalTextPosition(SwingConstants.CENTER);;
		ebutton1.setFont(new Font(null, Font.PLAIN, 18));
		
		ebutton2.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		ebutton2.setHorizontalTextPosition(SwingConstants.CENTER);
		ebutton2.setVerticalTextPosition(SwingConstants.CENTER);;
		ebutton2.setFont(new Font(null, Font.PLAIN, 18));
		
		ebutton3.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		ebutton3.setHorizontalTextPosition(SwingConstants.CENTER);
		ebutton3.setVerticalTextPosition(SwingConstants.CENTER);;
		ebutton3.setFont(new Font(null, Font.PLAIN, 18));
		
		ebutton4.setPreferredSize(new Dimension((int)WINDOW_WIDTH/8,(int) WINDOW_HEIGHT/14));
		ebutton4.setHorizontalTextPosition(SwingConstants.CENTER);
		ebutton4.setVerticalTextPosition(SwingConstants.CENTER);;
		ebutton4.setFont(new Font(null, Font.PLAIN, 18));
		
		
		searchPan.add(ebutton1);
		createPan.add(ebutton2);
		addPan.add(ebutton3);
		deletePan.add(ebutton4);
		quitPan.add(button0);	
		
		employeeMainMenu.add(searchPan);
		employeeMainMenu.add(createPan);
		employeeMainMenu.add(addPan);
		employeeMainMenu.add(deletePan);
		employeeMainMenu.add(placePan);
		employeeMainMenu.add(mergePan);
		employeeMainMenu.add(trackPan);
		employeeMainMenu.add(changePan);
		employeeMainMenu.add(printPan);	
		employeeMainMenu.add(burn);
		employeeMainMenu.add(quitPan);	
		
		this.add(employeeMainMenu);
		this.pack();
	}

	
	
	
	public void userLogin() {
		
		topMain.removeAll();
		middleMain.removeAll();
		mainMenuMain.removeAll();	
		this.remove(mainMenuMain);
		
		
		userLoginMainPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};			
		userLoginMainPanel.setLayout(new BoxLayout(userLoginMainPanel, BoxLayout.Y_AXIS));
		userLoginMainPanel.setPreferredSize(fullWindow);			
		
		loginBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));		
		loginBottomPanel.setOpaque(false);
		
		loginTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));		
		loginTopPanel.setOpaque(false);
		
		loginMiddle.setLayout(new FlowLayout(FlowLayout.CENTER));
		loginMiddle.setOpaque(false);
		
		loginMiddleInput.setLayout(new GridLayout(3, 2));		
		loginMiddleInput.setOpaque(false);
		
		loginInput1.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		loginInput1.setOpaque(false);
		
		loginInput2.setLayout(new FlowLayout(FlowLayout.LEFT));		
		loginInput2.setOpaque(false);
		
		loginInput3.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		loginInput3.setOpaque(false);
		
		loginInput4.setLayout(new FlowLayout(FlowLayout.LEFT));		
		loginInput4.setOpaque(false);
		
		loginInput5.setLayout(new FlowLayout(FlowLayout.CENTER));		
		loginInput5.setOpaque(false);
		
		loginBurn.setLayout(new FlowLayout(FlowLayout.CENTER));		
		loginBurn.setOpaque(false);	
		
		loginButton.setFont(new Font(null, Font.PLAIN, 26));
		
		loginNewUser = new JButton("New User");
		loginNewUser.setFont(new Font(null, Font.PLAIN, 24));
		
		headerLabel = new JLabel("Enter Your User Name and Password", JLabel.CENTER);
		headerLabel.setFont(new Font("Serif", Font.PLAIN, 55));
		headerLabel.setForeground(Color.WHITE);
		
		userid = new JLabel("User ID: ");
		userid.setFont(new Font(null, Font.PLAIN, 22));
		userid.setForeground(Color.WHITE);
		
		password = new JLabel("Password:");
		password.setFont(new Font(null, Font.PLAIN, 22));	
		password.setForeground(Color.WHITE);		
		
		usertext = new JTextField(10);
		usertext.setFont(new Font(null, Font.PLAIN, 20));	
		
		passwordtext = new JPasswordField(10);
		passwordtext.setFont(new Font(null, Font.PLAIN, 20));	
		
		
		// Button Press Listener
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				userIDtextField = usertext.getText();
				passwordTextField = passwordtext.getPassword();	
				usertext.setText("");
				passwordtext.setText("");
				getCartIDs();
				userValidation(userIDtextField, passwordTextField);
			}
		});	
		
		// Mouse over listener
		loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	loginButton.setBackground(Color.GRAY);
		    	loginButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	loginButton.setBackground(UIManager.getColor("control"));
		    	loginButton.setForeground(Color.BLACK);
		    }
		});
		
		loginButton.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					userIDtextField = usertext.getText();
					passwordTextField = passwordtext.getPassword();	
					usertext.setText("");
					passwordtext.setText("");
					getCartIDs();
					userValidation(userIDtextField, passwordTextField);
				}		      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
			
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		
		// Button Press Listener
		loginNewUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				if(registerMain.isDisplayable()) {
					
					userLoginMainPanel.setVisible(false);
					registerMain.setVisible(true);
					registerMain.repaint();
					
				}
				else {					
					userLoginMainPanel.setVisible(false);
					registerNewUser();
				}
			}
		});	
		
		// Mouse over listener
		loginNewUser.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	loginNewUser.setBackground(Color.GRAY);
		    	loginNewUser.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	loginNewUser.setBackground(UIManager.getColor("control"));
		    	loginNewUser.setForeground(Color.BLACK);
		    }
		});
		
		// Key Press Listener
		loginNewUser.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Enter Key Listener
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					if(registerMain.isDisplayable()) {
						
						userLoginMainPanel.setVisible(false);
						registerMain.setVisible(true);
						registerMain.repaint();
						
					}
					else {					
						userLoginMainPanel.setVisible(false);
						registerNewUser();
					}
				}		      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		
		// Key Press Listener
		passwordtext.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Enter Key Listener
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					userIDtextField = usertext.getText();
					passwordTextField = passwordtext.getPassword();	
					usertext.setText("");
					passwordtext.setText("");
					getCartIDs();
					userValidation(userIDtextField, passwordTextField);
				}		      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		
		// Key Press Listener
		usertext.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Enter Key Listener
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					userIDtextField = usertext.getText();
					passwordTextField = passwordtext.getPassword();	
					usertext.setText("");
					passwordtext.setText("");
					getCartIDs();
					userValidation(userIDtextField, passwordTextField);
				}			      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
				
			
		loginInput1.add(userid);
		loginInput2.add(usertext);
		loginInput3.add(password);
		loginInput4.add(passwordtext);
		loginInput5.add(loginButton);	
		
		loginMiddleInput.add(loginInput1);
		loginMiddleInput.add(loginInput2);
		loginMiddleInput.add(loginInput3);
		loginMiddleInput.add(loginInput4);
		loginMiddleInput.add(loginBurn);
		loginMiddleInput.add(loginInput5);	
		
		loginTopPanel.add(headerLabel);				
		
		loginMiddle.add(loginMiddleInput);
		
		loginBottomPanel.add(loginNewUser);
		
		userLoginMainPanel.add(loginTopPanel);
		userLoginMainPanel.add(loginMiddle);
		userLoginMainPanel.add(loginBottomPanel);
				
		this.add(userLoginMainPanel);		
		this.pack();	
		this.setVisible(true);
	}
	
	
	/**
	 * Displays quit GUI
	 */
	public void employeeValidation(String empid, char[] pwText) {
		
		
		pass = "";			
		
		for (int i = 0; i < pwText.length; i++) {
			pass += pwText[i];
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();		
			
			
			sql = "SELECT * FROM employee WHERE employeeID = '" + empid + "' and ManagerID = '" + pass + "'";
			
			
			rs = stmt.executeQuery(sql);		
			
			if(rs.next()) {				
				employeeID = empid;					
				JOptionPane.showMessageDialog(null,"Welcome", "Success", JOptionPane.INFORMATION_MESSAGE);
				displayEmployeeMenu();
			}
			else {					
				JOptionPane.showMessageDialog(null,"Incorrect Username and/or Password", "Failed Login", JOptionPane.ERROR_MESSAGE);						
			}			
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
	}
		
	
	
public void employeeLogin() {
		
		topMain.removeAll();
		middleMain.removeAll();
		mainMenuMain.removeAll();	
		this.remove(mainMenuMain);
		
		
		employeeLoginMainPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};			
		employeeLoginMainPanel.setLayout(new BoxLayout(employeeLoginMainPanel, BoxLayout.Y_AXIS));
		employeeLoginMainPanel.setPreferredSize(fullWindow);			
		
		employeeLoginBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));		
		employeeLoginBottomPanel.setOpaque(false);
		
		
		employeeLoginTopPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));		
		employeeLoginTopPanel.setOpaque(false);
		
		employeeLoginMiddle.setLayout(new FlowLayout(FlowLayout.CENTER));
		employeeLoginMiddle.setOpaque(false);
		//employeeLoginMiddle.setBackground(Color.RED);
		
		employeeLoginMiddleInput.setLayout(new GridLayout(3, 2));		
		employeeLoginMiddleInput.setOpaque(false);
		
		
		employeeLoginInput1.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		employeeLoginInput1.setOpaque(false);
		
		employeeLoginInput2.setLayout(new FlowLayout(FlowLayout.LEFT));		
		employeeLoginInput2.setOpaque(false);
		
		employeeLoginInput3.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		employeeLoginInput3.setOpaque(false);
		
		employeeLoginInput4.setLayout(new FlowLayout(FlowLayout.LEFT));		
		employeeLoginInput4.setOpaque(false);
		
		employeeLoginInput5.setLayout(new FlowLayout(FlowLayout.CENTER));		
		employeeLoginInput5.setOpaque(false);
		
		employeeLoginBurn.setLayout(new FlowLayout(FlowLayout.CENTER));		
		employeeLoginBurn.setOpaque(false);	
		
		employeeLoginButton.setFont(new Font(null, Font.PLAIN, 26));
		
		//loginNewUser = new JButton("New User");
		//loginNewUser.setFont(new Font(null, Font.PLAIN, 24));
		
		employeeHeaderLabel = new JLabel("Enter Your employee ID and address", JLabel.CENTER);
		employeeHeaderLabel.setFont(new Font("Serif", Font.PLAIN, 55));
		employeeHeaderLabel.setForeground(Color.WHITE);
		
		employeeid = new JLabel("User ID: ");
		employeeid.setFont(new Font(null, Font.PLAIN, 22));
		employeeid.setForeground(Color.WHITE);
		
		employeePassword = new JLabel("Password:");
		employeePassword.setFont(new Font(null, Font.PLAIN, 22));	
		employeePassword.setForeground(Color.WHITE);		
		
		employeeText = new JTextField(10);
		employeeText.setFont(new Font(null, Font.PLAIN, 20));	
		
		employeePasswordText = new JPasswordField(10);
		employeePasswordText.setFont(new Font(null, Font.PLAIN, 20));	
		
		
		// Button Press Listener
		employeeLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				employeeIDtextField = employeeText.getText();
				employeePasswordTextField = employeePasswordText.getPassword();	
				employeeText.setText("");
				employeePasswordText.setText("");
				//getCartIDs();
				employeeValidation(employeeIDtextField, employeePasswordTextField);
			}
		});	
		
		// Mouse over listener
		employeeLoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	employeeLoginButton.setBackground(Color.GRAY);
		    	employeeLoginButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	employeeLoginButton.setBackground(UIManager.getColor("control"));
		    	employeeLoginButton.setForeground(Color.BLACK);
		    }
		});
		
		employeeLoginButton.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					employeeIDtextField = employeeText.getText();
					employeePasswordTextField = employeePasswordText.getPassword();	
					employeeText.setText("");
					employeePasswordText.setText("");
					//getCartIDs();
					employeeValidation(employeeIDtextField, employeePasswordTextField);
				}		      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
				
		// Key Press Listener
		employeePasswordText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Enter Key Listener
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					employeeIDtextField = employeeText.getText();
					employeePasswordTextField = employeePasswordText.getPassword();	
					employeeText.setText("");
					employeePasswordText.setText("");
					//getCartIDs();
					employeeValidation(employeeIDtextField, employeePasswordTextField);
				}		      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}
		});
		
		// Key Press Listener
		employeeText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Enter Key Listener
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					employeeIDtextField = employeeText.getText();
					employeePasswordTextField = employeePasswordText.getPassword();	
					employeeText.setText("");
					employeePasswordText.setText("");
					//getCartIDs();
					employeeValidation(employeeIDtextField, employeePasswordTextField);
				}			      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				 
				
			}
		});
				
			
		employeeLoginInput1.add(employeeid);
		employeeLoginInput2.add(employeeText);
		employeeLoginInput3.add(employeePassword);
		employeeLoginInput4.add(employeePasswordText);
		employeeLoginInput5.add(employeeLoginButton);	
		
		employeeLoginMiddleInput.add(employeeLoginInput1);
		employeeLoginMiddleInput.add(employeeLoginInput2);
		employeeLoginMiddleInput.add(employeeLoginInput3);
		employeeLoginMiddleInput.add(employeeLoginInput4);
		employeeLoginMiddleInput.add(employeeLoginBurn);
		employeeLoginMiddleInput.add(employeeLoginInput5);	
		
		employeeLoginTopPanel.add(employeeHeaderLabel);				
		
		employeeLoginMiddle.add(employeeLoginMiddleInput);
		
		//employeeLoginBottomPanel.add(loginNewUser);
		
		employeeLoginMainPanel.add(employeeLoginTopPanel);
		employeeLoginMainPanel.add(employeeLoginMiddle);
		employeeLoginMainPanel.add(employeeLoginBottomPanel);
				
		this.add(employeeLoginMainPanel);		
		this.pack();	
		this.setVisible(true);
	}
	
	
	/**
	 * Displays quit GUI
	 */

	
	public void quitSplashScreen() {
		
		quitButton = new JButton("Quit");
		
		quitMainPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};					
		quitMainPanel.setLayout(new FlowLayout(1, 75, 200));		
		quitMainPanel.setPreferredSize(fullWindow);		
		
		splashScreen = new JLabel("", goodbye, SwingConstants.CENTER);
		
		quitMainPanel.add(splashScreen);
		
		this.add(quitMainPanel);
		this.pack();
		this.setVisible(true);	
		int delay=2250;// wait for 2.25 seconds
		
		final JFrame n = this;		
		Timer timer = new Timer(delay, new AbstractAction() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
		    public void actionPerformed(ActionEvent ae) {
		        //action that you want performed 
		    	n.dispose();
		    }
		});
		timer.setRepeats(false);
		timer.start();	
	}
	
	/**
	 * Sets Book Search GUI
	 */
	public void bookSearch() {
				
		
		searchMainPanel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};		
		searchMainPanel.setLayout(new BoxLayout(searchMainPanel, BoxLayout.Y_AXIS));
		searchMainPanel.setPreferredSize(fullWindow);
			
		searchBooksFieldPanel.setLayout(new GridLayout(1,3));
		searchBooksFieldPanel.setOpaque(false);
		
		searchBooksTopPanel.setLayout(new BoxLayout(searchBooksTopPanel,BoxLayout.Y_AXIS));
		searchBooksTopPanel.setOpaque(false);
		
		searchBooksBottomPanel.setLayout(new BoxLayout(searchBooksBottomPanel,BoxLayout.Y_AXIS));
		searchBooksBottomPanel.setOpaque(false);
		
		searchBottomTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		searchBottomTop.setOpaque(false);
		
		searchBottomBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		searchBottomBottom.setOpaque(false);
		
		searchBooksTextMainPanel.setLayout(new BoxLayout(searchBooksTextMainPanel,BoxLayout.Y_AXIS));
		searchBooksTextMainPanel.setOpaque(false);
		
		searchTopTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		searchTopTop.setOpaque(false);
		
		searchTopBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		searchTopBottom.setOpaque(false);
		
		searchBooksMainPanel.setLayout(new GridLayout(5,2));		
		searchBooksMainPanel.setOpaque(false);
		
		searchBooksTablePanel.setLayout(new BorderLayout());	
		searchBooksTablePanel.setOpaque(false);
		searchBooksTablePanel.setPreferredSize(tableSize);			
		
		searchTitlePan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		searchTitlePan.setOpaque(false);
		
		searchAuthorPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		searchAuthorPan.setOpaque(false);
		
		searchISBNPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		searchISBNPan.setOpaque(false);
		
		searchCatPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
		searchCatPan.setOpaque(false);
		
		searchTitleTextPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchTitleTextPan.setOpaque(false);
		
		searchAuthorTextPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchAuthorTextPan.setOpaque(false);
		
		searchISBNTextPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchISBNTextPan.setOpaque(false);
		
		searchCatTextPan.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchCatTextPan.setOpaque(false);
		
		searchMiddleLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		searchMiddleLeft.setOpaque(false);
		
		searchMiddleRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
		searchMiddleRight.setOpaque(false);
		
		info = new JLabel("(Leave blank for all books)");	
		info.setFont(new Font("Serif", Font.PLAIN, 25));
		info.setForeground(Color.WHITE);
		
		searchBooks.setFont(new Font("Serif", Font.PLAIN, 50));
		titleLabel.setFont(new Font(null, Font.PLAIN, 22));
		authorLabel.setFont(new Font(null, Font.PLAIN, 22));
		isbnLabel.setFont(new Font(null, Font.PLAIN, 22));
		categoryLabel.setFont(new Font(null, Font.PLAIN, 22));
		
		searchButton.setFont(new Font(null, Font.PLAIN, 28));
		searchButton.setPreferredSize(new Dimension(150,50));
		
		userMenuButton.setFont(new Font(null, Font.PLAIN, 28));
		userMenuButton.setPreferredSize(new Dimension(300,50));
		
		title = new JTextField(15);
		title.setFont(new Font(null, Font.PLAIN, 20));
		
		author = new JTextField(15);
		author.setFont(new Font(null, Font.PLAIN, 20));
		
		isbn = new JTextField(15);
		isbn.setFont(new Font(null, Font.PLAIN, 20));
		
		category = new JTextField(15);
		category.setFont(new Font(null, Font.PLAIN, 20));
		
		searchBooks.setForeground(Color.WHITE);
		titleLabel.setForeground(Color.WHITE);
		authorLabel.setForeground(Color.WHITE);
		isbnLabel.setForeground(Color.WHITE);
		categoryLabel.setForeground(Color.WHITE);	
		
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
										
				titleInput = title.getText();
				authorInput = author.getText();
				isbnInput = isbn.getText();
				categoryInput = category.getText();
				title.setText("");
				author.setText("");
				isbn.setText("");
				category.setText("");
				searchBooksTablePanel.removeAll();
				bookValidation(titleInput, authorInput, isbnInput, categoryInput);
			}
		});		
		
		userMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				searchBooksTablePanel.removeAll();
				searchMainPanel.setVisible(false);
				
				
				userMainMenu.repaint();
				userMainMenu.setVisible(true);
				
			}
		});
		
		searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	searchButton.setBackground(Color.GRAY);
		    	searchButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	searchButton.setBackground(UIManager.getColor("control"));
		    	searchButton.setForeground(Color.BLACK);
		    }
		});	
		
		
		userMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	userMenuButton.setBackground(Color.GRAY);
		    	userMenuButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	userMenuButton.setBackground(UIManager.getColor("control"));
		    	userMenuButton.setForeground(Color.BLACK);
		    }
		});
		
		searchBottomTop.add(searchBooksTablePanel);
		searchBottomBottom.add(userMenuButton);
		
		searchBooksBottomPanel.add(searchBottomTop);
		searchBooksBottomPanel.add(searchBottomBottom);
		
		searchTopTop.add(searchBooks);
		searchTopBottom.add(info);
		
		searchBooksTextMainPanel.add(searchTopTop);
		searchBooksTextMainPanel.add(searchTopBottom);		
		
		searchTitlePan.add(titleLabel);
		searchTitleTextPan.add(title);
		
		searchAuthorPan.add(authorLabel);
		searchAuthorTextPan.add(author);
		
		searchISBNPan.add(isbnLabel);
		searchISBNTextPan.add(isbn);
		
		searchCatPan.add(categoryLabel);
		searchCatTextPan.add(category);		
		
		searchBooksMainPanel.add(searchTitlePan);
		searchBooksMainPanel.add(searchTitleTextPan);
		searchBooksMainPanel.add(searchAuthorPan);
		searchBooksMainPanel.add(searchAuthorTextPan);
		searchBooksMainPanel.add(searchISBNPan);
		searchBooksMainPanel.add(searchISBNTextPan);
		searchBooksMainPanel.add(searchCatPan);		
		searchBooksMainPanel.add(searchCatTextPan);		
		
		searchMiddleRight.add(searchButton);
		
		searchBooksFieldPanel.add(searchMiddleLeft);
		searchBooksFieldPanel.add(searchBooksMainPanel);
		searchBooksFieldPanel.add(searchMiddleRight);
		
		searchBooksTopPanel.add(searchBooksTextMainPanel);
		searchBooksTopPanel.add(searchBooksFieldPanel);
						
		searchMainPanel.add(searchBooksTopPanel);
		searchMainPanel.add(searchBooksBottomPanel);	
		
		this.add(searchMainPanel);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	/**
	 * Runs the sql statements for searching for a book
	 * 
	 * @param String - The title
	 * @param String - The author
	 * @param String - The ISBN
	 * @param String - The category
	 */
	public void bookValidation(String title, String author, String isbn, String category) {
			
		conn = null;
		stmt = null;
		stmt2 = null;
		conn2= null;
		rs = null;
		rs2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();	
			stmt2 = conn.createStatement();	
				
			sql = "SELECT * FROM books WHERE Title = '" + title + "' or Author = '" + author + "' or ISBN = '" + isbn + "' or Category = '" + category + "'";	      		
		      
			rs = stmt.executeQuery(sql);
			rs2 = stmt2.executeQuery(sql);
			
			if (title.equals("") && author.equals("") && isbn.equals("") && category.equals("")) {
				sql = "SELECT * FROM books";
				rs = stmt.executeQuery(sql);				
				
				table = new JTable(buildTableModel(rs));
				JScrollPane tableContainer = new JScrollPane(table);
				tableContainer.setPreferredSize(new Dimension(searchBooksTablePanel.getWidth(), searchBooksTablePanel.getHeight()));
				searchBooksTablePanel.add(tableContainer);
				this.setVisible(true);
			}
			else {
				if (rs.next()) {	
					//searchBooksTablePanel.removeAll();			
					table = new JTable(buildTableModel(rs2));
					JScrollPane tableContainer = new JScrollPane(table);
					searchBooksTablePanel.add(tableContainer);
					//searchBooksTablePanel.setVisible(true);
					this.setVisible(true);				
				}
				else {	
					JOptionPane.showMessageDialog(null, "No Books Match Those Results", "Error", JOptionPane.ERROR_MESSAGE);
					
					
				}
			}
				
		
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
	}
	
	
	/**
	 * Accepts a ResultSet object and returns a GUI table representation of the returned table
	 * 
	 * @param ResultSet - after executing a sql statement
	 * @return DefaultTableModel - the GUI representation of the table
	 * @throws SQLException
	 */
	public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();	   
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	       
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);
	}
	
	

	/**
	 * Creates the create a cart GUI
	 */
	public void createShoppingCart() {	
				
		Dimension successSize = new Dimension((int)(this.getWidth()/3.5), (int)(this.getHeight()/6));
		
		getCartIDs();
		
		createAcartMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};			
		createAcartMain.setLayout(new BoxLayout(createAcartMain, BoxLayout.Y_AXIS));
		createAcartMain.setPreferredSize(fullWindow);
		
		createAcartTop.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 65));			
		createAcartTop.setOpaque(false);
		
		createAcartMiddle.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));	
		createAcartMiddle.setOpaque(false);
		
		createAcartRealBottom.setLayout(new BoxLayout(createAcartRealBottom, BoxLayout.X_AXIS));		
		createAcartRealBottom.setOpaque(false);
		
		createAcartBottom.setLayout(new GridLayout(2, 1));
		createAcartBottom.setOpaque(false);
		
		createAcartInfo.setLayout(new BoxLayout(createAcartInfo, BoxLayout.X_AXIS));
		createAcartInfo.setOpaque(false);
		
		createCartInputMid.setLayout(new GridLayout(2, 2));
		createCartInputMid.setOpaque(false);
		
		infoRight.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 80));	
		infoRight.setOpaque(false);
		
		succesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));		
		succesPanel.setPreferredSize(successSize);
		succesPanel.setOpaque(false);
		
		createInput1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		createInput1.setOpaque(false);
		
		createInput2.setLayout(new FlowLayout(FlowLayout.LEFT));
		createInput2.setOpaque(false);
		
		createInput3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		createInput3.setOpaque(false);
		
		createInput4.setLayout(new FlowLayout(FlowLayout.LEFT));		
		createInput4.setOpaque(false);
		
		createSuccessPan.setLayout(new GridLayout(2, 1));
		createSuccessPan.setOpaque(false);
		
		createCartNum.setLayout(new FlowLayout(FlowLayout.CENTER));
		createCartNum.setOpaque(false);
		
		createBottomBurn.setLayout(new FlowLayout(FlowLayout.CENTER));
		createBottomBurn.setOpaque(false);
			
		createBottomBurnLeft.setLayout(new FlowLayout(FlowLayout.CENTER));
		createBottomBurnLeft.setOpaque(false);
				
		createBackButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		createBackButtonPanel.setOpaque(false);
		
		
		
		createTitle = new JLabel("Create A Cart");
		createTitle.setFont(new Font("Serif", Font.PLAIN, 60));
		createTitle.setForeground(Color.WHITE);	
		
		cartName = new JLabel("Cart Name:");
		cartName.setFont(new Font(null, Font.PLAIN, 22));
		cartName.setForeground(Color.WHITE);	
		
		cartNameText = new JTextField(10);
		cartNameText.setFont(new Font(null, Font.PLAIN, 20));
		
		createSuccess = new JLabel("");
		createSuccess.setFont(new Font("Serif", Font.ITALIC, 40));
		createSuccess.setForeground(Color.WHITE);	
		
		createCartNumLabel = new JLabel("");
		createCartNumLabel.setFont(new Font("Serif", Font.ITALIC, 20));
		createCartNumLabel.setForeground(Color.WHITE);	
		
		createCartButton = new JButton("Create Cart");
		createCartButton.setFont(new Font(null, Font.PLAIN, 22));		
		
		backButton = new JButton("Back to Menu");	
		backButton.setFont(new Font(null, Font.PLAIN, 22));		
	
		
		createCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createSuccess.setText("Cart Created!");				
				succesPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				createKart = cartNameText.getText();
				cartNameText.setText("");
				makeAcartSQL(createKart);
				getCartIDs();
			}
		});
		
		createCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	createCartButton.setBackground(Color.GRAY);
		    	createCartButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	createCartButton.setBackground(UIManager.getColor("control"));
		    	createCartButton.setForeground(Color.BLACK);
		    }
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				succesPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 0));
				createSuccess.setText("");
				createCartNumLabel.setText("");
				createAcartMain.setVisible(false);
				
				getCartIDs();
				
				userMainMenu.setVisible(true);
				userMainMenu.repaint();								
			}
		});
		
		backButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	backButton.setBackground(Color.GRAY);
		    	backButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	backButton.setBackground(UIManager.getColor("control"));
		    	backButton.setForeground(Color.BLACK);
		    }
		});
		
		
		createAcartTop.add(createTitle);	
		
		createSuccessPan.add(createSuccess);		
		
		createCartNum.add(createCartNumLabel);
		
		succesPanel.add(createSuccessPan);
		succesPanel.add(createCartNum);
		
		createAcartBottom.add(succesPanel);	
		createAcartBottom.add(createBackButtonPanel);
		
		createInput1.add(cartName);
		createInput2.add(cartNameText);
		createInput4.add(createCartButton);
		
		createCartInputMid.add(createInput1);
		createCartInputMid.add(createInput2);
		createCartInputMid.add(createInput3);
		createCartInputMid.add(createInput4);
		
		createBackButtonPanel.add(backButton);		
		
		createAcartRealBottom.add(createBottomBurnLeft);
		createAcartRealBottom.add(createAcartBottom);
		createAcartRealBottom.add(createBottomBurn);
		
		createAcartMiddle.add(createCartInputMid);		
		
		createAcartMain.add(createAcartTop);
		createAcartMain.add(createAcartMiddle);
		createAcartMain.add(createAcartRealBottom);	
		
		
		
		
		this.add(createAcartMain);
		this.pack();
	}
	
	
	/**
	 * Runs the create a cart sql script
	 * 
	 * @param String - The cart name from the text field
	 */
	public void makeAcartSQL(String cartName) {
		
		conn = null;
		conn2 = null;
		stmt = null;	
		stmt2 = null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);	
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);		
			stmt = conn.createStatement();	
			stmt2 = conn2.createStatement();		
				
			sql = "CALL createCart('" + customerID + "', '" + cartName + "')"; 	
		      
			update = stmt.executeUpdate(sql);
			
			sql2 = "SELECT CartID FROM cart WHERE CartName = '" + cartName + "'";
			
			rs = stmt2.executeQuery(sql2);
			
			if(rs.next()) {
				cartID = rs.getString("CartID") + " ";
				//globalCartIDs += cartID;
				createCartNumLabel.setText("Cart ID: " + cartID);				
			}			
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}		
	}
	
	
	/**
	 * Adds books to the shopping cart
	 */
	public void addToCart() {
		
		getCartIDs();
		
		availableCarts.setText(globalCartIDs);
		
		addToCartMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};
		addToCartMain.setLayout(new BoxLayout(addToCartMain, BoxLayout.Y_AXIS));
		addToCartMain.setPreferredSize(fullWindow);		
		
		addToCartTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		addToCartTop.setOpaque(false);	
		
		addToCartMiddleTop.setLayout(new GridLayout(1, 3));
		addToCartMiddleTop.setOpaque(false);		
		
		addToCartBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		addToCartBottom.setOpaque(false);			
		
		addToCartMiddleBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		addToCartMiddleBottom.setOpaque(false);
		
		adToCartSuccessPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		adToCartSuccessPan.setOpaque(false);
		adToCartSuccessPan.setPreferredSize(new Dimension( (int)(this.getWidth() / 3.5), (int)(this.getHeight() / 8) ));
		
		addToCartMiddleTopRightBottom.setLayout(new FlowLayout(FlowLayout.CENTER));		
		addToCartMiddleTopRightBottom.setOpaque(false);
		
		addToCartMiddleAddButpan.setLayout(new BoxLayout(addToCartMiddleAddButpan, BoxLayout.Y_AXIS));
		addToCartMiddleAddButpan.setOpaque(false);
		
		addToCartMiddleTopBurn1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		addToCartMiddleTopBurn1.setOpaque(false);
		
		addToCartMiddleTopMainGrid.setLayout(new GridLayout(3, 2));
		addToCartMiddleTopMainGrid.setOpaque(false);
		
		addToCartMiddleTopAddButtPan.setLayout(new FlowLayout(FlowLayout.CENTER));
		addToCartMiddleTopAddButtPan.setOpaque(false);
		
		addToCartMiddleBottomCartspan.setLayout(new FlowLayout(FlowLayout.CENTER));
		addToCartMiddleBottomCartspan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
		addToCartMiddleBottomCartspan.setOpaque(false);		
		
		addToCartMiddleTopMiddle.setLayout(new FlowLayout(FlowLayout.CENTER));
		addToCartMiddleTopMiddle.setOpaque(false);		
		
		addToInput1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		addToInput1.setOpaque(false);
		
		addToInput2.setLayout(new FlowLayout(FlowLayout.LEFT));
		addToInput2.setOpaque(false);
		
		addToInput3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		addToInput3.setOpaque(false);
		
		addToInput4.setLayout(new FlowLayout(FlowLayout.LEFT));
		addToInput4.setOpaque(false);
		
		addToInput5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		addToInput5.setOpaque(false);
		
		addToInput6.setLayout(new FlowLayout(FlowLayout.LEFT));
		addToInput6.setOpaque(false);
		
		
		
		
		addToCartTitle = new JLabel("Add To Cart");
		addToCartTitle.setFont(new Font("Serif", Font.PLAIN, 70));
		addToCartTitle.setForeground(Color.WHITE);
		
		addToCartISBN = new JLabel("ISBN:");
		addToCartISBN.setFont(new Font(null, Font.PLAIN, 22));
		addToCartISBN.setForeground(Color.WHITE);
		
		addToCartQuant = new JLabel("Quantity:");
		addToCartQuant.setFont(new Font(null, Font.PLAIN, 22));
		addToCartQuant.setForeground(Color.WHITE);
		
		addToCartCartID = new JLabel("Cart ID:");
		addToCartCartID.setFont(new Font(null, Font.PLAIN, 22));
		addToCartCartID.setForeground(Color.WHITE);
		
		yourCartsTitle = new JLabel("Your Carts:");
		yourCartsTitle.setFont(new Font("Serif", Font.PLAIN, 30));
		yourCartsTitle.setForeground(Color.WHITE);
				
		availableCarts.setFont(new Font("Serif", Font.PLAIN, 24));
		availableCarts.setForeground(Color.WHITE);
		
		addToCartSuccess = new JLabel("");
		addToCartSuccess.setForeground(Color.WHITE);
		addToCartSuccess.setFont(new Font("Serif", Font.PLAIN, 40));
		
		addISBN = new JTextField(10);
		addISBN.setFont(new Font(null, Font.PLAIN, 20));
		
		addQuant = new JTextField(10);
		addQuant.setFont(new Font(null, Font.PLAIN, 20));
		
		addCartID = new JTextField(10);
		addCartID.setFont(new Font(null, Font.PLAIN, 20));
		
		addToCartButton = new JButton("Add");
		addToCartButton.setFont(new Font(null, Font.PLAIN, 24));		
		
		addToCartBackButton = new JButton("Back To Menu");
		addToCartBackButton.setFont(new Font(null, Font.PLAIN, 24));		
		
		
		
		addToCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
				isbnInput = addISBN.getText();
				quantInput = addQuant.getText();
				cartIdInput = addCartID.getText();
				
				addISBN.setText("");				
				addQuant.setText("");					
				addCartID.setText("");	
					
				runAddToCartSQL(isbnInput, quantInput, cartIdInput);
				
							
			}
		});
		
		addToCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	addToCartButton.setBackground(Color.GRAY);
		    	addToCartButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	addToCartButton.setBackground(UIManager.getColor("control"));
		    	addToCartButton.setForeground(Color.BLACK);
		    }
		});
		
		addToCartBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				
				addISBN.setText("");				
				addQuant.setText("");					
				addCartID.setText("");	
				
				addToCartSuccess.setText("");
				adToCartSuccessPan.setBorder(BorderFactory.createLineBorder(panelColor, 0));
				
				availableCarts.setText(globalCartIDs);
				
				addToCartMain.setVisible(false);
				
				userMainMenu.setVisible(true);
				userMainMenu.repaint();
				
			}
		});
		
		addToCartBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	addToCartBackButton.setBackground(Color.GRAY);
		    	addToCartBackButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	addToCartBackButton.setBackground(UIManager.getColor("control"));
		    	addToCartBackButton.setForeground(Color.BLACK);
		    }
		});		
		
		
		addToInput1.add(addToCartISBN);
		addToInput2.add(addISBN);
		addToInput3.add(addToCartQuant);
		addToInput4.add(addQuant);
		addToInput5.add(addToCartCartID);
		addToInput6.add(addCartID);
		
		addToCartMiddleTopMainGrid.add(addToInput1);
		addToCartMiddleTopMainGrid.add(addToInput2);
		addToCartMiddleTopMainGrid.add(addToInput3);
		addToCartMiddleTopMainGrid.add(addToInput4);
		addToCartMiddleTopMainGrid.add(addToInput5);
		addToCartMiddleTopMainGrid.add(addToInput6);		
		
		addToCartMiddleTopMiddle.add(addToCartMiddleTopMainGrid);
		
		adToCartSuccessPan.add(addToCartSuccess);
		
		addToCartMiddleTopRightBottom.add(adToCartSuccessPan);
		
		addToCartMiddleAddButpan.add(addToCartMiddleTopAddButtPan);
		addToCartMiddleAddButpan.add(addToCartMiddleTopRightBottom);
		
		addToCartMiddleTopAddButtPan.add(addToCartButton);	
		
		addToCartMiddleTop.add(addToCartMiddleTopBurn1);
		addToCartMiddleTop.add(addToCartMiddleTopMiddle);
		addToCartMiddleTop.add(addToCartMiddleAddButpan);
		
		addToCartTop.add(addToCartTitle);
		
		addToCartMiddleBottomCartspan.add(yourCartsTitle);
		addToCartMiddleBottomCartspan.add(availableCarts);
		
		addToCartMiddleBottom.add(addToCartMiddleBottomCartspan);
		
		addToCartBottom.add(addToCartBackButton);
		
		addToCartMain.add(addToCartTop);
		addToCartMain.add(addToCartMiddleTop);
		addToCartMain.add(addToCartMiddleBottom);
		addToCartMain.add(addToCartBottom);
		
		this.add(addToCartMain);
		this.pack();
	}
	
	public void runAddToCartSQL(String isbnInput, String quantInput, String cartIdInput) {
		
		conn = null;
		conn2 = null;
		conn3 = null;
		stmt = null;	
		stmt2 = null;
		stmt3 = null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);	
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn3 = DriverManager.getConnection(DB_URL,USER,PASS);		
			
			stmt = conn.createStatement();	
			stmt2 = conn2.createStatement();		
			stmt3 = conn3.createStatement();		
						
			
			sql = "CALL addToCart ('" + cartIdInput + "', '" + isbnInput + "', '" + quantInput + "')"; //add the columns to the cart. Executed after input validation
		
			sql2 = "SELECT * from books WHERE isbn = '" + isbnInput + "'";
			sql3 = "SELECT * from cart WHERE cartid = '" + cartIdInput + "'";
			sql4 = "SELECT * FROM cart WHERE cartid = '" + cartIdInput + "' and customerid = '" + customerID + "'";
			
			rs = stmt.executeQuery(sql2);
			rs2 = stmt2.executeQuery(sql3);
			rs3 = stmt3.executeQuery(sql4);						
			
			
			if (cartIdInput.equals("") && isbnInput.equals("") && quantInput.equals("")) {
				addToCartSuccess.setText("No Info Recieved");
				adToCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
			}
			else {
				//input validation
				if (!(rs.next())) {
					addToCartSuccess.setText("Book does not exist");
					adToCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				}
				else if (!(rs2.next())) {
					addToCartSuccess.setText("Cart does not exist");			
					adToCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				}
				else if (!(rs3.next())) {
					addToCartSuccess.setText("Not your cart");	
					adToCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));			
				}
				else {
					addToCartSuccess.setText("Added To Cart!");	
					adToCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
					update = stmt.executeUpdate(sql);
				}
			}
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}	
	}
	
	public void changePassword() {
		
		//userMainMenu.setVisible(false);	
		
		passwordStatus= new JLabel("");
		changePassword = new JLabel("Change User Password");
		currentPassword = new JLabel("Current Password:");
		newPassword = new JLabel("New Password:");
		final JTextField currentPasswordText = new JTextField(10);
		final JTextField newPasswordText = new JTextField(10);
		changePasswordSubmitButton = new JButton("Submit");
		changePasswordBackButton = new JButton("Back to Menu");
		
		
		changePasswordMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};		
		changePasswordMain.setLayout(new BoxLayout(changePasswordMain, BoxLayout.Y_AXIS));
		changePasswordMain.setPreferredSize(fullWindow);
				
		changePasswordTop.setLayout(new FlowLayout(FlowLayout.CENTER, 0,10));
		changePasswordTop.setOpaque(false);
		
		changePasswordMidTop.setLayout(new FlowLayout(FlowLayout.CENTER));		
		changePasswordMidTop.setOpaque(false);
		
		changePasswordMidBot.setLayout(new FlowLayout(FlowLayout.CENTER));
		changePasswordMidBot.setOpaque(false);
		
		changePasswordMidTopCenter.setLayout(new GridLayout(3, 2));	
		changePasswordMidTopCenter.setOpaque(false);		
		
		changeMiddleBottomText.setLayout(new FlowLayout(FlowLayout.CENTER, 0 ,20));
		changeMiddleBottomText.setOpaque(false);
		changeMiddleBottomText.setPreferredSize(new Dimension( (int)(this.getWidth() / 3), (int)(this.getHeight() / 10) ));
		
		changeMiddleBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		changeMiddleBottom.setOpaque(false);
		
		changeInput1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		changeInput1.setOpaque(false);
		
		changeInput2.setLayout(new FlowLayout(FlowLayout.LEFT));
		changeInput2.setOpaque(false);
		
		changeInput3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		changeInput3.setOpaque(false);
		
		changeInput4.setLayout(new FlowLayout(FlowLayout.LEFT));
		changeInput4.setOpaque(false);
		
		changeInput5.setLayout(new FlowLayout(FlowLayout.CENTER));
		changeInput5.setOpaque(false);
		
		changeInput6.setLayout(new FlowLayout(FlowLayout.CENTER));
		changeInput6.setOpaque(false);
		
		changePassword.setFont(new Font("Serif", Font.PLAIN, 50));
		currentPassword.setFont(new Font(null, Font.PLAIN, 22));
		newPassword.setFont(new Font(null, Font.PLAIN, 22));
		passwordStatus.setFont(new Font(null, Font.PLAIN, 30));
		passwordStatus.setForeground(Color.WHITE);
		
		currentPasswordText.setFont(new Font(null, Font.PLAIN, 20));
		newPasswordText.setFont(new Font(null, Font.PLAIN, 20));
		
		changePasswordSubmitButton.setFont(new Font(null, Font.PLAIN, 28));		
		
		changePasswordBackButton.setFont(new Font(null, Font.PLAIN, 28));
		
		changePassword.setForeground(Color.WHITE);
		currentPassword.setForeground(Color.WHITE);
		newPassword.setForeground(Color.WHITE);
		
		changePasswordTop.add(changePassword);
		
		changeInput1.add(currentPassword);
		changeInput2.add(currentPasswordText);
		changeInput3.add(newPassword);
		changeInput4.add(newPasswordText);
		changeInput6.add(changePasswordSubmitButton);
		
		changePasswordMidTopCenter.add(changeInput1);
		changePasswordMidTopCenter.add(changeInput2);
		changePasswordMidTopCenter.add(changeInput3);
		changePasswordMidTopCenter.add(changeInput4);
		changePasswordMidTopCenter.add(changeInput5);
		changePasswordMidTopCenter.add(changeInput6);
		
		changeMiddleBottomText.add(passwordStatus);
		
		searchBooksFieldPanel.add(searchBooksMainPanel);
		
		searchBooksTopPanel.add(searchBooksTextMainPanel, BorderLayout.NORTH);
		
		changePasswordMidTop.add(changePasswordMidTopCenter);		
		
		changePasswordMidBot.add(changePasswordBackButton);
		
		changeMiddleBottom.add(changeMiddleBottomText);
		
		changePasswordMain.add(changePasswordTop);
		changePasswordMain.add(changePasswordMidTop);
		changePasswordMain.add(changeMiddleBottom);
		changePasswordMain.add(changePasswordMidBot);	
		
		changePasswordSubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
										
				changePasswordSQL(customerID, currentPasswordText.getText(), newPasswordText.getText());

				currentPasswordText.setText("");
				newPasswordText.setText("");
				changeMiddleBottomText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				
			}
		});		
		
		changePasswordBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				
				changePasswordMain.setVisible(false);
				currentPasswordText.setText("");
				newPasswordText.setText("");
				passwordStatus.setText("");
				userMainMenu.repaint();
				userMainMenu.setVisible(true);
				changeMiddleBottomText.setBorder(BorderFactory.createLineBorder(panelColor, 0));
				
				
			}
		});
		
		changePasswordSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	changePasswordSubmitButton.setBackground(Color.GRAY);
		    	changePasswordSubmitButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	changePasswordSubmitButton.setBackground(UIManager.getColor("control"));
		    	changePasswordSubmitButton.setForeground(Color.BLACK);
		    }
		});	
		
		
		changePasswordBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	changePasswordBackButton.setBackground(Color.GRAY);
		    	changePasswordBackButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	changePasswordBackButton.setBackground(UIManager.getColor("control"));
		    	changePasswordBackButton.setForeground(Color.BLACK);
		    }
		});
		
		this.add(changePasswordMain);
		this.pack();
		this.setVisible(true);
		

	}
	
	public void changePasswordSQL (String cid, String currentPass, String newPass) {
		
		conn = null;
		stmt = null;
		stmt2 = null;
		conn2= null;
		rs = null;
		rs2 = null;
		String passCheck = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();	
			stmt2 = conn.createStatement();	
				
			sql = "CALL UpdatePassword ('" + cid + "', '" + currentPass + "', '" + newPass + "')";			
			
			update = stmt.executeUpdate(sql);
			
			sql = "SELECT cuspassword FROM customer WHERE customerid =" + cid;
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				passCheck = rs.getString("cuspassword");
				
			}
			
			if (passCheck.equals(newPass)) {
				passwordStatus.setText("Password Changed!");		
			}
			else {
				passwordStatus.setText("Failure: Password incorrect");
			}	
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
	
	}
	

	public void deleteFromCart() {
		
		getCartIDs();
		
		deleteFromAvailableCarts.setText(globalCartIDs);
		
		deleteFromCartMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};
		deleteFromCartMain.setLayout(new BoxLayout(deleteFromCartMain, BoxLayout.Y_AXIS));
		deleteFromCartMain.setPreferredSize(fullWindow);		
		
		deleteFromCartTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		deleteFromCartTop.setOpaque(false);	
		
		deleteFromCartMiddleTop.setLayout(new GridLayout(1, 3));
		deleteFromCartMiddleTop.setOpaque(false);		
		
		deleteFromCartBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		deleteFromCartBottom.setOpaque(false);			
		
		deleteFromCartMiddleBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		deleteFromCartMiddleBottom.setOpaque(false);
		
		deleteFromCartSuccessPan.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		deleteFromCartSuccessPan.setOpaque(false);
		deleteFromCartSuccessPan.setPreferredSize(new Dimension( (int)(this.getWidth() / 3.5), (int)(this.getHeight() / 8) ));
		
		deleteFromCartMiddleTopRightBottom.setLayout(new FlowLayout(FlowLayout.CENTER));		
		deleteFromCartMiddleTopRightBottom.setOpaque(false);
		
		deleteFromCartMiddleAddButpan.setLayout(new BoxLayout(deleteFromCartMiddleAddButpan, BoxLayout.Y_AXIS));
		deleteFromCartMiddleAddButpan.setOpaque(false);
		
		deleteFromCartMiddleTopBurn1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
		deleteFromCartMiddleTopBurn1.setOpaque(false);
		
		deleteFromCartMiddleTopMainGrid.setLayout(new GridLayout(3, 2));
		deleteFromCartMiddleTopMainGrid.setOpaque(false);
		
		deleteFromCartMiddleTopAddButtPan.setLayout(new FlowLayout(FlowLayout.CENTER));
		deleteFromCartMiddleTopAddButtPan.setOpaque(false);
		
		deleteFromCartMiddleBottomCartspan.setLayout(new FlowLayout(FlowLayout.CENTER));
		deleteFromCartMiddleBottomCartspan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
		deleteFromCartMiddleBottomCartspan.setOpaque(false);		
		
		deleteFromCartMiddleTopMiddle.setLayout(new FlowLayout(FlowLayout.CENTER));
		deleteFromCartMiddleTopMiddle.setOpaque(false);		
		
		deleteFromInput1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		deleteFromInput1.setOpaque(false);
		
		deleteFromInput2.setLayout(new FlowLayout(FlowLayout.LEFT));
		deleteFromInput2.setOpaque(false);
		
		deleteFromInput3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		deleteFromInput3.setOpaque(false);
		
		deleteFromInput4.setLayout(new FlowLayout(FlowLayout.LEFT));
		deleteFromInput4.setOpaque(false);
		
		deleteFromInput5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		deleteFromInput5.setOpaque(false);
		
		deleteFromInput6.setLayout(new FlowLayout(FlowLayout.LEFT));
		deleteFromInput6.setOpaque(false);
		
		
		
		
		deleteFromTitle = new JLabel("Delete From Cart");
		deleteFromTitle.setFont(new Font("Serif", Font.PLAIN, 70));
		deleteFromTitle.setForeground(Color.WHITE);
		
		deleteFromISBN = new JLabel("ISBN:");
		deleteFromISBN.setFont(new Font(null, Font.PLAIN, 22));
		deleteFromISBN.setForeground(Color.WHITE);
		
		deleteFromQuant = new JLabel("Quantity:");
		deleteFromQuant.setFont(new Font(null, Font.PLAIN, 22));
		deleteFromQuant.setForeground(Color.WHITE);
		
		deleteFromCartID = new JLabel("Cart ID:");
		deleteFromCartID.setFont(new Font(null, Font.PLAIN, 22));
		deleteFromCartID.setForeground(Color.WHITE);
		
		deleteFromYourTitle = new JLabel("Your Carts:");
		deleteFromYourTitle.setFont(new Font("Serif", Font.PLAIN, 30));
		deleteFromYourTitle.setForeground(Color.WHITE);
				
		deleteFromAvailableCarts.setFont(new Font("Serif", Font.PLAIN, 24));
		deleteFromAvailableCarts.setForeground(Color.WHITE);
		
		deleteFromCartSuccess = new JLabel("");
		deleteFromCartSuccess.setForeground(Color.WHITE);
		deleteFromCartSuccess.setFont(new Font("Serif", Font.PLAIN, 40));
		
	
		
		deleteFromIsbnInput = new JTextField(10);
		deleteFromIsbnInput.setFont(new Font(null, Font.PLAIN, 20));
		
		deleteFromQuantInput = new JTextField(10);
		deleteFromQuantInput.setFont(new Font(null, Font.PLAIN, 20));
		
		deleteFromCartIDInput = new JTextField(10);
		deleteFromCartIDInput.setFont(new Font(null, Font.PLAIN, 20));
		
		deleteFromCartButton = new JButton("Remove");
		deleteFromCartButton.setFont(new Font(null, Font.PLAIN, 24));		
		
		deleteFromCartBackButton = new JButton("Back To Menu");
		deleteFromCartBackButton.setFont(new Font(null, Font.PLAIN, 24));		
		
		
		
		deleteFromCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
				deleteUserIsbn = deleteFromIsbnInput.getText();
				deleteUserQuant = deleteFromQuantInput.getText();
				deleteUserCartId = deleteFromCartIDInput.getText();
				
				deleteFromIsbnInput.setText("");				
				deleteFromQuantInput.setText("");					
				deleteFromCartIDInput.setText("");	
					
				runDeleteFromCartSQL(deleteUserIsbn, deleteUserQuant, deleteUserCartId);
				
							
			}
		});
		
		deleteFromCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	deleteFromCartButton.setBackground(Color.GRAY);
		    	deleteFromCartButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	deleteFromCartButton.setBackground(UIManager.getColor("control"));
		    	deleteFromCartButton.setForeground(Color.BLACK);
		    }
		});
		
		deleteFromCartBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				
				deleteFromIsbnInput.setText("");				
				deleteFromQuantInput.setText("");					
				deleteFromCartIDInput.setText("");	
				
				deleteFromCartSuccess.setText("");
				deleteFromCartSuccessPan.setBorder(BorderFactory.createLineBorder(panelColor, 0));
				
				deleteFromAvailableCarts.setText(globalCartIDs);
				
				deleteFromCartMain.setVisible(false);
				
				userMainMenu.setVisible(true);
				userMainMenu.repaint();
				
			}
		});
		
		deleteFromCartBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	deleteFromCartBackButton.setBackground(Color.GRAY);
		    	deleteFromCartBackButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	deleteFromCartBackButton.setBackground(UIManager.getColor("control"));
		    	deleteFromCartBackButton.setForeground(Color.BLACK);
		    }
		});		
		
		
		deleteFromInput1.add(deleteFromISBN);
		deleteFromInput2.add(deleteFromIsbnInput);
		deleteFromInput3.add(deleteFromQuant);
		deleteFromInput4.add(deleteFromQuantInput);
		deleteFromInput5.add(deleteFromCartID);
		deleteFromInput6.add(deleteFromCartIDInput);
		
		deleteFromCartMiddleTopMainGrid.add(deleteFromInput1);
		deleteFromCartMiddleTopMainGrid.add(deleteFromInput2);
		deleteFromCartMiddleTopMainGrid.add(deleteFromInput3);
		deleteFromCartMiddleTopMainGrid.add(deleteFromInput4);
		deleteFromCartMiddleTopMainGrid.add(deleteFromInput5);
		deleteFromCartMiddleTopMainGrid.add(deleteFromInput6);		
		
		deleteFromCartMiddleTopMiddle.add(deleteFromCartMiddleTopMainGrid);
		
		deleteFromCartSuccessPan.add(deleteFromCartSuccess);
		
		deleteFromCartMiddleTopRightBottom.add(deleteFromCartSuccessPan);
		
		deleteFromCartMiddleAddButpan.add(deleteFromCartMiddleTopAddButtPan);
		deleteFromCartMiddleAddButpan.add(deleteFromCartMiddleTopRightBottom);
		
		deleteFromCartMiddleTopAddButtPan.add(deleteFromCartButton);	
		
		deleteFromCartMiddleTop.add(deleteFromCartMiddleTopBurn1);
		deleteFromCartMiddleTop.add(deleteFromCartMiddleTopMiddle);
		deleteFromCartMiddleTop.add(deleteFromCartMiddleAddButpan);
		
		deleteFromCartTop.add(deleteFromTitle);
		
		deleteFromCartMiddleBottomCartspan.add(deleteFromYourTitle);
		deleteFromCartMiddleBottomCartspan.add(deleteFromAvailableCarts);
		
		deleteFromCartMiddleBottom.add(deleteFromCartMiddleBottomCartspan);
		
		deleteFromCartBottom.add(deleteFromCartBackButton);
		
		deleteFromCartMain.add(deleteFromCartTop);
		deleteFromCartMain.add(deleteFromCartMiddleTop);
		deleteFromCartMain.add(deleteFromCartMiddleBottom);
		deleteFromCartMain.add(deleteFromCartBottom);
		
		this.add(deleteFromCartMain);
		this.pack();
		
	}
	
	public void runDeleteFromCartSQL(String isbnInput, String quantInput, String cartIdInput) {
		
		conn = null;
		conn2 = null;
		conn3 = null;
		conn4 = null;
		conn5 = null;
		stmt = null;	
		stmt2 = null;
		stmt3 = null;
		stmt4 = null;
		stmt5 = null;
		double current = 0;
		double quantNum = 0;
		
		if (!(quantInput.equals("")))
			quantNum = Double.parseDouble(quantInput);		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);	
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn3 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn4 = DriverManager.getConnection(DB_URL,USER,PASS);	
			conn5 = DriverManager.getConnection(DB_URL,USER,PASS);	
			
			stmt = conn.createStatement();	
			stmt2 = conn2.createStatement();		
			stmt3 = conn3.createStatement();		
			stmt4 = conn4.createStatement();	
			stmt5 = conn5.createStatement();	
			
			
			sql = "CALL deletefromcart ('" + cartIdInput + "', '" + isbnInput + "')"; 
		
			sql2 = "SELECT * from books WHERE isbn = '" + isbnInput + "'";
			sql3 = "SELECT * from cart WHERE cartid = '" + cartIdInput + "'";
			sql4 = "SELECT * FROM cart WHERE cartid = '" + cartIdInput + "' and customerid = '" + customerID + "'";
			
			sql5 = "SELECT quantity from booksincart where cartid = '" + cartIdInput + "' and isbn = '" + isbnInput + "'";
			
			sql6 = "SELECT * FROM booksincart where isbn = '" + isbnInput + "' and cartID = '" + cartIdInput + "'";
			
			rs = stmt.executeQuery(sql2);
			rs2 = stmt2.executeQuery(sql3);
			rs3 = stmt3.executeQuery(sql4);						
			rs4 = stmt4.executeQuery(sql5);
			rs5 = stmt5.executeQuery(sql6);
			
			if (rs4.next()) {
				current = Double.parseDouble(rs4.getString("quantity"));
			}
			
			sql6 = "CALL addToCart ('" + cartIdInput + "', '" + isbnInput + "', '" + (current-quantNum) + "')";
			
			
			if (cartIdInput.equals("") && isbnInput.equals("") && quantInput.equals("")) {
				deleteFromCartSuccess.setText("No Info Recieved");
				deleteFromCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
			}
			else {
				//input validation
				if (!(rs.next())) {
					deleteFromCartSuccess.setText("Book does not exist");
					deleteFromCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				}
				else if (!(rs2.next())) {
					deleteFromCartSuccess.setText("Cart does not exist");			
					deleteFromCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				}
				else if (!(rs3.next())) {
					deleteFromCartSuccess.setText("Not your cart");	
					deleteFromCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));				
				}
				else if (!(rs5.next())) {
					deleteFromCartSuccess.setText("Book Not In Cart");	
					deleteFromCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				}
				else {
					deleteFromCartSuccess.setText("Deleted From Cart");	
					deleteFromCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));				
					if (current == quantNum) { //if the same amount of books are in the cart as you want to remove, delete entire row
						update = stmt.executeUpdate(sql);
						getCartIDs();
						deleteFromAvailableCarts.setText(globalCartIDs);
					}
					else if (current > quantNum){ //if there are more in the cart than you want to remove, remove all then add back in apropriate amount
						update = stmt.executeUpdate(sql);
						update = stmt2.executeUpdate(sql6);
						getCartIDs();
						deleteFromAvailableCarts.setText(globalCartIDs);
					}
					else { //throw error if you attempt to remove more books than there are in the cart					
						deleteFromCartSuccess.setText("Not Enough Books");	
						deleteFromCartSuccessPan.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
					}
				}
			}
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}	
		
	}

	
	public void mergeCarts() {
		
		getCartIDs();
		
		mergeCartsTitle = new JLabel("Merge Carts");
		mergeCartsResults= new JLabel("");
		mergeCartOne = new JLabel("Cart one: ");
		mergeCartTwo = new JLabel("Cart two: ");
		mergeCartOneText = new JTextField(10);
		mergeCartTwoText = new JTextField(10);
		mergeCartsSubmitButton = new JButton("Submit");
		mergeCartsBackButton = new JButton("Back to Menu");
		mergeCartsCartLabel = new JLabel("Carts: ");
		mergeCartsListLabel = new JLabel("");
		mergeCartsListLabel.setText(globalCartIDs);

		mergeCartsMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};	
		mergeCartsMain.setLayout(new BoxLayout(mergeCartsMain, BoxLayout.Y_AXIS));
		mergeCartsMain.setPreferredSize(fullWindow);
		
		mergeCartsTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsTop.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/6)));
		mergeCartsTop.setOpaque(false);

		mergeCartsMidTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsMidTop.setPreferredSize(quarterWindow);
		mergeCartsMidTop.setOpaque(false);

		mergeCartsMidTopList.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsMidTopList.setPreferredSize(new Dimension(((int)(this.getWidth()/1)), ((int)this.getHeight()/6)));
		mergeCartsMidTopList.setOpaque(false);

		
		mergeCartsMid.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
		mergeCartsMid.setPreferredSize(quarterQuarterWindow);
		mergeCartsMid.setOpaque(false);

		mergeCartsBot.setLayout(new BoxLayout(mergeCartsBot, BoxLayout.X_AXIS));	
		mergeCartsBot.setPreferredSize(quarterWindow);
		mergeCartsBot.setOpaque(false);
		
		mergeCartsBotLeft.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsBotLeft.setPreferredSize(fullWindow);
		mergeCartsBotLeft.setOpaque(false);

		mergeCartsBotRight.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsBotRight.setPreferredSize(quarterQuarterWindow);
		mergeCartsBotRight.setOpaque(false);

		mergeCartsBotRightTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsBotRightTop.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/8)));
		mergeCartsBotRightTop.setOpaque(false);

		mergeCartsBotRightBot.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsBotRightBot.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/8)));
		mergeCartsBotRightBot.setOpaque(false);

		mergeCartsBotRightTopSubmit.setLayout(new FlowLayout(FlowLayout.CENTER));		
		mergeCartsBotRightTopSubmit.setOpaque(false);
		
		mergeCartsBotRightBotBack.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsBotRightBotBack.setOpaque(false);

		mergeCartsBotLeftResults.setLayout(new FlowLayout(FlowLayout.CENTER));
		mergeCartsBotLeftResults.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/6)));
		mergeCartsBotLeftResults.setOpaque(false);		
		
		mergeCartsTitle.setFont(new Font("Serif", Font.PLAIN, 50));
		mergeCartsTitle.setForeground(Color.WHITE);
		mergeCartOne.setFont(new Font(null, Font.PLAIN, 18));
		mergeCartTwo.setFont(new Font(null, Font.PLAIN, 18));
		mergeCartsResults.setFont(new Font(null, Font.PLAIN, 22));
		mergeCartsResults.setForeground(Color.WHITE);
		mergeCartsCartLabel.setForeground(Color.WHITE);
		mergeCartsCartLabel.setFont(new Font(null, Font.PLAIN, 18));
		mergeCartsListLabel.setFont(new Font(null, Font.PLAIN, 22));
		mergeCartsListLabel.setForeground(Color.WHITE);
		mergeCartsMidTopList.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
		
		mergeCartsSubmitButton.setFont(new Font(null, Font.PLAIN, 28));
		
		mergeCartsBackButton.setFont(new Font(null, Font.PLAIN, 28));		
		
		mergeCartOne.setForeground(Color.WHITE);
		mergeCartTwo.setForeground(Color.WHITE);
		
		mergeCartsMain.add(mergeCartsTop);
		mergeCartsMain.add(mergeCartsMidTop);
		mergeCartsMain.add(mergeCartsMid);
		mergeCartsMain.add(mergeCartsBot);
		
		mergeCartsTop.add(mergeCartsTitle);
		
		mergeCartsMidTop.add(mergeCartsCartLabel);
		mergeCartsMidTop.add(mergeCartsMidTopList);
		
		mergeCartsMidTopList.add(mergeCartsListLabel);
		
		mergeCartsMid.add(mergeCartOne);
		mergeCartsMid.add(mergeCartOneText);
		mergeCartsMid.add(mergeCartTwo);
		mergeCartsMid.add(mergeCartTwoText);
		
		mergeCartsBot.add(mergeCartsBotLeft);
		mergeCartsBot.add(mergeCartsBotRight);
		
		mergeCartsBotLeft.add(mergeCartsBotLeftResults);
		mergeCartsBotLeftResults.add(mergeCartsResults);
		
		mergeCartsBotRight.add(mergeCartsBotRightTop);
		mergeCartsBotRight.add(mergeCartsBotRightBot);
		
		mergeCartsBotRightTop.add(mergeCartsBotRightTopSubmit);
		mergeCartsBotRightBot.add(mergeCartsBotRightBotBack);
		
		mergeCartsBotRightTopSubmit.add(mergeCartsSubmitButton);
		mergeCartsBotRightBotBack.add(mergeCartsBackButton);
		

		mergeCartsSubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
										
				mergeCartsSQL(customerID, mergeCartOneText.getText(), mergeCartTwoText.getText());

				mergeCartsBotLeftResults.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));

				mergeCartOneText.setText("");
				mergeCartTwoText.setText("");
				getCartIDs();
				mergeCartsListLabel.setText(globalCartIDs);
				
				
			}
		});		
		
		mergeCartsBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				
				mergeCartsMain.setVisible(false);
				
				mergeCartsResults.setText("");
				userMainMenu.repaint();
				userMainMenu.setVisible(true);
				mergeCartsBotLeftResults.setBorder(BorderFactory.createLineBorder(panelColor, 0));
				
				
			}
		});
		
		mergeCartsSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	mergeCartsSubmitButton.setBackground(Color.GRAY);
		    	mergeCartsSubmitButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	mergeCartsSubmitButton.setBackground(UIManager.getColor("control"));
		    	mergeCartsSubmitButton.setForeground(Color.BLACK);
		    }
		});	
		
		
		mergeCartsBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	mergeCartsBackButton.setBackground(Color.GRAY);
		    	mergeCartsBackButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	mergeCartsBackButton.setBackground(UIManager.getColor("control"));
		    	mergeCartsBackButton.setForeground(Color.BLACK);
		    }
		});

		mergeCartOneText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {					
					
					
						
						mergeCartsSQL(customerID, mergeCartOneText.getText(), mergeCartTwoText.getText());

						mergeCartsBotLeftResults.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));

						mergeCartOneText.setText("");
						mergeCartTwoText.setText("");
						getCartIDs();
						mergeCartsListLabel.setText(globalCartIDs);
						
					
					
				}	      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});


		mergeCartTwoText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {					
					
					mergeCartsSQL(customerID, mergeCartOneText.getText(), mergeCartTwoText.getText());

					mergeCartsBotLeftResults.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));

					mergeCartOneText.setText("");
					mergeCartTwoText.setText("");
					getCartIDs();
					mergeCartsListLabel.setText(globalCartIDs);
					
				}	      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				 
				
			}
		});

		

		this.add(mergeCartsMain);
		this.pack();
		this.setVisible(true);		
	}
	

	/**
	 * 
	 * @param cid
	 * @param cartid1
	 * @param cartid2
	 */
	public void mergeCartsSQL(String cid, String cart1, String cart2) {

		conn = null;
		stmt = null;
		stmt2 = null;
		conn2= null;
		rs = null;
		rs2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn3 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn4 = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();	
			stmt2 = conn.createStatement();	
			stmt3 = conn.createStatement();	
			stmt4 = conn.createStatement();	
			
			boolean flag3 = true;
			ArrayList<Double> ar1 = new ArrayList<Double>();
			ArrayList<Double> ar2 = new ArrayList<Double>();
			double a, b;
			
			
			//used for input validation 
			sql = "CALL mergecarts ('" + cart1 + "', '" + cart2 + "', '" + cid + "')";
			sql2 = "SELECT * FROM cart WHERE cartid = '" + cart1 + "' and customerid = '" + cid+"'";
			sql3 = "SELECT * FROM cart WHERE cartid = '" + cart2 + "' and customerid = '" + cid+"'";
			
			//grabs a list of all book ISBNs in both carts
			sql4 = "SELECT ISBN from booksincart where cartid = '"+cart1+"'";
			sql5 = "SELECT ISBN from booksincart where cartid = '"+cart2+"'";
			
			
			rs = stmt.executeQuery(sql2);
			rs2 = stmt2.executeQuery(sql3);
			rs3 = stmt3.executeQuery(sql4);
			rs4 = stmt4.executeQuery(sql5);
			
			//add the ISBNs to two separate arraylists
			while(rs3.next()){
				ar1.add(Double.parseDouble(rs3.getString("ISBN")));
			
			}
			
			while(rs4.next()) {
				ar2.add(Double.parseDouble(rs4.getString("ISBN")));
			}
			
			//cycle through the arrays and check to see if any of the ISBNs match. If they do, flag it as bad
			for (int i = 0; i < ar1.size() && flag3 ==true; i++) {
				for (int j = 0; j < ar2.size() && flag3 == true; j++) {
					a = ar1.get(i);
					b = ar2.get(j);
					if (a==b)
						flag3 = false;
				}
			}
			
			//print an error if the arrays are flagged
			if (flag3 == false)
				mergeCartsResults.setText("Sorry, your carts contain two of the same book. Please delete that book from one of your carts and try again");
			//input validation
			if (!(rs.next()))
				mergeCartsResults.setText("Cart is not yours");		
			else if (!(rs2.next()))
				mergeCartsResults.setText("Cart is not yours");		
			else if (flag3 == true){
				update = stmt.executeUpdate(sql);
				mergeCartsResults.setText("Carts successfully merged");
			}		
					
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}

	}
	
	
	/**
	 * 
	 */
	public void placeOrder() {
		
		getCartIDs();
		
		placeOrderMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};	
		placeOrderMain.setLayout(new BoxLayout(placeOrderMain, BoxLayout.Y_AXIS));
		placeOrderMain.setPreferredSize(fullWindow);
		
		placeOrderTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		placeOrderTop.setOpaque(false);
		
		placeOrderMiddle.setLayout(new GridLayout(2,3));
		placeOrderMiddle.setOpaque(false);
		
		placeOrderBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		placeOrderBottom.setOpaque(false);
		
		placeOrderMiddleMiddle.setLayout(new GridLayout(4, 2));
		placeOrderMiddleMiddle.setOpaque(false);
		
		placeOrderBottomMiddle.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		placeOrderBottomMiddle.setOpaque(false);
		
		placeOrderMiddleCartSlot.setLayout(new FlowLayout(FlowLayout.RIGHT));
		placeOrderMiddleCartSlot.setOpaque(false);
		
		placeOrderMiddleShipAdSlot.setLayout(new FlowLayout(FlowLayout.RIGHT));
		placeOrderMiddleShipAdSlot.setOpaque(false);
		
		placeOrderMiddleBillAdSlot.setLayout(new FlowLayout(FlowLayout.RIGHT));
		placeOrderMiddleBillAdSlot.setOpaque(false);
		
		placeOrderMiddleCCNumSlot.setLayout(new FlowLayout(FlowLayout.RIGHT));
		placeOrderMiddleCCNumSlot.setOpaque(false);
		
		placeOrderMiddleCartText.setLayout(new FlowLayout(FlowLayout.LEFT));
		placeOrderMiddleCartText.setOpaque(false);
		
		placeOrderMiddleShipAdText.setLayout(new FlowLayout(FlowLayout.LEFT));
		placeOrderMiddleShipAdText.setOpaque(false);
		
		placeOrderMiddleBillAdText.setLayout(new FlowLayout(FlowLayout.LEFT));
		placeOrderMiddleBillAdText.setOpaque(false);
		
		placeOrderMiddleCCNumText.setLayout(new FlowLayout(FlowLayout.LEFT));
		placeOrderMiddleCCNumText.setOpaque(false);
		
		burn.setLayout(new BoxLayout(burn, BoxLayout.Y_AXIS));
		burn.setOpaque(false);
		
		burn2.setOpaque(false);
		
		burn3.setLayout(new FlowLayout(FlowLayout.CENTER));
		burn3.setOpaque(false);
		burn3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
		
		burn4.setOpaque(false);	
		
		burn5.setLayout(new FlowLayout(FlowLayout.CENTER));
		burn5.setOpaque(false);
		
		burnTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		burnTop.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
		burnTop.setOpaque(false);
		
		burnBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		burnBottom.setOpaque(false);	
		
		placeOrderTablePan.setLayout(new FlowLayout(FlowLayout.CENTER));
		placeOrderTablePan.setOpaque(false);
		placeOrderTablePan.setPreferredSize(new Dimension(375, 250));
		
		placeOrderText2 = new JLabel("Enter Cart:");
		placeOrderText2.setFont(new Font(null, Font.PLAIN, 22));
		placeOrderText2.setForeground(Color.WHITE);
		
		placeOrderNumCarts = new JLabel("Your Carts:");
		placeOrderNumCarts.setFont(new Font(null, Font.PLAIN, 22));
		placeOrderNumCarts.setForeground(Color.WHITE);
		
		placeOrderYourCarts = new JLabel(globalCartIDs);		
		placeOrderYourCarts.setFont(new Font(null, Font.PLAIN, 20));
		placeOrderYourCarts.setForeground(Color.WHITE);	
		
		placeOrderTitle = new JLabel("Place Order");
		placeOrderTitle.setFont(new Font("Serif", Font.PLAIN, 60));
		placeOrderTitle.setForeground(Color.WHITE);
		
		placeOrderCartID = new JLabel("Cart ID:");
		placeOrderCartID.setFont(new Font(null, Font.PLAIN, 18));
		placeOrderCartID.setForeground(Color.WHITE);
		
		placeOrderShippingAd = new JLabel("Shippint Address:");
		placeOrderShippingAd.setFont(new Font(null, Font.PLAIN, 18));
		placeOrderShippingAd.setForeground(Color.WHITE);
		
		placeOrderBillingAd = new JLabel("Billing Address:");
		placeOrderBillingAd.setFont(new Font(null, Font.PLAIN, 18));
		placeOrderBillingAd.setForeground(Color.WHITE);
		
		placeOrderCCNum = new JLabel("Credit Card Number:");
		placeOrderCCNum.setFont(new Font(null, Font.PLAIN, 18));
		placeOrderCCNum.setForeground(Color.WHITE);		  
		
		placeOrderUpdateInfo = new JLabel("");
		placeOrderUpdateInfo.setFont(new Font(null, Font.PLAIN, 18));
		placeOrderUpdateInfo.setForeground(Color.WHITE);
		
		placeOrderCartInput = new JTextField(16);
		
		placeOrderShippAdInput = new JTextField(16);
		
		placeOrderBillAdInput = new JTextField(16);
		
		placeOrderCCNumInput = new JTextField(16);
				
		placeOrderText = new JTextField(5);	
		
		checkout = new JButton("Checkout");
		checkout.setFont(new Font(null, Font.PLAIN, 16));
		checkout.setPreferredSize(new Dimension((int)WINDOW_WIDTH / 16, (int)WINDOW_HEIGHT / 20));
		
		placeOrderView = new JButton("View");	
		placeOrderView.setFont(new Font(null, Font.PLAIN, 14));
		placeOrderView.setPreferredSize(new Dimension((int)WINDOW_WIDTH / 22, (int)WINDOW_HEIGHT / 22));
		
		placeOrderBack1 = new JButton("Back to Menu");
		placeOrderBack1.setFont(new Font(null, Font.PLAIN, 14));
		placeOrderBack1.setPreferredSize(new Dimension((int)WINDOW_WIDTH / 16, (int)WINDOW_HEIGHT / 20));
		
		checkout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String cartID = placeOrderCartInput.getText();
				String shipAdd = placeOrderShippAdInput.getText();
				String billAdd = placeOrderBillAdInput.getText();
				String ccNum = placeOrderCCNumInput.getText();
				
				placeOrderCartInput.setText("");
				placeOrderShippAdInput.setText("");
				placeOrderBillAdInput.setText("");
				placeOrderCCNumInput.setText("");			
				
				runPlaceOrderSQL(cartID, shipAdd, billAdd, ccNum);
			}
		});
		
		checkout.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	checkout.setBackground(Color.GRAY);
		    	checkout.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	checkout.setBackground(UIManager.getColor("control"));
		    	checkout.setForeground(Color.BLACK);		    	
		    }
		});
		
		placeOrderBack1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				placeOrderCartInput.setText("");
				placeOrderShippAdInput.setText("");
				placeOrderBillAdInput.setText("");
				placeOrderCCNumInput.setText("");
				
				placeOrderUpdateInfo.setText("");
				
				placeOrderTablePan.removeAll();
				
				placeOrderMain.setVisible(false);
				
				userMainMenu.setVisible(true);
				userMainMenu.revalidate();
			}
		});
		
		placeOrderBack1.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	placeOrderBack1.setBackground(Color.GRAY);
		    	placeOrderBack1.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	placeOrderBack1.setBackground(UIManager.getColor("control"));
		    	placeOrderBack1.setForeground(Color.BLACK);		    	
		    }
		});
		
		placeOrderView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				placeOrderTablePan.removeAll();
				
				runBooksInCartSQL();				
			}
		});
		
		placeOrderView.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	placeOrderView.setBackground(Color.GRAY);
		    	placeOrderView.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	placeOrderView.setBackground(UIManager.getColor("control"));
		    	placeOrderView.setForeground(Color.BLACK);		    	
		    }
		});	
		
		placeOrderTop.add(placeOrderTitle);
		
		placeOrderMiddleCartSlot.add(placeOrderCartID);
		placeOrderMiddleCartText.add(placeOrderCartInput);
		placeOrderMiddleShipAdSlot.add(placeOrderShippingAd);
		placeOrderMiddleShipAdText.add(placeOrderShippAdInput);
		placeOrderMiddleBillAdSlot.add(placeOrderBillingAd);
		placeOrderMiddleBillAdText.add(placeOrderBillAdInput);
		placeOrderMiddleCCNumSlot.add(placeOrderCCNum);
		placeOrderMiddleCCNumText.add(placeOrderCCNumInput);
		
		placeOrderMiddleMiddle.add(placeOrderMiddleCartSlot);
		placeOrderMiddleMiddle.add(placeOrderMiddleCartText);
		placeOrderMiddleMiddle.add(placeOrderMiddleShipAdSlot);
		placeOrderMiddleMiddle.add(placeOrderMiddleShipAdText);
		placeOrderMiddleMiddle.add(placeOrderMiddleBillAdSlot);
		placeOrderMiddleMiddle.add(placeOrderMiddleBillAdText);
		placeOrderMiddleMiddle.add(placeOrderMiddleCCNumSlot);
		placeOrderMiddleMiddle.add(placeOrderMiddleCCNumText);
		
		placeOrderBottomMiddle.add(checkout);
		placeOrderBottomMiddle.add(placeOrderBack1);
		
		placeOrderBottom.add(placeOrderBottomMiddle);		
		
		burnBottom.add(placeOrderText2);
		burnBottom.add(placeOrderText);
		burnBottom.add(placeOrderView);	
		
		burnTop.add(placeOrderNumCarts);
		burnTop.add(placeOrderYourCarts);
		
		burn.add(burnTop);
		burn.add(burnBottom);	
		
		burn5.add(placeOrderUpdateInfo);
		
		burn3.add(placeOrderTablePan);
		
		placeOrderMiddle.add(burn);
		placeOrderMiddle.add(placeOrderMiddleMiddle);
		placeOrderMiddle.add(burn2);
		placeOrderMiddle.add(burn3);
		placeOrderMiddle.add(burn5);
		placeOrderMiddle.add(burn4);
		
		placeOrderMain.add(placeOrderTop);
		placeOrderMain.add(placeOrderMiddle);
		placeOrderMain.add(placeOrderBottom);
		
		this.add(placeOrderMain);
		this.pack();		
	}
	
	public void runBooksInCartSQL() {
		
		
		conn = null;
		conn2 = null;		
		stmt = null;	
		stmt2 = null;	
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);	
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);				
			
			stmt = conn.createStatement();	
			stmt2 = conn2.createStatement();		
			
			sql = "SELECT * FROM booksincart WHERE CartID = '" + placeOrderText.getText() + "'";
			
			rs = stmt.executeQuery(sql);				
			rs2 = stmt2.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs2));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(375, 250));
			
			placeOrderTablePan.add(tableContainer);
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	
	public void runPlaceOrderSQL(String cartID, String shipAdd, String billAdd, String ccNum) {
		
		conn = null;
		conn2 = null;
		conn3 = null;
		conn4 = null;
		conn5 = null;
		stmt = null;	
		stmt2 = null;
		stmt3 = null;
		stmt4 = null;
		stmt5 = null;		
		boolean flag = true;

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt((3-1)+1)*10;
		String empID2 = Integer.toString(randomInt), quant, isbnS;
		double isbn, quantity2, quantity;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);	
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn3 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn4 = DriverManager.getConnection(DB_URL,USER,PASS);	
			conn5 = DriverManager.getConnection(DB_URL,USER,PASS);	
			
			stmt = conn.createStatement();	
			stmt2 = conn2.createStatement();		
			stmt3 = conn3.createStatement();		
			stmt4 = conn4.createStatement();	
			stmt5 = conn5.createStatement();	
			
			
			//sql to place the order after input validation
			sql = "CALL placeorder('" + cartID + "', '" + customerID + "', '" + empID2 + "', '" + shipAdd + "', '" + billAdd + "', '" + ccNum + "')";
			
			//used for input validation
			sql2 = "SELECT * from cart WHERE cartid = '" + cartID + "'";
			sql3 = "SELECT * FROM cart WHERE cartid = '" + cartID + "' and customerid = '" + customerID + "'";
			sql4 = "SELECT ISBN, Quantity from booksInCart WHERE cartid = '"+cartID + "'"; //gets the list of ISBNs and their quantities from the cart
			
			
					
			rs4 = stmt4.executeQuery(sql4);
			
			//This loop is stupid complicated. Basically it compares the quantities in the books table and the booksincart table and makes sure
			//there are enough books available to sell. Otherwise it flags it as an error.
			while(rs4.next() && flag == true)
			{
				isbn = Double.parseDouble(rs4.getString("ISBN"));
				quantity2 = Double.parseDouble(rs4.getString("Quantity"));
				sql4 = "select quantity from books where ISBN = '" + isbn + "'";
				
				rs = stmt.executeQuery(sql4);
				
				rs.next();
				quantity = Double.parseDouble(rs.getString("Quantity"));
				
				
				if (quantity < quantity2)
				{					
					String s = "Sorry, you cannot place this order: You are requesting more copies of a book than are in stock";				
					String html1 = "<html><body style='width: ";
			        String html2 = "px'>";     
			       
					placeOrderUpdateInfo.setText(html1+"225"+html2+s);
					flag = false;
				}
			}
			
			
			rs2 = stmt2.executeQuery(sql2);
			rs3 = stmt3.executeQuery(sql3);				
			rs4 = stmt4.executeQuery(sql4);
			
			
			if (!(rs2.next())) {				
				String s = "Cart does not exist";				
				String html1 = "<html><body style='width: ";
		        String html2 = "px'>";   
		        
				placeOrderUpdateInfo.setText(html1+"225"+html2+s);
				placeOrderUpdateInfo.setFont(new Font("Serif", Font.PLAIN, 30));
			}
			else if (!(rs3.next())) {
				
				String s = "Not your cart";				
				String html1 = "<html><body style='width: ";
		        String html2 = "px'>";  
		        
		        placeOrderUpdateInfo.setText(html1+"225"+html2+s);
		        placeOrderUpdateInfo.setFont(new Font("Serif", Font.PLAIN, 30));
			}
			else if (flag == true) {
				
				String s = "Order Placed!";				
				String html1 = "<html><body style='width: ";
		        String html2 = "px'>";  		        
		        placeOrderUpdateInfo.setText(html1+"225"+html2+s);
		        placeOrderUpdateInfo.setFont(new Font("Serif", Font.ITALIC, 30));
		        
				update = stmt.executeUpdate(sql);
				sql = "SELECT * FROM booksincart WHERE cartid = " + cartID;
				rs2 = stmt.executeQuery(sql);
				getCartIDs();
				placeOrderYourCarts.setText(globalCartIDs);	
		        
				
				
				while (rs2.next()) {
					
					cartID = rs2.getString("CartID");
					isbnS = rs2.getString("ISBN");
					quant = rs2.getString("Quantity");
					
					
					sql2 = "CALL mergebooks ('" + cartID + "', '" + isbnS + "', '" + quant + "')";
					update = stmt2.executeUpdate(sql2);
				}
			}
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}	

public void trackOrder() {
	getOrders();
	System.out.println(globalOrders);
	
	trackOrderTitle = new JLabel("Track Order");
	trackOrderResults= new JLabel("");
	trackOrderNumber = new JLabel("Order #: ");
	trackOrderListLabel = new JLabel("");
	trackOrderListLabel.setText(globalOrders);
	
	trackOrderText = new JTextField(10);

	trackOrderSubmitButton = new JButton("Submit");
	trackOrderBackButton = new JButton("Back to Menu");
	trackOrderNumberLabel = new JLabel("Orders: ");
	

	trackOrderMain = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			setBackground(panelColor);
			
			GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
			
			g2d.setPaint(gp);
			g2d.fillRect(0,  0, getWidth(), getHeight());
		}
	};	
	
	trackOrderMain.setLayout(new BoxLayout(trackOrderMain, BoxLayout.Y_AXIS));
	trackOrderMain.setPreferredSize(fullWindow);

	
	trackOrderTop.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderTop.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/6)));
	trackOrderTop.setOpaque(false);

	trackOrderMidTop.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderMidTop.setPreferredSize(quarterWindow);
	trackOrderMidTop.setOpaque(false);;

	trackOrderMidTopList.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderMidTopList.setPreferredSize(new Dimension(((int)(this.getWidth()/1)), ((int)this.getHeight()/6)));
	trackOrderMidTopList.setOpaque(false);;

	
	trackOrderMid.setLayout(new FlowLayout(FlowLayout.CENTER,20,0));
	trackOrderMid.setPreferredSize(quarterQuarterWindow);
	trackOrderMid.setOpaque(false);;

	trackOrderBot.setLayout(new BoxLayout(trackOrderBot, BoxLayout.X_AXIS));	
	trackOrderBot.setPreferredSize(quarterWindow);
	trackOrderBot.setOpaque(false);;
	
	trackOrderBotLeft.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderBotLeft.setPreferredSize(fullWindow);
	trackOrderBotLeft.setOpaque(false);;

	trackOrderBotRight.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderBotRight.setPreferredSize(quarterQuarterWindow);
	trackOrderBotRight.setOpaque(false);;

	trackOrderBotRightTop.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderBotRightTop.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/8)));
	trackOrderBotRightTop.setOpaque(false);;

	trackOrderBotRightBot.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderBotRightBot.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/8)));
	trackOrderBotRightBot.setOpaque(false);;

	trackOrderBotRightTopSubmit.setLayout(new FlowLayout(FlowLayout.CENTER));
	//mergeCartsBotRightTopSubmit.setPreferredSize(fullWindow);
	trackOrderBotRightTopSubmit.setOpaque(false);
	
	trackOrderBotRightBotBack.setLayout(new FlowLayout(FlowLayout.CENTER));
	//mergeCartsBotRightBotBack.setPreferredSize(fullWindow);
	trackOrderBotRightBotBack.setOpaque(false);

	trackOrderBotLeftResults.setLayout(new FlowLayout(FlowLayout.CENTER));
	trackOrderBotLeftResults.setPreferredSize(new Dimension(((int)(this.getWidth()/2.5)), ((int)this.getHeight()/6)));
	trackOrderBotLeftResults.setBackground(Color.RED);
	trackOrderBotLeftResults.setOpaque(false);
	
	
	trackOrderTitle.setFont(new Font("Serif", Font.PLAIN, 50));
	trackOrderTitle.setForeground(Color.WHITE);
	trackOrderNumber.setFont(new Font(null, Font.PLAIN, 18));
	
	trackOrderResults.setFont(new Font(null, Font.PLAIN, 22));
	trackOrderResults.setForeground(Color.WHITE);
	trackOrderListLabel.setForeground(Color.WHITE);
	trackOrderListLabel.setFont(new Font(null, Font.PLAIN, 18));
	trackOrderNumberLabel.setFont(new Font(null, Font.PLAIN, 22));
	trackOrderNumberLabel.setForeground(Color.WHITE);
	trackOrderMidTopList.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
	
	trackOrderSubmitButton.setFont(new Font(null, Font.PLAIN, 28));
	trackOrderSubmitButton.setPreferredSize(new Dimension(150,50));
	trackOrderBackButton.setFont(new Font(null, Font.PLAIN, 28));
	trackOrderBackButton.setPreferredSize(new Dimension(150,50));
	
	trackOrderNumber.setForeground(Color.WHITE);
	
	
	trackOrderMain.add(trackOrderTop);
	trackOrderMain.add(trackOrderMidTop);
	trackOrderMain.add(trackOrderMid);
	trackOrderMain.add(trackOrderBot);
	
	trackOrderTop.add(trackOrderTitle);
	
	trackOrderMidTop.add(trackOrderNumberLabel);
	trackOrderMidTop.add(trackOrderMidTopList);
	
	trackOrderMidTopList.add(trackOrderListLabel);
	
	trackOrderMid.add(trackOrderNumber);
	trackOrderMid.add(trackOrderText);
	
	trackOrderBot.add(trackOrderBotLeft);
	trackOrderBot.add(trackOrderBotRight);
	
	trackOrderBotLeft.add(trackOrderBotLeftResults);
	trackOrderBotLeftResults.add(trackOrderResults);
	
	trackOrderBotRight.add(trackOrderBotRightTop);
	trackOrderBotRight.add(trackOrderBotRightBot);
	
	trackOrderBotRightTop.add(trackOrderBotRightTopSubmit);
	trackOrderBotRightBot.add(trackOrderBotRightBotBack);
	
	trackOrderBotRightTopSubmit.add(trackOrderSubmitButton);
	trackOrderBotRightBotBack.add(trackOrderBackButton);
	

	trackOrderSubmitButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
									
			trackOrderSQL(customerID, trackOrderText.getText());

			trackOrderBotLeftResults.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

			trackOrderText.setText("");
			
			trackOrderListLabel.setText(globalOrders);
			trackOrderBotLeftResults.setOpaque(true);
			
			
		}
	});		
	
	trackOrderBackButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {				
			
			
			trackOrderMain.setVisible(false);
			
			trackOrderResults.setText("");
			userMainMenu.repaint();
			userMainMenu.setVisible(true);
			trackOrderBotLeftResults.setBorder(BorderFactory.createLineBorder(panelColor, 0));
			trackOrderBotLeftResults.setOpaque(false);
			
			
		}
	});
	
	trackOrderSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
	    	trackOrderSubmitButton.setBackground(Color.GRAY);
	    	trackOrderSubmitButton.setForeground(Color.WHITE);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	    	trackOrderSubmitButton.setBackground(UIManager.getColor("control"));
	    	trackOrderSubmitButton.setForeground(Color.BLACK);
	    }
	});	
	
	
	trackOrderBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
	    	trackOrderBackButton.setBackground(Color.GRAY);
	    	trackOrderBackButton.setForeground(Color.WHITE);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	    	trackOrderBackButton.setBackground(UIManager.getColor("control"));
	    	trackOrderBackButton.setForeground(Color.BLACK);
	    }
	});
	
	trackOrderText.addKeyListener(new KeyListener() {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER) {					
				
				if (trackOrderSubmitButton.isDisplayable()) {
					
					trackOrderSQL(customerID, trackOrderText.getText());

					trackOrderBotLeftResults.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

					trackOrderText.setText("");
					
					trackOrderListLabel.setText(globalOrders);
					trackOrderBotLeftResults.setOpaque(true);
				}
				else {
					registerInfoPanel.setBackground(panelColor);
					registerInfoLabel.setText("");
					registerInfoPanel.setBorder(BorderFactory.createLineBorder(panelColor));
					
					registerMain.setVisible(false);
					
					userLogin();
				}
			}	      
	    }

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {			
			
		}
	});

	this.add(trackOrderMain);
	this.pack();
	this.setVisible(true);
	
}
	public void trackOrderSQL(String cid, String orderNum) {
		
	
		conn = null;
		stmt = null;
		stmt2 = null;
		conn2= null;
		rs = null;
		rs2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn3 = DriverManager.getConnection(DB_URL,USER,PASS);
			conn4 = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();	
			stmt2 = conn.createStatement();	
			stmt3 = conn.createStatement();	
			stmt4 = conn.createStatement();	
			/*
			if (rs.next()) {			
				table = new JTable(buildTableModel(rs2));
				JScrollPane tableContainer = new JScrollPane(table);
				trackOrderBotLeft.add(tableContainer);
				this.setVisible(true);				
			}
			else {
				trackOrderResults.setText("Carts successfully merged");
				
			}
				*/
			
			String status = null;						
			
			//sql that gets the order numbers from the user 
			sql2 = "SELECT ordernum from orders WHERE customerid =" + cid;
									
			rs2 = stmt2.executeQuery(sql2);
			
			//sql that checks to make sure the order the user wants is their order
			sql = "SELECT ordernum FROM orders WHERE ordernum = " + orderNum + " and customerid = " + cid;						
			rs = stmt.executeQuery(sql);
			
			//input validation
			if (!(rs.next())) {
				trackOrderResults.setText("No such order");
			trackOrderBotLeftResults.setBackground(Color.RED); 
			}
			else { //sql gets the order status of the order requested
				sql3 = "SELECT orderstatus FROM orders WHERE ordernum = " + orderNum;
				rs3 = stmt3.executeQuery(sql3);
				
				while (rs3.next()) {
					status = rs3.getString("OrderStatus");
				}
				//prints status
				trackOrderResults.setText(status);
				trackOrderBotLeftResults.setBackground(Color.GREEN);
			}
		
			
		//housekeeping
			stmt.close();
			stmt2.close();
			conn.close();
			conn2.close();
			
			
			
			
			
					
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
			
			
	
	

	
}
	public void getOrders() {
		
		

		
		
		stmt2 = null;
		conn2= null;
		
		rs2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt2 = conn2.createStatement();	
			
			
			//sql that gets the order numbers from the user 
			sql2 = "SELECT ordernum from orders WHERE customerid =" + customerID;
									
			rs2 = stmt2.executeQuery(sql2);
			
			//This chunk prints out all the orders the user has
			globalOrders = "";
			while (rs2.next()) {
				globalOrders += rs2.getString("OrderNum")+" ";
				
			}
			
		
			
		//housekeeping
			//stmt2.close();
			//conn2.close();
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
	      //Handle errors for Class.forName
		   System.out.println("2");
	      e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  System.out.println("4");
		         se.printStackTrace();
		      }
		}
				
	}
	
	
	
	public void printTables() {
		
		
		printTablesMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};		
		printTablesMain.setLayout(new BoxLayout(printTablesMain, BoxLayout.Y_AXIS));
		printTablesMain.setSize(fullWindow);
		
		printTablesVeryTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		printTablesVeryTop.setOpaque(false);
		
		printTablesTop.setLayout(new FlowLayout(FlowLayout.CENTER));
		printTablesTop.setOpaque(false);
		
		printTablesBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		printTablesBottom.setOpaque(false);
		
		printTablesVeryBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		printTablesVeryBottom.setOpaque(false);
		
		printTablePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		printTablePanel.setOpaque(false);
		printTablePanel.setPreferredSize(tableSize);
		
		printTopGrid.setLayout(new GridLayout(3, 3));
		printTopGrid.setOpaque(false);
		
		printGrid1.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid1.setOpaque(false);
		
		printGrid2.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid2.setOpaque(false);
		
		printGrid3.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid3.setOpaque(false);
		
		printGrid4.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid4.setOpaque(false);
		
		printGrid5.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid5.setOpaque(false);
		
		printGrid6.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid6.setOpaque(false);
		
		printGrid7.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid7.setOpaque(false);
		
		printGrid8.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid8.setOpaque(false);
		
		printGrid9.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid9.setOpaque(false);
		
		printGrid10.setLayout(new FlowLayout(FlowLayout.CENTER));
		printGrid10.setOpaque(false);
		
		
		
		printTitle = new JLabel("Print Tables");
		printTitle.setFont(new Font("Serif", Font.PLAIN, 50));
		printTitle.setForeground(Color.WHITE);
		
		printBackButton = new JButton("Back To Menu");
		printBackButton.setFont(new Font(null, Font.PLAIN, 28));
		
		printBooksButton = new JButton("Books");
		printBooksButton.setFont(new Font(null, Font.PLAIN, 28));
		printBooksButton.setPreferredSize(new Dimension( (int)(this.getWidth() / 5), (int)(this.getHeight() / 16.2) ));
		
		printBooksInCartButton = new JButton("Books in Cart");
		printBooksInCartButton.setFont(new Font(null, Font.PLAIN, 28));
		printBooksInCartButton.setPreferredSize(new Dimension( (int)(this.getWidth() / 5), (int)(this.getHeight() / 16.2) ));
		
		printBooksOrderedButton = new JButton("Books Ordered");
		printBooksOrderedButton.setFont(new Font(null, Font.PLAIN, 28));
		printBooksOrderedButton.setPreferredSize(new Dimension( (int)(this.getWidth() / 5), (int)(this.getHeight() / 16.2) ));
		
		printCartButton = new JButton("Carts");
		printCartButton.setFont(new Font(null, Font.PLAIN, 28));
		printCartButton.setPreferredSize(new Dimension( (int)(this.getWidth() / 5), (int)(this.getHeight() / 16.2) ));
		
		printCustomerButton = new JButton("Customer Info");
		printCustomerButton.setFont(new Font(null, Font.PLAIN, 28));
		printCustomerButton.setPreferredSize(new Dimension( (int)(this.getWidth() / 5), (int)(this.getHeight() / 16.2) ));
		
		printOrdersButton = new JButton("Orders");
		printOrdersButton.setFont(new Font(null, Font.PLAIN, 28));
		printOrdersButton.setPreferredSize(new Dimension( (int)(this.getWidth() / 5), (int)(this.getHeight() / 16.2) ));
		
		
		printBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
								
				printTablePanel.removeAll();
				
				printTablesMain.setVisible(false);
				
				userMainMenu.setVisible(true);
				userMainMenu.revalidate();
			}
		});
		
		printBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	printBackButton.setBackground(Color.GRAY);
		    	printBackButton.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	printBackButton.setBackground(UIManager.getColor("control"));
		    	printBackButton.setForeground(Color.BLACK);		    	
		    }
		});
		
		printBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				printTablePanel.removeAll();
				
				printBooksTable();
				
			}
		});
		
		printBooksButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	printBooksButton.setBackground(Color.GRAY);
		    	printBooksButton.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	printBooksButton.setBackground(UIManager.getColor("control"));
		    	printBooksButton.setForeground(Color.BLACK);		    	
		    }
		});
		
		printBooksInCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				printTablePanel.removeAll();
				
				printBooksInCart();
			}
		});
		
		printBooksInCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	printBooksInCartButton.setBackground(Color.GRAY);
		    	printBooksInCartButton.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	printBooksInCartButton.setBackground(UIManager.getColor("control"));
		    	printBooksInCartButton.setForeground(Color.BLACK);		    	
		    }
		});
		
		printBooksOrderedButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				printTablePanel.removeAll();
				
				printBooksOrdered();
			}
		});
		
		printBooksOrderedButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	printBooksOrderedButton.setBackground(Color.GRAY);
		    	printBooksOrderedButton.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	printBooksOrderedButton.setBackground(UIManager.getColor("control"));
		    	printBooksOrderedButton.setForeground(Color.BLACK);		    	
		    }
		});
		
		printCartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				printTablePanel.removeAll();
				
				printCarts();
			}
		});
		
		printCartButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	printCartButton.setBackground(Color.GRAY);
		    	printCartButton.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	printCartButton.setBackground(UIManager.getColor("control"));
		    	printCartButton.setForeground(Color.BLACK);		    	
		    }
		});
		
		printCustomerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				printTablePanel.removeAll();
				
				printCustomerInfo();
			}
		});
		
		printCustomerButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	printCustomerButton.setBackground(Color.GRAY);
		    	printCustomerButton.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	printCustomerButton.setBackground(UIManager.getColor("control"));
		    	printCustomerButton.setForeground(Color.BLACK);		    	
		    }
		});
		
		printOrdersButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				printTablePanel.removeAll();
				
				printOrders();
			}
		});
		
		printOrdersButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	printOrdersButton.setBackground(Color.GRAY);
		    	printOrdersButton.setForeground(Color.WHITE);		    	
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	printOrdersButton.setBackground(UIManager.getColor("control"));
		    	printOrdersButton.setForeground(Color.BLACK);		    	
		    }
		});
		
		
		printTablesVeryBottom.add(printBackButton);
		
		printTablesBottom.add(printTablePanel);
		
		printGrid1.add(printBooksButton);
		printGrid2.add(printBooksInCartButton);
		printGrid3.add(printBooksOrderedButton);
		printGrid4.add(printCartButton);
		printGrid5.add(printCustomerButton);
		printGrid6.add(printOrdersButton);
		
		printTopGrid.add(printGrid1);
		printTopGrid.add(printGrid2);
		printTopGrid.add(printGrid3);
		printTopGrid.add(printGrid4);
		printTopGrid.add(printGrid5);
		printTopGrid.add(printGrid6);	
		
		printTablesTop.add(printTopGrid);
		
		printTablesVeryTop.add(printTitle);
		
		printTablesMain.add(printTablesVeryTop);
		printTablesMain.add(printTablesTop);
		printTablesMain.add(printTablesBottom);
		printTablesMain.add(printTablesVeryBottom);
		
		this.add(printTablesMain);		
	}
	
	public void printBooksTable() {
		
		conn = null;		
		stmt = null;			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			
			sql = "SELECT * FROM books";			
					
			rs = stmt.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(printTablePanel.getWidth(), printTablePanel.getHeight()));
			printTablePanel.add(tableContainer);
			
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	
	public void printBooksInCart() {
		
		conn = null;		
		stmt = null;			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			
			sql = "SELECT * FROM booksincart WHERE CartID in (SELECT CartID FROM cart WHERE CustomerID = '" + customerID + "')";			
					
			rs = stmt.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(printTablePanel.getWidth(), printTablePanel.getHeight()));
			printTablePanel.add(tableContainer);
			
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	
	public void printBooksOrdered() {
		
		conn = null;		
		stmt = null;			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			
			sql = "SELECT * FROM booksordered WHERE OrderNum in (SELECT OrderNum FROM orders WHERE CustomerID = '" + customerID + "')";			
					
			rs = stmt.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(printTablePanel.getWidth(), printTablePanel.getHeight()));
			printTablePanel.add(tableContainer);
			
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	
	public void printCarts() {
		
		conn = null;		
		stmt = null;			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			
			sql = "SELECT * FROM cart WHERE CustomerID = '" + customerID + "'";			
					
			rs = stmt.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(printTablePanel.getWidth(), printTablePanel.getHeight()));
			printTablePanel.add(tableContainer);
			
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	
	public void printCustomerInfo() {

		conn = null;		
		stmt = null;			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			
			sql = "SELECT * FROM customer WHERE customerid ='"+customerID+"'";			
					
			rs = stmt.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(printTablePanel.getWidth(), printTablePanel.getHeight()));
			printTablePanel.add(tableContainer);
			
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	
		
	
	
	public void printOrders() {
		conn = null;		
		stmt = null;			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			
			sql = "SELECT * FROM orders WHERE customerid ='"+customerID+"'";			
					
			rs = stmt.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(printTablePanel.getWidth(), printTablePanel.getHeight()));
			printTablePanel.add(tableContainer);
			
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	
	public void bookInfo() {		
		
		
		@SuppressWarnings("serial")
		Timer timer = new Timer(10, new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		bookInfoTable();    }
		
	});
		timer.setRepeats(false);
		timer.start();
		

		
		
		//bookInfoTable();
		biMain = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}
		};
		
		
		

		biTitleLabel = new JLabel("Book Info");
		biTitleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
		biTitleLabel.setForeground(Color.WHITE);
		
		biISBNLabel = new JLabel("ISBN: ");
		biISBNLabel.setFont(new Font(null, Font.PLAIN, 22));
		biISBNLabel.setForeground(Color.WHITE);
		
		biClearButton = new JButton("Clear");
		biClearButton.setFont(new Font(null, Font.PLAIN, 28));
		
		biBackButton = new JButton("Back");
		biBackButton.setFont(new Font(null, Font.PLAIN, 28));
		
		biSearchButton = new JButton("Search");
		biSearchButton.setFont(new Font(null, Font.PLAIN, 28));
		
		biv4h1Results = new JLabel("");
		biv4h1Results.setFont(new Font("Serif", Font.PLAIN, 20));
		biv4h1Results.setForeground(Color.WHITE);
		
		
		biISBNText = new JTextField(15);
		
		biMain.setLayout(new BoxLayout(biMain, BoxLayout.Y_AXIS));
		biMain.setPreferredSize(fullWindow);
		

		biv1.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv1.setOpaque(false);

		biv2.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv2.setOpaque(false);
		
		biv3.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv3.setOpaque(false);
		
		biv4.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv4.setOpaque(false);
		//biv4.setBackground(Color.MAGENTA);
		biv4.setPreferredSize(new Dimension((int)WINDOW_WIDTH, (int)WINDOW_HEIGHT / 9));
		
		biv5.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv5.setOpaque(false);
		//biv5.setBackground(Color.GREEN);
		biv5.setPreferredSize(halfWindow);
		
		biv3h1.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv3h1.setOpaque(false);
		
		biv3h2.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv3h2.setOpaque(false);
		
		biv3h3.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv3h3.setOpaque(false);
		
		biv4h1.setLayout(new FlowLayout(FlowLayout.CENTER));
		//biv4h1.setBackground(Color.ORANGE);
		
		biv4h1.setOpaque(false);
		
		biv4h2.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv4h2.setOpaque(false);
	
		biv5List.setLayout(new FlowLayout(FlowLayout.CENTER));
		biv5List.setOpaque(false);
		biv5List.setPreferredSize(new Dimension(((int)(this.getWidth()/1.25)), ((int)this.getHeight()/3)));
		
		
		biMain.add(biv1);
		biMain.add(biv2);
		biMain.add(biv3);
		biMain.add(biv4);
		biMain.add(biv5);
		
		biv1.add(biTitleLabel);
		
		biv2.add(biISBNLabel);
		biv2.add(biISBNText);
		
		biv3.add(biv3h1);
		biv3.add(biv3h2);
		biv3.add(biv3h3);
		
		biv4.add(biv4h1);
		biv4.add(biv4h2);
		
		biv3h1.add(biClearButton);
		biv3h2.add(biSearchButton);
		biv3h3.add(biBackButton);
		
		
		biv4h1.add(biv4h1Results);
		
		biv5.add(biv5List);
		
		
		biSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
										
				addTempBooks(biISBNText.getText());
				
				biISBNText.setText("");
				
				
			}
		});		
		
		biBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				
				biMain.setVisible(false);
				
				
				employeeMainMenu.repaint();
				employeeMainMenu.setVisible(true);
				
				
			}
		});
		
		biClearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
										
				createTempBooks();
				biv4h1Results.setText("");
				biv4h1.setOpaque(false);
				bookInfoTable();
				biMain.repaint();
				
				
			}
		});		
	
		biBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	biBackButton.setBackground(Color.GRAY);
		    	biBackButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	biBackButton.setBackground(UIManager.getColor("control"));
		    	biBackButton.setForeground(Color.BLACK);
		    }
		});		

		biSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	biSearchButton.setBackground(Color.GRAY);
		    	biSearchButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	biSearchButton.setBackground(UIManager.getColor("control"));
		    	biSearchButton.setForeground(Color.BLACK);
		    }
		});		

		biClearButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	biClearButton.setBackground(Color.GRAY);
		    	biClearButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	biClearButton.setBackground(UIManager.getColor("control"));
		    	biClearButton.setForeground(Color.BLACK);
		    }
		});		

		biISBNText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {					
					
					if (biSearchButton.isDisplayable()) {
						
						addTempBooks(biISBNText.getText());
						
						biISBNText.setText("");
						
							}
					}
					      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {			
				
			}
		});

		
		

		this.add(biMain);
		this.pack();
		this.setVisible(true);
	
	
	}
	
public void bookInfoTable() {
		
		conn = null;		
		stmt = null;			
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			
			sql = "SELECT * FROM tempBooks";			
					
			rs = stmt.executeQuery(sql);			
			
			JTable table = new JTable(buildTableModel(rs));
			JScrollPane tableContainer = new JScrollPane(table);
			
			tableContainer.setPreferredSize(new Dimension(biv5List.getWidth(), biv5List.getHeight()));
			biv5List.removeAll();
			biv5List.add(tableContainer);
			//biv5List.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
			
			this.setVisible(true);
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
	public void createTempBooks() {
		
		
		conn = null;		
		stmt = null;			
		conn2 = null;
		stmt2 = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt2 = conn2.createStatement();				
			
			sql="drop table if exists tempBooks";
			sql2 = "CREATE TABLE tempBooks"
			+"(ISBN DECIMAL(20,0) PRIMARY KEY,"+
			"Author CHAR(50),"+
			"Title CHAR(80),"+
			"Edition CHAR(5),"+
			"PubYear Decimal(4,0),"+
			"Category CHAR(20),"+
			"Publisher CHAR(30),"+
			"Quantity Decimal(4,0),"+
			"Price Decimal (5,2));";			
					
			update = stmt.executeUpdate(sql);			
			update = stmt2.executeUpdate(sql2);
			
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
public void addTempBooks(String tempISBN) {
		
		
		conn = null;		
		stmt = null;			
		conn2 = null;
		stmt2 = null;
		conn3 = null;
		stmt3 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt = conn.createStatement();					
			conn2 = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt2 = conn2.createStatement();				
			conn3 = DriverManager.getConnection(DB_URL,USER,PASS);					
			stmt3 = conn3.createStatement();				
			
			sql="select * from books where ISBN='"+tempISBN+"'";
			sql2="select * from tempBooks where ISBN='"+tempISBN+"'";
			rs = stmt.executeQuery(sql);
			rs2= stmt2.executeQuery(sql2);
			
			//make sure that tempBooks does NOT contain the book you want
			//to add
			if(!rs2.next()){
			if(rs.next()) {
				String a = rs.getString("ISBN");
				String b = rs.getString("Author");
				String c = rs.getString("Title");
				String d = rs.getString("Edition");
				String e = rs.getString("PubYear");
				String f = rs.getString("Category");
				String g = rs.getString("Publisher");
				String h = rs.getString("Quantity");
				String i = rs.getString("Price");
				
				sql2="insert into tempBooks VALUES ('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"')";
				update = stmt3.executeUpdate(sql2);
				bookInfoTable();
				biGoodSearch();
		
			}
			else {
				biBadSearch();
				
			}
			}else {
				biNoSearch();
			}
			
			
		} catch(SQLException se) {
		      //Handle errors for JDBC
			   System.out.println("1");
		      se.printStackTrace();
		} catch(Exception e) {
		      //Handle errors for Class.forName
			   System.out.println("2");
		       e.printStackTrace();
		} finally {	      
		      try {
		         if(stmt!=null)
		            stmt.close();
		      } catch(SQLException se2) {
		    	  	System.out.println("3");
		      }
		      try {
		         if(conn!=null)
		            conn.close();
		      } catch(SQLException se) {
		    	  	System.out.println("4");
		    	  	se.printStackTrace();
		      }
		}
	}
		
		public void biGoodSearch() {
			//biv4h1.removeAll();
		
			biv4.add(biv4h1);
			biv4h1.add(biv4h1Results);
			
			
			@SuppressWarnings("serial")
			Timer timer = new Timer(50, new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent ae) {
			    	biv4h1Results.setText("Book found");
					
					biv4h1.setOpaque(true);
					biv4h1.setBackground(Color.GREEN);
			    }
			
		});
			timer.setRepeats(false);
			timer.start();
			
			
			@SuppressWarnings("serial")
			Timer timer2 = new Timer(3000, new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent ae) {
			    	//biv4h1.removeAll();
			    	biv4h1.setOpaque(false);
			    	biv4h1Results.setText("");
			    	biMain.repaint();
			    }
			
		});
			timer2.setRepeats(false);
			timer2.start();	
	
	
	
	
}
		
		public void biBadSearch() {
			//biv4h1.removeAll();

			biv4.add(biv4h1);
			biv4h1.add(biv4h1Results);
			
			
			@SuppressWarnings("serial")
			Timer timer = new Timer(50, new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent ae) {
			    	biv4h1Results.setText("Book not found");
					
					biv4h1.setOpaque(true);
					biv4h1.setBackground(Color.RED);
			    }
			
		});
			timer.setRepeats(false);
			timer.start();
			
			
			@SuppressWarnings("serial")
			Timer timer2 = new Timer(3000, new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent ae) {
			    	//biv4h1.removeAll();
			    	biv4h1.setOpaque(false);
			    	biv4h1Results.setText("");
			    	biMain.repaint();
			    }
			
		});
			timer2.setRepeats(false);
			timer2.start();	
	
	
	
}
		
		
		public void biNoSearch() {
			//biv4h1.removeAll();

			biv4.add(biv4h1);
			biv4h1.add(biv4h1Results);
			
			
			@SuppressWarnings("serial")
			Timer timer = new Timer(50, new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent ae) {
			    	biv4h1Results.setText("Book already in list");
					
					biv4h1.setOpaque(true);
					biv4h1.setBackground(Color.ORANGE);
			    }
			
		});
			timer.setRepeats(false);
			timer.start();
			
			
			@SuppressWarnings("serial")
			Timer timer2 = new Timer(3000, new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent ae) {
			    	//biv4h1.removeAll();
			    	biv4h1.setOpaque(false);
			    	biv4h1Results.setText("");
			    	biMain.repaint();
			    }
			
		});
			timer2.setRepeats(false);
			timer2.start();	
	
	
	
}
		
		public void employeeOrders() {
			
			
			eoMain = new JPanel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D)g;
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
					setBackground(panelColor);
					
					GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
					
					g2d.setPaint(gp);
					g2d.fillRect(0,  0, getWidth(), getHeight());
				}
			};
			
			
			eoTitleLabel= new JLabel("List of your orders");
			eoTitleLabel.setFont(new Font("Serif", Font.PLAIN, 30));
			eoTitleLabel.setForeground(Color.WHITE);
			
			eoMain.setLayout(new BoxLayout(eoMain, BoxLayout.Y_AXIS));
			eoMain.setPreferredSize(fullWindow);
			
			eov1.setLayout(new FlowLayout(FlowLayout.CENTER));
			eov1.setOpaque(false);;
			

			eov2.setLayout(new FlowLayout(FlowLayout.CENTER));
			eov2.setOpaque(false);;

			eov3.setLayout(new FlowLayout(FlowLayout.CENTER));
			eov3.setOpaque(false);;

			eoBackButton = new JButton("Back");
			eoBackButton.setFont(new Font(null, Font.PLAIN, 28));
			
			eoMain.add(eov1);
			eoMain.add(eov2);
			eoMain.add(eov3);
			
			eov1.add(eoTitleLabel);
			
			eov2.add(eoBackButton);
			
			employeeOrdersTable();
			
			
			eoBackButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {				
					
					
					eoMain.setVisible(false);
					
					
					employeeMainMenu.repaint();
					employeeMainMenu.setVisible(true);
					
					
				}
			});
			
			eoBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
			    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	eoBackButton.setBackground(Color.GRAY);
			    	eoBackButton.setForeground(Color.WHITE);
			    }

			    public void mouseExited(java.awt.event.MouseEvent evt) {
			    	eoBackButton.setBackground(UIManager.getColor("control"));
			    	eoBackButton.setForeground(Color.BLACK);
			    }
			});		

			
			
			this.add(eoMain);
			this.pack();
			this.setVisible(true);
			
			@SuppressWarnings("serial")
			Timer timer2 = new Timer(100, new AbstractAction() {
			    @Override
			    public void actionPerformed(ActionEvent ae) {
			    	employeeOrdersTable();
			    }
			
		});
			timer2.setRepeats(false);
			timer2.start();	
	
			
			
		
		}
		
		public void employeeOrdersTable() {
			
			conn = null;		
			stmt = null;			
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL,USER,PASS);					
				stmt = conn.createStatement();					
				
				sql = "SELECT * FROM orders where employeeID='"+employeeID+"'";			
						
				rs = stmt.executeQuery(sql);			
				
				JTable table = new JTable(buildTableModel(rs));
				JScrollPane tableContainer = new JScrollPane(table);
				
				tableContainer.setPreferredSize(new Dimension(eov3.getWidth(), eov3.getHeight()));
				//eov3.removeAll();
				eov3.add(tableContainer);
				
				this.setVisible(true);
				
				@SuppressWarnings("serial")
				Timer timer2 = new Timer(100, new AbstractAction() {
				    @Override
				    public void actionPerformed(ActionEvent ae) {
				    	eoMain.repaint();
				    	
				    }
				
			});
				timer2.setRepeats(false);
				timer2.start();	
				
			} catch(SQLException se) {
			      //Handle errors for JDBC
				   System.out.println("1");
			      se.printStackTrace();
			} catch(Exception e) {
			      //Handle errors for Class.forName
				   System.out.println("2");
			       e.printStackTrace();
			} finally {	      
			      try {
			         if(stmt!=null)
			            stmt.close();
			      } catch(SQLException se2) {
			    	  	System.out.println("3");
			      }
			      try {
			         if(conn!=null)
			            conn.close();
			      } catch(SQLException se) {
			    	  	System.out.println("4");
			    	  	se.printStackTrace();
			      }
			}
		}
		
		public void updateStatus() {
			

			
			usMain = new JPanel() {
				/**
				 * 
				 */
			
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2d = (Graphics2D)g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				
				setBackground(panelColor);
				
				GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
				
				g2d.setPaint(gp);
				g2d.fillRect(0,  0, getWidth(), getHeight());
			}	
		};
		
		
		usTitleLabel= new JLabel("Set Update Status");
		usTitleLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		usTitleLabel.setForeground(Color.WHITE);
		
		usStatusLabel= new JLabel("Status: ");
		usStatusLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		usStatusLabel.setForeground(Color.WHITE);
		
		usOrderNumLabel= new JLabel("Order #: ");
		usOrderNumLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		usOrderNumLabel.setForeground(Color.WHITE);
		
		usResultsLabel= new JLabel("");
		usResultsLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		usResultsLabel.setForeground(Color.WHITE);
		
		usBackButton = new JButton("Back");
		usBackButton.setFont(new Font(null, Font.PLAIN, 28));
		
		usSubmitButton = new JButton("Submit");
		usSubmitButton.setFont(new Font(null, Font.PLAIN, 28));
		
		
		usOrderNumText = new JTextField(15);
		
		usStatusText = new JTextField(15);
		
		usMain.setLayout(new BoxLayout(usMain, BoxLayout.Y_AXIS));
		usMain.setPreferredSize(fullWindow);
		
		usv1.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv1.setOpaque(false);
		

		usv2.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv2.setOpaque(false);

		usv3.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv3.setOpaque(false);
		
		usv4.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv4.setOpaque(false);
		
		usv2h1.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv2h1.setOpaque(false);
	

		usv2h2.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv2h2.setOpaque(false);

		usv2h2Results.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv2h2Results.setOpaque(false);
		
		usv2h1Grid.setLayout(new GridLayout(2, 2));
		usv2h1Grid.setOpaque(false);
		//usv2h1Grid.setBackground(Color.GREEN);
		
		
		usg1.setLayout(new FlowLayout(FlowLayout.CENTER));
		usg1.setOpaque(false);

		usg2.setLayout(new FlowLayout(FlowLayout.CENTER));
		usg2.setOpaque(false);

		usg3.setLayout(new FlowLayout(FlowLayout.CENTER));
		usg3.setOpaque(false);

		usg4.setLayout(new FlowLayout(FlowLayout.CENTER));
		usg4.setOpaque(false);

		usv3h1.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv3h1.setOpaque(false);
		
		usv3h2.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv3h2.setOpaque(false);

		usv4List.setLayout(new FlowLayout(FlowLayout.CENTER));
		usv4List.setOpaque(false);

		usv4List.setPreferredSize(new Dimension(((int)(this.getWidth()/1.5)), ((int)this.getHeight()/4)));
		usv4List.setBackground(Color.GREEN);
		
		usMain.add(usv1);
		usMain.add(usv2);
		usMain.add(usv3);
		usMain.add(usv4);
		
		usv1.add(usTitleLabel);

		usv2.add(usv2h1);
		usv2.add(usv2h2);
		
		usv2h1.add(usv2h1Grid);
		
		usv2h1Grid.add(usg1);
		usv2h1Grid.add(usg2);
		usv2h1Grid.add(usg3);
		usv2h1Grid.add(usg4);
		
		usv2h2.add(usv2h2Results);
		
		usv2h2Results.add(usResultsLabel);
		
		usv3.add(usv3h1);
		usv3.add(usv3h2);
		
		usg1.add(usOrderNumLabel);
		usg2.add(usOrderNumText);
		usg3.add(usStatusLabel);
		usg4.add(usStatusText);

		usv4.add(usv4List);
		
		usv3h1.add(usSubmitButton);
		usv3h2.add(usBackButton);
		
		usBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				
				usMain.setVisible(false);
				usv2h2Results.setOpaque(false);
				usResultsLabel.setText("");
				usv2h2Results.setBorder(null);
				
				
				
				employeeMainMenu.repaint();
				employeeMainMenu.setVisible(true);
				
				
			}
		});
		
		usBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	usBackButton.setBackground(Color.GRAY);
		    	usBackButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	usBackButton.setBackground(UIManager.getColor("control"));
		    	usBackButton.setForeground(Color.BLACK);
		    }
		});		
		
		usSubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				
				updateStatusSQL(employeeID, usOrderNumText.getText(), usStatusText.getText());

				usOrderNumText.setText("");
			//	usv2h2Results.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				
				
				usStatusText.setText("");
				updateStatusTable();
				usv4List.repaint();
				
				
			}
		});
		
		usSubmitButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	usSubmitButton.setBackground(Color.GRAY);
		    	usSubmitButton.setForeground(Color.WHITE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	usSubmitButton.setBackground(UIManager.getColor("control"));
		    	usSubmitButton.setForeground(Color.BLACK);
		    }
		});	

		usOrderNumText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {					
					
					if (usSubmitButton.isDisplayable()) {
						
						updateStatusSQL(employeeID, usOrderNumText.getText(), usStatusText.getText());

						usOrderNumText.setText("");
					//	usv2h2Results.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
						
						
						usStatusText.setText("");
						updateStatusTable();
						usv4List.repaint();
							}
					}
					      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {			
				
			}
		});


		usStatusText.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {					
					
					if (usSubmitButton.isDisplayable()) {
						
						updateStatusSQL(employeeID, usOrderNumText.getText(), usStatusText.getText());

						usOrderNumText.setText("");
						//usv2h2Results.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
						
						
						usStatusText.setText("");
						updateStatusTable();
						usv4List.repaint();
							}
					}
					      
		    }

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {			
				
			}
		});



		
		
		this.add(usMain);
		this.pack();
		this.setVisible(true);

			

			
		}
		
		
		public void updateStatusSQL(String empid, String ordern, String stat) {
			
			
			conn = null;		
			stmt = null;			
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL,USER,PASS);					
				stmt = conn.createStatement();					
				
				sql2 = "select * from orders where EmployeeID ="+empid+" and orderNum ="+ordern;
				rs2 = stmt2.executeQuery(sql2);
				
				if(!rs2.next()){
					usResultsLabel.setText("Not your Order");
					usv2h2Results.setOpaque(true);
					usv2h2Results.setBackground(Color.RED);
				}
				else {
					usResultsLabel.setText("Success");
					usv2h2Results.setOpaque(true);
					usv2h2Results.setBackground(Color.GREEN);
					
					sql = "CALL updateStatus("+ordern+",'"+stat+"')";
					update = stmt.executeUpdate(sql);
					
				}	
			} catch(SQLException se) {
			      //Handle errors for JDBC
				   System.out.println("1");
			      se.printStackTrace();
			} catch(Exception e) {
			      //Handle errors for Class.forName
				   System.out.println("2");
			       e.printStackTrace();
			} finally {	      
			      try {
			         if(stmt!=null)
			            stmt.close();
			      } catch(SQLException se2) {
			    	  	System.out.println("3");
			      }
			      try {
			         if(conn!=null)
			            conn.close();
			      } catch(SQLException se) {
			    	  	System.out.println("4");
			    	  	se.printStackTrace();
			      }
			}
		}
			
		

		public void updateStatusTable() {
			
			conn = null;		
			stmt = null;			
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL,USER,PASS);					
				stmt = conn.createStatement();					
				
				sql = "SELECT * FROM orders where employeeID='"+employeeID+"'";			
						
				rs = stmt.executeQuery(sql);			
				
				JTable table = new JTable(buildTableModel(rs));
				JScrollPane tableContainer = new JScrollPane(table);
				
				tableContainer.setPreferredSize(new Dimension(usv4List.getWidth(), usv4List.getHeight()));
				//eov3.removeAll();
				usv4List.removeAll();
				usv4List.add(tableContainer);
			//	usv4List.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				this.setVisible(true);
				
				@SuppressWarnings("serial")
				Timer timer2 = new Timer(100, new AbstractAction() {
				    @Override
				    public void actionPerformed(ActionEvent ae) {
				    	usMain.repaint();
				    	
				    }
				
			});
				timer2.setRepeats(false);
				timer2.start();	
				
			} catch(SQLException se) {
			      //Handle errors for JDBC
				   System.out.println("1");
			      se.printStackTrace();
			} catch(Exception e) {
			      //Handle errors for Class.forName
				   System.out.println("2");
			       e.printStackTrace();
			} finally {	      
			      try {
			         if(stmt!=null)
			            stmt.close();
			      } catch(SQLException se2) {
			    	  	System.out.println("3");
			      }
			      try {
			         if(conn!=null)
			            conn.close();
			      } catch(SQLException se) {
			    	  	System.out.println("4");
			    	  	se.printStackTrace();
			      }
			}
		}

		public void addUpdateBook() {
			 
			
			
			
			
			
			auMain = new JPanel() {
				/**
				 * 
				 */
			
				private static final long serialVersionUID = 1L;

				@Override
				protected void paintComponent(Graphics g) {
					Graphics2D g2d = (Graphics2D)g;
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					
					setBackground(panelColor);
					
					GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter().brighter(), 0, getHeight(), getBackground().darker().darker());
					
					g2d.setPaint(gp);
					g2d.fillRect(0,  0, getWidth(), getHeight());
				}
			};		
			auMain.setLayout(new BoxLayout(auMain, BoxLayout.Y_AXIS));
			auMain.setPreferredSize(fullWindow);
				
			auTop = new JPanel();
			auTop.setLayout(new FlowLayout(FlowLayout.CENTER));
			auTop.setOpaque(false);
			
			auMiddle = new JPanel();
			auMiddle.setLayout(new FlowLayout(FlowLayout.CENTER));
			auMiddle.setOpaque(false);
			 	
			auBottom = new JPanel();
			auBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
			auBottom.setOpaque(false);
			 	
			auInputGrid = new JPanel();
			auInputGrid.setLayout(new GridLayout(5, 4));
			auInputGrid.setOpaque(false);
			 	
			auInput1 = new JPanel();
			auInput1.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput1.setOpaque(false);
				
			auInput2 = new JPanel();
			auInput2.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput2.setOpaque(false);
			 	
			auInput3 = new JPanel();
			auInput3.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput3.setOpaque(false);
			 
			auInput4 = new JPanel();
			auInput4.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput4.setOpaque(false);
			 	
			auInput5 = new JPanel();
			auInput5.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput5.setOpaque(false);
				
			auInput6 = new JPanel();
			auInput6.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput6.setOpaque(false);
			 	
			auInput7 = new JPanel();
			auInput7.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput7.setOpaque(false);
			 	
			auInput8 = new JPanel();
			auInput8.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput8.setOpaque(false);
			 	
			auInput9 = new JPanel();
			auInput9.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput9.setOpaque(false);
				
			auInput10 = new JPanel();
			auInput10.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput10.setOpaque(false);
			 	
			auInput11 = new JPanel();
			auInput11.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput11.setOpaque(false);
			 	
			auInput12 = new JPanel();
			auInput12.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput12.setOpaque(false);
			 	
			auInput13 = new JPanel();
			auInput13.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput13.setOpaque(false);
			 
			auInput14 = new JPanel();
			auInput14.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput14.setOpaque(false);
			 	
			auInput15 = new JPanel();
			auInput15.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput15.setOpaque(false);
			 
			auInput16 = new JPanel();
			auInput16.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput16.setOpaque(false);
			
			auInput17 = new JPanel();
			auInput17.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput17.setOpaque(false);
			
			auInput18 = new JPanel();
			auInput18.setLayout(new FlowLayout(FlowLayout.CENTER));
			auInput18.setOpaque(false);
			
			auMessagePanel = new JPanel();
			auMessagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
			auMessagePanel.setOpaque(false);		
			auMessagePanel.setPreferredSize(new Dimension( (int)(WINDOW_WIDTH / 8), (int)(WINDOW_HEIGHT / 14) ));
				
			auBurn = new JPanel();
			auBurn.setLayout(new FlowLayout(FlowLayout.CENTER));
			auBurn.setOpaque(false);
			
			auButtonPan = new JPanel();
			auButtonPan.setLayout(new FlowLayout(FlowLayout.CENTER));
			auButtonPan.setOpaque(false);		
			 
			auVeryBottom = new JPanel();
			auVeryBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
			auVeryBottom.setOpaque(false);
			
			 
				
			isbnLab = new JLabel("ISBN:");
			isbnLab.setFont(new Font(null, Font.PLAIN, 22));
			isbnLab.setForeground(Color.WHITE);
			 
			authLab = new JLabel("Author:");
			authLab.setFont(new Font(null, Font.PLAIN, 22));
			authLab.setForeground(Color.WHITE);
			 
			titLab = new JLabel("Title:");
			titLab.setFont(new Font(null, Font.PLAIN, 22));
			titLab.setForeground(Color.WHITE);
			 
			edLab = new JLabel("Edition:");
			edLab.setFont(new Font(null, Font.PLAIN, 22));
			edLab.setForeground(Color.WHITE);
			 
			yearLab = new JLabel("Year:");
			yearLab.setFont(new Font(null, Font.PLAIN, 22));
			yearLab.setForeground(Color.WHITE);
			 
			catLab = new JLabel("Category:");
			catLab.setFont(new Font(null, Font.PLAIN, 22));
			catLab.setForeground(Color.WHITE);
			 
			pubLab = new JLabel("Publisher");
			pubLab.setFont(new Font(null, Font.PLAIN, 22));
			pubLab.setForeground(Color.WHITE);
			 
			quantLab = new JLabel("Quantity:");
			quantLab.setFont(new Font(null, Font.PLAIN, 22));
			quantLab.setForeground(Color.WHITE);
			 
			priceLab = new JLabel("Price:");
			priceLab.setFont(new Font(null, Font.PLAIN, 22));
			priceLab.setForeground(Color.WHITE);
			 
			auTitle = new JLabel("Add Books To System");
			auTitle.setFont(new Font("Serif", Font.BOLD, 60));
			auTitle.setForeground(Color.WHITE);
			 
			auMessageLab = new JLabel("");
			auMessageLab.setFont(new Font("Serif", Font.ITALIC, 40));
			auMessageLab.setForeground(Color.WHITE);
			 
			 
			 
			isbnT = new JTextField(15);
			isbnT.setFont(new Font(null, Font.PLAIN, 20));
			 
			authT = new JTextField(15);
			authT.setFont(new Font(null, Font.PLAIN, 20));
			 
			titT = new JTextField(15);
			titT.setFont(new Font(null, Font.PLAIN, 20));
			 
			edT = new JTextField(15);
			edT.setFont(new Font(null, Font.PLAIN, 20));
			 
			yearT = new JTextField(15);
			yearT.setFont(new Font(null, Font.PLAIN, 20));
			 
			catT = new JTextField(15);
			catT.setFont(new Font(null, Font.PLAIN, 20));
			 
			pubT = new JTextField(15);
			pubT.setFont(new Font(null, Font.PLAIN, 20));
			 
			quantT = new JTextField(15);
			quantT.setFont(new Font(null, Font.PLAIN, 20));
			 
			priceT = new JTextField(15);
			priceT.setFont(new Font(null, Font.PLAIN, 20));
			 
			 
			 
			auBackButton = new JButton("Back To Menu");
			auBackButton.setFont(new Font(null, Font.PLAIN, 28));
			 
			auAddButton = new JButton("Add");
			auAddButton.setFont(new Font(null, Font.PLAIN, 20));
			 
			auBackButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {				
						
					auMessageLab.setText("");
					auMessagePanel.setBorder(null);
						
					auMain.setVisible(false);
					
						
					employeeMainMenu.repaint();
					employeeMainMenu.setVisible(true);
						
						
				}
			});
				
			auBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
				   public void mouseEntered(java.awt.event.MouseEvent evt) {
					   auBackButton.setBackground(Color.GRAY);
					   auBackButton.setForeground(Color.WHITE);
				    }

				   public void mouseExited(java.awt.event.MouseEvent evt) {
				    	auBackButton.setBackground(UIManager.getColor("control"));
				    	auBackButton.setForeground(Color.BLACK);
				    }
			});	
				
			auAddButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {			
						
					String isbn = isbnT.getText();
					String author = authT.getText();
					String title = titT.getText();
					String ed = edT.getText();				
					String year = yearT.getText();
					String cat = catT.getText();
					String pub = pubT.getText();
					String quant = quantT.getText();
					String price = priceT.getText();
					
					isbnT.setText("");
					authT.setText("");
					titT.setText("");
					edT.setText("");
					yearT.setText("");
					catT.setText("");
					pubT.setText("");
					quantT.setText("");
					priceT.setText("");
					
					runAddBookSql(isbn, author, title, ed, year, cat, pub, quant, price);
					
					auMessageLab.setText("Book Added!");
					auMessagePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE));
				}
			});
				
			auAddButton.addMouseListener(new java.awt.event.MouseAdapter() {
				   public void mouseEntered(java.awt.event.MouseEvent evt) {
				    	auAddButton.setBackground(Color.GRAY);
				    	auAddButton.setForeground(Color.WHITE);
				   }

				   public void mouseExited(java.awt.event.MouseEvent evt) {
				    	auAddButton.setBackground(UIManager.getColor("control"));
				    	auAddButton.setForeground(Color.BLACK);
				   }
			});	
			 
			 
			 
			 
			 
			auTop.add(auTitle);
			 
			auInput1.add(isbnLab);
			auInput2.add(isbnT);
			auInput3.add(titLab);
			auInput4.add(titT);
			auInput5.add(authLab);
			auInput6.add(authT);
			auInput7.add(pubLab);
			auInput8.add(pubT);
			auInput9.add(edLab);
			auInput10.add(edT);
			 
			auInput11.add(yearLab);
			auInput12.add(yearT);
			auInput13.add(catLab);
			auInput14.add(catT);
			auInput15.add(quantLab);
			auInput16.add(quantT);
			auInput17.add(priceLab);
			auInput18.add(priceT);
			auButtonPan.add(auAddButton);
			 
			auInputGrid.add(auInput1);
			auInputGrid.add(auInput2);
			auInputGrid.add(auInput11);
			auInputGrid.add(auInput12);
			auInputGrid.add(auInput3);
			auInputGrid.add(auInput4);
			auInputGrid.add(auInput13);
			auInputGrid.add(auInput14);
			auInputGrid.add(auInput5);
			auInputGrid.add(auInput6);
			auInputGrid.add(auInput15);
			auInputGrid.add(auInput16);
			auInputGrid.add(auInput7);
			auInputGrid.add(auInput8);
			auInputGrid.add(auInput17);
			auInputGrid.add(auInput18);
			auInputGrid.add(auInput9);
			auInputGrid.add(auInput10);
			auInputGrid.add(auBurn);
			auInputGrid.add(auButtonPan);
			 
			auMiddle.add(auInputGrid);
			 
			auMessagePanel.add(auMessageLab);
			 
			auBottom.add(auMessagePanel);
			 
			auVeryBottom.add(auBackButton);
			 
			auMain.add(auTop);
			auMain.add(auMiddle);
			auMain.add(auBottom);
			auMain.add(auVeryBottom);
			 
			this.add(auMain);
			this.pack();
		}
		
		public void runAddBookSql(String isbn, String author, String title, String ed, String year, String cat, String pub, String quant, String price) {
			
			conn = null;	
			stmt = null;
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL,USER,PASS);				
				stmt = conn.createStatement();	
						
				
				sql = "CALL addBook ('" + isbn + "', '" + author + "', '" + title + "', '" + ed + "', '" + year + "', '" + cat + "', '" + pub + "', '" + quant + "', '" + price + "')";
				
				
				update = stmt.executeUpdate(sql);		
				
			} catch(SQLException se) {
			      //Handle errors for JDBC
				   System.out.println("1");
			      se.printStackTrace();
			} catch(Exception e) {
			      //Handle errors for Class.forName
				   System.out.println("2");
			       e.printStackTrace();
			} finally {	      
			      try {
			         if(stmt!=null)
			            stmt.close();
			      } catch(SQLException se2) {
			    	  	System.out.println("3");
			      }
			      try {
			         if(conn!=null)
			            conn.close();
			      } catch(SQLException se) {
			    	  	System.out.println("4");
			    	  	se.printStackTrace();
			      }
			}
		}
			
	}



	
	
	
