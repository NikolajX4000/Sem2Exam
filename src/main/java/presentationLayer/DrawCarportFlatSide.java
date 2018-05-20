
package presentationLayer;

import functionLayer.Order;
import java.util.Random;

public class DrawCarportFlatSide {

    String std = "fill:none;stroke:black;stroke-width:1;";
    String thin = "fill:none;stroke:black;stroke-width:0.5;";
    
    double xOffset = 30;
    double difHeight = 10;
    double width, height, shedWidth, shedHeight;
    double remHeight = 20;
    double beam = 10;
    double beamOffset = remHeight * 2;
    double newBeamAfter = 400;
    
    boolean isFlat, hasShed;

    SVG svg;
    
    String tmp = "";

    public DrawCarportFlatSide(Order o) {

        isFlat = o.isFlat();
        hasShed = o.hasShed();
        
        height = 210;
        width = o.getLength();
        shedWidth = o.getShedLength();
        
        if (shedWidth > width - xOffset*2) {
            shedWidth = width - xOffset*2;
        }
        
        svg = new SVG(width, height + beamOffset);
    }

    public String getDrawing() {
        
        drawBeams();
        if(hasShed)drawShedBeams();
        drawLines();
        if(hasShed)drawShed();
        drawHeightWidthArrow();
        return svg.toString();
        //return tmp;
    }

    private void drawBeams() {
        
        if (width - shedWidth > 200){
            double xWidth = (hasShed) ? width-2*xOffset-shedWidth : width-2*xOffset - beam;
        
            double beamAmount = 2 + (int)(xWidth/newBeamAfter);
            double beamGutter = (xWidth) / (beamAmount-1);

            for (int i = 0; i < beamAmount; i++) {
                double x = xOffset + (i*beamGutter);
                svg.rct(x, beamOffset + (x / width * difHeight), height - (x / width * difHeight), beam, "fill:white;stroke:black;");
            }
        }
    }

    private void drawLines() {
        //top line
        svg.line(0, 0, width, difHeight, "stroke:black;stroke-width:1");

        //mid line
        svg.line(0, remHeight, width, difHeight + remHeight, "stroke:black;stroke-width:1");

        // bot line
        svg.line(1, remHeight * 2, width - 1, difHeight + remHeight * 2, "stroke:black;stroke-width:1");

        //left
        svg.line(0, 0, 0, remHeight, "stroke:black;stroke-width:1");
        svg.line(1, remHeight, 1, remHeight * 2, "stroke:black;stroke-width:1");

        //right
        svg.line(width, difHeight, width, difHeight + remHeight, "stroke:black;stroke-width:1");
        svg.line(width - 1, difHeight + remHeight, width - 1, difHeight + remHeight * 2, "stroke:black;stroke-width:1");
    }

    private void drawShed() {
        double plankWidth = 8;
        double shedStartAt = width - shedWidth - xOffset;
        double x, y, h;
        
        double plankAmount = (int)((shedWidth)/(plankWidth*1.4));
        double plankGutter = (shedWidth-plankWidth) / (plankAmount-1);

        //bottom layer
        for (int i = 0; i < plankAmount; i++) {
            x = shedStartAt + (i*plankGutter);
            y = beamOffset - remHeight + 1 + (x / width * difHeight);
            h = height + remHeight - 1 - (x / width * difHeight);
            
            tmp += i + ": " + x + "  ";
            
            svg.rct(x, y, h, plankWidth, "fill:white;stroke:black;stroke-width:0.5;"); // first layer
        }
        
        plankAmount--;
        
        // to give some texture to the planks :p
        //Random rand = new Random();
        
        //top Layer
        for (int i = 0; i < plankAmount; i++) {
            x = shedStartAt + (i*plankGutter) + (plankGutter/2);
            y = beamOffset - remHeight + 1 + (x / width * difHeight);
            h = height + remHeight - 1 - (x / width * difHeight);
            
            tmp += i + ": " + x + "  ";
            
            svg.rct(x, y, h, plankWidth, "fill:white;stroke:black;stroke-width:0.5;"); // first layer
            
//            svg.rct(x, y, h, plankWidth, "fill:white;stroke:black;stroke-width:0.25;", "stroke-dasharray='3%, 0."+ rand.nextInt(9) +"%'"); // first layer
//            svg.rct(x+0.25, y, h, plankWidth-0.5, "fill:white;stroke:black;stroke-width:0.25;", "stroke-dasharray='4%, 0."+ rand.nextInt(9) +"%'"); // first layer
//            svg.rct(x+0.5, y, h, plankWidth-1, "fill:white;stroke:black;stroke-width:0.25;", "stroke-dasharray='5%, 0."+ rand.nextInt(9) +"%'"); // first layer
        }

    }

    private void drawShedBeams() {
        double x;
        //left
        x = width - xOffset - shedWidth - beam/4;
        svg.rct(x, beamOffset + (x / width * difHeight) + 1, height - (x / width * difHeight) - 1, beam, "fill:white;stroke:black;");
        
        //right
        x = width - xOffset - beam + beam/4;
        svg.rct(x, beamOffset + (x / width * difHeight) + 1, height - (x / width * difHeight) - 1, beam, "fill:white;stroke:black;");
        
    }
    
    private void drawHeightWidthArrow() {
        
        //cp
        svg.arrowX(0, -20, width, -20);
        svg.text(width/2, -25, (int)(width) + " cm");
        
        //shed
        if(hasShed){
            svg.arrowX(width-shedWidth-xOffset, beamOffset + height + 25, width-xOffset, beamOffset + height + 25);
            svg.text(width - xOffset - (shedWidth/2), beamOffset + height + 20, (int)(shedWidth) + " cm");
        }
        
        //beam height
        svg.arrowY(-10, beamOffset, -10, beamOffset + height);
        svg.textRotated(-15, beamOffset + height/2, (int)(height) + " cm");
        
        //cp full height
        svg.arrowY(-30, 0, -30, beamOffset + height);
        svg.textRotated(-35, (beamOffset + height)/2, (int)(beamOffset + height) + " cm");
    }
    
}
    
