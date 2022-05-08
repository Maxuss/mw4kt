package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class RedirectTag(val page: String): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append("#REDIRECT [[$page]]")
        if(fancy)
            builder.append('\n')
    }
}