package com.gustavosc.rickandmortyapi.ui.main.utils


enum class StatusResource {
    SUCCESS,
    ERROR,
    LOADING
}
data class ResourceBody<out T>(val status: StatusResource, val data: T? = null, val message: String? = null) {
    companion object {
        fun <T> success(data: T): ResourceBody<T> =
            ResourceBody(
                status = StatusResource.SUCCESS,
                data = data,
                message = null
            )
        fun <T> error(message: String): ResourceBody<T> =
            ResourceBody(
                status = StatusResource.ERROR,
                data = null,
                message = message
            )
        fun <T> loading(data: T? = null): ResourceBody<T> =
            ResourceBody(
                status = StatusResource.LOADING,
                data = null,
                message = null
            )
    }
}