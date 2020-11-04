package com.mindboardapps.app.smallsketch.tosvg.svg

import com.mindboardapps.app.smallsketch.tosvg.style.*

class SvgLine2(
    private val styleObject: IStyleObject,
    private val strokeColorAsIndex: Int,
    private val pointList: List<Point>) : ISvgPart {

    private val strokeWidth = styleObject.strokeWidth
    private val strokeColor = SvgRes.createRgbColor(StrokeColorResolver.resolve(styleObject, strokeColorAsIndex))

    override fun toSvg(): String {
        if( pointList.size<2 ){
            return ""
        }
        else if( pointList.size==2 ){
            return SvgLine(
                styleObject,
                strokeColorAsIndex,
                pointList[0], pointList[1] ).toSvg() 
        }
        else {
            val sb = StringBuilder()
            sb.append("<g stroke=\"$strokeColor\" stroke-width=\"$strokeWidth\" fill=\"none\">")

            val startPt = pointList[0]
            sb.append("<path d = \"M ${startPt.x} ${startPt.y} L ")
            
            1.until(pointList.size).forEach {index->
                val stopPt = pointList[index]
                sb.append("${stopPt.x} ${stopPt.y} ")
            }
            sb.append("\" />")

            sb.append("</g>")
            return sb.toString()
        }
    }
}
