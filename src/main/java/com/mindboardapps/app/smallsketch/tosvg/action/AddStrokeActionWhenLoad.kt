package com.mindboardapps.app.smallsketch.tosvg.action

import com.mindboardapps.app.smallsketch.tosvg.db.IDb
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject
import com.mindboardapps.app.smallsketch.tosvg.model.StrokeObject

class AddStrokeActionWhenLoad(
        private val db: IDb,
        pageUuid: String,
        uuid: String,
        pts: FloatArray,
        color: Int,
        groupUuid: String,
        logicalStrokeWidth: Int) : IAction{

    private val strokeObject: IStrokeObject = StrokeObject(
                pageUuid,
                uuid,
                pts,
                color,
                groupUuid,
                logicalStrokeWidth)

    override fun exec() {
        db.add(strokeObject)
    }
}
