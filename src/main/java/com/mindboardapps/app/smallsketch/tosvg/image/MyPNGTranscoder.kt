package com.mindboardapps.app.smallsketch.tosvg.image

import java.awt.RenderingHints
import org.apache.batik.transcoder.image.PNGTranscoder
import org.apache.batik.gvt.renderer.ImageRenderer

class MyPNGTranscoder(): PNGTranscoder() {
    override fun createRenderer(): ImageRenderer {
        val r = super.createRenderer()
        val rh = r.getRenderingHints()

        rh.add(RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY))
        rh.add(RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC))
        rh.add(RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON))
        rh.add(RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY))
        rh.add(RenderingHints(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE))
        rh.add(RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY))
        rh.add(RenderingHints(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE))
        rh.add(RenderingHints(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON))
        rh.add(RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)) 

        r.setRenderingHints(rh)

        return r
    }
}

