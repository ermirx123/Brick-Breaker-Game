import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Main  {

   public static void main(String[] args) {
   
      JFrame obj = new JFrame();
      
      LojeLuajtja lojeLuajtja = new LojeLuajtja();
               
      obj.setBounds(10,10,700,600);
      obj.setTitle("Breackout Ball");
      obj.setResizable(false);
      obj.setVisible(true);
      obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      obj.add(lojeLuajtja);

   }
   

}