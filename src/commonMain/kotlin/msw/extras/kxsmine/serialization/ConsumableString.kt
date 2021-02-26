package msw.extras.kxsmine.serialization

internal class ConsumableString(
    private var str: String? = null
) {
    internal fun consume(): String {
        val ret = str ?: ""
        str = null
        return ret
    }

    internal fun supply(newString: String) {
        check(str == null) { "String was not yet consumed" }
        str = newString
    }
}