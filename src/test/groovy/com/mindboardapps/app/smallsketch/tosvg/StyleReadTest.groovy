package com.mindboardapps.app.smallsketch.tosvg

import spock.lang.Specification
import spock.lang.Ignore
import com.mindboardapps.app.smallsketch.tosvg.style.*

class StyleReadTest extends Specification {
    //@Ignore
    def "read-style-json-file-test"(){
        when:
        def styleJsonFile = new File('./examples/style.json')
        def styleObject = new StyleObject( styleJsonFile )

        then:
        styleJsonFile.exists()
        styleObject.hasPadding == true
        styleObject.strokeWidth == 1.2f
        styleObject.backgroundColor.r == 253
        styleObject.backgroundColor.g == 246
        styleObject.backgroundColor.b == 227
        styleObject.strokeColorList != null
        styleObject.strokeColorList?.size == 6
    }
}
