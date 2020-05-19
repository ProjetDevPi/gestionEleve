/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.MenuBar;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceEleve;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;

import com.codename1.capture.Capture;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Toolbar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




/**
 *
 * @author wejdene
 */
public class addEleve  extends Form{
      Form current;

    com.codename1.io.File file;
    String fileName;
//    public static int idUser=User.user.getId();
    
    TextField tf_nom;
    TextField tf_prenom;
    TextField tf_age;
    TextField tf_niveau;
    TextField tf_adresse;

    
        Picker date;

   public addEleve(Form previous) {
 ServiceUser s1= new ServiceUser();
  ServiceEleve s2= new ServiceEleve();
       
         setTitle("Formulaire Inscription :Kid'o");
         setLayout(BoxLayout.y());
                 MenuBar m=new MenuBar();
               Toolbar tb = getToolbar();
        Resources theme = UIManager.initFirstTheme("/theme_2");
          tb.addMaterialCommandToLeftBar(
                   "", FontImage.MATERIAL_LOGOUT, 6f,( ActionEvent e) -> {
                   

            
          new Menu().show();
            
        });
ComboBox interval = new ComboBox(new Object[]{ "Creche", "Preparatoire", "Garderie"});
         //-------------------------------Components-------------------------------------
         Label nom= new Label("           Nom: ");
         Label prenom = new Label("       Prenom:");
         Label age = new Label("          Age:");
         Label niveau = new Label("Niveau Scolaire:");
         Label adresse = new Label("      Adresse:");
         nom.getAllStyles().setFgColor(	0x000000);
  
           prenom.getAllStyles().setFgColor(0x000000);
             age.getAllStyles().setFgColor(0x000000);
               niveau.getAllStyles().setFgColor(0x000000);
                 adresse.getAllStyles().setFgColor(0x000000);
 
        tf_nom = new TextField("");

        tf_prenom = new TextField("");
        tf_age = new TextField("");
        tf_niveau = new TextField("");
        tf_adresse = new TextField("");
 
                Container uploadCont = new Container(new GridLayout(1, 2));
                Button upload = new Button("upload");
                ImageViewer imgV = new ImageViewer();
                  Image Icon=theme.getImage("upload.png");
       upload.setIcon(Icon);
            imgV.setPreferredSize(new Dimension(400, 400));
             
                Label img_lab = new Label(" ");
           
                //imgV.setVisible(false);
             
                uploadCont.add(imgV);
            

                upload.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        ActionListener actionListner = new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ev) {

                                if (ev != null && ev.getSource() != null) {

   
                                   String filePath = (String) ev.getSource();
                                   System.out.println(filePath);

                                    // file=(File) ev.getSource();
                                  int fileNameIndex = filePath.lastIndexOf("/") + 1;
                                   fileName = filePath.substring(fileNameIndex, filePath.length() - 4) + "jpg";

                                    System.out.println(fileName);

                                    Image img = null;

                                    try {

                                      
                                      img = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath));
                                          imgV.setImage(img);
                                        img_lab.setText(filePath);
                                        //imgV.setVisible(true);

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }

                                }
                            }
                        };

                        Display.getInstance().openGallery(actionListner, Display.GALLERY_IMAGE);

                    }
                });
        //      upload        

       Button add = new Button("s'inscrir");
       Image Icon2=theme.getImage("ins.png");
       add.setIcon(Icon2);
       
       
       
        //=======================================================================================
        //=====================================TRAITEMENT========================================
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
             
                try {

                    s2.saveFile(img_lab.getText(), fileName);
 login l =new login();
             l=s1.login();
                    System.out.println(l);
             
                    ConnectionRequest con = new ConnectionRequest();
                   
                    con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/addEleve?"
                            + "user_id="+l.getId_user()
                            + "&nom="+tf_nom.getText()
                        
                            + "&prenom=" + tf_prenom.getText()
                            + "&niveau=" +  interval.getSelectedItem()
                            + "&age=" + tf_age.getText()
                            + "&adresse=" + tf_adresse.getText()
                            + "&nom_image=" + fileName
                            );
System.out.println(con.getUrl());
                    System.out.println("************************");
                    System.out.println(tf_nom.getText()
                            + "/" + tf_prenom.getText()
                          
                            + "/" + tf_age.getText()
                            + "/" + tf_niveau.getText()
                            + "/" + tf_adresse.getText()
                          
                            + "/" + img_lab.getText()
                            + "/" + 1);
                    System.out.println("************************");

                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {

                            System.out.println("L'iscription a été ajouté avec succées.");
                            
                            ToastBar.showMessage("challenge ajouté avec succées.",FontImage.MATERIAL_DONE);
                            
                 
                        s2.mail();
new Menu().show();
                        }
                    });

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                         
                 
                    
                          
        


                } catch (IOException ex) {
                }
                        }
      
            
        });
        
         //=======================================================================================
        //=======================================================================================
         addComponent(nom);
        
         addComponent(tf_nom);
          addComponent(prenom);
         addComponent(tf_prenom);
         addComponent(age);
          addComponent(tf_age);
          addComponent(niveau);
          addComponent(interval);
         addComponent(adresse);
          addComponent(tf_adresse);
           addComponent(upload);
     
        //f.add(dateCont);
    
         addComponent(uploadCont);

         addComponent(add);
            

    }
}



    
