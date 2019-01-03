package com.mindboardapps.app.smallsketch.tosvg.db

import com.mindboardapps.app.smallsketch.tosvg.model.IStrokeObject

interface IDb {
    fun add(strokeObject:IStrokeObject)
    fun remove(strokeObject: IStrokeObject)
    fun getStroke(uuid: String): IStrokeObject?
    fun clearAllStrokes()
    fun strokeObjectList(): List<IStrokeObject>
}
