package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag
import space.maxus.mw4kt.util.times

data class HeaderTag(val level: Int, val inner: PageTag): PageTag  {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        val lvlFmt = '=' * level
        builder.append(lvlFmt)
        if(inner is HeaderTag)
            throw AssertionError("Can not have a header inside a header!")
        inner.build(builder, false)
        builder.append(lvlFmt)
        if(fancy)
            builder.append('\n')
    }
}