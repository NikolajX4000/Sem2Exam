package presentationLayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author super
 */


public class SVG {

    private final double svgWidth;
    private final double svgHeight;
    private StringBuilder svg = new StringBuilder();
    private String svgClass = "";

    /**
     *
     * @param width
     * @param height
     */
    public SVG(double width, double height)
    {
        this.svgWidth = width;
        this.svgHeight = height;
    }
    
    /**
     *
     * @param width
     * @param height
     * @param clas
     */
    public SVG(double width, double height, String clas)
    {
        this.svgWidth = width;
        this.svgHeight = height;
        this.svgClass = " class='" + clas + "'";
    }
    
    /**
     *
     * @param x
     * @param y
     * @param height
     * @param width
     * @param style
     */
    public void rct(double x, double y, double height, double width, String style){
        
        StringBuilder s = new StringBuilder();
        s.append("<rect");
        s.append(" x='").append(x).append("'"); 
        s.append(" y='").append(y).append("'"); 
        s.append(" height='").append(height).append("'"); 
        s.append(" width='").append(width).append("'"); 
        s.append(" style='").append(style).append("'");
        s.append("/>");
        svg.append(s);
    }
    
    /**
     *
     * @param x
     * @param y
     * @param height
     * @param width
     * @param style
     * @param custom
     */
    public void rct(double x, double y, double height, double width, String style, String custom){
        
        StringBuilder s = new StringBuilder();
        s.append("<rect");
        s.append(" x='").append(x).append("'"); 
        s.append(" y='").append(y).append("'"); 
        s.append(" height='").append(height).append("'"); 
        s.append(" width='").append(width).append("'"); 
        s.append(" style='").append(style).append("'"); 
        s.append(" ").append(custom);
        s.append("/>");
        svg.append(s);
    }
    
    /**
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param style
     */
    public void line(double x1, double y1, double x2, double y2, String style){
        
        StringBuilder s = new StringBuilder();
        s.append("<line");
        s.append(" x1='").append(x1).append("'"); 
        s.append(" y1='").append(y1).append("'"); 
        s.append(" x2='").append(x2).append("'"); 
        s.append(" y2='").append(y2).append("'");
        s.append(" style='").append(style).append("'");
        s.append("/>");
        svg.append(s);
    }
    
    /**
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param style
     * @param custom
     */
    public void line(double x1, double y1, double x2, double y2, String style, String custom){
        
        StringBuilder s = new StringBuilder();
        s.append("<line");
        s.append(" x1='").append(x1).append("'"); 
        s.append(" y1='").append(y1).append("'"); 
        s.append(" x2='").append(x2).append("'"); 
        s.append(" y2='").append(y2).append("'");
        s.append(" style='").append(style).append("'");
        s.append(" ").append(custom);
        s.append("/>");
        svg.append(s);
    }

    @Override
    public String toString()
    {
        
        StringBuilder s = new StringBuilder();
        s.append("<SVG");
        s.append(svgClass);
        s.append(" xmlns='http://www.w3.org/2000/svg'");
        s.append(" version='1.1' style='width:100%;padding:5px;'");
        s.append(" viewBox='0 0 " + svgWidth + " " + svgHeight + "'");
        s.append(">");
        
        s.append(svg);
        
        s.append("</SVG>");
        return s.toString();
    }
    
    
}