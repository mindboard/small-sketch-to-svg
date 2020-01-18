package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import spock.lang.Ignore
import java.util.zip.GZIPInputStream
import org.json.*
import com.mindboardapps.app.smallsketch.tosvg.db.*
import com.mindboardapps.app.smallsketch.tosvg.model.*

class CmdLoaderTest extends Specification {

    private static List<String> getLines(){
        def ssfFile = new File('./examples/nuc.ssf')
        return new GZIPInputStream(new FileInputStream(ssfFile)).readLines()
    }

    private static List<IStrokeObject> toStrokeObject(List<String> lines){
        def db = new Db()

        def cmdLoader = new CmdLoader(db)
        getLines().each { line->
            def jsonObject = new JSONObject(line)
            cmdLoader.process( jsonObject )
        }

        return db.strokeObjectList()
    }
    
    //@Ignore
    def "stroke-object-list-size-test"(){
        when:
        def strokeObjectList = toStrokeObject( getLines() )

        then:
        strokeObjectList.size() == 153
    }

    //@Ignore
    def "canavs-params-test"(){
        when:
        def strokeObjectList = toStrokeObject( getLines() )

        def minLeft   = strokeObjectList.collect { it.getBounds().left }.min()
        def minTop    = strokeObjectList.collect { it.getBounds().top }.min()
        def maxRight  = strokeObjectList.collect { it.getBounds().right }.max()
        def maxBottom = strokeObjectList.collect { it.getBounds().bottom }.max()

        then:
        minLeft == -651.21277f
        minTop == -541.86035f
        maxRight == 296.5984f
        maxBottom == 207.61145f
    }

    //@Ignore
    def "cmd-helper-test"(){
        when:
        def strokeObjectListA = toStrokeObject( getLines() )
        def strokeObjectListB = CmdHelper.INSTANCE.toStrokeObjectList(getLines())

        def canvasRectF = CmdHelper.INSTANCE.toCanvasRectF(strokeObjectListB)

        then:
        strokeObjectListA.size() == strokeObjectListB.size()
        canvasRectF.left == -651.21277f
        canvasRectF.top == -541.86035f
        canvasRectF.right == 296.5984f
        canvasRectF.bottom == 207.61145f
    }
}
