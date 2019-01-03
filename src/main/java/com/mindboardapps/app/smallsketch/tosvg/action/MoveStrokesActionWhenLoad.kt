package com.mindboardapps.app.smallsketch.tosvg.action

import com.mindboardapps.app.smallsketch.tosvg.db.IDb
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject

class MoveStrokesActionWhenLoad(
        private val db: IDb,
        private val strokeObjectUuidList: List<String>,
        private val startPoint: FloatArray,
        private val stopPoint: FloatArray) : IAction {

    private val strokeObjectList: List<IStrokeObject> by lazy {
        ActionHelper.createStrokeObjectList(db, strokeObjectUuidList)
    }

    override fun exec() {
        val startX = startPoint[0]
        val startY = startPoint[1]

        val stopX = stopPoint[0]
        val stopY = stopPoint[1]

        ActionHelper.applyTransform(strokeObjectList, startX, startY, stopX, stopY)
    }
}
