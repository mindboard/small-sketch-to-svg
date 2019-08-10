package com.mindboardapps.app.smallsketch.tosvg.utils

import com.mindboardapps.app.smallsketch.tosvg.model.RectF

object ResizeGroupUtils {
    fun createTransformScaleX(oldGroupBounds: RectF, newGroupBounds: RectF): Float {
        return newGroupBounds.width() / oldGroupBounds.width()
    }

    fun createTransformScaleY(oldGroupBounds: RectF, newGroupBounds: RectF): Float {
        return newGroupBounds.height() / oldGroupBounds.height()
    }

    fun createTransformMatrixValues(
        xScale: Float,
        yScale: Float,
        oldGroupBounds: RectF): FloatArray {

        val oldGroupCenterX = oldGroupBounds.left + oldGroupBounds.width()/2f
        val oldGroupCenterY = oldGroupBounds.top  + oldGroupBounds.height()/2f

        val transform0  = floatArrayOf(
            1f, 0f, -oldGroupCenterX,
            0f, 1f, -oldGroupCenterY,
            0f, 0f, 1f)

        val transform1  = floatArrayOf(
            xScale, 0f, 0f,
            0f, yScale, 0f,
            0f, 0f, 1f)
            
        val transform2  = floatArrayOf(
            1f, 0f, oldGroupCenterX,
            0f, 1f, oldGroupCenterY,
            0f, 0f, 1f)

        // transform0->1->2 の順に適用
        return MatrixUtils.times(transform2, transform1, transform0)
    }
}
