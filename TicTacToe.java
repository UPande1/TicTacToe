import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Utkarsh's project on TicTacToe Game


public class TicTacToe implements ActionListener {


JFrame window = new JFrame( "Tic-Tac-Toe");

//Creating panels for each individual section of the window
//Title panel
JPanel titlePanel = new JPanel();

//main panel of buttons where the game will be played
JPanel buttonPanel = new JPanel();

// the score panel
JPanel scorePanel = new JPanel();

//panel for the reload and closed buttons
JPanel reloadPanel = new JPanel();


//Now we need to define what every section will do 
//like the button pannel will be used for playing and it will contain all the buttons
// so we declare all the buttons required to the button panel similar ways for title panel
//which we wiol declare it to be the textfield


//declaring the work of panels

JLabel textfield = new JLabel();
JButton[] buttons = new JButton[9];
JButton reloadButton = new JButton("Reload");
JButton closeButton = new JButton("Close");
JLabel player1 = new JLabel();
JLabel player2 = new JLabel();

int player1Score = 0;
int player2Score = 0;


boolean player1Turn;
/*
Now Here we are setting the window where whenever the game runs all the content will be shown
Also we are setting up each panel and its interface
 */
TicTacToe(){ 
    //Base window settngs

    window.setSize(1200, 800);
    window.getContentPane().setBackground(new Color(100,100,100));
    window.setLayout(new BorderLayout());
    window.setVisible(true);
    //ends
        
    //setting title panel 
    titlePanel.setLayout(new BorderLayout());
    titlePanel.setBounds(0,0,1200,200);
    titlePanel.add(textfield);



    //Title panel textfield settings
    textfield.setBackground(new Color(185, 243, 252) );
    textfield.setForeground(new Color(25,25,25));
    textfield.setFont(new Font("Sans Serif", Font.BOLD  , 75));
    textfield.setHorizontalAlignment(JLabel.CENTER);
    textfield.setText("Tic-Tac-Toe");
    textfield.setOpaque(true);

    //titlepanel ends

    //button panel starts
    buttonPanel.setBackground(new Color(227,246,255));
    buttonPanel.setLayout(new GridLayout(3,3));
    for(int i = 0; i < 9; i++ ){ 
        buttons[i] = new JButton();
        buttonPanel.add(buttons[i]);
        buttons[i].setFont(new Font("Sans Serif", Font.BOLD  , 100));
        buttons[i].setFocusable(false);
        buttons[i].addActionListener(this);


    }
    //setting the reload pannel with grid layout
    reloadPanel.setLayout(new GridLayout(1, 2));
    // setting the background color of the JPanel
    reloadPanel.setBackground(new Color(174, 226, 255));
    //adding buttons to reload panel
    reloadPanel.add(reloadButton);
    reloadPanel.add(closeButton);
    // setting the font of the reload button
    reloadButton.setFont(new Font("Sans Serif", Font.BOLD, 50));
    // setting color of the reload button
    reloadButton.setBackground(new Color(174, 226, 255));
    // setting the reload button to be not focusable
    reloadButton.setFocusable(false);
    // setting an action listener to the reload button
    reloadButton.addActionListener(this);
    // setting the font of the close button
    closeButton.setFont(new Font("Sans Serif", Font.BOLD, 50));
    // setting color of the close button
    closeButton.setBackground(new Color(174, 226, 255));
    // setting the close button to be not focusable
    closeButton.setFocusable(false);
    // adding action listener to the close button
    closeButton.addActionListener(this);
    // add the close button to the JPanel

    // set the layout of the scorePanel
    scorePanel.setLayout(new GridLayout(2, 1));
    
    // set the background color of the scorePanel
    scorePanel.setBackground(new Color(147, 198, 231));
    // set the font of the score label
    player1.setFont(new Font("Sans Serif", Font.BOLD, 20));
    // set the text of the score label such that it displays the score 
    //of both players in two lines
    //also im adding spaces before and after the text so the panel looks clean
    player1.setText("          Player 1: " + player1Score +  "          ");
    // add the score label to the JPanel
    scorePanel.add(player1);

    // set the font of the score label
    player2.setFont(new Font("Sans Serif", Font.BOLD, 20));
    // set the text of the score label such that it displays the score of 
    //both players in two lines
    player2.setText("          Player 2: " + player2Score + "          ");
    // add the score label to the scorePanel
    scorePanel.add(player2);
    titlePanel.add(textfield);
    
    // add the JPanel to the JFrame
    window.add(titlePanel, BorderLayout.NORTH);
    // add the JPanel to the JFrame
    window.add(buttonPanel);
    // add the JPanel to the JFrame
    window.add(reloadPanel, BorderLayout.SOUTH);
    // add the JPanel to the JFrame
    window.add(scorePanel, BorderLayout.EAST);
    firstTurn();
}

public void firstTurn(){
    try{
        textfield.setText("Tic-Tac-Toe");
        Thread.sleep(1500);
    }
    catch(InterruptedException e){
        e.printStackTrace();
    }

    if(Math.random() < 0.5){
        player1Turn =true;
        textfield.setText("Player 1 Turn");
    }else{
        player1Turn = false;
        textfield.setText("Player 2 Turn");
    }
}
@Override
public void actionPerformed(ActionEvent e){
    for(int i=0;i<9;i++){
        if(e.getSource() == buttons[i]){
            if(player1Turn){
                if(buttons[i].getText()==""){
                    buttons[i].setForeground(new Color(100, 92, 187));
                    buttons[i].setText("X");
                    player1Turn = false;
                    textfield.setText("Player 2 turn");
                    // change color of the textfield
                    textfield.setForeground(new Color(103, 65, 136));
                    check();
                }
            } else {
                // set the text of the JButton array to O
                if (buttons[i].getText() == "") {
                    buttons[i].setForeground(new Color(103, 65, 136));
                    buttons[i].setText("O");
                    player1Turn = true;
                    textfield.setText("Player 1 turn");
                    // change color of the textfield
                    textfield.setForeground(new Color(100, 92, 187));
                    check();
                }
            }
        }
    }
    if(e.getSource() == reloadButton){
        reload();
    }
    if(e.getSource() == closeButton){
        window.dispose();
    }
}

public void reload(){
    for(int i=0;i<9;i++){
        buttons[i].setText("");
        buttons[i].setBackground(new Color(240, 240, 240));
        buttons[i].setEnabled(true);
    }
    firstTurn();
}

//checking the buttons to check and display if somebody wins
public void check(){
    // if statement
    if (
        (buttons[0].getText() == "X") &&
        (buttons[1].getText() == "X") &&
        (buttons[2].getText() == "X")
    ) {
        xWins(0, 1, 2);
    }
    // if statement
    if (
        (buttons[3].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[5].getText() == "X")
    ) {
        xWins(3, 4, 5);
    }

    // if statement
    if (
        (buttons[6].getText() == "X") &&
        (buttons[7].getText() == "X") &&
        (buttons[8].getText() == "X")
    ) {
        xWins(6, 7, 8);
    }
    // if statement
    if (
        (buttons[0].getText() == "X") &&
        (buttons[3].getText() == "X") &&
        (buttons[6].getText() == "X")
    ) {
        xWins(0, 3, 6);
    }
    // if statement
    if (
        (buttons[1].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[7].getText() == "X")
    ) {
        xWins(1, 4, 7);
    }
    // if statement
    if (
        (buttons[2].getText() == "X") &&
        (buttons[5].getText() == "X") &&
        (buttons[8].getText() == "X")
    ) {
        xWins(2, 5, 8);
    }
    // if statement
    if (
        (buttons[0].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[8].getText() == "X")
    ) {
        xWins(0, 4, 8);
    }
    // if statement
    if (
        (buttons[2].getText() == "X") &&
        (buttons[4].getText() == "X") &&
        (buttons[6].getText() == "X")
    ) {
        xWins(2, 4, 6);
    }
    // if statement
    if (
        (buttons[0].getText() == "O") &&
        (buttons[1].getText() == "O") &&
        (buttons[2].getText() == "O")
    ) {
        oWins(0, 1, 2);
    }
    // if statement
    if (
        (buttons[3].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[5].getText() == "O")
    ) {
        oWins(3, 4, 5);
    }
    //if statement
    if (
        (buttons[6].getText() == "O") &&
        (buttons[7].getText() == "O") &&
        (buttons[8].getText() == "O")
    ) {
        oWins(6, 7, 8);
    }
    //if statement
    if (
        (buttons[0].getText() == "O") &&
        (buttons[3].getText() == "O") &&
        (buttons[6].getText() == "O")
    ) {
        oWins(0, 3, 6);
    }
    // if statement
    if (
        (buttons[1].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[7].getText() == "O")
    ) {
        oWins(1, 4, 7);
    }
    // if statement
    if (
        (buttons[2].getText() == "O") &&
        (buttons[5].getText() == "O") &&
        (buttons[8].getText() == "O")
    ) {
        oWins(2, 5, 8);
    }
    // if statement
    if (
        (buttons[0].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[8].getText() == "O")
    ) {
        oWins(0, 4, 8);
    }
    // if statement
    if (
        (buttons[2].getText() == "O") &&
        (buttons[4].getText() == "O") &&
        (buttons[6].getText() == "O")
    ) {
        oWins(2, 4, 6);
    }
}
//creating a method if x wins, the player score increases and the title panel shows the winner
public void xWins(int a,int b,int c){
    buttons[a].setBackground(Color.green);
    buttons[b].setBackground(Color.green);
    buttons[c].setBackground(Color.green);
    for (int i = 0; i < 9; i++) {
        buttons[i].setEnabled(false);
    }
    textfield.setText("Player 1 wins");
    player1Score++;
    player1.setText("          Player 1: " + player1Score +  "          ");
}
// creating a method as above
public void oWins(int a, int b, int c) {
    // set the text of the textfield to O wins
    buttons[a].setBackground(Color.GREEN);
    buttons[b].setBackground(Color.GREEN);
    buttons[c].setBackground(Color.GREEN);
    for (int i = 0; i < 9; i++) {
        buttons[i].setEnabled(false);
    }
    textfield.setText("Player 2 wins");
    // add 1 to the score of O
    player2Score++;
    // set the text of the playerO_score_label to the oCount
    player2.setText("          Player 2: " + player2Score +  "          ");

}
}









 
