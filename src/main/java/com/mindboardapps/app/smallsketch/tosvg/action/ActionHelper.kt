package com.mindboardapps.app.smallsketch.tosvg.action

import com.mindboardapps.app.smallsketch.tosvg.db.IDb
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject

object ActionHelper {
    fun createStrokeObjectList(
            db: IDb,
            strokeObjectUuidList: List<String>): List<IStrokeObject> {

        val list = arrayListOf<IStrokeObject>()
        for (uuid in strokeObjectUuidList) {
            val strokeObject = db.getStroke(uuid)
            if (strokeObject != null) {
                list.add(strokeObject)
            }
        }
        return list
    }

    fun applyTransform(
            strokeObjectList: List<IStrokeObject>,
            startX: Float,
            startY: Float,
            stopX: Float,
            stopY: Float) {

        val tx = stopX - startX
        val ty = stopY - startY

        val matrixValues = floatArrayOf(
                1f, 0f, tx,
                0f, 1f, ty,
                0f, 0f, 1f)

        for (strokeObject in strokeObjectList) {
            strokeObject.applyTransform(matrixValues)
        }
    }
}
