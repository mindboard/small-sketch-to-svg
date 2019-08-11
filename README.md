
# ssf2img

CLI tool to convert SSF(small-sketch-format) into SVG, PNG format.

* Small Sketch : https://play.google.com/store/apps/details?id=com.mindboardapps.app.smallsketch


## Usage

1. Build ssf2img.zip  
    `./gradlew distZip` and build/distributions/ssf2img.zip is generated.

2. Install ssf2img command  
    Unzip ssf2img.zip in somewhere you want

3. Convert ssf to svg  
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img > foo.svg`

4. Convert ssf to svg with style
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img -s style.json > foo.svg`

5. Convert ssf to png with style
    `cat foo.ssf | /path/to/ssf2img/bin/ssf2img -f png -s style.json > foo.png`

There is an example file example1.ssf in the ./resources dir and a style.json example in the ./examples dir.

![Example1](https://github.com/mindboard/ssf2img/blob/master/resources/example1.svg)


## License

See the LICENSE file for license rights and limitations (MIT).
