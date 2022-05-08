package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag
import space.maxus.mw4kt.util.times

data class HierarchicalListTag(val children: HashMap<Int, List<PageTag>>): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        for((level, tags) in children.toSortedMap()) {
            val prefix = '*' * level
            for(tag in tags) {
                builder.append(prefix)
                tag.build(builder, false)
                builder.append('\n')
            }
        }
        if(fancy)
            builder.append('\n')
    }
}