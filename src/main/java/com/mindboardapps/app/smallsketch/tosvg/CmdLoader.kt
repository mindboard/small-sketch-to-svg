package com.mindboardapps.app.smallsketch.tosvg

import java.io.*
import java.util.zip.GZIPInputStream

import org.json.*

import com.mindboardapps.app.smallsketch.tosvg.db.*
import com.mindboardapps.app.smallsketch.tosvg.action.*

class CmdLoader(private val db: IDb) {
    private companion object {
        val pageUuid = "0"
        val logicalStrokeWidth = 0
    }

    fun process(jsonObject: JSONObject) {
        val name = jsonObject.getString("name")
        val contentsJsonObject = jsonObject.getJSONObject("contents")

        when(name){
            CmdName.ADD_STROKE.name     -> processAddStroke(contentsJsonObject)
            CmdName.DELETE_STROKES.name -> processDeleteStrokes(contentsJsonObject)
            CmdName.MOVE_STROKES.name   -> processMoveStrokes(contentsJsonObject)
            CmdName.RESIZE_GROUP.name   -> processResizeGroup(contentsJsonObject)
        }
    }

    private fun processAddStroke(contentsJsonObject: JSONObject){
        val strokeObjectUuid = contentsJsonObject.getString("uuid")
        val strokeColor = contentsJsonObject.getInt("color")
        val groupUuid = contentsJsonObject.getString("groupUuid")
        val ptsArray = contentsJsonObject.getJSONArray("pts")

        val pts0 = 0.until(ptsArray.length()).map { ptsArray.getDouble(it).toFloat() }
        val pts1 = pts0.toFloatArray()

        if (pts1.isNotEmpty()) {
            val action = AddStrokeActionWhenLoad(
                    db,
                    pageUuid,
                    strokeObjectUuid,
                    pts1,
                    strokeColor,
                    groupUuid,
                    logicalStrokeWidth)

            action.exec()
        }
    }

    private fun processDeleteStrokes(contentsJsonObject: JSONObject){
        val uuidArray = contentsJsonObject.getJSONArray("uuids")
        val uuids = 0.until(uuidArray.length()).map { uuidArray.getString(it) }

        if (uuids.isNotEmpty()) {
            val action = DeleteStrokesActionWhenLoad(db, uuids)
            action.exec()
        }
    }

    private fun processMoveStrokes(contentsJsonObject: JSONObject){
        val ptsArray = contentsJsonObject.getJSONArray("pts")
        val pts0 = 0.until(ptsArray.length()).map { ptsArray.getDouble(it).toFloat() }

        val uuidArray = contentsJsonObject.getJSONArray("uuids")
        val uuids = 0.until(uuidArray.length()).map { uuidArray.getString(it) }

        if (uuids.isNotEmpty()) {
            val pts1 = pts0.toFloatArray()
            if (pts1.size == 4) {
                val startX = pts1[0]
                val startY = pts1[1]
                val stopX = pts1[2]
                val stopY = pts1[3]

                val action = MoveStrokesActionWhenLoad(
                        db,
                        uuids,
                        floatArrayOf(startX, startY),
                        floatArrayOf(stopX, stopY))

                action.exec()
            }
        }
    }

    private fun processResizeGroup(contentsJsonObject: JSONObject){
        val scaleX = contentsJsonObject.getDouble("scaleX").toFloat()
        val scaleY = contentsJsonObject.getDouble("scaleY").toFloat()

        val strokeUuidArray = contentsJsonObject.getJSONArray("strokeUuids")
        val uuids = 0.until(strokeUuidArray.length()).map { strokeUuidArray.getString(it) }

        if( uuids.size>0 ){
            val action = GroupResizeActionWhenLoad(
                db,
                uuids,
                scaleX,
                scaleY)
            action.exec()
        }
    }
}
