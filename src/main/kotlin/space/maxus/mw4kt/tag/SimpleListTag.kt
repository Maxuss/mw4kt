package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

open class SimpleListTag(private val children: List<PageTag>, private val prefix: Char = '*'): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        for(child in children) {
            builder.append("* ")
            child.build(builder, false)
            if(children.last() != child)
                builder.append('\n')
        }
        if(fancy)
            builder.append('\n')
    }
}