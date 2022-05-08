package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class DefinitionTag(val term: String, val definition: PageTag): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append(";$term: ")
        definition.build(builder, false)
        if(fancy)
            builder.append('\n')
    }
}