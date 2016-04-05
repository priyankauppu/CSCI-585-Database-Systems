/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.shape.Line;
import javax.swing.JFrame;
import javax.swing.JPanel;
import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

/**
 *
 * @author Priyanka
 */

class MyCanvas extends JPanel implements ItemListener{
int flagPond=0;
int highlight=0;
    private Polygon r1=new Polygon();
    private Polygon r2=new Polygon();
    private Polygon r3=new Polygon();
    private Polygon r4=new Polygon();
    private Polygon r5=new Polygon();
    private Polygon r6=new Polygon();
    private Polygon r7=new Polygon();
    private Polygon r8=new Polygon();
    private Polygon r9=new Polygon();
    private Polygon r10=new Polygon();
    private Polygon r11=new Polygon();
    private Polygon r12=new Polygon();
    private Polygon r13=new Polygon();
    private Polygon r14=new Polygon();
    private Polygon r15=new Polygon();
    private Polygon r16=new Polygon();
    double[] location=null; 
    double[] location_pond=null;
    double[] location_ambulance=null;
    double[] location_lion=null;
    private Ellipse2D p1;
    private Ellipse2D p2;
    private Ellipse2D p3;
    private Ellipse2D p4;
    private Ellipse2D p5;
    private Ellipse2D p6;
    private Ellipse2D p7;
    private Ellipse2D p8;
    
    private Ellipse2D a1;
    private Ellipse2D a2;
    private Ellipse2D a3;
    private Ellipse2D a4;
    private Ellipse2D a5;
    
    private Ellipse2D ellipse;
    private float alpha_rectangle;
    private float alpha_ellipse;
private int l1=0,l2=0,l3=0,l4=0,l5=0,l6=0,l7=0,l8=0,l9=0,l10=0,l11=0,l12=0,l13=0,l14=0;
  private int w1=0,w2=0,w3=0,w4=0,w5=0,w6=0,w7=0,w8=0; 
   

    public MyCanvas() {
        
        initCanvas();
    }
    
    public double[] dataFromDb(String str){
       double location[]=null;
        try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","tinku","oracle");

Statement stmt=con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT location FROM region where region_id='"+str+"'");
 STRUCT st = null;
while(rs.next()){
    st = (oracle.sql.STRUCT) rs.getObject(1);
}     
//STRUCT st = (oracle.sql.STRUCT) rs.getObject(1);
     //convert STRUCT into geometry
     JGeometry j_geom = JGeometry.load(st);
      location=j_geom.getOrdinatesArray();
con.close();

}catch(Exception e){ System.out.println(e);}
   
    return(location);
    }
   
      public double[] dataForPond(String str){
       
        try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","tinku","oracle");

Statement stmt=con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT location FROM pond where pond_id='"+str+"'");
 STRUCT st = null;
while(rs.next()){
    st = (oracle.sql.STRUCT) rs.getObject(1);
}     
//STRUCT st = (oracle.sql.STRUCT) rs.getObject(1);
     //convert STRUCT into geometry
     JGeometry j_geom = JGeometry.load(st);
      location_pond=j_geom.getOrdinatesArray();
     
con.close();

}catch(Exception e){ System.out.println(e);}
   
    return(location_pond);
    } 
    
      //ambulance
      public double[] dataForAmbulance(String str){
       
        try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","tinku","oracle");

Statement stmt=con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT location FROM ambulance where ambulance_id='"+str+"'");
 STRUCT st = null;
while(rs.next()){
    st = (oracle.sql.STRUCT) rs.getObject(1);
}     
//STRUCT st = (oracle.sql.STRUCT) rs.getObject(1);
     //convert STRUCT into geometry
     JGeometry j_geom = JGeometry.load(st);
      location_ambulance=j_geom.getOrdinatesArray();
    
con.close();

}catch(Exception e){ System.out.println(e);}
   
    return(location_ambulance);
    } 
    //lion
      public double[] dataForLion(String str){
       
        try{
Class.forName("oracle.jdbc.driver.OracleDriver");

Connection con=DriverManager.getConnection(
"jdbc:oracle:thin:@localhost:1521:xe","tinku","oracle");

Statement stmt=con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT location FROM lion where lion_id='"+str+"'");
 STRUCT st = null;
while(rs.next()){
    st = (oracle.sql.STRUCT) rs.getObject(1);
}     
//STRUCT st = (oracle.sql.STRUCT) rs.getObject(1);
     //convert STRUCT into geometry
     JGeometry j_geom = JGeometry.load(st);
      location_lion=j_geom.getPoint();
    

con.close();

}catch(Exception e){ System.out.println(e);}
   
    return(location_lion);
    } 
    @Override
    public void itemStateChanged(ItemEvent e) {
        
        int sel = e.getStateChange();
        
        if (sel==ItemEvent.SELECTED) {
            
           highlight=1;
           System.out.println("Display ON");
        } else {
            
           highlight=0;
            System.out.println("Display OFF");
        }
    }   
   
    private void initCanvas() {
        JCheckBox cb = new JCheckBox("Highlight all lions", true);
       cb.addItemListener(this);
        cb.setLocation(600, 600);
        add(cb);
        addMouseListener(new HitTestAdapter());
    
      location=dataFromDb("R1");
      for(int k=0;k<8;k=k+2){
        r1.addPoint((int)location[k], (int) location[k+1]);  
      }
       location=dataFromDb("R2");
      for(int k=0;k<8;k=k+2){
        r2.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R3");
      for(int k=0;k<8;k=k+2){
        r3.addPoint((int)location[k], (int) location[k+1]);  
      }  
       location=dataFromDb("R4");
      for(int k=0;k<8;k=k+2){
        r4.addPoint((int)location[k], (int) location[k+1]);  
      }   
        location=dataFromDb("R5");
      for(int k=0;k<8;k=k+2){
        r5.addPoint((int)location[k], (int) location[k+1]);  
      } 
     location=dataFromDb("R6");
      for(int k=0;k<8;k=k+2){
        r6.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R7");
      for(int k=0;k<8;k=k+2){
        r7.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R8");
      for(int k=0;k<8;k=k+2){
        r8.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R9");
      for(int k=0;k<8;k=k+2){
        r9.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R10");
      for(int k=0;k<8;k=k+2){
        r10.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R11");
      for(int k=0;k<8;k=k+2){
        r11.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R12");
      for(int k=0;k<8;k=k+2){
        r12.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R13");
      for(int k=0;k<8;k=k+2){
        r13.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R14");
      for(int k=0;k<8;k=k+2){
        r14.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R15");
      for(int k=0;k<8;k=k+2){
        r15.addPoint((int)location[k], (int) location[k+1]);  
      } 
       location=dataFromDb("R16");
      for(int k=0;k<8;k=k+2){
        r16.addPoint((int)location[k], (int) location[k+1]);  
      } 
      location_pond=dataForPond("P1");
       p1=new Ellipse2D.Double(location_pond[0]+15, location_pond[1], 30,30);
       location_pond=dataForPond("P2");
       p2=new Ellipse2D.Double(location_pond[0]+15, location_pond[1], 30,30);
       location_pond=dataForPond("P3");
       p3=new Ellipse2D.Double(location_pond[0]+15, location_pond[1], 30,30);
       location_pond=dataForPond("P4");
       p4=new Ellipse2D.Double(location_pond[0]+15, location_pond[1], 30,30);
       location_pond=dataForPond("P5");
       p5=new Ellipse2D.Double(location_pond[0]+15, location_pond[1], 30,30);
       location_pond=dataForPond("P6");
       p6=new Ellipse2D.Double(location_pond[0]+15, location_pond[1], 30,30);
       location_pond=dataForPond("P7");
       p7=new Ellipse2D.Double(location_pond[0]+15, location_pond[1], 30,30);
       location_pond=dataForPond("P8");
       p8=new Ellipse2D.Double(location_pond[0]-15, location_pond[1], 30,30);
       
       
       location_ambulance=dataForAmbulance("A1");
       a1=new Ellipse2D.Double(location_ambulance[0]+90, location_ambulance[1], 180,180);
       location_ambulance=dataForAmbulance("A2");
       a2=new Ellipse2D.Double(location_ambulance[0]+90, location_ambulance[1], 180,180);
       
       location_ambulance=dataForAmbulance("A3");
       a3=new Ellipse2D.Double(location_ambulance[0]+90, location_ambulance[1], 180,180);
       
       location_ambulance=dataForAmbulance("A4");
       a4=new Ellipse2D.Double(location_ambulance[0]+90, location_ambulance[1], 180,180);
       
       location_ambulance=dataForAmbulance("A5");
       a5=new Ellipse2D.Double(location_ambulance[0]+90, location_ambulance[1], 180,180);
       
       
       
       alpha_rectangle = 1f;
        alpha_ellipse = 1f;        
    }

    public void paint(Graphics2D g, Polygon r){
        g.setColor(Color.white);
      g.fillPolygon(r);
    g.setColor(Color.BLACK);
    g.drawPolygon(r);
    
    }
    public void paintPond(Graphics2D g, Ellipse2D r,int w){
         
      if(w==1){
          g.setColor(Color.yellow);
      g.fill(r);
      g.setColor(Color.BLACK);
      g.draw(r);
      w=0;
      }
      else{
         g.setColor(Color.BLUE);
      g.fill(r);
      g.setColor(Color.BLACK);
      g.draw(r); 
      }
    }
    
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g.create();
    paint(g2d,r1);
    paint(g2d,r2);
    paint(g2d,r3);
    paint(g2d,r4);
    paint(g2d,r5);
    paint(g2d,r6);
    paint(g2d,r7);
    paint(g2d,r8);
    paint(g2d,r9);
    paint(g2d,r10);
    paint(g2d,r11);
    paint(g2d,r12);
    paint(g2d,r13);
    paint(g2d,r14);
    paint(g2d,r15);
    paint(g2d,r16);
    
    paintPond(g2d,p1,w1);
   paintPond(g2d,p2,w2);
   paintPond(g2d,p3,w3);
   paintPond(g2d,p4,w4);
   paintPond(g2d,p5,w5);
   paintPond(g2d,p6,w6);
   paintPond(g2d,p7,w7);
   paintPond(g2d,p8,w8);
   
   g.setColor(Color.BLACK);
       g2d.draw(a1);
        g2d.draw(a2);
         g2d.draw(a3);
          g2d.draw(a4);
           g2d.draw(a5);
           g2d.setColor(Color.green);
            
      BasicStroke bs1=new BasicStroke(8,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
      g2d.setStroke(bs1);
       location_lion=dataForLion("L1");
      if(l1==1){   
         g2d.setColor(Color.RED); 
         g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));    
         l1=0;
      }
      else{
      g2d.setColor(Color.green);
      g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));    
        
      }
      location_lion=dataForLion("L2");
     if(l2==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l2=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
     
 location_lion=dataForLion("L3");
     if(l3==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l3=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }     
            location_lion=dataForLion("L4");
     if(l4==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l4=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
            location_lion=dataForLion("L5");
     if(l5==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l5=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
      
 location_lion=dataForLion("L6");
     if(l6==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l6=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }

 location_lion=dataForLion("L7");
     if(l7==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l7=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }

 location_lion=dataForLion("L8");
     if(l8==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l8=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
 location_lion=dataForLion("L9");
     if(l9==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l9=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }

      location_lion=dataForLion("L10");
     if(l10==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l10=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
        location_lion=dataForLion("L11");
     if(l11==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l11=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }  
      location_lion=dataForLion("L12");
     if(l12==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l12=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
      location_lion=dataForLion("L13");
     if(l13==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l13=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
      location_lion=dataForLion("L14");
     if(l14==1){   
         g2d.setColor(Color.RED); 
          g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5));
         
         l14=0;
      }
     else{ 
         g2d.setColor(Color.green);
           g2d.draw(new Line2D.Double(location_lion[0],location_lion[1],location_lion[0]+5,location_lion[1]+5)); 
     }
     
     
           g2d.dispose();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void setTitle(String jCheckBox) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    class RectRunnable implements Runnable {

        private Thread runner;

        public RectRunnable() {
            
            initThread();
        }
        
        private void initThread() {
            
            runner = new Thread(this);
            runner.start();
        }

        @Override
        public void run() {

            while (alpha_rectangle >= 0) {
                
                repaint();
                alpha_rectangle += -0.01f;

                if (alpha_rectangle < 0) {
                    alpha_rectangle = 0;
                }

                try {
                    
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    
                     Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, 
                             null, ex);
                }
            }
        }
    }

    class HitTestAdapter extends MouseAdapter
            implements Runnable {

        private RectRunnable rectAnimator;
        private Thread ellipseAnimator;

        @Override
        public void mousePressed(MouseEvent e) {
            if(highlight==1){
                
            
            int x = e.getX();
            int y = e.getY();
double arr1[]=dataForLion("L1");
double arr2[]=dataForLion("L2");
double arr3[]=dataForLion("L3");
double arr4[]=dataForLion("L4");
double arr5[]=dataForLion("L5");
double arr6[]=dataForLion("L6");
double arr7[]=dataForLion("L7");
double arr8[]=dataForLion("L8");
double arr9[]=dataForLion("L9");
double arr10[]=dataForLion("L10");
double arr11[]=dataForLion("L11");
double arr12[]=dataForLion("L12");
double arr13[]=dataForLion("L13");
double arr14[]=dataForLion("L14");

  if (r1.contains(x, y)) {
      w1=w2=w3=w4=w5=w6=w7=w8=0;
      //flagPond=1;
      
if(r1.contains(arr1[0],arr1[1])){
    l1=1;
     
}
if(r1.contains(arr2[0],arr2[1])){
       l2=1;
   
     
}
if(r1.contains(arr3[0],arr3[1])){
   l3=1;
    
}
if(r1.contains(arr4[0],arr4[1])){
    l4=1; 
}
if(r1.contains(arr5[0],arr5[1])){
    l5=1;
}
if(r1.contains(arr6[0],arr6[1])){
     l6=1;
}if(r1.contains(arr7[0],arr7[1])){
    l7=1;
}if(r1.contains(arr8[0],arr8[1])){
     l8=1;
}if(r1.contains(arr9[0],arr9[1])){
    l9=1;
}if(r1.contains(arr10[0],arr10[1])){
     l10=1;
}if(r1.contains(arr11[0],arr11[1])){
     l11=1;
}if(r1.contains(arr12[0],arr12[1])){
     l12=1;
}if(r1.contains(arr13[0],arr13[1])){
     l13=1;
}if(r1.contains(arr14[0],arr14[1])){
    l14=1;
}
repaint();            
System.out.println("R1");
            }
  
  
  
  
  // R2 check
if (r2.contains(x, y)) {
      w1=w2=w3=w4=w5=w6=w7=w8=0;
      //flagPond=1;
      
if(r2.contains(arr1[0],arr1[1])){
    l1=1;
     
}
if(r2.contains(arr2[0],arr2[1])){
       l2=1;
   
     
}
if(r2.contains(arr3[0],arr3[1])){
   l3=1;
    
}
if(r2.contains(arr4[0],arr4[1])){
    l4=1; 
}
if(r2.contains(arr5[0],arr5[1])){
    l5=1;
}
if(r2.contains(arr6[0],arr6[1])){
     l6=1;
}if(r2.contains(arr7[0],arr7[1])){
    l7=1;
}if(r2.contains(arr8[0],arr8[1])){
     l8=1;
}if(r2.contains(arr9[0],arr9[1])){
    l9=1;
}if(r2.contains(arr10[0],arr10[1])){
     l10=1;
}if(r2.contains(arr11[0],arr11[1])){
     l11=1;
}if(r2.contains(arr12[0],arr12[1])){
     l12=1;
}if(r2.contains(arr13[0],arr13[1])){
     l13=1;
}if(r2.contains(arr14[0],arr14[1])){
    l14=1;
}
repaint();            
System.out.println("R2");
            }


//R3

if (r3.contains(x, y)) { 
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r3.contains(arr1[0],arr1[1])){
    l1=1;
     
}
if(r3.contains(arr2[0],arr2[1])){
       l2=1;
}
if(r3.contains(arr3[0],arr3[1])){
   l3=1;
    
}
if(r3.contains(arr4[0],arr4[1])){
    l4=1; 
}
if(r3.contains(arr5[0],arr5[1])){
    l5=1;
}
if(r3.contains(arr6[0],arr6[1])){
     l6=1;
}if(r3.contains(arr7[0],arr7[1])){
    l7=1;
}if(r3.contains(arr8[0],arr8[1])){
     l8=1;
}if(r3.contains(arr9[0],arr9[1])){
    l9=1;
}if(r3.contains(arr10[0],arr10[1])){
     l10=1;
}if(r3.contains(arr11[0],arr11[1])){
     l11=1;
}if(r3.contains(arr12[0],arr12[1])){
     l12=1;
}if(r3.contains(arr13[0],arr13[1])){
     l13=1;
}if(r3.contains(arr14[0],arr14[1])){
    l14=1;
}
repaint();            
System.out.println("R3");
            }


//R4
if (r4.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r4.contains(arr1[0],arr1[1])){l1=1;}
if(r4.contains(arr2[0],arr2[1])){l2=1;}
if(r4.contains(arr3[0],arr3[1])){l3=1;}
if(r4.contains(arr4[0],arr4[1])){l4=1;}
if(r4.contains(arr5[0],arr5[1])){l5=1;}
if(r4.contains(arr6[0],arr6[1])){l6=1;}
if(r4.contains(arr7[0],arr7[1])){l7=1;}
if(r4.contains(arr8[0],arr8[1])){l8=1;}
if(r4.contains(arr9[0],arr9[1])){l9=1;}
if(r4.contains(arr10[0],arr10[1])){l10=1;}
if(r4.contains(arr11[0],arr11[1])){l11=1;}
if(r4.contains(arr12[0],arr12[1])){l12=1;}
if(r4.contains(arr13[0],arr13[1])){l13=1;}
if(r4.contains(arr14[0],arr14[1])){l14=1;}
repaint();            
System.out.println("R4");
            }

//R5
if (r5.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r5.contains(arr1[0],arr1[1])){l1=1;}
if(r5.contains(arr2[0],arr2[1])){l2=1;}
if(r5.contains(arr3[0],arr3[1])){l3=1;}
if(r5.contains(arr4[0],arr4[1])){l4=1;}
if(r5.contains(arr5[0],arr5[1])){l5=1;}
if(r5.contains(arr6[0],arr6[1])){l6=1;}
if(r5.contains(arr7[0],arr7[1])){l7=1;}
if(r5.contains(arr8[0],arr8[1])){l8=1;}
if(r5.contains(arr9[0],arr9[1])){l9=1;}
if(r5.contains(arr10[0],arr10[1])){l10=1;}
if(r5.contains(arr11[0],arr11[1])){l11=1;}
if(r5.contains(arr12[0],arr12[1])){l12=1;}
if(r5.contains(arr13[0],arr13[1])){l13=1;}
if(r5.contains(arr14[0],arr14[1])){l14=1;}
repaint();            
System.out.println("R5");
            }


//R6
if (r6.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r6.contains(arr1[0],arr1[1])){l1=1;}
if(r6.contains(arr2[0],arr2[1])){l2=1;}
if(r6.contains(arr3[0],arr3[1])){l3=1;}
if(r6.contains(arr4[0],arr4[1])){l4=1;}
if(r6.contains(arr5[0],arr5[1])){l5=1;}
if(r6.contains(arr6[0],arr6[1])){l6=1;}
if(r6.contains(arr7[0],arr7[1])){l7=1;}
if(r6.contains(arr8[0],arr8[1])){l8=1;}
if(r6.contains(arr9[0],arr9[1])){l9=1;}
if(r6.contains(arr10[0],arr10[1])){l10=1;}
if(r6.contains(arr11[0],arr11[1])){l11=1;}
if(r6.contains(arr12[0],arr12[1])){l12=1;}
if(r6.contains(arr13[0],arr13[1])){l13=1;}
if(r6.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r6.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r6.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r6.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r6.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r6.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r6.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r6.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r6.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}

repaint(); 

System.out.println("R6");
            }

//R7
if (r7.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r7.contains(arr1[0],arr1[1])){l1=1;}
if(r7.contains(arr2[0],arr2[1])){l2=1;}
if(r7.contains(arr3[0],arr3[1])){l3=1;}
if(r7.contains(arr4[0],arr4[1])){l4=1;}
if(r7.contains(arr5[0],arr5[1])){l5=1;}
if(r7.contains(arr6[0],arr6[1])){l6=1;}
if(r7.contains(arr7[0],arr7[1])){l7=1;}
if(r7.contains(arr8[0],arr8[1])){l8=1;}
if(r7.contains(arr9[0],arr9[1])){l9=1;}
if(r7.contains(arr10[0],arr10[1])){l10=1;}
if(r7.contains(arr11[0],arr11[1])){l11=1;}
if(r7.contains(arr12[0],arr12[1])){l12=1;}
if(r7.contains(arr13[0],arr13[1])){l13=1;}
if(r7.contains(arr14[0],arr14[1])){l14=1;}
repaint();            
System.out.println("R7");
            }

//R8
if (r8.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r8.contains(arr1[0],arr1[1])){l1=1;}
if(r8.contains(arr2[0],arr2[1])){l2=1;}
if(r8.contains(arr3[0],arr3[1])){l3=1;}
if(r8.contains(arr4[0],arr4[1])){l4=1;}
if(r8.contains(arr5[0],arr5[1])){l5=1;}
if(r8.contains(arr6[0],arr6[1])){l6=1;}
if(r8.contains(arr7[0],arr7[1])){l7=1;}
if(r8.contains(arr8[0],arr8[1])){l8=1;}
if(r8.contains(arr9[0],arr9[1])){l9=1;}
if(r8.contains(arr10[0],arr10[1])){l10=1;}
if(r8.contains(arr11[0],arr11[1])){l11=1;}
if(r8.contains(arr12[0],arr12[1])){l12=1;}
if(r8.contains(arr13[0],arr13[1])){l13=1;}
if(r8.contains(arr14[0],arr14[1])){l14=1;}
repaint();            
System.out.println("R8");
            }

//R9
if (r9.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r9.contains(arr1[0],arr1[1])){l1=1;}
if(r9.contains(arr2[0],arr2[1])){l2=1;}
if(r9.contains(arr3[0],arr3[1])){l3=1;}
if(r9.contains(arr4[0],arr4[1])){l4=1;}
if(r9.contains(arr5[0],arr5[1])){l5=1;}
if(r9.contains(arr6[0],arr6[1])){l6=1;}
if(r9.contains(arr7[0],arr7[1])){l7=1;}
if(r9.contains(arr8[0],arr8[1])){l8=1;}
if(r9.contains(arr9[0],arr9[1])){l9=1;}
if(r9.contains(arr10[0],arr10[1])){l10=1;}
if(r9.contains(arr11[0],arr11[1])){l11=1;}
if(r9.contains(arr12[0],arr12[1])){l12=1;}
if(r9.contains(arr13[0],arr13[1])){l13=1;}
if(r9.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r9.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r9.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r9.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r9.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r9.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r9.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r9.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r9.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R9");
            }

//R10
if (r10.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r10.contains(arr1[0],arr1[1])){l1=1;}
if(r10.contains(arr2[0],arr2[1])){l2=1;}
if(r10.contains(arr3[0],arr3[1])){l3=1;}
if(r10.contains(arr4[0],arr4[1])){l4=1;}
if(r10.contains(arr5[0],arr5[1])){l5=1;}
if(r10.contains(arr6[0],arr6[1])){l6=1;}
if(r10.contains(arr7[0],arr7[1])){l7=1;}
if(r10.contains(arr8[0],arr8[1])){l8=1;}
if(r10.contains(arr9[0],arr9[1])){l9=1;}
if(r10.contains(arr10[0],arr10[1])){l10=1;}
if(r10.contains(arr11[0],arr11[1])){l11=1;}
if(r10.contains(arr12[0],arr12[1])){l12=1;}
if(r10.contains(arr13[0],arr13[1])){l13=1;}
if(r10.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r10.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r10.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r10.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r10.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r10.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r10.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r10.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r10.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R10");
            }
//R11
if (r11.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r11.contains(arr1[0],arr1[1])){l1=1;}
if(r11.contains(arr2[0],arr2[1])){l2=1;}
if(r11.contains(arr3[0],arr3[1])){l3=1;}
if(r11.contains(arr4[0],arr4[1])){l4=1;}
if(r11.contains(arr5[0],arr5[1])){l5=1;}
if(r11.contains(arr6[0],arr6[1])){l6=1;}
if(r11.contains(arr7[0],arr7[1])){l7=1;}
if(r11.contains(arr8[0],arr8[1])){l8=1;}
if(r11.contains(arr9[0],arr9[1])){l9=1;}
if(r11.contains(arr10[0],arr10[1])){l10=1;}
if(r11.contains(arr11[0],arr11[1])){l11=1;}
if(r11.contains(arr12[0],arr12[1])){l12=1;}
if(r11.contains(arr13[0],arr13[1])){l13=1;}
if(r11.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r11.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r11.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r11.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r11.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r11.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r11.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r11.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r11.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R11");
            }

//R12
if (r12.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r12.contains(arr1[0],arr1[1])){l1=1;}
if(r12.contains(arr2[0],arr2[1])){l2=1;}
if(r12.contains(arr3[0],arr3[1])){l3=1;}
if(r12.contains(arr4[0],arr4[1])){l4=1;}
if(r12.contains(arr5[0],arr5[1])){l5=1;}
if(r12.contains(arr6[0],arr6[1])){l6=1;}
if(r12.contains(arr7[0],arr7[1])){l7=1;}
if(r12.contains(arr8[0],arr8[1])){l8=1;}
if(r12.contains(arr9[0],arr9[1])){l9=1;}
if(r12.contains(arr10[0],arr10[1])){l10=1;}
if(r12.contains(arr11[0],arr11[1])){l11=1;}
if(r12.contains(arr12[0],arr12[1])){l12=1;}
if(r12.contains(arr13[0],arr13[1])){l13=1;}
if(r12.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r12.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r12.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r12.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r12.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r12.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r12.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r12.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r12.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R12");
            }

//R13
if (r13.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r13.contains(arr1[0],arr1[1])){l1=1;}
if(r13.contains(arr2[0],arr2[1])){l2=1;}
if(r13.contains(arr3[0],arr3[1])){l3=1;}
if(r13.contains(arr4[0],arr4[1])){l4=1;}
if(r13.contains(arr5[0],arr5[1])){l5=1;}
if(r13.contains(arr6[0],arr6[1])){l6=1;}
if(r13.contains(arr7[0],arr7[1])){l7=1;}
if(r13.contains(arr8[0],arr8[1])){l8=1;}
if(r13.contains(arr9[0],arr9[1])){l9=1;}
if(r13.contains(arr10[0],arr10[1])){l10=1;}
if(r13.contains(arr11[0],arr11[1])){l11=1;}
if(r13.contains(arr12[0],arr12[1])){l12=1;}
if(r13.contains(arr13[0],arr13[1])){l13=1;}
if(r13.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r13.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r13.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r13.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r13.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r13.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r13.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r13.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r13.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R13");
            }
//R14
if (r14.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r14.contains(arr1[0],arr1[1])){l1=1;}
if(r14.contains(arr2[0],arr2[1])){l2=1;}
if(r14.contains(arr3[0],arr3[1])){l3=1;}
if(r14.contains(arr4[0],arr4[1])){l4=1;}
if(r14.contains(arr5[0],arr5[1])){l5=1;}
if(r14.contains(arr6[0],arr6[1])){l6=1;}
if(r14.contains(arr7[0],arr7[1])){l7=1;}
if(r14.contains(arr8[0],arr8[1])){l8=1;}
if(r14.contains(arr9[0],arr9[1])){l9=1;}
if(r14.contains(arr10[0],arr10[1])){l10=1;}
if(r14.contains(arr11[0],arr11[1])){l11=1;}
if(r14.contains(arr12[0],arr12[1])){l12=1;}
if(r14.contains(arr13[0],arr13[1])){l13=1;}
if(r14.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r14.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r14.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r14.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r14.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r14.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r14.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r14.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r14.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R14");
            }
//R15
if (r15.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r15.contains(arr1[0],arr1[1])){l1=1;}
if(r15.contains(arr2[0],arr2[1])){l2=1;}
if(r15.contains(arr3[0],arr3[1])){l3=1;}
if(r15.contains(arr4[0],arr4[1])){l4=1;}
if(r15.contains(arr5[0],arr5[1])){l5=1;}
if(r15.contains(arr6[0],arr6[1])){l6=1;}
if(r15.contains(arr7[0],arr7[1])){l7=1;}
if(r15.contains(arr8[0],arr8[1])){l8=1;}
if(r15.contains(arr9[0],arr9[1])){l9=1;}
if(r15.contains(arr10[0],arr10[1])){l10=1;}
if(r15.contains(arr11[0],arr11[1])){l11=1;}
if(r15.contains(arr12[0],arr12[1])){l12=1;}
if(r15.contains(arr13[0],arr13[1])){l13=1;}
if(r15.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r15.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r15.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r15.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r15.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r15.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r15.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r15.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r15.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R15");
            }

//R16
if (r16.contains(x, y)) {
    w1=w2=w3=w4=w5=w6=w7=w8=0;
if(r16.contains(arr1[0],arr1[1])){l1=1;}
if(r16.contains(arr2[0],arr2[1])){l2=1;}
if(r16.contains(arr3[0],arr3[1])){l3=1;}
if(r16.contains(arr4[0],arr4[1])){l4=1;}
if(r16.contains(arr5[0],arr5[1])){l5=1;}
if(r16.contains(arr6[0],arr6[1])){l6=1;}
if(r16.contains(arr7[0],arr7[1])){l7=1;}
if(r16.contains(arr8[0],arr8[1])){l8=1;}
if(r16.contains(arr9[0],arr9[1])){l9=1;}
if(r16.contains(arr10[0],arr10[1])){l10=1;}
if(r16.contains(arr11[0],arr11[1])){l11=1;}
if(r16.contains(arr12[0],arr12[1])){l12=1;}
if(r16.contains(arr13[0],arr13[1])){l13=1;}
if(r16.contains(arr14[0],arr14[1])){l14=1;}
location_pond=dataForPond("P1");
if(r16.contains(location_pond[0]+15, location_pond[1])){w1=1;
System.out.println("Pond 1");
}
location_pond=dataForPond("P2");
if(r16.contains(location_pond[0]+15, location_pond[1])){w2=1;
System.out.println("Pond 2");
}
location_pond=dataForPond("P3");
if(r16.contains(location_pond[0]+15, location_pond[1])){w3=1;
System.out.println("Pond 3");
}
location_pond=dataForPond("P4");
if(r16.contains(location_pond[0]+15, location_pond[1])){w4=1;
System.out.println("Pond 4");
}
location_pond=dataForPond("P5");
if(r16.contains(location_pond[0]+15, location_pond[1])){w5=1;
System.out.println("Pond 5");
}
location_pond=dataForPond("P6");
if(r16.contains(location_pond[0]+15, location_pond[1])){w6=1;
System.out.println("Pond 6");
}
location_pond=dataForPond("P7");
if(r16.contains(location_pond[0]+15, location_pond[1])){w7=1;
System.out.println("Pond 7");
}
location_pond=dataForPond("P8");
if(r16.contains(location_pond[0]+15, location_pond[1])){w8=1;
System.out.println("Pond 8");
}
repaint();            
System.out.println("R16");
            }
          /*  if (ellipse.contains(x, y)) {

                ellipseAnimator = new Thread(this);
                ellipseAnimator.start();
            }*/
        }//end highlight IF 
            else{
                  w1=w2=w3=w4=w5=w6=w7=w8=0;
                repaint();
            } 
        }

        @Override
        public void run() {

            while (alpha_ellipse >= 0) {

                repaint();
                alpha_ellipse += -0.01f;

                if (alpha_ellipse < 0) {

                    alpha_ellipse = 0;
                }

                try {
                    
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    
                    Logger.getLogger(Surface.class.getName()).log(Level.SEVERE, 
                        null, ex);
                }
            }
        }
    }
}
public class display extends javax.swing.JFrame  {

    /**
     * Creates new form display
     */
     public void itemStateChanged(ItemEvent e) {
        
        int sel = e.getStateChange();
        
        if (sel==ItemEvent.SELECTED) {
            
            setTitle("JCheckBox");
        } else {
            
            setTitle("");
        }
    }    
    
      
    public display() {
     
        add(new MyCanvas());
    
 //JCheckBox cb = new JCheckBox("Show title", true);
   //     cb.addItemListener((ItemListener) this);

     //   createLayout(cb);
        setTitle("Panthera Display");
        setSize(700,700);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new display().setVisible(true);
            }
        });
    }

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
