/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.Accordion;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.File;
import com.codename1.io.Log;
import com.codename1.io.Storage;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.messaging.Message;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.mycompany.myapp.entities.carnet;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.services.ServiceEleve;
import com.mycompany.myapp.services.ServiceUser;




import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;





/**
 *
 * @author bhk
 */
public class affichageCar extends Form{
       
              ServiceEleve serviceEleve = new ServiceEleve();
            ArrayList<carnet> lis = serviceEleve.getAllcarnet();
            Container cCenter = new Container();

    public affichageCar(Form previous) throws ParseException {
        
      super(); 
        setTitle("Carnets");
         
            
     
        Toolbar toolBar = getToolbar();
          
           toolBar.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_LOGOUT, 6f,( ActionEvent e) -> {
                   

            
          new Menu().show();
            
        });
        toolBar.getAllStyles().setFgColor(0xf20d0d);
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
     
       for(carnet c : lis) {
           cnt.addComponent(new AccrClient(c));
       }
       cnt.repaint();
    }
  
    }
 class AccrClient extends Accordion {

        carnet c;
        MultiButton mb;
        
        public AccrClient(carnet c) throws ParseException {
            super();
            this.c = c;
            addContent(
                this.mb = (MultiButton) createHeader(c), createDetail(c)
            );
        }
        
        public carnet getClient() {
            return this.c;
        } 
        
        private Container createHeader(carnet c) {
               eleve e=new eleve();
              ServiceEleve serviceEleve = new ServiceEleve();
         
         
              e=serviceEleve.getNomEleve(c.getEleve_id());
            MultiButton mbt = new MultiButton();
            
            mbt.setTextLine1("Carnet de "+e.getNom());
            mbt.setTextLine2("Vous Pouvez Confirmer Cette Absence");
            return mbt;
        }
        
        private Container createDetail(carnet c) throws ParseException {
         
   SimpleDateFormat Date = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat Heure = new SimpleDateFormat("HH:mm");
                    
                    String d = Date.format(c.getDate());
                    String h = Heure.format(c.getDate());
                    Label date = new Label("" + d + " à " + h);
                      Label l=    new Label("Semestre:   ");
                      
            Container cDetail = BorderLayout.center(
                TableLayout.encloseIn(
                        2,
                        true,
                l,
                        new Label(c.getSemestre()),
                          new Label("Date:        "),
                        date,
                          new Label("Note:            "),
                        new Label("" + c.getNote()),
                          new Label("Appreciation:                                   "),
                        new Label(""+c.getAppreciation())
                     
                    
                )            
            );
            
            Container cButtons = new Container(new GridLayout(1,3));
         
            //cButtons.addComponent(pdf);
        
  
             Resources theme = UIManager.initFirstTheme("/theme_2");
           Button pdf=new Button("print pdf");
                    Image Icon=theme.getImage("carnet.png");
       pdf.setIcon(Icon);
            
      cDetail.addComponent(BorderLayout.SOUTH, pdf);
   pdf.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
           Display.getInstance().setProperty("WebLoadingHidden", "true");

  
  BrowserComponent  browser = new BrowserComponent();

 
        browser.setURL("http://localhost/test/dev/web/app_dev.php/pdf2");
           Display.getInstance().execute(browser.getURL());


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

            /*  for(carnet t : lis){
            
        setTitle("List tasks");
    
                    SpanLabel phone = new SpanLabel(t.getNote()+"");
                    SpanLabel email = new SpanLabel(t.getAppreciation());
                    SpanLabel sem = new SpanLabel(t.getSemestre());
         add(phone);
        add(email);
        add(sem);
              }

     
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());*/
    
    

