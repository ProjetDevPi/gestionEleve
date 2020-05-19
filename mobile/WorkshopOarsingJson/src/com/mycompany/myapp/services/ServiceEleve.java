/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.absence;
import com.mycompany.myapp.entities.carnet;
import com.mycompany.myapp.entities.classe;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.gui.Menu;
import static com.mycompany.myapp.services.ServiceUser.instance;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author wejdene
 */
public class ServiceEleve {
        public ArrayList<eleve> eleve;
    public ArrayList<carnet> carnet;
      public ArrayList<absence> absence;
    public static ServiceEleve instance=null;
        public boolean resultOK;
    private ConnectionRequest req;
    ServiceUser s1 =new ServiceUser();
    
     public ServiceEleve() {
         req = new ConnectionRequest();
    }

    public static ServiceEleve getInstance() {
        if (instance == null) {
            instance = new ServiceEleve();
        }
        return instance;
    }
     public ArrayList<eleve> parseEleve(String jsonText){
        try {
            eleve=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(list);
            for(Map<String,Object> obj : list){
                eleve t = new eleve();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                eleve.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return eleve;
    }
      public ArrayList<eleve> parseFind(String json) throws IOException {

        ArrayList<eleve> eleve = new ArrayList<>();

        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
            eleve e = new eleve();
            
            float id = Float.parseFloat(obj.get("id").toString());
            
            e.setId((int) id);
             
             
     
           
             e.setNom(obj.get("nom").toString());
           
            
                 
            eleve.add(e);
            
        }
  
        return eleve;

    }
       public ArrayList<carnet> parseCarnet(String jsonText){
        try {
            carnet=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
               
            for(Map<String,Object> obj : list){
             if(obj!=null){
  Map<String, Object> listRecupCategory = null;
                carnet t = new carnet();
                  eleve e = new eleve();
                if (obj.get("eleve") != null) {

                    listRecupCategory = (Map<String, Object>) obj.get("eleve");
                     t.setEleve_id((int) Float.parseFloat(listRecupCategory.get("id").toString()));
            
                 }
          
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                 
           
                t.setAppreciation(obj.get("appreciation").toString());
                Map<String, Object> date = null;
                        date = (Map<String, Object>) obj.get("date");
                    
                        try {

                            Date longdate = new Date((long) Float.parseFloat(date.get("timestamp").toString()) * 1000);
                            

                            System.out.println("*************" + longdate);

                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                            String d = formatter.format(longdate);
                            t.setDate(longdate);
                        } catch (NumberFormatException ex) {

                        }
                
                   t.setSemestre(obj.get("semestre").toString());
                       float note = Float.parseFloat(obj.get("note").toString());
                t.setNote((int)note);
System.out.println(t);

                carnet.add(t);
                

             }    
            
            }
            
        } catch (IOException ex) {
            
        }
        return carnet;
    }
       
         public ArrayList<absence> parseAbsence(String jsonText){
        try {
            absence=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
               
            for(Map<String,Object> obj : list){
                  absence t = new absence();
             if(obj!=null){
                  Map<String, Object> listRecupCategory = null;

                eleve e = new eleve();
                if (obj.get("eleve") != null) {

                    listRecupCategory = (Map<String, Object>) obj.get("eleve");
                     t.setEleve_id((int) Float.parseFloat(listRecupCategory.get("id").toString()));
            
                 }
          
           
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                 
           
                t.setJustification(obj.get("justification").toString());
                Map<String, Object> date = null;
                        date = (Map<String, Object>) obj.get("date");
                    
                        try {

                            Date longdate = new Date((long) Float.parseFloat(date.get("timestamp").toString()) * 1000);
                            

                            System.out.println("*************" + longdate);

                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                            String d = formatter.format(longdate);
                            t.setDate(longdate);
                        } catch (NumberFormatException ex) {

                        }
                
                   t.setEtat(obj.get("etat").toString());
                  
              
System.out.println(t);

                absence.add(t);
                

             }    
            
            }
            
        } catch (IOException ex) {
            
        }
        return absence;
    }
           public ArrayList<eleve> parseE(String jsonText){
        try {
            eleve=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
               
            for(Map<String,Object> obj : list){
                  if(obj!=null){
                  eleve e = new eleve();
           System.out.println(list);
          
           
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int)id);
                 
           System.out.println(e.getId());
               
                      Map<String, Object> date = null;
                        date = (Map<String, Object>) obj.get("dateNaissance");
                    
                        try {

                            Date longdate = new Date((long) Float.parseFloat(date.get("timestamp").toString()) * 1000);
                            

                            System.out.println("*************" + longdate);

                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                            String d = formatter.format(longdate);
                            e.setDate_naissance(longdate);
                        } catch (NumberFormatException ex) {

                        }
                 e.setNom(obj.get("nom").toString());
                   float age = Float.parseFloat(obj.get("age").toString());
                e.setAge((int)age);
                 
                   e.setPrenom(obj.get("prenom").toString());
                    e.setAdresse(obj.get("adresse").toString());
                      e.setNom_image(obj.get("nomImage").toString());
                e.setNiveau(obj.get("niveau").toString());
                
                 Map<String, Object> listRecupCategory = null;

                classe c= new classe();
                if (obj.get("classe") != null) {

                    listRecupCategory = (Map<String, Object>) obj.get("classe");
                     e.setClasse_id((int) Float.parseFloat(listRecupCategory.get("id").toString()));
            
                 }
           
                if (obj.get("nbreAbsence") != null) {

              float ab = Float.parseFloat(obj.get("nbreAbsence").toString());
                e.setNbre_absence((int)ab);
                 }
              

                


                eleve.add(e);
                

                  }      
            
            }
            
        } catch (IOException ex) {
            
        }
        return eleve;
    }
            public ArrayList<eleve> getAllE(){
              login l =new login();
             l=s1.login();
             
        String url = "http://127.0.0.1/test/dev/web/app_dev.php/affE/"+l.getId_user();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                eleve = parseE(new String(req.getResponseData()));
      
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return eleve;
    }
          public ArrayList<absence> getAllabsence(){
              login l =new login();
             l=s1.login();
             
        String url = "http://127.0.0.1/test/dev/web/app_dev.php/affA/"+l.getId_user();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                absence = parseAbsence(new String(req.getResponseData()));
      
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return absence;
    }
           public void saveFile(String input,String output) throws IOException {

        OutputStream outStream = null;
        InputStream stream = null;

            //------------------------------------TO STORAGE-------------------------------------
            
            stream = FileSystemStorage.getInstance().openInputStream(input);
            OutputStream out =Storage.getInstance().createOutputStream(output);
            Util.copy(stream, out);
            Util.cleanup(stream);
            Util.cleanup(out);
            System.out.println("the file is copied successfully in the storage !");
            

            
            



             

         }
            public ArrayList<eleve> getAllEleve(){
        String url = "http://127.0.0.1/test/dev/web/app_dev.php/eleve";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                eleve = parseEleve(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return eleve;
    }
          public ArrayList<carnet> getAllcarnet(){
              login l =new login();
              l=s1.login();
             
        String url = "http://127.0.0.1/test/dev/web/app_dev.php/mobile/"+l.getId_user();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                carnet = parseCarnet(new String(req.getResponseData()));
      
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return carnet;
    }
          public eleve getNomEleve(int id ){
            
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/findE/"+id);  
      
      
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                try {
                    eleve = parseFind(new String(con.getResponseData()));
                } catch (IOException ex) {
                   
                }
      
           
                
            }
        });
    NetworkManager.getInstance().addToQueueAndWait(con);
        return eleve.get(0);
    }
          public void mail(){
                  login l =new login();
             l=s1.login();
            ConnectionRequest con = new ConnectionRequest();
                   
                    con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/mail2/"
                     
                            );


                 

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                         
                 
                    
                          
        


                        }
      
        
          }

