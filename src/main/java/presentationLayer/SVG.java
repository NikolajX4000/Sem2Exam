package presentationLayer;

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
    
    public void text(double x, double y, String text){
        
        StringBuilder s = new StringBuilder();
        s.append("<text");
        s.append(" x='").append(x).append("'");
        s.append(" y='").append(y).append("'");
        s.append(" fill='black'");
        s.append(" text-anchor='middle'");
        s.append(">").append(text).append("</text>");
        svg.append(s);
    }
    
    public void textRotated(double x, double y, String text){
        
        StringBuilder s = new StringBuilder();
        s.append("<text");
        s.append(" x='").append(x).append("'");
        s.append(" y='").append(y).append("'");
        s.append(" fill='black'");
        s.append(" text-anchor='middle'");
        s.append(" transform='rotate(-90 ").append(x).append(" ").append(y).append(")'");
        s.append(">").append(text).append("</text>");
        svg.append(s);
    }
    
    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void arrowX(double x1, double y1, double x2, double y2){
        
        StringBuilder s = new StringBuilder();
        s.append("<line");
        s.append(" x1='").append(x1).append("'"); 
        s.append(" y1='").append(y1).append("'"); 
        s.append(" x2='").append(x2).append("'"); 
        s.append(" y2='").append(y2).append("'");
        s.append(" style='stroke:black; stroke-width: 0.75; marker-start: url(#beginArrow").append(uniqID).append("); marker-end: url(#endArrow").append(uniqID).append(");'");
        s.append("/>");
        
        s.append("<line");
        s.append(" x1='").append(x1).append("'"); 
        s.append(" y1='").append(y1+9).append("'"); 
        s.append(" x2='").append(x1).append("'"); 
        s.append(" y2='").append(y1-10).append("'");
        s.append(" style='stroke:grey;'");
        s.append("/>");

        s.append("<line");
        s.append(" x1='").append(x2).append("'"); 
        s.append(" y1='").append(y2+9).append("'"); 
        s.append(" x2='").append(x2).append("'"); 
        s.append(" y2='").append(y2-10).append("'");
        s.append(" style='stroke:grey;'");
        s.append("/>");
        
        svg.append(s);
    }
    
    public void arrowY(double x1, double y1, double x2, double y2){
        
        StringBuilder s = new StringBuilder();
        s.append("<line");
        s.append(" x1='").append(x1).append("'"); 
        s.append(" y1='").append(y1).append("'"); 
        s.append(" x2='").append(x2).append("'"); 
        s.append(" y2='").append(y2).append("'");
        s.append(" style='stroke:black; stroke-width: 0.75; marker-start: url(#beginArrow").append(uniqID).append("); marker-end: url(#endArrow").append(uniqID).append(");'");
        s.append("/>");
        
        s.append("<line");
        s.append(" x1='").append(x1+9).append("'"); 
        s.append(" y1='").append(y1).append("'"); 
        s.append(" x2='").append(x1-10).append("'"); 
        s.append(" y2='").append(y1).append("'");
        s.append(" style='stroke:grey;'");
        s.append("/>");

        s.append("<line");
        s.append(" x1='").append(x2+9).append("'"); 
        s.append(" y1='").append(y2).append("'"); 
        s.append(" x2='").append(x2-10).append("'"); 
        s.append(" y2='").append(y2).append("'");
        s.append(" style='stroke:grey;'");
        s.append("/>");
        
        svg.append(s);
    }
    
    /**
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param custom 
     */
//    public void arrow(double x1, double y1, double x2, double y2, String custom){
//        
//        StringBuilder s = new StringBuilder();
//        s.append("<line");
//        s.append(" x1='").append(x1).append("'"); 
//        s.append(" y1='").append(y1).append("'"); 
//        s.append(" x2='").append(x2).append("'"); 
//        s.append(" y2='").append(y2).append("'");
//        s.append(" style='stroke:black; marker-start: url(#beginArrow); marker-end: url(#endArrow);'");
//        s.append(" ").append(custom);
//        s.append("/>");
//        
//        
//        
//        
//        
//        svg.append(s);
//    }

    @Override
    public String toString()
    {
        
        StringBuilder s = new StringBuilder();
        s.append("<SVG");
        s.append(svgClass);
        s.append(" xmlns='http://www.w3.org/2000/svg'");
        s.append(" version='1.1' style='width:100%;padding:5px;'");
        s.append(" viewBox='-50 -50 ").append(svgWidth+100).append(" ").append(svgHeight+100).append("'");
        s.append(">");
        
        s.append("<defs>");
            s.append("<marker id=\"beginArrow").append(uniqID).append("\" markerWidth=\"9\" markerHeight=\"9\" refX=\"0\" refY=\"4\" orient=\"auto\">");
            s.append("<path d=\"M0,4 L8,0 L8,8 L0,4\" style=\"fill: #000000;\" />");
            s.append("</marker>");

            s.append("<marker id=\"endArrow").append(uniqID).append("\" markerWidth=\"9\" markerHeight=\"9\" refX=\"8\" refY=\"4\" orient=\"auto\">");
            s.append("<path d=\"M0,0 L8,4 L0,8 L0,0\" style=\"fill: #000000;\" />");
            s.append("</marker>");
        s.append("</defs>");
        
        s.append(svg);
        
        s.append("</SVG>");
        return s.toString();
    }
    
    
}