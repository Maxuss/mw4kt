package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class SimpleLinkTag(val link: String, val display: String?): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append("[[$link")
        if(display != null)
            builder.append("|$display")
        builder.append("]]")
        if(fancy)
            builder.append('\n')
    }
}