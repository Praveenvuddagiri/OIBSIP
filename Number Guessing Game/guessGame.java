import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class game extends JFrame{
    String name,res;
    JLabel l1,l2,l3,l4,l5,l6;
    JButton b1;
    JTextField t1;
    int score=0,attempt,round,key,i;
    game(String name){
        super("Number guessing game");
        this.name = name;
        key = getRandom();
        attempt = 5;
        round = 1;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l1 = new JLabel();
        l2 = new JLabel();
        l1.setText("Player :- "+name);
        l1.setBounds(30,20,150,30);
        l1.setFont(new Font("SansSerif",1,18));
        l2.setText("Score :- "+score);
        l2.setBounds(290,20,130,30);
        l2.setFont(new Font("SansSerif",1,20));
        l3 = new JLabel();
        l3.setText("Round No. :- "+round);
        l3.setBounds(530,20,150,30);
        l3.setFont(new Font("SansSerif",1,18));
        
        l4 = new JLabel("Guess a number between 1-100 : ");
        l4.setBounds(130,100,280,30);
        l4.setFont(new Font("SansSerif",6,18));
        
        t1 = new JTextField();
        t1.setBounds(420,100,120,30);
        t1.setFont(new Font("SansSerif",4,18));

        b1 = new JButton("Check");
        b1.setBounds(300,180,100,30);
        b1.setFont(new Font("SansSerif",2,18));

        l5 = new JLabel("Attempts Left : "+attempt);
        l5.setBounds(270,240,190,30);
        l5.setFont(new Font("SansSerif",1,18));
        res = "Computer has selected a random number between 1-100, try to guess.";    
        l6 = new JLabel(res);
        l6.setBounds(70,300,600,30);
        l6.setFont(new Font("SansSerif",3,16));
        l6.setForeground(new Color(255,0,0));

        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String in = t1.getText();
                if(in.equals("")){
                    res = "Input Cannot be empty.";
                    l6.setText(res);
                    t1.setText("");
                    return;
                }
                for(i=0;i<in.length();i++){
                    if(!(in.charAt(i)>='0' && in.charAt(i)<='9')){
                        res = "You should only give numbers in input";
                        l6.setText(res);
                        t1.setText("");
                        return;
                    }
                }
                int num = Integer.parseInt(in);
                if(num<0 || num>100){
                    res = "You should only give number between 1-100";
                    l6.setText(res);
                    t1.setText("");
                    return;
                }
                
                if(num == key ){
                    key = getRandom();

                    if(round == 1){
                        res = "Congratulations you had cleared first round successfully.";
                        l6.setText(res);
                        t1.setText("");
                        round++;
                        l3.setText("Round No. :- "+round);

                        updateScore(attempt);
                        attempt=5;
                        l5.setText("Attempts Left : "+attempt);
                        return;
                    }
                    else if(round==2){
                        res = "Congratulations you had cleared second round also.";
                        l6.setText(res);
                        t1.setText("");
                        round++;
                        l3.setText("Round No. :- "+round);
                        updateScore(attempt);
                        attempt=5;
                        l5.setText("Attempts Left : "+attempt);
                        return;
                    }
                    else{
                        updateScore(attempt);
                        attempt=5;
                        newFrame();
                        
                    }
                    
                    
                }
                attempt--;
                getStatus();
                if(attempt==0){
                    round++;
                    if(round>3){
                        newFrame();
                    }
                    l3.setText("Round No. :- "+round);
                    l6.setText("You lost the previous round, Better luck in this round.");
                    attempt = 5;
                    key = getRandom();
                }
                l5.setText("Attempts Left : "+attempt);
        }});

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(t1);
        add(b1);
        add(l5);
        add(l6);
        setSize(700,400);
        setLocationRelativeTo(null); 
        setLayout(null);
        setVisible(true);

        
    }
    protected void newFrame() {
        getContentPane().removeAll();
        getContentPane().repaint();
        setSize(570,300);
        l1.setBounds(50,80,500,30);
        l1.setText("Congratulations, You had completed all the rounds.");
        l1.setForeground(new Color(0,100,0) );
        add(l1);
        l2.setBounds(200,140,250,30);
        l2.setText("Total Score : "+score+"/300");
        add(l2);
        b1.setText("New Game");
        add(b1);
        b1.setBounds(200,190,160,30);
        b1.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new guessGame();
        }});                
    }
    protected void getStatus() {
        int in = Integer.parseInt(t1.getText());
        int diff = key-in;
        if(diff<0){
            diff = Math.abs(diff);
            if(diff>40){
                l6.setText("Number is too greater than computer generated number.");
            }
            else if(diff>15){
                l6.setText("Number is greater than computer generated number.");
            }
            else if(diff>6){
                l6.setText("Number is greater but close to computer generated number.");
            }
            else {
                l6.setText("Number is greater and too close to computer generated number.");
            }
        }
        else{
            if(diff>40){
                l6.setText("Number is too smaller than computer generated number.");
            }
            else if(diff>15){
                l6.setText("Number is smaller than computer generated number.");
            }
            else if(diff>6){
                l6.setText("Number is smaller but close to computer generated number.");
            }
            else {
                l6.setText("Number is smaller and too close to computer generated number.");
            }
        }
    }
    protected void updateScore(int attempt2) {
        if(attempt2==5)
            score+=100;
        else if(attempt2==4){
            score+=80;
        }
        else if(attempt2==3){
            score+=60;
        }
        else if(attempt2==2){
            score+=40;
        }
        else if(attempt2==1){
            score+=20;
        }
        l2.setText("Score :- "+score);
    }
    public int getRandom() {
        return (int) ((Math.random() * (100 - 1)) + 1);
    }
}
class guessGame extends JFrame {
    JLabel l1,l2;
    JButton b1;
    JTextField t1;
    String name;
    guessGame(){
        super("Number guessing game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        l2 = new JLabel("Welcome to Number Guessing Game");
        l2.setBounds(70,40,500,30);
        l1 = new JLabel("Enter your name : ");
        l1.setBounds(90,120,220,30);
        t1 = new JTextField();
        t1.setBounds(270,120,200,30);
        b1 = new JButton("Click Here To Start");
        b1.setBounds(180,180,200,30);
        l1.setFont(new Font("SansSerif",1,18));
        l2.setFont(new Font("SansSerif",1,24));
        t1.setFont(new Font("SansSerif",4,18));
        b1.setFont(new Font("SansSerif",2,16));
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                name = t1.getText();
                setVisible(false);
                new game(name);
                
            }});
        add(l2);
        add(l1);
        add(b1);
        add(t1);
        
        setSize(570,310);
        setLocationRelativeTo(null); 
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new guessGame();
    }
}
