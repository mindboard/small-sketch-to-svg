package com.mindboardapps.app.smallsketch.tosvg.action

import com.mindboardapps.app.smallsketch.tosvg.db.IDb
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject
import com.mindboardapps.app.smallsketch.tosvg.model.RectF
import com.mindboardapps.app.smallsketch.tosvg.utils.GroupUtils
import com.mindboardapps.app.smallsketch.tosvg.utils.ResizeGroupUtils

class GroupResizeActionWhenLoad(
        private val db: IDb,
        private val strokeObjectUuidList: List<String>,
        private val transformScaleX: Float,
        private val transformScaleY: Float) : IAction {

    private val strokeObjectList: List<IStrokeObject> by lazy {
        ActionHelper.createStrokeObjectList(db, strokeObjectUuidList)
    }

    override fun exec() {
        val groupBounds: RectF = GroupUtils.getGroupBounds(strokeObjectList)

        val transformMatrixValues: FloatArray = ResizeGroupUtils.createTransformMatrixValues(
            transformScaleX,
            transformScaleY,
            groupBounds)

        strokeObjectList.forEach {
            it.applyTransform( transformMatrixValues )
        }
    }
}
