package com.mindboardapps.app.smallsketch.tosvg.action

import com.mindboardapps.app.smallsketch.tosvg.db.IDb
import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject

class DeleteStrokesActionWhenLoad(
        private val db: IDb,
        private val strokeObjectUuidList: List<String>) : IAction {

    private val strokeObjectList: List<IStrokeObject> by lazy {
        ActionHelper.createStrokeObjectList(db, strokeObjectUuidList)
    }

    override fun exec() {
        strokeObjectList.forEach {
            db.remove(it)
        }
    }
}
