package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class RawTextTag(val inner: String, val children: MutableList<PageTag>): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append(this.inner)
        for(child in children)
            child.build(builder, false)
        if(fancy)
            builder.append('\n')
    }

    operator fun plus(other: PageTag): RawTextTag {
        children.add(other)
        return this
    }
}