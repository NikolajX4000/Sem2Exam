
package logicLayer;

public class DrawCarportFlatTop {
    
    String std = "fill:none;stroke:black;stroke-width:1;";
    String thin = "fill:none;stroke:black;stroke-width:0.5;";
    
    double xOffset = 30, yOffset = 15;
    
    double width, height, shedWidth, shedHeight;
    double spaerWidth = 5;
    double spaerDistance, spaerAmount, spaerGutter;
    double remHeight = 8;
    double beam = 10;
    double newBeamAfter = 400;
    
    boolean isFlat, hasShed;

    SVG svg;
    
    String tmp = "";

    public DrawCarportFlatTop(Order o)
    {
        isFlat = o.isFlat();
        hasShed = o.hasShed();
        
        width = o.getLength();
        height = o.getWidth();

        shedWidth = o.getShedLength();
        shedHeight = o.getShedWidth();
        
        //spaerDistance = width - spaerWidth;
        spaerAmount = 1 + Math.ceil(width / (55+spaerWidth));
        spaerGutter = (width - spaerWidth) / (spaerAmount-1);
        
        svg = new SVG(width, height);

        if (shedHeight > height - yOffset*2) {
            shedHeight = height - yOffset*2;
        }

        if (shedWidth > width - xOffset*2) {
            shedWidth = width - xOffset*2;
        }
        
    }
    
    
    public String getDrawing() {
        
        
        drawRem();
        drawSpaer();
        drawCarport();
        drawCross();
        if(hasShed)drawShed();
        if(hasShed)drawShedBeams();
        drawXBeams();
        drawHeightWidthArrow();
        
        return svg.getSvg();
        //return tmp;
    }

    private void drawCarport()
    {
        svg.rct(0, 0, height, width, std);
    }

    private void drawRem()
    {
        svg.rct(0, yOffset                  , remHeight, width, std);
        svg.rct(0, height-yOffset-remHeight , remHeight, width, std);
    }
    
    private void drawSpaer() {
        

        for (int i = 0; i < spaerAmount; i++) {
            svg.rct(i*spaerGutter, 1, height-1, spaerWidth, "stroke:grey; stroke-width: 0.75; fill: white;");
            
            //LINES AND ARROWS
            if(i+1<spaerAmount){
                svg.arrowX(i*spaerGutter + spaerWidth, -20, i*spaerGutter + spaerGutter, -20);
                svg.text(i*spaerGutter + (spaerGutter/2) + (spaerWidth/2), -25, (int)(spaerGutter-spaerWidth) + "cm");
            }
        }
        
        
    }

    private void drawShed()
    {
        // shed skeleton
        double shedSkeletonStartX = width - shedWidth - xOffset - 1;
        double shedSkeletonStartY = yOffset - 1;
        double shedSkeletonHeight = shedHeight + 2;
        double shedSkeletonWidth = shedWidth + 2;
        String shedSkeletonStyle = "fill:none;stroke:black;stroke-width:2;";
        String shedSkeletonCustom = "stroke-dasharray='5, 5'";

        svg.rct(shedSkeletonStartX, shedSkeletonStartY, shedSkeletonHeight, shedSkeletonWidth, shedSkeletonStyle, shedSkeletonCustom);
    }

    private void drawCross()
    {
        
        String crossStyle = "stroke:black;stroke-width:1";
        String crossCustom = "stroke-dasharray='3, 3'";

        double crossXstart = spaerWidth + xOffset + spaerWidth; //spaerGutter
        double crossXend = (hasShed) ? spaerWidth + width - shedWidth - xOffset : width - xOffset - spaerWidth;

        double crossTopYstart = remHeight + yOffset;
        double crossBotYstart = height - remHeight - yOffset;

        double crossTopYend = crossBotYstart;
        double crossBotYend = crossTopYstart;
        
        if(width-shedWidth>200){
            svg.line(crossXstart, crossTopYstart, crossXend, crossTopYend, crossStyle, crossCustom);
            svg.line(crossXstart, crossTopYstart + 5, crossXend, crossTopYend + 5, crossStyle, crossCustom);

            svg.line(crossXstart, crossBotYstart, crossXend, crossBotYend, crossStyle, crossCustom);
            svg.line(crossXstart, crossBotYstart - 5, crossXend, crossBotYend - 5, crossStyle, crossCustom);
        }
    }
    
    
    private void drawShedBeams() {
        double shedXStartAt = width - shedWidth - xOffset;
        double shedXEndAt = width - xOffset;
        double shedYStartAt = yOffset;
        double shedYEndAt = shedHeight + yOffset;
        
        //x beams
        double beamAmount = 2 + (int)(shedWidth/newBeamAfter);
        double beamGutter = (shedWidth-beam) / (beamAmount - 1); // -1 because we start at x = 0 // -beam because you need to - the width of the element you wanna loop
        for (int i = 0; i < beamAmount; i++) {
            svg.rct(shedXStartAt + (i*beamGutter), shedYStartAt             , beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Top shed
            svg.rct(shedXStartAt + (i*beamGutter), shedYEndAt - beam        , beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Bot shed
            svg.rct(shedXStartAt + (i*beamGutter), height - yOffset - beam  , beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Bot CP
        }
        
        //y beams
        beamAmount = 2 + (int)(shedHeight/newBeamAfter);
        beamGutter = (shedHeight-beam) / (beamAmount - 1);
        for (int i = 0; i < beamAmount; i++) {
            svg.rct(shedXStartAt        , shedYStartAt  + (i*beamGutter), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Left shed
            svg.rct(shedXEndAt - beam   , shedYStartAt  + (i*beamGutter), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Right shed
            //svg.rct(xOffset             , shedYStartAt  + (i*beamGutter), beam, beam, "fill:none;stroke:red;stroke-width:2.5;"); // LEFT CP IDK IF USEFUL
        }
    }
    
    private void drawXBeams() {
        
        if (width - shedWidth > 200){
            double xWidth = (hasShed) ? width-2*xOffset-shedWidth : width-2*xOffset - beam;
        
            double beamAmount = 2 + (int)(xWidth/newBeamAfter);
            double beamGutter = (xWidth) / (beamAmount-1);

            for (int i = 0; i < beamAmount; i++) {
                svg.rct(xOffset + (i*beamGutter), yOffset                   , beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Top
                svg.rct(xOffset + (i*beamGutter), height - yOffset - beam   , beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // Bot
            }
        }
    }
    
    private void drawHeightWidthArrow() {
        svg.arrowX(0, height + 25, width, height + 25);
        svg.text(width/2, height + 20, (int)(width) + " cm");
        
        svg.arrowY(-20, 0, -20, height);
        svg.textRotated(-25, height/2, (int)(height) + " cm");
    }
}
