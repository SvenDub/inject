package nl.svendubbeld.inject.exception

class TypeNotResolvedException(type: Class<*>) : Exception("Could not resolve ${type.toString()}.") {
    var type: Class<*> = type;
}