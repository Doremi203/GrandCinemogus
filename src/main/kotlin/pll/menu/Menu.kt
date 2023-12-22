package pll.menu

interface Menu {
    fun show()
    fun processInputIfNotExit(): Boolean
    fun handle()
}