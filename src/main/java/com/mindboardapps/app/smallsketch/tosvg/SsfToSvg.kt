package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import java.util.zip.GZIPInputStream

import org.json.*

import com.mindboardapps.app.smallsketch.tosvg.db.*
import com.mindboardapps.app.smallsketch.tosvg.svg.*
import com.mindboardapps.app.smallsketch.tosvg.style.*
import com.mindboardapps.app.smallsketch.tosvg.utils.Matrix
import com.mindboardapps.app.smallsketch.tosvg.utils.MatrixUtils

class SsfToSvg(private val styleObject: IStyleObject) {
    companion object {
        private val DEFAULT_PADDING_FACTOR = 0.9f
    }

    val strokeWidth = styleObject.strokeWidth

    fun createSvg(lines: List<String>): String {
        val db = Db()

        val cmdLoader = CmdLoader(db)
        lines.forEach { line->
            val jsonObject = JSONObject(line)
            cmdLoader.process( jsonObject )
        }

        val strokeObjectList = db.strokeObjectList()

        val minLeft   = strokeObjectList.map { it.getBounds().left }.reduce { item0, item1-> Math.min(item0,item1) }
        val maxRight  = strokeObjectList.map { it.getBounds().right }.reduce { item0, item1-> Math.max(item0,item1) }
        val minTop    = strokeObjectList.map { it.getBounds().top }.reduce { item0, item1-> Math.min(item0,item1) }
        val maxBottom = strokeObjectList.map { it.getBounds().bottom }.reduce { item0, item1-> Math.max(item0,item1) }

        val canvasWidth  = (maxRight - minLeft)
        val canvasHeight = (maxBottom - minTop)

        val scale = if( styleObject.enabledCanvasWidth() ){
            styleObject.canvasWidth!!.toFloat() / canvasWidth
        }
        else {
            1f
        }

        val svg = if( styleObject.enabledCanvasWidth() ){
            Svg(
                styleObject,
                ( canvasWidth * scale ).toInt(),
                ( canvasHeight * scale ).toInt() )
        }
        else {
            Svg(
                styleObject,
                canvasWidth.toInt(),
                canvasHeight.toInt() )
        }

        val translationMatrixValues0 = floatArrayOf(
            1f, 0f, (0f - minLeft) - canvasWidth/2f,
            0f, 1f, (0f - minTop)  - canvasHeight/2f,
            0f, 0f, 1f)

        var paddingFactor = if( styleObject.hasPadding ){ DEFAULT_PADDING_FACTOR } else { 1f }
        val scaleMatrixValues = floatArrayOf(
            scale * paddingFactor , 0f, 0f,
            0f, scale * paddingFactor, 0f,
            0f, 0f, 1f)

        val translationMatrixValues1 = floatArrayOf(
            1f, 0f, canvasWidth*scale/2f,
            0f, 1f, canvasHeight*scale/2f,
            0f, 0f, 1f)

        val matrixValues = MatrixUtils.times(translationMatrixValues1, scaleMatrixValues, translationMatrixValues0)


        val matrix = Matrix()
        matrix.setValues( matrixValues )

        val svgPartList = arrayListOf<ISvgPart>()

        strokeObjectList.forEach {
            val pts = it.pts
            
            val len = pts.size

            if( len>3 ){
                matrix.mapPoints( pts )

                // In rendering quality, A is better than B.

                // A) Simple
                val ptList = 0.until(len).step(2).map { Point(pts[it],pts[it+1]) }
                svgPartList.add( SvgSmoothLine(styleObject, ptList) )

                /*
                // B) Smooth
                val ptList = 0.until(len).step(2).map { Point(pts[it],pts[it+1]) }
                if( len<8 ){
                    svgPartList.add( SvgLine2(styleObject, ptList) )
                }
                else {
                    svgPartList.add( SvgSmoothLine(styleObject, ptList) )
                }
                */
            }
        }

        return svg.createSvg( svgPartList )
    }
}
