package space.maxus.mw4kt.util

operator fun String.times(times: Int): String = this.repeat(times)
operator fun Char.times(times: Int): String = this.toString().repeat(times)