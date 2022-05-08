package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class ImageTag(val file: String, val caption: String?): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append("[[File:$file|thumb|frame|$caption]]")
        if(fancy)
            builder.append('\n')
    }

}