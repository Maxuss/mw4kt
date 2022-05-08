package space.maxus.mw4kt.dsl

import space.maxus.mw4kt.Page
import space.maxus.mw4kt.PageTag
import space.maxus.mw4kt.tag.*

@WikiDsl
inline fun page(name: String, ctor: PageBuilder.() -> Unit) = PageBuilder(name).apply(ctor).build()

@WikiDsl
class PageBuilder(val name: String) {
    private val tree: MutableList<PageTag> = mutableListOf()

    @WikiDsl
    fun h2(tag: PageTag) = header(tag, 2)
    @WikiDsl
    fun h3(tag: PageTag) = header(tag, 3)
    @WikiDsl
    fun h4(tag: PageTag) = header(tag, 4)

    @WikiDsl
    operator fun PageTag.unaryPlus() = tree.add(this)
    @WikiDsl
    fun header(text: PageTag, level: Int = 2) = HeaderTag(level, text)
    @WikiDsl
    inline fun table(builder: TableBuilder.() -> Unit) = TableBuilder().apply(builder).build()
    @WikiDsl
    fun template(template: String, call: String) = TemplateTag(template, call)
    @WikiDsl
    fun transclude(page: String, description: String? = null) = TrasnclusiveTag(page, description)
    @WikiDsl
    fun link(link: String, display: String? = null, actualLink: Boolean = false) = if(actualLink) ForwardingLinkTag(link, display ?: link) else SimpleLinkTag(link, display)
    @WikiDsl
    inline fun hierarchicalList(builder: HierarchicalListBuilder.() -> Unit) = HierarchicalListBuilder().apply(builder).build()
    @WikiDsl
    inline fun list(numbered: Boolean = false, builder: ListBuilder.() -> Unit) = ListBuilder(numbered).apply(builder).build()
    @WikiDsl
    fun image(file: String, caption: String? = null) = ImageTag(file, caption)
    @WikiDsl
    fun define(term: String, description: PageTag) = DefinitionTag(term, description)
    @WikiDsl
    fun redirect(page: String) = RedirectTag(page)
    @WikiDsl
    fun add(tag: PageTag) = tree.add(tag)
    @WikiDsl
    fun text(text: String) = RawTextTag(text, mutableListOf())
    @WikiDsl
    fun text(text: String, italic: Boolean = false, bold: Boolean = false) = FormattedTextTag(text, bold, italic, mutableListOf())

    fun build() = Page.ofTree(name, tree)
}

class TableBuilder {
    private val inner: HashMap<String, PageTag> = hashMapOf()
    @WikiDsl
    infix fun String.with(tag: PageTag) = inner.put(this, tag)
    @WikiDsl
    fun build() = TableTag(inner)
}

class HierarchicalListBuilder {
    private val inner: HashMap<Int, MutableList<PageTag>> = hashMapOf()

    @WikiDsl
    operator fun Pair<Int, PageTag>.unaryPlus() = inner.put(first, inner[first]!!.apply { add(second) })

    @WikiDsl
    @Suppress("UNCHECKED_CAST")
    fun build() = HierarchicalListTag(inner as HashMap<Int, List<PageTag>>)
}

class ListBuilder(private val numbered: Boolean) {
    private val inner: MutableList<PageTag> = mutableListOf()

    @WikiDsl
    operator fun PageTag.unaryPlus() = inner.add(this)

    @WikiDsl
    fun build() = if(numbered) NumberedListTag(inner) else SimpleListTag(inner)
}

