package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class TableTag(val table: HashMap<String, PageTag>): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append("{| class=\"wikitable\"\n")
        for((key, tag) in table) {
            builder.append("! $key\n| ")
            tag.build(builder, false)
            if(table.keys.last() != key)
                builder.append("\n|-\n")
            else
                builder.append("\n|}")
        }
        if(fancy)
            builder.append('\n')
    }

}