package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class FormattedTextTag(val value: String, val bold: Boolean, val italic: Boolean, val children: MutableList<PageTag>): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        var prefix = if(italic) "''" else ""
        if(bold)
            prefix += "'''"
        builder.append("$prefix$value$prefix")
        for(child in children)
            child.build(builder, false)
        if(fancy)
            builder.append('\n')
    }

    operator fun plus(other: PageTag): FormattedTextTag {
        children.add(other)
        return this
    }
}