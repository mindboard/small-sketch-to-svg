package com.mindboardapps.app.smallsketch.tosvg.utils

import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject
import com.mindboardapps.app.smallsketch.tosvg.model.StrokeObject
import com.mindboardapps.app.smallsketch.tosvg.model.RectF

object GroupUtils {
    private fun isOdd(len: Int): Boolean {
        return (len%2==1)
    }

    fun getGroupBounds(
            tmpStrokeObjectList: List<IStrokeObject>,
            padding: Float = 0f
        ): RectF {

        val xList = arrayListOf<Float>()
        val yList = arrayListOf<Float>()

        for(tmpStrokeObject in tmpStrokeObjectList) {
            val pts = tmpStrokeObject.pts

            // 偶数に直す, 必要なら
            val len = if( isOdd(pts.size) ) { pts.size-1 } else { pts.size }

            val lenMinus1 = len - 1

            for (index in 0..lenMinus1 step 2) {
                val x = pts[index]
                xList.add(x)

                val y = pts[index + 1]
                yList.add(y)
            }
        }

        val left   = xList.min()?:0f
        val top    = yList.min()?:0f
        val right  = xList.max()?:0f
        val bottom = yList.max()?:0f

        return RectF(
                left - padding,
                top - padding,
                right + padding,
                bottom + padding)
    }
}
