package com.mindboardapps.app.smallsketch.tosvg.style

import java.io.*
import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.model.Color

class StyleObject(styleJsonFile: File) : IStyleObject{
    override var border: Boolean = false
    override var borderColor: Color = StyleObjectRes.BLACK
    override var borderWidth: Float = 1.0f
    override var padding: Boolean = false
    override var fillBackground: Boolean = false
    override var strokeWidth: Float = 1.0f
    override var strokeColor: Color? = null
    override var strokeColorList: List<Color>? = null
    override var backgroundColor: Color = StyleObjectRes.WHITE
    override var canvasWidth: Int? = null

    init {
        if( styleJsonFile.exists() ){
            val styleJsonText = FileInputStream(styleJsonFile).bufferedReader(Charsets.UTF_8).use {
                it.readText()
            }

            val jsonObject = JSONObject(styleJsonText)

            this.border = jsonObject.getBoolean("border")
            this.borderWidth = jsonObject.getDouble("borderWidth").toFloat()
            this.borderColor = StyleObjectRes.createColor( jsonObject.getJSONObject("borderColor"), StyleObjectRes.BLACK )

            try {
                this.padding = jsonObject.getBoolean("padding")
            }
            catch( ex:JSONException ){
                try {
                    this.padding = jsonObject.getBoolean("hasPadding")
                }
                catch( ex:JSONException ){
                    this.padding = false
                }
            }

            try {
                this.fillBackground = jsonObject.getBoolean("fillBackground")
            }
            catch( ex:JSONException ){
                this.fillBackground = false
            }

            this.backgroundColor = StyleObjectRes.createColor( jsonObject.getJSONObject("backgroundColor"), StyleObjectRes.WHITE )

            this.strokeWidth = jsonObject.getDouble("strokeWidth").toFloat()


            try {
                val strokeColorArray = jsonObject.getJSONArray("strokeColorList")

                val strokeColorList = 0.until(6).map{ index->
                    if( index<strokeColorArray.length()){
                        val strokeColorValue = strokeColorArray.getJSONObject(index)
                        StyleObjectRes.createColor(strokeColorValue, StyleObjectRes.BLACK )
                    }
                    else {
                        StyleObjectRes.BLACK
                    }
                }

                this.strokeColorList = strokeColorList

                if( strokeColorList.size>0 ){
                    this.strokeColor = strokeColorList[0]
                }
                else {
                    this.strokeColor = StyleObjectRes.BLACK
                }
            }
            catch( ex:JSONException ){
                try {
                    val strokeColorValue = jsonObject.getJSONObject("strokeColor")
                    this.strokeColor = StyleObjectRes.createColor(strokeColorValue, StyleObjectRes.BLACK )
                }
                catch( ex:JSONException ){
                    this.strokeColorList = StyleObjectRes.DEFAULT_STROKE_COLOR_LIST
                }
            }

            this.canvasWidth = jsonObject.getInt("canvasWidth")
        }
    }

    override fun enabledCanvasWidth(): Boolean {
        val w = this.canvasWidth

        return if( w==null ){
            false
        }
        else if( w<1 ){
            false
        }
        else {
            true
        }
    }
}
