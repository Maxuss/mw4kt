import space.maxus.mw4kt.dsl.page
import kotlin.test.Test

object Test {
    @Test
    fun testing() {
        val page = page("hello_world") {
            +h2(text("Hello, World!"))
            +(text("Check ") + link("https:///github.com/Maxuss/", "my repo!", true))
            +list(true) {
                +text("First")
                +text("Second")
                +text("Third")
            }
            +transclude("Other thing")
            +h3(text("Another header!"))
            +define("Test", text("some tests"))
            +table {
                "First" with text("First!")
                "Second" with text("This is second!")
                "Third" with text("This is third!", bold = true)
            }
        }
        println(page.build())
    }
}