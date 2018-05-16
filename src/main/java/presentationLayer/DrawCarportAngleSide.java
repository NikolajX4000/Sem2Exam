/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentationLayer;

import functionLayer.Order;
import java.util.Random;

public class DrawCarportAngleSide {

    String std = "fill:none;stroke:black;stroke-width:1;";
    String thin = "fill:none;stroke:black;stroke-width:0.5;";
    
    double xOffset = 30;
    //double x, y, h, w;
    double width, shedWidth, shedHeight, height = 210;
    
    double remHeight = 20;
    double sternHeight = 15;
    double roofBottom = 10;
    double beam = 10;
    double newBeamAfter = 400;
    double brickWidth = 10;
    double topBrick = 7.5;
    double barge = 10;
    
    double angle;
    double brickHeight;
    double topBrickOffset, brickOffset, roofBottomOffset, sternOffset, remOffset, beamOffset;
            
    boolean isFlat, hasShed;

    SVG svg;
    
    String tmp = "";

    public DrawCarportAngleSide(Order o)
    {
        isFlat = o.isFlat();
        hasShed = o.hasShed();
        
        width = o.getLength();
        shedWidth = o.getShedLength();
        angle = o.getAngle();

        if (shedWidth > width - xOffset*2) {
            shedWidth = width - xOffset*2;
        }

        //tri
        double angleA = angle;
        double angleC = 90;
        double angleB = 180 - angleC - angleA;
        double sideb = o.getWidth() / 2;
        double sidea = (Math.sin(Math.toRadians(angleA)) * sideb) / Math.sin(Math.toRadians(angleB));
        brickHeight = sidea;
        
        topBrickOffset = 2.5;
        brickOffset = topBrickOffset + topBrick;
        roofBottomOffset = brickOffset + brickHeight;
        sternOffset = roofBottomOffset + roofBottom;
        remOffset = sternOffset + sternHeight;
        beamOffset = remOffset + remHeight;

        svg = new SVG(width, height + beamOffset);
        
    }

    @Override
    public String toString()
    {
        drawRem();
        drawStern();
        drawRoofBottom();
        drawBricks();
        drawBeams();
        if(hasShed)drawShedBeams();
        if(hasShed)drawShed();
        drawBarge();
        
        return svg.toString();
    }

    private void drawRem()
    {
        svg.rct(xOffset, remOffset, remHeight, width - (xOffset * 2), "fill:white;stroke:black;");
    }

    private void drawStern()
    {
        svg.rct(barge / 2, sternOffset, sternHeight, width - barge, "fill:white;stroke:black;");
    }

    private void drawRoofBottom()
    {
        svg.rct(barge / 2, roofBottomOffset, roofBottom, width - barge, "fill:white;stroke:black;");
    }

    private void drawBricks()
    {
        for (double i = barge / 2; i < width - brickWidth; i += brickWidth) {
            svg.rct(i, brickOffset, brickHeight, brickWidth, "fill:white;stroke:black;stroke-width:0.5");
        }
        
        //top brick
        svg.rct(barge / 2, topBrickOffset, topBrick, width - barge, "fill:white;stroke:black;;stroke-width:0.5");
    }

    private void drawShed()
    {
        double plankWidth = 8;
        double shedStartAt = width - shedWidth - xOffset;
        double x, y, h;
        
        double plankAmount = (int)((shedWidth)/(plankWidth*1.4));
        double plankGutter = (shedWidth-plankWidth) / (plankAmount-1);

        //bottom layer
        for (int i = 0; i < plankAmount; i++) {
            x = shedStartAt + (i*plankGutter);
            y = beamOffset - remHeight + 1;
            h = height + remHeight - 1;
            
            tmp += i + ": " + x + "  ";
            
            svg.rct(x, y, h, plankWidth, "fill:white;stroke:black;stroke-width:0.5;"); // first layer
        }
        
        plankAmount--;
        
        // to give some texture to the planks :p
        //Random rand = new Random();
        
        //top Layer
        for (int i = 0; i < plankAmount; i++) {
            x = shedStartAt + (i*plankGutter) + (plankGutter/2);
            y = beamOffset - remHeight + 1;
            h = height + remHeight - 1;
            
            tmp += i + ": " + x + "  ";
            
            svg.rct(x, y, h, plankWidth, "fill:white;stroke:black;stroke-width:0.5;"); // first layer
            
//            svg.rct(x, y, h, plankWidth, "fill:white;stroke:black;stroke-width:0.25;", "stroke-dasharray='3%, 0."+ rand.nextInt(9) +"%'"); // first layer
//            svg.rct(x+0.25, y, h, plankWidth-0.5, "fill:white;stroke:black;stroke-width:0.25;", "stroke-dasharray='4%, 0."+ rand.nextInt(9) +"%'"); // first layer
//            svg.rct(x+0.5, y, h, plankWidth-1, "fill:white;stroke:black;stroke-width:0.25;", "stroke-dasharray='5%, 0."+ rand.nextInt(9) +"%'"); // first layer
        }
    }

    private void drawShedBeams()
    {
        double x;
        //left
        x = width - xOffset - shedWidth - beam/4;
        svg.rct(x, beamOffset + 1, height - 1, beam, "fill:white;stroke:black;");
        
        //right
        x = width - xOffset - beam + beam/4;
        svg.rct(x, remOffset + 1, height + remHeight - 1, beam, "fill:white;stroke:black;");
    }

    private void drawBeams()
    {
        if (width - shedWidth > 200){
            double xWidth = (hasShed) ? width-2*xOffset-shedWidth : width-2*xOffset - beam*3;
        
            double beamAmount = 2 + (int)(xWidth/newBeamAfter);
            double beamGutter = (xWidth) / (beamAmount-1);

            for (int i = 0; i < beamAmount; i++) {
                svg.rct(xOffset + (i*beamGutter) + beam, beamOffset, height, beam, "fill:white;stroke:black;");
            }
        }
    }

    private void drawBarge()
    {
        svg.rct(0, 0, brickHeight + brickOffset, barge, "fill:white;stroke:black;stroke-width:0.75");
        svg.rct(width - barge, 0, brickHeight + brickOffset, barge, "fill:white;stroke:black;stroke-width:0.75");
    }

    
    
    
    
    
    
}