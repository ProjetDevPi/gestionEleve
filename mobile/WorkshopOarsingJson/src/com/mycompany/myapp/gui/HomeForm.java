/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.services.UpdatableBCrypt;


/**
 *
 * @author bhk
 */
public class HomeForm extends Form{

 Form current;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff;
    final UpdatableBCrypt s2= new UpdatableBCrypt(10);
    public HomeForm() {
              Resources theme = UIManager.initFirstTheme("/theme_2");
        Image icon=theme.getImage("seo-and-web.png");
        ImageViewer img=new ImageViewer(icon);

        login l=new login();
        ServiceUser s1= new ServiceUser();
 l=s1.login();
       
        
    
        System.out.println(l.getId_user());
     new BorderLayout();
     Label lbl=new Label("Username");
        Label lbl1=new Label("Password");
        tnom = new TextField();
        tetat = new TextField();
             tetat.setConstraint(TextField.PASSWORD);
        btnajout = new Button("Login");
   
        add(img);
          add(lbl);
        add(tnom);
          add(lbl1);
        add(tetat);
        add(btnajout);
     
        btnajout.addActionListener((e) -> {
         if (s1.connexion(tnom.getText(),tetat.getText()))
         { 
             
                Dialog.show("Success", "Les données sont Correctes", new Command("OK"));
             System.out.println("les données s7a7");
            System.out.println(s1.FosUser2(tetat.getText())); 
                
            new Menu().show();
         }
         else {
             System.out.println("les données ghaltin ");
         }
             
        });
    
     
        setTitle("Login ");
        setLayout(BoxLayout.y());
       
        
     
  
        
        
    }
    
    
}
