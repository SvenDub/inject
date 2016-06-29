package nl.svendubbeld.inject.exception

/**
 * Thrown when the requested type could not be resolved.
 */
class TypeNotResolvedException(type: Class<*>) : Exception("Could not resolve ${type.toString()}.") {
    /**
     * The type that could not be resolved.
     */
    val type: Class<*> = type;
}