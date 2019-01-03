package com.mindboardapps.app.smallsketch.tosvg.db

import com.mindboardapps.app.smallsketch.tosvg.model.*

class Db : IDb {
    private val mapForStroke = hashMapOf<String, IStrokeObject>()

    override fun add(strokeObject:IStrokeObject) {
        mapForStroke[strokeObject.uuid]=strokeObject
    }

    override fun remove(strokeObject: IStrokeObject){
        mapForStroke.remove(strokeObject.uuid)
    }

    override fun getStroke(uuid: String): IStrokeObject? {
        return mapForStroke[uuid]
    }

    override fun clearAllStrokes() {
        mapForStroke.clear()
    }

    override fun strokeObjectList(): List<IStrokeObject> {
        return mapForStroke.toList().map { pair-> pair.second }
    }
}
