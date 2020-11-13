package com.mindboardapps.app.smallsketch.tosvg.svg

import com.mindboardapps.app.smallsketch.tosvg.style.*
import com.mindboardapps.app.smallsketch.tosvg.utils.ColorUtils
import com.mindboardapps.app.smallsketch.tosvg.model.Color

object StrokeColorResolver {
    fun resolve(styleObject: IStyleObject, strokeColorAsIndex: Int): Color {
        val strokeColor = styleObject.strokeColor
        val strokeColorList = styleObject.strokeColorList

        return if( strokeColorList!=null ){
            if(0<=strokeColorAsIndex && strokeColorAsIndex<strokeColorList.size){
                strokeColorList[strokeColorAsIndex]
            }
            else {
                if( strokeColor!=null ){ strokeColor } else { StyleObjectRes.BLACK }
            }
        }
        else {
           if( strokeColor!=null ){ strokeColor } else { StyleObjectRes.BLACK }
        }
    }
}
