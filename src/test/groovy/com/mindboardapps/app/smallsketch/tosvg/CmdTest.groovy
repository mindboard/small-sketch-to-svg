package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import spock.lang.Ignore

import java.util.zip.GZIPInputStream

import org.json.*

class CmdTest extends Specification {
	//@Ignore
    def "cmd-test"(){
        when:
        //def cmdFile = new File('cmd.txt')

        def ssfFile = new File('./examples/nuc.ssf')
        def lines = new GZIPInputStream(new FileInputStream(ssfFile)).readLines()

		def list = []
		lines.each { line->
			def obj = new JSONObject(line)
			if( obj.name=='RESIZE_GROUP' ){
				list << obj.name
				list << obj.contents
				// scaleX, scaleY
			}
		}

		//cmdFile.text = list.join(System.getProperty('line.separator'))

        then:
		list.size()>0
		//cmdFile.exists()
    }
}
