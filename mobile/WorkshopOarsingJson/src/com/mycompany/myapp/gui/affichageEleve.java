/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.messaging.Message;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.services.ServiceEleve;
import java.util.ArrayList;

/**
 *
 * @author wejdene
 */
public class affichageEleve  extends Form{
       ServiceEleve serviceEleve= new ServiceEleve();
            ArrayList<eleve> lis = serviceEleve.getAllE(); //liste des Produits
            Container cCenter = new Container(); 
            
      public affichageEleve(Form previous) throws ParseException {
        
       
      super(); 
        setTitle("Vos Enfants");
      
        Toolbar toolBar = getToolbar();
                 toolBar.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_LOGOUT, 6f,( ActionEvent e) -> {
                   

            
          new Menu().show();
            
        });
      getToolbar().addMaterialCommandToRightBar("logOut",FontImage.MATERIAL_LOGOUT,
            
                                        event->{
                                        new HomeForm().show();
            
                                        });
        
     
             
       
                
        Button btnClose = new Button("Exit");
        btnClose.setIcon(
            FontImage.createMaterial(
                FontImage.MATERIAL_EXIT_TO_APP,
                UIManager.getInstance().getComponentStyle("Button")
            )            
        );
        btnClose.addActionListener
                ((ActionListener<ActionEvent>) (ActionEvent evt) -> {
                    
                    Display.getInstance().exitApplication();
                    
        });

        setLayout(new BorderLayout());
        addComponent(BorderLayout.CENTER, cCenter);
        addComponent(BorderLayout.SOUTH, btnClose);
       
        populateScreen(cCenter);
        

         
       

    }
  private void populateScreen(Container cnt) throws ParseException {
       cnt.removeAll();
       cnt.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
       cnt.setScrollableY(true);
     
       for(eleve c : lis) {
           cnt.addComponent(new AccrEleve(c));
       }
       cnt.repaint();
    }
  
    }
 class AccrEleve extends Accordion {
     
      private Resources theme=UIManager.initFirstTheme("/theme_2");

        eleve c;
        MultiButton mb;
         Container IMG = new Container();
          Container c2 = new Container();
          
       Container c3 = new Container();
        public AccrEleve(eleve c) throws ParseException {
            super();
            this.c = c;
            addContent(
                this.c2 = createHeader(c), createDetail(c)
            );
        }
        
        public eleve getClient() {
            return this.c;
        } 
        
        private Container createHeader(eleve c) {
       
             
            IMG.setPreferredSize(new Dimension(300, 300));
             
 EncodedImage placeholder=EncodedImage.createFromImage(theme.getImage("load.gif"),true);
                                
                //System.out.println(c.getNom_image());
                                       URLImage Urlimg=URLImage.createToStorage(placeholder,"http://127.0.0.1/test/dev/web/imageProduits/"+ c.getNom_image(),"http://127.0.0.1/test/dev/web/imageProduits/"+ c.getNom_image());
   
                                       ImageViewer img=new ImageViewer( Urlimg);
                                       IMG.add(img);
                                       IMG.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
            img.getParent().revalidate();
               Label s = new Label("Nom Eleve :  "+c.getNom());
               
                s.getAllStyles().setFgColor(0xf20d0d) ;
            c2.add(IMG);
            c2.add(s);
            
           return  c2;
                                       //add(img);

   
                
           
          
          
          //return mbt;
            
            
        }
        
        private Container createDetail(eleve c) throws ParseException {
          Label classe =      new Label(); 
  if(c.getClasse_id()==0){
             classe .setText("pas encore Affecté");  }
  else {
       classe .setText("Papillon");
  }
                      
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
               
                        new Label("Niveau:        "),
                       new Label("" +c.getNiveau()),
                        new Label("Age:            "),
                        new Label("" + c.getAge()),
                          new Label("Date de Naissance:                                  "),
                        new Label(""+c.getDate_naissance()),
                        
                      new Label("Classe:                                 "),
                      classe,
                          new Label("Nombre d'Absence:                                 "),
                        new Label(""+c.getNbre_absence()),
                          new Label("Adresse:                                 "),
                        new Label(""+c.getAdresse())
                    
                )            
            );
            
             Resources theme = UIManager.initFirstTheme("/theme_2");
            Container cButtons = new Container(new GridLayout(1,3));
            Button supp =new Button("Retirer Inscription");
              Image Icon=theme.getImage("supprimer2.png");
       supp.setIcon(Icon);
           cDetail.addComponent(BorderLayout.SOUTH, supp);
      supp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

               
       if (Dialog.show("Retirer Inscription", "voulez_vous supprimer L'inscription de votre enfant"+c.getNom()  +"?", "Oui", "Non"))
            {
               ConnectionRequest con = new ConnectionRequest(); 
                   con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/deleteE/"
                            +c.getId()      
                    );
                      removeAll();
                   
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                            
                        System.out.println("L'iscription de votre enfant"+c.getNom()  +"a été supprimé avec succées.");
                          Dialog.show("Confirmation", "vous-devez envoyez un mail à l'administration", new Command("OK"));

                            
                        }
                    });
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    
               
              
                   
                    
                      
            }
          
                    
            }});
           
        
            
          
            
            return cDetail;
        }
        
      
        
        public boolean isInteger(String s) {
            try { 
                Integer.parseInt(s); 
            } catch(NumberFormatException | NullPointerException e) { 
                return false; 
            }
            // only got here if we didn't return false
            return true;
        }        
}
