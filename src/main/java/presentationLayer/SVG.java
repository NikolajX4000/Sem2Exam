package presentationLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class SVG {

    private final double svgWidth;
    private final double svgHeight;
    private String svg = "";
    private String svgClass = "";

    public SVG(double width, double height)
    {
        this.svgWidth = width;
        this.svgHeight = height;
    }
    
    public SVG(double width, double height, String clas)
    {
        this.svgWidth = width;
        this.svgHeight = height;
        this.svgClass = " class='" + clas + "'";
    }
    
    public void rct(double x, double y, double height, double width, String style){
        
        String s = "<rect";
        s+= " x='" + x + "'"; 
        s+= " y='" + y + "'"; 
        s+= " height='" + height + "'"; 
        s+= " width='" + width + "'"; 
        s+= " style='" + style + "'";
        s+= "/>";
        svg+=s;
    }
    
    public void rct(double x, double y, double height, double width, String style, String custom){
        
        String s = "<rect";
        s+= " x='" + x + "'"; 
        s+= " y='" + y + "'"; 
        s+= " height='" + height + "'"; 
        s+= " width='" + width + "'"; 
        s+= " style='" + style + "'"; 
        s+= " " + custom;
        s+= "/>";
        svg+=s;
    }
    
    public void line(double x1, double y1, double x2, double y2, String style){
        
        String s = "<line";
        s+= " x1='" + x1 + "'"; 
        s+= " y1='" + y1 + "'"; 
        s+= " x2='" + x2 + "'"; 
        s+= " y2='" + y2 + "'";
        s+= " style='" + style + "'";
        s+= "/>";
        svg+=s;
    }
    
    public void line(double x1, double y1, double x2, double y2, String style, String custom){
        
        String s = "<line";
        s+= " x1='" + x1 + "'"; 
        s+= " y1='" + y1 + "'"; 
        s+= " x2='" + x2 + "'"; 
        s+= " y2='" + y2 + "'";
        s+= " style='" + style + "'";
        s+= " " + custom;
        s+= "/>";
        svg+=s;
    }

    @Override
    public String toString()
    {
        String s = "<SVG";
        s+= svgClass;
        s+= " xmlns='http://www.w3.org/2000/svg'";
        s+= " version='1.1' style='width:100%;padding:5px;'";
        s+= " viewBox='0 0 " + svgWidth + " " + svgHeight + "'";
        s+= ">";
        
        s+= svg;
        
        s+= "</SVG>";
        return s;
    }
    
    
}