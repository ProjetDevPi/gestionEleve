/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.absence;
import com.mycompany.myapp.entities.carnet;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.services.ServiceEleve;

import java.util.ArrayList;


/**
 *
 * @author wejdene
 */
public class listAbsence extends Form {
         ServiceEleve serviceEleve = new ServiceEleve();
            ArrayList<absence> lis = serviceEleve.getAllabsence();
            Container cCenter = new Container();
        

    public listAbsence(Form previous) throws ParseException {
        
      super(); 
        setTitle("Absence");
      
      Toolbar toolBar = getToolbar();
                 toolBar.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_LOGOUT, 6f,( ActionEvent e) -> {
                   

            
          new Menu().show();
            
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
     
       for(absence c : lis) {
           cnt.addComponent(new AccrAbsence(c));
       }
       cnt.repaint();
    }
  
    }
 class AccrAbsence extends Accordion {
    Form current;
        absence c;
        MultiButton mb;
        
        public AccrAbsence(absence c) throws ParseException {
            super();
            this.c = c;
            addContent(
                this.mb = (MultiButton) createHeader(c), createDetail(c)
            );
        }
        
        public absence getAbsence() {
            return this.c;
        } 
        
        private Container createHeader(absence c) {
              eleve e=new eleve();
              ServiceEleve serviceEleve = new ServiceEleve();
         
         
              e=serviceEleve.getNomEleve(c.getEleve_id());
            MultiButton mbt = new MultiButton();
            mbt.setTextLine1("Absence de  "+e.getNom());
            mbt.setTextLine2("Vous Pouvez confirmer cette Absence ");
            return mbt;
        }
         TextField txt_jus =new TextField("");
             Label lbl=new Label();

        private Container createDetail(absence c) throws ParseException {
         
   SimpleDateFormat Date = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat Heure = new SimpleDateFormat("HH:mm");
      
                    String d = Date.format(c.getDate());
                    String h = Heure.format(c.getDate());
                    Label date = new Label("" + d + " à " + h);
                      Label l=    new Label("Justification:   ");
                     
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
                l,
              
            
                     txt_jus=new TextField(c.getJustification()),
                          new Label("Date:        "),
                        date,
                          new Label("etat:            "),
                        new Label("" + c.getEtat())
                      
                     
                    
                )            
            );
             Resources theme = UIManager.initFirstTheme("/theme_2");
            Container cButtons = new Container(new GridLayout(1,3));
            Button edit=new Button("comfirmer l'absence");
        cDetail.addComponent(BorderLayout.SOUTH, edit);
             Image Icon=theme.getImage("abscence.png");
       edit.setIcon(Icon);
            
        
        
            
         
              edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

               
     
                    
                    ConnectionRequest con = new ConnectionRequest();
                    con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/edite/"
                            +c.getId()
                            +"?justification="+txt_jus.getText()
                     
                            
                    );
                       
               /*      lbl=new Label("la justification a été changée :   "+c.getJustification());
                       lbl.getAllStyles().setFgColor(0xf20d0d);*/
                      
  cDetail.addComponent(BorderLayout.SOUTH, lbl);
                  
        
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {
                      System.out.println(" is done babe");
                     Dialog.show("Confirmation", "L'absence a ete modifiée avec succée\n la Rectification est envoyé a l'Administration", "OK",null);
                     
                
                            try {
                                new  listAbsence(current).show();
                            } catch (ParseException ex) {
                               
                            }
                  

                        
                        }
                    });
                    
                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                    
                   
                              





            }
            
        });
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
