package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class ForwardingLinkTag(val link: String, val description: String): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append("[$link $description]")
        if(fancy)
            builder.append('\n')
    }
}