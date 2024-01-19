package com.shamsheev.wildberries.api.statistics.filter

import mu.KotlinLogging
import org.springframework.core.annotation.Order
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter
@Order(1)
class LogFilter : Filter {
    override fun doFilter(
        servletRequest: ServletRequest?,
        servletResponse: ServletResponse?,
        filterChain: FilterChain?,
    ) {
        log.info { "request: ${getRequestInfo(servletRequest)} response:${getResponseInfo(servletResponse)}" }
        filterChain!!.doFilter(servletRequest, servletResponse)
    }

    private fun getRequestInfo(servletRequest: ServletRequest?): String {
        val request = servletRequest as HttpServletRequest
        return request.method.toString() + " " +
                request.requestURI + " " +
//                request.headerNames.toList().map { it + " " + request.getHeader(it) } +
                request.parameterMap.toList().map { it.first + " " + it.second.toList() }
    }

    private fun getResponseInfo(servletResponse: ServletResponse?): String {
        val response = servletResponse as HttpServletResponse
        return response.status.toString()
    }

    companion object {
        val log = KotlinLogging.logger {}
    }
}