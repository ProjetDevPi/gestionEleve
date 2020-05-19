
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.FosUser;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.entities.absence;
import com.mycompany.myapp.entities.carnet;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class ServiceUser {

    public ArrayList<Task> tasks;
    public ArrayList<eleve> eleve;
    public ArrayList<carnet> carnet;
      public ArrayList<absence> absence;
    public static ServiceUser instance=null;
      ArrayList<login> login = new ArrayList<>();
    ArrayList<FosUser> allusers = new ArrayList<>();
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean addTask(Task t) {
        String url = Statics.BASE_URL + "/tasks/" + t.getName() + "/" + t.getStatus();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Task> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Task t = new Task();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setName(obj.get("name").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
     
       public ArrayList<FosUser> users() {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/login2");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                try {
                    allusers = ser.parseUsers(new String(con.getResponseData()));
                } catch (IOException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return allusers;
    }
       
        public ArrayList<FosUser> parseUsers(String json) throws IOException {

        ArrayList<FosUser> log = new ArrayList<>();

        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        for (Map<String, Object> obj : list) {
            
            FosUser e = new FosUser();
            
            float id = Float.parseFloat(obj.get("id").toString());
            e.setId((int) id);
            
            e.setEmail(obj.get("email").toString());
            e.setUsername(obj.get("username").toString());
           e.setPassword(obj.get("password").toString());
           
            
            
            log.add(e);
            
        }
        
     
     
        return log;

    }
        public login login() {   
            
            ConnectionRequest con = new ConnectionRequest();
            con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/yass");
            con.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    ServiceUser ser = new ServiceUser();
                    try {
                        login = ser.parseLogin(new String(con.getResponseData()));
                    } catch (IOException ex) {
                        
                    }
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(con);
             return login.get(0);
        }
 
 
  public ArrayList<login> parseLogin(String json) throws IOException {

        ArrayList<login> log = new ArrayList<>();

        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
            login e = new login();
            
            float id = Float.parseFloat(obj.get("id").toString());
            
            e.setId((int) id);
             
             
             float id_user = Float.parseFloat(obj.get("idUser").toString());
            
            e.setId_user((int)id_user);
           
             e.setMail(obj.get("mail").toString());
             e.setUsername(obj.get("username").toString());
            
           
             
            
            log.add(e);
            
        }
  
        return log;

    }
  
  
  public boolean connexion(String pass,String username)
  
  {

       final UpdatableBCrypt s2= new UpdatableBCrypt(10);
     ArrayList<FosUser>  l2=users();
      
           for (FosUser u : l2)
           {
 String pwd=convert(u.getPassword());
  
                   if  ( s2.verifyHash(pass,pwd) && username.equals(u.getUsername()))
            {
                
                return true;
            }
                   
               }

   return false;  
}
    
  
  public String convert(String pass)
  {
       if (pass.startsWith("$2a"))
               {
                   return pass;
               }
  else 
               {
                   String strCopy1 = (pass.substring(3));
                  return "$2a"+strCopy1;
               }
  }
  
    public ArrayList<FosUser> parseFos(String json,String username) throws IOException {

       
FosUser e = new FosUser();
        
        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        
        
        for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
           
            
            if  (obj.get("username").toString().equals(username))
            {
                 float id = Float.parseFloat(obj.get("id").toString());
            e.setId((int) id);
            
            e.setEmail(obj.get("email").toString());
            e.setUsername(obj.get("username").toString());
           e.setPassword(obj.get("password").toString());
                    allusers.add(e);
                
           }

           }
           return allusers;
        }
 
  
  
  public ArrayList<FosUser> FosUser2(String username) {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1/test/dev/web/app_dev.php/login3/"+username);  
        System.out.println(con.getUrl());
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                try {
                    allusers = ser.parseFos(new String(con.getResponseData()),username);
                } catch (IOException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return allusers;
    }

    
    
    public ArrayList<Task> getAllTasks(){
        String url = Statics.BASE_URL+"/tasks/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    
      
}
