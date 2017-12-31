package me.kwsk.odptlibrary.repository

/**
 * 指定したIDの事業者ではその操作を行えないことを示すException
 */
class InvalidOdptOperationException(message: String? = "") : Throwable(message)