package space.maxus.mw4kt

interface PageTag {
    fun build(builder: StringBuilder, fancy: Boolean = true)
}