package functionLayer;

import java.util.Random;

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
    
    int uniqID = new Random().nextInt(Integer.MAX_VALUE);


    /**
     *
     * @param width
     * @param height
     */
    public SVG(double width, double height)
    {
        this.svgWidth = width;
        this.svgHeight = height;
        svgStart();
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
        svgStart();
    }
    
    
    private void svgStart(){
        svg.append("<SVG");
        svg.append(svgClass);
        svg.append(" xmlns='http://www.w3.org/2000/svg'");
        svg.append(" version='1.1' style='width:100%;padding:5px;'");
        svg.append(" viewBox='-50 -50 ").append(svgWidth+100).append(" ").append(svgHeight+100).append("'");
        svg.append(">");
        
        svg.append("<defs>");
            svg.append("<marker id=\"beginArrow").append(uniqID).append("\" markerWidth=\"9\" markerHeight=\"9\" refX=\"0\" refY=\"4\" orient=\"auto\">");
            svg.append("<path d=\"M0,4 L8,0 L8,8 L0,4\" style=\"fill: #000000;\" />");
            svg.append("</marker>");

            svg.append("<marker id=\"endArrow").append(uniqID).append("\" markerWidth=\"9\" markerHeight=\"9\" refX=\"8\" refY=\"4\" orient=\"auto\">");
            svg.append("<path d=\"M0,0 L8,4 L0,8 L0,0\" style=\"fill: #000000;\" />");
            svg.append("</marker>");
        svg.append("</defs>");

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
        
        svg.append("<rect");
        svg.append(" x='").append(x).append("'"); 
        svg.append(" y='").append(y).append("'"); 
        svg.append(" height='").append(height).append("'"); 
        svg.append(" width='").append(width).append("'"); 
        svg.append(" style='").append(style).append("'");
        svg.append("/>");
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
        
        svg.append("<rect");
        svg.append(" x='").append(x).append("'"); 
        svg.append(" y='").append(y).append("'"); 
        svg.append(" height='").append(height).append("'"); 
        svg.append(" width='").append(width).append("'"); 
        svg.append(" style='").append(style).append("'"); 
        svg.append(" ").append(custom);
        svg.append("/>");
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
        
        svg.append("<line");
        svg.append(" x1='").append(x1).append("'"); 
        svg.append(" y1='").append(y1).append("'"); 
        svg.append(" x2='").append(x2).append("'"); 
        svg.append(" y2='").append(y2).append("'");
        svg.append(" style='").append(style).append("'");
        svg.append("/>");
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
        
        svg.append("<line");
        svg.append(" x1='").append(x1).append("'"); 
        svg.append(" y1='").append(y1).append("'"); 
        svg.append(" x2='").append(x2).append("'"); 
        svg.append(" y2='").append(y2).append("'");
        svg.append(" style='").append(style).append("'");
        svg.append(" ").append(custom);
        svg.append("/>");
    }
    
    public void text(double x, double y, String text){
        
        svg.append("<text");
        svg.append(" x='").append(x).append("'");
        svg.append(" y='").append(y).append("'");
        svg.append(" fill='black'");
        svg.append(" text-anchor='middle'");
        svg.append(">").append(text).append("</text>");
    }
    
    public void textRotated(double x, double y, String text){
        
        svg.append("<text");
        svg.append(" x='").append(x).append("'");
        svg.append(" y='").append(y).append("'");
        svg.append(" fill='black'");
        svg.append(" text-anchor='middle'");
        svg.append(" transform='rotate(-90 ").append(x).append(" ").append(y).append(")'");
        svg.append(">").append(text).append("</text>");
    }
    
    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void arrowX(double x1, double y1, double x2, double y2){
        
        svg.append("<line");
        svg.append(" x1='").append(x1).append("'"); 
        svg.append(" y1='").append(y1).append("'"); 
        svg.append(" x2='").append(x2).append("'"); 
        svg.append(" y2='").append(y2).append("'");
        svg.append(" style='stroke:black; stroke-width: 0.75; marker-start: url(#beginArrow").append(uniqID).append("); marker-end: url(#endArrow").append(uniqID).append(");'");
        svg.append("/>");
        
        svg.append("<line");
        svg.append(" x1='").append(x1).append("'"); 
        svg.append(" y1='").append(y1+9).append("'"); 
        svg.append(" x2='").append(x1).append("'"); 
        svg.append(" y2='").append(y1-10).append("'");
        svg.append(" style='stroke:grey;'");
        svg.append("/>");

        svg.append("<line");
        svg.append(" x1='").append(x2).append("'"); 
        svg.append(" y1='").append(y2+9).append("'"); 
        svg.append(" x2='").append(x2).append("'"); 
        svg.append(" y2='").append(y2-10).append("'");
        svg.append(" style='stroke:grey;'");
        svg.append("/>");
    }
    
    public void arrowY(double x1, double y1, double x2, double y2){
        
        svg.append("<line");
        svg.append(" x1='").append(x1).append("'"); 
        svg.append(" y1='").append(y1).append("'"); 
        svg.append(" x2='").append(x2).append("'"); 
        svg.append(" y2='").append(y2).append("'");
        svg.append(" style='stroke:black; stroke-width: 0.75; marker-start: url(#beginArrow").append(uniqID).append("); marker-end: url(#endArrow").append(uniqID).append(");'");
        svg.append("/>");
        
        svg.append("<line");
        svg.append(" x1='").append(x1+9).append("'"); 
        svg.append(" y1='").append(y1).append("'"); 
        svg.append(" x2='").append(x1-10).append("'"); 
        svg.append(" y2='").append(y1).append("'");
        svg.append(" style='stroke:grey;'");
        svg.append("/>");

        svg.append("<line");
        svg.append(" x1='").append(x2+9).append("'"); 
        svg.append(" y1='").append(y2).append("'"); 
        svg.append(" x2='").append(x2-10).append("'"); 
        svg.append(" y2='").append(y2).append("'");
        svg.append(" style='stroke:grey;'");
        svg.append("/>");
        
    }
    

//    public void arrow(double x1, double y1, double x2, double y2, String custom){
//        
//        svg.append("<line");
//        svg.append(" x1='").append(x1).append("'"); 
//        svg.append(" y1='").append(y1).append("'"); 
//        svg.append(" x2='").append(x2).append("'"); 
//        svg.append(" y2='").append(y2).append("'");
//        svg.append(" style='stroke:black; marker-start: url(#beginArrow); marker-end: url(#endArrow);'");
//        svg.append(" ").append(custom);
//        svg.append("/>");
//    }

    public String getSvg()
    {
        
        svg.append("</SVG>");
        return svg.toString();
    }
    
    
}