import java.util.*

enum class FilteringOperation(val value: String) {
    EQ("eq"),
    NEQ("neq"),
    GT("gt"),
    LT("lt"),
    GTE("gte"),
    LTE("lte"),
    UNDEFINED("undefined");

    companion object {
             fun fromValue(value: String?): FilteringOperation {
                 return Arrays.stream(values())
                     .filter { e -> e.value == value }
                     .findFirst()
                     .orElse(UNDEFINED)
             }
    }
}