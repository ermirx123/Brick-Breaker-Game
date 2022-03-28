import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.*;

public class LojeLuajtja extends JPanel implements KeyListener,ActionListener {
   
   // Variable per te treguar nese eshte loja duke u luajtur
   private boolean luaj = false;
   
   // Rezultati 
   private int rezultati = 0;
   
   // Piket ekstra
   private int piket_ekstra = 0;
   
   // Numri i rreshtave dhe kolonave
   private int rreshtat = 8,kolonat = 8;
   
   // Numri total i tulalve
   private int numri_i_tullave;
   
   // Koha dhe vonesa 
   private Timer koha;
   private int vonesa = 8;
   
   // Pozita e vozes
   private int pozita_vozes_x = 310;
   
   // Pozita e topit
   private int pozita_topit_x = 12;
   private int pozita_topit_y = 350;
   
   // Shpejtsia e levizjes se topave
   private int drejtimi_topit_x = -2;
   private int drejtimi_topit_y = -3;
   
   // Hart(Mapi) apo dizajni i tullave
   private GjeneruesiSkemes skema;
   
   // Gjenerimi i dy numrave random per ti mbledhur
   private int x = (int)(1 + Math.random()*30);
   private int y = (int)(1 + Math.random()*30);
   
   // Numri i goditjes se tullave
   private int numri_goditjeve = 0;
   
   // Vargu dydimensional per ti
   private int[][] numrat;
   
   // Variabla per matjen e kohes
   private int mbledhja_kohes = 0;
   
   // Numri i tulles qe ke goditur
   private int numri_i_goditjes = 0;
   
   // Lista per ti mbajtur rezultatet e sakta
   private ArrayList<String> rezultatet_e_sakta = new ArrayList<String>();
   
   // Teksti ne fillim qe tregon cilin buton per ta prekur per te filluar
   private boolean teksti_startit = true;
   
   public LojeLuajtja() {
      
      numri_i_tullave = rreshtat*kolonat;
      skema = new GjeneruesiSkemes(rreshtat,kolonat);  
      numrat = skema.merrNumrat();
      addKeyListener(this);
      setFocusable(true);
      setFocusTraversalKeysEnabled(false);
      koha = new Timer(vonesa,this);
      koha.start();
   }
   
   public void paint(Graphics g) {
   
      // Prapaskena
      g.setColor(new Color(0,255,0));
      g.fillRect(1,1,692,592);      
      
      if(teksti_startit) {
      
         g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
         g.setFont(new Font("serif",Font.BOLD,20));
         g.drawString("Shtypeni enter,shigjeta majtas ose shigjeta djathas per te filluar",50,280);
      }
      
      if(luaj) { 
         
         teksti_startit = false;
        
         // Vizatimi i mapit
         skema.draw((Graphics2D)g); 
      
         // Kufijt e mpit
         g.setColor(Color.yellow);
         g.fillRect(0,0,3,592);
         g.fillRect(0,0,682,3);
         g.fillRect(691,0,3,592);
      
         // Rezultatet
         g.setColor(Color.white);
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("" + rezultati,590,30);
      
         // Voza
         g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
         g.fillRect(pozita_vozes_x,550,100,8);
      
         // Topi
         g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
         g.fillOval(pozita_topit_x,pozita_topit_y,20,20);
      
         // Numrat per ti mbledhur 
         g.setColor(Color.BLUE);
         g.setFont(new Font("serif",Font.BOLD,30));
         g.drawString(Integer.toString(x) + " + " + Integer.toString(y) + " = ", 280,35);
         
         // Koha 
         g.drawString("Koha: " + Integer.toString(mbledhja_kohes/100),20,550);
        
         // Goditja e tulles
         g.drawString("Tulla: " + Integer.toString(numri_i_goditjes),530,550);
         
         // Piket ekstra
         g.setFont(new Font("serif",Font.BOLD,20));
         g.drawString("Piket ekstra : " + Integer.toString(piket_ekstra), 30,220);
         
         // Restartimi i kohes
         if(mbledhja_kohes == 3000) {
         
            x = (int)(1 + Math.random()*31);
            y = (int)(1 + Math.random()*31);    
         
            mbledhja_kohes = 0;
         } 
         
         mbledhja_kohes++;
      }
      
       
      // Nese numri total i tullave eshte 0 ather mbaron loja. Lojtari Fiton
      if(numri_i_tullave <= 0) {
        
         luaj = false;
         drejtimi_topit_x = 0;
         drejtimi_topit_y = 0;
         
         //g.setColor(Color.RED);
         g.setColor(new Color((int)(100 +Math.random()*155),0,0));
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("Ju keni fituar: " + rezultati,260,220);
         
         //g.setColor(Color.GREEN);
         g.setColor(new Color(0,(int)(100 + Math.random()*155),0));
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("Shtypeni enter per te restartuar",260,250);
         
         //g.setColor(Color.BLUE);
         g.setColor(new Color(0,0,(int)(100 + Math.random()*155)));
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("Piket ekstra jane: " + Integer.toString(piket_ekstra),260,280);
         
         // Rezulatet e sakta
         //g.setColor(Color.BLUE);
         g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
         g.setFont(new Font("serif",Font.BOLD,20));
         
         for(int i = 0; i < rezultatet_e_sakta.size(); i++)  {
         
            g.drawString(rezultatet_e_sakta.get(i),50,50 + i*30);
         }
         
      }
       
      // Nese topi godet pjesen e poshte te ekranit. Lojtari Humb
      if(pozita_topit_y > 570) {
          
         luaj = false;
         drejtimi_topit_x = 0;
         drejtimi_topit_y = 0;
         
         //g.setColor(Color.RED);
         g.setColor(new Color((int)(100 +Math.random()*155),0,0));
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("Loja mbaroi, Rezultati: " + rezultati,260,220);
         
         //g.setColor(Color.GREEN);
         g.setColor(new Color(0,(int)(100 + Math.random()*155),0));
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("Shtypeni enter per te restartuar",260,250);
         
         //g.setColor(Color.BLUE);
         g.setColor(new Color(0,0,(int)(100 + Math.random()*155)));
         g.setFont(new Font("serif",Font.BOLD,25));
         g.drawString("Piket ekstra jane: " + Integer.toString(piket_ekstra),260,280);
         
         
         // Rezultatet e sakta
         //g.setColor(Color.BLUE);
         g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
         g.setFont(new Font("serif",Font.BOLD,20));
         
         for(int i = 0; i < rezultatet_e_sakta.size(); i++)  {
         
            g.drawString(rezultatet_e_sakta.get(i),50,50 + i*30);
         }
         
      }   
      
   
      g.dispose();
   }
   
   public void actionPerformed(ActionEvent e) {
    
      koha.start();
      repaint();     
    
      if(luaj) {
         
         gjeneroNumrat(numrat);
         
         // Nese topi godet vozen kthehet mbrapa
         if(new Rectangle(pozita_topit_x,pozita_topit_y,20,20).intersects(new Rectangle(pozita_vozes_x,550,100,8))) {
         
            drejtimi_topit_y = -drejtimi_topit_y;
         }
         
         A: for(int i = 0; i < skema.skema.length; i++) {
            
            for(int j = 0; j < skema.skema[0].length; j++) {
                
               // Nese tulla ekziston 
               if(skema.skema[i][j] == true) {
                  
                  int tulla_X = j* skema.gjeresia_tulles + 80;
                  int tulla_Y = i* skema.lartesia_tulles + 50; 
                  int gjeresia_Tulles = skema.gjeresia_tulles;
                  int lartesia_Tulles = skema.lartesia_tulles; 
                  
                  Rectangle rect = new Rectangle(tulla_X,tulla_Y,gjeresia_Tulles,lartesia_Tulles);
                  Rectangle ballRect = new Rectangle(pozita_topit_x,pozita_topit_y,20,20);
                  
                  Rectangle brickRect = rect;
                  
                   
                  // Nese topi rroken tullen 
                  if(ballRect.intersects(brickRect)) {
                  
                     skema.vendoseVlereneTules(false,i,j);
                     numri_i_tullave--;
                     rezultati += 5;
                     numri_goditjeve++; 
                     
                     
                     numri_i_goditjes = numrat[i][j];
                     
                      
                     // Nese mbledhja eshte e sakt me goditjen e tulles me top 
                     if(mbledhjaBarabarte(i,j)) {
                      
                        rezultatet_e_sakta.add(Integer.toString(x) + " + " + Integer.toString(y) + " = " + numrat[i][j]);
                      
                        rezultati += 5;
                        
                        x = (int)(1 + Math.random()*31);
                        y = (int)(1 + Math.random()*31);
                        
                        mbledhja_kohes = 0;
                        
                        piket_ekstra++;     
                     }
                     
                     // Kur ta godet tullen vleren e numrit e ben zero 
                     numrat[i][j] = 0;
                   
                     if(pozita_topit_x + 19 <= brickRect.x || pozita_topit_x + 1 >= brickRect.x + brickRect.width) {
                     
                        drejtimi_topit_x = -drejtimi_topit_x;
                        
                     } else {
                     
                        drejtimi_topit_y = - drejtimi_topit_y;
                     } 
                     
                     break A;
                  }
                  
               }
              
            }
         
         }
      
         pozita_topit_x += drejtimi_topit_x;
         pozita_topit_y += drejtimi_topit_y;
         
         
         // Kushtet nese topi godet anet dhe pjesen e siperme te ekranit per tu kthyer
         
         if(pozita_topit_x < 0) {
         
            drejtimi_topit_x = -drejtimi_topit_x;
         }
         
         if(pozita_topit_y < 0) {
         
            drejtimi_topit_y = -drejtimi_topit_y;
         }
         
         if(pozita_topit_x > 670) {
         
            drejtimi_topit_x = -drejtimi_topit_x;
         }
      
      }
     
      repaint();
   }

   public void keyPressed(KeyEvent e) {
   
      // Levizja e lozit ne anen e djathe me prekjen e butonit 
      if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
      
         if(pozita_vozes_x >= 600) {
         
            pozita_vozes_x = 600;
         
         } else {
         
            levizDjathtas();
         }
      
      }
       
      // Levizja e lozit ne anen e majte me prekjen e butonit 
      if(e.getKeyCode() == KeyEvent.VK_LEFT) {
      
         if(pozita_vozes_x < 10) {
         
            pozita_vozes_x = 10;
         
         } else {
         
            levizMajtas();
         }
      
      }
       
      // Nese mbaron loja duhat ta godasim Enter per ta restartuar 
      if(e.getKeyCode() == KeyEvent.VK_ENTER) {
         
         if(!luaj) {
         
            luaj = true;
            pozita_topit_x = 300;
            pozita_topit_y = 350;
            drejtimi_topit_x = -2;
            drejtimi_topit_y = -3;
            pozita_vozes_x = 310;
            rezultati = 0;
            piket_ekstra = 0;
            mbledhja_kohes = 0;
            rezultatet_e_sakta.clear();
            numri_i_tullave = rreshtat*kolonat;
            skema = new GjeneruesiSkemes(rreshtat,kolonat);
            numrat = skema.merrNumrat();
           
            repaint();
         }
      
      }
   
   }
   
   // Levizja e lozit ne anen e djathte
   public void levizDjathtas() {
   
      luaj = true;
      pozita_vozes_x += 20;
   }
   
   // Levizja e lozit ne anen e majte
   public void levizMajtas() {
   
      luaj = true;
      pozita_vozes_x -= 20;
   }
   
   public void keyTyped(KeyEvent e) {}

   public void keyReleased(KeyEvent e) {}
   
   
   // Metoda nese mbledhja eshte sakt me goditjen e topit me tullen
   public boolean mbledhjaBarabarte(int i,int j) {
         
      if((x + y) == numrat[i][j]) {
            
         return true;
         
      } else {
            
         return false;
      }
   
   }
      
   // Kushti per te gjeneruar numrat per ti mbledhuar ashtu qe jan te barabart me ndonje numer prej tullave
   public boolean kontrolloKushtin(int[][] numr) {
   
      for(int i = 0; i < numr.length; i++) { 
      
         for(int j = 0; j < numr[0].length; j++) {
           
            if((x+y) == numr[i][j]) {
            
               return true;
            }
         
         }
      
      }     
        
      return false;
   }
   
   //  Gjeneruar i numrave per ti mbledhuar ashtu qe jan te barabart me ndonje numer prej tullave
   public void gjeneroNumrat(int[][] nums) {
   
      while(!kontrolloKushtin(nums)) {
      
         if(!kontrolloKushtin(nums)) {
         
            x = (int)(1 + Math.random()*31);
            y = (int)(1 + Math.random()*31);   
         }
      
      }
   
   }
     
   
   // Merr numrin e goditjeve   
   public int getNumri_goditjeve() {
   
      return numri_goditjeve;
   }

}