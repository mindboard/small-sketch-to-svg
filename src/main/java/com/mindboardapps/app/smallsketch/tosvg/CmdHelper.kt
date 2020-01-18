package com.mindboardapps.app.smallsketch.tosvg

import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.model.*
import com.mindboardapps.app.smallsketch.tosvg.db.*

object CmdHelper {
    fun toStrokeObjectList(lines: List<String>): List<IStrokeObject> {
        val db = Db()

        val cmdLoader = CmdLoader(db)
        lines.forEach { line->
            val jsonObject = JSONObject(line)
            cmdLoader.process( jsonObject )
        }

        return db.strokeObjectList()
    }

    fun toCanvasRectF(strokeObjectList: List<IStrokeObject>): CanvasRectF {
        val minLeft   = strokeObjectList.map { it.getBounds().left }.reduce { item0, item1-> Math.min(item0,item1) }
        val maxRight  = strokeObjectList.map { it.getBounds().right }.reduce { item0, item1-> Math.max(item0,item1) }
        val minTop    = strokeObjectList.map { it.getBounds().top }.reduce { item0, item1-> Math.min(item0,item1) }
        val maxBottom = strokeObjectList.map { it.getBounds().bottom }.reduce { item0, item1-> Math.max(item0,item1) }

        return CanvasRectF(minLeft, minTop, maxRight, maxBottom)
    }
}
