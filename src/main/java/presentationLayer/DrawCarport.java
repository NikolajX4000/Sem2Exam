package presentationLayer;

public class DrawCarport {

    private DrawCarport() {
    }

    public static String flatSide(int pLength, int pWidth, int pShedLength, boolean hasShed) {

        double x, y, h, w;

        double width = pLength;
        double height = pWidth;
        double shedWidth = pShedLength;
        
        if (shedWidth > width - 60) {
            shedWidth = width - 60;
        }

        double difHeight = 10;

        double spaerHeight = 10;
        double remHeight = 20;
        double beam = 10;
        double beamOffset = remHeight * 2;

        SVG svg = new SVG(width + 10, height + beamOffset, "test");

        ///////////////////////////////////////////// BEAMS //////////////////////////////////////
        //always there: // PLACEMENTWIDTH/width*difHeight  placement/width gives % of width taken. difHeight = difHeight from top->bot times that with % width taken to see how much beam must be moved down
        
        svg.rct(100 + beam / 2, beamOffset + (100 / width * difHeight), height - (100 / width * difHeight), beam, "fill:white;stroke:black;");

        if (hasShed) {

            //left beam
            svg.rct(x = (width - shedWidth - 30), y = beamOffset + (x / width * difHeight), h = height - (x / width * difHeight), beam, "fill:white;stroke:black;");

            //right beam
            svg.rct(x = (width - beam - 30), y = beamOffset + (x / width * difHeight), h = height - (x / width * difHeight), beam, "fill:white;stroke:black;");

            if (width - shedWidth > 200) {
                svg.rct(x = (width - shedWidth - 30 - 100 + beam / 2), y = beamOffset + (x / width * difHeight), h = height - (x / width * difHeight), beam, "fill:white;stroke:black;");
            }
        } else {

            //right beam
            svg.rct(x = (width - 100 - beam / 2), y = beamOffset + (x / width * difHeight), h = height - (x / width * difHeight), beam, "fill:white;stroke:black;");

        }

        // middle beams
        double distance = (hasShed) ? width - 100 * 2 - shedWidth - 30 : width - (100 * 2) - beam;
        double middleBeams = (int) (distance / 400);
        double middleBeamsOffset = distance / (middleBeams);

        if (middleBeamsOffset > 0) {
            for (double i = middleBeamsOffset; i <= middleBeamsOffset * middleBeams; i += middleBeamsOffset) {
                svg.rct(x = (100 + beam / 2 + i), y = beamOffset + (x / width * difHeight), h = height - (x / width * difHeight), beam, "fill:white;stroke:black;");
            }
        }

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

        if (hasShed) {

            //loop :O træbrædder
            for (double i = 2.5; i < shedWidth; i += 15) {
                svg.rct(x = (width - shedWidth - 30 + i), y = beamOffset - remHeight + 1 + (x / width * difHeight), h = height + remHeight - 1 - (x / width * difHeight), beam, "fill:white;stroke:black;");
            }

            for (double i = 10; i < shedWidth - 15; i += 15) {
                svg.rct(x = (width - shedWidth - 30 + i), y = beamOffset - remHeight + 1 + (x / width * difHeight), h = height + remHeight - 1 - (x / width * difHeight), beam, "fill:white;stroke:black;stroke-width:0.6");
            }

        }
        return svg.toString();
    }

    public static String flatTop(int pLength, int pWidth, int pShedLength, int pShedWidth, boolean hasShed) {

        double width = pLength;
        double height = pWidth;

        double shedWidth = pShedLength;
        double shedHeight = pShedWidth;

        if (shedHeight > height - 70) {
            shedHeight = height - 70;
        }

        if (shedWidth > width - 60) {
            shedWidth = width - 60;
        }

        double spaerWidth = 4.5;
        double remWidth = spaerWidth * 1;
        double beam = 10;

        double spaerAmount = (int) (width / 55);
        double spaerGutter = ((width + spaerWidth) / spaerAmount);

        SVG svg = new SVG((width + spaerWidth * 2), height, "test");

        // rem top
        svg.rct(spaerWidth, 35, remWidth, width, "stroke:black; stroke-width: 1.5; fill: none;");

        // rem bot
        svg.rct(spaerWidth, height - remWidth - 35, remWidth, width, "stroke:black; stroke-width: 1.5; fill: none;");

        // spaer
        for (double i = 0; i <= width + spaerGutter; i += spaerGutter) {
            svg.rct(i, 0, height, spaerWidth, "stroke:black; stroke-width: 0.75; fill: white;");
        }

        //carport skeleton
        svg.rct(spaerWidth, 0, height, width, "fill:none;stroke:black;stroke-width:1.5;");

        // shed skeleton
        double shedSkeletonStartX = spaerWidth + width - shedWidth - 30 - 1;
        double shedSkeletonStartY = height - shedHeight - 35 - 1;
        double shedSkeletonHeight = shedHeight + 2;
        double shedSkeletonWidth = shedWidth + 2;
        String shedSkeletonStyle = "fill:none;stroke:black;stroke-width:2;";
        String shedSkeletonCustom = "stroke-dasharray='5, 5'";

        if (hasShed) {
            svg.rct(shedSkeletonStartX, shedSkeletonStartY, shedSkeletonHeight, shedSkeletonWidth, shedSkeletonStyle, shedSkeletonCustom);
        }

        // cross
        String crossStyle = "stroke:black;stroke-width:1";
        String crossCustom = "stroke-dasharray='3, 3'";

        double crossXstart = spaerWidth + spaerGutter;
        double crossXend = (hasShed) ? spaerWidth + width - shedWidth - 30 : width - spaerGutter + spaerWidth;

        double crossTopYstart = remWidth + 35;
        double crossBotYstart = height - remWidth - 35;

        double crossTopYend = crossBotYstart;
        double crossBotYend = crossTopYstart;
        
        if(width-shedWidth-100>100){
            svg.line(crossXstart, crossTopYstart, crossXend, crossTopYend, crossStyle, crossCustom);
            svg.line(crossXstart, crossTopYstart + 5, crossXend, crossTopYend + 5, crossStyle, crossCustom);

            svg.line(crossXstart, crossBotYstart, crossXend, crossBotYend, crossStyle, crossCustom);
            svg.line(crossXstart, crossBotYstart - 5, crossXend, crossBotYend - 5, crossStyle, crossCustom);
        }
        
        // beams
        //always there:
        if(width-shedWidth-100>100){
            svg.rct(spaerWidth + 100, 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            svg.rct(spaerWidth + 100, height - beam - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
        }
        
        if (!hasShed) {
            svg.rct(width - spaerWidth - 100, 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            svg.rct(width - spaerWidth - 100, height - beam - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
        }

        if (hasShed) {
            //shed top left
            svg.rct(width - spaerWidth - 30 - shedWidth + beam, height - shedHeight - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            //shed top right
            svg.rct(width - spaerWidth - 30, height - shedHeight - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            //shed bottom left
            svg.rct(width - spaerWidth - 30 - shedWidth + beam, height - beam - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            //shed bottom right
            svg.rct(width - spaerWidth - 30, height - beam - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");

            //CP top left (from shed pov)
            svg.rct(width - spaerWidth - 30 - shedWidth + beam, 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            //CP top right (from shed pov)
            svg.rct(width - spaerWidth - 30, 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");

            if (shedHeight > 400) {

                // shedMid
                svg.rct(spaerWidth + width - shedWidth - 30, (height - 35) - shedHeight + shedHeight / 2, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
                // rightMid
                svg.rct(spaerWidth + width - beam - 30, (height - 35) - shedHeight + shedHeight / 2, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");

            }
            //check if need beams top right part of CP skeleton
            if (shedWidth < width - 70) {
                double x = width + spaerWidth - shedWidth - 30 - 100;
                //out.print(x);
                svg.rct(x, 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
                svg.rct(x, height - beam - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            }
        }

        // middle beams
        //double distance = (hasShed) ? width-100*2-shedWidth-30 : width-beam-100*2;
        double distance = (hasShed) ? width - 100 * 2 - shedWidth - 30 : width - (100 * 2) - spaerWidth * 2;
        double middleBeams = (int) (distance / 400);
        double middleBeamsOffset = distance / (middleBeams);

        if (middleBeamsOffset > 0) {
            for (double i = middleBeamsOffset; i <= middleBeamsOffset * middleBeams; i += middleBeamsOffset) {
                svg.rct(spaerWidth + 100 + i, 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
                svg.rct(spaerWidth + 100 + i, height - beam - 35, beam, beam, "fill:white;stroke:black;stroke-width:2.5;");
            }
        }

        return svg.toString();
    }

    public static String angledSide(int pLength, int pWidth, int pShedLength, int pAngle, boolean hasShed) {

        double x, y, h, w;

        double width = pLength;
        double shedWidth = pShedLength;
        double angle = pAngle;
        double cpWidth = pWidth;
        double height = 210;

        if (shedWidth > width - 60) {
            shedWidth = width - 60;
        }

        double sternHeight = 15;
        double remHeight = 20;
        double beam = 10;

        double roofBottom = 10;

        //tri
        double angleA = angle;
        double angleC = 90;
        double angleB = 180 - angleC - angleA;
        double sideb = cpWidth / 2;
        double sidea = (Math.sin(Math.toRadians(angleA)) * sideb) / Math.sin(Math.toRadians(angleB));

        double brickHeight = sidea;
        double brickWidth = 10;
        double topBrick = 7.5;
        double barge = 10;

        double topBrickOffset = 2.5;
        double brickOffset = topBrickOffset + topBrick;
        double roofBottomOffset = brickOffset + brickHeight;
        double sternOffset = roofBottomOffset + roofBottom;
        double remOffset = sternOffset + sternHeight;
        double beamOffset = remOffset + remHeight;

        SVG svg = new SVG(width, height + beamOffset, "test");

        //REM 30?
        svg.rct(30, remOffset, remHeight, width - 30 * 2, "fill:white;stroke:black;");

        //stern
        svg.rct(barge / 2, sternOffset, sternHeight, width - barge, "fill:white;stroke:black;");

        //roof bottom
        svg.rct(barge / 2, roofBottomOffset, roofBottom, width - barge, "fill:white;stroke:black;");

        //bricks
        for (double i = barge / 2; i < width - brickWidth; i += brickWidth) {
            svg.rct(x = 0 + i, y = brickOffset, h = brickHeight, w = brickWidth, "fill:white;stroke:black;stroke-width:0.5");
        }

        //top brick
        svg.rct(barge / 2, topBrickOffset, topBrick, width - barge, "fill:white;stroke:black;;stroke-width:0.5");

        ///////////////////////////////////////////// BEAMS //////////////////////////////////////
        
        if(width-shedWidth-80>80){
       
            svg.rct(80, beamOffset, height, beam, "fill:white;stroke:black;");

        }
        

        if (hasShed) {

            //left beam
            svg.rct(x = (width - shedWidth - 30), y = beamOffset, h = height, beam, "fill:white;stroke:black;");

            //right beam
            svg.rct(x = (width - beam - 30), y = beamOffset - remHeight, h = height + remHeight, beam, "fill:white;stroke:black;");

            if (width - shedWidth > 200) {
                svg.rct(x = (width - shedWidth - 30 - 80) - beam, y = beamOffset, h = height, beam, "fill:white;stroke:black;");
            }
        } else {

            //right beam
            svg.rct(x = (width - 80 - beam), y = beamOffset, h = height, beam, "fill:white;stroke:black;");

        }

        // middle beams
        double distance = (hasShed) ? width - 80 * 2 - shedWidth - 30 - beam : width - beam - 80 * 2;
        double middleBeams = (int) (distance / 400);
        double middleBeamsOffset = distance / (middleBeams + 1);

        if (middleBeamsOffset > 0) {
            for (double i = middleBeamsOffset; i <= middleBeamsOffset * middleBeams; i += middleBeamsOffset) {
                svg.rct(x = (80 + i), y = beamOffset, h = height, beam, "fill:white;stroke:black;");
            }
        }

        if (hasShed) {

            //loop :O træbrædder
            for (double i = 2.5; i < shedWidth; i += 15) {
                svg.rct(x = (width - shedWidth - 30 + i), y = beamOffset - remHeight + 1, h = height + remHeight - 1, beam, "fill:white;stroke:black;");
            }

            for (double i = 10; i < shedWidth - 15; i += 15) {
                svg.rct(x = (width - shedWidth - 30 + i), y = beamOffset - remHeight + 1, h = height + remHeight - 1, beam, "fill:white;stroke:black;stroke-width:0.6");
            }
        }

        // barge
        svg.rct(0, 0, brickHeight + brickOffset, barge, "fill:white;stroke:black;stroke-width:0.75");
        svg.rct(width - barge, 0, brickHeight + brickOffset, barge, "fill:white;stroke:black;stroke-width:0.75");

        return svg.toString();

    }

    public static String angledTop(int pLength, int pWidth, int pShedLength, int pShedWidth, boolean hasShed) {
        // variabler
        double width = pLength;
        double height = pWidth;

        double shedWidth = pShedLength;
        double shedHeight = pShedWidth;

        String std = "fill:white;stroke:black;stroke-width:1;";
        String thin = "fill:white;stroke:black;stroke-width:0.5;";

        if (shedHeight > height - 30) {
            shedHeight = height - 30;
        }

        if (shedWidth > width - 60) {
            shedWidth = width - 60;
        }

        double bargeHeight = height;
        double bargeWidth = 5;

        double coverHeight = 5;
        double coverWidth = width - bargeWidth * 2;

        double remWidth = width - 30 - 25;
        double remHeight = 4.5;

        double lathWidth = width - bargeWidth * 2;
        double lathHeight = 3.8;
        double lathHalfAmount = (int) (height / 2 / 35);
        double lathGutter = ((height / 2 - lathHeight * 2)) / lathHalfAmount;

        double spaerWidth = 4.5;
        double spaerHeight = height - 2;

        double spaerDistance = width - 30 * 2 - bargeWidth;
        double spaerAmount = (int) (spaerDistance / 89);
        double spaerGutter = spaerDistance / (spaerAmount);

        double beam = 10;

        //dynamisk???
        //double spaerAmount = (int) (width / 55);
        //double spaerGutter = ((width + spaerWidth) / spaerAmount);
        SVG svg = new SVG(width, height, "test");

        //shed
        if (hasShed) {
            svg.rct(width - shedWidth - 30 - 2, 15 - 2, shedHeight + 4, shedWidth + 2, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
            svg.rct(width - shedWidth - 30 - 1, 15 - 1, shedHeight + 2, shedWidth + 1, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
            svg.rct(width - shedWidth - 30, 15, shedHeight, shedWidth, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        }

        // spaer
        for (double i = 0; i <= spaerGutter * spaerAmount; i += spaerGutter) {
            svg.rct(i + 30, 1, spaerHeight, spaerWidth, "stroke:grey; stroke-width: 0.75; fill: white;");
        }

        // barge left & right
        svg.rct(0, 0, bargeHeight, bargeWidth, std);
        svg.rct(width - bargeWidth, 0, bargeHeight, bargeWidth, std);

        svg.line(0, height / 2, bargeWidth, height / 2, std);
        svg.line(width - bargeWidth, height / 2, width, height / 2, std);

        //rem
        svg.rct(25, 15, remHeight, remWidth, std);
        svg.rct(25, height - remHeight - 15, remHeight, remWidth, std);

        //skirts left -> right
        svg.line(30 - 1, coverHeight, 30 - 1, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        svg.line(30 - 2, coverHeight + 5, 30 - 2, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");

        svg.line(width - 30 + 1, coverHeight, width - 30 + 1, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");
        svg.line(width - 30 + 2, coverHeight + 5, width - 30 + 2, height - coverHeight, "fill:none;stroke:black;stroke-width:0.75;", "stroke-dasharray='10, 2'");

        //cover
        svg.rct(bargeWidth, 1, coverHeight, coverWidth, std);
        svg.rct(bargeWidth, height - coverHeight - 1, coverHeight, coverWidth, std);

        //lath (lægte)
        //middle lath
        svg.rct(bargeWidth, height / 2 - lathHeight / 2 / 1.5, lathHeight / 1.5, lathWidth, thin);

        for (double i = lathHeight * 2; i < height / 2; i += lathGutter) {
            svg.rct(bargeWidth, height / 2 + i, lathHeight, lathWidth, thin);
            svg.rct(bargeWidth, (height / 2) - i - lathHeight, lathHeight, lathWidth, thin);
        }

        //beams
        //always there:
        if(width-shedWidth-80>80){
            svg.rct(80, height-(15+remHeight/2+beam/2), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // CP BL
            svg.rct(80, 15+remHeight/2-beam/2, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // TL
        }
        
        if (hasShed) {
            svg.rct(width - 30 - beam, height - (15 + remHeight / 2 + beam / 2), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // CP BR
            svg.rct(width - 30 - beam, 15 + remHeight / 2 - beam / 2, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // TR

            svg.rct(width - shedWidth - 30 - 1, 15 + remHeight / 2 - beam / 2, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // TL
            svg.rct(width - 30 - beam, 15 + remHeight / 2 - beam / 2, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // TR

            svg.rct(width - shedWidth - 30 - 1, (15 + remHeight / 2) + shedHeight - beam, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // BL
            svg.rct(width - 30 - beam, (15 + remHeight / 2) + shedHeight - beam, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // BL

            svg.rct(width - shedWidth - 30 - 1, height - (15 + remHeight / 2 + beam / 2), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // EXTRA BOT

            if (width - shedWidth > 200) {

                svg.rct(width - shedWidth - 30 - 80 - beam, height - (15 + remHeight / 2 + beam / 2), beam, beam, "fill:none;stroke:black;stroke-width:2.5;");
                svg.rct(width - shedWidth - 30 - 80 - beam, 15 + remHeight / 2 - beam / 2, beam, beam, "fill:none;stroke:black;stroke-width:2.5;");
            }
        } else {
            svg.rct(width - 80 - beam / 2 - bargeWidth, height - (15 + remHeight / 2 + beam / 2), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // CP BR
            svg.rct(width - 80 - beam / 2 - bargeWidth, 15 + remHeight / 2 - beam / 2, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // TR
        }

        // middle beams
        double distance = (hasShed) ? width - 80 * 2 - shedWidth - 30 - beam : width - beam - 80 * 2;
        double middleBeams = (int) (distance / 400);
        double middleBeamsOffset = distance / (middleBeams + 1);

        if (middleBeamsOffset > 0) {
            for (double i = middleBeamsOffset; i <= middleBeamsOffset * middleBeams; i += middleBeamsOffset) {
                svg.rct(80 + i, height - (15 + remHeight / 2 + beam / 2), beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // CP BL
                svg.rct(80 + i, 15 + remHeight / 2 - beam / 2, beam, beam, "fill:none;stroke:black;stroke-width:2.5;"); // TL

                //svg.rct( x = (80 + i),   y = beamOffset,   h = height,    beam, "fill:white;stroke:black;");
            }
        }

        return svg.toString();
    }

}
