/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.entities.rating;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;

/**
 *
 * @author wejdene
 */
public class addRating  extends Form{
      Form current;
        private Resources theme;
        rating r=new rating();
        public addRating(Form previous){
        Toolbar tb = getToolbar();
            theme = UIManager.initFirstTheme("/theme_2");
            tb.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_LOGOUT, 6f,( ActionEvent e) -> {
                   

            
          new Menu().show();
            
        });
           
   getToolbar().addMaterialCommandToRightBar("logOut",FontImage.MATERIAL_LOGOUT,
            
                                        event->{
                                        new HomeForm().show();
            
                                        });
            setTitle("Rating  nos Service");
                 setLayout(BoxLayout.y());
                     Label nom= new Label("         Service Eleves :");
            Slider SliderStar = createStarRankSlider();
            add(nom);
            add(SliderStar);
  
            TextField com=new TextField();
             Button rat=new Button("Rate");
            add(com);
            add(rat);
            Label nom2= new Label("                 Service Education :");
            Slider SliderStar2 = createStarRankSlider();
            add(nom2);
            add(SliderStar2);
  
            TextField com2=new TextField();
             Button rat2=new Button("Rate");
            add(com2);
            add(rat2);
              rat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ServiceUser s1=new ServiceUser();
 login l =new login();
             l=s1.login();
                    System.out.println(l);
             
                    ConnectionRequest con = new ConnectionRequest();
                         int rateVal = SliderStar.getProgress();
                    con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/rat?"
                            + "user="+l.getId_user()
                            + "&nom="+nom.getText()
                            + "&rat=" + rateVal
                            + "&commentaire=" +  com.getText()
                   
                            );

                        System.out.println(con.getUrl());
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {

                          
                            
                           Dialog.show("Confirmation", "Vote Avis a pris en consideration", "OK",null);
                            
                 
                     

                        }
                    });

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                         
                 
                    
                          
        


             
      
            
                      }
              
                      
              
                  });
               rat.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ServiceUser s1=new ServiceUser();
 login l =new login();
             l=s1.login();
                    System.out.println(l);
             
                    ConnectionRequest con = new ConnectionRequest();
                         int rateVal2 = SliderStar2.getProgress();
                    con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/rat?"
                            + "user="+l.getId_user()
                            + "&nom="+nom2.getText()
                            + "&rat=" + rateVal2
                            + "&commentaire=" +  com2.getText()
                   
                            );

                        System.out.println(con.getUrl());
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {

                          
                            
                           Dialog.show("Confirmation", "Vote Avis a pris en consideration", "OK",null);
                            
                 
                     
new Menu().show();
                        }
                    });

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                         
                 
                    
                          
        


             
      
            
                      }
              
                      
              
                  });


                      
                      }
            
            
        
         private void initStarRankStyle(Style s, Image star) {
        s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
        s.setBorder(Border.createEmpty());
        s.setBgImage(star);
        s.setBgTransparency(0);
    }
private Slider createStarRankSlider() {

        Slider starRank = new Slider();

        // starRank.setProgress(1);
        starRank.setEditable(true);
        starRank.setMinValue(0);
        starRank.setMaxValue(5);
        starRank.setRenderValueOnTop(true);
        starRank.setRenderPercentageOnTop(true);
        Font fnt = Font.createTrueTypeFont("native:MainThin", "native:MainThin").
                derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
        Style s = new Style(0xffff33, 0, fnt, (byte) 0);
        Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        s.setOpacity(100);
        s.setFgColor(0);
        Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
        initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
        initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
        initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
        starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
        return starRank;
    }
}
              

                     



                      
