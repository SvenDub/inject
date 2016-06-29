package nl.svendubbeld.inject.exception

/**
 * Thrown when the requested type could not be resolved.
 */
class TypeNotResolvedException : Exception {
    /**
     * The type that could not be resolved.
     */
    var type: Class<*>;

    constructor(type: Class<*>) : super("Could not resolve ${type.toString()}.") {
        this.type = type;
    }

    constructor(type: Class<*>, innerException: Exception) : super("Could not resolve ${type.toString()}.", innerException) {
        this.type = type;
    }
}