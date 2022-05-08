package space.maxus.mw4kt.tag

import space.maxus.mw4kt.PageTag

data class TemplateTag(val template: String, val call: String): PageTag {
    override fun build(builder: StringBuilder, fancy: Boolean) {
        builder.append("{{$template|$call}}")
        if(fancy)
            builder.append('\n')
    }

}