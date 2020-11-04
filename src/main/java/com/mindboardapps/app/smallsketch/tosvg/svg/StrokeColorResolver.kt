package com.mindboardapps.app.smallsketch.tosvg.svg

import com.mindboardapps.app.smallsketch.tosvg.style.*
import com.mindboardapps.app.smallsketch.tosvg.utils.ColorUtils
import com.mindboardapps.app.smallsketch.tosvg.model.Color

object StrokeColorResolver {
    fun resolve(styleObject: IStyleObject, strokeColorAsIndex: Int): Color {
        val strokeColor = styleObject.strokeColor
        val strokeColorList = styleObject.strokeColorList

        return if( strokeColor!=null ){
            strokeColor
        }
        else if( strokeColorList!=null ){
            if( 0<=strokeColorAsIndex && strokeColorAsIndex<strokeColorList.size){
                strokeColorList[strokeColorAsIndex]
            }
            else {
                StyleObjectRes.BLACK
            }
        }
        else {
            if( 0<=strokeColorAsIndex && strokeColorAsIndex<StyleObjectRes.DEFAULT_STROKE_COLOR_LIST.size){
                StyleObjectRes.DEFAULT_STROKE_COLOR_LIST[strokeColorAsIndex]
            }
            else {
                StyleObjectRes.BLACK
            }
        }
    }
}
