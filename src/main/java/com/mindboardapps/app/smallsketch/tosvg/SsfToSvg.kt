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


        val translationMatrixValues = floatArrayOf(
            1f, 0f, (0f - minLeft),
            0f, 1f, (0f - minTop),
            0f, 0f, 1f)

        val scaleMatrixValues = floatArrayOf(
            scale, 0f, 0f,
            0f, scale, 0f,
            0f, 0f, 1f)

        // translation -> scale 
        val matrixValues = MatrixUtils.times(scaleMatrixValues, translationMatrixValues)

        val matrix = Matrix()
        matrix.setValues( matrixValues )

        val svgPartList = arrayListOf<ISvgPart>()

        strokeObjectList.forEach {
            val pts = it.pts
            
            val len = pts.size
            if( len>3 ){
                matrix.mapPoints( pts )
                val ptList = arrayListOf<Point>()
                0.until(len).step(2).forEach { index->
                    val x = pts[index]
                    val y = pts[index+1]
                    ptList.add( Point(x,y) )
                }

                svgPartList.add( SvgLine2(styleObject, ptList) )
            }
        }

        return svg.createSvg( svgPartList )
    }
}
