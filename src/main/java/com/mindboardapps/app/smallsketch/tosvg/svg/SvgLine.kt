package com.mindboardapps.app.smallsketch.tosvg.svg

import com.mindboardapps.app.smallsketch.tosvg.style.*

class SvgLine(
    private val styleObject: IStyleObject,
    private val startPt: Point,
    private val stopPt: Point) : ISvgPart {

    private val strokeWidth = styleObject.strokeWidth
    private val strokeColor = SvgRes.createRgbColor( styleObject.strokeColor )

    override fun toSvg(): String {
        val sb = StringBuilder()
        sb.append("<g stroke = \"$strokeColor\" stroke-width = \"$strokeWidth\" fill = \"none\">")
        sb.append("<path d = \"M ${startPt.x} ${startPt.y} L ${stopPt.x} ${stopPt.y}\" />")
        sb.append("</g>")
        return sb.toString()
    }
}
