/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.Order;
import java.util.Random;

public class RenderOrder {
    
    private static Order o;
    private static String id;

    private RenderOrder() {
    }
    
    private static String unqString(int amount){
        
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String randomString = "";
        Random rand = new Random();
        char[] text = new char[amount];
        
        for (int i = 0; i < amount; i++) {
            text[i] = chars.charAt(rand.nextInt(chars.length()));
        }
        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }
        return randomString;
        
    }

    private static String getStatusColor(){
        String color = "orange";
        switch (o.getStatus()) {
            case "Modtaget":
                color = "green";
                break;
            case "Sendt":
                color = "blue";
                break;
            case "Annulleret":
                color = "red";
                break;
            case "Behandles":
                color = "orange";
                break;
        }
        return color;
    }
    
    private static String cardTop(){
        String s = "";
        
        s+="<div class=\"card-content\">";
        
            s+="<span class=\"new badge "+ getStatusColor() +" right\" data-badge-caption=\""+ o.getStatus() +"\"></span>";
            s+="<span class=\"card-title\">Carport #"+ o.getId() +"</span>";

            s+="<blockquote style=\"border-color: #2196f3;\">";
                
                s+="<div class=\"right\">";
                
                    s+="<p class=\"right\">Bestilt: "+ o.getPlaced() +"</p><br><br>";
                    
                    if(o.isFlat()){
                        s+="<img class=\"right\" src=\"https://www.johannesfog.dk/globalassets/service/quickbyg/quickurejs.gif\">";
                    }else{
                        s+="<img class=\"right\" src=\"https://www.johannesfog.dk/globalassets/service/quickbyg/quickmrejs.gif\">";
                    }
                    
                s+="</div>";
                
                s+="<p>Navn: " + o.getName() + "</p>";
                s+="<p>Addresse: "+ o.getAddress() +", "+ o.getZipCode() +" "+ o.getCity() +"</p><br>";
                
                s+="<div>";
                    s+="<p >Telefon: "+ o.getPhone() +"</p>";
                    s+="<p >E-mail: "+ o.getEmail() +"</p>";
                s+="</div>";

            s+="</blockquote>";
        
        
        s+="</div>";
        
        return s;
    }
    
    private static String cardTabs(){
        String s = "";
        
        s+="<div class=\"card-tabs\"><ul class=\"tabs\">";
        
        s+="<li class=\"tab\"><a href=\"#"+ id + "a" +"\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Detaljer\"><i class=\"material-icons\">zoom_out_map</i></a></li>";
        s+="<li class=\"tab\"><a href=\"#"+ id + "b" +"\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Tegninger\"><i class=\"material-icons\">photo</i></a></li>";
        s+="<li class=\"tab\"><a href=\"#"+ id + "c" +"\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Tegninger\"><i class=\"material-icons\">format_list_bulleted</i></a></li>";
        s+="<li class=\"tab\"><a href=\"#"+ id + "d" +"\" class=\"tooltipped\" data-position=\"top\" data-delay=\"50\" data-tooltip=\"Tegninger\"><i class=\"material-icons\">event_note</i></a></li>";
        
        s+="</ul></div>";
        
        return s;
    }
    
    
    private static String tabA(){
        String s = "";
        
        s+="<div id=\""+ id + "a" +"\">";
        s+="<div class=\"row black-text\">";
        
            s+="<div class=\"input-field col s6\">";
                s+="<input disabled class=\"black-text\" type=\"text\" value=\""+ o.getWidth() +" cm\">";
                s+="<label>Carport Bredde</label>";
            s+="</div>";
            
            s+="<div class=\"input-field col s6\">";
                s+="<input disabled class=\"black-text\" type=\"text\" value=\""+ o.getLength() +" cm\">";
                s+="<label>Carport Længde</label>";
            s+="</div>";
            
            
            
            if(o.hasShed()){
            s+="<div class=\"input-field col s6\">";
                s+="<input disabled class=\"black-text\" type=\"text\" value=\""+ o.getShedWidth()+" cm\">";
                s+="<label>Redskabsrum Bredde</label>";
            s+="</div>";

            s+="<div class=\"input-field col s6\">";
                s+="<input disabled class=\"black-text\" type=\"text\" value=\""+ o.getShedLength() +" cm\">";
                s+="<label>Redskabsrum Længde</label>";
            s+="</div>";
            }
            
            
            
            s+="<div class=\"input-field col s6\">";
                s+="<input disabled class=\"black-text\" type=\"text\" value=\""+ o.getRoof() + " Flot Rødt Tag" +"\">";
                s+="<label>Tag</label>";
            s+="</div>";
            
            if(!o.isFlat()){
            s+="<div class=\"input-field col s6\">";
                s+="<input disabled class=\"black-text\" type=\"text\" value=\""+ o.getAngle()+" grader\">";
                s+="<label>Taghældning</label>";
            s+="</div>";
            }
            
        s+="</div>";
        s+="</div>";
        
        return s;
    }
    
    private static String tabB(){
        String s = "";
        
        s+="<div id=\""+ id + "b" +"\">";
            s+="<span class=\"card-title\">Tegninger</span>";
        s+="</div>";
        
        return s;
    }
    
    private static String tabC(){
        String s = "";
        
        s+="<div id=\""+ id + "c" +"\">";
            s+="<span class=\"card-title\">Stykliste</span>";
            s+="<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>";
        s+="</div>";
        
        return s;
    }
    
    private static String tabD(){
        String s = "";
        
        String note = (o.getNote().equals(""))? "Der er ikke nogen bemærkninger til denne ordre." : o.getNote();
        
        s+="<div id=\""+ id + "d" +"\">";
            s+="<span class=\"card-title\">Bemærkning</span>";
            s+="<p>"+ note +"</p>";
        s+="</div>";
        
        return s;
    }
    
    public static String print(Order o)
    {
        
        RenderOrder.o = o;
        RenderOrder.id = unqString(10);
        
        String s = "";
        
        s+= "<div class=\"col l6 m12\">";
        s+= "<div class=\"card\">";
        
        s+= cardTop();
        s+= cardTabs();
        
        s+="<div class=\"card-content grey lighten-4\">";
        
            s+= tabA();
            s+= tabB();
            s+= tabC();
            s+= tabD();
        
        s+="</div>";
        
        s+= "</div>";
        s+= "</div>";
        
        return s;
        
        //return "RenderOrder{" + "s=" + s + '}';
    }
    
    
    
    

}
