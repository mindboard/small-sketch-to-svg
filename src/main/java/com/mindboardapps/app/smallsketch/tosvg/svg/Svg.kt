package com.mindboardapps.app.smallsketch.tosvg.svg

import com.mindboardapps.app.smallsketch.tosvg.model.Color
import com.mindboardapps.app.smallsketch.tosvg.style.*

class Svg(
    private val styleObject: IStyleObject,
    private val canvasWidth: Int = 1080,
    private val canvasHeight: Int = 1080){

    private companion object {
        val BR = System.getProperty("line.separator")

        private fun createXYWH(bounds: Rectangle): String{
            val sb = StringBuilder()
            sb.append("x=\"").append( bounds.x ).append("\" ")
            sb.append("y=\"").append( bounds.y ).append("\" ")
            sb.append("width=\"").append( bounds.width ).append("\" ")
            sb.append("height=\"").append( bounds.height ).append("\" ")
            return sb.toString()
        }

        private fun createViewBox(bounds: Rectangle): String{
            val sb = StringBuilder()
            sb.append("viewBox=\"").append( bounds.x ).append(" ").append( bounds.y ).append(" ").append( bounds.width ).append(" ").append( bounds.height ).append("\"");
            return sb.toString()
        }

        fun createSVGHeader(
            styleObject: IStyleObject,
            paperRect: Rectangle,
            viewBoxRect: Rectangle): String {
                val sb = StringBuilder()
            
                sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                sb.append(BR)
                sb.append("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">")
                sb.append(BR)
                sb.append("<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" ")
                sb.append(BR)
                sb.append(createXYWH(paperRect))
                sb.append(" ")
                sb.append(createViewBox(viewBoxRect))
                sb.append(">")
        
                val backgroundFillColor = if( styleObject.fillBackground ){ SvgRes.createRgbColor(styleObject.backgroundColor) } else { "none" }

                if( styleObject.border ){
                    val borderStrokeColor   = SvgRes.createRgbColor(styleObject.borderColor)
                    sb.append("<g style=\"stroke:$borderStrokeColor\" stroke-width=\"1\" fill=\"$backgroundFillColor\">")
                    sb.append("<rect x=\"${viewBoxRect.x}\" y=\"${viewBoxRect.y}\" width=\"${viewBoxRect.width}\" height=\"${viewBoxRect.height}\"/>")
                    sb.append("</g>")
                }
                else {
                    val borderStrokeColor = SvgRes.createRgbColor(styleObject.backgroundColor)
                    sb.append("<g style=\"stroke:$borderStrokeColor\" stroke-width=\"0\" fill=\"$backgroundFillColor\">")
                    sb.append("<rect x=\"${viewBoxRect.x}\" y=\"${viewBoxRect.y}\" width=\"${viewBoxRect.width}\" height=\"${viewBoxRect.height}\"/>")
                    sb.append("</g>")
                }

                return sb.toString()
        }

        fun createSVGFooter(): String {
            return "</svg>"
        }
    }

    fun createSvg(svgPartList: List<ISvgPart>): String {
        val paperRect   = Rectangle( 0, 0, canvasWidth, canvasHeight )
        val viewBoxRect = Rectangle( 0, 0, canvasWidth, canvasHeight )

        val sb = StringBuilder()
        sb.append(createSVGHeader( styleObject, paperRect, viewBoxRect ))
        sb.append(BR)

        svgPartList.forEach {
            sb.append(it.toSvg())
            sb.append(BR)
        }

        sb.append(createSVGFooter())

        return sb.toString()
    }
}
