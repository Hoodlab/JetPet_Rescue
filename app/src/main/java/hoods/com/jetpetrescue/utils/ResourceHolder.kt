package hoods.com.jetpetrescue.utils

sealed class ResourceHolder<T>(
    val data: T? = null,
    val throwable: Throwable? = null,
) {
    class Loading<T> : ResourceHolder<T>()

    class Success<T>(data: T) : ResourceHolder<T>(data = data)

    class Error<T>(throwable: Throwable?) : ResourceHolder<T>(throwable = throwable)
}