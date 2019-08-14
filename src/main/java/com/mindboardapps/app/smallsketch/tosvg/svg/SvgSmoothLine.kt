package com.mindboardapps.app.smallsketch.tosvg.svg

import com.mindboardapps.app.smallsketch.tosvg.style.*
import com.mindboardapps.app.smallsketch.tosvg.utils.*

class SvgSmoothLine(
    private val styleObject: IStyleObject,
    private val pointList: List<Point>) : ISvgPart {

    private val strokeWidth = styleObject.strokeWidth
    private val strokeColor = SvgRes.createRgbColor( styleObject.strokeColor )

    override fun toSvg(): String {
        if( pointList.size<2 ){
            return ""
        }
        else if( pointList.size==2 ){
            return SvgLine( styleObject, pointList[0], pointList[1] ).toSvg() 
        }
        else if( pointList.size<8 ){
            return SvgLine2( styleObject, pointList).toSvg()
        }
        else {
            val sb = StringBuilder()
            sb.append("<g stroke=\"$strokeColor\" stroke-width=\"$strokeWidth\" fill=\"none\">")

            val startPt = pointList[0]
            sb.append("<path d = \"M ${startPt.x} ${startPt.y} Q ")

            val start = 1
            val end   = pointList.size-2

            start.until(end).forEach { index->
                val point1 = pointList[index]
                val pt1X = point1.x
                val pt1Y = point1.y

                val point2 = pointList[index+1]
                val pt2X = point2.x
                val pt2Y = point2.y

                val xc = (pt1X + pt2X) / 2f
                val yc = (pt1Y + pt2Y) / 2f

                sb.append("${pt1X} ${pt1Y} ${xc} ${yc} ")
                /*
                val state: TwoPointState = PointCheckUtils.valid(pt1X, pt1Y, xc, yc, 1.0f)
                if( state!=TwoPointState.SAME ){ 
                    sb.append("${pt1X} ${pt1Y} ${xc} ${yc} ")
                }
                */
            }

            val indexLast2  = pointList.size-2
            val indexLast1  = pointList.size-1

            val pt1X = pointList[indexLast2].x
            val pt1Y = pointList[indexLast2].y

            val pt2X = pointList[indexLast1].x
            val pt2Y = pointList[indexLast1].y

            sb.append("${pt1X} ${pt1Y} ${pt2X} ${pt2Y}")
            /*
            val state: TwoPointState = PointCheckUtils.valid(pt1X, pt1Y, pt2X, pt2Y, 1.0f)
            if( state!=TwoPointState.SAME ){
                sb.append("${pt1X} ${pt1Y} ${pt2X} ${pt2Y}")
            }
            */

            sb.append("\" />")
            sb.append("</g>")
            return sb.toString()
        }
    }
}
