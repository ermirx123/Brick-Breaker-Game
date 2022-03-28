import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class GjeneruesiSkemes {
   
   // Harta mapi
   public boolean skema[][];
   
   // Numrat random qka i bashkangjiten tulles
   public int[][] numrat;
   
   // Gjeresia dhe gjatesia e tulles
   public int gjeresia_tulles;
   public int lartesia_tulles;
   
   public int x = (int)(1 + Math.random()*31) ,y = (int)(1 + Math.random()*31);
   
   public GjeneruesiSkemes(int rreshtat,int kolonat) {
   
      skema = new boolean[rreshtat][kolonat];
   
      for(int i = 0; i < skema.length; i++) {
      
         for(int j = 0; j < skema[0].length; j++) {
         
            skema[i][j] = true;
         }
      }
      
      gjeresia_tulles = 540/kolonat;
      lartesia_tulles = 150/kolonat;
      
      numrat = new int[rreshtat][kolonat];
      
      for(int i = 0; i < numrat.length; i++) {
        
         for(int j = 0; j < numrat[0].length; j++) {
         
            numrat[i][j] = 1 + (int)(Math.random()*63);
         }
      
      }
      
   }
   
   // Vizaton skemen(mapin) e tullave
   public void draw(Graphics2D g) {
      
      int k = 0;
     
      for(int i = 0; i < skema.length; i++) {
      
         for(int j = 0; j < skema[0].length; j++) {
            
            // Nese ekziston tulla e vizaton aty
            if(skema[i][j] == true) {
            
               g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
               //g.setColor(Color.blue);
               g.fillRect(j * gjeresia_tulles + 80, i * lartesia_tulles + 50, gjeresia_tulles,lartesia_tulles);
               g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
               //g.setColor(Color.red);
               //g.drawString(Integer.toString(i) + " , " + Integer.toString(j),j * brickWidth + 110,i * brickHeight + 65);
               g.drawString(Integer.toString(numrat[i][j]),j * gjeresia_tulles + 110,i * lartesia_tulles + 65);
               
               g.setStroke(new BasicStroke(3));
               g.setColor(Color.black);
               g.drawRect(j * gjeresia_tulles + 80, i * lartesia_tulles + 50, gjeresia_tulles,lartesia_tulles);
               
            }
         }
      }
   
   }
    
   // E vendos vleren e tulles : true =  ekziston , false = nuk ekziston
   public void vendoseVlereneTules(boolean vlera,int rreshti,int kolona) {
   
      skema[rreshti][kolona] = vlera;
   }
      
   public int[][] merrNumrat() {
   
      return numrat;
   }
   
}