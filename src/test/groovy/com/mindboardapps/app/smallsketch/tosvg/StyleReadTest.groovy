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
        styleObject.padding == true
        styleObject.backgroundColor.r == 253
        styleObject.backgroundColor.g == 246
        styleObject.backgroundColor.b == 227
        styleObject.borderWidth == 1.2f
        styleObject.strokeWidth == 3.2f
        styleObject.strokeColor != null
        styleObject.strokeColorList != null
        styleObject.strokeColorList?.size == 6
    }
}
