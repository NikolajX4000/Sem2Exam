
package presentationLayer;

import functionLayer.Order;

public class DrawCarportAngleTop {
    
    String std = "fill:white;stroke:black;stroke-width:1;";
    String thin = "fill:white;stroke:black;stroke-width:0.5;";
    
    double xOffset = 30, yOffset = 15;
    
    
    double width, height, shedWidth, shedHeight;
    double bargeHeight, bargeWidth;
    double coverHeight, coverWidth;
    double remWidth, remHeight;
    double lathWidth, lathHeight;
    double spaerWidth, spaerHeight;
    double beam = 10;
    double newBeamAfter = 50;
    
    double shedXStartAt, shedXEndAt, shedYStartAt, shedYEndAt;
    
    boolean isFlat, hasShed;

    SVG svg;
    
    String tmp = "";

    public DrawCarportAngleTop(Order o) {
        
        width = o.getLength();
        height = o.getWidth();

        if(o.hasShed()){
            shedWidth = o.getShedLength();
            shedHeight = o.getShedWidth();
        }else{
            shedWidth = 0;
            shedHeight = 0;
        }
        
        
        isFlat = o.isFlat();
        hasShed = o.hasShed();

        if (shedHeight > height - yOffset*2) {
            shedHeight = height - yOffset*2;
        }

        if (shedWidth > width - xOffset*2) {
            shedWidth = width - xOffset*2;
        }
        
        spaerWidth = 4.5;
        spaerHeight = height - 2;
        
        bargeWidth = 5;
        bargeHeight = height;
        
        lathWidth = width - bargeWidth * 2;
        lathHeight = 3.8;
        
        coverHeight = 5;
        coverWidth = width - bargeWidth * 2;

        remWidth = width - 30 - 25;
        remHeight = 4.5;

        beam = 10;
        
        shedXStartAt = width - shedWidth - xOffset;
        shedXEndAt = width - xOffset;
        shedYStartAt = yOffset;
        shedYEndAt = shedHeight + yOffset;
        
        svg = new SVG(width, height);
    }

    @Override
    public String toString() {
        
        if(hasShed) drawShed();
        drawSpaer();
        drawBarge();
        drawRem();
        drawSkirts();
        drawCover();
        drawLath();
        if(hasShed) drawShedBeams();
        drawXBeams();
        drawYBeams();
        
        return svg.toString();
        //return tmp;
    }

    private void drawShed() {
        
        svg.rct(shedXStartAt - 2 , yOffset - 2   , shedHeight + 4, shedWidth + 2 , "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        svg.rct(shedXStartAt - 1 , yOffset - 1   , shedHeight + 2, shedWidth + 1 , "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        svg.rct(shedXStartAt     , yOffset       , shedHeight    , shedWidth     , "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        
        tmp = "shedStartAt: " + shedXStartAt + " shedEndAt: " + shedXEndAt + " width: " + shedWidth + " calcWidth: " + (Math.abs(shedXStartAt-shedXEndAt));
        
    }

    private void drawSpaer() {
        
        double spaerDistance = width - xOffset * 2 - spaerWidth;
        double spaerAmount = (int) (spaerDistance / 89);
        double spaerGutter = spaerDistance / (spaerAmount-1);
        
//        for (double i = 0; i <= spaerGutter * spaerAmount; i += spaerGutter) {
//            
//            svg.rct(i + xOffset, 1, spaerHeight, spaerWidth, "stroke:grey; stroke-width: 0.75; fill: white;");
//        }

        for (int i = 0; i < spaerAmount; i++) {
            svg.rct(i*spaerGutter + xOffset, 1, spaerHeight, spaerWidth, "stroke:grey; stroke-width: 0.75; fill: white;");
        }
    }

    private void drawBarge() {
        
        svg.rct(0                   , 0, bargeHeight, bargeWidth, std);
        svg.rct(width - bargeWidth  , 0, bargeHeight, bargeWidth, std);
        
        svg.line(0                  , height / 2, bargeWidth, height / 2, std);
        svg.line(width - bargeWidth , height / 2, width     , height / 2, std);
    }

    private void drawRem() {
        
        svg.rct(xOffset - 5, yOffset                        , remHeight, remWidth, std);
        svg.rct(xOffset - 5, height - remHeight - yOffset   , remHeight, remWidth, std);
    }

    private void drawSkirts() {
        //left skirt
        svg.line(xOffset - 1, coverHeight       , xOffset - 1, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        svg.line(xOffset - 2, coverHeight + 5   , xOffset - 2, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        
        //right skirt
        svg.line(width - xOffset + 1, coverHeight       , width - xOffset + 1, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        svg.line(width - xOffset + 2, coverHeight + 5   , width - xOffset + 2, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
    }

    private void drawCover() {
        
        svg.rct(bargeWidth, 0                   , coverHeight, coverWidth, std);
        svg.rct(bargeWidth, height - coverHeight, coverHeight, coverWidth, std);
    }

    private void drawLath() {
        
        double lathHalfAmount = (int) (height / 2 / 35);
        double lathGutter = ((height / 2 - lathHeight * 2)) / lathHalfAmount;
        
        //middle lath
        svg.rct(bargeWidth, height / 2 - lathHeight / 2 / 1.5, lathHeight / 1.5, lathWidth, thin);

        for (double i = lathHeight * 2; i < height / 2; i += lathGutter) {
            svg.rct(bargeWidth, (height / 2) + i                , lathHeight, lathWidth, thin);
            svg.rct(bargeWidth, (height / 2) - i - lathHeight   , lathHeight, lathWidth, thin);
        }
    }

    private void drawShedBeams() {
        
        //x beams
        double beamAmount = 2 + (int)(shedWidth/newBeamAfter);
        double beamGutter = (shedWidth-beam) / (beamAmount - 1); // -1 because we start at x = 0 // -beam because you need to - the width of the element you wanna loop
        for (int i = 0; i < beamAmount; i++) {
            svg.rct(shedXStartAt + (i*beamGutter), shedYStartAt             , beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Top shed
            svg.rct(shedXStartAt + (i*beamGutter), shedYEndAt - beam        , beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Bot shed
            svg.rct(shedXStartAt + (i*beamGutter), height - yOffset - beam  , beam, beam, "fill:none;stroke:red;stroke-width:2.5;"); // Bot CP
        }
        
        //y beams
        beamAmount = 2 + (int)(shedHeight/newBeamAfter);
        beamGutter = (shedHeight-beam) / (beamAmount - 1);
        for (int i = 0; i < beamAmount; i++) {
            svg.rct(shedXStartAt        , shedYStartAt  + (i*beamGutter), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Left shed
            svg.rct(shedXEndAt - beam   , shedYStartAt  + (i*beamGutter), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Right shed
            svg.rct(xOffset             , shedYStartAt  + (i*beamGutter), beam, beam, "fill:none;stroke:red;stroke-width:2.5;"); // LEFT CP IDK IF USEFUL
        }
        
    }

    private void drawXBeams() {
        
        double xWidth = (hasShed) ? width-2*xOffset-shedWidth : width-2*xOffset - beam;
        
        double beamAmount = 2 + (int)(xWidth/newBeamAfter);
        double beamGutter = (xWidth) / (beamAmount-1);
        
        for (int i = 0; i < beamAmount; i++) {
            svg.rct(xOffset + (i*beamGutter), yOffset                   , beam, beam, "fill:green;stroke:green;stroke-width:2.5;"); // Top
            svg.rct(xOffset + (i*beamGutter), height - yOffset - beam   , beam, beam, "fill:green;stroke:green;stroke-width:2.5;"); // Bot
        }
    }

    private void drawYBeams() {
        
        double yHeight = (hasShed) ? height-2*yOffset-shedHeight : height-2*yOffset - beam;
       
        double beamAmount = 2 + (int)(yHeight/newBeamAfter);
        double beamGutter = (yHeight) / (beamAmount-1);
        
        for (int i = 0; i < beamAmount; i++) {
            svg.rct(xOffset                 , height - yOffset - beam - (i*beamGutter), beam, beam, "fill:none;stroke:blue;stroke-width:2.5;"); // LEFT
            svg.rct(width - xOffset - beam  , height - yOffset - beam - (i*beamGutter), beam, beam, "fill:none;stroke:blue;stroke-width:2.5;"); // RIGHT
        }
    }
    
    
    
    
    
    
    

}
