package space.maxus.mw4kt

class Page private constructor(val name: String) {
    companion object {
        fun create(name: String) = Page(name)
        fun ofTree(name: String, tags: List<PageTag>) = Page(name).apply { tree = tags }
    }
    private var tree: List<PageTag> = listOf()

    fun build(): String {
        val builder = StringBuilder()
        for(element in tree)
            element.build(builder, true)
        return builder.toString()
    }
}